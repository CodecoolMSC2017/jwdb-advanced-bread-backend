package com.codecool.bread.model.dto;

import java.math.BigDecimal;

public class InvoiceItemDto {

    private int itemId;
    private int quantity;
    private String itemName;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public InvoiceItemDto() {
    }

    public InvoiceItemDto(int itemId, int quantity, String itemName, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
