package tinnova.test.com.example.demo.builders.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

public class CreateVehiclePayloadBuilder {

    private String chassis = "12345678901234567";
    private String plate = "ABC1234";
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private CreateVehiclePayloadBuilder() {
    }

    public static CreateVehiclePayloadBuilder create() {
        return new CreateVehiclePayloadBuilder();
    }

    public CreateVehiclePayloadBuilder withChassis(String chassis) { this.chassis = chassis; return this; }
    public CreateVehiclePayloadBuilder withPlate(String plate) { this.plate = plate; return this; }
    public CreateVehiclePayloadBuilder withBrand(String brand) { this.brand = brand; return this; }
    public CreateVehiclePayloadBuilder withModel(String model) { this.model = model; return this; }
    public CreateVehiclePayloadBuilder withYear(Integer year) { this.year = year; return this; }
    public CreateVehiclePayloadBuilder withColor(String color) { this.color = color; return this; }
    public CreateVehiclePayloadBuilder withPrice(BigDecimal price) { this.price = price; return this; }
    public CreateVehiclePayloadBuilder withType(VehicleType type) { this.type = type; return this; }

    public Map<String, Object> build() {
        Map<String, Object> map = new HashMap<>();
        map.put("chassis", chassis);
        map.put("plate", plate);
        map.put("brand", brand);
        map.put("model", model);
        map.put("year", year);
        map.put("color", color);
        map.put("price", price);
        map.put("type", type.name());
        return map;
    }
}
