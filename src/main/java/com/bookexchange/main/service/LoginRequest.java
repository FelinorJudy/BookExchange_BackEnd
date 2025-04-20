package com.bookexchange.main.service;

public class LoginRequest {
    private String email;
    private String password;

    // Costruttore vuoto necessario per @RequestBody
    public LoginRequest() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

