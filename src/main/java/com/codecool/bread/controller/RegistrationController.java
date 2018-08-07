package com.codecool.bread.controller;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.User;
import com.codecool.bread.model.dto.ChangePasswordDto;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;

@RestController
@EnableWebMvc
public class RegistrationController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public Employee registerEvaluation(@RequestParam Map<String, String> requestParams) {
        int employeeId = Integer.parseInt(requestParams.get("employeeId"));
        int restaurantId = Integer.parseInt(requestParams.get("restaurantId"));
        return employeeService.getByIdAndUserIdNull(employeeId, restaurantId);
    }

    @RequestMapping(path = "/register/{employeeId}", method = RequestMethod.PUT)
    public Employee addUsername(@PathVariable("employeeId") int employeeId,
                                @RequestBody Map<String, String> user) {
        return employeeService.addUsername(user, employeeId);
    }

    @RequestMapping(path = "/changepw", method = RequestMethod.GET)
    public User changePwEvaluate(@RequestParam Map<String, String> requestParams) {
        int userId = Integer.parseInt(requestParams.get("userId"));
        String username = requestParams.get("username");
        return userService.getUserByIdAndUserName(userId, username);
    }

    @RequestMapping(path = "/changepw", method = RequestMethod.PUT)
    public User ChangePassword(@RequestBody ChangePasswordDto passwordDto) {
        return userService.changeForgotPassword(passwordDto);
    }
}
