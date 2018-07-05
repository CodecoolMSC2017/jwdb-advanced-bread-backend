package com.codecool.bread.model;

public final class Seat extends POSObject {
    private boolean isActive;
    private int table_id;

    public Seat(int id, boolean isActive, int table_id) {
        super(id);
        this.isActive = isActive;
        this.table_id = table_id;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getTable_id() {
        return table_id;
    }
}
