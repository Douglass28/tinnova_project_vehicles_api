# Vehicles API - Tinnova

API REST para gestão de veículos, com operações de cadastro, consulta, atualização, remoção lógica, relatórios por marca, autenticação JWT e integração com cotação USD/BRL.

O projeto foi estruturado em camadas com foco em casos de uso, separando controller, aplicação, domínio, persistência e integrações externas.

## Tecnologias

- Java 21
- Spring Boot 4.0.4
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Redis
- Spring Security
- JWT
- Swagger / OpenAPI
- Testcontainers
- Docker / Docker Compose
- JaCoCo
- SonarQube / Sonar Scanner Maven

## Como Rodar

### Pré-requisitos

- Java 21
- Docker em execução
- Maven Wrapper disponível no projeto (`./mvnw`)

### Configurações da aplicação

A aplicação usa variáveis de ambiente com defaults locais em `src/main/resources/application.properties`.

Valores padrão atuais:

- Banco:
  - host: `localhost`
  - porta: `5432`
  - database: `demo`
  - usuário: `demo`
  - senha: `demo`
- Redis:
  - host: `localhost`
  - porta: `6379`
- JWT:
  - expiração padrão: `3600000`

Variáveis relevantes:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `REDIS_HOST`
- `REDIS_PORT`
- `JWT_SECRET`
- `JWT_EXPIRATION_MS`
- `USD_BRL_CACHE_TTL_MINUTES`

### Rodando localmente com Maven

Antes de iniciar a aplicação, garanta que PostgreSQL e Redis estejam disponíveis com os valores esperados.

Subir a aplicação:

```bash
./mvnw spring-boot:run
```

Ou gerar o artefato e executar:

```bash
./mvnw clean package -Dmaven.test.skip=true
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Rodando com Docker Compose

Subida padrão:

```bash
docker compose up --build
```

Se você já tiver serviços locais ocupando as portas padrão, pode usar portas alternativas:

```bash
APP_PORT=18080 POSTGRES_PORT=15432 REDIS_PORT_PUBLISHED=16379 docker compose up --build
```

### Portas principais

- Aplicação: `8080`
- PostgreSQL: `5432`
- Redis: `6379`

Com portas alternativas no Compose:

- Aplicação: `18080`
- PostgreSQL: `15432`
- Redis: `16379`

## Testes

### Rodar a suíte completa

```bash
./mvnw test
```

### Rodar apenas os testes de integração de use case

```bash
./mvnw -Dtest=CreateVehicleTest,DeleteVehicleTest,PatchVehicleTest,RetrieveVehiclesByBrandReportTest,RetrieveVehicleTest,RetrieveVehicleByFiltersTest,RetrieveVehicleByIdTest,UpdateVehicleTest test
```

### Rodar apenas os testes de integração do controller

```bash
./mvnw -Dtest=VehicleControllerReadIT,VehicleControllerWriteIT,VehicleControllerSecurityIT test
```

### Observação importante

Os testes de integração usam Testcontainers com PostgreSQL real. Por isso, Docker precisa estar disponível durante a execução.

## Cobertura

### Gerar relatório com JaCoCo

O projeto já possui plugin configurado no `pom.xml`.

```bash
./mvnw clean verify
```

Ou explicitamente:

```bash
./mvnw org.jacoco:jacoco-maven-plugin:0.8.12:prepare-agent test org.jacoco:jacoco-maven-plugin:0.8.12:report
```

### Onde encontrar os arquivos gerados

- HTML: `target/site/jacoco/index.html`
- XML: `target/site/jacoco/jacoco.xml`
- CSV: `target/site/jacoco/jacoco.csv`

### Sonar

O projeto já possui configuração mínima para Sonar no `pom.xml`.

Execução:

```bash
./mvnw clean verify sonar:sonar -Dsonar.token=$SONAR_TOKEN
```

Observação:

- por padrão, `sonar.host.url` está configurado para `http://localhost:9000`
- o servidor SonarQube precisa estar em execução para a análise ser publicada
- a análise está limitada às classes de `controller`, `usecases` e `domain`

## Swagger

### URLs

Com a aplicação local na porta padrão:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Com o Compose usando porta alternativa:

- Swagger UI: [http://localhost:18080/swagger-ui.html](http://localhost:18080/swagger-ui.html)
- OpenAPI: [http://localhost:18080/v3/api-docs](http://localhost:18080/v3/api-docs)

### Autenticação para testar endpoints protegidos

Login:

```http
POST /api/v1/auth/login
```

Credenciais de desenvolvimento atuais:

- `user` / `user123`
- `admin` / `admin123`

Após obter o `accessToken`, informe o token Bearer no Swagger para testar os endpoints protegidos.

## Arquitetura

O projeto segue uma organização em camadas com forte orientação a casos de uso.

### Visão geral

- `application/controllers`
  - expõe os endpoints REST
  - implementa a interface documentada em `VehicleApi`
- `application/controllers/docs`
  - centraliza o contrato HTTP e a documentação OpenAPI
- `application/usecases`
  - concentra a lógica de aplicação por fluxo
  - usa o padrão `UseCase<I, O>`
- `domain`
  - contém entidade, validações e regras de negócio
- `application/persistence`
  - adapta JPA/PostgreSQL para a aplicação
- `application/integrations`
  - integrações externas, como cotação do dólar
- `configurations`
  - segurança, OpenAPI, paginação, cache e infraestrutura

### Padrão de Use Case

Cada fluxo tem seu próprio caso de uso, por exemplo:

- criação
- busca por ID
- busca por filtros
- relatório por marca
- atualização completa
- atualização parcial
- remoção lógica

O contrato base é simples:

```java
public interface UseCase<I, O> {
    O execute(I input);
}
```

### Organização dos endpoints

O `VehicleController` recebe a requisição HTTP, monta o input necessário e delega o processamento para o use case correspondente.

O `VehicleApi` define as anotações do contrato e a documentação Swagger/OpenAPI.

### Domínio

A entidade principal é `Vehicle`, que concentra regras como:

- criação de veículo válido
- reconstrução de entidade persistida
- atualização
- soft delete
- validação de faixa de preço

## Segurança

### Autenticação

A autenticação é feita por login com usuário e senha em:

```http
POST /api/v1/auth/login
```

Se as credenciais forem válidas, a aplicação gera um JWT assinado com HS256.

### JWT

O token é gerado pelo `JwtService` e inclui:

- `sub` com o username
- `roles` com os papéis do usuário
- data de emissão
- data de expiração

### Perfis disponíveis

Atualmente a aplicação usa usuários em memória:

- `USER`
- `ADMIN`

### Regras de acesso

- `GET /api/v1/**`
  - acessível para `USER` e `ADMIN`
- `POST /api/v1/**`
  - acessível apenas para `ADMIN`
- `PUT /api/v1/**`
  - acessível apenas para `ADMIN`
- `PATCH /api/v1/**`
  - acessível apenas para `ADMIN`
- `DELETE /api/v1/**`
  - acessível apenas para `ADMIN`
- públicos:
  - `/api/v1/auth/login`
  - `/swagger-ui/**`
  - `/swagger-ui.html`
  - `/v3/api-docs/**`

## Dockerização da API

### Arquivos

- `Dockerfile`
  - build multi-stage
  - compila a aplicação com Maven
  - executa o jar em uma imagem final com JRE 21
- `.dockerignore`
  - evita enviar artefatos locais desnecessários para o build
- `docker-compose.yml`
  - sobe `app`, `postgres` e `redis`

### Serviços da stack

- `app`
  - aplicação Spring Boot
- `postgres`
  - banco principal
- `redis`
  - cache usado pela aplicação

### Healthchecks e persistência

- Postgres possui `healthcheck` com `pg_isready`
- Redis possui `healthcheck` com `redis-cli ping`
- Postgres usa volume persistente:
  - `postgres-data`

### Comando recomendado

```bash
docker compose up --build
```

Se necessário, use portas alternativas:

```bash
APP_PORT=18080 POSTGRES_PORT=15432 REDIS_PORT_PUBLISHED=16379 docker compose up --build
```

## Estrutura resumida do projeto

```text
src/main/java/tinnova/test/com/example/demo
├── application
│   ├── controllers
│   ├── integrations
│   ├── persistence
│   └── usecases
├── configurations
└── domain
```

## Observações finais

- A aplicação usa `spring.jpa.hibernate.ddl-auto=update`
- O Redis é parte do runtime da aplicação, não apenas de desenvolvimento
- Os testes de integração dependem de Docker por causa do Testcontainers
- O Sonar depende de um servidor SonarQube acessível
