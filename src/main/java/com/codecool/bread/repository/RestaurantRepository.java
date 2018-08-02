package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Set<Restaurant> findByOwnerId(Integer id);

    Set<Restaurant> findByOwnerIdAndEnabledTrue(int ownerId);

    Restaurant findByIdAndOwnerId(Integer restaurantId, Integer ownerId);

    Restaurant findByEmployeesId(Integer employeesId);
}
