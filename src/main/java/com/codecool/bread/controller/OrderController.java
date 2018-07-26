package com.codecool.bread.controller;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/seat/{seatId}/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public Set<CustomerOrder> getAllCustomerOrder(@PathVariable("seatId") int seatId) {
        return orderService.getAllCustomerOrderBySeat(seatId);
    }

    @GetMapping("/seat/{seatId}/customer-order/{customerOrderId}")
    public CustomerOrder getCustomerOrder(@PathVariable("seatId") int seatId, @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getCustomerOrderById(seatId, customerOrderId);
    }

    @GetMapping("/seat/{seatId}/orderitem")
    public OrderItem getOrderItem(@PathVariable("seatId") int seatId, @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getOrderItem(seatId, customerOrderId);
    }

    @PostMapping("/seat/{seatId}/order")
    public OrderDto addOrder(@RequestBody OrderDto orderDto, @PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        return orderService.addOrderToDb(orderDto, restaurantId, employeeId, tableId, seatId);
    }
}
