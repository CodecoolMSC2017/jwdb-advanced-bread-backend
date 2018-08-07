package com.codecool.bread.service;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.Table;
import com.codecool.bread.model.dto.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface OrderService {
    List<CustomerOrder> getAllCustomerOrderBySeat(int seatId);

    CustomerOrder getCustomerOrderById(int seatId, int customerOrderId);

    OrderItem getOrderItem(int seatId, int customerOrderId);

    OrderItem add(OrderDto orderDto, int seatId, int loggedInEmployeeId);

    void setEmployeeToTable(int employeeId, int tableId);

    TableDto getActiveOrdersByTable(int tableId);

    RestaurantDto getActiveOrdersByRestaurant(int restaurantId);

    InvoiceDto createInvoiceForTable(int tableId, int employeeId);

    //Invoice createInvoiceForSeat(int seatId);

    Invoice createInvoiceForSeats(int[] seatIds);

    void setInvoiceAsPaid(int invoiceId);

    SeatDto getActiveOrdersBySeat(int seatId);

    void deleteOrderFromSeat(int seatId, int orderItemId);
}