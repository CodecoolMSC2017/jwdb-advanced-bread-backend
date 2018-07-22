package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.AddressRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.simple.OwnerService;
import com.codecool.bread.service.simple.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Owner getOwnerByIdFromDb(Integer id) throws OwnerNotFoundException {
        Optional<Owner> result = ownerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new OwnerNotFoundException();
        }
    }



    @Override
    public Restaurant getRestaurantByIdFromDb(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        return restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
    }

    @Override
    public Restaurant addRestaurantToDb(Restaurant restaurant, int ownerId) {
        addressRepository.save(restaurant.getAddress());
        restaurant.setOwner(getOwnerByIdFromDb(ownerId));
        getOwnerByIdFromDb(ownerId).getRestaurants().add(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant editRestaurantDb(Restaurant restaurant, int ownerId) {
        restaurant.setOwner(getOwnerByIdFromDb(ownerId));
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Owner getOwner(String username) throws OwnerNotFoundException {
        User user = userService.get(username);
        Optional<Owner> owner = ownerRepository.findById(user.getId());
        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new OwnerNotFoundException();
        }
    }
}
