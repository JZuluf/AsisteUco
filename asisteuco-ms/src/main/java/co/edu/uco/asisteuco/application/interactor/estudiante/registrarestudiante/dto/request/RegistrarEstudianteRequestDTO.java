package co.edu.uco.asisteuco.application.interactor.estudiante.registrarestudiante.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;

public final class RegistrarEstudianteRequestDTO {

    private UUID uuid;
    private UUID tipo_identificacion;
    private String numero_identificacion;
    private String nombres_completos;
    @Email
    private String email; 

    // Constructor vacío
    public RegistrarEstudianteRequestDTO() {
    	super();
    }

    // Constructor con parámetros
    public RegistrarEstudianteRequestDTO(UUID uuid, UUID tipo_identificacion, String numero_identificacion, String nombres_completos, String email) {
        this.uuid = uuid;
        this.tipo_identificacion = tipo_identificacion;
        this.numero_identificacion = numero_identificacion;
        this.nombres_completos = nombres_completos;
        this.email = email;
    }

    // Getters y Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getTipoIdentificacion() {
        return tipo_identificacion;
    }

    public void setTipoIdentificacion(UUID tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getNumeroIdentificacion() {
        return numero_identificacion;
    }

    public void setNumeroIdentificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getNombresCompletos() {
        return nombres_completos;
    }

    public void setNombresCompletos(String nombres_completos) {
        this.nombres_completos = nombres_completos;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
