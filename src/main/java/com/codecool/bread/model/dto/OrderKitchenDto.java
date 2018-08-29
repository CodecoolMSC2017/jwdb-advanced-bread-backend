package com.codecool.bread.model.dto;

import com.codecool.bread.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.web.JsonPath;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class OrderKitchenDto {

    private OrderItem orderedItem;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
