package com.covidata.application.model;

public class User {
    private String name;
    private String email;
    private String password;
    private String note;

    public User(String name, String email, String password, String note) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.note = note;
    }

    public User(){
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

    public String getNote() {
        return note;
    }
}
