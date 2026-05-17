package com.gym.sucursales.controller;

import com.gym.sucursales.dto.SucursalRequestDto;
import com.gym.sucursales.dto.SucursalResponseDto;
import com.gym.sucursales.service.SucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    // 1. Obtener todas las sucursales con sus equipos
    @GetMapping
    public ResponseEntity<List<SucursalResponseDto>> listarTodas() {
        return ResponseEntity.ok(sucursalService.obtenerTodas());
    }

    // 2. Obtener una sucursal específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponseDto> obtenerPorId(@PathVariable Long id) {
        return sucursalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva sucursal
    @PostMapping
    public ResponseEntity<SucursalResponseDto> crear(@Valid @RequestBody SucursalRequestDto dto) {
        SucursalResponseDto nueva = sucursalService.guardar(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }
}