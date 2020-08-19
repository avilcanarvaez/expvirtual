package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualFechaGenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesPorResponsableBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorFechaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ReporteIndicadoresDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresDocumentosExpedientesPorDepBean;

public class ReportesIndicadoresServiceImpl implements ReportesIndicadoresService {
	private static final Log log = LogFactory.getLog(ReportesIndicadoresServiceImpl.class);

	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;
	private SequenceDAO sequenceDAO;
	private T01ParamDAO t01ParamDAO;
	private GeneralService generalService;
	private CatalogoService catalogoService;
	private ValidarParametrosService validarParametrosService;
	private ReporteIndicadoresDAO reporteIndicadoresDAO;
	private PersonaService personaService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]

	@Override
	public List<ReporteIndicadoresExpedienteVirtualDependBean> reporteIndicadorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedienteVirtualDependBean> listaReporteIndicadorBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.ReporteIndicadorDepedencias");

		try {

			listaReporteIndicadorBean = reporteIndicadoresDAO.reportePorDepedencias(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.obtenerListaExpedienteVirtual");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}

	@Override
	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reporteIndicadorFechasGeneracion(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedienteVirtualFechaGenBean> listaReporteIndicadorBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.ReporteIndicadorFechasGeneracion");

		try {

			listaReporteIndicadorBean = reporteIndicadoresDAO.reportePorFechasGeneracion(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.ReporteIndicadorFechasGeneracion");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.ReporteIndicadorFechasGeneracion");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}

	@Override
	public List<ReporteIndicadoresExpedienteVirtualDependBean> reporteIndicadorDepedenciasConsultXUsuIntYContrib(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedienteVirtualDependBean> listaReporteIndicadorBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteIndicadorDepedenciasConsultXUsuIntYContrib");

		try {

			listaReporteIndicadorBean = reporteIndicadoresDAO.reportePorDepedenciasConsultXUsuIntYContrib(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteIndicadorDepedenciasConsultXUsuIntYContrib");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteIndicadorDepedenciasConsultXUsuIntYContrib");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}

	@Override
	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reporteIndicadorFechasGeneracionConsultXUsuIntYContrib(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedienteVirtualFechaGenBean> listaReporteIndicadorBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteIndicadorFechasGeneracionConsultXUsuIntYContrib");

		try {

			listaReporteIndicadorBean = reporteIndicadoresDAO.reportePorFechasGeneracionConsultXUsuIntYContrib(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteIndicadorFechasGeneracionConsultXUsuIntYContrib");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteIndicadorFechasGeneracionConsultXUsuIntYContrib");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}

	@Override
	public List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> reporteExpdteGenerPorDependConsultados(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> listaReporteIndicadorBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");

		try {
			listaReporteIndicadorBean = reporteIndicadoresDAO.reporteExpdteGenerPorDependConsultados(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}
	@Override
	public List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> reporteExpdtesCobranzaAcumPorDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> listaReporteIndicadorBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteExpdtesCobranzaAcumPorDependencia");
		
		try {
//			public List<ReporteIndicadoresExpedientesVirtualesDocumpentosPorDependenciaBean> reporteExpdtesCobranzaAcumPorDependencia(Map<String, Object> parametros)
			listaReporteIndicadorBean = reporteIndicadoresDAO.reporteExpdtesCobranzaAcumPorDependencia(mapParametrosBusqueda);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteExpdtesCobranzaAcumPorDependencia");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteExpdtesCobranzaAcumPorDependencia");
			
			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}
	@Override
	public List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> reporteCantidadDocumentosPorDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> listaReporteIndicadorBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");
		
		try {
			listaReporteIndicadorBean = reporteIndicadoresDAO.reporteCantidadDocumentosPorDependencia(mapParametrosBusqueda);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteExpdteGenerPorDependConsultados");
			
			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorBean;
	}

	@Override
	public List<Map<String, Object>> cantDocsPorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<Map<String, Object>> map = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.cantDocsPorDepedencias");

		try {

			map = reporteIndicadoresDAO.cantDocsPorDepedencias(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.cantDocsPorDepedencias");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.cantDocsPorDepedencias");

			NDC.pop();
			NDC.remove();
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> cantDocsPorFechaGeneracion(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<Map<String, Object>> map = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.cantDocsPorFechaGeneracion");

		try {

			map = reporteIndicadoresDAO.cantDocsPorFechaGeneracion(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.cantDocsPorFechaGeneracion");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.cantDocsPorFechaGeneracion");

			NDC.pop();
			NDC.remove();
		}
		return map;
	}
	
	@Override
	public List<Map<String, Object>> cantGrupoDocsPorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<Map<String, Object>> map = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.cantGrupoDocsPorDepedencias");

		try {

			map = reporteIndicadoresDAO.cantGrupoDocsPorDepedencias(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.cantGrupoDocsPorDepedencias");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.cantGrupoDocsPorDepedencias");

			NDC.pop();
			NDC.remove();
		}
		return map;
	}

	public List<Map<String, Object>> cantDocsPorTipo(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<Map<String, Object>> map = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.cantDocsPorTipo");

		try {

			map = reporteIndicadoresDAO.cantDocsPorTipo(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.cantDocsPorTipo");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.cantDocsPorTipo");

			NDC.pop();
			NDC.remove();
		}
		return map;
	}
	@Override
	public List<Map<String, Object>> cantGrupoDocsPorFecha(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<Map<String, Object>> map = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.cantGrupoDocsPorFecha");

		try {

			map = reporteIndicadoresDAO.cantGrupoDocsPorFecha(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.cantGrupoDocsPorFecha");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.cantGrupoDocsPorFecha");

			NDC.pop();
			NDC.remove();
		}
		return map;
	}
	//Inicio [gangles 18/08/2016] --Se agregan los reportes por responsables y Documentos
	@Override
	public List<ReporteIndicadoresExpedientesPorResponsableBean> reporteRptExpGeneradosPorResponsables(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<ReporteIndicadoresExpedientesPorResponsableBean> listaReporteResponsablesBean = null;
		Map<String, Object> mapParam = null;
		String tmpCodResp = null;
		Map<String, String> mapaResps = new HashMap<String, String>();
		Map<String, Object> mapaTemp = null;
		T02PerdpBean personaBean = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteRptExpGeneradosPorResponsables");

		try {
			listaReporteResponsablesBean = reporteIndicadoresDAO.reporteRptExpGeneradosPorResponsables(mapParametrosBusqueda);
			
			// CARGA DECRIPCION DEPENDENCIA
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParam.put("indLimite", true);
			mapParam.put("limInferior", CatalogoConstantes.LIMITE_INFERIOR_DEPENDENCIA);
			mapParam.put("limSuperior", CatalogoConstantes.LIMITE_SUPERIOR_DEPENDENCIA);
			
			Map<String, Object> mapaDepTI = catalogoService.obtenerCatalogo(mapParam);
			Map<String, Object> mapaDepTA = aduanaService.obtenerDependencias();
	
			// MAPA DE PROCESOS
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			Map<String, Object> mapProcesos = catalogoService.obtenerCatalogo(mapParam);
	
			// MAPA DE TIPOS DE EXPEDIENTE
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			Map<String, Object> mapTipExps = catalogoService.obtenerCatalogo(mapParam);
	
			// MAPA DE TIPOS DE ORIGEN
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapOrig = catalogoService.obtenerCatalogo(mapParam);
			// MAPA DE ESTADOS
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapEstados = catalogoService.obtenerCatalogo(mapParam);
			
			for (ReporteIndicadoresExpedientesPorResponsableBean reporteResponsables : listaReporteResponsablesBean){
				tmpCodResp = reporteResponsables.getCodResponsable().trim();
				if (tmpCodResp.equals("-")) {
					reporteResponsables.setNombreResponsable(ValidaConstantes.CADENA_VACIA);					
				} else {
					if (mapaResps.get(tmpCodResp) == null) {
						mapaTemp = new HashMap<String,Object>();
						mapaTemp.put("codPersona", tmpCodResp);
						personaBean = personaService.validarPersona(mapaTemp);
						if (personaBean != null) {
							personaBean = personaService.completarInformacionPersona(personaBean);
							reporteResponsables.setNombreResponsable(personaBean.getDesNombreCompleto());
						} else {
							reporteResponsables.setNombreResponsable(ValidaConstantes.CADENA_VACIA);
						}
						mapaResps.put(tmpCodResp, reporteResponsables.getNombreResponsable());
					} else {
						reporteResponsables.setNombreResponsable(mapaResps.get(tmpCodResp));
					}
				}
				reporteResponsables.setDesDependencia(Utils.toStr(mapaDepTI.get(reporteResponsables.getCodDependencia())));
				if (reporteResponsables.getDesDependencia() == null) {
					reporteResponsables.setDesDependencia(Utils.toStr(mapaDepTA.get(reporteResponsables.getCodDependencia().trim())));
				}
				reporteResponsables.setDesProceso(Utils.toStr(mapProcesos.get(reporteResponsables.getCodProceso())));
				reporteResponsables.setDesTipoExpediente(Utils.toStr(mapTipExps.get(reporteResponsables.getCodTipoExpediente())));
				reporteResponsables.setDesEstado(Utils.toStr(mapEstados.get(reporteResponsables.getCodEstado())));
				reporteResponsables.setDesOrigen(Utils.toStr(mapOrig.get(reporteResponsables.getCodOrigen())));
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteRptExpGeneradosPorResponsables");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteRptExpGeneradosPorResponsables");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteResponsablesBean;
	}
	
	@Override
	public List<ReporteIndicadoresDocumentosExpedientesPorDepBean> reporteRptCantDocumentosPorExpDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {

		List<ReporteIndicadoresDocumentosExpedientesPorDepBean> listaReporteDocumentosExpBean = null;
		
		Map<String, Object> mapa = new HashMap<String, Object>();

		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.ReporteIndicadoresDocumentosExpedientesPorDepBean");

		try {
			listaReporteDocumentosExpBean = reporteIndicadoresDAO.reporteRptCantDocumentosPorExpDependencia(mapParametrosBusqueda);
			
			// MAPA DE PROCESOS
			Map<String, Object> paramsProc = new HashMap<String, Object>();
			paramsProc.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			Map<String, Object> mapProcesos = catalogoService.obtenerCatalogo(paramsProc);
	
			// MAPA DE TIPOS DE EXPEDIENTE
			Map<String, Object> paramsTipExpd = new HashMap<String, Object>();
			paramsTipExpd.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			Map<String, Object> mapTipExps = catalogoService.obtenerCatalogo(paramsTipExpd);
	
			// MAPA DE TIPOS DE ORIGEN
			Map<String, Object> paramsOrig = new HashMap<String, Object>();
			paramsOrig.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapOrig = catalogoService.obtenerCatalogo(paramsOrig);
			// MAPA DE ESTADOS
			Map<String, Object> paramsEstados = new HashMap<String, Object>();
			paramsEstados.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapEstados = catalogoService.obtenerCatalogo(paramsEstados);
			
			Map<String, Object> mapaTipoDocs =  new HashMap<String, Object>();
			Map<String, Object> tempMapa = null;
			Map<String, Object> mapParams = null;
			
			//Obtención de la  descripción de Tipos de documentos
			mapParams = new HashMap<String,Object>();
			
			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if(tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}
			
			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if(tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}			
			
			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if(tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}
			
			for (ReporteIndicadoresDocumentosExpedientesPorDepBean reporteDocumentosExpedientes : listaReporteDocumentosExpBean){
				reporteDocumentosExpedientes.setDesDependencia(Utils.toStr(mapaDependencias.get(reporteDocumentosExpedientes.getCodDependencia().trim())));
				reporteDocumentosExpedientes.setDesProceso(Utils.toStr(mapProcesos.get(reporteDocumentosExpedientes.getCodProceso())));
				reporteDocumentosExpedientes.setDesTipoExpediente(Utils.toStr(mapTipExps.get(reporteDocumentosExpedientes.getCodTipoExpediente())));
				reporteDocumentosExpedientes.setDesTipoDocumento(Utils.toStr(mapaTipoDocs.get(reporteDocumentosExpedientes.getCodTipoDocumento())));
				reporteDocumentosExpedientes.setDesEstado(Utils.toStr(mapEstados.get(reporteDocumentosExpedientes.getCodEstado())));
				reporteDocumentosExpedientes.setDesOrigen(Utils.toStr(mapOrig.get(reporteDocumentosExpedientes.getCodOrigen())));
			}
			

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.ReporteIndicadoresDocumentosExpedientesPorDepBean");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.ReporteIndicadoresDocumentosExpedientesPorDepBean");

			NDC.pop();
			NDC.remove();
		}
		return listaReporteDocumentosExpBean;
	}
	//Fin [gangles 18/08/2016] --Se agregan los reportes por responsables y Documentos
	
	//Inicio [jtejada 24/08/2016]
	public List<ReporteIndicadoresRepresImprPorDepBean> reporteIndicadorRepresImprPorDepend(Map<String, Object> mapParametrosBusqueda)  throws Exception{
		List<ReporteIndicadoresRepresImprPorDepBean> listaReporteIndicadorRepreImprPorDepBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorDepend");
		
		try {
			listaReporteIndicadorRepreImprPorDepBean = reporteIndicadoresDAO.reporteIndicadorRepresImprPorDepend(mapParametrosBusqueda);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorDepend");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorDepend");
			
			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorRepreImprPorDepBean;
	}
	
	public List<ReporteIndicadoresRepresImprPorFechaBean> reporteIndicadorRepresImprPorFecha(Map<String, Object> mapParametrosBusqueda) throws Exception{
		List<ReporteIndicadoresRepresImprPorFechaBean> listaReporteIndicadorRepreImprPorFecBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorFecha");
		
		try {
			listaReporteIndicadorRepreImprPorFecBean = reporteIndicadoresDAO.reporteIndicadorRepresImprPorFecha(mapParametrosBusqueda);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorFecha");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.reporteIndicadorRepresImprPorFecha");
			
			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorRepreImprPorFecBean;
	}
	
	public List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> reporteIndicadorPorExpdteYDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception{
		List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> listaReporteIndicadorRepreImprPorExpdteYDependenciaBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReportesIndicadoresServiceImpl.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean");
		
		try {
			listaReporteIndicadorRepreImprPorExpdteYDependenciaBean = reporteIndicadoresDAO.reporteIndicadorRepresImprPorExpdteYDependenciaBean(mapParametrosBusqueda);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReportesIndicadoresServiceImpl.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReportesIndicadoresServiceImpl.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean");
			
			NDC.pop();
			NDC.remove();
		}
		return listaReporteIndicadorRepreImprPorExpdteYDependenciaBean;
	}
	
	//Fin [jtejada 24/08/2016]
	
	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}

	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setReporteIndicadoresDAO(ReporteIndicadoresDAO reporteIndicadoresDAO) {
		this.reporteIndicadoresDAO = reporteIndicadoresDAO;
	}
	
	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	

}
