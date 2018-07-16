package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.service.simple.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository repository;

    public Owner getOwnerById(Integer id) throws OwnerNotFoundException {
        if(repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }else {
            throw new OwnerNotFoundException();
        }
    }
}
