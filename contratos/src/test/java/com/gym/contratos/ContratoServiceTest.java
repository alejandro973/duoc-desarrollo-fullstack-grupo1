package com.gym.contratos;

import com.gym.contratos.dto.ContratoRequestDto;
import com.gym.contratos.dto.ContratoResponseDto;
import com.gym.contratos.dto.ProveedorDto;      
import com.gym.contratos.dto.MantenimientoDto;  
import com.gym.contratos.client.ProveedorClient;  
import com.gym.contratos.client.MantenimientoClient; 
import com.gym.contratos.model.Contrato;
import com.gym.contratos.repository.ContratoRepository;
import com.gym.contratos.service.*;

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
public class ContratoServiceTest {

    @Autowired
    private ContratoService contratoService;

    @MockBean
    private ContratoRepository contratoRepository;

    @MockBean
    private ProveedorClient proveedorClient;

    @MockBean
    private MantenimientoClient mantenimientoClient;


    @Test
    public void testObtenerTodos() {
      
        Contrato contratoFake = new Contrato(1L, 10L, 20L, LocalDate.now(), 150000.0);
        when(contratoRepository.findAll()).thenReturn(List.of(contratoFake));

        ProveedorDto proveedorFake = new ProveedorDto();
        proveedorFake.setNombreEmpresa("Distribuidora GymMax");

        MantenimientoDto mantencionFake = new MantenimientoDto();
        mantencionFake.setDescripcion("Mantención Preventiva");

        when(proveedorClient.buscarPorId(10L)).thenReturn(proveedorFake);
        when(mantenimientoClient.buscarPorId(20L)).thenReturn(mantencionFake);

       
        List<ContratoResponseDto> resultado = contratoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Distribuidora GymMax", resultado.get(0).getProveedor().getNombreEmpresa());
    }

    
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Contrato contratoFake = new Contrato(id, 10L, 20L, LocalDate.now(), 150000.0);
        when(contratoRepository.findById(id)).thenReturn(Optional.of(contratoFake));

       
        when(proveedorClient.buscarPorId(10L)).thenReturn(new ProveedorDto());
        when(mantenimientoClient.buscarPorId(20L)).thenReturn(new MantenimientoDto());

        Optional<ContratoResponseDto> found = contratoService.obtenerPorId(id);

        assertTrue(found.isPresent());
        assertEquals(150000.0, found.get().getMontoFinal());
    }

   
    
    @Test
    public void testGuardar() {
        ContratoRequestDto request = new ContratoRequestDto();
        request.setIdProveedor(10L);
        request.setIdMantenimiento(20L);
        request.setFechaFirma(LocalDate.now());
        request.setMontoFinal(150000.0);

        
        when(proveedorClient.buscarPorId(10L)).thenReturn(new ProveedorDto());
        when(mantenimientoClient.buscarPorId(20L)).thenReturn(new MantenimientoDto());

        Contrato contratoGuardado = new Contrato(100L, 10L, 20L, LocalDate.now(), 150000.0);
        when(contratoRepository.save(any(Contrato.class))).thenReturn(contratoGuardado);

        ContratoResponseDto respuesta = contratoService.guardar(request);

        assertNotNull(respuesta);
        assertEquals(100L, respuesta.getId());
    }
}