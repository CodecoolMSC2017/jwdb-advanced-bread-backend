package com.codecool.bread.service.simple;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Address;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;

import java.util.List;

public interface OwnerService {

    Owner getOwnerByIdFromDb(Integer id) throws OwnerNotFoundException;

    List<Restaurant> getRestaurantsByOwnerIdFromDb(int id);

    Restaurant getRestaurantByIdFromDb(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

    Restaurant addRestaurantToDb(Restaurant restaurant, int ownerId);

    Restaurant editRestaurantDb(Restaurant restaurant, int ownerId);

}
