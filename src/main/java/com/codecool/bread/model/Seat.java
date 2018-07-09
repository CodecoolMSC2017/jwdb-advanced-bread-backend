package com.codecool.bread.model;

import javax.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "active")
    private boolean isActive;
    @Column(name = "table_id")
    private int tableId;

    public Seat(int id, boolean isActive, int tableId) {
        this.id = id;
        this.isActive = isActive;
        this.tableId = tableId;
    }

    public Seat() {
    }

    public int getId(){
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getTableId() {
        return tableId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
