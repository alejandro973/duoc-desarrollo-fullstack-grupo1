package com.gym.sucursales.config; // Ajustado al entorno de tu VS Code

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le indica a Spring que aplique este ajuste al arrancar el contexto
public class SwaggerConfig {

    @Bean // Registra el objeto OpenAPI administrado por el framework
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gym API 2026 - Microservicio de Sucursales") // Título modular
                        .version("1.0") // Versión del artefacto [cite: 438]
                        .description("Documentación oficial de endpoints REST para la administración de sedes físicas, horarios de atención, aforo máximo y localización geográfica de los gimnasios (Duoc UC)."));
    }
}