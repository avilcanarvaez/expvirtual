package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.lowagie.text.pdf.PdfReader;

import pe.gob.sunat.framework.spring.util.buzon.bean.BeMensajeAppBean;
import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T12UOrgaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T12UOrga;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6613DocExpVirt;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpCoaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpFiscaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6621RespExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6622SeguimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6623TipDocExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExcepcionECM;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
//LLRB
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10373DocAdjReqDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10460VincExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10460VincExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.RecAudDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10458VersDocAdjDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10459AccepExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10461SolDesDAO;

public class ExpedienteVirtualServiceImpl implements ExpedienteVirtualService {
	private static final Log log = LogFactory.getLog(ExpedienteVirtualServiceImpl.class);

	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;
	private T6620RequerimDAO t6620RequerimDAO;
	private T6621RespExpVirtDAO t6621RespExpVirtDAO;
	private T6622SeguimDAO t6622SeguimDAO;// Creado 15/05/2017 JEFFIO
	private T6623TipDocExpDAO t6623TipDocExpDAO;// ALEX
	// Inicio LLRB
	private T10373DocAdjReqDAO t10373DocAdjReqDAO;
	private T10458VersDocAdjDAO t10458VersDocAdjDAO;
	private T10459AccepExpVirtDAO t10459AccepExpVirtDAO;
	private T10461SolDesDAO t10461SolDesDAO;
	// Fin LLRB
	private SequenceDAO sequenceDAO;
	private T01ParamDAO t01ParamDAO;
	private T02PerdpDAO t02PerdpDAO;
	private T12UOrgaDAO t12UOrgaDAO;
	private ExpCoaDAO expCoaDAO;

	private GeneralService generalService;
	private SeguimientoService seguiService;
	private CorreosService correosService;
	private CatalogoService catalogoService;
	private ValidarParametrosService validarParametrosService;
	private EcmService ecmService;
	private PersonaService personaService;
	private String generaReporteURLService;
	private String uploadReporteURLService;
	// PAS20191U210500144 Inicio 400101 RF04, RF13 PSALDARRIAGA
	private ExpFiscaDAO expFiscaDAO;
	// PAS20191U210500144 Fin 400101 RF04, RF13 PSALDARRIAGA
	// inicio CVG
	private T10460VincExpVirtDAO t10460VincExpVirtDAO;
	// fin CVG

	@Override
	public T6614ExpVirtualBean obtenerExpedienteVirtual(Map<String, Object> parametros) throws Exception {

		T6614ExpVirtualBean expedienteVirtual = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerExpedienteVirtual");

		try {

			Map<String, Object> mapa = new HashMap<String, Object>();

			String indClaseExpediente = parametros.get("indClaseExpediente").toString();
			String numExpediente = parametros.get("numExpediente").toString();

			if (ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN.equals(indClaseExpediente)) {

				mapa.put("numExpedienteOrigen", numExpediente);

			} else if (ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL.equals(indClaseExpediente)) {

				mapa.put("numExpedienteVirtual", numExpediente);

			}

			expedienteVirtual = t6614ExpVirtualDAO.obtener(mapa);

			// Inicio [ATORRESCH 2017-02-17]
			if (expedienteVirtual != null) {
				// DESCRIPCION ORIGEN EXPEDIENTE
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

				Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(expedienteVirtual.getCodOrigen())));

				// DESCRIPCION ESTADO EXPEDIENTE
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

				Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(expedienteVirtual.getCodEstado())));

				// PAS20201U210500029 - HHC INICIO
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

				expedienteVirtual
						.setDesProceso(Utils.toStr(mapaProcesos.get(expedienteVirtual.getCodProceso())).toUpperCase());
				expedienteVirtual.setDesTipoExpediente(
						Utils.toStr(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente())).toUpperCase());

				DdpBean beanContribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
				expedienteVirtual.setDesRazonSocial(
						!Utils.isEmpty(beanContribuyente) ? beanContribuyente.getDesRazonSocial().trim()
								: ValidaConstantes.CADENA_VACIA);
				// PAS20201U210500029 - HHC FIN

			}
			// Fin [ATORRESCH 2017-02-17]

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerExpedienteVirtual");

			NDC.pop();
			NDC.remove();
		}

		return expedienteVirtual;

	}

	@Override
	public List<T6614ExpVirtualBean> listaExpedientePorRuc(Map<String, Object> mapParametrosBusqueda) throws Exception {

		List<T6614ExpVirtualBean> listT6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listaExpedientePorRuc");

		try {

			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listaExpedientePorRuc(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listaExpedientePorRuc");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listaExpedientePorRuc");

			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}

	@Override
	public String registrarExpedienteVirtual(Map<String, Object> parametros) throws Exception {
		String numExpeDv = null;
		Long numSeq = null;
		Map<String, Object> beanExp = null;
		Map<String, Object> beanResp = null;
		Map<String, Object> beanDocExp = null;
		Map<String, Object> beanEcm = null;
		Integer numResp = null;
		Calendar fechaActual = null;
		Calendar fechaVacia = null;
		String codIdecm = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		JsonSerializer serializer = null;
		String email = null;
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		String codTipExpExpeDv = null;
		int tamSeqExpeDv = 0;
		String numSeqExpeDv = null;
		String formatoFechaReq = "dd-MM-yyyy";
		String fecha = null;
		String flagOrigen = null;
		String nombreArchivo = null;
		String codEstexpori = null;
		String codEtaexpori = null;
		String indEmiAlerta = null;

		Map<String, Object> mapaPool = null; // 13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE
												// (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.registrarExpedienteVirtual");

		try {
			// Fecha actual
			fechaActual = Calendar.getInstance();

			// Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			// Generar el número del expediente virtual
			beanExp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_EXPVIRT);
			numSeqExpeDv = "0000000" + numSeq.toString();
			tamSeqExpeDv = numSeqExpeDv.length();
			numSeqExpeDv = numSeqExpeDv.substring(tamSeqExpeDv - 7, tamSeqExpeDv);

			codTipExpExpeDv = (String) parametros.get("codTipexp");
			numExpeDv = codTipExpExpeDv + fechaActual.get(Calendar.YEAR) + numSeqExpeDv;
			parametros.put("numExpeDv", numExpeDv);
			flagOrigen = (String) parametros.get("flagOrigen");

			beanExp.put("numExpeDv", numExpeDv);
			beanExp.put("numRuc", parametros.get("numRuc"));
			beanExp.put("numExpedo", parametros.get("numExpedo"));
			beanExp.put("codProc", parametros.get("codProc"));
			beanExp.put("codTipexp", parametros.get("codTipexp"));
			beanExp.put("desExpedv", parametros.get("desExpedv"));
			beanExp.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			beanExp.put("codOrigen", parametros.get("codOrigen"));
			beanExp.put("codUsuRegis", parametros.get("codUsuregis"));
			beanExp.put("fecRegis", fechaActual.getTime());
			beanExp.put("codDep", parametros.get("codDep"));
			beanExp.put("codOriCierr", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("codEstCierr", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("desMotCierr", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("desSumilla", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("codUsuCierr", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("fecCierre", fechaVacia.getTime());
			beanExp.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE); // 21-12-15- miguel ochoa
			beanExp.put("numAcu", ValidaConstantes.NUM_EXPEDIENTE_ACUMLADOR); // 21-12-15- miguel ochoa

			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				beanExp.put("codRetserv", AvisoConstantes.COD_HTTP_STATUS_200);
			} else {
				beanExp.put("codRetserv", null);
			}
			beanExp.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE);
			beanExp.put("numAcu", new Integer(0));
			// Inicio - [oachahuic][PAS20175E210400016]
			beanExp.put("desEliExp", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("fecModif", fechaVacia.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6614ExpVirtualDAO.insertar(beanExp);

			beanResp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_RESPONS);
			numResp = numSeq.intValue();
			beanResp.put("numCorrelativo", numResp);
			beanResp.put("numExpedienteVirtual", numExpeDv);
			beanResp.put("codColaborador", parametros.get("codColab"));
			beanResp.put("indResponsable", ValidaConstantes.IND_RESP_PRINCIPAL);
			beanResp.put("codUsuarioRegistro", parametros.get("codUsuregis"));
			beanResp.put("fecRegistro", fechaActual.getTime());
			beanResp.put("codUsuarioModificacion", ValidaConstantes.SEPARADOR_GUION);
			beanResp.put("fecModificacion", fechaVacia.getTime());
			beanResp.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			// Inicio - [oachahuic][PAS20175E210400016]
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6621RespExpVirtDAO.insertar(beanResp);

			codIdecm = parametros.get("codIdecm") != null ? ((String) parametros.get("codIdecm")).trim() : null;
			if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				// Crear el bean para el ecm
				beanEcm = new HashMap<String, Object>();

				nombreArchivo = (String) parametros.get("desArch");
				nombreArchivo = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));

				beanEcm.put("nomArchivoSinExt", nombreArchivo);
				beanEcm.put("numExpeDv", numExpeDv);
				beanEcm.put("numRuc", parametros.get("numRuc"));
				beanEcm.put("numDoc", parametros.get("numDoc"));
				beanEcm.put("fecEmi", parametros.get("fecDoc"));
				beanEcm.put("codTipExpVir", parametros.get("codTipexp"));
				beanEcm.put("codTipDocu", parametros.get("codTipdoc"));
				beanEcm.put("codDep", parametros.get("codDep"));
				beanEcm.put("codDepOri", parametros.get("codDep"));
				beanEcm.put("archLength", parametros.get("archLength"));
				beanEcm.put("archMimeType", parametros.get("archMimeType"));
				beanEcm.put("archFileName", parametros.get("archFileName"));
				beanEcm.put("archContent", parametros.get("binDoc"));
				beanEcm.put("stream", parametros.get("stream"));
				beanEcm.put("metadata", parametros.get("metadata"));
				codIdecm = ecmService.guardarDocumentoECM(beanEcm);
				// codIdecm = "{0039875B-0000-C61B-9424-230B97A98E11}";//DESARROLLO LOCAL
				// QUITAR!!!
				if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
					throw new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
				}
			}

			// Crear el documento
			beanDocExp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			beanDocExp.put("numCordoc", numSeq);
			beanDocExp.put("numExpeDv", numExpeDv);
			beanDocExp.put("codTipdoc", parametros.get("codTipdoc"));
			beanDocExp.put("codTipExp", parametros.get("codTipexp"));
			beanDocExp.put("numRequerim", null);
			beanDocExp.put("codEstDocReq", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("desMotSolDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("numDoc", parametros.get("numDoc"));
			beanDocExp.put("fecDoc", parametros.get("fecDoc"));
			beanDocExp.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			beanDocExp.put("codUsuCarg", parametros.get("codUsuregis"));
			beanDocExp.put("fecCarg", fechaActual.getTime());
			beanDocExp.put("codUsuRegis", parametros.get("codUsuregis"));
			beanDocExp.put("fecRegis", fechaActual.getTime());
			beanDocExp.put("desArch", parametros.get("desArch"));
			beanDocExp.put("codIdecm", codIdecm);
			beanDocExp.put("codOrigDoc", parametros.get("codOrigDoc"));
			beanDocExp.put("indVisible", parametros.get("indVisible"));
			beanDocExp.put("numCordocRel", new Integer(0));
			// Inicio - [oachahuic][PAS20175E210400016]
			beanDocExp.put("fecNotif", fechaVacia.getTime());
			beanDocExp.put("codForNotif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codEstDoc", ValidaConstantes.COD_EST_DOC_REGISTRADO);
			beanDocExp.put("desEliDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("fecModif", fechaVacia.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtDAO.insertar(beanDocExp);

			codEstexpori = (String) parametros.get("codEstexpori");
			if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEstexpori = ValidaConstantes.CADENA_VACIA;
			}

			codEtaexpori = (String) parametros.get("codEtaexpori");
			if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEtaexpori = ValidaConstantes.CADENA_VACIA;
			}

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				// Setear el map del request de seguimiento
				mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("numExpedo", parametros.get("numExpedo"));

				mapRequest.put("codEstExpOri", codEstexpori);
				mapRequest.put("codEtaExpOri", codEtaexpori);
				mapRequest.put("codUsuRegis", parametros.get("codUsuregis"));

				fecha = Utils.convertirDateToString(fechaActual.getTime(), formatoFechaReq);
				mapRequest.put("fecRegis", fecha);

				mapRequest.put("numRuc", parametros.get("numRuc"));
				mapRequest.put("codProc", parametros.get("codProc"));
				mapRequest.put("codTipExp", parametros.get("codTipexp"));
				mapRequest.put("codTipDoc", parametros.get("codTipdoc"));
				mapRequest.put("numDoc", parametros.get("numDoc"));

				fecha = Utils.convertirDateToString((Date) parametros.get("fecDoc"), formatoFechaReq);
				mapRequest.put("fecDoc", fecha);

				mapRequest.put("codDep", parametros.get("codDep"));
				mapRequest.put("metadata", parametros.get("metadata"));
				mapRequest.put("codColab", parametros.get("codColab"));
				mapRequest.put("desExpedv", parametros.get("desExpedv"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));

				mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
				mapResponse.put("numExpedv", numExpeDv);

				// Seguimiento WS
				serializer = new JsonSerializer();
				beanSeguiWS = new HashMap<String, Object>();

				beanSeguiWS.put("num_expedv", numExpeDv);
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_GENERA_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());

				seguiService.registrarSeguimiento(beanSeguiWS);
				// Inicio [jquispe 27/05/2016] Se retiro la llave de cierre para que se
				// ejecutara el bloque de abajo.
				// }
				// Fin [jquispe 27/05/2016]

				// Trazabilidad
				if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS = new HashMap<String, Object>();

					beanSeguiWS.put("num_expedv", numExpeDv);
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual.getTime());
					beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia.getTime());
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);

					beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
					beanSeguiWS.put("num_doc", parametros.get("numDoc"));

					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
					}

					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
					}

					seguiService.registrarSeguimiento(beanSeguiWS);
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			} else {
				registrarSeguimientoRegistroManualExpedienteVirtual(parametros);// [jjurado 03/07/2016] Se levanta la
																				// observación P_SNADE006-5277
			}
			// Fin [jquispe 27/05/2016]
			mapConsultaCorreo = new HashMap<String, Object>();
			mapConsultaCorreo.put("codPers", parametros.get("codColab"));
			email = correosService.obtenerCorreo(mapConsultaCorreo);

			// Enviar correos a los responsables asignados al expediente virtual
			indEmiAlerta = (String) parametros.get("indEmiAlerta");
			if (indEmiAlerta.equals(ValidaConstantes.IND_EMI_ALERTA_SI)) {

				// Inicio [gangles 10/08/2016]
//				String fechaDoc= (String) parametros.get("fecDoc");
				Date fechaDoc = (Date) parametros.get("fecDoc");
				SimpleDateFormat sdf = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
//				Date fecDocumento = sdf.parse(fechaDoc); 
				Date fechaValidacion = sdf.parse(CatalogoConstantes.FECHA_VALIDACION_VIGENCIA_RS);

				if (fechaValidacion.compareTo(fechaDoc) <= 0) {
					// Fin [gangles 10/08/2016]
					mapEnvioCorreo = new HashMap<String, Object>();
					mapEnvioCorreo.put("destinatario", email);
					String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_REG_EXP_VIRT.replace(
							ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("numExpedo").toString().trim());
					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, numExpeDv);
					mapEnvioCorreo.put("mensaje", mensaje);

					if (log.isDebugEnabled())
						log.debug("Inicio Envio de correo - ExpedienteVirtualServiceImpl.registrarExpedienteVirtual");
					correosService.enviarCorreo(mapEnvioCorreo);
				} // Inicio [gangles 10/08/2016] Fin Gangles

			}

			if (log.isDebugEnabled())
				log.debug("Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarExpedienteVirtual");
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.registrarExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.registrarExpedienteVirtual");

			NDC.pop();
			NDC.remove();
		}

		return numExpeDv;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoRegistroManualExpedienteVirtual(Map<String, Object> parametros) throws Exception { // [jjurado
																														// 03/07/2016]
																														// Se
																														// levanta
																														// la
																														// observación
																														// P_SNADE006-5277

		Map<String, Object> beanSegui = new HashMap<String, Object>();

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", Utils.toStr(parametros.get("numExpeDv")));// [jjurado 03/07/2016] Se levanta la
																				// observación P_SNADE006-5277
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_EXPEDIENTE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_REGISTRAR_EXPEDIENTE);
		beanSegui.put("des_datcons",
				Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_REGISTRAR_EXPEDIENTE)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);

		seguiService.registrarSeguimiento(beanSegui);

		// Inicio [jjurado 03/07/2016] Se levanta la observación P_SNADE006-5277
		String codEstexpori = (String) parametros.get("codEstexpori");
		if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
			codEstexpori = ValidaConstantes.CADENA_VACIA;
		}

		String codEtaexpori = (String) parametros.get("codEtaexpori");
		if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
			codEtaexpori = ValidaConstantes.CADENA_VACIA;
		}

		Map<String, Object> beanSeguiEstadoEtapa = null;

		// Trazabilidad
		if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
				|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
			beanSeguiEstadoEtapa = new HashMap<String, Object>();

			beanSeguiEstadoEtapa.put("num_expedv", Utils.toStr(parametros.get("numExpeDv")));
			beanSeguiEstadoEtapa.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
			beanSeguiEstadoEtapa.put("fec_seguim", fechaActual.getTime());
			beanSeguiEstadoEtapa.put("fec_invserv", fechaVacia.getTime());
			beanSeguiEstadoEtapa.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiEstadoEtapa.put("fec_cons", fechaVacia.getTime());
			beanSeguiEstadoEtapa.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);

			beanSeguiEstadoEtapa.put("cod_tipdoc", parametros.get("codTipdoc"));
			beanSeguiEstadoEtapa.put("num_doc", parametros.get("numDoc"));

			if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				beanSeguiEstadoEtapa.put("cod_estexpori", codEstexpori);
				beanSeguiEstadoEtapa.put("fec_cambest", fechaActual.getTime());
			} else {
				beanSeguiEstadoEtapa.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiEstadoEtapa.put("fec_cambest", fechaVacia.getTime());
			}

			if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				beanSeguiEstadoEtapa.put("cod_etaexpori", codEtaexpori);
				beanSeguiEstadoEtapa.put("fec_cambeta", fechaActual.getTime());
			} else {
				beanSeguiEstadoEtapa.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiEstadoEtapa.put("fec_cambeta", fechaVacia.getTime());
			}

			seguiService.registrarSeguimiento(beanSeguiEstadoEtapa);

		}
		// Fin [jjurado 03/07/2016]
	}
	// Fin [jquispe 27/05/2016]

	@SuppressWarnings("unchecked")
	public String adjuntarDocumentosRequerimiento(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Inicio -  ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");

		Map<String, Object> mapDocumentoExpediente;
		Map<String, Object> documentoPresentados;
		Map<String, Object> mapConsultaCorreo;
		Map<String, Object> mapEnvioCorreo;
		Map<String, Object> beanEcm;

		StringBuilder listaDocumentos = new StringBuilder();
		List<Map<String, Object>> listaDocumentosASubir;
		List<CorreosBean> listaCorreosBeans;
		List<T6621RespExpVirtBean> listaRespAsignados;
		List<Map<String, Object>> listaDocumentosEnvio;
		String numExpedienteVirtual;
		String numExpedienteOrigen;
		String usuarioCarga;
		String fechaRequerimiento;
		String mensaje;
		String codTipoExpediente;
		String estadoExpediente;
		String razonSocial;
		String proceso;
		String desTipoExpediente;
		String fechaVencimiento;
		String flagAcceso;
		// Inicio [nchavez 27/05/2016]
		String numRequerimientOrigen;
		String estadoRequerimiento = null;
		// Fin [nchavez 27/05/2016]
		Long numCorrelativo;
		Integer numDocumento;
		JsonSerializer serializer = null;
		T6613DocExpVirtBean t6613DocExpVirtBean;
		CorreosBean correosBean;
		File archivo;
		String mimeType;
		String codIdecm;
		String error;
		String palabrasClave = "";
		String numRequerimiento = "";
		Calendar fechaVacia = null;

		try {
			// Inicio - [oachahuic][PAS20175E210400016]
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			// Fin - [oachahuic][PAS20175E210400016]
			error = "";
			listaDocumentosASubir = (List<Map<String, Object>>) parametros.get("listaDocumentosASubir");
			numExpedienteVirtual = Utils.toStr(parametros.get("numExpedienteVirtual"));
			numExpedienteOrigen = Utils.toStr(parametros.get("numExpedienteOrigen"));
			usuarioCarga = Utils.toStr(parametros.get("codUsuarioCarga"));
			numRequerimiento = Utils.toStr(parametros.get("numRequerimiento"));
			// Inicio [nchavez 27/05/2016]
			numRequerimientOrigen = Utils.toStr(parametros.get("numRequerimientOrigen"));
			// Fin [nchavez 27/05/2016]
			fechaRequerimiento = Utils.toStr(parametros.get("fechaRequerimiento"));
			codTipoExpediente = Utils.toStr(parametros.get("codTipoExpediente"));
			estadoExpediente = Utils.toStr(parametros.get("estadoExpediente"));
			razonSocial = !Utils.isEmpty(Utils.toStr(parametros.get("razonSocial")))
					? Utils.toStr(parametros.get("razonSocial"))
					: ValidaConstantes.CADENA_VACIA;
			// Inicio nchavez 24/06/2016
			proceso = Utils.convertirUnicode(Utils.toStr(parametros.get("proceso")));
			// Fin nchavez 24/06/2016
			desTipoExpediente = Utils.toStr(parametros.get("desTipoExpediente"));
			fechaVencimiento = Utils.toStr(parametros.get("fechaVencimiento"));

			// inicio lestrada 15092016
			flagAcceso = Utils.toStr(parametros.get("flagAcceso"));
			// fin lestrada 15092016
			SimpleDateFormat formateador = new SimpleDateFormat("EEEEEEEEE',' dd 'de' MMMMM 'del' yyyy");
			String fechaFormateada = "";
			// Calendar cal = Calendar.getInstance();
			// cal.setTimeZone(TimeZone.getDefault());

			// Inicio [nchavez 27/05/2016]
			estadoRequerimiento = (!Utils.isEmpty((List<T6613DocExpVirtBean>) parametros.get("listT6613DocExpVirtBean"))
					&& ((List<T6613DocExpVirtBean>) parametros.get("listT6613DocExpVirtBean"))
							.size() == listaDocumentosASubir.size() ? ValidaConstantes.DES_ESTADO_REQUERIMIENTO_CERRADO
									: ValidaConstantes.DES_ESTADO_REQUERIMIENTO_ABIERTO);

			// Fin [nchavez 27/05/2016]

			// Inicio [gangles 10/08/2016] Recomendación 10: Cuerpo: debería visualizarse
			// fecha de vencimiento y debe indicar si se presentó dentro o fuera del plazo.
			SimpleDateFormat sdf = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			Date fecPresRequerimiento = new Date();
			String mdy = sdf.format(fecPresRequerimiento);
			Date fechaValidacionVencimiento = sdf.parse(fechaVencimiento);
			fecPresRequerimiento = sdf.parse(mdy);
			String envioPresentacion = "";
			if (fecPresRequerimiento.compareTo(fechaValidacionVencimiento) <= 0) {
				envioPresentacion = AvisoConstantes.ENVIO_EN_PLAZO;
			} else {
				envioPresentacion = AvisoConstantes.ENVIO_FUERA_DE_PLAZO;
			}
			// Fin [gangles 10/08/2016]

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposDocumentos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			Calendar c = Calendar.getInstance();
			log.debug("current: " + c.getTime());

			TimeZone z = c.getTimeZone();
			int offset = z.getRawOffset();
			if (z.inDaylightTime(new Date())) {
				offset = offset + z.getDSTSavings();
			}
			int offsetHrs = offset / 1000 / 60 / 60;
			int offsetMins = offset / 1000 / 60 % 60;

			log.debug("offset: " + offsetHrs);
			log.debug("offset: " + offsetMins);

			c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
			c.add(Calendar.MINUTE, (-offsetMins));

			Utils.toStr(c.getTime());

			/**************************************************************/
			/** ENVIAMOS LOS ARCHIVOS AL ECM Y ACTUALIZAMOS LOS DOCUMENTOS **/
			/**************************************************************/
			serializer = new JsonSerializer();
			listaDocumentosEnvio = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : listaDocumentosASubir) {

				documentoPresentados = new HashMap<String, Object>();
				String codigoDocumento = Utils.toStr(map.get("CODDOCUMENTO"));
				String nombreArchivo = Utils.convertirUnicode(Utils.toStr(map.get("NOMBREARCHIVO"))); // validar unicode
																										// ie8
				log.info("***********Nombre Archivo (Utils.toStr(map.get(NOMBREARCHIVO))*********************"
						+ nombreArchivo);
				archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO + numRequerimiento + "@@" + codigoDocumento
						+ "@@" + nombreArchivo);
				mimeType = Utils.toStr(map.get("MIMETYPE"));

				FileInputStream fis = new FileInputStream(archivo);

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}

				byte[] data = bos.toByteArray();

				// llenamos el bean ECM
				beanEcm = new HashMap<String, Object>();
				beanEcm.put("nomArchivoSinExt", nombreArchivo.substring(0, nombreArchivo.lastIndexOf(".")));
				beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
				beanEcm.put("numRuc", parametros.get("numRuc"));
				beanEcm.put("numDoc", Utils.toStr(map.get("CODDOCUMENTO")));
				beanEcm.put("fecEmi", new Date());
				beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
				beanEcm.put("codTipDocu", Utils.toStr(map.get("CODTIPDOC")));
				beanEcm.put("archLength", archivo.length());
				beanEcm.put("archMimeType", mimeType);
				beanEcm.put("archFileName", nombreArchivo);
				beanEcm.put("archContent", data);
				palabrasClave = (String) map.get("PALABRASBUSQUEDA");
				palabrasClave = palabrasClave != null ? palabrasClave : "";
				beanEcm.put("metadata", palabrasClave);
				beanEcm.put("codDep", parametros.get("codDep"));
				beanEcm.put("codDepOri", parametros.get("codDep"));
				codIdecm = ecmService.guardarDocumentoECM(beanEcm);

				if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
					// new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
					error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
					return error;
				}

				/*****************************/
				/** ACTUALIZAMOS EL DOCUMENTO **/
				/*****************************/
				t6613DocExpVirtBean = new T6613DocExpVirtBean();
				t6613DocExpVirtBean.setNumCorDoc(new Integer(Utils.toStr(map.get("CODDOCUMENTO"))));
				t6613DocExpVirtBean.setEstadoDocumentoReq(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ENTREGADO);
				t6613DocExpVirtBean.setCodUsucarga(usuarioCarga);
				t6613DocExpVirtBean.setFechaCarga(new Date());
				t6613DocExpVirtBean.setDescripcionArchivo(nombreArchivo);
				t6613DocExpVirtBean.setCodIdentificadorECM(codIdecm);
				t6613DocExpVirtBean.setFecDoc(new Date());
				t6613DocExpVirtBean.setNumDoc(Utils.toStr(map.get("CODDOCUMENTO")));
				t6613DocExpVirtBean.setCodOrigenDocuento((String) parametros.get("codOrigDoc"));
				// Inicio - [oachahuic][PAS20175E210400016]
				t6613DocExpVirtBean.setCodUsuModif(usuarioCarga);
				t6613DocExpVirtBean.setFecModif(new Date());
				// Fin - [oachahuic][PAS20175E210400016]

				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6613DocExpVirtDAO.update(t6613DocExpVirtBean);

				// guardamos la lista de documentos para el envio de mensaje a los responsables
				// Inicio [gangles 15/09/2016]
				String motivo = Utils.isEmpty(Utils.toStr(map.get("MOTIVO"))) ? ValidaConstantes.SEPARADOR_GUION
						: Utils.convertirUnicode(Utils.toStr(map.get("MOTIVO")));
				// Inicio [gangles 15/09/2016]
				listaDocumentos.append("			<tr>");
				listaDocumentos.append("				<td>" + Utils.toStr(map.get("CODTIPDOC")) + " - "
						+ Utils.escapeHTML(Utils.toStr(mapaTiposDocumentos.get(Utils.toStr(map.get("CODTIPDOC")))))
						+ "</td>");
				listaDocumentos.append("				<td>" + Utils.escapeHTML(Utils.toStr(nombreArchivo)) + "</td>");
				listaDocumentos.append("				<td>" + Utils.dateUtilToStringDDMMYYYY(new Date()) + "</td>");
				listaDocumentos.append("				<td>" + Utils.escapeHTML(motivo) + "</td>");
				listaDocumentos.append("			</tr>");

				// generamos la lisda de documentos a llenar en la constancia de envio

				String desTipoDoc = Utils.toStr(map.get("CODTIPDOC")) + " - "
						+ mapaTiposDocumentos.get(Utils.toStr(map.get("CODTIPDOC")));

				documentoPresentados.put("tipoDocumento", desTipoDoc);
				documentoPresentados.put("nombreArchivo", Utils.convertirUnicode(nombreArchivo));
				documentoPresentados.put("fechaPresentacion", Utils.dateUtilToStringDDMMYYYY(new Date()));
				// Inicio [nchavez 24/06/2016]
				documentoPresentados.put("motivo",
						Utils.isEmpty(Utils.toStr(map.get("MOTIVO"))) ? ValidaConstantes.SEPARADOR_GUION
								: Utils.convertirUnicode(Utils.toStr(map.get("MOTIVO"))));
				// Fin [nchavez 24/06/2016]
				listaDocumentosEnvio.add(documentoPresentados);
				archivo.delete();

			}

			/*****************************************************************/
			/** ENVIAMOS UN CORREO A TODOS LOS RESPONSABLES DEL REQUERIMIENTO **/
			/*****************************************************************/

			// i)construimos el Mensaje
			// Inicio [gangles 10/08/2016]-- Recomendación 10: Se agrega Ruc+ Razón Social,
			// Expediente y requerimiento al asunto
			String asunto = CatalogoConstantes.ASUNTO_MENSAJE_04_01 + " - RUC: " + Utils.toStr(parametros.get("numRuc"))
					+ " - RAZÓN SOCIAL: "
					+ (razonSocial != null && !Utils.isEmpty(razonSocial) ? razonSocial.trim()
							: ValidaConstantes.CADENA_VACIA)
					+ " - Expediente: " + numExpedienteOrigen + " - Requerimiento: " + numRequerimientOrigen;
			// Fin [gangles 10/08/2016]
			StringBuilder sbMensaje = new StringBuilder();
			sbMensaje.append("<html lang='en'>");
			sbMensaje.append("<head>");
			sbMensaje.append("<title>Declaraci&oacute;n informativa de Contratos de Arrendamiento</title>");
			sbMensaje.append("</head>");
			sbMensaje.append("<style>");
			sbMensaje.append("body{font-family:'Arial';font-size:10px;}");
			sbMensaje.append("table{font-family:'Arial';font-size:10px;}");
			sbMensaje.append("</style>");
			sbMensaje.append("<body> ");
			sbMensaje.append("<div>");
			sbMensaje.append("	Sr(a)(ita). {1}</br></br>");
			// Inicio [nchavez 28/06/2016]
			sbMensaje.append(
					"	El contribuyente con RUC " + Utils.toStr(parametros.get("numRuc")) + " y RAZ&Oacute;N SOCIAL: "
							+ (!Utils.isEmpty(razonSocial) ? razonSocial : ValidaConstantes.CADENA_VACIA)
							+ ", entreg&oacute; los siguientes documentos:");
			// Fin [nchavez 28/06/2016]
			sbMensaje.append("	<table style='padding: 10px' width='100%'>");
			sbMensaje.append("		<thead>");
			sbMensaje.append("			<tr>");
			sbMensaje.append("				<th width='25%' align='left'><u>Tipo de Documento</u></th>");
			sbMensaje.append("				<th width='25%' align='left'><u>Nombre del Archivo</u></th>");
			sbMensaje.append("				<th width='25%'align='left'><u>Fecha de Presentaci&oacute;n</u></th>");
			// Inicio [gangles 15/09/2016]
			sbMensaje.append("				<th width='25%'align='left'><u>Motivo</u></th>");
			// Inicio [gangles 15/09/2016]
			sbMensaje.append("			</tr>");
			sbMensaje.append("		</thead>");
			sbMensaje.append("		<tbody>");
			sbMensaje.append("			" + listaDocumentos);
			sbMensaje.append("		</tbody>");
			sbMensaje.append("	</table>");
			sbMensaje.append("	</br>");
			// Inicio [nchavez 28/06/2016]
			sbMensaje.append("	N&uacute;mero del Requerimiento : " + numRequerimientOrigen + "</br>");
			sbMensaje.append("	N&uacute;mero del Expediente    : " + numExpedienteOrigen + "</br>");
			sbMensaje.append("	Fecha de Requerimiento          : " + fechaRequerimiento + "</br>");
			// Inicio [gangles 10/08/2016]
			sbMensaje.append("	Fecha de Vencimiento            : " + fechaVencimiento + "</br>");
			// Fin [gangles 10/08/2016]
			sbMensaje.append("	Estado del Requerimiento		: " + estadoRequerimiento.toUpperCase() + "</br>"); // Fin
																													// [nchavez
																													// 28/06/2016]
			// Inicio [gangles 10/08/2016]
			sbMensaje.append(
					"	Requerimiento Presentado 		: " + envioPresentacion.toUpperCase() + "</br></br></br></br>");
			// Fin [gangles 10/08/2016]

			fechaFormateada = formateador.format(new Date());
			fechaFormateada = StringUtils.capitalize(fechaFormateada);
			fechaFormateada = fechaFormateada.substring(0, fechaFormateada.indexOf("de") + 3)
					+ fechaFormateada.substring(fechaFormateada.indexOf("de") + 3, fechaFormateada.indexOf("de") + 4)
							.toUpperCase()
					+ fechaFormateada.substring(fechaFormateada.indexOf("de") + 4, fechaFormateada.length());

			fechaFormateada = Utils.escapeHTML(fechaFormateada);

			sbMensaje.append("	" + fechaFormateada);
			sbMensaje.append("</body>");
			sbMensaje.append("</html>");

			mensaje = sbMensaje.toString();

			// ii) obtenemos los responsables asignados
			if (log.isDebugEnabled())
				log.debug("proceso -  ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");
			listaRespAsignados = (List<T6621RespExpVirtBean>) parametros.get("listaRespAsignados");
			if (!Utils.isEmpty(listaRespAsignados)) {
				mapConsultaCorreo = new HashMap<String, Object>();
				listaCorreosBeans = new ArrayList<CorreosBean>();

				for (T6621RespExpVirtBean t6621RespExpVirtBean : listaRespAsignados) {

					correosBean = new CorreosBean();
					correosBean.setCodPers(t6621RespExpVirtBean.getCodColaborador());
					listaCorreosBeans.add(correosBean);
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);

					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1,
							t6621RespExpVirtBean.getApellidosPaterno() + " "
									+ t6621RespExpVirtBean.getApellidosMaterno() + ", "
									+ t6621RespExpVirtBean.getNombres());

					// iii)Obtenemos la lista de correos del responsable y enviamos el mensaje a
					// cada uno de estos
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
					if (!Utils.isEmpty(listaCorreosBeans)) {
						for (CorreosBean bean : listaCorreosBeans) {

							mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
							mapEnvioCorreo.put("mensaje", mensaje.toString());
							mapEnvioCorreo.put("asunto", asunto);
							mapEnvioCorreo.put("formatoHTML", 1);
							correosService.enviarCorreo(mapEnvioCorreo);
						}
					}
				}
			}

			/***************************************************************/
			/** GENERAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS - PDF **/
			/***************************************************************/
			/*
			 * //verificamos si la constancia esta asociado al tipo de expediente
			 * SimpleDateFormat formatoDelTexto = new
			 * SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO); Date fecVacia =
			 * null; fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);
			 * 
			 * 
			 * List<T6623TipDocExpBean> listT6623TipDocExpBean =
			 * (List<T6623TipDocExpBean>)parametros.get("listT6623TipDocExpBean");
			 * if(Utils.isEmpty(listT6623TipDocExpBean)){
			 * 
			 * 
			 * mapParametros = new HashMap<String, Object>();
			 * 
			 * mapParametros.put("codTipoDocumento",
			 * ValidaConstantes.COD_TIPO_DOCU_CONS_PRESENT_DOCUMENTO);
			 * mapParametros.put("codTipoExpediente", codTipoExpediente);
			 * mapParametros.put("indVisibleContribuyente",
			 * ValidaConstantes.INDICADOR_NO_VISIBILE_CONTRIBUYENTE);
			 * mapParametros.put("codUsuarioRegistro",
			 * ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			 * mapParametros.put("fecRegistro", new Date());
			 * mapParametros.put("codUsuarioModificacion",
			 * ValidaConstantes.COD_USUARIO_VACIO); mapParametros.put("fecModificacion",
			 * fecVacia); mapParametros.put("indEliminado",
			 * ValidaConstantes.IND_REGI_NO_ELIMINADO); mapParametros.put("origenDatos",
			 * origenDatos);
			 * 
			 * t6623TipDocExpDAO.insertar(mapParametros); }
			 */
			numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			String listaDetalle = (String) serializer.serialize(listaDocumentosEnvio);
			String json = "{" + "\"" + "cabecera" + "\":{";
			json += "\"" + "numConstancia" + "\":" + "\"" + numCorrelativo;
			// Inicio [nchavez 27/05/2016]
			json += "\"," + "\"" + "numRequerimiento" + "\":" + "\"" + numRequerimientOrigen;
			// Fin [nchavez 27/05/2016]
			json += "\"," + "\"" + "numExpediente" + "\":" + "\"" + numExpedienteOrigen;
			json += "\"," + "\"" + "estadoExpediente" + "\":" + "\"" + estadoExpediente.toUpperCase();
			json += "\"," + "\"" + "ruc" + "\":" + "\"" + Utils.toStr(parametros.get("numRuc"));
			razonSocial = razonSocial.replace("&AMP;", "&");
			razonSocial = razonSocial.replace("&amp;", "&");
			json += "\"," + "\"" + "desRazonSocial" + "\":" + "\"" + razonSocial.replace("\"", "\\\"");
//					json+="\","+"\""+"proceso"+"\":"+"\""+proceso.toUpperCase();
			json += "\"," + "\"" + "proceso" + "\":" + "\"" + Utils.convertirUnicode(proceso.toUpperCase());
			json += "\"," + "\"" + "desTipoExpediente" + "\":" + "\"" + desTipoExpediente.toUpperCase();
			json += "\"," + "\"" + "fechaRequerimiento" + "\":" + "\"" + fechaRequerimiento;
			json += "\"," + "\"" + "fechaVencimiento" + "\":" + "\"" + fechaVencimiento;
			json += "\"," + "\"" + "envioPresentacion" + "\":" + "\"" + envioPresentacion;
			json += "\"},\"" + "detalle\"" + ":" + listaDetalle;
			json += "}";

			// nchavez 13/06/2016
			int firmaPdf = 1;
			int modeloPdf = 1000;
			Integer idDocumento = ExportaConstantes.PDF_COD_PLANTILLA_04_001;
			/* Generar el Pdf */

			Service servicioAxis = new Service();
			Call generaPDF = (Call) servicioAxis.createCall();

			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));

			generaPDF.setOperationName("genera0");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
			// generaPDF.setReturnClass(byte[].class);
			generaPDF.setReturnClass(int.class);

			// byte[] docConstPresentDocu = (byte[]) (generaPDF.invoke(new
			// Object[]{idDocumento, json, "pdf", modeloPdf, firmaPdf}));
			Integer numeroPdf = Utils
					.toInteger(generaPDF.invoke(new Object[] { idDocumento, json, "pdf", modeloPdf, firmaPdf }));

			byte[] binDoc = ObtenerPDFUnload(numeroPdf);

			// String desArchivoPdf = ExportaConstantes.NOMBRE_PLANTILLA_PDF_002_01 +
			// ".pdf";
			String desArchivoPdf = numeroPdf.toString() + ".pdf";

			/*******************************************************************/
			/** GUARDAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS EN EL ECM **/
			/*******************************************************************/
			beanEcm = new HashMap<String, Object>();

			beanEcm.put("nomArchivoSinExt", numCorrelativo);
			beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
			beanEcm.put("numRuc", parametros.get("numRuc"));
			beanEcm.put("numDoc", numeroPdf);
			beanEcm.put("fecEmi", new Date());
			beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
			beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_CONS_PRESENT_DOCUMENTO);
			beanEcm.put("archLength", binDoc.length);
			beanEcm.put("archMimeType", "application/pdf");
			beanEcm.put("archFileName", desArchivoPdf);
			beanEcm.put("archContent", binDoc);
			beanEcm.put("metadata", palabrasClave);
			codIdecm = ecmService.guardarDocumentoECM(beanEcm);
			if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				// new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
				error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
				return error;
			}

			/********************************************************************************/
			/**
			 * ADJUNTAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS AL EXPEDIENTE VIRTUAL
			 **/
			/*******************************************************************************/
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			numDocumento = Integer.parseInt(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM)));

			t6613DocExpVirtBean.setNumCorDoc(numDocumento);
			t6613DocExpVirtBean.setNumExpedienteVir(numExpedienteVirtual);
			t6613DocExpVirtBean.setCodTipDoc(ValidaConstantes.COD_TIPO_DOCU_CONS_PRESENT_DOCUMENTO);
			t6613DocExpVirtBean.setCodTipExp(codTipoExpediente);
			t6613DocExpVirtBean.setNumRequerimiento(null);
			t6613DocExpVirtBean.setFechaCarga(new Date());
			t6613DocExpVirtBean.setDescripcionArchivo(Utils.toStr(numeroPdf) + ".pdf");
			t6613DocExpVirtBean.setCodIdentificadorECM(codIdecm);
			t6613DocExpVirtBean.setCodUsuarioRegistro(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setFecRegis(new Date());
			t6613DocExpVirtBean.setCodOrigenDocuento((String) parametros.get("codOrigDoc"));
			t6613DocExpVirtBean.setFecDoc(new Date());
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setEstadoDocumentoReq(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setDesMotsoldoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setIndVisDocumento(ValidaConstantes.IND_VISIBLE_DOC);
			t6613DocExpVirtBean.setNumDocRelacionado(ValidaConstantes.NUM_DEFAULT_DOC_RELACIONADO);
			t6613DocExpVirtBean.setNumDoc(Utils.toStr(numeroPdf));
			t6613DocExpVirtBean.setFechaCarga(new Date());
			// Inicio - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtBean.setFecNotif(fechaVacia.getTime());
			t6613DocExpVirtBean.setCodForNotif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_REGISTRADO);
			t6613DocExpVirtBean.setDesEliDoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodUsuModif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setFecModif(new Date());
			// Fin - [oachahuic][PAS20175E210400016]

			mapDocumentoExpediente = new HashMap<String, Object>();
			mapDocumentoExpediente.put("t6613DocExpVirtBean", t6613DocExpVirtBean);

			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6613DocExpVirtDAO.insertarManual(mapDocumentoExpediente);

			/*************************************************************************/
			/** ENVIAMOS LA CONSTANSIA DE PRESENTACION DE DOCUMENTOS AL CONTRIBUYENDE **/
			/*************************************************************************/
			if (log.isDebugEnabled())
				log.debug("====[ENVIO BUZON]====");
			Map<String, Object> mensajeMap = new HashMap<String, Object>();
			mensajeMap.put("num_ruc", parametros.get("numRuc"));
			mensajeMap.put("rsocial", razonSocial);
			mensajeMap.put("id_PDF_Formulario", Utils.toStr(numeroPdf));
			// Inicio nchavez 30/06/2016
			mensajeMap.put("numeroRequerimiento", numRequerimientOrigen);
			// Fin nchavez 30/06/2016
			if (log.isDebugEnabled())
				log.debug("====[PARAMETROS PARA ENVIO BUZON: " + mensajeMap.toString() + "]====");
			enviarMensajeBuzon(mensajeMap);

			if (log.isDebugEnabled())
				log.debug("Fin -  ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");
			error = ValidaConstantes.NO_ERROR;

			/**********************************************/
			/** ACTUALIZAMOS LA CABEZERA DEL REQUERIMIENTO **/
			/**********************************************/

			T6620RequerimBean t6620RequerimBean = new T6620RequerimBean();
			t6620RequerimBean.setNumRequerimiento(Utils.toStr(parametros.get("numRequerimiento")));
			t6620RequerimBean.setCodUsuMod(Utils.toStr(parametros.get("codUsuarioCarga")));
			t6620RequerimBean.setFechaMod(new Date());
			// Inicio - [oachahuic][PAS20175E210400016]
			List<T6613DocExpVirtBean> listT6613DocExpVirtBean = (List<T6613DocExpVirtBean>) parametros
					.get("listT6613DocExpVirtBean");
			if (!Utils.isEmpty(listT6613DocExpVirtBean)) { // se agrego una negacion por criterio deberia entrar a la
															// siguiente condicion
				if (listT6613DocExpVirtBean.size() == listaDocumentosASubir.size()) {
					t6620RequerimBean.setCodEstadoRequerim(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO);
				} else {
					t6620RequerimBean.setCodEstadoRequerim(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
				}
			}
			// Fin - [oachahuic][PAS20175E210400016]
			Map<String, Object> mapRequerimientoExpediente = new HashMap<String, Object>();
			mapRequerimientoExpediente.put("t6620RequerimBean", t6620RequerimBean);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6620RequerimDAO.update(mapRequerimientoExpediente);

			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			// Inicio Luis Estrada

			realizarSeguimientoAdjuntarDocumentoRequerimiento(numExpedienteVirtual, flagAcceso);
			// Fin Luis Estrada
			// Fin [jquispe 27/05/2016]
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");

		} catch (Exception ex) {
			error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento");

		}
		return error;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void realizarSeguimientoAdjuntarDocumentoRequerimiento(String numeroExpedienteVirtual, String flag)
			throws Exception {

		Map<String, Object> beanSegui = new HashMap<String, Object>();
		Map<String, Object> mapParam = null;
		T6614ExpVirtualBean beanExp = null;
		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv",
				numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);

		// lestrada 15092016

		if (Utils.equiv(flag, AvisoConstantes.FLAG_INTRANET)) {
			beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_ATENCION_REQUERIMIENTO);
			beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS);
			beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
			beanSegui.put("des_datcons",
					Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS)));
		} else if (Utils.equiv(flag, AvisoConstantes.FLAG_INTERNET)) {
			mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedv", numeroExpedienteVirtual);
			beanExp = t6614ExpVirtualDAO.listar(mapParam).get(0);
			beanSegui.put("num_ruc", beanExp.getNumRuc());
			beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_ATENCION_REQUERIM);
			beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTERNET_SUBIRDOCUMENTOS);
			beanSegui.put("des_datcons",
					Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTERNET_SUBIRDOCUMENTOS)));
		}
		// fin lestrada 15092016
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

	public byte[] ObtenerPDFUnload(Integer idPDF) {

		String urlUnload = "";
		RestTemplate restTemplate = null;
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		ResponseEntity<byte[]> responseEntity = null;
		urlUnload = uploadReporteURLService + "data0_sis_id=1000&data0_num_id=" + idPDF;

		restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.ALL));
		entity = new HttpEntity<String>(headers);

		responseEntity = restTemplate.exchange(urlUnload, HttpMethod.GET, entity, byte[].class);
		return responseEntity.getBody();
	}

	public void enviarMensajeBuzon(Map<String, Object> mensajeMap) {
		int numEnv = 0;
		try {
			BeMensajeAppBean beanMsg = getMensajeBean(mensajeMap);
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			Call callEnv = (Call) service.createCall();

			QName qnamer = new QName("java:pe.gob.sunat.framework.util.buzon.bean", "BeMensajeAppBean");
			callEnv.registerTypeMapping(BeMensajeAppBean.class, qnamer,
					new BeanSerializerFactory(BeMensajeAppBean.class, qnamer),
					new BeanDeserializerFactory(BeMensajeAppBean.class, qnamer));
			if (log.isDebugEnabled())
				log.debug("====[METODO : ENVIARMENSAJEBUZON ==> WS PARA EL ENVIO BUZON: "
						+ ValidaConstantes.URL_WS_ENVIA_BUZON + "]====");
			callEnv.setTargetEndpointAddress(new URL(ValidaConstantes.URL_WS_ENVIA_BUZON));

			callEnv.setOperationName("enviaBuzon");
			callEnv.addParameter("beanApp", qnamer, BeMensajeAppBean.class, ParameterMode.IN);
			callEnv.setReturnClass(Integer.TYPE);

			numEnv = new Integer(callEnv.invoke(new Object[] { beanMsg }).toString()).intValue();
			if (log.isDebugEnabled())
				log.debug("====[RETORNO ENVIO BUZON: " + String.valueOf(numEnv) + "]====");
			if (numEnv <= 0) {
				if (log.isDebugEnabled())
					log.debug(
							"Se ha producido un error al procesar su transaccion. No se pudo enviar La constancia al Buzon SOL");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("Se ha producido un error al procesar su transacci&oacute;n. Por favor intente nuevamente.");
		}
	}

	private BeMensajeAppBean getMensajeBean(Map<String, Object> mensajeMap) {
		FechaBean fechaHoy = new FechaBean();
		BeMensajeAppBean mensajeBean = new BeMensajeAppBean();
		mensajeBean.setCod_usremisor("SUNAT");
		mensajeBean.setDes_asunto("Constancia de Presentaci&oacute;n de Documentos N&deg; "
				+ Utils.toStr(mensajeMap.get("id_PDF_Formulario")));
		mensajeBean.setFec_publica(fechaHoy.getFormatDate("dd/MM/yyyy-HH:mm:ss"));
		mensajeBean.setFec_vigencia("31/12/2099");
		mensajeBean.setInd_texto("1");
		mensajeBean.setCodigoTemplate("3");
		mensajeBean.setInd_tipmsj("1");

		List<String> listDestApp = new ArrayList<String>();
		listDestApp.add((String) mensajeMap.get("num_ruc"));
		mensajeBean.setListaDestinatarios(listDestApp);

		List<String> listDocApp = new ArrayList<String>();
		listDocApp.add((String) mensajeMap.get("id_PDF_Formulario"));
		mensajeBean.setListaDocumentos(listDocApp);

		StringBuffer str = new StringBuffer();
		str.append("Sr. Contribuyente," + "<BR>");
		str.append("Se emite la constancia de la Presentaci&oacute;n de documentos a trav&eacute;s del M&oacute;dulo de"
				+ "<BR>");
		str.append("Ingreso Virtual de Documentos del Expediente Virtual para la atenci&oacute;n del" + "<BR>");
		str.append("Requerimiento N&deg; " + Utils.toStr(mensajeMap.get("numeroRequerimiento"))
				+ " realizado por la SUNAT." + "<BR>");

		str.append("<BR>");
		str.append("La presente Constancia s&oacute;lo acredita los documentos presentados pero no la" + "<BR>");
		str.append("conformidad de los mismos lo cual ser&aacute; validado por el responsable del proceso." + "<BR>");

		mensajeBean.setMsj_mensaje(str.toString());
		if (log.isDebugEnabled())
			log.debug("====[CREANDO MENSAJE BUZON]====> " + new JsonSerializer().serialize(mensajeBean).toString());
		return mensajeBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> registrarDocumentosExpediente(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.registrarDocumentosExpediente");

		Map<String, Object> beanEcm = null;
		Calendar fechaActual = null;
		Map<String, Object> beanDocExp = null;
		Long numSeq = null;
		Long numCordoc = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		// inicio CVG
		List<T10373DocAdjReqBean> listT10373DocAdjReqBean = (List<T10373DocAdjReqBean>) parametros
				.get("listT10373DocAdjReqBean");
		// fin CVG
		Calendar fechaVacia = null;
		String codIdecm = null;
		List<String> listaColaboradores = null;
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		String mensaje = null;
		String formatoFechaReq = "dd-MM-yyyy";
		String fecha = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		String descTipoDoc = null;
		Map<String, Object> mapT01Param = null;
		String flagOrigen = null;
		String nombreArchivo = null;
		String codEstexpori = null;
		String codEtaexpori = null;
		Map<String, Object> mapParamActualizaAcum = null;
		String indEmiAlerta = null;

		try {
			fechaActual = Calendar.getInstance();
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			// Crear el documento
			beanDocExp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			numCordoc = numSeq;
			beanDocExp.put("numCordoc", numCordoc);
			beanDocExp.put("numExpeDv", parametros.get("numExpeDv"));
			beanDocExp.put("codTipdoc", parametros.get("codTipdoc"));
			beanDocExp.put("codTipExp", parametros.get("codTipexp"));
			beanDocExp.put("numRequerim", null);
			beanDocExp.put("codEstDocReq", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("desMotSolDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("numDoc", parametros.get("numDoc"));
			beanDocExp.put("fecDoc", parametros.get("fecDoc"));
			beanDocExp.put("codTipDocSust", parametros.get("codTipDocSust"));
			beanDocExp.put("codUsuCarg", parametros.get("codUsuregis"));
			beanDocExp.put("fecCarg", fechaActual.getTime());
			beanDocExp.put("codUsuRegis", parametros.get("codUsuregis"));
			beanDocExp.put("fecRegis", fechaActual.getTime());
			beanDocExp.put("indVisible", parametros.get("indVisible"));
			beanDocExp.put("codOrigDoc", parametros.get("codOrigDoc"));
			beanDocExp.put("numCordocRel", parametros.get("numCordocRel"));
			// PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
			if (parametros.get("indReserTrib") == null) {
				beanDocExp.put("indReserTrib", ValidaConstantes.IND_SIN_RESERVA_TRIBUTARIA);
			} else {
				beanDocExp.put("indReserTrib", parametros.get("indReserTrib"));
			}
			// PAS20191U210500144 Fin 400101 RF06 PSALDARRIAGA

			if (parametros.get("binDoc") != null || parametros.get("stream") != null) {// ALMACENAR EL DOCUMENTO EN EL
																						// ECM
				nombreArchivo = (String) parametros.get("desArch");
				nombreArchivo = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
				beanEcm = new HashMap<String, Object>();
				beanEcm.put("nomArchivoSinExt", nombreArchivo);
				beanEcm.put("numExpeDv", parametros.get("numExpeDv"));
				beanEcm.put("numRuc", parametros.get("numRuc"));
				beanEcm.put("numDoc", parametros.get("numDoc"));
				// INICIO LLRB
				log.debug("FechaDocExp:" + parametros.get("fecDoc"));
				log.debug("FechaEmiExp:" + parametros.get("fecEmi"));
				if (parametros.get("fecDoc") == null) {
					beanEcm.put("fecEmi", parametros.get("fecEmi"));
				} else {
					beanEcm.put("fecEmi", parametros.get("fecDoc"));
				}
				// FIN LLRB
				beanEcm.put("fecEmi", parametros.get("fecDoc"));
				beanEcm.put("codTipExpVir", parametros.get("codTipexp"));
				beanEcm.put("codTipDocu", parametros.get("codTipdoc"));
				beanEcm.put("archLength", parametros.get("archLength"));
				beanEcm.put("archMimeType", parametros.get("archMimeType"));
				beanEcm.put("archFileName", parametros.get("archFileName"));
				beanEcm.put("archContent", parametros.get("binDoc"));
				beanEcm.put("metadata", parametros.get("metadata"));
				beanEcm.put("stream", parametros.get("stream"));
				beanEcm.put("codDep", parametros.get("codDep"));
				codIdecm = ecmService.guardarDocumentoECM(beanEcm);
				// codIdecm = "{0039875B-0000-C61B-9424-230B97A98E11}";//DESARROLLO LOCAL
				// QUITAR!!!
				if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
					new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
				}
				beanDocExp.put("desArch", parametros.get("desArch"));
				beanDocExp.put("codIdecm", codIdecm);
			} else {
				if (parametros.get("codIdecm") != null
						&& !((String) parametros.get("codIdecm")).equals(ValidaConstantes.CADENA_VACIA)) {
					beanDocExp.put("desArch", parametros.get("desArch"));
					// Inicio CVG
					if (!Utils.isEmpty(listT10373DocAdjReqBean)) {
						codIdecm = "0";
					} else {
						codIdecm = (String) parametros.get("codIdecm");
					}
					// Fin CVG
					beanDocExp.put("codIdecm", codIdecm);
				}
			}

			beanDocExp.put("codOrigDoc", parametros.get("codOrigDoc"));
			// Inicio - [oachahuic][PAS20175E210400016]
			beanDocExp.put("fecNotif", fechaVacia.getTime());
			beanDocExp.put("codForNotif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codEstDoc", ValidaConstantes.COD_EST_DOC_REGISTRADO);
			beanDocExp.put("desEliDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("fecModif", fechaVacia.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtDAO.insertar(beanDocExp);

			// inicio CVG
			if (!Utils.isEmpty(listT10373DocAdjReqBean)) {
				/** Guardamos listado Documentos **/
				for (T10373DocAdjReqBean t10373Doc : listT10373DocAdjReqBean) {

					T10373DocAdjReqBean t10373DocAdjReqBean = new T10373DocAdjReqBean();

					t10373DocAdjReqBean.setNumArchAdj(0);
					t10373DocAdjReqBean.setNumRequerimiento("0");
					t10373DocAdjReqBean.setNumItem(0);
					t10373DocAdjReqBean.setNumSubItem(0);
					t10373DocAdjReqBean.setNumArcItem(0);
					t10373DocAdjReqBean.setNomArchAdj(t10373Doc.getNomArchAdj());
					t10373DocAdjReqBean.setNumArchivo(t10373Doc.getNumArchivo());
					t10373DocAdjReqBean.setCntTamArch(t10373Doc.getCntTamArch());
					t10373DocAdjReqBean.setNumFolios(t10373Doc.getNumFolios());
					t10373DocAdjReqBean.setCodTipAlm(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_SOLICITADO);
					t10373DocAdjReqBean.setCodIdECM(t10373Doc.getCodIdECM());
					t10373DocAdjReqBean.setNumCorDoc(Utils.toInteger(numCordoc));
					t10373DocAdjReqBean.setCodOriDoc(t10373Doc.getCodOriDoc());
					t10373DocAdjReqBean.setCodUsuRegis(t10373Doc.getCodUsuRegis());
					t10373DocAdjReqBean.setFechaRegistro(new Date());
					t10373DocAdjReqBean.setCodUsuMod(ValidaConstantes.SEPARADOR_GUION);
					t10373DocAdjReqBean.setFechaMod(fechaVacia.getTime());

					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
					t10373DocAdjReqDAO.insertar(t10373DocAdjReqBean);
				}
			}
			// fin CVG

			// Inicio - [oachahuic][PAS20175E210400016]
			if (parametros.get("actDocRel") != null) {
				T6613DocExpVirtBean t6613Bean = new T6613DocExpVirtBean();
				t6613Bean.setNumCorDoc((Integer) parametros.get("numCordocRel"));
				if (parametros.get("indVisDocRel") != null) {
					t6613Bean.setIndVisDocumento((String) parametros.get("indVisDocRel"));
				}
				if (parametros.get("fecNotDocRel") != null) {
					t6613Bean.setFecNotif((Date) parametros.get("fecNotDocRel"));
					t6613Bean.setCodForNotif((String) parametros.get("forNotDocRel"));
				}
				t6613Bean.setCodUsuModif((String) parametros.get("codUsuregis"));
				t6613Bean.setFecModif(fechaActual.getTime());

				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6613DocExpVirtDAO.update(t6613Bean);
			}
			// Fin - [oachahuic][PAS20175E210400016]

			if (parametros.get("listExpAcum") != null) {
				List<Map<String, String>> listExpAcum = (List<Map<String, String>>) parametros.get("listExpAcum");
				for (Map<String, String> mapExp : listExpAcum) {
					mapParamActualizaAcum = new HashMap<String, Object>();
					mapParamActualizaAcum.put("numExpedienteVirtual", mapExp.get("numExpVir"));
					mapParamActualizaAcum.put("indAcu", mapExp.get("indAcu"));
					mapParamActualizaAcum.put("numAcu", mapExp.get("numAcu"));
					mapParamActualizaAcum.put("codUsuModif", parametros.get("codUsuregis").toString());
					mapParamActualizaAcum.put("fecModif", fechaActual.getTime());
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
					t6614ExpVirtualDAO.actualizar(mapParamActualizaAcum);
				}
			}

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", parametros.get("numExpeDv"));
			mapResponse.put("codIdecm", codIdecm);

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				JsonSerializer serializer = new JsonSerializer();

				mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("numExpedo", parametros.get("numExpedo"));

				codEstexpori = (String) parametros.get("codEstexpori");
				if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEstexpori = ValidaConstantes.CADENA_VACIA;
				}
				codEtaexpori = (String) parametros.get("codEtaexpori");
				if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEtaexpori = ValidaConstantes.CADENA_VACIA;
				}
				mapRequest.put("codEstexpori", codEstexpori);
				mapRequest.put("codEtaexpori", codEtaexpori);
				mapRequest.put("codTipexp", parametros.get("codTipexp"));
				mapRequest.put("codTipdoc", parametros.get("codTipdoc"));
				mapRequest.put("numDoc", parametros.get("numDoc"));
				mapRequest.put("codIdecm", parametros.get("codIdecm"));
				mapRequest.put("fileExtension", parametros.get("fileExtension"));
				mapRequest.put("desArch", parametros.get("desArch"));
				mapRequest.put("codUsuregis", parametros.get("codUsuregis"));
				// INICIO LLRB
				if (parametros.get("fecDoc") == null) {
					fecha = Utils.convertirDateToString((Date) parametros.get("fecEmi"), formatoFechaReq);
				} else {
					fecha = Utils.convertirDateToString((Date) parametros.get("fecDoc"), formatoFechaReq);
				}
				// FIN LLRB
				mapRequest.put("fecDoc", fecha);
				mapRequest.put("metadata", parametros.get("metadata"));
				mapRequest.put("codTipdocRel", parametros.get("codTipdocRelParam"));
				mapRequest.put("numDocRel", parametros.get("numDocRelParam"));
				mapRequest.put("indAcu", parametros.get("indAcu"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));
				// PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
				mapRequest.put("indReserTrib", parametros.get("indReserTrib"));
				// PAS20191U210500144 Fin 400101 RF06 PSALDARRIAGA

				// Seguimiento
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_REC_ANEXDOC_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				seguiService.registrarSeguimiento(beanSeguiWS);

				// Trazabilidad
				if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS = new HashMap<String, Object>();
					beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual.getTime());
					beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia.getTime());
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
					beanSeguiWS.put("num_doc", parametros.get("numDoc"));

					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
					}
					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
					}
					seguiService.registrarSeguimiento(beanSeguiWS);
				}
				// eaguilar 28 JUN: se quita el registro de seguimiento para los acumuladores
				// (WS)
				Map<String, Object> mapSeguiAcum = new HashMap<String, Object>();
				mapSeguiAcum.put("codTipdoc", parametros.get("codTipdoc"));
				mapSeguiAcum.put("numDoc", parametros.get("numDoc"));
				mapSeguiAcum.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
				mapSeguiAcum.put("numAcu", Utils.toStr(parametros.get("numExpedo")).trim());
				mapSeguiAcum.put("codEstexpori", codEstexpori);
				mapSeguiAcum.put("codEtaexpori", codEtaexpori);
				mapSeguiAcum.put("fechaActual", fechaActual.getTime());
				mapSeguiAcum.put("fechaVacia", fechaVacia.getTime());
				mapResponse.put("mapSeguiAcum", mapSeguiAcum);
			} else if (flagOrigen.equals(ValidaConstantes.IND_REG_DOC_INT)) {
				codEstexpori = (String) parametros.get("codEstexpori");
				if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEstexpori = ValidaConstantes.CADENA_VACIA;
				}
				codEtaexpori = (String) parametros.get("codEtaexpori");
				if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEtaexpori = ValidaConstantes.CADENA_VACIA;
				}
				// Inicio [jquispe 27/05/2016] Mapa de descripciones de acciones
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);
				// Fin [jquispe 27/05/2016]

				// Seguimiento
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_DOCUMENTO_INTERNO);
				beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS);
				beanSeguiWS.put("des_datcons",
						Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS)));
				beanSeguiWS.put("fec_cons", fechaActual.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				seguiService.registrarSeguimiento(beanSeguiWS);

				// Trazabilidad
				if ((!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA))
						&& esTipoDocumentoRegistraSeguimiento(parametros)) {// [jquispe 05/07/2016] Verifica si el
																			// documento se puede grabar en la tabla de
																			// seguimiento.

					beanSeguiWS = new HashMap<String, Object>();
					beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual.getTime());
					beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia.getTime());
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
					beanSeguiWS.put("num_doc", parametros.get("numDoc"));

					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
					}
					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
					}
					seguiService.registrarSeguimiento(beanSeguiWS);
				}
				// eaguilar 28 JUN: se quita el registro de seguimiento para los acumuladores
				// (FRONT)
				Map<String, Object> mapSeguiAcum = new HashMap<String, Object>();
				mapSeguiAcum.put("codTipdoc", parametros.get("codTipdoc"));
				mapSeguiAcum.put("numDoc", parametros.get("numDoc"));
				mapSeguiAcum.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
				mapSeguiAcum.put("numAcu", Utils.toStr(parametros.get("numExpedo")).trim());
				mapSeguiAcum.put("codEstexpori", codEstexpori);
				mapSeguiAcum.put("codEtaexpori", codEtaexpori);
				mapSeguiAcum.put("fechaActual", fechaActual.getTime());
				mapSeguiAcum.put("fechaVacia", fechaVacia.getTime());
				mapResponse.put("mapSeguiAcum", mapSeguiAcum);
			}

			indEmiAlerta = (String) parametros.get("indEmiAlerta");
			if (ValidaConstantes.IND_EMI_ALERTA_SI.equals(indEmiAlerta)) {// Enviar correos a los responsables asignados
																			// al expediente virtual
				listaColaboradores = (ArrayList<String>) parametros.get("listaColaboradores");
				if (listaColaboradores != null && listaColaboradores.size() > 0) {
					mapConsultaCorreo = new HashMap<String, Object>();
					listaCorreosBeans = new ArrayList<CorreosBean>();
					for (String codColaborador : listaColaboradores) {
						correosBean = new CorreosBean();
						correosBean.setCodPers(codColaborador);
						listaCorreosBeans.add(correosBean);
					}
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
					if (listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
						mapT01Param = new HashMap<String, Object>();
						mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
						mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
						mapT01Param.put("codParametro", (String) parametros.get("codTipdoc"));
						descTipoDoc = this.obtenerDescParamExpVirt(mapT01Param);
						if (log.isDebugEnabled())
							log.debug(
									"Inicio Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpediente");
						for (CorreosBean bean : listaCorreosBeans) {
							mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());

							descTipoDoc = descTipoDoc != null ? descTipoDoc.trim() : "";
							mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ANX_DOC_EXP_VIRT
									.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, descTipoDoc);
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1,
									(String) parametros.get("numDoc"));
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_2,
									(String) parametros.get("numExpedo"));

							mapEnvioCorreo.put("mensaje", mensaje);
							correosService.enviarCorreo(mapEnvioCorreo);
						}
						if (log.isDebugEnabled())
							log.debug(
									"Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpediente");
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.registrarDocumentosExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.registrarDocumentosExpediente");
		}
		return mapResponse;
	}

	// Inicio [jquispe 05/07/2016] Verifica si el documento se puede grabar en la
	// tabla de seguimiento.
	private boolean esTipoDocumentoRegistraSeguimiento(Map<String, Object> parametros) {

		String codigoTipoExpediente = parametros.get("codTipexp").toString().trim();
		String numeroExpedienteOrigen = parametros.get("numExpedo").toString().trim();
		String numeroDocumento = parametros.get("numDoc").toString().trim();
		String codigoTipoDocumento = parametros.get("codTipdoc").toString().trim();
		String prefijoCodigoTipoDocumento = (!Utils.isEmpty(codigoTipoDocumento) && codigoTipoDocumento.length() >= 3)
				? codigoTipoDocumento.substring(0, 3)
				: codigoTipoDocumento;

		if (!(ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA.equals(codigoTipoExpediente))) {
			return true;
		}
		if (!(ValidaConstantes.PREFIJO_CODIGO_TIPO_NOTIFICACION_SOL.equals(prefijoCodigoTipoDocumento))) {
			return true;
		}
		if (numeroExpedienteOrigen.equals(numeroDocumento)) {
			return true;
		}
		return false;
	}
	// Fin [jquispe 05/07/2016]

	@Override
	public Map<String, Object> registrarSeguimientoAcumulados(Map<String, Object> mapSeguiAcum) throws Exception {
		// Inicio [jjurado 23/06/2016] Se obtiene todos los expedientes acumulados en
		// caso de ser un expediente acumulador y se registra el seguimiento
		// para todos los expedientes acumulados
		String codEstexpori = mapSeguiAcum.get("codEstexpori").toString();
		String codEtaexpori = mapSeguiAcum.get("codEtaexpori").toString();
		Date fechaActual = (Date) mapSeguiAcum.get("fechaActual");
		Date fechaVacia = (Date) mapSeguiAcum.get("fechaVacia");

		Map<String, Object> mapParamsCierre = new HashMap<String, Object>();
		mapParamsCierre.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
		mapParamsCierre.put("numAcu", mapSeguiAcum.get("numAcu").toString());
		List<Map<String, Object>> listaAcumCierre = listarNumExpVirtualAcumCierre(mapParamsCierre);
		if (listaAcumCierre != null && listaAcumCierre.size() > 0) {
			Map<String, Object> beanSeguiWS = null;
			for (Map<String, Object> mapAcumCierre : listaAcumCierre) {

				String numExpedv = Utils.toStr(mapAcumCierre.get("numExpedv"));

				if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS = new HashMap<String, Object>();

					beanSeguiWS.put("num_expedv", numExpedv);
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual);
					beanSeguiWS.put("fec_invserv", fechaVacia);
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia);
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_tipdoc", mapSeguiAcum.get("codTipdoc"));
					beanSeguiWS.put("num_doc", mapSeguiAcum.get("numDoc"));
					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual);
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia);
					}
					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual);
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia);
					}
					seguiService.registrarSeguimiento(beanSeguiWS);
				}
			}
		}
		// Fin [jjurado 23/06/2016]
		return null;
	}

	@Override
	public Map<String, Object> obtenerDatosDocumentoOrigen(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoOrigen");
		Map<String, Object> resultMap = null;
		Map<String, Object> mapDocExpVirt = null;
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		Map<String, Object> mapT01Param = null;
		String descParam = null;

		try {
			resultMap = new LinkedHashMap<String, Object>();

			mapDocExpVirt = new HashMap<String, Object>();
			mapDocExpVirt.put("codTipDocSust", parametros.get("codTipDocSust"));
			mapDocExpVirt.put("numExpeDv", parametros.get("numExpeDv"));
			t6613DocExpVirtBean = t6613DocExpVirtDAO.obtenerDocOrigen(mapDocExpVirt);

			resultMap.put("numDoc",
					t6613DocExpVirtBean != null && t6613DocExpVirtBean.getNumDoc() != null
							? t6613DocExpVirtBean.getNumDoc().trim()
							: "");

			if (t6613DocExpVirtBean == null || Utils.esFechaVacia(t6613DocExpVirtBean.getFecDoc())) {
				resultMap.put("fecDoc", null);
			} else {
				resultMap.put("fecDoc", t6613DocExpVirtBean.getFecDoc());
			}

			mapT01Param = new HashMap<String, Object>();
			mapT01Param.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapT01Param.put("codParametro", parametros.get("codProc"));

			descParam = this.obtenerDescParamExpVirt(mapT01Param);

			resultMap.put("desProc", descParam);

			mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapT01Param.put("codParametro", parametros.get("codTipExp"));

			descParam = this.obtenerDescParamExpVirt(mapT01Param);
			resultMap.put("desTipexp", descParam);

			if (t6613DocExpVirtBean != null) {
				mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				mapT01Param.put("codParametro", t6613DocExpVirtBean.getCodTipDoc());

				descParam = this.obtenerDescParamExpVirt(mapT01Param);
			} else {
				descParam = "";
			}

			resultMap.put("desTipdoc", descParam);

			// Inicio - [oachahuic][PAS20175E210400016]
			if (t6613DocExpVirtBean == null || Utils.esFechaVacia(t6613DocExpVirtBean.getFecNotif())) {
				resultMap.put("fecNot", null);
			} else {
				resultMap.put("fecNot", t6613DocExpVirtBean.getFecNotif());
			}
			if (t6613DocExpVirtBean == null
					|| ValidaConstantes.SEPARADOR_GUION.equals(t6613DocExpVirtBean.getCodForNotif().trim())) {
				resultMap.put("forNot", null);
			} else {
				mapT01Param = new HashMap<String, Object>();
				mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapT01Param.put("codParametro", t6613DocExpVirtBean.getCodForNotif());
				descParam = this.obtenerDescParamExpVirt(mapT01Param);
				resultMap.put("forNot", descParam);
			}
			// Fin - [oachahuic][PAS20175E210400016]
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoOrigen");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoOrigen");

			NDC.pop();
			NDC.remove();
		}

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String registrarDocumentosExpAdjunto(Map<String, Object> parametros) throws Exception {
		DataSource origenDatos = null;
		Map<String, Object> mapDataSource = null;
		Map<String, Object> beanCcm = null;
		Calendar fechaActual = null;
		Map<String, Object> beanDocExp = null;
		Long numSeq = null;
		Long numCordoc = null;
		JsonSerializer serializer = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		Calendar fechaVacia = null;
		String codIdecm = null;
		String jsonResponse = null;
		List<String> listaColaboradores = null;
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		String mensaje = null;
		String formatoFechaReq = "dd-MM-yyyy";
		String fecha = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		Map<String, Object> mapDataSourceParam = null;
		// DataSource origenDatosParam = null;
		Map<String, Object> mapT01Param = null;
		String descTipoDoc = null;
		String flagOrigen = null;
		Map<String, Object> mapaPool = null; // 13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE
												// (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.registrarDocumentosExpAdjunto");

		try {
			// Fecha actual
			fechaActual = Calendar.getInstance();

			// Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			// ECM
			beanCcm = new HashMap<String, Object>();
			beanCcm.put("tramaDatos", parametros.get("tramaDatos"));// pendiente se tiene que generar la trama
			beanCcm.put("metadata", parametros.get("metadata"));
			beanCcm.put("numExpeDv", parametros.get("numExpeDv"));

			// codIdecm = ecmService.guardarDocumentoECM(beanCcm);
			codIdecm = "TEMPORAL";
			if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
			}

			// PDF ??
			// resultCreatePDF = ccmService.guardarDocumentoPDF(beanCcm)

			// Crear el documento
			beanDocExp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			numCordoc = numSeq;
			beanDocExp.put("numCordoc", numCordoc);
			beanDocExp.put("numExpeDv", parametros.get("numExpeDv"));
			beanDocExp.put("codTipdoc", parametros.get("codTipDoc"));
			beanDocExp.put("codTipExp", parametros.get("codTipExp"));
			beanDocExp.put("numRequerim", null);
			beanDocExp.put("codEstDocReq", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("desMotSolDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("numDoc", parametros.get("numDoc"));
			beanDocExp.put("fecDoc", parametros.get("fecDoc"));
			beanDocExp.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			beanDocExp.put("codUsuCarg", parametros.get("codUsuregis"));
			beanDocExp.put("fecCarg", fechaActual.getTime());
			beanDocExp.put("codUsuRegis", parametros.get("codUsuregis"));
			beanDocExp.put("fecRegis", fechaActual.getTime());
			beanDocExp.put("desArch", parametros.get("desArch"));
			beanDocExp.put("codIdecm", codIdecm);
			beanDocExp.put("codOrigDoc", parametros.get("codOrigDoc"));

			mapDataSource = new HashMap<String, Object>();
			mapDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

			// origenDatos = generalService.obtenerOrigenDatos(mapDataSource);
			mapaPool = generalService.obtenerOrigenDatosMap(mapDataSource);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());

			beanDocExp.put("origenDatos", origenDatos);

			t6613DocExpVirtDAO.insertar(beanDocExp);

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", parametros.get("numExpeDv"));
			mapResponse.put("codTipdoc", parametros.get("codTipDoc"));
			mapResponse.put("numDoc", parametros.get("numDoc"));
			mapResponse.put("codIdecm", codIdecm);

			serializer = new JsonSerializer();
			jsonResponse = (String) serializer.serialize(mapResponse);

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {

				// Setear el map del request de seguimiento
				mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("numExpedo", parametros.get("numExpedo"));
				mapRequest.put("codEstExpOri", parametros.get("codEstexpori"));
				mapRequest.put("codEtaExpOri", parametros.get("codEtaexpori"));
				mapRequest.put("numDoc", parametros.get("numDoc"));
				mapRequest.put("codIdecm", codIdecm);
				mapRequest.put("metadata", parametros.get("metadata"));
				mapRequest.put("codUsuRegis", parametros.get("codUsuregis"));
				mapRequest.put("codTipExp", parametros.get("codTipExp"));
				mapRequest.put("codTipDoc", parametros.get("codTipDoc"));

				fecha = Utils.convertirDateToString(fechaActual.getTime(), formatoFechaReq);
				fecha = fecha.equals(ValidaConstantes.FECHA_VACIA) ? ValidaConstantes.CADENA_VACIA : fecha;
				mapRequest.put("fecRegis", fecha);

				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("cod_tipseguim", parametros.get("cod_tipseguim"));
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_SOL_GENDOC_ANEX_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", jsonResponse);
				beanSeguiWS.put("num_ruc", parametros.get("numRuc"));
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", parametros.get("codEstexpori"));
				beanSeguiWS.put("cod_etaexpori", parametros.get("codEtaexpori"));
				beanSeguiWS.put("fec_cambest", fechaActual.getTime());
				beanSeguiWS.put("fec_cambeta", fechaActual.getTime());

				seguiService.registrarSeguimiento(beanSeguiWS);
			}

			listaColaboradores = (ArrayList<String>) parametros.get("listaColaboradores");
			if (listaColaboradores != null && listaColaboradores.size() > 0) {
				mapConsultaCorreo = new HashMap<String, Object>();
				listaCorreosBeans = new ArrayList<CorreosBean>();

				for (String codColaborador : listaColaboradores) {
					correosBean = new CorreosBean();

					correosBean.setCodPers(codColaborador);
					listaCorreosBeans.add(correosBean);
				}

				mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);

				listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
				// Enviar correos a los responsables asignados al expediente virtual
				if (listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
					mapDataSourceParam = new HashMap<String, Object>();

					mapDataSourceParam.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
					mapDataSourceParam.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);

					// origenDatosParam = generalService.obtenerOrigenDatos(mapDataSourceParam);
					mapaPool = generalService.obtenerOrigenDatosMap(mapDataSourceParam);
					DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());

					mapT01Param = new HashMap<String, Object>();
					// mapT01Param.put("origenDatos", origenDatosParam);
					mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
					mapT01Param.put("codParametro", (String) parametros.get("codTipDoc"));

					descTipoDoc = this.obtenerDescParamExpVirt(mapT01Param);

					if (log.isDebugEnabled())
						log.debug(
								"Inicio Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpAdjunto");
					for (CorreosBean bean : listaCorreosBeans) {
						mapEnvioCorreo = new HashMap<String, Object>();
						mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());

						descTipoDoc = descTipoDoc != null ? descTipoDoc.trim() : "";
						mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ANX_GEN_DOC_EXP_VIRT
								.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, descTipoDoc);
						mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, numCordoc.toString());
						mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_2,
								(String) parametros.get("numExpeDv"));

						mapEnvioCorreo.put("mensaje", mensaje);

						correosService.enviarCorreo(mapEnvioCorreo);
					}

					if (log.isDebugEnabled())
						log.debug("Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpAdjunto");
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.registrarDocumentosExpAdjunto");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.registrarDocumentosExpAdjunto");

			NDC.pop();
			NDC.remove();
		}

		return jsonResponse;
	}

	@Override
	public T6614ExpVirtualBean obtenerDatosExpediente(Map<String, Object> parametros) throws Exception {

		T6614ExpVirtualBean beanExp = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDatosExpediente");

		try {

			String codigoProceso;
			String codigoTipoExpediente;
			String codigoOrigen;
			String codigoEstado;

			String numRuc;

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

			beanExp = t6614ExpVirtualDAO.obtenerDatosExpediente(parametros);

			if (beanExp != null) {

				codigoProceso = beanExp.getCodProceso();
				codigoTipoExpediente = beanExp.getCodTipoExpediente();
				codigoOrigen = beanExp.getCodOrigen();
				codigoEstado = beanExp.getCodEstado();
				numRuc = beanExp.getNumRuc();

				DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
				// nchavez 13/06/2016
				if (!Utils.isEmpty(contribuyente.getNumRuc())) {
					beanExp.setDesRazonSocial(contribuyente.getDesRazonSocial());
				} else {
					beanExp.setDesRazonSocial(ValidaConstantes.CADENA_VACIA);
				}

				beanExp.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(codigoOrigen)));
				beanExp.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(codigoEstado)));
				beanExp.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
				beanExp.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDatosExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDatosExpediente");

			NDC.pop();
			NDC.remove();
		}
		return beanExp;
	}

	@Override
	public T6614ExpVirtualBean obtenerExpVirByNumExpOri(String numExpOri) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerExpVirByNumExpOri");
		T6614ExpVirtualBean t6614Bean = null;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numExpedienteOrigen", numExpOri);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			t6614Bean = t6614ExpVirtualDAO.obtener(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerExpVirByNumExpOri");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerExpVirByNumExpOri");
		}
		return t6614Bean;
	}

	private String obtenerDescParamExpVirt(Map<String, Object> mapT01Param) throws Exception {
		String descripcion = "";
		T01ParamBean t01ParamBean = null;

		try {
			// Inicio: jtejada [12-10-2016]
			// Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			// Fin: jtejada [12-10-2016]
			t01ParamBean = t01ParamDAO.obtener(mapT01Param);
			if (t01ParamBean != null) {
				descripcion = t01ParamBean.getDesParametro().trim();
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDescParamExpVirt");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDescParamExpVirt");

			NDC.pop();
			NDC.remove();
		}

		return descripcion;
	}

	@Override
	public List<Map<String, Object>> obtenerDocumentosExpediente(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaT6613DocExpVirts = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listaT6613DocExpVirtsTemp = null;
		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> itemResult = null;
		String desTipdoc = null;
		Map<String, Object> mapParams = null;
		Map<String, Object> mapaTipoDocs = new HashMap<String, Object>();
		Map<String, Object> mapaOrigenDocumento = null;
		String temp = null;
		Date fecCarga = null;
		Date fecNotif = null; // [ATORRESCH 2017-02-20]
		String forNotif = null; // [ATORRESCH 2017-02-20]
		Date fecDocu = null;
		Map<String, Object> mapa = null;
		String desOrigenDoc = null;
		String codOrigenDoc = null;
		Map<String, Object> tempMapa = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDocumentosExpediente");

		try {
			SimpleDateFormat formatoFechaVista = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			mapParams = new HashMap<String, Object>();

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams = new HashMap<String, Object>();

			mapParams.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			mapaOrigenDocumento = catalogoService.obtenerCatalogo(mapParams);

			parametros.put("orderby", "num_cordoc");
			parametros.put("codEstDocReqNo", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			listaT6613DocExpVirtsTemp = t6613DocExpVirtDAO.listarDocumentosPorExp(parametros);
			if (listaT6613DocExpVirtsTemp != null) {
				listaT6613DocExpVirts.addAll(listaT6613DocExpVirtsTemp);
			}

			parametros.remove("codEstDocReqNo");
			parametros.put("codEstDocReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			parametros.put("orderby", "fec_regis");
			listaT6613DocExpVirtsTemp = t6613DocExpVirtDAO.listarDocumentosPorExp(parametros);
			if (listaT6613DocExpVirtsTemp != null) {
				listaT6613DocExpVirts.addAll(listaT6613DocExpVirtsTemp);
			}

			int i = 1;
			if (listaT6613DocExpVirts != null) {
//				listaMapResult = new ArrayList<Map<String, Object>>();
				listaMapResult = new LinkedList<Map<String, Object>>();

				// Inicio - [oachahuic][PAS20175E210400016]
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapa.put("codTipoExpediente", ValidaConstantes.GRUPO_TIP_DOC_CONST);
				Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);
				// Fin - [oachahuic][PAS20175E210400016]

				// [PAS20191U210500144] Inicio
				obtenerDocumentoRelacionado(listaT6613DocExpVirts);
				// [PAS20191U210500144] Fin

				for (Map<String, Object> mapT6613 : listaT6613DocExpVirts) {
					itemResult = new HashMap<String, Object>();

					temp = (String) mapT6613.get("numExpeDo");
					temp = temp.trim();
					itemResult.put("numExpedo", temp);

					temp = (String) mapT6613.get("numExpeDv");
					temp = temp.trim();
					itemResult.put("numExpedv", temp);

					temp = (String) mapT6613.get("numRuc");
					temp = temp.trim();
					itemResult.put("numRuc", temp);

					codOrigenDoc = mapT6613.get("codTipDoc").toString().trim();
					itemResult.put("codTipDoc", codOrigenDoc);

					desTipdoc = (String) mapaTipoDocs.get(codOrigenDoc);

					itemResult.put("desTipdoc", codOrigenDoc + " " + desTipdoc);
					itemResult.put("desTipdoc2", desTipdoc);

					temp = (String) mapT6613.get("numDoc");
					temp = temp.trim();
					itemResult.put("numDoc", temp);

					temp = (String) mapT6613.get("descArchivo");
					temp = temp.trim();
					itemResult.put("descArchivo", temp);

					temp = (String) mapT6613.get("codIdecm");
					temp = temp.trim();
					itemResult.put("codIdecm", temp);

					temp = (String) mapT6613.get("cod_tipdocsust");
					temp = temp.trim();
					itemResult.put("cod_tipdocsust", temp);

					// INICIO LLRB 09/12/2019

					// cambio 20/04
					String indVisC = (String) mapT6613.get("indVis");
					if (indVisC.trim().equals(ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE)) {
						indVisC = ValidaConstantes.IND_SI;
					} else {
						indVisC = ValidaConstantes.IND_NO;
					}

					String indResTriC = (String) mapT6613.get("indResTri");
					if (indResTriC.trim().equals(ValidaConstantes.IND_RESTRI_SI)) {
						indResTriC = ValidaConstantes.IND_SI;
					} else {
						indResTriC = ValidaConstantes.IND_NO;
					}

					itemResult.put("indVisible", indVisC);
					log.debug("indVis " + mapT6613.get("indVis"));
					itemResult.put("indReservTrib", indResTriC);
					log.debug("indResTri " + mapT6613.get("indResTri"));

					Integer numCorDoc = (Integer) mapT6613.get("numCorDoc");

					log.debug("numCorDoc " + mapT6613.get("numCorDoc"));

					itemResult.put("numCorDoc", numCorDoc);

					log.debug("numCordocrel-antes " + mapT6613.get("numCordocrel"));

					Integer numCordocrel = (Integer) mapT6613.get("numCordocrel");

					log.debug("numcordcrel" + numCordocrel);

					if (numCordocrel.intValue() != 0) {

						log.debug("numcordocrel-if " + numCordocrel);

						Map<String, Object> mapaTipNumDocRel = (Map<String, Object>) this
								.buscarTipNumDocRel(numCordocrel.intValue(), listaT6613DocExpVirts);

						if (mapaTipNumDocRel != null) {
							codOrigenDoc = mapaTipNumDocRel.get("codTipDoc").toString().trim();
							itemResult.put("codTipDocRel", codOrigenDoc);

							desTipdoc = (String) mapaTipoDocs.get(codOrigenDoc);

							itemResult.put("desTipdocRel", codOrigenDoc + " " + desTipdoc);

							itemResult.put("numDocRel", mapaTipNumDocRel.get("numDoc"));
						} else {
							itemResult.put("numcordocrel ", 0);
						}

					} else {
						itemResult.put("numcordocrel ", numCordocrel);
						log.debug("numcordocrel no entra" + numCordocrel);
					}
					// fin LLRB

					// LLRB 29012020
					Map<String, Object> nroCopia = null;
					nroCopia = new HashMap<String, Object>();
					nroCopia.put("numCorDoc", numCorDoc);
					log.debug("nroCopia-if " + nroCopia);
					Integer mapaCopiaP = (Integer) this.obtenerNroCopia(nroCopia);
					log.debug("mapaCopiaP-if " + mapaCopiaP);
					if (mapaCopiaP.intValue() != 0) {
						itemResult.put("numCopia", mapaCopiaP);
					} else {
						itemResult.put("numCopia", 0);
					}

					// FIN LLRB 29012020

					fecCarga = (Date) mapT6613.get("fecCarg");
					if (Utils.esFechaVacia(fecCarga)) {
						itemResult.put("fecCarg", null);
						// Inicio [jquispe 27/05/2016] Fecha de carga con formato
						itemResult.put("fecCargVista", ValidaConstantes.SEPARADOR_ESPACIO);
						// Fin [jquispe 27/05/2016]
					} else {
						itemResult.put("fecCarg", fecCarga);
						// Inicio [jquispe 27/05/2016] Fecha de carga con formato.
						itemResult.put("fecCargVista", formatoFechaVista.format(fecCarga));
						// Fin [jquispe 27/05/2016]
					}

					// Inicio [ATORRESCH 2017-02-20]
					fecNotif = (Date) mapT6613.get("fecNotif");

					if (Utils.esFechaVacia(fecNotif)) {
						itemResult.put("fecNotif", null);
						itemResult.put("fecNotifVista", ValidaConstantes.SEPARADOR_ESPACIO);
					} else {
						itemResult.put("fecNotif", fecNotif);
						itemResult.put("fecNotifVista", Utils.dateUtilToStringDDMMYYYY(fecNotif));
					}

					temp = (String) mapT6613.get("codForNotif");
					temp = temp.trim();

					if (ValidaConstantes.SEPARADOR_GUION.equals(temp)) {
						itemResult.put("desForNotif", "");
					} else {
						itemResult.put("desForNotif", mapaTiposNotificaciones.get(temp));
					}
					// Fin [ATORRESCH 2017-02-20]

					fecDocu = (Date) mapT6613.get("fecDocumento");

					if (Utils.esFechaVacia(fecDocu)) {
						itemResult.put("fecDocu", null);
						itemResult.put("fechaVista", ValidaConstantes.SEPARADOR_ESPACIO);
					} else {
						itemResult.put("fecDocu", fecDocu);
						itemResult.put("fechaVista", formatoFechaVista.format(fecDocu));
					}

					temp = (String) mapT6613.get("codUsuCarg");
					temp = temp.trim();

					itemResult.put("codUsuCarg", temp);

					mapa = new HashMap<String, Object>();
					mapa.put("codPersona", temp);

					if (temp.equals("-")) {
						itemResult.put("nomPersonaCargo", ValidaConstantes.CADENA_VACIA);
					} else {
						T02PerdpBean personaBean = personaService.validarPersona(mapa);
						if (personaBean != null) {
							personaBean = personaService.completarInformacionPersona(personaBean);
							itemResult.put("nomPersonaCargo", personaBean.getDesNombreCompleto());
						} else {
							itemResult.put("nomPersonaCargo", ValidaConstantes.CADENA_VACIA);
						}
					}

					desOrigenDoc = (String) mapaOrigenDocumento.get(mapT6613.get("codOrigen"));
					itemResult.put("codOrigen", mapT6613.get("codOrigen"));
					itemResult.put("desOrigen", desOrigenDoc);

					temp = (String) mapT6613.get("codIdecm");
					temp = temp.trim();
					itemResult.put("codIdecm", temp);
					String nroRequerim = "";
					if (Utils.isEmpty(mapT6613.get("nroRequerim")) || Utils
							.equiv(Utils.toStr(mapT6613.get("nroRequerim")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("nroRequerim", ValidaConstantes.CADENA_VACIA);
					} else {
						nroRequerim = Utils.toStr(mapT6613.get("nroRequerim")).trim();
						itemResult.put("nroRequerim", nroRequerim);
					}

					if (Utils.isEmpty(mapT6613.get("estadoDocumentoReq")) || Utils
							.equiv(Utils.toStr(mapT6613.get("estadoDocumentoReq")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("estadoDocumentoReq", ValidaConstantes.CADENA_VACIA);
					} else {
						temp = (String) mapT6613.get("estadoDocumentoReq");
						temp = temp.trim();
						itemResult.put("estadoDocumentoReq", temp);
					}

					if (Utils.isEmpty(mapT6613.get("numDoc"))
							|| Utils.equiv(Utils.toStr(mapT6613.get("numDoc")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("numDoc", ValidaConstantes.CADENA_VACIA);
					} else {
						itemResult.put("numDoc", Utils.toStr(mapT6613.get("numDoc")).trim());
					}

					if (Utils.isEmpty(mapT6613.get("desArch"))
							|| Utils.equiv(Utils.toStr(mapT6613.get("desArch")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("desArch", ValidaConstantes.CADENA_VACIA);
					} else {
						itemResult.put("desArch", Utils.toStr(mapT6613.get("desArch")).trim());
					}

					itemResult.put("codTipExp", mapT6613.get("codTipExp"));
					itemResult.put("numCorDoc", mapT6613.get("numCorDoc"));

					itemResult.put("numOrden", i);
					i++;

					String numAcumulador = (String) mapT6613.get("numAcumulador");

					numAcumulador = numAcumulador.trim().equals("0") ? "" : numAcumulador;
					itemResult.put("numAcumulador", numAcumulador);
					// Inicio [jquispe 27/05/2016] Numero de acumulador en la vista.
					itemResult.put("numAcumuladorVista", numAcumulador);
					// Fin [jquispe 27/05/2016]

					// [ATORRESCH 2017-07-07] INDICADOR DE TIPO DE DOCUMENTO
					temp = (String) mapT6613.get("indTipDoc");
					temp = temp.trim();
					itemResult.put("indTipDoc", temp);

					// PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
					if (mapT6613.get("codTipExp").equals(ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR)
							|| mapT6613.get("codTipExp").equals(ValidaConstantes.TIPO_EXPE_CRUCE_INFO)) {

						String indVis = (String) mapT6613.get("indVis");
						if (indVis.trim().equals(ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE)) {
							indVis = ValidaConstantes.IND_SI;
						} else {
							indVis = ValidaConstantes.IND_NO;
						}

						String indResTri = (String) mapT6613.get("indResTri");
						if (indResTri.trim().equals(ValidaConstantes.IND_RESTRI_SI)) {
							indResTri = ValidaConstantes.IND_SI;
						} else {
							indResTri = ValidaConstantes.IND_NO;
						}

						itemResult.put("indVis", indVis);
						itemResult.put("indResTri", indResTri);

						String numCorDocRel = String.valueOf(mapT6613.get("numCorDocRel"));

						if (numCorDocRel.equals(ValidaConstantes.IND_RESTRI_NO)) {

							itemResult.put("desTipdocRel", "");
							itemResult.put("numDocRel", "");

						} else {

							T6613DocExpVirtBean buscarDatoDocRel = new T6613DocExpVirtBean();
							Map<String, Object> mapBuscarDocRel = new HashMap<String, Object>();

							mapBuscarDocRel.put("numCorDoc", mapT6613.get("numCorDocRel"));

							buscarDatoDocRel = t6613DocExpVirtDAO.obtenerDocOrigen(mapBuscarDocRel);

							desTipdoc = (String) mapaTipoDocs.get(buscarDatoDocRel.getCodTipDoc());

							itemResult.put("numDocRel", buscarDatoDocRel.getNumDoc());
							itemResult.put("desTipdocRel", buscarDatoDocRel.getCodTipDoc() + " "
									+ ValidaConstantes.SEPARADOR_ESPACIO + desTipdoc);

						}
					}
					// PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

					// [PAS20191U210500144] Inicio
					temp = (String) mapT6613.get("indVisible");
					temp = (temp.equals("0")) ? "No" : "Si";
					itemResult.put("indVisible", temp);

					temp = (String) mapT6613.get("indReserTrib");
					temp = (temp.equals("0")) ? "No" : "Si";
					itemResult.put("indReserTrib", temp);

					// documento relacionado
					String numCorDocRel = String.valueOf(mapT6613.get("numCorDocRel"));
					itemResult.put("numCorDocRel", numCorDocRel);

					String tipDocRel = (String) mapT6613.get("tipDocRel");
					itemResult.put("tipDocRel", tipDocRel);

					String numDocRel = (String) mapT6613.get("numDocRel");
					itemResult.put("numDocRel", numDocRel);

					// [PAS20191U210500144] Fin

					listaMapResult.add(itemResult);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDocumentosExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDocumentosExpediente");

			NDC.pop();
			NDC.remove();
		}
		return listaMapResult;
	}

	// [PAS20191U210500144] Inicio
	/**
	 * Funcion que obtiene datos del documento relacionado y agrega al actual
	 * documento
	 * 
	 * @param listaT6613DocExpVirts
	 * @throws Exception
	 */
	private void obtenerDocumentoRelacionado(List<Map<String, Object>> listaT6613DocExpVirts) throws Exception {
		List<Map<String, Object>> listaT6613ConDocRelacionado = new ArrayList<Map<String, Object>>();
		Integer numCorDoc;
		Integer numCorDocRel;
		listaT6613ConDocRelacionado = listaT6613DocExpVirts;

		Map<String, Object> mapParams = new HashMap<String, Object>();
		Map<String, Object> tempMapa = new HashMap<String, Object>();
		Map<String, Object> mapaTipoDocs = new HashMap<String, Object>();
		mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
		mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDocumentoRelacionado");
		try {
			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			for (Map<String, Object> mapT6613 : listaT6613DocExpVirts) {
				// mapT6613 actual para agregar campos de doc relacionado
				numCorDocRel = (Integer) mapT6613.get("numCorDocRel");
				int isRelacionado = 0;

				for (Map<String, Object> mapDoc : listaT6613ConDocRelacionado) {
					numCorDoc = (Integer) mapDoc.get("numCorDoc");

					if (numCorDoc.equals(numCorDocRel)) {
						String codOrigenDoc = mapDoc.get("codTipDoc").toString().trim();
						String desTipdoc = (String) mapaTipoDocs.get(codOrigenDoc);
						String numDocRel = mapDoc.get("numDoc").toString().trim();

						mapT6613.put("tipDocRel", codOrigenDoc + " " + desTipdoc);
						mapT6613.put("numDocRel", numDocRel);
						isRelacionado++;
					}
				}
				if (isRelacionado == 0) {
					mapT6613.put("tipDocRel", "");
					mapT6613.put("numDocRel", "");
				}

			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDocumentoRelacionado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDocumentoRelacionado");
			NDC.pop();
			NDC.remove();
		}

	}
	// [PAS20191U210500144] Fin

	@Override
	public List<T6613DocExpVirtBean> obtenerDocumentosEstadoExpediente(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDocumentosEstadoExpediente");
		List<T6613DocExpVirtBean> listT6613DocVirtualBean = null;
		String codigoTipoDoc;
		String codigoOrigenExpe;
		String fechaRegistro;
		String vacio = "";
		String numeroDoc;
		String codResponsable;
		String codTipoDocSustento = null;

		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTipoDocumento = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenDoc = catalogoService.obtenerCatalogo(mapa);

			listT6613DocVirtualBean = t6613DocExpVirtDAO.listarDocEstadosExp(mapParametrosBusqueda);
			int i = 1;
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocVirtualBean) {
				codigoTipoDoc = t6613DocExpVirtBean.getCodTipDoc();
				codigoOrigenExpe = t6613DocExpVirtBean.getCodOrigenExpe();
				codTipoDocSustento = t6613DocExpVirtBean.getCodigoTipoDocumentoSust();
				codResponsable = t6613DocExpVirtBean.getCodUsucarga();

				String sCadena = t6613DocExpVirtBean.getNumDoc();
				sCadena = sCadena.replace(" ", "");

				if (sCadena.equals("-")) {
					numeroDoc = vacio;
				} else {
					numeroDoc = t6613DocExpVirtBean.getNumDoc();
				}

				fechaRegistro = Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFecRegis());
				if (fechaRegistro.equals("01/01/1")) {
					t6613DocExpVirtBean.setFechaDocumento(vacio);
				} else {
					t6613DocExpVirtBean.setFechaDocumento(fechaRegistro);
				}

				if (Utils.equiv(codTipoDocSustento, ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN)) {
					t6613DocExpVirtBean.setDesEstadoExpe(ValidaConstantes.DES_ESTADO_EXPEDIENTE_ABIERTO);
				} else if (Utils.equiv(codTipoDocSustento, ValidaConstantes.IND_TIP_DOC_SUST_CIERRE)) {
					t6613DocExpVirtBean.setDesEstadoExpe(ValidaConstantes.DES_ESTADO_EXPEDIENTE_CERRADO);
				} else {
					t6613DocExpVirtBean.setDesEstadoExpe(ValidaConstantes.SEPARADOR_ESPACIO);
				}

				String desTipoDoc = Utils.toStr(mapaTipoDocumento.get(codigoTipoDoc));
				t6613DocExpVirtBean.setDesTipdoc(codigoTipoDoc + ValidaConstantes.SEPARADOR_GUION + desTipoDoc);
				t6613DocExpVirtBean.setDesOrigenExpe(Utils.toStr(mapaOrigenDoc.get(codigoOrigenExpe)));

				t6613DocExpVirtBean.setNumDoc(numeroDoc);
				t6613DocExpVirtBean.setCorrelativo(i);

				mapa = new HashMap<String, Object>();
				mapa.put("codPersona", codResponsable);

				T02PerdpBean personaBean = personaService.validarPersona(mapa);
				if (personaBean != null) {
					personaBean = personaService.completarInformacionPersona(personaBean);
				}

				if (personaBean != null) {
					t6613DocExpVirtBean.setResponsableExpediente(personaBean.getDesNombreCompleto());
				} else {
					t6613DocExpVirtBean.setResponsableExpediente(ValidaConstantes.CADENA_VACIA);
				}

				i++;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDocumentosEstadoExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDocumentosEstadoExpediente");

			NDC.pop();
			NDC.remove();
		}
		return listT6613DocVirtualBean;
	}

	public List<T6613DocExpVirtBean> obtenerListaDocVisContribuyente(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6613DocExpVirtBean> listT6613DocVirtualBean = new ArrayList<T6613DocExpVirtBean>();
		List<T6613DocExpVirtBean> listT6613DocVirtualBeanTemp = null;
		Map<String, Object> mapaTipoDocumento = new HashMap<String, Object>();
		Map<String, Object> tempMapa = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyente");

		try {
			String codigoTipoDoc;
			String codigoOrigenDoc;
			String nroRequerim;
			String codEstDocRequerido;
			String fecEmiDoc;
			String vacio = "";
			String numeroDoc;
			String fechaReg;
			// INICIO LLRB
			Integer numCordocrel;
			// FIN LLRB

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			mapaTipoDocumento.putAll(tempMapa);

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			if (tempMapa != null) {
				mapaTipoDocumento.putAll(tempMapa);
			}

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			if (tempMapa != null) {
				mapaTipoDocumento.putAll(tempMapa);
			}
			// Inicio - [jeffio][PAS20175E210400016]
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapa.put("codTipoExpediente", ValidaConstantes.GRUPO_TIP_DOC_CONST);
			Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);
			String strFecha = null;
			// Fin - [jeffio][PAS20175E210400016]

			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenDoc = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_DOCUMENTO_REQUERIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstDocRequerido = catalogoService.obtenerCatalogo(mapa);

			mapParametrosBusqueda.put("orderby", "fec_carg");
			mapParametrosBusqueda.put("codEstDocReqNo", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			listT6613DocVirtualBeanTemp = t6613DocExpVirtDAO.listarDocVisiblesContribuyente(mapParametrosBusqueda);
			if (listT6613DocVirtualBeanTemp != null) {
				listT6613DocVirtualBean.addAll(listT6613DocVirtualBeanTemp);
			}

			mapParametrosBusqueda.remove("codEstDocReqNo");
			// INICIO LLRB
			List<String> listaEstDocReq = new ArrayList<String>();

			listaEstDocReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			listaEstDocReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO);
			listaEstDocReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
			mapParametrosBusqueda.put("listaEstDocReq", listaEstDocReq);
			// FIN LLRB
			mapParametrosBusqueda.put("orderby", "fec_regis");
			listT6613DocVirtualBeanTemp = t6613DocExpVirtDAO.listarDocVisiblesContribuyente(mapParametrosBusqueda);
			if (listT6613DocVirtualBeanTemp != null) {
				// listT6613DocVirtualBean.addAll(listT6613DocVirtualBeanTemp);
				// INICIO LLRB
				for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocVirtualBeanTemp) {
					if (t6613DocExpVirtBean != null) {
						listT6613DocVirtualBean.add(t6613DocExpVirtBean);
					}
				}
				// FIN LLRB
			}

			int i = 1;
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocVirtualBean) {
				codigoTipoDoc = t6613DocExpVirtBean.getCodTipDoc();
				codigoOrigenDoc = t6613DocExpVirtBean.getCodOrigenDocuento();
				codEstDocRequerido = t6613DocExpVirtBean.getEstadoDocumentoReq();
				// INICIO LLRB
				numCordocrel = t6613DocExpVirtBean.getNumCordocrel();
				log.debug("numCordocrel-antes " + t6613DocExpVirtBean.getNumCordocrel());

				if (numCordocrel.intValue() != 0) {
					String desTipdocRel = null;
					String numDocRel = null;
					T6613DocExpVirtBean beanDoc = this.buscarTipNumDocRelBean(
							(t6613DocExpVirtBean.getNumCordocrel()).intValue(), listT6613DocVirtualBean);
					log.debug("Listado Final buscarTipNumDocRelBean  " + beanDoc);

					if (beanDoc != null) {
						t6613DocExpVirtBean.setDesTipdocRel(beanDoc.getDesTipdoc());
						t6613DocExpVirtBean.setNumDocRel(beanDoc.getNumDoc());
						log.debug("desTipdocRel beanDoc " + desTipdocRel);
						log.debug("numDocRel beanDoc " + numDocRel);
						// .add(t6613DocExpVirtBean);
						log.debug("Listado Final " + listT6613DocVirtualBean);
					} // HACK
					else {
						t6613DocExpVirtBean.setDesTipdocRel("-");
						t6613DocExpVirtBean.setNumDocRel("-");
					}

				} else {

					t6613DocExpVirtBean.setDesTipdocRel("-");
					t6613DocExpVirtBean.setNumDocRel("-");
				}
				// FIN LLRB
				fecEmiDoc = Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFecDoc());// [PAS20191U210500076][OAC]

				if (!Utils.isEmpty(t6613DocExpVirtBean.getNumRequerimiento())) {
					nroRequerim = Utils.toStr(t6613DocExpVirtBean.getNumRequerimiento());
				} else {
					nroRequerim = vacio;
				}
				String sCadena = t6613DocExpVirtBean.getEstadoDocumentoReq() != null
						? t6613DocExpVirtBean.getEstadoDocumentoReq().trim()
						: "";

				if (sCadena.equals("-")) {
					codEstDocRequerido = vacio;
				} else {
					codEstDocRequerido = Utils.toStr(mapaEstDocRequerido.get(sCadena));
					t6613DocExpVirtBean.setDesEstDocRequerido(codEstDocRequerido);
				}
				// INICIO LLRB
				String ECM;
				ECM = t6613DocExpVirtBean.getCodIdentificadorECM();
				ECM = ECM.replace(" ", "");

				if (ECM.equals("0")) {
					t6613DocExpVirtBean.setCodIdentificadorECM("-");
				}
				// FIN LLRB
				sCadena = t6613DocExpVirtBean.getNumDoc();
				sCadena = sCadena.replace(" ", "");

				if (sCadena.equals("-")) {
					numeroDoc = vacio;
				} else {
					numeroDoc = t6613DocExpVirtBean.getNumDoc();
				}

				if (fecEmiDoc.equals("01/01/1")) {
					fecEmiDoc = "";
					t6613DocExpVirtBean.setFechaDocumento(fecEmiDoc);
				} else {
					t6613DocExpVirtBean.setFechaDocumento(fecEmiDoc);
				}

				if (t6613DocExpVirtBean.getDescripcionArchivo() == null) {
					t6613DocExpVirtBean.setDescripcionArchivo("-");
				}
				// Inicio - [JEFFIO][PAS20175E210400016]
				if (ValidaConstantes.SEPARADOR_GUION.equals(t6613DocExpVirtBean.getCodForNotif().trim())) {
					t6613DocExpVirtBean.setDesForNotif("");
				} else {
					t6613DocExpVirtBean.setDesForNotif(
							mapaTiposNotificaciones.get(t6613DocExpVirtBean.getCodForNotif()).toString());
				}
				strFecha = new SimpleDateFormat("yyyy-MM-dd").format(t6613DocExpVirtBean.getFecNotif());
				if (Utils.equiv(strFecha, ValidaConstantes.FECHA_VACIA)) {
					t6613DocExpVirtBean.setStrfecNotif("");
				} else {
					t6613DocExpVirtBean
							.setStrfecNotif(Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFecNotif()));
				}
				// Fin - [JEFFIO][PAS20175E210400016]

				fechaReg = Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFecRegis());
				t6613DocExpVirtBean.setNroRequerim(nroRequerim);
				t6613DocExpVirtBean.setDesTipdoc(Utils.toStr(mapaTipoDocumento.get(codigoTipoDoc)));
				t6613DocExpVirtBean.setDesOrigenDoc(Utils.toStr(mapaOrigenDoc.get(codigoOrigenDoc)));
				t6613DocExpVirtBean.setFechaRegistroDoc(fechaReg);
				t6613DocExpVirtBean.setNumDoc(numeroDoc);
				t6613DocExpVirtBean.setCorrelativo(i);
				i++;

				// Inicio [jquispe 13/06/2016] Numero de acumulador de la vista.
				String numAcumulador = t6613DocExpVirtBean.getNumAcumulador().trim().equals("0") ? ""
						: t6613DocExpVirtBean.getNumAcumulador();
				t6613DocExpVirtBean.setNumAcumulador(numAcumulador);
				t6613DocExpVirtBean.setNumAcumuladorVista(numAcumulador);
				// Fin [jquispe 13/06/2016]
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyente");

			NDC.pop();
			NDC.remove();
		}
		return listT6613DocVirtualBean;
	}

	@Override
	public List<Map<String, Object>> obtenerExpedientesVirtuales(Map<String, Object> parametros) throws Exception {
		List<T6614ExpVirtualBean> listaT6614ExpVirtualBeans = null;
		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> map = null;
		Map<String, Object> mapParamCatalogo = null;
		Map<String, Object> mapProcesos = null;
		Map<String, Object> mapTipExps = null;
		Map<String, Object> mapTipDocs = null;
		Map<String, Object> mapDependencias = null;
		String descDepen = null;
		Date fecCierre = null;
		Map<String, Object> mapDocExpVirt = null;
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		String numExpeDv = null;
		List<Map<String, Object>> listaMapPerdp = null;
		List<T6621RespExpVirtBean> listaResponsablesAsig = null;
		Map<String, Object> mapResp = null;
		Map<String, Object> mapPerdp = null;
		Map<String, String> mapPerdpTodos = null;
		String codColab = null;
		String descColab = null;
		T02PerdpBean t02PerdpBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerExpedientesVirtuales");

		try {
			listaT6614ExpVirtualBeans = t6614ExpVirtualDAO.listar(parametros);
			if (listaT6614ExpVirtualBeans != null) {
				listaMapResult = new ArrayList<Map<String, Object>>();

				mapParamCatalogo = new HashMap<String, Object>();
				mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapParamCatalogo.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapProcesos = catalogoService.obtenerCatalogo(mapParamCatalogo);

				mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				mapTipDocs = catalogoService.obtenerCatalogo(mapParamCatalogo);

				mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapTipExps = catalogoService.obtenerCatalogo(mapParamCatalogo);

				mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
				mapDependencias = catalogoService.obtenerCatalogo(mapParamCatalogo);

				mapPerdpTodos = new HashMap<String, String>();

				for (T6614ExpVirtualBean bean : listaT6614ExpVirtualBeans) {
					map = new HashMap<String, Object>();

					map.put("desProc", mapProcesos.get(bean.getCodProceso()));
					map.put("desTipexp", mapTipExps.get(bean.getCodTipoExpediente()));

					descDepen = "";
					if (mapDependencias.get(bean.getCodDependencia()) != null) {
						descDepen = (String) mapDependencias.get(bean.getCodDependencia());

						if (descDepen != null) {
							descDepen = descDepen.substring(ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA,
									ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA).trim();
						}
					}

					map.put("descDep", descDepen);
					map.put("numRuc", bean.getNumRuc() != null ? bean.getNumRuc().trim() : "");
					map.put("numExpedo",
							bean.getNumExpedienteOrigen() != null ? bean.getNumExpedienteOrigen().trim() : "");

					numExpeDv = bean.getNumExpedienteVirtual() != null ? bean.getNumExpedienteVirtual().trim() : "";
					map.put("numExpedv", numExpeDv);

					// Documento origen
					mapDocExpVirt = new HashMap<String, Object>();
					mapDocExpVirt.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
					mapDocExpVirt.put("numExpeDv", numExpeDv);

					t6613DocExpVirtBean = t6613DocExpVirtDAO.obtenerDocOrigen(mapDocExpVirt);
					if (t6613DocExpVirtBean != null) {
						map.put("desTipdocorig",
								(String) mapTipDocs.get(t6613DocExpVirtBean.getCodTipDoc() != null
										? t6613DocExpVirtBean.getCodTipDoc().trim()
										: ""));
						map.put("numDocorig",
								t6613DocExpVirtBean.getNumDoc() != null ? t6613DocExpVirtBean.getNumDoc().trim() : "");

						if (Utils.esFechaVacia(t6613DocExpVirtBean.getFecDoc())) {
							map.put("fecDocorig", null);
						} else {
							map.put("fecDocorig", t6613DocExpVirtBean.getFecDoc());
						}
					} else {
						map.put("desTipdocorig", "");
						map.put("numDocorig", "");
						map.put("fecDocorig", null);
					}

					// Inicio - [oachahuic][PAS20175E210400016]
					if (t6613DocExpVirtBean == null || Utils.esFechaVacia(t6613DocExpVirtBean.getFecNotif())) {
						map.put("fecNot", null);
					} else {
						map.put("fecNot", t6613DocExpVirtBean.getFecNotif());
					}
					if (t6613DocExpVirtBean == null
							|| ValidaConstantes.SEPARADOR_GUION.equals(t6613DocExpVirtBean.getCodForNotif().trim())) {
						map.put("forNot", null);
					} else {
						mapParamCatalogo = new HashMap<String, Object>();
						mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
						mapParamCatalogo.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
						mapParamCatalogo.put("codParametro", t6613DocExpVirtBean.getCodForNotif());
						map.put("forNot", this.obtenerDescParamExpVirt(mapParamCatalogo));
					}
					// Fin - [oachahuic][PAS20175E210400016]

					map.put("codUsuregis",
							bean.getCodUsuarioRegistro() != null ? bean.getCodUsuarioRegistro().trim() : "");
					map.put("fecRegis", bean.getFecRegistro());

					mapDocExpVirt.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
					mapDocExpVirt.put("numExpeDv", numExpeDv);

					t6613DocExpVirtBean = t6613DocExpVirtDAO.obtenerDocOrigen(mapDocExpVirt);
					if (t6613DocExpVirtBean != null) {
						map.put("desTipdoccierr",
								(String) mapTipDocs.get(t6613DocExpVirtBean.getCodTipDoc() != null
										? t6613DocExpVirtBean.getCodTipDoc().trim()
										: ""));
						map.put("numDoccierr",
								t6613DocExpVirtBean.getNumDoc() != null ? t6613DocExpVirtBean.getNumDoc().trim() : "");
					} else {
						map.put("desTipdoccierr", "");
						map.put("numDoccierr", "");
					}

					fecCierre = bean.getFecCierre();
					if (Utils.esFechaVacia(fecCierre)) {
						map.put("fecCierre", null);
					} else {
						map.put("fecCierre", fecCierre);
					}

					map.put("desMotcierr", bean.getDesMotivoCierre() != null ? bean.getDesMotivoCierre().trim() : "");

					listaMapPerdp = new ArrayList<Map<String, Object>>();

					mapResp = new HashMap<String, Object>();
					mapResp.put("numExpedienteVirtual", numExpeDv);
					mapResp.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					listaResponsablesAsig = t6621RespExpVirtDAO.listar(mapResp);

					if (listaResponsablesAsig != null && listaResponsablesAsig.size() > 0) {
						for (T6621RespExpVirtBean respBean : listaResponsablesAsig) {
							mapPerdp = new HashMap<String, Object>();

							codColab = respBean.getCodColaborador().trim();
							mapPerdp.put("codPersona", codColab);
							descColab = mapPerdpTodos.get(codColab);
							if (descColab == null) {
								t02PerdpBean = t02PerdpDAO.obtener(mapPerdp);
								if (t02PerdpBean != null) {
									descColab = t02PerdpBean.getDesNombres().trim() + ValidaConstantes.SEPARADOR_ESPACIO
											+ t02PerdpBean.getDesApellidoPaterno().trim()
											+ ValidaConstantes.SEPARADOR_ESPACIO
											+ t02PerdpBean.getDesApellidoMaterno().trim();

									mapPerdpTodos.put(codColab, descColab);
								}
							}

							mapPerdp.put("descColab", descColab);

							listaMapPerdp.add(mapPerdp);
						}
					}

					map.put("respExpvirt", listaMapPerdp);

					listaMapResult.add(map);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerExpedientesVirtuales");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerExpedientesVirtuales");

			NDC.pop();
			NDC.remove();
		}

		return listaMapResult;
	}

	private byte[] GenerarIndiceGenerico(Map<String, Object> mapaTiposExpedientes, Map<String, Object> parametros,
			Map<String, Object> mapaTiposDocumentos, Map<String, Object> mapaProcesos, String desRazonSocial)
			throws Exception {

		Map<String, Object> mapParam = null;
		mapParam = new HashMap<String, Object>();
		mapParam.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
		mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaOrigDoc = catalogoService.obtenerCatalogo(mapParam);

		try {
			JsonSerializer serializer = new JsonSerializer();
			// FORMATEAR DOCUMENTOS DEL EXPEDIENTE
			List<Map<String, Object>> listaDocumentosEnvio = new ArrayList<Map<String, Object>>();
			int numOrden = 0;
			String fecVista = ValidaConstantes.CADENA_VACIA;
			StringBuilder sbDesTipDoc = null;
			Map<String, Object> documentoEnvio = null;

			for (T6613DocExpVirtBean documentoExpediente : (List<T6613DocExpVirtBean>) parametros
					.get("listadoDocumentosExpediente")) {
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				try {
					fecVista = formatoFechaExporta.format(documentoExpediente.getFecDoc());
				} catch (Exception ex) {
					fecVista = ValidaConstantes.CADENA_VACIA;
				}
				sbDesTipDoc = new StringBuilder();
				sbDesTipDoc.append(documentoExpediente.getCodTipDoc());
				sbDesTipDoc.append(ValidaConstantes.SEPARADOR_GUION);
				sbDesTipDoc.append(mapaTiposDocumentos.get(documentoExpediente.getCodTipDoc()).toString());

				documentoEnvio = new HashMap<String, Object>();
				documentoEnvio.put("numDocumento", documentoExpediente.getNumDoc());
				documentoEnvio.put("desTipoDocumento", sbDesTipDoc.toString());
				documentoEnvio.put("desDocumento", documentoExpediente.getDescripcionArchivo());
				documentoEnvio.put("fecEmision", fecVista);
				documentoEnvio.put("desOrigenDocumento", mapaOrigDoc.get(documentoExpediente.getCodOrigenDocuento()));
				documentoEnvio.put("numRequerimiento", documentoExpediente.getNumRequerimiento());
				documentoEnvio.put("numOrden", " " + (++numOrden));

				listaDocumentosEnvio.add(documentoEnvio);
			}

			// ARMAR JSON PARA GENERAR EL PDF
			StringBuilder sbJson = new StringBuilder();
			sbJson.append("{\"cabecera\":{");
			sbJson.append("\"numExpedienteVirtual\":\"")
					.append(Utils.toStr(parametros.get("numExpedienteOrigen")).trim()).append("\",");
			sbJson.append("\"desProceso\":\"").append(mapaProcesos.get(parametros.get("codProceso")).toString())
					.append("\",");
			sbJson.append("\"desTipoExpediente\":\"")
					.append(mapaTiposExpedientes.get(parametros.get("codTipoExpediente")).toString()).append("\",");
			sbJson.append("\"numRuc\":\"").append(parametros.get("numRuc").toString()).append("\",");
			sbJson.append("\"fecEmisionExp\":\"").append(Utils.toStr(parametros.get("fecEmi"))).append("\",");
			sbJson.append("\"desRazonSocial\":\"").append(desRazonSocial.replace("\"", "''")).append("\"},");
			sbJson.append("\"detalle\":").append((String) serializer.serialize(listaDocumentosEnvio)).append("}");

			// GENERAR PDF
			Service servicioAxis = new Service();

			Call generaPDF = (Call) servicioAxis.createCall();
			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
			generaPDF.setOperationName("generar");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(byte[].class);
			byte[] docCierreExpPDF = (byte[]) (generaPDF.invoke(
					new Object[] { ExportaConstantes.PDF_COD_PLANTILLA_01_001, sbJson.toString(), "pdf", 10000, 1 }));
			return docCierreExpPDF;

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtual");
			NDC.pop();
			NDC.remove();
		}

	}

	private Map<String, Object> GenerarIndiceFisca(Map<String, Object> mapaTiposExpedientes,
			Map<String, Object> parametros, Map<String, Object> mapaTiposDocumentos, Map<String, Object> mapaProcesos,
			String desRazonSocial) throws Exception {

		log.debug("Inicio - ExpedienteVirtualServiceImpl.GenerarIndiceFisca");
		Map<String, Object> mapParam = null;
		String nomResp;
		mapParam = new HashMap<String, Object>();
		mapParam.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
		mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		// Calendar fechaActual = Calendar.getInstance();
		Map<String, Object> mapResponse = null;
		ResponseEntity<byte[]> responseEntity = null;
		Integer correlativoPDF = 0;
		List<Map<String, Object>> listaT10373DocAdjReqTemp = null;
		Map<String, Object> mapParamCatalogo = null;

		try {
			JsonSerializer serializer = new JsonSerializer();
			// FORMATEAR DOCUMENTOS DEL EXPEDIENTE
			List<Map<String, Object>> listDocumentos = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listDocumentosV = new ArrayList<Map<String, Object>>();
			int numOrden = 0;
			int numOrdenV = 0;
			String fecCarga;
			String fecNotif;
			String fecConclusion;
			StringBuilder sbDesTipDoc = null;
			Map<String, Object> documentoEnvio = null;
			int contadordePaginas = 0;
			int contadordePaginasV = 0;
			int totalFolios = 0;
			int totalFoliosV = 0;
			int totalFoliosE = 0;
			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

			fecConclusion = formatoFechaExporta.format(new Date());

			// OBTENER DESCRIPCIÓN DE DEPENDENCIA
			mapParamCatalogo = new HashMap<String, Object>();
			mapParamCatalogo.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			mapParamCatalogo.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParamCatalogo.put("codParametro", parametros.get("codDep").toString());
			String dependencia = obtenerDescParamExpVirt(mapParamCatalogo).trim().substring(
					ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA, ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA);

			// CREACION EN DIRECTORIO TEMPORAL
			Date fecSistema = new Date();
			SimpleDateFormat formatoFecDirTemp = new SimpleDateFormat("yyyy-MM-dd_hhmmssSSS");
			String fecDirTemp = formatoFecDirTemp.format(fecSistema);
			StringBuilder pathDirTemp = new StringBuilder();
			pathDirTemp.append(ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
			pathDirTemp.append(fecDirTemp).append("-indice-");
			pathDirTemp.append(parametros.get("numExpedienteOrigen").toString().trim()).append("/");
			File dirTemp = new File(pathDirTemp.toString());
			dirTemp.mkdir();

			for (T6613DocExpVirtBean documentoExpediente : (List<T6613DocExpVirtBean>) parametros
					.get("listadoDocumentosExpediente")) {
				int cntFolios = 0;
				int paginasdel = 0;
				int paginasdelV = 0;
				int paginasal = 0;
				int paginasalV = 0;
				int size = 0;
				String extension = Utils.obtenerExtension(documentoExpediente.getDescripcionArchivo());
				try {
					fecNotif = formatoFechaExporta.format(documentoExpediente.getFecNotif());
					if (ValidaConstantes.FECHA_DEFAULT_DD_MM_YYYY.endsWith(fecNotif)) {
						fecNotif = "No aplica";
					}
				} catch (Exception ex) {
					fecNotif = "No aplica";
				}
				try {
					fecCarga = formatoFechaExporta.format(documentoExpediente.getFechaCarga());
				} catch (Exception ex) {
					String fechaCar = "00/00/0000";
					fecCarga = formatoFechaExporta.format(fechaCar);
				}
				sbDesTipDoc = new StringBuilder();
				sbDesTipDoc.append(mapaTiposDocumentos.get(documentoExpediente.getCodTipDoc()).toString());

				log.debug("REvalor idecm: " + documentoExpediente.getCodIdentificadorECM().toString().trim());

				// VERIFICAR SI EL IDECM ES 0, CONSULTAR TABLA T10373
				if (ValidaConstantes.FILTRO_CERO
						.equals(documentoExpediente.getCodIdentificadorECM().toString().trim())) {
					HashMap<String, Object> mapParametros = new HashMap<String, Object>();
					mapParametros.put("numCorDoc", documentoExpediente.getNumCorDoc());

					listaT10373DocAdjReqTemp = t10373DocAdjReqDAO.listarArcDocPorExp(mapParametros);
					log.debug("lista: " + listaT10373DocAdjReqTemp);
					int cntFoliosE = 0;
					int sizeE = 0;
					for (Map<String, Object> documentoEscrito : listaT10373DocAdjReqTemp) {
						cntFoliosE = cntFoliosE + (Integer) documentoEscrito.get("numFolios");
						sizeE = sizeE + (Integer) documentoEscrito.get("cntTamArc");
					}
					paginasdel = contadordePaginas + 1;
					paginasal = contadordePaginas + cntFoliosE;
					contadordePaginas = paginasal;
					totalFoliosE = totalFoliosE + cntFoliosE;

					documentoEnvio = new HashMap<String, Object>();
					documentoEnvio.put("numDocumento", documentoExpediente.getNumDoc());
					documentoEnvio.put("desTipoDocumento", sbDesTipDoc.toString());
					documentoEnvio.put("desDocumento", documentoExpediente.getDescripcionArchivo());
					documentoEnvio.put("foliosDel", String.valueOf(paginasdel));
					documentoEnvio.put("foliosAl", String.valueOf(paginasal));
					documentoEnvio.put("cantidad", String.valueOf(cntFoliosE));
					documentoEnvio.put("tamano", getSize(sizeE));
					documentoEnvio.put("fecCarga", fecCarga);
					documentoEnvio.put("fecNotif", fecNotif);
					documentoEnvio.put("numOrden", " " + (++numOrden));
					log.debug("documentoEnvio: " + documentoEnvio);
					listDocumentos.add(documentoEnvio);

					// CONTRIBUYENTE
					paginasdelV = contadordePaginasV + 1;
					paginasalV = contadordePaginasV + cntFoliosE;
					contadordePaginasV = paginasalV;

					documentoEnvio = new HashMap<String, Object>();
					documentoEnvio.put("numDocumento", documentoExpediente.getNumDoc());
					documentoEnvio.put("desTipoDocumento", sbDesTipDoc.toString());
					documentoEnvio.put("desDocumento", documentoExpediente.getDescripcionArchivo());
					documentoEnvio.put("foliosDel", String.valueOf(paginasdelV));
					documentoEnvio.put("foliosAl", String.valueOf(paginasalV));
					documentoEnvio.put("cantidad", String.valueOf(cntFoliosE));
					documentoEnvio.put("tamano", getSize(sizeE));
					documentoEnvio.put("fecCarga", fecCarga);
					documentoEnvio.put("fecNotif", fecNotif);
					documentoEnvio.put("numOrden", " " + (++numOrdenV));
					log.debug("documentoEnvio: " + documentoEnvio);
					listDocumentosV.add(documentoEnvio);
				} else {
					// DESCARGAR EL DOCUMENTO PARA CALCULAR LOS FOLIOS Y TAMANIO
					mapParam = new HashMap<String, Object>();
					mapParam.put("inline", "false");
					mapParam.put("codIdecm", documentoExpediente.getCodIdentificadorECM());
					responseEntity = ecmService.descargarDocumentoECM(mapParam);
					log.debug("responseEntity: " + responseEntity);

					// OBTENER FOLIOS
					correlativoPDF++;
					if (extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
						FileOutputStream fos = new FileOutputStream(pathDirTemp.toString() + correlativoPDF + ".pdf");
						fos.write(responseEntity.getBody());
						fos.close();
						File filePdf = new File(pathDirTemp.toString() + correlativoPDF + ".pdf");
						log.debug("ruta: " + pathDirTemp.toString() + correlativoPDF + ".pdf");
						FileInputStream fis = new FileInputStream(filePdf);
						PdfReader oPdfReader = new PdfReader(responseEntity.getBody());
						cntFolios = oPdfReader.getNumberOfPages();
						fis.close();
					} else {
						cntFolios = 1;
					}
					paginasdel = contadordePaginas + 1;
					paginasal = contadordePaginas + cntFolios;
					contadordePaginas = paginasal;
					totalFolios = totalFolios + cntFolios;
					size = responseEntity.getBody().length;

					documentoEnvio = new HashMap<String, Object>();
					documentoEnvio.put("numDocumento", documentoExpediente.getNumDoc());
					documentoEnvio.put("desTipoDocumento", sbDesTipDoc.toString());
					documentoEnvio.put("desDocumento", documentoExpediente.getDescripcionArchivo());
					documentoEnvio.put("foliosDel", String.valueOf(paginasdel));
					documentoEnvio.put("foliosAl", String.valueOf(paginasal));
					documentoEnvio.put("cantidad", String.valueOf(cntFolios));
					documentoEnvio.put("tamano", getSize(size));
					documentoEnvio.put("fecCarga", fecCarga);
					documentoEnvio.put("fecNotif", fecNotif);
					documentoEnvio.put("numOrden", " " + (++numOrden));
					log.debug("documentoEnvio: " + documentoEnvio);
					listDocumentos.add(documentoEnvio);

					if (ValidaConstantes.INDICADOR_VISIBILE_CONTRIBUYENTE.equals(documentoExpediente.getIndVisible())) {
						paginasdelV = contadordePaginasV + 1;
						paginasalV = contadordePaginasV + cntFolios;
						contadordePaginasV = paginasalV;
						totalFoliosV = totalFoliosV + cntFolios;

						documentoEnvio = new HashMap<String, Object>();
						documentoEnvio.put("numDocumento", documentoExpediente.getNumDoc());
						documentoEnvio.put("desTipoDocumento", sbDesTipDoc.toString());
						documentoEnvio.put("desDocumento", documentoExpediente.getDescripcionArchivo());
						documentoEnvio.put("foliosDel", String.valueOf(paginasdelV));
						documentoEnvio.put("foliosAl", String.valueOf(paginasalV));
						documentoEnvio.put("cantidad", String.valueOf(cntFolios));
						documentoEnvio.put("tamano", getSize(size));
						documentoEnvio.put("fecCarga", fecCarga);
						documentoEnvio.put("fecNotif", fecNotif);
						documentoEnvio.put("numOrden", " " + (++numOrdenV));
						log.debug("documentoEnvio: " + documentoEnvio);
						listDocumentosV.add(documentoEnvio);
					}

				}
			}

			totalFolios = totalFolios + totalFoliosE;
			totalFoliosV = totalFoliosV + totalFoliosE;

			String total = String.valueOf(totalFolios);
			String totalV = String.valueOf(totalFoliosV);

			mapResponse = serviceFisca(parametros.get("numExpedienteOrigen").toString().trim(),
					parametros.get("numRuc").toString().trim());

			mapParam = new HashMap<String, Object>();
			mapParam.put("codPersona", mapResponse.get("regSup").toString());
			log.debug("codPersona: " + mapResponse.get("regSup").toString());
			mapParam.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);
			T02PerdpBean responsable = personaService.validarPersona(mapParam);
			log.debug("responsable: " + responsable);
			if (responsable != null) {
				responsable = personaService.completarInformacionPersona(responsable);
				nomResp = responsable.getDesNombreCompleto();
			} else {
				nomResp = "";
			}
			T12UOrga uorga = t12UOrgaDAO.getUnidadOrganica(mapResponse.get("codUuoo").toString());
			// DOCUMENTOS VISIBLES AL CONTRIBUYENTE
			// ARMAR JSON PARA GENERAR EL PDF
			StringBuilder sbJson = new StringBuilder();
			sbJson.append("{\"cabecera\":{");
			sbJson.append("\"numExpedienteVirtual\":\"").append(parametros.get("numExpedienteOrigen").toString().trim())
					.append("\",");
			sbJson.append("\"desPeriodo\":\"").append(mapResponse.get("perOrden").toString()).append("\",");
			sbJson.append("\"codUuoo\":\"").append(uorga.getDesUorga().toString()).append("\",");
			sbJson.append("\"desIntendencia\":\"").append(dependencia).append("\",");
			sbJson.append("\"desPrograma\":\"").append(mapResponse.get("proFis").toString()).append("\",");
			sbJson.append("\"registroSuper\":\"").append(mapResponse.get("regSup").toString()).append("\",");
			sbJson.append("\"nombresSuper\":\"").append(nomResp).append("\",");
			sbJson.append("\"desTipoFiscalizacion\":\"").append(mapResponse.get("tipFis").toString()).append("\",");
			sbJson.append("\"numRuc\":\"").append(parametros.get("numRuc").toString()).append("\",");
			sbJson.append("\"totFolios\":\"").append(totalV).append("\",");
			sbJson.append("\"fecConclusion\":\"").append(fecConclusion).append("\",");
			sbJson.append("\"desRazonSocial\":\"").append(desRazonSocial.replace("\"", "''")).append("\"},");
			sbJson.append("\"detalle\":").append((String) serializer.serialize(listDocumentosV)).append("}");
			log.debug("sbJson: " + sbJson);
			// GENERAR PDF
			Service servicioAxis = new Service();
			Call generaPDF = (Call) servicioAxis.createCall();
			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
			generaPDF.setOperationName("genera");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(int.class);
			Integer idPdfIndiceV = (Integer) (generaPDF.invoke(
					new Object[] { ExportaConstantes.PDF_COD_PLANTILLA_01_004, sbJson.toString(), "pdf", 1000 }));
			byte[] byteIndiceV = this.ObtenerPDFUnload(idPdfIndiceV);

			// TODOS LOS DOCUMENTOS
			// ARMAR JSON PARA GENERAR EL PDF
			sbJson = new StringBuilder();
			sbJson.append("{\"cabecera\":{");
			sbJson.append("\"numExpedienteVirtual\":\"").append(parametros.get("numExpedienteOrigen").toString().trim())
					.append("\",");
			sbJson.append("\"desPeriodo\":\"").append(mapResponse.get("perOrden").toString()).append("\",");
			sbJson.append("\"codUuoo\":\"").append(uorga.getDesUorga().toString()).append("\",");
			sbJson.append("\"desIntendencia\":\"").append(dependencia).append("\",");
			sbJson.append("\"desPrograma\":\"").append(mapResponse.get("proFis").toString()).append("\",");
			sbJson.append("\"registroSuper\":\"").append(mapResponse.get("regSup").toString()).append("\",");
			sbJson.append("\"nombresSuper\":\"").append(nomResp).append("\",");
			sbJson.append("\"desTipoFiscalizacion\":\"").append(mapResponse.get("tipFis").toString()).append("\",");
			sbJson.append("\"numRuc\":\"").append(parametros.get("numRuc").toString()).append("\",");
			sbJson.append("\"totFolios\":\"").append(total).append("\",");
			sbJson.append("\"fecConclusion\":\"").append(fecConclusion).append("\",");
			sbJson.append("\"desRazonSocial\":\"").append(desRazonSocial.replace("\"", "''")).append("\"},");
			sbJson.append("\"detalle\":").append((String) serializer.serialize(listDocumentos)).append("}");
			log.debug("sbJson: " + sbJson);
			// GENERAR PDF
			servicioAxis = new Service();
			generaPDF = (Call) servicioAxis.createCall();
			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
			generaPDF.setOperationName("genera");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(int.class);
			Integer idPdfIndiceNV = (Integer) (generaPDF.invoke(
					new Object[] { ExportaConstantes.PDF_COD_PLANTILLA_01_004, sbJson.toString(), "pdf", 1000 }));
			byte[] byteIndiceNV = this.ObtenerPDFUnload(idPdfIndiceNV);

			Map<String, Object> mapRpta = new HashMap<String, Object>();
			mapRpta.put("byteIndiceV", byteIndiceV);
			mapRpta.put("byteIndiceNV", byteIndiceNV);
			return mapRpta;
		} catch (Exception ex) {
			log.error("Error - ExpedienteVirtualServiceImpl.GenerarIndiceFisca");
			log.error(ex, ex);
			throw ex;
		} finally {
			log.debug("Final - ExpedienteVirtualServiceImpl.GenerarIndiceFisca");
		}
	}

	private Map<String, Object> serviceFisca(String numExp, String numRuc) throws Exception {

		Map<String, Object> mapResponse = null;
		URL url = null;
		HttpURLConnection httpConnection = null;
		BufferedReader responseBuffer = null;
		String output = null;
		String error = null;

		// DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

		String uri = "http://api.sunat.peru/v1/auditoria/tributaria/operativo/cruces/e/orden/?ruc=" + numRuc
				+ "&numExp=" + numExp;
		url = new URL(uri);

		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Accept", "application/json");

		if (httpConnection.getResponseCode() != 200) {
			responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getErrorStream()), "UTF-8"));

			while ((output = responseBuffer.readLine()) != null) {
				error = output;
			}
			throw new RuntimeException(error);
		}

		responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream()), "UTF-8"));

		while ((output = responseBuffer.readLine()) != null) {
			mapResponse = (Map<String, Object>) new JsonSerializer().deserialize(output, java.util.Map.class);
		}

		httpConnection.disconnect();
		log.debug("resultado " + mapResponse);
		return mapResponse;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String cerrarExpedienteVirtual(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtual");

		String jsonResponse = null;
		Map<String, Object> mapParam = null;

		try {
			JsonSerializer serializer = new JsonSerializer();
			Calendar fechaActual = Calendar.getInstance();
			Calendar fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			// VERIFICAR SI ES DE ORIGEN AUTOMATICO O MANUAL PARA EL REGISTRO DE SEGUIMIENTO
			if (ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO.equals(parametros.get("indOrigenInvoca").toString())) {
				Map<String, Object> mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
				mapResponse.put("numExpedv", ((String) parametros.get("numExpedienteVirtual")).trim());

				jsonResponse = (String) serializer.serialize(mapResponse);

				Map<String, Object> mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("codEstcierrexp", parametros.get("codEstadoCierre"));
				mapRequest.put("codUsucierr", parametros.get("codUsuario"));
				mapRequest.put("desMotcierr", parametros.get("desMotivoCierre"));
				mapRequest.put("desSumilla", parametros.get("desSumilla"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));
				// PAS20191U210500144 Inicio 400101 RF04, RF13 PSALDARRIAGA
				if (parametros.get("fecCierre") != null) {
					mapRequest.put("fecCierre", parametros.get("fecCierre"));
				} else {
					mapRequest.put("fecCierre", fechaActual.getTime());
				}
				// PAS20191U210500144 Fin 400101 RF04, RF13 PSALDARRIAGA

				Map<String, Object> beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpedienteVirtual"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_CERRAR_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuario"));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", jsonResponse);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				seguiService.registrarSeguimiento(beanSeguiWS);
			} else {
				this.registrarSeguimientoCerrarExpedienteVirtual(parametros);
			}

			// [PAS20181U210400241] REGISTRAR EL ESTADO Y LA ETAPA SÓLO PARA LOS TIPOS DE
			// EXPEDIENTE DE CARTA INDUCTIVA Y ESQUELA
			if (ValidaConstantes.TIPO_EXPE_CARTA_INDUCTIVA.equals(parametros.get("codTipoExpediente").toString())
					|| ValidaConstantes.TIPO_EXPE_ESQUELA.equals(parametros.get("codTipoExpediente").toString())) {
				Map<String, Object> beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpedienteVirtual"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.COD_ESTADO_CERRADO);// OJO DEBERÍA DE LLEGAR COMO
																						// PARÁMETRO
				beanSeguiWS.put("fec_cambest", fechaActual.getTime());// OJO DEBERÍA DE LLEGAR COMO PARÁMETRO
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.COD_ETAPA_TERMINADO);// OJO DEBERÍA DE LLEGAR COMO
																						// PARÁMETRO
				beanSeguiWS.put("fec_cambeta", fechaActual.getTime());// OJO DEBERÍA DE LLEGAR COMO PARÁMETRO
				seguiService.registrarSeguimiento(beanSeguiWS);
			}

			// RECUPERAR DESCRIPCIÓN DEL PROCESO
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapParam);

			// RECUPERAR DESCRIPCIÓN DEL TIPO DE EXPEDIENTE
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapParam);

			// RECUPERAR DESCRIPCIÓN DE LOS TIPOS DE DOCUMENTOS
			Map<String, Object> mapaTiposDocumentos = new HashMap<String, Object>();

			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapaTiposDocumentos.putAll(catalogoService.obtenerCatalogo(mapParam));

			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapaTiposDocumentos.putAll(catalogoService.obtenerCatalogo(mapParam));

			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapaTiposDocumentos.putAll(catalogoService.obtenerCatalogo(mapParam));

			// RECUPERAR DATOS DEL CONTRIBUYENTE
			DdpBean contribuyente = validarParametrosService.validarRUC(parametros.get("numRuc").toString());
			String desRazonSocial = ValidaConstantes.CADENA_VACIA;
			if (!Utils.isEmpty(contribuyente.getNumRuc())) {
				desRazonSocial = contribuyente.getDesRazonSocial().trim();
			}

			// VERIFICAR SI SE GENENERA EL INDICE DE CIERRE
			if (ValidaConstantes.FILTRO_UNO.equals((String) parametros.get("indReporteIndice"))
					|| ValidaConstantes.FILTRO_DOS.equals((String) parametros.get("indReporteIndice"))) {
				// RECUPERAR DESCRIPCIÓN DEL ORIGEN DE DOCUMENTOS

				byte[] indiceVisibleContrib = null;
				byte[] indiceNoVisibleContrib = null;
				boolean indExpfisca = false;
				if (ValidaConstantes.TIPO_EXPE_CRUCE_INFORMACION.equals(parametros.get("codTipoExpediente").toString())
						|| ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP
								.equals(parametros.get("codTipoExpediente").toString())
						|| ValidaConstantes.TIPO_EXPE_FISCA_DEFINITIVA_PARCIAL
								.equals(parametros.get("codTipoExpediente").toString())) {
					Map<String, Object> mapIndices = this.GenerarIndiceFisca(mapaTiposExpedientes, parametros,
							mapaTiposDocumentos, mapaProcesos, desRazonSocial);
					indiceVisibleContrib = (byte[]) mapIndices.get("byteIndiceV");
					indiceNoVisibleContrib = (byte[]) mapIndices.get("byteIndiceNV");
					indExpfisca = true;
				} else {
					indiceVisibleContrib = this.GenerarIndiceGenerico(mapaTiposExpedientes, parametros,
							mapaTiposDocumentos, mapaProcesos, desRazonSocial);
				}

				Long numCorrelativoNV = null;
				String codIdecmCierreNV = null;

				Long numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
				String desArchivoPdf = numCorrelativo.toString() + ".pdf";

				// ENVIAR EL INDICE AL ECM
				mapParam = new HashMap<String, Object>();
				mapParam.put("nomArchivoSinExt", numCorrelativo);
				mapParam.put("numExpeDv", parametros.get("numExpedienteVirtual"));
				mapParam.put("numRuc", parametros.get("numRuc"));
				mapParam.put("numDoc", numCorrelativo);
				mapParam.put("fecEmi", fechaActual.getTime());
				mapParam.put("codTipExpVir", parametros.get("codTipoExpediente"));
				mapParam.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_INDICE_CIERRE);
				mapParam.put("archLength", indiceVisibleContrib.length);
				mapParam.put("archMimeType", "application/pdf");
				mapParam.put("archFileName", desArchivoPdf);
				mapParam.put("archContent", indiceVisibleContrib);
				mapParam.put("codDep", parametros.get("codDep"));
				mapParam.put("codDepOri", parametros.get("codDep"));
				String codIdecmCierre = ecmService.guardarDocumentoECM(mapParam);
				// String codIdecmCierre = "{0039875B-0000-C61B-9424-230B97A98E11}";//DESARROLLO
				// LOCAL QUITAR!!!
				if (indExpfisca) {
					numCorrelativoNV = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
					mapParam.put("nomArchivoSinExt", numCorrelativoNV);
					mapParam.put("numDoc", numCorrelativoNV);
					mapParam.put("archLength", indiceNoVisibleContrib.length);
					mapParam.put("archFileName", numCorrelativoNV.toString() + ".pdf");
					mapParam.put("archContent", indiceNoVisibleContrib);
					codIdecmCierreNV = ecmService.guardarDocumentoECM(mapParam);
				}

				// REGISTRAR EL INDICE EN LA TABLA DE DOCUMENTOS
				mapParam = new HashMap<String, Object>();
				mapParam.put("numCordoc", numCorrelativo);
				mapParam.put("numExpeDv", parametros.get("numExpedienteVirtual"));
				mapParam.put("codTipExp", parametros.get("codTipoExpediente"));
				mapParam.put("codTipdoc", ValidaConstantes.COD_TIPO_DOCU_INDICE_CIERRE);
				mapParam.put("numRequerim", null);
				mapParam.put("codEstDocReq", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("desMotSolDoc", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("numDoc", numCorrelativo);
				mapParam.put("fecDoc", fechaActual.getTime());
				mapParam.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_OTRO); // Por ahora
				mapParam.put("codUsuCarg", parametros.get("codUsuario"));
				mapParam.put("fecCarg", fechaActual.getTime());
				mapParam.put("codUsuRegis", parametros.get("codUsuario"));
				mapParam.put("fecRegis", fechaActual.getTime());
				mapParam.put("codOrigDoc", parametros.get("codOrigenDocumento"));
				mapParam.put("numCordocRel", new Integer(0));
				mapParam.put("indVisible", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);// PARA CIERRE ES POR DEFAULT 1
				mapParam.put("desArch", desArchivoPdf);
				mapParam.put("codIdecm", codIdecmCierre);
				// Inicio - [oachahuic][PAS20175E210400016]
				mapParam.put("fecNotif", fechaVacia.getTime());
				mapParam.put("codForNotif", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("codEstDoc", ValidaConstantes.COD_EST_DOC_REGISTRADO);
				mapParam.put("desEliDoc", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("fecModif", fechaVacia.getTime());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				// Fin - [oachahuic][PAS20175E210400016]
				t6613DocExpVirtDAO.insertar(mapParam);
				if (indExpfisca) {
					mapParam.put("numCordoc", numCorrelativoNV);
					mapParam.put("numDoc", numCorrelativoNV);
					mapParam.put("desArch", numCorrelativoNV.toString() + ".pdf");
					mapParam.put("codIdecm", codIdecmCierreNV);
					mapParam.put("indVisible", ValidaConstantes.IND_INVISIBLE_CONTRIBUYENTE);
					t6613DocExpVirtDAO.insertar(mapParam);
				}

				if (parametros.get("numCorDocCierre") != null) {// [PAS20181U210400241]
					// ACTUALIZAR EL INDICADOR DE SUSTENTO DEL DOCUMENTO DE CIERRE
					T6613DocExpVirtBean t6613BeanCierre = new T6613DocExpVirtBean();
					t6613BeanCierre.setNumCorDoc((Integer) parametros.get("numCorDocCierre"));
					t6613BeanCierre.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
					t6613BeanCierre.setCodUsuModif(parametros.get("codUsuario").toString());
					t6613BeanCierre.setFecModif(fechaActual.getTime());
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
					t6613DocExpVirtDAO.update(t6613BeanCierre);
				}
			}

			// ACTUALIZAR EL EXPEDIENTE VIRTUAL
			mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedienteVirtual", parametros.get("numExpedienteVirtual"));
			mapParam.put("codOrigenCierre", parametros.get("codOrigenCierre"));
			mapParam.put("codEstadoCierre", parametros.get("codEstadoCierre"));
			mapParam.put("desMotivoCierre", parametros.get("desMotivoCierre"));
			mapParam.put("desSumilla", parametros.get("desSumilla"));
			mapParam.put("codUsuarioCierre", parametros.get("codUsuario"));
			mapParam.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO);
			// PAS20191U210500144 Inicio 400101 RF04, RF13 PSALDARRIAGA
			if (parametros.get("fecCierre") != null) {
				mapParam.put("fecCierre", parametros.get("fecCierre"));
			} else {
				mapParam.put("fecCierre", fechaActual.getTime());
			}
			// PAS20191U210500144 Fin 400101 RF04, RF13 PSALDARRIAGA

			// Inicio - [oachahuic][PAS20175E210400016]
			mapParam.put("codUsuModif", parametros.get("codUsuario").toString());
			mapParam.put("fecModif", fechaActual.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6614ExpVirtualDAO.actualizar(mapParam);

			// VERIFICAR SI SE ENVÍA LA ALERTA A LOS RESPONSABLES DEL EXPEDIENTE
			if (ValidaConstantes.IND_EMI_ALERTA_SI.equals(parametros.get("indEmiAlerta").toString())) {
				// RECUPERAR CORREOS ELECTRONICOS DE LOS RESPONSABLES
				mapParam = new HashMap<String, Object>();
				mapParam.put("listCodPers", parametros.get("listaColaboradores"));
				List<CorreosBean> listaCorreosBeans = correosService.listarCorreo(mapParam);

				// ENVIAR LA ALERTA A LOS RESPONSABLES DEL EXPEDIENTE
				Map<String, Object> mapEnvioCorreo = null;
				String mensaje = "";
				for (CorreosBean correosBean : listaCorreosBeans) {
					mapEnvioCorreo = new HashMap<String, Object>();
					mapEnvioCorreo.put("destinatario", correosBean.getSmtp().trim());
					mensaje = MensajeAlertaConstantes.MSJ_ALERTA_CIE_EXP_VIRT.replace(
							ValidaConstantes.PATRON_CAMBIO_MENSAJES_0,
							parametros.get("numExpedienteOrigen").toString().trim());
					mapEnvioCorreo.put("mensaje", mensaje);
					correosService.enviarCorreo(mapEnvioCorreo);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtual");
			NDC.pop();
			NDC.remove();
		}
		return jsonResponse;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoCerrarExpedienteVirtual(Map<String, Object> parametros) throws Exception {

		Map<String, Object> beanSegui = new HashMap<String, Object>();
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", parametros.get("numExpedienteVirtual"));
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CIERRE_EXPEDIENTE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_CERRAR_EXPEDIENTE);
		beanSegui.put("des_datcons",
				Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_CERRAR_EXPEDIENTE)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
		seguiService.registrarSeguimiento(beanSegui);
	}
	// Fin [jquispe 27/05/2016]

	@Override
	public void cerrarExpedienteVirtualAcum(List<Map<String, Object>> listaParametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtualAcum");
		try {
			for (Map<String, Object> parametros : listaParametros) {
				this.cerrarExpedienteVirtual(parametros);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtualAcum");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.cerrarExpedienteVirtualAcum");
			NDC.pop();
			NDC.remove();
		}
	}

	@Override
	public List<Map<String, Object>> listarNumExpVirtualAcumCierre(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaAcumCierre = new ArrayList<Map<String, Object>>();

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listarNumExpVirtualAcumCierre");

		try {
			if (log.isDebugEnabled())
				log.debug("Procesa - ExpedienteVirtualServiceImpl.listarNumExpVirtualAcumCierre");

			listaAcumCierre = t6614ExpVirtualDAO.listarNumExpVirtualAcumCierre(parametros);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listarNumExpVirtualAcumCierre");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listarNumExpVirtualAcumCierre");

			NDC.pop();
			NDC.remove();
		}

		return listaAcumCierre;
	}

	@Override
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtual(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6614ExpVirtualBean> listT6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");

		try {

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaDependencia = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoCierreExpediente = catalogoService.obtenerCatalogo(mapa);

			// Inicio - [oachahuic][PAS20175E210400016]
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapa.put("codTipoExpediente", ValidaConstantes.GRUPO_TIP_DOC_CONST);
			Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);
			String strFecha = null;
			// Fin - [oachahuic][PAS20175E210400016]

			// inicio - [JEFFIO][13/03/2017]
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPO_DOCUMENTO_SUSTENTO);
			mapa.put("indtipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaDesFormaNotifi = catalogoService.obtenerCatalogo(mapa);

			// fin - [JEFFIO][13/03/2017]

			Map<String, Object> parametros;
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listarDocumentoExpediente(mapParametrosBusqueda);
			int i = 1;
			for (T6614ExpVirtualBean t6614ExpVirBean : listT6614ExpVirtualBean) {

				// Inicio [jquispe 10/06/2016]
				DdpBean beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirBean.getNumRuc());
				t6614ExpVirBean.setDesRazonSocial(
						!Utils.isEmpty(beanContribuyente) ? beanContribuyente.getDesRazonSocial().trim()
								: ValidaConstantes.CADENA_VACIA);
				// Inicio [jquispe 10/06/2016]

				t6614ExpVirBean.setNumExpedienteVirtual(t6614ExpVirBean.getNumExpedienteVirtual().trim());
				t6614ExpVirBean.setNumExpedienteOrigen(t6614ExpVirBean.getNumExpedienteOrigen().trim());
				t6614ExpVirBean.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(t6614ExpVirBean.getCodOrigen())));
				t6614ExpVirBean.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(t6614ExpVirBean.getCodEstado())));
				t6614ExpVirBean.setDesForNotifOrigen(
						Utils.toStr(mapaDesFormaNotifi.get(t6614ExpVirBean.getCodForNotifOrigen())));
				t6614ExpVirBean
						.setDesProceso(Utils.toStr(mapaProcesos.get(t6614ExpVirBean.getCodProceso())).toUpperCase());
				t6614ExpVirBean.setDesTipoExpediente(
						Utils.toStr(mapaTiposExpedientes.get(t6614ExpVirBean.getCodTipoExpediente())).toUpperCase());
				if (Utils.isEmpty(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())) || Utils.equiv(
						Utils.toStr(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())),
						ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesEstadoCierre("");
				} else {
					t6614ExpVirBean.setDesEstadoCierre(
							Utils.toStr(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())));
				}

				if (Utils.isEmpty(t6614ExpVirBean.getIndicadorAcumulado())
						|| Utils.equiv(t6614ExpVirBean.getIndicadorAcumulado(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesIndicadorAcumulado("");
				} else {
					if (Utils.equiv(t6614ExpVirBean.getIndicadorAcumulado(),
							ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE)) {
						t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_INDEPENDIENTE);
					} else {
						if (Utils.equiv(t6614ExpVirBean.getIndicadorAcumulado(),
								ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADO);
						} else {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADOR);
						}
					}
				}

				if (Utils.isEmpty(t6614ExpVirBean.getNumExpedienteAcumulador()) || Utils
						.equiv(t6614ExpVirBean.getNumExpedienteAcumulador(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setNumExpedienteAcumulador("0");
				} else {
					String sCadena = t6614ExpVirBean.getNumExpedienteAcumulador();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setNumExpedienteAcumulador("0");
					} else {
						t6614ExpVirBean.setNumExpedienteAcumulador(sCadena);
					}
				}

				if (Utils.equiv(t6614ExpVirBean.getIndicadorAcumulado(), ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)) {
					t6614ExpVirBean.setNumExpedienteAcumulador(t6614ExpVirBean.getNumExpedienteOrigen().trim());
				}

				if (Utils.isEmpty(t6614ExpVirBean.getCodRetornoServicio())
						|| Utils.equiv(t6614ExpVirBean.getCodRetornoServicio(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setCodRetornoServicio("0");
				} else {
					String sCadena = t6614ExpVirBean.getCodRetornoServicio();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setCodRetornoServicio("0");
					} else {
						t6614ExpVirBean.setCodRetornoServicio(sCadena);
					}
				}
				// Inicio - [oachahuic][PAS20165E210400270]
				// t6614ExpVirBean.setDesDependencia(desDep);
				// Fin - [oachahuic][PAS20165E210400270]
				parametros = new HashMap<String, Object>();
				parametros.put("codPersona", t6614ExpVirBean.getCodResponsable());
				parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);

				T02PerdpBean responsable = personaService.validarPersona(parametros);

				if (responsable != null) {
					responsable = personaService.completarInformacionPersona(responsable);
					t6614ExpVirBean.setNombreResponsable(responsable.getDesNombreCompleto());
				} else {
					t6614ExpVirBean.setNombreResponsable("");
				}
				// Inicio - [oachahuic][PAS20175E210400016]
				if (ValidaConstantes.SEPARADOR_GUION.equals(t6614ExpVirBean.getCodForNotifOrigen().trim())) {
					t6614ExpVirBean.setDesForNotifOrigen("");
				} else {
					t6614ExpVirBean.setDesForNotifOrigen(
							mapaTiposNotificaciones.get(t6614ExpVirBean.getCodForNotifOrigen()).toString());
				}
				strFecha = new SimpleDateFormat("yyyy-MM-dd").format(t6614ExpVirBean.getFecNotifOrigen());
				if (Utils.equiv(strFecha, ValidaConstantes.FECHA_VACIA)) {
					t6614ExpVirBean.setStrFecNotifOrigen("");
				} else {
					t6614ExpVirBean
							.setStrFecNotifOrigen(Utils.dateUtilToStringDDMMYYYY(t6614ExpVirBean.getFecNotifOrigen()));
				}
				// Fin - [oachahuic][PAS20175E210400016]

				t6614ExpVirBean.setNumOrden(i);

				// [PAS20191U210500291]: JMC - INI
				t6614ExpVirBean.setCodDependencia(t6614ExpVirBean.getCodDependencia().trim());
				// [PAS20191U210500291]: JMC - FIN
				i++;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");

			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}

	// Inicio [jquispe 27/05/2016] Obtener numero total de expediente virtual de la
	// consulta.
	@Override
	public Integer obtenerNumExpedienteVirtual(Map<String, Object> mapParametrosBusqueda) throws Exception {
		return t6614ExpVirtualDAO.obtenerNumDocumentoExpediente(mapParametrosBusqueda);
	}
	// Fin [jquispe 27/05/2016]

	@Override
	public List<T6614ExpVirtualBean> listarRegDocExpediente(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6614ExpVirtualBean> listT6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listarRegDocExpediente");

		try {
			String codigoProceso;
			String codigoTipoExpediente;
			String codigoOrigen;
			String codigoEstado;
			String codigoEstadoCierre;
			String codigoDependencia;
			String codIndicadorAcumulado;

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaDependencia = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoCierreExpediente = catalogoService.obtenerCatalogo(mapa);

			// INICIO[ATORRESCH 20170313] MAPA DE TIPO DE NOTIFICACIONES
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapa.put("codTipoExpediente", "803");
			Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);
			// INICIO[ATORRESCH 20170313]

			Map<String, Object> parametros;
			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listarRegDocExpediente(mapParametrosBusqueda);
			int i = 1;
			for (T6614ExpVirtualBean t6614ExpVirBean : listT6614ExpVirtualBean) {
				codigoProceso = t6614ExpVirBean.getCodProceso();
				codigoTipoExpediente = t6614ExpVirBean.getCodTipoExpediente();
				codigoOrigen = t6614ExpVirBean.getCodOrigen();
				codigoEstado = t6614ExpVirBean.getCodEstado();
				codigoEstadoCierre = t6614ExpVirBean.getCodEstadoCierre();
				codigoDependencia = t6614ExpVirBean.getCodDependencia();
				// Inicio - [oachahuic][PAS20165E210400270]
				// String desDep =
				// Utils.toStr(mapaDependencia.get(codigoDependencia)).trim().substring(ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA,
				// ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA);
				// Inicio - [oachahuic][PAS20165E210400270]
				codIndicadorAcumulado = t6614ExpVirBean.getIndicadorAcumulado();

				t6614ExpVirBean.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(codigoOrigen)));
				t6614ExpVirBean.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(codigoEstado)));
				t6614ExpVirBean.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
				t6614ExpVirBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));
				// INICIO LLRB
				t6614ExpVirBean.setCodTipoExpediente(Utils.toStr(t6614ExpVirBean.getCodTipoExpediente()));
				// FIN LLRB
				if (Utils.isEmpty(mapaEstadoCierreExpediente.get(codigoEstadoCierre))
						|| Utils.equiv(Utils.toStr(mapaEstadoCierreExpediente.get(codigoEstadoCierre)),
								ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesEstadoCierre("");
				} else {
					t6614ExpVirBean.setDesEstadoCierre(Utils.toStr(mapaEstadoCierreExpediente.get(codigoEstadoCierre)));
				}

				if (Utils.isEmpty(codIndicadorAcumulado)
						|| Utils.equiv(codIndicadorAcumulado, ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesIndicadorAcumulado("");
				} else {
					if (Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE)) {
						t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_INDEPENDIENTE);
					} else {
						if (Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADO);
						} else {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADOR);
						}
					}
				}

				if (Utils.isEmpty(t6614ExpVirBean.getNumExpedienteAcumulador()) || Utils
						.equiv(t6614ExpVirBean.getNumExpedienteAcumulador(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setNumExpedienteAcumulador("0");
				} else {
					String sCadena = t6614ExpVirBean.getNumExpedienteAcumulador();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setNumExpedienteAcumulador("0");
					} else {
						t6614ExpVirBean.setNumExpedienteAcumulador(sCadena);
					}
				}
				// Inicio [gangles 15/09/2016]Se agrega como número de expediente acumulador el
				// número de expediente origen
				if (Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)) {
					t6614ExpVirBean.setNumExpedienteAcumulador(t6614ExpVirBean.getNumExpedienteOrigen().trim());
				}
				// Fin [gangles 15/09/2016]

				if (Utils.isEmpty(t6614ExpVirBean.getCodRetornoServicio())
						|| Utils.equiv(t6614ExpVirBean.getCodRetornoServicio(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setCodRetornoServicio("0");
				} else {
					String sCadena = t6614ExpVirBean.getCodRetornoServicio();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setCodRetornoServicio("0");
					} else {
						t6614ExpVirBean.setCodRetornoServicio(sCadena);
					}
				}
				// Inicio - [oachahuic][PAS20165E210400270]
				// t6614ExpVirBean.setDesDependencia(desDep);
				// Inicio - [oachahuic][PAS20165E210400270]

				/*
				 * [ATORRESCH 2017-05-12] NO SE USABA NO SE USABA parametros = new
				 * HashMap<String, Object>(); parametros.put("codPersona",
				 * t6614ExpVirBean.getCodResponsable()); parametros.put("indHabilitado",
				 * ValidaConstantes.IND_REGI_SI_HABILITADO);
				 * 
				 * T02PerdpBean responsable = personaService.validarPersona(parametros);
				 * if(responsable != null) { responsable =
				 * personaService.completarInformacionPersona(responsable);
				 * t6614ExpVirBean.setNombreResponsable(responsable.getDesNombreCompleto()); }
				 * else { t6614ExpVirBean.setNombreResponsable(""); }
				 */

				// INICIO[ATORRESCH 20170313]
				if (ValidaConstantes.SEPARADOR_GUION.equals(t6614ExpVirBean.getCodForNotifOrigen().trim())) {
					t6614ExpVirBean.setDesForNotifOrigen("");
				} else {
					t6614ExpVirBean.setDesForNotifOrigen(
							mapaTiposNotificaciones.get(t6614ExpVirBean.getCodForNotifOrigen().trim()).toString());
				}

				if (Utils.esFechaVacia(t6614ExpVirBean.getFecNotifOrigen())) {
					t6614ExpVirBean.setStrFecNotifOrigen("");
				} else {
					t6614ExpVirBean
							.setStrFecNotifOrigen(Utils.dateUtilToStringDDMMYYYY(t6614ExpVirBean.getFecNotifOrigen()));
				}
				// FIN [ATORRESCH 20170313]

				t6614ExpVirBean.setNumOrden(i);
				i++;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listarRegDocExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listarRegDocExpediente");

			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}

	@Override
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtualAcumulado(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6614ExpVirtualBean> listT6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualAcumulado");

		try {
			String codigoProceso;
			String codigoTipoExpediente;
			String codigoOrigen;
			String codigoEstado;
			String codigoEstadoCierre;
			String codigoDependencia;
			String codIndicadorAcumulado;

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaDependencia = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoCierreExpediente = catalogoService.obtenerCatalogo(mapa);

			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listarExpedientesDelAcumulado(mapParametrosBusqueda);
			int i = 1;
			for (T6614ExpVirtualBean t6614ExpVirBean : listT6614ExpVirtualBean) {
				codigoProceso = t6614ExpVirBean.getCodProceso();
				codigoTipoExpediente = t6614ExpVirBean.getCodTipoExpediente();
				codigoOrigen = t6614ExpVirBean.getCodOrigen();
				codigoEstado = t6614ExpVirBean.getCodEstado();
				codigoEstadoCierre = t6614ExpVirBean.getCodEstadoCierre();
				codigoDependencia = t6614ExpVirBean.getCodDependencia();
				String desDep = Utils.toStr(mapaDependencia.get(codigoDependencia)).trim().substring(
						ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA, ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA);
				codIndicadorAcumulado = t6614ExpVirBean.getIndicadorAcumulado();

				t6614ExpVirBean.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(codigoOrigen)));
				t6614ExpVirBean.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(codigoEstado)));
				t6614ExpVirBean.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
				t6614ExpVirBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));
				if (Utils.isEmpty(mapaEstadoCierreExpediente.get(codigoEstadoCierre))
						|| Utils.equiv(Utils.toStr(mapaEstadoCierreExpediente.get(codigoEstadoCierre)),
								ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesEstadoCierre("");
				} else {
					t6614ExpVirBean.setDesEstadoCierre(Utils.toStr(mapaEstadoCierreExpediente.get(codigoEstadoCierre)));
				}

				if (Utils.isEmpty(codIndicadorAcumulado)
						|| Utils.equiv(codIndicadorAcumulado, ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesIndicadorAcumulado("");
				} else {
					if (Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE)) {
						t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_INDEPENDIENTE);
					} else {
						if (Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADO);
						} else {
							t6614ExpVirBean.setDesIndicadorAcumulado(ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADOR);
						}
					}
				}

				if (Utils.isEmpty(t6614ExpVirBean.getNumExpedienteAcumulador()) || Utils
						.equiv(t6614ExpVirBean.getNumExpedienteAcumulador(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setNumExpedienteAcumulador("0");
				} else {
					String sCadena = t6614ExpVirBean.getNumExpedienteAcumulador();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setNumExpedienteAcumulador("0");
					} else {
						t6614ExpVirBean.setNumExpedienteAcumulador(sCadena);
					}
				}

				if (Utils.isEmpty(t6614ExpVirBean.getCodRetornoServicio())
						|| Utils.equiv(t6614ExpVirBean.getCodRetornoServicio(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setCodRetornoServicio("0");
				} else {
					String sCadena = t6614ExpVirBean.getCodRetornoServicio();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setCodRetornoServicio("0");
					} else {
						t6614ExpVirBean.setCodRetornoServicio(sCadena);
					}
				}
				t6614ExpVirBean.setDesDependencia(desDep);

				t6614ExpVirBean.setNumOrden(i);
				i++;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualAcumulado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualAcumulado");

			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}

	@Override
	public List<Map<String, Object>> obtenerListaDocumentosAcumulado(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaT6613DocExpVirts = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listaT6613DocExpVirtsTemp = null;

		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> itemResult = null;
		String desTipdoc = null;
		Map<String, Object> mapParams = null;
		Map<String, Object> mapaTipoDocs = new HashMap<String, Object>();
		Map<String, Object> mapaOrigenDocumento = null;
		String temp = null;
		Date fecCarga = null;
		Date fecDocu = null;
		Map<String, Object> mapa = null;
		String desOrigenDoc = null;
		String codOrigenDoc = null;
		Map<String, Object> tempMapa = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaDocumentosAcumulado");

		try {
			SimpleDateFormat formatoFechaVista = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			mapParams = new HashMap<String, Object>();

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapParams);
			if (tempMapa != null) {
				mapaTipoDocs.putAll(tempMapa);
			}

			mapParams = new HashMap<String, Object>();

			mapParams.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
			mapParams.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			mapaOrigenDocumento = catalogoService.obtenerCatalogo(mapParams);

			parametros.put("orderby", "fec_carg");
			parametros.put("codEstDocReqNo", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			listaT6613DocExpVirtsTemp = t6613DocExpVirtDAO.listarDocumentosAcumulados(parametros);
			if (listaT6613DocExpVirtsTemp != null) {
				listaT6613DocExpVirts.addAll(listaT6613DocExpVirtsTemp);
			}

			parametros.remove("codEstDocReqNo");
			parametros.put("codEstDocReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			parametros.put("orderby", "fec_regis");
			listaT6613DocExpVirtsTemp = t6613DocExpVirtDAO.listarDocumentosAcumulados(parametros);
			if (listaT6613DocExpVirtsTemp != null) {
				listaT6613DocExpVirts.addAll(listaT6613DocExpVirtsTemp);
			}

			int i = 1;
			if (listaT6613DocExpVirts != null) {
				listaMapResult = new ArrayList<Map<String, Object>>();

				for (Map<String, Object> mapT6613 : listaT6613DocExpVirts) {
					itemResult = new HashMap<String, Object>();

					temp = (String) mapT6613.get("numExpeDo");
					temp = temp.trim();
					itemResult.put("numExpedo", temp);
					itemResult.put("numExpedv", mapT6613.get("numExpeDv"));

					String numAcumulador = (String) mapT6613.get("numAcumulador");
					numAcumulador = numAcumulador.trim().equals("0") ? "" : numAcumulador;
					itemResult.put("numAcumulador", numAcumulador);
					// Inicio [jquispe 27/05/2016] Numero de acumulador de la vista.
					if (Utils.isEmpty(numAcumulador) || "0".equals(numAcumulador)) {
						itemResult.put("numAcumuladorVista", temp);
					} else {
						itemResult.put("numAcumuladorVista", numAcumulador);
					}
					// Fin [jquispe 27/05/2016]
					temp = (String) mapT6613.get("numRuc");
					temp = temp.trim();
					itemResult.put("numRuc", temp);

					codOrigenDoc = mapT6613.get("codTipDoc").toString().trim();
					desTipdoc = (String) mapaTipoDocs.get(codOrigenDoc);

					itemResult.put("desTipdoc", codOrigenDoc + " " + desTipdoc);
					// Inicio [jquispe 30/05/2016] Campos para generar el archivo pdf con indice de
					// expediente electronico.
					itemResult.put("codTipDoc", codOrigenDoc);
					itemResult.put("desTipdoc2", desTipdoc);
					// Fin [jquispe 30/05/2016]

					temp = (String) mapT6613.get("numDoc");
					temp = temp.trim();
					itemResult.put("numDoc", temp);

					temp = (String) mapT6613.get("descArchivo");
					temp = temp.trim();
					itemResult.put("descArchivo", temp);

					temp = (String) mapT6613.get("codIdecm");
					temp = temp.trim();
					itemResult.put("codIdecm", temp);

					temp = (String) mapT6613.get("cod_tipdocsust");
					temp = temp.trim();
					itemResult.put("cod_tipdocsust", temp);

					fecCarga = (Date) mapT6613.get("fecCarg");
					if (Utils.esFechaVacia(fecCarga)) {
						itemResult.put("fecCarg", null);
						// Inicio [jquispe 27/05/2016] Fecha de carga con formato
						itemResult.put("fecCargVista", ValidaConstantes.SEPARADOR_ESPACIO);
						// Fin [jquispe 27/05/2016]
					} else {
						itemResult.put("fecCarg", fecCarga);
						// Inicio [jquispe 27/05/2016] Fecha de carga con formato
						itemResult.put("fecCargVista", formatoFechaVista.format(fecCarga));
						// Fin [jquispe 27/05/2016]
					}

					fecDocu = (Date) mapT6613.get("fecDocumento");

					if (Utils.esFechaVacia(fecDocu)) {
						itemResult.put("fecDocu", null);
						itemResult.put("fechaVista", ValidaConstantes.SEPARADOR_ESPACIO);
					} else {
						itemResult.put("fecDocu", fecDocu);
						itemResult.put("fechaVista", formatoFechaVista.format(fecDocu));
					}

					temp = (String) mapT6613.get("codUsuCarg");
					temp = temp.trim();

					itemResult.put("codUsuCarg", temp);

					mapa = new HashMap<String, Object>();
					mapa.put("codPersona", temp);
					if (temp.equals("-")) {
						itemResult.put("nomPersonaCargo", ValidaConstantes.CADENA_VACIA);

					} else {
						T02PerdpBean personaBean = personaService.validarPersona(mapa);
						if (personaBean != null) {
							personaBean = personaService.completarInformacionPersona(personaBean);
						}

						if (personaBean != null) {
							itemResult.put("nomPersonaCargo", personaBean.getDesNombreCompleto());
						} else {
							itemResult.put("nomPersonaCargo", ValidaConstantes.CADENA_VACIA);
						}
					}

					desOrigenDoc = (String) mapaOrigenDocumento.get(mapT6613.get("codOrigen"));

					itemResult.put("desOrigen", desOrigenDoc);

					temp = (String) mapT6613.get("codIdecm");
					temp = temp.trim();
					itemResult.put("codIdecm", temp);
					if (Utils.isEmpty(mapT6613.get("nroRequerim")) || Utils
							.equiv(Utils.toStr(mapT6613.get("nroRequerim")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("nroRequerim", ValidaConstantes.CADENA_VACIA);
					} else {
						String nroRequerimStr = (String) mapT6613.get("nroRequerim");

						if (nroRequerimStr != null) {
							itemResult.put("nroRequerim", nroRequerimStr.trim());
						} else {
							itemResult.put("nroRequerim", "");
						}
					}

					if (Utils.isEmpty(mapT6613.get("estadoDocumentoReq")) || Utils
							.equiv(Utils.toStr(mapT6613.get("estadoDocumentoReq")), ValidaConstantes.SEPARADOR_GUION)) {
						itemResult.put("estadoDocumentoReq", ValidaConstantes.CADENA_VACIA);
					} else {
						temp = (String) mapT6613.get("estadoDocumentoReq");
						temp = temp.trim();
						itemResult.put("estadoDocumentoReq", temp);
					}

					itemResult.put("codTipExp", mapT6613.get("codTipExp"));
					itemResult.put("numCorDoc", mapT6613.get("numCorDoc"));

					itemResult.put("numOrden", i);
					i++;
					listaMapResult.add(itemResult);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaDocumentosAcumulado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaDocumentosAcumulado");

			NDC.pop();
			NDC.remove();
		}
		return listaMapResult;
	}

	@Override
	public List<T6613DocExpVirtBean> listarTipoDocPorExpe(Map<String, Object> mapParametrosBusqueda) throws Exception {
		List<T6613DocExpVirtBean> listT6613DocVirtualBean = null;
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listarTipoDocPorExpe");

		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarTipoDocPorExpe");

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposDocumentos = catalogoService.obtenerCatalogo(mapa);

			listT6613DocVirtualBean = t6613DocExpVirtDAO.listarTipoDocPorExpe(mapParametrosBusqueda);

			for (T6613DocExpVirtBean tipoDocExpedBean : listT6613DocVirtualBean) {

				String desTipoDocumento = ValidaConstantes.CADENA_VACIA;

				String codTipoDocumento = tipoDocExpedBean.getCodTipDoc().toString().trim();
				tipoDocExpedBean.getIndVisDocumento();
				if (mapaTiposDocumentos.get(codTipoDocumento) != null) {

					desTipoDocumento = mapaTiposDocumentos.get(codTipoDocumento).toString();

				}

				tipoDocExpedBean.setDesTipdoc(desTipoDocumento);

				String desTipoDocumentoCompuesto = "";
				if (Utils.isEmpty(tipoDocExpedBean.getNumDoc())) {
					desTipoDocumentoCompuesto = desTipoDocumento;
				} else {
					desTipoDocumentoCompuesto = desTipoDocumento + ValidaConstantes.SEPARADOR_ESPACIO
							+ ValidaConstantes.SEPARADOR_GUION + ValidaConstantes.SEPARADOR_ESPACIO
							+ tipoDocExpedBean.getNumDoc();
				}

				tipoDocExpedBean.setDesNombreCompuesto(desTipoDocumentoCompuesto);

			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listarTipoDocPorExpe");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listarTipoDocPorExpe");

			NDC.pop();
			NDC.remove();
		}
		return listT6613DocVirtualBean;
	}

	private boolean validarHabilAlertaProceso(String codProceso) throws Exception {
		boolean valido = true;
		Map<String, Object> mapProcesoNoHabil = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.validarHabilAlertaProceso");

			mapProcesoNoHabil = new HashMap<String, Object>();
			mapProcesoNoHabil.put("codClase", CatalogoConstantes.CATA_PROCESO_NO_ALERTAS);
			mapProcesoNoHabil.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			// mapProcesoNoHabil = catalogoService.obtenerCatalogo(mapProcesoNoHabil);
			mapProcesoNoHabil = new HashMap<String, Object>();
			mapProcesoNoHabil.put(ValidaConstantes.COD_PROCESO_COBRANZA_NO_ALERTA,
					ValidaConstantes.COD_PROCESO_COBRANZA_NO_ALERTA);

			if (mapProcesoNoHabil != null && mapProcesoNoHabil.get(codProceso) != null) {
				valido = false;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.validarHabilAlertaProceso");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.validarHabilAlertaProceso");

			NDC.pop();
			NDC.remove();
		}

		return valido;
	}

	public Integer obtenerIdDocumento(Map<String, Object> parametros) throws Exception {
		Integer numCorDoc = null;
		List<Map<String, Object>> listaDocExpVirts = null;
		Map<String, Object> mapT6613 = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerNumDocRelDocumento");

			listaDocExpVirts = t6613DocExpVirtDAO.listarDocumentosPorExp(parametros);
			if (listaDocExpVirts != null && listaDocExpVirts.size() == 1) {
				mapT6613 = listaDocExpVirts.get(0);
				numCorDoc = (Integer) mapT6613.get("numCorDoc");
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerNumDocRelDocumento");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerNumDocRelDocumento");

			NDC.pop();
			NDC.remove();
		}

		return numCorDoc;
	}

	@Override
	public Map<String, Object> actualizarDocumentoVisible(Map<String, Object> parametros) throws Exception {
		Calendar fechaActual = null;
		JsonSerializer serializer = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		Calendar fechaVacia = null;
		String jsonResponse = null;
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		Map<String, Object> mapDocumento = null;
		String flagOrigen = null;
		Map<String, Object> mapaPool = null; // 13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE
												// (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");

		try {
			// Fecha actual
			fechaActual = Calendar.getInstance();

			// Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			mapDocumento = new HashMap<String, Object>();
			t6613DocExpVirtBean.setNumCorDoc((Integer) parametros.get("numCorDoc"));
			t6613DocExpVirtBean.setIndVisDocumento((String) parametros.get("indVisible"));
			// Inicio - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuario").toString());
			t6613DocExpVirtBean.setFecModif(fechaActual.getTime());
			// Fin - [oachahuic][PAS20175E210400016]

			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6613DocExpVirtDAO.update(t6613DocExpVirtBean);

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", parametros.get("numExpedv"));

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				// Setear el map del request de seguimiento
				mapRequest = new LinkedHashMap<String, Object>();

				mapRequest.put("numExpedo", parametros.get("numExpedo"));
				mapRequest.put("codTipdoc", parametros.get("codTipdoc"));
				mapRequest.put("numDoc", parametros.get("numDoc"));
				mapRequest.put("codUsuario", parametros.get("codUsuario"));
				mapRequest.put("indVisible", parametros.get("indVisible"));

				// Crear el bean de seguimiento WS
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("num_expedv", parametros.get("numExpedv"));
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_ACTUALIZA_DOC_VISIBLE);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuario"));
				serializer = new JsonSerializer();
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				jsonResponse = (String) serializer.serialize(mapResponse);
				beanSeguiWS.put("des_response", jsonResponse);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);

				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());

				// beanSeguiWS.put("origenDatos", origenDatos);

				seguiService.registrarSeguimiento(beanSeguiWS);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");

			NDC.pop();
			NDC.remove();
		}

		return mapResponse;
	}

	@Override
	public Map<String, Object> actualizarDocumentoVisibleRelacionado(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.actualizarDocumentoVisibleRelacionado");

		T6613DocExpVirtBean t6613DocExpVirtBean = null;

		try {
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			t6613DocExpVirtBean.setNumCorDoc(new Integer(parametros.get("numCorDoc").toString()));
			t6613DocExpVirtBean.setIndVisDocumento((String) parametros.get("indVisDocumento"));

			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6613DocExpVirtDAO.update(t6613DocExpVirtBean);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.actualizarDocumentoVisibleRelacionado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.actualizarDocumentoVisibleRelacionado");

			NDC.pop();
			NDC.remove();
		}
		return null;
	}

	@Override
	public Map<String, Object> reAbrirExpediente(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapParamExpAct = null;
		T6613DocExpVirtBean t6613DocExpVirtBean = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		JsonSerializer serializer = null;
		String fecha = null;
		Calendar fechaActual = null;
		Calendar fechaVacia = null;
		String formatoFechaReq = "dd-MM-yyyy";
		String numExpedv = null;
		String codEstexpori = null;
		String codEtaexpori = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.reAbrirExpediente");

		try {
			// Fecha actual
			fechaActual = Calendar.getInstance();

			// ReAbrir el expediente
			mapParamExpAct = new HashMap<String, Object>();
			numExpedv = (String) parametros.get("numExpeDv");
			mapParamExpAct.put("numExpedienteVirtual", numExpedv);
			mapParamExpAct.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParamExpAct.put("codOrigenCierre", ValidaConstantes.SEPARADOR_GUION);
			mapParamExpAct.put("codEstadoCierre", ValidaConstantes.SEPARADOR_GUION);
			// Inicio - [oachahuic][PAS20175E210400016]
			mapParamExpAct.put("codUsuModif", parametros.get("codUsuregis").toString());
			mapParamExpAct.put("fecModif", fechaActual.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6614ExpVirtualDAO.actualizar(mapParamExpAct);

			// Actualizar el documento de cierre a un tipo Otros
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			t6613DocExpVirtBean.setNumCorDoc((Integer) parametros.get("numCorDoc"));
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			// Inicio - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuregis").toString());
			t6613DocExpVirtBean.setFecModif(fechaActual.getTime());

			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6613DocExpVirtDAO.update(t6613DocExpVirtBean);

			// Seguimiento de la ReApertura del Exp.Virtual
			serializer = new JsonSerializer();

			// Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			mapRequest = new LinkedHashMap<String, Object>();
			mapRequest.put("numExpedo", parametros.get("numExpedo"));

			codEstexpori = (String) parametros.get("codEstexpori");
			if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEstexpori = ValidaConstantes.CADENA_VACIA;
			}

			codEtaexpori = (String) parametros.get("codEtaexpori");
			if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEtaexpori = ValidaConstantes.CADENA_VACIA;
			}

			mapRequest.put("codEstexpori", codEstexpori);
			mapRequest.put("codEtaexpori", codEtaexpori);
			mapRequest.put("codUsuregis", parametros.get("codUsuregis"));
			mapRequest.put("codTipexp", parametros.get("codTipexp"));
			mapRequest.put("codTipdoc", parametros.get("codTipdoc"));
			mapRequest.put("numDoc", parametros.get("numDoc"));

			fecha = Utils.convertirDateToString((Date) parametros.get("fecDoc"), formatoFechaReq);
			mapRequest.put("fecDoc", fecha);

			mapRequest.put("numDoc", parametros.get("numDoc"));
			mapRequest.put("fileExtension", parametros.get("fileExtension"));
			mapRequest.put("codIdecm", parametros.get("codIdecm"));
			mapRequest.put("desArch", parametros.get("desArch"));
			mapRequest.put("metadata", parametros.get("metadata"));
			mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", numExpedv);

			beanSeguiWS = new HashMap<String, Object>();
			beanSeguiWS.put("num_expedv", numExpedv);
			beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
			beanSeguiWS.put("fec_seguim", fechaActual.getTime());
			beanSeguiWS.put("fec_invserv", fechaActual.getTime());
			beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_REAPERTURA_EXP_VIRT);
			beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
			beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
			beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
			beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
			beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("fec_cons", fechaVacia.getTime());
			beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
			beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());

			seguiService.registrarSeguimiento(beanSeguiWS);

			// Trazabilidad
			if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
					|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				beanSeguiWS = new HashMap<String, Object>();

				beanSeguiWS.put("num_expedv", numExpedv);
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);

				beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
				beanSeguiWS.put("num_doc", parametros.get("numDoc"));

				if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS.put("cod_estexpori", codEstexpori);
					beanSeguiWS.put("fec_cambest", fechaActual.getTime());
				} else {
					beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				}

				if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS.put("cod_etaexpori", codEtaexpori);
					beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
				} else {
					beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				}

				seguiService.registrarSeguimiento(beanSeguiWS);
			}

			// Adjuntar el documento de reapertura
			parametros.put("flagOrigen", ValidaConstantes.IND_INVOC_SERVICIO_MANUAL);
			this.registrarDocumentosExpediente(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.reAbrirExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.reAbrirExpediente");

			NDC.pop();
			NDC.remove();
		}

		return mapResponse;
	}

	@Override
	public T6613DocExpVirtBean obtenerDatosDocumentoEstado(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapDocExpVirt = null;
		T6613DocExpVirtBean t6613DocExpVirtBean = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoEstado");

			mapDocExpVirt = new HashMap<String, Object>();
			mapDocExpVirt.put("codTipDocSust", parametros.get("codTipDocSust"));
			mapDocExpVirt.put("numExpeDv", parametros.get("numExpeDv"));
			t6613DocExpVirtBean = t6613DocExpVirtDAO.obtenerDocOrigen(mapDocExpVirt);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoEstado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDatosDocumentoEstado");

			NDC.pop();
			NDC.remove();
		}

		return t6613DocExpVirtBean;
	}

	public List<T6613DocExpVirtBean> obtenerListaDocVisContribuyenteAcumulado(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6613DocExpVirtBean> listT6613DocVirtualBean = new ArrayList<T6613DocExpVirtBean>();
		List<T6613DocExpVirtBean> listT6613DocVirtualBeanTemp = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyenteAcumulado");

		try {
			String codigoTipoDoc;
			String codigoOrigenDoc;
			String nroRequerim;
			String codEstDocRequerido;
			String fecEmiDoc;
			String vacio = "";
			String numeroDoc;
			String fechaReg;

			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<String, Object> mapaTipoDocumento = new HashMap<String, Object>();
			Map<String, Object> tempMapa = null;

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			if (tempMapa != null) {
				mapaTipoDocumento.putAll(tempMapa);
			}

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			if (tempMapa != null) {
				mapaTipoDocumento.putAll(tempMapa);
			}

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			tempMapa = catalogoService.obtenerCatalogo(mapa);
			if (tempMapa != null) {
				mapaTipoDocumento.putAll(tempMapa);
			}

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_DOCUMENTO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenDoc = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_DOCUMENTO_REQUERIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstDocRequerido = catalogoService.obtenerCatalogo(mapa);

			mapParametrosBusqueda.put("codEstDocReqNo", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			mapParametrosBusqueda.put("orderby", "fec_carg");
			listT6613DocVirtualBeanTemp = t6613DocExpVirtDAO
					.listarDocVisiblesContribuyenteAcumulados(mapParametrosBusqueda);
			if (listT6613DocVirtualBeanTemp != null) {
				listT6613DocVirtualBean.addAll(listT6613DocVirtualBeanTemp);
			}

			mapParametrosBusqueda.remove("codEstDocReqNo");
			mapParametrosBusqueda.put("codEstDocReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			mapParametrosBusqueda.put("orderby", "fec_regis");
			listT6613DocVirtualBeanTemp = t6613DocExpVirtDAO
					.listarDocVisiblesContribuyenteAcumulados(mapParametrosBusqueda);
			if (listT6613DocVirtualBeanTemp != null) {
				listT6613DocVirtualBean.addAll(listT6613DocVirtualBeanTemp);
			}

			int i = 1;
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocVirtualBean) {
				codigoTipoDoc = t6613DocExpVirtBean.getCodTipDoc();
				codigoOrigenDoc = t6613DocExpVirtBean.getCodOrigenDocuento();
				codEstDocRequerido = t6613DocExpVirtBean.getEstadoDocumentoReq();
				fecEmiDoc = Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFechaCarga());// [PAS20191U210500076][OAC]

				if (!Utils.isEmpty(t6613DocExpVirtBean.getNumRequerimiento())) {
					nroRequerim = Utils.toStr(t6613DocExpVirtBean.getNumRequerimiento());
				} else {
					nroRequerim = vacio;
				}
				String sCadena = t6613DocExpVirtBean.getEstadoDocumentoReq();
				sCadena = sCadena.replace(" ", "");

				if (sCadena.equals("-")) {
					codEstDocRequerido = vacio;
				} else {
					codEstDocRequerido = Utils.toStr(mapaEstDocRequerido.get(sCadena));
					t6613DocExpVirtBean.setDesEstDocRequerido(codEstDocRequerido);
				}

				sCadena = t6613DocExpVirtBean.getCodIdentificadorECM();
				sCadena = sCadena.replace(" ", "");

				if (sCadena.equals("-")) {
					t6613DocExpVirtBean.setCodIdentificadorECM(vacio);
				}

				sCadena = t6613DocExpVirtBean.getNumDoc();
				sCadena = sCadena.replace(" ", "");

				if (sCadena.equals("-")) {
					numeroDoc = vacio;
				} else {
					numeroDoc = t6613DocExpVirtBean.getNumDoc();
				}

				if (fecEmiDoc.equals("01/01/1")) {
					fecEmiDoc = "";
					t6613DocExpVirtBean.setFechaDocumento(fecEmiDoc);
				} else {
					t6613DocExpVirtBean.setFechaDocumento(fecEmiDoc);
				}

				if (t6613DocExpVirtBean.getDescripcionArchivo() == null) {
					t6613DocExpVirtBean.setDescripcionArchivo("-");
				}
				fechaReg = Utils.dateUtilToStringDDMMYYYY(t6613DocExpVirtBean.getFecRegis());
				t6613DocExpVirtBean.setNroRequerim(nroRequerim);
				t6613DocExpVirtBean.setDesTipdoc(Utils.toStr(mapaTipoDocumento.get(codigoTipoDoc)));
				t6613DocExpVirtBean.setDesOrigenDoc(Utils.toStr(mapaOrigenDoc.get(codigoOrigenDoc)));
				t6613DocExpVirtBean.setFechaRegistroDoc(fechaReg);
				t6613DocExpVirtBean.setNumDoc(numeroDoc);
				t6613DocExpVirtBean.setCorrelativo(i);
				i++;

				// Inicio [jquispe 13/06/2016] Numero de acumulador de la vista.
				String numAcumulador = t6613DocExpVirtBean.getNumAcumulador().trim().equals("0") ? ""
						: t6613DocExpVirtBean.getNumAcumulador();
				t6613DocExpVirtBean.setNumAcumulador(numAcumulador);

				if (Utils.isEmpty(numAcumulador) || "0".equals(numAcumulador)) {
					t6613DocExpVirtBean.setNumAcumuladorVista(t6613DocExpVirtBean.getCodOrigenExpe());
				} else {
					t6613DocExpVirtBean.setNumAcumuladorVista(numAcumulador);
				}
				// Fin [jquispe 13/06/2016]
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyenteAcumulado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaDocVisContribuyenteAcumulado");

			NDC.pop();
			NDC.remove();
		}
		return listT6613DocVirtualBean;
	}

	@Override
	public List<String> listarNumExpAcumCOA(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaMapNumExpAcumCOA = null;
		Map<String, Object> mapParamNumExpVirtual = null;
		List<String> listaExpAcumulados = null;
		List<T6614ExpVirtualBean> listaT6614ExpVirtualBean = null;
		T6614ExpVirtualBean t6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listarNumExpAcumCOA");

		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT
					+ parametros.get("coddependencia").toString().trim());
			listaMapNumExpAcumCOA = expCoaDAO.listarAcumuladosDoc(parametros);
			if (listaMapNumExpAcumCOA != null & listaMapNumExpAcumCOA.size() > 0) {
				listaExpAcumulados = new ArrayList<String>();

				for (Map<String, Object> mapNumExpAcumCOA : listaMapNumExpAcumCOA) {
					mapParamNumExpVirtual = new HashMap<String, Object>();
					mapParamNumExpVirtual.put("numExpedo", mapNumExpAcumCOA.get("numExpCoa").toString().trim());
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
					listaT6614ExpVirtualBean = t6614ExpVirtualDAO.listar(mapParamNumExpVirtual);

					if (listaT6614ExpVirtualBean != null && listaT6614ExpVirtualBean.size() > 0) {
						t6614ExpVirtualBean = listaT6614ExpVirtualBean.get(0);
						listaExpAcumulados.add(t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
					} else {
						log.info("El número de exp. origen no existe en la bd de expediente virtual");
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listarNumExpAcumCOA");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listarNumExpAcumCOA");
		}
		return listaExpAcumulados;
	}

	// Inicio [jjurado 27/05/2016]
	public byte[] verDatosExpedientePDF(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - verDatosExpedientePDF.descargarDocumento");
		}

		byte[] binDoc = null;
		String json = Utils.toStr(parametros.get("json"));
		try {

			int firmaPdf = 0;
			int modeloPdf = 1000;
			Integer idDocumento = Utils.toInteger(parametros.get("codPDF"));
			/* Generar el Pdf */

			Service servicioAxis = new Service();
			Call generaPDF = (Call) servicioAxis.createCall();

			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));

			generaPDF.setOperationName("generar");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(byte[].class);
			// generaPDF.setReturnClass(int.class);

			binDoc = (byte[]) (generaPDF.invoke(new Object[] { idDocumento, json, "pdf", modeloPdf, firmaPdf }));
			// Integer numeroPdf = Utils.toInteger(generaPDF.invoke(new Object[] {
			// idDocumento, datos, "datos", modeloPdf, firmaPdf }));

			// byte[] binDoc = ObtenerPDFUnload(numeroPdf);

			// String desArchivoPdf = ExportaConstantes.NOMBRE_PLANTILLA_PDF_002_01 +
			// ".pdf";
			// String desArchivoPdf = numeroPdf.toString() + ".pdf";
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraExpedienteVirtualController.verDatosExpedientePDF");
			}

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraExpedienteVirtualController.verDatosExpedientePDF");
			}
			NDC.pop();
			NDC.remove();
		}

		return binDoc;
	}

	@Override
	public T6613DocExpVirtBean buscarDocumentoCierre(Map<String, Object> parametroBusqueda) throws Exception {
		// TODO Auto-generated method stub
		return t6613DocExpVirtDAO.obtenerDocumentoCierre(parametroBusqueda);
	}
	// Fin [jjurado 27/05/2016]

	// Inicio [eaguilar 24/05/2015]
	@Override
	public boolean desasociarAcum(Map<String, Object> parametros, List<String> listaNumExpAcumCOA) throws Exception {
		boolean seActualizo = false;
		Map<String, Object> mapParamActualizaAcum = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");

		try {
			// 22 JUN: correcciones eaguilar se envia numexepdo en vez de virtual para q
			// realice la disociacion
			// Inicio nchavez 07/07/2016
			if (Utils.isEmpty(listaNumExpAcumCOA)) {
				mapParamActualizaAcum = new HashMap<String, Object>();
				mapParamActualizaAcum.put("numAcu", "0");
				mapParamActualizaAcum.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE);
				// Inicio - [oachahuic][PAS20175E210400016]
				mapParamActualizaAcum.put("numExpedienteVirtual", parametros.get("numExpeDv").toString());
				mapParamActualizaAcum.put("codUsuModif", parametros.get("codUsuregis").toString());
				mapParamActualizaAcum.put("fecModif", Calendar.getInstance().getTime());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				// Fin - [oachahuic][PAS20175E210400016]
				t6614ExpVirtualDAO.actualizar(mapParamActualizaAcum);
			}

			mapParamActualizaAcum = new HashMap<String, Object>();
			mapParamActualizaAcum.put("numAcu", "0");
			mapParamActualizaAcum.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE);
			mapParamActualizaAcum.put("listaNumExpevirNotIn", listaNumExpAcumCOA);
			mapParamActualizaAcum.put("numAcuW", parametros.get("numExpedo"));
			// Inicio - [oachahuic][PAS20175E210400016]
			mapParamActualizaAcum.put("codUsuModif", parametros.get("codUsuregis").toString());
			mapParamActualizaAcum.put("fecModif", Calendar.getInstance().getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t6614ExpVirtualDAO.actualizar(mapParamActualizaAcum);
			// Fin nchavez 07/07/2016
			seActualizo = true;

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.actualizarDocumentoVisible");
			NDC.pop();
			NDC.remove();
		}
		return seActualizo;
	}
	// Fin [eaguilar 24/05/2015]

	// Inicio [eaguilar 24/05/2015]
	@Override
	public Long generarCorrelativo(String codSeq) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.generarCorrelativo");
		Long numSeq = null;
		try {
			numSeq = sequenceDAO.getNextSequence(codSeq);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.generarCorrelativo");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.generarCorrelativo");
			NDC.pop();
			NDC.remove();
		}
		return numSeq;
	}
	// Fin [eaguilar 24/05/2015]

	// Inicio gangles 16/09/2016
	@Override
	public T6613DocExpVirtBean buscarDatosDocumentoCierre(Map<String, Object> parametroBusqueda) throws Exception {
		// TODO Auto-generated method stub
		return t6613DocExpVirtDAO.obtenerDatosDocumentoCierre(parametroBusqueda);
	}

	// Fin gangles 16/09/2016
	@Override
	public List<T6613DocExpVirtBean> listarDocExp(Map<String, Object> parametroBusqueda) throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled())
			log.debug("Inicio - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

		List<T6613DocExpVirtBean> listaDocumentosExpediente = new ArrayList<T6613DocExpVirtBean>();

		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			listaDocumentosExpediente = t6613DocExpVirtDAO.listar(parametroBusqueda);

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			NDC.pop();
			NDC.remove();

		}

		return listaDocumentosExpediente;
	}

	// Fin [jjurado 27/05/2016]
	@SuppressWarnings("unchecked")
	// INICIO JEFFIO 22/02/2017
	@Override
	public String ReaperturaExpVirtual(Map<String, Object> parametros, Map<String, Object> parametros2)
			throws Exception {

		T6614ExpVirtualBean t6614ExpVirtualBean = null;
		HashMap<String, Object> mapDocumento = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSegui = null;
		T6613DocExpVirtBean t6613Bean = null;
		Calendar fechaVacia = null;
		Calendar fechaActual = null;
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.ReaperturaExpVirtual");
		{
			try {
				if (log.isDebugEnabled())
					log.debug("Procesa - ExpedienteVirtualServiceImpl.ReaperturaExpVirtual");

				Map<String, Object> mymap = this.registrarDocumentosExpediente(parametros2);

				if (!Utils.isEmpty(mymap)) {
					if (!Utils.isEmpty(mymap.get("codIdecm"))) {

						fechaActual = Calendar.getInstance();
						fechaVacia = Calendar.getInstance();
						fechaVacia.set(1, 0, 1, 0, 0, 0);
						t6614ExpVirtualBean = new T6614ExpVirtualBean();
						t6614ExpVirtualBean.setNumExpedienteVirtual(parametros.get("numExpedienteVirtual").toString());
						t6614ExpVirtualBean.setNumExpedienteOrigen(parametros.get("numExpedienteOrigen").toString());
						t6614ExpVirtualBean.setCodEstado("01");
						t6614ExpVirtualBean.setFecCierre(fechaVacia.getTime());
						t6614ExpVirtualBean.setCodOrigenCierre(ValidaConstantes.SEPARADOR_GUION);
						t6614ExpVirtualBean.setCodEstadoCierre(ValidaConstantes.SEPARADOR_GUION);
						t6614ExpVirtualBean.setDesMotivoCierre(ValidaConstantes.SEPARADOR_GUION);
						t6614ExpVirtualBean.setDesSumilla(ValidaConstantes.SEPARADOR_GUION);
						t6614ExpVirtualBean.setCodUsuarioCierre(ValidaConstantes.SEPARADOR_GUION);
						t6614ExpVirtualBean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
						t6614ExpVirtualBean.setFecModif(new Date());

						mapDocumento = new HashMap<String, Object>();
						mapDocumento.put("t6614ExpVirtualBean", t6614ExpVirtualBean);

						DataSourceContextHolder
								.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
						t6614ExpVirtualDAO.ReaperturaExpVirtual(mapDocumento);

						// REGISTO DE TRAZABILIDAD
						// Inicio - [oachahuic][\PAS20181U210400241]
						String codEstexpori = (String) parametros2.get("codEstexpori");
						if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
							codEstexpori = ValidaConstantes.CADENA_VACIA;
						}
						String codEtaexpori = (String) parametros2.get("codEtaexpori");
						if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
							codEtaexpori = ValidaConstantes.CADENA_VACIA;
						}
						if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
								|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
							beanSegui = new HashMap<String, Object>();
							beanSegui.put("num_expedv", parametros.get("numExpedienteVirtual"));
							beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
							beanSegui.put("fec_seguim", fechaActual.getTime());
							beanSegui.put("fec_invserv", fechaVacia.getTime());
							beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("fec_cons", fechaVacia.getTime());
							beanSegui.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
							beanSegui.put("cod_tipdoc", parametros.get("codTipdoc"));
							beanSegui.put("num_doc", parametros.get("numDoc"));
							beanSegui.put("cod_estexpori", codEstexpori);
							beanSegui.put("fec_cambest", fechaActual.getTime());
							beanSegui.put("cod_etaexpori", codEtaexpori);
							beanSegui.put("fec_cambeta", fechaVacia.getTime());
							DataSourceContextHolder
									.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
							seguiService.registrarSeguimiento(beanSegui);
						}
						// Fin - [oachahuic][\PAS20181U210400241]

						if (parametros.get("numCorDocCierre") != null) {// [PAS20181U210400241]
							// ELIMINAR EL DOCUMENTO DE CIERRE
							t6613Bean = new T6613DocExpVirtBean();
							t6613Bean.setNumCorDoc(new Integer(Utils.toStr(parametros.get("numCorDocCierre"))));
							t6613Bean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
							t6613Bean.setDesEliDoc(ValidaConstantes.DES_ELI_DOC_REABRIR);
							t6613Bean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
							t6613Bean.setFecModif(new Date());
							DataSourceContextHolder
									.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
							t6613DocExpVirtDAO.update(t6613Bean);
						}

						// ENVIAR CORREO A RESPONSABLES
						for (CorreosBean bean : (List<CorreosBean>) parametros.get("listCorreos")) {
							Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
							String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_REA_EXP_VIRT.replace(
									ValidaConstantes.PATRON_CAMBIO_MENSAJES_0,
									parametros.get("numExpedienteOrigen").toString().trim());
							mapEnvioCorreo.put("mensaje", mensaje);
							correosService.enviarCorreo(mapEnvioCorreo);
						}
						mapResponse = new LinkedHashMap<String, Object>();
						mapResponse.put("status", true);
						mapResponse.put("message", AvisoConstantes.AVISO_MODULO_01_06_002);
					} else {
						mapResponse = new LinkedHashMap<String, Object>();
						mapResponse.put("status", false);
						mapResponse.put("message", "El codIdecm está vacio ");
					}
				} else {
					mapResponse = new LinkedHashMap<String, Object>();
					mapResponse.put("status", false);
					mapResponse.put("message", "No se pudo registrar el docuemnto");
				}

			} catch (Exception ex) {
				if (log.isDebugEnabled())
					log.debug("Error - ExpedienteVirtualServiceImpl.ReaperturaExpVirtual");
				log.error(ex, ex);

				mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("status", false);
				mapResponse.put("message", ex.getMessage());
				throw ex;
			} finally {
				if (log.isDebugEnabled())
					log.debug("Final - ExpedienteVirtualServiceImpl.ReaperturaExpVirtual");

				NDC.pop();
				NDC.remove();
			}
		}

		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);
		return jsonResponse;
	}

	public T6613DocExpVirtBean buscarDatosDocumentos(Map<String, Object> parametro) throws Exception {
		T6613DocExpVirtBean buscarDatoDocumento = new T6613DocExpVirtBean();
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - ExpedienteServiceImpl.buscarDatosDocumentos");

			mapa.put("numExpeDv", parametro.get("numexpediente").toString());
			mapa.put("codTipDocSust", parametro.get("tipodocsustento").toString());

			buscarDatoDocumento = t6613DocExpVirtDAO.obtenerDocOrigen(mapa);

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteServiceImpl.buscarDatosDocumentos");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteServiceImpl.buscarDatosDocumentos");

			NDC.pop();
			NDC.remove();

		}
		return buscarDatoDocumento;

	}

	public Integer NumDocRegisPorExpediente(Map<String, Object> parametrosBusqueda) throws Exception {
		Integer NumDocPorExp = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - ExpedienteServiceImpl.NumDocRegisPorExpediente");

			String numexpediente = parametrosBusqueda.get("numexpediente").toString();
			String tipodocsustento = parametrosBusqueda.get("tipodocsustento").toString();

			mapa.put("numExpedienteVir", numexpediente);
			mapa.put("codigoTipoDocumentoSust", tipodocsustento);

			NumDocPorExp = t6613DocExpVirtDAO.obtenerNumDocumentoXExpediente(mapa);

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteServiceImpl.NumDocRegisPorExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteServiceImpl.NumDocRegisPorExpediente");

			NDC.pop();
			NDC.remove();

		}
		return NumDocPorExp;
	}

	@SuppressWarnings("unchecked")
	public String EliminarExpVirtual(Map<String, Object> parametros) throws Exception {

		T6614ExpVirtualBean t6614ExpVirtualBean = null;
		HashMap<String, Object> mapDocumento = null;
		HashMap<String, Object> mapSeguimiento = null;
		Map<String, Object> mapResponse = null;
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.EliminarExpVirtual");
		{
			try {
				if (log.isDebugEnabled())
					log.debug("Procesa - ExpedienteVirtualServiceImpl.EliminarExpVirtual");

				// ELIMINAR ESTADO ETAPA
				mapSeguimiento = new HashMap<String, Object>();
				mapSeguimiento.put("numExpeDv", parametros.get("numExpedienteVirtual").toString());
				mapSeguimiento.put("codTipDoc", parametros.get("codTipoDocumento").toString());
				mapSeguimiento.put("numDoc", parametros.get("numDocumento").toString());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6622SeguimDAO.eliminarSeguimientoCambioEstadoEtapa(mapSeguimiento);

				T6613DocExpVirtBean t6613DocExpVirtBean = new T6613DocExpVirtBean();
				mapDocumento = new HashMap<String, Object>();

				t6613DocExpVirtBean.setNumExpedienteVir(parametros.get("numExpedienteVirtual").toString());
				t6613DocExpVirtBean.setCodigoTipoDocumentoSust("01");
				t6613DocExpVirtBean.setCodEstDoc("02");
				t6613DocExpVirtBean.setDesEliDoc(parametros.get("desEliExp").toString());
				t6613DocExpVirtBean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
				t6613DocExpVirtBean.setFecModif(new Date());

				mapDocumento = new HashMap<String, Object>();
				mapDocumento.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6613DocExpVirtDAO.Eliminar(mapDocumento);

				t6614ExpVirtualBean = new T6614ExpVirtualBean();
				t6614ExpVirtualBean.setNumExpedienteVirtual(parametros.get("numExpedienteVirtual").toString());
				t6614ExpVirtualBean.setCodEstado("03");
				t6614ExpVirtualBean.setDesEliExp(parametros.get("desEliExp").toString());
				t6614ExpVirtualBean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
				t6614ExpVirtualBean.setFecModif(new Date());

				mapDocumento = new HashMap<String, Object>();
				mapDocumento.put("t6614ExpVirtualBean", t6614ExpVirtualBean);

				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6614ExpVirtualDAO.EliminarExpVirtual(mapDocumento);

				// ENVIAR CORREO A RESPONSABLES
				for (CorreosBean bean : (List<CorreosBean>) parametros.get("listCorreos")) {
					Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
					mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
					String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ELI_EXP_VIRT.replace(
							ValidaConstantes.PATRON_CAMBIO_MENSAJES_0,
							parametros.get("numExpedienteOrigen").toString().trim());
					mapEnvioCorreo.put("mensaje", mensaje);
					correosService.enviarCorreo(mapEnvioCorreo);
				}

				mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("status", true);
				mapResponse.put("message", AvisoConstantes.AVISO_MODULO_01_07_002);

			} catch (Exception ex) {
				if (log.isDebugEnabled())
					log.debug("Error - ExpedienteVirtualServiceImpl.EliminarExpVirtual");
				log.error(ex, ex);

				mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("status", false);
				mapResponse.put("message", ex.getMessage());
				throw ex;
			} finally {
				if (log.isDebugEnabled())
					log.debug("Final - ExpedienteVirtualServiceImpl.EliminarExpVirtual");

				NDC.pop();
				NDC.remove();
			}
		}

		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);
		return jsonResponse;
	}

	@Override
	public String RevertirCierreExpVirtual(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.RevertirCierreExpVirtual");
		T6614ExpVirtualBean t6614ExpVirtualBean = null;
		HashMap<String, Object> mapDocumento = null;
		HashMap<String, Object> mapSeguimiento = null;
		Map<String, Object> mapResponse = null;
		T6613DocExpVirtBean t6613Bean = null;
		Calendar fechaVacia = null;
		try {
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			t6614ExpVirtualBean = new T6614ExpVirtualBean();
			t6614ExpVirtualBean.setNumExpedienteVirtual(parametros.get("numExpedienteVirtual").toString());
			t6614ExpVirtualBean.setNumExpedienteOrigen(parametros.get("numExpedienteOrigen").toString());
			t6614ExpVirtualBean.setCodEstado("01");
			t6614ExpVirtualBean.setCodOrigenCierre(ValidaConstantes.SEPARADOR_GUION);
			t6614ExpVirtualBean.setCodEstadoCierre(ValidaConstantes.SEPARADOR_GUION);
			t6614ExpVirtualBean.setDesMotivoCierre(ValidaConstantes.SEPARADOR_GUION);
			t6614ExpVirtualBean.setDesSumilla(ValidaConstantes.SEPARADOR_GUION);
			t6614ExpVirtualBean.setCodUsuarioCierre(ValidaConstantes.SEPARADOR_GUION);
			t6614ExpVirtualBean.setFecCierre(fechaVacia.getTime());
			t6614ExpVirtualBean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
			t6614ExpVirtualBean.setFecModif(new Date());

			mapDocumento = new HashMap<String, Object>();
			mapDocumento.put("t6614ExpVirtualBean", t6614ExpVirtualBean);

			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6614ExpVirtualDAO.ReaperturaExpVirtual(mapDocumento);

			if (parametros.get("numCorDocCierre") != null) {// [PAS20181U210400241]
				// ELIMINAR EL DOCUMENTO DE CIERRE
				t6613Bean = new T6613DocExpVirtBean();
				t6613Bean.setNumCorDoc(new Integer(Utils.toStr(parametros.get("numCorDocCierre"))));
				t6613Bean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
				t6613Bean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_ELIMINADO);
				t6613Bean.setDesEliDoc(ValidaConstantes.DES_ELI_DOC_REABRIR);
				t6613Bean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
				t6613Bean.setFecModif(new Date());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6613DocExpVirtDAO.update(t6613Bean);
			}

			// ELIMINAR LA CONSTANCIA DEL DOCUMENTO DE CIERRE
			if (parametros.get("numCorDocConst") != null) {
				t6613Bean = new T6613DocExpVirtBean();
				t6613Bean.setNumCorDoc(new Integer(Utils.toStr(parametros.get("numCorDocConst"))));
				t6613Bean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_ELIMINADO);
				t6613Bean.setDesEliDoc(ValidaConstantes.DES_ELI_DOC_REABRIR);
				t6613Bean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
				t6613Bean.setFecModif(new Date());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				t6613DocExpVirtDAO.update(t6613Bean);
			}

			// ELIMINAR EL INDICE
			t6613Bean = new T6613DocExpVirtBean();
			t6613Bean.setNumCorDoc(new Integer(Utils.toStr(parametros.get("numCorDocIndice"))));
			t6613Bean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_ELIMINADO);
			t6613Bean.setDesEliDoc(ValidaConstantes.DES_ELI_DOC_REABRIR);
			t6613Bean.setCodUsuModif(parametros.get("codUsuarioModifica").toString());
			t6613Bean.setFecModif(new Date());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6613DocExpVirtDAO.update(t6613Bean);

			// ELIMINAR ESTADO ETAPA
			mapSeguimiento = new HashMap<String, Object>();
			mapSeguimiento.put("numExpeDv", parametros.get("numExpedienteVirtual").toString());
			mapSeguimiento.put("codTipDoc", parametros.get("codTipoDocumento").toString());
			mapSeguimiento.put("numDoc", parametros.get("numDocuemento").toString());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t6622SeguimDAO.eliminarSeguimientoCambioEstadoEtapa(mapSeguimiento);

			// ENVIAR CORREO A RESPONSABLES
			for (CorreosBean bean : (List<CorreosBean>) parametros.get("listCorreos")) {
				Map<String, Object> mapEnvioCorreo = new HashMap<String, Object>();
				mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
				String mensaje = MensajeAlertaConstantes.MSJ_ALERTA_REV_EXP_VIRT.replace(
						ValidaConstantes.PATRON_CAMBIO_MENSAJES_0,
						parametros.get("numExpedienteOrigen").toString().trim());
				mapEnvioCorreo.put("mensaje", mensaje);
				correosService.enviarCorreo(mapEnvioCorreo);
			}

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", true);
			mapResponse.put("message", AvisoConstantes.AVISO_MODULO_01_07_004);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.RevertirCierreExpVirtual");
			log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.RevertirCierreExpVirtual");
		}

		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);
		return jsonResponse;
	}

	public boolean obviarDocCierreByTipExp(String codTipExp) throws Exception {
		boolean rpta = false;
		log.debug("Inicio - ExpedienteVirtualServiceImpl.obviarDocCierreByTipExp");
		if (ValidaConstantes.TIPO_EXPE_CARTA_INDUCTIVA.equals(codTipExp)) {// OPTIMIZAR CON PARAMETROS
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_ESQUELA.equals(codTipExp)) {
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(codTipExp)) {
			rpta = true;
			// PAS20191U210500144 Inicio 400101 RF04, RF13 PSALDARRIAGA
		} else if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(codTipExp)) {
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(codTipExp)) {
			rpta = true;
			// PAS20191U210500144 Fin 400101 RF04, RF13 PSALDARRIAGA
		} else {
			rpta = false;
		}
		log.debug("Final - ExpedienteVirtualServiceImpl.obviarDocCierreByTipExp");
		return rpta;
	}

	public boolean expReAbiertoByTipExp(String codTipExp, String numExpVir) throws Exception {
		boolean rpta = false;
		log.debug("Inicio - ExpedienteVirtualServiceImpl.expReAbiertoByTipExp");
		try {
			if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(codTipExp) || // OPTIMIZAR CON PARAMETROS
			// PAS20191U210500144 Inicio 400101 RF13 PSALDARRIAGA
					ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(codTipExp)
					|| ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(codTipExp)
			// PAS20191U210500144 Fin 400101 RF13 PSALDARRIAGA
			) {

				Integer nroDocRea = t6613DocExpVirtDAO.countDocReaperByNumExpVir(numExpVir);
				if (nroDocRea.intValue() == 0) {
					rpta = false;
				} else {
					rpta = true;
				}
			} else {
				rpta = false;
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.expReAbiertoByTipExp");
			log.error(ex, ex);
			throw ex;
		} finally {
			log.debug("Fin - ExpedienteVirtualServiceImpl.expReAbiertoByTipExp");
		}
		return rpta;
	}

	public boolean expAbiertoByTipExp(String codTipExp, String codEstExp) throws Exception {
		boolean rpta = false;
		log.debug("Inicio - ExpedienteVirtualServiceImpl.expAbiertoByTipExp");
		if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(codTipExp) ||
		// PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
				ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(codTipExp)
				|| ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(codTipExp)
		// PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA
		) {
			if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO.equals(codEstExp)) {
				rpta = true;
			} else {
				rpta = false;
			}
		} else {
			rpta = false;
		}
		log.debug("Final - ExpedienteVirtualServiceImpl.expAbiertoByTipExp");
		return rpta;
	}

	public boolean obviarOrigenAutoByTipExp(String codTipExp) throws Exception {
		boolean rpta = false;
		log.debug("Inicio - ExpedienteVirtualServiceImpl.obviarOrigenAutoByTipExp");
		if (ValidaConstantes.TIPO_EXPE_CARTA_INDUCTIVA.equals(codTipExp)) {// OPTIMIZAR CON PARAMETROS
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_ESQUELA.equals(codTipExp)) {
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(codTipExp)) {
			rpta = true;
			// PAS20191U210500144 Inicio 400101 RF25 PSALDARRIAGA
		} else if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(codTipExp)) {
			rpta = true;
		} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(codTipExp)) {
			rpta = true;
			// PAS20191U210500144 Fin 400101 RF25 PSALDARRIAGA

		} else {
			rpta = false;
		}
		log.debug("Final - ExpedienteVirtualServiceImpl.obviarOrigenAutoByTipExp");
		return rpta;
	}

	// PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA
	// Verificar cierre del expediente, para los tipos
	// Fiscalizacion definitiva/parcial 430
	// cruces de información 431
	// cruces de información OCP 432
	@Override
	public T6614ExpVirtualBean verificarCierre(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedientevirtualServiceImpl.verificarCierre");

		T6614ExpVirtualBean expVirtualBean = new T6614ExpVirtualBean();
		Map<String, Object> mapExpVir = null;

		try {

			DataSourceContextHolder
					.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + params.get("cod_dep"));
			mapExpVir = expFiscaDAO.spFiverificarCierreExpediente(params);
			expVirtualBean.setCodRetornoServicio(mapExpVir.get("cod_resp").toString());
			expVirtualBean.setDesMotivoCierre(
					(mapExpVir.get("desc_resp") == null) ? "" : mapExpVir.get("desc_resp").toString());

			if (ValidaConstantes.FILTRO_UNO.equals(mapExpVir.get("cod_resp").toString())) {
				expVirtualBean.setFecCierre(Utils.stringToDate(
						Utils.toStr(mapExpVir.get("fec_cierre")).substring(0, 10), CatalogoConstantes.INT_FOUR));

			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedientevirtualServiceImpl.verificarCierre");
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedientevirtualServiceImpl.verificarCierre");
		}

		return expVirtualBean;
	}
	// PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA

	// fin
	@Override
	public List<Map<String, Object>> obtenerArchivosExpediente(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaT10373DocAdjReq = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listaT10373DocAdjReqTemp = null;
		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> itemResult = null;

		Integer numCorDoc = null;
		Integer numArcAdj = null;
		String numReq = null;
		Integer numArcItem = null;
		String nomArcAdj = null;
		String numArc = null;
		Integer cntTamArc = null;
		Integer numFolios = null;
		String codIdecm = null;
		String codTipExp = null;
		String codTipDoc = null;
		String numDoc = null;
		String numExpedO = null;
		String desTamArc = null;
		//
		Integer numItem = null;
		//
		Map<String, Object> mapa = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerArchivosExpediente");

		try {

			log.debug("numCordoc->p : " + parametros);

			listaT10373DocAdjReqTemp = t10373DocAdjReqDAO.listarArcDocPorExp(parametros);

			if (listaT10373DocAdjReqTemp != null) {
				listaT10373DocAdjReq.addAll(listaT10373DocAdjReqTemp);
			}

			int i = 1;
			if (listaT10373DocAdjReq != null) {
				listaMapResult = new LinkedList<Map<String, Object>>();

				mapa = new HashMap<String, Object>();

				log.debug(listaT10373DocAdjReq);
				for (Map<String, Object> mapT10373 : listaT10373DocAdjReq) {
					log.debug(listaT10373DocAdjReq);

					log.debug("numCordoc : " + mapT10373.get("numCorDoc"));

					itemResult = new HashMap<String, Object>();

					numCorDoc = (Integer) mapT10373.get("numCorDoc");
					itemResult.put("numCorDoc", numCorDoc);

					numArcAdj = (Integer) mapT10373.get("numArcAdj");
					itemResult.put("numArcAdj", numArcAdj);

					numReq = ((String) mapT10373.get("numReq")).trim();
					itemResult.put("numReq", numReq);

					numArcItem = (Integer) mapT10373.get("numArcItem");
					itemResult.put("numArcItem", numArcItem);

					//
					numItem = (Integer) mapT10373.get("numItem");
					itemResult.put("numItem", numItem);
					//

					nomArcAdj = ((String) mapT10373.get("nomArcAdj")).trim();
					itemResult.put("nomArcAdj", nomArcAdj);

					numArc = ((String) mapT10373.get("numArc")).trim();
					itemResult.put("numArc", numArc);

					String formato = "MB";

					cntTamArc = (Integer) mapT10373.get("cntTamArc");
					itemResult.put("cntTamArc", cntTamArc);

					desTamArc = (String) cntTamArc.toString();
					itemResult.put("desTamArc", desTamArc + formato);

					numFolios = (Integer) mapT10373.get("numFolios");
					itemResult.put("numFolios", numFolios);

					codIdecm = ((String) mapT10373.get("codIdecm")).trim();
					itemResult.put("codIdecm", codIdecm);

					codTipExp = ((String) mapT10373.get("codTipExp")).trim();
					itemResult.put("codTipExp", codTipExp);

					codTipDoc = ((String) mapT10373.get("codTipDoc")).trim();
					itemResult.put("codTipDoc", codTipDoc);

					numDoc = ((String) mapT10373.get("numDoc")).trim();
					itemResult.put("numDoc", numDoc);

					numExpedO = ((String) mapT10373.get("numExpedO")).trim();
					itemResult.put("numExpedO", numExpedO);

					itemResult.put("numOrden", i);
					i++;

					listaMapResult.add(itemResult);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerArchivosExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerArchivosExpediente");

			NDC.pop();
			NDC.remove();
		}
		return listaMapResult;
	}

	@Override
	public List<Map<String, Object>> listarArchivosAdj(Map<String, Object> parametroBusqueda) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Inicio - DocumentoExpedienteServiceImpl.listarArchivosAdj");

		List<Map<String, Object>> listaArchivosAdj = new ArrayList<Map<String, Object>>();

		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - DocumentoExpedienteServiceImpl.listarArchivosAdj");

			listaArchivosAdj = t10373DocAdjReqDAO.listar(parametroBusqueda);

			log.debug("listaServicioTRY: " + listaArchivosAdj);

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - DocumentoExpedienteServiceImpl.listarArchivosAdj");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - DocumentoExpedienteServiceImpl.listarArchivosAdj");

			NDC.pop();
			NDC.remove();

		}
		log.debug("listaServicio: " + listaArchivosAdj);
		return listaArchivosAdj;
	}

	private Map<String, Object> buscarTipNumDocRel(int numCordocrel, List<Map<String, Object>> listaT6613DocExpVirts)
			throws Exception {
		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - ExpedienteServiceImpl.buscarTipNumDocRel");
			log.debug(listaT6613DocExpVirts);

			for (Map<String, Object> mapT6613 : listaT6613DocExpVirts) {

				// log.debug(" mapT6613: " + mapT6613);
				log.debug("Doc Relacionado-For: " + mapT6613.get("numCorDoc").toString().trim());

				if (Integer.parseInt(mapT6613.get("numCorDoc").toString()) == numCordocrel) {

					log.debug("Doc Relacionado If: " + mapT6613);
					log.debug("numCordocrel: " + numCordocrel);
					return mapT6613;
					// break;
				} else {
					log.debug("Doc Relacionado-Else: " + mapT6613);
				}

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteServiceImpl.buscarTipNumDocRel");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteServiceImpl.buscarTipNumDocRel");

			NDC.pop();
			NDC.remove();

		}
		return null;

	}

	private T6613DocExpVirtBean buscarTipNumDocRelBean(int numCordocrel,
			List<T6613DocExpVirtBean> listT6613DocVirtualBean) throws Exception {

		try {

			if (log.isDebugEnabled())
				log.debug("Procesa - ExpedienteServiceImpl.buscarTipNumDocRelBean");
			log.debug(" listT6613: " + listT6613DocVirtualBean);

			for (T6613DocExpVirtBean listT6613 : listT6613DocVirtualBean) {
				log.debug(" listT6613: " + listT6613);
				log.debug("Doc Relacionado-For: " + listT6613.getNumCorDoc());
				log.debug("numCordocrel For: " + numCordocrel);

				if (Integer.parseInt(listT6613.getNumCorDoc().toString()) == numCordocrel) {
					log.debug("Doc Relacionado If: " + listT6613.getNumCorDoc());
					return listT6613;
				} else {

					listT6613.setDesTipdocRel("-");
					listT6613.setNumDocRel("-");
					return listT6613;
				}

			}
		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteServiceImpl.buscarTipNumDocRelBean");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteServiceImpl.buscarTipNumDocRelBean");

			NDC.pop();
			NDC.remove();

		}

		return null;
	}
//FIN LLRB PAS20191U210500291

//INCIO LLRB 15/01/2020

	public List<Map<String, Object>> obtenerCopiasExpediente(Map<String, Object> parametros) throws Exception {
		List<Map<String, Object>> listaT10458VersDocAdj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listaT10458VersDocAdjTemp = null;
		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> itemResult = null;

		Integer numCorDoc = null;
		Integer numVerDoc = null;
		Integer numCopia = null;
		String nomArcAdj = null;
		Integer cntTamArc = null;
		String codIdecm = null;
		String indVis = null;
		String codUsuRegis = null;
		Date fecRegis = null;
		String codTipExp = null;
		String codTipDoc = null;
		String numDoc = null;
		String numExpedO = null;
		String desTamArc = null;
		Map<String, Object> mapa = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerCopiasExpediente");

		try {

			log.debug("numCordoc->p : " + parametros);

			listaT10458VersDocAdjTemp = t10458VersDocAdjDAO.listarCopDocPorExp(parametros);
			log.debug("listaTXXXXXVersDocAdjTemp->p : " + listaT10458VersDocAdjTemp);

			if (listaT10458VersDocAdjTemp != null) {
				// listaTXXXXXVersDocAdj.addAll(listaTXXXXXVersDocAdjTemp);
				// INICIO LLRB
				for (Map<String, Object> mapT10458 : listaT10458VersDocAdjTemp) {
					if (mapT10458 != null) {
						listaT10458VersDocAdj.add(mapT10458);
					}
				}
				// FIN LLRB
			}

			int i = 1;
			if (listaT10458VersDocAdj != null) {
				listaMapResult = new LinkedList<Map<String, Object>>();

				mapa = new HashMap<String, Object>();

				log.debug(listaT10458VersDocAdj);
				for (Map<String, Object> mapT10458 : listaT10458VersDocAdj) {
					log.debug(listaT10458VersDocAdj);

					log.debug("numCordoc : " + mapT10458.get("numCorDoc"));

					itemResult = new HashMap<String, Object>();

					numCorDoc = (Integer) mapT10458.get("numCorDoc");
					itemResult.put("numCorDoc", numCorDoc);

					numVerDoc = (Integer) mapT10458.get("numVerDoc");
					itemResult.put("numVerDoc", numVerDoc);

					numCopia = (Integer) mapT10458.get("numCopia");
					itemResult.put("numCopia", numCopia);

					nomArcAdj = ((String) mapT10458.get("nomArcAdj")).trim();
					itemResult.put("nomArcAdj", nomArcAdj);

					cntTamArc = (Integer) mapT10458.get("cntTamArc");
					itemResult.put("cntTamArc", cntTamArc);

					codUsuRegis = ((String) mapT10458.get("codUsuRegis")).trim();
					itemResult.put("codUsuRegis", codUsuRegis);

					SimpleDateFormat formatoFechaVista = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

					fecRegis = (Date) mapT10458.get("fecRegis");

					if (Utils.esFechaVacia(fecRegis)) {
						itemResult.put("fecDocu", null);
						itemResult.put("fechaVista", ValidaConstantes.SEPARADOR_ESPACIO);
					} else {
						itemResult.put("fecRegis", fecRegis);
						itemResult.put("fechaVista", formatoFechaVista.format(fecRegis));
					}

					indVis = ((String) mapT10458.get("indVis")).trim();
					itemResult.put("indVis", indVis);

					codIdecm = ((String) mapT10458.get("codIdecm")).trim();
					itemResult.put("codIdecm", codIdecm);

					codTipExp = ((String) mapT10458.get("codTipExp")).trim();
					itemResult.put("codTipExp", codTipExp);

					codTipDoc = ((String) mapT10458.get("codTipDoc")).trim();
					itemResult.put("codTipDoc", codTipDoc);

					numDoc = ((String) mapT10458.get("numDoc")).trim();
					itemResult.put("numDoc", numDoc);

					numExpedO = ((String) mapT10458.get("numExpedO")).trim();
					itemResult.put("numExpedO", numExpedO);

					itemResult.put("numOrden", i);
					i++;

					listaMapResult.add(itemResult);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerCopiasExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerCopiasExpediente");

			NDC.pop();
			NDC.remove();
		}
		return listaMapResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> registrarDocumentosExpedienteFisca(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.registrarDocumentosExpedienteFisca");

		Map<String, Object> beanEcm = null;
		Calendar fechaActual = null;
		Map<String, Object> beanDocExp = null;
		Long numSeq = null;
		Long numCordoc = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		Calendar fechaVacia = null;
		String codIdecm = null;
		List<String> listaColaboradores = null;
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		String mensaje = null;
		String formatoFechaReq = "dd-MM-yyyy";
		String fecha = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		String descTipoDoc = null;
		Map<String, Object> mapT01Param = null;
		String flagOrigen = null;
		String nombreArchivo = null;
		String codEstexpori = null;
		String codEtaexpori = null;
		Map<String, Object> mapParamActualizaAcum = null;
		String indEmiAlerta = null;

		try {
			fechaActual = Calendar.getInstance();
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			// Crear el documento
			beanDocExp = new HashMap<String, Object>();
			nombreArchivo = (String) parametros.get("desArch");
			nombreArchivo = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));

			beanDocExp.put("numCorDoc", parametros.get("numCorDoc"));
			beanDocExp.put("nombreArchivo", parametros.get("nombreArchivo"));
			beanDocExp.put("tamanoDoc", parametros.get("tamanoDoc"));
			beanDocExp.put("numCopia", parametros.get("numCopia"));
			beanDocExp.put("codUsuRegis", parametros.get("codUsuregis"));
			beanDocExp.put("fecRegis", fechaActual.getTime());
			beanDocExp.put("indVisible", parametros.get("indVisible"));

			beanEcm = new HashMap<String, Object>();
			beanEcm.put("nomArchivoSinExt", nombreArchivo);
			beanEcm.put("numExpeDv", parametros.get("numExpeDv"));
			beanEcm.put("numRuc", parametros.get("numRuc"));
			beanEcm.put("numDoc", parametros.get("numDoc"));
			beanEcm.put("fecEmi", parametros.get("fecDoc"));
			beanEcm.put("codTipExpVir", parametros.get("codTipexp"));
			beanEcm.put("codTipDocu", parametros.get("codTipdoc"));
			beanEcm.put("archLength", parametros.get("archLength"));
			beanEcm.put("archMimeType", parametros.get("archMimeType"));
			beanEcm.put("archFileName", parametros.get("archFileName"));
			beanEcm.put("archContent", parametros.get("binDoc"));
			beanEcm.put("metadata", parametros.get("metadata"));
			beanEcm.put("stream", parametros.get("stream"));
			beanEcm.put("codDep", parametros.get("codDep"));
			codIdecm = ecmService.guardarDocumentoECM(beanEcm);
			// codIdecm = "{0039875B-0000-C61B-9424-230B97A98E11}";//DESARROLLO LOCAL
			// QUITAR!!!
			if (codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
			}
			beanDocExp.put("desArch", parametros.get("desArch"));
			beanDocExp.put("codIdecm", codIdecm);

			beanDocExp.put("codOrigDoc", parametros.get("codOrigDoc"));
			// Inicio - [oachahuic][PAS20175E210400016]
			beanDocExp.put("fecNotif", fechaVacia.getTime());
			beanDocExp.put("codForNotif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codEstDoc", ValidaConstantes.COD_EST_DOC_REGISTRADO);
			beanDocExp.put("desEliDoc", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("fecModif", fechaVacia.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			// Fin - [oachahuic][PAS20175E210400016]
			t10458VersDocAdjDAO.insertar(beanDocExp);

			// Inicio - [oachahuic][PAS20175E210400016]
			/*
			 * if (parametros.get("actDocRel") != null) { T6613DocExpVirtBean t6613Bean =
			 * new T6613DocExpVirtBean();
			 * t6613Bean.setNumCorDoc((Integer)parametros.get("numCordocRel")); if
			 * (parametros.get("indVisDocRel") != null) {
			 * t6613Bean.setIndVisDocumento((String)parametros.get("indVisDocRel")); } if
			 * (parametros.get("fecNotDocRel") != null) {
			 * t6613Bean.setFecNotif((Date)parametros.get("fecNotDocRel"));
			 * t6613Bean.setCodForNotif((String)parametros.get("forNotDocRel")); }
			 * t6613Bean.setCodUsuModif((String)parametros.get("codUsuregis"));
			 * t6613Bean.setFecModif(fechaActual.getTime());
			 * 
			 * DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.
			 * DATASOURCE_TRANSACCION_EXPVIRTUAL); t6613DocExpVirtDAO.update(t6613Bean); }
			 * //Fin - [oachahuic][PAS20175E210400016]
			 * 
			 * if (parametros.get("listExpAcum") != null) { List<Map<String, String>>
			 * listExpAcum = (List<Map<String, String>>) parametros.get("listExpAcum"); for
			 * (Map<String, String> mapExp : listExpAcum) { mapParamActualizaAcum = new
			 * HashMap<String, Object>(); mapParamActualizaAcum.put("numExpedienteVirtual",
			 * mapExp.get("numExpVir")); mapParamActualizaAcum.put("indAcu",
			 * mapExp.get("indAcu")); mapParamActualizaAcum.put("numAcu",
			 * mapExp.get("numAcu")); mapParamActualizaAcum.put("codUsuModif",
			 * parametros.get("codUsuregis").toString());
			 * mapParamActualizaAcum.put("fecModif", fechaActual.getTime());
			 * DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.
			 * DATASOURCE_TRANSACCION_EXPVIRTUAL);
			 * t6614ExpVirtualDAO.actualizar(mapParamActualizaAcum); } }
			 */

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", parametros.get("numExpeDv"));
			mapResponse.put("codIdecm", codIdecm);

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				JsonSerializer serializer = new JsonSerializer();

				mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("numExpedo", parametros.get("numExpedo"));

				codEstexpori = (String) parametros.get("codEstexpori");
				if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEstexpori = ValidaConstantes.CADENA_VACIA;
				}
				codEtaexpori = (String) parametros.get("codEtaexpori");
				if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEtaexpori = ValidaConstantes.CADENA_VACIA;
				}
				mapRequest.put("codEstexpori", codEstexpori);
				mapRequest.put("codEtaexpori", codEtaexpori);
				mapRequest.put("codTipexp", parametros.get("codTipexp"));
				mapRequest.put("codTipdoc", parametros.get("codTipdoc"));
				mapRequest.put("numDoc", parametros.get("numDoc"));
				mapRequest.put("codIdecm", parametros.get("codIdecm"));
				mapRequest.put("fileExtension", parametros.get("fileExtension"));
				mapRequest.put("desArch", parametros.get("desArch"));
				mapRequest.put("codUsuregis", parametros.get("codUsuregis"));
				fecha = Utils.convertirDateToString((Date) parametros.get("fecDoc"), formatoFechaReq);
				mapRequest.put("fecDoc", fecha);
				mapRequest.put("metadata", parametros.get("metadata"));
				mapRequest.put("codTipdocRel", parametros.get("codTipdocRelParam"));
				mapRequest.put("numDocRel", parametros.get("numDocRelParam"));
				mapRequest.put("indAcu", parametros.get("indAcu"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));
				// PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
				mapRequest.put("indReserTrib", parametros.get("indReserTrib"));
				// PAS20191U210500144 Fin 400101 RF06 PSALDARRIAGA

				// Seguimiento
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_REC_ANEXDOC_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				seguiService.registrarSeguimiento(beanSeguiWS);

				// Trazabilidad
				if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					beanSeguiWS = new HashMap<String, Object>();
					beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual.getTime());
					beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia.getTime());
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
					beanSeguiWS.put("num_doc", parametros.get("numDoc"));

					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
					}
					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
					}
					seguiService.registrarSeguimiento(beanSeguiWS);
				}
				// eaguilar 28 JUN: se quita el registro de seguimiento para los acumuladores
				// (WS)
				Map<String, Object> mapSeguiAcum = new HashMap<String, Object>();
				mapSeguiAcum.put("codTipdoc", parametros.get("codTipdoc"));
				mapSeguiAcum.put("numDoc", parametros.get("numDoc"));
				mapSeguiAcum.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
				mapSeguiAcum.put("numAcu", Utils.toStr(parametros.get("numExpedo")).trim());
				mapSeguiAcum.put("codEstexpori", codEstexpori);
				mapSeguiAcum.put("codEtaexpori", codEtaexpori);
				mapSeguiAcum.put("fechaActual", fechaActual.getTime());
				mapSeguiAcum.put("fechaVacia", fechaVacia.getTime());
				mapResponse.put("mapSeguiAcum", mapSeguiAcum);
			} else if (flagOrigen.equals(ValidaConstantes.IND_REG_DOC_INT)) {
				codEstexpori = (String) parametros.get("codEstexpori");
				if (codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEstexpori = ValidaConstantes.CADENA_VACIA;
				}
				codEtaexpori = (String) parametros.get("codEtaexpori");
				if (codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
					codEtaexpori = ValidaConstantes.CADENA_VACIA;
				}
				// Inicio [jquispe 27/05/2016] Mapa de descripciones de acciones
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);
				// Fin [jquispe 27/05/2016]

				// Seguimiento
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_DOCUMENTO_INTERNO);
				beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS);
				beanSeguiWS.put("des_datcons",
						Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_SUBIRDOCUMENTOS)));
				beanSeguiWS.put("fec_cons", fechaActual.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				seguiService.registrarSeguimiento(beanSeguiWS);

				// Trazabilidad
				if ((!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)
						|| !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA))
						&& esTipoDocumentoRegistraSeguimiento(parametros)) {// [jquispe 05/07/2016] Verifica si el
																			// documento se puede grabar en la tabla de
																			// seguimiento.

					beanSeguiWS = new HashMap<String, Object>();
					beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));
					beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
					beanSeguiWS.put("fec_seguim", fechaActual.getTime());
					beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
					beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("fec_cons", fechaVacia.getTime());
					beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiWS.put("cod_tipdoc", parametros.get("codTipdoc"));
					beanSeguiWS.put("num_doc", parametros.get("numDoc"));

					if (!codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_estexpori", codEstexpori);
						beanSeguiWS.put("fec_cambest", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
					}
					if (!codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
						beanSeguiWS.put("cod_etaexpori", codEtaexpori);
						beanSeguiWS.put("fec_cambeta", fechaActual.getTime());
					} else {
						beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
						beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
					}
					seguiService.registrarSeguimiento(beanSeguiWS);
				}
				// eaguilar 28 JUN: se quita el registro de seguimiento para los acumuladores
				// (FRONT)
				Map<String, Object> mapSeguiAcum = new HashMap<String, Object>();
				mapSeguiAcum.put("codTipdoc", parametros.get("codTipdoc"));
				mapSeguiAcum.put("numDoc", parametros.get("numDoc"));
				mapSeguiAcum.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
				mapSeguiAcum.put("numAcu", Utils.toStr(parametros.get("numExpedo")).trim());
				mapSeguiAcum.put("codEstexpori", codEstexpori);
				mapSeguiAcum.put("codEtaexpori", codEtaexpori);
				mapSeguiAcum.put("fechaActual", fechaActual.getTime());
				mapSeguiAcum.put("fechaVacia", fechaVacia.getTime());
				mapResponse.put("mapSeguiAcum", mapSeguiAcum);
			}

			indEmiAlerta = (String) parametros.get("indEmiAlerta");
			if (ValidaConstantes.IND_EMI_ALERTA_SI.equals(indEmiAlerta)) {// Enviar correos a los responsables asignados
																			// al expediente virtual
				listaColaboradores = (ArrayList<String>) parametros.get("listaColaboradores");
				if (listaColaboradores != null && listaColaboradores.size() > 0) {
					mapConsultaCorreo = new HashMap<String, Object>();
					listaCorreosBeans = new ArrayList<CorreosBean>();
					for (String codColaborador : listaColaboradores) {
						correosBean = new CorreosBean();
						correosBean.setCodPers(codColaborador);
						listaCorreosBeans.add(correosBean);
					}
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
					if (listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
						mapT01Param = new HashMap<String, Object>();
						mapT01Param.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
						mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
						mapT01Param.put("codParametro", (String) parametros.get("codTipdoc"));
						descTipoDoc = this.obtenerDescParamExpVirt(mapT01Param);
						if (log.isDebugEnabled())
							log.debug(
									"Inicio Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpedienteFisca");
						for (CorreosBean bean : listaCorreosBeans) {
							mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());

							descTipoDoc = descTipoDoc != null ? descTipoDoc.trim() : "";
							mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ANX_DOC_EXP_VIRT
									.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, descTipoDoc);
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1,
									(String) parametros.get("numDoc"));
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_2,
									(String) parametros.get("numExpedo"));

							mapEnvioCorreo.put("mensaje", mensaje);
							correosService.enviarCorreo(mapEnvioCorreo);
						}
						if (log.isDebugEnabled())
							log.debug(
									"Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarDocumentosExpedienteFisca");
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.registrarDocumentosExpedienteFisca");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.registrarDocumentosExpedienteFisca");
		}
		return mapResponse;
	}

	public Integer obtenerNroCopia(Map<String, Object> parametros) throws Exception {
		Integer numCopia = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerNroCopia");

			log.debug("parametros : " + parametros);
			numCopia = t10458VersDocAdjDAO.obtenerCopia(parametros);
			log.debug("numCopia-E : " + numCopia);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerNroCopia");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerNroCopia");

			NDC.pop();
			NDC.remove();
		}

		return numCopia;
	}

	public Integer obtenerNroExpediente(Map<String, Object> parametros) throws Exception {
		Integer numExp = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerNroExpediente");

			log.debug("parametros : " + parametros);
			numExp = t10459AccepExpVirtDAO.obtenerNroExpediente(parametros);
			log.debug("numExp-E : " + numExp);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerNroExpediente");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerNroExpediente");

			NDC.pop();
			NDC.remove();
		}

		return numExp;
	}

	public Map<String, Object> obtenerExpVirtual(Map<String, Object> parametros) throws Exception {
		Map<String, Object> nroExpVirtual = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerExpVirtual");

			log.debug("parametros : " + parametros);
			nroExpVirtual = t6614ExpVirtualDAO.obtenerNroExpedienteVirtual(parametros);
			log.debug("nroExpVirtual : " + nroExpVirtual);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerExpVirtual");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerExpVirtual");

			NDC.pop();
			NDC.remove();
		}

		return nroExpVirtual;
	}

	@Override
	public String registrarSolicitudExpVirt(Map<String, Object> parametros) throws Exception {

		String numExpe = null;
		String numSeqExpeDv = null;
		Long numSeq = null;
		Map<String, Object> beanExp = null;
		Map<String, Object> beanSeguiWS = null;
		Calendar fechaActual = null;
		Calendar fechaVacia = null;
		JsonSerializer serializer = null;
		String estado = null;
		String numSeqSol = null;
		Integer tamSeqExpeDv = null;
		String codProc = null;
		String codTipExp = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.registrarSolicitudExpVirt");

		try {
			fechaActual = Calendar.getInstance();

			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			beanExp = new HashMap<String, Object>();

			// COLOCAR EL SEQUENCE CONSTANTE CREADO
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_SOLDES);
			log.debug("numSeq : " + numSeq);
			numSeqExpeDv = "0000000" + numSeq.toString();
			tamSeqExpeDv = numSeqExpeDv.length();
			numSeqExpeDv = numSeqExpeDv.substring(tamSeqExpeDv - 7, tamSeqExpeDv);

			codProc = (String) parametros.get("codProc");
			codTipExp = (String) parametros.get("codTipExp");
			numSeqSol = codProc + codTipExp + numSeqExpeDv;

			numExpe = (String) parametros.get("numExpe");
			log.debug("numExpe : " + parametros.get("numExpe"));
			log.debug("codUsuRegis : " + parametros.get("codUsuregis"));
			estado = "01";// INICIADO

			beanExp.put("numSeq", numSeqSol);
			beanExp.put("numExpe", numExpe);
			beanExp.put("codEstado", estado);
			beanExp.put("codUsuRegis", parametros.get("codUsuRegis"));
			beanExp.put("fecRegIni", fechaActual.getTime());
			beanExp.put("fecRegFin", fechaActual.getTime());
			beanExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanExp.put("fecModif", fechaVacia.getTime());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

			t10461SolDesDAO.insertar(beanExp);
			// Seguimiento WS
			serializer = new JsonSerializer();
			beanSeguiWS = new HashMap<String, Object>();

			beanSeguiWS.put("num_expedv", numExpe);
			beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
			beanSeguiWS.put("fec_seguim", fechaActual.getTime());
			beanSeguiWS.put("fec_invserv", fechaActual.getTime());
			beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_GENERA_EXP_VIRT);
			beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
			beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuregis"));
			beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("fec_cons", fechaVacia.getTime());
			beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
			beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());

			seguiService.registrarSeguimiento(beanSeguiWS);

			if (log.isDebugEnabled())
				log.debug("Fin Envio de correo - ExpedienteVirtualServiceImpl.registrarSolicitudExpVirt");
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.registrarSolicitudExpVirt");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.registrarSolicitudExpVirt");

			NDC.pop();
			NDC.remove();
		}

		return numSeqExpeDv;
	}

	@Override
	public List<Map<String, Object>> obtenerNumExpVirVinc(Map<String, Object> mapParametrosBusqueda) throws Exception {
		List<Map<String, Object>> listaT10460VincExpVirt = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listaT10460VincExpVirtTemp = null;
		List<Map<String, Object>> listaMapResult = null;
		Map<String, Object> itemResult = null;
		Map<String, Object> mapa = null;
		String numExpedv = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerNumExpVirVinc");

			log.debug("parametros : " + mapParametrosBusqueda);
			listaT10460VincExpVirtTemp = t10460VincExpVirtDAO.obtenerNumExpVinculados(mapParametrosBusqueda);

			if (listaT10460VincExpVirtTemp != null) {

				for (Map<String, Object> mapT10460 : listaT10460VincExpVirtTemp) {
					if (mapT10460 != null) {
						listaT10460VincExpVirt.add(mapT10460);
					}
				}

			}
			int i = 1;
			if (listaT10460VincExpVirt != null) {
				listaMapResult = new LinkedList<Map<String, Object>>();

				mapa = new HashMap<String, Object>();

				log.debug(listaT10460VincExpVirt);
				for (Map<String, Object> mapT10460 : listaT10460VincExpVirt) {
					log.debug(listaT10460VincExpVirt);
					log.debug("nroExpVirtual : " + mapT10460.get("numExpedv"));

					itemResult = new HashMap<String, Object>();

					numExpedv = (String) mapT10460.get("numExpedv");

					itemResult.put("numExpedv", numExpedv);
					itemResult.put("numOrden", i);
					i++;

					listaMapResult.add(itemResult);

				}
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerNumExpVirVinc");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerNumExpVirVinc");

			NDC.pop();
			NDC.remove();
		}

		return listaMapResult;

	}

	public Integer obtenerNroSolicitud(Map<String, Object> parametros) throws Exception {
		Integer numSolicitud = null;
		Date fechaActual = null;
		Map<String, Object> mapSol = null;

		try {
			fechaActual = Calendar.getInstance().getTime();

			log.debug("fechaActual : " + fechaActual);
			mapSol = new HashMap<String, Object>();

			if (log.isDebugEnabled())
				log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerNroSolicitud");

			log.debug("parametros : " + parametros);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaS = formatter.format(fechaActual);
			log.debug("fechaS : " + fechaS);

			mapSol.put("nroExpVirtual", parametros.get("nroExpVirtual"));
			mapSol.put("codUsuRegis", parametros.get("codUsuRegis"));
			mapSol.put("fecRegIni", fechaS);

			numSolicitud = t10461SolDesDAO.obtenerNroSolicitud(mapSol);
			log.debug("numSolicitud : " + numSolicitud);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerNroSolicitud");

			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerNroSolicitud");

			NDC.pop();
			NDC.remove();
		}

		return numSolicitud;
	}

//FIN LLRB 03/02/2020

	// inicio CVG
	@Override
	public void vincularExpedienteVirtual(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteServiceImpl.vincularExpedienteVirtual");
		T10460VincExpVirtBean vincExp = new T10460VincExpVirtBean();

		vincExp.setNumExpepri(parametros.get("numExpri").toString());
		vincExp.setNumExpevin(parametros.get("numExpvin").toString());
		vincExp.setFecVinc((Date) parametros.get("fechaVinc"));
		vincExp.setCodEst(parametros.get("codEstVinc").toString());
		vincExp.setUsuVinc(parametros.get("codUsuregis").toString());
		vincExp.setUsuReg(parametros.get("codUsuregis").toString());
		vincExp.setFecReg(new Date());
		log.debug("pri " + parametros.get("numExpri").toString());
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
		t10460VincExpVirtDAO.insertar(vincExp);

		if (log.isDebugEnabled())
			log.debug("Fin - ExpedienteServiceImpl.vincularExpedienteVirtual");

	}

	@Override
	public void desvincularExpedienteVirtual(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteServiceImpl.desvincularExpedienteVirtual");
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
		t10460VincExpVirtDAO.actualizar(parametros);
		if (log.isDebugEnabled())
			log.debug("Fin - ExpedienteServiceImpl.desvincularExpedienteVirtual");

	}
	// fin CVG

	// PAS20201U210500029 - HHC INICIO
	@Override
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtualReq(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T6614ExpVirtualBean> listT6614ExpVirtualBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualReq");

		try {

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaEstadoCierreExpediente = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapa.put("codTipoExpediente", ValidaConstantes.GRUPO_TIP_DOC_CONST);
			Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);
			String strFecha = null;

			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPO_DOCUMENTO_SUSTENTO);
			mapa.put("indtipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaDesFormaNotifi = catalogoService.obtenerCatalogo(mapa);

			Map<String, Object> parametros;
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listT6614ExpVirtualBean = t6614ExpVirtualDAO.listarDocumentoExpedienteReq(mapParametrosBusqueda);
			int i = 1;
			for (T6614ExpVirtualBean t6614ExpVirBean : listT6614ExpVirtualBean) {

				DdpBean beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirBean.getNumRuc());
				t6614ExpVirBean.setDesRazonSocial(
						!Utils.isEmpty(beanContribuyente) ? beanContribuyente.getDesRazonSocial().trim()
								: ValidaConstantes.CADENA_VACIA);

				t6614ExpVirBean.setNumExpedienteVirtual(t6614ExpVirBean.getNumExpedienteVirtual().trim());
				t6614ExpVirBean.setNumExpedienteOrigen(t6614ExpVirBean.getNumExpedienteOrigen().trim());
				t6614ExpVirBean.setDesOrigen(Utils.toStr(mapaOrigenExpediente.get(t6614ExpVirBean.getCodOrigen())));
				t6614ExpVirBean.setDesEstado(Utils.toStr(mapaEstadoExpediente.get(t6614ExpVirBean.getCodEstado())));
				t6614ExpVirBean.setDesForNotifOrigen(
						Utils.toStr(mapaDesFormaNotifi.get(t6614ExpVirBean.getCodForNotifOrigen())));
				t6614ExpVirBean
						.setDesProceso(Utils.toStr(mapaProcesos.get(t6614ExpVirBean.getCodProceso())).toUpperCase());
				t6614ExpVirBean.setDesTipoExpediente(
						Utils.toStr(mapaTiposExpedientes.get(t6614ExpVirBean.getCodTipoExpediente())).toUpperCase());
				if (Utils.isEmpty(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())) || Utils.equiv(
						Utils.toStr(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())),
						ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setDesEstadoCierre("");
				} else {
					t6614ExpVirBean.setDesEstadoCierre(
							Utils.toStr(mapaEstadoCierreExpediente.get(t6614ExpVirBean.getCodEstadoCierre())));
				}

				if (Utils.isEmpty(t6614ExpVirBean.getNumExpedienteAcumulador()) || Utils
						.equiv(t6614ExpVirBean.getNumExpedienteAcumulador(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setNumExpedienteAcumulador("0");
				} else {
					String sCadena = t6614ExpVirBean.getNumExpedienteAcumulador();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setNumExpedienteAcumulador("0");
					} else {
						t6614ExpVirBean.setNumExpedienteAcumulador(sCadena);
					}
				}

				if (Utils.equiv(t6614ExpVirBean.getIndicadorAcumulado(), ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)) {
					t6614ExpVirBean.setNumExpedienteAcumulador(t6614ExpVirBean.getNumExpedienteOrigen().trim());
				}

				if (Utils.isEmpty(t6614ExpVirBean.getCodRetornoServicio())
						|| Utils.equiv(t6614ExpVirBean.getCodRetornoServicio(), ValidaConstantes.SEPARADOR_GUION)) {
					t6614ExpVirBean.setCodRetornoServicio("0");
				} else {
					String sCadena = t6614ExpVirBean.getCodRetornoServicio();
					sCadena = sCadena.replace(" ", "");
					if (sCadena.equals("-")) {
						t6614ExpVirBean.setCodRetornoServicio("0");
					} else {
						t6614ExpVirBean.setCodRetornoServicio(sCadena);
					}
				}
				parametros = new HashMap<String, Object>();
				parametros.put("codPersona", t6614ExpVirBean.getCodResponsable());
				parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);

				T02PerdpBean responsable = personaService.validarPersona(parametros);

				if (responsable != null) {
					responsable = personaService.completarInformacionPersona(responsable);
					t6614ExpVirBean.setNombreResponsable(responsable.getDesNombreCompleto());
				} else {
					t6614ExpVirBean.setNombreResponsable("");
				}
				// Inicio - [oachahuic][PAS20175E210400016]
				if (ValidaConstantes.SEPARADOR_GUION.equals(t6614ExpVirBean.getCodForNotifOrigen().trim())) {
					t6614ExpVirBean.setDesForNotifOrigen("");
				} else {
					t6614ExpVirBean.setDesForNotifOrigen(
							mapaTiposNotificaciones.get(t6614ExpVirBean.getCodForNotifOrigen()).toString());
				}
				strFecha = new SimpleDateFormat("yyyy-MM-dd").format(t6614ExpVirBean.getFecNotifOrigen());
				if (Utils.equiv(strFecha, ValidaConstantes.FECHA_VACIA)) {
					t6614ExpVirBean.setStrFecNotifOrigen("");
				} else {
					t6614ExpVirBean
							.setStrFecNotifOrigen(Utils.dateUtilToStringDDMMYYYY(t6614ExpVirBean.getFecNotifOrigen()));
				}

				t6614ExpVirBean.setNumOrden(i);

				t6614ExpVirBean.setCodDependencia(t6614ExpVirBean.getCodDependencia().trim());
				i++;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualReq");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtualReq");

			NDC.pop();
			NDC.remove();
		}
		return listT6614ExpVirtualBean;
	}

	@Override
	public List<T10461SolDesBean> obtenerListaSolicitudDesc(Map<String, Object> mapParametrosBusqueda)
			throws Exception {

		List<T10461SolDesBean> listT10461SolDesBean = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.listarPedidos");

		try {
			String codigoProceso;
			String codigoTipoExpediente;
			String codigoEstadoReporte;
			String fechaRegistro;

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);

			mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			Map<String, Object> parametros;
			listT10461SolDesBean = t10461SolDesDAO.listarPedidosSolicitud(mapParametrosBusqueda);
			if (!Utils.isEmpty(listT10461SolDesBean)) {
				int i = 1;
				for (T10461SolDesBean t10461SolDesBean : listT10461SolDesBean) {
					codigoEstadoReporte = t10461SolDesBean.getCodEstadoSol();

					if (Utils.isEmpty(codigoEstadoReporte)
							|| Utils.equiv(codigoEstadoReporte, ValidaConstantes.SEPARADOR_GUION)) {
						t10461SolDesBean.setDescripcionEstSol("");
					} else {
						if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_PENDIENTE)) {
							t10461SolDesBean
									.setDescripcionEstSol(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
						} else if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_PROCESANDO)) {
							t10461SolDesBean.setDescripcionEstSol(ValidaConstantes.DES_ESTADO_SOL_DES_PROCESANDO);
						} else if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_GENERADO)) {
							t10461SolDesBean.setDescripcionEstSol(ValidaConstantes.DES_ESTADO_SOL_DES_GENERADO);
						} else if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_CONSULTADO)) {
							t10461SolDesBean.setDescripcionEstSol(ValidaConstantes.DES_ESTADO_SOL_DES_CONSULTADO);
						} else if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_ELIMINADO)) {
							t10461SolDesBean.setDescripcionEstSol(ValidaConstantes.DES_ESTADO_SOL_DES_ELIMINADO);
						} else if (Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_SOL_DES_ERROR)) {
							t10461SolDesBean.setDescripcionEstSol(ValidaConstantes.DES_ESTADO_SOL_DES_ERROR);
						}
					}
					fechaRegistro = Utils.dateUtilToStringDDMMYYYY(t10461SolDesBean.getFecGeneracionIni());
					if (fechaRegistro.equals("01/01/1")) {
						t10461SolDesBean.setFechaGeneracion("");
					} else {
						t10461SolDesBean.setFechaGeneracion(fechaRegistro);
					}
					parametros = new HashMap<String, Object>();
					parametros.put("codPersona", t10461SolDesBean.getCodUsuarioReg());
					parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);

					T02PerdpBean responsable = personaService.validarPersona(parametros);
					if (!Utils.isEmpty(responsable)) {
						responsable = personaService.completarInformacionPersona(responsable);
						t10461SolDesBean.setNombrePersonaRegistro(responsable.getDesNombreCompleto());
					} else {
						t10461SolDesBean.setNombrePersonaRegistro("");
					}
					t10461SolDesBean.setNumOrden(i);
					i++;
				}
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.listarPedidos");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.listarPedidos");

			NDC.pop();
			NDC.remove();
		}
		return listT10461SolDesBean;
	}
	// HHC - FIN

	// CVG
	private static String getSize(int size) {
		double n = 1024;
		String s = "";
		double kb = size / n;
		double mb = kb / n;
		double gb = mb / n;
		double tb = gb / n;

		if (size < n) {
			s = size + " Bytes";
		}

		else if (size >= n && size < (n * n)) {
			s = String.format("%.2f", kb) + " KB";
		}

		else if (size >= (n * n) && size < (n * n * n)) {
			s = String.format("%.2f", mb) + " MB";
		}

		else if (size >= (n * n * n) && size < (n * n * n * n)) {
			s = String.format("%.2f", gb) + " GB";
		}

		else if (size >= (n * n * n * n)) {
			s = String.format("%.2f", tb) + " TB";
		}

		return s;
	}

	/* Seteo de Spring - Inicio */
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	public void setT6620RequerimDAO(T6620RequerimDAO t6620RequerimDAO) {
		this.t6620RequerimDAO = t6620RequerimDAO;
	}

	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}

	public void setT6621RespExpVirtDAO(T6621RespExpVirtDAO t6621RespExpVirtDAO) {
		this.t6621RespExpVirtDAO = t6621RespExpVirtDAO;
	}

	// Inicio [JEFFIO]
	public void setT6622SeguimDAO(T6622SeguimDAO t6622SeguimDAO) {
		this.t6622SeguimDAO = t6622SeguimDAO;
	}

	// FIN
	public T02PerdpDAO getT02PerdpDAO() {
		return t02PerdpDAO;
	}

	public void setT02PerdpDAO(T02PerdpDAO t02PerdpDAO) {
		this.t02PerdpDAO = t02PerdpDAO;
	}

	// inicio CVG
	public T12UOrgaDAO getT12UOrgaDAO() {
		return t12UOrgaDAO;
	}

	public void setT12UOrgaDAO(T12UOrgaDAO t12uOrgaDAO) {
		t12UOrgaDAO = t12uOrgaDAO;
	}
	// fin CVG

	public void setExpCoaDAO(ExpCoaDAO expCoaDAO) {
		this.expCoaDAO = expCoaDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setRespService(ResponsableService respService) {
	}

	public void setGeneraReporteURLService(String generaReporteURLService) {
		this.generaReporteURLService = generaReporteURLService;
	}

	public void setUploadReporteURLService(String uploadReporteURLService) {
		this.uploadReporteURLService = uploadReporteURLService;
	}

	public void setT6623TipDocExpDAO(T6623TipDocExpDAO t6623TipDocExpDAO) {
		this.t6623TipDocExpDAO = t6623TipDocExpDAO;
	}

	public void setExpFiscaDAO(ExpFiscaDAO expFiscaDAO) {
		this.expFiscaDAO = expFiscaDAO;
	}

	// INICIO LLRB
	public T10373DocAdjReqDAO getT10373DocAdjReqDAO() {
		return t10373DocAdjReqDAO;
	}

	public void setT10373DocAdjReqDAO(T10373DocAdjReqDAO t10373DocAdjReqDAO) {
		this.t10373DocAdjReqDAO = t10373DocAdjReqDAO;
	}
	// FIN LLRB

	// INICIO LLRB 17012020
	public T10458VersDocAdjDAO getT10458VersDocAdjDAO() {
		return t10458VersDocAdjDAO;
	}

	public void setT10458VersDocAdjDAO(T10458VersDocAdjDAO t10458VersDocAdjDAO) {
		this.t10458VersDocAdjDAO = t10458VersDocAdjDAO;
	}

	public T10459AccepExpVirtDAO getT10459AccepExpVirtDAO() {
		return t10459AccepExpVirtDAO;
	}

	public void setT10459AccepExpVirtDAO(T10459AccepExpVirtDAO t10459AccepExpVirtDAO) {
		this.t10459AccepExpVirtDAO = t10459AccepExpVirtDAO;
	}

	public T10461SolDesDAO getT10461SolDesDAO() {
		return t10461SolDesDAO;
	}

	public void setT10461SolDesDAO(T10461SolDesDAO t10461SolDesDAO) {
		this.t10461SolDesDAO = t10461SolDesDAO;
	}

	public T10460VincExpVirtDAO getT10460VincExpVirtDAO() {
		return t10460VincExpVirtDAO;
	}

	// Inicio CVG
	public void setT10460VincExpVirtDAO(T10460VincExpVirtDAO t10460VincExpVirtDAO) {
		this.t10460VincExpVirtDAO = t10460VincExpVirtDAO;
	}
	// Fin CVG

	// Inicio - [avilcan]
	@Override
	public List<T6613DocExpVirtBean> obtenerDocumentosExp(Map<String, Object> mapParametrosBusqueda) throws Exception {
		List<T6613DocExpVirtBean> listT6613DocExpVirt = null;

		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerDocumentosExp");

		try {
			listT6613DocExpVirt = t6613DocExpVirtDAO.obtenerDocumentosExp(mapParametrosBusqueda);

		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.obtenerDocumentosExp");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.obtenerDocumentosExp");
			NDC.pop();
			NDC.remove();
		}

		return listT6613DocExpVirt;
	}
	// Fin - [avilcan]
	
	// Inicio - [avilcan]
	@Override
	public int verficarDocumentoAsociado(Map<String, Object> parametros) throws Exception {
		int resultado = 0;
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.verficarDocumentoAsociado");

		try {
			resultado = t6623TipDocExpDAO.verficarDocumentoAsociado(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.verficarDocumentoAsociado");
			log.error(ex, ex);
			log.error("==========");
			log.error("Error:" + ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.verficarDocumentoAsociado");
			NDC.pop();
			NDC.remove();
		}
		return resultado;
	}
	// Fin - [avilcan]

	// Inicio - [avilcan]
	@Override
	public void insertarDocumento(T6614ExpVirtualBean t6614BeanDes, T6613DocExpVirtBean t6613BeanOri) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inicio - ExpedienteVirtualServiceImpl.insertarDocumento");
		log.debug("codTipdoc2" +  t6613BeanOri.getCodTipDoc());
		log.debug("codTipExp" + t6614BeanDes.getCodTipoExpediente());
		Map<String, Object> beanDocExp = null;
		Long numSeq = null;
		Long numCordoc = null;
		Calendar fechaVacia = null;
		try {
			// Crear el documento
			beanDocExp = new HashMap<String, Object>();
			numSeq = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			
			log.debug("numSeq=>" + numSeq);
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			numCordoc = numSeq;
			beanDocExp.put("numCordoc", numCordoc);
			beanDocExp.put("numExpeDv", t6614BeanDes.getNumExpedienteVirtual() );
			beanDocExp.put("codTipdoc",  t6613BeanOri.getCodTipDoc());
			beanDocExp.put("codTipExp", t6614BeanDes.getCodTipoExpediente());
			beanDocExp.put("numRequerim", t6613BeanOri.getNumRequerimiento() );			
			beanDocExp.put("codEstDocReq", t6613BeanOri.getEstadoDocumentoReq()); //recuperar de t6613BeanOri 
			beanDocExp.put("desMotSolDoc", t6613BeanOri.getDesMotsoldoc() ); //recuperar de t6613BeanOri
			beanDocExp.put("numDoc", t6613BeanOri.getNumDoc());
			beanDocExp.put("fecDoc",  t6613BeanOri.getFecDoc());
			beanDocExp.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST );// Constante 04 
			beanDocExp.put("codUsuCarg", t6613BeanOri.getCodUsucarga() );
			beanDocExp.put("fecCarg", new Date());
			beanDocExp.put("codUsuRegis",  t6613BeanOri.getCodUsuarioRegistro());
			beanDocExp.put("fecRegis", new Date());
			beanDocExp.put("codIdecm", t6613BeanOri.getCodIdentificadorECM() );
			beanDocExp.put("codOrigDoc", t6613BeanOri.getCodOrigenDocuento() );
			beanDocExp.put("indVisible", t6613BeanOri.getIndVisible() );
			beanDocExp.put("numCordocRel", ValidaConstantes.IND_NUM_COR_DOC_REL); //constante 0 
			beanDocExp.put("desArch", t6613BeanOri.getDescripcionArchivo() );			
			beanDocExp.put("fecNotif", t6613BeanOri.getFecNotif());
			beanDocExp.put("codForNotif", t6613BeanOri.getCodForNotif());
			beanDocExp.put("codEstDoc", t6613BeanOri.getCodEstDoc());
			beanDocExp.put("desEliDoc", t6613BeanOri.getDesEliDoc ());		
			beanDocExp.put("desEliDoc", t6613BeanOri.getDesEliDoc ());
			beanDocExp.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
			beanDocExp.put("fecModif", fechaVacia.getTime());			
			beanDocExp.put("indReserTrib", t6613BeanOri.getIndReserTrib());
			t6613DocExpVirtDAO.insertar(beanDocExp);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - ExpedienteVirtualServiceImpl.insertarDocumento");
			log.error(ex, ex);
			log.error("==========");
			log.error("Error:" + ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - ExpedienteVirtualServiceImpl.insertarDocumento");
			NDC.pop();
			NDC.remove();
		}
	}
	// Fin - [avilcan]

	/*
	 * @Override public T6613DocExpVirtBean
	 * validarDocumentoExpedienteDestino(Map<String, Object> parametros) throws
	 * Exception { T6613DocExpVirtBean t6613DocExpVirtBean= new
	 * T6613DocExpVirtBean(); try { t6613DocExpVirtBean=
	 * t6613DocExpVirtDAO.validarDocumentoExpedienteDestino(parametros); } catch
	 * (Exception ex) { if (log.isDebugEnabled()) log.
	 * debug("Error - ExpedienteVirtualServiceImpl.validarDocumentoExpedienteDestino"
	 * ); log.error(ex, ex); log.error("==========");
	 * log.error("Error:"+ex.getMessage()); throw ex; } finally { if
	 * (log.isDebugEnabled()) log.
	 * debug("Final - ExpedienteVirtualServiceImpl.validarDocumentoExpedienteDestino"
	 * ); NDC.pop(); NDC.remove(); } return t6613DocExpVirtBean; }
	 */

}