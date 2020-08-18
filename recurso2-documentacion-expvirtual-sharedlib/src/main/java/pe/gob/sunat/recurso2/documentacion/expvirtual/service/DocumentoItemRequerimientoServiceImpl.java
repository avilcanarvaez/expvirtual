package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.transaction.UserTransaction;
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
import org.springframework.web.client.RestTemplate;


import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Containable;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.CustomObject;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Document;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.exception.EngineRuntimeException;
//import pe.gob.sunat.framework.core.bean.ParamBean;
import pe.gob.sunat.framework.core.pattern.ServiceLocator;

import pe.gob.sunat.framework.spring.util.buzon.bean.BeMensajeAppBean;
import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpediDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.SeguimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10372DetRequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10373DocAdjReqDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T4486DAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AlmacenFirmas;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SignerFactory;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
/*LLROMERO*/
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CEUtil;
import es.realsec.tsasdk.PdfSignerTimestamperSDK;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CEConnection;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;

import pe.gob.sunat.framework.util.Propiedades;


public class DocumentoItemRequerimientoServiceImpl implements DocumentoItemRequerimientoService {
	private static final Log log = LogFactory.getLog(DocumentoItemRequerimientoServiceImpl.class);
	
	private ResponsableService respService;
	private CorreosService correosService;
	private T6620RequerimDAO t6620RequerimDAO;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private SequenceDAO sequenceDAO;
	private String generaReporteURLService;	
	private EcmService ecmService;
	private T10372DetRequerimDAO t10372DetRequerimDAO;
	private T10373DocAdjReqDAO t10373DocAdjReqDAO;
	private DualDAO dualDAO;
	private String uploadReporteURLService;
	private ExpediDAO expediDAO;
	private SeguimDAO seguimDAO;
	private T02PerdpDAO t02PerdpDAO;
	
	private CatalogoService catalogoService; //PAS20201U210500029 - HHC
	private ValidarParametrosService validarParametrosService;//PAS20201U210500029 - HHC
	
	private static final String VAR_PROPERTIES_FILENAME = "/generador.properties";
	private static final String PDF = ".pdf";
	private static final String TYPE = "X.509";
	private T01ParamDAO t01ParamDAO;
	
	/*LLROMERO*/
	
	private Connection con;
	private Domain dom;
	private String domainName;
	private ObjectStoreSet ost;
	private Vector osnames;
	private boolean isConnected;
	private UserContext uc;

	/*public DocumentoItemRequerimientoServiceImpl() {
		String  docClass= "Document";
		Document doc;
		con = null;
		uc = UserContext.get();
		dom = null;
		domainName = null;
		ost = null;
		osnames = new Vector();
		isConnected = false;
	}*/

	private static Propiedades propiedades = new Propiedades(
			DocumentoItemRequerimientoServiceImpl.class, VAR_PROPERTIES_FILENAME);
	
	public List<T10373DocAdjReqBean> obtenerListaDocAdj(Map<String, Object> mapParametrosBusqueda) throws Exception {		
		
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoItemRequerimientoServiceImpl.obtenerListaDocAdj");
		
		List<T10373DocAdjReqBean> lstDocAdjItem = new ArrayList<T10373DocAdjReqBean>();
		int numOrden = 0;
		
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			lstDocAdjItem = t10373DocAdjReqDAO.obtenerListaDocAdj(mapParametrosBusqueda);
			
			for (T10373DocAdjReqBean docAdjItem : lstDocAdjItem) {
				if (log.isDebugEnabled()) log.debug("Inicio - peso"+docAdjItem.getCntTamArch());
				String[] listaSeparada = docAdjItem.getNomArchAdj().split("\\.");
				String extensionArchivo = listaSeparada[listaSeparada.length - 1];
				String tipoMime = Utils.obtenerTipoMime(extensionArchivo.trim());
				numOrden = numOrden + 1;
				
				docAdjItem.setNumOrden(numOrden);
				docAdjItem.setEsNuevo("0");
				docAdjItem.setMimeType(tipoMime);
				docAdjItem.setTamArch(docAdjItem.getCntTamArch());
				docAdjItem.setFolios(docAdjItem.getNumFolios());
			}			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DocumentoItemRequerimientoServiceImpl.obtenerListaDocAdj");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoItemRequerimientoServiceImpl.obtenerListaDocAdj");
			
			NDC.pop();
			NDC.remove();
		}
		
		return lstDocAdjItem;
	}
	
	private Long generaSecuenciaEscrito() {
		
		Long secuencia;
		//Secuencia generada en sistemas administrativos para Escrito ElectrÃ³nico
		DataSourceContextHolder.setKeyDataSource("dxprad1");				
		secuencia = dualDAO.getNextSequence();
		log.debug("secuencia: "+secuencia);
		
		
		return secuencia;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public String adjuntarDocumentosDetRequerimiento(Map<String, Object> parametros) throws Exception{
				
		if (log.isDebugEnabled()) log.debug("Inicio -  DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetRequerimiento"); 
		
		Map<String, Object>	mapDocumentoExpediente;
		Map<String, Object> documentoPresentados;
		Map<String, Object> mapConsultaCorreo;
		Map<String, Object>	mapEnvioCorreo;
		Map<String, Object> beanEcm;
		Map<String, Object> mapRegTramDoc;
		Map<String, Object> mapParametros;
		
		StringBuilder listaDocumentos = new StringBuilder();
		List<Map<String, Object>> listaDocumentosASubir;
		List<Map<String, Object>> listaDocumentosASubirEcm;
		List<Map<String, Object>> listaDetRequerim;
		List<CorreosBean> listaCorreosBeans;
		List<T6621RespExpVirtBean> listaRespAsignados;
		List<Map<String, Object>> listaDocumentosEnvio;		
		String numExpedienteVirtual;
		String numExpedienteOrigen;
		String numRequerimientOrigen;
		String usuarioCarga;
		String mensaje;
		String codTipoExpediente;
		String codTipDoc;
		String razonSocial;
		String desTipoExpediente;
		String fechaVencimiento; 
		String codUUOOSuperv;
		String codSuperv;
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
		String numRequerimiento="";
		Calendar fechaVacia = null;
		/*LLROMERO*/
		File nomArchivo;
		T01ParamBean t01ParamBean;
		
		Calendar fechaActual = null;
		String anio;
		String anioCorto;
		Long numSec;
		int tamSecEscElect = 0;
		String numSecEscElect;
		String numEscElectonico;
		T10373DocAdjReqBean t10373DocAdjReqBean;
		Integer foliosTotal;
		Integer fechaTramDoc;
		String horaTramDoc;
		Integer numEscrito;
		UserTransaction tx = null;
		
		
		Map params = new HashMap();
        Map certificado = null;
        Map mldap = null;
        String alias= null;
        T01ParamBean paramBean ;
        String extension=null;
		try {
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			error = "";
			anio = "";
			// Fecha actual
			fechaActual = Calendar.getInstance();	
			anio = Utils.toStr(fechaActual.get(Calendar.YEAR));
			anioCorto = anio.substring(2, 4);
			log.debug("anioCorto: "+anioCorto);
			
			fechaTramDoc = Utils.toInteger(new FechaBean().getAnho() + new FechaBean().getMes() + new FechaBean().getDia());
			log.debug("fechaTramDoc: "+fechaTramDoc+" - Calendar.Date(): "+Calendar.DATE);
			horaTramDoc = Utils.toStr(new FechaBean().getHora24() + ":" + new FechaBean().getMinuto());
			
			listaDocumentosASubir = (List<Map<String, Object>>) parametros.get("listaDocumentosASubir");
			listaDetRequerim = (List<Map<String, Object>>) parametros.get("listaDetRequerim");
			numExpedienteVirtual = Utils.toStr(parametros.get("numExpedienteVirtual"));
			numExpedienteOrigen = Utils.toStr(parametros.get("numExpedienteOrigen"));
			numRequerimientOrigen = Utils.toStr(parametros.get("numRequerimientOrigen"));
			usuarioCarga = Utils.toStr(parametros.get("codUsuarioCarga"));
			numRequerimiento = Utils.toStr(parametros.get("numRequerimiento"));
			codTipoExpediente = Utils.toStr(parametros.get("codTipoExpediente"));
			codTipDoc = Utils.toStr(parametros.get("codTipDoc"));
			razonSocial = !Utils.isEmpty(Utils.toStr(parametros.get("razonSocial")))? Utils.toStr(parametros.get("razonSocial")):ValidaConstantes.CADENA_VACIA;
			desTipoExpediente = Utils.toStr(parametros.get("desTipoExpediente"));
			fechaVencimiento = Utils.toStr(parametros.get("fechaVencimiento"));
			codUUOOSuperv = Utils.toStr(parametros.get("codUUOOSuperv"));
			codSuperv = Utils.toStr(parametros.get("codSuperv"));
			SimpleDateFormat formateador = new SimpleDateFormat("EEEEEEEEE',' dd 'de' MMMMM 'del' yyyy");
			String fechaFormateada = "";
						
			//Obtenemos secuencia para numero de Escrito ElectrÃ³nico
			numSec = generaSecuenciaEscrito();
			numSecEscElect = "000000" + numSec.toString();
			tamSecEscElect = numSecEscElect.length(); 
			numSecEscElect = numSecEscElect.substring(tamSecEscElect - 6, tamSecEscElect);
			log.debug("numSecEscElect: "+numSecEscElect);
			
			//Numero de Escrito ElectrÃ³nico formateado
			numEscElectonico = anioCorto + ValidaConstantes.DESC_URD + numSecEscElect;
			
			//Obtenemos los responsables del expediente
			listaRespAsignados = respService.obtenerResponsablesAsignados(numExpedienteVirtual.trim());
								    
		    /******** valores para sello de tiempo*****/
           //   String codApliPadron =  "NSOL";
				Map<String, Object> mapParam = new HashMap<String, Object>();
    			mapParam.put("codClase", CatalogoConstantes.COD_NOTIFICA_BUZON);
    			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
    			mapParam.put("codParametro", CatalogoConstantes.COD_ARGUMENTO_BUZON);
    			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
    			paramBean = t01ParamDAO.obtener(mapParam);
    			
    			String codApliPadron =  paramBean.getDesParametro().substring(37, 41);//nsol
    			if(log.isDebugEnabled())log.debug("0. codApliPadron:"+codApliPadron);
				params.put("codApli",codApliPadron.toUpperCase().trim());
                T4486DAO t4486DAO = new T4486DAO(ServiceLocator.getInstance().getDataSource("jdbc/dcbditsgme"));
                certificado = t4486DAO.findByCodAplic(params);
                
                if(log.isDebugEnabled())log.debug("0. certificado:"+certificado);
				alias = (String) certificado.get("cod_pin");
                
				long timeoutCache = Long.parseLong(propiedades.leePropiedad("tiempoExpiracionAlmacen"));
				 AlmacenFirmas almacenFirmas = AlmacenFirmas.getInstance();
	             mldap = almacenFirmas.getStore(alias, timeoutCache);
	             if(log.isDebugEnabled())log.debug("3. mldap:"+mldap);
	             SignerFactory signerFactory = new SignerFactory();
                if (mldap == null){
                    if(log.isDebugEnabled())log.debug("1. cargando certificado al cache de almacen");
                    mldap = signerFactory.getCertificadoDigital(alias);
                    almacenFirmas.setStore(alias, mldap);
                }else{
                    if(log.isDebugEnabled())log.debug("2. cargando certificado desde el cache de almacen");
                    X509Certificate cert = (X509Certificate)mldap.get("certificado");
                    try {
                        cert.checkValidity();
                    } catch (CertificateException e){
                        mldap = signerFactory.getCertificadoDigital(alias);
                        almacenFirmas.setStore(alias, mldap);
                    } finally {
                    }

                }
				
                mapParam = new HashMap<String, Object>();
    			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_SELLO_TIME);
    			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
    			mapParam.put("codParametro", CatalogoConstantes.COD_ARGUMENTO_SELLO);
    			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
    			paramBean = t01ParamDAO.obtener(mapParam);
                String ipTSA = paramBean.getDesParametro();
                if(log.isDebugEnabled())log.debug(ipTSA);
                mldap.put((String)"url", ipTSA);
                
                if(log.isDebugEnabled())log.debug("4. mldap:"+mldap);
			/************/
						
			/**************************************************************/
			/**ENVIAMOS LOS ARCHIVOS AL ECM Y REGISTRAMOS T10373DOCADJREQ**/
			/**************************************************************/			
			serializer = new JsonSerializer();
			listaDocumentosEnvio = new ArrayList<Map<String, Object>> ();
			foliosTotal = 0;
			listaDocumentosASubirEcm = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : listaDocumentosASubir) {
				
				documentoPresentados = new HashMap<String, Object>();
				numRequerimiento = Utils.toStr(map.get("numRequerimiento"));
				Integer numItem = Utils.toInteger(map.get("numItem"));
				String numArchivo = Utils.toStr(map.get("numOrden"));
				Integer folios =  Utils.toInteger(map.get("folios"));;
				String numArchivoFormato;
				foliosTotal = foliosTotal + folios;
				
				if (numArchivo.length() == 2) {
					numArchivo = "" +Utils.toStr(numArchivo);
				} else {
					numArchivo = "0" + Utils.toStr(numArchivo);
				}
				numArchivoFormato = numEscElectonico.trim() + numArchivo.trim();
				
				String nombreArchivo = Utils.convertirUnicode(Utils.toStr(map.get("nomArchAdj"))); //validar unicode ie8
				log.info("***********Nombre Archivo (Utils.toStr(map.get(nomArchAdj))*********************"+nombreArchivo);
				
				archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento+"/"+numItem+"/"+nombreArchivo);
				mimeType = Utils.toStr(map.get("mimeType"));
				/*LLROMERO docClass
				String  docClass= "Document";
				Document doc;*/
				
				if(log.isDebugEnabled())log.debug("adjuntarDocumentosDetEscritos:"+mimeType);
				//LUEGO QUITAR : FIRMA
				extension = Utils.obtenerExtension(nombreArchivo);
				if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
					
					String ruta =ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento+"/"+numItem+"/";
					archivo = this.sign(archivo, mldap,ruta);
				}


				/*FileInputStream fis = new FileInputStream(archivo);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024];
		            for (int readNum; (readNum = fis.read(buf)) != -1;) {
		                bos.write(buf, 0, readNum); 
		            }
		            
		        byte[] data = bos.toByteArray();
		        
				//llenamos el bean ECM
				beanEcm = new HashMap<String, Object>();
				beanEcm.put("nomArchivoSinExt", nombreArchivo.substring(0, nombreArchivo.lastIndexOf(".")));
				beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
				beanEcm.put("numRuc", parametros.get("numRuc"));
				beanEcm.put("numDoc", numEscElectonico.trim());
				beanEcm.put("fecEmi", new Date());
				beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
				beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_ESCRITO);				
				beanEcm.put("archLength", archivo.length());
				beanEcm.put("archMimeType", mimeType);
				beanEcm.put("archFileName", nombreArchivo);
				beanEcm.put("archContent", data);
			
				palabrasClave = palabrasClave != null ? palabrasClave : "";  
				beanEcm.put("metadata", palabrasClave);
				beanEcm.put("codDep", parametros.get("codDep"));
				beanEcm.put("codDepOri", parametros.get("codDep"));
				codIdecm = ecmService.guardarDocumentoECM(beanEcm);    //produccion
//				codIdecm = "idd_40BB8B6F-0000-C819-AE90-94F6F1EC5448";// local
				
				if(codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
					error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
					return error;
				}*/
				/*LLROMERO*/
				/*LLROMERO docClass*/
				
				
				//ce = c;
				
				/*LIBRERIA JAR ECM*/
				 /*conexion al ECM*/
				
				con = null;
				uc = UserContext.get();
				dom = null;
				domainName = null;
				ost = null;
				osnames = new Vector();
				isConnected = false;
				
				Map<String, Object> mapT01Param = new HashMap<String, Object>();
                String  docClass= "Document";
                Document doc;
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_URL_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String uri = t01ParamBean.getDesParametro().trim();
                log.debug("uri:"+uri);
                
                mapT01Param = new HashMap<String, Object>();
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_USER_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String userName = t01ParamBean.getDesParametro().trim();
                log.debug("username:"+userName);
                
                mapT01Param = new HashMap<String, Object>();
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_USER_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String password = t01ParamBean.getDesParametro().trim();
                log.debug("password:"+password);
				
				/*String uri = ValidaConstantes.RUTA_SERVIDOR_ECM;
				log.debug("uri:"+uri);
				String userName = ValidaConstantes.USERNAME_ECM;
				log.debug("username:"+userName);
				String password = ValidaConstantes.PASSWORD_ECM;
				log.debug("password:"+password);*/
				String stanza = ValidaConstantes.JAAS_STANZA_ECM ;
				log.debug("stanza:"+stanza);
				//ce.establishConnection(username,password,stanza,uri);
				con = Factory.Connection.getConnection(uri);
		        log.debug("conFactory:"+con);
		        Subject sub = UserContext.createSubject(con,userName,password,stanza);
		        log.debug("subConection:"+sub);
		        uc.pushSubject(sub);
		        //dom = fetchDomain();
		        dom = Factory.Domain.fetchInstance(con, null, null);
		        log.debug("domConection:"+dom);
		        domainName = dom.get_Name();
		        log.debug("domainName:"+domainName);		       
		        ost = dom.get_ObjectStores();
		        log.debug("ostOBject:"+ost);
		        isConnected = true;
		        log.debug("isConnected:"+isConnected);
				
				/* CREANDO EL DOCUMENTO */
				
				String ecm= "ECM";
				log.debug("ecm:"+ecm);
				log.debug("archivoCreando:"+archivo);
				log.debug("mimeTypeCreando:"+mimeType);
				log.debug("nombreArchivoCreando:"+nombreArchivo);
				log.debug("docClassCreando:"+docClass);
				//ObjectStore os = ce.fetchOS(ecm);
				ObjectStore os = Factory.ObjectStore.fetchInstance(dom, ecm, null);
		        log.debug("ObjectStoreFet:"+os);
				String fPath = ValidaConstantes.FILE_FOLDER_ECM;
				log.debug("fPath:"+fPath);
				doc = CEUtil.createDocWithContent(archivo, mimeType, os, nombreArchivo, docClass);
				log.debug("docUtil:"+doc);
    			doc.save(RefreshMode.REFRESH);
    			log.debug("Documento se ha grabado");
    			ReferentialContainmentRelationship rcr = CEUtil.fileObject(os, doc, fPath);
    			log.debug("referential:"+rcr);
    			rcr.save(RefreshMode.REFRESH);
    			log.debug("Files se ha grabado");
    			

    			/*OBTENIENDO LOS DATOS QUE SE GUARDO EN EL ECM */
    			String path = fPath+nombreArchivo;
    			log.debug("path:"+path);
    			doc = fetchDocByPath(os, path);
    			log.debug("docpath:"+doc);
    			HashMap mapGetEcm = getContainableObjectProperties(doc);
    			   log.debug("MapECm:"+mapGetEcm);

        			String id = Utils.toStr(mapGetEcm.get("Id"));
        			log.debug("id:"+id);
    				String name = Utils.toStr(mapGetEcm.get("Name"));
    				log.debug("name:"+name);
    				String usuario = Utils.toStr(mapGetEcm.get("Creator"));
    				log.debug("usuario:"+usuario);
    				String owner = Utils.toStr(mapGetEcm.get("Owner"));
    				log.debug("owner:"+owner);
				
				Map<String, Object> mapEcm = new HashMap<String, Object>();
				mapEcm.put("numRequerimiento",numRequerimiento);
				mapEcm.put("numItem",numItem);
				mapEcm.put("numOrden",numArchivo);
				mapEcm.put("folios",folios);
				//mapEcm.put("codIdecm",codIdecm);
				mapEcm.put("codIdecm",id);
				mapEcm.put("nomArchAdj",nombreArchivo);
				mapEcm.put("numOrden",Utils.toInteger(map.get("numOrden")));
//				mapEcm.put("tamArch",Utils.toInteger(map.get("tamArch")));
				mapEcm.put("tamArch",archivo.length());
				
				listaDocumentosASubirEcm.add(mapEcm);
				
				double pesoActual=archivo.length();
				double pesoKB=pesoActual/1024;
				double pesoDecimal=Utils.round(pesoKB,2);
				String pesoFinal=Utils.toStr(pesoDecimal)+" KB" ;
				
				//guardamos la lista de documentos para el envio de mensaje a los responsables
				listaDocumentos.append("			<tr>");
				listaDocumentos.append("				<td>"+Utils.toStr(numArchivoFormato)+"</td>");
				listaDocumentos.append("				<td>"+Utils.escapeHTML(Utils.toStr(nombreArchivo))+"</td>");
				listaDocumentos.append("				<td>"+Utils.toStr(map.get("numItem"))+"</td>");
//				listaDocumentos.append("				<td>"+Utils.toStr(map.get("cntTamArch"))+"</td>");
				listaDocumentos.append("				<td>"+Utils.toStr(pesoFinal)+"</td>");
				listaDocumentos.append("				<td>"+Utils.toStr(folios)+"</td>");
				listaDocumentos.append("			</tr>");
				
				//generamos la lista de documentos a llenar en la constancia de envio								
				documentoPresentados.put("numArchivo", numArchivoFormato);
				documentoPresentados.put("nombreArchivo", Utils.convertirUnicode(nombreArchivo));
				documentoPresentados.put("item", Utils.toStr(map.get("numItem")));
//				documentoPresentados.put("tamaÃ±oArchivo", Utils.toStr(map.get("cntTamArch")));
				documentoPresentados.put("tamaÃ±oArchivo", Utils.toStr(pesoFinal));
				documentoPresentados.put("folios", Utils.toStr(folios));
				listaDocumentosEnvio.add(documentoPresentados);
				archivo.delete();
			}			
			
			 tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
	         tx.begin();
	         
			for (Map<String, Object> map : listaDocumentosASubirEcm) {
				
				numRequerimiento = Utils.toStr(map.get("numRequerimiento"));
				Integer numItem = Utils.toInteger(map.get("numItem"));
				String numArchivo = Utils.toStr(map.get("numOrden"));
				Integer folios =  Utils.toInteger(map.get("folios"));;
				String numArchivoFormato;
				
				if (numArchivo.length() == 2) {
					numArchivo = "" +Utils.toStr(numArchivo);
				} else {
					numArchivo = "0" + Utils.toStr(numArchivo);
				}
				numArchivoFormato = numEscElectonico.trim() + numArchivo.trim();
				
				String nombreArchivo = Utils.convertirUnicode(Utils.toStr(map.get("nomArchAdj"))); //validar unicode ie8
				log.info("***********Nombre Archivo (Utils.toStr(map.get(nomArchAdj))*********************"+nombreArchivo);
				
				/*******************************/
				/**REGISTRAMOS T10373DOCADJREQ**/
				/*******************************/
				t10373DocAdjReqBean = new T10373DocAdjReqBean();
				t10373DocAdjReqBean.setNumArchAdj(0);
				t10373DocAdjReqBean.setNumRequerimiento(numRequerimiento);
				t10373DocAdjReqBean.setNumItem(numItem);
				t10373DocAdjReqBean.setNumSubItem(1);
				t10373DocAdjReqBean.setNumArcItem(Utils.toInteger(map.get("numOrden")));
				t10373DocAdjReqBean.setNomArchAdj(nombreArchivo);
				t10373DocAdjReqBean.setNumArchivo(numArchivoFormato);
				t10373DocAdjReqBean.setCntTamArch(Utils.toInteger(map.get("tamArch")));
				t10373DocAdjReqBean.setNumFolios(folios);
				t10373DocAdjReqBean.setCodTipAlm(ValidaConstantes.COD_TIPDOC_ALM);
				t10373DocAdjReqBean.setCodIdECM(Utils.toStr(map.get("codIdecm")));
				t10373DocAdjReqBean.setNumCorDoc(0);
				t10373DocAdjReqBean.setCodOriDoc(Utils.toStr(parametros.get("codOrigDoc")));
				t10373DocAdjReqBean.setCodUsuRegis(usuarioCarga);
				t10373DocAdjReqBean.setFechaRegistro((new FechaBean()).getSQLDate());
				t10373DocAdjReqBean.setCodUsuMod(usuarioCarga);
				t10373DocAdjReqBean.setFechaMod((new FechaBean()).getSQLDate());
				
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
				t10373DocAdjReqDAO.insertar(t10373DocAdjReqBean);
				
			}
			
			/****************************************************************/
			/**REGISTRAMOS EL ESCRITO ELECTRONICO EN EL EXPEDIENTE VIRTUAL **/
			/****************************************************************/
			Map<String, Object> paramBusqueda = new HashMap<String, Object>();
			T6613DocExpVirtBean buscarDatoDocumento = new T6613DocExpVirtBean();
			
			paramBusqueda.put("numCorDocRel", ValidaConstantes.FILTRO_NUM_CORDOCREL);
			paramBusqueda.put("numExpeDv", numExpedienteVirtual);
			paramBusqueda.put("numDoc", numRequerimientOrigen);
			paramBusqueda.put("codTipDoc", codTipDoc);
			
			buscarDatoDocumento = t6613DocExpVirtDAO.obtenerDocOrigen(paramBusqueda);
			
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			numDocumento = Integer.valueOf(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM)));
			numEscrito = numDocumento;
			String descArchivoEscrito = Utils.toStr(numEscElectonico) + "_" +ValidaConstantes.COD_TIPO_DOCU_ESCRITO + "_" +parametros.get("numRuc").toString().trim();
			
			t6613DocExpVirtBean.setNumCorDoc(numDocumento);
			t6613DocExpVirtBean.setNumExpedienteVir(numExpedienteVirtual);
			t6613DocExpVirtBean.setCodTipExp(codTipoExpediente);
			t6613DocExpVirtBean.setCodTipDoc(ValidaConstantes.COD_TIPO_DOCU_ESCRITO);			
			t6613DocExpVirtBean.setNumDoc(Utils.toStr(numEscElectonico));
			t6613DocExpVirtBean.setFecDoc(new Date());
			t6613DocExpVirtBean.setDescripcionArchivo(Utils.toStr(descArchivoEscrito)+".pdf");
			t6613DocExpVirtBean.setNumRequerimiento(numRequerimiento.trim());
			t6613DocExpVirtBean.setFechaCarga(new Date());			
			t6613DocExpVirtBean.setCodIdentificadorECM("0");			
			t6613DocExpVirtBean.setCodUsuarioRegistro(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setFecRegis(new Date());
			t6613DocExpVirtBean.setCodOrigenDocuento(Utils.toStr(parametros.get("codOrigDoc")));			
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setDesMotsoldoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setEstadoDocumentoReq("01");
			t6613DocExpVirtBean.setIndVisDocumento(ValidaConstantes.IND_VISIBLE_DOC);
			t6613DocExpVirtBean.setNumDocRelacionado(Utils.toStr(buscarDatoDocumento.getNumCorDoc()));
			t6613DocExpVirtBean.setFecNotif(fechaVacia.getTime());
			t6613DocExpVirtBean.setCodForNotif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_REGISTRADO);
			t6613DocExpVirtBean.setDesEliDoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodUsuModif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setFecModif(new Date());
			
			mapDocumentoExpediente = new HashMap<String, Object>();
			mapDocumentoExpediente.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
			t6613DocExpVirtDAO.insertarManual(mapDocumentoExpediente);
			
			/****************************************************************************/
			/**REGISTRAMOS EL ESCRITO ELECTRONICO EN EL SISTEMA DE TRAMITE DOCUMENTARIO**/
			/****************************************************************************/
			mapRegTramDoc = new HashMap<String, Object>();
			mapParametros = new HashMap<String, Object>();
			
			String nombre;
			mapParametros.put("codPersona", codSuperv.trim());
			
			T02PerdpBean supervisor = t02PerdpDAO.obtener(mapParametros);
			
			nombre = supervisor.getDesNombres().trim();
			String[] nomFuncionar = nombre.split(" ");
			mapRegTramDoc.put("dFuncionar", nomFuncionar[0] + " " + supervisor.getDesApellidoPaterno().trim());
			mapRegTramDoc.put("unidOrg", codUUOOSuperv.trim());						
			
			//Datos tabla Expedi
			mapRegTramDoc.put("codAdua", ValidaConstantes.COD_ADUANA_TRAM_DOC);
			mapRegTramDoc.put("oficRec", ValidaConstantes.DESC_URD);
			mapRegTramDoc.put("anoExpedi", anio);
			mapRegTramDoc.put("nroExpedi", numSec);
			mapRegTramDoc.put("digCheck", 0);
			mapRegTramDoc.put("clasePar", ValidaConstantes.CLASE_TRAM_DOC);
			mapRegTramDoc.put("fecExp", fechaTramDoc);
			mapRegTramDoc.put("timeExp", horaTramDoc);
			mapRegTramDoc.put("procedimPar", ValidaConstantes.COD_PROC_TRAM_DOC);
			mapRegTramDoc.put("tipoUser", ValidaConstantes.TIPO_USER_TRAM_DOC);
			mapRegTramDoc.put("nroDoc", parametros.get("numRuc").toString().trim());
			mapRegTramDoc.put("tipoDoc", ValidaConstantes.TIPO_DOC_TRAM_DOC);
			mapRegTramDoc.put("codigoPar", ValidaConstantes.CADENA_VACIA);
			mapRegTramDoc.put("asuntoPar", ValidaConstantes.ASUNTO_EXPEDI_TRAM_DOC + " " +numExpedienteOrigen);
			mapRegTramDoc.put("digUser", parametros.get("codUsuarioCarga"));
			mapRegTramDoc.put("actEstado", ValidaConstantes.EST_EXP_TRAM_DOC);
			mapRegTramDoc.put("actAccion", ValidaConstantes.ACC_EXP_TRAM_DOC);
			
			DataSourceContextHolder.setKeyDataSource("dxprad1");
			expediDAO.insertar(mapRegTramDoc);
			
			//Datos tabla Seguim
			mapRegTramDoc.put("nroSeguim", ValidaConstantes.NRO_SEGUIM_TRAM_DOC);
			mapRegTramDoc.put("foliosPar", foliosTotal);
			mapRegTramDoc.put("cAccion", ValidaConstantes.ACC_EXP_TRAM_DOC);
			mapRegTramDoc.put("cDepOrig", ValidaConstantes.DESC_URD);
			mapRegTramDoc.put("dCargo", ValidaConstantes.CARGO_TRAM_DOC);
			mapRegTramDoc.put("dAsunto", ValidaConstantes.ASUNTO_SEGUIM_TRAM_DOC);
			mapRegTramDoc.put("fSeguim", fechaTramDoc);
			mapRegTramDoc.put("hSeguim", horaTramDoc); 
			mapRegTramDoc.put("dPerRecib", ValidaConstantes.DESC_URD);
			
			//seguimDAO.insertar(mapRegTramDoc);			
			
			/***************************************************************/
			/**GENERAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS - PDF**/
			/***************************************************************/
			FechaBean fechaHoy = new FechaBean();
						
			numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			String listaDetalle = (String) serializer.serialize(listaDocumentosEnvio);
			String 	json = "{"+"\""+"cabecera"+"\":{";
					json+="\""+"numConstancia"+"\":"+"\""+numEscElectonico;
					json+="\","+"\""+"fechaPresentacion"+"\":"+"\""+fechaHoy.getFormatDate("dd/MM/yyyy-HH:mm");
					json+="\","+"\""+"ruc"+"\":"+"\""+Utils.toStr(parametros.get("numRuc"));
					razonSocial = razonSocial.replace("&AMP;", "&"); 
					razonSocial = razonSocial.replace("&amp;", "&");
					json+="\","+"\""+"desRazonSocial"+"\":"+"\""+razonSocial.replace("\"", "\\\"").toUpperCase();
					json+="\","+"\""+"numExpediente"+"\":"+"\""+numExpedienteOrigen;
					json+="\","+"\""+"numRequerimiento"+"\":"+"\""+numRequerimientOrigen;	
					json+="\","+"\""+"desTipoExpediente"+"\":"+"\""+desTipoExpediente.toUpperCase();
					json+="\","+"\""+"fechaVencimiento"+"\":"+"\""+fechaVencimiento;
					json+="\"},\""+"detalle\"" + ":" + listaDetalle;
					json+= "}";
					
			//nchavez 13/06/2016
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
			//generaPDF.setReturnClass(byte[].class);
			generaPDF.setReturnClass(int.class);
			
			//byte[] docConstPresentDocu = (byte[]) (generaPDF.invoke(new Object[]{idDocumento, json, "pdf", modeloPdf, firmaPdf}));
			Integer numeroPdf = Utils.toInteger(generaPDF.invoke(new Object[] { idDocumento, json, "pdf", modeloPdf, firmaPdf }));
			log.debug("numeroPdf: "+numeroPdf);
			byte[] binDoc = ObtenerPDFUnload(numeroPdf);
			
			//String desArchivoPdf = ExportaConstantes.NOMBRE_PLANTILLA_PDF_002_01 + ".pdf";
//			String desArchivoPdf = numeroPdf.toString() + ".pdf";
			
			/*******************************************************************/
			/**GUARDAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS EN EL ECM**/
			/*******************************************************************/
			beanEcm = new HashMap<String, Object>();
			
			beanEcm.put("nomArchivoSinExt", numEscElectonico);
			beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
			beanEcm.put("numRuc", parametros.get("numRuc"));
			beanEcm.put("numDoc", numeroPdf);
			beanEcm.put("fecEmi", new Date());
			beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
			beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO);
			beanEcm.put("archLength", binDoc.length);
			beanEcm.put("archMimeType", "application/pdf");
			beanEcm.put("archFileName", Utils.toStr(numEscElectonico)+".pdf");
			beanEcm.put("archContent", binDoc);
			beanEcm.put("metadata", palabrasClave);
			//codIdecm = ecmService.guardarDocumentoECM(beanEcm); //produccion
			codIdecm = "idd_40BB8B6F-0000-C819-AE90-94F6F1EC5448";  //local
			if(codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
				return error;
			} 
			
			
			
			/********************************************************************************/
			/**ADJUNTAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS AL EXPEDIENTE VIRTUAL**/
			/*******************************************************************************/
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			numDocumento = Integer.valueOf(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM)));
			String descArchivoConstancia = Utils.toStr(numCorrelativo) + "_" +ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO + "_" +parametros.get("numRuc").toString().trim();
			
			t6613DocExpVirtBean.setNumCorDoc(numDocumento);
			t6613DocExpVirtBean.setNumExpedienteVir(numExpedienteVirtual);
			t6613DocExpVirtBean.setCodTipDoc(ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO);
			t6613DocExpVirtBean.setCodTipExp(codTipoExpediente);
			t6613DocExpVirtBean.setNumDoc(Utils.toStr(numEscElectonico));
			t6613DocExpVirtBean.setNumRequerimiento(numRequerimiento);
			t6613DocExpVirtBean.setFechaCarga(new Date());
			t6613DocExpVirtBean.setDescripcionArchivo(Utils.toStr(descArchivoConstancia)+".pdf");
			t6613DocExpVirtBean.setCodIdentificadorECM(codIdecm);			
			t6613DocExpVirtBean.setCodUsuarioRegistro(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setFecRegis(new Date());
			t6613DocExpVirtBean.setCodOrigenDocuento(Utils.toStr(parametros.get("codOrigDoc")));
			t6613DocExpVirtBean.setFecDoc(new Date());
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setDesMotsoldoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setIndVisDocumento(ValidaConstantes.IND_VISIBLE_DOC);
			t6613DocExpVirtBean.setNumDocRelacionado(Utils.toStr(numEscrito));
			t6613DocExpVirtBean.setEstadoDocumentoReq("01");
			t6613DocExpVirtBean.setFecNotif(fechaVacia.getTime());
			t6613DocExpVirtBean.setCodForNotif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_REGISTRADO);
			t6613DocExpVirtBean.setDesEliDoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodUsuModif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setFecModif(new Date());
			
			mapDocumentoExpediente = new HashMap<String, Object>();
			mapDocumentoExpediente.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
			t6613DocExpVirtDAO.insertarManual(mapDocumentoExpediente);
							
			/**********************************/
			/**ACTUALIZAMOS T10373DOCADJREQ**/
			/**********************************/
			Map<String, Object> beanT10373 = new HashMap<String, Object>();
			for (Map<String, Object> mapT10373 : listaDocumentosASubir){
				beanT10373.put("numItem", mapT10373.get("item"));
				beanT10373.put("numRequerimiento", numRequerimiento);
				beanT10373.put("numCorDoc", numEscrito);
				beanT10373.put("codUsuModif", usuarioCarga);
				beanT10373.put("fecModif", new Date());
				t10373DocAdjReqDAO.update(beanT10373);							
			}
			
			/**********************************/
			/**ACTUALIZAMOS T10372DETREQUERIM**/
			/**********************************/
			Map<String, Object> beanT10372 = new HashMap<String, Object>();
			for (Map<String, Object> mapDetReq : listaDetRequerim){
				if (Utils.toInteger(mapDetReq.get("cantArchivos")) > 0) {
					beanT10372.put("codEstadoItemReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_PRESENTADO);
					beanT10372.put("numItem", mapDetReq.get("item"));
					beanT10372.put("numRequerimiento", numRequerimiento);
					beanT10372.put("numCorDoc", numEscrito);
					beanT10372.put("codUsuModif", usuarioCarga);
					beanT10372.put("fecModif", new Date());
					t10372DetRequerimDAO.update(beanT10372);
				} 				
			}
			
			/******************************/
			/**ACTUALIZAMOS T6620REQUERIM**/
			/******************************/
			T6620RequerimBean t6620RequerimBean = new T6620RequerimBean();
			t6620RequerimBean.setNumRequerimiento(Utils.toStr(parametros.get("numRequerimiento")));
			t6620RequerimBean.setCodUsuMod(Utils.toStr(parametros.get("codUsuarioCarga")));
			t6620RequerimBean.setFechaMod(new Date());
			
			for (Map<String, Object> mapDetReq : listaDetRequerim){
				if (Utils.toInteger(mapDetReq.get("cantArchivos")) == 0) {
					t6620RequerimBean.setCodEstadoRequerim(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
					break;
				} else {
					t6620RequerimBean.setCodEstadoRequerim(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO);
				}
			}
			Map<String, Object> mapRequerimiento = new HashMap<String, Object>();
			mapRequerimiento.put("t6620RequerimBean", t6620RequerimBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
			t6620RequerimDAO.update(mapRequerimiento);			
			
			tx.commit();
			
			/*************************************************************************/
			/**ENVIAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS AL CONTRIBUYENTE**/
			/*************************************************************************/
			if (log.isDebugEnabled()) log.debug("====[ENVIO BUZON]====");
			Map<String, Object> mensajeMap = new HashMap<String, Object>();
			mensajeMap.put("num_ruc",parametros.get("numRuc"));
			mensajeMap.put("rsocial", razonSocial);
			mensajeMap.put("id_PDF_Formulario", Utils.toStr(numeroPdf));
			mensajeMap.put("numConstancia",numCorrelativo);
			if (log.isDebugEnabled()) log.debug("====[PARAMETROS PARA ENVIO BUZON: " + mensajeMap.toString() + "]====");
			enviarMensajeBuzon(mensajeMap);
			
			if (log.isDebugEnabled()) log.debug("Fin -  ExpedienteVirtualServiceImpl.adjuntarDocumentosRequerimiento"); 
			error = ValidaConstantes.NO_ERROR;
			
			/*****************************************************************/
			/**ENVIAMOS UN CORREO A TODOS LOS RESPONSABLES DEL REQUERIMIENTO**/
			/*****************************************************************/			
			// i)construimos el Mensaje
			String asunto = ValidaConstantes.ASUNTO_MENSAJE_RESP
			+" RUC: "+Utils.toStr(parametros.get("numRuc"))+" - RAZÃ“N SOCIAL: "+(razonSocial != null && !Utils.isEmpty(razonSocial) ? razonSocial.trim() : ValidaConstantes.CADENA_VACIA)
			+" - Expediente: "+numExpedienteOrigen+" - Requerimiento: "+numRequerimientOrigen;		
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
			sbMensaje.append("	Fecha de presentaci&oacute;n del escrito: "+fechaHoy.getFormatDate("dd/MM/yyyy")+"</br>");
			sbMensaje.append("	Fecha de Vencimiento            		: "+fechaVencimiento+"</br>");
			sbMensaje.append("	Cantidad de folios            			: "+foliosTotal+"</br>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	El contribuyente ha presentado un escrito electr&oacute;nico conformado por: </br>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	<table style='padding: 10px' width='100%'>");
			sbMensaje.append("		<thead>");
			sbMensaje.append("			<tr>");
			sbMensaje.append("				<th width='25%' align='left'><u>N&uacute;mero de Archivo</u></th>");	
			sbMensaje.append("				<th width='25%' align='left'><u>Nombre de Archivo</u></th>");
			sbMensaje.append("				<th width='10%' align='left'><u>Item</u></th>");
			sbMensaje.append("				<th width='25%'align='left'><u>Tama&ntilde;o de Archivo</u></th>");
			sbMensaje.append("				<th width='15%'align='left'><u>Folios</u></th>");
			sbMensaje.append("			</tr>");
			sbMensaje.append("		</thead>");
			sbMensaje.append("		<tbody>");	
			sbMensaje.append("			"+listaDocumentos);
			sbMensaje.append("		</tbody>");	
			sbMensaje.append("	</table>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	</br>");
			
			fechaFormateada = formateador.format(new Date());
			fechaFormateada=StringUtils.capitalize(fechaFormateada);
			fechaFormateada = fechaFormateada.substring(0,fechaFormateada.indexOf("de")+3)+
					  fechaFormateada.substring(fechaFormateada.indexOf("de")+3,fechaFormateada.indexOf("de")+4).toUpperCase()+
					  fechaFormateada.substring(fechaFormateada.indexOf("de")+4,fechaFormateada.length());
			
			fechaFormateada = Utils.escapeHTML(fechaFormateada);
							  
			sbMensaje.append("	" + fechaFormateada);
			sbMensaje.append("</body>");
			sbMensaje.append("</html>");
			
			mensaje = sbMensaje.toString();			
			
			// ii) obtenemos los correos de los responsables asignados			
			if(!Utils.isEmpty(listaRespAsignados)) {
				mapConsultaCorreo =  new HashMap<String, Object>();
				listaCorreosBeans = new ArrayList<CorreosBean>();
				
				for(T6621RespExpVirtBean t6621RespExpVirtBean : listaRespAsignados) {
					
					correosBean = new CorreosBean();
					correosBean.setCodPers(t6621RespExpVirtBean.getCodColaborador());
					listaCorreosBeans.add(correosBean);
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
					
					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, 
								t6621RespExpVirtBean.getApellidosPaterno() + " " +
								t6621RespExpVirtBean.getApellidosMaterno() + ", " +
								t6621RespExpVirtBean.getNombres()
							);
					
					// iii)Obtenemos la lista de correos del responsable y enviamos el mensaje a cada uno de estos
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);					
				}
				if(!Utils.isEmpty(listaCorreosBeans)){
					for(CorreosBean bean : listaCorreosBeans) {
						
						mapEnvioCorreo = new HashMap<String, Object>();
						mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
						mapEnvioCorreo.put("mensaje", mensaje.toString());
						mapEnvioCorreo.put("asunto", asunto);
						mapEnvioCorreo.put("formatoHTML",1);
						correosService.enviarCorreo(mapEnvioCorreo);
					}
				}
			}

			if (log.isDebugEnabled()) log.debug("Final - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetRequerimiento");			
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetRequerimiento");
			log.error(ex, ex);
			
			tx.rollback();
            if (log.isDebugEnabled()) {log.debug("********* Roll Back *********");}
            error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
			
			return error;
			 
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetRequerimiento");			
		}
		return error;
	}	
	
	public byte[] ObtenerPDFUnload(Integer idPDF){
		
		String urlUnload="";
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
		log.debug("responseEntity: " +responseEntity);
		return responseEntity.getBody();
	}
	
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
	
	private BeMensajeAppBean getMensajeBean(Map<String, Object> mensajeMap) {
		FechaBean fechaHoy = new FechaBean();
		BeMensajeAppBean mensajeBean = new BeMensajeAppBean();
		mensajeBean.setCod_usremisor("SUNAT");
		mensajeBean.setDes_asunto("Constancia de Presentaci&oacute;n de Escritos Electr&oacute;nicos");
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
		str.append("Sr. Contribuyente,"+ "<BR>");		
		str.append("<BR>");
		str.append("La presente Constancia s&oacute;lo acredita los documentos presentados pero no la"+ "<BR>");
		str.append("conformidad de los mismos, lo cual ser&aacute; validado por el responsable del proceso."+ "<BR>");

		mensajeBean.setMsj_mensaje(str.toString());
		if (log.isDebugEnabled()) log.debug("====[CREANDO MENSAJE BUZON]====> " + new JsonSerializer().serialize(mensajeBean).toString());
		return mensajeBean;
	}
	
	/*Sets*/
	
	public void setRespService(ResponsableService respService) {
		this.respService = respService;
	}
	
	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}

	public void setT6620RequerimDAO(T6620RequerimDAO t6620RequerimDAO) {
		this.t6620RequerimDAO = t6620RequerimDAO;
	}
	
	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setGeneraReporteURLService(String generaReporteURLService) {
		this.generaReporteURLService = generaReporteURLService;
	}

	public void setT10372DetRequerimDAO(T10372DetRequerimDAO t10372DetRequerimDAO) {
		this.t10372DetRequerimDAO = t10372DetRequerimDAO;
	}

	public void setT10373DocAdjReqDAO(T10373DocAdjReqDAO t10373DocAdjReqDAO) {
		this.t10373DocAdjReqDAO = t10373DocAdjReqDAO;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setDualDAO(DualDAO dualDAO) {
		this.dualDAO = dualDAO;
	}

	public void setUploadReporteURLService(String uploadReporteURLService) {
		this.uploadReporteURLService = uploadReporteURLService;
	}

	public void setExpediDAO(ExpediDAO expediDAO) {
		this.expediDAO = expediDAO;
	}

	public void setSeguimDAO(SeguimDAO seguimDAO) {
		this.seguimDAO = seguimDAO;
	}

	public void setT02PerdpDAO(T02PerdpDAO t02PerdpDAO) {
		this.t02PerdpDAO = t02PerdpDAO;
	}

	
	//PAS20201U210500029 - HHC INICIO  FALTA IMPLEMENTAR

	@Override
	public List<T6613DocExpVirtBean> obtenerListaDocumentosEscritos(Map<String, Object> mapParametrosBusqueda) throws Exception {
		// TODO Auto-generated method stub
		List<T6613DocExpVirtBean> listT6613DocExpVirtBean= null;
		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoItemRequerimientoServiceImpl.obtenerListaDocumentosEscritos");
		try {
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
			
			Map<String, Object> parametros;
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listT6613DocExpVirtBean = t6613DocExpVirtDAO.listarDocumentosDeEscritos(mapParametrosBusqueda);
			int i=1;
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listT6613DocExpVirtBean) {
			
				DdpBean beanContribuyente = validarParametrosService.validarRUC(t6613DocExpVirtBean.getNumRuc());
				t6613DocExpVirtBean.setDesRazonSocial(!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial().trim():ValidaConstantes.CADENA_VACIA);					

				
				t6613DocExpVirtBean.setNumExpedienteVirtual(t6613DocExpVirtBean.getNumExpedienteVirtual().trim());
				t6613DocExpVirtBean.setNumExpedienteOrigen(t6613DocExpVirtBean.getNumExpedienteOrigen().trim());

				t6613DocExpVirtBean.setDesProceso(Utils.toStr(mapaProcesos.get(t6613DocExpVirtBean.getCodProceso())).toUpperCase());
				t6613DocExpVirtBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(t6613DocExpVirtBean.getCodTipoExpediente())).toUpperCase());
				t6613DocExpVirtBean.setNumRequerimOrigen(t6613DocExpVirtBean.getNumRequerimOrigen().trim());
				t6613DocExpVirtBean.setNumRequerimiento(t6613DocExpVirtBean.getNumRequerimiento().trim());


				i++;
			}
		} catch (Exception ex) {
			// TODO: handle exception
			if (log.isDebugEnabled()) log.debug("Error -  DocumentoItemRequerimientoServiceImpl.obtenerListaDocumentosEscritos");
			log.error(ex, ex);
			throw ex;
		}finally {
			if (log.isDebugEnabled()) log.debug("Final  - DocumentoItemRequerimientoServiceImpl.obtenerListaDocumentosEscritos");
			
			NDC.pop();
			NDC.remove();
		}
		return listT6613DocExpVirtBean;
	}

	
	
	public File getArchivoSalida(String name,String ruta ) 
			throws IOException{
			
			File archivo = File.createTempFile("signed_" + name, PDF, 
							new File(ruta));
			
			return archivo;
	}
	
	public File sign(File archivo, Map mldap, String ruta) throws IOException, DocumentException, Exception {
		Certificate[] chain = new Certificate[1];
		File salida = null;
		String nombrearch = archivo.getName();
		nombrearch = nombrearch.substring(0,nombrearch.length()-4);
		if (log.isDebugEnabled()) log.debug("inicio firma: ");

		try{

			if ((mldap == null) || (archivo == null)) {
				throw new Exception ("Problemas con el certificado digital o el archivo.");
			}

			PrivateKey key = (PrivateKey)mldap.get("pkey");
			chain[0] = (X509Certificate)mldap.get("certificado");

			HashMap metadata = new HashMap();

			/* Verificar que el certificado digital utilizado para realizar la firma se 
			 * encuentre destinado a dicho fin - se le aÃ±ade el tipo certificado*/
			String tipoCert = chain[0].getType();
			if (log.isDebugEnabled()) log.debug("tipoCert: " + tipoCert );

			if (tipoCert != null && tipoCert.equals(TYPE)) {
				//archivo = setTipoCertificado(archivo,nombrearch,tipoCert);
				metadata.put("Tipo Certificado", tipoCert);
			}
		    
		    if (log.isDebugEnabled()) log.debug("firma nombrearch"+nombrearch);
		    if (log.isDebugEnabled()) log.debug("firma: ruta:"+ruta);
			salida = getArchivoSalida(nombrearch,ruta);

			if (log.isDebugEnabled()) log.debug("firma salida:"+salida);
			
            PdfSignerTimestamperSDK objeto = new PdfSignerTimestamperSDK();
            salida = objeto.sign(archivo,mldap,salida);
            if (log.isDebugEnabled()) log.debug("firma: salida:"+salida);
//			archivo.delete();

		} catch (IOException e) {
			if (log.isErrorEnabled()) log.error("Error en sign: " , e);
			throw new IOException (e.getMessage());
		} catch (DocumentException e) {
			if (log.isErrorEnabled()) log.error("Error en sign: " , e);
			throw new DocumentException (e.getMessage());
		} finally {
		}	
		return salida;       
	}
	@Override
	public String adjuntarDocumentosDetEscritos(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio -  DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetEscritos"); 
		
		Map<String, Object>	mapDocumentoExpediente;
		Map<String, Object> documentoPresentados;
		Map<String, Object> mapConsultaCorreo;
		Map<String, Object>	mapEnvioCorreo;
		Map<String, Object> beanEcm;
		Map<String, Object> mapRegTramDoc;
		
		StringBuilder listaDocumentos = new StringBuilder();
		List<Map<String, Object>> listaDocumentosASubir;
		List<Map<String, Object>> listaDocumentosASubirEcm;
		List<Map<String, Object>> listaDetRequerim;
		List<CorreosBean> listaCorreosBeans;
		List<T6621RespExpVirtBean> listaRespAsignados;
		List<T6621RespExpVirtBean> listaRespAsignadosDatos;
		List<Map<String, Object>> listaDocumentosEnvio;		
		String numExpedienteVirtual;
		String numExpedienteOrigen;
		String usuarioCarga;
		String mensaje;
		String codTipoExpediente;
		String codTipDoc;
		String razonSocial;
		String desTipoExpediente;
		String fechaVencimiento;
		Long numCorrelativo;
		Integer numDocumento;
		JsonSerializer serializer = null;
		T6613DocExpVirtBean t6613DocExpVirtBean;
		T01ParamBean t01ParamBean;
		CorreosBean correosBean;
		File archivo;
		String mimeType;
		String codIdecm;
		String error;
		String palabrasClave = "";
		String numRequerimiento="";
		Calendar fechaVacia = null;
		
		Calendar fechaActual = null;
		String anio;
		String anioCorto;
		Long numSec;
		int tamSecEscElect = 0;
		String numSecEscElect;
		String numEscElectonico;
		T10373DocAdjReqBean t10373DocAdjReqBean;
		Integer foliosTotal;
		Integer fechaTramDoc;
		String horaTramDoc;
		Integer numEscrito;
		UserTransaction tx = null;
		
		Map params = new HashMap();
        Map certificado = null;
        Map mldap = null;
        String alias= null;
        T01ParamBean paramBean;
        String extension = null;
		try {
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			error = "";
			anio = "";
			// Fecha actual
			fechaActual = Calendar.getInstance();	
			anio = Utils.toStr(fechaActual.get(Calendar.YEAR));
			anioCorto = anio.substring(2, 4);
			if (log.isDebugEnabled())log.debug("anioCorto: "+anioCorto);
			
			fechaTramDoc = Utils.toInteger(new FechaBean().getAnho() + new FechaBean().getMes() + new FechaBean().getDia());
			if (log.isDebugEnabled())log.debug("fechaTramDoc: "+fechaTramDoc+" - Calendar.Date(): "+Calendar.DATE);
			horaTramDoc = Utils.toStr(new FechaBean().getHora24() + ":" + new FechaBean().getMinuto());
			
			listaDocumentosASubir = (List<Map<String, Object>>) parametros.get("listaDocumentosASubir");
			listaDetRequerim = (List<Map<String, Object>>) parametros.get("listaDetRequerim");
			numExpedienteVirtual = Utils.toStr(parametros.get("numExpedienteVirtual"));
			numExpedienteOrigen = Utils.toStr(parametros.get("numExpedienteOrigen"));
			usuarioCarga = Utils.toStr(parametros.get("codUsuarioCarga"));
			codTipoExpediente = Utils.toStr(parametros.get("codTipoExpediente"));
			codTipDoc = Utils.toStr(parametros.get("codTipDoc"));
			razonSocial = !Utils.isEmpty(Utils.toStr(parametros.get("razonSocial")))? Utils.toStr(parametros.get("razonSocial")):ValidaConstantes.CADENA_VACIA;
			desTipoExpediente = Utils.toStr(parametros.get("desTipoExpediente"));
			
			SimpleDateFormat formateador = new SimpleDateFormat("EEEEEEEEE',' dd 'de' MMMMM 'del' yyyy");
			String fechaFormateada = "";
			
			//Obtenemos secuencia para numero de Escrito ElectrÃ³nico
			numSec = generaSecuenciaEscrito();
			numSecEscElect = "000000" + numSec.toString();
			tamSecEscElect = numSecEscElect.length(); 
			numSecEscElect = numSecEscElect.substring(tamSecEscElect - 6, tamSecEscElect);
			if (log.isDebugEnabled())log.debug("numSecEscElect: "+numSecEscElect);
			
			
			//Numero de Escrito ElectrÃ³nico formateado
			numEscElectonico = anioCorto + ValidaConstantes.DESC_URD + numSecEscElect;
			
			//Obtenemos los responsables del expediente
			listaRespAsignados = respService.obtenerResponsablesAsignados(numExpedienteVirtual.trim());
						
			listaRespAsignadosDatos = respService.obtenerResponsablesAsignadosSepar(numExpedienteVirtual.trim());
		    
		    /*********obtiene valores de Sello de tiempo******/
//			String codApliPadron =  "NSOL";
				/*Map<String, Object> mapParam = new HashMap<String, Object>();
    			mapParam.put("codClase", CatalogoConstantes.COD_NOTIFICA_BUZON);
    			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
    			mapParam.put("codParametro", CatalogoConstantes.COD_ARGUMENTO_BUZON);
    			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
    			paramBean = t01ParamDAO.obtener(mapParam);
    			
    			String codApliPadron =  paramBean.getDesParametro().substring(37, 41);//nsol
    			if(log.isDebugEnabled())log.debug("0. codApliPadron:"+codApliPadron);
				params.put("codApli",codApliPadron.toUpperCase().trim());
                T4486DAO t4486DAO = new T4486DAO(ServiceLocator.getInstance().getDataSource("jdbc/dcbditsgme"));
                certificado = t4486DAO.findByCodAplic(params);
                
                if(log.isDebugEnabled())log.debug("0. certificado:"+certificado);
				alias = (String) certificado.get("cod_pin");
                
				long timeoutCache = Long.parseLong(propiedades.leePropiedad("tiempoExpiracionAlmacen"));
				 AlmacenFirmas almacenFirmas = AlmacenFirmas.getInstance();
	             mldap = almacenFirmas.getStore(alias, timeoutCache);
	             if(log.isDebugEnabled())log.debug("3. mldap:"+mldap);
	             SignerFactory signerFactory = new SignerFactory();
                if (mldap == null){
                    if(log.isDebugEnabled())log.debug("1. cargando certificado al cache de almacen");
                    mldap = signerFactory.getCertificadoDigital(alias);
                    almacenFirmas.setStore(alias, mldap);
                }else{
                    if(log.isDebugEnabled())log.debug("2. cargando certificado desde el cache de almacen");
                    X509Certificate cert = (X509Certificate)mldap.get("certificado");
                    try {
                        cert.checkValidity();
                    } catch (CertificateException e){
                        mldap = signerFactory.getCertificadoDigital(alias);
                        almacenFirmas.setStore(alias, mldap);
                    } finally {
                    }

                }
                
                mapParam = new HashMap<String, Object>();
    			mapParam.put("codClase", CatalogoConstantes.CATA_TIPOS_SELLO_TIME);
    			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
    			mapParam.put("codParametro", CatalogoConstantes.COD_ARGUMENTO_SELLO);
    			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
    			paramBean = t01ParamDAO.obtener(mapParam);
                String ipTSA = paramBean.getDesParametro();
                
                if(log.isDebugEnabled())log.debug(ipTSA);
                mldap.put((String)"url", ipTSA);
                
                if(log.isDebugEnabled())log.debug("4. mldap:"+mldap);*/
			/***************/
						
			/**************************************************************/
			/**ENVIAMOS LOS ARCHIVOS AL ECM Y REGISTRAMOS T10373DOCADJREQ**/
			/**************************************************************/			
			serializer = new JsonSerializer();
			listaDocumentosEnvio = new ArrayList<Map<String, Object>> ();
			foliosTotal = 0;
			listaDocumentosASubirEcm = new ArrayList<Map<String, Object>> ();
			for (Map<String, Object> map : listaDocumentosASubir) {
				
				documentoPresentados = new HashMap<String, Object>();
//				numExpedienteVirtual = Utils.toStr(parametros.get("numExpedienteVirtual"));
				String numArchivo = Utils.toStr(map.get("numOrden"));
				Integer folios =  Utils.toInteger(map.get("folios"));;
				String numArchivoFormato;
				foliosTotal = foliosTotal + folios;
				
				if (numArchivo.length() == 2) {
					numArchivo = "" +Utils.toStr(numArchivo);
				} else {
					numArchivo = "0" + Utils.toStr(numArchivo);
				}
				numArchivoFormato = numEscElectonico.trim() + numArchivo.trim();
				
				String nombreArchivo = Utils.convertirUnicode(Utils.toStr(map.get("nomArchAdj"))); //validar unicode ie8
				
				archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numExpedienteVirtual+"/"+nombreArchivo);
				/*CAMBIAR POR ESTA RUTA COMPARTIDA
				archivo = new File(ValidaConstantes.RUTA_ARCHIVO_COMPARTIDA+numExpedienteVirtual+"/"+nombreArchivo);
				*/
				log.info("***********Nombre Archivo (Utils.toStr(map.get(nomArchAdj))*********************"+archivo);
				mimeType = Utils.toStr(map.get("mimeType"));
				
				if(log.isDebugEnabled())log.debug("adjuntarDocumentosDetEscritos:"+mimeType);
				
				extension = Utils.obtenerExtension(nombreArchivo);
				//LUEGO QUITAR : FIRMA
				  if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
				
					String ruta =ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numExpedienteVirtual+"/";
					archivo = this.sign(archivo, mldap,ruta);
	
	                log.debug(archivo.getAbsolutePath());
				} 
                
				/*FileInputStream fis = new FileInputStream(archivo);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024];
		            for (int readNum; (readNum = fis.read(buf)) != -1;) {
		                bos.write(buf, 0, readNum); 
		            }
		            
		        byte[] data = bos.toByteArray();
		        
				//llenamos el bean ECM
				beanEcm = new HashMap<String, Object>();
				beanEcm.put("nomArchivoSinExt", nombreArchivo.substring(0, nombreArchivo.lastIndexOf(".")));
				beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
				beanEcm.put("numRuc", parametros.get("numRuc"));
				beanEcm.put("numDoc", numEscElectonico.trim());
				beanEcm.put("fecEmi", new Date());
				beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
				beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_ESCRITO);				
				beanEcm.put("archLength", archivo.length());
				beanEcm.put("archMimeType", mimeType);
				beanEcm.put("archFileName", nombreArchivo);
				beanEcm.put("archContent", data);
				palabrasClave = palabrasClave != null ? palabrasClave : "";  
				beanEcm.put("metadata", palabrasClave);
				beanEcm.put("codDep", parametros.get("codDep"));
				beanEcm.put("codDepOri", parametros.get("codDep"));
				codIdecm = ecmService.guardarDocumentoECM(beanEcm); // produccion
//				codIdecm = "idd_40BB8B6F-0000-C819-AE90-94F6F1EC5448"; //local
				
				if(codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
					error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
					return error;
				}*/
				/*LLROMERO*/
				/*LLROMERO docClass*/
				
				
				//ce = c;
				
				/*LIBRERIA JAR ECM*/
				 /*conexion al ECM*/
				
				con = null;
				uc = UserContext.get();
				dom = null;
				domainName = null;
				ost = null;
				osnames = new Vector();
				isConnected = false;
				
				Map<String, Object> mapT01Param = new HashMap<String, Object>();
                String  docClass= "Document";
                Document doc;
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_URL_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String uri = t01ParamBean.getDesParametro().trim();
                log.debug("uri:"+uri);
                
                mapT01Param = new HashMap<String, Object>();
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_USER_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String userName = t01ParamBean.getDesParametro().trim();
                log.debug("username:"+userName);
                
                mapT01Param = new HashMap<String, Object>();
                mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_CE);
                mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_CE);
                mapT01Param.put("codParametro", CatalogoConstantes.COD_USER_CE);
                t01ParamBean = t01ParamDAO.obtener(mapT01Param);
                String password = t01ParamBean.getDesParametro().trim();
                log.debug("password:"+password);

				
				//String  docClass= "Document";
				//Document doc;
				/*String uri = ValidaConstantes.RUTA_SERVIDOR_ECM;
				log.debug("uri:"+uri);
				String userName = ValidaConstantes.USERNAME_ECM;
				log.debug("username:"+userName);
				String password = ValidaConstantes.PASSWORD_ECM;
				log.debug("password:"+password);*/
				String stanza = ValidaConstantes.JAAS_STANZA_ECM ;
				log.debug("stanza:"+stanza);
				//ce.establishConnection(username,password,stanza,uri);
				con = Factory.Connection.getConnection(uri);
		        log.debug("conFactory:"+con);
		        Subject sub = UserContext.createSubject(con,userName,password,stanza);
		        log.debug("subConection:"+sub);
		        uc.pushSubject(sub);
		        //dom = fetchDomain();
		        dom = Factory.Domain.fetchInstance(con, null, null);
		        log.debug("domConection:"+dom);
		        domainName = dom.get_Name();
		        log.debug("domainName:"+domainName);		       
		        ost = dom.get_ObjectStores();
		        log.debug("ostOBject:"+ost);
		        isConnected = true;
		        log.debug("isConnected:"+isConnected);
				
				/* CREANDO EL DOCUMENTO */
				
				String ecm= "ECM";
				log.debug("ecm:"+ecm);
				log.debug("archivoCreando:"+archivo);
				log.debug("mimeTypeCreando:"+mimeType);
				log.debug("nombreArchivoCreando:"+nombreArchivo);
				log.debug("docClassCreando:"+docClass);
				//ObjectStore os = ce.fetchOS(ecm);
				ObjectStore os = Factory.ObjectStore.fetchInstance(dom, ecm, null);
		        log.debug("ObjectStoreFet:"+os);
				String fPath = ValidaConstantes.FILE_FOLDER_ECM;
				log.debug("fPath:"+fPath);
				doc = CEUtil.createDocWithContent(archivo, mimeType, os, nombreArchivo, docClass);
				log.debug("docUtil:"+doc);
    			doc.save(RefreshMode.REFRESH);
    			log.debug("Documento se ha grabado");
    			ReferentialContainmentRelationship rcr = CEUtil.fileObject(os, doc, fPath);
    			log.debug("referential:"+rcr);
    			rcr.save(RefreshMode.REFRESH);
    			log.debug("Files se ha grabado");
    			

    			/*OBTENIENDO LOS DATOS QUE SE GUARDO EN EL ECM */
    			String path = fPath+nombreArchivo;
    			log.debug("path:"+path);
    			doc = fetchDocByPath(os, path);
    			log.debug("docpath:"+doc);
    			    			
    			    			
    			HashMap mapGetEcm = getContainableObjectProperties(doc);
    			
    			log.debug("MapECm:"+mapGetEcm);

        			String id = Utils.toStr(mapGetEcm.get("Id"));
        			log.debug("id:"+id);
    				String name = Utils.toStr(mapGetEcm.get("Name"));
    				log.debug("name:"+name);
    				String usuario = Utils.toStr(mapGetEcm.get("Creator"));
    				log.debug("usuario:"+usuario);
    				String owner = Utils.toStr(mapGetEcm.get("Owner"));
    				log.debug("owner:"+owner);

				Map<String, Object> mapEcm = new HashMap<String, Object>();
				mapEcm.put("numRequerimiento",numRequerimiento);
				mapEcm.put("numOrden",numArchivo);
				mapEcm.put("folios",folios);
				//mapEcm.put("codIdecm",codIdecm);
				mapEcm.put("codIdecm",id);
				mapEcm.put("nomArchAdj",nombreArchivo);
				mapEcm.put("numOrden",Utils.toInteger(map.get("numOrden")));
//				mapEcm.put("tamArch",Utils.toInteger(map.get("tamArch")));
				mapEcm.put("tamArch",archivo.length());
				
				listaDocumentosASubirEcm.add(mapEcm);
				
				double pesoActual=archivo.length();
				double pesoKB=pesoActual/1024;
				double pesoDecimal=Utils.round(pesoKB,2);
				String pesoFinal=Utils.toStr(pesoDecimal)+" KB" ;
				//guardamos la lista de documentos para el envio de mensaje a los responsables
				listaDocumentos.append("			<tr>");
				listaDocumentos.append("				<td>"+Utils.toStr(numArchivoFormato)+"</td>");
				listaDocumentos.append("				<td>"+Utils.escapeHTML(Utils.toStr(nombreArchivo))+"</td>");
//				listaDocumentos.append("				<td>"+Utils.toStr(map.get("cntTamArch"))+"</td>");
				listaDocumentos.append("				<td>"+Utils.toStr(pesoFinal)+"</td>");
				listaDocumentos.append("				<td>"+Utils.toStr(folios)+"</td>");
				listaDocumentos.append("			</tr>");
				
				//generamos la lista de documentos a llenar en la constancia de envio								
				documentoPresentados.put("numArchivo", numArchivoFormato);
				documentoPresentados.put("nombreArchivo", Utils.convertirUnicode(nombreArchivo));
//				documentoPresentados.put("item", "0");
//				documentoPresentados.put("tamaÃ±oArchivo", Utils.toStr(map.get("cntTamArch")));
				documentoPresentados.put("tamaÃ±oArchivo", Utils.toStr(pesoFinal));
				documentoPresentados.put("folios", Utils.toStr(folios));
				listaDocumentosEnvio.add(documentoPresentados);
				archivo.delete();
			}			
			
			tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            tx.begin();
			
			for (Map<String, Object> map : listaDocumentosASubirEcm) {
				
//				numExpedienteVirtual = Utils.toStr(parametros.get("numExpedienteVirtual"));
				String numArchivo = Utils.toStr(map.get("numOrden"));
				Integer folios =  Utils.toInteger(map.get("folios"));;
				String numArchivoFormato;
				
				if (numArchivo.length() == 2) {
					numArchivo = "" +Utils.toStr(numArchivo);
				} else {
					numArchivo = "0" + Utils.toStr(numArchivo);
				}
				numArchivoFormato = numEscElectonico.trim() + numArchivo.trim();
				
				String nombreArchivo = Utils.convertirUnicode(Utils.toStr(map.get("nomArchAdj"))); //validar unicode ie8
				
				
				/*******************************/
				/**REGISTRAMOS T10373DOCADJREQ**/
				/*******************************/
				t10373DocAdjReqBean = new T10373DocAdjReqBean();
				t10373DocAdjReqBean.setNumArchAdj(0);
				t10373DocAdjReqBean.setNumRequerimiento("0");
				t10373DocAdjReqBean.setNumItem(0);
				t10373DocAdjReqBean.setNumSubItem(0);
				t10373DocAdjReqBean.setNumArcItem(Utils.toInteger(map.get("numOrden")));
				t10373DocAdjReqBean.setNomArchAdj(nombreArchivo);
				t10373DocAdjReqBean.setNumArchivo(numArchivoFormato);
				t10373DocAdjReqBean.setCntTamArch(Utils.toInteger(map.get("tamArch")));
				t10373DocAdjReqBean.setNumFolios(folios);
				t10373DocAdjReqBean.setCodTipAlm(ValidaConstantes.COD_TIPDOC_ALM);
				t10373DocAdjReqBean.setCodIdECM(Utils.toStr(map.get("codIdecm")));
				t10373DocAdjReqBean.setNumCorDoc(0);
				t10373DocAdjReqBean.setCodOriDoc(Utils.toStr(parametros.get("codOrigDoc")));
				t10373DocAdjReqBean.setCodUsuRegis(usuarioCarga);
				t10373DocAdjReqBean.setFechaRegistro((new FechaBean()).getSQLDate());
				t10373DocAdjReqBean.setCodUsuMod(usuarioCarga);
				t10373DocAdjReqBean.setFechaMod((new FechaBean()).getSQLDate());
				
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
				t10373DocAdjReqDAO.insertar(t10373DocAdjReqBean);
				
			}	
			/****************************************************************/
			/**REGISTRAMOS EL ESCRITO ELECTRONICO EN EL EXPEDIENTE VIRTUAL **/
			/****************************************************************/
			Map<String, Object> paramBusqueda = new HashMap<String, Object>();
			T6613DocExpVirtBean buscarDatoDocumento = new T6613DocExpVirtBean();
			
			codTipDoc=ValidaConstantes.COD_TIPO_DOCU_ESCRITO;
			
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			numDocumento = Integer.valueOf(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM)));
			numEscrito = numDocumento;
			String descArchivoEscrito = Utils.toStr(numEscElectonico) + "_" +ValidaConstantes.COD_TIPO_DOCU_ESCRITO + "_" +parametros.get("numRuc").toString().trim();
			
			t6613DocExpVirtBean.setNumCorDoc(numDocumento);
			t6613DocExpVirtBean.setNumExpedienteVir(numExpedienteVirtual);
			t6613DocExpVirtBean.setCodTipExp(codTipoExpediente);
			t6613DocExpVirtBean.setCodTipDoc(ValidaConstantes.COD_TIPO_DOCU_ESCRITO);			
			t6613DocExpVirtBean.setNumDoc(Utils.toStr(numEscElectonico));
			t6613DocExpVirtBean.setFecDoc(new Date());
			t6613DocExpVirtBean.setDescripcionArchivo(Utils.toStr(descArchivoEscrito)+".pdf");
			t6613DocExpVirtBean.setNumRequerimiento("0");
			t6613DocExpVirtBean.setFechaCarga(new Date());			
			t6613DocExpVirtBean.setCodIdentificadorECM("0");			
			t6613DocExpVirtBean.setCodUsuarioRegistro(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setFecRegis(new Date());
			t6613DocExpVirtBean.setCodOrigenDocuento(Utils.toStr(parametros.get("codOrigDoc")));			
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setDesMotsoldoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setEstadoDocumentoReq("01");
			t6613DocExpVirtBean.setIndVisDocumento(ValidaConstantes.IND_VISIBLE_DOC);
//			t6613DocExpVirtBean.setNumDocRelacionado(Utils.toStr(buscarDatoDocumento.getNumCorDoc()));
			t6613DocExpVirtBean.setNumDocRelacionado("0");
			
			t6613DocExpVirtBean.setFecNotif(fechaVacia.getTime());
			t6613DocExpVirtBean.setCodForNotif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_REGISTRADO);
			t6613DocExpVirtBean.setDesEliDoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodUsuModif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setFecModif(new Date());
			
			mapDocumentoExpediente = new HashMap<String, Object>();
			mapDocumentoExpediente.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
			t6613DocExpVirtDAO.insertarManual(mapDocumentoExpediente);
			
			/****************************************************************************/
			/**REGISTRAMOS EL ESCRITO ELECTRONICO EN EL SISTEMA DE TRAMITE DOCUMENTARIO**/
			/****************************************************************************/
			mapRegTramDoc = new HashMap<String, Object>();
			String nombre;
			if(!Utils.isEmpty(listaRespAsignadosDatos)){
				for(T6621RespExpVirtBean t6621RespExpVirtBean : listaRespAsignadosDatos) {
					if ("1".equals(t6621RespExpVirtBean.getIndResponsable())) {						
						nombre = t6621RespExpVirtBean.getNombres().trim();
						String[] nomFuncionar = nombre.split(" ");
						mapRegTramDoc.put("dFuncionar", nomFuncionar[0] + " " + t6621RespExpVirtBean.getApellidosPaterno().trim());
						mapRegTramDoc.put("unidOrg", t6621RespExpVirtBean.getUnidOrganizacional().trim());						
					}
				}
			}
			//Datos tabla Expedi
			mapRegTramDoc.put("codAdua", ValidaConstantes.COD_ADUANA_TRAM_DOC);
			mapRegTramDoc.put("oficRec", ValidaConstantes.DESC_URD);
			mapRegTramDoc.put("anoExpedi", anio);
			mapRegTramDoc.put("nroExpedi", numSec);
			mapRegTramDoc.put("digCheck", 0);
			mapRegTramDoc.put("clasePar", ValidaConstantes.CLASE_TRAM_DOC);
			mapRegTramDoc.put("fecExp", fechaTramDoc);
			mapRegTramDoc.put("timeExp", horaTramDoc);
			mapRegTramDoc.put("procedimPar", ValidaConstantes.COD_PROC_TRAM_DOC);
			mapRegTramDoc.put("tipoUser", ValidaConstantes.TIPO_USER_TRAM_DOC);
			mapRegTramDoc.put("nroDoc", parametros.get("numRuc").toString().trim());
			mapRegTramDoc.put("tipoDoc", ValidaConstantes.TIPO_DOC_TRAM_DOC);
			mapRegTramDoc.put("codigoPar", ValidaConstantes.CADENA_VACIA);
			mapRegTramDoc.put("asuntoPar", numExpedienteOrigen);
			mapRegTramDoc.put("digUser", parametros.get("codUsuarioCarga"));
			mapRegTramDoc.put("actEstado", ValidaConstantes.EST_EXP_TRAM_DOC);
			mapRegTramDoc.put("actAccion", ValidaConstantes.ACC_EXP_TRAM_DOC);
			
			DataSourceContextHolder.setKeyDataSource("dxprad1");
			expediDAO.insertar(mapRegTramDoc);
			
			//Datos tabla Seguim
			mapRegTramDoc.put("nroSeguim", ValidaConstantes.NRO_SEGUIM_TRAM_DOC);
			mapRegTramDoc.put("foliosPar", foliosTotal);
			mapRegTramDoc.put("cAccion", ValidaConstantes.ACC_EXP_TRAM_DOC);
			mapRegTramDoc.put("cDepOrig", ValidaConstantes.DESC_URD);
			mapRegTramDoc.put("dCargo", ValidaConstantes.CARGO_TRAM_DOC);
			mapRegTramDoc.put("dAsunto", ValidaConstantes.ASUNTO_SEGUIM_TRAM_DOC);
			mapRegTramDoc.put("fSeguim", fechaTramDoc);
			mapRegTramDoc.put("hSeguim", horaTramDoc); 
			mapRegTramDoc.put("dPerRecib", ValidaConstantes.DESC_URD);
			mapRegTramDoc.put("fMod", fechaTramDoc);
			
			//seguimDAO.insertar(mapRegTramDoc);	Se comentÃ³ el insert para que permita grabar	
			
			
			/***************************************************************/
			/**GENERAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS - PDF**/
			/***************************************************************/
			FechaBean fechaHoy = new FechaBean();
			
//			FechaBean fechaVencimiento1= new FechaBean();
			numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			String listaDetalle = (String) serializer.serialize(listaDocumentosEnvio);
			String 	json = "{"+"\""+"cabecera"+"\":{";
					json+="\""+"numConstancia"+"\":"+"\""+numEscElectonico;
					json+="\","+"\""+"fechaPresentacion"+"\":"+"\""+fechaHoy.getFormatDate("dd/MM/yyyy-HH:mm");
					json+="\","+"\""+"ruc"+"\":"+"\""+Utils.toStr(parametros.get("numRuc"));
					razonSocial = razonSocial.replace("&AMP;", "&"); 
					razonSocial = razonSocial.replace("&amp;", "&");
					json+="\","+"\""+"desRazonSocial"+"\":"+"\""+razonSocial.replace("\"", "\\\"").toUpperCase();
					json+="\","+"\""+"numExpediente"+"\":"+"\""+numExpedienteOrigen;
					json+="\","+"\""+"desTipoExpediente"+"\":"+"\""+desTipoExpediente.toUpperCase();
					json+="\"},\""+"detalle\"" + ":" + listaDetalle;
					json+= "}";
					
			//nchavez 13/06/2016
			int firmaPdf = 1;
			int modeloPdf = 1000;
			Integer idDocumento = ExportaConstantes.PDF_COD_PLANTILLA_04_002;
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
			//generaPDF.setReturnClass(byte[].class);
			generaPDF.setReturnClass(int.class);
			
			//byte[] docConstPresentDocu = (byte[]) (generaPDF.invoke(new Object[]{idDocumento, json, "pdf", modeloPdf, firmaPdf}));
			if (log.isDebugEnabled())log.debug("idDocumento: "+idDocumento);
			if (log.isDebugEnabled())log.debug("json: "+json);
			
			Integer numeroPdf = Utils.toInteger(generaPDF.invoke(new Object[] { idDocumento, json, "pdf", modeloPdf, firmaPdf }));
			if (log.isDebugEnabled())log.debug("numeroPdf: "+numeroPdf);
			byte[] binDoc = ObtenerPDFUnload(numeroPdf);
			log.debug("binDoc: "+binDoc);
			
			//String desArchivoPdf = ExportaConstantes.NOMBRE_PLANTILLA_PDF_002_01 + ".pdf";
//			String desArchivoPdf = numeroPdf.toString() + ".pdf";
			
			/*******************************************************************/
			/**GUARDAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS EN EL ECM**/
			/*******************************************************************/
			beanEcm = new HashMap<String, Object>();
			
			beanEcm.put("nomArchivoSinExt", numCorrelativo);
			beanEcm.put("numExpeDv", numExpedienteVirtual.trim());
			beanEcm.put("numRuc", parametros.get("numRuc"));
			beanEcm.put("numDoc", numeroPdf);
			beanEcm.put("fecEmi", new Date());
			beanEcm.put("codTipExpVir", parametros.get("codTipoExpediente"));
			beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO);
			beanEcm.put("archLength", binDoc.length);
			beanEcm.put("archMimeType", "application/pdf");
			beanEcm.put("archFileName", Utils.toStr(numCorrelativo)+".pdf");
			beanEcm.put("archContent", binDoc);
			beanEcm.put("metadata", palabrasClave);
		//	codIdecm = ecmService.guardarDocumentoECM(beanEcm);    //produccion
	        codIdecm = "idd_40BB8B6F-0000-C819-AE90-94F6F1EC5448"; //local
			if(codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {
				error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
				return error;
			} 
			
			
			
			/********************************************************************************/
			/**ADJUNTAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS AL EXPEDIENTE VIRTUAL**/
			/*******************************************************************************/
			t6613DocExpVirtBean = new T6613DocExpVirtBean();
			numDocumento = Integer.valueOf(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM)));
//			String descArchivoConstancia = Utils.toStr(numCorrelativo) + "_" +ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO + "_" +parametros.get("numRuc").toString().trim();
			String descArchivoConstancia = Utils.toStr(numEscElectonico) + "_" +ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO + "_" +parametros.get("numRuc").toString().trim();
			
			t6613DocExpVirtBean.setNumCorDoc(numDocumento);
			t6613DocExpVirtBean.setNumExpedienteVir(numExpedienteVirtual);
			t6613DocExpVirtBean.setCodTipDoc(ValidaConstantes.COD_TIPO_DOCU_CONST_ESCRITO);
			t6613DocExpVirtBean.setCodTipExp(codTipoExpediente);
			t6613DocExpVirtBean.setNumDoc(Utils.toStr(numEscElectonico));
			t6613DocExpVirtBean.setNumRequerimiento("0");
			t6613DocExpVirtBean.setFechaCarga(new Date());
			t6613DocExpVirtBean.setDescripcionArchivo(Utils.toStr(descArchivoConstancia)+".pdf");
			t6613DocExpVirtBean.setCodIdentificadorECM(codIdecm);			
			t6613DocExpVirtBean.setCodUsuarioRegistro(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setFecRegis(new Date());
			t6613DocExpVirtBean.setCodOrigenDocuento(Utils.toStr(parametros.get("codOrigDoc")));
			t6613DocExpVirtBean.setFecDoc(new Date());
			t6613DocExpVirtBean.setCodigoTipoDocumentoSust(ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
			t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.USUARIO_EXPEDIENTE_VIRTUAL);
			t6613DocExpVirtBean.setDesMotsoldoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setIndVisDocumento(ValidaConstantes.IND_NO_VISIBLE_DOC);
			t6613DocExpVirtBean.setNumDocRelacionado(Utils.toStr(numEscrito));
			t6613DocExpVirtBean.setEstadoDocumentoReq("01");
			t6613DocExpVirtBean.setFecNotif(fechaVacia.getTime());
			t6613DocExpVirtBean.setCodForNotif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodEstDoc(ValidaConstantes.COD_EST_DOC_REGISTRADO);
			t6613DocExpVirtBean.setDesEliDoc(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setCodUsuModif(ValidaConstantes.SEPARADOR_GUION);
			t6613DocExpVirtBean.setFecModif(new Date());
			
			mapDocumentoExpediente = new HashMap<String, Object>();
			mapDocumentoExpediente.put("t6613DocExpVirtBean", t6613DocExpVirtBean);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCIONX_EXPVIRTUAL);
			t6613DocExpVirtDAO.insertarManual(mapDocumentoExpediente);
							
			/**********************************/
			/**ACTUALIZAMOS T10373DOCADJREQ**/
			/**********************************/
			Map<String, Object> beanT10373 = new HashMap<String, Object>();
			for (Map<String, Object> mapT10373 : listaDocumentosASubir){
//				beanT10373.put("numItem", mapT10373.get("item"));
				beanT10373.put("numItem", 0);
//				beanT10373.put("numRequerimiento", numRequerimiento);
				beanT10373.put("numRequerimiento", "0");
				beanT10373.put("numCorDoc", numEscrito);
				beanT10373.put("codUsuModif", usuarioCarga);
				beanT10373.put("fecModif", new Date());
				t10373DocAdjReqDAO.update(beanT10373);							
			}
			
			
			
			tx.commit();
			
			/*************************************************************************/
			/**ENVIAMOS LA CONSTANCIA DE PRESENTACION DE DOCUMENTOS AL CONTRIBUYENTE**/
			/*************************************************************************/
			if (log.isDebugEnabled()) log.debug("====[ENVIO BUZON]====");
			Map<String, Object> mensajeMap = new HashMap<String, Object>();
			mensajeMap.put("num_ruc",parametros.get("numRuc"));
			mensajeMap.put("rsocial", razonSocial);
			mensajeMap.put("id_PDF_Formulario", Utils.toStr(numeroPdf));
			mensajeMap.put("numConstancia",numCorrelativo);
			if (log.isDebugEnabled()) log.debug("====[PARAMETROS PARA ENVIO BUZON: " + mensajeMap.toString() + "]====");
			enviarMensajeBuzon(mensajeMap);
			
			if (log.isDebugEnabled()) log.debug("Fin -  DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetEscritos"); 
			error = ValidaConstantes.NO_ERROR;
			
			/*****************************************************************/
			/**ENVIAMOS UN CORREO A TODOS LOS RESPONSABLES DEL ESCRITO**/
			/*****************************************************************/			
			// i)construimos el Mensaje
			String asunto = ValidaConstantes.ASUNTO_MENSAJE_RESP
			+" RUC: "+Utils.toStr(parametros.get("numRuc"))+" - RAZÃ“N SOCIAL: "+(razonSocial != null && !Utils.isEmpty(razonSocial) ? razonSocial.trim() : ValidaConstantes.CADENA_VACIA)
//			+" - Expediente: "+numExpedienteOrigen+" - Requerimiento: "+numRequerimientOrigen;
			+" - Expediente: "+numExpedienteOrigen;
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
			sbMensaje.append("	Fecha de presentaci&oacute;n del escrito: "+fechaHoy.getFormatDate("dd/MM/yyyy")+"</br>");
			sbMensaje.append("	Cantidad de folios            			: "+foliosTotal+"</br>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	El contribuyente ha presentado un escrito electr&oacute;nico conformado por: </br>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	<table style='padding: 10px' width='100%'>");
			sbMensaje.append("		<thead>");
			sbMensaje.append("			<tr>");
			sbMensaje.append("				<th width='25%' align='left'><u>N&uacute;mero de Archivo</u></th>");	
			sbMensaje.append("				<th width='25%' align='left'><u>Nombre de Archivo</u></th>");
			sbMensaje.append("				<th width='25%'align='left'><u>Tama&ntilde;o de Archivo</u></th>");
			sbMensaje.append("				<th width='15%'align='left'><u>Folios</u></th>");
			sbMensaje.append("			</tr>");
			sbMensaje.append("		</thead>");
			sbMensaje.append("		<tbody>");	
			sbMensaje.append("			"+listaDocumentos);
			sbMensaje.append("		</tbody>");	
			sbMensaje.append("	</table>");
			sbMensaje.append("	</br>");
			sbMensaje.append("	</br>");
			
			fechaFormateada = formateador.format(new Date());
			fechaFormateada=StringUtils.capitalize(fechaFormateada);
			fechaFormateada = fechaFormateada.substring(0,fechaFormateada.indexOf("de")+3)+
					  fechaFormateada.substring(fechaFormateada.indexOf("de")+3,fechaFormateada.indexOf("de")+4).toUpperCase()+
					  fechaFormateada.substring(fechaFormateada.indexOf("de")+4,fechaFormateada.length());
			
			fechaFormateada = Utils.escapeHTML(fechaFormateada);
							  
			sbMensaje.append("	" + fechaFormateada);
			sbMensaje.append("</body>");
			sbMensaje.append("</html>");
			
			mensaje = sbMensaje.toString();			
			
			// ii) obtenemos los correos de los responsables asignados			
			if(!Utils.isEmpty(listaRespAsignados)) {
				mapConsultaCorreo =  new HashMap<String, Object>();
				listaCorreosBeans = new ArrayList<CorreosBean>();
				
				for(T6621RespExpVirtBean t6621RespExpVirtBean : listaRespAsignados) {
					
					correosBean = new CorreosBean();
					correosBean.setCodPers(t6621RespExpVirtBean.getCodColaborador());
					listaCorreosBeans.add(correosBean);
					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
					
					mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, 
								t6621RespExpVirtBean.getApellidosPaterno() + " " +
								t6621RespExpVirtBean.getApellidosMaterno() + ", " +
								t6621RespExpVirtBean.getNombres()
							);
					
					// iii)Obtenemos la lista de correos del responsable y enviamos el mensaje a cada uno de estos
					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);					
				}
				if(!Utils.isEmpty(listaCorreosBeans)){
					for(CorreosBean bean : listaCorreosBeans) {
						
						mapEnvioCorreo = new HashMap<String, Object>();
						mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());
						mapEnvioCorreo.put("mensaje", mensaje.toString());
						mapEnvioCorreo.put("asunto", asunto);
						mapEnvioCorreo.put("formatoHTML",1);
						correosService.enviarCorreo(mapEnvioCorreo);
					}
				}
			}
			

			if (log.isDebugEnabled()) log.debug("Final - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetEscritos");			
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetEscritos");
			log.error(ex, ex);
			tx.rollback();
            if (log.isDebugEnabled()) {log.debug("********* Roll Back *********");}
            error = ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS;
			
			return error;
			 
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DocumentoItemRequerimientoServiceImpl.adjuntarDocumentosDetEscritos");			
		}
		return error;
	}
	//LLROMEROB 
	public Domain fetchDomain()
    {
		log.debug("conFetch:"+con);
        dom = Factory.Domain.fetchInstance(con, null, null);
        log.debug("domain:"+dom);
        return dom;
    }
	public ObjectStoreSet getOSSet()
    {
        ost = dom.get_ObjectStores();
        log.debug("ostOBject:"+ost);
        return ost;
    }
	public static byte[] readDocContentFromFile(File f)
    {
        FileInputStream is;
        byte[] b = null;
        int fileLength = (int)f.length();
        if(fileLength != 0)
        {
        	try
        	{
        		is = new FileInputStream(f);
        		b = new byte[fileLength];
        		is.read(b);
        		is.close();
        	}
        	catch (FileNotFoundException e)
        	{
        		e.printStackTrace();
        	}
        	catch (IOException e)
        	{
        		e.printStackTrace();
        	}
        }
        return b;
    }
	
	public static ContentTransfer createContentTransfer(File f)
    {
        ContentTransfer ctNew = null;
        if(readDocContentFromFile(f) != null)
        {
        	ctNew = Factory.ContentTransfer.createInstance();
        	log.debug("ctNewContet:"+ctNew);
            ByteArrayInputStream is = new ByteArrayInputStream(readDocContentFromFile(f));
            log.debug("isContent:"+is);
            ctNew.setCaptureSource(is);
            ctNew.set_RetrievalName(f.getName());
            log.debug("ContenidoTransferido");
        }
        return ctNew;
    }
	
	public static ContentElementList createContentElements(File f)
    {
        ContentElementList cel = null;
        if(createContentTransfer(f) != null)
        {
        	cel = Factory.ContentElement.createList();
        	log.debug("celContent:"+cel);
            ContentTransfer ctNew = createContentTransfer(f);
            log.debug("ctNewList:"+ctNew);
            cel.add(ctNew);
        }
        return cel;
    }
	
	public static Document createDocWithContent(File f, String mimeType, ObjectStore os, String docName, String docClass)
    {
        Document doc = null;
		if (docClass.equals(""))
        	doc = Factory.Document.createInstance(os, null);
        else
        	doc = Factory.Document.createInstance(os, docClass);
		log.debug("docCreate:"+doc);
        doc.getProperties().putValue("DocumentTitle", docName);
        doc.set_MimeType(mimeType);
        ContentElementList cel = createContentElements(f);
        log.debug("celCreate:"+cel);
        if (cel != null)
        	doc.set_ContentElements(cel);
        return doc;
    }
	public static Document fetchDocByPath(ObjectStore os, String path)
    {
        Document doc = Factory.Document.fetchInstance(os, path, null);
        log.debug("docFectDoc:"+doc);
        return doc;
    }
	public static HashMap getContainableObjectProperties(Containable o)
    {
        HashMap hm = new HashMap();
        hm.put("Id", o.get_Id().toString());
        hm.put("Name", o.get_Name());
        hm.put("Creator", o.get_Creator());
        hm.put("Owner", o.get_Owner());
        hm.put("Date Created",o.get_DateCreated().toString());
        hm.put("Date Last Modified", o.get_DateLastModified().toString());
        return hm;
    }
    
	//
	

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setValidarParametrosService(
			ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}	
	
	
	//PAS20201U210500029 - HHC FIN

}