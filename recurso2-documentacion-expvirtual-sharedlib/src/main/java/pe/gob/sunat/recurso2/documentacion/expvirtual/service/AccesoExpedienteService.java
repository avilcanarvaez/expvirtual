package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10459AccepExpVirtBean;

public interface AccesoExpedienteService {
	
	public List<T10459AccepExpVirtBean> obtenerResponsablesAsignados(String numExpedVirtual) throws Exception;
	
	public Map<String, Object> actualizarAsignacionResponsables(Map<String, Object> parametros) throws Exception;
	
	public List<T10459AccepExpVirtBean> listarRelacionResponsables (Map<String, Object> parametros) throws Exception;
	
	public List<T10459AccepExpVirtBean> listarResponsables(Map<String, Object> parametros) throws Exception;
	
	public void realizarAsignacionResponsableExpediente(Map<String, Object> parametros) throws Exception;
	
	public List<T10459AccepExpVirtBean> obtenerResponsablesAsignadosSepar(String numExpedienteVirtual) throws Exception;
	
	public List<T1797DepenUOrgaBean> listarUnidadesOrganizacionales(Map<String, Object> parametros) throws Exception;
	
	public T10459AccepExpVirtBean obtenerResponsable(Map<String, Object> parametros) throws Exception;//[oachahuic][PAS20165E210400270]
	
	public List<String> obtenerCodResponsablesAsignados(String numExpedVirtual) throws Exception;
    
	public void realizarReasignacionResponsableExpediente(Map<String, Object> parametros) throws Exception;
	public List<T10459AccepExpVirtBean> listarResponsablesDependencia (Map<String, Object> mapParamBusq) throws Exception;
	
	//Inicio CVG
	public T02PerdpBean obtenerEmpleado(String codigoReg) throws Exception;
	
	public void registrarAccesoExpediente(Map<String, Object> parametros) throws Exception;
	
	public Integer verificarAcceso(Map<String, Object> parametros) throws Exception;
	
	 public void eliminarAccesoExpediente (Map<String, Object> parametros) throws Exception;
	//Fin CVG
}