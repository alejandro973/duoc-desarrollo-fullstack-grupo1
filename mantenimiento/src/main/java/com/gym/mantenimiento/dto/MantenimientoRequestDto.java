package com.gym.mantenimiento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoRequestDto {

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha programada es obligatoria")
    private LocalDate fechaProgramada;

    @NotNull(message = "El costo estimado es obligatorio")
    @Positive(message = "El costo debe ser un valor positivo")
    private Double costoEstimado;

    @NotBlank(message = "El estado es obligatorio (Pendiente/En Proceso/Completado)")
    private String estado;
}