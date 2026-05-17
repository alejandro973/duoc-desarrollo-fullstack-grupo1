package com.gym.contratos.controller;

import com.gym.contratos.dto.ContratoRequestDto;
import com.gym.contratos.dto.ContratoResponseDto;
import com.gym.contratos.service.ContratoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService contratoService;

    
    @GetMapping
    public ResponseEntity<List<ContratoResponseDto>> listarTodos() {
        return ResponseEntity.ok(contratoService.obtenerTodos());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponseDto> buscarPorId(@PathVariable Long id) {
        return contratoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContratoResponseDto> crear(@Valid @RequestBody ContratoRequestDto dto) {
        try {
            ContratoResponseDto nuevo = contratoService.guardar(dto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
          
            return ResponseEntity.badRequest().build();
        }
    }
}