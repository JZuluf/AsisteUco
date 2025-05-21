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

@Entity
@Table(name = "EstudianteGrupo")
public class EstudianteGrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoEntity grupo;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;

    public EstudianteGrupoEntity() {
        setDefaultId();
        setDefaultGrupo();
        setDefaultEstudiante();
    }

    public EstudianteGrupoEntity(UUID id) {
        this.setId(id); 
        setDefaultGrupo();
        setDefaultEstudiante();
    }

    public EstudianteGrupoEntity(final UUID id, final GrupoEntity grupo, final EstudianteEntity estudiante) {
        this.setId(id);         
        this.setGrupo(grupo);    
        this.setEstudiante(estudiante); 
    }


    private void setDefaultId() {
        this.id = UUID.randomUUID();
        setId(id);
    }

    private void setDefaultGrupo() {
        this.grupo = new GrupoEntity();
		setGrupo(grupo);
    }

    private void setDefaultEstudiante() {
        this.estudiante = new EstudianteEntity();
        setEstudiante(estudiante);
    }

    // --- Getters y Setters públicos ---

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(final GrupoEntity grupo) {
        this.grupo = grupo;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(final EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }
}