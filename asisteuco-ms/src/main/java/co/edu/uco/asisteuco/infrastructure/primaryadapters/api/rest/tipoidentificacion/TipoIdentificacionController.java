package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.tipoidentificacion;

// --- Imports de Spring Framework ---
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// --- Imports de tu aplicación ---
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.RegistrarTipoIdentificacionInteractor;
import co.edu.uco.asisteuco.application.interactor.tipoidentificacion.dto.request.RegistrarTipoIdentificacionRequestDTO;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException; 
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi; 

@RestController
@RequestMapping("/api/v1/tipo-identificacion")
@CrossOrigin(origins = "*") // Para desarrollo. En producción, limita los orígenes.
public class TipoIdentificacionController {

    // Inicializa un logger para registrar eventos y errores. Es una práctica estándar.
    private static final Logger logger = LoggerFactory.getLogger(TipoIdentificacionController.class);

    private final RegistrarTipoIdentificacionInteractor registrarInteractor;
    // Aquí podrías inyectar los otros casos de uso (consultar, modificar, etc.)
    // private final ConsultarTipoIdentificacionInteractor consultarInteractor;

    public TipoIdentificacionController(RegistrarTipoIdentificacionInteractor registrarInteractor) {
        this.registrarInteractor = registrarInteractor;
    }

    /**
     * Endpoint para registrar un nuevo tipo de identificación.
     * Método HTTP: POST
     * URL: /api/v1/tipo-identificacion
     *
     * @param requestDTO El objeto con los datos del nuevo tipo de identificación.
     * @return Una respuesta HTTP con el resultado de la operación.
     */
    @PostMapping
    public ResponseEntity<RespuestaApi<String>> registrar(@RequestBody RegistrarTipoIdentificacionRequestDTO requestDTO) {

        final RespuestaApi<String> respuesta = new RespuestaApi<>();
        HttpStatus httpStatus = HttpStatus.CREATED; // Por defecto, el estado es 201 Creado

        try {
            // 1. Se delega la lógica al caso de uso correspondiente.
            registrarInteractor.ejecutar(requestDTO);

            // 2. Si la ejecución termina sin excepciones, la operación fue exitosa.
            respuesta.getMensajes().add("El tipo de identificación se ha registrado exitosamente.");

        } catch (final AsisteUcoException excepcion) {
            // 3. Se captura una excepción de negocio controlada (ej: clave duplicada).
            httpStatus = HttpStatus.BAD_REQUEST; // 400 Solicitud incorrecta
            respuesta.getMensajes().add(excepcion.getMensajeUsuario());
            logger.error("Error de negocio al registrar tipo de identificación: {}", excepcion.getMessage(), excepcion);

        } catch (final Exception excepcion) {
            // 4. Se captura cualquier otro error inesperado (ej: fallo de conexión a la BD).
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // 500 Error interno del servidor
            respuesta.getMensajes().add("Ocurrió un problema inesperado. Por favor, contacte al administrador del sistema.");
            logger.error("Un error inesperado ha ocurrido al registrar un tipo de identificación.", excepcion);
        }

        // 5. Se construye y retorna la respuesta HTTP final.
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    /**
     * Endpoint para consultar todos los tipos de identificación.
     * Método HTTP: GET
     * URL: /api/v1/tipo-identificacion
     */
    @GetMapping
    public ResponseEntity<?> consultarTodos() {
        // TODO: Implementar la lógica para llamar al caso de uso de consulta.
        // La estructura try-catch sería muy similar a la del método registrar.
        return new ResponseEntity<>("Endpoint de consulta todos pendiente", HttpStatus.OK);
    }
    
    /**
     * Endpoint para consultar un tipo de identificación por su ID.
     * Método HTTP: GET
     * URL: /api/v1/tipo-identificacion/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable("id") String id) {
        // TODO: Implementar la lógica.
        // Importante: Si no se encuentra el ID, se debe devolver un HttpStatus.NOT_FOUND (404).
        return new ResponseEntity<>("Endpoint de consulta por ID pendiente", HttpStatus.OK);
    }
}