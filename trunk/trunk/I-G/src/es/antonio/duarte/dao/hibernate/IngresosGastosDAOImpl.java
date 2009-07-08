package es.antonio.duarte.dao.hibernate;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
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
	   
	   /**
	    * Consulta de ingresosgastos por alguno o todos los campos.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @param concepto Concepto por el cual se buscaran ingresosgastos
	    * @param cantidad Cantidad por la cual se buscaran ingresosgastos
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con alguno o todos los campos(vacia si no hay
	    *         registros)
	    */
	   public final Collection consultarPor(final Date fecha, final String tipo,
			   								final String cantidad, final String concepto) {
		   List resultados = null;
		   IngresosGastos ig = new IngresosGastos();
		   
		   		   Criteria criterios = getSessionFactory().getCurrentSession().createCriteria(
				   IngresosGastos.class);
		   if(fecha != null){
			   criterios = criterios.add(Expression.eq("fecha",fecha));			   
		   }
		   if(tipo != null && !tipo.equalsIgnoreCase("")){
			   criterios = criterios.add(Expression.eq("tipo",tipo));
		   }
		   if(cantidad != null && !cantidad.equalsIgnoreCase("")){
			   criterios = criterios.add(Expression.eq("cantidad",cantidad));
		   }
		   if(concepto != null && !concepto.equalsIgnoreCase("")){
			   criterios = criterios.add(Expression.like("concepto",concepto,MatchMode.ANYWHERE));			   
		   }
		   criterios.addOrder(Order.asc("fecha"))
		   .setFirstResult(0)
		   ;
		   resultados = criterios.list();
		   return resultados;
	   }
	   
	   /**
	    * Consulta de ingresosgastos por alguno o todos los campos.
	    * @param tipo Tipo por el cual se buscaran ingresosgastos
	    * @param concepto Concepto por el cual se buscaran ingresosgastos
	    * @param cantidad Cantidad por la cual se buscaran ingresosgastos
	    * @param fecha Fecha por la cual se buscaran ingresosgastos
	    * @return Lista de ingresosgastos que cumplen con alguno o todos los campos(vacia si no hay
	    *         registros)
	    */
	   public final Collection consultarMesAnyo(String mes, String anyo) {
		   List resultados = null;
		   IngresosGastos ig = new IngresosGastos();
		   int d = 1;
		   int a;
		   int m = GregorianCalendar.getInstance().get(Calendar.MONTH);
		   
		   if(anyo != null && !anyo.equalsIgnoreCase("")){
			   a = Integer.parseInt(anyo);
		   }else{
			   a = GregorianCalendar.getInstance().get(Calendar.YEAR);
		   }
		   
		   
		   Criteria criterios = getSessionFactory().getCurrentSession().createCriteria(
				   IngresosGastos.class);
		   
		   if (	(mes.equals("Enero")) ){
			   d = 31;
			   m = 0;
		   }else if((mes.equals("Febrero")) ){
			   m = 1;
			   if ( ( ( ( a % 4 ) == 0 ) && ( ( a % 100 ) ) != 100 ) || ( ( a % 400 ) == 0 ) ) {
				   d = 29;
			   }else{	
				   d = 28;
			   }			   
		   }else if((mes.equals("Marzo")) ){
			   d = 31;
			   m = 2;
		   }else if((mes.equals("Abril")) ){
			   d = 30;
			   m = 3;
		   }else if((mes.equals("Mayo")) ){
			   d = 31;
			   m = 4;
		   }else if((mes.equals("Junio")) ){
			   d = 30;
			   m = 5;
		   }else if((mes.equals("Julio")) ){
			   d = 31;
			   m = 6;			   
		   }else if((mes.equals("Agosto")) ){
			   d = 31;
			   m = 7;
		   }else if((mes.equals("Septiembre")) ){
			   d = 30;
			   m = 8;			   
		   }else if((mes.equals("Octubre")) ){
			   d = 31;
			   m = 9;
		   }else if((mes.equals("Noviembre")) ){
			   d = 30;
			   m = 10;
		   }else if((mes.equals("Diciembre")) ){
			   d = 31;
			   m = 11;			   
		   }
		   
		   Calendar fechaInicial = new GregorianCalendar(Integer.parseInt(anyo),m ,1);
		   Calendar fechaFinal = new GregorianCalendar(Integer.parseInt(anyo),m,d);	   
		   
		   //criterios = criterios.add(Expression.between("fecha", fechaInicial.getTime(), fechaFinal.getTime()));
		   criterios = criterios.add(Expression.ge("fecha", new java.sql.Date(fechaInicial.getTimeInMillis())));
		   criterios = criterios.add(Expression.le("fecha", new java.sql.Date(fechaFinal.getTimeInMillis())));
		   
		   criterios.addOrder(Order.asc("fecha"))
		   .setFirstResult(0)
		   ;
		   resultados = criterios.list();
		   return resultados;
	   }
	   
	

}
