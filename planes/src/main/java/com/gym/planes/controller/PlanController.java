package com.gym.planes.controller;
import com.gym.planes.dto.PlanRequestDto;
import com.gym.planes.dto.PlanResponseDto;
import com.gym.planes.service.PlanService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.hibernate.internal.log.SubSystemLogging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/planes")
@AllArgsConstructor
@Tag(name = "Plan",description = "Operaciones relacionadas a los planes ofrecidos en el gym")
public class PlanController {

    private final PlanService planService;

    @GetMapping
    @Operation(summary = "Obtener todos los planes",description = "Permite obtener todos los planes que se ofrecen en el gym")
    public ResponseEntity<List<PlanResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(planService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener plan por id ",description = "Permite seleccionar un plan por su id")
    public ResponseEntity<PlanResponseDto> obtenerPorId(@PathVariable Long id) {
        return planService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


@GetMapping("/buscar/{run}")
@Operation(summary = "Obtener Plan por run",description = "Permite obtener un plan por el rut asociado al socio")
public ResponseEntity<PlanResponseDto> obtenerPorRun(@PathVariable String run) {
    return planService.buscarPorRun(run)
            .map(ResponseEntity::ok) 
            .orElse(ResponseEntity.notFound().build()); 
}


    @PostMapping
    @Operation(summary = "Crear Plan",description = "Permite crear un plan nuevo ")
    public ResponseEntity<PlanResponseDto> crearPlan(@Valid @RequestBody PlanRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.guardarPlan(dto));
    }

   
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar plan",description = "Actualizar plan por medio de su id y dto")
    public ResponseEntity<PlanResponseDto> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDto dto) {
        return planService.actualizarPlan(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar plan",description = "Permite eliminar un plan por medio de su id ")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
}

