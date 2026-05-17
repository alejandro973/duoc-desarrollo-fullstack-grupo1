package com.gym.inscripciones.controller;

import com.gym.inscripciones.dto.InscripcionRequestDto;
import com.gym.inscripciones.dto.InscripcionResponseDto;
import com.gym.inscripciones.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionResponseDto>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

   
    @PostMapping
    public ResponseEntity<?> realizarInscripcion(@Valid @RequestBody InscripcionRequestDto request) {
        try {
            InscripcionResponseDto nueva = inscripcionService.crearInscripcion(request);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
         
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        try {
            inscripcionService.cancelarInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}