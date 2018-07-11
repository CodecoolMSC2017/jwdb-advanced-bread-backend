package com.codecool.bread.controller;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/{ownerId}/restaurants")
public class RestRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/all")
    public Iterable<Restaurant> getRestaurantsByOwnerId(@PathVariable("ownerId") int ownerId) {
        return restaurantService.getRestaurantsByOwnerId(ownerId);
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("ownerId") int ownerId,@PathVariable("restaurantId") int restaurantId) throws RestaurantNotFoundException {
        return restaurantService.getRestaurantById(restaurantId);
    }
}

