package tinnova.test.com.example.demo.builders.usecases;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.application.usecases.vehicle.patch.PatchVehicleInput;

public class PatchVehicleInputBuilder {

    private UUID id = UUID.randomUUID();
    private String chassis;
    private String plate;
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private PatchVehicleInputBuilder() {
    }

    public static PatchVehicleInputBuilder create() {
        return new PatchVehicleInputBuilder();
    }

    public PatchVehicleInputBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public PatchVehicleInputBuilder withChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public PatchVehicleInputBuilder withPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public PatchVehicleInputBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public PatchVehicleInputBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public PatchVehicleInputBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public PatchVehicleInputBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public PatchVehicleInputBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PatchVehicleInputBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public PatchVehicleInputBuilder withAllFieldsNull() {
        this.chassis = null;
        this.plate = null;
        this.brand = null;
        this.model = null;
        this.year = null;
        this.color = null;
        this.price = null;
        this.type = null;
        return this;
    }

    public PatchVehicleInput build() {
        return PatchVehicleInput.builder()
            .id(id)
            .chassis(chassis)
            .plate(plate)
            .brand(brand)
            .model(model)
            .year(year)
            .color(color)
            .price(price)
            .type(type)
            .build();
    }
}
