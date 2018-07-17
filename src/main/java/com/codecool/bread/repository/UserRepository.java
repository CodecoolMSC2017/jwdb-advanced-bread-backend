package com.codecool.bread.repository;

import com.codecool.bread.exception.UserNotFoundException;
import com.codecool.bread.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username) throws UserNotFoundException;
}
