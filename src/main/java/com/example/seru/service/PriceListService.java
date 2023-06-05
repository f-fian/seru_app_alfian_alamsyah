package com.example.seru.service;

import com.example.seru.dto.FindAllPriceListDto;
import com.example.seru.dto.FindAllVehicleModelsDto;
import com.example.seru.dto.PricelistDto;
import com.example.seru.exeption.ResourceNotFoundExeption;
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
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years id is not found"));
        VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years id is not found"));

        PriceList priceList = new PriceList(pricelistDto.price(),vehicleYears,vehicleModels);

        priceListRepo.save(priceList);
        return priceList;


    }
    public FindAllPriceListDto getAllPriceList(Integer page, Integer limit, Integer yearId, Integer modelId) {

        if((page == null || limit==null) && yearId==null && modelId==null){
            return FindAllPriceListDto.builder()
                    .total(priceListRepo.count())
                    .limit(priceListRepo.findAll().size())
                    .skip(0)
                    .page(1)
                    .data(priceListRepo.findAll())
                    .build();
        }

        if((page == null || limit==null) && yearId !=null && modelId==null){
            VehicleYears vehicleYears = vehicleYearsRepo.findById(yearId)
                    .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years is not found"));
            List<PriceList> data = priceListRepo.findAllByVehicleYears(vehicleYears);
            return FindAllPriceListDto.builder()
                    .total(data.stream().count())
                    .limit(data.size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        if((page == null || limit==null) && yearId ==null && modelId != null){
            VehicleModels vehicleModels = vehicleModelsRepo.findById(modelId)
                    .orElseThrow(()-> new ResourceNotFoundExeption("model id is not found"));
            List<PriceList> data = priceListRepo.findAllByVehicleModels(vehicleModels);
            return FindAllPriceListDto.builder()
                    .total(data.stream().count())
                    .limit(data.size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        if((page != null && limit!=null) && yearId == null && modelId==null){
            Pageable pageable= PageRequest.of(page-1,limit);
            Page<PriceList> data = priceListRepo.findAll(pageable);
            return FindAllPriceListDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .page(page)
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }

        if((page != null && limit!=null) && yearId != null && modelId==null){
            Pageable pageable= PageRequest.of(page-1,limit);
            VehicleYears vehicleYears = vehicleYearsRepo.findById(yearId)
                    .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years is not found"));
            Page<PriceList> data = priceListRepo.findAllByVehicleYears(vehicleYears,pageable);
            return FindAllPriceListDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .page(page)
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }

        if((page != null && limit!=null) && yearId == null && modelId != null){
            Pageable pageable= PageRequest.of(page-1,limit);
            VehicleModels vehicleModels = vehicleModelsRepo.findById(modelId)
                    .orElseThrow(()-> new ResourceNotFoundExeption("vehicle models id is not found"));
            Page<PriceList> data = priceListRepo.findAllByVehicleModels(vehicleModels,pageable);
            return FindAllPriceListDto.builder()
                    .total(data.getTotalElements())
                    .limit(limit)
                    .page(page)
                    .skip((page-1)*2)
                    .data(data.getContent())
                    .build();
        }


        VehicleYears vehicleYears = vehicleYearsRepo.findById(yearId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle years is not found"));
        VehicleModels vehicleModels = vehicleModelsRepo.findById(modelId)
                .orElseThrow(()-> new ResourceNotFoundExeption("vehicle models id is not found"));

        if((page == null && limit==null) && yearId != null && modelId != null){
            List<PriceList> data = priceListRepo.findAllByVehicleYearsAndVehicleModels(vehicleYears,vehicleModels);
            return FindAllPriceListDto.builder()
                    .total(data.stream().count())
                    .limit(data.size())
                    .page(1)
                    .skip(0)
                    .data(data)
                    .build();
        }

        Pageable pageable= PageRequest.of(page-1,limit);
        Page<PriceList> data = priceListRepo.findAllByVehicleYearsAndVehicleModels(vehicleYears,vehicleModels,pageable);
        // if all true
        return FindAllPriceListDto.builder()
                .total(data.getTotalElements())
                .limit(limit)
                .page(page)
                .skip((page-1)*2)
                .data(data.getContent())
                .build();

    }



    public PriceList getPriceList(Integer priceListId) {
        return priceListRepo.findById(priceListId)
                .orElseThrow(()->new ResourceNotFoundExeption("price list id is not found"));
    }

    public PriceList updatePriceList(PricelistDto pricelistDto, Integer priceListId) {

        PriceList priceList = priceListRepo.findById(priceListId)
                .orElseThrow(()->new ResourceNotFoundExeption("price list id is not found"));

        if(pricelistDto.model_id() != null){
            VehicleModels vehicleModels = vehicleModelsRepo.findById(pricelistDto.model_id())
                    .orElseThrow(()->new ResourceNotFoundExeption("vehicle models id is not found"));
            priceList.setVehicleModels(vehicleModels);
        }
        if(pricelistDto.year_id() != null){
            VehicleYears vehicleYears = vehicleYearsRepo.findById(pricelistDto.year_id())
                    .orElseThrow(()->new ResourceNotFoundExeption("vehicle years id is not found"));
            priceList.setVehicleYears(vehicleYears);
        }
        if(pricelistDto.price() != null){
            priceList.setPrice(pricelistDto.price());
        }

        priceListRepo.save(priceList);
        return priceList;
    }

    public void deletePriceList(Integer priceListId) {
        priceListRepo.findById(priceListId)
                .orElseThrow(()->new ResourceNotFoundExeption("price list id is not found"));
        priceListRepo.deleteById(priceListId);
    }
}
