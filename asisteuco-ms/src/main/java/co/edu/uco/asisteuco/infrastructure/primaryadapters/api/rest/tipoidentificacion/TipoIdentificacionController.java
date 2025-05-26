package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.tipoidentificacion;

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

import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.RegistrarTipoIdentificacionInteractor;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.registrartipoidentificacion.dto.request.RegistrarTipoIdentificacionRequestDTO;
import co.edu.uco.asisteuco.application.outputport.entity.TipoIdentificacionEntity;

import co.edu.uco.asisteuco.application.outputport.repository.TipoIdentificacionRepository;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

@RestController
@RequestMapping("/api/v1/tipo-identificacion")
@CrossOrigin(origins = "*")
public class TipoIdentificacionController {

    private static final Logger logger = LoggerFactory.getLogger(TipoIdentificacionController.class);

    private final RegistrarTipoIdentificacionInteractor registrarInteractor;
    private final TipoIdentificacionRepository repository; 

    public TipoIdentificacionController(RegistrarTipoIdentificacionInteractor registrarInteractor,
                                        TipoIdentificacionRepository repository) {
        this.registrarInteractor = registrarInteractor;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<String>> registrar(@RequestBody RegistrarTipoIdentificacionRequestDTO requestDTO) {
        final RespuestaApi<String> respuesta = new RespuestaApi<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
            registrarInteractor.ejecutar(requestDTO);
            respuesta.getMensajes().add("El tipo de identificación se ha registrado exitosamente.");
        } catch (final AsisteUcoException excepcion) {
            httpStatus = HttpStatus.BAD_REQUEST;
            respuesta.getMensajes().add(excepcion.getMensajeUsuario());
            logger.error("Error de negocio al registrar tipo de identificación: {}", excepcion.getMessage(), excepcion);
        } catch (final Exception excepcion) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            respuesta.getMensajes().add("Ocurrió un problema inesperado. Por favor, contacte al administrador del sistema.");
            logger.error("Un error inesperado ha ocurrido al registrar un tipo de identificación.", excepcion);
        }

        return new ResponseEntity<>(respuesta, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarPorId(@PathVariable("id") UUID id) {
        return repository.findById(id)
                .<ResponseEntity<Object>>map(tipo -> ResponseEntity.ok(tipo))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró tipo de identificación con ID: " + id));
    }

    /**
     * Consulta por filtros opcionales: clave, nombre o ambos.
     * Si no se pasan filtros, devuelve todos.
     */
    
    @GetMapping
    public ResponseEntity<List<TipoIdentificacionEntity>> consultarPorFiltros(
            @RequestParam(required = false) String clave,
            @RequestParam(required = false) String nombre) {
        
        List<TipoIdentificacionEntity> resultados;

        if (clave != null && nombre != null) {
            resultados = repository.findByClaveAndNombre(clave, nombre);
        } else if (clave != null) {
            resultados = repository.findByClave(clave);
        } else if (nombre != null) {
            resultados = repository.findByNombre(nombre);
        } else {
            resultados = repository.findAll();
        }

        return ResponseEntity.ok(resultados);
    }

}
