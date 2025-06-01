package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;

@Repository
public interface SesionRepository extends  JpaRepository<SesionEntity, UUID> {

	List<SesionEntity> findByGrupo_Id(UUID grupoId);
}
