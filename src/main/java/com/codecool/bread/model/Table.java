package com.codecool.bread.model;

public final class Table extends POSObject {
    private String name;
    private boolean isActive;
    private int restaurantId;
    private int employeeId;

    public Table(int id, String name, boolean isActive, int restaurantId, int employeeId) {
        super(id);
        this.name = name;
        this.isActive = isActive;
        this.restaurantId = restaurantId;
        this.employeeId = employeeId;
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
}
