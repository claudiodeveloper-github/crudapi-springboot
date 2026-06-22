package com.claudio.crudapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD API - Catálogo de Produtos")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de produtos. " +
                                "Construída com Java 21 e Spring Boot 4.")
                        .contact(new Contact()
                                .name("Cláudio G. S. Castro")
                                .url("https://github.com/claudiodeveloper-github")));
    }
}