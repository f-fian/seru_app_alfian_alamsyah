package com.example.seru.controller;


import com.example.seru.dto.FindAllVehicleModelsDto;
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
    public ResponseEntity<FindAllVehicleModelsDto> getAllVehicleModels(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false,value = "type-id") Integer typeId
    ){
        FindAllVehicleModelsDto allVehicleModels = vehicleModelsService.getAllVehicleModels(page,limit,typeId);
        return new ResponseEntity<>(allVehicleModels,HttpStatusCode.valueOf(200));
    }

    @GetMapping("{vehicle-models-id}")
    public ResponseEntity<VehicleModels> getVehicleModel(
          @PathVariable("vehicle-models-id") Integer vehicleModelsId)
    {
        VehicleModels vehicleModels = vehicleModelsService.getVehicleModel(vehicleModelsId);
        return new ResponseEntity<>(vehicleModels,HttpStatusCode.valueOf(200));
    }

    @PutMapping("{vehicle-models-id}")
    public ResponseEntity<VehicleModels> updateVehicleModel(
            @RequestBody VehicleModelsDto vehicleModelsDto,
            @PathVariable("vehicle-models-id") Integer vehicleModelsId)
    {
        VehicleModels vehicleModels = vehicleModelsService.updateVehicleModel(vehicleModelsDto,vehicleModelsId);
        return new ResponseEntity<>(vehicleModels,HttpStatusCode.valueOf(201));
    }
    @DeleteMapping("{vehicle-models-id}")
    public ResponseEntity<Void> deleteVehicleModel(@PathVariable("vehicle-models-id") Integer vehicleModelsId){
        vehicleModelsService.deleteVehicleModel(vehicleModelsId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }



}

















