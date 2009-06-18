package es.antonio.duarte.IngresosGastos;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.antonio.duarte.IngresosGastos.dao.Dao;

@Service
public class IngresosGastosManager {
	
	@Resource
	private Dao dao;
	
	public IngresosGastos newIngresosGastos(){
		return new IngresosGastos();
	}
	
	public void persist(IngresosGastos ingresosGastos){
		dao.persist(ingresosGastos);
	}
	
	public List<IngresosGastos> getIngresosGastos(){
		final List<IngresosGastos> list = dao.find(IngresosGastos.class);
		
		return list;		
	}

}
