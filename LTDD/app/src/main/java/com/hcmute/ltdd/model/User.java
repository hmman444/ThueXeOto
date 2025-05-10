package com.hcmute.ltdd.model;

public class User {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String address;

    // Constructor
    public User(String username, String email, String password, String confirmPassword, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;  // Getter cho address
    }

    public void setAddress(String address) {
        this.address = address;  // Setter cho address
    }
}
