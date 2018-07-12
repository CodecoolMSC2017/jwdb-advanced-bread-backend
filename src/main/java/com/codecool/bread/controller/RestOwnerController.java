package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class RestOwnerController {

    @Autowired
    private OwnerService ownerService;


    @GetMapping("")
    public Iterable<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        return ownerService.getOwnerById(ownerId);
    }
}
