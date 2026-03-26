package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public class OutputMapper {

    private OutputMapper() {}

    public static RetrieveVehicleByIdResponse toResponse(Vehicle vehicle) {
        return RetrieveVehicleByIdResponse.builder()
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
