package es.gdtel.prueba.services.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public abstract class HibernateService {
	
	private static String HIBERNATE_CONFIG = "es/gdtel/hibernate/hibernate.cfg.xml";	
	private static SessionFactory sessionFactory = new Configuration().configure(HIBERNATE_CONFIG).buildSessionFactory();
	/**
	 * Devuelve el objecto sessionFactory para poder abrir una sesi�n de Hibernate
	 * @return
	 */
	/*protected SessionFactory getHibernateSession() {
		return (SessionFactory) ActionContext.getContext().getApplication().get(HIBERNATE_SESSION);
	}*/
	
	protected Session getHibernateSession() {
		Session sessionDecorada = new HibernateSession(sessionFactory.openSession());
		
		return sessionDecorada;
	}
}
