package com.codecool.bread.service.simple;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;

public interface OwnerService {

    Owner getOwnerById(Integer id) throws OwnerNotFoundException;
}
