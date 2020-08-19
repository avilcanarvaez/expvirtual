package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10458VersDocAdjBean;

public interface T10458VersDocAdjDAO {
	
	public  List<Map<String, Object>> listarCopDocPorExp(Map<String, Object> parametros);
	
	public Integer obtenerCopia(Map<String, Object> parametros);
	
	public void insertar(Map<String, Object> parametros);

	
}