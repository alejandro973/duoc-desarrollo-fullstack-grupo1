package com.gym.mantenimiento.config; // Ajustado al paquete de mantenimiento

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le indica a Spring que aplique este ajuste al arrancar el contexto de Mantenimiento
public class SwaggerConfig {

    @Bean // Registra el objeto OpenAPI administrado por el framework
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gym API 2026 - Microservicio de Mantenimiento") // Título modular
                        .version("1.0") // Versión del artefacto
                        .description("Documentación oficial de endpoints REST para la gestión de órdenes de trabajo, reparaciones de infraestructura y estados de mantenimiento preventivo/correctivo."));
    }
}