package co.edu.uco.asisteuco.application.interactor.asistencia.registrarasistencia.dto.request;

import java.util.List;
import java.util.UUID;

public final class RegistrarAsistenciaRequestDTO {

    private UUID sesion;
    private UUID profesor;
    private List<Estudiante> estudiantes;

    public RegistrarAsistenciaRequestDTO() {
        super();
    }


    public UUID getSesion() { 
        return sesion;
    }

    public void setSesion(final UUID sesion) { 
        this.sesion = sesion;
    }

    public UUID getProfesor() { 
        return profesor;
    }

    public void setProfesor(final UUID profesor) { 
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() { 
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) { 
        this.estudiantes = estudiantes;
    }

    
    public static class Estudiante { 
        private UUID id;
        private boolean asistio;

        public Estudiante() {
            super();
        }

        // Constructor con argumentos (Jackson usualmente prefiere el constructor sin argumentos y setters)
        public Estudiante(final UUID id, final boolean asistio) {
            this.id = id; // Usar this.id para referirse al campo de la instancia
            this.asistio = asistio; // Usar this.asistio
        }

        public UUID getId() {
            return id;
        }

        public void setId(final UUID id) {
            this.id = id;
        }
        

        public boolean isAsistio() {
            return asistio;
        }

        public void setAsistio(final boolean asistio) {
            this.asistio = asistio;
        }
    }
}