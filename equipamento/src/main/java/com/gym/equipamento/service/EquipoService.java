package com.gym.equipamento.service;

import com.gym.equipamento.dto.EquipoRequestDto;
import com.gym.equipamento.dto.EquipoResponseDto;
import com.gym.equipamento.model.Equipo;
import com.gym.equipamento.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    
    private EquipoResponseDto mapToDto(Equipo equipo) {
        return new EquipoResponseDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getTipo(),
                equipo.getEstado(),
                equipo.getIdSucursal()
        );
    }

    
    public List<EquipoResponseDto> obtenerTodos() {
        return equipoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    
    public List<EquipoResponseDto> obtenerPorSucursal(Long idSucursal) {
        return equipoRepository.findByIdSucursal(idSucursal).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

  
    public Optional<EquipoResponseDto> obtenerPorId(Long id) {
        return equipoRepository.findById(id)
                .map(this::mapToDto);
    }


    public EquipoResponseDto guardar(EquipoRequestDto dto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());
        equipo.setTipo(dto.getTipo());
        equipo.setEstado(dto.getEstado());
        equipo.setIdSucursal(dto.getIdSucursal());
        
        return mapToDto(equipoRepository.save(equipo));
    }


    public Optional<EquipoResponseDto> actualizar(Long id, EquipoRequestDto dto) {
        return equipoRepository.findById(id).map(equipoExistente -> {
            equipoExistente.setNombre(dto.getNombre());
            equipoExistente.setTipo(dto.getTipo());
            equipoExistente.setEstado(dto.getEstado());
            equipoExistente.setIdSucursal(dto.getIdSucursal());
            return mapToDto(equipoRepository.save(equipoExistente));
        });
    }


    public void eliminar(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("El equipo con ID " + id + " no existe.");
        }
        equipoRepository.deleteById(id);
    }
}