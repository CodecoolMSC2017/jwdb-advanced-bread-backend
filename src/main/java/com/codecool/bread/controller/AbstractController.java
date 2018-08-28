package com.codecool.bread.controller;

import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.model.POSObject;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    EmailService emailService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProfileService profileService;

    @Autowired
    SeatService seatService;

    @Autowired
    TableService tableService;

    int getUserId(Principal principal) throws UserNotFoundException {
        if (principal == null) {
            throw new UserNotFoundException();
        }
        return userService.get(principal.getName()).getId();
    }

    int getLoggedInOwnerId(Principal principal) {
        return employeeService.getOwnerById(employeeService.getByUsername(principal.getName()).getId()).getId();
    }

    boolean checkIdMatch(POSObject posObject, int id) {
        return posObject.getId() == id;
    }

    int getLoggedInEmployeeId(Principal principal) {
        int userId = userService.get(principal.getName()).getId();
        return employeeService.getEmployeeByUserId(userId).getId();
    }
}
