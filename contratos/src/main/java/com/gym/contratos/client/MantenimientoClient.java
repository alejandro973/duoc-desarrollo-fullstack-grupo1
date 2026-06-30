package com.gym.contratos.client;

import com.gym.contratos.dto.MantenimientoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mantenimientos", url = "${FEIGN_URL_MANTENIMIENTOS:http://localhost:8089/api/mantenimientos}")
public interface MantenimientoClient {
    @GetMapping("/{id}")
    MantenimientoDto buscarPorId(@PathVariable("id") Long id);
}