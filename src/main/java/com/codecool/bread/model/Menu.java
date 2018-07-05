package com.codecool.bread.model;

public final class Menu extends POSObject{
    private String title;
    private boolean isActive;

    public Menu(int id, String title, boolean isActive) {
        super(id);
        this.title = title;
        this.isActive = isActive;
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return isActive;
    }
}
