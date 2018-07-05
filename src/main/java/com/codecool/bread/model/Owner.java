package com.codecool.bread.model;

import java.util.UUID;

public final class Owner extends POSObject {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private String password;

    public Owner(int id, UUID uuid, String firstName, String lastName, Address address, String email, String password) {
        super(id);
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
