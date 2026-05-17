package com.gym.mantenimiento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "mantenimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String descripcion; 

    @NotNull
    @Column(nullable = false)
    private LocalDate fechaProgramada;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double costoEstimado;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String estado; 
}