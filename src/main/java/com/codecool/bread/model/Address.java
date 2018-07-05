package com.codecool.bread.model;

public final class Address extends POSObject {
    private String street;
    private String city;
    private String postalCode;
    private String state;
    private String country;

    public Address(int id, String street, String city, String postalCode, String state, String country) {
        super(id);
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;


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
}




