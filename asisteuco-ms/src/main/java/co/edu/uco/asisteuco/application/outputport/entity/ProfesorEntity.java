package co.edu.uco.asisteuco.application.outputport.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "profesor")
public class ProfesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_identificacion", nullable = false)
    private TipoIdentificacionEntity tipoIdentificacion;

    @Column(name = "numero_identificacion", length = 20, nullable = false)
    private String numeroIdentificacion;

    @Column(name = "nombres_completos", length = 100, nullable = false)
    private String nombresCompletos;

    public ProfesorEntity() { }

    public ProfesorEntity(TipoIdentificacionEntity tipo, String numero, String nombres) {
        this.tipoIdentificacion = tipo;
        this.numeroIdentificacion = numero;
        this.nombresCompletos = nombres;
    }

    // --- Getters y setters ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public TipoIdentificacionEntity getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombresCompletos() { return nombresCompletos; }
    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}