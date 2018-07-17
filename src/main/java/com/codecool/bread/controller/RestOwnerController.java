package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Address;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.OwnerServiceImpl;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/{ownerId}")
public class RestOwnerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping("")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        return ownerService.getOwnerByIdFromDb(ownerId);
    }

    @GetMapping("/restaurant")
    public Iterable<Restaurant> getRestaurantsByOwnerId(@PathVariable("ownerId") int ownerId) {
        return ownerService.getRestaurantsByOwnerIdFromDb(ownerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) throws RestaurantNotFoundException {
        return ownerService.getRestaurantByIdFromDb(restaurantId, ownerId);
    }

    @PostMapping("/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant, @PathVariable("ownerId") int ownerId) {
        return ownerService.addRestaurantToDb(restaurant, ownerId);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public Restaurant changeRestaurantDetails(@RequestBody Restaurant restaurant,@PathVariable("ownerId") int ownerId) {
        return ownerService.editRestaurantDb(restaurant, ownerId);
    }
}
