package co.edu.uco.asisteuco.application.interactor.tipoidentificacion.consultartipoidentificacion.dto.request;

import java.util.UUID;

public final class ConsultarTipoIdentificacionRequestDTO {

    private UUID uuid;
    private String clave;
    private String nombre;

    public ConsultarTipoIdentificacionRequestDTO() {
		super();
	}

	public ConsultarTipoIdentificacionRequestDTO(final String clave,final String nombre) {
		super();
		this.clave = clave;
		this.nombre = nombre;
	}

	public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}