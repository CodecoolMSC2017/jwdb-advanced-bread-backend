package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController extends AbstractController {

    @GetMapping("/{ownerId}")
    public Employee getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        Employee employee = employeeService.getOwnerById(ownerId);
        if (employee == null) {
            throw new OwnerNotFoundException();
        } else
            return employee;
    }

    @GetMapping("/employee")
    public List<Employee> getEmployees(Principal principal) {
        int ownerId = getLoggedInOwnerId(principal);
        return employeeService.getAllEmployees(ownerId);
    }
}
