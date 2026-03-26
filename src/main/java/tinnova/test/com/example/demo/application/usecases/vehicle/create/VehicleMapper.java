package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.application.persistence.vehicles.VehicleEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VehicleMapper {

    public VehicleEntity toEntity(Vehicle domain) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(domain.getId());
        entity.setChassis(domain.getChassis());
        entity.setPlate(domain.getPlate());
        entity.setBrand(domain.getBrand());
        entity.setModel(domain.getModel());
        entity.setYear(domain.getYear());
        entity.setColor(domain.getColor());
        entity.setPrice(domain.getPrice());
        entity.setType(domain.getType());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        return entity;
    }

    public Vehicle toDomain(VehicleEntity entity) {
        VehicleData vehicleData = VehicleData.builder()
            .chassis(entity.getChassis())
            .plate(entity.getPlate())
            .brand(entity.getBrand())
            .model(entity.getModel())
            .year(entity.getYear())
            .color(entity.getColor())
            .price(entity.getPrice())
            .type(entity.getType())
            .build();
        return Vehicle.rebuild(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getStatus(), vehicleData);
    }

    public List<Vehicle> toDomainList(List<VehicleEntity> entities) {
        return entities.stream().map(this::toDomain).toList();
    }
}
