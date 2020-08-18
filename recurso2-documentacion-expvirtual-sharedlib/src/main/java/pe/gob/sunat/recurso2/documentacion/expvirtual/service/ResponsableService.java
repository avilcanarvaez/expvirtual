package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;

public interface ResponsableService {
	
	public List<T6621RespExpVirtBean> obtenerResponsablesAsignados(String numExpedVirtual) throws Exception;
	
	public Map<String, Object> actualizarAsignacionResponsables(Map<String, Object> parametros) throws Exception;
	
	public List<T6621RespExpVirtBean> listarRelacionResponsables (Map<String, Object> parametros) throws Exception;
	
	public List<T6621RespExpVirtBean> listarResponsables(Map<String, Object> parametros) throws Exception;
	
	public void realizarAsignacionResponsableExpediente(Map<String, Object> parametros) throws Exception;
	
	public List<T6621RespExpVirtBean> obtenerResponsablesAsignadosSepar(String numExpedienteVirtual) throws Exception;
	
	public List<T1797DepenUOrgaBean> listarUnidadesOrganizacionales(Map<String, Object> parametros) throws Exception;
	
	public T6621RespExpVirtBean obtenerResponsable(Map<String, Object> parametros) throws Exception;//[oachahuic][PAS20165E210400270]
	
	public List<String> obtenerCodResponsablesAsignados(String numExpedVirtual) throws Exception;
    
    //PAS20191U210500144 Inicio 400101 RF28 PSALDARRIAGA
	public void realizarReasignacionResponsableExpediente(Map<String, Object> parametros) throws Exception;
	public List<T6621RespExpVirtBean> listarResponsablesDependencia (Map<String, Object> mapParamBusq) throws Exception;
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA
}