package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.OwnerServiceImpl;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/{ownerId}")
public class RestOwnerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping("")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerId);
        if (owner == null) {
            throw new OwnerNotFoundException();
        } else
            return owner;
    }

    @GetMapping("/restaurant")
    public Iterable<Restaurant> getRestaurantsByOwnerId(@PathVariable("ownerId") int ownerId) {
        List<Restaurant> restaurants = ownerService.getRestaurantsByOwnerIdFromDb(ownerId);
        if (restaurants.size() == 0) {
            throw new RestaurantNotFoundException();
        } else
            return restaurants;
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

    @PutMapping("/restaurant/{restaurantId}")
    public Restaurant changeRestaurantDetails(@RequestBody Restaurant restaurant, @PathVariable("ownerId") int ownerId) {
        return ownerService.editRestaurantDb(restaurant, ownerId);
    }
}
