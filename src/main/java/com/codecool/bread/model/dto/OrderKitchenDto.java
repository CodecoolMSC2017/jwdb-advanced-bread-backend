package com.codecool.bread.model.dto;

import com.codecool.bread.model.Item;
import com.codecool.bread.model.OrderItem;

public class OrderKitchenDto {

    private OrderItem orderedItem;
    private boolean isReady;

    public OrderKitchenDto() {}

    public OrderKitchenDto(OrderItem orderItem) {
        this.orderedItem = orderItem;
        this.isReady = false;
    }

    public OrderItem getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(OrderItem orderedItem) {
        this.orderedItem = orderedItem;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
