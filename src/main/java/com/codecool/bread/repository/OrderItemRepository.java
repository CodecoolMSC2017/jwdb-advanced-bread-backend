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

    @Query(value = "Select order_item.id AS id, item_id, quantity, comment, ready,order_item.enabled    from order_item\n" +
            "inner JOIN customer_order\n" +
            "on order_item.id = customer_order.order_item_id\n" +
            "inner join item on order_item.item_id = item.id\n" +
            "where item.restaurant_id = 1 AND category = 'FOOD' AND invoice_id is null AND ready IS FALSE",nativeQuery = true)
    List<OrderItem> findByItemTypeAndRestaurantId(Integer restaurantId, String category);
}
