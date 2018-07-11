package com.codecool.bread.model;


import javax.persistence.*;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant extends POSObject {

    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;
    private String email;
    private int phone;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<com.codecool.bread.model.Table> tables = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Employee> employees = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Item> items = new HashSet<>();

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public Owner getOwner() {
        return owner;
    }

    public Set<com.codecool.bread.model.Table> getTables() {
        return tables;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setTables(Set<com.codecool.bread.model.Table> tables) {
        this.tables = tables;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
