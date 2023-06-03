package com.example.seru.model.priceList;

import com.example.seru.model.vehicleModel.VehicleModel;
import com.example.seru.model.vehicleYears.VehicleYears;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;

    @ManyToOne
    @JoinColumn(
            name = "model_id",
            referencedColumnName = "id"
    )
    private VehicleModel vehicleModel;

    @ManyToOne
    @JoinColumn(
            name = "year_id",
            referencedColumnName = "id"
    )
    private VehicleYears vehicleYears;




}
