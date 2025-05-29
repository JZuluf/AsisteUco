package co.edu.uco.asisteuco.application.outputport.entity;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "grupo")
public class GrupoEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private ProfesorEntity profesor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaEntity materia;

    @Column(name = "cantidad_estudiantes", nullable = false)
    private int cantidadEstudiantes;

    public GrupoEntity() { }

    public GrupoEntity(UUID id, ProfesorEntity profesor, MateriaEntity materia, int cantidadEstudiantes) {
        this.id = id;
        this.profesor = profesor;
        this.materia = materia;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProfesorEntity getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = profesor;
    }

    public MateriaEntity getMateria() {
        return materia;
    }

    public void setMateria(MateriaEntity materia) {
        this.materia = materia;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }
}
