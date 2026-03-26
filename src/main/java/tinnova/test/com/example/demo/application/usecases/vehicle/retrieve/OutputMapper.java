package tinnova.test.com.example.demo.application.usecases.vehicle.retrieve;

import java.util.List;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public class OutputMapper {

    private OutputMapper() {}

    public static RetrieveVehicleResponse toResponse(Vehicle vehicle) {
        return RetrieveVehicleResponse.builder()
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
            .createdAt(vehicle.getCreatedAt())
            .updatedAt(vehicle.getUpdatedAt())
            .build();
    }

    public static List<RetrieveVehicleResponse> toResponseList(List<Vehicle> vehicles) {
        return vehicles.stream().map(OutputMapper::toResponse).toList();
    }
}
