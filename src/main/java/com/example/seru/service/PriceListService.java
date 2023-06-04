package com.example.seru.service;

import com.example.seru.dto.PricelistDto;
import com.example.seru.model.priceList.PriceList;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.repository.PriceListRepo;
import com.example.seru.repository.VehicleModelsRepo;
import com.example.seru.repository.VehicleYearsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceListService {
    @Autowired
    private PriceListRepo priceListRepo;

    @Autowired
    private VehicleYearsRepo vehicleYearsRepo;

    @Autowired
    private VehicleModelsRepo vehicleModelsRepo;

    public PriceList addPriceList(PricelistDto pricelistDto) {

        VehicleYears vehicleYears = vehicleYearsRepo.findById(pricelistDto.year_id())
                .orElseThrow(()-> new UsernameNotFoundException("vehicle year id is not found"));
        VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                .orElseThrow(()-> new UsernameNotFoundException("vehicle year id is not found"));

        PriceList priceList = new PriceList(pricelistDto.price(),vehicleYears,vehicleModels);

        priceListRepo.save(priceList);
        return priceList;

    }
    public List<PriceList> getAllPriceList() {
        return priceListRepo.findAll();
    }
    public PriceList getPriceList(Integer priceListId) {
        return priceListRepo.findById(priceListId)
                .orElseThrow(()->new UsernameNotFoundException("price list id is not found"));
    }
}
