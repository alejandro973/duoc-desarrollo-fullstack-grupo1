package com.gym.planes.config;

import com.gym.planes.model.Plan;
import com.gym.planes.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PlanRepository repository;

    @Override
    public void run(String... args) {
       
        if (repository.count() > 0) {
            log.info(">>> Base de datos de Planes ya tiene información. Saltando inicialización.");
            return;
        }

        log.info(">>> Cargando planes de prueba iniciales emparejados con socios...");

       
        
        repository.save(new Plan(null, "Plan Mensual Pesas", "Activo", new Date(126, 11, 31), "12345678-9"));

     
        repository.save(new Plan(null, "Plan Anual Full", "Activo", new Date(127, 5, 20), "11222333-4"));

     
        repository.save(new Plan(null, "Plan Trimestral", "Inactivo", new Date(124, 2, 10), "15555666-k"));

       
        repository.save(new Plan(null, "Plan Pilates", "Activo", new Date(126, 8, 15), "17888999-2"));

      
        repository.save(new Plan(null, "Plan Natación", "Activo", new Date(126, 3, 22), "19222333-k"));

     
        repository.save(new Plan(null, "Plan Yoga", "Activo", new Date(126, 0, 1), "14777888-5"));

      
        repository.save(new Plan(null, "Plan Estudiante", "Activo", new Date(126, 6, 30), "20111222-7"));

       
        repository.save(new Plan(null, "Plan Crossfit", "Activo", new Date(126, 10, 15), "16444555-0"));

        log.info(">>> Planes iniciales cargados correctamente y asociados por RUN.");
    }
}