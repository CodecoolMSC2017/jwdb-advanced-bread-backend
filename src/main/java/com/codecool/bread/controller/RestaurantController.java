package com.codecool.bread.controller;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.simple.OwnerService;
import com.codecool.bread.service.simple.RestaurantService;
import com.codecool.bread.service.simple.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/owner/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Set<Restaurant> getRestaurantsByOwnerId(Principal principal) {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerService.getOwner(principal.getName()).getId());
        return restaurantService.getRestaurantsByOwnerIdFromDb(owner.getId());
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("restaurantId") int restaurantId, Principal principal) throws RestaurantNotFoundException {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerService.getOwner(principal.getName()).getId());
        Restaurant restaurant = ownerService.getRestaurantByIdFromDb(restaurantId, owner.getId());
        if (restaurant == null) {
            throw new RestaurantNotFoundException();
        } else
            return restaurant;
    }

    @PostMapping("")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant, Principal principal) {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerService.getOwner(principal.getName()).getId());
        return ownerService.addRestaurantToDb(restaurant, owner.getId());
    }

    @PutMapping("/{restaurantId}")
    public Restaurant changeRestaurantDetails(@RequestBody Restaurant restaurant, Principal principal) {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerService.getOwner(principal.getName()).getId());
        return ownerService.editRestaurantDb(restaurant, owner.getId());
    }
}
