package es.gdtel.prueba.services;

import java.util.List;

import es.gdtel.prueba.domain.Jsf;

public interface UsuarioService {
	
	/**
	 * Devuelve la lista actual de personas.
	 * 
	 * @return Lista de tipo personas
	 */
	public List<Jsf> cargarListaUsuarios();

	public void incorporarPersona(Jsf persona);

	public void eliminarPersona(Jsf Persona);
}
