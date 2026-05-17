package com.gym.entrenadores.controller;
import com.gym.entrenadores.dto.EntrenadorRequestDto;
import com.gym.entrenadores.dto.EntrenadorResponseDto;
import com.gym.entrenadores.service.EntrenadorService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/entrenadores")
@AllArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDto> obtenerPorId(@PathVariable Long id){
        return entrenadorService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{run}")
    public ResponseEntity<EntrenadorResponseDto> obtenerPorRun(@PathVariable String run){
        return entrenadorService.buscarPorRun(run).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    

    @GetMapping
    public ResponseEntity<List<EntrenadorResponseDto>> obtenderTodos(){
        return ResponseEntity.ok(entrenadorService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<EntrenadorResponseDto> crearEntrenador(@Valid @RequestBody EntrenadorRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorService.guardarEntrenador(dto));


    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDto> actualizarEntrenador(@PathVariable Long id,@Valid @RequestBody EntrenadorRequestDto dto){
      return entrenadorService.actualizarEntrenador(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id){
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();

    }
   
   
}
