package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class RetrieveVehicleByFilters implements RetrieveVehicleByFiltersUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public List<RetrieveVehicleByFiltersResponse> execute(RetrieveVehicleByFiltersInput input) {
        List<Vehicle> vehicles = vehicleRepository.findByFilters(input.getMarca(), input.getAno(), input.getCor());
        log.info("Veiculos recuperados por filtro com sucesso: {}", vehicles.size());
        return OutputMapper.toResponseList(vehicles);
    }
}
