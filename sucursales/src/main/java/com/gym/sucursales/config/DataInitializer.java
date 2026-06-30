package com.gym.sucursales.config;

import com.gym.sucursales.model.Sucursal;
import com.gym.sucursales.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) throws Exception {
        // Validamos si la tabla ya tiene datos para evitar duplicados
        if (sucursalRepository.count() == 0) {
            System.out.println("--> Cargando sucursales de prueba iniciales...");

            // Sede 1 (Calza con el idSucursal 1 que usamos en Equipamiento)
            Sucursal s1 = new Sucursal();
            s1.setNombre("Gym Central Sede Norte");
            s1.setDireccion("Av. Vitacura 1234");
            s1.setCiudad("Santiago");
            sucursalRepository.save(s1);

            // Sede 2
            Sucursal s2 = new Sucursal();
            s2.setNombre("Gym Sede Providencia");
            s2.setDireccion("Av. Nueva Providencia 567");
            s2.setCiudad("Santiago");
            sucursalRepository.save(s2);

            System.out.println("--> ¡Sucursales iniciales cargadas con éxito en Laragon!");
        } else {
            System.out.println("--> La tabla de sucursales ya contiene registros, omitiendo inicialización.");
        }
    }
}