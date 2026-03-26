package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid;

import lombok.Builder;
import lombok.Getter;
import java.util.UUID;
import java.math.BigDecimal;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
public class RetrieveVehicleByIdResponse {
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
