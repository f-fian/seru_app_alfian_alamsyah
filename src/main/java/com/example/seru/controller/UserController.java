package com.example.seru.controller;

import com.example.seru.dto.FindAllUserDto;
import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.dto.UserUpdateDto;
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
    public ResponseEntity<FindAllUserDto> getAllUser(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ){
        System.out.println("get all atas");
        FindAllUserDto allUser = userService.getAllUser(page,limit);
        System.out.println("get all");
        return new ResponseEntity<>(allUser,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRegistrationDto> getAllUser(@PathVariable Integer userId){
        UserRegistrationDto userRegistrationDto = userService.getUser(userId);
        return new ResponseEntity<>(userRegistrationDto,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId){

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));

    }


    @PutMapping("/{userId}")
    public UserRegistrationDto updateUser(
            @PathVariable Integer userId,
            @RequestBody UserUpdateDto userUpdateDto)

    {
        return userService.updateUser(userUpdateDto,userId);

    }



}
