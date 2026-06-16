package com.gym.mantenimiento.controller;

import com.gym.mantenimiento.dto.MantenimientoRequestDto;
import com.gym.mantenimiento.dto.MantenimientoResponseDto;
import com.gym.mantenimiento.service.MantenimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
@Tag(name = "Mantenimiento",description = "Operaciones relacionadas al mantenimiento de los equipos/instalaciones")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    
    @GetMapping
    @Operation(summary = "Listar mantenimientos",description = "Lista todos los mantenimientos programados")
    public ResponseEntity<List<MantenimientoResponseDto>> listarTodos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodos());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar mantenimiento por id",description = "Permite buscar el mantenimiento por su id asociado")
    public ResponseEntity<MantenimientoResponseDto> buscarPorId(@PathVariable Long id) {
        return mantenimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear Mantenimiento",description = "Permite crear un mantenimiento")
    public ResponseEntity<MantenimientoResponseDto> crear(@Valid @RequestBody MantenimientoRequestDto dto) {
        MantenimientoResponseDto nuevo = mantenimientoService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar mantenimiento",description = "Permite actualizar el mantenimiento por medio de su id y un dto")
    public ResponseEntity<MantenimientoResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody MantenimientoRequestDto dto) {
        return mantenimientoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Mantemiento",description = "Permite eliminar un mantenimiento por su id")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            mantenimientoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}