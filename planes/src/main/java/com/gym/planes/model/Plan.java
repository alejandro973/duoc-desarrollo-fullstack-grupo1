package com.gym.planes.model;
import lombok.*;
import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Plan")
public class Plan {

    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;

    @NotBlank(message = "Es necesario un nombre para el plan")
    @Column(length = 100,nullable = false)
    private String nombrePlan;

     @NotBlank(message = "Es necesario un estado para registrar el plan")
    @Column(length = 100,nullable = false)
    private String estadoPlan;

    @NotNull
    @Column(nullable = false)
    private Date fechaVencimiento;

    @NotBlank(message = "Es necesario un RUN para el registro")
    @Column(length = 13,nullable = false)
    private String runSocio;


}
