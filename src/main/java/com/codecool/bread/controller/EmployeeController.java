package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.EmailService;
import com.codecool.bread.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("owner/restaurant/{restaurantId}/employee")
public class EmployeeController extends AbstractController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("")
    public Set<Employee> getEmployeesByRestaurantId(@PathVariable("restaurantId") int restaurantId,
                                                    Principal principal) {
        return employeeService.getAllByRestaurantId(getLoggedInOwnerId(principal), restaurantId);
    }

    @GetMapping("/{employeeId}")
    public Employee getByIdAndRestaurantId(@PathVariable("restaurantId") int restaurantId,
                                           @PathVariable("employeeId") int employeeId,
                                           Principal principal) {
        return employeeService.getByIdAndRestaurantIdAndOwnerId(employeeId, restaurantId, getLoggedInOwnerId(principal));
    }

    @PostMapping(path = "")
    public Employee add(@RequestBody Employee employee,
                        @PathVariable("restaurantId") int restaurantId,
                        Principal principal) {
        Employee newEmployee = employeeService.add(employee, restaurantId, getLoggedInOwnerId(principal));
        try {
            emailService.sendSimpleMessage(emailService.createEmail(newEmployee));
        } catch (SendFailedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newEmployee;
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable("restaurantId") int restaurantId,
                       @PathVariable("employeeId") int employeeId) {
        employeeService.delete(restaurantId, employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee editDetails(@RequestBody Employee employee,
                                @PathVariable("restaurantId") int restaurantId,
                                Principal principal) {
        if (employeeService.getByIdAndRestaurantIdAndOwnerId(employee.getId(), restaurantId,
                getLoggedInOwnerId(principal)) != null) {
            return employeeService.editChanges(employee, restaurantId, getLoggedInOwnerId(principal));
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @PutMapping("/{employeeId}/addusername")
    public Employee addUsername(@PathVariable("employeeId") int employeeId,
                                @RequestBody Map<String, String> user) {
        return employeeService.addUsername(user, employeeId);
    }
}
