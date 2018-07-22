package com.codecool.bread.service.simple;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface RestaurantService {

    Set<Restaurant> getRestaurantsByOwnerIdFromDb(int ownerId);

    Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId);

    Table getTableByIdFromDb(int tableId, int restaurantId);

    Set<Seat> getAllSeatByTableIdFromDb(int restaurantId, int tableId);

    Seat getSeatByIdFromDb(int restaurantId, int tableId, int seatId);

    Table addOrModifyTableToDb(Table table, int ownerId, int restaurantId);

    Seat addOrModifySeatToDb(Seat seat, int restaurantId, int tableId);
}
