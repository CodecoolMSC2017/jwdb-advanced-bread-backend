package com.codecool.bread.controller;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}")
public class RestRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/table")
    public Iterable<Table> getAllTablesByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        List<Table> tables = (List<Table>) restaurantService.getAllTableByRestaurantIdFromDb(restaurantId, ownerId);
        if (tables.size() == 0) {
            throw new NoTablesFoundException();
        } else
            return tables;
    }

    @GetMapping("/table/{tableId}")
    public Table getTableById(@PathVariable("ownerId")int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        Table table = restaurantService.getTableByIdFromDb(tableId, restaurantId);
        List<Table> tables = (List<Table>) getAllTablesByRestaurantId(restaurantId,ownerId);
        if(table == null){
            throw new TableNotFoundException();
        }
        if(tables.contains(table)){
            return table;
        }
        throw new TableAccessDeniedException();
    }

    @GetMapping("/table/{tableId}/seat")
    public Iterable<Seat> getAllSeatsByRestaurantId(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        List<Seat> seats = (List<Seat>) restaurantService.getAllSeatByTableIdFromDb(restaurantId, tableId);
        if(seats.size() == 0){
            return seats;
        }else
            throw new NoSeatsFoundException();
    }

    @GetMapping("/table/{tableId}/seat/{seatId}")
    public Seat getSeatById(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        Seat seat = restaurantService.getSeatByIdFromDb(restaurantId, tableId, seatId);
        if(((List<Seat>)getAllSeatsByRestaurantId(restaurantId,tableId)).contains(seat)){
            return seat;
        }
        throw new SeatNotFoundException();

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

