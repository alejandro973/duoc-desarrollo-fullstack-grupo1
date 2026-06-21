package com.gym.proveedores.controller;

import com.gym.proveedores.dto.ProveedorRequestDto;
import com.gym.proveedores.dto.ProveedorResponseDto;
import com.gym.proveedores.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
@Tag(name = "Proveedor",description = "Operaciones relacionadas a los Proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

  
    @GetMapping
    @Operation(summary = "Listar proveedores",description = "Permite listar todos los proveedores existentes")
    public ResponseEntity<List<ProveedorResponseDto>> listarTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id",description  = "Buscar proveedor por id")
    public ResponseEntity<ProveedorResponseDto> buscarPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @Operation(summary="Crear proveedor",description = "Permite crear un proveedor ")
    public ResponseEntity<ProveedorResponseDto> crear(@Valid @RequestBody ProveedorRequestDto dto) {
        ProveedorResponseDto nuevo = proveedorService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor", description = "Permite actualizar el proveedor mediante su id y dto")
    public ResponseEntity<ProveedorResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDto dto) {
        return proveedorService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

 
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Proveedor", description = "Permite eliminar el proveedor mediante su id ")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            proveedorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}