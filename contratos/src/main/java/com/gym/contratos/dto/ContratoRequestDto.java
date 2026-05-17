package com.gym.contratos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoRequestDto {
    @NotNull(message = "El idProveedor es requerido")
    private Long idProveedor;

    @NotNull(message = "El idMantenimiento es requerido")
    private Long idMantenimiento;

    @NotNull(message = "La fecha de firma es requerida")
    private LocalDate fechaFirma;

    @NotNull(message = "El monto final es requerido")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double montoFinal;
}