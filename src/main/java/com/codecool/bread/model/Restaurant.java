package com.codecool.bread.model;


import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant extends POSObject {

    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;
    private String email;
    private int phone;
    @Column(name = "owner_id")
    private int ownerId;

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

    public int getOwnerId() {
        return ownerId;
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

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
