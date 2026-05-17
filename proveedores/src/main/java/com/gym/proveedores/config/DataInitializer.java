package com.gym.proveedores.config;

import com.gym.proveedores.model.Proveedor;
import com.gym.proveedores.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProveedorRepository repository;

    @Override
    public void run(String... args) {
        
        if (repository.count() > 0) {
            log.info(">>> La base de datos de Proveedores ya tiene información. Saltando carga.");
            return;
        }

        log.info(">>> Iniciando carga de proveedores de prueba...");

        repository.save(new Proveedor(null, "Distribuidora GymMax", "Juan Pérez", "+56911112222", "Equipamiento"));
        repository.save(new Proveedor(null, "Alfa Servicio Técnico", "María Lorca", "+56933334444", "Mantenimiento"));
        repository.save(new Proveedor(null, "NutriSport Mayorista", "Carlos Soto", "+56955556666", "Suplementos"));
        repository.save(new Proveedor(null, "Glow Clean Chile", "Ana Williams", "+56977778888", "Aseo e Higiene"));

        log.info(">>> ¡Carga de Proveedores finalizada con éxito!");
    }
}