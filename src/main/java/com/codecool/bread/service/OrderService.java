package com.codecool.bread.service;

import com.codecool.bread.exception.OrderItemNotFoundException;
import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.*;

import java.util.List;

public interface OrderService {
    List<CustomerOrder> getAllCustomerOrderBySeat(int seatId);

    CustomerOrder getCustomerOrderById(int seatId, int customerOrderId);

    OrderItem getOrderItem(int seatId, int customerOrderId);

    List<OrderKitchenDto> getNewOrderItems(String category, String username);

    OrderItem add(OrderDto orderDto, int seatId, int loggedInEmployeeId);

    void setEmployeeToTable(int employeeId, int tableId);

    TableDto getActiveOrdersByTable(int tableId);

    RestaurantDto getActiveOrdersByRestaurant(int restaurantId);

    InvoiceDto createInvoiceForTable(int tableId, int employeeId);

    //Invoice createInvoiceForSeat(int seatId);

    InvoiceDto createInvoiceForSeats(int[] seatIds, int employeeId);

    void setInvoiceAsPaid(int invoiceId);

    SeatDto getActiveOrdersBySeat(int seatId);

    void deleteOrderFromSeat(int seatId, int orderItemId);

    List<OrderKitchenDto> setOrderKitchenDtoReady(OrderKitchenDto orderDto, String name) throws OrderItemNotFoundException;
}