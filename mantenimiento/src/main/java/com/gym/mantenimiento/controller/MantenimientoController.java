package com.gym.mantenimiento.controller;

import com.gym.mantenimiento.dto.MantenimientoRequestDto;
import com.gym.mantenimiento.dto.MantenimientoResponseDto;
import com.gym.mantenimiento.service.MantenimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    
    @GetMapping
    public ResponseEntity<List<MantenimientoResponseDto>> listarTodos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodos());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoResponseDto> buscarPorId(@PathVariable Long id) {
        return mantenimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MantenimientoResponseDto> crear(@Valid @RequestBody MantenimientoRequestDto dto) {
        MantenimientoResponseDto nuevo = mantenimientoService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody MantenimientoRequestDto dto) {
        return mantenimientoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            mantenimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}