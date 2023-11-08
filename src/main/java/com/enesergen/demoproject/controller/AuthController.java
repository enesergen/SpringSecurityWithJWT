package com.enesergen.demoproject.controller;

import com.enesergen.demoproject.model.dto.UserAuthenticateRequestDto;
import com.enesergen.demoproject.model.dto.UserAuthenticateResponseDto;
import com.enesergen.demoproject.model.dto.UserRegisterRequestDto;
import com.enesergen.demoproject.model.dto.UserRegisterResponseDto;
import com.enesergen.demoproject.service.auth.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("register")
    public UserRegisterResponseDto register(@RequestBody UserRegisterRequestDto input) throws Exception {
        return userService.register(input);
    }

    @PostMapping("authenticate")
    public UserAuthenticateResponseDto authenticate(@RequestBody UserAuthenticateRequestDto input) throws Exception {
        return userService.authenticate(input);
    }
}
