package com.example.seru.repository;

import com.example.seru.model.vehicleTypes.VehicleTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypesRepo extends JpaRepository<VehicleTypes,Integer> {
}
