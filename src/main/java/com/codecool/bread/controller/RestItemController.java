package com.codecool.bread.controller;

import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.exception.NoItemsFoundException;
import com.codecool.bread.model.Item;
import com.codecool.bread.repository.ItemRepository;
import com.codecool.bread.service.ItemService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/owner/restaurant/{restaurantId}/item")
public class RestItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public List<Item> getItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {

        List<Item> items = itemService.getItemsByRestaurantId(restaurantId);
        if (items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable("itemId") int itemId, @PathVariable("restaurantId") int restaurantId) {
        Item item = itemService.getItemById(itemId,restaurantId);
        if(item == null){
            throw new ItemNotFoundException();
        }
        if(getItemsByRestaurantId(restaurantId).contains(item)){
            return itemService.getItemById(itemId, restaurantId);
        }else{
            throw new ItemAccessDeniedException();
        }
    }

    @PostMapping(path = "")
    public Item addNewItem(@RequestBody Item item, @PathVariable("restaurantId") int restaurantId) throws SQLException {
        return itemService.addNewItem(item, restaurantId);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItemById(@PathVariable("restaurantId") int restaurantId, @PathVariable("itemId") int itemId) throws SQLException {
        itemService.deleteItem(restaurantId, itemId);
    }

    @PutMapping("/{itemId}")
    public Item changeItemDetails(@RequestBody Item item, @PathVariable("restaurantId") int restaurantId) throws SQLException {
        return itemService.saveItemChanges(item, restaurantId);
    }

}
