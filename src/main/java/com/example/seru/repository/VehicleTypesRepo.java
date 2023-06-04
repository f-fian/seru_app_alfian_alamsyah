package com.example.seru.repository;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface VehicleTypesRepo extends JpaRepository<VehicleTypes,Integer> {

    List<VehicleTypes> findAllByVehicleBrands (VehicleBrands vehicleBrands);

    Page<VehicleTypes> findAllByVehicleBrands (VehicleBrands vehicleBrands, Pageable pageable);
}
