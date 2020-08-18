package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;

public interface PersonaService {
	
	public T02PerdpBean validarPersona (Map<String, Object> parametros) throws Exception;
	
	public T1797DepenUOrgaBean validarDependenciaUnidadOrganizacional (Map<String, Object> parametros) throws Exception;
	
	public T02PerdpBean completarInformacionPersona (T02PerdpBean persona) throws Exception;
	
}
