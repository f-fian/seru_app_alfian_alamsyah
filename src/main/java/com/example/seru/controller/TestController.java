package com.example.seru.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test-admin")
    public String testAdmin(){
        return "This is Admin auth";
    }

    @GetMapping("test-user")
    public String testUser(){
        return "This is User auth";
    }
}
