package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat extends POSObject{

    @Column(name = "active")
    private boolean isActive;
    @Column(name = "table_id")
    private int tableId;


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
