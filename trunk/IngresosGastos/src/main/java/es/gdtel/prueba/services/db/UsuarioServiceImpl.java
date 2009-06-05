package es.gdtel.prueba.services.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.gdtel.prueba.domain.Jsf;
import es.gdtel.prueba.services.UsuarioService;
import es.gdtel.prueba.services.db.hibernate.HibernateFacade;

public class UsuarioServiceImpl implements UsuarioService {

	private static Log log = LogFactory.getLog(UsuarioServiceImpl.class);
	
	//objeto con los servicios obtenidos del EntityManager de jpa
	private HibernateFacade hibernateFacade = new HibernateFacade();

	
	public List<Jsf> cargarListaUsuarios() {

		List<Jsf> personas = hibernateFacade.genericQueryForList("from Jsf",null);

		return personas;
	}

	public void incorporarPersona(Jsf objeto) {

		hibernateFacade.create(objeto);
	}

	public void eliminarPersona(Jsf persona) {

		hibernateFacade.delete(persona);
		
	}

}
