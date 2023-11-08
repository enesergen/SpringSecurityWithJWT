package com.enesergen.demoproject.exception;

public class UserRoleNotFoundException  extends RuntimeException{
    private String msg;

    public UserRoleNotFoundException(String msg) {
        this.msg = msg;
    }
}
