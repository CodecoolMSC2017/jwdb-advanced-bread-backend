package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("owner/restaurant/{restaurantId}/employee")
public class RestEmployeeController extends AbstractController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public Set<Employee> getEmployeesByRestaurantId(@PathVariable("restaurantId") int restaurantId, Principal principal) {
        return employeeService.getAllByRestaurantId(restaurantId, getLoggedInOwnerId(principal));
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("ownerId") int ownerId, @PathVariable("restaurantId") int restaurantId, @PathVariable("employeeId") int employeeId){
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByRestaurantIdAndRestaurantOwnerId(ownerId, restaurantId).contains(employeeRepository.findById(employeeId).get())) {
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
