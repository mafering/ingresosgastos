package es.antonio.duarte.exception;

import java.io.Serializable;


/**
 * Clase que envuelve a las excepciones checked producidas
 * para que puedan ser propagadas como excepciones Runtime.
 * @author Antonio Duarte
 */
public class GeneralException extends RuntimeException implements Serializable {

    /**
     * Para la serializacion.
     */
    private static final long serialVersionUID = -9052559874544628529L;

    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked y el mensaje hacia arriba.
     * @param mensaje Mensaje de seguimiento/error para la traza.
     * @param causa Checked Exception encapsulada.
     */
    public GeneralException(final String mensaje, final Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked hacia arriba.
     * @param causa Checked Exception encapsulada.
     */
    public GeneralException(final Throwable causa) {
        super(causa);
    }
}
