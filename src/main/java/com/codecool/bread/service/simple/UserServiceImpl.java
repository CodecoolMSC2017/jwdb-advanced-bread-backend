package com.codecool.bread.service.simple;

import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.exception.UsernameTakenException;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User get(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public User add(String username, String password, String confirmationPassword) {
        if (!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameTakenException(username);
        } else {
            userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                    username,
                    passwordEncoder.encode(password),
                        AuthorityUtils.createAuthorityList("ROLE_USER")));
            return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        }
    }

    public void changePassword(String oldPassword, String newPassword, String confirmationPassword) {
        if (!newPassword.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        userDetailsManager.changePassword(oldPassword, encodedNewPassword);
    }
}
