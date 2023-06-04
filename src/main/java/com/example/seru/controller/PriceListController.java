package com.example.seru.controller;

import com.example.seru.dto.FindAllPriceListDto;
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
    public ResponseEntity<FindAllPriceListDto> getAllPriceList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false,value = "year-id") Integer yearId,
            @RequestParam(required = false,value = "model-id") Integer modelId
    ){
        FindAllPriceListDto allPricelist = priceListService.getAllPriceList(page,limit,yearId,modelId);
        return new ResponseEntity<>(allPricelist,HttpStatusCode.valueOf(200));
    }

    @GetMapping("{price-list-id}")
    public ResponseEntity<PriceList> getPriceList(
            @PathVariable("price-list-id") Integer priceListId)
    {
        PriceList pricelist = priceListService.getPriceList(priceListId);
        return new ResponseEntity<>(pricelist,HttpStatusCode.valueOf(200));
    }

    @PutMapping("{price-list-id}")
    public ResponseEntity<PriceList> updatePriceList(
            @PathVariable("price-list-id") Integer priceListId,
            @RequestBody PricelistDto pricelistDto)
    {
        PriceList pricelist = priceListService.updatePriceList(pricelistDto,priceListId);
        return new ResponseEntity<>(pricelist,HttpStatusCode.valueOf(201));
    }
    @DeleteMapping("{price-list-id}")
    public ResponseEntity<Void> deletePriceList(
            @PathVariable("price-list-id") Integer priceListId)
    {
        priceListService.deletePriceList(priceListId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

}














