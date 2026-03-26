package tinnova.test.com.example.demo.application.usecases.vehicle.update;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Getter
@Builder
public class UpdateVehicleInput {

    private UUID id;
    private String chassis;
    private String plate;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleType type;
}
