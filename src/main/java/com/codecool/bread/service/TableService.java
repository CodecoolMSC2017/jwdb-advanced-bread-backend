package com.codecool.bread.service;

import com.codecool.bread.exception.NoTablesFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Table;

import java.util.Set;

public interface TableService {

    Set<Table> getAllTablesByRestaurantId(int restaurantId) throws NoTablesFoundException;

    Set<Table> getEnableTablesByRestaurantId(int restaurantId) throws NoTablesFoundException;

    Table getTableById(int tableId, int restaurantId) throws TableNotFoundException;

    Table getEnableTableById(int restaurantId, int tableId) throws TableNotFoundException;

    Table add(Table table, int restaurantId) throws RestaurantNotFoundException;

    Table edit(Table table, int restaurantId);

    void deleteTable(int tableId, int restaurantId) throws TableNotFoundException;
}
