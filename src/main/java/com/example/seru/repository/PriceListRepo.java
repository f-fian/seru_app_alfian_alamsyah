package com.example.seru.repository;

import com.example.seru.model.priceList.PriceList;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleYears.VehicleYears;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceListRepo extends JpaRepository<PriceList,Integer> {
    
    List<PriceList> findAllByVehicleYears(VehicleYears vehicleYears);
    List<PriceList> findAllByVehicleModels(VehicleModels vehicleModels);
    List<PriceList> findAllByVehicleYearsAndVehicleModels(
            VehicleYears vehicleYears,
            VehicleModels vehicleModels);

    Page<PriceList> findAllByVehicleYears(VehicleYears vehicleYear, Pageable pageable);
    Page<PriceList> findAllByVehicleModels(VehicleModels vehicleModels, Pageable pageable);

    Page<PriceList> findAllByVehicleYearsAndVehicleModels(
            VehicleYears vehicleYears,
            VehicleModels vehicleModels,
            Pageable pageable);


}
