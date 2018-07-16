package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Address;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.service.simple.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Owner getOwnerByIdFromDb(Integer id) throws OwnerNotFoundException {
        if(ownerRepository.findById(id).isPresent()) {
            return ownerRepository.findById(id).get();
        }else {
            throw new OwnerNotFoundException();
        }
    }

    public List<Restaurant> getRestaurantsByOwnerIdFromDb(int id) {
        return restaurantRepository.findByOwnerId(id);
    }

    @Override
    public Restaurant getRestaurantByIdFromDb(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        return restaurantRepository.findByIdAndOwnerId(ownerId, restaurantId);
    }

    @Override
    public Restaurant addRestaurantToDb(Restaurant restaurant, int ownerId) {
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
}
