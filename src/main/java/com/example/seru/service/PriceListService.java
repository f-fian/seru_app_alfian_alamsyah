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

    public PriceList updatePriceList(PricelistDto pricelistDto, Integer priceListId) {

        PriceList priceList = priceListRepo.findById(priceListId)
                .orElseThrow(()->new UsernameNotFoundException("price list id is not found"));

        if(pricelistDto.model_id() != null){
            VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                    .orElseThrow(()->new UsernameNotFoundException("vehicle model id is not found"));
            priceList.setVehicleModels(vehicleModels);
        }
        if(pricelistDto.year_id() != null){
            VehicleYears vehicleYears = vehicleYearsRepo.findById(pricelistDto.year_id())
                    .orElseThrow(()->new UsernameNotFoundException("vehicle years id is not found"));
            priceList.setVehicleYears(vehicleYears);
        }
        if(pricelistDto.price() != null){
            priceList.setPrice(pricelistDto.price());
        }

        priceListRepo.save(priceList);
        return priceList;
    }

    public void deletePriceList(Integer priceListId) {
        priceListRepo.deleteById(priceListId);
    }
}
