# Spring Boot CRUD API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/status-concluído-green)
![License](https://img.shields.io/badge/license-MIT-green)

API CRUD desenvolvida com Spring Boot e PostgreSQL para operações básicas de cadastro.

***

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

***

## Funcionalidades

- Cadastro de usuários
- Listagem de usuários
- Atualização de dados
- Remoção de registros

***

## Objetivo

Projeto desenvolvido para praticar construção de APIs REST utilizando Spring Boot, arquitetura em camadas e integração com banco de dados relacional.

***

## Endpoints

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/usuarios` | Lista todos os usuários |
| POST | `/usuarios` | Cria um novo usuário |
| PUT | `/usuarios/{id}` | Atualiza um usuário existente |
| DELETE | `/usuarios/{id}` | Remove um usuário por ID |

### Exemplo de Body para Criação (POST):
```json
{
  "nome": "Fulano de Tal",
  "email": "fulano@email.com"
}

## Como Executar o Projeto

### Pré-requisitos
* Java 21
* PostgreSQL 15+
* Maven 3.x

### 1. Clonar o repositório
```bash
git clone [https://github.com/claudiodeveloper-github/crudapi-springboot.git](https://github.com/claudiodeveloper-github/crudapi-springboot.git)
cd crudapi-springboot

2. Configurar o banco de dados
Crie um banco de dados no seu PostgreSQL chamado crud_db e ajuste o arquivo src/main/resources/application.properties com suas credenciais locais:

spring.datasource.url=jdbc:postgresql://localhost:5432/crud_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

3. Executar a aplicação

./mvnw spring-boot:run

Arquitetura
O projeto segue arquitetura em camadas:

Controller: Exposição dos endpoints da API.

Service: Concentração das regras de negócio.

Repository: Comunicação e consultas ao banco de dados com Spring Data JPA.

Model: Representação das entidades e mapeamento relacional.

Melhorias Futuras
Validação de dados

Tratamento global de exceções

Documentação Swagger/OpenAPI

Testes unitários

Docker

Licença
Este projeto está sob a licença MIT.

