package com.codecool.bread.model.dto;

import com.codecool.bread.model.Address;
import com.codecool.bread.model.User;

public class ProfileDto {

    private User user;
    private String firstName;
    private String lastName;
    private Address address;
    private String email;

    public ProfileDto(User user, String firstName, String lastName, Address address, String email) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
