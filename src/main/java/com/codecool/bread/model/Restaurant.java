package com.codecool.bread.model;


import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Address address;
    private String email;
    private int phone;
    @Column(name = "owner_id")
    private int ownerId;

    public Restaurant(int id, String name, Address address, String email, int phone, int ownerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.ownerId = ownerId;
    }
    public int getId(){
        return id;
    }

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
