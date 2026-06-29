package com.gym.sucursales;

import com.gym.sucursales.dto.SucursalRequestDto;
import com.gym.sucursales.dto.SucursalResponseDto;
import com.gym.sucursales.dto.EquipoDto; 
import com.gym.sucursales.client.EquipoClient; 
import com.gym.sucursales.model.Sucursal;
import com.gym.sucursales.repository.SucursalRepository;
import com.gym.sucursales.service.SucursalService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class SucursalServiceTest {

    @Autowired
    private SucursalService sucursalService;

    @MockBean
    private SucursalRepository sucursalRepository;

    @MockBean
    private EquipoClient equipoClient;


    public void testObtenerTodas() {
        Sucursal sucursalFake = new Sucursal(1L, "Sede Central", "Av. Vitacura 1234", "Santiago");
        when(sucursalRepository.findAll()).thenReturn(List.of(sucursalFake));

        EquipoDto equipoFake = new EquipoDto();
        equipoFake.setId(5L);
        equipoFake.setNombre("Trotadora Pro");
        equipoFake.setTipo("Cardio");
        equipoFake.setEstado("Disponible");
       
        when(equipoClient.obtenerEquiposPorSucursal(1L)).thenReturn(List.of(equipoFake));

        List<SucursalResponseDto> resultado = sucursalService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getEquipos().size());
    }
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Sucursal sucursalFake = new Sucursal(id, "Sede Central", "Av. Vitacura 1234", "Santiago");
        when(sucursalRepository.findById(id)).thenReturn(Optional.of(sucursalFake));

        // Simulamos la llamada externa para que no lance la excepción del catch
        when(equipoClient.obtenerEquiposPorSucursal(id)).thenReturn(List.of());

        Optional<SucursalResponseDto> found = sucursalService.obtenerPorId(id);

        assertTrue(found.isPresent());
        assertEquals("Sede Central", found.get().getNombre());
    }

    
    @Test
    public void testGuardar() {
        SucursalRequestDto request = new SucursalRequestDto();
        request.setNombre("Sede Ñuñoa");
        request.setDireccion("Av. Irarrazaval 456");
        request.setCiudad("Santiago");

        Sucursal sucursalGuardada = new Sucursal(2L, "Sede Ñuñoa", "Av. Irarrazaval 456", "Santiago");
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalGuardada);
        when(equipoClient.obtenerEquiposPorSucursal(2L)).thenReturn(List.of());

        SucursalResponseDto respuesta = sucursalService.guardar(request);

        assertNotNull(respuesta);
        assertEquals(2L, respuesta.getId());
        assertEquals("Sede Ñuñoa", respuesta.getNombre());
    }
}