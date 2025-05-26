package co.edu.uco.asisteuco.application.outputport.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
    name = "estudiantes",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"tipo_identificacion_id", "numero_identificacion"}
    )
)
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "tipo_identificacion_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_estudiante_tipo_identificacion")
    )
    private TipoIdentificacionEntity tipoIdentificacion;

    @Column(name = "numero_identificacion", nullable = false, length = 50)
    private String numeroIdentificacion;

    @Column(name = "nombres_completos", nullable = false, length = 200)
    private String nombresCompletos;

    /**
     * Constructor protegido para JPA
     */
    
    public EstudianteEntity() {
        // Constructor para JPA
    }

    /**
     * Constructor de negocio
     *
     * @param tipoIdentificacion Entidad de tipo de identificación (no nulo)
     * @param numeroIdentificacion Número de identificación (no nulo)
     * @param nombresCompletos Nombres completos del estudiante (no nulo)
     */
    
    public EstudianteEntity(
        TipoIdentificacionEntity tipoIdentificacion,
        String numeroIdentificacion,
        String nombresCompletos
    ) {
        this.id = UUID.randomUUID();
        this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, "tipoIdentificacion no puede ser nulo");
        this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, "numeroIdentificacion no puede ser nulo");
        this.nombresCompletos = Objects.requireNonNull(nombresCompletos, "nombresCompletos no puede ser nulo");
    }

    // --- Getters y Setters ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoIdentificacionEntity getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
        this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, "tipoIdentificacion no puede ser nulo");
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, "numeroIdentificacion no puede ser nulo");
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = Objects.requireNonNull(nombresCompletos, "nombresCompletos no puede ser nulo");
    }
}
