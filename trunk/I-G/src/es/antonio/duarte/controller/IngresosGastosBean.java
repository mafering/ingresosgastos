package es.antonio.duarte.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	/**
	 * La lista de tipos que se compone Ingresos y Gastos 
	 */
	private List<SelectItem> tipos = new ArrayList<SelectItem>();
	
	/**
	 * La lista de meses  
	 */
	private List<SelectItem> meses = new ArrayList<SelectItem>();
	
	/**
	 * La lista de años  
	 */
	private List<SelectItem> anyos = new ArrayList<SelectItem>();
	
	/**
	 * Para indicar si se está cargando la listaIngresosGastos de una busqueda o no
	 */
	private boolean buscando = false;
	
	/**
	 * Para indicar si se está consultando la listaIngresosGastos de un mes y año concretos 
	 * diferentes al actual
	 */
	private boolean consultando = false;

	/**
	    * La fecha del ingresogasto a buscar.
	    */
	private Date fechaBusqueda;
	/**
	 * El tipo del ingresogasto a buscar.
	 */
	private String tipoBusqueda;
	
	/**
	 * El concepto del ingresogasto a buscar.
	 */
	private String conceptoBusqueda;
	
	/**
	 * La cantidad del ingresogasto a buscar.
	 */
	private String cantidadBusqueda;
	
	/**
	 * El mes a consultar.
	 */
	private String mesConsulta;
	
	/**
	 * El anyo a consultar.
	 */
	private String anyoConsulta;
	
	/**
	 * Meses
	 */
	private String [] arrayMeses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
													"Septiembre","Octubre","Noviembre","Diciembre"};

	
	/**
	 * Ingresos Totales 
	 */
	private BigDecimal ingresosTotales = new BigDecimal(0);
	
	/**
	 * Gastos Totales
	 */
	private BigDecimal gastosTotales = new BigDecimal(0);
	
	/**
	 * Resultados Totales
	 */
	private BigDecimal resultadosTotales = new BigDecimal(0);
	
	/**
	 * Ingresos Totales Por Meses
	 * listaIngresosMeses[0] = ingresos totales del Mes de Enero
	 * listaIngresosMeses[1] = ingresos totales del Mes de Febrero
	 * etc
	 */
	private List<BigDecimal> listaIngresosMeses = new ArrayList<BigDecimal>(11);
		
	/**
	 * Gastos Totales Por Meses
	 * listaGastosMeses[0] = gastos totales del Mes de Enero
	 * listaGastosMeses[1] = gastos totales del Mes de Febrero
	 * etc
	 */
	private List<BigDecimal> listaGastosMeses = new ArrayList<BigDecimal>(11);
	
	/**
	 * Resultados Totales Por Meses
	 * listaResultadosMeses[0] = resultados totales del Mes de Enero
	 * listaResultadosMeses[1] = resultados totales del Mes de Febrero
	 * etc
	 */
	private List<BigDecimal> listaResultadosMeses = new ArrayList<BigDecimal>(11);
	
	/**
	 * Total Ingresos Anual
	 */
	private BigDecimal totalIngresosAnual = new BigDecimal(0);	
	
	/**
	 * Total Gastos Anual
	 */
	private BigDecimal totalGastosAnual = new BigDecimal(0);
	
	/**
	 * Total Resultados Anual
	 */
	private BigDecimal totalResultadosAnual = new BigDecimal(0);
	
	
	/**
	 * @return Lista de IngresosGastos
	 */
	public DataModel getListaIngresosGastos() {
		if(!buscando && !consultando){
			Calendar hoy = new GregorianCalendar();			
			rellenarMesAnyoConsulta(hoy.get(Calendar.MONTH), hoy.get(Calendar.YEAR));
			listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarMesAnyo(this.mesConsulta,this.anyoConsulta)));
			
			//listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));			
		}else if(!buscando && consultando){
			listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarMesAnyo(this.mesConsulta,this.anyoConsulta)));			
		}
		calcularTotales();
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
		
		if((this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) && 
				!(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			rellenarMesAnyoConsulta(GregorianCalendar.getInstance().get(Calendar.MONTH), 
					Integer.parseInt(this.anyoConsulta));			
		}else if(!(this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) &&
					(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			
				int m = 0;
				if(this.mesConsulta.equalsIgnoreCase(arrayMeses[0])){
					m = 0;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[1])){
					m = 1;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[2])){
					m = 2;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[3])){
					m = 3;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[4])){
					m = 4;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[5])){
					m = 5;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[6])){
					m = 6;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[7])){
					m = 7;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[8])){
					m = 8;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[9])){
					m = 9;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[10])){
					m = 10;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[11])){
					m = 11;					
				}
			rellenarMesAnyoConsulta(m, 
					GregorianCalendar.getInstance().get(Calendar.YEAR));						
		}else if ((this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) &&
				(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			rellenarMesAnyoConsulta(GregorianCalendar.getInstance().get(Calendar.MONTH), 
					GregorianCalendar.getInstance().get(Calendar.YEAR));			
		}else{
			LOG.debug("****** Los campos mesConsulta y anyoConsulta están rellenos ******");
		}
		//Calendar hoy = new GregorianCalendar();		
		//rellenarMesAnyoConsulta(hoy.get(Calendar.MONTH), hoy.get(Calendar.YEAR));
		
		listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarMesAnyo(this.mesConsulta,this.anyoConsulta)));
		this.consultando = true;
		
		calcularTotales();
		return SUCCESS;
	}
	
	/**
	 * @return La navegación a la que va después de que el usuario hace click en el consultar 
	 */
	public String consultarMesAnyo(){
		LOG.debug("En el metodo consultarMesAnyo() de IngresosGastosBean");
		
		if((this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) && 
				!(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			rellenarMesAnyoConsulta(GregorianCalendar.getInstance().get(Calendar.MONTH), 
					Integer.parseInt(this.anyoConsulta));			
		}else if(!(this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) &&
					(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			
				int m = 0;
				if(this.mesConsulta.equalsIgnoreCase(arrayMeses[0])){
					m = 0;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[1])){
					m = 1;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[2])){
					m = 2;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[3])){
					m = 3;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[4])){
					m = 4;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[5])){
					m = 5;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[6])){
					m = 6;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[7])){
					m = 7;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[8])){
					m = 8;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[9])){
					m = 9;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[10])){
					m = 10;
				}else if(this.mesConsulta.equalsIgnoreCase(arrayMeses[11])){
					m = 11;					
				}
			rellenarMesAnyoConsulta(m, 
					GregorianCalendar.getInstance().get(Calendar.YEAR));						
		}else if ((this.mesConsulta == null || this.mesConsulta.equalsIgnoreCase("")) &&
				(this.anyoConsulta == null || this.anyoConsulta.equalsIgnoreCase(""))){
			rellenarMesAnyoConsulta(GregorianCalendar.getInstance().get(Calendar.MONTH), 
					GregorianCalendar.getInstance().get(Calendar.YEAR));			
		}else{
			LOG.debug("****** Los campos mesConsulta y anyoConsulta están rellenos ******");
		}
		
		listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarMesAnyo(this.mesConsulta, this.anyoConsulta)));
		this.consultando = true;
		
		calcularTotales();
		return SUCCESS;
	}
	
	/**
    *
    */
   public void consultarPorFecha() {
      listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarPorFecha(fechaBusqueda)));
      calcularTotales();
   }
	
   /**
    * La consulta por tipo de ingresogasto.
    */
   public void consultarPorTipo() {
      listaIngresosGastos.setWrappedData(new ArrayList(servicio
               .consultarPorTipo(tipoBusqueda)));
      
      calcularTotales();
   }
   /**
    * La consulta por cualquier campo o por todos.
    */
   public void consultarPor() {
	   listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarPor(fechaBusqueda, tipoBusqueda, cantidadBusqueda, conceptoBusqueda)));
	   calcularTotales();
   }
   
	
	/**
	    * @return La navegacion a la que se dirige despues de seleccionar un ingresogasto
	    *         para actualizar
	    */
	   public String actualizar() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo actualizar() de IngresosGastosBean");
	      }
	      int m = 0;
	      int a = 2006;
	      ingresogasto = (IngresosGastos) listaIngresosGastos.getRowData();
	      LOG.info("El ingresogasto a actualizar es (" + ingresogasto.getId() + ") " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      
	      Calendar calendario = new GregorianCalendar();
	      calendario.setTime(this.ingresogasto.getFecha());
	      
	      m = calendario.get(GregorianCalendar.MONTH);
	      a = calendario.get(GregorianCalendar.YEAR);
	      
	      rellenarMesAnyoConsulta(m,a);
	      this.consultando = true;
	      	      
	      calcularTotales();	      
	      return SUCCESS;
	   }

	   /**
	    * @return La navegacion a la que se dirige despues de actualizar un ingresogasto
	    *         con un nuevo valor
	    */
	   public String actualizarLista() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo actualizarLista() de IngresosGastosBean");
	      }
	      int m = 0;
	      int a = 2006;
	      
	      LOG.info("El ingresogasto a actualizar es " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      servicio.actualizar(ingresogasto);
	      FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(this.ingresogasto.getTipo() + " con ID " + ingresogasto.getId()
	                        + " actualizado correctamente."));
	      
	      Calendar calendario = new GregorianCalendar();
	      calendario.setTime(this.ingresogasto.getFecha());
	      
	      m = calendario.get(GregorianCalendar.MONTH);
	      a = calendario.get(GregorianCalendar.YEAR);
	      recargar();
	      
	      rellenarMesAnyoConsulta(m,a);
	      this.consultando = true;
	      
	      calcularTotales();
	      return SUCCESS;
	   }

	   private void rellenarMesAnyoConsulta(int m, int a) {
			if(m == 0){
				this.mesConsulta = arrayMeses[0];
			}else if(m == 1){
				this.mesConsulta = arrayMeses[1];
			}else if(m == 2){
				this.mesConsulta = arrayMeses[2];
			}else if(m == 3){
				this.mesConsulta = arrayMeses[3];
			}else if(m == 4){
				this.mesConsulta = arrayMeses[4];
			}else if(m == 5){
				this.mesConsulta = arrayMeses[5];
			}else if(m == 6){
				this.mesConsulta = arrayMeses[6];
			}else if(m == 7){
				this.mesConsulta = arrayMeses[7];
			}else if(m == 8){
				this.mesConsulta = arrayMeses[8];
			}else if(m == 9){
				this.mesConsulta = arrayMeses[9];
			}else if(m == 10){
				this.mesConsulta = arrayMeses[10];
			}else if(m == 11){
				this.mesConsulta = arrayMeses[11];				
			}			
			this.anyoConsulta = String.valueOf(a);		
	}

	/**
	    * @return La navegacion a la cual se dirige despues de eliminar un ingresogasto
	    *         de la lista
	    */
	   public String eliminar() {
	      if (LOG.isTraceEnabled()) {
	         LOG.info("entrando en el metodo eliminar() de IngresosGastosBean");
	      }
	      int a = 2006;
	      int m = 0;
	    	  
	      ingresogasto = (IngresosGastos) listaIngresosGastos.getRowData();
	      LOG.info("El ingresogasto a eliminar es " + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " e importe: " + ingresogasto.getCantidad() );
	      servicio.eliminar(ingresogasto);
	      FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(this.ingresogasto.getTipo() + " con ID " + ingresogasto.getId()
	                        + " eliminado correctamente."));

	      Calendar calendario = new GregorianCalendar();
	      calendario.setTime(this.ingresogasto.getFecha());
	      
	      m = calendario.get(GregorianCalendar.MONTH);
	      a = calendario.get(GregorianCalendar.YEAR);
	      recargar();
	      
	      rellenarMesAnyoConsulta(m,a);	      
	      this.consultando = true;
	      
	      calcularTotales();
	      return SUCCESS;
	   }

	   /**
	    * @return La navegacion a la cual se dirige despues de insertar un ingreso gasto
	    *         nuevo
	    */
	   public String insertar() {
	      LOG.info("Entrando en el metodo insertar() de IngresosGastosBean con concepto: "
	               + ingresogasto.getConcepto() + " con fecha: " + ingresogasto.getFecha() + " con importe: " + ingresogasto.getCantidad());
	     int m = 0;
	     int a = 2006;
	      
	      if (ingresogasto.getConcepto().equalsIgnoreCase("")) {
	         FacesContext.getCurrentInstance().addMessage(null,
	                  new FacesMessage("El concepto es un campo requerido"));
	         recargar();
	         return FAILURE;
	      } else {
	         servicio.insertar(ingresogasto);
	         
	         Calendar calendario = new GregorianCalendar();
		      calendario.setTime(this.ingresogasto.getFecha());
		      
		      m = calendario.get(GregorianCalendar.MONTH);
		      a = calendario.get(GregorianCalendar.YEAR);
		      recargar();
		      
		      rellenarMesAnyoConsulta(m,a);
		      this.consultando = true;
	         
		     calcularTotales();
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
	    * @return La navegacion a la que se dirige despues de seleccionar el enlace 
	    *         para insertar un ingreso gasto
	    */
	   public String aResultados() {
		   if (LOG.isTraceEnabled()) {
			   LOG.info("entrando en el metodo aResultados() de IngresosGastosBean");
		   }	      
		   LOG.info("Vamos a navegar a la pagina de resultados");
		   //reset();
		   
		   calcularTotalesAnuales();
		   
		   return SUCCESS;
	   }
	   
	 /**
	  * Se calculan los totales anuales para cada mes y la suma de todos ellos tanto por los 
	  * ingresos como los gastos y los resultados totales
	  */
	   private void calcularTotalesAnuales() {
		   
		   this.totalIngresosAnual = new BigDecimal(0);
		   this.totalGastosAnual = new BigDecimal(0);
		   this.totalResultadosAnual = new BigDecimal(0);
		   this.listaResultadosMeses = new ArrayList<BigDecimal>(11);
		   
		   
		   this.listaIngresosMeses = new ArrayList(servicio.calcularIngresosGastosAnuales(this.anyoConsulta,"Ingreso"));
		   this.listaGastosMeses = new ArrayList<BigDecimal>(servicio.calcularIngresosGastosAnuales(this.anyoConsulta,"Gasto"));
		   
		   if(this.listaIngresosMeses != null && this.listaGastosMeses != null){
			   for(int i=0;i<this.listaIngresosMeses.size();i++){
				   
				   /**
				    * Acumulamos para el total de Ingresos Anual
				    */
				   this.totalIngresosAnual = this.totalIngresosAnual.add(this.listaIngresosMeses.get(i));
				   
				   /**
				    * Acumulamos para el total de Gastos Anual
				    */				   
				   this.totalGastosAnual = this.totalGastosAnual.add(this.listaGastosMeses.get(i));
				   
				   /**
				    * Vamos calculando los resultados mensuales restando los gastos a los ingresos por cada mes
				    */
				   //this.listaResultadosMeses.set(i, (this.listaIngresosMeses.get(i)).subtract(this.listaGastosMeses.get(i)));				   
				   this.listaResultadosMeses.add((this.listaIngresosMeses.get(i)).subtract(this.listaGastosMeses.get(i)));
				   
			   }
		   }else{
			   this.totalIngresosAnual = new BigDecimal(0);
			   this.totalGastosAnual = new BigDecimal(0);			   
		   }
		   this.totalResultadosAnual = this.totalIngresosAnual.subtract(this.totalGastosAnual);
		   		   
		   this.totalIngresosAnual.setScale(2, BigDecimal.ROUND_HALF_UP);
		   this.totalGastosAnual.setScale(2, BigDecimal.ROUND_HALF_UP);
		   this.totalResultadosAnual.setScale(2, BigDecimal.ROUND_HALF_UP);		   
	   }

	/**
	    * @return La navegacion a la que va despues de buscar un ingresogastos por algun campo o todos
	    */
	   public String buscar() {
	      LOG.info("Entrando en el metodo buscar(), fecha=" + fechaBusqueda);
	      this.buscando = true;
	      /*
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
	      }*/
	      
	      LOG.info("*** consultar por los campos que se hayan rellenado");
	      listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultarPor(fechaBusqueda,tipoBusqueda,cantidadBusqueda,conceptoBusqueda)));
	      
	      //reset();
	      this.mesConsulta = null;
	      this.anyoConsulta = null;
	      
	      return SUCCESS;
	   }
	   
	   /**
	    * @return Para limpiar el formulario de busquedas de cualquier valor
	    */
	   public String limpiar(){
		   LOG.info("Entrando en el metodo limpiar()");
		   
		   reset();
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
	   if(!buscando && !consultando){
		   listaIngresosGastos.setWrappedData(new ArrayList(servicio.consultar()));		   
	   }else{
		   
	   }
      this.ingresogasto = new IngresosGastos();
      this.tipos = new ArrayList<SelectItem>();
      this.listaGastosMeses = new ArrayList<BigDecimal>(11);
      this.listaIngresosMeses = new ArrayList<BigDecimal>(11);
      this.listaResultadosMeses = new ArrayList<BigDecimal>(11);
      this.fechaBusqueda = null;
      this.tipoBusqueda = null;
      this.cantidadBusqueda = null;
      this.conceptoBusqueda = null;
      this.anyoConsulta = null;
      this.mesConsulta = null;
      this.buscando = false;
      this.consultando = false;
   }

   /**
    *
    */
   private void reset() {      
      this.ingresogasto = new IngresosGastos();
      this.tipos = new ArrayList<SelectItem>();
      this.listaGastosMeses = new ArrayList<BigDecimal>(11);
      this.listaIngresosMeses = new ArrayList<BigDecimal>(11);
      this.listaResultadosMeses = new ArrayList<BigDecimal>(11);
      this.fechaBusqueda = null;
      this.tipoBusqueda = null;      
      this.cantidadBusqueda = null;
      this.conceptoBusqueda = null;
      this.anyoConsulta = null;
      this.mesConsulta = null;
      this.buscando = false;
      this.consultando = false;
      this.ingresosTotales = new BigDecimal(0);
      this.gastosTotales = new BigDecimal(0);
      this.resultadosTotales = new BigDecimal(0);
      this.totalGastosAnual = new BigDecimal(0);
      this.totalIngresosAnual = new BigDecimal(0);
      this.totalResultadosAnual = new BigDecimal(0);      
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

	public String getConceptoBusqueda() {
		return conceptoBusqueda;
	}

	public void setConceptoBusqueda(String conceptoBusqueda) {
		this.conceptoBusqueda = conceptoBusqueda;
	}

	public String getCantidadBusqueda() {
		return cantidadBusqueda;
	}

	public void setCantidadBusqueda(String cantidadBusqueda) {
		this.cantidadBusqueda = cantidadBusqueda;
	}

	public String getMesConsulta() {
		return mesConsulta;
	}

	public void setMesConsulta(String mesConsulta) {
		this.mesConsulta = mesConsulta;
	}

	public String getAnyoConsulta() {
		return anyoConsulta;
	}

	public void setAnyoConsulta(String anyoConsulta) {
		this.anyoConsulta = anyoConsulta;
	}

	public boolean isConsultando() {
		return consultando;
	}

	public void setConsultando(boolean consultando) {
		this.consultando = consultando;
	}

	public List<SelectItem> getMeses() {
		
		this.meses = new ArrayList<SelectItem>();
		
		for(int i=0;i<arrayMeses.length;i++){
			this.meses.add(new SelectItem(arrayMeses[i]));			
		}
		
		return meses;
	}

	public void setMeses(List<SelectItem> meses) {
		this.meses = meses;
	}

	public List<SelectItem> getAnyos() {
		this.anyos = new ArrayList<SelectItem>();
		
		Calendar calendario = GregorianCalendar.getInstance();
				
		/**
		 * Se tiene en cuenta desde el año 2006 en adelante
		 */
		for(int a=2006;a<=calendario.get(Calendar.YEAR);a++){
			this.anyos.add(new SelectItem(a));
		}
		
		return anyos;
	}

	public void setAnyos(List<SelectItem> anyos) {
		this.anyos = anyos;
	}

	public BigDecimal getResultadosTotales() {
		return resultadosTotales;
	}

	public void setResultadosTotales(BigDecimal resultadosTotales) {
		this.resultadosTotales = resultadosTotales;
	}

	public BigDecimal getIngresosTotales() {
		return ingresosTotales;
	}

	public void setIngresosTotales(BigDecimal ingresosTotales) {
		this.ingresosTotales = ingresosTotales;
	}

	public BigDecimal getGastosTotales() {
		return gastosTotales;
	}

	public void setGastosTotales(BigDecimal gastosTotales) {
		this.gastosTotales = gastosTotales;
	}
	
	
	private void calcularTotales(){
		BigDecimal ti = new BigDecimal(0);
		BigDecimal tg = new BigDecimal(0);
		BigDecimal rt = new BigDecimal(0);
		
		this.ingresosTotales = new BigDecimal(0);
		this.gastosTotales = new BigDecimal(0);
		this.resultadosTotales = new BigDecimal(0);
		
		if(this.listaIngresosGastos != null){
			
			/**
			 * Inicializamos el indice de la lista para recorrerla desde el principio
			 */
			this.listaIngresosGastos.setRowIndex(0);
			
			for(int i=0;i<this.listaIngresosGastos.getRowCount();i++){
				IngresosGastos ig = (IngresosGastos) this.listaIngresosGastos.getRowData();
				if(ig.getTipo().equals("Ingreso")){
					ti = ti.add(new BigDecimal(ig.getCantidad()));
				}else{
					tg = tg.add(new BigDecimal(ig.getCantidad()));
				}
				this.listaIngresosGastos.setRowIndex(i+1);
			}
		
			this.ingresosTotales = new BigDecimal(ti.toString());
			this.gastosTotales = new BigDecimal(tg.toString());			
			rt = ti.subtract(tg);		
			this.resultadosTotales= new BigDecimal(rt.toString());
			
			this.ingresosTotales.setScale(3, BigDecimal.ROUND_HALF_UP);
			this.gastosTotales.setScale(3, BigDecimal.ROUND_HALF_UP);
			this.resultadosTotales.setScale(3, BigDecimal.ROUND_HALF_UP);
			
		}
	}

	public List<BigDecimal> getListaIngresosMeses() {
		return listaIngresosMeses;
	}

	public void setListaIngresosMeses(List<BigDecimal> listaIngresosMeses) {
		this.listaIngresosMeses = listaIngresosMeses;
	}

	public List<BigDecimal> getListaGastosMeses() {
		return listaGastosMeses;
	}

	public void setListaGastosMeses(List<BigDecimal> listaGastosMeses) {
		this.listaGastosMeses = listaGastosMeses;
	}

	public List<BigDecimal> getListaResultadosMeses() {
		return listaResultadosMeses;
	}

	public void setListaResultadosMeses(List<BigDecimal> listaResultadosMeses) {
		this.listaResultadosMeses = listaResultadosMeses;
	}

	public BigDecimal getTotalIngresosAnual() {
		return totalIngresosAnual;
	}

	public void setTotalIngresosAnual(BigDecimal totalIngresosAnual) {
		this.totalIngresosAnual = totalIngresosAnual;
	}

	public BigDecimal getTotalGastosAnual() {
		return totalGastosAnual;
	}

	public void setTotalGastosAnual(BigDecimal totalGastosAnual) {
		this.totalGastosAnual = totalGastosAnual;
	}

	public BigDecimal getTotalResultadosAnual() {
		return totalResultadosAnual;
	}

	public void setTotalResultadosAnual(BigDecimal totalResultadosAnual) {
		this.totalResultadosAnual = totalResultadosAnual;
	}

	public String[] getArrayMeses() {
		return arrayMeses;
	}

}
