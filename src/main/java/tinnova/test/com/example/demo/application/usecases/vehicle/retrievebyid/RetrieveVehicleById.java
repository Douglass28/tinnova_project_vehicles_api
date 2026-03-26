package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid;

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
public class RetrieveVehicleById implements RetrieveVehicleByIdUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public RetrieveVehicleByIdResponse execute(UUID input) {
        Vehicle vehicle = vehicleRepository.findById(input)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
        log.info("Veículo recuperado com sucesso: {}", vehicle.getId());
        return OutputMapper.toResponse(vehicle);
    }
}
