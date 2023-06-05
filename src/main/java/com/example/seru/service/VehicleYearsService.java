package com.example.seru.service;

import com.example.seru.dto.FindAllVehicleYearsDto;
import com.example.seru.exeption.DataAlreadyExistexeption;
import com.example.seru.exeption.ResourceNotFoundExeption;
import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.repository.VehicleYearsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehicleYearsService {

    @Autowired
    private VehicleYearsRepo vehicleYearsRepo;
    public VehicleYears addVehicleYears(VehicleYears vehicleYears) {
        VehicleYears newVehicleYears = new VehicleYears(vehicleYears.getYear());

        try{
            System.out.println("sini?");
            System.out.println(newVehicleYears);
            vehicleYearsRepo.save(newVehicleYears);
            return newVehicleYears;
        }catch (DataAccessException error){
            System.out.println("error");
            throw new DataAlreadyExistexeption("Data is already exist please insert another year");
        }


    }

    public FindAllVehicleYearsDto getAllVehicleYears(Integer page, Integer limit) {

        List<VehicleYears> data = vehicleYearsRepo.findAll();
        if(page == null || limit == null){
            return FindAllVehicleYearsDto.builder()
                    .total(data.stream().count())
                    .limit(data.size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable = PageRequest.of(page-1,limit);
        return FindAllVehicleYearsDto.builder()
                .total(data.stream().count())
                .limit(limit)
                .page(page)
                .skip((page-1)*limit)
                .data(vehicleYearsRepo.findAll(pageable).getContent())
                .build();
    }

    public VehicleYears getVehicleYears(Integer vehicleYearsId) {
        return vehicleYearsRepo.findById(vehicleYearsId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years id is not found"));
    }



    public void deleteVehicleYears(Integer vehicleYearsId) {
        vehicleYearsRepo.findById(vehicleYearsId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years id is not found"));
        vehicleYearsRepo.deleteById(vehicleYearsId);
    }

    public VehicleYears updateVehicleYears(VehicleYears newVehicleYears, Integer vehicleYearsId) {
        VehicleYears vehicleYears = vehicleYearsRepo.findById(vehicleYearsId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years id is not found"));
        vehicleYears.setYear(newVehicleYears.getYear());
        vehicleYearsRepo.save(vehicleYears);
        return vehicleYears;
    }
}
