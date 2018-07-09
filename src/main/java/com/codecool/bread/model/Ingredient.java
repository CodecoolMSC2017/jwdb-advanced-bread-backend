package com.codecool.bread.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient  extends POSObject {

    private String name;
    private String allergen;

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
