package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.asisteuco.application.outputport.entity.AsistenciaEntity;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;
import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Estudiante;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Profesor;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Sesion;

/**
 * Mapper para convertir entre entidades JPA y objetos de dominio de Asistencia.
 */
public final class AsistenciaMapper {

    private AsistenciaMapper() { }

    /**
     * Convierte un objeto de dominio Asistencia en una lista de AsistenciaEntity.
     */
    public static List<AsistenciaEntity> mapToEntities(
            Asistencia dominio,
            SesionEntity sesionEntity,
            List<EstudianteEntity> estudiantesEntity
    ) {
        return dominio.getEstudiantes().stream()
            .map(estDom -> {
                EstudianteEntity estEntity = estudiantesEntity.stream()
                    .filter(e -> e.getId().equals(estDom.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                        "Estudiante no encontrado con id: " + estDom.getId()));

                AsistenciaEntity asistenciaEntity = new AsistenciaEntity();
                asistenciaEntity.setSesion(sesionEntity);
                asistenciaEntity.setEstudiante(estEntity);
                asistenciaEntity.setAsistio(estDom.isAsistio());
                return asistenciaEntity;
            })
            .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de AsistenciaEntity en el objeto de dominio Asistencia.
     */
    public static Asistencia mapToDomain(
            List<AsistenciaEntity> entidades,
            Profesor profesor
    ) {
        if (entidades == null || entidades.isEmpty()) {
            throw new RuntimeException("No hay registros de asistencia");
        }

        // Crear objeto Sesion de dominio con ID
        Sesion sesion = new Sesion(entidades.get(0).getSesion().getId());

        // Mapear cada entidad a dominio, usando constructor con nombre y asistencia
        List<Estudiante> estudiantes = entidades.stream()
            .map(e -> new Estudiante(
                e.getEstudiante().getId(),
                e.getEstudiante().getNombresCompletos(),
                e.isAsistio()
            ))
            .collect(Collectors.toList());

        return new Asistencia(sesion, profesor, estudiantes);
    }
}