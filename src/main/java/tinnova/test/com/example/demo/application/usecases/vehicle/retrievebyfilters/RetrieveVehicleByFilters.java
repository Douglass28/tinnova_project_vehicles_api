package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    public Page<RetrieveVehicleByFiltersResponse> execute(RetrieveVehicleByFiltersInput input) {
        Vehicle.validatePriceRange(input.getMinPreco(), input.getMaxPreco());

        Page<Vehicle> vehicles = vehicleRepository.findByFilters(
            input.getMarca(),
            input.getAno(),
            input.getCor(),
            input.getMinPreco(),
            input.getMaxPreco(),
            input.getPageable()
        );
        log.info("Veiculos recuperados por filtro com sucesso: {}", vehicles.getNumberOfElements());
        return OutputMapper.toResponsePage(vehicles);
    }
}
