package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters;

import java.util.List;
import org.springframework.data.domain.Page;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public class OutputMapper {

    private OutputMapper() {}

    public static RetrieveVehicleByFiltersResponse toResponse(Vehicle vehicle) {
        return RetrieveVehicleByFiltersResponse.builder()
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

    public static List<RetrieveVehicleByFiltersResponse> toResponseList(List<Vehicle> vehicles) {
        return vehicles.stream().map(OutputMapper::toResponse).toList();
    }

    public static Page<RetrieveVehicleByFiltersResponse> toResponsePage(Page<Vehicle> vehicles) {
        return vehicles.map(OutputMapper::toResponse);
    }
}
