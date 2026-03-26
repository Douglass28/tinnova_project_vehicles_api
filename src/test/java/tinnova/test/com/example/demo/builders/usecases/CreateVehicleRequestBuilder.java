package tinnova.test.com.example.demo.builders.usecases;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleRequest;

public class CreateVehicleRequestBuilder {

    private String chassis = "12345678901234567";
    private String plate = "ABC1234";
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private CreateVehicleRequestBuilder() {
    }

    public static CreateVehicleRequestBuilder create() {
        return new CreateVehicleRequestBuilder();
    }

    public CreateVehicleRequestBuilder withChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public CreateVehicleRequestBuilder withPlate(String plate) {
        this.plate = plate;
        return this;
    }

    public CreateVehicleRequestBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CreateVehicleRequestBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CreateVehicleRequestBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public CreateVehicleRequestBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CreateVehicleRequestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CreateVehicleRequestBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public CreateVehicleRequest build() {
        return CreateVehicleRequest.builder()
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
