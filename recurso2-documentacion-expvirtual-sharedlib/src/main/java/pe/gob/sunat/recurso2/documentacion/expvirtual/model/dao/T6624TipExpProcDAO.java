package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;

public interface T6624TipExpProcDAO {
	
	public List<T6624TipExpProcBean> listar(Map<String, Object> parametros);
	
	public void insertar(Map<String, Object> parametros);
	
	public void actualizarMasivo(Map<String, Object> parametros);
	
}