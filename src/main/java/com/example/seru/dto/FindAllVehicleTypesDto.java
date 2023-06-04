package com.example.seru.dto;

import com.example.seru.model.vehicleTypes.VehicleTypes;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllVehicleTypesDto(
        Long total,
        Integer limit,
        Integer page,
        Integer skip,
        List<VehicleTypes> data
) {
}
