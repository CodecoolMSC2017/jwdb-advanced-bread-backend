package com.codecool.bread.controller;


import com.codecool.bread.model.POSObject;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

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
        return employeeService.getByUsername(principal.getName()).getUser().getId();
    }
}
