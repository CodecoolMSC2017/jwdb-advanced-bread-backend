package com.codecool.bread.model;


import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient  extends POSObject {

    private String name;
    private String allergen;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "item_ingredient",joinColumns = {@JoinColumn(name = "ingredient_id")},inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> items = new HashSet<>();

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getAllergen() {
        return allergen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }
}
