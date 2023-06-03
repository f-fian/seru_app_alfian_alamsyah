package com.example.seru.service;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.repository.VehicleBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleBrandsService {

    @Autowired
    private VehicleBrandRepo vehicleBrandRepo;

    public VehicleBrands addVehicleBrands(VehicleBrands vehicleBrands) {
        VehicleBrands newVehicleBrands = new VehicleBrands(vehicleBrands.getName());
        return vehicleBrandRepo.save(newVehicleBrands);
    }

    public List<VehicleBrands> getAllVehicleBrands() {
        return vehicleBrandRepo.findAll();
    }

    public VehicleBrands getVehicleBrands(Integer vehicleBrandsId) {
        return vehicleBrandRepo.findById(vehicleBrandsId)
                .orElseThrow(()->new UsernameNotFoundException("brands id not found"));
    }

    public VehicleBrands updateVehicleBrands(VehicleBrands newVehicleBrands, Integer vehicleBrandsId) {
        VehicleBrands vehicleBrands = vehicleBrandRepo.findById(vehicleBrandsId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle brands id tidak ditemukan"));
        vehicleBrands.setName(newVehicleBrands.getName());
        return vehicleBrandRepo.save(vehicleBrands);
    }

    public void deleteVehicleBrands(Integer vehicleBrandsId) {
        vehicleBrandRepo.deleteById(vehicleBrandsId);
    }
}
