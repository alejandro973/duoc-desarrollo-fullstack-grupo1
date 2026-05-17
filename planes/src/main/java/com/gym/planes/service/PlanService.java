package com.gym.planes.service;
import com.gym.planes.repository.PlanRepository;
import com.gym.planes.dto.PlanRequestDto;
import com.gym.planes.dto.PlanResponseDto;
import com.gym.planes.model.Plan;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {

  private final PlanRepository planRepository;

  private PlanResponseDto maptoDto(Plan p) {
    return new PlanResponseDto(
        p.getIdPlan(),
        p.getNombrePlan(),      
        p.getEstadoPlan(),      
        p.getFechaVencimiento(),
        p.getRunSocio()        
    ); 
}

public Optional<PlanResponseDto> buscarPorId(Long id){
  return planRepository.findById(id).map(this::maptoDto);
}

public Optional<PlanResponseDto> buscarPorRun(String run) {
    return planRepository.findByRunSocio(run)
            .map(plan -> new PlanResponseDto(
                plan.getIdPlan(),
                plan.getNombrePlan(),
                plan.getEstadoPlan(),
                plan.getFechaVencimiento(),
                plan.getRunSocio()
            ));
}

public List<PlanResponseDto> buscarTodos(){
  return planRepository.findAll().stream().map(this::maptoDto).collect(Collectors.toList());
   

}

public PlanResponseDto guardarPlan(PlanRequestDto dto){
  Plan p = new Plan(null,dto.getNombrePlan(),dto.getEstadoPlan(),dto.getFechaVencimiento(),dto.getRunSocio());
  return maptoDto(planRepository.save(p));
}

public Optional<PlanResponseDto> actualizarPlan(Long id,PlanRequestDto dto){
  return planRepository.findById(id).map(e->{
    e.setNombrePlan(dto.getNombrePlan());
    e.setEstadoPlan(dto.getEstadoPlan());
    e.setFechaVencimiento(dto.getFechaVencimiento());
    e.setRunSocio(dto.getRunSocio());
    return maptoDto(planRepository.save(e));
  
  });
}

public void eliminarPlan(Long id){
  planRepository.deleteById(id);
}

}
