package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.asistencia;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.RegistrarAsistenciaInteractor;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request.RegistrarAsistenciaRequestDTO;
import co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.response.RegistrarAsistenciaResponseDTO;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    private final RegistrarAsistenciaInteractor registrarAsistencia;

    public AsistenciaController(RegistrarAsistenciaInteractor registrarAsistencia) {
        this.registrarAsistencia = registrarAsistencia;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarAsistenciaResponseDTO>> registrar(
            @Valid @RequestBody RegistrarAsistenciaRequestDTO requestDTO,
            BindingResult bindingResult) {
        
        RespuestaApi<RegistrarAsistenciaResponseDTO> api = new RespuestaApi<>();
        HttpStatus status = HttpStatus.CREATED;

        // 1) Validación de DTO
        if (bindingResult.hasErrors()) {
            api.setTransaccionExitosa(false);
            String errores = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            api.getMensajes().add("Errores en la solicitud: " + errores);
            return ResponseEntity.badRequest().body(api);
        }

        try {
            // 2) Llamada al interactor
            RegistrarAsistenciaResponseDTO resultado = registrarAsistencia.ejecutar(requestDTO);
            api.setTransaccionExitosa(true);
            api.getDatos().add(resultado);
            api.getMensajes().add("Asistencias registradas exitosamente.");
        } catch (AsisteUcoException ex) {
            // 3) Errores de negocio
            status = HttpStatus.BAD_REQUEST;
            api.setTransaccionExitosa(false);
            api.getMensajes().clear();
            api.getMensajes().add(ex.getMensajeUsuario());
        } catch (Exception ex) {
            // 4) Cualquier otro error
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            api.setTransaccionExitosa(false);
            api.getMensajes().clear();
            api.getMensajes().add("Error interno al registrar asistencias.");
        }

        return new ResponseEntity<>(api, status);
    }
}
