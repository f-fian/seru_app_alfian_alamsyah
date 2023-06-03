package com.example.seru.repository;

import com.example.seru.model.vehicleYears.VehicleYears;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleYearsRepo extends JpaRepository<VehicleYears,Integer> {
}
