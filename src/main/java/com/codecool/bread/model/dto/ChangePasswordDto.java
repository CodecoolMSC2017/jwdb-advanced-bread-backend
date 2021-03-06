package com.codecool.bread.model.dto;

public class ChangePasswordDto {
    private String username;
    private String password;
    private String confirmationPassword;
    private String[] authorities;

    public ChangePasswordDto() {}

    public ChangePasswordDto(String username,String password,String confirmationPassword,String[] authorities) {
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
}
