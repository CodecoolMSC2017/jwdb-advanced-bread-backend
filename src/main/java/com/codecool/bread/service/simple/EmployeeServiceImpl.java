package com.codecool.bread.service.simple;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
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

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public List<Employee> getAllByRestaurantId(int ownerId, int restaurantId) {
        restaurantService.getById(restaurantId, ownerId);
        return employeeRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Employee getById(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
    }

    @Override
    public Employee add(Employee employee, int ownerId, int restaurantId) {
        employee.setRestaurant(restaurantService.getById(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException, SQLException {
        employeeRepository.delete(employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId));
    }

    @Override
    public Employee editChanges(Employee employee, int restaurantId, int ownerId) {
        employee.setRestaurant(restaurantService.getById(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }
}
