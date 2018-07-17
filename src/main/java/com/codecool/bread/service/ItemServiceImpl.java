package com.codecool.bread.service;

import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.exception.NoItemsFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.ItemRepository;
import com.codecool.bread.service.simple.IngredientService;
import com.codecool.bread.service.simple.ItemService;
import com.codecool.bread.service.simple.OwnerService;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OwnerService ownerService;


    public List<Item> getItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException {
        List<Item> items = itemRepository.findByRestaurantId(restaurantId);
        if(items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    public Item getItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException, NoItemsFoundException {
        return itemRepository.findByIdAndRestaurantId(id,restaurantId);
    }

    @Override
    public Item addNewItem(Item item, int restaurantId, int ownerId) {

        Restaurant restaurant = ownerService.getRestaurantByIdFromDb(restaurantId,ownerId);
        item.setRestaurant(restaurant);
        //ingredientService.addNewIngredients(item.getIngredients());
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(int restaurantId, int itemId) throws RestaurantAccessDeniedException, ItemNotFoundException {

        if (itemRepository.findById(itemId).isPresent()) {
            if (itemRepository.findByRestaurantId(restaurantId).contains(itemRepository.findById(itemId).get())) {
                itemRepository.deleteById(itemId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Item saveItemChanges(Item item, int restaurantId, int ownerId) {
        Restaurant restaurant = ownerService.getRestaurantByIdFromDb(restaurantId, ownerId);
        item.setRestaurant(restaurant);
        return itemRepository.save(item);
    }

}
