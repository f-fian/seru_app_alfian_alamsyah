package com.example.seru.controller;

import com.example.seru.dto.LoginUserDto;
import com.example.seru.model.user.UserDetailsServiceImp;
import com.example.seru.repository.UserRepo;
import com.example.seru.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping
    public String authenticate(
            @RequestBody LoginUserDto loginUserDto
    )
    {
        System.out.println("authenticate");

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

        System.out.println("dari controller");
        System.out.println(loginUserDto.username());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        return jwtService.generateToken(user);
    }
}
