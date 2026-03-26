package tinnova.test.com.example.demo.builders.usecases;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.application.usecases.vehicle.update.UpdateVehicleInput;

public class UpdateVehicleInputBuilder {

    private UUID id = UUID.randomUUID();
    private String chassis = "12345678901234567";
    private String plate = "ABC1234";
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private UpdateVehicleInputBuilder() {
    }

    public static UpdateVehicleInputBuilder create() {
        return new UpdateVehicleInputBuilder();
    }

    public UpdateVehicleInputBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public UpdateVehicleInputBuilder withChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public UpdateVehicleInputBuilder withPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public UpdateVehicleInputBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public UpdateVehicleInputBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public UpdateVehicleInputBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public UpdateVehicleInputBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public UpdateVehicleInputBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UpdateVehicleInputBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public UpdateVehicleInput build() {
        return UpdateVehicleInput.builder()
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
