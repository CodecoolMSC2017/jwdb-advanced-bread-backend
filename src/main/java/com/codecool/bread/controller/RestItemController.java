package com.codecool.bread.controller;

import com.codecool.bread.model.Item;
import com.codecool.bread.service.simple.ItemService;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}/item")
public class RestItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("")
    public List<Item> getItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        return itemService.getItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable("itemId") int itemId,@PathVariable("restaurantId") int restaurantId) {
        return itemService.getItemById(itemId, restaurantId);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Item addNewItem(@RequestBody Item item, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return itemService.addNewItem(item, restaurantId, ownerId);
    }
    @DeleteMapping("/{itemId}")
    public void deleteItemById(@PathVariable("restaurantId") int restaurantId, @PathVariable("itemId") int itemId) throws SQLException {
        itemService.deleteItem(restaurantId, itemId);
    }

    @PutMapping("/{itemId}")
    public Item changeItemDetails(@RequestBody Item item, @PathVariable("restaurantId") int restaurantId, @PathVariable("ownerId") int ownerId) throws SQLException {
        return itemService.saveItemChanges(item, restaurantId, ownerId);
    }

}
