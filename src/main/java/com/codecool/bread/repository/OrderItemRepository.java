package com.codecool.bread.repository;

import com.codecool.bread.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    Optional<OrderItem> findByIdAndEnabledTrue(Integer orderItemId);

    @Query(value = "Select order_item.id AS id, item_id, quantity, comment, ready,order_item.enabled from item\n" +
            "inner JOIN order_item\n" +
            "on item.id = order_item.item_id\n" +
            "where ready = false and restaurant_id = ?1 And category = ?2",nativeQuery = true)
    List<OrderItem> findByItemTypeAndRestaurantId(Integer restaurantId, String category);
}
