package tinnova.test.com.example.demo.builders.usecases;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import tinnova.test.com.example.demo.application.persistence.vehicles.VehicleEntity;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

public class VehicleEntityBuilder {

    private UUID id = UUID.randomUUID();
    private String chassis = ("CH" + UUID.randomUUID()).substring(0, 17);
    private String plate = ("PL" + UUID.randomUUID()).substring(0, 8);
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;
    private VehicleStatus status = VehicleStatus.ACTIVE;
    private LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
    private LocalDateTime updatedAt = LocalDateTime.now();

    private VehicleEntityBuilder() {
    }

    public static VehicleEntityBuilder create() {
        return new VehicleEntityBuilder();
    }

    public VehicleEntityBuilder withId(UUID id) { this.id = id; return this; }
    public VehicleEntityBuilder withChassis(String chassis) { this.chassis = chassis; return this; }
    public VehicleEntityBuilder withPlate(String plate) { this.plate = plate; return this; }
    public VehicleEntityBuilder withBrand(String brand) { this.brand = brand; return this; }
    public VehicleEntityBuilder withModel(String model) { this.model = model; return this; }
    public VehicleEntityBuilder withYear(Integer year) { this.year = year; return this; }
    public VehicleEntityBuilder withColor(String color) { this.color = color; return this; }
    public VehicleEntityBuilder withPrice(BigDecimal price) { this.price = price; return this; }
    public VehicleEntityBuilder withType(VehicleType type) { this.type = type; return this; }
    public VehicleEntityBuilder withStatus(VehicleStatus status) { this.status = status; return this; }

    public VehicleEntity build() {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(id);
        entity.setChassis(chassis);
        entity.setPlate(plate);
        entity.setBrand(brand);
        entity.setModel(model);
        entity.setYear(year);
        entity.setColor(color);
        entity.setPrice(price);
        entity.setType(type);
        entity.setStatus(status);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        return entity;
    }
}
