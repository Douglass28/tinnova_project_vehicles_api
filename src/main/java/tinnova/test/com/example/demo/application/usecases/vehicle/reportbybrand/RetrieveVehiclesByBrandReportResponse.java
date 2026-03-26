package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RetrieveVehiclesByBrandReportResponse {

    @Schema(description = "Marca do veículo")
    private String brand;

    @Schema(description = "Quantidade de veículos ativos da marca")
    private Long count;
}
