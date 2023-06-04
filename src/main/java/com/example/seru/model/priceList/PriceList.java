package com.example.seru.model.priceList;


import com.example.seru.model.vehicleModel.VehicleModels;
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
            name = "year_id",
            referencedColumnName = "id"
    )
    private VehicleYears vehicleYears;

    @ManyToOne
    @JoinColumn(
            name = "model_id",
            referencedColumnName = "id"
    )
    private VehicleModels vehicleModels;


    public PriceList(Integer price, VehicleYears vehicleYears, VehicleModels vehicleModels) {
        System.out.println("con");
        this.price = price;
        this.vehicleYears = vehicleYears;
        this.vehicleModels = vehicleModels;
    }
}
