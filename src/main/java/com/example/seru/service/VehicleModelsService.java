package com.example.seru.service;

import com.example.seru.dto.VehicleModelsDto;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleModelsRepo;
import com.example.seru.repository.VehicleTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelsService {

    @Autowired
    private VehicleModelsRepo vehicleModelsRepo;

    @Autowired
    private VehicleTypesRepo vehicleTypesRepo;

    public VehicleModels addVehicleModel(VehicleModelsDto vehicleModelsDto) {
        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(vehicleModelsDto.type_id())
                .orElseThrow(()->new UsernameNotFoundException("vehicle types id is not found"));
        VehicleModels vehicleModels = new VehicleModels(vehicleModelsDto.name(),vehicleTypes);
        vehicleModelsRepo.save(vehicleModels);
        return vehicleModels;
    }

    public List<VehicleModels> getAllVehicleModels() {
        return vehicleModelsRepo.findAll();
    }

    public VehicleModels getVehicleModel(Integer vehicleModelsId) {
        return vehicleModelsRepo.findById(vehicleModelsId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle models id is not found"));
    }

    public VehicleModels updateVehicleModel(VehicleModelsDto vehicleModelsDto,Integer vehiclModelId){

        VehicleModels vehicleModels = vehicleModelsRepo.findById(vehiclModelId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle model id is not found"));

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(vehicleModelsDto.type_id())
                .orElseThrow(()->new UsernameNotFoundException("model type id is not found"));

        vehicleModels.setName(vehicleModelsDto.name());
        vehicleModels.setVehicleTypes(vehicleTypes);
        vehicleModelsRepo.save(vehicleModels);
        return vehicleModels;
    }

    public void deleteVehicleModel(Integer vehicleModelId){
        vehicleModelsRepo.deleteById(vehicleModelId);
    }
}













