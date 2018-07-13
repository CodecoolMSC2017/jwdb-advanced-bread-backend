package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;

public interface OwnerService {
    Iterable<Owner> getAllOwners();

    Owner getOwnerById(Integer id) throws OwnerNotFoundException;
}
