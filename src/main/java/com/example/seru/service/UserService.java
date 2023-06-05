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
import java.util.stream.Collectors;

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
                    newUser.getIs_admin(),
                    newUser.getCreatedAt(),
                    newUser.getUpdatedAt()
            );
            return registeredUser;
        }catch (DataAccessException error){
            System.out.println("error");
            throw new DataAlreadyExistexeption("User is already exist, please insert another username");
        }




    }

    public List<UserRegistrationDto> getAllUser() {
        System.out.println("get all");
        return userRepo.findAll().stream()
                .map((data)-> new UserRegistrationDto(
                        data.getId(),
                        data.getUsername(),
                        data.getIs_admin(),
                        data.getCreatedAt(),
                        data.getUpdatedAt()))
                .collect(Collectors.toList());
    }
    public UserRegistrationDto getUser(Integer userId) {
        System.out.println("get one");
        User user = userRepo.findById(userId).orElseThrow(()->new UsernameNotFoundException("user id tidak ditemukan"));
        return new UserRegistrationDto(
                user.getId(),
                user.getUsername(),
                user.getIs_admin(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public String deleteUser(Integer userId) {
        userRepo.deleteById(userId);
        return "User Telah berhasil di delete";
    }
}
