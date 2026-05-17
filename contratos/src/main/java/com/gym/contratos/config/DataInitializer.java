package com.gym.contratos.config;

import com.gym.contratos.model.Contrato;
import com.gym.contratos.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ContratoRepository contratoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Validamos si la tabla de contratos ya tiene datos insertados
        if (contratoRepository.count() == 0) {
            
            System.out.println("--> Cargando datos iniciales en la tabla contratos_externos...");

            // Contrato de prueba 1 (Asume que ya creaste el Proveedor 1 y Mantenimiento 1)
            Contrato c1 = new Contrato();
            c1.setIdProveedor(1L);
            c1.setIdMantenimiento(1L);
            c1.setFechaFirma(LocalDate.now().minusDays(5)); // Firmado hace 5 días
            c1.setMontoFinal(120000.0);
            contratoRepository.save(c1);

            // Contrato de prueba 2 (Asume que existen el Proveedor 2 y Mantenimiento 2)
            Contrato c2 = new Contrato();
            c2.setIdProveedor(2L);
            c2.setIdMantenimiento(2L);
            c2.setFechaFirma(LocalDate.now()); // Firmado hoy
            c2.setMontoFinal(450000.0);
            contratoRepository.save(c2);

            System.out.println("--> ¡Datos iniciales cargados con éxito!");
        } else {
            System.out.println("--> La tabla contratos_externos ya contiene datos, omitiendo inicialización.");
        }
    }
}