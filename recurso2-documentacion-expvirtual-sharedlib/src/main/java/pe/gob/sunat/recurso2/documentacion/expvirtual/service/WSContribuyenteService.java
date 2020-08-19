package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;



public interface WSContribuyenteService {
	public List<Map<String, Object>> consultarWSContPrincipal(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSDireccionCont(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSTribAfectoCont(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSEstAnexoCont(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSRepLegalCont(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSContAdicional(Map<String, Object> parametros) throws Exception;
	public List<Map<String, Object>> consultarWSComprobantesPago(Map<String, Object> parametros) throws Exception;
	//Inicio [oachahuic][PAS20165E210400270]
	public Map<String, Object> consultarWSRectificatoriaAduana(String numRuc, String numDam) throws Exception;
	//Fin [oachahuic][PAS20165E210400270]
}