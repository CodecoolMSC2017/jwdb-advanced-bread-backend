package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findByEmployeesId(Integer employeesId);

    Set<Restaurant> findByOwnerId(Integer ownerId);

    Set<Restaurant> findByEmployeesIdAndEnabledTrue(Integer employeeId);
}
