package com.gym.equipamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoResponseDto {
    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    private Long idSucursal;
}