package com.ufpb.meuguiaapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meu Guia API")
                        .version("v1")
                        .description("API desenvolvida para servir de backend para a aplicação Meu Guia PB")
                        .termsOfService("https://github.com/Israelpsilva8246")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Israelpsilva8246")));
    }
}

//http://localhost:8080/swagger-ui/index.html#/
