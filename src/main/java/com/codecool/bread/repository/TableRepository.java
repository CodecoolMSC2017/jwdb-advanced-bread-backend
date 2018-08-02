package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {

    Set<Table> findByRestaurantId(Integer id);

    Optional<Table> findByIdAndRestaurantId(Integer id, Integer restaurantId);

    List<Table> findByRestaurantIdAndEnabledTrueOrderByName(Integer restaurantId);

    Optional<Table> findByIdAndRestaurantIdAndEnabledTrue(Integer tableId, Integer restaurantId);

}
