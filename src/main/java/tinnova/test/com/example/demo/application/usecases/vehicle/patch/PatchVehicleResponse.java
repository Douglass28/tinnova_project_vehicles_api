package tinnova.test.com.example.demo.application.usecases.vehicle.patch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Getter
@Builder
public class PatchVehicleResponse {

    @Schema(description = "ID do veículo")
    private UUID id;
    @Schema(description = "Chassis do veículo")
    private String chassis;
    @Schema(description = "Placa do veículo")
    private String plate;
    @Schema(description = "Marca do veículo")
    private String brand;
    @Schema(description = "Modelo do veículo")
    private String model;
    @Schema(description = "Ano do veículo")
    private Integer year;
    @Schema(description = "Cor do veículo")
    private String color;
    @Schema(description = "Preço do veículo")
    private BigDecimal price;
    @Schema(description = "Tipo do veículo")
    private VehicleType type;
    @Schema(description = "Status do veículo")
    private VehicleStatus status;
}
