package com.codecool.bread.service;

import com.codecool.bread.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int id);
}
