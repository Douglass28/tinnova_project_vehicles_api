package tinnova.test.com.example.demo.builders.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

public class UpdateVehiclePayloadBuilder {

    private String chassis = "12345678901234567";
    private String plate = "ABC1234";
    private String brand = "Ford";
    private String model = "Focus";
    private Integer year = LocalDateTime.now().getYear();
    private String color = "Azul";
    private BigDecimal price = new BigDecimal("10000.00");
    private VehicleType type = VehicleType.CAR;

    private UpdateVehiclePayloadBuilder() {
    }

    public static UpdateVehiclePayloadBuilder create() {
        return new UpdateVehiclePayloadBuilder();
    }

    public UpdateVehiclePayloadBuilder withChassis(String chassis) { this.chassis = chassis; return this; }
    public UpdateVehiclePayloadBuilder withPlate(String plate) { this.plate = plate; return this; }
    public UpdateVehiclePayloadBuilder withBrand(String brand) { this.brand = brand; return this; }
    public UpdateVehiclePayloadBuilder withModel(String model) { this.model = model; return this; }
    public UpdateVehiclePayloadBuilder withYear(Integer year) { this.year = year; return this; }
    public UpdateVehiclePayloadBuilder withColor(String color) { this.color = color; return this; }
    public UpdateVehiclePayloadBuilder withPrice(BigDecimal price) { this.price = price; return this; }
    public UpdateVehiclePayloadBuilder withType(VehicleType type) { this.type = type; return this; }

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
