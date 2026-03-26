package tinnova.test.com.example.demo.domain.repository;

import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DomainVehicleRepository {
    
    Vehicle save(Vehicle vehicle);
    
    List<Vehicle> findAll();

    Optional<Vehicle> findById(UUID id);

    Page<Vehicle> findByFilters(
        String marca,
        Integer ano,
        String cor,
        BigDecimal minPreco,
        BigDecimal maxPreco,
        Pageable pageable
    );

    Page<VehicleBrandCount> countActiveVehiclesByBrand(Pageable pageable);
}
