package com.gym.sucursales.service;

import com.gym.sucursales.client.EquipoClient;
import com.gym.sucursales.dto.*;
import com.gym.sucursales.model.Sucursal;
import com.gym.sucursales.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final EquipoClient equipoClient;

  
    private SucursalResponseDto mapToDto(Sucursal s) {
        SucursalResponseDto dto = new SucursalResponseDto();
        dto.setId(s.getId());
        dto.setNombre(s.getNombre());
        dto.setDireccion(s.getDireccion());
        dto.setCiudad(s.getCiudad());

        
        try {
            List<EquipoDto> equipos = equipoClient.obtenerEquiposPorSucursal(s.getId());
            dto.setEquipos(equipos);
        } catch (Exception e) {
        
            dto.setEquipos(List.of());
            System.err.println("Error al conectar con ms-equipamento: " + e.getMessage());
        }

        return dto;
    }

    public List<SucursalResponseDto> obtenerTodas() {
        return sucursalRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<SucursalResponseDto> obtenerPorId(Long id) {
        return sucursalRepository.findById(id).map(this::mapToDto);
    }

    public SucursalResponseDto guardar(SucursalRequestDto dto) {
        Sucursal s = new Sucursal();
        s.setNombre(dto.getNombre());
        s.setDireccion(dto.getDireccion());
        s.setCiudad(dto.getCiudad());
        return mapToDto(sucursalRepository.save(s));
    }
}