package co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.grupo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.ConsultarGrupoInteractor;
import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.request.ConsultarGrupoRequestDTO;
import co.edu.uco.asisteuco.application.interactor.grupo.consultargrupo.dto.response.ConsultarGrupoResponseDTO;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.RegistrarGrupoInteractor;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.dto.request.RegistrarGrupoRequestDTO;
import co.edu.uco.asisteuco.application.interactor.grupo.registrargrupo.dto.response.RegistrarGrupoResponseDTO;
import co.edu.uco.asisteuco.application.outputport.dto.GrupoDTO;
import co.edu.uco.asisteuco.crosscutting.exception.AsisteUcoException;
import co.edu.uco.asisteuco.infrastructure.primaryadapters.api.rest.response.RespuestaApi;


@RestController
@RequestMapping("/api/v1/grupo")
@CrossOrigin(origins = "*")
public class GrupoController {

    private final RegistrarGrupoInteractor registrar;
    private final ConsultarGrupoInteractor   consultar;

    @Autowired
    public GrupoController(RegistrarGrupoInteractor registrar,
                           ConsultarGrupoInteractor consultar) {
        this.registrar = registrar;
        this.consultar = consultar;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<RegistrarGrupoResponseDTO>> registrar(
            @RequestBody RegistrarGrupoRequestDTO request) {

        var api = new RespuestaApi<RegistrarGrupoResponseDTO>();
        HttpStatus status = HttpStatus.CREATED;

        try {
            var data = registrar.ejecutar(request);
            api.getDatos().add(data);
            api.getMensajes().add("Grupo registrado exitosamente.");
        } catch (AsisteUcoException ex) {
            status = HttpStatus.BAD_REQUEST;
            api.getMensajes().add(ex.getMensajeUsuario());
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            api.getMensajes().add("Error inesperado. Contacte al administrador.");
        }

        return new ResponseEntity<>(api, status);
    }


	@GetMapping
    public ResponseEntity<RespuestaApi<GrupoDTO>> listar(
            @RequestParam(value = "profesorId", required = false) UUID profesorId,
            @RequestParam(value = "materiaId",  required = false) UUID  materiaId) {

        var req = new ConsultarGrupoRequestDTO();
        req.setProfesorId(profesorId);
        req.setMateriaId( materiaId);

        var resp = consultar.ejecutar(req);
        var api  = new RespuestaApi<GrupoDTO>();
        api.setTransaccionExitosa(resp.isTransaccionExitosa());
        api.setMensajes(resp.getMensajes());
        api.setDatos(resp.getResultados());

        // Si falla y no es porque no había filtros (es malo), devolvemos 404
        var status = resp.isTransaccionExitosa() 
                   ? HttpStatus.OK 
                   : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(api, status);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<GrupoDTO>> obtenerPorId(@PathVariable UUID id) {
        var api = new RespuestaApi<GrupoDTO>();

        var req  = new ConsultarGrupoRequestDTO();
        req.setId(id);

        ConsultarGrupoResponseDTO<GrupoDTO> resp = consultar.ejecutar(req);

        if (resp.isTransaccionExitosa() && !resp.getResultados().isEmpty()) {
            api.setTransaccionExitosa(true);
            api.getDatos().add(resp.getResultados().get(0));
            api.getMensajes().add("Grupo encontrado.");
            return ResponseEntity.ok(api);

        } else {
            api.setTransaccionExitosa(false);
            api.getMensajes()
               .add("No se encontró grupo con ID: " + id);
            return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(api);
        }
    }
}
