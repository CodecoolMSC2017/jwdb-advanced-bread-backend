package com.codecool.bread.controller;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private EmployeeService employeeService;

    int getLoggedInOwnerId(Principal principal) {
        int userId = 0;
        try {
            userId = ownerService.getOwnerById(ownerService.getOwnerByUsername(principal.getName()).getId()).getId();
            return userId;
        } catch (OwnerNotFoundException o){
            try{
                userId = employeeService.getEmployeeById(employeeService.getEmployeeByUsername(principal.getName()).getId()).getId();
                return userId;
            } catch (EmployeeNotFoundException e) {
                throw new UserNotFoundException();
            }
        }


    }

}
