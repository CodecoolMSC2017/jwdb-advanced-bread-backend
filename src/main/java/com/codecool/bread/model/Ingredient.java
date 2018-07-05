package com.codecool.bread.model;

public final class Ingredient extends POSObject {

    private String name;
    private String allergen;

    public Ingredient(int id, String name, String allergen) {
        super(id);
        this.name = name;
        this.allergen = allergen;
    }

    public String getName() {
        return name;
    }

    public String getAllergen() {
        return allergen;
    }
}
