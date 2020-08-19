package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpCoaBean;

public interface ValidarParametrosService {
	
	public boolean validarAsociacionTipoExpediente(Map<String, Object> parametros) throws Exception;
	
	public boolean validarAsociacionTipoDocumento(Map<String, Object> parametros) throws Exception;
	
	public String validarExpedienteOrigen (Map<String, Object> parametros) throws Exception;
	
	public DdpBean validarRUC(String numRuc) throws Exception;
	
	public boolean validarExisteNumDoc(Map<String, Object> parametros) throws Exception;
	
	public ExpCoaBean validarExistenciaExpCOA (Map<String, Object> parametros) throws Exception;
	
	public DdpBean obtenerContribuyente(String numRuc) throws Exception;
	
	//EAGUILAR metodo provisional:
	public Map<String, Object> obtenerFoto(String dni) throws Exception;
	
}