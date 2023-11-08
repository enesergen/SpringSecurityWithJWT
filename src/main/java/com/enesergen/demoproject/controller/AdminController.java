package com.enesergen.demoproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1")
public class AdminController {

    @GetMapping("/deneme")
    public ResponseEntity<String>deneme(){
        return  ResponseEntity.ok("Deneme admin controller");
    }
}
