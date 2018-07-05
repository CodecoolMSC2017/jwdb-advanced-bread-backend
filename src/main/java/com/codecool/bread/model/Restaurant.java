package com.codecool.bread.model;

public final class Restaurant extends POSObject {
    private String name;
    private Address address;
    private String email;
    private int phone;
    private int owner_id;

    public Restaurant(int id, String name, Address address, String email, int phone, int owner_id) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.owner_id = owner_id;
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

    public int getOwner_id() {
        return owner_id;
    }
}
