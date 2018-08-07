package com.codecool.bread.service;

import com.codecool.bread.exception.PasswordNotMatchException;
import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import com.codecool.bread.model.dto.ChangePasswordDto;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> get(Integer id);

    void delete(Integer id);

    Iterable<User> getAll();

    User get(String username);

    User getUserByEmail(String email) throws UserNotFoundException;

    User getUserByIdAndUserName(int userId, String username) throws UserNotFoundException;

    User add(String username, String password, String confirmationPassword);

    User changeForgotPassword(ChangePasswordDto passwordDto) throws UserNotFoundException, PasswordNotMatchException;

    void changePassword(String oldPassword, String newPassword, String confirmationPassword);

}
