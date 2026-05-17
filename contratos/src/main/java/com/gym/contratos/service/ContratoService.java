package com.gym.contratos.service;

import com.gym.contratos.client.MantenimientoClient;
import com.gym.contratos.client.ProveedorClient;
import com.gym.contratos.dto.*;
import com.gym.contratos.model.Contrato;
import com.gym.contratos.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final ProveedorClient proveedorClient;
    private final MantenimientoClient mantenimientoClient;

   
    private ContratoResponseDto mapToDto(Contrato c) {
        ContratoResponseDto dto = new ContratoResponseDto();
        dto.setId(c.getId());
        dto.setFechaFirma(c.getFechaFirma());
        dto.setMontoFinal(c.getMontoFinal());

   
        try {
            ProveedorDto proveedor = proveedorClient.buscarPorId(c.getIdProveedor());
            dto.setProveedor(proveedor);
        } catch (Exception e) {
            dto.setProveedor(null); 
            System.err.println("Error de comunicación con proveedores: " + e.getMessage());
        }

        try {
            MantenimientoDto mantenimiento = mantenimientoClient.buscarPorId(c.getIdMantenimiento());
            dto.setMantenimiento(mantenimiento);
        } catch (Exception e) {
            dto.setMantenimiento(null);
            System.err.println("Error de comunicación con mantenimientos: " + e.getMessage());
        }

        return dto;
    }

  
    public List<ContratoResponseDto> obtenerTodos() {
        return contratoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<ContratoResponseDto> obtenerPorId(Long id) {
        return contratoRepository.findById(id)
                .map(this::mapToDto);
    }

  
    public ContratoResponseDto guardar(ContratoRequestDto dto) {
       
        try {
            proveedorClient.buscarPorId(dto.getIdProveedor());
            mantenimientoClient.buscarPorId(dto.getIdMantenimiento());
        } catch (Exception e) {
            throw new RuntimeException("No se puede crear el contrato: Uno de los servicios externos no responde o los IDs no existen.");
        }

        Contrato c = new Contrato();
        c.setIdProveedor(dto.getIdProveedor());
        c.setIdMantenimiento(dto.getIdMantenimiento());
        c.setFechaFirma(dto.getFechaFirma());
        c.setMontoFinal(dto.getMontoFinal());
        
        return mapToDto(contratoRepository.save(c));
    }
}