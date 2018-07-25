package com.codecool.bread.service.simple;

import com.codecool.bread.exception.NoTablesFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.TableAccessDeniedException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Set<Table> getAllTablesByRestaurantId(int restaurantId) throws NoTablesFoundException {
        Set<Table> tables = tableRepository.findByRestaurantId(restaurantId);
        if (tables.isEmpty()) {
            throw new NoTablesFoundException();
        }
        return tables;
    }

    @Override
    public Table getTableById(int restaurantId, int tableId) throws TableNotFoundException {
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
}
