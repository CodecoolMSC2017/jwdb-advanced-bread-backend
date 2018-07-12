package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees() throws SQLException;

    List<Employee> getAllEmployeesByRestaurantId(int restaurantId) throws SQLException;

    Employee getEmployeeById(int restaurantId,int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException;

    void addNewEmployeeToDb(Employee employee) throws SQLException;

    void deleteEmployeeFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException;
}
