package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrieve.RetrieveVehicleUseCase;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("RetrieveVehicleTest - integração com Postgres real")
class RetrieveVehicleTest extends UseCaseIntegrationTestBase {

    @Autowired
    private RetrieveVehicleUseCase retrieveVehicleUseCase;

    @Test
    @DisplayName("Deve listar veículos persistidos")
    void shouldListPersistedVehicles() {
        vehicleRepository.save(VehicleEntityBuilder.create().build());
        vehicleRepository.save(VehicleEntityBuilder.create().build());

        var result = retrieveVehicleUseCase.execute(null);

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver veículos")
    void shouldReturnEmptyListWhenNoVehiclesExist() {
        var result = retrieveVehicleUseCase.execute(null);
        assertEquals(0, result.size());
    }
}
