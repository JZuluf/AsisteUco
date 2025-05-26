package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.estudiantegrupo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.outputport.dto.EstudianteGrupoDTO;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteGrupoEntity;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteGrupoRepository;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

@RestController
@RequestMapping("/api/v1/estudiante-grupo")
@CrossOrigin(origins = "*")
public class EstudianteGrupoController {

    private final EstudianteGrupoRepository repository;

    public EstudianteGrupoController(EstudianteGrupoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<EstudianteGrupoDTO>> listar(
            @RequestParam(value = "grupoId", required = false) UUID grupoId,
            @RequestParam(value = "estudianteId", required = false) UUID estudianteId) {

        var api = new RespuestaApi<EstudianteGrupoDTO>();
        // indico que la transacción fue exitosa
        api.setTransaccionExitosa(true);

        // obtengo la lista según los filtros
        List<EstudianteGrupoEntity> entities;
        if (grupoId != null) {
            entities = repository.findByGrupo_Id(grupoId);
        } else if (estudianteId != null) {
            entities = repository.findByEstudiante_Id(estudianteId);
        } else {
            entities = repository.findAll();
        }

        // mapeo entidad → DTO
        List<EstudianteGrupoDTO> dtos = entities.stream()
            .map(e -> new EstudianteGrupoDTO(
                    e.getId(),
                    e.getEstudiante().getId(),
                    e.getGrupo().getId()
            ))
            .collect(Collectors.toList());

        api.setDatos(dtos);
        api.getMensajes().add("Consulta de asignaciones realizada exitosamente.");
        return ResponseEntity.ok(api);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<EstudianteGrupoDTO>> obtenerPorId(
            @PathVariable UUID id) {

        var api = new RespuestaApi<EstudianteGrupoDTO>();

        return repository.findById(id)
            .map(e -> {
                var dto = new EstudianteGrupoDTO(
                    e.getId(),
                    e.getEstudiante().getId(),
                    e.getGrupo().getId()
                );
                api.setTransaccionExitosa(true);
                api.getDatos().add(dto);
                api.getMensajes().add("Asignación encontrada correctamente.");
                return ResponseEntity.ok(api);
            })
            .orElseGet(() -> {
                api.setTransaccionExitosa(false);
                api.getMensajes().add("No se encontró asignación con ID: " + id);
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(api);
            });
    }
}
