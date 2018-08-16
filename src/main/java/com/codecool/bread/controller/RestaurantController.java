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

    @GetMapping("")
    public Set<Restaurant> findAllByOwnerId(Principal principal) {
        return restaurantService.getAllEnableByOwnerId(principal);
    }

    @GetMapping("/{restaurantId}")
    public Restaurant findById(@PathVariable("restaurantId") int restaurantId, Principal principal) {
        return restaurantService.getById(restaurantId, principal);
    }

    @PostMapping("")
    public Restaurant add(@RequestBody Restaurant restaurant, Principal principal) {
        return restaurantService.add(restaurant, getLoggedInOwnerId(principal));
    }

    @PutMapping("/{restaurantId}")
    public Restaurant editDetails(@RequestBody Restaurant restaurant, Principal principal) {
        if(restaurantService.getById(restaurant.getId(), principal) != null) {
            return restaurantService.edit(restaurant,principal);
        } else {
            throw new RestaurantNotFoundException();
        }
    }

    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurant(@PathVariable("restaurantId") int restaurantId, Principal principal) {
        restaurantService.deleteRestaurant(restaurantId, principal);
    }
}
