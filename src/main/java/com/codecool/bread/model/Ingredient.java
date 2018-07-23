package com.codecool.bread.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient extends POSObject {

    private String name;
    private String allergen;
    @ManyToMany(mappedBy = "ingredients"/*,cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
    //@JoinTable(name = "item_ingredient", joinColumns = {@JoinColumn(name = "ingredient_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    @JsonIgnore
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, allergen);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Ingredient)) {
            return false;
        }
        Ingredient ingredient = (Ingredient) obj;

        return Objects.equals(name, ingredient.name) &&
                Objects.equals(allergen, ingredient.allergen);
    }
}
