package com.codecool.bread.service.simple;

import com.codecool.bread.exception.MenuNotFoundException;
import com.codecool.bread.model.Item;
import com.codecool.bread.model.Menu;
import com.codecool.bread.service.ItemService;
import com.codecool.bread.service.MenuService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MenuServiceImpl extends AbstractService implements MenuService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemService itemService;

    @Override
    public Set<Menu> getAllEnabledMenuFromDb(int restaurantId) {
        return menuRepository.findAllByRestaurantIdAndEnabledTrue(restaurantId);
    }

    @Override
    public Menu getEnabledMenuFromDb(int menuId) {
        Menu menu = menuRepository.findByIdAndEnabledTrue(menuId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            return menu;
        }
    }

    @Override
    public Set<Item> getItemsByMenuIdFromDb(int menuId) {
        Menu menu = menuRepository.findByIdAndEnabledTrue(menuId);
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
    public void delete(int menuId) {
        Menu menu = menuRepository.findByIdAndEnabledTrue(menuId);
        if(menu == null || !menu.isEnabled()) {
            throw new MenuNotFoundException();
        } else {
            menu.setEnabled(false);
            menu.setActive(false);
            menuRepository.saveAndFlush(menu);
        }
    }

    @Override
    public Menu changeActivityInDb(Menu menu, int restaurantId) {
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

    @Override
    public Menu getActiveMenu(int restaurantId) throws MenuNotFoundException {
        Optional<Menu> menu = menuRepository.findByRestaurantIdAndActiveTrue(restaurantId);
        if (!menu.isPresent()) {
            throw new MenuNotFoundException();
        }
        return menu.get();
    }

    @Override
    public Menu addItemToMenuInDb(Item item, int menuId) {
        Menu menu = menuRepository.findByIdAndEnabledTrue(menuId);
        menu.getItems().add(item);
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void deleteItem(int menuId, int itemId) {
        Menu menu = getEnabledMenuFromDb(menuId);
        Item itemToDelete = itemService.getById(itemId);
        menu.getItems().remove(itemToDelete);
        menuRepository.saveAndFlush(menu);
    }
}
