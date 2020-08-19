package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;

public interface DdpDAO {
	
	public DdpBean obtener(Map<String, Object> parametros);
	
	public Map<String, Object> obtenerFoto(Map<String, Object> parametros);
	
}