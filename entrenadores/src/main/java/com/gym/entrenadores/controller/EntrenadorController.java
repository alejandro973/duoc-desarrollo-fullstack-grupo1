package com.gym.entrenadores.controller;
import com.gym.entrenadores.dto.EntrenadorRequestDto;
import com.gym.entrenadores.dto.EntrenadorResponseDto;
import com.gym.entrenadores.service.EntrenadorService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Entrenadores",description = "Operaciones relaciondas con Entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Entrenador por id",description = "Permite obtener un entrenador por su numero de id")
    public ResponseEntity<EntrenadorResponseDto> obtenerPorId(@PathVariable Long id){
        return entrenadorService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{run}")
    @Operation(summary = "Obtener entrenador por Run",description = "Permite obtener un entrenador por su run")
    public ResponseEntity<EntrenadorResponseDto> obtenerPorRun(@PathVariable String run){
        return entrenadorService.buscarPorRun(run).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    

    @GetMapping
    @Operation(summary = "Obtener todos los entrenadores",description = "Permite obtener una lista de todos los entrenadores")
    public ResponseEntity<List<EntrenadorResponseDto>> obtenderTodos(){
        return ResponseEntity.ok(entrenadorService.buscarTodos());
    }

    @PostMapping
    @Operation(summary = "Crear entrenador",description = "Permite crear un nuevo entrenador por medio de dto")
    public ResponseEntity<EntrenadorResponseDto> crearEntrenador(@Valid @RequestBody EntrenadorRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorService.guardarEntrenador(dto));


    } 
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Entrenador",description = "Permite actualizar un entrenador por id y dto")
    public ResponseEntity<EntrenadorResponseDto> actualizarEntrenador(@PathVariable Long id,@Valid @RequestBody EntrenadorRequestDto dto){
      return entrenadorService.actualizarEntrenador(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Entrenador",description = "Permite eliminar un entrenador por medio de su id")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id){
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();

    }
   
   
}
