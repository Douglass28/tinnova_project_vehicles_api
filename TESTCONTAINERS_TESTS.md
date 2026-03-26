# Testes de Use Case com Testcontainers

Este projeto possui testes de integração de use case usando PostgreSQL real via Testcontainers.

## Pré-requisitos

- Docker em execução local.
- Maven Wrapper disponível (`./mvnw`).

## Testes adicionados

- `CreateAndRetrieveVehicleUseCaseIT`
- `VehicleMutationUseCaseIT`
- `VehicleQueryUseCaseIT`

Todos usam:

- `UseCaseIntegrationTestBase`
- `TestcontainersConfiguration`
- Banco PostgreSQL em container
- Sem mock de repositório

## Estratégia de isolamento de dados

- Antes de cada teste, a tabela de veículos é limpa com `vehicleRepository.deleteAll()`.
- Isso garante independência entre cenários.

## Comandos úteis

Rodar apenas testes de integração de use cases:

```bash
./mvnw -Dtest=CreateAndRetrieveVehicleUseCaseIT,VehicleMutationUseCaseIT,VehicleQueryUseCaseIT test
```

Rodar suíte completa:

```bash
./mvnw test
```

## CI

O ambiente de CI precisa disponibilizar Docker para executar os testes com Testcontainers.
