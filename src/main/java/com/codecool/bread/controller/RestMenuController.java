package com.codecool.bread.controller;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/menu")
public class RestMenuController extends AbstractController {

    @Autowired
    MenuService menuService;

    @GetMapping("/{restaurantId")
    public Set<Menu> getAllMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuService.getAllEnabledMenuFromDb(restaurantId);
    }

    @GetMapping("/{menuId}")
    public Menu getMenu(@PathVariable("menuId") int menuId) {
        return menuService.getEnabledMenuFromDb(menuId);
    }

    @GetMapping("/{menuId}/items")
    public Set<Item> getItemByMenuId(@PathVariable("menuId") int menuId) {
        return menuService.getItemsByMenuIdFromDb(menuId);
    }

    @PostMapping("")
    public Menu addMenu(@RequestBody Menu menu) {
        return menuService.addOrEditMenuToDb(menu);
    }

    @PutMapping("")
    public Menu editMenu(@RequestBody Menu menu) {
        return menuService.addOrEditMenuToDb(menu);
    }

    @DeleteMapping("/{menuId}")
    public void deleteMenu(@PathVariable("menuId") int menuId) {
        menuService.delete(menuId);
    }

    @PutMapping("/{restaurandId}/activity")
    public Menu changeActivity(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId) {
        return menuService.changeActivityInDb(menu, restaurantId);
    }
}
