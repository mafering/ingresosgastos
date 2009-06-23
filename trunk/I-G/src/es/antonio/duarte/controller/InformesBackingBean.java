package es.antonio.duarte.controller;

import java.io.IOException;
import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.endesa.arqame4j.parqweb.servicios.InformeServicio;

/**
 * Clase que controla generación del informe PDF y el logout.
 * @author Nancy G Alemán
 */
public class InformesBackingBean {

   /**
    * Trazabilidad
    */
   private static final Log LOG = LogFactory.getLog(InformesBackingBean.class);

   /**
    * El servicio de informes.
    */
   private InformeServicio informeServicio;

   /**
    * Genera un informe de mensajes en PDF.
    * @return El nombre del id de navegación al que regresa, se utiliza null
    *         para la misma página.
    */
   public String generarInforme() {
       LOG.info("Entrando al metodo generarInforme()");
       FacesContext facesContext = FacesContext.getCurrentInstance();
       HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
       if (null == response) {
           throw new AbortProcessingException("Response es null");
       }

       response.setContentType("application/pdf");
       response.setHeader("Content-Disposition",
               "attachment; filename=mensajes.pdf");
       response.setHeader("Cache-Control", "no-cache");
       ServletOutputStream sos = null;
       try {
           sos = response.getOutputStream();
           informeServicio.generaInformeMensaje(sos,FacesContext.getCurrentInstance().getViewRoot().getLocale());
           sos.flush();
       } catch (IOException e) {
           LOG.error("Problemas al volcar los datos al flujo " + e);
       } finally {
           if (sos != null) {
               try {
                   sos.close();
               } catch(IOException io) {
                   LOG.error("Problemas al cerrar el flujo " + io);
               }
           }
       } 
       facesContext.responseComplete();
       return null;
   }

   /**
    * @return Servicio para generar los informes PDF con Jasper
    */
   public InformeServicio getInformeServicio() {
       return informeServicio;
   }

   /**
    * @param informeServicio Servicio para generar los informes PDF con Jasper
    */
   public void setInformeServicio(InformeServicio informeServicio) {
       this.informeServicio = informeServicio;
   }

}
