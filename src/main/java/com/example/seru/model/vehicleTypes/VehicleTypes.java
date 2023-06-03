package com.example.seru.model.vehicleTypes;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id"
    )
    private VehicleBrands vehicleBrands;
}
