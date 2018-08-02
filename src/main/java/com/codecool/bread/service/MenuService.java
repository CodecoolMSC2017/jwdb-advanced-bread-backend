package com.codecool.bread.service;


import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;

import java.util.Set;

public interface MenuService {

    Set<Menu> getAllEnabledMenuFromDb(int restaurantId);

    Menu getEnabledMenuFromDb(int menuId, int restaurantId);

    Set<Item> getItemsByMenuIdFromDb(int menuId, int restaurantId);

    Menu addOrEditMenuToDb(Menu menu);

    void delete(int menuId, int restaurantId);

    Menu changeActivityInDb(int menuId, int restaurantId);
}
