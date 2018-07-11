package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends POSObject {

    private String name;
    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal price;
    private String comment;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public BigDecimal getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
