package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanDocumentosRequerimiento;
//import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanRequerimiento;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;

public interface RequerimientoService {
	
	public String registrarRequerimiento(Map<String, Object> parametros) throws Exception;
	
	public List<T6620RequerimBean> obtenerListaRequerimientos(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public String registrarRequerimientoManual(HashMap<String, Object> mapParametros) throws Exception ;
	
	public List<T6613DocExpVirtBean> listarDocumentosPorRequerim(HashMap<String, Object> mapParametros) throws Exception;
	
	public List<T6620RequerimBean> listarRequerimientos(Map<String, Object> parametros) throws Exception;
	// Inicio [nchavez 26/05/2015]
	byte[] obtenerPrevisuaizacionRegistroRequerimiento(String parametroJSON) throws Exception;

	public List<String> obtenerTipoDocumentosPorRequerimientoValidos(Map<String, Object> parametros)  throws Exception;
	
	public String validarRequerimientoOrigen(Map<String, Object> parametros) throws Exception;	
	// Fin [nchavez 26/05/2015]
	
	// [ATORRESCH 2017-03-02]
	public String eliminarRequerimiento(Map<String, Object> parametros) throws Exception;
	
	//staype 26/12/2016 [PAS20191U210500291] 
	public void actualizar(Map<String, Object> parametros) throws Exception;
		
	//existencia de requerimiento
	public T6620RequerimBean obtenerRequerimiento(Map<String, Object> parametros) throws Exception;
		
	//numero de requerimiento del negocio se encuentre asociado al expediente
	public T6613DocExpVirtBean obtenerReqExpediente(Map<String, Object> parametros) throws Exception;
			
	//fin staype 26/12/2016 
}