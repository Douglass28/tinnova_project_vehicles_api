package tinnova.test.com.example.demo.domain.repository;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DomainVehicleRepository {
    
    Vehicle save(Vehicle vehicle);
    
    List<Vehicle> findAll();

    Optional<Vehicle> findById(UUID id);

    List<Vehicle> findByFilters(String marca, Integer ano, String cor, BigDecimal minPreco, BigDecimal maxPreco);

    List<VehicleBrandCount> countActiveVehiclesByBrand();
}
