package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CorreosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.model.ResCabecera;
import pe.gob.sunat.recurso2.documentacion.service.ResolucionService;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class ReaperturaExpedienteVirtualController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(ReaperturaExpedienteVirtualController.class);
	
	private JsonView jsonView;
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ResponsableService responsableService;
	private AduanaService aduanaService;
	private CorreosService correosService;
	private DocumentoExpedienteService documentoExpedienteService;
	
	public ModelAndView cargarReaperturaExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.cargarReaperturaExpedienteVirtual");

		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ReaperturaExpedienteVirtualController.cargarReaperturaExpedienteVirtual");
			String indCarga = request.getParameter("indCarga");
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {

				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_06_001);

				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				String codDependencia = usuarioBean.getCodDepend();

				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_06_001);
				
				
				//TODO - EXTRAER TAMAÑO MAXIMO PARA EXCEPCION 13
				String paramTamanioIA = "IA";
				long tamanoPermitido = 0;

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
				params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

				List<T01ParamBean> listaTamanioMaximo = parametroService.listarParametros(params);
				for (T01ParamBean item : listaTamanioMaximo) {
				  if (paramTamanioIA.equals(item.getCodParametro())) {
				    tamanoPermitido = Long.parseLong(item.getDesParametro().toString());
				  }
				}
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_06_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_06_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_06_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_06_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_06_005);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_06_006);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_01_06_007);
				excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_01_06_008);
				excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_01_06_009);
				excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_01_06_010);
				excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_06_011);
				excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_01_06_012);
				excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_01_06_013.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, String.valueOf(tamanoPermitido)));
				excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_01_06_014);
				excepciones.put("excepcion15", AvisoConstantes.EXCEP_MODULO_01_06_015);
				excepciones.put("excepcion16", AvisoConstantes.EXCEP_MODULO_01_06_016);
				excepciones.put("excepcion17", AvisoConstantes.EXCEP_MODULO_01_06_017);
				excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_01_06_018);
				
				Map<String, Object> avisos = new HashMap<String, Object>();
				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_06_001);

				Date fecSistema = new Date();
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				
				String fecVista = formatoFechaExporta.format(fecSistema);
				String codEstadoExpedientePermitido = ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO;

				String codTipoExpedienteEspecial = ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA;
				
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

				Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
				String desEstadoExpedientePermitido = mapaEstadoExpediente.get(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO).toString();

				List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
				} else {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
				}
				modelo.addObject("codEstadoExpedientePermitido", new JsonSerializer().serialize(codEstadoExpedientePermitido));
				modelo.addObject("desEstadoExpedientePermitido", new JsonSerializer().serialize(desEstadoExpedientePermitido));
				modelo.addObject("codTipoExpedienteEspecial", new JsonSerializer().serialize(codTipoExpedienteEspecial));
				modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));
				
			}else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {//VALIDAR EL INGRESO DEL NÚMERO DE EXPEDIENTE

				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				if (reader != null) {
					jsonEnviado = reader.readLine();
				}
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();
				modelo = new ModelAndView(jsonView);
				Map<String, Object> parametros = null;
				
				//VERIFICAR SI EXISTE EL NUMERO EXPEDIENTE INGRESADO EN BD
				parametros = new HashMap<String, Object>();
				parametros.put("indClaseExpediente", dataEnvio.get("indClaseExpediente").toString());
				parametros.put("numExpediente", dataEnvio.get("numExpediente").toString());
				
				T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
				
				if (expedienteVirtual == null || ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ELIMINADO.equals(expedienteVirtual.getCodEstado())) {
                     modelo.addObject("status", false);
                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_003);
                     return modelo;
				}
				
				//VERIFICAR SI EL EXPEDIENTE PERTENECE A LA DEPENDENCIA DEL USUARIO CONECTADO
				if (!Utils.equiv(codDependenciaBase.substring(0,3), expedienteVirtual.getCodDependencia().substring(0,3))) {
                     modelo.addObject("status", false);
                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_004);
                     return modelo;
				}
				
				//VERIFICAR SI ES RESPONSABLE DEL EXPEDIENTE
				parametros = new HashMap<String, Object>();
				parametros.put("num_expedv_par", expedienteVirtual.getNumExpedienteVirtual());
				parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
				parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				T6621RespExpVirtBean t6621respExpVirtBean = responsableService.obtenerResponsable(parametros);
				if (t6621respExpVirtBean == null) {
                     modelo.addObject("status", false);
                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_005);
                     return modelo;
				}
				
				//VERIFICAR QUE EL DOCUMENTO SE ENCUENTRA EN ESTADO CERRADO
				if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO.equals(expedienteVirtual.getCodEstado())) {
                     modelo.addObject("status", false);
                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_006);
                     return modelo;
				}

	           	//[PAS20191U210500076] VALIDAR SI SE REQUIERE OBVIAR EL ORIGEN AUTOMATICO POR TIPO DE EXPEDIENTE
	           	if (!expedienteVirtualService.obviarOrigenAutoByTipExp(expedienteVirtual.getCodTipoExpediente().trim())) {
					//VERIFICAR QUE SU PROCEDENCIA NO SEA MANUAL
	                if (ValidaConstantes.IND_ORIGEN_EXP_VIRT_AUTOMATICO.equals(expedienteVirtual.getCodOrigen())) {
	                     modelo.addObject("status", false);
	                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_007);
	                     return modelo;
	                }
				}

				
				//VERIFICAR QUE NO SEA UN EXPEDIENTE ACUMULADO
				if (Utils.equiv(expedienteVirtual.getIndicadorAcumulado(), ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
                    modelo.addObject("status", false);
                    modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_008);
                    return modelo;
				}
								
				
				//CARGAR DATOS DEL DOCUMENTO DE CIERRE 
				
				parametros = new HashMap<String, Object>();
				parametros.put("numexpediente", expedienteVirtual.getNumExpedienteVirtual());
				parametros.put("tipodocsustento", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
				
				T6613DocExpVirtBean datosDocuCierre = expedienteVirtualService.buscarDatosDocumentos(parametros);
				if(datosDocuCierre == null){
					modelo.addObject("t6613DocExpVirtBean", " ");
					modelo.addObject("fechaNotificacionDoc", " ");
					modelo.addObject("fechaNotificacionDoc", " ");
				}else {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					modelo.addObject("fechaEmisionDoc", sdf.format(datosDocuCierre.getFecDoc()));
					String fechanotifi = sdf.format(datosDocuCierre.getFecNotif());
					if(fechanotifi.equals("01/01/0001")){
						modelo.addObject("fechaNotificacionDoc","-");
					}else{
						modelo.addObject("fechaNotificacionDoc",fechanotifi);
					}
					
					T01ParamBean T01TipDoc = configuracionExpedienteService.obtenerTipoDoc(datosDocuCierre.getCodTipDoc(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					datosDocuCierre.setDesNombreCompuesto(datosDocuCierre.getCodTipDoc() + " - " +T01TipDoc.getDesParametro());
					modelo.addObject("t6613DocExpVirtBean", datosDocuCierre);
				}
				//CARGA TIPO DE DOCUMENTOS POR EXPEDIENTE
				parametros.put("codTipoExpediente", expedienteVirtual.getCodTipoExpediente());
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				parametros.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
				parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				List<T6623TipDocExpBean> listadoTiposDocumentos = configuracionExpedienteService.listarTiposDocumentos(parametros);
				modelo.addObject("listadoTiposDocumentos", listadoTiposDocumentos);
				
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesProceso(mapaProcesos.get(expedienteVirtual.getCodProceso()).toString());
				
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesTipoExpediente(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente()).toString());
				
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaEstadoCierre = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesEstadoCierre(mapaEstadoCierre.get(expedienteVirtual.getCodEstadoCierre()).toString());
				
				DdpBean contribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
				if (contribuyente == null) {
					modelo.addObject("contribuyente", null);
					return modelo;
				}
				
				modelo.addObject("expedienteVirtual", expedienteVirtual);
				modelo.addObject("contribuyente", contribuyente);
			}
			
		}catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ReaperturaExpedienteVirtualController.cargarReaperturaExpedienteVirtual");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ReaperturaExpedienteVirtualController.cargarReaperturaExpedienteVirtual");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	
	}
	
	public ModelAndView reabrirExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.reabrirExpedienteVirtual");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        HashMap<String, Object> mapParam = null;
        MultipartHttpServletRequest multipartRequest = null;
        MultipartFile archivo = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        	
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
        	//----------ADJUNTAR DOCUEMNTO ------------//
			multipartRequest = (MultipartHttpServletRequest) request;
			
			archivo = multipartRequest.getFile("docFisico");
			
			String dataOculta = request.getParameter("dataOculta").toString();
			
        	@SuppressWarnings("unchecked")                 
        	
        	String mimeType = null;
        	String arrayMimeType[] = null;
			mimeType = archivo.getContentType();
			arrayMimeType = mimeType.split(";");
			mimeType = arrayMimeType[0];
        	
        	Map<String, Object> infoDataOculta = (Map<String, Object>) new JsonSerializer().deserialize(dataOculta, Map.class);
        	modelo = new ModelAndView(jsonView);
               
        	Map<String, Object> mapDocumento = new HashMap<String, Object>();
        	
	        //DATOS DEL DOCUMENTO
        	mapDocumento.put("numExpeDv", infoDataOculta.get("numExpedienteVirtual").toString());
			String codTipDoc =  infoDataOculta.get("codTipdoc").toString();
			mapDocumento.put("codTipdoc",codTipDoc);
			mapDocumento.put("codTipexp", infoDataOculta.get("codTipexp").toString());
			mapDocumento.put("numDoc", infoDataOculta.get("numDoc").toString());
			String fechaDoc = infoDataOculta.get("fechaDoc").toString();
			String fechaDocCompleta = infoDataOculta.get("fechaDocCompleta").toString();
			if(Utils.equiv(fechaDocCompleta, "null")){
				fechaDocCompleta = "";
			}
			if(fechaDocCompleta != null && !fechaDocCompleta.equals("")) {
				mapDocumento.put("fecDoc", Utils.stringToDate(fechaDocCompleta, CatalogoConstantes.INT_ONE));
			} else {
				mapDocumento.put("fecDoc", Utils.stringToDate(fechaDoc, CatalogoConstantes.INT_TWO));
			}
			mapDocumento.put("codUsuregis", usuarioBean.getNroRegistro());
			mapDocumento.put("desArch", Utils.convertirUnicode(infoDataOculta.get("nomDocumentoFisico").toString()));
			mapDocumento.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_INTERNO);
			
			//OBTENER PROPIEDADES DEL TIPO DE DOCUMENTO [PAS20191U210500076][OAC]
			mapParam = new HashMap<String, Object>();
			mapParam.put("codTipoExpediente", infoDataOculta.get("codTipexp").toString());
			mapParam.put("codTipoDocumento", infoDataOculta.get("codTipdoc").toString());
			mapParam.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			T6623TipDocExpBean t6623Bean = configuracionExpedienteService.obtenerTipDocExpediente(mapParam);
			mapDocumento.put("indVisible", t6623Bean.getIndVisibleContribuyente());
			mapDocumento.put("codTipDocSust", t6623Bean.getIndTipDocumento());
			
			//DATOS PARA EL ECM
			mapDocumento.put("numRuc", infoDataOculta.get("numRuc").toString());
			mapDocumento.put("archLength", archivo.getSize());
			mapDocumento.put("archMimeType", mimeType);
			mapDocumento.put("archFileName", archivo.getOriginalFilename());
			mapDocumento.put("binDoc",archivo.getBytes());
			mapDocumento.put("metadata", infoDataOculta.get("busqueda").toString());
			mapDocumento.put("codDep", usuarioBean.getCodDepend());
			
	        //DATOS GENERALES
			mapDocumento.put("indAcu", ValidaConstantes.IND_ACU_NO);
			mapDocumento.put("numExpedo", infoDataOculta.get("numExpedienteOrigen").toString());
			mapDocumento.put("flagOrigen", ValidaConstantes.IND_INVOC_SERVICIO_MANUAL);
			mapDocumento.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
			mapDocumento.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);
			mapDocumento.put("codTipdocRelParam", "");
			mapDocumento.put("numDocRelParam", "");
	        
			//VERIFICAR SI EL TIPO DE DOCUMENTO REALIZAR UN CAMBIO DE ESTADO Y/O ETAPA SIN CONSIDERAR LAS CONSTANCIAS DE NOTIFICACIÓN
			if (!ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {
				Map<String, Object> estEtapMap = new HashMap<String, Object>();
				estEtapMap.put("codTipoDoc", codTipDoc);
				mapDocumento.putAll(configuracionExpedienteService.obtenerEstadoEtapa(estEtapMap));
			}
			
			//----------REAPERTURAR EL EXPEDIENTE ------------//
        	Map<String, Object> parametros = null;                
        	parametros = new HashMap<String, Object>();
        	parametros.put("numExpedienteVirtual", infoDataOculta.get("numExpedienteVirtual").toString());
        	parametros.put("numExpedienteOrigen", infoDataOculta.get("numExpedienteOrigen").toString());               
        	parametros.put("codUsuarioModifica", usuarioBean.getNroRegistro());
            
           	//OBTENER RESPONSABLES
           	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(infoDataOculta.get("numExpedienteVirtual").toString());
           	
           	//OBTENER CORREOS DE RESPONSABLES
           	if (!listCodRespAsignados.isEmpty()) {
           		List<CorreosBean> listaCorreosBeans = new ArrayList<CorreosBean>();
   				for (String codColaborador : listCodRespAsignados) {
   					CorreosBean correosBean = new CorreosBean();
   					correosBean.setCodPers(codColaborador);
   					listaCorreosBeans.add(correosBean);
   				}
           		Map<String, Object> mapConsultaCorreo =  new HashMap<String, Object>();
           		mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
           		listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);   
           		parametros.put("listCorreos", listaCorreosBeans);
           	}

           	//[PAS20191U210500076] VALIDAR SI SE REQUIERE EL DOCUMENTO DE CIERRE POR TIPO DE EXPEDIENTE
           	if (!expedienteVirtualService.obviarDocCierreByTipExp(infoDataOculta.get("codTipexp").toString())) {
               	//OBTENER DOCUMENTO DE CIERRE
               	Map<String, Object> mapT6613 = new HashMap<String, Object>();
               	mapT6613.put("numExpeDv", infoDataOculta.get("numExpedienteVirtual").toString());
               	mapT6613.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
               	T6613DocExpVirtBean t6613Bean = documentoExpedienteService.obtenerDocumentoExpediente(mapT6613);
               	parametros.put("numCorDocCierre", t6613Bean.getNumCorDoc());
           	}else{
           	//PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA
           		mapDocumento.put("numCordocRel", 0);
           	//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA 
           	}
           	
           	//DATOS GENERALES
           	parametros.put("codTipdoc", infoDataOculta.get("codTipdoc").toString());
           	parametros.put("numDoc", infoDataOculta.get("numDocuemento").toString());
           	
           	String stringResult = expedienteVirtualService.ReaperturaExpVirtual(parametros, mapDocumento);
        	@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);                    
               
        	modelo.addObject("grabadoExitoso", true);
        	modelo.addObject("status", MapJsonObject.get("status"));
           	modelo.addObject("message",MapJsonObject.get("message"));
     
        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - ReaperturaExpedienteVirtualController.reabrirExpedienteVirtual");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - ReaperturaExpedienteVirtualController.reabrirExpedienteVirtual");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
	
	//Validar Archivo Metodo Controller
	public ModelAndView validarArchivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.validarArchivo");

		ModelAndView modelo = new ModelAndView(jsonView);
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		String paramTamanioIA = "IA";
		long tamanoPermitido = 0;
		int tamanoPermitidoMsj = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_MEGAS;
		int longitudnombrearchivo = 0;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ReaperturaExpedienteVirtualController.validarArchivo");
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO); // CATA_TAMANIO_TOTAL_PERMITIDO
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			multipartRequest = (MultipartHttpServletRequest) request;
			multipartFile = multipartRequest.getFile("docFisico");

			List<T01ParamBean> listaTamanioMaximo = parametroService.listarParametros(params);
			for (T01ParamBean item : listaTamanioMaximo) {
				if (paramTamanioIA.equals(item.getCodParametro())) {
					tamanoPermitido = Long.parseLong(item.getDesParametro().toString());
					tamanoPermitido = tamanoPermitido * 1048576;
					
					tamanoPermitidoMsj = Integer.parseInt(item.getDesParametro());
				}
			}
			if (multipartFile.getSize() < tamanoPermitido) {

				modelo.addObject("tamanoArchivoSuperado", false);

			} else {

				modelo.addObject("tamanoArchivoSuperado", true);
				modelo.addObject("tamanoArchivoPermitido", tamanoPermitidoMsj);

			}

			String nombreArchivo = multipartFile.getOriginalFilename();

			String[] listaSeparada = nombreArchivo.split("\\.");

			Integer num = listaSeparada.length;

			String extensionArchivo = listaSeparada[num - 1].toLowerCase();
			//cantidad maxima a cargar
			longitudnombrearchivo = CatalogoConstantes.CATA_LONGITUD_MAXIMA_ARCHIVOCARGA;
			if (nombreArchivo.length() > longitudnombrearchivo) {
				modelo.addObject("longitudnombrearchivo", false);
			} else {
				modelo.addObject("longitudnombrearchivo", true);
			}

			// extensiones permitidas para el archivo a cargar
			params.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			List<T01ParamBean> listaExtensionesPermitidas = parametroService.listarParametros(params);

			String validaPatron = null;

			if (listaExtensionesPermitidas != null && listaExtensionesPermitidas.size() > 0) {
				validaPatron = "(";
				for (T01ParamBean extensionBean : listaExtensionesPermitidas) {
					validaPatron += extensionBean.getDesParametro().trim().toLowerCase() + "|";
				}
				validaPatron = validaPatron.substring(0, validaPatron.length() - 1);
				validaPatron += ")";
			}

			Pattern patron = Pattern.compile(validaPatron);
			Matcher matcher = patron.matcher(extensionArchivo);

			if (matcher.matches()) {
				modelo.addObject("extensionPermitida", true);
			} else {
				modelo.addObject("extensionPermitida", false);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ReaperturaExpedienteVirtualController.validarArchivo");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ReaperturaExpedienteVirtualController.validarArchivo");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	//Descargar Archivo Subido
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - ReaperturaExpedienteVirtualController.descargarDocumento");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		
		try {
			multipartFile = multipartRequest.getFile("docFisico");
			String nombreArchivo = Utils.convertirUnicode(multipartFile.getOriginalFilename());
			File downloadFile = new File(nombreArchivo);
			multipartFile.transferTo(downloadFile);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +"@@"+nombreArchivo); 
	         
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	              
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(nombreArchivo));
	        response.setHeader(headerKey, headerValue);
	        int BUFFER_SIZE = 8192;
	        OutputStream outStream = response.getOutputStream();
	        
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	  
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	        	outStream.write(buffer, 0, bytesRead);
	        }
	  
	        inputStream.close();
	        outStream.close(); 
			
	      //  destino.delete();
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ReaperturaExpedienteVirtualController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ReaperturaExpedienteVirtualController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView validarNumDocumento (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.validarNumDocumento");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		//Inicio [gangles 23/08/2016]
		ResCoaBean obtenerNumDocu=null;		
		boolean flagAcumuladoCierre=false;
		List<String> tipoDocumentos=null;
		//Fin [gangles 23/08/2016]
		
		try {
			String indCarga = request.getParameter("indCarga");
		
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			} else {
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			}
				
			if(ValidaConstantes.CARGA_INICIAL.equals(indCarga)){
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				//CARGA TIPO DE DOCUMENTOS POR EXPEDIENTE
				parametros.put("codTipoExpediente", dataEnvio.get("codTipoExpediente").toString());
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				parametros.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
				parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				List<T6623TipDocExpBean> listadoTiposDocumentos = configuracionExpedienteService.listarTiposDocumentos(parametros);
				modelo.addObject("listadoTiposDocumentos", listadoTiposDocumentos);
				
				String numDocumento = dataEnvio.get("numDocumento").toString();
				String numExpeOrigen = dataEnvio.get("numExpedienteOrigen").toString();
				String codTipoDoc3Dig = dataEnvio.get("codTipoDoc3Dig").toString();
				String numeroRuc = dataEnvio.get("numRuc").toString();
				String codTipoExpediente = dataEnvio.get("codTipoExpediente").toString();
				String codIndicadorAcumulado = dataEnvio.get("codIndicadorAcumulado").toString();
				
				Map<String, Object> parametrosSirat = new HashMap<String, Object>();
				parametrosSirat.put("numExpediente", numExpeOrigen.trim());
				parametrosSirat.put("numRC", numDocumento.trim());
				parametrosSirat.put("numeroRuc", numeroRuc);
				parametrosSirat.put("codTipoDoc3Dig", codTipoDoc3Dig);
				parametrosSirat.put("coddependencia", usuarioBean.getCodDepend().trim());

				Map<String, Object> mapaObtenerNumDocu = configuracionExpedienteService.obtenerNumDocumento(parametrosSirat);
				obtenerNumDocu = (ResCoaBean) mapaObtenerNumDocu.get("obtenerNumDocumento");

				if (obtenerNumDocu != null && Utils.equiv(codIndicadorAcumulado, ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
					Map<String, Object> parametrosQuery = new HashMap<String, Object>();
					parametrosQuery.put("codClase", CatalogoConstantes.CATA_DOCUMENTOS_CIERRE);
					parametrosQuery.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					Map<String, Object> tiposDocValidos = catalogoService.obtenerCatalogoConDetalles(parametrosQuery);
					Map<String, Object> tiposDocValidosxTipExp = new HashMap<String, Object>();

					for (Map.Entry<String, Object> entry : tiposDocValidos.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						if (Utils.equiv(key.substring(0, 3), codTipoExpediente)) {
							tiposDocValidosxTipExp.put(key.substring(0, 3), value);
						}
					}

					if (!Utils.isEmpty(tiposDocValidosxTipExp) && tiposDocValidosxTipExp.size() > 0) {
						tipoDocumentos = (List<String>) tiposDocValidosxTipExp.get(codTipoExpediente);
						for (String tipoDocumento : tipoDocumentos) {
							if (obtenerNumDocu.getCod_tip_doc()!= null && Utils.equiv(tipoDocumento, obtenerNumDocu.getCod_tip_doc().trim())) {
								flagAcumuladoCierre = true;
								break;
							}
						}
					}
				}

				if (flagAcumuladoCierre == false) {

					modelo.addObject("flagConsultaSirat", mapaObtenerNumDocu.get("flagConsultaSirat"));
					modelo.addObject("listaEspecifica", mapaObtenerNumDocu.get("listaEspecifica")); // lista para documentos alternativos
				}
				modelo.addObject("obtenerNumDocu", obtenerNumDocu);
				modelo.addObject("flagAcumuladoCierre", flagAcumuladoCierre);

			}else {
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			}
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ReaperturaExpedienteVirtualController.validarNumDocumento");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ReaperturaExpedienteVirtualController.validarNumDocumento");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView validarDocuemntoExistente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.validarDocuemntoExistente");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        	
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
        	@SuppressWarnings("unchecked")                 
        	Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
        	Map<String, Object> parametros = new HashMap<String, Object>();  
        	
        	modelo = new ModelAndView(jsonView);
        	
			parametros.put("codTipDoc", dataEnvio.get("codTipoDocumento").toString().trim());
			parametros.put("numDoc", dataEnvio.get("numDocuemento").toString().trim());
			parametros.put("numExpeDv", dataEnvio.get("numExpeVirtual").toString().trim());
		
			T6613DocExpVirtBean result = documentoExpedienteService.obtenerDocumentoExpediente(parametros);
			if(!Utils.isEmpty(result)){
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_06_019);
			}

        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - MantemientoExpedienteVirtualController.validarDocuemntoExistente");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - MantemientoExpedienteVirtualController.validarDocuemntoExistente");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
	
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
	
	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	
	public void setConfiguracionExpedienteService (ConfiguracionExpedienteService configuracionExpedienteService){
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}
	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}
	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	
}