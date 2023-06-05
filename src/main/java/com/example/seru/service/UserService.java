package com.example.seru.service;

import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.exeption.DataAlreadyExistexeption;
import com.example.seru.model.user.User;
import com.example.seru.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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

        try{
            userRepo.save(newUser);
            UserRegistrationDto registeredUser = new UserRegistrationDto(
                    newUser.getId(),
                    newUser.getUsername(),
                    newUser.getIs_admin()
            );
            return registeredUser;
        }catch (DataAccessException error){
            System.out.println("error");
            throw new DataAlreadyExistexeption("User is already exist, please insert another username");
        }




    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }
    public User getUser(Integer userId) {
        return userRepo.findById(userId).orElseThrow(()->new UsernameNotFoundException("user id tidak ditemukan"));
    }

    public String deleteUser(Integer userId) {
        userRepo.deleteById(userId);
        return "User Telah berhasil di delete";
    }
}
