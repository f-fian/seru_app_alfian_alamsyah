package com.example.seru.model.vehicleModel;

import com.example.seru.model.vehicleTypes.VehicleTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public VehicleModels(String name, VehicleTypes vehicleTypes) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
    }



}
