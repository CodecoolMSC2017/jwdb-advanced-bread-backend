package com.codecool.bread.controller;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/restaurant/{restaurantId}")
public class TableController extends AbstractController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

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
                           @PathVariable("tableId") int tableId,
                           @RequestBody Table table) {
        if (checkIdMatch(table, tableId)) {
            return tableService.edit(table, restaurantId);
        } else {
            throw new  IdMismatchException();
        }
    }

    @DeleteMapping("/table/{tableId}")
    public void deleteTable(@PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        tableService.deleteTable(tableId, restaurantId);
    }

    @PostMapping("/table/{tableId}")
    public void setEmployeeToTable(@PathVariable("employeeId") int tableId,
                                   Principal principal) {
        int emmployeeId = getLoggedInEmployeeId(principal);
        orderService.setEmployeeToTable(emmployeeId, tableId);
    }

}

