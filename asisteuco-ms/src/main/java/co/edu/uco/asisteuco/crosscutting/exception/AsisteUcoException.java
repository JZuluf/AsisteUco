package co.edu.uco.asisteuco.crosscutting.exception;

/**
 * Excepción personalizada para manejar errores de negocio controlados dentro de la aplicación.
 * Al heredar de RuntimeException, se convierte en una excepción "no chequeada".
 */
public final class AsisteUcoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    // El mensaje que se le puede mostrar de forma segura al usuario final.
    private final String mensajeUsuario;

    /**
     * Constructor principal.
     * @param mensajeUsuario Mensaje para el usuario (ej: "La clave ya existe").
     * @param mensajeTecnico Mensaje para el log del desarrollador (este se pasa a la clase padre).
     * @param causa La excepción original que provocó este error.
     */
    public AsisteUcoException(String mensajeUsuario, String mensajeTecnico, Throwable causa) {
        // ✅ La llamada a super() es la PRIMERA línea, cumpliendo la regla de Java.
        super(mensajeTecnico, causa); 
        this.mensajeUsuario = mensajeUsuario;
    }
    
    /**
     * Constructor de conveniencia sin una excepción raíz.
     * @param mensajeUsuario Mensaje para el usuario.
     * @param mensajeTecnico Mensaje para el log.
     */
    public AsisteUcoException(String mensajeUsuario, String mensajeTecnico) {
        // ✅ La llamada a this() también debe ser la primera línea.
        this(mensajeUsuario, mensajeTecnico, null);
    }
    
    /**
     * Constructor más simple donde el mensaje de usuario y técnico son el mismo.
     * @param mensajeUsuario Mensaje para el usuario y para el log.
     */
    public AsisteUcoException(String mensajeUsuario) {
        // ✅ La llamada a this() es la primera línea.
        this(mensajeUsuario, mensajeUsuario, null);
    }

    /**
     * Devuelve el mensaje diseñado para ser mostrado al usuario final.
     * @return El mensaje de usuario.
     */
    public String getMensajeUsuario() {
        return mensajeUsuario;
    }
}