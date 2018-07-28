package com.codecool.bread.repository;

import com.codecool.bread.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    Optional<OrderItem> findByIdAndEnabledTrue(Integer orderItemId);
}
