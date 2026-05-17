package com.gym.contratos.client;

import com.gym.contratos.dto.ProveedorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "proveedores", url = "http://localhost:8090/api/proveedores")
public interface ProveedorClient {
    @GetMapping("/{id}")
    ProveedorDto buscarPorId(@PathVariable("id") Long id);
}