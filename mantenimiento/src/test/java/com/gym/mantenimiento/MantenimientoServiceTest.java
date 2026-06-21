package com.gym.mantenimiento;
import com.gym.mantenimiento.dto.MantenimientoResponseDto;
import com.gym.mantenimiento.model.Mantenimiento;
import com.gym.mantenimiento.repository.MantenimientoRepository;
import com.gym.mantenimiento.service.MantenimientoService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@SpringBootTest
public class MantenimientoServiceTest {

    @Autowired
    private MantenimientoService mantenimientoService;

    @MockBean
    private MantenimientoRepository mantenimientoRepository;

    @Test
    public void testObtenerTodos() {
    
        Mantenimiento mantencionFake = new Mantenimiento(1L, "Mantención preventiva de trotadoras", LocalDate.now(), 45000.0, "Pendiente");
        
        when(mantenimientoRepository.findAll()).thenReturn(List.of(mantencionFake));

        List<MantenimientoResponseDto> resultado = mantenimientoService.obtenerTodos();

   
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

 
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Mantenimiento mantencionFake = new Mantenimiento(id, "Mantención preventiva de trotadoras", LocalDate.now(), 45000.0, "Pendiente");

        when(mantenimientoRepository.findById(id)).thenReturn(Optional.of(mantencionFake));

  
        Optional<MantenimientoResponseDto> found = mantenimientoService.obtenerPorId(id);

   
        assertTrue(found.isPresent());
        assertEquals("Mantención preventiva de trotadoras", found.get().getDescripcion());
    }


    @Test
    public void testEliminar() {
        Long id = 1L;

        when(mantenimientoRepository.existsById(id)).thenReturn(true);
        doNothing().when(mantenimientoRepository).deleteById(id);

      
        mantenimientoService.eliminar(id);

   
        verify(mantenimientoRepository, times(1)).deleteById(id);
    }
}