package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uco.asisteuco.application.outputport.entity.ProfesorEntity;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, UUID> {

    boolean existsByTipoIdentificacionIdAndNumeroIdentificacion(UUID tipoId, String numero);

    List<ProfesorEntity> findByTipoIdentificacionIdAndNumeroIdentificacion(UUID tipoId, String numero);
    List<ProfesorEntity> findByTipoIdentificacionId(UUID tipoId);
    List<ProfesorEntity> findByNumeroIdentificacion(String numero);
    List<ProfesorEntity> findByNombresCompletosContainingIgnoreCase(String nombres);
}