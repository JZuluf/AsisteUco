package co.edu.uco.asisteuco.application.outputport.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uco.asisteuco.application.outputport.entity.MateriaEntity;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, UUID> {
    
	boolean existsByClave(String clave);
	
    // busca por clave y nombre exactos
    List<MateriaEntity> findByClaveAndNombre(String clave, String nombre);

    // busca todas las materias con esa clave
    List<MateriaEntity> findByClave(String clave);

    // busca todas las materias cuyo nombre contenga el texto, ignorando mayúsculas/minúsculas
    List<MateriaEntity> findByNombreContainingIgnoreCase(String nombre);
}