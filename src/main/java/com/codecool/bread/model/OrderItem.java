package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem  extends POSObject {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id" )
    private Item item;
    private int quantity;
    private String comment;

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public String getComment() {
        return comment;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
