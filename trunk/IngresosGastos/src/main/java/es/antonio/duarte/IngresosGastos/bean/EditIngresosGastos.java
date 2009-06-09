package es.antonio.duarte.IngresosGastos.bean;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import es.antonio.duarte.IngresosGastos.IngresosGastos;
import es.antonio.duarte.IngresosGastos.IngresosGastosManager;

@Controller
@Scope("session")
public class EditIngresosGastos {
	
	@Resource
	private IngresosGastosManager igMgr;
	
	private IngresosGastos ig;
	
	
	public String save(){
		igMgr.persist(ig);
		
		return "home";
	}
	
	public IngresosGastos getIngresosGastos(){
		return ig;
	}
	
	public void setIngresosGastos(IngresosGastos ig){
		this.ig = ig;
	}

}
