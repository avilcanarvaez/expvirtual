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
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;

public class ParametroServiceImpl implements ParametroService{
	
	private static final Log log = LogFactory.getLog(ParametroServiceImpl.class);
	
	private T01ParamDAO t01ParamDAO;
	

	
	@Override
	public List<T01ParamBean> listarNumeroTipoExpediente() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarNumeroTipoExpediente");
		
		List<T01ParamBean> listadoNumeroTipoExpedienteBean = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarNumeroTipoExpediente");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_TIPOS_NUM_EXPEDIENTE);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoNumeroTipoExpedienteBean = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoNumeroExpedienteBean : listadoNumeroTipoExpedienteBean) {
				
				String codClase = tipoNumeroExpedienteBean.getCodClase().trim();
				String indTipo = tipoNumeroExpedienteBean.getIndTipo().trim();
				String codParametro = tipoNumeroExpedienteBean.getCodParametro().trim();
				String desParametro = tipoNumeroExpedienteBean.getDesParametro().trim();
				
				tipoNumeroExpedienteBean.setCodClase(codClase);
				tipoNumeroExpedienteBean.setIndTipo(indTipo);
				tipoNumeroExpedienteBean.setCodParametro(codParametro);
				tipoNumeroExpedienteBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarNumeroTipoExpediente");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarNumeroTipoExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listadoNumeroTipoExpedienteBean;
		
	}
	
	@Override
	public List<T01ParamBean> listarTipoFecha() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarTipoFecha");
		
		List<T01ParamBean> listarTipoFechaBean = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_TIPOS_FECHA_EXPEDIENTE);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listarTipoFechaBean = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoFechaBean : listarTipoFechaBean) {
				
				String codClase = tipoFechaBean.getCodClase().trim();
				String indTipo = tipoFechaBean.getIndTipo().trim();
				String codParametro = tipoFechaBean.getCodParametro().trim();
				String desParametro = tipoFechaBean.getDesParametro().trim();
				
				tipoFechaBean.setCodClase(codClase);
				tipoFechaBean.setIndTipo(indTipo);
				tipoFechaBean.setCodParametro(codParametro);
				tipoFechaBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarTipoFecha");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarTipoFecha");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listarTipoFechaBean;
		
	}
	
	@Override
	public List<T01ParamBean> listaEstDocumento() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarTipoFecha");
		
		List<T01ParamBean> listaEstDocumento = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_ORIGEN);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listaEstDocumento = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoFechaBean : listaEstDocumento) {
				
				String codClase = tipoFechaBean.getCodClase().trim();
				String indTipo = tipoFechaBean.getIndTipo().trim();
				String codParametro = tipoFechaBean.getCodParametro().trim();
				String desParametro = tipoFechaBean.getDesParametro().trim();
				
				tipoFechaBean.setCodClase(codClase);
				tipoFechaBean.setIndTipo(indTipo);
				tipoFechaBean.setCodParametro(codParametro);
				tipoFechaBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarTipoFecha");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarTipoFecha");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listaEstDocumento;
		
	}
	
	@Override
	public List<T01ParamBean> listaEtapaDocumento() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarTipoFecha");
		
		List<T01ParamBean> listaEtapaDocumento = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_ETAPAS_EXPEDIENTE_ORIGEN);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listaEtapaDocumento = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoFechaBean : listaEtapaDocumento) {
				
				String codClase = tipoFechaBean.getCodClase().trim();
				String indTipo = tipoFechaBean.getIndTipo().trim();
				String codParametro = tipoFechaBean.getCodParametro().trim();
				String desParametro = tipoFechaBean.getDesParametro().trim();
				
				tipoFechaBean.setCodClase(codClase);
				tipoFechaBean.setIndTipo(indTipo);
				tipoFechaBean.setCodParametro(codParametro);
				tipoFechaBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarTipoFecha");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarTipoFecha");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listaEtapaDocumento;
		
	}
	
	@Override
	public List<T01ParamBean> listarEstadosCierreExpediente() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarTipoFecha");
		
		List<T01ParamBean> listarTipoFechaBean = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listarTipoFechaBean = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoFechaBean : listarTipoFechaBean) {
				
				String codClase = tipoFechaBean.getCodClase().trim();
				String indTipo = tipoFechaBean.getIndTipo().trim();
				String codParametro = tipoFechaBean.getCodParametro().trim();
				String desParametro = tipoFechaBean.getDesParametro().trim();
				
				tipoFechaBean.setCodClase(codClase);
				tipoFechaBean.setIndTipo(indTipo);
				tipoFechaBean.setCodParametro(codParametro);
				tipoFechaBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarTipoFecha");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarTipoFecha");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listarTipoFechaBean;
		
	}
	
	@Override
	public List<T01ParamBean> listarParametros(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.listarTipoFecha");
		
		List<T01ParamBean> listarTipoFechaBean = new ArrayList<T01ParamBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listarTipoFechaBean = t01ParamDAO.listar(parametros);
			
			for (T01ParamBean tipoFechaBean : listarTipoFechaBean) {
				
				String codClase = tipoFechaBean.getCodClase().trim();
				String indTipo = tipoFechaBean.getIndTipo().trim();
				String codParametro = tipoFechaBean.getCodParametro().trim();
				String desParametro = tipoFechaBean.getDesParametro().trim();
				
				tipoFechaBean.setCodClase(codClase);
				tipoFechaBean.setIndTipo(indTipo);
				tipoFechaBean.setCodParametro(codParametro);
				tipoFechaBean.setDesParametro(desParametro);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.listarParametros");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.listarParametros");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listarTipoFechaBean;
	}
	
	//[PAS20191U210500144] ETITO - INICIO
	@Override
	public T01ParamBean obtenerServicioSignnet() throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ParametroServiceImpl.obtenerServicioSignnet");
		
		T01ParamBean servicioSignnet;
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ParametroServiceImpl.listarTipoFecha");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codClase", CatalogoConstantes.CATA_WS_ECM);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			parametros.put("codParametro", CatalogoConstantes.ARGUMENTO_SIGNNET);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			servicioSignnet = t01ParamDAO.obtener(parametros);
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ParametroServiceImpl.obtenerServicioSignnet");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ParametroServiceImpl.obtenerServicioSignnet");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return servicioSignnet;
		
	}
	
	//[PAS20191U210500144] ETITO - FIN	
	
	/* Seteo de Spring - Inicio */
	
	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}
	
	
	
	/* Seteo de Spring - Final */
	
}