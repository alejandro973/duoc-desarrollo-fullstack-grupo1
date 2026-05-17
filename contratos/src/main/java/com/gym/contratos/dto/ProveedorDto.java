package com.gym.contratos.dto;

import lombok.Data;

@Data
public class ProveedorDto {
    private Long id;
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
    private String categoria;
}