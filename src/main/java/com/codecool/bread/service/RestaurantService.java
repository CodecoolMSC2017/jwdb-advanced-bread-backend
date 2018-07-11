package com.codecool.bread.service;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RestaurantService {

    List<Restaurant> getAllRestaurants();
    List<Restaurant> getRestaurantsByOwnerId(int id);
    Restaurant getRestaurantById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException;

}
