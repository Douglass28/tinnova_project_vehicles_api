package tinnova.test.com.example.demo.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tinnova.test.com.example.demo.application.controllers.docs.VehicleApi;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleUseCase;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VehicleController implements VehicleApi {

    private final CreateVehicleUseCase createVehicleUseCase;

    @Override
    public ResponseEntity<CreateVehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createVehicleUseCase.execute(request));
    }

}
