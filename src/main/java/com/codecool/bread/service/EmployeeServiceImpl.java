package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.simple.EmployeeService;
import com.codecool.bread.service.simple.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OwnerService ownerService;


    @Override
    public List<Employee> getAllByRestaurantIdFromDb(int ownerId, int restaurantId) throws SQLException {
        ownerService.getRestaurantByIdFromDb(restaurantId, ownerId);
        return employeeRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Employee getByIdFromDb(int ownerId, int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException{
        return employeeRepository.findById(employeeId).get();
    public Employee getByIdFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException {
        return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
    }

    @Override
    public Employee addNewToDb(Employee employee, int ownerId, int restaurantId) {
        employee.setRestaurant(ownerService.getRestaurantByIdFromDb(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException {
        employeeRepository.delete(employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId));
    }

    @Override
    public Employee saveChanges(Employee employee, int restaurantId, int ownerId) {
        employee.setRestaurant(ownerService.getRestaurantByIdFromDb(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }
}
