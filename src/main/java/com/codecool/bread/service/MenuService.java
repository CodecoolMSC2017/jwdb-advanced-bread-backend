package com.codecool.bread.service;


import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;

import java.util.Set;

public interface MenuService {

    Set<Menu> getAllEnabledMenuFromDb(int restaurantId);

    Menu getEnabledMenuFromDb(int menuId);

    Set<Item> getItemsByMenuIdFromDb(int menuId);

    Menu addOrEditMenuToDb(Menu menu);

    void delete(int menuId);

    Menu changeActivityInDb(Menu menu, int restaurantId);

    void setAllInactiveInDb(int restaurantId);
}
