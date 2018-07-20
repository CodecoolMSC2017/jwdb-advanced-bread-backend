package com.codecool.bread.repository;

import com.codecool.bread.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByRestaurantId(Integer restaurantId);
    Employee findByIdAndRestaurantId(Integer id, Integer restaurantId);
    Optional<Employee> findByUserId(Integer id);
}
