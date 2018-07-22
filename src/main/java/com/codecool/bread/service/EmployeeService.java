package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.NoEmployeeForRestaurantException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Set<Employee> getAllByRestaurantId(int ownerId, int restaurantId) throws NoEmployeeForRestaurantException;

    Employee getById(int employeeId, int restaurantId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee getByIdAndRestaurantIdAndOwnerId(int employeeId, int restaurantId, int ownerId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee add(Employee employee, int ownerId, int restaurantId);

    void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee editChanges(Employee employee, int restaurantId, int ownerId);

}
