#  crudapi-springboot

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 4.0.6"/>
  <img src="https://img.shields.io/badge/PostgreSQL-18.3-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL 18.3"/>
  <img src="https://img.shields.io/badge/Swagger-OpenAPI_3.1-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger OpenAPI 3.1"/>
  <img src="https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven 3.x"/>
</p>

API RESTful completa para o gerenciamento de um catálogo de produtos, construída com Java 21 e Spring Boot 4. Implementa todas as operações de CRUD via endpoints HTTP padronizados, com persistência em PostgreSQL, credenciais protegidas por variáveis de ambiente e documentação interativa via Swagger UI. Projeto estruturado em arquitetura em camadas, demonstrando separação clara de responsabilidades e boas práticas de engenharia de software.

---

##  Tecnologias Utilizadas

| Tecnologia           | Versão                | Finalidade no Projeto                                     |
|----------------------|-----------------------|-----------------------------------------------------------|
| Java                 | 21 (Oracle OpenJDK)   | Linguagem principal da aplicação                          |
| Spring Boot          | 4.0.6                 | Framework base: IoC, auto-configuração, servidor embutido |
| Spring Data JPA      | (gerenciado pelo BOM) | Abstração do acesso a dados via repositórios              |
| Hibernate            | 7.2.12.Final          | Implementação JPA; geração e execução de SQL              |
| PostgreSQL           | 18.3                  | Banco de dados relacional para persistência               |
| springdoc-openapi    | 2.8.9                 | Geração automática da documentação OpenAPI 3.1 + Swagger UI |
| Maven                | 3.x                   | Gerenciamento de dependências e ciclo de build            |

---

##  Documentação da API

A documentação interativa é gerada automaticamente pelo **springdoc-openapi** e fica disponível após iniciar a aplicação:

| Interface         | URL                                          |
|-------------------|----------------------------------------------|
| Swagger UI        | http://localhost:8080/swagger-ui/index.html  |
| OpenAPI JSON      | http://localhost:8080/v3/api-docs            |

Pelo Swagger UI é possível visualizar todos os endpoints, seus parâmetros, schemas de request/response e executar chamadas diretamente no browser — sem precisar de Postman ou curl.

---

##  Arquitetura e Fluxo de Requisição

### Estrutura de Pacotes

```text
src/
└── main/
    ├── java/
    │   └── com/claudio/crudapi/
    │       ├── config/
    │       │   └── OpenApiConfig.java       ← Configuração do título e metadados da API
    │       ├── controller/
    │       │   └── ProdutoController.java   ← Recebe requisições HTTP
    │       ├── service/
    │       │   └── ProdutoService.java      ← Regras de negócio
    │       ├── repository/
    │       │   └── ProdutoRepository.java   ← Acesso ao banco (Spring Data JPA)
    │       └── model/
    │           └── Produto.java             ← Entidade JPA (id, nome, preco)
    └── resources/
        └── application.properties           ← Configuração com ${DB_PASSWORD}
```

### Fluxo de uma Requisição HTTP

```text
[Cliente HTTP / curl / Postman / Swagger UI]
           │
           ▼  HTTP Request
   [ ProdutoController ]   ← Camada de entrada; mapeia rotas e serializa JSON
           │
           ▼  Chama método de negócio
    [ ProdutoService ]     ← Valida regras; orquestra operações
           │
           ▼  Consulta / Persiste
  [ ProdutoRepository ]    ← Interface JPA; gera SQL automaticamente
           │
           ▼  JDBC / SQL
      [ PostgreSQL ]       ← Banco relacional; tabela 'produto'
           │
           ▲  Resultado
   (resposta percorre o caminho inverso até o cliente)
```

---

##  Matriz de Endpoints — CRUD Completo

| Método   | Endpoint           | Descrição da Ação                      | Status HTTP de Sucesso |
|----------|--------------------|----------------------------------------|------------------------|
| `GET`    | `/produtos`        | Lista todos os produtos cadastrados    | `200 OK`               |
| `GET`    | `/produtos/{id}`   | Retorna um produto específico pelo ID  | `200 OK`               |
| `POST`   | `/produtos`        | Cadastra um novo produto               | `201 Created`          |
| `PUT`    | `/produtos/{id}`   | Atualiza os dados de um produto        | `200 OK`               |
| `DELETE` | `/produtos/{id}`   | Remove um produto pelo ID              | `204 No Content`       |

---

##  Payloads de Requisição e Resposta

### `POST /produtos` — Cadastrar produto

**Request Body:**
```json
{
  "nome": "Monitor LG UltraWide 29\"",
  "preco": 1499.90
}
```

**Response Body** `201 Created`:
```json
{
  "id": 1,
  "nome": "Monitor LG UltraWide 29\"",
  "preco": 1499.90
}
```

---

### `PUT /produtos/{id}` — Atualizar produto

**Request Body:**
```json
{
  "nome": "Monitor LG UltraWide 29\" (Refurbished)",
  "preco": 1199.90
}
```

**Response Body** `200 OK`:
```json
{
  "id": 1,
  "nome": "Monitor LG UltraWide 29\" (Refurbished)",
  "preco": 1199.90
}
```

---

### `GET /produtos` — Listar todos os produtos

**Response Body** `200 OK`:
```json
[
  {
    "id": 1,
    "nome": "Monitor LG UltraWide 29\" (Refurbished)",
    "preco": 1199.90
  },
  {
    "id": 2,
    "nome": "Teclado Mecânico Keychron K2",
    "preco": 459.00
  },
  {
    "id": 3,
    "nome": "SSD Kingston NV3 1TB M.2",
    "preco": 329.90
  }
]
```

---

##  Passo a Passo para Execução Local

### ✅ Pré-requisitos

- Java 21 instalado e configurado no `PATH`
- PostgreSQL 18.3 em execução na porta `5432`
- Maven 3.x (ou use o Maven Wrapper incluso no projeto — recomendado)

---

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/claudiodeveloper-github/crudapi-springboot.git
cd crudapi-springboot
```

---

### 2️⃣ Criar o Banco de Dados

Conecte-se ao PostgreSQL (via `psql`, DBeaver ou pgAdmin) e execute:

```sql
CREATE DATABASE crudapi;
```

> O Hibernate irá criar a tabela `produto` automaticamente ao iniciar a aplicação (DDL auto).

---

### 3️⃣ ⚠️ Configurar a Variável de Ambiente `DB_PASSWORD`

O arquivo `application.properties` **não armazena a senha em texto puro**. Ele lê a credencial via `${DB_PASSWORD}`.

**No IntelliJ IDEA:**

1. Clique em **"Edit Configurations..."** (ao lado do botão ▶ Run).
2. Selecione a configuração da sua aplicação Spring Boot.
3. Localize o campo **"Environment variables"** e clique no ícone 📋.
4. Adicione a entrada:
   DB_PASSWORD=sua_senha_do_postgres

6. Clique em **OK** → **Apply** → **OK** e execute com ▶ Run.

**No terminal (PowerShell):**

```powershell
$env:DB_PASSWORD="sua_senha_do_postgres"
./mvnw spring-boot:run
```

**No terminal (Linux / macOS):**

```bash
export DB_PASSWORD=sua_senha_do_postgres
./mvnw spring-boot:run
```

> Sem essa configuração, o Spring Boot lançará `PSQLException: FATAL: password authentication failed`.

---

### 4️⃣ Inicializar a Aplicação

**Linux / macOS:**
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em: **`http://localhost:8080`**

A documentação Swagger estará disponível em: **`http://localhost:8080/swagger-ui/index.html`**

---

##  Guia de Testes Rápidos com `curl`

### Listar todos os produtos
```bash
curl -X GET http://localhost:8080/produtos
```

### Buscar produto por ID
```bash
curl -X GET http://localhost:8080/produtos/1
```

### Cadastrar novo produto
```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Headset HyperX Cloud II\", \"preco\": 399.90}"
```

> **Windows (Prompt de Comando):**
> ```cmd
> curl -X POST http://localhost:8080/produtos -H "Content-Type: application/json" -d "{\"nome\": \"Headset HyperX Cloud II\", \"preco\": 399.90}"
> ```

### Atualizar produto existente
```bash
curl -X PUT http://localhost:8080/produtos/1 \
  -H "Content-Type: application/json" \
  -d "{\"nome\": \"Headset HyperX Cloud II Wireless\", \"preco\": 549.90}"
```

### Deletar produto
```bash
curl -X DELETE http://localhost:8080/produtos/1
```

> Resposta esperada: `204 No Content` (sem body).

---

##  Segurança de Credenciais

O `application.properties` utiliza interpolação de variável de ambiente para a senha do banco:

```properties
spring.datasource.password=${DB_PASSWORD}
```

Isso garante que **nenhuma credencial é exposta no código-fonte ou no histórico do Git**. A senha existe apenas em tempo de execução, via configuração local da IDE ou variáveis do sistema operacional.

---

## 👤 Autor

<div align="center">

<img src="https://github.com/claudiodeveloper-github.png" width="100" style="border-radius: 50%;" />

**Cláudio G. S. Castro**
*Java Backend Developer em Formação*

Desenvolvedor Backend em formação com foco em Java e Spring Boot.
Construindo APIs robustas com boas práticas, aprendendo continuamente e enfrentando desafios reais.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/claudio-g-s-castro)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/claudiodeveloper-github)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:claudiodeveloper007@gmail.com)

</div>

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT** — veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<div align="center">

*Feito com ☕ Java e muito aprendizado por **Cláudio G. S. Castro***

⭐ Se este projeto te ajudou ou te inspirou, deixa uma estrela no repositório!

</div>

