package com.codecool.bread.controller;

import com.codecool.bread.exception.ServiceException;
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
    private OwnerService service;


    @GetMapping("/all")
    public Iterable<Owner> getAllOwners() {
        return service.getAllOwners();
    }

    @GetMapping("{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws ServiceException {
        return service.getOwnerById(ownerId);
    }
}
