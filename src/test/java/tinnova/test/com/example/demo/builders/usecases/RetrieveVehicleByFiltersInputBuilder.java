package tinnova.test.com.example.demo.builders.usecases;

import java.math.BigDecimal;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersInput;

@Setter
public class RetrieveVehicleByFiltersInputBuilder {

    private String marca;
    private Integer ano;
    private String cor;
    private BigDecimal minPreco;
    private BigDecimal maxPreco;
    private Pageable pageable = PageRequest.of(0, 10);

    private RetrieveVehicleByFiltersInputBuilder() {
    }

    public static RetrieveVehicleByFiltersInputBuilder create() {
        return new RetrieveVehicleByFiltersInputBuilder();
    }

    public RetrieveVehicleByFiltersInputBuilder withMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public RetrieveVehicleByFiltersInputBuilder withAno(Integer ano) {
        this.ano = ano;
        return this;
    }

    public RetrieveVehicleByFiltersInputBuilder withCor(String cor) {
        this.cor = cor;
        return this;
    }

    public RetrieveVehicleByFiltersInputBuilder withMinPreco(BigDecimal minPreco) {
        this.minPreco = minPreco;
        return this;
    }

    public RetrieveVehicleByFiltersInputBuilder withMaxPreco(BigDecimal maxPreco) {
        this.maxPreco = maxPreco;
        return this;
    }

    public RetrieveVehicleByFiltersInputBuilder withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public RetrieveVehicleByFiltersInput build() {
        return RetrieveVehicleByFiltersInput.builder()
            .marca(marca)
            .ano(ano)
            .cor(cor)
            .minPreco(minPreco)
            .maxPreco(maxPreco)
            .pageable(pageable)
            .build();
    }
}
