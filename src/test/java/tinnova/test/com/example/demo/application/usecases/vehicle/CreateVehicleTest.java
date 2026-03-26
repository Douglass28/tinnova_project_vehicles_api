package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.integrations.IDollarApiIntegration;
import tinnova.test.com.example.demo.application.persistence.vehicles.VehicleEntity;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleUseCase;
import tinnova.test.com.example.demo.builders.usecases.CreateVehicleRequestBuilder;

@DisplayName("CreateVehicleTest - integração com Postgres real")
@Import(CreateVehicleTest.CreateVehicleTestConfig.class)
class CreateVehicleTest extends UseCaseIntegrationTestBase {

    @Autowired
    private CreateVehicleUseCase createVehicleUseCase;

    @TestConfiguration
    static class CreateVehicleTestConfig {
        @Bean
        @Primary
        IDollarApiIntegration testDollarApiIntegrationForCreate() {
            return () -> new BigDecimal("5.00");
        }
    }

    @Test
    @DisplayName("Deve criar veiculo com sucesso persistindo preco em USD")
    void shouldCreateVehicleWithUsdPrice() {
        var request = CreateVehicleRequestBuilder.create()
            .withPrice(new BigDecimal("10000.00"))
            .build();

        CreateVehicleResponse response = createVehicleUseCase.execute(request);

        assertNotNull(response.getId());
        assertEquals(0, new BigDecimal("2000.00").compareTo(response.getPrice()));

        Optional<VehicleEntity> persisted = vehicleRepository.findById(response.getId());
        assertNotNull(persisted.orElse(null));
        assertEquals(0, new BigDecimal("2000.00").compareTo(persisted.get().getPrice()));
    }

    @Test
    @DisplayName("Deve lançar excecao ao criar com chassis duplicado")
    void shouldThrowWhenChassisAlreadyExists() {
        String duplicatedChassis = "23955678901234590";
        createVehicleUseCase.execute(
            CreateVehicleRequestBuilder.create()
                .withChassis(duplicatedChassis)
                .withPlate("AAA1234")
                .build()
        );

        assertThrowsExactly(
            DataIntegrityViolationException.class,
            () -> createVehicleUseCase.execute(
                CreateVehicleRequestBuilder.create()
                    .withChassis(duplicatedChassis)
                    .withPlate("BBB1234")
                    .build()
            )
        );
    }
}
