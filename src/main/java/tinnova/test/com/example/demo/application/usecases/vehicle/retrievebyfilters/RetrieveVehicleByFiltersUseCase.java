package tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters;

import org.springframework.data.domain.Page;
import tinnova.test.com.example.demo.application.usecases.UseCase;

public interface RetrieveVehicleByFiltersUseCase
    extends UseCase<RetrieveVehicleByFiltersInput, Page<RetrieveVehicleByFiltersResponse>> {
}
