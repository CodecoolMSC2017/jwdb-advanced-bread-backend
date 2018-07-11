package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesByRestaurantId(int restaurantId);

    Employee getEmployeeById(int restaurantId,int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

}
