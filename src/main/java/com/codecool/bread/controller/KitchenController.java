package com.codecool.bread.controller;


import com.codecool.bread.model.dto.OrderKitchenDto;
import com.codecool.bread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {


    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<OrderKitchenDto> getPendingOrders(Principal principal, @RequestParam("category") String category) {
        return orderService.getNewOrderItems(category.toUpperCase(), principal.getName());
    }

    @PutMapping("")

    public List<OrderKitchenDto> setOrderedItemToReady(@RequestBody OrderKitchenDto orderDto, Principal principal) {
        return orderService.setOrderKitchenDtoReady(orderDto, principal.getName());
    }
}


