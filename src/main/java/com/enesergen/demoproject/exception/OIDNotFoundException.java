package com.enesergen.demoproject.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OIDNotFoundException extends Exception {
    public OIDNotFoundException(String message) {
        super(message);
    }
}
