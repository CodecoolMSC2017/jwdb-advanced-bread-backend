package com.codecool.bread.service.simple;

import com.codecool.bread.exception.CustomerOrderNotFoundException;
import com.codecool.bread.exception.OrderItemNotFoundException;
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
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl extends AbstractService implements OrderService { // TODO remove empty customerOrders when sending orders to fron end

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public Set<CustomerOrder> getAllCustomerOrderBySeat(int seatId) {
        Seat seat = seatService.getById(seatId);
        return seat.getCustomerOrders();
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
        Seat seat = seatService.getById(seatId);
        Table table = seat.getTable();
        Restaurant restaurant = table.getRestaurant();
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
        customerOrder.setSeat(seat);
        customerOrder.setEmployee(employeeService.getById(table.getEmployee().getId(), restaurantId));
        customerOrder.setOrderItem(orderItem);
        if (customerOrder.getArrivalTime() == null) {
            customerOrder.setArrivalTime(LocalDateTime.now());
        }
        customerOrderRepository.saveAndFlush(customerOrder);
        seat.getCustomerOrders().add(customerOrder);
        seatRepository.saveAndFlush(seat);
        return savedOrderItem;
    }

    @Override
    public void setEmployeeToTable(int employeeId, int tableId) {
        Employee employee = employeeService.getById(employeeId);
        Table table = tableService.getById(tableId);
        table.setEmployee(employee);
        tableRepository.saveAndFlush(table);
    }

    @Override
    public TableDto getActiveOrdersByTable(int tableId) {
        Set<Seat> seats = seatService.getAllSeatsByTableId(tableId);
        Set<SeatDto> seatDtoSet = new HashSet<>();
        String tableName = tableService.getById(tableId).getName();
        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto(seat.getId(), setEnabledOrderItemToCustomerOrder(seat.getCustomerOrders()));
            if (!seatDto.getCustomerOrderSet().isEmpty()) {
                seatDtoSet.add(seatDto);
            }
        }
        return new TableDto(tableId, tableName, seatDtoSet);
    }

    @Override
    public RestaurantDto getActiveOrdersByRestaurant(int restaurantId) {
        Set<Table> tableSet = tableService.getAllTablesByRestaurantId(restaurantId);
        Set<TableDto> tableDtoSet = new HashSet<>();
        for (Table table : tableSet) {
            tableDtoSet.add(getActiveOrdersByTable(table.getId()));
        }
        return new RestaurantDto(restaurantId,  tableDtoSet);
    }

    @Override
    public Invoice createInvoiceForTable(int tableId) {
        Set<Seat> seats = seatService.getEnableSeatsByTableId(tableId);
        BigDecimal total = new BigDecimal(0);
        for (Seat seat: seats) {
            total = total.add(calculateTotalPriceForSeat(seat));
        }
        Invoice invoice = new Invoice(total);
        invoice.setEnabled(true);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice createInvoiceForSeat(int seatId) {
        Seat seat = seatService.getById(seatId);
        BigDecimal total = calculateTotalPriceForSeat(seat);
        Invoice invoice = new Invoice(total);
        //invoice.setEnabled(true);
        int invoiceId = invoiceRepository.save(invoice).getId();
        Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(seatId);
        setInvoiceForCustomerOrders(invoiceService.getById(invoiceId), customerOrderSet);
        return invoiceService.getById(invoiceId);
    }

    @Override
    public Invoice createInvoiceForSeats(int[] seatIds) {
        BigDecimal total = new BigDecimal(0);
        for (int i : seatIds) {
            total = total.add(calculateTotalPriceForSeat(seatService.getById(i)));
        }
        Invoice invoice = new Invoice(total);
        int invoiceId = invoiceRepository.save(invoice).getId();
        setInvoiceForSeats(seatIds, invoiceId);
        //invoice.setEnabled(true);
        return invoiceService.getById(invoiceId);
    }

    private void setInvoiceForSeats(int[] seatIds, int invoiceId) {
        for (int i : seatIds) {
            Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(i);
            setInvoiceForCustomerOrders(invoiceService.getById(invoiceId), customerOrderSet);
        }
    }

    private void setInvoiceForCustomerOrders(Invoice invoice, Set<CustomerOrder> customerOrderSet) {
        for (CustomerOrder customerOrder: customerOrderSet) {
            customerOrder.setInvoice(invoice);
            customerOrderRepository.save(customerOrder);
        }
    }

    @Override
    public void setInvoiceAsPaid(int invoiceId) {
        Set<Seat> seatSet = seatRepository.findByInvoiceId(invoiceId);
        Invoice invoice = invoiceService.getById(invoiceId);
        for (Seat seat : seatSet) {
           setSeatAsPaid(seat.getId());
        }
        invoice.setPaid(true);
        invoiceRepository.save(invoice);
    }

    public Set<Seat> getByInvoideId(int invoiceId) {
        return seatRepository.findByInvoiceId(invoiceId);
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

    private BigDecimal calculateTotalPriceForSeat(Seat seat) {
        BigDecimal total = new BigDecimal(0);
        Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(seat.getId());
        for (CustomerOrder customerOrder: customerOrderSet) {
            total = total.add(calculateTotalPriceForCustomerOrder(customerOrder));
        }
        return total;
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
