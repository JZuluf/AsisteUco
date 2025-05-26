package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, UUID> {

    boolean existsByTipoIdentificacionIdAndNumeroIdentificacion(
        UUID tipoIdentificacionId,
        String numeroIdentificacion
    );

    List<EstudianteEntity> findByTipoIdentificacionIdAndNumeroIdentificacion(
        UUID tipoIdentificacionId,
        String numeroIdentificacion
    );

    List<EstudianteEntity> findByTipoIdentificacionId(UUID tipoIdentificacionId);

    List<EstudianteEntity> findByNumeroIdentificacion(String numeroIdentificacion);

	List<EstudianteEntity> findByNombresCompletosContainingIgnoreCase(String nombresCompletos);
}
