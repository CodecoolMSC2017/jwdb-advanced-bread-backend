package com.codecool.bread.repository;

import com.codecool.bread.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    List<Table> findByRestaurantId(int id);
    Table findByIdAndRestaurantId(Integer id, Integer restaurantId);
}
