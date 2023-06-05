package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleTypesDto;
import com.example.seru.dto.VehicleTypesDto;
import com.example.seru.exeption.DataAlreadyExistexeption;
import com.example.seru.exeption.ResourceNotFoundExeption;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleBrandRepo;
import com.example.seru.repository.VehicleTypesRepo;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class VehicleTypesService {

    @Autowired
    private VehicleTypesRepo vehicleTypesRepo;

    @Autowired
    private VehicleBrandRepo vehicleBrandRepo;


    public VehicleTypes addVehicleTypes(VehicleTypesDto vehicleTypesDto) {
        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(vehicleTypesDto.brand_id())
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle brands id is not found"));

        try {
            VehicleTypes vehicleTypes = new VehicleTypes(vehicleTypesDto.name(),vehicleBrands);
            vehicleTypesRepo.save(vehicleTypes);
            return vehicleTypes;
        }catch (DataAccessException exception){
            System.out.println("error");
            throw new DataAlreadyExistexeption("Data is already exist please insert another name");
        }



    }
    public FindAllVehicleTypesDto findAllVehicleTypes(Integer page, Integer limit, Integer brandId) {



        if((page == null || limit==null) && brandId==null){
            return FindAllVehicleTypesDto.builder()
                    .total(vehicleTypesRepo.count())
                    .limit(vehicleTypesRepo.findAll().size())
                    .page(1)
                    .skip(0)
                    .data(vehicleTypesRepo.findAll())
                    .build();
        }

        if(brandId == null){
            Pageable pageable= PageRequest.of(page-1,limit);
            Page<VehicleTypes> data = vehicleTypesRepo.findAll(pageable);
            return FindAllVehicleTypesDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .page(page)
                    .skip((page-1)*limit)
                    .data(data.getContent())
                    .build();
        }

        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(brandId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle brands id is not found"));

        if(page == null || limit==null){
            List<VehicleTypes> data = vehicleTypesRepo.findAllByVehicleBrands(vehicleBrands);
            return FindAllVehicleTypesDto.builder()
                    .total(data.stream().count())
                    .limit(vehicleTypesRepo.findAll().size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable= PageRequest.of(page-1,limit);
        Page<VehicleTypes> data = vehicleTypesRepo.findAllByVehicleBrands(vehicleBrands,pageable);
        return FindAllVehicleTypesDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .page(page)
                .skip((page-1)*limit)
                .data(data.getContent())
                .build();

    }

    public VehicleTypes findVehicleTypes(Integer vehicleTypesId) {
        return vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle types id is not found"));
    }

    public VehicleTypes updateVehicleTypes(VehicleTypesDto vehicleTypesDto, Integer vehicleTypesId) {

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle types id is not found"));

        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(vehicleTypesDto.brand_id())
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle brands id is not found"));

        vehicleTypes.setName(vehicleTypesDto.name());
        vehicleTypes.setVehicleBrands(vehicleBrands);

        vehicleTypesRepo.save(vehicleTypes);
        return vehicleTypes;

    }

    public void deleteVehicleTypes(Integer vehicleTypesId) {
        vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle types id is not found"));
        vehicleTypesRepo.deleteById(vehicleTypesId);
    }
}
