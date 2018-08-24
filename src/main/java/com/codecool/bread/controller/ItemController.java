package com.codecool.bread.controller;

import com.codecool.bread.exception.CategoryNotFoundException;
import com.codecool.bread.exception.ItemAccessDeniedException;
import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.exception.NoItemsFoundException;
import com.codecool.bread.model.Category;
import com.codecool.bread.model.Item;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/owner/restaurant/{restaurantId}/item")
public class ItemController extends AbstractController {

    @GetMapping("")
    public List<Item> getItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId, @RequestParam("category") String category) {
        List<Item> items = null;
        if(category.equals("all")) {
            items = itemService.getEnableItemsByRestaurantId(restaurantId);
        } else if(category.equals("food") || category.equals("drink")){
            items = itemService.getCategorizedItemsByRestaurantId(restaurantId, Category.valueOf(category.toUpperCase()));
        } else {
            throw new CategoryNotFoundException();
        }
        if (items.size() == 0) {
            throw new NoItemsFoundException();
        }
        return items;
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable("itemId") int itemId, @PathVariable("restaurantId") int restaurantId) {
        Item item = itemService.getEnableItemById(itemId,restaurantId);
        List<Item> items = itemService.getEnableItemsByRestaurantId(restaurantId);
        if(item == null){
            throw new ItemNotFoundException();
        }
        if(items.contains(item)){
            return itemService.getByIdAndRestaurantId(itemId, restaurantId);
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

    @GetMapping("{menuId}/noitems")
    public List<Item> getNotContainingItemsByMenuId(@PathVariable("menuId") int menuId, @PathVariable("restaurantId") int restaurantId) {
        return itemService.getNotContainingItemsByMenuIdFromDb(menuId, restaurantId);
    }

}
