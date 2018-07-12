package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat extends POSObject{

    @Column(name = "active")
    private boolean isActive;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_table_id",nullable = false)
    private com.codecool.bread.model.Table table;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "seat")
    private Set<CustomerOrder> orders = new HashSet<>();

    public boolean isActive() {
        return isActive;
    }

    public com.codecool.bread.model.Table getTable() {
        return table;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTable(com.codecool.bread.model.Table table) {
        this.table = table;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }
}
