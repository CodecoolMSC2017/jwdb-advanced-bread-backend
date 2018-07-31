package com.codecool.bread.repository;

import com.codecool.bread.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value = "SELECT menu.id, menu.title, menu.active, menu.enabled FROM menu\n" +
            "JOIN menu_item ON menu_item.menu_id = menu.id\n" +
            "JOIN item ON item.id = menu_item.item_id\n" +
            "JOIN restaurant ON restaurant.id = item.restaurant_id\n" +
            "WHERE restaurant.id = ?1",nativeQuery = true)
    Set<Menu> findAllByRestaurantId(Integer restaurantId);

    @Query(value = "SELECT menu.id, menu.title, menu.active, menu.enabled FROM menu\n" +
            "JOIN menu_item ON menu_item.menu_id = menu.id\n" +
            "JOIN item ON item.id = menu_item.item_id\n" +
            "JOIN restaurant ON restaurant.id = item.restaurant_id\n" +
            "WHERE menu.id = ?1 AND restaurant.id = ?2", nativeQuery = true)
    Menu findByIdAndRestaurantId(Integer id, Integer restaurantId);
}
