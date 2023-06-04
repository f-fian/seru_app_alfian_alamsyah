package com.example.seru.dto;

import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllVehicleModelsDto(
        Long total,
        Integer limit,
        Integer page,
        Integer skip,
        List<VehicleModels> data
) {
}
