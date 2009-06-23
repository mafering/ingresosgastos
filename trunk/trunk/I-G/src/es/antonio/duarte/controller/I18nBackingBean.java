package es.antonio.duarte.controller;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que controla la internacionalizacion, la generación del informe PDF y
 * el logout.
 * @author NGA
 */
public class I18nBackingBean {

    /**
     * Trazabilidad
     */
    private static final Log LOG = LogFactory.getLog(I18nBackingBean.class);
    /**
     * Constante de que ha sido correcto todo
     */
    private static final String SUCCESS = "success";

    /**
     * La clase que nos permitira ver la aplicacion en español.
     */
    private static  Locale spain = new Locale("es");
    /**
     * Cambia el Locale a idioma español.
     * @return El nombre del id de navegación al que regresa, se utiliza null
     *         para la misma página.
     */
    public String cambiarEsp() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute("lenguaje", spain);
        }
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(spain);

        LOG.debug("Cambiando el locale a Espaniol");
        return null;
    }

    /**
     * Cambia el Locale a idioma ingles.
     * @return El nombre del id de navegación al que regresa, se utiliza null
     *         para la misma página.
     */
    public String cambiarIng() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute("lenguaje", Locale.ENGLISH);
        }
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale( Locale.ENGLISH );

        LOG.debug("Cambiando el locale a Ingles");
        return null;
    }

}
