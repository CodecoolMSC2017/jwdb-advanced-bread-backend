package com.codecool.bread.service;

import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.exception.NoItemsFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException;
    List<Item> getEnableItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException;
    Item getItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException, NoItemsFoundException;
    Item addNewItem(Item item, int restaurantId);
    void deleteItem(int restaurantId, int itemId) throws RestaurantAccessDeniedException, ItemNotFoundException;
    Item saveItemChanges(Item item, int restaurantId);
}
