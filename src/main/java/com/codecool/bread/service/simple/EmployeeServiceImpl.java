package com.codecool.bread.service.simple;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.*;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.SQLException;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("employeeService")
public class EmployeeServiceImpl extends AbstractService implements EmployeeService {

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Set<Employee> getAllByRestaurantId(Principal principal, int restaurantId) throws NoEmployeeForRestaurantException {
        Set<Employee> employees = returnEmployeeSet(principal, restaurantId);

        if (employees == null || employees.isEmpty()) {
            throw new NoEmployeeForRestaurantException();
        } else {
            return employees;
        }
    }

    public List<Employee> getAllEmployees(int ownerId) {
        List<Employee> employees = new ArrayList<>();
        Set<Restaurant> restaurants = restaurantService.getAllByOwnerId(ownerId);
        for (Restaurant restaurant : restaurants) {
            employees.addAll(restaurant.getEmployees());
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
    public Employee getById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee getByIdAndUserIdNull(int employeeId, int restaurantId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findByIdAndRestaurantIdAndUserNull(employeeId, restaurantId);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException();
        }
        return employee.get();
    }

    @Override
    public Employee getByUsername(String username) throws EmployeeNotFoundException, UsernameNotFoundException {
        User user = userService.get(username);
        Optional<Employee> employee = employeeRepository.findByUserId(user.getId());
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException();
        } else {
            return employee.get();
        }
    }

    @Override
    public Employee getByIdAndRestaurantIdAndOwnerId(int employeeId, int restaurantId, int ownerId) throws
            RestaurantAccessDeniedException, EmployeeNotFoundException {
        if (employeeRepository.findById(employeeId).isPresent()) {
            if (employeeRepository.findByEnabledTrueAndRestaurantIdAndRestaurantOwnerId(ownerId, restaurantId)
                    .contains(employeeRepository.findById(employeeId).get())) {
                return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee getByIdAndRestaurantId(int employeeId, int restaurantId, Principal principal) throws RestaurantAccessDeniedException {
        if(!isOwner(principal, restaurantId) && !isManager(principal, restaurantId)) {
            throw new RestaurantAccessDeniedException();
        }

        Employee employee = employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
        if(employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee add(Employee employee, int restaurantId, Principal principal) {
        Employee result = null;

        if (isOwner(principal)) {
            int ownerId = ownerService.getOwnerByUsername(principal.getName()).getId();
            employee.setRestaurant(restaurantService.getById(restaurantId, principal));
            result = employeeRepository.saveAndFlush(employee);
        }

        if (isManager(principal, restaurantId)) {
            employee.setRestaurant(restaurantService.getById(restaurantId));
            result = employeeRepository.saveAndFlush(employee);
        }
        return result;
    }

    @Override
    public void delete(int restaurantId, int employeeId, Principal principal) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        if(!isOwner(principal, restaurantId)
            && !isManager(principal, restaurantId)) {
            throw new RestaurantAccessDeniedException();
        }
        Employee employee = employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        if(employee.getUser()!=null) {
            employee.getUser().setEnabled(false);
        }
        employee.setEnabled(false);
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee editChanges(Employee employee, int restaurantId, Principal principal) throws EmployeeNotFoundException, RestaurantAccessDeniedException {
        if(isOwner(principal, restaurantId) || isManager(principal, restaurantId)) {
            employee.setRestaurant(restaurantService.getById(restaurantId,principal));
            return employeeRepository.saveAndFlush(employee);
        }  else {
            throw new RestaurantAccessDeniedException();
        }

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
        return employeeRepository.saveAndFlush(employeeOptional.get());
    }

    public Employee getEmployeeByUserId(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUserId(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public void setAllEmployeeRestaurantNull(int ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        List<Employee> employees = getAllEmployees(owner.getId());
        for (Employee employee : employees) {
            employee.setRestaurant(null);
        }
        employeeRepository.saveAll(employees);
    }

    private Set<Employee> returnEmployeeSet(Principal principal, int restaurantId) throws RestaurantAccessDeniedException {
        User user = userService.get(principal.getName());
        if (isOwner(principal)) {
            int ownerId = ownerService.getOwnerByUsername(principal.getName()).getId();
            return employeeRepository.findByEnabledTrueAndRestaurantIdAndRestaurantOwnerId(restaurantId, ownerId);
        } else if (isManager(principal, restaurantId)) {
            Employee loggedIn = employeeService.getByUsername(principal.getName());
            if (loggedIn.getRole().equals(Role.MANAGER)) {
                return employeeRepository.findByRestaurantId(restaurantId);
            }
        } else {
            throw new RestaurantAccessDeniedException();
        }
        return null;
    }
}
