package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand.RetrieveVehiclesByBrandReportUseCase;
import tinnova.test.com.example.demo.builders.usecases.RetrieveVehiclesByBrandReportInputBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("RetrieveVehiclesByBrandReportTest - integração com Postgres real")
class RetrieveVehiclesByBrandReportTest extends UseCaseIntegrationTestBase {

    @Autowired
    private RetrieveVehiclesByBrandReportUseCase reportUseCase;

    @Test
    @DisplayName("Deve gerar relatorio por marca considerando apenas ativos")
    void shouldGenerateReportOnlyForActiveVehicles() {
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withStatus(VehicleStatus.ACTIVE).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withStatus(VehicleStatus.ACTIVE).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("BMW").withStatus(VehicleStatus.ACTIVE).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("BMW").withStatus(VehicleStatus.DELETED).build());

        var result = reportUseCase.execute(
            RetrieveVehiclesByBrandReportInputBuilder.create()
                .withPageable(PageRequest.of(0, 10))
                .build()
        );

        assertFalse(result.isEmpty());
        assertTrue(result.getContent().stream().anyMatch(item -> "Ford".equals(item.getBrand()) && item.getCount() == 2L));
        assertTrue(result.getContent().stream().anyMatch(item -> "BMW".equals(item.getBrand()) && item.getCount() == 1L));
    }
}
