package tinnova.test.com.example.demo.application.usecases.vehicle.patch;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public class OutputMapper {

    private OutputMapper() {}

    public static PatchVehicleResponse toResponse(Vehicle vehicle) {
        return PatchVehicleResponse.builder()
            .id(vehicle.getId())
            .chassis(vehicle.getChassis())
            .plate(vehicle.getPlate())
            .brand(vehicle.getBrand())
            .model(vehicle.getModel())
            .year(vehicle.getYear())
            .color(vehicle.getColor())
            .price(vehicle.getPrice())
            .type(vehicle.getType())
            .status(vehicle.getStatus())
            .build();
    }
}
