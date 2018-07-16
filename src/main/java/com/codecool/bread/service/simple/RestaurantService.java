package com.codecool.bread.service.simple;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;

import java.util.Set;

public interface RestaurantService {

    Restaurant getRestaurantById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

    Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId);

    Table getTableByIdFromDb(int tableId, int restaurantId, int ownerId);

}
