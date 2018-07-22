package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllByRestaurantId(int ownerId, int restaurantId) throws SQLException;

    Employee getById(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee add(Employee employee, int ownerId, int restaurantId) throws SQLException;

    void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException;

    Employee editChanges(Employee employee, int restaurantId, int ownerId) throws SQLException;

}
