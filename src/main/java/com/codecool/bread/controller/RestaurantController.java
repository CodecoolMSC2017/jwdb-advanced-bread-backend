package com.codecool.bread.controller;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.simple.OwnerService;
import com.codecool.bread.service.simple.RestaurantService;
import com.codecool.bread.service.simple.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/owners/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserService userService;

    @GetMapping("/restaurant")
    public Set<Restaurant> getRestaurantsByOwnerId(Principal principal) {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerService.getOwner(principal.getName()).getId());
        return restaurantService.getRestaurantsByOwnerIdFromDb(owner.getId());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) throws RestaurantNotFoundException {
        Restaurant restaurant = ownerService.getRestaurantByIdFromDb(restaurantId, ownerId);
        if (restaurant == null) {
            throw new RestaurantNotFoundException();
        } else
            return restaurant;
    }

    @PostMapping("/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant, @PathVariable("ownerId") int ownerId) {
        return ownerService.addRestaurantToDb(restaurant, ownerId);
    }

    @PostMapping("/restaurant/{restaurantId}/table")
    public Table addTable(@RequestBody Table table, @PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.addOrModifyTableToDb(table, ownerId, restaurantId);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public Restaurant changeRestaurantDetails(@RequestBody Restaurant restaurant, @PathVariable("ownerId") int ownerId) {
        return ownerService.editRestaurantDb(restaurant, ownerId);
    }
}
