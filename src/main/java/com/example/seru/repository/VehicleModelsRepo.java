package com.example.seru.repository;

import com.example.seru.model.vehicleModel.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleModelsRepo extends JpaRepository<VehicleModel,Integer> {
}
