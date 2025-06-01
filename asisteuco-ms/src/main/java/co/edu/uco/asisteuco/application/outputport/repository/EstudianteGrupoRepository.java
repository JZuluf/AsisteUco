package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.asisteuco.application.outputport.entity.EstudianteGrupoEntity;

public interface EstudianteGrupoRepository extends JpaRepository<EstudianteGrupoEntity, UUID> {

    /** Evita duplicados en la asignación */
    boolean existsByGrupo_IdAndEstudiante_Id(UUID grupoId, UUID estudianteId);

    List<EstudianteGrupoEntity> findByGrupo_Id(UUID grupoId);

    List<EstudianteGrupoEntity> findByEstudiante_Id(UUID estudianteId);
}
