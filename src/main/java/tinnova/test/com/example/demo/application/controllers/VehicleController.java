package tinnova.test.com.example.demo.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tinnova.test.com.example.demo.application.controllers.docs.VehicleApi;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleRequest;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersInput;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid.RetrieveVehicleByIdUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid.RetrieveVehicleByIdResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand.RetrieveVehiclesByBrandReportResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand.RetrieveVehiclesByBrandReportUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.delete.DeleteVehicleUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.patch.PatchVehicleRequest;
import tinnova.test.com.example.demo.application.usecases.vehicle.patch.PatchVehicleUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.update.UpdateVehicleRequest;
import tinnova.test.com.example.demo.application.usecases.vehicle.update.UpdateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.update.UpdateVehicleUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.math.BigDecimal;
import java.util.UUID;
import tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand.RetrieveVehiclesByBrandReportInput;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VehicleController implements VehicleApi {

    private final CreateVehicleUseCase createVehicleUseCase;
    private final RetrieveVehicleByFiltersUseCase retrieveVehicleByFiltersUseCase;
    private final RetrieveVehicleByIdUseCase retrieveVehicleByIdUseCase;
    private final RetrieveVehiclesByBrandReportUseCase retrieveVehiclesByBrandReportUseCase;
    private final UpdateVehicleUseCase updateVehicleUseCase;
    private final PatchVehicleUseCase patchVehicleUseCase;
    private final DeleteVehicleUseCase deleteVehicleUseCase;

    @Override
    public ResponseEntity<CreateVehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createVehicleUseCase.execute(request));
    }

    @Override
    public ResponseEntity<Page<RetrieveVehicleByFiltersResponse>> retrieveVehicles(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) String cor,
        @RequestParam(required = false) BigDecimal minPreco,
        @RequestParam(required = false) BigDecimal maxPreco,
        @PageableDefault(size = 20, sort = "brand") Pageable pageable) {
        RetrieveVehicleByFiltersInput input = RetrieveVehicleByFiltersInput.builder()
            .marca(marca)
            .ano(ano)
            .cor(cor)
            .minPreco(minPreco)
            .maxPreco(maxPreco)
            .pageable(pageable)
            .build();
        return ResponseEntity.ok(retrieveVehicleByFiltersUseCase.execute(input));
    }

    @Override
    public ResponseEntity<RetrieveVehicleByIdResponse> retrieveVehicleById(UUID id) {
        return ResponseEntity.ok(retrieveVehicleByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<Page<RetrieveVehiclesByBrandReportResponse>> retrieveVehiclesByBrandReport(
        @PageableDefault(size = 20, sort = "brand") Pageable pageable) {
        RetrieveVehiclesByBrandReportInput input = RetrieveVehiclesByBrandReportInput.builder()
            .pageable(pageable)
            .build();
        return ResponseEntity.ok(retrieveVehiclesByBrandReportUseCase.execute(input));
    }

    @Override
    public ResponseEntity<UpdateVehicleResponse> updateVehicle(UUID id, @RequestBody UpdateVehicleRequest request) {
        return ResponseEntity.ok(updateVehicleUseCase.execute(request.toInput(id)));
    }

    @Override
    public ResponseEntity<Void> patchVehicle(UUID id, @RequestBody PatchVehicleRequest request) {
        patchVehicleUseCase.execute(request.toInput(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(UUID id) {
        deleteVehicleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
