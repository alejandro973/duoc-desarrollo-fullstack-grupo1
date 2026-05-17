package com.gym.planes.repository;
import com.gym.planes.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long>  {

    Optional<Plan> findByRunSocio(String runSocio);

}
