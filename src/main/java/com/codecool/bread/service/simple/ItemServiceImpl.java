package com.codecool.bread.service.simple;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Category;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.service.IngredientService;
import com.codecool.bread.service.ItemService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class ItemServiceImpl extends AbstractService implements ItemService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientService ingredientService;

    public List<Item> getItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException {
        List<Item> items = itemRepository.findByRestaurantId(restaurantId);
        if (items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    public List<Item> getCategorizedItemsByRestaurantId(Integer restaurantId, Category category) throws NoItemsFoundException {
        List<Item> items = itemRepository.findByRestaurantIdAndEnabledTrueAndCategory(restaurantId, category);
        if (items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    public List<Item> getEnableItemsByRestaurantId(Integer restaurantId) throws NoItemsFoundException {
        List<Item> items = itemRepository.findByRestaurantIdAndEnabledTrue(restaurantId);
        if (items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    @Override
    public Item getById(Integer itemId) throws ItemNotFoundException {
        Optional<Item> item = itemRepository.findById(itemId);
        if (!item.isPresent()) {
            throw new ItemNotFoundException();
        } else {
            return item.get();
        }

    }

    public Item getByIdAndRestaurantId(Integer id, Integer restaurantId) throws ItemAccessDeniedException, NoItemsFoundException {
        Optional<Item> item = itemRepository.findByIdAndRestaurantId(id, restaurantId);
        if (!item.isPresent()) {
            throw new NoItemsFoundException();
        } else {
            return item.get();
        }
    }

    public Item getEnableItemById(Integer id, Integer restaurantId) throws ItemAccessDeniedException, NoItemsFoundException {
        Optional<Item> item = itemRepository.findByIdAndRestaurantIdAndEnabledTrue(id, restaurantId);
        if (!item.isPresent()) {
            throw new NoItemsFoundException();
        } else {
            return item.get();
        }
    }

    @Override
    public Item addNewItem(Item item, int restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        if (isItemNameExists(item.getName(), restaurantId)) {
            throw new ItemAlreadyExistsException();
        }
        item.setRestaurant(restaurant);
        return itemRepository.saveAndFlush(item);
    }

    public boolean isItemNameExists (String itemName, int restaurantId) {
        for (Item item : getItemsByRestaurantId(restaurantId)) {
            if (itemName.toLowerCase().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteItem(int restaurantId, int itemId) throws RestaurantAccessDeniedException, ItemNotFoundException {
        Item item = getByIdAndRestaurantId(itemId, restaurantId);
        item.setEnabled(false);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public Item saveItemChanges(Item item, int restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        item.setRestaurant(restaurant);
        if (!itemRepository.findById(item.getId()).isPresent()) {
            throw new ItemNotFoundException();
        }
        return itemRepository.saveAndFlush(item);
    }

    @Override
    public List<Item> getNotContainingItemsByMenuIdFromDb(int menuId, int restaurantId) {
        Set<Item> menuItems = menuRepository.findByIdAndEnabledTrue(menuId).getItems();
        List<Item> allItems = itemRepository.findByRestaurantIdAndEnabledTrue(restaurantId);
        List<Item> tmpItems = new ArrayList<>();
        for (Item allItem : allItems) {
            if (!menuItems.contains(allItem)) {
                tmpItems.add(allItem);
            }

        }
        return tmpItems;
    }
}
