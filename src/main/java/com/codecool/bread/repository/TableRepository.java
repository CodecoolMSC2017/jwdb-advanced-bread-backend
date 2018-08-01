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

    @Query(value = "select restaurant_table.id,\n" +
            "\t   restaurant_table.name,\n" +
            "\t   restaurant_table.active,\n" +
            "\t   restaurant_table.restaurant_id,\n" +
            "\t   restaurant_table.employee_id,\n" +
            "\t   restaurant_table.enabled\n" +
            "FROM restaurant_table\n" +
            "JOIN seat ON restaurant_table.id = seat.restaurant_table_id\n" +
            "WHERE seat.id = ?1", nativeQuery = true)
    Table findBySeatId(Integer seatId);
}
