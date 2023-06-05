package com.example.seru.dto;

public record UserUpdateDto(
        String username,
        Boolean is_admin,
        String old_password,
        String new_password
) {
}
