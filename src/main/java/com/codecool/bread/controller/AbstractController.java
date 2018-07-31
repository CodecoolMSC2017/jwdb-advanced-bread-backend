package com.codecool.bread.controller;


import com.codecool.bread.model.POSObject;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    OwnerService ownerService;

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

    int getUserId(Principal principal) {
        return userService.get(principal.getName()).getId();
    }

    int getLoggedInOwnerId(Principal principal) {
        return ownerService.getOwnerById(ownerService.getOwnerByUsername(principal.getName()).getId()).getId();
    }

    boolean checkIdMatch(POSObject posObject, int id) {
        return posObject.getId() == id;
    }

    int getLoggedInEmployeeId(Principal principal) {
        int userId = userService.get(principal.getName()).getId();
        return employeeService.getEmployeeByUserId(userId).getId();
    }
}
