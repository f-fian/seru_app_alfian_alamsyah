package com.example.seru.controller;

import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.model.User;
import com.example.seru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("add")
    public ResponseEntity<UserRegistrationDto> addUser(@RequestBody() User user)
    {
        UserRegistrationDto newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{userId}")
    public User getAllUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }




}
