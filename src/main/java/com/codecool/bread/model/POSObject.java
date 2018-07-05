package com.codecool.bread.model;

public abstract class POSObject {

    private int id;

    public POSObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
