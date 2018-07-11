package com.codecool.bread.controller;

import com.codecool.bread.model.Item;
import com.codecool.bread.service.ItemService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}/item")
public class RestItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public List<Item> getItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        return itemService.getItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable("itemId") int itemId,@PathVariable("restaurantId") int restaurantId) {
        return itemService.getItemById(itemId, restaurantId);
    }
}
