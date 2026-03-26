package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.*;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequest {

    @NotBlank
    @Size(max = 17)
    @Schema(description = "Chassi do veículo", example = "12345678901234567")
    private String chassis;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "Placa do veículo", example = "ABC1234")
    private String plate;

    @NotBlank
    @Size(max = 120)
    @Schema(description = "Marca do veículo", example = "Ford")
    private String brand;

    @NotBlank
    @Size(max = 120)
    @Schema(description = "Modelo do veículo", example = "Focus")
    private String model;

    @NotNull
    @Schema(description = "Ano do veículo", example = "2020")
    @Positive
    private Integer year;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "Cor do veículo", example = "Azul")
    private String color;

    @NotNull
    @Schema(description = "Preco do veiculo em BRL (sera convertido e persistido em USD)", example = "10000.00")
    @Positive
    private BigDecimal price;

    @NotNull
    @Schema(description = "Tipo do veículo", example = "CAR")
    private VehicleType type;

}
