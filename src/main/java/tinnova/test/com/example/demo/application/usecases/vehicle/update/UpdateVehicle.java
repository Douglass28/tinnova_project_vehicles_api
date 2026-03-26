package tinnova.test.com.example.demo.application.usecases.vehicle.update;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UpdateVehicle implements UpdateVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public UpdateVehicleResponse execute(UpdateVehicleInput input) {
        Vehicle vehicle = vehicleRepository.findById(input.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        VehicleData vehicleData = VehicleData.builder()
            .chassis(input.getChassis())
            .plate(input.getPlate())
            .brand(input.getBrand())
            .model(input.getModel())
            .year(input.getYear())
            .color(input.getColor())
            .price(input.getPrice())
            .type(input.getType())
            .build();

        vehicle.update(vehicleData);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        log.info("Veículo atualizado com sucesso: {}", updatedVehicle.getId());
        return OutputMapper.toResponse(updatedVehicle);
    }
}
