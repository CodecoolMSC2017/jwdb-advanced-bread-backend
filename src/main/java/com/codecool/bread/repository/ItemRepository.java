package com.codecool.bread.repository;

import com.codecool.bread.exception.ItemNotFoundException;
import com.codecool.bread.model.Category;
import com.codecool.bread.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByRestaurantId(Integer restaurantId);

    List<Item> findByRestaurantIdAndEnabledTrue(Integer restaurantId);

    Optional<Item> findByIdAndRestaurantId(Integer id, Integer restaurantId) throws ItemNotFoundException;

    Optional<Item> findByName(String name) throws ItemNotFoundException;

    Optional<Item> findByIdAndRestaurantIdAndEnabledTrue(Integer id, Integer restaurantId) throws ItemNotFoundException;

    List<Item> findByRestaurantIdAndEnabledTrueAndCategory(Integer restaurantId, Category category);

}
