package tinnova.test.com.example.demo.application.usecases.vehicle.retrieve;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Getter
@Builder
public class RetrieveVehicleResponse {

    private UUID id;
    private String chassis;
    private String plate;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleType type;
    private VehicleStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
