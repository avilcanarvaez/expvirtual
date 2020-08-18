package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;

public interface Ccs19DAO {
	
	public Map<String, Object> obtenerDatosSirat(Map<String, Object> parametros);

	// [jjurado 25/05/2015] obtiene los datos del expediente origen 
	public Map<String, Object> obtenerDatosExpOrigen(Map<String, Object> parametros);
	
}
