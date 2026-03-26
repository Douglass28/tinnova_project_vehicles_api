package tinnova.test.com.example.demo.application.controllers.docs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleRequest;

@Tag(name = "Veículos", description = "Endpoints relacionados à gestão de veículos.")
public interface VehicleApi {

    @Operation(summary = "Criar um novo veículo", description = "Cria um novo veículo")
    @PostMapping("/veiculos")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CreateVehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request);

}
