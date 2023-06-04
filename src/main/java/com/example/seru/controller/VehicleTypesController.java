package com.example.seru.controller;

import com.example.seru.dto.FindAllVehicleTypesDto;
import com.example.seru.dto.VehicleTypesDto;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.service.VehicleTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle-types")
public class VehicleTypesController {
    @Autowired
    private VehicleTypesService vehicleTypesService;

    @PostMapping("")
    public ResponseEntity<VehicleTypes> addVehicleTypes(@RequestBody VehicleTypesDto vehicleTypesDto){
        VehicleTypes vehicleTypes = vehicleTypesService.addVehicleTypes(vehicleTypesDto);
        return new ResponseEntity<>(vehicleTypes, HttpStatusCode.valueOf(201));

    }
    @GetMapping("")
    public ResponseEntity<FindAllVehicleTypesDto> getAllVehicleTypes(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false,value = "brand-id") Integer brandId){

        FindAllVehicleTypesDto data = vehicleTypesService.findAllVehicleTypes(page,limit,brandId);


        return new ResponseEntity<>(data,HttpStatusCode.valueOf(200));



    }


    @GetMapping("{vehicle-types-id}")
    public ResponseEntity<VehicleTypes> getVehicleTypes(
            @PathVariable("vehicle-types-id") Integer vehicleTypesId)
    {
        VehicleTypes VehicleTypes = vehicleTypesService.findVehicleTypes(vehicleTypesId);
        return new ResponseEntity<>(VehicleTypes,HttpStatusCode.valueOf(200));
    }

    @PutMapping("{vehicle-types-id}")
    public ResponseEntity<VehicleTypes> updateVehicleTypes(
            @RequestBody VehicleTypesDto vehicleTypesDto,
            @PathVariable("vehicle-types-id") Integer vehicleTypesId)

    {
        VehicleTypes vehicleTypes = vehicleTypesService.updateVehicleTypes(vehicleTypesDto,vehicleTypesId);
        return new ResponseEntity<>(vehicleTypes,HttpStatusCode.valueOf(201));
    }


    @DeleteMapping("{vehicle-types-id}")
    public ResponseEntity<Void> deleteVehicleTypes(
            @PathVariable("vehicle-types-id") Integer vehicleTypesId)
    {
        vehicleTypesService.deleteVehicleTypes(vehicleTypesId);

        return new ResponseEntity<>(HttpStatusCode.valueOf(204));

    }



}
