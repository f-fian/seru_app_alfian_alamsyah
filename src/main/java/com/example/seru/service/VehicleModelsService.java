package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleModelsDto;
import com.example.seru.dto.FindAllVehicleTypesDto;
import com.example.seru.dto.VehicleModelsDto;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleModelsRepo;
import com.example.seru.repository.VehicleTypesRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
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

    public FindAllVehicleModelsDto getAllVehicleModels(Integer page, Integer limit, Integer typeId) {


        if((page == null || limit==null) && typeId==null){
            return FindAllVehicleModelsDto.builder()
                    .total(vehicleModelsRepo.count())
                    .limit(0)
                    .page(0)
                    .skip(0)
                    .data(vehicleModelsRepo.findAll())
                    .build();
        }

        if(typeId == null){
            Pageable pageable= PageRequest.of(page-1,limit);
            Page<VehicleModels> data = vehicleModelsRepo.findAll(pageable);
            return FindAllVehicleModelsDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .page(page)
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(typeId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle brand id not found"));

        if(page == null || limit==null){
            List<VehicleModels> data = vehicleModelsRepo.findAllByVehicleTypes(vehicleTypes);
            return FindAllVehicleModelsDto.builder()
                    .total(data.stream().count())
                    .limit(0)
                    .page(0)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable= PageRequest.of(page-1,limit);
        Page<VehicleModels> data = vehicleModelsRepo.findAllByVehicleTypes(vehicleTypes,pageable);
        return FindAllVehicleModelsDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .page(page)
                .skip((page-1)*2)
                .data(data.getContent())
                .build();
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













