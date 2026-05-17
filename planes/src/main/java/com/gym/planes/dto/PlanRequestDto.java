package com.gym.planes.dto;
import lombok.*;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanRequestDto {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombrePlan;
    
    @NotBlank(message = "El plan debe de tener un estado")
    private String estadoPlan;

    @NotNull(message = "Debe de tener una fecha ")
     private Date fechaVencimiento;

     @NotBlank(message = "Debe de tener un run asociado")
    private String runSocio;
}

