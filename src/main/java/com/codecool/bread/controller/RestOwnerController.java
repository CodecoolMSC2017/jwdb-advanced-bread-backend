package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.service.simple.OwnerServiceImpl;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class RestOwnerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping("/{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") int ownerId) throws OwnerNotFoundException {
        Owner owner = ownerService.getOwnerByIdFromDb(ownerId);
        if (owner == null) {
            throw new OwnerNotFoundException();
        } else
            return owner;
    }
}
