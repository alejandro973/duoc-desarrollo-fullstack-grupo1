package com.gym.planes.controller;
import com.gym.planes.dto.PlanRequestDto;
import com.gym.planes.dto.PlanResponseDto;
import com.gym.planes.service.PlanService;

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
@RequestMapping("/api/planes")
@AllArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(planService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> obtenerPorId(@PathVariable Long id) {
        return planService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


@GetMapping("/buscar/{run}")
public ResponseEntity<PlanResponseDto> obtenerPorRun(@PathVariable String run) {
    return planService.buscarPorRun(run)
            .map(ResponseEntity::ok) 
            .orElse(ResponseEntity.notFound().build()); 
}


    @PostMapping
    public ResponseEntity<PlanResponseDto> crearPlan(@Valid @RequestBody PlanRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.guardarPlan(dto));
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDto dto) {
        return planService.actualizarPlan(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
}

