package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asisteuco.application.outputport.entity.AsistenciaEntity;

@Repository
public interface AsistenciaRepository extends JpaRepository<AsistenciaEntity, UUID> {
	
	boolean existsBySesionIdAndEstudianteId(UUID sesionId, UUID estudianteId);

}
