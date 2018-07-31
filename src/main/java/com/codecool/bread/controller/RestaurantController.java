package com.codecool.bread.controller;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/owner/restaurant")
public class RestaurantController extends AbstractController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Set<Restaurant> findAllByOwnerId(Principal principal) {
        return restaurantService.getAllEnableByOwnerId(getLoggedInOwnerId(principal));
    }

    @GetMapping("/{restaurantId}")
    public Restaurant findById(@PathVariable("restaurantId") int restaurantId, Principal principal) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantService.getById(restaurantId, getLoggedInOwnerId(principal));
        if (restaurant == null) {
            throw new RestaurantNotFoundException();
        } else
            return restaurant;
    }

    @PostMapping("")
    public Restaurant add(@RequestBody Restaurant restaurant, Principal principal) {
        return restaurantService.add(restaurant, getLoggedInOwnerId(principal));
    }

    @PutMapping("/{restaurantId}")
    public Restaurant editDetails(@RequestBody Restaurant restaurant, Principal principal) {
        if(restaurantService.getById(restaurant.getId(), getLoggedInOwnerId(principal)) != null) {
            return restaurantService.edit(restaurant, getLoggedInOwnerId(principal));
        } else {
            throw new RestaurantNotFoundException();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteRestaurant(@RequestBody int restaurantId) {
        try {
            restaurantService.deleteRestaurant(restaurantId);
            return ResponseEntity.ok("Success");
        } catch (RestaurantNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
