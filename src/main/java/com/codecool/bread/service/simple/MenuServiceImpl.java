package com.codecool.bread.service.simple;

import com.codecool.bread.exception.MenuNotFoundException;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MenuServiceImpl extends AbstractService implements MenuService {

    @Override
    public Set<Menu> getAllEnabledMenuFromDb(int restaurantId) {
        return menuRepository.findAllByRestaurantIdAndEnabledTrue(restaurantId);
    }

    @Override
    public Menu getEnabledMenuFromDb(int menuId, int restaurantId) {
        Menu menu = menuRepository.findByIdAndRestaurantIdAndEnabledTrue(menuId, restaurantId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            return menu;
        }
    }

    @Override
    public Set<Item> getItemsByMenuIdFromDb(int menuId, int restaurantId) {
        Menu menu = menuRepository.findByIdAndRestaurantIdAndEnabledTrue(menuId, restaurantId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            return menu.getItems();
        }
    }

    @Override
    public Menu addOrEditMenuToDb(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delete(int menuId, int restaurantId) {
        Menu menu = menuRepository.findByIdAndRestaurantIdAndEnabledTrue(menuId, restaurantId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            menu.setEnabled(false);
            menuRepository.saveAndFlush(menu);
        }
    }

    @Override
    public Menu changeActivityInDb(int menuId, int restaurantId) {
        Menu menu = menuRepository.findByIdAndRestaurantIdAndEnabledTrue(menuId, restaurantId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            setAllInactiveInDb(restaurantId);
            if(menu.isActive()) {
                menu.setActive(false);
                return menuRepository.saveAndFlush(menu);
            } else {
                menu.setActive(true);
                return menuRepository.saveAndFlush(menu);
            }
        }
    }

    @Override
    public void setAllInactiveInDb(int restaurantId) {
        Set<Menu> menus = getAllEnabledMenuFromDb(restaurantId);
        for(Menu menu : menus) {
            menu.setActive(false);
            menuRepository.saveAndFlush(menu);
        }
    }
}
