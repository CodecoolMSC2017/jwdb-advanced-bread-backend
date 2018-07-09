package com.codecool.bread.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class POSObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }
}
