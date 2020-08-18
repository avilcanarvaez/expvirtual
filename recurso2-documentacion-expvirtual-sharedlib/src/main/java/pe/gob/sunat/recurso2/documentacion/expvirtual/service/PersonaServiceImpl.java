package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T1797DepenUOrgaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class PersonaServiceImpl implements PersonaService {
	
	private static final Log log = LogFactory.getLog(PersonaServiceImpl.class);
	
	private T02PerdpDAO t02PerdpDAO;
	private T1797DepenUOrgaDAO t1797DepenUOrgaDAO;
	
	public T02PerdpBean validarPersona (Map<String, Object> parametros) throws Exception {
		
		T02PerdpBean persona = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarPersona");
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ValidarParametrosServiceImpl.validarPersona");
			
			String codPersona = parametros.get("codPersona") == null ? null : parametros.get("codPersona").toString().toUpperCase();
			String indHabilitado = parametros.get("indHabilitado") == null ? null : parametros.get("indHabilitado").toString();
			if (codPersona != null) {
				
				Map<String, Object> mapa = new HashMap<String, Object> ();
				
				mapa.put("codPersona", codPersona);
				
				if (indHabilitado != null) {
					
					mapa.put("indHabilitado", indHabilitado);
					
				}
				
				persona = t02PerdpDAO.obtener(mapa);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarPersona");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarPersona");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return persona;
		
	}
	
	public T1797DepenUOrgaBean validarDependenciaUnidadOrganizacional (Map<String, Object> parametros) throws Exception {
		
		T1797DepenUOrgaBean unidadOrganizacionalDependencia = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarDependenciaUnidadOrganizacional");
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ValidarParametrosServiceImpl.validarDependenciaUnidadOrganizacional");
			
			String codUnidadOrganizacional = parametros.get("codUnidadOrganizacional") == null ? null : parametros.get("codUnidadOrganizacional").toString();
			String codTipoDependencia = parametros.get("codTipoDependencia") == null ? null : parametros.get("codTipoDependencia").toString();
			//String codDependencia = parametros.get("codDependencia") == null ? null : parametros.get("codDependencia").toString();
			
			//if (codUnidadOrganizacional != null && codTipoDependencia != null && codDependencia != null) {
			if (codUnidadOrganizacional != null && codTipoDependencia != null) {
				
				Map<String, Object> mapa = new HashMap<String, Object> ();
				
				mapa.put("codUnidadOrganizacional", codUnidadOrganizacional);
				mapa.put("codTipoDependencia", codTipoDependencia);
				//mapa.put("codDependencia", codDependencia);
				
				unidadOrganizacionalDependencia = t1797DepenUOrgaDAO.obtener(mapa);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarDependenciaUnidadOrganizacional");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarDependenciaUnidadOrganizacional");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return unidadOrganizacionalDependencia;
		
	}
	
	public T02PerdpBean completarInformacionPersona (T02PerdpBean persona) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarPersona");
		List<String> listaDatos = null;
		String desNombres = null;
		String desApellidoPaterno = null;
		String desApellidoMaterno = null;
		String nombreCompleto = "";
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ValidarParametrosServiceImpl.validarPersona");
			
			listaDatos = new ArrayList<String>();
			desNombres = persona.getDesNombres() != null ? persona.getDesNombres().trim() :  "";
			
			if(!desNombres.equals("")) {
				listaDatos.add(desNombres);
			}
			
			desApellidoPaterno = persona.getDesApellidoPaterno() != null ? persona.getDesApellidoPaterno().trim() :  "";
			
			if(!desApellidoPaterno.equals("")) {
				listaDatos.add(desApellidoPaterno);
			}
			
			desApellidoMaterno = persona.getDesApellidoMaterno() != null ? persona.getDesApellidoMaterno().trim() :  "";
			
			if(!desApellidoMaterno.equals("")) {
				listaDatos.add(desApellidoMaterno);
			}
			
			for(String valor : listaDatos) {
				nombreCompleto += valor + ValidaConstantes.SEPARADOR_ESPACIO;
			}
			
			persona.setDesNombreCompleto(nombreCompleto.trim());
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarPersona");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarPersona");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return persona;
		
	}
	
	/* Seteo de Spring - Inicio */
	
	public void setT02PerdpDAO(T02PerdpDAO t02PerdpDAO) {
		this.t02PerdpDAO = t02PerdpDAO;
	}
	
	public void setT1797DepenUOrgaDAO(T1797DepenUOrgaDAO t1797DepenUOrgaDAO) {
		this.t1797DepenUOrgaDAO = t1797DepenUOrgaDAO;
	}
	
	/* Seteo de Spring - Final */
	
}
