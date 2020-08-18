package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;

public interface ResCoaDAO {
	
	public ResCoaBean obtener(Map<String, Object> parametros);

	public Map<String, Object> obtenerFechaModificacion(Map<String, Object> parametros);
	//Inicio [nchavez 26/05/2015]
	public ResCoaBean obtenerNumeroDocumentoRequerimiento(Map<String, Object> parametros);
	//Fin [nchavez 26/05/2015]
}
