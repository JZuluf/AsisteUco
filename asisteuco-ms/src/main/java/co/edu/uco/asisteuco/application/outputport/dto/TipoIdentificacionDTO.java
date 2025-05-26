package co.edu.uco.asisteuco.application.outputport.dto;

import java.util.UUID;

public final class TipoIdentificacionDTO {

    private UUID uuid;
    private String clave;
    private String nombre;

    public TipoIdentificacionDTO() {
        super();
    }

    public TipoIdentificacionDTO(UUID uuid, String clave, String nombre) {
        this.uuid = uuid;
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

