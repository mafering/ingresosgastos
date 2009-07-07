package es.antonio.duarte.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.datalist.HtmlDataList;

import es.antonio.duarte.model.IngresosGastos;
import es.antonio.duarte.servicios.IngresosGastosServicio;

/**
 * Clase que controla las operaciones CRUD de IngresosGastos
 * @author Antonio Duarte
 *
 */

public class IngresosGastosBean {
	
	/**
	 * Servicio para la consulta de ingresosgastos
	 */
	private IngresosGastosServicio servicio;
	
	/**
	 * Lista de IngresosGastos
	 */
	private DataModel listaIngresosGastos = new ListDataModel();
	
	/**
	 * Trazabilidad
	 */
	private static final Log LOG = LogFactory.getLog(IngresosGastosBean.class);
	
	/**
	 * Constante que indica que ha sido todo correcto
	 */
	private static final String SUCCESS = "success";
	
	/**
	    * Constante para la regla de navegacion.
	    */
	   public static final String FAILURE = "failure";
	
	/**
	 * El ingreso o gasto que se agrega o actualiza
	 */
	private IngresosGastos ingresogasto = new IngresosGastos();
	
	private List<SelectItem> tipos = new ArrayList<SelectItem>();
	
	/**
	 * Para indicar si se está cargando la listaIngresosGastos de una busqueda o no
	 */
	private boolean buscando = false;

	/**
	    * La fecha del ingresogasto a buscar.
	    */
	private Date fechaBusqueda;
	/**
	 * El tipo del ingresogasto a buscar.
	 */
	private String tipoBusqueda;
	
	
	/**
	 * @return Lista de IngresosGastos
	 */
	public DataModel getListaIngresosGastos() {
		if(!buscando){
			listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));			
		}
		return listaIngresosGastos;
	}
	
	/**
	 * @param listaIngresosGastos
	 * Lista de objetos de vista de IngresosGastos
	 */
	public void setListaIngresosGastos(DataModel listaIngresosGastos) {
		this.listaIngresosGastos = listaIngresosGastos;
	}
	
	/**
	 * @return El servicio que hace el CRUD de IngresosGastos
	 */
	public IngresosGastosServicio getServicio(){
		return servicio;
	}
	
	/**
	 * @param servicio
	 * Servicio de IngresosGastos
	 */
	public void setServicio(IngresosGastosServicio servicio){
		this.servicio = servicio;
	}
	
	/**
	 * @return La navegación a la que va después de que el usuario hace click en el consultar 
	 */
	public String consultar(){
		LOG.debug("En el metodo consultar() de IngresosGastosBean");
		
		listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));
		return SUCCESS;
	}
	
	/**
    *
    */
   public void consultarPorFecha() {
      listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarPorFecha(fechaBusqueda)));
   }
	
   /**
    * La consulta por tipo de ingresogasto.
    */
   public void consultarPorTipo() {
      listaIngresosGastos.setWrappedData(new ArrayList(servicio
               .consultarPorTipo(tipoBusqueda)));
   }
   
	
	/**
	    * @return La navegacion a la que se dirige despues de seleccionar un ingresogasto
	    *         para actualizar
	    */
	   public String actualizar() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo actualizar() de IngresosGastosBean");
	      }
	      ingresogasto = (IngresosGastos) listaIngresosGastos.getRowData();
	      LOG.info("El ingresogasto a actualizar es (" + ingresogasto.getId() + ") " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      return SUCCESS;
	   }

	   /**
	    * @return La navegacion a la que se dirige despues de actualizar un ingresogasto
	    *         con un nuevo valor
	    */
	   public String actualizarLista() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo actualizar() de IngresosGastosBean");
	      }
	      LOG.info("El ingresogasto a actualizar es " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      servicio.actualizar(ingresogasto);
	      FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(this.ingresogasto.getTipo() + " con ID " + ingresogasto.getId()
	                        + " actualizado correctamente."));
	      recargar();
	      return SUCCESS;
	   }

	   /**
	    * @return La navegacion a la cual se dirige despues de eliminar un ingresogasto
	    *         de la lista
	    */
	   public String eliminar() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo eliminar() de IngresosGastosBean");
	      }
	      ingresogasto = (IngresosGastos) listaIngresosGastos.getRowData();
	      LOG.info("El ingresogasto a eliminar es " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      servicio.eliminar(ingresogasto);
	      FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(this.ingresogasto.getTipo() + " con ID " + ingresogasto.getId()
	                        + " eliminado correctamente."));
	      recargar();
	      return SUCCESS;
	   }

	   /**
	    * @return La navegacion a la cual se dirige despues de insertar un ingreso gasto
	    *         nuevo
	    */
	   public String insertar() {
	      LOG.info("Entrando en el metodo insertar() de IngresosGastosBean con concepto: "
	               + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " con importe: " + ingresogasto.getCantidad());
	      if (ingresogasto.getConcepto().equalsIgnoreCase("")) {
	         FacesContext.getCurrentInstance().addMessage(null,
	                  new FacesMessage("El concepto es un campo requerido"));
	         recargar();
	         return FAILURE;
	      } else {
	         servicio.insertar(ingresogasto);
	         recargar();
	         return SUCCESS;
	      }
	   }
	
	   /**
	    * @return La navegacion a la que se dirige despues de seleccionar el enlace 
	    *         para insertar un ingreso gasto
	    */
	   public String aInsertar() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo aInsertar() de IngresosGastosBean");
	      }	      
	      LOG.info("Vamos a insertar un registro de tipo INGRESO o GASTO");
	      reset();
	      return SUCCESS;
	   }
	   
	 
	   /**
	    * @return La navegacion a la que va despues de buscar un ingresogastos por fecha
	    */
	   public String buscar() {
	      LOG.info("Entrando en el metodo buscar(), fecha=" + fechaBusqueda);
	      this.buscando = true;
	      if (fechaBusqueda != null) {
	         LOG.info("*** Fecha a buscar " + fechaBusqueda);
	         listaIngresosGastos.setWrappedData(new ArrayList(servicio
	                  .consultarPorFecha(fechaBusqueda)));
	      }else if (tipoBusqueda != null && !tipoBusqueda.equalsIgnoreCase("")) {
	          LOG.info("*** Tipo a buscar " + tipoBusqueda);
	          listaIngresosGastos.setWrappedData(new ArrayList(servicio
	                   .consultarPorTipo(tipoBusqueda)));
	      }else {
	         LOG.info("*** TODA la lista");
	         listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));
	      }
	      //reset();
	      return SUCCESS;
	   }
	   

	/**
	 * @return el ingresogasto actual, ya sea para actualizar o para modificar
	 */
	public IngresosGastos getIngresogasto() {
		return ingresogasto;
	}

	/**
	 * @param ingresogasto - el ingreso o gasto a actualizar o insertar
	 */
	public void setIngresogasto(IngresosGastos ingresogasto) {
		this.ingresogasto = ingresogasto;
	}
	
	
	/**
    *
    */
   private void recargar() {
	   if(!buscando){
		   listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));		   
	   }else{
		   
	   }
      this.ingresogasto = new IngresosGastos();
      this.tipos = new ArrayList<SelectItem>();
      this.fechaBusqueda = null;
      this.tipoBusqueda = null;
      this.buscando = false;
   }

   /**
    *
    */
   private void reset() {      
      this.ingresogasto = new IngresosGastos();
      this.tipos = new ArrayList<SelectItem>();
      this.fechaBusqueda = null;
      this.tipoBusqueda = null;
      this.buscando = false;
   }
   
	public List<SelectItem> getTipos() {
		this.tipos = new ArrayList<SelectItem>();
		this.tipos.add(new SelectItem("Ingreso"));
		this.tipos.add(new SelectItem("Gasto"));
		
		return tipos;
	}
	
	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}
	
	/**
	    * @return La fecha de busqueda que indroduce el usuario
	    */
	   public Date getFechaBusqueda() {
	      return fechaBusqueda;
	   }

	   /**
	    * @param fechaBusqueda Fecha del ingresogasto a buscar
	    */
	   public void setFechaBusqueda(final Date fechaBusqueda) {
	      this.fechaBusqueda = fechaBusqueda;
	   }

	public boolean isBuscando() {
		return buscando;
	}

	public void setBuscando(boolean buscando) {
		this.buscando = buscando;
	}

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	

}
