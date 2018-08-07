package com.codecool.bread.service.simple;

import com.codecool.bread.exception.PasswordNotMatchException;
import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.exception.UsernameTakenException;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import com.codecool.bread.model.dto.ChangePasswordDto;
import com.codecool.bread.repository.EmployeeRepository;
import com.codecool.bread.repository.OwnerRepository;
import com.codecool.bread.repository.UserRepository;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Map;
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

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        Optional<Owner> ownerOptional = ownerRepository.findByEmail(email);

        if(employeeOptional.isPresent()) {
            return employeeOptional.get().getUser();
        } else if(ownerOptional.isPresent()) {
            return ownerOptional.get().getUser();
        } else {
            throw new UserNotFoundException();
        }
    }

    public User getUserByIdAndUserName(int userId, String username) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByIdAndUsername(userId, username);
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    public User add(String username, String password, String confirmationPassword) {
        if (!password.equals(confirmationPassword)) {
            throw new PasswordNotMatchException();
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

    @Override
    public User changeForgotPassword(ChangePasswordDto passwordDto) throws UserNotFoundException, PasswordNotMatchException {
        String username = passwordDto.getUsername();
        String newPassword = passwordDto.getPassword();
        String confirmNewPassword = passwordDto.getConfirmationPassword();
        String authority = passwordDto.getAuthorities()[0];

        Optional<User> existingUser = userRepository.findByUsername(username);
        if(!existingUser.isPresent()) {
            throw new UserNotFoundException();
        }
        if(!newPassword.equals(confirmNewPassword)) {
            throw new PasswordNotMatchException();
        }
        userDetailsManager.updateUser(new org.springframework.security.core.userdetails.User(
                username,
                passwordEncoder.encode(newPassword),
                AuthorityUtils.createAuthorityList(authority)));
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
