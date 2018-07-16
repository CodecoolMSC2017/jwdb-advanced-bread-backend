package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.service.simple.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("owner/{ownerId}/restaurant/{restaurantId}/employee")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public Iterable<Employee> getEmployeesByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) throws SQLException {
        return employeeService.getAllByRestaurantIdFromDb(ownerId, restaurantId);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException, SQLException {
        return employeeService.getByIdFromDb(ownerId, restaurantId, employeeId);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployee(@RequestBody Employee employee, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return employeeService.addNewToDb(employee, ownerId, restaurantId);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws SQLException {
        employeeService.deleteFromDb(restaurantId, employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee changeDetails(@RequestBody Employee employee, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return employeeService.saveChanges(employee, restaurantId, ownerId);
    }
}
