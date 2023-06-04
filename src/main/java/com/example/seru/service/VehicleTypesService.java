package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleTypesDto;
import com.example.seru.dto.VehicleTypesDto;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.repository.VehicleBrandRepo;
import com.example.seru.repository.VehicleTypesRepo;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(()->new RuntimeException("brand-id not found"));

        VehicleTypes vehicleTypes = new VehicleTypes(vehicleTypesDto.name(),vehicleBrands);
        vehicleTypesRepo.save(vehicleTypes);
        return vehicleTypes;


    }
    public FindAllVehicleTypesDto findAllVehicleTypes(Integer page, Integer limit, Integer brandId) {



        if((page == null || limit==null) && brandId==null){
            return FindAllVehicleTypesDto.builder()
                    .total(vehicleTypesRepo.count())
                    .limit(0)
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
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }

        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(brandId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle brand id not found"));

        if(page == null || limit==null){
            List<VehicleTypes> data = vehicleTypesRepo.findAllByVehicleBrands(vehicleBrands);
            return FindAllVehicleTypesDto.builder()
                    .total(data.stream().count())
                    .limit(0)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable= PageRequest.of(page-1,limit);
        Page<VehicleTypes> data = vehicleTypesRepo.findAllByVehicleBrands(vehicleBrands,pageable);
        return FindAllVehicleTypesDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .skip((page-1)*2)
                .data(data.getContent())
                .build();




//        if(brandId != null){
//            VehicleBrands vehicleBrands = vehicleBrandRepo.findById(brandId)
//                    .orElseThrow(()->new UsernameNotFoundException("vehicle brand id not found"));
//
//            if(page != null && limit !=null){
//                Pageable pageable= PageRequest.of(page-1,limit);
//                return vehicleTypesRepo.findAllByVehicleBrands(vehicleBrands,pageable).getContent();
//            }
//
//        }
//
//        return vehicleTypesRepo.findAll();



    }

    public VehicleTypes findVehicleTypes(Integer vehicleTypesId) {
        return vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle types id not found"));
    }

    public VehicleTypes updateVehicleTypes(VehicleTypesDto vehicleTypesDto, Integer vehicleTypesId) {

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(vehicleTypesId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle types id is not found"));

        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(vehicleTypesDto.brand_id())
                .orElseThrow(()->new UsernameNotFoundException("vehcile brands id is not found"));

        vehicleTypes.setName(vehicleTypesDto.name());
        vehicleTypes.setVehicleBrands(vehicleBrands);

        vehicleTypesRepo.save(vehicleTypes);
        return vehicleTypes;

    }

    public void deleteVehicleTypes(Integer vehicleTypesId) {
        vehicleTypesRepo.deleteById(vehicleTypesId);
    }
}
