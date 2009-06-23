package es.antonio.duarte.dao.hibernate;

import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import es.antonio.duarte.dao.DAOGeneral;

/**
 * Implementacion del DAO de Mensajes con Hibernate.
 * @author Antonio Duarte
 */

public class DAOGeneralImpl implements DAOGeneral {
   /**
    * Cadena para mostrar en las excepciones.
    */
   private String mensaje = "Debe proporcionar un Objeto para llamar a este método";
   /**
    * Factoria de hibernate, desde donde se obtiene la sesion de Trabajo.
    */
   private SessionFactory sessionFactory;

   /**
    * La factoria de hibernate para obtener las sesiones.
    * @return SessionFactory
    */
   public final SessionFactory getSessionFactory() {
      return sessionFactory;
   }

   /**
    * Sera usado por spring para inyectar la factoria de hibernate.
    * @param sessionFactoryi La factoria de hibernate.
    */
   public final void setSessionFactory(final SessionFactory sessionFactoryi) {
      this.sessionFactory = sessionFactoryi;
   }

   /**
    * Inserta la entidad proporcionada.
    * @param entidad Entidad que se desea insertar a la BBDD
    * @return ID del registro insertado
    */
   public final long insertar(final Object entidad) {
      if (entidad == null) {
         throw new IllegalArgumentException(mensaje);
      }
      sessionFactory.getCurrentSession().save(entidad);
      return 1;
   }

   /**
    * Actualiza la entidad proporcionada.
    * @param entidad Entidad que se desea actualizar en la BBDD
    * @return Numero de registros modificados
    */
   public final int actualizar(final Object entidad) {
      if (entidad == null) {
         throw new IllegalArgumentException(mensaje);
      }
      sessionFactory.getCurrentSession().update(entidad);
      return 1;
   }

   /**
    * Elimina la entidad proporcionada.
    * @param entidad Entidad que se desea eliminar de la BBDD
    * @return Numero de registros modificados
    */
   public final int eliminar(final Object entidad) {
      if (entidad == null) {
         throw new IllegalArgumentException(mensaje);
      }
      sessionFactory.getCurrentSession().delete(entidad);
      return 1;
   }

   /**
    * Consulta de todos los mensajes.
    * @param objeto El objeto con la información para la consulta. Este
    *           parámetro variable, y si viene es el tipo de objeto que vamos a
    *           buscar.
    * @return Lista de mensajes (vacia si no hay registros)
    */
   public final Collection consultar(final Object... objeto) {

      if (objeto.length == 0) {
         throw new IllegalArgumentException(mensaje);
      }

      List resultados = null;

      Criteria criterios = sessionFactory.getCurrentSession().createCriteria(
               (Class) objeto[0]);
      resultados = criterios.list();
      return resultados;

   }

   /**
    * Obtiene el objjeto asociado al id proporcionado.
    * @param id El id del objeto a recuperar.
    * @param clase Tipo de objetoa a recuperar
    * @return Objeto recuperado de la base de datos
    */
   public final Object consultarPorId(final Long id, final Class... clase) {
      if (id == null) {
         throw new IllegalArgumentException(mensaje);
      }
      if (clase.length == 0) {
         throw new IllegalArgumentException(mensaje);
      }
      return sessionFactory.getCurrentSession().get(clase[0], id);
   }

}
