package com.example.seru.dto;

import com.example.seru.model.user.User;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllUserDto(
        Long total,
        Integer limit,
        Integer page,
        Integer skip,
        List<User> data
) {
}
