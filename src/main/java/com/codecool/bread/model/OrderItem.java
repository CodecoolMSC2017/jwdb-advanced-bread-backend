package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "item_id")
    private int itemId;
    private int quantity;
    private String comment;

    public OrderItem(int id, int itemId, int quantity, String comment) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.comment = comment;
    }

    public OrderItem(){

    }

    public int getId(){
        return id;
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

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
