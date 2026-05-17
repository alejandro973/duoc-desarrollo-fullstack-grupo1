package com.gym.equipamento.controller;

import com.gym.equipamento.dto.EquipoRequestDto;
import com.gym.equipamento.dto.EquipoResponseDto;
import com.gym.equipamento.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<EquipoResponseDto>> listarTodos() {
        return ResponseEntity.ok(equipoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponseDto> buscarPorId(@PathVariable Long id) {
        return equipoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<EquipoResponseDto>> listarPorSucursal(@PathVariable Long idSucursal) {
        List<EquipoResponseDto> equipos = equipoService.obtenerPorSucursal(idSucursal);
        return ResponseEntity.ok(equipos);
    }


    @PostMapping
    public ResponseEntity<EquipoResponseDto> crear(@Valid @RequestBody EquipoRequestDto dto) {
        EquipoResponseDto nuevo = equipoService.guardar(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody EquipoRequestDto dto) {
        return equipoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            equipoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}