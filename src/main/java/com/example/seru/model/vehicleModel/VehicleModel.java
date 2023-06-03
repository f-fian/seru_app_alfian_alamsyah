package com.example.seru.model.vehicleModel;

import com.example.seru.model.vehicleTypes.VehicleTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "type_id",
            referencedColumnName = ""
    )
    private VehicleTypes vehicleTypes;

}
