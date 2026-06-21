package com.gym.equipamento;

import com.gym.equipamento.dto.EquipoResponseDto;
import com.gym.equipamento.model.Equipo;
import com.gym.equipamento.repository.EquipoRepository;
import com.gym.equipamento.service.EquipoService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EquipoServiceTest {

    @Autowired
    private EquipoService equipoService;

    @MockBean
    private EquipoRepository equipoRepository;

  
    @Test
    public void testObtenerTodos() {
      
        Equipo equipoFake = new Equipo(1L, "Trotadora Pro", "Cardio", "Disponible", 10L);
        
        when(equipoRepository.findAll()).thenReturn(List.of(equipoFake));

        List<EquipoResponseDto> equipos = equipoService.obtenerTodos();


        assertNotNull(equipos);
        assertEquals(1, equipos.size());
    }

 
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Equipo equipoFake = new Equipo(id, "Trotadora Pro", "Cardio", "Disponible", 10L);

        when(equipoRepository.findById(id)).thenReturn(Optional.of(equipoFake));

        Optional<EquipoResponseDto> found = equipoService.obtenerPorId(id);

        assertTrue(found.isPresent());
        assertEquals("Trotadora Pro", found.get().getNombre());
    }


    @Test
    public void testEliminar() {
        Long id = 1L;

    
        when(equipoRepository.existsById(id)).thenReturn(true);
        doNothing().when(equipoRepository).deleteById(id);

      
        equipoService.eliminar(id);

      
        verify(equipoRepository, times(1)).deleteById(id);
    }
}