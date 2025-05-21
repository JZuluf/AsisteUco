package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Estudiante;
import co.edu.uco.asisteuco.application.outputport.repository.AsistenciaRepository;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.RegistrarAsistenciaUseCase;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Asistencia;
import co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.RegistrarAsistenciaResponseVO;
import co.edu.uco.asisteuco.application.usecase.estudiante.validator.ValidarQueEstudianteExista;
import co.edu.uco.asisteuco.application.usecase.sesion.validator.ValidarQueSesionExista;

@Service
public class RegistrarAsistenciaUseCaseImpl implements RegistrarAsistenciaUseCase {

    private ValidarQueEstudianteExista estudianteExiste;
    private ValidarQueSesionExista sesionExiste;
    private AsistenciaRepository asistenciaRepository;
    private RegistrarAsistenciaResponseVO resultado;

    public RegistrarAsistenciaUseCaseImpl(AsistenciaRepository asistenciaRepository,
            ValidarQueEstudianteExista estudianteExiste, ValidarQueSesionExista sesionExiste) {
        this.asistenciaRepository = asistenciaRepository;
        this.estudianteExiste = estudianteExiste;
        this.sesionExiste = sesionExiste;
        resultado = new RegistrarAsistenciaResponseVO();
    }


    @Override
    public RegistrarAsistenciaResponseVO ejecutar(Asistencia dominio) {
    	// 1. Validar integridad del objeto a nivel de tipo de datos, longitud, 

    	// obligatoriedad, formato, rango, etc.}


    	// 2. Las sesion debe existir

    	if (resultado.isValidacionCorrecta()) {

    	resultado.agregarMensajes(sesionExiste.validate(dominio.getSesion().getId()).getMensajes());

    	}


    	// 3. El profesor debe existir

    	if (resultado.isValidacionCorrecta()) {

    	}


    	// 4. El grupo debe estar activo

    	if (resultado.isValidacionCorrecta()) {

    	}


    	// 5. El profesor debe estar asignado al grupo

    	if (resultado.isValidacionCorrecta()) {

    	}


    	// 6. No se puede tener una asistencia ya registrada para la misma sesion

    	if (resultado.isValidacionCorrecta()) {

    	}


    	// 7. La asistencia se debe registrar entre los plazos de tiempo establecidos

    	if (resultado.isValidacionCorrecta()) {

    	}

        // 8. Validar que estudiantes sean consistentes para el registro de asistencia
        if (resultado.isValidacionCorrecta()) {
            // dominio.getEstudiantes() devuelve List<co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Estudiante>
            registrarAsistenciaEstudiantes(dominio.getEstudiantes()); // ¡Ahora los tipos coinciden!
        }

        //9. Retornar Resultado de la validacion
        return resultado;
    }

    // Cambiado el tipo del parámetro a List<Estudiante> (del dominio)
    private void registrarAsistenciaEstudiantes(List<Estudiante> estudiantes) {

        for (var estudiante : estudiantes) { // estudiante es ahora de tipo co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain.Estudiante

            var registrarAsistenciaResponseEstudianteVO = new RegistrarAsistenciaResponseVO();

            //1. Validar que el estudiante exista
            // Asegúrate que tu domain.Estudiante tenga el método getId() que devuelva UUID
            ValidarQueEstudianteExista(estudiante.getId());

            //2. Validar que el estudiante este registrado en el grupo
            if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
                //Validar que el estudiante este registrado en el grupo
            }

            //3. validar que el estudiante no tenga la materia cancelada por alguna novedad
            if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
                //Validar que el estudiante no tenga la materia cancelada por alguna novedad
            }

            // 4. registrar la asistencia por cada estudiante
            if (registrarAsistenciaResponseEstudianteVO.isValidacionCorrecta()) {
                registrarAsistenciaEstudiante(estudiante); // Se pasa un objeto domain.Estudiante
            }

            resultado.agregarMensajes(registrarAsistenciaResponseEstudianteVO.getMensajes());
        }
    }

    private void ValidarQueEstudianteExista(UUID idEstudiante) { // Convención: validarQueEstudianteExista
        resultado.agregarMensajes(estudianteExiste.validate(idEstudiante).getMensajes());
    }


    // Cambiado el tipo del parámetro a Estudiante (del dominio)
    private void registrarAsistenciaEstudiante(Estudiante estudiante) {
        //1. Registrar asistencia
        // Aquí va la lógica para registrar la asistencia del estudiante (domain.Estudiante)

        // Asegúrate que tu domain.Estudiante tenga el método isAsistio() o equivalente
        if (!estudiante.isAsistio()) {
            //2. Enviar correo al estudiante que no asistió
        }
    }
}