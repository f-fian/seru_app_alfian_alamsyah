package com.example.seru.controller;

import com.example.seru.dto.LoginUserDto;
import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.model.user.User;
import com.example.seru.model.user.UserDetailsServiceImp;
import com.example.seru.repository.UserRepo;
import com.example.seru.security.JwtService;
import com.example.seru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRegisterController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @PostMapping("authenticate")
    public String authenticate(
            @RequestBody LoginUserDto loginUserDto
    )
    {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.username(),
                        loginUserDto.password()
                )
        );
        if(authentication.isAuthenticated()) {
            System.out.println("authenticated mas bro!!");
        }
        var user = userDetailsServiceImp.loadUserByUsername(loginUserDto.username());
        return jwtService.generateToken(user);
    }
    @PostMapping("register")
    public ResponseEntity<UserRegistrationDto> addUser(@RequestBody() User user)
    {
        UserRegistrationDto newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatusCode.valueOf(201));
    }
}
