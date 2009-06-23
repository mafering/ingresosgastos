package es.antonio.duarte.util;

import java.util.Locale;

import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.facelets.FaceletViewHandler;

public class VistasIGFacelets extends FaceletViewHandler{

	public VistasIGFacelets(ViewHandler parent) {
		super(parent);
	}

	 /**
	 * Para obtener el locale de la sesion de la aplicacion.
	 * @author Antonio Duarte
	 * @param context El contexto de jsf.
	 * @return Locale El Locale que fija el idioma de la aplicacion. 
	 */

	    @Override
	    public Locale calculateLocale(FacesContext context) {
	              
	       HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
	       if (session != null) {
	            Locale locale = (Locale) session.getAttribute("lenguaje");
	            if (locale != null) {
	                 return locale;
	               }
	          }
	            return super.calculateLocale(context);
	        }
	  }
