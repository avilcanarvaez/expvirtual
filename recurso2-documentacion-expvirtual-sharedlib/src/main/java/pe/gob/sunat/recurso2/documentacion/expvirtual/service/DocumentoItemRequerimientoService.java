package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;

public interface DocumentoItemRequerimientoService {
	
	public List<T10373DocAdjReqBean> obtenerListaDocAdj(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public String adjuntarDocumentosDetRequerimiento (Map<String, Object> parametros) throws Exception;
	
	//PAS20201U210500029 - HHC INICIO
	public List<T6613DocExpVirtBean> obtenerListaDocumentosEscritos(Map<String, Object> mapParametrosBusqueda) throws Exception; 
	
	public String adjuntarDocumentosDetEscritos (Map<String, Object> parametros) throws Exception; 
	//PAS20201U210500029 - HHC FIN
	
}