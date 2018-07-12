package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {


    List<Employee> getAllByRestaurantIdFromDb(int restaurantId) throws SQLException;

    Employee getByIdFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException;

    void addNewToDb(Employee employee, int ownerId, int restaurantId) throws SQLException;

    void deleteFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException;

    Employee saveChanges(Employee employee, int restaurantId, int ownerId) throws SQLException;

}
