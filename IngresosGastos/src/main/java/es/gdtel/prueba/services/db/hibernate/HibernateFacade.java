package es.gdtel.prueba.services.db.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public final class HibernateFacade extends HibernateService{

	public Log log = LogFactory.getLog(HibernateFacade.class);
	
	private Session session;
	private Transaction transaction;
	//Indica si se hace algo de forma transaccional o no
	private boolean esTransaccional = true;
	
	
	
	/**
	 * Inicia una sesi�n y una transacci�n de esa sesi�n.
	 */
	public void beginTransaction(){
		try{
			session = getHibernateSession();
			transaction = session.beginTransaction();
			esTransaccional = false;
		}catch(Exception e){
			log.error("Fallo al iniciar sesion. Causa: "+e.getMessage());
			throw new RuntimeException("Error al iniciar sesion de hibernate. ",e);
		}
	}
	/**
	 * Finaliza una sesi�n y una Transacci�n de una sesi�n que ya est�n inicializadas.
	 */
	public void endTransaction(){
		try{
			log.debug("Ejecutando transacciones ...");
			transaction.commit();
			log.debug("Transacciones ejecutadas con exito.");
		}catch(Exception e){
			transaction.rollback();
			log.error("Fallo al finalizar sesion. Causa: "+e.getMessage());
			throw new RuntimeException("Error al finalizar la sesion de hibernate. ",e);
		}finally{
			session.close();
			esTransaccional = true;
		}
	}
	/**
	 * Finaliza una sesi�n y una Transacci�n de una sesi�n en caso de que ya este inicializada. Se utiliza para controlar los errores.
	 */
	public void endNTSessionError(){
		try{
			if(transaction.isActive()) transaction.rollback();
			if(session.isOpen()) session.close();
			esTransaccional = true;
		}catch(Exception e){
			log.error("Fallo al finalizar sesion. Causa: "+e.getMessage());
			throw new RuntimeException("Error al finalizar la sesion de hibernate. ",e);
		}
	}
	/**
	 * Guarda un objeto en la base de datos 
	 * @param o Objeto que se quiere crear.
	 * @return Identificador del objeto creado
	 */
	public Serializable create(Object o){
		Serializable id = null;
		session = getHibernateSession();
		transaction = session.beginTransaction();
		try{
			id = session.save(o);
			transaction.commit();
		}catch(Exception e)
		{
			transaction.rollback();
			log.error("Fallo al guardar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al guardar en la base de datos. ",e);
		}finally{
			session.close();
		}
		
		return id;
	}
	/**
	 * Guarda un objeto en la BD. No tendr� efecto hasta finalizar la transacci�n.
	 * @param o Object objeto que se va a crear
	 * @return Identificador del objeto creado
	 */
	public Serializable NTCreate(Object o){
		Serializable id = null;
		try{
			id = session.save(o);
		}catch(Exception e)
		{
			log.error("Fallo al guardar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al guardar en la base de datos.",e);
		}
		
		return id;
	}
	/**
	 * Elimina un objeto de la base de datos
	 * @param o Objeto a eliminar. El atributo que identifica al objeto dentro de la tabla (clave primaria) no debe ser nulo.
	 * @return True si ha sido eliminado, False en caso contrario
	 */
	public boolean delete(Object o){
		session = getHibernateSession();
		transaction = session.beginTransaction();
		try{
			session.delete(o);
			transaction.commit();
		}catch(Exception e)
		{
			transaction.rollback();
			log.error("Fallo al eliminar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al eliminar en la base de datos. ",e);
			
		}finally{
			session.close();
		}
		
		return true;
	}
	/**
	 * Elimina un objeto de la base de datos. No tendr� efecto hasta finalizar la transacci�n.
	 * @param o Objeto a eliminar. El atributo que identifica al objeto dentro de la tabla (clave primaria) no debe ser nulo.
	 * @return True si ha sido eliminado, False en caso contrario
	 */
	public boolean NTDelete(Object o){
		try{
			session.delete(o);
			log.debug("Borrando objeto: "+o.toString());
		}catch(Exception e)
		{
			log.error("Fallo al eliminar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al eliminar en la base de datos. ",e);
		}
		
		return true;
	}
	/**
	 * Actualiza un registro de la base de datos
	 * @param o Objeto a actualizar. El atributo que identifica al objeto dentro de la tabla (clave primaria) no debe ser nulo.
	 * @return
	 */
	public boolean modify(Object o){
		session = getHibernateSession();
		transaction = session.beginTransaction();
		try{
			session.update(o);
			transaction.commit();
		}catch(Exception e)
		{
			transaction.rollback();
			log.error("Fallo al actualizar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al actualizar en la base de datos. ",e);
			
		}finally{
			session.close();
		}
		
		return true;
	}
	/**
	 * Actualiza un registro de la base de datos. No tendr� efecto hasta finalizar la transacci�n.
	 * @param o Objeto a actualizar. El atributo que identifica al objeto dentro de la tabla (clave primaria) no debe ser nulo.
	 * @return
	 */
	public boolean NTModify(Object o){
		try{
			session.update(o);
		}catch(Exception e)
		{
			log.error("Fallo al actualizar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al actualizar en la base de datos. ",e);
			
		}
		
		return true;
	}
	/**
	 * Consulta generica que devuelve una lista de resultados
	 * @param query String con la consulta a realizar en lenguaje HQL
	 * @param params HashMap con los pares clave-valor para cada par�metro de la consulta
	 * @return List con la lista de objetos de dominio devuelta por la consulta.
	 */
	public synchronized List genericQueryForList(String query, HashMap params){
		List result = null;
		
		try{
			if(esTransaccional) 
				session = getHibernateSession();
			if(params==null)
			{	
				//Si no hay parametros devolvemos directamente la lista
				result = session.createQuery(query).list();
			}else{
				//Si hay parametros los asignamos y devolvemos la lista
				Query q = session.createQuery(query);
				Collection claves = params.keySet();
				for(Iterator it=claves.iterator();it.hasNext();)
				{
					Object o = it.next();
					q.setParameter(o.toString(),params.get(o));
				}
				result = q.list();
				log.debug("Registros encontrados: "+result.size());
			}
		
		}catch(Exception e)
		{
			log.error("Fallo al consultar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al consultar en la base de datos. ",e);
			
		}finally{
			if(esTransaccional && session.isOpen()) 
				session.close();
		}
		
		return result;
	}
	/**
	 * Consulta gen�rica que devuelve un solo resultado.
	 * @param query String con la consulta a realizar en lenguaje HQL
	 * @param params HashMap con los pares clave-valor que se corresponden con los par�metros de la consulta
	 * @return Object objeto de dominio con los datos resultantes de la consulta. Null en caso de no encontrar ninguno.
	 */
	public synchronized Object genericQueryForObject(String query, HashMap params){
		Object result = null;
		
		try{
			if(esTransaccional) 
				session = getHibernateSession();
			if(params==null)
			{	
				//Si no hay parametros devolvemos directamente la lista
				List list = session.createQuery(query).list();
				result = (list.size()==0?null:list.get(0));
			}else{
				//Si hay parametros los asignamos y devolvemos la lista
				Query q = session.createQuery(query);
				Collection claves = params.keySet();
				for(Iterator it=claves.iterator();it.hasNext();)
				{
					Object o = it.next();
					q.setParameter(o.toString(),params.get(o));
				}
				List list = q.list();
				result = (list.size()==0?null:list.get(0));
				if(result!=null)
					log.debug("Registro encontrado.");
				else
					log.debug("No se ha encontrado ning�n registro.");
			}
		
		}catch(Exception e)
		{
			log.error("Fallo al consultar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al consultar en la base de datos. ",e);
			
		}finally{
			if(esTransaccional && session.isOpen()) 
				session.close();
		}
		
		return result;
	}
	/**
	 * Devuelve un objeto de la clase que se indique
	 * @param claseDominio Class clase de dominio del tipo que se quiere devolver
	 * @param id Long identificador (clave primaria) del registro que se quiere recuperar de la base de datos
	 * @return Object objeto devuelto con el identificador Id
	 */
	public Object getObject(Class claseDominio, Long id){
		Object result = null;
		
		try{
			if(esTransaccional) 
				session = getHibernateSession();
			result = session.get(claseDominio, id);
		
		}catch(Exception e)
		{
			log.error("Fallo al consultar. Causa: "+e.getMessage());
			throw new RuntimeException("Error al consultar en la base de datos. ",e);
			
		}finally{
			if(esTransaccional && session.isOpen()) 
				session.close();
		}
		
		return result;
	}
	/**
	 * Ejecuta directamente una sentencia HQL que se le pasa como parametro.
	 * @param query Sentencia HQL
	 * @param params Parametros para mapear en la sentencia
	 * @return
	 */
	public int executeQuery(String query, HashMap params)
	{
		try{
			session = getHibernateSession();
			if(params==null)
			{	
				return session.createQuery(query).executeUpdate();
			}else{
				//Si hay parametros los asignamos y devolvemos la lista
				Query q = session.createQuery(query);
				Collection claves = params.keySet();
				for(Iterator it=claves.iterator();it.hasNext();)
				{
					Object o = it.next();
					q.setParameter(o.toString(),params.get(o));
				}
				
				return q.executeUpdate();
			}
		
		}catch(Exception e)
		{
			log.error("Fallo al ejecutar el query. Causa: "+e.getMessage());
			throw new RuntimeException("Error al ejecutar sentencia sql. ",e);			
		}finally{
			session.close();
		}
	}
	/**
	 * Ejecuta directamente una sentencia HQL que se le pasa como parametro en un ambito no transaccional.
	 * @param query Sentencia HQL
	 * @param params Parametros para mapear en la sentencia
	 * @return
	 */
	public int executeNTQuery(String query, HashMap params)
	{
		try{
			if(params==null)
			{	
				return session.createQuery(query).executeUpdate();
			}else{
				//Si hay parametros los asignamos y devolvemos la lista
				Query q = session.createQuery(query);
				Collection claves = params.keySet();
				for(Iterator it=claves.iterator();it.hasNext();)
				{
					Object o = it.next();
					q.setParameter(o.toString(),params.get(o));
				}
				
				return q.executeUpdate();
			}
		
		}catch(Exception e)
		{
			log.error("Fallo al ejecutar el query. Causa: "+e.getMessage());
			throw new RuntimeException("Error al ejecutar sentencia sql. ",e);
			
		}
	}
	
}
