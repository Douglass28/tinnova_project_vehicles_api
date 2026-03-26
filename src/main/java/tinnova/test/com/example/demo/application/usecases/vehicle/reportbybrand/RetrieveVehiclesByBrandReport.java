package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class RetrieveVehiclesByBrandReport implements RetrieveVehiclesByBrandReportUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public List<RetrieveVehiclesByBrandReportResponse> execute(Void input) {
        List<VehicleBrandCount> report = vehicleRepository.countActiveVehiclesByBrand();
        log.info("Relatório por marca gerado com sucesso: {} linhas", report.size());
        return OutputMapper.toResponseList(report);
    }
}
