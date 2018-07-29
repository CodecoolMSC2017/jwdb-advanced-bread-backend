package com.codecool.bread.service;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.Table;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.model.dto.RestaurantDto;
import com.codecool.bread.model.dto.TableDto;

import java.security.Principal;
import java.util.Set;

public interface OrderService {
    Set<CustomerOrder> getAllCustomerOrderBySeat(int seatId);

    CustomerOrder getCustomerOrderById(int seatId, int customerOrderId);

    OrderItem getOrderItem(int seatId, int customerOrderId);

    OrderItem add(OrderDto orderDto, int seatId, int loggedInEmployeeId);

    void setEmployeeToTable(int employeeId, int tableId);

    TableDto getActiveOrdersByTable(int tableId);

    RestaurantDto getActiveOrdersByRestaurant(int restaurantId);

    Invoice getInvoiceForTable(int tableId);

    Invoice getInvoiceForSeat(int seatId);
}