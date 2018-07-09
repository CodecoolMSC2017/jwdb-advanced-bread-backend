package com.codecool.bread.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal price;
    private String comment;
    private Category category;
    @Column(name = "restaurant_id")
    private int restaurantId;

    public Item(int id, BigDecimal price, String comment, Category category, int reastaurantId) {
        this.id = id;
        this.price = price;
        this.comment = comment;
        this.category = category;
        this.restaurantId = reastaurantId;
    }

    public int getId(){
        return id;
    }

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
