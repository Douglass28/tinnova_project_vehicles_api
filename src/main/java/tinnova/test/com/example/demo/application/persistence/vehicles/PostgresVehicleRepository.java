package tinnova.test.com.example.demo.application.persistence.vehicles;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.VehicleMapper;

@Repository
@RequiredArgsConstructor
public class PostgresVehicleRepository implements DomainVehicleRepository {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity entity = vehicleMapper.toEntity(vehicle);
        VehicleEntity savedEntity = vehicleRepository.save(entity);
        return vehicleMapper.toDomain(savedEntity);
    }

}
