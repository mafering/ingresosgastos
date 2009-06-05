package es.gdtel.prueba.bean;


import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import es.gdtel.afirma.Autenticacion;



public class LoginBean{

	private String usuario;
	private String clave;
	private String nombreCompleto;
	private Autenticacion autenticacion;
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String login() {
		if ("prueba".equals(usuario)
				&& "prueba".equals(clave)) {
			nombreCompleto = "Prueba Prueba";
			return "ok";
		} else {
			return "error";
		}
	}

	
	public String logout() {
		usuario = "";
		clave = "";
		nombreCompleto = "";
		autenticacion = null;
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
		return "logout";
	}
	
	public String getUrlautenticacion () {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return Autenticacion.getURLAutenticacion(session.getId());
	}

	public boolean getAutenticar () {
		String sessionId = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getId();
		String datos = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("datos");
		NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		autenticacion = new Autenticacion();
		String resultado = "logout";
		if (autenticacion.autenticar(datos, sessionId)) {
			nombreCompleto = autenticacion.getNombreCompleto();
			resultado = "ok";
		}
		if (!FacesContext.getCurrentInstance().getResponseComplete()) {
			navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, resultado);
		}
		return true;
	}
}
