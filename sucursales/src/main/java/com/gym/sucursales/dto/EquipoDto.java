package com.gym.sucursales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDto {
    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    
}