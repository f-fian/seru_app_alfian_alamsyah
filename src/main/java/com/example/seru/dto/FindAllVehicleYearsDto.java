package com.example.seru.dto;

import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.model.vehicleYears.VehicleYears;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllVehicleYearsDto(
        Long total,
        Integer limit,
        Integer skip,
        List<VehicleYears> data
) {
}
