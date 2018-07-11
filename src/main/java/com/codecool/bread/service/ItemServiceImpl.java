package com.codecool.bread.service;

import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;


    public List<Item> getItemsByRestaurantId(Integer restaurantId) {
        return itemRepository.findByRestaurantId(restaurantId);
    }

    public Item getItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException {
        if(itemRepository.findById(id).isPresent()) {
            Item item = itemRepository.findById(id).get();
            if(getItemsByRestaurantId(restaurantId).contains(item)) {
                return item;
            } else {
                throw new ItemAccessDeniedException();
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

}
