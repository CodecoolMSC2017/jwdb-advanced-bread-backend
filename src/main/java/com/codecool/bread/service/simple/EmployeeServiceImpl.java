package com.codecool.bread.service.simple;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.NoEmployeeForRestaurantException;
import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Set<Employee> getAllByRestaurantId(int ownerId, int restaurantId) throws NoEmployeeForRestaurantException {
        Set<Employee> result = employeeRepository.findByRestaurantIdAndRestaurantOwnerId(restaurantId, ownerId);
        if (result.isEmpty()) {
            throw new NoEmployeeForRestaurantException();
        } else {
            return result;
        }
    }

    @Override
    public Employee getById(int employeeId, int restaurantId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
    }

    @Override
    public Employee getByIdAndRestaurantIdAndOwnerId(int employeeId, int restaurantId, int ownerId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByRestaurantIdAndRestaurantOwnerId(ownerId, restaurantId).contains(employeeRepository.findById(employeeId).get())) {
                return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee add(Employee employee, int ownerId, int restaurantId) {
        employee.setRestaurant(restaurantService.getById(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        employeeRepository.delete(employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId));
    }

    @Override
    public Employee editChanges(Employee employee, int restaurantId, int ownerId) {
        employee.setRestaurant(restaurantService.getById(restaurantId, ownerId));
        employeeRepository.save(employee);
        return employee;
    }
}
