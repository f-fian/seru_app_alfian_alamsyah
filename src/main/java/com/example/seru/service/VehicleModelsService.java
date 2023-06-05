package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleModelsDto;
import com.example.seru.dto.FindAllVehicleTypesDto;
import com.example.seru.dto.VehicleModelsDto;
import com.example.seru.exeption.DataAlreadyExistexeption;
import com.example.seru.exeption.ResourceNotFoundExeption;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleModelsRepo;
import com.example.seru.repository.VehicleTypesRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle types id is not found"));
        try{
            VehicleModels vehicleModels = new VehicleModels(vehicleModelsDto.name(),vehicleTypes);
            vehicleModelsRepo.save(vehicleModels);
            return vehicleModels;
        }catch (DataAccessException error){
            System.out.println("error");
            throw new DataAlreadyExistexeption("Data is already exist please insert another name");
        }

    }

    public FindAllVehicleModelsDto getAllVehicleModels(Integer page, Integer limit, Integer typeId) {


        if((page == null || limit==null) && typeId==null){
            return FindAllVehicleModelsDto.builder()
                    .total(vehicleModelsRepo.count())
                    .limit(vehicleModelsRepo.findAll().size())
                    .page(1)
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
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle types id is not found"));

        if(page == null || limit==null){
            List<VehicleModels> data = vehicleModelsRepo.findAllByVehicleTypes(vehicleTypes);
            return FindAllVehicleModelsDto.builder()
                    .total(data.stream().count())
                    .limit(vehicleModelsRepo.findAll().size())
                    .page(1)
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
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle models id is not found"));
    }

    public VehicleModels updateVehicleModel(VehicleModelsDto vehicleModelsDto,Integer vehicleModelId){

        VehicleModels vehicleModels = vehicleModelsRepo.findById(vehicleModelId)
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle models id is not found"));

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(vehicleModelsDto.type_id())
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle models type id is not found"));

        vehicleModels.setName(vehicleModelsDto.name());
        vehicleModels.setVehicleTypes(vehicleTypes);
        vehicleModelsRepo.save(vehicleModels);
        return vehicleModels;
    }

    public void deleteVehicleModel(Integer vehicleModelId){
        vehicleModelsRepo.findById(vehicleModelId)
                .orElseThrow(()->new ResourceNotFoundExeption("vehicle models id is not found"));
        vehicleModelsRepo.deleteById(vehicleModelId);
    }
}













