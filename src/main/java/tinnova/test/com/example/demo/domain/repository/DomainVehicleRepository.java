package tinnova.test.com.example.demo.domain.repository;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DomainVehicleRepository {
    
    Vehicle save(Vehicle vehicle);
    
    List<Vehicle> findAll();

    Optional<Vehicle> findById(UUID id);
}
