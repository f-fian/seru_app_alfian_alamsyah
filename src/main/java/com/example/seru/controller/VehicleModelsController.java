package com.example.seru.controller;


import com.example.seru.dto.VehicleModelsDto;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.service.VehicleModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle-models")
public class VehicleModelsController {

    @Autowired
    private VehicleModelsService vehicleModelsService;


    @PostMapping("")
    public ResponseEntity<VehicleModels> addVehicleModel(@RequestBody VehicleModelsDto vehicleModelsDto){
        VehicleModels vehicleModels = vehicleModelsService.addVehicleModel(vehicleModelsDto);
        return new ResponseEntity<>(vehicleModels, HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<List<VehicleModels>> getAllVehicleModels(){
        List<VehicleModels> allVehicleModels = vehicleModelsService.getAllVehicleModels();
        return new ResponseEntity<>(allVehicleModels,HttpStatusCode.valueOf(200));
    }

    @GetMapping("{vehicle-models-id}")
    public ResponseEntity<VehicleModels> getVehicleModels(
          @PathVariable("vehicle-models-id") Integer vehicleModelsId)
    {
        VehicleModels vehicleModels = vehicleModelsService.getVehicleModels(vehicleModelsId);
        return new ResponseEntity<>(vehicleModels,HttpStatusCode.valueOf(200));
    }



}

















