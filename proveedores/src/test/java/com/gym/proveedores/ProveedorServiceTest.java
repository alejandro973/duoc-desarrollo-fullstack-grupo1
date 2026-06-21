package com.gym.proveedores;
import com.gym.proveedores.dto.ProveedorResponseDto;
import com.gym.proveedores.model.Proveedor;
import com.gym.proveedores.repository.ProveedorRepository;
import com.gym.proveedores.service.ProveedorService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    @MockBean
    private ProveedorRepository proveedorRepository;

    @Test
    public void testObtenerTodos() {
        Proveedor proveedorFake = new Proveedor(1L, "Distribuidora GymMax", "Claudio Soto", "+56912345678", "Suplementos");
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedorFake));

        List<ProveedorResponseDto> resultado = proveedorService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }


    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Proveedor proveedorFake = new Proveedor(id, "Distribuidora GymMax", "Claudio Soto", "+56912345678", "Suplementos");
        when(proveedorRepository.findById(id)).thenReturn(Optional.of(proveedorFake));

        Optional<ProveedorResponseDto> found = proveedorService.obtenerPorId(id);

        assertTrue(found.isPresent());
        assertEquals("Distribuidora GymMax", found.get().getNombreEmpresa());
    }

   
    @Test
    public void testEliminar() {
        Long id = 1L;
        when(proveedorRepository.existsById(id)).thenReturn(true);
        doNothing().when(proveedorRepository).deleteById(id);

        proveedorService.eliminar(id);

        verify(proveedorRepository, times(1)).deleteById(id);
    }
}