package com.example.seru.service;

import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.model.User;
import com.example.seru.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserRegistrationDto addUser(User user) {
        User newUser = new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getIs_admin()
        );

        userRepo.save(newUser);

        UserRegistrationDto registeredUser = new UserRegistrationDto(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getIs_admin()
        );

        return registeredUser;
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }
    public User getUser(Integer userId) {
        return userRepo.findById(userId).orElseThrow(()->new UsernameNotFoundException("user id tidak ditemukan"));
    }
}
