package tinnova.test.com.example.demo.application.controllers.docs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.create.CreateVehicleRequest;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyfilters.RetrieveVehicleByFiltersResponse;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid.RetrieveVehicleByIdResponse;

@Tag(name = "Veículos", description = "Endpoints relacionados à gestão de veículos.")
public interface VehicleApi {

    @Operation(summary = "Criar um novo veículo", description = "Cria um novo veículo")
    @PostMapping("/veiculos")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CreateVehicleResponse> createVehicle(@RequestBody CreateVehicleRequest request);

    @Operation(summary = "Listar veículos", description = "Retorna todos os veículos")
    @GetMapping("/veiculos")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<RetrieveVehicleByFiltersResponse>> retrieveVehicles(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) String cor
    );

    @Operation(summary = "Buscar veículo por ID", description = "Retorna os dados de um veículo pelo seu ID")
    @GetMapping("/veiculos/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RetrieveVehicleByIdResponse> retrieveVehicleById(@PathVariable UUID id);

}
