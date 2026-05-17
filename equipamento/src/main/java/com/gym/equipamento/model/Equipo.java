package com.gym.equipamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column( nullable = false,length = 100)
    private String tipo; 

    @Column( length = 100,nullable = false)
    private String estado; 

    @NotNull
    @Column(nullable = false)
    private Long idSucursal;
}