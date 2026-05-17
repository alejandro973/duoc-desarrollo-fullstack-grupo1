package com.gym.sucursales.client;

import com.gym.sucursales.dto.EquipoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "equipamento", url = "http://localhost:8087/api/equipos")
public interface EquipoClient {

    // Usamos el endpoint que creamos en el micro de Equipamento
    @GetMapping("/sucursal/{idSucursal}")
    List<EquipoDto> obtenerEquiposPorSucursal(@PathVariable("idSucursal") Long idSucursal);
}