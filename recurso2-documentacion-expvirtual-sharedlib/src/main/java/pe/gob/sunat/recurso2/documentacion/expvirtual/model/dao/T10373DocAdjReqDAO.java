package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;

public interface T10373DocAdjReqDAO {
	
	public List<Map<String, Object>> listarArcDocPorExp(Map<String, Object> parametros);
	public List<Map<String, Object>> listar(Map<String, Object> parametros);
	
	//[PAS20191U210500291]: JMC - INI
	public Integer contarArchivos (Map<String, Object> parametros);
	
	public List<T10373DocAdjReqBean> obtenerListaDocAdj (Map<String, Object> parametros);
	
	public void insertar(T10373DocAdjReqBean t10373DocAdjReqBean);
	
	public void update (Map<String, Object> parametros);
	//[PAS20191U210500291]: JMC - FIN
}