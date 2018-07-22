package com.codecool.bread.service;

import com.codecool.bread.model.Table;

import java.util.Set;

public interface TableService {

    Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId);

    Table getTableByIdFromDb(int tableId, int restaurantId);

    Table addOrModifyTableToDb(Table table, int ownerId, int restaurantId);
}
