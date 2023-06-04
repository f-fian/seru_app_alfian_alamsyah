package com.example.seru.service;

import com.example.seru.dto.FindAllPriceListDto;
import com.example.seru.dto.FindAllVehicleModelsDto;
import com.example.seru.dto.PricelistDto;
import com.example.seru.model.priceList.PriceList;
import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleTypes.VehicleTypes;
import com.example.seru.model.vehicleYears.VehicleYears;
import com.example.seru.repository.PriceListRepo;
import com.example.seru.repository.VehicleModelsRepo;
import com.example.seru.repository.VehicleYearsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceListService {
    @Autowired
    private PriceListRepo priceListRepo;

    @Autowired
    private VehicleYearsRepo vehicleYearsRepo;

    @Autowired
    private VehicleModelsRepo vehicleModelsRepo;

    public PriceList addPriceList(PricelistDto pricelistDto) {

        VehicleYears vehicleYears = vehicleYearsRepo.findById(pricelistDto.year_id())
                .orElseThrow(()-> new UsernameNotFoundException("vehicle year id is not found"));
        VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                .orElseThrow(()-> new UsernameNotFoundException("vehicle year id is not found"));

        PriceList priceList = new PriceList(pricelistDto.price(),vehicleYears,vehicleModels);

        priceListRepo.save(priceList);
        return priceList;


    }
    public FindAllPriceListDto getAllPriceList(Integer page, Integer limit, Integer yearId, Integer modelId) {

        if((page == null || limit==null) && typeId==null){
            return FindAllVehicleModelsDto.builder()
                    .total(vehicleModelsRepo.count())
                    .limit(0)
                    .skip(0)
                    .data(vehicleModelsRepo.findAll())
                    .build();
        }

        if(typeId == null){
            Pageable pageable= PageRequest.of(page-1,limit);
            Page<VehicleModels> data = vehicleModelsRepo.findAll(pageable);
            return FindAllVehicleModelsDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }

        VehicleTypes vehicleTypes = vehicleTypesRepo.findById(typeId)
                .orElseThrow(()->new UsernameNotFoundException("vehicle brand id not found"));

        if(page == null || limit==null){
            List<VehicleModels> data = vehicleModelsRepo.findAllByVehicleTypes(vehicleTypes);
            return FindAllVehicleModelsDto.builder()
                    .total(data.stream().count())
                    .limit(0)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable= PageRequest.of(page-1,limit);
        Page<VehicleModels> data = vehicleModelsRepo.findAllByVehicleTypes(vehicleTypes,pageable);
        return FindAllVehicleModelsDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .skip((page-1)*2)
                .data(data.getContent())
                .build();
    }
    }




    public PriceList getPriceList(Integer priceListId) {
        return priceListRepo.findById(priceListId)
                .orElseThrow(()->new UsernameNotFoundException("price list id is not found"));
    }

    public PriceList updatePriceList(PricelistDto pricelistDto, Integer priceListId) {

        PriceList priceList = priceListRepo.findById(priceListId)
                .orElseThrow(()->new UsernameNotFoundException("price list id is not found"));

        if(pricelistDto.model_id() != null){
            VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                    .orElseThrow(()->new UsernameNotFoundException("vehicle model id is not found"));
            priceList.setVehicleModels(vehicleModels);
        }
        if(pricelistDto.year_id() != null){
            VehicleYears vehicleYears = vehicleYearsRepo.findById(pricelistDto.year_id())
                    .orElseThrow(()->new UsernameNotFoundException("vehicle years id is not found"));
            priceList.setVehicleYears(vehicleYears);
        }
        if(pricelistDto.price() != null){
            priceList.setPrice(pricelistDto.price());
        }

        priceListRepo.save(priceList);
        return priceList;
    }

    public void deletePriceList(Integer priceListId) {
        priceListRepo.deleteById(priceListId);
    }
}
