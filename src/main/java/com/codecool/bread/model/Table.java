package com.codecool.bread.model;


import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "active")
    private boolean isActive;
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Column(name = "employee_id")
    private int employeeId;

    public Table(int id, String name, boolean isActive, int restaurantId, int employeeId) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.restaurantId = restaurantId;
        this.employeeId = employeeId;
    }

    public Table() {
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
