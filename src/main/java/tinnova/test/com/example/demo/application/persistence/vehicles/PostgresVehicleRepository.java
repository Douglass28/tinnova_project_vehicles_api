package tinnova.test.com.example.demo.application.persistence.vehicles;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.VehicleMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.util.StringUtils;

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

    @Override
    public List<Vehicle> findAll() {
        return vehicleMapper.toDomainList(vehicleRepository.findAll());
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return vehicleRepository.findById(id).map(vehicleMapper::toDomain);
    }

    @Override
    public Page<Vehicle> findByFilters(
        String marca,
        Integer ano,
        String cor,
        BigDecimal minPreco,
        BigDecimal maxPreco,
        Pageable pageable
    ) {
        Specification<VehicleEntity> specification = (root, query, cb) -> cb.conjunction();

        if (StringUtils.hasText(marca)) {
            specification = specification.and((root, query, cb) ->
                cb.equal(cb.lower(root.get("brand")), marca.trim().toLowerCase())
            );
        }

        if (ano != null) {
            specification = specification.and((root, query, cb) ->
                cb.equal(root.get("year"), ano)
            );
        }

        if (StringUtils.hasText(cor)) {
            specification = specification.and((root, query, cb) ->
                cb.equal(cb.lower(root.get("color")), cor.trim().toLowerCase())
            );
        }

        if (minPreco != null) {
            specification = specification.and((root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("price"), minPreco)
            );
        }

        if (maxPreco != null) {
            specification = specification.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("price"), maxPreco)
            );
        }

        return vehicleRepository.findAll(specification, pageable).map(vehicleMapper::toDomain);
    }

    @Override
    public Page<VehicleBrandCount> countActiveVehiclesByBrand(Pageable pageable) {
        return vehicleRepository.countActiveVehiclesByBrand(pageable)
            .map(result -> new VehicleBrandCount(result.getBrand(), result.getCount()));
    }

}
