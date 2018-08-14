package com.codecool.bread.model.dto;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.OrderItem;

import java.time.LocalDateTime;

public class OrderKitchenDto {

    private OrderItem orderedItem;

    private LocalDateTime orderingTime;

    public OrderKitchenDto() {}

    public OrderKitchenDto(OrderItem orderItem, LocalDateTime orderingTime) {
        this.orderedItem = orderItem;
        this.orderingTime = orderingTime;

    }

    public OrderItem getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(OrderItem orderedItem) {
        this.orderedItem = orderedItem;
    }

    public LocalDateTime getorderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(LocalDateTime orderingTime) {
        this.orderingTime = orderingTime;
    }
}
