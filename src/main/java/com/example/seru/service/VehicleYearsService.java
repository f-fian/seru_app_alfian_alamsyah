package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleYearsDto;
import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.repository.VehicleYearsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleYearsService {

    @Autowired
    private VehicleYearsRepo vehicleYearsRepo;
    public VehicleYears addVehicleYears(VehicleYears vehicleYears) {
        VehicleYears newVehicleYears = new VehicleYears(vehicleYears.getYear());
        vehicleYearsRepo.save(newVehicleYears);
        return newVehicleYears;
    }

    public FindAllVehicleYearsDto getAllVehicleYears(Integer page, Integer limit) {

        if(page == null && limit == null){
            List<VehicleYears> data = vehicleYearsRepo.findAll();
            return FindAllVehicleYearsDto.builder()
                    .total(data.stream().count())
                    .limit(0)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable = PageRequest.of(page-1,limit);
        Page<VehicleYears> data = vehicleYearsRepo.findAll(pageable);
        return FindAllVehicleYearsDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .skip((page-1)*2)
                .data(data.getContent())
                .build();
    }

    public VehicleYears getVehicleYears(Integer vehicleYearsId) {
        return vehicleYearsRepo.findById(vehicleYearsId)
                .orElseThrow(()-> new UsernameNotFoundException("vehicle Id is not found"));
    }



    public void deleteVehicleYears(Integer vehicleYearsId) {
        vehicleYearsRepo.deleteById(vehicleYearsId);
    }

    public VehicleYears updateVehicleYears(VehicleYears newVehicleYears, Integer vehicleYearsId) {
        VehicleYears vehicleYears = vehicleYearsRepo.findById(vehicleYearsId)
                .orElseThrow(()-> new UsernameNotFoundException("Vehicle years id not found"));
        vehicleYears.setYear(newVehicleYears.getYear());
        vehicleYearsRepo.save(vehicleYears);
        return vehicleYears;
    }
}
