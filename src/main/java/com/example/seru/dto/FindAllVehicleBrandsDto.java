package com.example.seru.dto;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleYears.VehicleYears;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllVehicleBrandsDto(
        Long total,
        Integer limit,
        Integer skip,
        List<VehicleBrands> data
) {
}
