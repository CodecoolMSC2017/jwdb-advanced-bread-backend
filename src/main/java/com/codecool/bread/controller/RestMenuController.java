package com.codecool.bread.controller;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/restaurant/{restaurantId}/menu")
public class RestMenuController extends AbstractController {

    @Autowired
    MenuService menuService;

    @GetMapping("")
    public Set<Menu> getAllMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuService.getAllMenuFromDb(restaurantId);
    }

    @GetMapping("{menuId}")
    public Menu getMenu(@PathVariable("menuId") int menuId, @PathVariable("restaurantId") int restaurantId) {
        return menuService.getMenuFromDb(menuId, restaurantId);
    }

    @GetMapping("{menuId}/items")
    public Set<Item> getItemByMenuId(@PathVariable("menuId") int menuId, @PathVariable("restaurantId") int restaurantId) {
        return menuService.getItemsByMenuIdFromDb(menuId, restaurantId);
    }

    @PostMapping("")
    public Menu addMenu(@RequestBody Menu menu) {
        return menuService.addOrEditMenuToDb(menu);
    }

    @PutMapping("{menuId}")
    public Menu editMenu(@RequestBody Menu menu) {
        return menuService.addOrEditMenuToDb(menu);
    }

    @DeleteMapping("{menuId}")
    public void deleteMenu(@PathVariable("menuId") int menuId, @PathVariable("restaurantId") int restaurantId) {
        menuService.delete(menuId, restaurantId);
    }

    @PutMapping("{menuId}/activity")
    public Menu changeActivity(@PathVariable("menuId") int menuId, @PathVariable("restaurantId") int restaurantId) {
        return menuService.changeActivityInDb(menuId, restaurantId);
    }
}
