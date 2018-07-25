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
public class TableController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TableService tableService;

    @GetMapping("/table")
    public Set<Table> getAllTablesByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        return tableService.getEnableTablesByRestaurantId(restaurantId);
    }

    @GetMapping("/table/{tableId}")
    public Table getTableById(@PathVariable("restaurantId") int restaurantId,
                              @PathVariable("tableId") int tableId) {
        return  tableService.getEnableTableById(restaurantId, tableId);
    }

    @PostMapping("/table")
    public Table addTable(@RequestBody Table table,
                          @PathVariable("restaurantId") int restaurantId,
                          Principal principal) {
        return tableService.add(table, restaurantId);
    }


    @PutMapping("table/{tableId}")
    public Table editTable(@PathVariable("restaurantId") int restaurantId,
                           @RequestBody Table table) {
        return tableService.edit(table, restaurantId);
    }

    @DeleteMapping("/table/{tableId}")
    public void deleteTable(@PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        tableService.deleteTable(tableId, restaurantId);
    }

}

