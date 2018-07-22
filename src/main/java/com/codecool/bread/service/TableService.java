package com.codecool.bread.service;

import com.codecool.bread.model.Table;

import java.util.Set;

public interface TableService {

    Set<Table> getAllTableByRestaurantId(int restaurantId, int ownerId);

    Table getTableById(int tableId, int restaurantId);

    Table addOrModifyTable(Table table, int ownerId, int restaurantId);
}
