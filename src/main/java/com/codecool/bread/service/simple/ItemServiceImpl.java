package com.codecool.bread.service.simple;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.ItemRepository;
import com.codecool.bread.service.IngredientService;
import com.codecool.bread.service.ItemService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OwnerService ownerService;


    public List<Item> getItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException {
        List<Item> items =itemRepository.findByRestaurantId(restaurantId);
        if(items.size()==0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    public List<Item> getEnableItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException {
        List<Item> items =itemRepository.findByRestaurantIdAndEnabledTrue(restaurantId);
        if(items.size()==0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    public Item getItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException, NoItemsFoundException {
        Item item = itemRepository.findByIdAndRestaurantId(id,restaurantId);
        if(item == null) {
            throw new NoItemsFoundException();
        }
        return item;
    }

    @Override
    public Item addNewItem(Item item, int restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        item.setRestaurant(restaurant);
        if(itemRepository.findById(item.getId()).isPresent() || itemRepository.findByName(item.getName()).isPresent()){
            throw new ItemAlreadyExistsException();
        }
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(int restaurantId, int itemId) throws RestaurantAccessDeniedException, ItemNotFoundException {
        Item item = itemRepository.findByIdAndRestaurantId(itemId, restaurantId);
        if(item == null) {
            throw new ItemNotFoundException();
        }
        item.setEnabled(false);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public Item saveItemChanges(Item item, int restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        item.setRestaurant(restaurant);
        if(!itemRepository.findById(item.getId()).isPresent()){
            throw new ItemNotFoundException();
        }
        return itemRepository.save(item);
    }
}
