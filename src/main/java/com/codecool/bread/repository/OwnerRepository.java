package com.codecool.bread.repository;

import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findByUserId(Integer userId);

    Optional<Owner> findByEmail(String email);
}
