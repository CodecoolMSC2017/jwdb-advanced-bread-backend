package com.codecool.bread.service.simple;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;

import java.util.List;

public interface OwnerService {

    Owner getOwnerById(Integer id) throws OwnerNotFoundException;

    List<Restaurant> getRestaurantsByOwnerIdFromDb(int id);

    Restaurant getRestaurantById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

}
