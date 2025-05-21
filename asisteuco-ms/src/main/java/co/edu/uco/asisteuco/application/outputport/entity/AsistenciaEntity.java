package co.edu.uco.asisteuco.application.outputport.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; 
import jakarta.persistence.FetchType; // Para controlar la carga de relaciones

@Entity
@Table(name = "asistencia")
public class AsistenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) // Una asistencia pertenece a una sesión
    @JoinColumn(name = "sesion_id", nullable = false) // Columna de clave foránea para la sesión
    private SesionEntity sesion;

    @ManyToOne(fetch = FetchType.LAZY) // Una asistencia pertenece a un estudiante
    @JoinColumn(name = "estudiante_id", nullable = false) // Columna de clave foránea para el estudiante
    private EstudianteEntity estudiante;

    @Column(name = "asistio", nullable = false) // Para marcar si el estudiante asistió (true) o no (false)
    private boolean asistio;

    // Constructor por defecto (requerido por JPA)
    public AsistenciaEntity() {
    }

    // Constructor con campos (opcional, pero útil)
    public AsistenciaEntity(SesionEntity sesion, EstudianteEntity estudiante, boolean asistio) {
        this.sesion = sesion;
        this.estudiante = estudiante;
        this.asistio = asistio;
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SesionEntity getSesion() {
        return sesion;
    }

    public void setSesion(SesionEntity sesion) {
        this.sesion = sesion;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public boolean isAsistio() { 
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}