package com.codecool.bread.service;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.repository.CustomerOrderRepository;
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
}
