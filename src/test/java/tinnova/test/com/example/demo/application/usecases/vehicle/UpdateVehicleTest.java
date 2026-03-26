package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.usecases.vehicle.update.UpdateVehicleUseCase;
import tinnova.test.com.example.demo.builders.usecases.UpdateVehicleInputBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("UpdateVehicleTest - integração com Postgres real")
class UpdateVehicleTest extends UseCaseIntegrationTestBase {

    @Autowired
    private UpdateVehicleUseCase updateVehicleUseCase;

    @Test
    @DisplayName("Deve atualizar veículo com sucesso")
    void shouldUpdateVehicle() {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());

        var response = updateVehicleUseCase.execute(
            UpdateVehicleInputBuilder.create()
                .withId(entity.getId())
                .withBrand("Honda")
                .withModel("Civic")
                .withPrice(new BigDecimal("32000.00"))
                .build()
        );

        assertEquals(entity.getId(), response.getId());
        assertEquals("Honda", response.getBrand());
        assertEquals("Civic", response.getModel());
        assertEquals(0, new BigDecimal("32000.00").compareTo(response.getPrice()));
    }

    @Test
    @DisplayName("Deve retornar not found para id inexistente")
    void shouldReturnNotFound() {
        ResponseStatusException exception = assertThrowsExactly(
            ResponseStatusException.class,
            () -> updateVehicleUseCase.execute(
                UpdateVehicleInputBuilder.create().withId(UUID.randomUUID()).build()
            )
        );
        assertEquals(404, exception.getStatusCode().value());
    }
}
