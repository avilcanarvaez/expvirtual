package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.humano.asistencia.util.Constantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.service.ResolucionService;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class CierraExpedienteVirtualController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(CierraExpedienteVirtualController.class);
	
	private JsonView jsonView;
	
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private RequerimientoService requerimientoService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ResponsableService responsableService;
	private DocumentoExpedienteService documentoExpedienteService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	private ResolucionService resolucionService;//[oachahuic][PAS20165E210400270]
	
	public ModelAndView cargarCierreExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - CierraExpedienteVirtualController.cargarCierreExpedienteVirtual");

		ModelAndView modelo = null;

		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - CierraExpedienteVirtualController.cargarCierreExpedienteVirtual");

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

				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_05_001);

				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

				String codDependencia = usuarioBean.getCodDepend();

				Map<String, Object> titulos = new HashMap<String, Object>();

				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_05_001);

				Map<String, Object> excepciones = new HashMap<String, Object>();

				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_05_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_05_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_05_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_05_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_05_005);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_05_006);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_01_05_007);
				excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_01_05_008);
				excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_01_05_009);
				excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_01_05_010);
				// Inicio [jjurado 26/05/2016]
				excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_05_011);
				excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_01_05_012);
				excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_01_05_013);
				// Fin [jjurado 26/05/2016]
				excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_01_05_014);//[oachahuic][PAS20165E210400270]
				excepciones.put("excepcion15", AvisoConstantes.EXCEP_MODULO_01_05_015);//[oachahuic][PAS20165E210400270]
				Map<String, Object> avisos = new HashMap<String, Object>();

				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_05_001);
				avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_05_002);

				Date fecSistema = new Date();

				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

				String fecVista = formatoFechaExporta.format(fecSistema);

				String codEstadoExpedientePermitido = ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO;

				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

				Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);

				String desEstadoExpedientePermitido = mapaEstadoExpediente.get(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO).toString();

				List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();

				Long numBytesArchivo = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_BYTES;
				Integer numMegasArchivo = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_MEGAS;

				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				//Inicio [oachahuic][PAS20165E210400270]
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
				} else {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
				}
				//Fin [oachahuic][PAS20165E210400270]
				modelo.addObject("codEstadoExpedientePermitido", new JsonSerializer().serialize(codEstadoExpedientePermitido));
				modelo.addObject("desEstadoExpedientePermitido", new JsonSerializer().serialize(desEstadoExpedientePermitido));
				modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));
				modelo.addObject("numBytesArchivo", new JsonSerializer().serialize(numBytesArchivo));
				modelo.addObject("numMegasArchivo", new JsonSerializer().serialize(numMegasArchivo));
			//Inicio - [oachahuic][PAS20165E210400270]
			} else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {//VALIDAR EL INGRESO DEL NÚMERO DE EXPEDIENTE

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
				
				//VERIFICAR LA EXISTENCIA DEL NUMERO EXPEDIENTE INGRESADO
				parametros = new HashMap<String, Object>();
				parametros.put("indClaseExpediente", dataEnvio.get("indClaseExpediente").toString());
				parametros.put("numExpediente", dataEnvio.get("numExpediente").toString());
				T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
				if (expedienteVirtual == null) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_003);
					return modelo;
				}
				//VERIFICAR SI EL EXPEDIENTE VIRTUAL PERTENECE A LA DEPENDENCIA DEL USUARIO EN SESIÓN
				if (!Utils.equiv(codDependenciaBase.substring(0, 3), expedienteVirtual.getCodDependencia().substring(0, 3))) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_006);
					return modelo;
				}
				//VERIFICAR SI EL USUARIO EN SESIÓN ES RESPONSABLE DEL EXPEDIENTE VIRTUAL
				parametros = new HashMap<String, Object>();
				parametros.put("num_expedv_par", expedienteVirtual.getNumExpedienteVirtual());
				parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
				parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				T6621RespExpVirtBean t6621Bean = responsableService.obtenerResponsable(parametros);
				if (t6621Bean == null) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_014);
					return modelo;
				}
				//VERIFICAR SI EL EXPEDIENTE VIRTUAL SE ENCUENTRA ABIERTO
				if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO.equals(expedienteVirtual.getCodEstado())) {
					modelo.addObject("expedienteVirtual", null);
					//CVG modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_004.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, expedienteVirtual.getCodEstado()));
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_016);
					return modelo;
				}
				

				if (!(Utils.equiv(expedienteVirtual.getCodTipoExpediente().toString().trim(), ValidaConstantes.TIPO_EXPE_FISCA_DEFINITIVA_PARCIAL) || Utils.equiv(expedienteVirtual.getCodTipoExpediente().toString().trim(), ValidaConstantes.TIPO_EXPE_CRUCE_INFORMACION))) {		//VERIFICAR SI EXISTEN REQUERIMIENTOS ABIERTOS Y/O ATENDIDOS PARCIALMENTE
				//VERIFICAR SI EXISTEN REQUERIMIENTOS ABIERTOS Y/O ATENDIDOS PARCIALMENTE
				parametros = new HashMap<String, Object>();
				parametros.put("numExpedienteVirtual", expedienteVirtual.getNumExpedienteVirtual());
				List<String> listCodEstReq = new ArrayList<String>();
				listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
				listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
				parametros.put("listCodEstReq", listCodEstReq);
				List<T6620RequerimBean> listadoRequerimientos = requerimientoService.listarRequerimientos(parametros);
				if (!listadoRequerimientos.isEmpty()) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_007);
					return modelo;
				}
				}
				
				//VERIFICAR QUE EL EXPEDIENTE VIRTUAL NO SEA UN EXPEDIENTE ACUMULADO
				if (Utils.equiv(expedienteVirtual.getIndicadorAcumulado(), ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_013);
					return modelo;
				}
				//VERIFICAR NÚMERO DE RUC
				DdpBean contribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
				if (contribuyente == null) {
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_005);
					return modelo;
				}
				//[PAS20191U210500076] VALIDAR SI SE REQUIERE EL DOCUMENTO DE CIERRE POR TIPO DE EXPEDIENTE
				T6613DocExpVirtBean documentoCierre = null;
				if (!expedienteVirtualService.obviarDocCierreByTipExp(expedienteVirtual.getCodTipoExpediente())) {
					//VERIFICAR SI EXISTEN LOS TIPOS DE DOCUMENTOS DE CIERRE PARA EL TIPO DE EXPEDIENTE
					parametros = new HashMap<String, Object>();
					parametros.put("codTipoExpediente", expedienteVirtual.getCodTipoExpediente());
					parametros.put("indEliminado", ValidaConstantes.INDICADOR_ELIMINADO_NO_ASOC_TIPEXP_TIPDOC);
					parametros.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
					List<T6623TipDocExpBean> listTipDocCierre = configuracionExpedienteService.listarTipDocExpediente(parametros);
					if (listTipDocCierre.isEmpty()) {
						modelo.addObject("expedienteVirtual", null);
						modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_012.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, expedienteVirtual.getDesTipoExpediente()));
						return modelo;
					}
					//VERIFICAR SI EXISTE DOCUMENTOS DE CIERRE
					parametros = new HashMap<String, Object>();
					parametros.put("numExpeDv", expedienteVirtual.getNumExpedienteVirtual());
					parametros.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
					documentoCierre = documentoExpedienteService.obtenerDocumentoExpediente(parametros); 
					if (documentoCierre == null) {
						modelo.addObject("expedienteVirtual", null);
						modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_015);
						return modelo;
					}
					//VERIFICAR SI EXISTE LA CONSTANCIA DE NOTIFICACIÓN DEL DOCUMENTO DE CIERRE
					if (Utils.esFechaVacia(documentoCierre.getFecNotif())) {
						modelo.addObject("expedienteVirtual", null);
						modelo.addObject("desError", AvisoConstantes.EXCEP_MODULO_01_05_011);
						return modelo;
					}
				}
				if (documentoCierre != null) {//CARGAR DATOS DEL DOCUMENTO DE CIERRE
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					modelo.addObject("fechaEmisionDoc", sdf.format(documentoCierre.getFecDoc()));
					modelo.addObject("fechaNotificacionDoc", sdf.format(documentoCierre.getFecNotif()));
					T01ParamBean T01TipDoc = configuracionExpedienteService.obtenerTipoDoc(documentoCierre.getCodTipDoc(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					documentoCierre.setDesNombreCompuesto(documentoCierre.getCodTipDoc() + " - " +T01TipDoc.getDesParametro());
					modelo.addObject("t6613DocExpVirtBean", documentoCierre);
				}
                //PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA 
				if(ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(expedienteVirtual.getCodTipoExpediente()) ||
				   ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(expedienteVirtual.getCodTipoExpediente())	||
				   ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(expedienteVirtual.getCodTipoExpediente())){
					
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("cod_dep", expedienteVirtual.getCodDependencia());
					params.put("tip_exp", expedienteVirtual.getCodTipoExpediente());
					params.put("num_exp", dataEnvio.get("numExpediente").toString());
										
					T6614ExpVirtualBean expVirtual = expedienteVirtualService.verificarCierre(params);
					
                    if(expVirtual == null){
						
						expVirtual = new T6614ExpVirtualBean();
						expVirtual.setDesMotivoCierre("Ocurrió un error al verificar el cierre de expediente");
						modelo.addObject("expedienteVirtual", null);
						modelo.addObject("desError", expVirtual.getDesMotivoCierre());
						return modelo;
							
					} else{
						
						if(ValidaConstantes.FILTRO_CERO.equals(expVirtual.getCodRetornoServicio())){
														
							modelo.addObject("expedienteVirtual", null);
							modelo.addObject("desError", expVirtual.getDesMotivoCierre());
							return modelo;
							
						}					
					}

				}
				//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA
				
				modelo.addObject("listadoTiposDocumentos", new ArrayList<T6623TipDocExpBean>());
				modelo.addObject("listadoEstadoCierreExpediente", parametroService.listarEstadosCierreExpediente());
				
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
				expedienteVirtual.setDesProceso(mapaProcesos.get(expedienteVirtual.getCodProceso()).toString());
				expedienteVirtual.setDesTipoExpediente(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente()).toString());
				modelo.addObject("expedienteVirtual", expedienteVirtual);
				modelo.addObject("contribuyente", contribuyente);
			}
			//Fin - [oachahuic][PAS20165E210400270]
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CierraExpedienteVirtualController.cargarCierreExpedienteVirtual");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CierraExpedienteVirtualController.cargarCierreExpedienteVirtual");
		}
		return modelo;
	}
	
	public ModelAndView validarArchivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (log.isDebugEnabled()) log.debug("Inicio - CierraExpedienteVirtualController.validarArchivo");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		String paramTamanioIA = "IA";
		long tamanoPermitido = 0;
		int tamanoPermitidoMsj = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_MEGAS;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - CierraExpedienteVirtualController.validarArchivo");
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
			
			if(multipartFile.getSize() < tamanoPermitido){
				
				modelo.addObject("tamanoArchivoSuperado", false);
				
			} else {
				
				modelo.addObject("tamanoArchivoSuperado", true);
				modelo.addObject("tamanoArchivoPermitido", tamanoPermitidoMsj);
				
			}
			
			String nombreArchivo = multipartFile.getOriginalFilename();
			
			String[] listaSeparada = nombreArchivo.split("\\.");
			
			Integer num = listaSeparada.length;
			
			String extensionArchivo = listaSeparada[num-1].toLowerCase();
			
			params = new HashMap<String, Object>();
			
			params.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			List<T01ParamBean> listaExtensionesPermitidas = parametroService.listarParametros(params);
			
			String validaPatron = null;
			
			if (listaExtensionesPermitidas != null && listaExtensionesPermitidas.size() > 0) {
				
				validaPatron = "(";
				
				for(T01ParamBean extensionBean : listaExtensionesPermitidas) {
					
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
			
			if (log.isDebugEnabled()) log.debug("Error - CierraExpedienteVirtualController.validarArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - CierraExpedienteVirtualController.validarArchivo");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - CierraExpedienteVirtualController.resolveException(MaxUploadSizeExceededException)");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		
		if (exception instanceof MaxUploadSizeExceededException) {
			
			Boolean tamanoArchivoSuperado = false;
			
			try {
				
				if (log.isDebugEnabled()) log.debug("Procesa - CierraExpedienteVirtualController.resolveException(MaxUploadSizeExceededException)");
				
				modelo.addObject("tamanoArchivoSuperado", tamanoArchivoSuperado);
				
			} catch (Exception ex) {
				
				if (log.isDebugEnabled()) log.debug("Error - CierraExpedienteVirtualController.resolveException(MaxUploadSizeExceededException)");
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);
				
			} finally {
				
				if (log.isDebugEnabled()) log.debug("Final - CierraExpedienteVirtualController.resolveException(MaxUploadSizeExceededException)");
				NDC.pop();
				NDC.remove();
				
			}
			
		}
		
		return modelo;
		
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView cerrarExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - CierraExpedienteVirtualController.cerrarExpedienteVirtual");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		List<Map<String, Object>> listaParametros = new ArrayList<Map<String, Object>>();
		SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
		
		try {
			//RECUPERAR DATOS DE SESION
			HttpSession session = request.getSession(true);
			UsuarioBean usuarioBean = null;
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			} else {
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			}
			
			//RECUPERAR DATOS DE LA VISTA
			String dataOculta = request.getParameter("dataOculta").toString();
			Map<String, Object> infoExpediente = (Map<String, Object>) new JsonSerializer().deserialize(dataOculta, Map.class);
			
			//RECUPERAR DATOS DEL EXPEDIENTE VIRTUAL
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL);
			mapParam.put("numExpediente", infoExpediente.get("numExpedienteVirtual").toString());
			T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(mapParam);
			
			//RECUPERAR DOCUMENTO ORIGEN
			mapParam = new HashMap<String, Object>();
			mapParam.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			mapParam.put("numExpeDv", expedienteVirtual.getNumExpedienteVirtual().trim());
			T6613DocExpVirtBean documentoOrigen = expedienteVirtualService.obtenerDatosDocumentoEstado(mapParam);
			
			//RECUPERAR RESPONSABLES DEL EXPEDIENTE VIRTUAL
			List<T6621RespExpVirtBean> listaRespAsignados = responsableService.obtenerResponsablesAsignados(expedienteVirtual.getNumExpedienteVirtual().trim());
			List<String> listaColaboradores = new ArrayList<String>();
			for (T6621RespExpVirtBean t6621Bean : listaRespAsignados) {
				listaColaboradores.add(t6621Bean.getCodColaborador().trim());
			}
			
			//RECUPERAR DOCUMENTOS DEL EXPEDIENTE VIRTUAL
			mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedienteVirtual", expedienteVirtual.getNumExpedienteVirtual().trim());
			List<T6613DocExpVirtBean> listadoDocumentosExpediente = documentoExpedienteService.listarDocumentosExpediente(mapParam);
			
			//PREPARAR DATOS DE CIERRE
			Map<String, Object> mapParamCierre = new HashMap<String, Object>();
			mapParamCierre.put("indOrigenInvoca", ValidaConstantes.IND_INVOC_SERVICIO_MANUAL);
			mapParamCierre.put("numExpedienteVirtual", expedienteVirtual.getNumExpedienteVirtual().trim());
			mapParamCierre.put("numExpedienteOrigen", expedienteVirtual.getNumExpedienteOrigen().trim());
			mapParamCierre.put("numRuc", infoExpediente.get("numRuc").toString());
			mapParamCierre.put("indReporteIndice", ValidaConstantes.FILTRO_DOS);
			if (infoExpediente.get("numCorDocCierre") != null) {//[PAS20181U210400241]
				mapParamCierre.put("numCorDocCierre", ((Long) infoExpediente.get("numCorDocCierre")).intValue());
			}
			mapParamCierre.put("codUsuario", usuarioBean.getNroRegistro());
			mapParamCierre.put("numExpedienteOrigen", expedienteVirtual.getNumExpedienteOrigen().trim());
			mapParamCierre.put("codProceso", expedienteVirtual.getCodProceso());
			mapParamCierre.put("codTipoExpediente", expedienteVirtual.getCodTipoExpediente());
			mapParamCierre.put("fecEmi", formatoFechaExporta.format(documentoOrigen.getFecDoc()));
			mapParamCierre.put("codDep", expedienteVirtual.getCodDependencia());
			mapParamCierre.put("codOrigenDocumento", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTRANET);
			mapParamCierre.put("codOrigenCierre", ValidaConstantes.IND_ORIGEN_CIERRE_EXPVIRT_MANUAL);
			mapParamCierre.put("codEstadoCierre", infoExpediente.get("codEstadoCierre").toString());
			mapParamCierre.put("desMotivoCierre", request.getParameter("hdnMotivoCierre").toString());
			mapParamCierre.put("desSumilla", request.getParameter("hdnSumilla").toString());
			mapParamCierre.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);
			mapParamCierre.put("listaColaboradores", listaColaboradores);
            
            //PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA 
			if(ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(expedienteVirtual.getCodTipoExpediente()) ||
			   ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(expedienteVirtual.getCodTipoExpediente())	||
			   ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(expedienteVirtual.getCodTipoExpediente())){
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("cod_dep", expedienteVirtual.getCodDependencia());
				params.put("tip_exp", expedienteVirtual.getCodTipoExpediente());
				params.put("num_exp", expedienteVirtual.getNumExpedienteOrigen().trim());
								
				T6614ExpVirtualBean expVirtual = expedienteVirtualService.verificarCierre(params);
		
				if(expVirtual == null){
					
					expVirtual = new T6614ExpVirtualBean();
					expVirtual.setDesMotivoCierre("Ocurrió un error al verificar el cierre de expediente");
					modelo.addObject("expedienteVirtual", null);
					modelo.addObject("desError", expVirtual.getDesMotivoCierre());
					return modelo;
						
				} else{
					
					if(ValidaConstantes.FILTRO_CERO.equals(expVirtual.getCodRetornoServicio())){
													
						modelo.addObject("expedienteVirtual", null);
						modelo.addObject("desError", expVirtual.getDesMotivoCierre());
						return modelo;
						
					} else {
                        
						mapParamCierre.put("fecCierre", expVirtual.getFecCierre());						
						
					}
				}				
			}
			//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA

			
			//RECUPERAR EXPEDIENTES ACUMULADOS
			mapParam = new HashMap<String, Object>();
			mapParam.put("numAcu", expedienteVirtual.getNumExpedienteOrigen().trim());
			List<Map<String, Object>> listMapExpAcum = expedienteVirtualService.listarNumExpVirtualAcumCierre(mapParam);
			Map<String, Object> mapParamExpAcu = null;
			List<T6613DocExpVirtBean> listDocExpAcu = null;
			for (Map<String, Object> mapExpAcum : listMapExpAcum) {
				mapParamExpAcu = new HashMap<String, Object>();
				mapParamExpAcu.put("indOrigenInvoca", ValidaConstantes.IND_INVOC_SERVICIO_MANUAL);
				mapParamExpAcu.put("numExpedienteVirtual", mapExpAcum.get("numExpedv").toString().trim());
				mapParamExpAcu.put("numExpedienteOrigen", mapExpAcum.get("numExpedo").toString().trim());
				mapParamExpAcu.put("numRuc", infoExpediente.get("numRuc").toString());
				mapParamExpAcu.put("indReporteIndice", ValidaConstantes.FILTRO_CERO);
				mapParamExpAcu.put("codOrigenCierre", ValidaConstantes.IND_ORIGEN_CIERRE_EXPVIRT_MANUAL);
				mapParamExpAcu.put("codEstadoCierre", mapParamCierre.get("codEstadoCierre"));
				mapParamExpAcu.put("desMotivoCierre", mapParamCierre.get("desMotivoCierre"));
				mapParamExpAcu.put("desSumilla", mapParamCierre.get("desSumilla"));
				mapParamExpAcu.put("codUsuario", usuarioBean.getNroRegistro());
				mapParamExpAcu.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);
				
				//RECUPERAR RESPONSABLES DEL EXPEDIENTE
				listaRespAsignados = responsableService.obtenerResponsablesAsignados(mapExpAcum.get("numExpedv").toString().trim());
				listaColaboradores = new ArrayList<String>();
				for (T6621RespExpVirtBean t6621Bean : listaRespAsignados) {
					listaColaboradores.add(t6621Bean.getCodColaborador().trim());
				}
				mapParamExpAcu.put("listaColaboradores", listaColaboradores);
				listaParametros.add(mapParamExpAcu);
				
				//RECUPERAR DOCUMENTOS DEL EXPEDIENTE ACUMULADO
				mapParam = new HashMap<String, Object>();
				mapParam.put("numExpedienteVirtual", mapExpAcum.get("numExpedv").toString().trim());
				listDocExpAcu = documentoExpedienteService.listarDocumentosExpediente(mapParam);
				
				//AGREGAR DOCUMENTOS AL EXPEDIENTE ACUMULADOR
				listadoDocumentosExpediente.addAll(listDocExpAcu);
			}
			mapParamCierre.put("listadoDocumentosExpediente", listadoDocumentosExpediente);
			listaParametros.add(mapParamCierre);
			
			expedienteVirtualService.cerrarExpedienteVirtualAcum(listaParametros);			
			
			modelo.addObject("numExpedienteVirtual", expedienteVirtual.getNumExpedienteVirtual().trim());
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CierraExpedienteVirtualController.cerrarExpedienteVirtual");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CierraExpedienteVirtualController.cerrarExpedienteVirtual");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	/*
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CierraExpedienteVirtualController.descargarDocumento");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		
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
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CierraExpedienteVirtualController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CierraExpedienteVirtualController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
		
	}
	*/
	@RequestMapping(value = "/verDatosExpedientePDF", method = RequestMethod.POST)
	public void verDatosExpedientePDF(HttpServletRequest request,HttpServletResponse response){
		
		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - CierraExpedienteVirtualController.verDatosExpedientePDF(descargarDocumento)");
		}
		
		try {
			
			//Obtenemos los datos del JSP
			String txtDesTipNumExpediente = Utils.toBlank(Utils.toStr(request.getParameter("txtDesTipNumExpediente")));
			String txtNumExpVirtual = Utils.toBlank(Utils.toStr(request.getParameter("txtNumeroExpediente")));
			String txtFechaRegistro = Utils.toBlank(Utils.toStr(request.getParameter("txtFechaSistema")));
			
			String txtNumRuc =Utils.toBlank( Utils.toStr(request.getParameter("txtNumRuc")));
			String txtDesRazonSocial = Utils.toBlank(Utils.toStr(request.getParameter("txtDesRazonSocial")));
			txtDesRazonSocial = txtDesRazonSocial.replace("\"", "''");
			String txtDesProceso = Utils.toBlank(Utils.toStr(request.getParameter("txtDesProceso")));
			String txtDesTipoExpediente = Utils.toBlank(Utils.toStr(request.getParameter("txtDesTipoExpediente")));
			//Inicio [gangles 09/08/2016]
			String txtTipoDoc = Utils.toBlank(Utils.toStr(request.getParameter("txtTipoDoc")));
			String txtNroDoc = Utils.toBlank(Utils.toStr(request.getParameter("txtNroDoc")));
			String txtFecEmisionDoc = Utils.toBlank(Utils.toStr(request.getParameter("txtFecEmisionDoc")));
			String txtFecNotificacionDoc = Utils.toBlank(Utils.toStr(request.getParameter("txtFecNotificacionDoc")));
			//Fin [gangles 09/08/2016]
			String desEstadoCierre = Utils.toBlank(Utils.toStr(request.getParameter("desEstadoCierre")));
			if(Utils.equiv(desEstadoCierre, "Seleccione")){
				desEstadoCierre="";
			}
			String txtDesMotivoCierre = Utils.toBlank(Utils.toStr(request.getParameter("txtDesMotivoCierre")));
			txtDesMotivoCierre = txtDesMotivoCierre.replace("\"", "''");
			String txtDesSumilla = Utils.toBlank(Utils.toStr(request.getParameter("txtDesSumilla")));
			txtDesSumilla = txtDesSumilla.replace("\"", "''");
			//String txtBusquedaClave = Utils.toBlank(Utils.toStr(request.getParameter("txtBusquedaClave")));
			
			//Generamos el PDF
			String 	json = "{"+"\""+"cabecera"+"\":{";
			json+="\""+"txtNumExpVirtual"+"\":"+"\""+txtNumExpVirtual;
			json+="\","+"\""+"txtFechaRegistro"+"\":"+"\""+txtFechaRegistro;
			json+="\","+"\""+"txtNumRuc"+"\":"+"\""+txtNumRuc;
			json+="\","+"\""+"txtDesRazonSocial"+"\":"+"\""+txtDesRazonSocial.replace("\"", "\\\"");
			json+="\","+"\""+"txtDesTipNumExpediente"+"\":"+"\""+txtDesTipNumExpediente;
			json+="\","+"\""+"selProceso"+"\":"+"\""+txtDesProceso;
			json+="\","+"\""+"selTipoExpediente"+"\":"+"\""+txtDesTipoExpediente;
			json+="\","+"\""+"desEstadoCierre"+"\":"+"\""+desEstadoCierre;
			/*if(!txtBusquedaClave.contains("\"")){
				json+="\","+"\""+"txtBusquedaClave"+"\":"+"\""+txtBusquedaClave;
			}*/
			json+="\","+"\""+"txtDesMotivoCierre"+"\":"+"\""+txtDesMotivoCierre;
			//Inicio [gangles 09/08/2016] 
			json+="\","+"\""+"txtTipoDoc"+"\":"+"\""+txtTipoDoc;
			json+="\","+"\""+"txtNroDoc"+"\":"+"\""+txtNroDoc;
			json+="\","+"\""+"txtFecEmisionDoc"+"\":"+"\""+txtFecEmisionDoc;
			json+="\","+"\""+"txtFecNotificacionDoc"+"\":"+"\""+txtFecNotificacionDoc;
			//Fin [gangles 09/08/2016] 
			json+="\","+"\""+"txtDesSumilla"+"\":"+"\""+txtDesSumilla;
			json+="\"}";
			json+= "}";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("json", json);
			parametros.put("codPDF", ExportaConstantes.PDF_COD_PLANTILLA_01_003);
			
			byte[] docConstPresentDocu = expedienteVirtualService.verDatosExpedientePDF(parametros);
			OutputStream outStream = response.getOutputStream();
			response.setContentLength(docConstPresentDocu.length);
		    response.setContentType("application/pdf");
		    outStream.write(docConstPresentDocu);
		    outStream.close(); 
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CierraExpedienteVirtualController.verDatosExpedientePDF");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CierraExpedienteVirtualController.verDatosExpedientePDF");
			}
			NDC.pop();
			NDC.remove();
		}	
		
	}
	
	/* Seteo - Spring - Inicio */
	
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
	
	public void setRequerimientoService(RequerimientoService requerimientoService) {
		this.requerimientoService = requerimientoService;
	}
	
	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	
	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}
	
	public void setDocumentoExpedienteService(DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	public void setResolucionService(ResolucionService resolucionService) {
		this.resolucionService = resolucionService;
	}
	
	/* Seteo - Spring - Final */
	
}