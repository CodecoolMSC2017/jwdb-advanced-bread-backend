package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByOwnerId(Integer id);

    Restaurant findByIdAndOwnerId(Integer restaurantId, Integer ownerId);
}
