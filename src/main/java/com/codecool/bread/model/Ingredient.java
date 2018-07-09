package com.codecool.bread.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String allergen;

    public int getId(){
        return id;
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
