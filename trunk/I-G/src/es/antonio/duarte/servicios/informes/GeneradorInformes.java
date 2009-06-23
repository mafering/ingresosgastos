package es.antonio.duarte.servicios.informes;

import java.io.OutputStream;
import java.util.Collection;

/**
 * Interfaz de la clase encargada de la generacion de informes.
 * @author Antonio Duarte
 */
public interface GeneradorInformes {

    /**
     * Genera un informe con nombre y tipo proporcionados por el objeto informe.
     * Genera el informe en el directorio dado.
     * @param informe Objeto informe
     */
    void generarInforme(final Informe informe);

    /**
     * Genera un informe con nombre y tipo proporcionados por el objeto informe.
     * Genera el informe en un array de bytes.
     * @param informe para generar.
     * @return informe en forma de array de bytes
     */
    byte[] generarInformeEnBytes(final Informe informe);

    /**
     * Genera informes con nombre y tipo proporcionados por cada uno de los
     * objeto informe que vienen en la coleccion.
     * Genera los informes en el directorio dado.
     * @param informes Coleccion de objetos informe
     */
    void generarInformes(final Collection<Informe> informes);

    /**
     * Genera informes y los vuelca en el flujo de salida que le pasamos como
     * parametro.
     *
     * @param informe objeto informe
     * @param outs flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     */
    void generarInforme(final Informe informe, OutputStream outs);
}
