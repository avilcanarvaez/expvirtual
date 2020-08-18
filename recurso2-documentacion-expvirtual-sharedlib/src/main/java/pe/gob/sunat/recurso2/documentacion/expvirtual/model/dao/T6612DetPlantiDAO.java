package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6612DetPlantiBean;

public interface T6612DetPlantiDAO {
	public void insertar(HashMap<String, Object> parametros);
	public List<Map<String, Object>> listarExpTrab(Map<String, Object> parametros);
	public List<T6612DetPlantiBean> listar(Map<String, Object> parametros);
	public void eliminar(HashMap<String, Object> parametros);
}
