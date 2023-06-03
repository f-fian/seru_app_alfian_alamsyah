package com.example.seru.repository;

import com.example.seru.model.priceList.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepo extends JpaRepository<PriceList,Integer> {
}
