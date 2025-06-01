package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uco.asisteuco.application.outputport.entity.TipoIdentificacionEntity;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacionEntity, UUID> {

    List<TipoIdentificacionEntity> findByClave(String clave);

    List<TipoIdentificacionEntity> findByNombre(String nombre);

    List<TipoIdentificacionEntity> findByClaveAndNombre(String clave, String nombre);
	
	boolean existsByClave(String clave);
	boolean existsByNombre(String nombre);

}
