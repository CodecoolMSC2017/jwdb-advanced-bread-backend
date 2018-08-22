package com.codecool.bread.service.simple;

import com.codecool.bread.exception.ProfileNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Role;
import com.codecool.bread.model.dto.ProfileDto;
import com.codecool.bread.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl extends AbstractService implements ProfileService {

    @Override
    public ProfileDto getProfileFromDb(int userId) {
        if(employeeRepository.findByUserId(userId).isPresent()) {
            Employee owner = employeeRepository.findByUserId(userId).get();
            return new ProfileDto(owner.getUser(), owner.getFirstName(),
                    owner.getLastName(), owner.getAddress(), owner.getEmail(), Role.OWNER);
        } else if (employeeRepository.findByUserId(userId).isPresent()) {
            Employee employee = employeeRepository.findByUserId(userId).get();
            Restaurant restaurant = employee.getRestaurant();
            return new ProfileDto(employee.getUser(), employee.getFirstName(),
                    employee.getLastName(), employee.getAddress(), employee.getEmail(), employee.getRole(), restaurant.getId());
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
