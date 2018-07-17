package com.codecool.bread.controller;

import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Table getTableById(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        return restaurantService.getTableByIdFromDb(tableId, restaurantId);
    }

    @GetMapping("/table/{tableId}/seat")
    public Iterable<Seat> getAllSeatsByRestaurantId(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        return restaurantService.getAllSeatByTableIdFromDb(restaurantId, tableId);
    }

    @GetMapping("/table/{tableId}/seat/{seatId}")
    public Seat getSeatById(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        return restaurantService.getSeatByIdFromDb(restaurantId, tableId, seatId);
    }

    @PostMapping("table")
    public Table addTable(@RequestBody Table table, @PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.addOrModifyTableToDb(table, ownerId, restaurantId);
    }

    @PostMapping("/table/{tableId}/seat")
    public Seat addSeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.addOrModifySeatToDb(seat, restaurantId, tableId);
    }

    @PutMapping("table/{tableId}")
    public Table modifyTable(@RequestBody Table table, @PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.addOrModifyTableToDb(table, ownerId, restaurantId);
    }

    @PutMapping("table/{tableId}/seat/{seatId}")
    public Seat modifySeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return restaurantService.addOrModifySeatToDb(seat, restaurantId, tableId);
    }
}

