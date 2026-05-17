package com.gym.contratos.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoResponseDto {
    private Long id;
    private LocalDate fechaFirma;
    private Double montoFinal;
    
   
    private ProveedorDto proveedor;
    private MantenimientoDto mantenimiento;
}