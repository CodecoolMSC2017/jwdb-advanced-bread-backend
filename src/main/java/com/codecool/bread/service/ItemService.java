package com.codecool.bread.service;

import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItemsByRestaurantId(Integer restaurantId);
    Item getItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException;
}
