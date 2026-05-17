package com.gym.mantenimiento.service;

import com.gym.mantenimiento.dto.MantenimientoRequestDto;
import com.gym.mantenimiento.dto.MantenimientoResponseDto;
import com.gym.mantenimiento.model.Mantenimiento;
import com.gym.mantenimiento.repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

   
    private MantenimientoResponseDto mapToDto(Mantenimiento m) {
        return new MantenimientoResponseDto(
                m.getId(),
                m.getDescripcion(),
                m.getFechaProgramada(),
                m.getCostoEstimado(),
                m.getEstado()
        );
    }

    public List<MantenimientoResponseDto> obtenerTodos() {
        return mantenimientoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<MantenimientoResponseDto> obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id)
                .map(this::mapToDto);
    }


    public MantenimientoResponseDto guardar(MantenimientoRequestDto dto) {
        Mantenimiento m = new Mantenimiento();
        m.setDescripcion(dto.getDescripcion());
        m.setFechaProgramada(dto.getFechaProgramada());
        m.setCostoEstimado(dto.getCostoEstimado());
        m.setEstado(dto.getEstado());
        
        return mapToDto(mantenimientoRepository.save(m));
    }

    public Optional<MantenimientoResponseDto> actualizar(Long id, MantenimientoRequestDto dto) {
        return mantenimientoRepository.findById(id).map(mExistente -> {
            mExistente.setDescripcion(dto.getDescripcion());
            mExistente.setFechaProgramada(dto.getFechaProgramada());
            mExistente.setCostoEstimado(dto.getCostoEstimado());
            mExistente.setEstado(dto.getEstado());
            return mapToDto(mantenimientoRepository.save(mExistente));
        });
    }


    public void eliminar(Long id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new RuntimeException("El mantenimiento con ID " + id + " no existe.");
        }
        mantenimientoRepository.deleteById(id);
    }
}