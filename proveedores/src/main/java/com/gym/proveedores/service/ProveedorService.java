package com.gym.proveedores.service;

import com.gym.proveedores.dto.ProveedorRequestDto;
import com.gym.proveedores.dto.ProveedorResponseDto;
import com.gym.proveedores.model.Proveedor;
import com.gym.proveedores.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    private ProveedorResponseDto mapToDto(Proveedor p) {
        return new ProveedorResponseDto(
                p.getId(),
                p.getNombreEmpresa(),
                p.getContacto(),
                p.getTelefono(),
                p.getCategoria()
        );
    }

    public List<ProveedorResponseDto> obtenerTodos() {
        return proveedorRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

 
    public Optional<ProveedorResponseDto> obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .map(this::mapToDto);
    }


    public ProveedorResponseDto guardar(ProveedorRequestDto dto) {
        Proveedor p = new Proveedor();
        p.setNombreEmpresa(dto.getNombreEmpresa());
        p.setContacto(dto.getContacto());
        p.setTelefono(dto.getTelefono());
        p.setCategoria(dto.getCategoria());
        
        return mapToDto(proveedorRepository.save(p));
    }

    public Optional<ProveedorResponseDto> actualizar(Long id, ProveedorRequestDto dto) {
        return proveedorRepository.findById(id).map(pExistente -> {
            pExistente.setNombreEmpresa(dto.getNombreEmpresa());
            pExistente.setContacto(dto.getContacto());
            pExistente.setTelefono(dto.getTelefono());
            pExistente.setCategoria(dto.getCategoria());
            return mapToDto(proveedorRepository.save(pExistente));
        });
    }

   
    public void eliminar(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException("El proveedor con ID " + id + " no existe.");
        }
        proveedorRepository.deleteById(id);
    }
}