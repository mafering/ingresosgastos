package es.antonio.duarte.exception;

/**
 * Clase que envuelve a las excepciones checked producidas en la
 * capa de los DAO's para que puedan ser propagadas como
 * excepciones Runtime.
 * @author Antonio Duarte
 */
public class DaoException extends GeneralException {

    /**
     * Para la serializacion.
     */
    private static final long serialVersionUID = 3960543616512353270L;


    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked y el mensaje hacia arriba.
     * @param mensaje Mensaje de seguimiento/error para la traza.
     * @param causa Checked Exception encapsulada.
     */
    public DaoException(final String mensaje, final Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Invoca al constructor de GeneralException pasando
     * la excepcion checked hacia arriba.
     * @param causa Checked Exception encapsulada.
     */
    public DaoException(final Throwable causa) {
        super(causa);
    }
}
