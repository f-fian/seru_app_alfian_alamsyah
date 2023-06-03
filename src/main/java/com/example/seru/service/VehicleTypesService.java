package com.example.seru.service;

import com.example.seru.dto.VehicleTypesDto;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleBrandRepo;
import com.example.seru.repository.VehicleTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypesService {

    @Autowired
    private VehicleTypesRepo vehicleTypesRepo;

    @Autowired
    private VehicleBrandRepo vehicleBrandRepo;


    public VehicleTypes addVehicleTypes(VehicleTypesDto vehicleTypesDto) {
        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(vehicleTypesDto.brand_id())
                .orElseThrow(()->new RuntimeException("brand-id not found"));

        VehicleTypes vehicleTypes = new VehicleTypes(vehicleTypesDto.name(),vehicleBrands);
        vehicleTypesRepo.save(vehicleTypes);
        return vehicleTypes;


    }
    public List<VehicleTypes> findAllVehicleTypes() {
        return vehicleTypesRepo.findAll();
    }

    public VehicleTypes findVehicleTypes(Integer vehicleTypesId) {
        return vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle types id not found"));
    }
}
