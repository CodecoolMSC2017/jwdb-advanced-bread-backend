package com.codecool.bread.service.simple;

import com.codecool.bread.exception.EmployeeNotFoundException;
import com.codecool.bread.exception.ProfileNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Role;
import com.codecool.bread.model.dto.ProfileDto;
import com.codecool.bread.service.EmployeeService;
import com.codecool.bread.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl extends AbstractService implements ProfileService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ProfileDto get(int userId) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeByUserId(userId);
        if(employee.getRole().equals(Role.OWNER)) {
            return new ProfileDto(employee.getUser(), employee.getFirstName(),
                    employee.getLastName(), employee.getAddress(), employee.getEmail(), Role.OWNER);
        } else if (employeeRepository.findByUserId(userId).isPresent()) {
            Restaurant restaurant = employee.getRestaurant();
            return new ProfileDto(employee.getUser(), employee.getFirstName(),
                    employee.getLastName(), employee.getAddress(), employee.getEmail(), employee.getRole(), restaurant.getId());
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
