package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public class OutputMapper {

    private OutputMapper() {}

    public static CreateVehicleResponse toResponse(Vehicle vehicle) {
        return CreateVehicleResponse.builder()
            .id(vehicle.getId())
            .chassis(vehicle.getChassis())   
            .plate(vehicle.getPlate())
            .brand(vehicle.getBrand())
            .model(vehicle.getModel())
            .year(vehicle.getYear())
            .color(vehicle.getColor())
            .price(vehicle.getPrice())
            .type(vehicle.getType())
            .build();
    }
}
