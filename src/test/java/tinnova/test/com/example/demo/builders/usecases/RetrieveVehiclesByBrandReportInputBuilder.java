package tinnova.test.com.example.demo.builders.usecases;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand.RetrieveVehiclesByBrandReportInput;

public class RetrieveVehiclesByBrandReportInputBuilder {

    private Pageable pageable = PageRequest.of(0, 10);

    private RetrieveVehiclesByBrandReportInputBuilder() {
    }

    public static RetrieveVehiclesByBrandReportInputBuilder create() {
        return new RetrieveVehiclesByBrandReportInputBuilder();
    }

    public RetrieveVehiclesByBrandReportInputBuilder withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public RetrieveVehiclesByBrandReportInput build() {
        return RetrieveVehiclesByBrandReportInput.builder()
            .pageable(pageable)
            .build();
    }
}
