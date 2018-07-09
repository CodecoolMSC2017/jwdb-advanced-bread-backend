package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Address address;
    private String email;
    private String password;

    public Owner(int id, UUID uuid, String firstName, String lastName, Address address, String email, String password) {
        this.id = id;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }
    public Owner(){
    }

    public int getId(){
        return id;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
