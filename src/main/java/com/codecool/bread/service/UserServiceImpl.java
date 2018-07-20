package com.codecool.bread.service;

import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.simple.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Optional get(String username) {
        User user = userRepository.findByUsername(username).get();
        Optional owner = ownerRepository.findByUserId(user.getId());
        Optional employee = employeeRepository.findByUserId(user.getId());
        if(owner.isPresent()) {
            return owner;
        }
        return employee;
    }

    public User add(String username, String password, String confirmationPassword) {
        if (!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }

        userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                username,
                passwordEncoder.encode(password),
                AuthorityUtils.createAuthorityList("USER_ROLE")));
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public void changePassword(String oldPassword, String newPassword, String confirmationPassword) {
        if (!newPassword.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        userDetailsManager.changePassword(oldPassword, encodedNewPassword);
    }
}
