package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public List<Employee> getAllByRestaurantIdFromDb(int restaurantId) throws SQLException {
        return employeeRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Employee getByIdFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException {
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByRestaurantId(restaurantId).contains(employeeRepository.findById(employeeId).get())) {
                return employeeRepository.findById(employeeId).get();
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public void addNewToDb(Employee employee, int ownerId, int restaurantId) throws SQLException {
        employee.setRestaurant(restaurantService.getRestaurantById(restaurantId, ownerId));
        employeeRepository.save(employee);
    }

    @Override
    public void deleteFromDb(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException {
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByRestaurantId(restaurantId).contains(employeeRepository.findById(employeeId).get())) {
                employeeRepository.deleteById(employeeId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee saveChanges(Employee employee, int restaurantId, int ownerId) throws SQLException {
        employee.setRestaurant(restaurantService.getRestaurantById(restaurantId, ownerId));
        return employeeRepository.save(employee);
    }
}
