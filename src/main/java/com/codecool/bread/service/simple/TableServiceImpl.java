package com.codecool.bread.service.simple;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId) {
        return restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId).getTables();
    }

    @Override
    public Table getTableByIdFromDb(int tableId, int restaurantId) {
        return tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
    }

    @Override
    public Table addOrModifyTableToDb(Table table, int ownerId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
        restaurant.getTables().add(table);
        table.setRestaurant(restaurant);
        tableRepository.save(table);
        return table;
    }
}
