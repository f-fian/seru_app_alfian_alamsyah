package com.example.seru.dto;

import java.time.LocalDateTime;

public record UserRegistrationDto(
        Integer id,
        String username,
        Boolean is_admin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
