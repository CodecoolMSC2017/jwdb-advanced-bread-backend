package com.codecool.bread.controller;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/owner/restaurant")
public class RestaurantController extends AbstractController {

    @GetMapping("")
    public Set<Restaurant> findAllByOwnerId(Principal principal) {
        return restaurantService.getAllEnableByOwnerId(getLoggedInOwnerId(principal));
    }

    @GetMapping("/{restaurantId}")
    public Restaurant findById(@PathVariable("restaurantId") int restaurantId, Principal principal) throws RestaurantNotFoundException {
        return restaurantService.getByIdAndAuthorizedEmployee(restaurantId, getLoggedInEmployeeId(principal));
    }

    @PostMapping("")
    public Restaurant add(@RequestBody Restaurant restaurant, Principal principal) {
        return restaurantService.add(restaurant, getLoggedInEmployeeId(principal));
    }

    @PutMapping("/{restaurantId}")
    public Restaurant editDetails(@RequestBody Restaurant restaurant,
                                  @PathVariable("restaurantId") int restaurantId,
                                  Principal principal) {
        return restaurantService.edit(restaurant, getLoggedInEmployeeId(principal), restaurantId);

    }

    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurant(@PathVariable("restaurantId") int restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
