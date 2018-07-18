package com.codecool.bread.service;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.repository.CustomerOrderRepository;
import com.codecool.bread.repository.OrderItemRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.service.simple.EmployeeService;
import com.codecool.bread.service.simple.ItemService;
import com.codecool.bread.service.simple.OrderService;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Set<CustomerOrder> getAllCustomerOrderBySeatFromDb(int restaurantId, int tableId, int seatId) {
        return restaurantService.getSeatByIdFromDb(restaurantId, tableId, seatId).getOrders();
    }

    @Override
    public CustomerOrder getCustomerOrderByIdFromDb(int seatId, int customerOrderId) {
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
        customerOrder.setEmployee(employeeService.getByIdFromDb(restaurantId, employeeId));
        customerOrder.setOrderItem(orderItem);
        customerOrderRepository.save(customerOrder);
        seat.getOrders().add(customerOrder);
        seatRepository.save(seat);
        return orderDto;
    }
}
