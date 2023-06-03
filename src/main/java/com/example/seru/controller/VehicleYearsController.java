package com.example.seru.controller;

import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.service.VehicleYearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle-years")
public class VehicleYearsController {

    @Autowired
    private VehicleYearsService vehicleYearsService;


    @PostMapping("")
    public ResponseEntity<VehicleYears> addVehicleYears(@RequestBody VehicleYears vehicleYears){
        VehicleYears newVehicleYears = vehicleYearsService.addVehicleYears(vehicleYears);
        return new ResponseEntity<>(newVehicleYears, HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<List<VehicleYears>> getAllVehicleYears(){
        List<VehicleYears> allVehicleYears = vehicleYearsService.getAllVehicleYears();

        return new ResponseEntity<>(allVehicleYears,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{vehicle-years-id}")
    public ResponseEntity<VehicleYears> getVehicleYears(
            @PathVariable("vehicle-years-id") Integer vehicleYearsId)
    {
        VehicleYears vehicleYears = vehicleYearsService.getVehicleYears(vehicleYearsId);
        return new ResponseEntity<>(vehicleYears,HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{vehicle-years-id}")
    public ResponseEntity<VehicleYears> updateVehicleYears(
            @RequestBody VehicleYears vehicleYears,
            @PathVariable("vehicle-years-id") Integer vehicleYearsId){
        VehicleYears newVehicleYears = vehicleYearsService.updateVehicleYears(vehicleYears,vehicleYearsId);
        return new ResponseEntity<>(newVehicleYears, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/{vehicle-years-id}")
    public ResponseEntity<Void> deleteVehicleYears(
            @PathVariable("vehicle-years-id") Integer vehicleYearsId)
    {
        vehicleYearsService.deleteVehicleYears(vehicleYearsId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
