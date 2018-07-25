package com.codecool.bread.service.simple;

import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.*;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.repository.CustomerOrderRepository;
import com.codecool.bread.repository.OrderItemRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
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

    @Override
    public Set<CustomerOrder> getAllCustomerOrderBySeat(int seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (!seat.isPresent()) {
            throw new SeatNotFoundException();
        }
        return seat.get().getOrders();
    }

    @Override
    public CustomerOrder getCustomerOrderById(int seatId, int customerOrderId) {
        return customerOrderRepository.findByIdAndSeatId(customerOrderId, seatId);
    }

    @Override
    public OrderItem getOrderItem(int seatId, int customerOrderId) {
        return customerOrderRepository.findByIdAndSeatId(customerOrderId, seatId).getOrderItem();
    }

    @Override
    public OrderDto addOrderToDb(OrderDto orderDto, int restaurantId, int employeeId, int tableId, int seatId) {
        Item item = itemService.getItemById(orderDto.getItemId(), restaurantId);
        OrderItem orderItem = new OrderItem();
        CustomerOrder customerOrder = new CustomerOrder();
        Seat seat = seatRepository.findByIdAndTableId(seatId, tableId);
        orderItem.setItem(item);
        orderItem.setQuantity(orderDto.getQuantity());
        orderItem.setComment(orderDto.getComment());
        orderItemRepository.save(orderItem);
        customerOrder.setSeat(seat);
        customerOrder.setEmployee(employeeService.getById(employeeId, restaurantId));
        customerOrder.setOrderItem(orderItem);
        customerOrderRepository.save(customerOrder);
        seat.getOrders().add(customerOrder);
        seatRepository.save(seat);
        return orderDto;
    }

    @Override
    public void setEmployeeToTableInDb(int employeeId, int tableId, int restaurantId) {
        Employee employee = employeeService.getById(employeeId, restaurantId);
        Optional<Table> table =tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
        table.get().setEmployee(employee);
        tableRepository.save(table.get());
    }
}
