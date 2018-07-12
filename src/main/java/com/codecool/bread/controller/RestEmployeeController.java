package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("owner/{ownerId}/restaurant/{restaurantId}")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Iterable<Employee> getEmployeesByRestaurantId(@PathVariable("restaurantId") int restaurantId) throws SQLException {
        return employeeService.getAllEmployeesByRestaurantId(restaurantId);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException, SQLException {
        return employeeService.getEmployeeById(restaurantId, employeeId);
    }

    @PostMapping(path = "/employee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployee(@RequestBody Employee employee, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        employeeService.addNewEmployeeToDb(employee, ownerId, restaurantId);
        return employee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployeeById(@PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws SQLException {
        employeeService.deleteEmployeeFromDb(restaurantId, employeeId);
    }
}
