package com.codecool.bread.service.simple;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;

import java.util.List;
import java.util.Set;

public interface RestaurantService {

    Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId);

    Table getTableByIdFromDb(int tableId, int restaurantId);

    Set<Seat> getAllSeatByTableIdFromDb(int restaurantId, int tableId);

    Seat getSeatByIdFromDb(int restaurantId, int tableId, int seatId);

}
