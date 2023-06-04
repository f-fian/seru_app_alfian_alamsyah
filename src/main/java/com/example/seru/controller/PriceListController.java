package com.example.seru.controller;

import com.example.seru.dto.PricelistDto;
import com.example.seru.model.priceList.PriceList;
import com.example.seru.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("price-list")
public class PriceListController {

    @Autowired
    PriceListService priceListService;


    @PostMapping("")
    public ResponseEntity<PriceList> addPriceList(
            @RequestBody PricelistDto pricelistDto)
    {
        PriceList priceList = priceListService.addPriceList(pricelistDto);
        return new ResponseEntity<>(priceList, HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<List<PriceList>> getAllPriceList(){
        List<PriceList> allPricelist = priceListService.getAllPriceList();
        return new ResponseEntity<>(allPricelist,HttpStatusCode.valueOf(200));
    }

    @GetMapping("{price-list-id}")
    public ResponseEntity<PriceList> getPriceList(
            @PathVariable("price-list-id") Integer priceListId)
    {
        PriceList pricelist = priceListService.getPriceList(priceListId);
        return new ResponseEntity<>(pricelist,HttpStatusCode.valueOf(200));
    }


}














