package com.codecool.bread.service;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface RestaurantService {

    Restaurant getById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

    Restaurant getById(int restaurantId);

    Set<Restaurant> getAllByOwnerId(int ownerId);

    Set<Restaurant> getAllEnableByOwnerId(int ownerId) throws RestaurantNotFoundException;

    Restaurant getByIdAndAuthorizedEmployee(int restaurantId, int employeeId) throws RestaurantNotFoundException, RestaurantAccessDeniedException;

    Restaurant add(Restaurant restaurant, int ownerId);

    Restaurant edit(Restaurant restaurant, int ownerId, int restaurantId) throws RestaurantNotFoundException;

    void deleteRestaurant(int restaurantId) throws RestaurantNotFoundException;
}
