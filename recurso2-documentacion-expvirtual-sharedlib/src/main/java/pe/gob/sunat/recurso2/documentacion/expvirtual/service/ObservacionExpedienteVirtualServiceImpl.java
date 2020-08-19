package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6615ObsExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class ObservacionExpedienteVirtualServiceImpl implements ObservacionExpedienteVirtualService {
	private static final Log log = LogFactory.getLog(GeneralServiceImpl.class);

	private SequenceDAO sequenceDAO;
	private T6615ObsExpDAO t6615ObsExpDAO;	
	private GeneralService generalService;;
	private PersonaService personaService;
	// Inicio [jquispe 27/05/2016]
	private SeguimientoService seguiService;
	private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	
	public String registrarObservacion(Map<String, Object> mapParametros) throws Exception {
		
		//DataSource origenDatos;
		T6615ObsExpBean t6615ObsExpBean = new T6615ObsExpBean();
		Map<String, Object> mapDatos;
		Integer numObservacion = null;
		
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

		try {
			if(!Utils.isEmpty(mapParametros.get("t6615ObsExpBean"))){
				t6615ObsExpBean = (T6615ObsExpBean) mapParametros.get("t6615ObsExpBean");
			}else{
				return "";
			}

			numObservacion = Integer.parseInt(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_OBS)));
			
			t6615ObsExpBean.setNumObservacion(numObservacion);
			t6615ObsExpBean.setFechaGeneracionObs(new Date());
			t6615ObsExpBean.setFechaRegistro(new Date());
			t6615ObsExpBean.setCodUsuarioGeneraObs(Utils.toStr(mapParametros.get("usuarioRegistro")));
			t6615ObsExpBean.setCodUsuRegis(Utils.toStr(mapParametros.get("usuarioRegistro")));
			
			// Data Source
			Map<String, Object> mapDataSource = new HashMap<String, Object>();
			mapDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			//origenDatos = generalService.obtenerOrigenDatos(mapDataSource);
			mapaPool = generalService.obtenerOrigenDatosMap(mapDataSource);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			
			mapDatos = new HashMap<String, Object>();
			mapDatos.put("t6615ObsExpBean", t6615ObsExpBean);
			//mapDatos.put("origenDatos", origenDatos);
			t6615ObsExpDAO.insertar(mapDatos);
			
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoRegistroObservacion(t6615ObsExpBean.getNumExpedienteVirtual());
			// Fin [jquispe 27/05/2016]
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ObservacionExpedienteVirtualServiceImpl.registrarObservacion");
			ex.printStackTrace();
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ObservacionExpedienteVirtualServiceImpl.registrarObservacion");
			
			NDC.pop();
			NDC.remove();
		}
		return Utils.toStr(numObservacion);
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoRegistroObservacion(String numeroExpedienteVirtual) throws Exception{
		
		Map<String, Object> beanSegui = new HashMap<String, Object>();
		
		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();
		
		// Fecha fin
		Calendar fechaVacia =  Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);
		
		//Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String,Object>();		
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);		
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);     					
		
		beanSegui.put("num_expedv", numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv",  ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_OBSERVACION);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_REGISTRAR_OBSERVACION);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_REGISTRAR_OBSERVACION)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);					
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		
		seguiService.registrarSeguimiento(beanSegui);
	}
	// Fin [jquispe 27/05/2016]

	public List<T6615ObsExpBean> obtenerObservacionesExpediente(Map<String, Object> mapParametrosBusqueda) throws Exception{
			
			List<T6615ObsExpBean> listT6615ObsExpBean = null;
			
			if (log.isDebugEnabled()) log.debug("Inicio - ObservacionExpedienteVirtualServiceImpl.obtenerObservacionesExpediente");
			Map<String, Object> parametros = new HashMap<String, Object>();
			T02PerdpBean responsable;
			try {
				String codUsuarioRegObservacion;
				int cont=1;
				
				listT6615ObsExpBean = t6615ObsExpDAO.listarObservaciones(mapParametrosBusqueda);
				
				if (!Utils.isEmpty(listT6615ObsExpBean)){
					
					for (T6615ObsExpBean t6615ObsExpBean : listT6615ObsExpBean) {
						
						codUsuarioRegObservacion = t6615ObsExpBean.getCodUsuarioGeneraObs();
						parametros.put("codPersona", codUsuarioRegObservacion);
						
						responsable = personaService.validarPersona(parametros);
						if(responsable != null) {
							responsable = personaService.completarInformacionPersona(responsable);
						}
						
						if(!Utils.isEmpty(responsable)){
							t6615ObsExpBean.setNomUsuRegistraObs(responsable.getDesNombreCompleto());
							t6615ObsExpBean.setNomUsuGeneraObs(responsable.getDesNombreCompleto());
						}
						
						t6615ObsExpBean.setNumOrden(cont);
						t6615ObsExpBean.setFecRegistroObs(Utils.dateUtilToStringDDMMYYYY(t6615ObsExpBean.getFechaRegistro()));
						t6615ObsExpBean.setFecGeneracionObs(Utils.dateUtilToStringDDMMYYYY(t6615ObsExpBean.getFechaGeneracionObs()));
						cont++;
					}
					
				}
				
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - ObservacionExpedienteVirtualServiceImpl.obtenerObservacionesExpediente");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ObservacionExpedienteVirtualServiceImpl.obtenerObservacionesExpediente");
				
				NDC.pop();
				NDC.remove();
			}
			return listT6615ObsExpBean;
		}
	
	// Inicio [jquispe 05/07/2016] Obtiene la consulta de observaciones.
	public List<T6615ObsExpBean> consultarObservacionesExpediente(String numeroExpedienteVirtual) throws Exception{
		List<T6615ObsExpBean> listT6615ObsExpBean = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ObservacionExpedienteVirtualServiceImpl.consultarObservacionesExpediente");
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numExpedVirtual", numeroExpedienteVirtual);			
			listT6615ObsExpBean = t6615ObsExpDAO.listarObservaciones(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ObservacionExpedienteVirtualServiceImpl.consultarObservacionesExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ObservacionExpedienteVirtualServiceImpl.consultarObservacionesExpediente");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6615ObsExpBean;
	}
	// Fin [jquispe 05/07/2016]
	
	/*Sets*/


	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setT6615ObsExpDAO(T6615ObsExpDAO t6615ObsExpDAO) {
		this.t6615ObsExpDAO = t6615ObsExpDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	// Inicio [jquispe 27/05/2016]
	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}	
	// Fin [jquispe 27/05/2016]
} 