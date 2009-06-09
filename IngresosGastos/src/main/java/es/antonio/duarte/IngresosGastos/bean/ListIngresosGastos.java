package es.antonio.duarte.IngresosGastos.bean;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.UIData;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import es.antonio.duarte.IngresosGastos.IngresosGastos;
import es.antonio.duarte.IngresosGastos.IngresosGastosManager;

@Controller
@Scope("session")
public class ListIngresosGastos {
	
	@Resource
	private IngresosGastosManager igMgr;
	
	@Resource
	private EditIngresosGastos editIG;
	
	private UIData ingresosGastosDataTable;
	
	
	public String editIngresosGastos(){
		IngresosGastos ig;
		try{
			ig = (IngresosGastos)ingresosGastosDataTable.getRowData();
		}catch(IllegalArgumentException e){
			//No se ha seleccionado ninguna fila; se está añadiendo un nuevo elemento
			ig = igMgr.newIngresosGastos();
		}
		
		editIG.setIngresosGastos(ig);
		return "editIngresosGastos";
	}
	
	
	public List<IngresosGastos> getIngresosGastos(){
		return igMgr.getIngresosGastos();
	}
	
	public UIData getIngresosGastosDataTable(){
		return ingresosGastosDataTable;
	}
	
	public void setIngresosGastosDataTable(UIData ingresosGastosDataTable){
		this.ingresosGastosDataTable = ingresosGastosDataTable;
	}

}
