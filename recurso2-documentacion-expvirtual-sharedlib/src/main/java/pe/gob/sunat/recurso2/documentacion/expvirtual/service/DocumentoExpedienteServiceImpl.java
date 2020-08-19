package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocNotSineBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DocInteDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6303DetPedidoDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6622SeguimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DocumentoExpedienteServiceImpl implements DocumentoExpedienteService {
	
	private static final Log log = LogFactory.getLog(DocumentoExpedienteServiceImpl.class);
	private CorreosService correosService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;	
	private T6622SeguimDAO t6622SeguimDAO;
	private T6303DetPedidoDAO t6303DetPedidoDAO;
	private T01ParamDAO t01ParamDAO;
	//[PAS20191U210500144] - INICIO
	private DocInteDAO docInteDAO; 
	private SeguimientoService seguiService;
	//[PAS20191U210500144] - FIN
	
	//[PAS20191U210500144] Inicio 
	@SuppressWarnings("unchecked")
	public String modificarDocumentoExpediente(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapResponse = null;		
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteService .modificarDocumentoExpediente");		
		try {			
			t6613DocExpVirtBean = new T6613DocExpVirtBean();			
			t6613DocExpVirtBean.setNumCorDoc(new Integer(parametros.get("numCorDoc").toString()));			
			t6613DocExpVirtBean.setIndVisDocumento(parametros.get("indVisDocumento").toString());
			t6613DocExpVirtBean.setIndReserTrib(parametros.get("indReserTrib").toString());
			t6613DocExpVirtBean.setNumCorDocRel(new Integer(parametros.get("numCorDocRel").toString()));
			t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuModif").toString());				
			t6613DocExpVirtBean.setFecModif(new Date());
			
			//VERIFICAR SI SE CAMBIARON VALORES EN DOCUMENTO INTERNO
			Map<String, Object>	mapDocumento = new HashMap<String, Object>();
			log.debug("numExpeDv:"+parametros.get("numExpeDv").toString());
			mapDocumento.put("numExpeDv", parametros.get("numExpeDv").toString());
			mapDocumento.put("numCorDoc", parametros.get("numCorDoc").toString());
			mapDocumento.put("indVisDocumentoNew", parametros.get("indVisDocumento").toString());
			mapDocumento.put("indReserTribNew", parametros.get("indReserTrib").toString());
			mapDocumento.put("numCorDocRelNew", parametros.get("numCorDocRel").toString());
			Map<String, Object> cambiosMap = obtenerCambiosDocumentoInterno(mapDocumento);
			log.debug("cambiosMap: " + cambiosMap);
			
			mapResponse = new LinkedHashMap<String, Object>();
			
			if(Integer.valueOf(cambiosMap.get("numCambios").toString()) > 0) {
				log.debug("cambiosMap.get(numCambios): " + cambiosMap.get("numCambios"));
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				//MODIFICAR DOCUMENTO INTERNO
				t6613DocExpVirtDAO.update(t6613DocExpVirtBean);
				
				//AGREGAR SEGUIMIENTO DEL DOCUMENTO - AUDITORIA
				mapDocumento = new HashMap<String, Object>();
				log.debug("Seguimiento:"+parametros.get("numExpeDv").toString());
				mapDocumento.put("numExpeDv", parametros.get("numExpeDv").toString());
				log.debug("numExpeDv:"+ parametros.get("numExpeDv").toString());
				log.debug("desDatCons:"+ cambiosMap.get("message").toString());
				mapDocumento.put("desDatCons", cambiosMap.get("message").toString());				
				registrarSeguimientoActualizacionDocumento(mapDocumento);
				
				//ENVIAR CORREO A RESPONSABLES
				for (CorreosBean bean : (List<CorreosBean>)parametros.get("listCorreos")) {
					Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
					mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
					String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_MOD_DOC_EXP_VIRT.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("desTipDoc").toString());
					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, parametros.get("numDoc").toString());
					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_2, parametros.get("numExpeDv").toString());
					mapEnvioCorreo.put("mensaje", mensaje);
					correosService.enviarCorreo(mapEnvioCorreo);
				}
				
				mapResponse.put("status", true);
				mapResponse.put("message", AvisoConstantes.AVISO_MODULO_03_02_005.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("numDoc").toString()));
			} else {
				mapResponse.put("status", true);
				mapResponse.put("message", "No se realizaron actualizaciones al documento interno");
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteService .modificarDocumentoExpediente");
			//log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteService .modificarDocumentoExpediente");
			
			NDC.pop();
			NDC.remove();
		}		
		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);		
		return jsonResponse;		
	}
	
	public Map<String, Object> obtenerCambiosDocumentoInterno(Map<String, Object> parametros ) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		T6613DocExpVirtBean t6613BeanOld = null;
		String mensaje = "Actualizar el ";
		try {
			t6613BeanOld  = (T6613DocExpVirtBean) t6613DocExpVirtDAO.obtenerDocumentoInterno(parametros);
		
			Integer numCambios= 0;
			if (!parametros.get("indVisDocumentoNew").equals(t6613BeanOld.getIndVisDocumento())) {
				mensaje = mensaje + "Indicador de Visibilidad, ";
				numCambios ++;
			} 
			if(!parametros.get("indReserTribNew").equals(t6613BeanOld.getIndReserTrib())) {
				mensaje = mensaje + "Indicador de Reserva Tributaria, ";
				numCambios ++;
			} 
			if((Integer.valueOf(parametros.get("numCorDocRelNew").toString())).compareTo((Integer)t6613BeanOld.getNumCorDocRel()) != 0 ) {
				mensaje = mensaje + "Documento Relacionado, ";
				numCambios ++;
			}
			
			int posicion = mensaje.trim().length() -  1;
			mensaje = mensaje.substring(0, posicion) + ".";

			resultMap.put("message", mensaje);
			resultMap.put("numCambios", numCambios);
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteService.obtenerCambiosDocumentoInterno");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteService.obtenerCambiosDocumentoInterno");
			
			NDC.pop();
			NDC.remove();
		}
		
		return resultMap;
	}
	
	//Registra seguimiento de modificacion de documento interno
	public void registrarSeguimientoActualizacionDocumento(Map<String, Object> parametros) throws Exception {
		
		Map<String, Object> beanSegui = new HashMap<String, Object>();
		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Fecha fin
		Calendar fechaVacia =  Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		beanSegui.put("num_expedv", parametros.get("numExpeDv"));
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv",  ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_MODIFICACION_DOCUMENTO_INTERNO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_MODIFICAR_DOCUMENTO_INTERNO);
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("des_datcons", parametros.get("desDatCons"));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		
		log.debug("beanSegui Service: " + beanSegui);
		seguiService.registrarSeguimiento(beanSegui);
	}
	//[PAS20191U210500144] Fin 
 
	
	@SuppressWarnings("unchecked")
	public String EliminarDocumentoExpediente(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapResponse = null;		
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteService .EliminarDocumentoExpediente");		
		try {			
			t6613DocExpVirtBean = new T6613DocExpVirtBean();			
			t6613DocExpVirtBean.setNumCorDoc(new Integer(parametros.get("numCorDoc").toString()));			
			t6613DocExpVirtBean.setCodEstDoc("02");
			t6613DocExpVirtBean.setDesEliDoc(parametros.get("desEliDoc").toString());
			t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuModif").toString());				
			t6613DocExpVirtBean.setFecModif(new Date());
			
			Map<String, Object>	mapDocumento = new HashMap<String, Object>();
			mapDocumento.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			//#############################################
			//TODO DAO - ELIMINAR O ANULAR DOCUMENTO INTERNO
			t6613DocExpVirtDAO.Eliminar(mapDocumento);
			
			//#############################################
			//TODO DAO - ELIMINAR SEGUIMIENTO DEL DOCUMENTO	
			if(((Boolean)parametros.get("flgEstEta"))) {
				mapDocumento = new HashMap<String, Object>();
				mapDocumento.put("numExpeDv", parametros.get("numExpeDv").toString());
				mapDocumento.put("codTipDoc", parametros.get("codTipDoc").toString());
				mapDocumento.put("numDoc", parametros.get("numDoc").toString());
				t6622SeguimDAO.eliminarSeguimientoCambioEstadoEtapa(mapDocumento);
			}
			
			//#############################################
			//TODO DAO - ELIMINAR ACUMULACION DEL DOCUMENTO	
			if(ValidaConstantes.NUMERO_RC_ACUMULADOR.equals(parametros.get("codTipDoc").toString())){
				mapDocumento = new HashMap<String, Object>();				
				mapDocumento.put("indAcu", "1");
				mapDocumento.put("numExpedienteVirtual", parametros.get("numExpeDv").toString());
				t6614ExpVirtualDAO.actualizar(mapDocumento);
				
				mapDocumento = new HashMap<String, Object>();				
				mapDocumento.put("indAcu", "1");
				mapDocumento.put("numAcu", "0");
				mapDocumento.put("numAcuW", parametros.get("numExpeDo").toString());
				t6614ExpVirtualDAO.actualizar(mapDocumento);
			}
			//#############################################			
			//TODO ENVIAR CORREO A RESPONSABLES
			for (CorreosBean bean : (List<CorreosBean>)parametros.get("listCorreos")) {
				Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
				mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
				String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ELI_DOC_EXP_VIRT.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("desTipDoc").toString());
				mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, parametros.get("numDoc").toString());
				mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_2, parametros.get("numExpeDv").toString());
				mapEnvioCorreo.put("mensaje", mensaje);
				correosService.enviarCorreo(mapEnvioCorreo);
			}

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", true);
			mapResponse.put("message", AvisoConstantes.AVISO_MODULO_03_02_003.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("numDoc").toString()));	
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteService .EliminarDocumentoExpediente");
			log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteService .EliminarDocumentoExpediente");
			
			NDC.pop();
			NDC.remove();
		}		
		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);		
		return jsonResponse;		
	}	
	
	public String CantidadDocumentosInternoRelacion(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapResponse = null;
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteService .ConsultarDocumentosInternoRelacion");
		
		try {
			int iCant = t6613DocExpVirtDAO.contarDocumentosRelacion(parametros);
			
			int iCont = t6622SeguimDAO.contarExpedienteSeguimiento(parametros);
			
			mapResponse = new LinkedHashMap<String, Object>();
			if(iCant > 0 ){
				mapResponse.put("status", false);
				mapResponse.put("message", AvisoConstantes.EXCEP_MODULO_03_02_008);
			}else{
				if(iCont > 0){
					mapResponse.put("status", false);
					mapResponse.put("message", AvisoConstantes.EXCEP_MODULO_03_02_010);
				}else{
					mapResponse.put("status", true);
					mapResponse.put("message", "");
				}
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteService .ConsultarDocumentosInternoRelacion");
			log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteService .ConsultarDocumentosInternoRelacion");
			
			NDC.pop();
			NDC.remove();
		}		
		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);		
		return jsonResponse;	
	}	
	//Fin [ATORRESCH 2017-02-21]
	
	@Override
	public List<T6613DocExpVirtBean> listarDocumentosExpediente(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");
		
		List<T6613DocExpVirtBean> listaDocumentosExpediente = new ArrayList<T6613DocExpVirtBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listaDocumentosExpediente = t6613DocExpVirtDAO.listar(parametros);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");
			
			log.error(ex, ex);
			
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listaDocumentosExpediente;
		
	}

	@Override
	public T6613DocExpVirtBean obtenerDocumentoExpediente(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteServiceImpl.obtenerDocumentoExpediente");
		T6613DocExpVirtBean documentoExpediente = new T6613DocExpVirtBean();
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			documentoExpediente = t6613DocExpVirtDAO.obtenerDocOrigen(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.obtenerDocumentoExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.obtenerDocumentoExpediente");
			NDC.pop();
			NDC.remove();
		}
		return documentoExpediente;
	}
	//Inicio - [oachahuic][PAS20181U210400241]
	@Override
	public DocNotSineBean obtenerDocNotPorSINE(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteServiceImpl.obtenerDocNotPorSINE");
		DocNotSineBean docNotSineBean = null;
		try {
			//Recuperar la equivalencia
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_EQUIV_TIP_DOC_SINE);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParam.put("codTipDoc", parametros.get("cod_tip_doc"));
			mapParam.put("codPro", ValidaConstantes.COD_PRO_SINE_MANUAL);
			List<T01ParamBean> listT01ParamBean = t01ParamDAO.listarEquivalencia(mapParam);
			if (!listT01ParamBean.isEmpty()) {
				parametros.put("cod_tip_doc", listT01ParamBean.get(0).getCodParametro());
			}
			
			DataSourceContextHolder.setKeyDataSource("c");
			List<Map<String, Object>> listDocNotif = t6303DetPedidoDAO.listarDocNotif(parametros);
			if (listDocNotif.size() > 0) {
				docNotSineBean = new DocNotSineBean();
				StringBuilder sbDesDoc = new StringBuilder();
				for (Map<String, Object> docNotif : listDocNotif) {
					if (ValidaConstantes.COD_FORMA_NOTIF_POR_SOL.equals(docNotif.get("cod_tip_doc"))) {
						docNotSineBean.setCodTipCons(docNotif.get("cod_tip_doc").toString());
						docNotSineBean.setFecCons(docNotif.get("fec_regis").toString());
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						String strFecEmiDoc = sdf.format(Utils.stringToDate(Utils.toStr(docNotif.get("fec_regis")).substring(0, 19),CatalogoConstantes.INT_FIVE));
						docNotSineBean.setFecCompCons(strFecEmiDoc);
						sbDesDoc = new StringBuilder();
						sbDesDoc.append(docNotif.get("num_doc").toString().trim()).append("_");
						sbDesDoc.append(docNotif.get("cod_tip_doc").toString().trim()).append("_");
						sbDesDoc.append(docNotif.get("num_ruc").toString().trim()).append(".pdf");
						docNotSineBean.setDesCons(sbDesDoc.toString());
						docNotSineBean.setCntCons(docNotif.get("cnt_archivo").toString());
						docNotSineBean.setIdEcmCons(docNotif.get("num_id_ecm").toString());
					} else {
						docNotSineBean.setNumRuc(docNotif.get("num_ruc").toString());
						docNotSineBean.setCodTipDoc(docNotif.get("cod_tip_doc").toString());
						docNotSineBean.setNumDoc(docNotif.get("num_doc").toString().trim());
						docNotSineBean.setFecDoc(docNotif.get("fec_regis").toString());
						docNotSineBean.setFecCompDoc(docNotif.get("fec_regis").toString());
						docNotSineBean.setFecNot(docNotif.get("fec_not").toString());
						sbDesDoc = new StringBuilder();
						sbDesDoc.append(docNotif.get("num_doc").toString().trim()).append("_");
						sbDesDoc.append(docNotif.get("cod_tip_doc").toString().trim()).append("_");
						sbDesDoc.append(docNotif.get("num_ruc").toString().trim()).append(".pdf");
						docNotSineBean.setDesDoc(sbDesDoc.toString());
						docNotSineBean.setCntDoc(docNotif.get("cnt_archivo").toString());
						docNotSineBean.setIdEcmDoc(docNotif.get("num_id_ecm").toString());
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.obtenerDocNotPorSINE");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.obtenerDocNotPorSINE");
		}
		return docNotSineBean;
	}
	//Fin - [oachahuic][PAS20181U210400241]
	
	
	//[PAS20191U210500144] Inicio 
	@Override
	public Map<String, Object> validarDocSIGAD(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteServiceImpl.validarDocSIGAD");
		Map<String, Object> mapDatosDoc = new HashMap<String, Object>();
		Map<String, Object > mapParam = new HashMap<String, Object>();
		
		String strNumDoc = parametros.get("num_doc").toString().trim();
		if (strNumDoc.length() == 17) {
			try {
				Integer.parseInt(strNumDoc.substring(0, 7));
				mapParam = new HashMap<String, Object>();
				mapParam.put("tipodoc_par", parametros.get("tipDocSigad").toString().trim());
				mapParam.put("numedoc_par", Integer.parseInt(strNumDoc.substring(0, 7)));
				mapParam.put("annodoc_par", strNumDoc.substring(7, 11));
				mapParam.put("areadoc_par", strNumDoc.substring(11, 17));
				DataSourceContextHolder.setKeyDataSource("dcprad1");
				Map<String, Object> mapDocInte = null;
				if (ValidaConstantes.COD_DOC_SIGAD_CARTA_CIRCULAR.equals(parametros.get("tipDocSigad").toString().trim())){
					List<Map<String, Object>> listDocInte = docInteDAO.listarDocCircular(mapParam);
					mapDocInte = listDocInte.get(0);
				}	
				else {
					mapDocInte = docInteDAO.getByPk(mapParam);
				}
				log.debug("mapDocInte: " + mapDocInte);
				if (mapDocInte != null) {
					//Valido si RUC pertenece a documento 
					String numRuc = parametros.get("num_ruc").toString().trim();
					if (numRuc.equals(mapDocInte.get("NDOCID").toString().trim())) {
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
						Date fecEmision = dateFormat1.parse(mapDocInte.get("FECHDOC").toString());
						SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						Date fecNotificacion = dateFormat1.parse("00010101");
						mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_UNO);
						//mapDatosDoc.put("tipDoc", parametros.get("cod_tip_doc").toString());
						//mapDatosDoc.put("numeroDoc", strNumDoc);
						mapDatosDoc.put("fec_emi_doc", dateFormat2.format(fecEmision));
						mapDatosDoc.put("fec_not_doc", dateFormat2.format(fecNotificacion));
						
					} else {
						mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
						mapDatosDoc.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_47.
														replace("{num_doc}", parametros.get("num_doc").toString()).
														replace("{num_ruc}", parametros.get("num_ruc").toString()) );
					}
				}
				else {
					mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
					mapDatosDoc.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_43.replace("{num_doc}", parametros.get("num_doc").toString()));
				}
			} catch(NumberFormatException e) {
				mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
				mapDatosDoc.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_43.replace("{num_doc}", parametros.get("num_doc").toString()));
			} catch (ParseException e) {
				mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
				mapDatosDoc.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_43.replace("{num_doc}", parametros.get("num_doc").toString()));
			} 
			
		} else {
			//Formato incorrecto
			mapDatosDoc.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
			mapDatosDoc.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_46);
			
		}
		return mapDatosDoc;
	}
	//[PAS20191U210500144] Fin 
	
	/* Inicio - Seteo de Spring */
	
	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}
	
	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}

	public void setT6622SeguimDAO(T6622SeguimDAO t6622SeguimDAO) {
		this.t6622SeguimDAO = t6622SeguimDAO;
	}

	//INCIO [ATORRESCH 2017-03-03]
	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}
	//FIN   [ATORRESCH 2017-03-03]
	/* Final - Seteo de Spring */

	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setT6303DetPedidoDAO(T6303DetPedidoDAO t6303DetPedidoDAO) {
		this.t6303DetPedidoDAO = t6303DetPedidoDAO;
	}
	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}
	//[PAS20191U210500144] Inicio 
	public void setDocInteDAO(DocInteDAO docInteDAO) {
		this.docInteDAO = docInteDAO;
	}
	
	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	
	//[PAS20191U210500144] Fin
	
}