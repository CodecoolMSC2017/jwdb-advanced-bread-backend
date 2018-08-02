package com.codecool.bread.controller;

import com.codecool.bread.model.Employee;
import com.codecool.bread.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("")
    public Employee registerEvaluation(@RequestParam Map<String,String> requestParams) {
        int employeeId = Integer.parseInt(requestParams.get("employeeId"));
        int restaurantId = Integer.parseInt(requestParams.get("restaurantId"));
        return employeeService.getByIdAndUserIdNull(employeeId, restaurantId);
    }

    @PutMapping("/{employeeId}")
    public Employee addUsername(@PathVariable("employeeId") int employeeId,
                                @RequestBody Map<String, String> user) {
        return employeeService.addUsername(user, employeeId);

    }
}
