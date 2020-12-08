package com.covidata.application.model;

public class User {
    private String name;
    private String email;
    private String password;
    private String residenceNumber;

    public User(String name, String email, String password, String residenceNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.residenceNumber = residenceNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getResidenceNumber() {
        return residenceNumber;
    }
}
