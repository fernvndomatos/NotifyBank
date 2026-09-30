package com.github.fernvndomatos.NotifyBank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notifyBankOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NotifyBank API")
                        .description("Bank transaction monitoring API with async notifications via RabbitMQ")
                        .version("v1.0"));
    }
}