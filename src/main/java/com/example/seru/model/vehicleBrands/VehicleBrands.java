package com.example.seru.model.vehicleBrands;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name",name = "uniqeNameConstraint")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBrands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "the name field is cant be blank")
    private String name;

    public VehicleBrands(String name) {
        this.name = name;
    }
}
