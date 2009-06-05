package es.gdtel.prueba.bean;

import java.util.List;

import es.gdtel.prueba.domain.Jsf;
import es.gdtel.prueba.services.UsuarioService;
import es.gdtel.prueba.services.db.UsuarioServiceImpl;

public class DataBean {

	private String nombre;
	private String apellidos;
	private List<Jsf> listaNombres;
	
	private UsuarioService userService;
	
	
	public DataBean() {
		listaNombres = getUserService().cargarListaUsuarios();
	}

	public String nuevo() {
		
		Jsf usuario = new Jsf();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		
		getUserService().incorporarPersona(usuario);
		listaNombres.add(usuario);
		
		return "list";
	}
	
	public UsuarioService getUserService(){
		
		if(userService==null){
			userService = new UsuarioServiceImpl();
		}
		
		return userService;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public java.lang.String getNombre() {
		return nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

	public List<Jsf> getListaNombres() {
		return listaNombres;
	}

	public void setListaNombres(List<Jsf> listaNombres) {
		this.listaNombres = listaNombres;
	}
}
