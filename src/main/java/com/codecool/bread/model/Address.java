package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String state;
    private String country;

    public Address(int id, String street, String city, String postalCode, String state, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;


    }

    public Address(){

    }

    public int getId(){
        return id;
    }


    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}




