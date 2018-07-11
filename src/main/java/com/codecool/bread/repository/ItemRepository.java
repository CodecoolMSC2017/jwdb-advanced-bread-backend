package com.codecool.bread.repository;

import com.codecool.bread.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByRestaurantId(Integer restaurantId);
}
