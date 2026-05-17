package com.gym.proveedores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column( nullable = false, length = 100)
    private String nombreEmpresa; 

    @NotBlank
    @Column(nullable = false, length = 100)
    private String contacto; 

    @NotBlank
    @Column(nullable = false, length = 20)
    private String telefono;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String categoria; 
}