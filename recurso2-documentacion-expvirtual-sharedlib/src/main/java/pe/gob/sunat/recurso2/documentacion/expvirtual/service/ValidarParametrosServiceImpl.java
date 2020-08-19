package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpCoaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6623TipDocExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6624TipExpProcDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class ValidarParametrosServiceImpl implements ValidarParametrosService {
	
	private static final Log log = LogFactory.getLog(ExpedienteVirtualServiceImpl.class);
	
	private GeneralService generalService;
	
	private T6624TipExpProcDAO t6624TipExpProcDAO;
	private T6623TipDocExpDAO t6623TipDocExpDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;
	private ExpCoaDAO expCoaDAO;
	private DdpDAO ddpDAO;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	
	public boolean validarAsociacionTipoExpediente(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarAsociacionTipoExpediente");
		
		boolean resp = true;
		List<T6624TipExpProcBean> listaTipExpProcs = null;
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);			
			listaTipExpProcs = t6624TipExpProcDAO.listar(parametros);
			if (listaTipExpProcs == null || listaTipExpProcs.isEmpty()) {
				resp = false;
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarAsociacionTipoExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarAsociacionTipoExpediente");
		}
		return resp;
	}
	
	public boolean validarAsociacionTipoDocumento(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarAsociacionTipoDocumento");
		
		boolean resp = true;
		List<T6623TipDocExpBean> listaTipDocExps = null;
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listaTipDocExps = t6623TipDocExpDAO.listar(parametros);
			if(listaTipDocExps == null || listaTipDocExps.size() == 0) {
				resp = false;
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarAsociacionTipoDocumento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarAsociacionTipoDocumento");
		}
		return resp;
	}
	
	public String validarExpedienteOrigen (Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarExpedienteOrigen");

		String numExpVirtual = null;
		
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			List<T6614ExpVirtualBean> listaExpVirts = t6614ExpVirtualDAO.listar(parametros);
			if(listaExpVirts != null && listaExpVirts.size() > 0) {
				T6614ExpVirtualBean expedienteVirtual = listaExpVirts.get(0);
				numExpVirtual = expedienteVirtual.getNumExpedienteVirtual(); 
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarExpedienteOrigen");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarExpedienteOrigen");
		}
		return numExpVirtual;
	}
	
	public ExpCoaBean validarExistenciaExpCOA (Map<String, Object> parametros) throws Exception {
		
		String numExpedienteVirtual = null;
		List<ExpCoaBean> listaExpVirts = null;
		ExpCoaBean expedienteVirtual = null;	
		//DataSource origenDatos = null;//se comenta pues ya no se pasa el objeto Datasource a la consulta 
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		Map<String, Object> mapaDataSource = new HashMap<String,Object>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fecVistaCompleta = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarExistenciaExpCOA");
		
		try {
			
			if(parametros.get("coddependencia") != null){
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_NO_CENTRAL);
				mapaDataSource.put("preDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT);
				mapaDataSource.put("sufDatos", parametros.get("coddependencia").toString().trim());
			}else{
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);				
				mapaDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT);
			}
			
			//origenDatos = generalService.obtenerOrigenDatos(mapaDataSource);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapaDataSource); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml		
			//parametros.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			
			
			listaExpVirts =  expCoaDAO.listar(parametros);
			
			if(listaExpVirts != null && listaExpVirts.size() > 0) {
				
				expedienteVirtual = listaExpVirts.get(0);
				//numExpedienteVirtual = expedienteVirtual.getNum_exp_coa(); 
				
				fecVistaCompleta = sdf2.format(expedienteVirtual.getFec_emi());
				expedienteVirtual.setFechaEmisionCompleta(fecVistaCompleta);
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarExistenciaExpCOA");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarExistenciaExpCOA");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return expedienteVirtual;
		
	}
	
	@Override
	public DdpBean validarRUC(String numRuc) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarRUC");
		
		DdpBean contribuyente = null;
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("numRuc", numRuc);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA_RUC);		
			contribuyente = ddpDAO.obtener(mapa);
			if (Utils.isEmpty(contribuyente)) {
				contribuyente = new DdpBean();
				contribuyente.setDesRazonSocial(ValidaConstantes.CADENA_VACIA);
				contribuyente.setNumRuc(ValidaConstantes.CADENA_VACIA);
				contribuyente.setCodDependencia(ValidaConstantes.CADENA_VACIA);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarRUC");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarRUC");
		}
		return contribuyente;
	}
	
	public boolean validarExisteNumDoc(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.validarExisteNumDoc");

		boolean resp = false;
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			T6613DocExpVirtBean t6613Bean = t6613DocExpVirtDAO.obtenerDocOrigen(parametros);
			
			if(t6613Bean != null) {
				resp = true;
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.validarExisteNumDoc");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.validarExisteNumDoc");
		}
		return resp;
	}
	
	@Override
	public DdpBean obtenerContribuyente(String numRuc) throws Exception {
		DdpBean contribuyente = null;
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.obtenerContribuyente");
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ValidarParametrosServiceImpl.obtenerContribuyente");
			
			Map<String, Object> mapa = new HashMap<String, Object> ();
			
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA_RUC);
			
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml	
			mapa = new HashMap<String, Object> ();
			
			//mapa.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapa.put("numRuc", numRuc);
			
			contribuyente = ddpDAO.obtener(mapa);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.obtenerContribuyente");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.obtenerContribuyente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return contribuyente;
		
	}
	
	//EAGUILAR METODO PROVISIONAL:
	
	@Override
	public Map<String, Object> obtenerFoto(String dni) throws Exception {
		Map<String, Object> resultado = null;
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		
		if (log.isDebugEnabled()) log.debug("Inicio - ValidarParametrosServiceImpl.obtenerFoto");
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ValidarParametrosServiceImpl.obtenerFoto");
			Map<String, Object> mapa = new HashMap<String, Object> ();
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA_RUC);
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml
			
			mapa = new HashMap<String, Object>();
			//mapa.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			mapa.put("numDoc", dni);
			resultado = ddpDAO.obtenerFoto(mapa);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ValidarParametrosServiceImpl.obtenerFoto");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ValidarParametrosServiceImpl.obtenerFoto");
			NDC.pop();
			NDC.remove();
		}
		return resultado;
	}
	
	/* Seteo de Spring - Inicio */
	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public void setT6624TipExpProcDAO(T6624TipExpProcDAO t6624TipExpProcDAO) {
		this.t6624TipExpProcDAO = t6624TipExpProcDAO;
	}
	
	public void setT6623TipDocExpDAO(T6623TipDocExpDAO t6623TipDocExpDAO) {
		this.t6623TipDocExpDAO = t6623TipDocExpDAO;
	}
	
	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}
	
	public void setDdpDAO(DdpDAO ddpDAO) {
		this.ddpDAO = ddpDAO;
	}

	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}
	
	public void setExpCoaDAO(ExpCoaDAO expCoaDAO) {
		this.expCoaDAO = expCoaDAO;
	}
	
	/* Seteo de Spring - Final */
}