package com.codecool.bread.model.dto;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.OrderItem;

import java.time.LocalDateTime;

public class OrderKitchenDto {

    private OrderItem orderedItem;

    private LocalDateTime ordering_time;

    public OrderKitchenDto() {}

    public OrderKitchenDto(OrderItem orderItem, LocalDateTime ordering_time) {
        this.orderedItem = orderItem;
        this.ordering_time = ordering_time;

    }

    public OrderItem getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(OrderItem orderedItem) {
        this.orderedItem = orderedItem;
    }

    public LocalDateTime getOrdering_time() {
        return ordering_time;
    }

    public void setOrdering_time(LocalDateTime ordering_time) {
        this.ordering_time = ordering_time;
    }
}
