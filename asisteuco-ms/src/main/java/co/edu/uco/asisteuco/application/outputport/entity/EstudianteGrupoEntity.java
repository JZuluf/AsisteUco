package co.edu.uco.asisteuco.application.outputport.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "estudiante_grupo")
public class EstudianteGrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private EstudianteEntity estudiante;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoEntity grupo;

    public EstudianteGrupoEntity() { }

    public EstudianteGrupoEntity(final GrupoEntity grupo, final EstudianteEntity estudiante) {
        this.grupo = grupo;
        this.estudiante = estudiante;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }
    public void setGrupo(GrupoEntity grupo) {
        this.grupo = grupo;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }
}
