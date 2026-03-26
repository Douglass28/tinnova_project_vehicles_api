package tinnova.test.com.example.demo.domain.repository;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;

public interface DomainVehicleRepository {
    
    Vehicle save(Vehicle vehicle);
}
