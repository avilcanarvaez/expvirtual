package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.net.URL;
import java.nio.charset.CodingErrorAction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import pe.gob.sunat.framework.spring.util.buzon.bean.BeMensajeAppBean;
import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DetReqOrdFisBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReqOrdFisBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.RequerimientoDocData;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DetReqOrdFisDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ReqOrdFisDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10372DetRequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class RequerimientoServiceImpl implements RequerimientoService {
	private static final Log log = LogFactory.getLog(RequerimientoServiceImpl.class);
	
	private ResponsableService respService;
	private CorreosService correosService;
	private SeguimientoService seguiService;
	private T6620RequerimDAO t6620RequerimDAO;
	private ReqOrdFisDAO reqOrdFisDAO;
	private DetReqOrdFisDAO detReqOrdFisDAO;
	private T10372DetRequerimDAO t10372DetRequerimDAO;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private SequenceDAO sequenceDAO;
	private GeneralService generalService;
	private PersonaService personaService;
	private ExpedienteVirtualService expedienteVirtualService;
	private CatalogoService catalogoService;
	// Inicio [nchavez 27/05/2016]
	private String generaReporteURLService;
	// Fin [nchavez 27/05/2016]
	
	// Inicio [jjurado 27/05/2016]
	private ValidarParametrosService validarParametrosService;
	// Fin [jjurado 27/05/2016]
	
	// Inicio [avilcan]
	private T01ParamDAO t01ParamDAO;
	//public T01ParamDAO t01ParamDAO;
	// Fin [avilcan]
	@Override
	public String registrarRequerimiento(Map<String, Object> parametros) throws Exception {
		
		Map<String, Object> beanReq = new HashMap<String, Object>();
		Map<String, Object> beanDocExpVirt = new HashMap<String, Object>();
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		Map<String, Object> beanSegui = new HashMap<String, Object>();
		List<Map<String, Object>> listaDocumentosReq = new ArrayList<Map<String,Object>>();
		Map<String, Object>	response = new HashMap<String, Object>();
		JsonSerializer serializer = new JsonSerializer();
		String resp = ValidaConstantes.CADENA_VACIA;
		Map<String, Object> mapDataSource = null;
		//DataSource origenDatos = null;
		Long numSeqReq = null;
		List<T6621RespExpVirtBean> listaRespAsignados = null;
		Map<String, Object>	mapConsultaCorreo = null;
		Map<String, Object>	mapEnvioCorreo = null;
		String mensaje = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.registrarRequerimiento");
			
			// Data Source
			mapDataSource = new HashMap<String, Object>();
			mapDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			//origenDatos = generalService.obtenerOrigenDatos(mapDataSource);
			mapaPool = generalService.obtenerOrigenDatosMap(mapDataSource);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			
			numSeqReq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REQUERIM);
			
			int num_requerim = Integer.valueOf(numSeqReq.toString());
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);
			
			Date fecVacia = null;
			
			fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);
			
			beanReq.put("num_requerim",	num_requerim);
			beanReq.put("num_reqorig",	"010101");
			beanReq.put("num_expedv",	parametros.get("num_expedv"));
			beanReq.put("cod_estreq",	"ABIERTO");
			beanReq.put("fec_requerim", parametros.get("fec_requerim"));
			beanReq.put("fec_venc", 	parametros.get("fec_venc"));
			beanReq.put("cod_orireq",	"AUTOMATICO");
			beanReq.put("fec_regis", 	parametros.get("fec_reg"));
			beanReq.put("cod_usuregis", parametros.get("cod_usureg"));
			beanReq.put("fec_mod", 		fecVacia);
			beanReq.put("cod_usumod", 	ValidaConstantes.SEPARADOR_GUION);
			//beanReq.put("origenDatos", 	origenDatos);
			
			beanDocExpVirt.put("des_motsoldoc", parametros.get("des_motsoldoc"));
			beanDocExpVirt.put("fec_venc ", parametros.get("fec_venc"));
			
			listaDocumentosReq.add(beanDocExpVirt);
			
			beanReq.put("listaDocumentosReq", listaDocumentosReq);
			
			t6620RequerimDAO.insertar(beanReq);
			
			mapRequest.put("num_expedo", 		parametros.get("num_expedo"));
			mapRequest.put("cod_tipdoc", 		parametros.get("cod_tipdoc"));
			mapRequest.put("des_motsoldoc", 	parametros.get("des_motsoldoc"));
			mapRequest.put("fec_venc", 			parametros.get("fec_venc"));
			mapRequest.put("cod_usuinvserv", 	parametros.get("cod_usuinvserv"));
			mapRequest.put("fec_reg", 			parametros.get("fec_reg"));
			
			beanSegui.put("num_expvirt", 		parametros.get("num_expvirt"));
			beanSegui.put("cod_tipseguim", 		"SEGUIMIENTO_WS");
			beanSegui.put("fec_seguim", 		parametros.get("fec_seguim"));
			beanSegui.put("cod_servinv", 		"WS_GENERAR_REQUERIMIENTO");
			beanSegui.put("cod_retservinv", 	ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_usuinvserv", 	parametros.get("cod_usuinvserv"));
			beanSegui.put("fec_invserv", 		parametros.get("fec_invserv"));
			beanSegui.put("des_request", 		(String) serializer.serialize(mapRequest));
			beanSegui.put("des_response", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("num_ruc", 			ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_opccons", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_accion", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_respacc", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("des_datcons", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("fec_cons", 			fecVacia);
			beanSegui.put("cod_tipdoc",			ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("num_doc", 			ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_estexpori", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("cod_etaexpori", 		ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("fec_cambest", 		fecVacia);
			beanSegui.put("fec_cambeta", 		fecVacia);
			
			seguiService.registrarSeguimiento(beanSegui);
			
			response.put("num_expedo", parametros.get("num_expedo"));
			response.put("num_requerim", num_requerim);
			
			
			listaRespAsignados = respService.obtenerResponsablesAsignados(parametros.get("num_expedo").toString());//temporal
			
			if(listaRespAsignados != null && listaRespAsignados.size() > 0){
				listaCorreosBeans = new ArrayList<CorreosBean>();
				
				for(T6621RespExpVirtBean mapRespAsig : listaRespAsignados) {
					
					mapConsultaCorreo = new HashMap<String, Object>();
					correosBean = new CorreosBean();
					
					correosBean.setCodPers(((String)mapRespAsig.getCodColaborador()));
					listaCorreosBeans.add(correosBean);
					
				}
				
				mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
				
				listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
				
				// Enviar correos a los responsables asignados al expediente virtual
				if(listaCorreosBeans != null) {
					
					for(CorreosBean bean : listaCorreosBeans) {
						
						mapEnvioCorreo = new HashMap<String, Object>();
						mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
						
						mensaje = MensajeAlertaConstantes.MSJ_ALERTA_GEN_REQ_EXP_VIRT.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, Integer.toString(num_requerim));
						mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, (String)parametros.get("num_expedo"));
						
						mapEnvioCorreo.put("mensaje", mensaje);
						
						correosService.enviarCorreo(mapEnvioCorreo);
						
					}
					
				}
				
			}
			
			resp = (String) serializer.serialize(response);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.registrarRequerimiento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.registrarRequerimiento");
			
			NDC.pop();
			NDC.remove();
		}
		return resp;
	}
	
	@Override
	public List<T6620RequerimBean> obtenerListaRequerimientos(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();
		List<T6620RequerimBean> listT6620RequerimBeanTemp;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.obtenerListaRequerimientos");
		Map<String, Object> parametros = new HashMap<String, Object>();
		T02PerdpBean responsable;
		String codUsuarioRespReq;
		String codEstadoReq;
		int cont=1;
		int plazoVigencia;
		
		try {
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				if(Utils.isEmpty(listaExpedientesVirtuales)){
					return null;
				}
				
				//TODO - DESCRIPCION TIPO DE CIERRE - REQUERIMIENTO
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_CIERRE_REQUERIMIENTO);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
				Map<String, Object> mapaTipoCierreRequerimiento = catalogoService.obtenerCatalogo(mapa);
				
				
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					
					mapParametrosBusqueda.put("numExpedVirtual", t6614ExpVirtualBean.getNumExpedienteVirtual());
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
					listT6620RequerimBeanTemp = t6620RequerimDAO.listarRequerimientos(mapParametrosBusqueda);
					
					for (T6620RequerimBean t6620RequerimBean : listT6620RequerimBeanTemp) {
						
						codUsuarioRespReq = t6620RequerimBean.getCodUsuRegis();
						parametros.put("codPersona", codUsuarioRespReq);
						
						responsable = personaService.validarPersona(parametros);
						
						if(!Utils.isEmpty(responsable)){
							responsable = personaService.completarInformacionPersona(responsable);
						}
						
						if(!Utils.isEmpty(responsable)){
							t6620RequerimBean.setNomUsuReq(responsable.getDesNombreCompleto());
						}else{//[ATORRESCH 2017-04-24] SI NO ENCUENTRA AL USUARIO LO PONEMOS EN BLANCO
							t6620RequerimBean.setNomUsuReq(codUsuarioRespReq);
						}
						codEstadoReq = t6620RequerimBean.getCodEstadoRequerim();
						if (Utils.equiv(codEstadoReq, ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO)) {
							t6620RequerimBean.setDesEstado(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ABIERTO);
						} else if (Utils.equiv(codEstadoReq, ValidaConstantes.IND_ESTADO_REQUERIMIENTO_CERRADO)) {
							t6620RequerimBean.setDesEstado(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_CERRADO);
						//Inicio - [oachahuic][PAS20175E210400016]
						} else if (Utils.equiv(codEstadoReq, ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO)) {
							t6620RequerimBean.setDesEstado(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ATENDIDO);
						} else if (Utils.equiv(codEstadoReq, ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL)) {
							t6620RequerimBean.setDesEstado(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
						} else if (Utils.equiv(codEstadoReq, ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO)) {
							t6620RequerimBean.setDesEstado(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ELIMINADO);
						//Fin - [oachahuic][PAS20175E210400016]
						} 
						
						//[PAS20191U210500291]: JMC - INI
						t6620RequerimBean.setNumRequerimiento(t6620RequerimBean.getNumRequerimiento().trim()); 
						t6620RequerimBean.setCodEstadoExp(t6614ExpVirtualBean.getCodEstado().trim());
						t6620RequerimBean.setCodTipoExp(t6614ExpVirtualBean.getCodTipoExpediente().trim());
						t6620RequerimBean.setCodTipDoc(t6620RequerimBean.getCodTipDoc().trim());
						t6620RequerimBean.setCodDependencia(t6614ExpVirtualBean.getCodDependencia().trim());
						//[PAS20191U210500291]: JMC - FIN
						
						t6620RequerimBean.setCorrelativo(cont);
						t6620RequerimBean.setFecRequerimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaRequerimiento()));
						t6620RequerimBean.setFecVencimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaVencimiento()));
						t6620RequerimBean.setNumRuc(t6614ExpVirtualBean.getNumRuc());
						
						//Inicio [nchavez 26/05/2016]
						DdpBean beanContribuyente;
						beanContribuyente = validarParametrosService.validarRUC(t6620RequerimBean.getNumRuc());
						t6620RequerimBean.setDesRazonSocial(!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
						//Inicio [nchavez 26/05/2016]
						
						t6620RequerimBean.setNumExpedienteOrigen(t6614ExpVirtualBean.getNumExpedienteOrigen());
						t6620RequerimBean.setDesProceso(t6614ExpVirtualBean.getDesProceso());
						t6620RequerimBean.setDesTipoExpediente(t6614ExpVirtualBean.getDesTipoExpediente());
						t6620RequerimBean.setFecRegistroExpediente(t6614ExpVirtualBean.getFecRegistro());
						
						t6620RequerimBean.setFechaDocumentoOrigen(t6614ExpVirtualBean.getFechaDocumentoOrigen());
						//INICIO[ATORRESCH 20170313]
						String strFecha = new SimpleDateFormat("yyyy-MM-dd").format(t6614ExpVirtualBean.getFecNotifOrigen());
						if (Utils.equiv(strFecha, ValidaConstantes.FECHA_VACIA)) {
							t6620RequerimBean.setStrfecNotifOrigen("");
						} else {
							t6620RequerimBean.setStrfecNotifOrigen(Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecNotifOrigen()));
						}
						//t6620RequerimBean.setStrfecNotifOrigen(t6614ExpVirtualBean.getStrFecNotifOrigen());
						t6620RequerimBean.setDesForNotifOrigen(t6614ExpVirtualBean.getDesForNotifOrigen());						
						
						String strCodTipCierr = t6620RequerimBean.getCodTipCierr().trim();						
						if(Utils.isEmpty(strCodTipCierr) || Utils.equiv(strCodTipCierr, ValidaConstantes.SEPARADOR_GUION)){
							t6620RequerimBean.setDesTipCierr("");
						}else{
							t6620RequerimBean.setDesTipCierr(mapaTipoCierreRequerimiento.get(strCodTipCierr).toString());
						}
						
						//FIN   [ATORRESCH 20170313]
						Date fechaActual = Utils.stringToDate(Utils.dateUtilToStringDDMMYYYY(new Date()),CatalogoConstantes.INT_TWO);
						//[PAS20191U210500291]: JMC - INI
						Date fechaVence = Utils.stringToDate(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaVencimiento()),CatalogoConstantes.INT_TWO);
						plazoVigencia = Utils.obtenerDifDias(fechaVence,fechaActual);
						//[PAS20191U210500291]: JMC - FIN
						
						t6620RequerimBean.setPlazoVigencia(plazoVigencia);
						t6620RequerimBean.setPlazoVigenciaDias(plazoVigencia+CatalogoConstantes.CONSTANTE_DIAS);
						// Inicio [nchavez 27/05/2016]
						t6620RequerimBean.setDesOrigRequerimiento(t6620RequerimBean.getCodOrigRequerimiento()
								.equals(ValidaConstantes.IND_ORIGEN_REQ_MANUAL)?ValidaConstantes.DES_ORIGEN_REQ_DIGITALIZADO:ValidaConstantes.DES_ORIGEN_REQ_AUTOMATICO);
						// Fin [nchavez 27/05/2016]
						cont++;
					}
					
					listT6620RequerimBean.addAll(listT6620RequerimBeanTemp);
				}
				
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.obtenerListaRequerimientos");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.obtenerListaRequerimientos");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6620RequerimBean;
	}
	
	
	//Inicio staype
	@Override
	public String registrarRequerimientoManual(HashMap<String, Object> mapParametros) throws Exception {
		
		T6614ExpVirtualBean t6614ExpVirtualBean;
		T6620RequerimBean t6620RequerimBean = new T6620RequerimBean();
		ReqOrdFisBean reqOrdFisBean = new ReqOrdFisBean();
	    List<DetReqOrdFisBean> listDetReqOrdFisBean;
		Map<String, Object> mapDatos;
		String numRequerimiento; //correlativo requerimiento
		JsonSerializer serializer = null;
		String flagOrigen = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> beanSeguiWS = null;
		Map<String, Object> requeSeguiWS = null;
		String formatoFechaReq = "dd-MM-yyyy";
		Calendar fechaActual = null;
		Calendar fechaVacia = null;
		Map<String, Object>	mapConsultaCorreo = null;
		Map<String, Object>	mapEnvioCorreo = null;
		String mensaje = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		List<String> listaColaboradores = null;
		String indEmiAlerta = null;
		String fecFormat = null;
		Map<String, Object> mapResponse = null;
		// Inicio [nchavez 26/05/2015]
		List<RequerimientoDocData> listadoDocumentos=null;
		// Fin [nchavez 26/05/2015]
		String numeroRuc=null;
		String razonSocial=null;
		String nroExpeVirtual=null;
		// Inicio [jquispe 11/07/2016] Numero del Expediente Origen.
		String nroExpeOrigen = null;
		// Fin [jquispe 11/07/2016]
		// Inicio [nchavez 26/05/2015]
		String requerimientoOrigen=null;
		String fechareq=null;
		String fechavencimiento=null;
		// Fin [nchavez 26/05/2015]
		Map<String, Object>	parametrosBusqueda;
		
		
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.registrarRequerimientoManual");
		
		try {
			
			/**OBTENEMOS LOS PARAMETROS DE ENTRADA**/
			//Bean Requerimiento
			fechaActual = Calendar.getInstance();
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			//Expediente Virtual
			if(!Utils.isEmpty(mapParametros.get("t6614ExpVirtualBean"))){
				t6614ExpVirtualBean = (T6614ExpVirtualBean) mapParametros.get("t6614ExpVirtualBean");
				// Inicio [nchavez 27/05/2016]
				nroExpeVirtual=t6614ExpVirtualBean.getNumExpedienteVirtual();
				// Fin [nchavez 27/05/2016]
				// Inicio [jquispe 11/07/2016]
				nroExpeOrigen = t6614ExpVirtualBean.getNumExpedienteOrigen();
				// Fin [jquispe 11/07/2016]
			}else{
				return "";
			}

			//Inicio staype 26/12/2019 [PAS20191U210500291] consulta a tablas de dependencia
			t6620RequerimBean = (T6620RequerimBean) mapParametros.get("t6620RequerimBean");
			//obtener el dato de numero de requerimiento
			requerimientoOrigen=t6620RequerimBean.getNumRequerimOrigen();
			log.debug("NumReqOrigen"+requerimientoOrigen);
			parametrosBusqueda = new HashMap<String, Object>();
			parametrosBusqueda.put("numRequerimientoOrigen",requerimientoOrigen);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + mapParametros.get("codDepen").toString().trim());
			
			reqOrdFisBean = reqOrdFisDAO.obtenerDatosReq(parametrosBusqueda);
			
			numRequerimiento = Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REQUERIM));
			String correlativoCompletado = String.format("%07d",Utils.toInteger(numRequerimiento));
			numRequerimiento = Utils.toStr(t6614ExpVirtualBean.getCodTipoExpediente()).trim()+correlativoCompletado;
			log.debug("Numero de requerimiento correlativo: " + numRequerimiento);
			
			t6620RequerimBean.setNumRequerimiento(numRequerimiento);
			
			//numero requerimiento origen num requerimiento del negocio
			t6620RequerimBean.setNumRequerimOrigen(t6620RequerimBean.getNumRequerimOrigen());
			log.debug("Numero de expediente virtual"+t6614ExpVirtualBean.getNumExpedienteVirtual());
			t6620RequerimBean.setNumExpedienteVirtual(t6614ExpVirtualBean.getNumExpedienteVirtual());
			log.debug("fecha de requerimiento sacado de dependencia"+reqOrdFisBean.getFecReq());
			t6620RequerimBean.setFechaRequerimiento(reqOrdFisBean.getFecReq());
			t6620RequerimBean.setCodEstadoRequerim(ValidaConstantes.IND_ESTADO_REQ_ABIERTO);
			log.debug("Fecha de vencimiento sacado de dependencia"+reqOrdFisBean.getFecVencReq());
			t6620RequerimBean.setFechaVencimiento(reqOrdFisBean.getFecVencReq());
			t6620RequerimBean.setFechaRegistro(new Date());
			log.debug("Codigo de usuario de registro"+t6614ExpVirtualBean.getCodUsuarioRegistro());
			t6620RequerimBean.setCodUsuRegis(t6614ExpVirtualBean.getCodUsuarioRegistro());
			//Fin staype 26/12/2019 consulta a tablas de dependencia
			t6620RequerimBean.setFechaMod(fechaVacia.getTime());
			//Inicio - [oachahuic][PAS20175E210400016]
			t6620RequerimBean.setCodUsuMod(ValidaConstantes.SEPARADOR_GUION);
			t6620RequerimBean.setDesEliReq(ValidaConstantes.SEPARADOR_GUION);
			t6620RequerimBean.setDesAteReq(ValidaConstantes.SEPARADOR_GUION);
			t6620RequerimBean.setCodTipCierr(ValidaConstantes.SEPARADOR_GUION);
			//CVG inicio [PAS20191U210500291]
			t6620RequerimBean.setCodTipDoc(mapParametros.get("codTipDoc").toString());
			//CVG Fin
			mapDatos = new HashMap<String, Object>();
			mapDatos.put("t6620RequerimBean", t6620RequerimBean);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			//Fin - [oachahuic][PAS20175E210400016]
			t6620RequerimDAO.insertarManual(mapDatos);
			

	        //Inicio staype 26/12/2019 [PAS20191U210500291] consulta a tablas detalle det_req_ordfis de dependencia
			requerimientoOrigen=t6620RequerimBean.getNumRequerimOrigen();
			log.debug("Requerimiento origen"+requerimientoOrigen);
			parametrosBusqueda = new HashMap<String, Object>();
			parametrosBusqueda.put("numRequerimientoOrigen",requerimientoOrigen);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + mapParametros.get("codDepen").toString().trim());
			listDetReqOrdFisBean = detReqOrdFisDAO.obtenerDatosReqDet(parametrosBusqueda);
			//Fin staype 26/12/2019 consulta a tablas detalle det_req_ordfis de dependencia
			
			//Inicio staype 26/12/2019 [PAS20191U210500291] Guardamos detalle de requerimientos
			for (DetReqOrdFisBean detReqOrdFisBean : listDetReqOrdFisBean) {
				T10372DetRequerimBean t10372DetRequerimBean = new T10372DetRequerimBean();
				log.debug("Numero de requerimiento de detalle: "+t6620RequerimBean.getNumRequerimiento());
				t10372DetRequerimBean.setNumReq(t6620RequerimBean.getNumRequerimiento());
				log.debug("Numero de Item de detalle: "+detReqOrdFisBean.getItem());
				t10372DetRequerimBean.setItem(detReqOrdFisBean.getItem());
				log.debug("Numero de subitem de detalle: "+detReqOrdFisBean.getSubitem());
				t10372DetRequerimBean.setSubitem(detReqOrdFisBean.getSubitem());
				log.debug("Texto de item de detalle: "+detReqOrdFisBean.getTextitem());
				t10372DetRequerimBean.setTextitem(detReqOrdFisBean.getTextitem());
				t10372DetRequerimBean.setEstItem(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_NOPRESENTADO);
				t10372DetRequerimBean.setDescElim(ValidaConstantes.SEPARADOR_GUION);
				t10372DetRequerimBean.setNumCorDoc(ValidaConstantes.CARGA_INICIAL_NUM_CORDOC);
				log.debug("Codigo de usuario de detalle: "+t6614ExpVirtualBean.getCodUsuarioRegistro());
				t10372DetRequerimBean.setUsuReg(t6614ExpVirtualBean.getCodUsuarioRegistro());
				t10372DetRequerimBean.setFecReg(new Date());
				t10372DetRequerimBean.setUsuMod(ValidaConstantes.SEPARADOR_GUION);
				log.debug("NUMERO FECHAAAA PARA DETALLE: "+fechaVacia.getTime());
				t10372DetRequerimBean.setFecMod(fechaVacia.getTime());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t10372DetRequerimDAO.insertarManualDetReq(t10372DetRequerimBean);
			}
			//Fin staype 26/12/2019 Guardamos detalle de requerimientos
			
			/*************************************************************************/
			/*************************ENVIO DE CORREO*********************************/
			/*************************************************************************/
			
			
			indEmiAlerta = Utils.toStr(mapParametros.get("indEmiAlerta"));
			
			if(!Utils.isEmpty(indEmiAlerta) && indEmiAlerta.equals(ValidaConstantes.IND_EMI_ALERTA_SI)) {
				listaColaboradores = (ArrayList<String>)mapParametros.get("listaColaboradores");
				
				if(listaColaboradores != null && listaColaboradores.size() > 0) {
					mapConsultaCorreo =  new HashMap<String, Object>();
					listaCorreosBeans = new ArrayList<CorreosBean>();
					
					for(String codColab : listaColaboradores) {
						correosBean = new CorreosBean();
						correosBean.setCodPers(codColab);
						listaCorreosBeans.add(correosBean);
					}
					
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
					
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
					// Enviar correos a los responsables asignados al expediente virtual
					//destinad
					if(listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
						if (log.isDebugEnabled()) log.debug("Inicio Envio de correo - ExpedienteVirtualServiceImpl.registrarRequerimientoManual"); 
						for(CorreosBean bean : listaCorreosBeans) {
							mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
							mensaje = MensajeAlertaConstantes.MSJ_ALERTA_GEN_REQ_EXP_VIRT_WS.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, Utils.toStr(numRequerimiento));
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
							mapEnvioCorreo.put("mensaje", mensaje);
							correosService.enviarCorreo(mapEnvioCorreo);
						}
						
						if (log.isDebugEnabled()) log.debug("Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarRequerimientoManual");
					}
				}
			}	
			
			flagOrigen = Utils.toStr(mapParametros.get("flagOrigen"));
			if(!Utils.isEmpty(flagOrigen) && Utils.equiv(flagOrigen, ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
		    	// Setear el map del request de seguimiento
				mapRequest = new HashMap<String, Object>();
				mapRequest.put("numExpedo", t6614ExpVirtualBean.getNumExpedienteOrigen().toString().trim());
				mapRequest.put("numReqOrig", mapParametros.get("numReqOrig"));
				fecFormat = Utils.convertirDateToString(t6620RequerimBean.getFechaVencimiento(), formatoFechaReq);
				mapRequest.put("fechaVenc ", fecFormat);
				fecFormat = Utils.convertirDateToString((Date)mapParametros.get("fechaReq"), formatoFechaReq);
				mapRequest.put("fechaReq ", fecFormat);
				mapRequest.put("codUsuReg ", Utils.toStr(mapParametros.get("usuarioRegistro")));
				// Inicio [nchavez 26/05/2015]
				listadoDocumentos = (List<RequerimientoDocData>) mapParametros.get("listadoDocumentos");
				// Fin [nchavez 26/05/2015]
				List<Map<String, String>> listaMapTemp = new ArrayList<Map<String, String>>();
				Map<String, String> mapTemp = null;
				serializer = new JsonSerializer();
				
				
				if(listadoDocumentos != null) {
					for(RequerimientoDocData req : listadoDocumentos) {
						mapTemp = new HashMap<String, String>();
						//Inicio nchavez 12/07/2016
						mapTemp.put("codTipDoc", Utils.convertirUnicode(req.getCodTipDoc()));
						mapTemp.put("desMotSolDoc", Utils.convertirUnicode(req.getDesMotSolDoc()));
						//Fin nchavez 12/07/2016
						listaMapTemp.add(mapTemp);
					}
					
					mapRequest.put("listDocumentos", (String) serializer.serialize(listaMapTemp));
				} else {
					mapRequest.put("listDocumentos", "[]");
				}
				
				mapRequest.put("indEmiAlerta", mapParametros.get("indEmiAlerta"));
				
				
				
				// Seguimiento WS
				serializer = new JsonSerializer();
				beanSeguiWS = new HashMap<String, Object>();
				
				beanSeguiWS.put("num_expedv", t6614ExpVirtualBean.getNumExpedienteVirtual().toString().trim());
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_GEN_REQ);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv",  Utils.toStr(mapParametros.get("usuarioRegistro")));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				
				mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
				mapResponse.put("numExpedv", t6614ExpVirtualBean.getNumExpedienteVirtual().toString().trim());
				mapResponse.put("numRequerim", Utils.toStr(numRequerimiento));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				
				// Seguimiento EE
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				
				seguiService.registrarSeguimiento(beanSeguiWS);
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
		    }else{
		    	registrarSeguimientoRegistroRequerimientoDocumento(t6614ExpVirtualBean.getNumExpedienteVirtual().toString().trim());
		    // Fin [jquispe 27/05/2016]
		    }
			// Inicio [nchavez 24/05/2015]
			
			/*************************************************************************/
			/**ENVIAMOS LA CONSTANSIA DE REGISTRO DE REQUERIMIENTO AL CONTRIBUYENDE**/
			/*************************************************************************/
			if (log.isDebugEnabled()) log.debug("====[ENVIO BUZON]====");
			Map<String, Object> mensajeMap = new HashMap<String, Object>();
			
			mensajeMap.put("razsocial", razonSocial);
			mensajeMap.put("ruc", numeroRuc);
			mensajeMap.put("requerimientOrigen", requerimientoOrigen);
			// Inicio [jquispe 11/07/2016] Debe de mostrar el numero del expediente origen.
			//mensajeMap.put("nexpediente", nroExpeVirtual);
			mensajeMap.put("nexpediente", nroExpeOrigen);
			// Fin [jquispe 11/07/2016]
			mensajeMap.put("nrequerimiento", numRequerimiento);
			mensajeMap.put("fechareq", fechareq);
			mensajeMap.put("fechavencimiento",fechavencimiento);
			// Inicio - [oachahuic][PAS20165E210400270]
			mensajeMap.put("desTipProceso", Utils.toStr(mapParametros.get("desTipProceso")));
			mensajeMap.put("desTipExpediente", Utils.toStr(mapParametros.get("desTipExpediente")));
			// Fin - [oachahuic][PAS20165E210400270]
			
			if (log.isDebugEnabled()) log.debug("====[PARAMETROS PARA ENVIO BUZON: " + mensajeMap.toString() + "]====");
			enviarMensajeBuzon(mensajeMap);
			// Fin [jjurado 24/05/2015]
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.registrarRequerimiento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.registrarRequerimiento");
			
			NDC.pop();
			NDC.remove();
		}
		return Utils.toStr(numRequerimiento);
		
	}
	//fin staype
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoRegistroRequerimientoDocumento(String numeroExpedienteVirtual) throws Exception{
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
					
	  beanSegui.put("num_expedv", numeroExpedienteVirtual);
	  beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
	  beanSegui.put("fec_seguim", fechaActual.getTime());
	  beanSegui.put("fec_invserv", fechaVacia.getTime());
	  beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("cod_usuinvserv",  ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_REQUERIMIENTO);
	  beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_REGISTRAR_REQUERIMIENTO);
	  beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_REGISTRAR_REQUERIMIENTO)));
	  beanSegui.put("fec_cons", fechaActual.getTime());
	  beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
	  beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("fec_cambest", fechaVacia.getTime());
	  beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
	  beanSegui.put("fec_cambeta", fechaVacia.getTime());
	  	 		
	  seguiService.registrarSeguimiento(beanSegui);
	}
	// Fin [jquispe 27/05/2016]
	

	//Inicio nchavez [31/05/2016]
	@Override
	public String validarRequerimientoOrigen(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.validarRequerimientoOrigen");
		
		try {
			List<T6620RequerimBean> listaRequerimientos=t6620RequerimDAO.listarRequerimientos(parametros);
			
			if (!Utils.isEmpty(listaRequerimientos)) {
				return listaRequerimientos.get(0).getNumRequerimOrigen();
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.validarRequerimientoOrigen");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.validarRequerimientoOrigen");
			
			NDC.pop();
			NDC.remove();
		}
		return null;
	}
	//Fin nchavez [31/05/2016]
	
	// Inicio [nchavez 26/05/2015]
	@Override
	public List<T6613DocExpVirtBean> listarDocumentosPorRequerim(HashMap<String, Object> mapParametros) throws Exception {
		
		List<T6613DocExpVirtBean> listT6613DocExpVirtBean;
		int contador = 1;
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.ListarDocumentosPorRequerim");
		
		try {
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaTiposDocumentos = catalogoService.obtenerCatalogo(mapa);
			
			listT6613DocExpVirtBean = t6613DocExpVirtDAO.listarDocumentosPorRequerim(mapParametros);
			
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocExpVirtBean) {
				t6613DocExpVirtBean.setStrFechaCarga("");	
				if(!Utils.isEmpty(mapaTiposDocumentos.get(t6613DocExpVirtBean.getCodTipDoc()))){
					t6613DocExpVirtBean.setDesTipdoc(Utils.toStr(mapaTiposDocumentos.get(t6613DocExpVirtBean.getCodTipDoc())));
				}
				if(Utils.equiv(t6613DocExpVirtBean.getEstadoDocumentoReq(), ValidaConstantes.IND_ESTADO_REQUERIMIENTO_SOLICITADO)){
					t6613DocExpVirtBean.setDesEstadoDocumento(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_SOLICITADO);
				}else{
					t6613DocExpVirtBean.setDesEstadoDocumento(ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ENTREGADO);
					t6613DocExpVirtBean.setStrFechaCarga((Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFechaCarga())));
				}
				if(Utils.isEmpty(t6613DocExpVirtBean.getDesMotsoldoc())){
					t6613DocExpVirtBean.setDesMotsoldoc("");
				}
				
				String desTipoDocumentoCompuesto = t6613DocExpVirtBean.getCodTipDoc() + ValidaConstantes.SEPARADOR_ESPACIO + ValidaConstantes.SEPARADOR_GUION + ValidaConstantes.SEPARADOR_ESPACIO + t6613DocExpVirtBean.getDesTipdoc();

				t6613DocExpVirtBean.setDesTipdoc(desTipoDocumentoCompuesto);
				
				t6613DocExpVirtBean.setCorrelativo(contador);
				contador++;
				
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.ListarDocumentosPorRequerim");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.ListarDocumentosPorRequerim");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6613DocExpVirtBean;
		
	}
	// Fin [nchavez 26/05/2015]
	
	@Override
	public List<T6620RequerimBean> listarRequerimientos(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.listarRequerimientos");
		
		List<T6620RequerimBean> listadoRequerimientos = new ArrayList<T6620RequerimBean>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RequerimientoServiceImpl.listarRequerimientos");
			
			listadoRequerimientos = t6620RequerimDAO.listar(parametros);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.listarRequerimientos");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.listarRequerimientos");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listadoRequerimientos;
		
	}
	
	// Inicio [nchavez 24/05/2015] 
	@Override
	public byte[] obtenerPrevisuaizacionRegistroRequerimiento(String parametroJSON) throws Exception {
		
		
		int firmaPdf = 0;
		int modeloPdf = 1000;
		Integer idDocumento = ExportaConstantes.PDF_REQPREVISUALIZACION_CD_FORMATO_09;
		/* Generar el Pdf */
		
		Service servicioAxis = new Service();
		Call generaPDF = (Call) servicioAxis.createCall();
		generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
		
//		generaPDF.setOperationName("genera0"); //Retorna entero
		generaPDF.setOperationName("generar"); //Retorna bytes[]
		generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
		generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
		generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
		generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
		generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
		
		
		generaPDF.setReturnClass(byte[].class);
//		generaPDF.setReturnClass(int.class);
		
		byte[] docConstPresentDocu =null; 
	try {
		 docConstPresentDocu =(byte[]) (generaPDF.invoke(new Object[]{idDocumento,parametroJSON, "pdf", modeloPdf, firmaPdf}));
//		Integer numeroPdf = Utils.toInteger(generaPDF.invoke(new Object[] { idDocumento, parametroJSON, "pdf", modeloPdf, firmaPdf }));
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return docConstPresentDocu;
	}
	// Fin [nchavez 24/05/2015] 
	
	
	// Inicio [nchavez 24/05/2015] 
	public void enviarMensajeBuzon(Map<String, Object> mensajeMap) {
		int numEnv = 0;
		try {
			BeMensajeAppBean beanMsg = getMensajeBean(mensajeMap);
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			Call callEnv = (Call) service.createCall();

			QName qnamer = new QName("java:pe.gob.sunat.framework.util.buzon.bean", "BeMensajeAppBean");
			callEnv.registerTypeMapping(BeMensajeAppBean.class, qnamer, new BeanSerializerFactory(BeMensajeAppBean.class, qnamer), new BeanDeserializerFactory(BeMensajeAppBean.class, qnamer));
			if (log.isDebugEnabled()) log.debug("====[METODO : ENVIARMENSAJEBUZON ==> WS PARA EL ENVIO BUZON: " + ValidaConstantes.URL_WS_ENVIA_BUZON + "]====");
			callEnv.setTargetEndpointAddress(new URL(ValidaConstantes.URL_WS_ENVIA_BUZON));

			callEnv.setOperationName("enviaBuzon");
			callEnv.addParameter("beanApp", qnamer, BeMensajeAppBean.class, ParameterMode.IN);
			callEnv.setReturnClass(Integer.TYPE);

			numEnv = new Integer(callEnv.invoke(new Object[] { beanMsg }).toString()).intValue();
			if (log.isDebugEnabled()) log.debug("====[RETORNO ENVIO BUZON: " + String.valueOf(numEnv) + "]====");
			if (numEnv <= 0) {
				if (log.isDebugEnabled()) log.debug("Se ha producido un error al procesar su transaccion. No se pudo enviar La constancia al Buzon SOL");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) log.debug("Se ha producido un error al procesar su transacci&oacute;n. Por favor intente nuevamente.");
		}
	}
	// Fin [nchavez 24/05/2015] 
	
	// Inicio [nchavez 24/05/2015] 
	private BeMensajeAppBean getMensajeBean(Map<String, Object> mensajeMap) {
		FechaBean fechaHoy = new FechaBean();
		BeMensajeAppBean mensajeBean = new BeMensajeAppBean();
		mensajeBean.setCod_usremisor("SUNAT");
		mensajeBean.setDes_asunto("Registro de Requerimiento para el Expediente Origen N&deg;"+Utils.toStr(mensajeMap.get("nexpediente")));
		mensajeBean.setFec_publica(fechaHoy.getFormatDate("dd/MM/yyyy-HH:mm:ss"));
		mensajeBean.setFec_vigencia("31/12/2099");
		mensajeBean.setInd_texto("1");
		mensajeBean.setCodigoTemplate("3");
		mensajeBean.setInd_tipmsj("1");
		//Inicio nchavez 24/06/2016 
		List<String> listDestApp = new ArrayList<String>();
		listDestApp.add(Utils.toStr(mensajeMap.get("ruc")));
		mensajeBean.setListaDestinatarios(listDestApp);
		//Fin nchavez 24/06/2016 
		List<T6613DocExpVirtBean> listaDocumentos=(List<T6613DocExpVirtBean>)mensajeMap.get("listaDocumentos");
		
		StringBuffer str = new StringBuffer();
		str.append("<!DOCTYPE html>");
		str.append("<html>");
		str.append("<head>");
		str.append("<meta charset='utf-8'/>");
		str.append("</head>");
		str.append("<body>");
		str.append("<div style='max-width: 680px; margin: 0px auto;font-family:Arial,Verdana,Helvetica,sans-serif;font-size:10px;'>");
		str.append("<center style='font-weight: bold;font-size:12px;'>M&Oacute;DULO DE INGRESO VIRTUAL DE DOCUMENTOS DEL EXPEDIENTE VIRTUAL <br/>REGISTRO DE REQUERIMIENTOS</center>");
		str.append("<table>");
		str.append("<tr>");
		str.append("<td colspan='2' style='font-size:10px;'>Se&ntilde;or Contribuyente:</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td style='font-weight: bold;font-size:10px;'>Razón Social: </td>");
		str.append("<td style='font-size:10px;'>&nbsp;"+Utils.toStr(mensajeMap.get("razsocial"))+"</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<table>");
		str.append("<tr>");
		str.append("<td style='font-weight: bold;font-size:10px;'>RUC: </td>");
		str.append("<td style='font-size:10px;'>&nbsp;"+Utils.toStr(mensajeMap.get("ruc"))+"</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<div>");
		str.append("<p>Se ha registrado el Requerimiento "+Utils.toStr(mensajeMap.get("requerimientOrigen"))+" ");
		str.append("en el M&oacute;dulo de Ingreso Virtual de Documentos del Expediente Virtual para su ");
		str.append("atenci&oacute;n mediante el Portal de SUNAT, utilizando su Clave SOL.</p>");
		str.append("</div>");
		str.append("<table style='width: 100%;'>");
		str.append("<tr>");
		str.append("<td style='width: 25%;font-weight: bold;font-size:10px;'>N&deg; DE EXPEDIENTE:</td>");
		str.append("<td style='width: 25%;font-size:10px;'>"+Utils.toStr(mensajeMap.get("nexpediente"))+"</td>");
		str.append("<td style='width: 25%;font-weight: bold;font-size:10px;'>N&deg; REQUERIMIENTO:</td>");
		str.append("<td style='width: 25%;font-size:10px;'>"+Utils.toStr(mensajeMap.get("requerimientOrigen"))+"</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td style='font-weight: bold;font-size:10px;'>PROCESO:</td>");
		str.append("<td style='font-size:10px;'>"+Utils.toStr(mensajeMap.get("desTipProceso"))+"</td>");
		str.append("<td style='font-weight: bold;font-size:10px;'>TIPO DE EXPEDIENTE:</td>");
		str.append("<td style='font-size:10px;'>"+Utils.toStr(mensajeMap.get("desTipExpediente"))+"</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td style='font-weight: bold;font-size:10px;'>FECHA DEL REQUERIMIENTO:</td>");
		str.append("<td style='font-size:10px;'>"+Utils.toStr(mensajeMap.get("fechareq"))+"</td>");
		str.append("<td style='font-weight: bold;font-size:10px;'>FECHA DE VENCIMIENTO:</td>");
		str.append("<td style='font-size:10px;'>"+Utils.toStr(mensajeMap.get("fechavencimiento"))+"</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<br/>");
		str.append("<div style='font-weight: bold;'>DETALLE DEL REQUERIMIENTO</div>");
		str.append("<table style='width: 100%;border-collapse:collapse'>");
		str.append("<thead>");
		str.append("<tr style='background-color: rgb(212, 212, 212);'>");
		//Inicio nchavez 12/07/07
		str.append("<th style='width: 45%; border: 1px solid black;font-size:10px;text-align: center;' align='center'>Tipo de Documento</th>");
		str.append("<th style='width: 45%; border: 1px solid black;font-size:10px;text-align: center;' align='center'>Motivo</th>");
		//Fin nchavez 12/07/07
		str.append("</tr>");
		str.append("</thead>");
		str.append("<tbody>");
		if (!Utils.isEmpty(listaDocumentos)) {
			for (T6613DocExpVirtBean reqDoc : listaDocumentos) {
				str.append("<tr>");
					str.append("<td style='border: 1px solid black;border: 1px solid black;font-size:10px;'>");
					str.append(reqDoc.getDesNombreCompuesto());
					str.append("</td>");
					str.append("<td style='border: 1px solid black;border: 1px solid black;font-size:10px;'>");
					str.append(reqDoc.getDesMotsoldoc());
					str.append("</td>");
				str.append("</tr>");
			}
		}
		str.append("</tbody>");
		str.append("</table>");
		str.append("<div>");
		str.append("<p>Asimismo se le recuerda que mediante:</p>");
		str.append("<ul style='margin-left: -24px;'>");
		str.append("<li>La opci&oacute;n: <b>Consulta de Requerimientos</b> puede revisar los requerimientos que se encuentren habilitados en el Expediente Virtual.</li>");
		str.append("<li>La opci&oacute;n: <b>Atenci&oacute;n de Requerimientos</b> puede subir los documentos requeridos por la SUNAT.</li>");
		str.append("</ul>");
		str.append("</div>");
		str.append("<div style='margin-top: 5%; background-color: rgb(212, 212, 212); font-weight: bold; padding-left: 8px; padding-right: 8px;'>");
		str.append("<div style='width: 50%; display: inline-block; text-align: left;'>SUNAT  A SU SERVICIO</div>");
		str.append("<div style='width: 49%; display: inline-block; text-align: right;'>FECHA: "+Utils.toStr(Utils.dateUtilToStringDDMMYYYY(new Date()))+"</div>");
		str.append("</div>");
		str.append("</div>");
		str.append("</body>");
		str.append("</html>");
		str.append("<BR>");

		mensajeBean.setMsj_mensaje(str.toString());
		if (log.isDebugEnabled()) log.debug("====[CREANDO MENSAJE BUZON]====> " + new JsonSerializer().serialize(mensajeBean).toString());
		return mensajeBean;
	}
	// Fin [nchavez 24/05/2015] 
	
	
	// Inicio [nchavez 25/05/2015]
	@SuppressWarnings("unchecked")
	public List<String> obtenerTipoDocumentosPorRequerimientoValidos(Map<String, Object> parametros){
		List<String> tipoDocumentos=new ArrayList<String>(0);
		try {
			String codTipoExpediente=Utils.toStr(parametros.get("tipoExpe"));
			
			Map<String, Object> parametrosQuery=new HashMap<String, Object>();
			parametrosQuery.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTO_REQUERIMIENTO);
			parametrosQuery.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			//Inicio nchavez [03/06/2016]
			parametrosQuery.put("codTipoExpediente",codTipoExpediente);
			//Fin nchavez [03/06/2016]
			Map<String,Object> tiposDocValidos=catalogoService.obtenerCatalogoConDetalles(parametrosQuery);
			
			if (tiposDocValidos!=null && !tiposDocValidos.isEmpty()) {
				tipoDocumentos=(List<String>)tiposDocValidos.get(codTipoExpediente);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipoDocumentos;
	}
	// Fin [nchavez 25/05/2015]
	
	
	@SuppressWarnings("unchecked")
	public String eliminarRequerimiento(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapResponse = null;		
		T6620RequerimBean t6620RequerimBean = null;
		Map<String, Object>	mapDocumento = null;		
		
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.EliminarRequerimiento");		
		try {			
			t6620RequerimBean = new T6620RequerimBean();
			//parametros.get("numExpedienteVirtual").toString()
			t6620RequerimBean.setNumRequerimiento(parametros.get("numRequerimiento").toString());			
			t6620RequerimBean.setCodEstadoRequerim("05"); //ELIMINADO
			t6620RequerimBean.setDesEliReq(parametros.get("desEliReq").toString());
			t6620RequerimBean.setCodUsuMod(parametros.get("codUsuModif").toString());				
			t6620RequerimBean.setFechaMod(new Date());
			
			mapDocumento = new HashMap<String, Object>();
			mapDocumento.put("t6620RequerimBean", t6620RequerimBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6620RequerimDAO.Eliminar(mapDocumento);
			
			
			T6613DocExpVirtBean t6613DocExpVirtBean = new T6613DocExpVirtBean();
			mapDocumento = new HashMap<String, Object>();
			t6613DocExpVirtBean.setNroRequerim(parametros.get("numRequerimiento").toString());			
			t6613DocExpVirtBean.setCodEstDoc("02");
			t6613DocExpVirtBean.setDesEliDoc(parametros.get("desEliReq").toString());
			t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuModif").toString());				
			t6613DocExpVirtBean.setFecModif(new Date());
			
			mapDocumento = new HashMap<String, Object>();
			mapDocumento.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6613DocExpVirtDAO.Eliminar(mapDocumento);

			//ENVIAR CORREO A RESPONSABLES
			for (CorreosBean bean : (List<CorreosBean>)parametros.get("listCorreos")) {
				Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
				mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
				String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ELI_REQ.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("numRequerimientoOrigen").toString());
				mapEnvioCorreo.put("mensaje", mensaje);
				correosService.enviarCorreo(mapEnvioCorreo);
			}
			
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", true);
			mapResponse.put("message", AvisoConstantes.AVISO_MODULO_02_05_003.replace("{0}", parametros.get("numRequerimientoOrigen").toString()));			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.EliminarRequerimiento");
			log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.EliminarRequerimiento");			
			NDC.pop();
			NDC.remove();
		}		
		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);		
		return jsonResponse;		
	}	
	//inicio staype
	public void setReqOrdFisDAO(ReqOrdFisDAO reqOrdFisDAO) {
		this.reqOrdFisDAO = reqOrdFisDAO;
	}
	
	public void setDetReqOrdFisDAO (DetReqOrdFisDAO  detReqOrdFisDAO) {
		this.detReqOrdFisDAO = detReqOrdFisDAO;
	}
	
	public void setT10372DetRequerimDAO (T10372DetRequerimDAO  t10372DetRequerimDAO) {
		this.t10372DetRequerimDAO = t10372DetRequerimDAO;
	}
	//fin staype
	
	/*Sets*/
	
	public void setRespService(ResponsableService respService) {
		this.respService = respService;
	}
	
	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}

	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setT6620RequerimDAO(T6620RequerimDAO t6620RequerimDAO) {
		this.t6620RequerimDAO = t6620RequerimDAO;
	}
	
	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	// Inicio - [avilcan]
	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}
	// Fin - [avilcan]
	
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}
	



	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setExpedienteVirtualService(
			ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setGeneraReporteURLService(String generaReporteURLService) {
		this.generaReporteURLService = generaReporteURLService;
	}
	
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	



	//Inicio staype 26/12/2019 [PAS20191U210500291] valida existencia de requerimiento
	public T6620RequerimBean obtenerRequerimiento(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.obtenerRequerimiento");
		
		T6620RequerimBean listadoRequerimientos = new T6620RequerimBean();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RequerimientoServiceImpl.obtenerRequerimiento");
			
			listadoRequerimientos = (T6620RequerimBean) t6620RequerimDAO.obtenerRequerimiento(parametros);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.obtenerRequerimiento");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.obtenerRequerimiento");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listadoRequerimientos;
		
	}
	//Fin staype 26/12/2019 valida existencia de requerimiento
	

	//Inicio staype [PAS20191U210500291] 26/12/2019 actualiza fecha de vencimiento
	@Override
	public void actualizar(Map<String, Object> parametros, T6614ExpVirtualBean beanExp, T6620RequerimBean t6620RequerimBean)
			throws Exception {
		// TODO Auto-generated method stub
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
		t6620RequerimDAO.actualizar(parametros);
		beanExp.setCodTipoExpediente(ValidaConstantes.IND_TIP_EXP_730);
		Map<String, Object> paramDescripcion = null;
		// Inicio - [avilcan]
		if (beanExp.getCodTipoExpediente().equals(ValidaConstantes.IND_TIP_EXP_730)  ||  beanExp.getCodTipoExpediente().equals(ValidaConstantes.IND_TIP_EXP_731) ) {
			/*************************************************************************/
			/**ENVIAMOS LA CONSTANSIA DE REGISTRO DE REQUERIMIENTO AL CONTRIBUYENDE**/
			/*************************************************************************/
			String numRequerimiento;
			numRequerimiento = Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REQUERIM));
			String correlativoCompletado = String.format("%07d",Utils.toInteger(numRequerimiento));
			String fechareq=null;
			String fechavencimiento=null;
			numRequerimiento = Utils.toStr(beanExp.getCodTipoExpediente()).trim()+correlativoCompletado;
			if (log.isDebugEnabled()) log.debug("====[ENVIO BUZON]====");
			Map<String, Object> mensajeMap = new HashMap<String, Object>();			
			mensajeMap.put("razsocial", beanExp.getDesRazonSocial());
			mensajeMap.put("ruc", beanExp.getNumRuc() );
			mensajeMap.put("requerimientOrigen",  t6620RequerimBean.getNumRequerimOrigen() );
			mensajeMap.put("nexpediente", beanExp.getNumExpedienteOrigen());
			mensajeMap.put("nrequerimiento",  numRequerimiento);
			mensajeMap.put("fechareq",  fechareq);
			mensajeMap.put("fechavencimiento",   fechavencimiento);
			
			paramDescripcion= new HashMap<String, Object>();
			paramDescripcion.put("parametro", beanExp.getCodProceso());
			String desTipProceso= obtenerDescripcion(CatalogoConstantes.CATA_PROCESOS,paramDescripcion);
			mensajeMap.put("desTipProceso", Utils.toStr(desTipProceso));			
			log.debug("desTipProceso=>"+ desTipProceso);			
			paramDescripcion=null;
			paramDescripcion= new HashMap<String, Object>();
			paramDescripcion.put("parametro", beanExp.getCodTipoExpediente());			
			String desTipExpediente= obtenerDescripcion( CatalogoConstantes.CATA_TIPOS_EXPEDIENTES, paramDescripcion);			
			log.debug("desTipExpediente=>"+ desTipExpediente);			
			
			if (log.isDebugEnabled()) log.debug("====[PARAMETROS PARA ENVIO BUZON: " + mensajeMap.toString() + "]====");
			enviarMensajeBuzon(mensajeMap);
			log.debug("Final - RequerimientoServiceImpl.actualizar");
		}
		// Fin - [avilcan]
		
	}
	//Fin staype 26/12/2019 actualiza fecha de vencimiento

	//Inicio staype 26/12/2019 [PAS20191U210500291] obtiene datos de requerimiento
	@Override
	public T6613DocExpVirtBean obtenerReqExpediente(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.obtenerReqExpediente");
		
		T6613DocExpVirtBean listadoRequerimientos = new T6613DocExpVirtBean();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RequerimientoServiceImpl.obtenerReqExpediente");
			
			listadoRequerimientos = (T6613DocExpVirtBean) t6613DocExpVirtDAO.obtenerReqExpediente(parametros);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.obtenerReqExpediente");
			log.error(ex, ex);
			throw ex;
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.obtenerReqExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return listadoRequerimientos;
	}
	//Fin staype 26/12/2019 obtiene datos de requerimiento
	
	// Inicio - [avilcan]
		public String obtenerDescripcion(String codClase,Map<String, Object> parametros) throws Exception {
			if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.obtenerDescripcion");
			String resultado = null;
			try {
				Map<String, Object> mapParam = new HashMap<String, Object>();
				
				mapParam.put("codClase", codClase);
				mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapParam.put("codParametro", parametros.get("parametro"));
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
				log.debug("codClase=>" + mapParam.get("codClase"));
				log.debug("indTipo=>" + mapParam.get("indTipo"));
				log.debug("codParametro=>" + mapParam.get("codParametro"));
				T01ParamBean paramBean = t01ParamDAO.obtener(mapParam);
				if (paramBean == null) {
					resultado= ValidaConstantes.CADENA_VACIA;
				} else {
					resultado= paramBean.getDesParametro().trim();
				}
				log.debug("resultado: " + resultado);
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - RequerimientoServiceImpl.obtenerDescripcion");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - RequerimientoServiceImpl.obtenerDescripcion");
			}
			return resultado;
		}
		// Fin - [avilcan]
	
}