package es.antonio.duarte.dao.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import es.antonio.duarte.dao.IngresosGastosDAO;
import es.antonio.duarte.model.IngresosGastos;

/**
 * Implementacion del DAO de IngresosGastos con Hibernate
 * @author Antonio Duarte
 *
 */

public class IngresosGastosDAOImpl extends DAOGeneralImpl implements IngresosGastosDAO{
	
	
		/**
	    * Consulta de ingresosgastos por fecha.
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con la fecha (vacia si no hay
	    *         registros)
	    */
	   public final Collection consultarPorFecha(final Date fecha) {
	      List resultados = null;
	      IngresosGastos ig = new IngresosGastos();
	      ig.setFecha(fecha);
	      Criteria criterios = getSessionFactory().getCurrentSession().createCriteria(
	               IngresosGastos.class);
	      //criterios = criterios.add(Example.create(ig));
	      criterios = criterios.add(Expression.eq("fecha",fecha))
	      							.addOrder(Order.asc("fecha") )
	    		  					.setFirstResult(0);

	      resultados = criterios.list();
	      return resultados;
	   }
	   
	   
	   /**
	    * Consulta de ingresosgastos por tipo.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con el tipo (vacia si no hay
	    *         registros)
	    */
	   public final Collection consultarPorTipo(final String tipo) {
	      List resultados = null;
	      IngresosGastos ig = new IngresosGastos();
	      ig.setTipo(tipo);
	      Criteria criterios = getSessionFactory().getCurrentSession().createCriteria(
	               IngresosGastos.class);
	      criterios = criterios.add(Expression.eq("tipo",tipo))
	      							.addOrder(Order.asc("fecha"))
	      							.setFirstResult(0)
	      				;
	      resultados = criterios.list();
	      return resultados;
	   }
	   
	

}
