package com.codecool.bread.controller;

import com.codecool.bread.model.dto.TableDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
