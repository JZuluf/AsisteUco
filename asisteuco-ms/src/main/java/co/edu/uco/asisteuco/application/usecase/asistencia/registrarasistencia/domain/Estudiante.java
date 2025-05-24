package co.edu.uco.asisteuco.application.usecase.asistencia.registrarasistencia.domain;

import java.util.UUID;

public final class Estudiante {
    private UUID id;
    private String nombresCompletos; // Ejemplo de otros campos que podría tener
    // private TipoIdentificacionDomain tipoIdentificacion; // Ejemplo
    private boolean asistio; // Si decides que el objeto de dominio Estudiante lleve este estado

    // --- Constructor que coincide con la llamada actual ---
    public Estudiante(UUID id) {
        this.id = id;
        // Aquí podrías inicializar otros campos a valores por defecto si es necesario,
        // o dejarlos para que se establezcan mediante setters si el mapeo es más complejo.
    }

    // --- Podrías tener un constructor más completo ---
    // public Estudiante(UUID id, String nombresCompletos /*, otros campos de EstudianteEntity... */) {
    //    this.id = id;
    //    this.nombresCompletos = nombresCompletos;
    //    // ... inicializar otros campos ...
    // }

    // --- Getters ---
    public UUID getId() {
        return id;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    // --- Setters (ejemplo) ---
    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    // --- Para manejar 'asistio' ---
    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}