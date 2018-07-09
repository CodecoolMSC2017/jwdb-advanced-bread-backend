package com.codecool.bread.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String allergen;

    public Ingredient(int id, String name, String allergen) {
        this.id = id;
        this.name = name;
        this.allergen = allergen;
    }

    public Ingredient(){

    }

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
