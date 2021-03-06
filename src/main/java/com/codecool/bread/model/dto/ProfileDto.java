package com.codecool.bread.model.dto;

import com.codecool.bread.model.Address;
import com.codecool.bread.model.Role;
import com.codecool.bread.model.User;

public class ProfileDto {

    private User user;
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private Role role;
    private int restaurantId;

    public ProfileDto() {
    }

    public ProfileDto(User user, String firstName, String lastName, Address address, String email, Role role) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public ProfileDto(User user, String firstName, String lastName, Address address, String email, Role role, int restaurantId) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.role = role;
        this.restaurantId = restaurantId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
