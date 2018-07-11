package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant/{restaurantId}/employee")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public Iterable<Employee> getEmployeesByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        return employeeService.getAllEmployeesByRestaurantId(restaurantId);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(restaurantId, employeeId);
    }
}
