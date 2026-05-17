package com.gym.sucursales.dto;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalResponseDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    
   
    private List<EquipoDto> equipos; 
}