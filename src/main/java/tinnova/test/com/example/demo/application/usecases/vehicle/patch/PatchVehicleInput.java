package tinnova.test.com.example.demo.application.usecases.vehicle.patch;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Getter
@Builder
public class PatchVehicleInput {

    private UUID id;
    private String chassis;
    private String plate;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleType type;

    public boolean isEmpty() {
        return chassis == null
            && plate == null
            && brand == null
            && model == null
            && year == null
            && color == null
            && price == null
            && type == null;
    }
}
