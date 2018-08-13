package com.codecool.bread.controller;


import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.OrderKitchenDto;
import com.codecool.bread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
public class RestChefController {


    @Autowired
    OrderService orderService;

    @GetMapping("")
    public List<OrderKitchenDto> getPendingOrders(@RequestParam("category") String category) {
        return orderService.getNewOrderItems(category);
    }



}
