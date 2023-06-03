package com.example.seru.model.vehicleTypes;

import com.example.seru.model.vehicleBrands.VehicleBrands;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class VehicleTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name field is cant be blank")
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id"
    )
    private VehicleBrands vehicleBrands;

    public VehicleTypes(String name, VehicleBrands vehicleBrands) {
        this.name = name;
        this.vehicleBrands = vehicleBrands;
    }
}
