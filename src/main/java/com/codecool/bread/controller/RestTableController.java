package com.codecool.bread.controller;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.SeatService;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/restaurant/{restaurantId}")
public class RestTableController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TableService tableService;

    @Autowired
    private SeatService seatService;

    @GetMapping("/table")
    public Set<Table> getAllTablesByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        Set<Table> tables = tableService.getAllTableByRestaurantId(restaurantId, ownerId);
        if (tables.size() == 0) {
            throw new NoTablesFoundException();
        } else
            return tables;
    }

    @GetMapping("/table/{tableId}")
    public Table getTableById(@PathVariable("ownerId")int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        Set<Table> tables = tableService.getAllTableByRestaurantId(restaurantId, ownerId);
        Table table = tableService.getTableById(tableId, restaurantId);
        if(table == null){
            throw new TableNotFoundException();
        }
        if(tables.contains(table)){
            return table;
        }
        throw new TableAccessDeniedException();
    }

    @GetMapping("/table/{tableId}/seat")
    public Set<Seat> getAllSeatsByRestaurantId(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        Set<Seat> seats = seatService.getAllSeatByTableId(restaurantId, tableId);
        if(seats.size() != 0){
            return seats;
        }else
            throw new NoSeatsFoundException();
    }

    @GetMapping("/table/{tableId}/seat/{seatId}")
    public Seat getSeatById(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        Seat seat = seatService.getSeatById(restaurantId, tableId, seatId);
        Set<Seat> seats = seatService.getAllSeatByTableId(restaurantId, tableId);
        if(seats.contains(seat)){
            return seat;
        }
        throw new SeatNotFoundException();

    }

    @PostMapping("/table")
    public Table addTable(@RequestBody Table table, @PathVariable("restaurantId") int restaurantId, Principal principal) {
        Owner owner = ownerService.getOwnerById(ownerService.getOwnerByUsername(principal.getName()).getId());
        return tableService.addOrModifyTable(table, owner.getId(), restaurantId);
    }

    @PostMapping("/table/{tableId}/seat")
    public Seat addSeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return seatService.addOrModifySeat(seat, restaurantId, tableId);
    }

    @PutMapping("table/{tableId}")
    public Table modifyTable(@RequestBody Table table, @PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) {
        return tableService.addOrModifyTable(table, ownerId, restaurantId);
    }

    @PutMapping("table/{tableId}/seat/{seatId}")
    public Seat modifySeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return seatService.addOrModifySeat(seat, restaurantId, tableId);
    }
}

