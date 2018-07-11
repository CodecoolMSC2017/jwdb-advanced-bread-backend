package com.codecool.bread.service;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesByRestaurantId(int restaurantId) {
        return employeeRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Employee getEmployeeById(int restaurantId,int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        if(employeeRepository.findById(employeeId).isPresent()) {
            if(employeeRepository.findByRestaurantId(restaurantId).contains(employeeRepository.findById(employeeId).get())) {
                return employeeRepository.findById(employeeId).get();
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }
}
