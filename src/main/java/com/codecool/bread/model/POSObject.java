package com.codecool.bread.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class POSObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean enabled = true;

    public POSObject() {

    }

    public POSObject(int id,boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
