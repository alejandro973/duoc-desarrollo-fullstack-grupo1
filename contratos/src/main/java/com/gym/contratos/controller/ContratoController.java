package com.gym.contratos.controller;

import com.gym.contratos.dto.ContratoRequestDto;
import com.gym.contratos.dto.ContratoResponseDto;
import com.gym.contratos.service.ContratoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@RequiredArgsConstructor
@Tag(name = "Contrato",description = "Operaciones relacionadas al contrato")
public class ContratoController {

    private final ContratoService contratoService;

    
    @GetMapping
    @Operation(summary = "Listar contratos",description = "Listar todos los contratos existentes")
    public ResponseEntity<List<ContratoResponseDto>> listarTodos() {
        return ResponseEntity.ok(contratoService.obtenerTodos());
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Contratos",description = "Permite buscar contratos por id")
    public ResponseEntity<ContratoResponseDto> buscarPorId(@PathVariable Long id) {
        return contratoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear Contrato",description = "Permite crear contratos nuevos")
    public ResponseEntity<ContratoResponseDto> crear(@Valid @RequestBody ContratoRequestDto dto) {
        try {
            ContratoResponseDto nuevo = contratoService.guardar(dto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
          
            return ResponseEntity.badRequest().build();
        }
    }
}