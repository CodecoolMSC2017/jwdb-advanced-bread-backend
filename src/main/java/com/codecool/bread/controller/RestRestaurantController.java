package com.codecool.bread.controller;

import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}")
public class RestRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/table")
    public Iterable<Table> getAllTablesByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.getAllTableByRestaurantIdFromDb(restaurantId, ownerId);
    }

    @GetMapping("/table/{tableId}")
    public Table getTableById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        return null;
    }

    @GetMapping("/table/{tableId}/seat")
    public Iterable<Seat> getAllSeatsByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return null;
    }

    @GetMapping("/table/{tableId}/seat/{seatId}")
    public Seat getSeatById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("seatId") int seatId) {
        return null;
    }
}

