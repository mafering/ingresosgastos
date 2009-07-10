package es.antonio.duarte.servicios;

import java.util.Collection;
import java.util.Date;

/**
 * Interfaz del servicio de IngresosGastos de la plantilla arquitectonica 
 * @author Antonio Duarte
 *
 */

@SuppressWarnings("unchecked")
public interface IngresosGastosServicio extends ServicioAntonioDuarte{
	
	/**
	    * Consulta de ingresosgastos por fecha.
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con la fecha (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarPorFecha(Date fecha);
	   
	   /**
	    * Consulta de ingresosgastos por tipo.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con el tipo (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarPorTipo(String tipo);
	   
	   /**
	    * Consulta de ingresosgastos por cualquiera o todos los campos.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @param cantidad Cantidad por la cual se buscaran ingresosgastos
	    * @param concepto Concepto por al cual se buscaran ingresosgastos
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con los campos buscados (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarPor(Date fecha, String tipo, String cantidad, String concepto);
	
	   /**
	    * Consulta de ingresosgastos por un mes y año.
	    * @param mes Mes por el cual se buscaran ingresosgastos
	    * @param anyo Año por el cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con el mes y año especificado (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarMesAnyo(String mes, String anyo);
	   
	   /**
	    * Se realizara el calculo de los ingresos o los gastos para cada mes del año pasado
	    * @param anyo Año para el que se calculan los ingresos
	    * @param tipo Tipo para el que se calculan los totales (Ingreso o Gasto)
	    * @return Lista con el total de ingresos o gastos para cada mes del año 
	    */
	   public Collection calcularIngresosGastosAnuales(String anyo,String tipo);
	   

	   
}
