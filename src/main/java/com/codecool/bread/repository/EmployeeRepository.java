package com.codecool.bread.repository;

import com.codecool.bread.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Set<Employee> findByRestaurantIdAndRestaurantOwnerId(Integer restaurantId, Integer restaurantOwnerId);

    Employee findByIdAndRestaurantId(Integer id, Integer restaurantId);

    Optional<Employee> findByUserId(Integer id);
}
