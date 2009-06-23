package es.antonio.duarte.servicios;

import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;

import es.antonio.duarte.model.IngresosGastos;
import es.antonio.duarte.servicios.informes.Informe;

/**
 * Implementacion del servicio de generacion de informes de la plantilla.
 * @author Antonio Duarte
 */
public class InformeServicioImpl implements InformeServicio {

   /**
    * Servicio para IngresosGastos.
    */
   private IngresosGastosServicio ingresosGastosServicio;


   /**
    * Clase report para el informe de los mensajes a generar.
    */
   private Informe informeIngresosGastos;

   /**
    * Genera el informe de todos los ingresosgastos.
    * Genera el informe donde le indiquemos.
    * @param locale Locale para el informe.
    */
   public final void generaInformeIngresosGastos(final Locale locale) {
      informeIngresosGastos.setJrDataSource(ingresosGastosServicio.consultar(IngresosGastos.class));
      informeIngresosGastos.setLocale(locale);
      informeIngresosGastos.generar();
   }

   /**
    * Genera el informe de todos los ingresosgastos.
    * @param outStr flujo de salida en el que se escribe el fichero generado.
    * Este parámetro se le pasa ya creado y la responsabilidad de
    * cerrarlo es del que lo ha creado.
    * @param locale Locale para el informe.
    */
   public final void generaInformeIngresosGastos(OutputStream outStr,
                                                       final Locale locale) {
	   informeIngresosGastos.setJrDataSource(ingresosGastosServicio.consultar(IngresosGastos.class));
	   informeIngresosGastos.setLocale(locale);
	   informeIngresosGastos.generar(outStr);
   }

   /**
    * Genera el informe de todos los ingresosgastos.
    * @param locale Locale para el informe.
    * @return informe en forma de array de bytes
    */
   public final byte[] generaInformeIngresosGastosEnBytes(final Locale locale) {
	   informeIngresosGastos.setJrDataSource(ingresosGastosServicio.consultar(IngresosGastos.class));
	   informeIngresosGastos.setLocale(locale);
      return informeIngresosGastos.generarEnBytes();
   }


   /**
    * Genera el informe de todos los ingresosgastos con tal fecha.
    * Genera el informe donde le indiquemos.
    * @param fecha Fecha a buscar
    * @param locale Locale para el informe.
   public final void generaInformeIngresosGastosPorFecha(final String fecha,
                                                       final Locale locale) {
      informeIngresosGastos.setJrDataSource(
                              ingresosGastosServicio.consultarPorFecha(fecha));
      informeIngresosGastos.setLocale(locale);
      informeIngresosGastos.generar();
   }
    */

   /**
    * Genera el informe de todos los mensajes con tal descripcion.
    * @param descripcion Descripcion a buscar
    * @param outStr flujo de salida en el que se escribe el fichero generado.
    * Este parámetro se le pasa ya creado y la responsabilidad de
    * cerrarlo es del que lo ha creado.
    * @param locale Locale para el informe.
   public final void generaInformeMensajePorDesc(final String descripcion,
                                OutputStream outStr, final Locale locale) {
      informeMensaje.setJrDataSource(mensajesServicio.consultarPorDesc(
                                                              descripcion));
      informeMensaje.setLocale(locale);
      informeMensaje.generar(outStr);
   }
    */

   /**
    * Genera el informe de todos los mensajes con tal descripcion.
    * @param descripcion Descripcion a buscar
    * @param locale Locale para el informe.
    * @return informe en forma de array de bytes
   public final byte[] generaInformeMensajePorDescEnBytes(
                               final String descripcion, final Locale locale) {
       informeMensaje.setJrDataSource(
                               mensajesServicio.consultarPorDesc(descripcion));
       informeMensaje.setLocale(locale);
       return informeMensaje.generarEnBytes();
   }
    */

    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * Genera el informe donde le indiquemos.
     * @param fecha a buscar
     * @param locale Locale para el informe.
    public final void generaInformeMensajePorFecha(final Date fecha,
                                                        final Locale locale) {
       informeMensaje.setJrDataSource(
                                   mensajesServicio.consultarPorFecha(fecha));
       informeMensaje.setLocale(locale);
       informeMensaje.generar();
    }
     */

    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * @param fecha a buscar
     * @param outStr flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de
     * cerrarlo es del que lo ha creado.
     * @param locale Locale para el informe.
    public final void generaInformeMensajePorFecha(final Date fecha,
                                     OutputStream outStr, final Locale locale) {
       informeMensaje.setJrDataSource(
                                   mensajesServicio.consultarPorFecha(fecha));
       informeMensaje.setLocale(locale);
       informeMensaje.generar(outStr);
    }
     */

    /**
     * Genera el informe de todos los mensajes con tal fecha.
     * @param fecha a buscar
     * @param locale Locale para el informe.
     * @return informe en forma de array de bytes
     public final byte[] generaInformeMensajePorFechaEnBytes(final Date fecha,
                                                         final Locale locale) {
         informeMensaje.setJrDataSource(
                                     mensajesServicio.consultarPorFecha(fecha));
         informeMensaje.setLocale(locale);
         return informeMensaje.generarEnBytes();
     }
     */

    /**
     * Obtiene el servicio de ingresosgastos.
     * @return Servicio de ingresosgastos.
     */
    public final IngresosGastosServicio getIngresosGastosServicio() {
       return ingresosGastosServicio;
    }

    /**
     * Modifica el servicio de ingresosgastos.
     * @param newIGServicio nuevo Servicio de ingresosgastos.
     */
    public final void setIngresosGastosServicios(
                                        final IngresosGastosServicio newIGServicio) {
       this.ingresosGastosServicio = newIGServicio;
    }

    /**
     * Obtiene el informe de mensajes.
     * @return informeMensaje informe de mensajes
     */
    public final Informe getInformeIngresosGastos() {
       return informeIngresosGastos;
    }

    /**
     * Modifica el informe de ingresosgastos.
     * @param newInformeIngresosGastos informe para ingresosgastos
     */
    public final void setInformeIngresosGastos(final Informe newInformeIngresosGastos) {
       this.informeIngresosGastos = newInformeIngresosGastos;
    }

}
