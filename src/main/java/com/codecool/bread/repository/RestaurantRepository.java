package com.codecool.bread.repository;

import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Set<Restaurant> findByOwnerId(Integer id);

    Set<Restaurant> findByOwnerIdAndEnabledTrue(int ownerId);

    Restaurant findByIdAndOwnerId(Integer restaurantId, Integer ownerId);

    @Query(value = "select restaurant.id,\n" +
            "\t   restaurant.name,\n" +
            "\t   restaurant.address_id,\n" +
            "\t   restaurant.email,\n" +
            "\t   restaurant.phone,\n" +
            "\t   restaurant.owner_id,\n" +
            "\t   restaurant.enabled\n" +
            "FROM restaurant\n" +
            "JOIN restaurant_table ON restaurant_table.restaurant_id = restaurant.id\n" +
            "WHERE restaurant_table.id = ?1", nativeQuery = true)
    Restaurant findByTableId(Integer tableId);


}
