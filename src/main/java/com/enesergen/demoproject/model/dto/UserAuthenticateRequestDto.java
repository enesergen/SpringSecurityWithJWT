package com.enesergen.demoproject.model.dto;

import jakarta.validation.constraints.NotNull;

public class UserAuthenticateRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;

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

    public UserAuthenticateRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
