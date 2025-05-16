package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;

@Repository
public interface SesionRepository extends  JpaRepository<SesionEntity, UUID> {
	// No additional methods are needed for now
	// The GenericRepository interface provides basic CRUD operations
	// and we can add custom queries if needed in the future

}
