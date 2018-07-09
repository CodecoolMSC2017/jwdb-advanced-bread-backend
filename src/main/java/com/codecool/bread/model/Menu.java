package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name = "active")
    private boolean isActive;

    public Menu(int id, String title, boolean isActive) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
    }

    public Menu(){
    }

    public int getId(){
        return id;
    }

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
