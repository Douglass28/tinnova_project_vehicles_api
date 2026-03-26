package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersUseCase;
import tinnova.test.com.example.demo.builders.usecases.RetrieveVehicleByFiltersInputBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;
import tinnova.test.com.example.demo.domain.exceptions.BusinessException;

@DisplayName("RetrieveVehicleByFiltersTest - integração com Postgres real")
class RetrieveVehicleByFiltersTest extends UseCaseIntegrationTestBase {

    @Autowired
    private RetrieveVehicleByFiltersUseCase retrieveVehicleByFiltersUseCase;

    @Test
    @DisplayName("Deve filtrar por marca e faixa de preço")
    void shouldFilterByBrandAndPriceRange() {
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withPrice(new BigDecimal("10000.00")).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withPrice(new BigDecimal("18000.00")).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("BMW").withPrice(new BigDecimal("90000.00")).build());

        var result = retrieveVehicleByFiltersUseCase.execute(
            RetrieveVehicleByFiltersInputBuilder.create()
                .withMarca("ford")
                .withMinPreco(new BigDecimal("9000.00"))
                .withMaxPreco(new BigDecimal("20000.00"))
                .withPageable(PageRequest.of(0, 10))
                .build()
        );

        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().stream().allMatch(v -> "Ford".equals(v.getBrand())));
    }

    @Test
    @DisplayName("Deve lançar exceção para faixa inválida")
    void shouldThrowExceptionForInvalidRange() {
        BusinessException exception = assertThrowsExactly(
            BusinessException.class,
            () -> retrieveVehicleByFiltersUseCase.execute(
                RetrieveVehicleByFiltersInputBuilder.create()
                    .withMinPreco(new BigDecimal("20.00"))
                    .withMaxPreco(new BigDecimal("10.00"))
                    .build()
            )
        );
        assertEquals("Faixa de preço inválida", exception.getMessage());
    }

    @Test
    @DisplayName("Deve respeitar paginação e ordenação")
    void shouldRespectPaginationAndSort() {
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Audi").build());

        var result = retrieveVehicleByFiltersUseCase.execute(
            RetrieveVehicleByFiltersInputBuilder.create()
                .withPageable(PageRequest.of(0, 1, Sort.by("brand").ascending()))
                .build()
        );

        assertEquals(1, result.getContent().size());
        assertEquals("Audi", result.getContent().getFirst().getBrand());
    }
}
