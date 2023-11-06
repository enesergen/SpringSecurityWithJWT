package com.enesergen.demoproject.model.dto;

public class UserAuthenticateResponseDto {
    private boolean isSuccess;
    private String token;
    private String message;

    public UserAuthenticateResponseDto(boolean isSuccess, String token, String message) {
        this.isSuccess = isSuccess;
        this.token = token;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
