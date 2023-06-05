package com.example.seru.service;

import com.example.seru.dto.FindAllUserDto;
import com.example.seru.dto.FindAllVehicleYearsDto;
import com.example.seru.dto.UserRegistrationDto;
import com.example.seru.dto.UserUpdateDto;
import com.example.seru.exeption.DataAlreadyExistexeption;
import com.example.seru.exeption.ResourceNotFoundExeption;
import com.example.seru.model.user.User;
import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public FindAllUserDto getAllUser(Integer page,Integer limit) {

        List<User> data = userRepo.findAll();
        if(page == null || limit == null){
            return FindAllUserDto.builder()
                    .total(data.stream().count())
                    .limit(data.size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable = PageRequest.of(page-1,limit);
        return FindAllUserDto.builder()
                .total(data.stream().count())
                .limit(limit)
                .page(page)
                .skip((page-1)*2)
                .data(userRepo.findAll(pageable).getContent())
                .build();
    }
    public UserRegistrationDto getUser(Integer userId) {
        System.out.println("get one");
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundExeption("user id tidak ditemukan"));
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

    public UserRegistrationDto updateUser(UserUpdateDto userUpdateDto, Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundExeption("user id is not found"));

        if(userUpdateDto.username() != null){
            user.setUsername(userUpdateDto.username());
        }
        if(userUpdateDto.is_admin() != null){
            user.setIs_admin(userUpdateDto.is_admin());
        }
        if(userUpdateDto.new_password() != null && userUpdateDto.old_password() != null){
            if(!(passwordEncoder.matches(userUpdateDto.old_password(),user.getPassword()))){
                System.out.println(passwordEncoder);
                System.out.println(user.getPassword());
                throw new ResourceNotFoundExeption("old password is not same");
            }
            user.setPassword(passwordEncoder.encode(userUpdateDto.new_password()));

        }
        userRepo.save(user);

        return new UserRegistrationDto(
                user.getId(),
                user.getUsername(),
                user.getIs_admin(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
