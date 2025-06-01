package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.estudiante;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.RegistrarEstudianteInteractor;
import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.request.RegistrarEstudianteRequestDTO;
import co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.response.RegistrarEstudianteResponseDTO;
import co.edu.uco.asisteuco.application.outputport.entity.EstudianteEntity;
import co.edu.uco.asisteuco.application.outputport.repository.EstudianteRepository;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);

    private final RegistrarEstudianteInteractor registrarInteractor;
    private final EstudianteRepository repository;

    public EstudianteController(RegistrarEstudianteInteractor registrarInteractor,
                                EstudianteRepository repository) {
        this.registrarInteractor = registrarInteractor;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarEstudianteResponseDTO>> registrar(
            @RequestBody RegistrarEstudianteRequestDTO requestDTO) {
        RespuestaApi<RegistrarEstudianteResponseDTO> respuesta = new RespuestaApi<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
            RegistrarEstudianteResponseDTO data = registrarInteractor.ejecutar(requestDTO);
            respuesta.getDatos().add(data);
            respuesta.getMensajes().add("El estudiante se ha registrado exitosamente.");
        } catch (AsisteUcoException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            respuesta = new RespuestaApi<>();
            respuesta.getMensajes().add(e.getMensajeUsuario());
            logger.error("Error de negocio al registrar estudiante: {}", e.getMessage(), e);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            respuesta = new RespuestaApi<>();
            respuesta.getMensajes().add("Ocurrió un problema inesperado. Por favor, contacte al administrador del sistema.");
            logger.error("Un error inesperado ha ocurrido al registrar un estudiante.", e);
        }

        return new ResponseEntity<>(respuesta, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarPorId(@PathVariable("id") UUID id) {
        return repository.findById(id)
            .<ResponseEntity<Object>>map(est -> ResponseEntity.ok(est))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró estudiante con ID: " + id));
    }

    @GetMapping
    public ResponseEntity<List<EstudianteEntity>> consultarPorFiltros(
        @RequestParam(value = "tipoIdentificacionId", required = false) UUID tipoIdentificacionId,
        @RequestParam(value = "numeroIdentificacion", required = false) String numeroIdentificacion,
        @RequestParam(value = "nombresCompletos", required = false) String nombresCompletos
    ) {
        List<EstudianteEntity> resultados;
        if (tipoIdentificacionId != null && numeroIdentificacion != null) {
            resultados = repository.findByTipoIdentificacionIdAndNumeroIdentificacion(tipoIdentificacionId, numeroIdentificacion);
        } else if (tipoIdentificacionId != null) {
            resultados = repository.findByTipoIdentificacionId(tipoIdentificacionId);
        } else if (numeroIdentificacion != null) {
            resultados = repository.findByNumeroIdentificacion(numeroIdentificacion);
        } else if (nombresCompletos != null && !nombresCompletos.isBlank()) {
            resultados = repository.findByNombresCompletosContainingIgnoreCase(nombresCompletos);
        } else {
            resultados = repository.findAll();
        }
        return ResponseEntity.ok(resultados);
    }

}
