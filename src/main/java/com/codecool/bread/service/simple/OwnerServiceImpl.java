package com.codecool.bread.service.simple;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.AddressRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Owner getOwnerById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> result = ownerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new OwnerNotFoundException();
        }
    }

    public Owner getOwnerByUsername(String username) throws OwnerNotFoundException {
        User user = userService.get(username);
        Optional<Owner> owner = ownerRepository.findById(user.getId());
        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new OwnerNotFoundException();
        }
    }
}
