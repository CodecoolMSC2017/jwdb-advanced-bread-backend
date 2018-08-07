package com.codecool.bread.service.simple;

import com.codecool.bread.exception.CustomerOrderNotFoundException;
import com.codecool.bread.exception.OrderItemNotFoundException;
import com.codecool.bread.model.*;
import com.codecool.bread.model.dto.*;
import com.codecool.bread.repository.*;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl extends AbstractService implements OrderService {

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
    public List<CustomerOrder> getAllCustomerOrderBySeat(int seatId) {
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
    public RestaurantDto getActiveOrdersByRestaurant(int restaurantId) {
        Set<Table> tableSet = tableService.getAllTablesByRestaurantId(restaurantId);
        List<TableDto> tableDtoList = new ArrayList<>();
        for (Table table : tableSet) {
            tableDtoList.add(getActiveOrdersByTable(table.getId()));
        }
        return new RestaurantDto(restaurantId,  tableDtoList);
    }

    @Override
    public TableDto getActiveOrdersByTable(int tableId) {
        Set<Seat> seats = seatService.getAllSeatsByTableId(tableId);
        List<SeatDto> seatDtoList = new ArrayList<>();
        String tableName = tableService.getById(tableId).getName();
        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto(seat.getId(), setEnabledOrderItemToCustomerOrder(seat.getCustomerOrders()));
            if (!seatDto.getCustomerOrderList().isEmpty()) {
                seatDtoList.add(seatDto);
            }
        }
        return new TableDto(tableId, tableName, seatDtoList);
    }

    @Override
    public SeatDto getActiveOrdersBySeat(int seatId) {
        Seat seat = seatService.getById(seatId);
        return new SeatDto(seatId, setEnabledOrderItemToCustomerOrder(seat.getCustomerOrders()));
    }

    @Override
    public void deleteOrderFromSeat(int seatId, int orderItemId) {
        Seat seat = seatService.getById(seatId);
        for (CustomerOrder customerOrder : seat.getCustomerOrders()) {
            if (customerOrder.getOrderItem().getId().equals(orderItemId) && customerOrder.getOrderItem().getQuantity() == 1) {
                orderItemRepository.delete(customerOrder.getOrderItem());
            } else {
                customerOrder.getOrderItem().setQuantity(customerOrder.getOrderItem().getQuantity() - 1);
                orderItemRepository.save(customerOrder.getOrderItem());
            }
        }
    }

    // INVOICE SERVICES

    @Override
    public InvoiceDto createInvoiceForTable(int tableId, int employeeId) {
        Set<Seat> seats = seatService.getEnableSeatsByTableId(tableId);
        BigDecimal totalPriceForSeats = getTotalPriceForSeats(seats);
        Invoice invoice = new Invoice(totalPriceForSeats);
        setInvoiceForSeats(seats, invoiceRepository.save(invoice).getId());
        return createInvoiceDtoForTable(invoice, employeeId);
    }

    /*
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
    */
    @Override
    public Invoice createInvoiceForSeats(int[] seatIds) {
        BigDecimal total = getTotalPriceForSeats(seatIds);
        Invoice invoice = new Invoice(total);
        int invoiceId = invoiceRepository.save(invoice).getId();
        setInvoiceForSeats(seatIds, invoiceId);
        //invoice.setEnabled(true);
        return invoiceService.getById(invoiceId);
    }

    // HELPER METHODS

    private InvoiceDto createInvoiceDtoForTable(Invoice invoice, int employeeId) {
        int invoiceId = invoice.getId();
        LocalDateTime created = invoice.getDate();
        Address restaurantAddress = employeeService.getById(employeeId).getRestaurant().getAddress();
        BigDecimal totalPrice = invoice.getTotal();

        List<InvoiceItemDto> invoiceItemDtoList = new ArrayList<>();

        Set<CustomerOrder> customerOrderSet = customerOrderRepository.findByInvoiceId(invoiceId);
        for (CustomerOrder customerOrder : customerOrderSet) {
            int id = customerOrder.getOrderItem().getItem().getId();
            int quantity = customerOrder.getOrderItem().getQuantity();
            String itemName = customerOrder.getOrderItem().getItem().getName();
            BigDecimal unitPrice = customerOrder.getOrderItem().getItem().getPrice();
            InvoiceItemDto invoiceItemDto = new InvoiceItemDto(id, quantity, itemName, unitPrice);
            invoiceItemDtoList.add(invoiceItemDto);
        }
        return new InvoiceDto(invoiceId, created, employeeId,restaurantAddress, totalPrice, invoiceItemDtoList);
    }

    private void setInvoiceForSeats(int[] seatIds, int invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        for (int i : seatIds) {
            Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(i);
            setInvoiceForCustomerOrders(invoice, customerOrderSet);
        }
    }

    private void setInvoiceForSeats(Set<Seat> seats, int invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        for (Seat seat : seats) {
            Set<CustomerOrder> customerOrderSet = customerOrderRepository.findBySeatIdAndEnabledTrue(seat.getId());
            setInvoiceForCustomerOrders(invoice, customerOrderSet);
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
        Invoice invoice = invoiceService.getById(invoiceId);
        Set<Seat> seatSet = seatRepository.findByInvoiceId(invoice.getId());
        for (Seat seat : seatSet) {
           setSeatAsPaid(seat.getId());
        }
        invoice.setPaid(true);
        invoiceRepository.save(invoice);
    }

    public Set<Seat> getByInvoiceId(int invoiceId) {
        return seatRepository.findByInvoiceId(invoiceId);
    }

    private List<CustomerOrder>  setEnabledOrderItemToCustomerOrder(List<CustomerOrder> customerOrders) {
        List<CustomerOrder> result = new ArrayList<>();
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

    private List<CustomerOrder> summarizeCustomerOrder(List<CustomerOrder> customerOrders) {
        List<CustomerOrder> result = new ArrayList<>();
        return null;


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

    private BigDecimal getTotalPriceForSeats(int[] seatIds) {
        BigDecimal total = new BigDecimal(0);
        for (int i : seatIds) {
            total = total.add(calculateTotalPriceForSeat(seatService.getById(i)));
        }
        return total;
    }

    private BigDecimal getTotalPriceForSeats(Set<Seat> seats) {
        BigDecimal total = new BigDecimal(0);
        for (Seat seat: seats) {
            total = total.add(calculateTotalPriceForSeat(seat));
        }
        return total;
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
