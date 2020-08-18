package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class WSContribuyenteServiceImpl implements WSContribuyenteService {
	private static final Log log = LogFactory.getLog(WSContribuyenteServiceImpl.class);
	
	private String wsURLContPrincipal;
	private String wsURLContAdicional;
	private String wsURLDireccionCont;
	private String wsURLTribAfectoCont;
	private String wsURLEstAnexoCont;
	private String wsURLRepLegalCont;
	private String wsURLComprobantesPago;
	private String wsURLDireccionNumDAM;
	
	@Override
	public List<Map<String, Object>> consultarWSContPrincipal(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSContPrincipal");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLContPrincipal.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSContPrincipal");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSContPrincipal");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	
	@Override
	public List<Map<String, Object>> consultarWSContAdicional(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSContAdicional");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLContAdicional.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSContAdicional");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSContAdicional");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	
	@Override
	public List<Map<String, Object>> consultarWSDireccionCont(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSDireccionCont");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLDireccionCont.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSDireccionCont");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSDireccionCont");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	
	@Override
	public List<Map<String, Object>> consultarWSTribAfectoCont(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSTribAfectoCont");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLTribAfectoCont.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSTribAfectoCont");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSTribAfectoCont");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	
	@Override
	public List<Map<String, Object>> consultarWSEstAnexoCont(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSEstAnexoCont");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLEstAnexoCont.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSEstAnexoCont");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSEstAnexoCont");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	
	@Override
	public List<Map<String, Object>> consultarWSRepLegalCont(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSRepLegalCont");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLRepLegalCont.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSRepLegalCont");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSRepLegalCont");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	//Inicio [gangles 30/06/2016] servicio Comprobantes de pago
	@Override
	public List<Map<String, Object>> consultarWSComprobantesPago(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMap = null;
		String numRUC = null;
		String url = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSComprobantesPago");
			numRUC = (String)parametros.get("numRUC");
			
			url = wsURLComprobantesPago.replace("{numRuc}", numRUC);
			listaMap = Utils.consultarWS(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSComprobantesPago");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSComprobantesPago");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaMap;
	}
	//Fin [gangles 30/06/2016]
	
	//Inicio [oachahuic][PAS20165E210400270]
	@Override
	public Map<String, Object> consultarWSRectificatoriaAduana(String numRuc, String numDam) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - WSContribuyenteServiceImpl.consultarWSRectificatoriaAduana");
		Map<String, Object> mapRpta = null;
		String url = null; 
		
		try {
			url = wsURLDireccionNumDAM.replace("{numRuc}", numRuc);
			url = url.replace("{numDAM}", numDam);
			log.debug("url: " + url);
			mapRpta = Utils.consultarWSv2(url);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - WSContribuyenteServiceImpl.consultarWSRectificatoriaAduana");
			log.error(ex, ex);
			mapRpta = new HashMap<String, Object>();
			mapRpta.put("codRpta", ValidaConstantes.COD_RPTA_WS_REST_9999);
			mapRpta.put("desError", ValidaConstantes.ERROR_DAM_WS_NO_DISPONIBLE);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - WSContribuyenteServiceImpl.consultarWSRectificatoriaAduana");
			
			NDC.pop();
			NDC.remove();
		}
		
		return mapRpta;
	}
	//Fin [oachahuic][PAS20165E210400270]
	
	/* Seteo de Spring - Inicio */
	public void setWsURLDireccionCont(String wsURLDireccionCont) {
		this.wsURLDireccionCont = wsURLDireccionCont;
	}
	public void setWsURLContPrincipal(String wsURLContPrincipal) {
		this.wsURLContPrincipal = wsURLContPrincipal;
	}

	public void setWsURLContAdicional(String wsURLContAdicional) {
		this.wsURLContAdicional = wsURLContAdicional;
	}

	public void setWsURLTribAfectoCont(String wsURLTribAfectoCont) {
		this.wsURLTribAfectoCont = wsURLTribAfectoCont;
	}
	public void setWsURLEstAnexoCont(String wsURLEstAnexoCont) {
		this.wsURLEstAnexoCont = wsURLEstAnexoCont;
	}
	public void setWsURLRepLegalCont(String wsURLRepLegalCont) {
		this.wsURLRepLegalCont = wsURLRepLegalCont;
	}
	//Inicio [gangles 30/06/2016] servicio Comprobantes de pago
	public void setWsURLComprobantesPago(String wsURLComprobantesPago) {
		this.wsURLComprobantesPago = wsURLComprobantesPago;
	}
	//Fin [gangles 30/06/2016]

	public void setWsURLDireccionNumDAM(String wsURLDireccionNumDAM) {
		this.wsURLDireccionNumDAM = wsURLDireccionNumDAM;
	}

	/* Seteo de Spring - Fin */
}