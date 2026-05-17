package com.gym.equipamento.repository;

import com.gym.equipamento.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByIdSucursal(Long idSucursal);

    List<Equipo> findByEstado(String estado);
}