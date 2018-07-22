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

    Restaurant findById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

    Set<Restaurant> findAllByOwnerId(int ownerId);

    Restaurant add(Restaurant restaurant, int ownerId);

    Restaurant edit(Restaurant restaurant, int ownerId);
}
