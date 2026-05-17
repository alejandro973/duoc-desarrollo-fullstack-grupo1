package com.gym.proveedores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponseDto {
    private Long id;
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
    private String categoria;
}