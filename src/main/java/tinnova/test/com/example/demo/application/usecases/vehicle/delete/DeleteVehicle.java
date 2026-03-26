package tinnova.test.com.example.demo.application.usecases.vehicle.delete;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class DeleteVehicle implements DeleteVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public Void execute(UUID input) {
        Vehicle vehicle = vehicleRepository.findById(input)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        vehicle.delete();
        vehicleRepository.save(vehicle);
        log.info("Veículo deletado logicamente com sucesso: {}", vehicle.getId());
        return null;
    }
}
