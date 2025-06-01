package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.profesor;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.ConsultarProfesorInteractor;
import co.edu.uco.asisteuco.application.interactor.profesor.consultarprofesor.dto.request.ConsultarProfesorRequestDTO;
import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.RegistrarProfesorInteractor;
import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.request.RegistrarProfesorRequestDTO;
import co.edu.uco.asisteuco.application.interactor.profesor.registrarprofesor.dto.response.RegistrarProfesorResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.ProfesorDTO;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

@RestController
@RequestMapping("/api/v1/profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {

    private static final Logger logger = LoggerFactory.getLogger(ProfesorController.class);

    private final RegistrarProfesorInteractor registrar;
    private final ConsultarProfesorInteractor consultar;

    public ProfesorController(
        RegistrarProfesorInteractor registrar,
        ConsultarProfesorInteractor consultar
    ) {
        this.registrar  = registrar;
        this.consultar  = consultar;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarProfesorResponseDTO>> registrar(
            @RequestBody RegistrarProfesorRequestDTO requestDTO) {
        RespuestaApi<RegistrarProfesorResponseDTO> api = new RespuestaApi<>();
        HttpStatus status = HttpStatus.CREATED;

        try {
            var data = registrar.ejecutar(requestDTO);
            api.getDatos().add(data);
            api.getMensajes().add("Profesor registrado exitosamente.");
        } catch (AsisteUcoException ex) {
            status = HttpStatus.BAD_REQUEST;
            api.getMensajes().clear();
            api.getMensajes().add(ex.getMensajeUsuario());
            logger.error("Error al registrar profesor: {}", ex.getMessage(), ex);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            api.getMensajes().clear();
            api.getMensajes().add("Error inesperado. Contacte al administrador.");
            logger.error("Error inesperado en ProfesorController", ex);
        }

        return new ResponseEntity<>(api, status);
    }

    /**
     * GET /api/v1/profesores
     * Listado con filtros opcionales: tipoIdentificacionId, numeroIdentificacion, nombresCompletos.
     */
    @GetMapping
    public ResponseEntity<RespuestaApi<ProfesorDTO>> listar(
            @RequestParam(value = "tipoIdentificacionId", required = false) UUID tipoId,
            @RequestParam(value = "numeroIdentificacion",   required = false) String numero,
            @RequestParam(value = "nombresCompletos",       required = false) String nombres) {

        // Construyo el DTO de consulta
        var req = new ConsultarProfesorRequestDTO();
        req.setTipoIdentificacionId(tipoId);
        req.setNumeroIdentificacion(numero);
        req.setNombresCompletos(nombres);

        // Ejecuto la consulta
        var resp = consultar.ejecutar(req);

        // Armo la respuesta
        RespuestaApi<ProfesorDTO> api = new RespuestaApi<>();
        api.setTransaccionExitosa(resp.isTransaccionExitosa());
        api.setMensajes(resp.getMensajes());
        api.setDatos(resp.getResultados());

        HttpStatus status = resp.isTransaccionExitosa() 
                         ? HttpStatus.OK 
                         : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(api, status);
    }

    /**
     * GET /api/v1/profesores/{id}
     * Recupera un único profesor por su UUID o devuelve 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<ProfesorDTO>> obtenerPorId(@PathVariable UUID id) {
        // Reutilizamos el mismo interactor, solo seteamos el ID
        var req  = new ConsultarProfesorRequestDTO();
        req.setId(id);
        var resp = consultar.ejecutar(req);

        RespuestaApi<ProfesorDTO> api = new RespuestaApi<>();
        if (resp.isTransaccionExitosa() && !resp.getResultados().isEmpty()) {
            api.setTransaccionExitosa(true);
            api.getDatos().add((ProfesorDTO) resp.getResultados().get(0));
            api.getMensajes().add("Profesor encontrado correctamente.");
            return ResponseEntity.ok(api);
        } else {
            api.setTransaccionExitosa(false);
            api.getMensajes().add("No se encontró profesor con ID: " + id);
            return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        }
    }
}
