package com.gym.clases.controller;

import com.gym.clases.dto.ClaseRequestDto;
import com.gym.clases.dto.ClaseResponseDto;
import com.gym.clases.service.ClaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
@RequiredArgsConstructor
public class ClaseController {

    private final ClaseService claseService;

 
    @GetMapping
    public ResponseEntity<List<ClaseResponseDto>> listarClases() {
        List<ClaseResponseDto> clases = claseService.obtenerTodas();
        return ResponseEntity.ok(clases);
    }

    // 2. Obtener una clase por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClaseResponseDto> buscarPorId(@PathVariable Long id) {
        return claseService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @PostMapping
    public ResponseEntity<ClaseResponseDto> crearClase(@Valid @RequestBody ClaseRequestDto dto) {
        ClaseResponseDto nuevaClase = claseService.guardarClase(dto);
        return new ResponseEntity<>(nuevaClase, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody ClaseRequestDto dto) {
        return claseService.actualizarClase(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            claseService.eliminarClase(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Filtrar por horario (Ejemplo: /api/clases/buscar?horario=Lunes 10:00)
    @GetMapping("/buscar")
    public ResponseEntity<List<ClaseResponseDto>> buscarPorHorario(@RequestParam String horario) {
        List<ClaseResponseDto> filtradas = claseService.buscarPorHorario(horario);
        return ResponseEntity.ok(filtradas);
    }
}