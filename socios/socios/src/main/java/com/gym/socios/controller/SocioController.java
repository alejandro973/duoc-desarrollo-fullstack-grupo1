package com.gym.socios.controller;
import com.gym.socios.dto.SocioRequestDTO;
import com.gym.socios.dto.SocioResponseDTO;
import com.gym.socios.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @GetMapping
    public List<SocioResponseDTO> listarTodos() {
        return socioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <SocioResponseDTO> buscarPorId(@PathVariable Long id) {
        return socioService.obtenerPorId(id) // 1. Llama al servicio
        .map(ResponseEntity::ok) // 2. Si existe el objeto y da un mensaje http 200 -> 200 OK
        .orElse(ResponseEntity.notFound().build()); // 3. Si no -> 404 Not Found , el build() cierra el sobre vacio y lo envia
    }

@GetMapping("/buscar/{run}")
public ResponseEntity<SocioResponseDTO> obtenerPorRun(@PathVariable String run) {
    return socioService.buscarPorRun(run)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarSocio(@PathVariable Long id){
         socioService.eliminarSocio(id); // ACCIÓN 1: El servicio borra en la base de datos.
         return ResponseEntity.noContent().build(); // ACCIÓN 2: El controlador construye y envía la respuesta.
    }

    @PostMapping
    public ResponseEntity<SocioResponseDTO> crearSocio(@Valid @RequestBody SocioRequestDTO socio) { // Valid para validar el NotNull y NotBlank, RequestBody convertir un JSON en objeto
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(socioService.guardarSocio(socio));
        
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> actualizarSocio(@PathVariable Long id, @Valid @RequestBody SocioRequestDTO socio) {
       return socioService.actualizarSocio(id, socio)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/filtro")
    public List<SocioResponseDTO> buscarPorEstado(@RequestParam String estado){
        return socioService.buscarPorCategoria(estado);


    }
    
    // http://localhost:8080/api/socios/filtro?estado=Activo busqueda en postman
    
}
