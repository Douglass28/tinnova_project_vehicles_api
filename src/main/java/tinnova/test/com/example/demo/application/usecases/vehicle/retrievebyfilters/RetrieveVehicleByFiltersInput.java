package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RetrieveVehicleByFiltersInput {

    private String marca;
    private Integer ano;
    private String cor;
    private BigDecimal minPreco;
    private BigDecimal maxPreco;
}
