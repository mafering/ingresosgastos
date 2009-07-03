package es.antonio.duarte.model;

/**
 * Clase de modelo anotada con Hibernate para hacer este pojo en una entidad.
 * @author Antonio Duarte 
 */

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@GenericGenerator(name="nativo",strategy="native")
@Table(name="INGRESOSGASTOS", schema="INGRESOSGASTOS")
public class IngresosGastos implements Serializable{
	
	/**
	 * Identificador de la clase
	 */
	private Long id;
	
	/**
	 * Fecha del asiento del ingresogasto
	 */
	private Date fecha;
	
	/**
	 * Tipo de asiento que se realiza (o bien INGRESO o bien GASTO) 
	 */
	private String tipo;
	
	/**
	 * Importe de la operacion que se realiza
	 */
	private String cantidad;
	
	/**
	 * Concepto de la operacion
	 */
	private String concepto;
	
	
	/**
	 * Constructor basico
	 */
	public IngresosGastos(){
		
	}
	
	/**
	 * Constructor por clave
	 * @param id
	 * clave que identifica al objeto
	 */
	public IngresosGastos(final Long id){
		this.id = id;
	}
	
	/**
	 * Constructor con parametros: id, fecha, tipo, cantidad, concepto
	 * @param id - La clave del objeto
	 * @param fecha - La fecha cuando se realiza la operacion
	 * @param tipo - El tipo de operacion que se hace, o INGRESO o bien GASTO
	 * @param cantidad - El importe para la operacion a realizar
	 * @param concepto - El motivo por el que se hace una operacion
	 */
	public IngresosGastos(final Long id, final Date fecha, final String tipo, 
							final String cantidad, final String concepto){
		
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = concepto;
		this.tipo = tipo;
	}
	
	/**
	 * Se recupera al id del objeto
	 * @return id - es el id del objeto
	 */
	@Id
	@GeneratedValue(generator = "nativo")
	@Column(name = "ID",unique = true, nullable = false)
	public Long getId(){
		return this.id;
	}
	
	/**
	 * Para modificar el id del objeto
	 * @param id - el identificador del objeto
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 * Para recuperar la fecha de la operacion
	 * @return fecha - la fecha cuando se realiza la operacion
	 */
	@Column(name = "FECHA")
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Para modificar la fecha de la operacion
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Para recuperar el tipo de operacion a realizar
	 * @return tipo - o bien INGRESO o GASTO
	 */
	@Column(name = "TIPO")
	public String getTipo() {
		return tipo;
	}

	/**
	 * Para modificar el tipo de la operacion
	 * @param tipo - el tipo de operacion a realizar
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Para recuperar la cantidad de la operacion
	 * @return cantidad
	 */
	@Column(name = "CANTIDAD")
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Para modificar el importe de la operacion
	 * @param cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Para recuperar el concepto sobre la operacion
	 * @return concepto
	 */
	@Column(name = "CONCEPTO")
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Para modificar el concepto de la operacion
	 * @param concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
    * Una cadena con el valor actual de todos los campos del objeto.
    * @return La descripción en forma de cadena de este objeto.
    */
   @Override
   public String toString() {
      return "id: " + getId() + " fecha: " + getFecha() + " tipo: " + getTipo() + " cantidad: " + getCantidad() + " concepto: " + getConcepto();
   }
	
}
