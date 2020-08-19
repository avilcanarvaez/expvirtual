package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.CorreosDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CorreoUtil;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;

public class CorreosServiceImpl implements CorreosService {
	private static final Log log = LogFactory.getLog(CorreosServiceImpl.class);

	private CorreosDAO correosDAO;
	private Properties correoConfig;
	
	@Override
	public String obtenerCorreo(Map<String, Object> parametros) throws Exception {
		CorreosBean correoBean = null;
		String correo = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - CorreosServiceImpl.consultarCorreo");
		
		try {
			correoBean = correosDAO.obtener(parametros);
			if(correoBean != null) {
				correo = correoBean.getSmtp().trim();
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CorreosServiceImpl.consultarCorreo");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CorreosServiceImpl.consultarCorreo");
			
			NDC.pop();
			NDC.remove();
		}
		
		return correo;
	}
	
	@Override
	public List<CorreosBean> listarCorreo(Map<String, Object> parametros) throws Exception {
		List<CorreosBean> listaCorreosBeans = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - CorreosServiceImpl.listarCorreo");
		
		try {
			listaCorreosBeans = correosDAO.listar(parametros);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CorreosServiceImpl.listarCorreo");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CorreosServiceImpl.listarCorreo");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaCorreosBeans;
	}

	@Override
	public void enviarCorreo(Map<String, Object> parametros) {
		Map<String, Object> parametrosCorreo = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - CorreosServiceImpl.enviarCorreo");
		
		try {
			parametrosCorreo = new HashMap<String, Object>();
			if(Utils.isEmpty(parametros.get("asunto"))){
				parametrosCorreo.put("asunto",correoConfig.get("asuntoPorDefecto"));
			}else{
				parametrosCorreo.put("asunto",Utils.toStr(parametros.get("asunto")));
			}
			parametrosCorreo.put("servidor", correoConfig.get("servidor"));
			parametrosCorreo.put("correoRemitentePorDefecto", correoConfig.get("correoRemitentePorDefecto"));
			parametrosCorreo.put("destinatario", parametros.get("destinatario"));
			parametrosCorreo.put("mensaje", parametros.get("mensaje"));
			parametrosCorreo.put("formatoHTML", parametros.get("formatoHTML"));
			
			
			CorreoUtil.enviarCorreo(parametrosCorreo);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CorreosServiceImpl.enviarCorreo");
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CorreosServiceImpl.enviarCorreo");
			
			NDC.pop();
			NDC.remove();
		}
	}
	

	/* Seteo de Spring - Inicio */
	public void setCorreosDAO(CorreosDAO correosDAO) {
		this.correosDAO = correosDAO;
	}

	public void setCorreoConfig(Properties correoConfig) {
		this.correoConfig = correoConfig;
	}
	/* Seteo de Spring - Final */
}
