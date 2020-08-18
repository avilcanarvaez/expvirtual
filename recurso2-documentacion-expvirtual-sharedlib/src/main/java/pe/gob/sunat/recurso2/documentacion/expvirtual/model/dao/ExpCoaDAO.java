package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpCoaBean;

public interface ExpCoaDAO {
		
	public List<ExpCoaBean> listar(Map<String, Object> parametros);
	public List<Map<String, Object>> listarAcumuladosDoc(Map<String, Object> parametros);
	public ExpCoaBean obtenerIndicadores (Map<String, Object> parametros);
}