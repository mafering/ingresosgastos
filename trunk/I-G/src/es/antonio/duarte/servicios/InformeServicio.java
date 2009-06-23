package es.antonio.duarte.servicios;

import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;

/**
 * Interfaz del servicio de generacion de informes.
 * @author Antonio Duarte
 */
public interface InformeServicio {

    /**
     * Genera un informe con todos los ingresosgastos.
     * Genera el informe en el directorio que determinemos.
     * @param locale Locale para el informe.
     */
    void generaInformeIngresosGastos(Locale locale);

    /**
     * Genera un informe con todos los ingresosgastos.
     * @param outStr flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     * @param locale Locale para el informe.
     */
    void generaInformeIngresosGastos(OutputStream outStr, Locale locale);

    /**
     * Genera un informe con todos los ingresosgastos.
     * @param locale Locale para el informe.
     * @return informe en forma de array de bytes
     */
    byte[] generaInformeIngresosGastosEnBytes(Locale locale);


    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * Genera el informe en el directorio que determinemos.
     * @param fecha a buscar
     * @param locale Locale para el informe.
    void generaInformeMensajePorFecha(final Date fecha, Locale locale);
     */

    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * @param fecha a buscar
     * @param outStr flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     * @param locale Locale para el informe.
    void generaInformeMensajePorFecha(final Date fecha, OutputStream outStr,
                                                                Locale locale);
     */

    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * @param fecha a buscar
     * @param locale Locale para el informe.
     * @return informe en forma de array de bytes
    byte[] generaInformeMensajePorFechaEnBytes(final Date fecha, Locale locale);
     */

    /**
     * Genera el informe de todos los mensajes con tal descripcion.
     * Genera el informe en el directorio que determinemos.
     * @param descripcion a buscar
     * @param locale Locale para el informe.
    void generaInformeMensajePorDesc(final String descripcion, Locale locale);
     */

    /**
     * Genera el informe de todos los mensajes con tal descripcion.
     * @param descripcion a buscar
     * @param outStr flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     * @param locale Locale para el informe.
    void generaInformeMensajePorDesc(final String descripcion,
                                            OutputStream outStr, Locale locale);
     */

    /**
     * Genera el informe de todos los mensajes con tal descripcion.
     * @param descripcion a buscar
     * @param locale Locale para el informe.
     * @return informe en forma de array de bytes
    byte[] generaInformeMensajePorDescEnBytes(final String descripcion,
                                                                Locale locale);
     */
}
