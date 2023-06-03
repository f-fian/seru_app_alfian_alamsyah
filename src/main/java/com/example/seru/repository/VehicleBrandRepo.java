package com.example.seru.repository;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleBrandRepo extends JpaRepository<VehicleBrands,Integer> {
}
