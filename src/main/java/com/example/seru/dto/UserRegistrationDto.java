package com.example.seru.dto;

public record UserRegistrationDto(
        Integer id,
        String username,
        Boolean is_admin
) {
}
