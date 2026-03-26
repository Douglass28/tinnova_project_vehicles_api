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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VehicleController implements VehicleApi {

    private final CreateVehicleUseCase createVehicleUseCase;
    private final RetrieveVehicleByFiltersUseCase retrieveVehicleByFiltersUseCase;
    private final RetrieveVehicleByIdUseCase retrieveVehicleByIdUseCase;

    @Override
    public ResponseEntity<CreateVehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createVehicleUseCase.execute(request));
    }

    @Override
    public ResponseEntity<List<RetrieveVehicleByFiltersResponse>> retrieveVehicles(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) String cor) {
            
        RetrieveVehicleByFiltersInput input = RetrieveVehicleByFiltersInput.builder()
            .marca(marca)
            .ano(ano)
            .cor(cor)
            .build();
        return ResponseEntity.ok(retrieveVehicleByFiltersUseCase.execute(input));
    }

    @Override
    public ResponseEntity<RetrieveVehicleByIdResponse> retrieveVehicleById(UUID id) {
        return ResponseEntity.ok(retrieveVehicleByIdUseCase.execute(id));
    }

}
