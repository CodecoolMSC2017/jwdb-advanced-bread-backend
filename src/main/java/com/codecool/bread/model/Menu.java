package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu extends POSObject {

    private String title;
    @Column(name = "active")
    private boolean isActive;

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
