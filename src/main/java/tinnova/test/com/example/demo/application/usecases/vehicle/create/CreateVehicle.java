package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CreateVehicle implements CreateVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public CreateVehicleResponse execute(CreateVehicleRequest input) {

        Vehicle vehicle = 
                Vehicle.create(VehicleData.builder()
                    .chassis(input.getChassis())
                    .plate(input.getPlate())
                    .brand(input.getBrand())
                    .model(input.getModel())
                    .year(input.getYear())
                    .color(input.getColor())
                    .price(input.getPrice())
                    .type(input.getType())
                    .build());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        log.info("Veículo criado com sucesso: {}", savedVehicle);
        return OutputMapper.toResponse(savedVehicle);
    }

}
