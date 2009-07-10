package es.antonio.duarte.dao;

import java.util.Collection;
import java.util.Date;

/**
 * DAO de IngresosGastos 
 * @author Antonio Duarte
 *
 */

public interface IngresosGastosDAO extends DAOGeneral{

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
	    * Consulta de ingresosgastos por alguno o todos los campos.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @param concepto Concepto por el cual se buscaran ingresosgastos
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @param cantidad Cantidad por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con alguno o todos los campos (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarPor(Date fecha, String tipo, String cantidad, String concepto);
	   
	   /**
	    * Consulta de ingresosgastos por el mes y año
	    * @param mes Mes por el cual se buscaran ingresosgastos
	    * @param anyo Año por el cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con el mes y anyo especificado (vacia si no hay
	    *         registros)
	    */
	   public Collection consultarMesAnyo(String mes, String anyo);
	   
	   /**
	    * Calcular ingresos o gastos totales por mes del año
	    * @param anyo Año por el cual se calcularan los ingresos o gastos totales
	    * @param tipo Tipo por el cual se calcularan los ingresos o gastos totales
	    * @return Lista de ingresos o gastos de todos los meses para el anyo especificado (vacia si no hay
	    *         registros)
	    */
	   public Collection calcularIngresosGastosAnuales(String anyo,String tipo);

	   
	   
}
