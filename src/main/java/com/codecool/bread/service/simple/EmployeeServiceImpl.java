package com.codecool.bread.service.simple;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Set<Employee> getAllByRestaurantId(int ownerId, int restaurantId) throws NoEmployeeForRestaurantException {
        Set<Employee> employees = employeeRepository.findByRestaurantIdAndRestaurantOwnerId(restaurantId, ownerId);
        if (employees.isEmpty()) {
            throw new NoEmployeeForRestaurantException();
        } else {
            return employees;
        }
    }

    public List<Employee> getAllEmployees(int ownerId) {
        List<Employee> employees = new ArrayList();
        Set<Restaurant> restaurants = restaurantService.getAllByOwnerId(ownerId);
        for (Restaurant restautrant : restaurants) {
            employees.addAll(restautrant.getEmployees());
        }
        if (employees.size() == 0) {
            throw new NoEmployeeFoundException();
        }
        return employees;
    }

    @Override
    public Employee getById(int employeeId, int restaurantId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
    }

    @Override
    public  Employee getById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee getByUsername(String username) throws EmployeeNotFoundException, UsernameNotFoundException {
        User user = userService.get(username);
        Optional<Employee> employee = employeeRepository.findById(user.getId());
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException();
        } else {
            return employee.get();
        }
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
    public Employee add(Employee employee, int restaurantId, int ownerId) {
        employee.setRestaurant(restaurantService.getById(restaurantId, ownerId));
        employeeRepository.saveAndFlush(employee);
        return employee;
    }

    @Override
    public void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        Optional<User> user = userService.get(employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId).getUser().getId());
        if (!user.isPresent()) {
            throw new EmployeeNotFoundException();
        } else {
            userService.get(user.get().getUsername()).setEnabled(false);
        }
        userRepository.saveAndFlush(user.get());
    }

    @Override
    public Employee editChanges(Employee employee, int restaurantId, int ownerId) throws EmployeeNotFoundException{
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee addUsername(Map<String, String> user, int employeeId) throws EmployeeNotFoundException {
        String username = user.get("username");
        String password = user.get("password");
        String confirmationPassword = user.get("confirmationPassword");
        userService.add(username, password, confirmationPassword);
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new EmployeeNotFoundException();
        } else {
            employeeOptional.get().setUser(userService.get(username));
        }
        return employeeOptional.get();
    }
}
