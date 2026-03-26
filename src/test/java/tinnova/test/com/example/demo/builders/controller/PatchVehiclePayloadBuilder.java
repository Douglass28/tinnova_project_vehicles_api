package tinnova.test.com.example.demo.builders.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

public class PatchVehiclePayloadBuilder {

    private String chassis;
    private String plate;
    private String brand = "BMW";
    private String model;
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleType type;

    private PatchVehiclePayloadBuilder() {
    }

    public static PatchVehiclePayloadBuilder create() {
        return new PatchVehiclePayloadBuilder();
    }

    public PatchVehiclePayloadBuilder withChassis(String chassis) { this.chassis = chassis; return this; }
    public PatchVehiclePayloadBuilder withPlate(String plate) { this.plate = plate; return this; }
    public PatchVehiclePayloadBuilder withBrand(String brand) { this.brand = brand; return this; }
    public PatchVehiclePayloadBuilder withModel(String model) { this.model = model; return this; }
    public PatchVehiclePayloadBuilder withYear(Integer year) { this.year = year; return this; }
    public PatchVehiclePayloadBuilder withColor(String color) { this.color = color; return this; }
    public PatchVehiclePayloadBuilder withPrice(BigDecimal price) { this.price = price; return this; }
    public PatchVehiclePayloadBuilder withType(VehicleType type) { this.type = type; return this; }

    public Map<String, Object> build() {
        Map<String, Object> map = new HashMap<>();
        if (chassis != null) map.put("chassis", chassis);
        if (plate != null) map.put("plate", plate);
        if (brand != null) map.put("brand", brand);
        if (model != null) map.put("model", model);
        if (year != null) map.put("year", year);
        if (color != null) map.put("color", color);
        if (price != null) map.put("price", price);
        if (type != null) map.put("type", type.name());
        return map;
    }
}
