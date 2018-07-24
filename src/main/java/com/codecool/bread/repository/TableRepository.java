package com.codecool.bread.repository;

import com.codecool.bread.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {

    Set<Table> findByRestaurantId(int id);

    Optional<Table> findByIdAndRestaurantId(Integer id, Integer restaurantId);
}
