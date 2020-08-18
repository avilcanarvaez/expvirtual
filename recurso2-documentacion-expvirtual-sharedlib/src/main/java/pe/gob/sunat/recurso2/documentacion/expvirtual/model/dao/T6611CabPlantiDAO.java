package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;

public interface T6611CabPlantiDAO {
	public List<T6611CabPlantiBean> listar(Map<String, Object> parametros);
	public void actualizar(Map<String, Object> parametros);
	public void insertar(HashMap<String, Object> parametros);
}
