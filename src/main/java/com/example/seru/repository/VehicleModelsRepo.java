package com.example.seru.repository;


import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleModelsRepo extends JpaRepository<VehicleModels,Integer> {
    List<VehicleModels> findAllByVehicleTypes(VehicleTypes vehicleTypes);

    Page<VehicleModels> findAllByVehicleTypes(VehicleTypes vehicleTypes, Pageable pageable);
}
