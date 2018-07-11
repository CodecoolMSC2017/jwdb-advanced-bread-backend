package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends POSObject {

    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal price;
    private String comment;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "item_ingredient", joinColumns = { @JoinColumn(name = "item_id")},inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public BigDecimal getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public Restaurant getRestaurant() {
        return restaurant;
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

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
