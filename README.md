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

