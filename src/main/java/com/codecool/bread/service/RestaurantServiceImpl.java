package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Iterable<Restaurant> getRestaurantsByOwnerId(int id){
        return restaurantRepository.findByOwnerId(id);
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        if(restaurantRepository.findById(id).isPresent()) {
            return restaurantRepository.findById(id).get();
        }else {
            throw new RestaurantNotFoundException();
        }
    }
}
