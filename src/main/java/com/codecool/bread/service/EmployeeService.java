package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.NoEmployeeForRestaurantException;
import com.codecool.bread.exception.OwnerNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeService {

    List<Employee> getAllByRestaurantId(int employeeId, int restaurantId) throws NoEmployeeForRestaurantException;

    List<Employee> getAllEmployees(int ownerId);

    Employee getById(int employeeId, int restaurantId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee getById(int employeeId) throws EmployeeNotFoundException;

    Employee getOwnerById( int ownerId) throws OwnerNotFoundException;

    Employee getByIdAndUserIdNull(int employeeId, int restaurantId) throws EmployeeNotFoundException;

    Employee getByUsername(String username) throws EmployeeNotFoundException, UsernameNotFoundException;

    Employee getByIdAndRestaurantIdAndOwnerId(int employeeId, int restaurantId, int ownerId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee add(Employee employee, int restaurantId, int loggedInEmployeeId) throws RestaurantAccessDeniedException;

    void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException;

    Employee editChanges(Employee employee, int restaurantId, int ownerId) throws EmployeeNotFoundException;

    Employee addUsername(Map<String,String> user, int employeeId) throws EmployeeNotFoundException;

    Employee getEmployeeByUserId(Integer id) throws EmployeeNotFoundException;

    void setAllEmployeeRestaurantNull(int ownerId);

}