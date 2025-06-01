package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.sesion;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.ConsultarSesionInteractor;
import co.edu.uco.asisteuco.application.interactor.sesion.consultarsesion.dto.request.ConsultarSesionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.RegistrarSesionInteractor;
import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.request.RegistrarSesionRequestDTO;
import co.edu.uco.asisteuco.application.interactor.sesion.registrarsesion.dto.response.RegistrarSesionResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.SesionDTO;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;

@RestController
@RequestMapping("/api/v1/sesiones")
@CrossOrigin(origins = "*")
public class SesionController {

    private final RegistrarSesionInteractor registrar;
    private final ConsultarSesionInteractor consultar;

    public SesionController(
        RegistrarSesionInteractor registrar,
        ConsultarSesionInteractor consultar
    ) {
        this.registrar = registrar;
        this.consultar = consultar;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarSesionResponseDTO>> registrar(
            @RequestBody RegistrarSesionRequestDTO req) {
        var api    = new RespuestaApi<RegistrarSesionResponseDTO>();
        var status = HttpStatus.CREATED;

        try {
            var data = registrar.ejecutar(req);
            api.getDatos().add(data);
            api.getMensajes().add("Sesión creada exitosamente.");
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            api.getMensajes().add(ex.getMessage());
        }

        return new ResponseEntity<>(api, status);
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<SesionDTO>> listar(
            @RequestParam(value = "id",      required = false) UUID id,
            @RequestParam(value = "grupoId", required = false) UUID grupoId) {

        var req  = new ConsultarSesionRequestDTO();
        req.setId(id);
        req.setGrupoId(grupoId);

        var resp = consultar.ejecutar(req);
        var status = resp.isTransaccionExitosa()
                   ? HttpStatus.OK
                   : HttpStatus.NOT_FOUND;

        var api = new RespuestaApi<SesionDTO>();
        api.setTransaccionExitosa(resp.isTransaccionExitosa());
        api.setMensajes(resp.getMensajes());
        api.setDatos(resp.getResultados());
        return new ResponseEntity<>(api, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<SesionDTO>> obtenerPorId(@PathVariable UUID id) {
        var req  = new ConsultarSesionRequestDTO();
        req.setId(id);

        var resp = consultar.ejecutar(req);
        var api  = new RespuestaApi<SesionDTO>();

        if (resp.isTransaccionExitosa() && !resp.getResultados().isEmpty()) {
            api.setTransaccionExitosa(true);
            api.getDatos().add((SesionDTO) resp.getResultados().get(0));
            api.getMensajes().add("Sesión encontrada.");
            return ResponseEntity.ok(api);
        } else {
            api.setTransaccionExitosa(false);
            api.getMensajes().add("No se encontró sesión con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(api);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<Void>> eliminar(@PathVariable UUID id) {
        var api = new RespuestaApi<Void>();
        // si quieres implementar borrado físico:
        // sesionRepo.deleteById(id);
        api.getMensajes().add("Funcionalidad de eliminación no implementada aún.");
        return ResponseEntity.ok(api);
    }
}
