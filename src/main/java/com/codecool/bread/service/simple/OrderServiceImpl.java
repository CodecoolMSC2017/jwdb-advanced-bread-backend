package com.codecool.bread.service.simple;

import com.codecool.bread.exception.CustomerOrderNotFoundException;
import com.codecool.bread.exception.OrderItemNotFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.*;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.model.dto.RestaurantDto;
import com.codecool.bread.model.dto.SeatDto;
import com.codecool.bread.model.dto.TableDto;
import com.codecool.bread.repository.*;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Set<CustomerOrder> getAllCustomerOrderBySeat(int seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (!seat.isPresent()) {
            throw new SeatNotFoundException();
        }
        return seat.get().getCustomerOrders();
    }

    @Override
    public CustomerOrder getCustomerOrderById(int seatId, int customerOrderId) throws CustomerOrderNotFoundException {
        Optional<CustomerOrder> customerOrder = customerOrderRepository.findByIdAndSeatId(customerOrderId, seatId);
        if (customerOrder.isPresent()) {
            return customerOrder.get();
        } throw new CustomerOrderNotFoundException();
    }

    @Override
    public OrderItem getOrderItem(int seatId, int customerOrderId) {
        return customerOrderRepository.findByIdAndSeatId(customerOrderId, seatId).get().getOrderItem();
    }

    @Override
    public OrderItem add(OrderDto orderDto, int seatId, int loggedInEmployeeId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (!seat.isPresent()) {
            throw new SeatNotFoundException();
        }
        Table table = tableRepository.findBySeatId(seatId);
        int tableId = table.getId();
        Restaurant restaurant = restaurantRepository.findByTableId(tableId);
        int restaurantId = restaurant.getId();
        if (table.getEmployee() == null) {
            table.setEmployee(employeeService.getById(loggedInEmployeeId));
        }
        Item item = itemService.getByIdAndRestaurantId(orderDto.getItemId(), restaurantId);
        OrderItem orderItem = new OrderItem();
        CustomerOrder customerOrder = new CustomerOrder();
        orderItem.setItem(item);
        orderItem.setQuantity(orderDto.getQuantity());
        orderItem.setComment(orderDto.getComment());
        orderItem.setEnabled(true);
        OrderItem savedOrderItem = orderItemRepository.saveAndFlush(orderItem);
        customerOrder.setSeat(seat.get());
        customerOrder.setEmployee(employeeService.getById(table.getEmployee().getId(), restaurantId));
        customerOrder.setOrderItem(orderItem);
        customerOrderRepository.saveAndFlush(customerOrder);
        seat.get().getCustomerOrders().add(customerOrder);
        seatRepository.saveAndFlush(seat.get());
        return savedOrderItem;
    }

    @Override
    public void setEmployeeToTable(int employeeId, int tableId) {
        Employee employee = employeeService.getById(employeeId);
        Optional<Table> table =tableRepository.findById(tableId);
        if (!table.isPresent()) {
            throw new TableNotFoundException();
        }
        table.get().setEmployee(employee);
        tableRepository.saveAndFlush(table.get());
    }

    @Override
    public TableDto getActiveOrdersByTable(int tableId) {
        Set<Seat> seats = seatService.getAllSeatsByTableId(tableId);
        Set<SeatDto> seatDtoSet = new HashSet<>();
        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto(seat.getId(), setEnabledOrderItemToCustomerOrder(seat.getCustomerOrders()));
            seatDtoSet.add(seatDto);
        }
        return new TableDto(tableId, seatDtoSet);
    }

    @Override
    public Invoice getInvoiceForTable(int tableId) {
        Invoice invoice = new Invoice(new BigDecimal(0));
        Set<Seat> seats = seatService.getEnableSeatsByTableId(tableId);
        for (Seat seat: seats) {
            calculateTotalPriceForSeat(invoice, seat);
        }
        invoice.setEnabled(true);
        return invoiceRepository.save(invoice);
    }

    @Override
    public RestaurantDto getActiveOrdersByRestaurant(int restaurantId) {
        Set<Table> tableSet = tableService.getAllTablesByRestaurantId(restaurantId);
        Set<TableDto> tableDtoSet = new HashSet<>();
        for (Table table : tableSet) {
            tableDtoSet.add(getActiveOrdersByTable(table.getId()));
        }
        return new RestaurantDto(restaurantId, tableDtoSet);
    }

    private Set<CustomerOrder>  setEnabledOrderItemToCustomerOrder(Set<CustomerOrder> customerOrders) {
        Set<CustomerOrder> result = new HashSet<>();
        for (CustomerOrder customerOrder: customerOrders) {
            if (customerOrder.isEnabled()) {
                CustomerOrder updatedCostumerOrder = new CustomerOrder();
                updateCustomerOrder(customerOrder, updatedCostumerOrder);
                int orderItemId = customerOrder.getOrderItem().getId();
                updatedCostumerOrder.setOrderItem(getOrderItemByIdAndEnabledTrue(orderItemId));
                result.add(updatedCostumerOrder);
            }
        }
        return result;
    }

    private void updateCustomerOrder(CustomerOrder customerOrder, CustomerOrder updatedCostumerOrder) {
        updatedCostumerOrder.setId(customerOrder.getId());
        updatedCostumerOrder.setEnabled(customerOrder.isEnabled());
        updatedCostumerOrder.setOrderingTime(customerOrder.getOrderingTime());
    }

    private OrderItem getOrderItemByIdAndEnabledTrue(int orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findByIdAndEnabledTrue(orderItemId);
        if(orderItem.isPresent()) {
            return orderItem.get();
        } else {
            throw new OrderItemNotFoundException();
        }
    }

    private void calculateTotalPriceForSeat(Invoice invoice, Seat seat) {
        Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(seat.getId());
        for (CustomerOrder customerOrder: customerOrderSet) {
            invoice.setTotal(invoice.getTotal().add(calculateTotalPriceForCustomerOrder(customerOrder)));
        }
    }

    private BigDecimal calculateTotalPriceForCustomerOrder(CustomerOrder customerOrder) {
        return calculateTotalPriceForOrderItem(customerOrder.getOrderItem());
    }

    private BigDecimal calculateTotalPriceForOrderItem(OrderItem orderItem) {
        int quantity = orderItem.getQuantity();
        BigDecimal price = orderItem.getItem().getPrice();
        return BigDecimal.valueOf(quantity).multiply(price);
    }

    private void setTableAsPaid(int tableId) {
        Set<Seat> seats = seatService.getEnableSeatsByTableId(tableId);
        for (Seat seat: seats) {
            setSeatAsPaid(seat.getId());
        }
    }

    private void setSeatAsPaid(int seatId) {
        Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(seatId);
        for (CustomerOrder customerOrder: customerOrderSet) {
            customerOrder.setEnabled(false);
            customerOrderRepository.saveAndFlush(customerOrder);
        }
    }

}
