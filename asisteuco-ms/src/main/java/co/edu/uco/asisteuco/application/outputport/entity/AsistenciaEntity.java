package co.edu.uco.asisteuco.application.outputport.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "asistencia")
public class AsistenciaEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_grupo_id", nullable = false)
    private EstudianteGrupoEntity estudianteGrupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sesion_id", nullable = false)
    private SesionEntity sesion;

    @Column(name = "asistio", nullable = false)
    private Boolean asistio;

    public AsistenciaEntity() { }

    public UUID getId() { return id; }

    public EstudianteGrupoEntity getEstudianteGrupo() {
        return estudianteGrupo;
    }
    public void setEstudianteGrupo(EstudianteGrupoEntity estudianteGrupo) {
        this.estudianteGrupo = estudianteGrupo;
    }

    public SesionEntity getSesion() {
        return sesion;
    }
    public void setSesion(SesionEntity sesion) {
        this.sesion = sesion;
    }

    public Boolean getAsistio() {
        return asistio;
    }
    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }
}
