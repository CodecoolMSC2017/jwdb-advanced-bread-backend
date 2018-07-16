package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.OwnerServiceImpl;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/{ownerId}")
public class RestOwnerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping("")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        return ownerService.getOwnerById(ownerId);
    }

    @GetMapping("/restaurant")
    public Iterable<Restaurant> getRestaurantsByOwnerId(@PathVariable("ownerId") int ownerId) {
        return ownerService.getRestaurantsByOwnerIdFromDb(ownerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) throws RestaurantNotFoundException {
        return ownerService.getRestaurantById(restaurantId, ownerId);
    }
}
