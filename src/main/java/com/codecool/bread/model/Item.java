package com.codecool.bread.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Entity
@Table(name = "item")
public class Item extends POSObject {


    private String name;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal price;
    private String comment;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "item_ingredient", joinColumns = {@JoinColumn(name = "item_id")}, inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    //@JsonIgnore
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, comment, category, ingredients, restaurant);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                price == item.price &&
                Objects.equals(comment, item.comment) &&
                Objects.equals(category, item.category) &&
                Objects.equals(ingredients, item.ingredients) &&
                Objects.equals(restaurant, item.restaurant);
    }
}

