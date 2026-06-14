# 🗄️ crudapi-springboot

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 4.0.6"/>
  <img src="https://img.shields.io/badge/PostgreSQL-18.3-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL 18.3"/>
  <img src="https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven 3.x"/>
</p>

API RESTful completa para o gerenciamento de um catálogo de produtos, construída com Java 21 e Spring Boot 4. Implementa todas as operações de CRUD via endpoints HTTP padronizados, com persistência em PostgreSQL e credenciais protegidas por variáveis de ambiente. Projeto estruturado em arquitetura em camadas, demonstrando separação clara de responsabilidades e boas práticas de engenharia de software.

---

<!-- TODO: adicionar tratamento global de exceções com @ControllerAdvice (#1) -->

## 📦 Tecnologias Utilizadas

| Tecnologia           | Versão              | Finalidade no Projeto                                     |
|----------------------|---------------------|-----------------------------------------------------------|
| Java                 | 21 (Oracle OpenJDK) | Linguagem principal da aplicação                          |
| Spring Boot          | 4.0.6               | Framework base: IoC, auto-configuração, servidor embutido |
| Spring Data JPA      | (gerenciado pelo BOM) | Abstração do acesso a dados via repositórios              |
| Hibernate            | 7.2.12.Final        | Implementação JPA; geração e execução de SQL              |
| PostgreSQL           | 18.3                | Banco de dados relacional para persistência               |
| Maven                | 3.x                 | Gerenciamento de dependências e ciclo de build            |

---

## 🏗️ Arquitetura e Fluxo de Requisição

### Estrutura de Pacotes

```text
src/
└── main/
    ├── java/
    │   └── com/seuprojeto/crudapi/
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
[Cliente HTTP / curl / Postman]
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

## 🔌 Matriz de Endpoints — CRUD Completo

| Método   | Endpoint           | Descrição da Ação                      | Status HTTP de Sucesso |
|----------|--------------------|----------------------------------------|------------------------|
| `GET`    | `/produtos`        | Lista todos os produtos cadastrados    | `200 OK`               |
| `GET`    | `/produtos/{id}`   | Retorna um produto específico pelo ID  | `200 OK`               |
| `POST`   | `/produtos`        | Cadastra um novo produto               | `201 Created`          |
| `PUT`    | `/produtos/{id}`   | Atualiza os dados de um produto        | `200 OK`               |
| `DELETE` | `/produtos/{id}`   | Remove um produto pelo ID              | `204 No Content`       |

---
<!-- TODO: documentar endpoints com Swagger/OpenAPI (#3) -->

## 📋 Payloads de Requisição e Resposta

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
<!-- TODO: implementar paginação no GET /produtos (#5) -->
---

### `PUT /produtos/{id}` — Atualizar produto

**Request Body:**
```json
{
  "nome": "Monitor LG UltraWide 29\" (Refurbished)",
  "preco": 1199.90
}
```
<!-- TODO: criar testes de integração com @SpringBootTest (#9) -->

**Response Body** `200 OK`:
```json
{
  "id": 1,
  "nome": "Monitor LG UltraWide 29\" (Refurbished)",
  "preco": 1199.90
}
```
<!-- TODO: adicionar validações com Bean Validation nos DTOs (#7) -->
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

## 🚀 Passo a Passo para Execução Local

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

### 3️⃣ ⚠️ Configurar a Variável de Ambiente `DB_PASSWORD` no IntelliJ IDEA

O arquivo `application.properties` **não armazena a senha em texto puro**. Ele lê a credencial via `${DB_PASSWORD}`. Para que a aplicação inicialize sem erros de autenticação com o PostgreSQL, siga os passos abaixo:

1. Na barra superior do IntelliJ, clique em **"Edit Configurations..."** (ao lado do botão ▶ Run).
2. No painel que abrir, selecione a configuração da sua aplicação Spring Boot.
3. Localize o campo **"Environment variables"** (na seção *Spring Boot* ou *JVM*).
4. Clique no ícone 📋 à direita do campo para abrir o editor.
5. Adicione a entrada:
   ```
   DB_PASSWORD=sua_senha_do_postgres
   ```
6. Clique em **OK** → **Apply** → **OK**.
7. Execute a aplicação normalmente com ▶ Run.

> Sem essa configuração, o Spring Boot lançará `PSQLException: FATAL: password authentication failed` na inicialização.

---

### 4️⃣ Inicializar a Aplicação via Maven Wrapper

**Linux / macOS:**
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

**Windows (Prompt de Comando ou PowerShell):**
```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em: **`http://localhost:8080`**

---

## 🧪 Guia de Testes Rápidos com `curl`

Copie e cole os comandos abaixo diretamente no terminal. Nenhuma ferramenta adicional necessária.

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

> **Windows (Prompt de Comando):** substitua as aspas simples externas por `"` e escape as internas com `\"`:
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

## 🔒 Segurança de Credenciais

O `application.properties` utiliza interpolação de variável de ambiente para a senha do banco:

```properties
spring.datasource.password=${DB_PASSWORD}
```

Isso garante que **nenhuma credencial é exposta no código-fonte ou no histórico do Git**. A senha existe apenas em tempo de execução, via configuração local da IDE ou variáveis do sistema operacional.

---

<p align="center">
  Desenvolvido por <strong>Cláudio</strong> · <a href="https://github.com/claudiodeveloper-github">@claudiodeveloper-github</a>
</p>
