package com.codecool.bread.controller;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.service.simple.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}/employee/{employeeId}/table/{tableId}/seat/{seatId}")
public class RestOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/customerorder")
    public Set<CustomerOrder> getAllCustomerOrderBySeat(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        return orderService.getAllCustomerOrderBySeatFromDb(restaurantId, tableId, seatId);
    }

    @GetMapping("/customerorder/{customerOrderId}")
    public CustomerOrder getCustomerOrder(@PathVariable("seatId") int seatId, @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getCustomerOrderByIdFromDb(seatId, customerOrderId);
    }

    @GetMapping("/orderitem")
    public OrderItem getOrderItem(@PathVariable("seatId") int seatId, @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getOrderItem(seatId, customerOrderId);
    }

    @PostMapping("order")
    public OrderDto addOrder(@RequestBody OrderDto orderDto, @PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        return orderService.addOrderToDb(orderDto, restaurantId, employeeId, tableId, seatId);
    }

}
