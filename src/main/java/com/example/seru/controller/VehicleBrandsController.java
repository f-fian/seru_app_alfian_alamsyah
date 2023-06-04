package com.example.seru.controller;

import com.example.seru.dto.FindAllVehicleBrandsDto;
import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.service.VehicleBrandsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("vehicle-brands")
public class VehicleBrandsController {


    @Autowired
    private VehicleBrandsService vehicleBrandsService;


    @PostMapping("")
    public ResponseEntity<VehicleBrands> addVehicleBrands(@Valid @RequestBody VehicleBrands vehicleBrands){
        VehicleBrands newVehicleBrands = vehicleBrandsService.addVehicleBrands(vehicleBrands);
        return new ResponseEntity<>(newVehicleBrands, HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<FindAllVehicleBrandsDto> getAllVehicleBrands(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ){
        FindAllVehicleBrandsDto allVehicleBrands = vehicleBrandsService.getAllVehicleBrands(page,limit);
        return new ResponseEntity<>(allVehicleBrands, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{vehicle-brand-id}")
    public ResponseEntity<VehicleBrands> getAllVehicleBrands(
            @PathVariable("vehicle-brand-id") Integer vehicleBrandsId)
    {
        VehicleBrands vehicleBrands = vehicleBrandsService.getVehicleBrands(vehicleBrandsId);
        return new ResponseEntity<>(vehicleBrands, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{vehicle-brand-id}")
    public ResponseEntity<VehicleBrands> updateVehicleBrands(
            @RequestBody() VehicleBrands vehicleBrands,
            @PathVariable("vehicle-brand-id") Integer vehicleBrandsId)
    {
        VehicleBrands newVehicleBrands = vehicleBrandsService.updateVehicleBrands(vehicleBrands,vehicleBrandsId);
        return new ResponseEntity<>(newVehicleBrands,HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/{vehicle-brand-id}")
    public ResponseEntity<Void> deleteVehicleBrands(
            @PathVariable("vehicle-brand-id") Integer vehicleBrandsId)
    {
        vehicleBrandsService.deleteVehicleBrands(vehicleBrandsId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
