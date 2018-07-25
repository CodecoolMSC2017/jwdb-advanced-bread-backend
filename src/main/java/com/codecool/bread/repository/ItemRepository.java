package com.codecool.bread.repository;

import com.codecool.bread.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByRestaurantId(Integer restaurantId);
    List<Item> findByRestaurantIdAndEnabledTrue(Integer restaurantId);
    Item findByIdAndRestaurantId(Integer id, Integer restaurantId);
    Optional<Item> findByName(String name);
}
