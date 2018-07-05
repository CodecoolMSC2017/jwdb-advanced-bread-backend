package com.codecool.bread.model;

public final class OrderItem extends POSObject{

    private int itemId;
    private int quantity;
    private String comment;

    public OrderItem(int id, int itemId, int quantity, String comment) {
        super(id);
        this.itemId = itemId;
        this.quantity = quantity;
        this.comment = comment;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getComment() {
        return comment;
    }
}
