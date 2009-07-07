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
	   
}
