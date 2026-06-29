package com.gym.planes;
import com.gym.planes.dto.PlanResponseDto;
import com.gym.planes.model.Plan;
import com.gym.planes.repository.PlanRepository;
import com.gym.planes.service.PlanService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
public class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @MockBean
    private PlanRepository planRepository;

  
    @Test
    public void testBuscarTodos() {
     
        Date fechaVencimientoFake = new Date();
        Plan planFake = new Plan(1L, "Plan Mensual VIP", "Activo", fechaVencimientoFake, "12345678-9");
        
        when(planRepository.findAll()).thenReturn(List.of(planFake));

     
        List<PlanResponseDto> resultado = planService.buscarTodos();

      
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Date fechaVencimientoFake = new Date();
        Plan planFake = new Plan(id, "Plan Mensual VIP", "Activo", fechaVencimientoFake, "12345678-9");

        when(planRepository.findById(id)).thenReturn(Optional.of(planFake));

        Optional<PlanResponseDto> found = planService.buscarPorId(id);


        assertTrue(found.isPresent());
        assertEquals("Plan Mensual VIP", found.get().getNombrePlan());
    }


    @Test
    public void testEliminarPlan() {
        Long id = 1L;

    
        doNothing().when(planRepository).deleteById(id);

    
        planService.eliminarPlan(id);

        verify(planRepository, times(1)).deleteById(id);
    }
}
