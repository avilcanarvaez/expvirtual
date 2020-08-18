package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class CatalogoServiceImpl implements CatalogoService {
	
	private static final Log log = LogFactory.getLog(CatalogoServiceImpl.class);
	
	private GeneralService generalService;
	
	private T01ParamDAO t01ParamDAO;
	
	@Override
	public Map<String, Object> obtenerCatalogo(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - CatalogoServiceImpl.obtenerCatalogo");
		
		Map<String, Object> mapaCatalogo = new HashMap<String,Object>();
		try {
			Boolean indArgumento = parametros.get("indArgumento") == null ? false : (Boolean) parametros.get("indArgumento");
			Boolean indLimite = parametros.get("indLimite") == null ? false : (Boolean) parametros.get("indLimite");
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);			
			List<T01ParamBean> listaCatalogo = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean catalogo: listaCatalogo) {
				if (catalogo.getCodParametro() != null && catalogo.getDesParametro() != null) {
					String codigo = catalogo.getCodParametro().trim();
					String detalle = catalogo.getDesParametro().toString();
					String codigoFinal = null;
					String detalleFinal = null;
					if (indArgumento) {
						Integer argInferior = (Integer) parametros.get("argInferior");
						Integer argSuperior = (Integer) parametros.get("argSuperior");
						codigoFinal = codigo.substring(argInferior, argSuperior).trim();
					} else {
						codigoFinal = codigo;
					}
					if (indLimite) {
						Integer limInferior = (Integer) parametros.get("limInferior");
						Integer limSuperior = (Integer) parametros.get("limSuperior");
						detalleFinal = detalle.substring(limInferior, limSuperior).trim();
					} else {
						detalleFinal = detalle.trim();
					}
					mapaCatalogo.put(codigoFinal, detalleFinal);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CatalogoServiceImpl.obtenerCatalogo");
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CatalogoServiceImpl.obtenerCatalogo");
		}
		return mapaCatalogo;
	}
	
	// Inicio [nchavez 26/05/2015]
	@SuppressWarnings("unchecked")
	public Map<String, Object> obtenerCatalogoConDetalles(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - CatalogoServiceImpl.obtenerCatalogo");
		
		Map<String, Object> mapaCatalogo = new HashMap<String,Object>();
		Map<String, Object> mapaDataSource = new HashMap<String,Object>();
		//DataSource origenDatos = null;
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - CatalogoServiceImpl.obtenerCatalogo");
			
			Boolean indArgumento = parametros.get("indArgumento") == null ? false : (Boolean) parametros.get("indArgumento");
			
			Boolean indLimite = parametros.get("indLimite") == null ? false : (Boolean) parametros.get("indLimite");
			
			/*seteamos con que pool va hacer la consulta - segun dependencia del usuario loggueado*/
			
			String codDependencia = parametros.get("codDependencia") == null ? "" : parametros.get("codDependenciaLoggin").toString();
			
			if (ValidaConstantes.CADENA_VACIA.equals(codDependencia)) {
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
				mapaDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
				
			} else {
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_NO_CENTRAL);
				mapaDataSource.put("preDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
				mapaDataSource.put("sufDatos", codDependencia);
			}
			
			//origenDatos = generalService.obtenerOrigenDatos(mapaDataSource);
			mapaPool = generalService.obtenerOrigenDatosMap(mapaDataSource);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			
			//parametros.put("origenDatos", origenDatos);
			
			List<T01ParamBean> listaCatalogo = t01ParamDAO.listar(parametros);
			List<String> detalles;
			
			for (T01ParamBean catalogo: listaCatalogo) {
				
				if (catalogo.getCodParametro() != null && catalogo.getDesParametro() != null) {
					
					String codigo = catalogo.getCodParametro().trim();
					String detalle = catalogo.getDesParametro().toString();
					
					String codigoFinal = null;
					String detalleFinal = null;
					
					if (indArgumento) {
						Integer argInferior = (Integer) parametros.get("argInferior");
						Integer argSuperior = (Integer) parametros.get("argSuperior");
						codigoFinal = codigo.substring(argInferior, argSuperior).trim();
					} else {
						//Inicio nchavez[03/06/2016]
						codigoFinal = codigo.substring(0,3);
						//Fin nchavez[03/06/2016]
					}
					
					if (indLimite) {
						Integer limInferior = (Integer) parametros.get("limInferior");
						Integer limSuperior = (Integer) parametros.get("limSuperior");
						detalleFinal = detalle.substring(limInferior, limSuperior).trim();
					} else {
						detalleFinal = detalle.trim();
					}
					
					if (mapaCatalogo.get(codigoFinal)!=null) {
						((List<String>)mapaCatalogo.get(codigoFinal)).add(detalleFinal);
					}else{
						detalles=new ArrayList<String>(0);
						detalles.add(detalleFinal);
						mapaCatalogo.put(codigoFinal, detalles);
					}
					
				}
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - CatalogoServiceImpl.obtenerCatalogo");
			
			log.error(ex, ex);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - CatalogoServiceImpl.obtenerCatalogo");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return mapaCatalogo;
		
	}
	
	@Override
	public List<String> listaTipDocCierreByTipExp(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicial - CatalogoServiceImpl.listaTipDocCierreByTipExp");
		List<String> listDocCierre = new ArrayList<String>(); 
		try {
			DataSourceContextHolder.setKeyDataSource("dcrecauda");
			listDocCierre = t01ParamDAO.listarDocCierreByTipExp(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CatalogoServiceImpl.listaTipDocCierreByTipExp");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CatalogoServiceImpl.listaTipDocCierreByTipExp");
			NDC.pop();
			NDC.remove();
		}
		return listDocCierre;
	}
	
	@Override
	public List<String> listaTipDocNotif(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicial - CatalogoServiceImpl.listaTipDocNotif");
		List<String> listTipDocNotif = new ArrayList<String>(); 
		try {
			DataSourceContextHolder.setKeyDataSource("dcrecauda");
			listTipDocNotif = t01ParamDAO.listarTipDocNotif(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CatalogoServiceImpl.listaTipDocNotif");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CatalogoServiceImpl.listaTipDocNotif");
			NDC.pop();
			NDC.remove();
		}
		return listTipDocNotif;
	}
	
	// Fin [nchavez 26/05/2015]
	/* Seteo - Spring - Inicio */
	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}

	/* Seteo - Spring - Final */
	
}