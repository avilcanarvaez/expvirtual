package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocNotSineBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;

public interface DocumentoExpedienteService {
	
	public List<T6613DocExpVirtBean> listarDocumentosExpediente(Map<String, Object> parametros) throws Exception;
	public T6613DocExpVirtBean obtenerDocumentoExpediente(Map<String, Object> parametros) throws Exception;
	
	//[ATORRESCH 2017-02-21]
	public String EliminarDocumentoExpediente(Map<String, Object> parametros) throws Exception;
	public String CantidadDocumentosInternoRelacion(Map<String, Object> parametros) throws Exception;
	public DocNotSineBean obtenerDocNotPorSINE(Map<String, Object> parametros) throws Exception;
	public Map<String, Object> validarDocSIGAD(Map<String, Object> parametros) throws Exception;
	
	//[PAS20191U210500144] - ETITO
	public String modificarDocumentoExpediente(Map<String, Object> parametros) throws Exception;
	public void registrarSeguimientoActualizacionDocumento(Map<String, Object> parametros) throws Exception;
}