package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uco.asisteuco.application.outputport.entity.AsistenciaEntity;

public interface AsistenciaRepository extends JpaRepository<AsistenciaEntity, UUID> {
   
	List<AsistenciaEntity> findBySesion_Id(UUID sesionId);
    List<AsistenciaEntity> findByEstudianteGrupo_Id(UUID egId);
    
}