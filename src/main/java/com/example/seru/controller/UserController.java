package com.example.seru.controller;

import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.model.user.User;
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



    @GetMapping("")
    public ResponseEntity<List<UserRegistrationDto>> getAllUser(){
        System.out.println("get all atas");
        List<UserRegistrationDto> allUser = userService.getAllUser();
        System.out.println("get all");
        return new ResponseEntity<>(allUser,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{userId}")
    public UserRegistrationDto getAllUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }




}
