package com.codecool.bread.service;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private SeatRepository seatRepository;


    @Override
    public Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId) {
        return restaurantRepository.findByIdAndOwnerId(ownerId, restaurantId).getTables();
    }

    @Override
    public Table getTableByIdFromDb(int tableId, int restaurantId) {
        return tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
    }

    @Override
    public Set<Seat> getAllSeatByTableIdFromDb(int restaurantId, int tableId) {
        return getTableByIdFromDb(tableId, restaurantId).getSeats();
    }

    @Override
    public Seat getSeatByIdFromDb(int restaurantId, int tableId, int seatId) {
        return seatRepository.findByIdAndTableId(seatId, tableId);
    }

    @Override
    public Table addOrModifyTableToDb(Table table, int ownerId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findByIdAndOwnerId(ownerId, restaurantId);
        restaurant.getTables().add(table);
        table.setRestaurant(restaurant);
        tableRepository.save(table);
        return table;
    }

    @Override
    public Seat addOrModifySeatToDb(Seat seat, int restaurantId, int tableId) {
        Table table = tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
        table.getSeats().add(seat);
        seat.setTable(table);
        seatRepository.save(seat);
        return seat;
    }
}
