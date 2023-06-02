package com.example.seru.controller;

import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.model.User;
import com.example.seru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("add")
    public UserRegistrationDto addUser(@RequestBody() User user)
    {
        return userService.addUser(user);
    }

    @GetMapping("")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getAllUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

}
