package com.example.seru.model.priceList;


import com.example.seru.model.vehicleModel.VehicleModels;
import com.example.seru.model.vehicleYears.VehicleYears;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public PriceList(Integer price, VehicleYears vehicleYears, VehicleModels vehicleModels) {
        System.out.println("con");
        this.price = price;
        this.vehicleYears = vehicleYears;
        this.vehicleModels = vehicleModels;
    }
}
