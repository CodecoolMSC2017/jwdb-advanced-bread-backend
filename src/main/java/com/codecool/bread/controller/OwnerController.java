package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.simple.OwnerServiceImpl;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController extends AbstractController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        Owner owner = ownerService.getOwnerById(ownerId);
        if (owner == null) {
            throw new OwnerNotFoundException();
        } else
            return owner;
    }

    @GetMapping("/employee")
    public List<Employee> getEmployees(Principal principal) {
        int ownerId = getLoggedInOwnerId(principal);
        return employeeService.getAllEmployees(ownerId);
    }
}
