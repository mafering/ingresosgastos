package es.antonio.duarte.IngresosGastos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IngresosGastos {
	
	@Id
	@GeneratedValue	
	private Integer id;
	
	private String fecha;	
	private String tipo;	
	private String cantidad;	
	private String concepto;
	
	IngresosGastos(){
		//Sólo el manager puede construir nuevas instancias
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	
	
	

}
