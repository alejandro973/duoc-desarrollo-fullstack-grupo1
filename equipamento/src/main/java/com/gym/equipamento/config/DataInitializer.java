package com.gym.equipamento.config;

import com.gym.equipamento.model.Equipo;
import com.gym.equipamento.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EquipoRepository equipamientoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (equipamientoRepository.count() == 0) {
            System.out.println("--> Cargando inventario de equipamiento de prueba...");

            // Equipo 1
            Equipo e1 = new Equipo();
            e1.setNombre("Treadmill Pro-Run");
            e1.setTipo("Cardio");
            e1.setEstado("Operativo");
            e1.setIdSucursal(1L); // <-- CORRECCIÓN: Agregado para cumplir la validación @NotNull
            equipamientoRepository.save(e1);

            // Equipo 2
            Equipo e2 = new Equipo();
            e2.setNombre("Banca de Prensa Plana");
            e2.setTipo("Fuerza");
            e2.setEstado("Mantenimiento");
            e2.setIdSucursal(1L); // <-- CORRECCIÓN: Agregado para cumplir la validación @NotNull
            equipamientoRepository.save(e2);

            System.out.println("--> ¡Inventario inicial cargado con éxito en Laragon!");
        } else {
            System.out.println("--> La tabla de equipamiento ya contiene registros, omitiendo inicialización.");
        }
    }
}