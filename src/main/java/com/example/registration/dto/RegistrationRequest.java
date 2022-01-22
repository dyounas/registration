package com.example.registration.dto;


import javax.validation.constraints.NotNull;

public class RegistrationRequest {

    @NotNull(message = "username cannot be null or empty.")
    private String username;
    @NotNull(message = "password cannot be null or empty.")
    private String password;
    @NotNull(message = "ip cannot be null or empty.")
    private String ip;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
