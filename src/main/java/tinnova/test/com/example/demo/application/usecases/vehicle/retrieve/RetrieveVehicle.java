package tinnova.test.com.example.demo.application.usecases.vehicle.retrieve;

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
public class RetrieveVehicle implements RetrieveVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public List<RetrieveVehicleResponse> execute(Void input) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        log.info("Veiculos recuperados com sucesso: {}", vehicles.size());
        return OutputMapper.toResponseList(vehicles);
    }
}
