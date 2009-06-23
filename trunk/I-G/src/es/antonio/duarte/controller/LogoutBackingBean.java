package es.antonio.duarte.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que invalida la sesión del usuario.
 * @author Nancy G Alemán
 */

public class LogoutBackingBean {

   /**
    * Constante para la regla de navegacion.
    */
   public static final String SUCCESS = "success";
   /**
    * Para la trazabilidad.
    */
   private static final Log LOG = LogFactory.getLog(LogoutBackingBean.class);

   /**
    * @return El nombre del id de navegación de la pagina de logout
    */
   public String logout() {
       FacesContext context = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
       session.invalidate();
       LOG.info("***** En logout() sesion invalidada");
       return SUCCESS;
   }
}
