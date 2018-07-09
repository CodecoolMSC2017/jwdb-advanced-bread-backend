package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Role role;
    private String password;

    public Employee(int id, String email, String firstName, String lastName, Role role, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }

    public Employee(){
    }

    public int getId(){
        return id;
    }

    public String getEmail() {
      return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
