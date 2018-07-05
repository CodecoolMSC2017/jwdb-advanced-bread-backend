package com.codecool.bread.model;

public final class Employee extends POSObject{

    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String password;

    public Employee(int id, String email, String firstName, String lastName, Role role, String password) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
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
}
