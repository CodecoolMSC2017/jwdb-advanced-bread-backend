package com.codecool.bread.service.simple;

import com.codecool.bread.exception.CustomerOrderNotFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.*;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.repository.*;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
        Item item = itemService.getItemById(orderDto.getItemId(), restaurantId);
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
}
