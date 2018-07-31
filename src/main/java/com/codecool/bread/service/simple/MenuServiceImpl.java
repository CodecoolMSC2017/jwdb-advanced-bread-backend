package com.codecool.bread.service.simple;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MenuServiceImpl extends AbstractService implements MenuService {

    @Override
    public Set<Menu> getAllMenuFromDb(int restaurantId) {
        return menuRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public Menu getMenuFromDb(int menuId, int restaurantId) {
        return menuRepository.findByIdAndRestaurantId(menuId, restaurantId);
    }

    @Override
    public Set<Item> getItemsByMenuIdFromDb(int menuId, int restaurantId) {
        return menuRepository.findByIdAndRestaurantId(menuId, restaurantId).getItems();
    }

    @Override
    public Menu addOrEditMenuToDb(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delete(int menuId, int restaurantId) {
        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId);
        menu.setEnabled(false);
        menuRepository.saveAndFlush(menu);
    }

    @Override
    public Menu changeActivityInDb(Menu menu) {
        if(menu.isActive()) {
            menu.setActive(false);
            return menuRepository.saveAndFlush(menu);
        } else {
            menu.setActive(true);
            return menuRepository.saveAndFlush(menu);
        }
    }
}
