package com.gym.planes.dto;
import lombok.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanResponseDto {

    private Long idPlan;
    private String nombrePlan;
    private String estadoPlan;

    @JsonFormat(pattern = "yyyy-MM-dd")
     private Date fechaVencimiento;
    private String runSocio;
}
