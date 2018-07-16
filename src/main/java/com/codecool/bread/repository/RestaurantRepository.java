package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByOwnerId(Integer id);
    Restaurant findByIdAndOwnerId(Integer ownerId, Integer restaurantId);
}
