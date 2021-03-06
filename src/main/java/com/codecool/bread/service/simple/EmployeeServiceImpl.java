package com.codecool.bread.service.simple;

import com.codecool.bread.exception.*;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Role;
import com.codecool.bread.model.User;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeService")
public class EmployeeServiceImpl extends AbstractService implements EmployeeService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public List<Employee> getAllByRestaurantId(int employeeId, int restaurantId) throws NoEmployeeForRestaurantException {
        List<Employee> employees = employeeRepository.findByEnabledTrueAndRestaurantIdOrderByLastName(restaurantId);
        Employee employee = getById(employeeId);
        if (employees.isEmpty()) {
            throw new NoEmployeeForRestaurantException();
        } else if(employee.getRole().equals(Role.OWNER)){
            employees.remove(employee);
            return employees;
        } else {
            employees.remove(employee);
            return removeManagerRoleEmployees(employees);

        }
    }

    private List<Employee> removeManagerRoleEmployees(List<Employee> employees) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (!employee.getRole().equals(Role.MANAGER) && !employee.getRole().equals(Role.OWNER)) {
                result.add(employee);
            }
        }
        return result;
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
    public  Employee getById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee getOwnerById(int ownerId) throws OwnerNotFoundException {
        Optional<Employee> result = employeeRepository.findById(ownerId);
        if (result.isPresent() && result.get().getRole().equals(Role.OWNER)) {
            return result.get();
        } else {
            throw new OwnerNotFoundException();
        }
    }

    @Override
    public Employee getByIdAndUserIdNull(int employeeId, int restaurantId) throws EmployeeNotFoundException {
        Optional<Employee> employee =  employeeRepository.findByIdAndRestaurantIdAndUserNull(employeeId, restaurantId);
        if(!employee.isPresent()) {
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
            if (employeeRepository.findByEnabledTrueAndRestaurantIdOrderByLastName(ownerId)
                    .contains(employeeRepository.findById(employeeId).get())) {
                return employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId);
            } else {
                throw new RestaurantAccessDeniedException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee add(Employee employee, int restaurantId, int loggedInEmployeeId) throws RestaurantAccessDeniedException {
        Employee loggedInEmployee = getById(loggedInEmployeeId);
        Restaurant restaurant = restaurantService.getById(restaurantId);
        if (loggedInEmployee.getRole().equals(Role.OWNER) && restaurant.getOwner().equals(loggedInEmployee) ||
                restaurant.getEmployees().contains(loggedInEmployee)) {
            employee.setRestaurant(restaurantService.getById(restaurantId));
            employee.setEnabled(false);
            return employeeRepository.saveAndFlush(employee);
        } else {
            throw new RestaurantAccessDeniedException();
        }
    }

    @Override
    public void delete(int restaurantId, int employeeId) throws RestaurantAccessDeniedException, EmployeeNotFoundException {
        Optional<User> user = userService.get(employeeRepository.findByIdAndRestaurantId(employeeId, restaurantId).getUser().getId());
        if (!user.isPresent()) {
            throw new EmployeeNotFoundException();
        } else {
            userService.get(user.get().getUsername()).setEnabled(false);
            Employee employee = getById(employeeId);
            employee.setEnabled(false);
            employeeRepository.saveAndFlush(employee);
            userRepository.saveAndFlush(user.get());
        }
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
            employeeOptional.get().setEnabled(true);
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
        Employee owner = getById(ownerId);
        List<Employee> employees = getAllEmployees(owner.getId());
        for(Employee employee : employees) {
            employee.setRestaurant(null);
        }
        employeeRepository.saveAll(employees);
    }
}
