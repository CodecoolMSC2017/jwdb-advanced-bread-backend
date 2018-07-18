package com.codecool.bread.service.simple;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.OrderDto;

import java.util.Set;

public interface OrderService {
    Set<CustomerOrder> getAllCustomerOrderBySeatFromDb(int restaurantId, int tableId, int seatId);

    CustomerOrder getCustomerOrderByIdFromDb(int seatId, int customerOrderId);

    OrderItem getOrderItem(int seatId, int customerOrderId);

    OrderDto addOrderToDb(OrderDto orderDto, int restaurantId, int employeeId, int tableId, int seatId);
}