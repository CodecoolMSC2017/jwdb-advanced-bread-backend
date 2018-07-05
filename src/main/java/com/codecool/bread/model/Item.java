package com.codecool.bread.model;

import java.math.BigDecimal;

public final class Item extends POSObject {
    private BigDecimal price;
    private String comment;
    private Category category;
    private int reasturantId;

    public Item(int id, BigDecimal price, String comment, Category category, int reasturantId) {
        super(id);
        this.price = price;
        this.comment = comment;
        this.category = category;
        this.reasturantId = reasturantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }

    public int getReasturantId() {
        return reasturantId;
    }
}
