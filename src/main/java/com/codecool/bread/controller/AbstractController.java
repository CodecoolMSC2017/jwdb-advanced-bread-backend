package com.codecool.bread.controller;

import com.codecool.bread.model.POSObject;
import com.codecool.bread.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class AbstractController {

    @Autowired
    private OwnerService ownerService;

    int getLoggedInOwnerId(Principal principal) {
        return ownerService.getOwnerById(ownerService.getOwnerByUsername(principal.getName()).getId()).getId();
    }

    boolean checkIdMatch(POSObject posObject, int id) {
        return posObject.getId() == id;
    }
}
