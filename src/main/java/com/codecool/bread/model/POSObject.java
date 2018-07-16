package com.codecool.bread.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class POSObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
