package com.serviceprovider.serviceprovider.Databases;

public class RegisterUser {
    String username;
    String email;
    String password;
    String name;

    public RegisterUser() {
//        this.username = username;
//        this.email = email;
//        this.password = password;
    }

    public RegisterUser(String username, String email, String password, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //getter

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    //setter

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}

