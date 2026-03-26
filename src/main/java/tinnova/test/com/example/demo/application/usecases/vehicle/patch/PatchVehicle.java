package tinnova.test.com.example.demo.application.usecases.vehicle.patch;

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
public class PatchVehicle implements PatchVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public PatchVehicleResponse execute(PatchVehicleInput input) {
        if (input.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe ao menos um campo para atualização");
        }

        Vehicle vehicle = vehicleRepository.findById(input.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        VehicleData mergedData = VehicleData.builder()
            .chassis(input.getChassis() != null ? input.getChassis() : vehicle.getChassis())
            .plate(input.getPlate() != null ? input.getPlate() : vehicle.getPlate())
            .brand(input.getBrand() != null ? input.getBrand() : vehicle.getBrand())
            .model(input.getModel() != null ? input.getModel() : vehicle.getModel())
            .year(input.getYear() != null ? input.getYear() : vehicle.getYear())
            .color(input.getColor() != null ? input.getColor() : vehicle.getColor())
            .price(input.getPrice() != null ? input.getPrice() : vehicle.getPrice())
            .type(input.getType() != null ? input.getType() : vehicle.getType())
            .build();

        vehicle.update(mergedData);

        Vehicle patchedVehicle = vehicleRepository.save(vehicle);
        log.info("Veículo atualizado parcialmente com sucesso: {}", patchedVehicle.getId());
        return OutputMapper.toResponse(patchedVehicle);
    }
}
