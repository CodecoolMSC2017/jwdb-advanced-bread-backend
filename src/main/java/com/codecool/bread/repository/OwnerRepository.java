package com.codecool.bread.repository;

import com.codecool.bread.model.Owner;
import com.codecool.bread.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
