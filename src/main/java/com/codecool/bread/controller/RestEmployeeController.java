package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("owner/{ownerId}/restaurant/{restaurantId}/employee")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public Iterable<Employee> getEmployeesByRestaurantId(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId) throws SQLException {
        Iterable<Employee> employees = employeeService.getAllByRestaurantId(ownerId, restaurantId);
        if(((List<Employee>) employees).size() == 0) {
            throw new EmployeeNotFoundException("s");
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId){
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByRestaurantId(restaurantId).contains(employeeRepository.findById(employeeId).get())) {
                return employeeService.getById( restaurantId, employeeId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @PostMapping("")
    public Employee add(@RequestBody Employee employee, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return employeeService.add(employee, ownerId, restaurantId);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId) throws SQLException {
        employeeService.delete(restaurantId, employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee editDetails(@RequestBody Employee employee, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return employeeService.editChanges(employee, restaurantId, ownerId);
    }
}
