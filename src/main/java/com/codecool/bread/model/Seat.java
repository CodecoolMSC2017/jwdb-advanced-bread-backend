package com.codecool.bread.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat extends POSObject {

    @Column(name = "active")
    private boolean isActive;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_table_id", nullable = false)
    @JsonBackReference
    private com.codecool.bread.model.Table table;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
    @JsonManagedReference
    private Set<CustomerOrder> orders = new HashSet<>();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public com.codecool.bread.model.Table getTable() {
        return table;
    }

    public void setTable(com.codecool.bread.model.Table table) {
        this.table = table;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }
}
