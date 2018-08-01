package com.codecool.bread.service;

import com.codecool.bread.exception.NoTablesFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Table;

import java.util.List;
import java.util.Set;

public interface TableService {

    Set<Table> getAllTablesByRestaurantId(int restaurantId) throws NoTablesFoundException;

    List<Table> getAllEnabledTablesByRestaurantId(int restaurantId) throws NoTablesFoundException;

    Table getTableByRestaurantIdAndTableId(int tableId, int restaurantId) throws TableNotFoundException;

    Table getEnableTableById(int restaurantId, int tableId) throws TableNotFoundException;

    Table add(Table table, int restaurantId) throws RestaurantNotFoundException;

    Table edit(Table table, int restaurantId);

    void deleteTable(int tableId, int restaurantId) throws TableNotFoundException;

    Table getById(int tableId) throws TableNotFoundException;

    void deleteAllTableByRestaurantId(int restaurantId);
}
