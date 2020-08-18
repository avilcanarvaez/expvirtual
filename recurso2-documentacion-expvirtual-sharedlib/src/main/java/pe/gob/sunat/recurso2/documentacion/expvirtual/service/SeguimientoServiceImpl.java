package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6622SeguimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class SeguimientoServiceImpl implements SeguimientoService {
	private static final Log log = LogFactory.getLog(SeguimientoServiceImpl.class);
	private T6622SeguimDAO t6622SeguimDAO;
	private SequenceDAO sequenceDAO;
	private GeneralService generalService;
	private CatalogoService catalogoService;
	
	@Override
	public void registrarSeguimiento(Map<String, Object> parametros)
			throws Exception {
		Long numSeq = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - SeguimientoServiceImpl.registrarSeguimiento");
		
		try {
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_SEGUIM);
			parametros.put("num_corseg", numSeq);
			//Inicio - [oachahuic][PAS20175E210400016]
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			//Fin - [oachahuic][PAS20175E210400016]
			t6622SeguimDAO.insertar(parametros);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - SeguimientoServiceImpl.registrarSeguimiento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - SeguimientoServiceImpl.registrarSeguimiento");
			
			NDC.pop();
			NDC.remove();
		}
		
	}
	
	@Override
	public List<T6622SeguimBean> listarSeguimientos(
			Map<String, Object> mapParametrosBusqueda) throws Exception {
		List<T6622SeguimBean> listT6622SeguimBean = null;
		Map<String, Object> mapParams = null;
		Map<String, Object> mapaTipoDocs = null;
		String codTipoDocumento = null;
		String desTipdoc = null;
		String fechaSegui = ValidaConstantes.CADENA_VACIA;
		Map<String, Object> mapaEstadoDocs = null;
		Map<String, Object> mapaEtapasDocs = null;
		String codEstado = ValidaConstantes.CADENA_VACIA;
		String codEstapa = ValidaConstantes.CADENA_VACIA;
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
		
		try {
			
			mapParams = new HashMap<String,Object>();
			
			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			mapaTipoDocs = catalogoService.obtenerCatalogo(mapParams);
			
			mapParams = new HashMap<String,Object>();
			
			mapParams.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_ORIGEN);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			mapaEstadoDocs = catalogoService.obtenerCatalogo(mapParams);
			
			mapParams = new HashMap<String,Object>();
			
			mapParams.put("codClase", CatalogoConstantes.CATA_ETAPAS_EXPEDIENTE_ORIGEN);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			mapaEtapasDocs = catalogoService.obtenerCatalogo(mapParams);
			
			listT6622SeguimBean = t6622SeguimDAO.listarSeguimientos(mapParametrosBusqueda);
			int cont=1;
			for (T6622SeguimBean t6622Seguim : listT6622SeguimBean) {
				codTipoDocumento = t6622Seguim.getCodTipoDocumento().trim();
				if( Utils.isEmpty(codTipoDocumento) || Utils.equiv(codTipoDocumento, ValidaConstantes.SEPARADOR_GUION)){
					t6622Seguim.setDesTipoDocumento(ValidaConstantes.SEPARADOR_GUION);
				}else{
					desTipdoc = (String)mapaTipoDocs.get(codTipoDocumento);
					t6622Seguim.setDesTipoDocumento(Utils.toStr(codTipoDocumento+" "+desTipdoc));
				}
				if(!Utils.isEmpty(t6622Seguim.getFec_seguim())){
					fechaSegui = Utils.dateUtilToStringDDMMYYYY(t6622Seguim.getFec_seguim());
					t6622Seguim.setFecVistaSegui(fechaSegui);
				}else{
					t6622Seguim.setFecVistaSegui(ValidaConstantes.SEPARADOR_GUION);
				}
				
				codEstado = t6622Seguim.getCodEstExpOrigen().trim();
				if(Utils.isEmpty(codEstado) || codEstado.equals(ValidaConstantes.SEPARADOR_GUION)){
					t6622Seguim.setDesEstado(ValidaConstantes.CADENA_VACIA);// [jquispe 02/07/2016] Se debe de mostrar vacio.
				}else{
					t6622Seguim.setDesEstado(Utils.toStr(mapaEstadoDocs.get(codEstado)));
				}
				
				codEstapa = t6622Seguim.getCodEtapaExpOrigen().trim();
				if(Utils.isEmpty(codEstapa) || codEstapa.equals(ValidaConstantes.SEPARADOR_GUION)){
					t6622Seguim.setDesEtapa(ValidaConstantes.CADENA_VACIA);// [jquispe 02/07/2016] Se debe de mostrar vacio.
				}else{
					t6622Seguim.setDesEtapa(Utils.toStr(mapaEtapasDocs.get(codEstapa)));
				}
				
				t6622Seguim.setNumOrden(cont);
				cont++;
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6622SeguimBean;
	}
	
	/*
	 * @Override
	public List<T6614ExpVirtualBean> obtenerListaSeguimientos(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		List<T6622SeguimBean> listT6614ExpVirtualBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
		
		try {
			String codigoProceso;
			String codigoTipoExpediente;
			String codigoOrigen;
			String codigoEstado;
			String codigoEstadoCierre;
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaEstadoCierreExpediente = catalogoService.obtenerCatalogo(mapa);
						
			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listarDocumentoExpediente(mapParametrosBusqueda);
			int i=1;
			for (T6614ExpVirtualBean t6614ExpVirBean : listT6614ExpVirtualBean) {
				codigoProceso = t6614ExpVirBean.getCodProceso();
				codigoTipoExpediente = t6614ExpVirBean.getCodTipoExpediente();
				codigoOrigen =  t6614ExpVirBean.getCodOrigen();
				codigoEstado =  t6614ExpVirBean.getCodEstado();
				codigoEstadoCierre=t6614ExpVirBean.getCodEstadoCierre();
				
				t6614ExpVirBean.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(codigoOrigen)));
				t6614ExpVirBean.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(codigoEstado)));
				t6614ExpVirBean.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
				t6614ExpVirBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));
				t6614ExpVirBean.setDesEstadoCierre(Utils.toStr(mapaEstadoCierreExpediente.get(codigoEstadoCierre)));
				
				t6614ExpVirBean.setNumOrden(i);
				i++;
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}
	*/
	
	/* Seteo de Spring - Inicio */
	public void setT6622SeguimDAO(T6622SeguimDAO t6622SeguimDAO) {
		this.t6622SeguimDAO = t6622SeguimDAO;
	}

	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	/* Seteo de Spring - Final */
}
