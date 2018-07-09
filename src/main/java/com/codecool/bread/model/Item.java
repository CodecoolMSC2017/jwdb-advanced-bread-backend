package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends POSObject {

    private BigDecimal price;
    private String comment;
    private Category category;
    @Column(name = "restaurant_id")
    private int restaurantId;

    public BigDecimal getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
