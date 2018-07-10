package com.codecool.bread.controller;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RestRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurant() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        return restaurantService.getRestaurantById(id);
    }
}
