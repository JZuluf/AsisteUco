package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.asisteuco.application.outputport.entity.AsistenciaEntity;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteGrupoEntity;
import co.edu.uco.asisteuco.application.outputport.entity.GrupoEntity;
import co.edu.uco.asisteuco.application.outputport.entity.ProfesorEntity;
import co.edu.uco.asisteuco.application.outputport.entity.SesionEntity;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Estudiante;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Profesor;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Sesion;

public final class AsistenciaMapper {

    private AsistenciaMapper() { }

    /**
     * Convierte un objeto de dominio Asistencia en una lista de AsistenciaEntity.
     */
    public static List<AsistenciaEntity> mapToEntities(
            Asistencia dominio,
            SesionEntity sesionEntity,
            List<EstudianteGrupoEntity> estudiantesGrupo
    ) {
        return dominio.getEstudiantes().stream()
            .map(estDom -> {
                // Localizo el EstudianteGrupoEntity
                EstudianteGrupoEntity eg = estudiantesGrupo.stream()
                    .filter(e -> e.getId().equals(estDom.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                        "Estudiante-Grupo no encontrado con id: " + estDom.getId()
                    ));

                AsistenciaEntity asistenciaEntity = new AsistenciaEntity();
                asistenciaEntity.setSesion(sesionEntity);
                asistenciaEntity.setEstudianteGrupo(eg);
                // <-- aquí cambiamos .isAsistio() por .getAsistio()
                asistenciaEntity.setAsistio(estDom.isAsistio());
                return asistenciaEntity;
            })
            .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de AsistenciaEntity en un objeto de dominio Asistencia.
     */
    public static Asistencia mapToDomain(
            List<AsistenciaEntity> entidades
    ) {
        if (entidades == null || entidades.isEmpty()) {
            throw new RuntimeException("No hay registros de asistencia");
        }

        // Extraigo Sesión, Grupo y Profesor de las entidades
        SesionEntity sesionE = entidades.get(0).getSesion();
        GrupoEntity  grupoE  = sesionE.getGrupo();
        ProfesorEntity profE = grupoE.getProfesor();

        // Mapeo a dominio Profesor
        Profesor profDom = new Profesor(
            profE.getId(),
            profE.getNombresCompletos(),
            profE.getNumeroIdentificacion()
        );

        // Mapeo a dominio Sesión
        Sesion sesionDom = new Sesion(sesionE.getId());

        // Mapeo a dominio Estudiante
        List<Estudiante> estudiantesDom = entidades.stream()
            .map(e -> {
                var eg = e.getEstudianteGrupo();
                var estE = eg.getEstudiante();
                return new Estudiante(
                    estE.getId(),
                    estE.getNombresCompletos(),
                    e.getAsistio()
                );
            })
            .collect(Collectors.toList());

        return new Asistencia(sesionDom, profDom, estudiantesDom);
    }
}
