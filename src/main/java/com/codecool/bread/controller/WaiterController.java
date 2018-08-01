package com.codecool.bread.controller;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Table;
import com.codecool.bread.model.dto.TableDto;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/waiter")
public class WaiterController extends AbstractController {

    @GetMapping("/table")
    public List<TableDto> getAllTables(Principal principal) {
        int employeeId = getLoggedInEmployeeId(principal);
        return tableService.getAllTablesByWaiter(employeeId);
    }

    @PutMapping("/table/assign")
    public void assignEmployeeToTable(@RequestBody TableDto tableDto, Principal principal) {
        int employeeId = getLoggedInEmployeeId(principal);
        int tableId = tableDto.getId();
        tableService.assignEmployeeToTable(employeeId, tableId);
    }

    @PutMapping("/table/unassign")
    public void unassignEmployeeFromTable(@RequestBody TableDto tableDto) {
        int tableId = tableDto.getId();
        tableService.unsassingEmployeeFromTable(tableId);
    }
}
