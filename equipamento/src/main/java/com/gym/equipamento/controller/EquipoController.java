package com.gym.equipamento.controller;

import com.gym.equipamento.dto.EquipoRequestDto;
import com.gym.equipamento.dto.EquipoResponseDto;
import com.gym.equipamento.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "Equipamento",description = "Operaciones relacionadas con los equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Listar todos los equipos ",description = "Permite listar todos los equipos disponibles")
    public ResponseEntity<List<EquipoResponseDto>> listarTodos() {
        return ResponseEntity.ok(equipoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar equipo por id",description = "Busca un equipo por su id ")
    public ResponseEntity<EquipoResponseDto> buscarPorId(@PathVariable Long id) {
        return equipoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/sucursal/{idSucursal}")
    @Operation(summary = "Listar equipo por sucursal",description = "Listar todos los equipos disponibles por sucursal")
    public ResponseEntity<List<EquipoResponseDto>> listarPorSucursal(@PathVariable Long idSucursal) {
        List<EquipoResponseDto> equipos = equipoService.obtenerPorSucursal(idSucursal);
        return ResponseEntity.ok(equipos);
    }


    @PostMapping
    @Operation(summary = "Crear Equipo",description = "Permite crear un equipo nuevo")
    public ResponseEntity<EquipoResponseDto> crear(@Valid @RequestBody EquipoRequestDto dto) {
        EquipoResponseDto nuevo = equipoService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

   
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Equipo",description = "Permite actualizar un equipo por medio de su id y un dto")
    public ResponseEntity<EquipoResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody EquipoRequestDto dto) {
        return equipoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Equipo",description = "Permite eliminar un equipo por su id")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            equipoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}