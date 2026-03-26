package tinnova.test.com.example.demo.application.persistence.vehicles;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;
import lombok.RequiredArgsConstructor;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.VehicleMapper;
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
    public List<Vehicle> findByFilters(String marca, Integer ano, String cor) {
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

        return vehicleMapper.toDomainList(vehicleRepository.findAll(specification));
    }

}
