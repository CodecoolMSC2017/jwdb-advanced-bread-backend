package com.codecool.bread.repository;

import com.codecool.bread.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value = "SELECT menu.id, menu.title, menu.active, menu.enabled FROM menu\n" +
            "JOIN menu_item ON menu_item.menu_id = menu.id\n" +
            "JOIN item ON item.id = menu_item.item_id\n" +
            "JOIN restaurant ON restaurant.id = item.restaurant_id\n" +
            "WHERE restaurant.id = ?1 AND menu.enabled = true",nativeQuery = true)
    Set<Menu> findAllByRestaurantIdAndEnabledTrue(Integer restaurantId);

    @Query(value = "SELECT menu.id, menu.title, menu.active, menu.enabled FROM menu\n" +
            "JOIN menu_item ON menu_item.menu_id = menu.id\n" +
            "JOIN item ON item.id = menu_item.item_id\n" +
            "JOIN restaurant ON restaurant.id = item.restaurant_id\n" +
            "WHERE menu.id = ?1 AND menu.enabled = true", nativeQuery = true)
    Menu findByIdAndEnabledTrue(Integer id);

    @Query(value = "SELECT DISTINCT menu.id, menu.title, menu.active, menu.enabled FROM menu\n" +
            "JOIN menu_item ON menu_item.menu_id = menu.id\n" +
            "JOIN item ON item.id = menu_item.item_id\n" +
            "JOIN restaurant ON restaurant.id = item.restaurant_id\n" +
            "WHERE restaurant.id = ?1 AND menu.active = true", nativeQuery = true)
    Optional<Menu> findByRestaurantIdAndActiveTrue(Integer restaurantId);
}
