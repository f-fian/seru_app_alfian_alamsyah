package com.example.seru.dto;

import com.example.seru.model.priceList.PriceList;
import com.example.seru.model.vehicleModel.VehicleModels;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllPriceListDto(
        Long total,
        Integer limit,
        Integer skip,
        List<PriceList> data
) {
}
