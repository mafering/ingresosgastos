package es.antonio.duarte.exception;

/**
 * Clase que envuelve a las excepciones checked producidas en la
 * capa de Servicios para que puedan ser propagadas como
 * excepciones Runtime.
 * @author Antonio Duarte
 */
public class ServicioException extends GeneralException {

    /**
     * Para la serializacion.
     */
    private static final long serialVersionUID = 5736552648732227243L;

    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked y el mensaje hacia arriba.
     * @param mensaje Mensaje de seguimiento/error para la traza.
     * @param causa Checked Exception encapsulada.
     */
    public ServicioException(final String mensaje, final Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked hacia arriba.
     * @param causa Checked Exception encapsulada.
     */
    public ServicioException(final Throwable causa) {
        super(causa);
    }
}
