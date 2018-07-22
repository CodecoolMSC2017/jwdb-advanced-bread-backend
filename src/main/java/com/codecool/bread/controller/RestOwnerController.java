package com.codecool.bread.controller;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.service.OwnerServiceImpl;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
