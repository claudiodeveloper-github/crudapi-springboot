# Spring Boot CRUD API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/status-concluído-green)
![License](https://img.shields.io/badge/license-MIT-green)

> API CRUD desenvolvida com Spring Boot e PostgreSQL para operações básicas de cadastro de usuários.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Funcionalidades](#-funcionalidades)
- [Endpoints](#-endpoints)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Melhorias Futuras](#-melhorias-futuras)
- [Licença](#-licença)

---

## 💡 Sobre o Projeto

Projeto desenvolvido para praticar a construção de APIs REST utilizando **Spring Boot**, seguindo a arquitetura em camadas (Controller → Service → Repository → Model) e integração com banco de dados relacional **PostgreSQL**.

O objetivo principal é consolidar boas práticas no desenvolvimento de APIs RESTful, incluindo separação de responsabilidades, uso do Spring Data JPA e exposição de endpoints padronizados.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.x | Framework base da aplicação |
| Spring Data JPA | — | Abstração do acesso ao banco de dados |
| PostgreSQL | 15+ | Banco de dados relacional |
| Maven | 3.x | Gerenciamento de dependências e build |

---

## 🏗 Arquitetura

O projeto segue a **arquitetura em camadas**, garantindo separação clara de responsabilidades:

```
src/main/java/
└── com.example.crudapi/
    ├── controller/      → Exposição dos endpoints REST
    ├── service/         → Regras de negócio da aplicação
    ├── repository/      → Comunicação com o banco de dados (Spring Data JPA)
    └── model/           → Entidades e mapeamento relacional (JPA/Hibernate)
```

### Fluxo de uma requisição

```
Cliente HTTP
    │
    ▼
Controller   ← recebe e valida a requisição HTTP
    │
    ▼
Service      ← processa regras de negócio
    │
    ▼
Repository   ← executa operações no banco de dados
    │
    ▼
PostgreSQL   ← persistência dos dados
```

---

## ✅ Funcionalidades

- [x] Cadastro de usuários
- [x] Listagem de todos os usuários
- [x] Atualização de dados de um usuário existente
- [x] Remoção de usuários por ID

---

## 🔗 Endpoints

Base URL: `http://localhost:8080`

| Método | Endpoint | Descrição | Status HTTP |
|:---:|:---|:---|:---:|
| `GET` | `/usuarios` | Lista todos os usuários | `200 OK` |
| `POST` | `/usuarios` | Cria um novo usuário | `201 Created` |
| `PUT` | `/usuarios/{id}` | Atualiza um usuário existente | `200 OK` |
| `DELETE` | `/usuarios/{id}` | Remove um usuário por ID | `204 No Content` |

---

### Exemplos de Requisições

#### ➕ Criar usuário — `POST /usuarios`

**Request Body:**
```json
{
  "nome": "Fulano de Tal",
  "email": "fulano@email.com"
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "email": "fulano@email.com"
}
```

---

#### 📋 Listar usuários — `GET /usuarios`

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "nome": "Fulano de Tal",
    "email": "fulano@email.com"
  },
  {
    "id": 2,
    "nome": "Ciclana Silva",
    "email": "ciclana@email.com"
  }
]
```

---

#### ✏️ Atualizar usuário — `PUT /usuarios/{id}`

**Request Body:**
```json
{
  "nome": "Fulano Atualizado",
  "email": "fulano.novo@email.com"
}
```

**Response `200 OK`:**
```json
{
  "id": 1,
  "nome": "Fulano Atualizado",
  "email": "fulano.novo@email.com"
}
```

---

#### 🗑️ Remover usuário — `DELETE /usuarios/{id}`

**Response:** `204 No Content`

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- [Java 21](https://adoptium.net/)
- [PostgreSQL 15+](https://www.postgresql.org/download/)
- [Maven 3.x](https://maven.apache.org/download.cgi) *(ou utilize o wrapper `./mvnw` incluso no projeto)*
- [Git](https://git-scm.com/)

---

### 1. Clonar o repositório

```bash
git clone https://github.com/claudiodeveloper-github/crudapi-springboot.git
cd crudapi-springboot
```

### 2. Configurar o banco de dados

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE crud_db;
```

Edite o arquivo `src/main/resources/application.properties` com suas credenciais:

```properties
# Datasource
spring.datasource.url=jdbc:postgresql://localhost:5432/crud_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

> ⚠️ **Atenção:** Nunca suba credenciais reais para o repositório. Utilize variáveis de ambiente em produção.

### 3. Executar a aplicação

Com o Maven Wrapper (recomendado):

```bash
./mvnw spring-boot:run
```

Ou com Maven instalado globalmente:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### 4. Testar os endpoints

Você pode testar a API com ferramentas como:

- [Postman](https://www.postman.com/)
- [Insomnia](https://insomnia.rest/)
- `curl` no terminal:

```bash
# Listar usuários
curl -X GET http://localhost:8080/usuarios

# Criar usuário
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome": "Fulano de Tal", "email": "fulano@email.com"}'
```

---

## 🔮 Melhorias Futuras

- [ ] Validação de dados com `Bean Validation` (`@NotBlank`, `@Email`, etc.)
- [ ] Tratamento global de exceções com `@ControllerAdvice`
- [ ] Documentação automática com **Swagger / OpenAPI**
- [ ] Testes unitários e de integração com **JUnit 5** e **Mockito**
- [ ] Containerização com **Docker** e **Docker Compose**
- [ ] Paginação e ordenação nos endpoints de listagem
- [ ] Autenticação e autorização com **Spring Security + JWT**

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<div align="center">
  Desenvolvido por <a href="https://github.com/claudiodeveloper-github">claudiodeveloper-github</a>
</div>
