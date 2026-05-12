# 📅 ScheduleFlow API

API REST para gerenciamento de agendamentos entre clientes e profissionais, desenvolvida com **Java 17** e **Spring Boot 3.3.2**.



## 🚀 Tecnologias

- Java 17+
- Spring Boot 3.3.2
  - Spring Web
  - Spring Data JPA
- Bean Validation (Jakarta Validation)
- Redis (cache distribuído via Spring Data Redis)
- Springdoc OpenAPI / Swagger UI
- Lombok
- Maven
- MySQL



## 🎯 Objetivo

Este projeto foi desenvolvido para demonstrar:

- Construção de APIs REST robustas.
- Aplicação de Clean Architecture na prática.
- Organização de código orientada a domínio.
- Padronização de respostas e erros.
- Baixo acoplamento entre camadas.


## 📌 Visão Geral

O **ScheduleFlow** permite cadastrar profissionais e clientes, e criar agendamentos entre eles.

Antes de persistir qualquer agendamento, o sistema aplica um pipeline de validações:

- Verificação de regras de negócio (dias, horários e antecedência mínima).
- Verificação de conflitos de horário do profissional.
- Cache distribuído com Redis para otimizar leituras frequentes.



## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**, separando responsabilidades em três camadas bem definidas, com dependências sempre apontando para dentro (em direção ao domínio).

```text
src/main/java/dev/zerphyis/schedule/
│
├── domain/                              # Regras de negócio puras — sem dependências externas
│   ├── entities/
│   │   ├── Appointment.java
│   │   ├── Client.java
│   │   └── Professional.java
│   │
│   └── repositories/                    # Contratos de repositório (interfaces)
│       ├── AppointmentRepository.java
│       ├── ClientRepository.java
│       └── ProfessionalRepository.java
│
├── application/                         # Orquestração dos casos de uso
│   ├── interfaceCases/                  # Contratos (interfaces) dos Use Cases
│   │   ├── Appointment/
│   │   ├── Client/
│   │   └── Professional/
│   │
│   ├── useCases/                        # Implementações dos Use Cases + Services (facades)
│   │   ├── Appointment/
│   │   ├── Client/
│   │   └── Professional/
│   │
│   └── exception/                       # Exceções de domínio e aplicação
│       ├── appointmentException/
│       ├── clientException/
│       └── professionalException/
│
└── infra/                               # Detalhes técnicos e frameworks
    ├── controller/                      # Controllers REST + Handler global de exceções
    ├── config/                          # Beans dos Use Cases + configuração Redis
    ├── mappers/                         # Conversão entre domínio e DTOs
    │   └── dtos/
    │
    └── persistence/
        ├── entities/                    # Entidades JPA
        ├── repository/                  # Interfaces Spring Data JPA
        └── adapters/                    # Implementação dos repositórios de domínio
```


##  🔄 Fluxo de uma Requisição

```text
HTTP Request
     │
     ▼
Controller (infra)
     │  valida o DTO com Bean Validation (@Valid)
     ▼
Service — facade (application)
     │  aplica @Cacheable / @CacheEvict via Redis
     ▼
Use Case (application)
     │  executa as regras de negócio
     ▼
Repository Interface (domain)
     │
     ▼
Repository Adapter (infra/persistence)
     │  converte entre domínio e JPA
     ▼
Spring Data JPA
     │
     ▼
MySQL
```

## 🔁 Inversão de Dependência na Persistência

O domínio nunca enxerga o JPA diretamente.

O `AppointmentRepositoryAdapter`, por exemplo, implementa `AppointmentRepository` (interface de domínio) e internamente utiliza `AppointmentRepositoryJpa` (Spring Data JPA).

O mesmo padrão é aplicado para:

- Client
- Professional
- Appointment


## ⚙️ Como Funciona a Criação de Agendamentos

Ao receber um `POST /appointments`, o `AppointmentService` delega ao `CreateAppointmentUseCase`, que executa três etapas:

---

## 1️⃣ ValidateScheduleUseCase — Regras de Negócio

Valida o `dateTime` recebido contra três regras:

| Regra | Detalhe |
|---|---|
| Dia da semana | Agendamentos aos domingos são bloqueados |
| Horário comercial | Apenas entre 08:00 e 17:59 |
| Antecedência mínima | Pelo menos 30 minutos à frente do horário atual |

Caso alguma regra falhe:

```text
BusinessException → 400 Bad Request
```



## 2️⃣ CheckScheduleConflictUseCase — Conflito de Horário

Consulta o banco:

```java
existsByProfessionalIdAndDateTime(professionalId, dateTime)
```

Caso já exista um agendamento no mesmo horário:

```text
ConflictException → 409 Conflict
```

## 3️⃣ Persistência

O sistema:

- Busca o Professional pelo ID.
- Busca o Client pelo ID.
- Cria a entidade Appointment.
- Persiste no banco.
- Invalida o cache do profissional via `@CacheEvict`.



## 🗑️ Como Funciona o Cancelamento de Agendamentos

O `CancelAppointmentUseCase` aplica uma regra antes de remover:

1. Busca o agendamento pelo ID.
2. Verifica se o cancelamento possui pelo menos 24 horas de antecedência.
3. Remove o agendamento.
4. Limpa o cache.

Caso a regra falhe:

```text
BusinessException → cancelamento permitido apenas com 24h de antecedência
```

## 💾 Cache com Redis

Os Services funcionam como facades e gerenciam cache automaticamente.

| Service | Operação | Cache |
|---|---|---|
| AppointmentService.listByProfessional | GET | `@Cacheable("appointments")` |
| AppointmentService.create | POST | `@CacheEvict("appointments")` |
| AppointmentService.cancel | DELETE | `@CacheEvict(allEntries=true)` |
| ClientService.findById | GET | `@Cacheable("clients")` |
| ClientService.findAll | GET | `@Cacheable("clientsList")` |
| ClientService.create/update/delete | Mutação | `@CacheEvict` |
| ProfessionalService.findById | GET | `@Cacheable("professionals")` |
| ProfessionalService.findAll | GET | `@Cacheable("professionalsList")` |
| ProfessionalService.create/update/delete | Mutação | `@CacheEvict` |

### Configuração

- TTL padrão: **10 minutos**
- Valores nulos nunca são cacheados.

## 🚀 Endpoints

## 📋 Agendamentos — `/appointments`



### POST `/appointments`

Cria um agendamento.

#### Body

```json
{
  "professionalId": 1,
  "clientId": 2,
  "dateTime": "25/12/2025 10:00"
}
```

### Regras

- Formato obrigatório: `dd/MM/yyyy HH:mm`
- Deve ser uma data futura.

#### Response — `201 Created`

```json
{
  "id": 1,
  "professionalName": "Dr. João",
  "clientName": "Maria Silva",
  "dateTime": "25/12/2025 10:00"
}
```

---

### GET `/appointments/professional/{professionalId}`

Lista os agendamentos do profissional.

#### Response — `200 OK`

```json
[
  {
    "id": 1,
    "professionalName": "Dr. João",
    "clientName": "Maria Silva",
    "dateTime": "25/12/2025 10:00"
  }
]
```

#### Observações

- Ordenado por `dateTime`.
- Resposta cacheada por profissional.

---

### DELETE `/appointments/{appointmentId}`

Cancela um agendamento.

#### Response — `204 No Content`

### Regra

Cancelamento permitido apenas com 24 horas de antecedência.

## 👤 Clientes — `/clients`

---

## POST `/clients`

Cria um cliente.

### Body

```json
{
  "nome": "Maria Silva",
  "cpf": "123.456.789-00",
  "telefone": "(85) 99999-9999"
}
```

#### Response — `201 Created`

```json
{
  "id": 1,
  "nome": "Maria Silva",
  "cpf": "123.456.789-00",
  "telefone": "(85) 99999-9999"
}
```

#### Regras

- CPF duplicado retorna `409 Conflict`.

### GET `/clients`

Lista todos os clientes.

#### Response — `200 OK`

Lista de `ClientResponseDTO`.

#### Observações

- Resposta cacheada.


### GET `/clients/{id}`

Busca cliente por ID.

#### Response — `200 OK`

```json
{
  "id": 1,
  "nome": "Maria Silva",
  "cpf": "123.456.789-00",
  "telefone": "(85) 99999-9999"
}
```

### PUT `/clients/{id}`

Atualiza um cliente.

#### Response — `200 OK`

Cliente atualizado.



### DELETE `/clients/{id}`

Remove um cliente.

#### Response — `204 No Content`



##  🩺 Profissionais — `/api/professionals`

### POST `/api/professionals`

Cria um profissional.

#### Body

```json
{
  "nome": "Dr. João",
  "especialidade": "Cardiologia",
  "email": "joao@clinica.com"
}
```

#### Response — `201 Created`

```json
{
  "id": 1,
  "nome": "Dr. João",
  "especialidade": "Cardiologia",
  "email": "joao@clinica.com"
}
```


### GET `/api/professionals`

Lista todos os profissionais.

#### Response — `200 OK`

Lista de `ProfessionalResponseDTO`.


### GET `/api/professionals/{id}`

Busca profissional por ID.

#### Response — `200 OK`

```json
{
  "id": 1,
  "nome": "Dr. João",
  "especialidade": "Cardiologia",
  "email": "joao@clinica.com"
}
```


### PUT `/api/professionals/{id}`

Atualiza um profissional.

#### Response — `200 OK`

Profissional atualizado.


### DELETE `/api/professionals/{id}`

Remove um profissional.

#### Response — `204 No Content`


## ⚠️ Tratamento de Erros

Todas as exceções são interceptadas pelo `HandlerController` (`@RestControllerAdvice`).

#### Exemplo de resposta

```json
{
  "status": 409,
  "error": "Conflict",
  "message": "O profissional já possui agendamento em: 2025-12-25T10:00",
  "path": "/appointments",
  "timestamp": "2025-12-25T09:30:00"
}
```

---

### Tabela de Exceções

| Exceção | HTTP | Gatilho |
|---|---|---|
| ClientNotFoundException | 404 | Cliente não encontrado |
| ClientAlreadyExistsException | 409 | CPF já cadastrado |
| InvalidClientDataException | 400 | Dados inválidos |
| ProfessionalNotFoundException | 404 | Profissional não encontrado |
| DuplicateProfessionalException | 409 | Profissional duplicado |
| InvalidProfessionalDataException | 400 | Dados inválidos |
| BusinessException | 400 | Violação de regra de negócio |
| ResourceNotFoundException | 404 | Recurso não encontrado |
| ConflictException | 409 | Conflito de horário |
| MethodArgumentNotValidException | 400 | Falha no Bean Validation |
| Exception | 500 | Erro inesperado |



## 🗄️ Entidades de Domínio

### 👤 Client

| Campo | Tipo | Validação |
|---|---|---|
| id | Long | Gerado automaticamente |
| nome | String | Obrigatório |
| cpf | String | Obrigatório e único |
| telefone | String | Obrigatório |


### 🩺 Professional

| Campo | Tipo | Validação |
|---|---|---|
| id | Long | Gerado automaticamente |
| nome | String | Obrigatório |
| especialidade | String | Obrigatório |
| email | String | Obrigatório e válido |



### 📅 Appointment

| Campo | Tipo | Validação |
|---|---|---|
| id | Long | Gerado automaticamente |
| dateTime | LocalDateTime | Obrigatório e futuro |
| professional | Professional | Obrigatório |
| client | Client | Obrigatório |

## 📖 Documentação Swagger

Com a aplicação rodando:

```text
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Como Rodar

### Pré-requisitos

- Java 17+
- Maven
- MySQL
- Redis

---

### Execução

```bash
./mvnw spring-boot:run
```

---

### Testes

```bash
./mvnw test
```
