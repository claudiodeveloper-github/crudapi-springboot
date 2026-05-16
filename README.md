# Spring Boot CRUD API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-blue)
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
|--------|----------|------------|
| GET | /usuarios | Lista usuários |
| POST | /usuarios | Cria usuário |
| PUT | /usuarios/{id} | Atualiza usuário |
| DELETE | /usuarios/{id} | Remove usuário |

***

## Arquitetura

O projeto segue arquitetura em camadas:

- Controller
- Service
- Repository
- Model

***

## Melhorias Futuras

- Validação de dados
- Tratamento global de exceções
- Documentação Swagger/OpenAPI
- Testes unitários
- Docker

***

## Licença

Este projeto está sob a licença MIT.
