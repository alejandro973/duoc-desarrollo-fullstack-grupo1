package com.gym.contratos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratos_externos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column( nullable = false)
    private Long idProveedor;

    @NotNull
    @Column(nullable = false)
    private Long idMantenimiento;

    @NotNull
    @Column(nullable = false)
    private LocalDate fechaFirma;

    @NotNull
    @Positive
    @Column( nullable = false)
    private Double montoFinal;
}