package com.example.seru.model.vehicleModel;

import com.example.seru.model.vehicleTypes.VehicleTypes;
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
public class VehicleModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "type_id",
            referencedColumnName = ""
    )
    private VehicleTypes vehicleTypes;

    public VehicleModels(String name, VehicleTypes vehicleTypes) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
    }



}
