package com.gym.mantenimiento.config;

import com.gym.mantenimiento.model.Mantenimiento;
import com.gym.mantenimiento.repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MantenimientoRepository repository;

    @Override
    public void run(String... args) {
        
        if (repository.count() > 0) {
            log.info(">>> La base de datos de Mantenimiento ya tiene información. Saltando carga.");
            return;
        }

        log.info(">>> Iniciando carga de órdenes de mantenimiento de prueba...");

       
        repository.save(new Mantenimiento(null, "Mantención preventiva y lubricación de trotadoras", LocalDate.of(2026, 5, 20), 45000.0, "PENDIENTE"));
        repository.save(new Mantenimiento(null, "Cambio de cables y poleas en máquina multifuncional", LocalDate.of(2026, 5, 22), 75000.0, "EN_PROCESO"));
        repository.save(new Mantenimiento(null, "Reparación del sensor de pulso en bicicleta estática N°4", LocalDate.of(2026, 5, 15), 25000.0, "COMPLETADO"));
        repository.save(new Mantenimiento(null, "Tapizado de asientos desgastados en bancos de pesas", LocalDate.of(2026, 5, 28), 60000.0, "PENDIENTE"));

        log.info(">>> ¡Carga de Mantenimiento finalizada con éxito!");
    }
}