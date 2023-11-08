package com.enesergen.demoproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    @GetMapping("/deneme")
    public ResponseEntity<String>deneme(){
        return ResponseEntity.ok("Deneme user controller");
    }
}
