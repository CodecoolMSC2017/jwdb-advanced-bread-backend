package com.codecool.bread.repository;

import com.codecool.bread.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByEnabledTrueAndRestaurantIdOrderByLastName(Integer restaurantId);

    Employee findByIdAndRestaurantId(Integer id, Integer restaurantId);

    Optional<Employee> findByUserId(Integer id);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByIdAndRestaurantIdAndUserNull(Integer employeeId, Integer restaurantId);

    @Query(value="SELECT * FROM employee where restaurant_id = ?1 AND user_id != ?2", nativeQuery = true)
    Set<Employee> findByRestaurantIdAndUserIdNotEnabledTrue(Integer restaurantId, Integer userId);
}
