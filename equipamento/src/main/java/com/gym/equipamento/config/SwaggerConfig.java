package com.gym.equipamento.config; // Ajustado al paquete de equipamento

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le indica a Spring que aplique este ajuste al arrancar el contexto
public class SwaggerConfig {

    @Bean 
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gym API 2026 - Microservicio de Equipamento") // Título modular
                        .version("1.0") 
                        .description("Documentación oficial de endpoints REST para el inventario, estado de máquinas, registros de mantención y disponibilidad de implementos deportivos (Duoc UC)."));
    }
}