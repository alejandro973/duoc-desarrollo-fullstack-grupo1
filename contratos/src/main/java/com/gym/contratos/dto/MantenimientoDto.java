package com.gym.contratos.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MantenimientoDto {
    private Long id;
    private String descripcion;
    private LocalDate fechaProgramada;
    private Double costoEstimado;
    private String estado;
}