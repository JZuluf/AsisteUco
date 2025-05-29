package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, UUID> {
    
	List<GrupoEntity> findByProfesor_Id(UUID profesorId);
    List<GrupoEntity> findByMateria_Id(UUID materiaId);
    List<GrupoEntity> findByProfesor_IdAndMateria_Id(UUID profesorId, UUID materiaId);
    
}

