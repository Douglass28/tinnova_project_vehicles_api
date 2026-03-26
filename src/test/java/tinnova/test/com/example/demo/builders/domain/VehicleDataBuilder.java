package tinnova.test.com.example.demo.builders.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;

public class VehicleDataBuilder {

    private String chassis = "12345678901234567";
    private String plate = "ABC1234";
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private VehicleDataBuilder() {
    }

    public static VehicleDataBuilder create() {
        return new VehicleDataBuilder();
    }

    public VehicleDataBuilder withChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public VehicleDataBuilder withPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public VehicleDataBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleDataBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public VehicleDataBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public VehicleDataBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public VehicleDataBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleDataBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleData build() {
        return VehicleData.builder()
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
