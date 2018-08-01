package com.codecool.bread.controller;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Table;
import com.codecool.bread.model.dto.TableDto;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/waiter")
public class WaiterController extends AbstractController {

    @GetMapping("/table")
    public List<TableDto> getAllTables(Principal principal) {
        int employeeId = getLoggedInEmployeeId(principal);
        return tableService.getAllTablesByWaiter(employeeId);
    }

    @PutMapping("/table/assign")
    public void assignEmployeeToTable(@RequestBody Table table, Principal principal) {
        int employeeId = getLoggedInEmployeeId(principal);
        tableService.assignEmployeeToTable(employeeId, table);
    }

    @PutMapping("/table/unassign")
    public void unassignEmployeeFromTable(@RequestBody Table table) {
        tableService.unsassingEmployeeFromTable(table);
    }
}
