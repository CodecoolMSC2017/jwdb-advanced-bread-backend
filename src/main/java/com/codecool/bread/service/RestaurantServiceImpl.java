package com.codecool.bread.service;

import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
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

    public List<Restaurant> getRestaurantsByOwnerId(int id){
        return restaurantRepository.findByOwnerId(id);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        if(restaurantRepository.findById(restaurantId).isPresent()) {
            if(restaurantRepository.findByOwnerId(ownerId).contains(restaurantRepository.findById(restaurantId).get())) {
                return restaurantRepository.findById(restaurantId).get();
            } else {
                throw new RestaurantAccessDeniedException();
            }
        }else {
            throw new RestaurantNotFoundException();
        }
    }
}
