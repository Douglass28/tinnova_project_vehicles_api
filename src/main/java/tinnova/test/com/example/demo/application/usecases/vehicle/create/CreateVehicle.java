package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tinnova.test.com.example.demo.application.integrations.IDollarApiIntegration;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;
import tinnova.test.com.example.demo.domain.entities.vehicle.Vehicle;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CreateVehicle implements CreateVehicleUseCase {

    private final DomainVehicleRepository vehicleRepository;
    private final IDollarApiIntegration dollarApiIntegration;

    @Override
    public CreateVehicleResponse execute(CreateVehicleRequest input) {
        BigDecimal usdToBrlRate = dollarApiIntegration.getUsdToBrlRate();
        BigDecimal priceInUsd = input.getPrice().divide(usdToBrlRate, 2, RoundingMode.HALF_UP);

        Vehicle vehicle = 
                Vehicle.create(VehicleData.builder()
                    .chassis(input.getChassis())
                    .plate(input.getPlate())
                    .brand(input.getBrand())
                    .model(input.getModel())
                    .year(input.getYear())
                    .color(input.getColor())
                    .price(priceInUsd)
                    .type(input.getType())
                    .build());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        log.info("Veiculo criado com sucesso em USD. Cotacao usada USD/BRL={}", usdToBrlRate);
        return OutputMapper.toResponse(savedVehicle);
    }

}
