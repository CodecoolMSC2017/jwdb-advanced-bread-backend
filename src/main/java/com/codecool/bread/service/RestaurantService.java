package com.codecool.bread.service;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RestaurantService {

    List<Restaurant> getAllRestaurants();
    Iterable<Restaurant> getRestaurantsByOwnerId(int id);
    Restaurant getRestaurantById(int id);

}
