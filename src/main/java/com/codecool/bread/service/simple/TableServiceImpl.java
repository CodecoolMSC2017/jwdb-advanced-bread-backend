package com.codecool.bread.service.simple;

import com.codecool.bread.exception.NoTablesFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.TableAccessDeniedException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.SeatService;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TableServiceImpl extends AbstractService implements TableService {

    @Autowired
    private SeatService seatService;

    @Override
    public Set<Table> getAllTablesByRestaurantId(int restaurantId) throws NoTablesFoundException {
        Set<Table> tables = tableRepository.findByRestaurantId(restaurantId);
        if (tables.isEmpty()) {
            throw new NoTablesFoundException();
        }
        return tables;
    }

    public Set<Table> getEnableTablesByRestaurantId(int restaurantId) throws NoTablesFoundException {
        Set<Table> enableTables = tableRepository.findByRestaurantIdAndEnabledTrue(restaurantId);
        if(enableTables.isEmpty()) {
            throw new NoTablesFoundException();
        }
        return enableTables;
    }


    @Override
    public Table getTableByRestaurantIdAndTableId(int restaurantId, int tableId) throws TableNotFoundException, TableAccessDeniedException {
        Set<Table> tables = tableRepository.findByRestaurantId(restaurantId);
        Optional<Table> table = tableRepository.findById(tableId);
        if(!table.isPresent()){
            throw new TableNotFoundException();
        }
        if(tables.contains(table.get())){
            return table.get();
        }
        throw new TableAccessDeniedException();
    }

    public Table getEnableTableById(int restaurantId, int tableId) throws TableNotFoundException {
        Optional<Table> table = tableRepository.findByIdAndRestaurantIdAndEnabledTrue(tableId, restaurantId);
        if(!table.isPresent()) {
            throw new TableNotFoundException();
        }
        return table.get();
    }

    @Override
    public Table add(Table table, int restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        restaurant.get().getTables().add(table);
        table.setRestaurant(restaurant.get());
        tableRepository.save(table);
        return table;
    }


    @Override
    public Table edit(Table table, int restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        table.setRestaurant(restaurant.get());
        return tableRepository.save(table);

    }

    public void deleteTable(int tableId, int restaurantId) throws TableNotFoundException {
        Optional<Table> table = tableRepository.findByIdAndRestaurantId(tableId,restaurantId);
        if(!table.isPresent()) {
            throw new TableNotFoundException();
        }

        table.get().setEnabled(false);
        seatService.deleteAllSeatsByTableId(table.get());
        tableRepository.saveAndFlush(table.get());
    }

    public Table getById(int tableId) throws TableNotFoundException {
        Optional<Table> table = tableRepository.findById(tableId);
        if (table.isPresent()) {
            return table.get();
        } else {
            throw new TableNotFoundException();
        }
    }

    @Override
    public void deleteAllTableByRestaurantId(int restaurantId) {
        Set<Table> tables = restaurantRepository.findById(restaurantId).get().getTables();
        for(Table table : tables) {
            table.setEnabled(false);
        }
        tableRepository.saveAll(tables);
    }
}
