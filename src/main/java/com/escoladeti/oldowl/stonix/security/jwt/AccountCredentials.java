package com.escoladeti.oldowl.stonix.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCredentials {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    String getUsername() { return username; }
    String getPassword() { return password; }

    public void setUsername(String _username) { this.username = _username; }
    public void setPassword(String _password) { this.password = _password; }
}
