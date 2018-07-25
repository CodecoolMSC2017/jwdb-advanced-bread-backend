package com.codecool.bread.service.simple;

import com.codecool.bread.exception.ProfileNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.dto.ProfileDto;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ProfileDto getProfileFromDb(int userId) {
        if(ownerRepository.findByUserId(userId).isPresent()) {
            Owner owner = ownerRepository.findByUserId(userId).get();
            return new ProfileDto(owner.getUser(), owner.getFirstName(),
                    owner.getLastName(), owner.getAddress(), owner.getEmail());
        } else if (employeeRepository.findByUserId(userId).isPresent()) {
            Employee employee = employeeRepository.findByUserId(userId).get();
            return new ProfileDto(employee.getUser(), employee.getFirstName(),
                    employee.getLastName(), employee.getAddress(), employee.getEmail());
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
