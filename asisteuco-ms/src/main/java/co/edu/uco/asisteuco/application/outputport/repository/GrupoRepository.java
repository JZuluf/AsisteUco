package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;

public interface GrupoRepository extends JpaRepository<GrupoEntity, UUID> {

}
