package com.codecool.bread.service.simple;

import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> get(Integer id);

    void delete(Integer id);

    Iterable<User> getAll();

    User get(String username);

    User add(String username, String password, String confirmationPassword);

    void changePassword(String oldPassword, String newPassword, String confirmationPassword);


}
