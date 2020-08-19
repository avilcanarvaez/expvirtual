package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ALIGN;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;
/**
 * 
 * @author nchavez
 * 
 */
//Inicio [nchavez 24/05/2015]
@Controller
public class RegistraRequerimientoManualController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(RegistraRequerimientoManualController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private ValidarParametrosService validarService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private EcmService ecmService;
	
	// Inicio [jquispe 27/05/2016]
		private SeguimientoService seguiService;
		private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]

	@RequestMapping(value = "/consultarExpedientesVirtualesView", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView consultarExpedientesVirtualesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - RegistraRequerimientoManualController.consultarExpedientesVirtualesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");

			modelAndView = new ModelAndView("ConsultaExpedientesVirtualesManualesIntranet");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			// Inicio [nchavez 24/05/2015]
			//Inicio [oachahuic][PAS20165E210400270]
			List<T01ParamBean> listadoDependencias = new ArrayList<T01ParamBean>();
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				if(!Utils.isEmpty(listadoDependenciasIni)){
					for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
						if(Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
								&& (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))){
							t01ParamBean.setDesParametro(t01ParamBean.getCodParametro()+" - " + t01ParamBean.getDesParametro());
							listadoDependencias.add(t01ParamBean);
						}
	                }
				}
			} else {
				usuarioBean.setCodDepend(mapDepAdu.get("ADUANA").toString().trim());
				T01ParamBean t01ParamBean = new T01ParamBean();
				t01ParamBean.setCodParametro(mapDepAdu.get("ADUANA").toString().trim());
				t01ParamBean.setDesParametro(mapDepAdu.get("ADUANA").toString().trim() + " - " + mapDepAdu.get("DEPEN2").toString().trim());
				listadoDependencias.add(t01ParamBean);
			}
			//Fin [oachahuic][PAS20165E210400270]
			
			// Fin [nchavez 24/05/2015]
			
			List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			
			List<T01ParamBean> listadoTipoFecha= paramService.listarTipoFecha();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_01_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_01_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_01_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_02_01_004);
			excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_02_01_005);
			excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_02_01_006);
			excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_02_01_008_01);
			excepciones.put("excepcion08",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
			
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize( listadoProcesos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			modelAndView.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias) );
			modelAndView.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente) );
			modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha) );
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim() );
			} else {
				modelAndView.addObject("dependencia", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistraRequerimientoManualController.consultarExpedientesVirtualesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.consultarExpedientesVirtualesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoManualController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoManualController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/consultarDetalleExpedienteVirtualView", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView consultarDetalleExpedienteVirtualView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.consultarDetalleExpedienteVirtualView");
		
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		HashMap<String, Object> mapParametrosBusqueda;
		DdpBean beanContribuyente;
		List<T6620RequerimBean> listaRequerimientos = new ArrayList<T6620RequerimBean>();
		String fechaDocOrigen="";
		String fechaRegExpediente="";
		// Inicio [jquispe 16/06/2016] Numero del expediente virtual.
		String parametroNumeroExpedienteVirtual = null;
		// Fin [jquispe 16/06/2016]
		// Inicio [jquispe 16/06/2016] Permite realiza la accion solo cuando es enviada por la pagina principal.
		boolean flagPaginaPrincipal = false;
		// Fin [jquispe 16/06/2016]
		try {
			
			String numExp = Utils.toStr( request.getParameter("numExpedienteVirtual"));
			mapParametrosBusqueda = new HashMap<String, Object>();
			modelAndView = new ModelAndView("ConsultaRequerimientosManualesAbiertos");
			// Inicio [jquispe 16/06/2016] Permite realiza la accion solo cuando es enviada por la pagina principal.
			flagPaginaPrincipal = Boolean.parseBoolean(Utils.toStr(request.getParameter("flagPaginaPrincipal")));
			// Fin [jquispe 16/06/2016]
			
			mapParametrosBusqueda.put("numExpedVirtual", numExp);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				// Inicio [jquispe 16/06/2016] Numero del expediente virtual.
				 parametroNumeroExpedienteVirtual = t6614ExpVirtualBean.getNumExpedienteVirtual();
				// Fin [jquispe 16/06/2016]
				 
				 modelAndView.addObject("fechaDocOrigen",fechaDocOrigen);
				 modelAndView.addObject("fechaRegExpediente",fechaRegExpediente);
				 modelAndView.addObject("razonSocial",beanContribuyente.getDesRazonSocial().trim());
				 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 break;
			}
			
			//[nchavez 27/05/16]
			//Solo Se lista los Requerimientos que fueron registrados Manualmente
			//mapParametrosBusqueda.put("codOrigenReq",ValidaConstantes.IND_ORIGEN_REQ_MANUAL);
			listaRequerimientos = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);
			
			modelAndView.addObject("listaRequerimientos",new JsonSerializer().serialize(listaRequerimientos));
			
			// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.
			if(flagPaginaPrincipal){
			  registrarSeguimientoDetalleExpedienteVirtual(parametroNumeroExpedienteVirtual);
			}
			// Fin [jquispe 16/06/2016]
			 
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoManualController.consultarDetalleExpedienteVirtualView");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.consultarDetalleExpedienteVirtualView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.	
	private void registrarSeguimientoDetalleExpedienteVirtual(String numeroExpedienteVirtual) throws Exception{
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.registrarSeguimientoDetalleExpedienteVirtual");
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_REQUERIMIENTO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_REQUERIMIENTOS);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_REQUERIMIENTOS)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);					
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		
		seguiService.registrarSeguimiento(beanSegui);
		
		if (log.isDebugEnabled()) {
			log.debug( "Final - RegistraRequerimientoManualController.registrarSeguimientoDetalleExpedienteVirtual");
		}
		
	}
	// Fin [jquispe 16/06/2016]

	@RequestMapping(value = { "/validarContribuyente" }, method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - RegistraRequerimientoManualController.validarContribuyente");
		
		DdpBean beanContribuyente;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;

		try {
			modelAndView = new ModelAndView(jsonView);
			String numeroRuc = Utils.toStr( request.getParameter("numRuc"));

			if (Utils.isEmpty(session) || Utils.isEmpty(session.getAttribute("usuarioBean")) || !Utils.isEmpty(request.getParameter("usub")) ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String codDepUsuario=usuarioBean.getCodDepend();

			// validamos numero de ruc
			//Inicio [oachahuic][PAS20165E210400270]
			if (!Utils.isEmpty(numeroRuc) && !Utils.isEmpty(codDepUsuario)) {
				beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
				if (Utils.isEmpty(beanContribuyente.getNumRuc())) {
					modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_04_01_019);
				} else {
					if (codDepUsuario.length() == 3) {
						beanContribuyente = aduanaService.obtenerAgenteHabilitado(beanContribuyente.getNumRuc(), codDepUsuario);
					}
					if (!Utils.equiv(beanContribuyente.getCodDependencia().substring(0, 3), codDepUsuario.substring(0, 3))) {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_04_01_018);
					} else {
						modelAndView.addObject("razonSocial", beanContribuyente.getDesRazonSocial());
					}
				}
			} else {
				modelAndView.addObject("msjError", AvisoConstantes.MSJ_CONSULTA_RUC_ERROR);
			}
			//Fin [oachahuic][PAS20165E210400270]
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoManualController.validarContribuyente");
			}
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraRequerimientoManualController.validarContribuyente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	
	@RequestMapping(value = { "/cargarListaTiposExpediente" }, method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - RegistraRequerimientoManualController.cargarListaTiposExpediente");
		}
		try {
			String codProceso = Utils.toStr( request.getParameter("codProceso"));

			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			// mapParametrosBusqueda.put("indConsulta", "2");
			List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			modelAndView = new ModelAndView((View) this.jsonView);
			modelAndView.addObject("listadoTiposExpendientes",listadoTiposExpendientes);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoManualController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraRequerimientoManualController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	
	@RequestMapping(value = "/cargarListadoExpedientesVirtuales", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cargarListadoExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - RegistraRequerimientoManualController.cargarListadoExpedientesVirtuales");
		}

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();//[jquispe 06/07/2016] Lista de requerimientos.
		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
		// Inicio [jquispe 16/06/2016] Realiza la accion solo cuando el usuario realiza la consulta.
		boolean flagBusquedaBoton = false;
		// Fin [jquispe 16/06/2016]
		
		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numExp = Utils.toStr(request.getParameter("numExp"));
		String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
		
		Date fecDesde = null;
		Date fecHasta = null;

		try {
			
			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")),CatalogoConstantes.INT_TWO);
				Calendar calendarDesde=Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde=calendarDesde.getTime();
			}
			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")),CatalogoConstantes.INT_TWO);
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")),CatalogoConstantes.INT_TWO);
				Calendar calendarHasta=Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta=calendarHasta.getTime();
			}

			modelAndView = new ModelAndView(jsonView);
			// Inicio [jquispe 27/05/2016] Realiza la accion solo cuando el usuario realiza la consulta.
			flagBusquedaBoton = Boolean.parseBoolean(Utils.toStr(request.getParameter("flagBusquedaBoton")));
			// Fin [jquispe 27/05/2016]

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			//mapParametrosBusqueda.put("codEstado", "01");//[jquispe 06/07/2016] No se debe de enviar este parametro para las consultas.
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim() );
			//mapParametrosBusqueda.put("codDependencia",usuarioBean.getCodDepend().trim());//[jquispe 13/07/2016]
			
			//[jquispe 13/07/2016] Agrega el codigo de dependencia.
			/*if (!Utils.isEmpty(numExp)) {
				mapParametrosBusqueda.put("codDependencia",usuarioBean.getCodDepend().trim().substring(0, 3));
			}else{
				mapParametrosBusqueda.put("codDependencia",Utils.toStr(request.getParameter("codDependencia")));
			}*/
			if (!Utils.isEmpty(numExp) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
				mapParametrosBusqueda.put("codDependencia",usuarioBean.getCodDepend().trim().substring(0, 3));
			} else if(Utils.isEmpty(numExp)){
				mapParametrosBusqueda.put("codDependencia",Utils.toStr(request.getParameter("codDependencia")));
			}
			//[jquispe 13/07/2016]
			
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("numExpedVirtual", numExp);
				} else {
					mapParametrosBusqueda.put("numExpedOrigen", numExp);
				}
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_01);
					}
					return modelAndView;
				} else {
					/* Inicio [jquispe 06/07/2016] Comentado porque no obtiene la lista de requerimientos.
					
					//Inicio [nchavez 26/05/2016]
					DdpBean beanContribuyente;
					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
						t6614ExpVirtualBean.setDesRazonSocial(!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
					}
					modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
					
					// Inicio [jquispe 15/06/2016] Registra el seguimiento de la accion realizada.
					if(flagBusquedaBoton){
						T6614ExpVirtualBean bean = listaExpedientesVirtuales.get(0);					
						registrarSeguimientoConsultaExpedientesVirtuales(bean.getNumExpedienteVirtual());
					}					
					// Fin [jquispe 15/06/2016]
										
					return modelAndView;
					
					Fin [jquispe 06/07/2016]
					
					*/
					
					//Inicio [jquispe 06/07/2016] Obtiene los requerimientos del expediente.
					mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
					listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

					if (Utils.isEmpty(listT6620RequerimBean)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
						return modelAndView;
					} else {
						// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
						if(flagBusquedaBoton){
							T6620RequerimBean bean = listT6620RequerimBean.get(0);							
							registrarSeguimientoConsultaExpedientesVirtuales(bean.getNumExpedienteVirtual());
						}
						// Fin [jquispe 27/05/2016]
						modelAndView.addObject("listaExpedientesVirtuales",listT6620RequerimBean);
						return modelAndView;
					}
					
					//Fin [jquispe 06/07/2016]
				}
			}
			// validamos numero de ruc
			if (!Utils.isEmpty(numeroRuc)) {
				mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			}
			
			// validamos las fechas
			if (!Utils.isEmpty(flagFecExp)) {
				if (Utils.equiv(flagFecExp,ValidaConstantes.BUSQUEDA_POR_FECHA_DOCUMENTO_ORIGEN)) {
					mapParametrosBusqueda.put("fecDocIni", fecDesde);
					mapParametrosBusqueda.put("fecDocFin", fecHasta);
				} else if (Utils.equiv(flagFecExp,ValidaConstantes.BUSQUEDA_POR_FECHA_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("fecGenIni", fecDesde);
					mapParametrosBusqueda.put("fecGenFin", fecHasta);
				}
			}
			// validamos el codigoProceso 
			if (!Utils.isEmpty(codProceso)) {
				mapParametrosBusqueda.put("codProceso", codProceso);
			}
			// validamos el codigoTipoExpediente
			if (!Utils.isEmpty(codTipExpediente)) {
				mapParametrosBusqueda.put("codTipExpediente", codTipExpediente);
			}
			
			/*
			 
			Inicio [jquispe 06/07/2016] Comentado porque no obtiene la lista de requerimientos.
			 
			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				//Inicio [nchavez 26/05/2016]
				DdpBean beanContribuyente;
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
					t6614ExpVirtualBean.setDesRazonSocial(!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
				}
				//Inicio [nchavez 26/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
				
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				if(flagBusquedaBoton){
				  registrarSeguimientoConsultaExpedientesVirtuales(null);
				}
				// Fin [jquispe 27/05/2016]
			}
			// Fin [jquispe 06/07/2016]
			*/
			
			// Inicio [jquispe 06/07/2016] Obtiene los requerimientos del expediente.
			mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
			listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

			if (Utils.isEmpty(listT6620RequerimBean)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
				if(flagBusquedaBoton){
					registrarSeguimientoConsultaExpedientesVirtuales(null);
				}
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales",listT6620RequerimBean);
			}
			// Fin [jquispe 06/07/2016]
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistraRequerimientoManualController.cargarListadoExpedientesVirtuales");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr", msb);
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.cargarListadoExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	
	// Inicio [jquispe 15/06/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaExpedientesVirtuales(String numeroExpedienteVirtual) throws Exception{
		
		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - RegistraRequerimientoManualController.registrarSeguimientoConsultaExpedientesVirtuales");
		}
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_REQUERIMIENTO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_CONSULTAR);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_CONSULTAR)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);					
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		
		seguiService.registrarSeguimiento(beanSegui);
		
		if (log.isDebugEnabled()) {
			log.debug( "Final - RegistraRequerimientoManualController.registrarSeguimientoConsultaExpedientesVirtuales");
		}
		
	}
	// Fin [jquispe 15/06/2016]
	

	@RequestMapping(value="/listarDocumentoExpediente",method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listarDocumentoExpediente(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelAndView;
		// Inicio [jquispe 16/06/2016] Numero del expediente virtual.
		String parametroNumeroExpedienteVirtual = null;
		// Fin [jquispe 16/06/2016]
		
	try {
			
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.listarDocumentoExpediente");

		String numExpedVirtual=String.valueOf(request.getParameter("numExpedVirtual"));
		String numRequerimiento=String.valueOf(request.getParameter("numRequerimiento"));
		//Inicio nchavez [02/06/2016]
		String numRequerimientoOrigen=String.valueOf(request.getParameter("numRequerimientoOrigen"));
		//Fin nchavez [02/06/2016]
		modelAndView=new ModelAndView("ConsultaDocumentosRequerimientoManual");
		List<T6613DocExpVirtBean> lisT6613DocExpVirtBean=null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		HashMap<String, Object> mapParametrosBusqueda;
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";
		String numExp = Utils.toStr(numExpedVirtual);
		//Inicio [gangles 15/08/2016]
		T6614ExpVirtualBean datosExpediente=null;
		String fechaVencimientoReq="";
		Date fecvenReq=null;
		//Fin [gangles 15/08/2016]
		mapParametrosBusqueda = new HashMap<String, Object>();
		mapParametrosBusqueda.put("numExpedVirtual", numExp);
		mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
		listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
		for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
			 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
			 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
			 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());			 
			 // Inicio [jquispe 16/06/2016] Numero del expediente virtual.
			 parametroNumeroExpedienteVirtual = t6614ExpVirtualBean.getNumExpedienteVirtual();
			 // Fin [jquispe 16/06/2016]			 
			 modelAndView.addObject("fechaDocOrigen",fechaDocOrigen);
			 modelAndView.addObject("fechaRegExpediente",fechaRegExpediente);
			 modelAndView.addObject("razonSocial",beanContribuyente.getDesRazonSocial().trim());
			 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
			//Inicio nchavez [02/06/2016]
			 modelAndView.addObject("numRequerimientoOrigen", numRequerimientoOrigen);
			//Fin nchavez [02/06/2016]
			 datosExpediente=t6614ExpVirtualBean;
			 break;
		}
		//Inicio [gangles 15/08/2016]--Se obtiene la fecha de vencimiento del Requerimiento
		HashMap<String, Object> parametros=new HashMap<String, Object>();		
		parametros.put("numExpedienteVirtual", datosExpediente.getNumExpedienteVirtual());
		parametros.put("numRequerim",numRequerimiento);		
		List<T6620RequerimBean> listadoRequerimientos = requerimientoService.listarRequerimientos(parametros);
		if (listadoRequerimientos!=null && !listadoRequerimientos.isEmpty()) {
			
			//TODO -  DESCRIPCION TIPO DE CIERRE DEL REQUERIMIENTO
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_CIERRE_REQUERIMIENTO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
			Map<String, Object> mapaTipoCierreRequerimiento = catalogoService.obtenerCatalogo(mapa);
			
			//TODO -  DESCRIPCION ESTADOS DEL REQUERIMIENTO			
			 mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_REQUERIMIENTO);
			 mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);				 
			 Map<String, Object> mapaTipoEstadoRequerimiento = catalogoService.obtenerCatalogo(mapa);
			
			
			 for (T6620RequerimBean t6620RequerimBean : listadoRequerimientos) {				 
				 fecvenReq = t6620RequerimBean.getFechaVencimiento();				 
				 //INICIO[ATORRESCH 20170314]				 
				 t6620RequerimBean.setDesOrigRequerimiento(t6620RequerimBean.getCodOrigRequerimiento()
							.equals(ValidaConstantes.IND_ORIGEN_REQ_MANUAL)?ValidaConstantes.DES_ORIGEN_REQ_DIGITALIZADO:ValidaConstantes.DES_ORIGEN_REQ_AUTOMATICO);				
				 t6620RequerimBean.setFecRequerimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaRequerimiento()));
				 t6620RequerimBean.setFecVencimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaVencimiento()));				 
				 
				 if(!Utils.isEmpty(t6620RequerimBean.getFechaRegistro())){
					 t6620RequerimBean.setFecRegistro(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaRegistro()));
				 }else{
					 t6620RequerimBean.setFecRegistro(""); 
				 }	
				 
				 if(!Utils.isEmpty(t6620RequerimBean.getCodEstadoRequerim()) && !t6620RequerimBean.getCodEstadoRequerim().trim().equals(ValidaConstantes.SEPARADOR_GUION)){				
					 t6620RequerimBean.setDesEstado(mapaTipoEstadoRequerimiento.get(t6620RequerimBean.getCodEstadoRequerim().trim()).toString());				 
				 }else{
					 t6620RequerimBean.setDesEstado("");
				 }
				 
				 if(!Utils.isEmpty(t6620RequerimBean.getCodTipCierr()) && !t6620RequerimBean.getCodTipCierr().trim().equals(ValidaConstantes.SEPARADOR_GUION)){				
					 t6620RequerimBean.setDesTipCierr(Utils.toStr(mapaTipoCierreRequerimiento.get(t6620RequerimBean.getCodTipCierr().trim())));
				 }else{				
					 t6620RequerimBean.setDesTipCierr("");
				 }
				 //FIN   [ATORRESCH 20170314]				 
				 modelAndView.addObject("t6620RequerimBean",t6620RequerimBean);				 
				 break;
			 }			 
		}		
		
		//Fin [gangles 15/08/2016]
		parametros = new HashMap<String, Object>();
		parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_REQUERIM);
		parametros.put("numExpeDv",numExpedVirtual);
		parametros.put("numRequerimiento",numRequerimiento);
		
		lisT6613DocExpVirtBean = requerimientoService.listarDocumentosPorRequerim(parametros);
		
		if (lisT6613DocExpVirtBean!=null && !lisT6613DocExpVirtBean.isEmpty()) {
			 for (T6613DocExpVirtBean document : lisT6613DocExpVirtBean) {
				String strFecha=new SimpleDateFormat("yyyy-MM-dd").format(document.getFecDoc());
				boolean isFechaVacia=Utils.equiv(strFecha, ValidaConstantes.FECHA_VACIA);
				document.setFechaDocumento(isFechaVacia?ValidaConstantes.SEPARADOR_GUION:Utils.dateUtilToStringDDMMYYYY(document.getFecDoc()));
				
				//Inicio [gangles 15/08/2016]
				SimpleDateFormat sdf = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO); 
				String fechaCarga=sdf.format(document.getFechaCarga());
				
				Date fecVenRequerimiento = fecvenReq;				
				Date fechaCargaDoc = sdf.parse(fechaCarga);
				
				if(Utils.equiv(fechaCarga, ValidaConstantes.FECHA_VACIA)){
					document.setDesEstadoEnvioDoc(AvisoConstantes.NO_ENVIADO);
				}else if(fechaCargaDoc.compareTo(fecVenRequerimiento)<=0){					
						document.setDesEstadoEnvioDoc(AvisoConstantes.ENVIO_EN_PLAZO);
					}else{
						document.setDesEstadoEnvioDoc(AvisoConstantes.ENVIO_FUERA_DE_PLAZO);
					}
				//Fin [gangles 15/08/2016]				
			}
		}
		
		modelAndView.addObject("listadocumentos", new JsonSerializer().serialize(lisT6613DocExpVirtBean).toString());
		
		// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.		
		registrarSeguimientoListaDocumentoExpediente(parametroNumeroExpedienteVirtual);		
		// Fin [jquispe 16/06/2016]
	
	} catch (Exception ex) {
		if (log.isDebugEnabled()) {
			log.debug((Object) "Error - RegistraRequerimientoManualController.listarDocumentoExpediente");
		}
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelAndView = new ModelAndView("PaginaError");
		msb.setError(true);
		msb.setMensajeerror("Error al ingresar a la opción.");
		modelAndView.addObject("beanErr", (Object) msb);
	} finally {

		if (log.isDebugEnabled()) {
			log.debug( "Final - RegistraRequerimientoManualController.listarDocumentoExpediente");
		}
		NDC.pop();
		NDC.remove();
	}

	return modelAndView;
}
	
	// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.	
	private void registrarSeguimientoListaDocumentoExpediente(String numeroExpedienteVirtual) throws Exception{	
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.registrarSeguimientoListaDocumentoExpediente");
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_REQUERIMIENTO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_REQUERIMIENTO_DOCUMENTOS);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_REQUERIMIENTO_DOCUMENTOS)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);					
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		
		seguiService.registrarSeguimiento(beanSegui);
		
		if (log.isDebugEnabled()) {
			log.debug( "Final - RegistraRequerimientoManualController.registrarSeguimientoListaDocumentoExpediente");
		}
		
	}
	// Fin [jquispe 16/06/2016]

	
	@RequestMapping(value = "/mostrarView",  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.mostrarView");
		
		ModelAndView modelAndView = null;
		
		String pageView = request.getParameter("pageView");
		modelAndView = new ModelAndView("ConsultaExpedientesVirtualesManualesIntranet");
		try {
			
			if(Utils.equiv(pageView, NavegaConstantes.MANT_MODULO_02_01_001)){
				
				UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
				
				List<T01ParamBean> listadoProcesos = null;
				
				listadoProcesos = configuracionExpedienteService.listarProcesos();
				
				// Inicio [nchavez 24/05/2015]
				//Inicio [oachahuic][PAS20165E210400270]
				List<T01ParamBean> listadoDependencias = new ArrayList<T01ParamBean>();
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu == null || mapDepAdu.isEmpty()) {
					List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
					if(!Utils.isEmpty(listadoDependenciasIni)){
						for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
							if(Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
									&& (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))){
								t01ParamBean.setDesParametro(t01ParamBean.getCodParametro()+" - " + t01ParamBean.getDesParametro());
								listadoDependencias.add(t01ParamBean);
							}
		                }
					}
				} else {
					usuarioBean.setCodDepend(mapDepAdu.get("ADUANA").toString().trim());
					T01ParamBean t01ParamBean = new T01ParamBean();
					t01ParamBean.setCodParametro(mapDepAdu.get("ADUANA").toString().trim());
					t01ParamBean.setDesParametro(mapDepAdu.get("ADUANA").toString().trim() + " - " + mapDepAdu.get("DEPEN2").toString().trim());
					listadoDependencias.add(t01ParamBean);
				}
				//Fin [oachahuic][PAS20165E210400270]
				// Fin [nchavez 24/05/2015]
				
				List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
				
				List<T01ParamBean> listadoTipoFecha= paramService.listarTipoFecha();
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_001);
				
				HashMap<String, String> excepciones = new HashMap<String, String>();
				excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_01_001);
				excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_01_002);
				excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_01_003);
				excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_02_01_004);
				excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_02_01_005);
				excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_02_01_006);
				excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_02_01_008_01);
				excepciones.put("excepcion08",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
				
				modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize( listadoProcesos));
				modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
				modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
				
				modelAndView.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias) );
				modelAndView.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente) );
				modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha) );
				//Inicio - [oachahuic][PAS20165E210400270]
				if (mapDepAdu == null || mapDepAdu.isEmpty()) {
					modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim() );	
				} else {
					modelAndView.addObject("dependencia", mapDepAdu.get("ADUANA").toString().trim() );
				}
				//Fin - [oachahuic][PAS20165E210400270]
				modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistraRequerimientoManualController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoManualController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - RegistraRequerimientoManualController.mostrarView");
		return modelAndView;
	}

	

	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	       
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoManualController.exportarExcelExpedientes");
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_01;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Calendar fechaVacia = null;
   
		try {
			
			listadoExportarCadena = request.getParameter("listadoExpedientesCadena");

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

			// Fecha actual
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			response.setContentType("application/vnd.ms-excel");
			
			//Inicio nchavez [07/07/2016]
			Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numRequerimOrigen",new ColumOption("N° Requerimiento",ALIGN.LEFT));
			columnsProperties.put("numExpedienteVirtual",new ColumOption("N° Expediente Virtual",ALIGN.LEFT));
			columnsProperties.put("numExpedienteOrigen",new ColumOption("N° Expediente Origen",ALIGN.LEFT));
			columnsProperties.put("numRuc",new ColumOption("RUC",ALIGN.LEFT));
			columnsProperties.put("desRazonSocial",new ColumOption("Razón Social",ALIGN.LEFT));
			columnsProperties.put("desOrigRequerimiento", new ColumOption("Origen", ALIGN.RIGHT));
			columnsProperties.put("desProceso",new ColumOption("Proceso",ALIGN.LEFT));
			columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente",ALIGN.LEFT));
			columnsProperties.put("fecRegistroExpediente",new ColumOption("Fecha de Registro del Expediente",ALIGN.RIGHT));
			columnsProperties.put("fechaDocumentoOrigen",new ColumOption("Fecha del Documento Origen",ALIGN.RIGHT));
			columnsProperties.put("fecRequerimiento", new ColumOption("Fecha del Requerimiento",ALIGN.RIGHT));
			columnsProperties.put("fecVencimiento",new ColumOption("Fecha de Vencimiento",ALIGN.LEFT));
			HSSFWorkbook libro=ExcelUtil.buildWorkbookXLS(titulo, "Hoja 1", columnsProperties, listadoExportar);
			
			//Fin nchavez [07/07/2016]
		 Calendar calendar = Calendar.getInstance();
		
		 int anio = (calendar.get(Calendar.YEAR));
		 int dia = (calendar.get(Calendar.DATE));
		 int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
		 int minutos = (calendar.get(Calendar.MINUTE));
		 String dd = (dia<10) ? "0"+dia : dia+"";
		 int mes = calendar.get(Calendar.MONTH)+1;
		 String mm = (mes<10) ? "0"+mes : mes+"";
		 String part=anio+mm+dd+"_"+hora+minutos;
		   
		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_EXP_XLS + part + ".xls";;
         FileOutputStream elFichero = new FileOutputStream(new File("/data0/tempo/" + filename));

         libro.write(elFichero);
         elFichero.close(); 
         
         File downloadFile = new File("/data0/tempo/" + filename);
         FileInputStream inputStream = new FileInputStream(downloadFile);
         ServletContext context = getServletContext();
         String mimeType = context.getMimeType("/data0/tempo/" + filename); 
         
         if (mimeType == null) {
            mimeType = "application/octet-stream";
         }
          response.setContentType(mimeType);
          response.setContentLength((int) downloadFile.length());
              
          String headerKey = "Content-Disposition";
          String headerValue = String.format("attachment; filename=\"%s\"",
                 downloadFile.getName());
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
    	   	 if (log.isDebugEnabled())log.info((Object) "Error - RegistraRequerimientoManualController.exportarExcelExpedientes");
    	   	 log.error( ex, ex);
    	   	 
             mensajeBean.setError(true);
             mensajeBean
                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
                                        + ex.getMessage());
             view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		if (log.isDebugEnabled())log.info((Object) "Fin - RegistraRequerimientoManualController.exportarExcelExpedientes");
		
       return view;

	}
	
	
	@RequestMapping(value = "/descargarArchivo", method = {RequestMethod.POST, RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivo (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistraRequerimientoManualController.descargarArchivo");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os=null;
		String nombreArchivo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RegistraRequerimientoManualController.descargarArchivo");
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			numIdEcm =request.getParameter("codIdentificadorECM").toString().trim(); 
			mapParametrosBusqueda.put("codIdecm", numIdEcm);
			mapParametrosBusqueda.put("inline", "true");
			nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));
			
			responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			MediaType contentType = responseDoc.getHeaders().getContentType();
			/*if(!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || 
					Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) ||
							Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))){*/
				String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(nombreArchivo));
				
		        response.setHeader(headerKey, headerValue);
			//}
			os = response.getOutputStream();
			os.write(responseDoc.getBody());
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistraRequerimientoManualController.descargarArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistraRequerimientoManualController.descargarArchivo");
			try {
				if (os!=null) {
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			NDC.pop();
			NDC.remove();
			
		}
	}
	
	private Map<String, Object> obtenerMapa(HttpServletRequest request) throws ServletException, IOException {
		Map<String, Object> parameterMap;
		// Inicio [nchavez 26/05/2015]
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),ValidaConstantes.CHARSET_UTF8));
		// Fin [nchavez 26/05/2015]
		String dataCadena = "";
		if (br != null) {
		dataCadena = br.readLine();
		}

		Map<String, Object> mapaData = (Map<String, Object>) new JsonSerializer().deserialize(dataCadena, Map.class);

		mapaData = mapaData == null ? new HashMap<String, Object>() : mapaData;
		parameterMap = request.getParameterMap();
		Iterator<String> it = parameterMap.keySet().iterator();
		while (it.hasNext()) {
		String key = it.next();
		if (!mapaData.containsKey(key)) {
		mapaData.put(key, ((String[]) parameterMap.get(key))[0]);
		}
		}
		return mapaData;
		}
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelDocumentos(HttpServletRequest request, HttpServletResponse response){
	    
	       String titulo = ExportaConstantes.TITULO_EXPORTA_02_05;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoDocumentosCadena = null;
	     	      
	       String numExpOrigen="";
	       String numExpVirtual="";
	       String numRuc="";
	       String razonSocial="";
	       String tipoProceso="";
	       String tipoExpediente="" ;
	       String fechaDocOrigen="";
	       String fechaRegExp="";
	       String responsable="";
	       String numRequerimiento="";

	       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	       Date fechaActual = new Date();
	       String fecImpresion = sdf.format(fechaActual);
	       Map<String, Object> beanSeguiWS = null;
		   Calendar fechaActualCalendar = null;
		   Calendar fechaVacia = null;
	       
	       try {
	    		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
	    		String numeroRuc = usuarioBean.getNumRUC();
	    		listadoDocumentosCadena = Utils.toStr(request.getParameter("listadoDocumentosCadena"));
		    	numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
		    	numExpVirtual=Utils.toStr(request.getParameter("hiddenNumExpVirtualDoc"));
		    	numRuc=Utils.toStr(request.getParameter("hiddenNumRuc"));
		    	razonSocial=Utils.toStr(request.getParameter("hiddenRazonSocial"));
			    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
			    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
			    fechaDocOrigen=Utils.toStr(request.getParameter("hiddenFechaDocOrigen"));
			    fechaRegExp=Utils.toStr(request.getParameter("hiddenFechaRegExp"));
			    responsable=Utils.toStr(request.getParameter("hiddenResponsable"));
			    numRequerimiento=Utils.toStr(request.getParameter("hiddenNumRequerimiento"));				
			
			    Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);	
				
			@SuppressWarnings("unchecked")
			List<T6613DocExpVirtBean> listadoExportar = (List<T6613DocExpVirtBean>) new JsonSerializer().deserialize(listadoDocumentosCadena, ArrayList.class);
	    	   
			// Fecha actual
			fechaActualCalendar = Calendar.getInstance();
						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
	    	response.setContentType("application/vnd.ms-excel");
	        HSSFWorkbook libro = new HSSFWorkbook();
	        HSSFSheet hoja = libro.createSheet("Hoja 1");
      
	        hoja.setColumnWidth(0, 2500);
	        hoja.setColumnWidth(1, 2500);
	        hoja.setColumnWidth(2, 3500);
	        hoja.setColumnWidth(3, 4500);
	        hoja.setColumnWidth(4, 4500);
	        hoja.setColumnWidth(5, 4500);
	        hoja.setColumnWidth(6, 4500);
	        
	        HSSFRow fila = hoja.createRow(1);
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 5));
			
			fila = hoja.createRow(2);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(numRuc+"-"+razonSocial);
			rango2 = new CellRangeAddress(2, 2, 2,3);
			hoja.addMergedRegion(rango2);
		
			HSSFCell subtituloCelda3 = fila.createCell(4);
			subtituloCelda3.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(5);
			contenido3.setCellValue(fecImpresion);

			fila = hoja.createRow(3);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(tipoProceso);

			HSSFCell subtituloCelda6 = fila.createCell(4);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido6 = fila.createCell(5);
			contenido6.setCellValue(tipoExpediente);

			fila = hoja.createRow(4);
			HSSFCell subtituloCelda7 = fila.createCell(1);
			subtituloCelda7.setCellValue("N° Expediente Origen:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(numExpOrigen);

			HSSFCell subtituloCelda8 = fila.createCell(4);
			subtituloCelda8.setCellValue("N° Expediente Virtual:");
			HSSFCell contenido8 = fila.createCell(5);
			contenido8.setCellValue(numExpVirtual);
			
			fila = hoja.createRow(5);
			HSSFCell subtituloCelda9 = fila.createCell(1);
			subtituloCelda9.setCellValue("Fecha del Documento Origen:");
			HSSFCell contenido9 = fila.createCell(2);
			contenido9.setCellValue(fechaDocOrigen);

			HSSFCell subtituloCelda10 = fila.createCell(4);
			subtituloCelda10.setCellValue("Fecha de Registro del Expediente:");
			HSSFCell contenido10 = fila.createCell(5);
			contenido10.setCellValue(fechaRegExp);	
			
			fila = hoja.createRow(6);
			HSSFCell subtituloCelda11 = fila.createCell(1);
			subtituloCelda11.setCellValue("Responsable del Expediente:");
			HSSFCell contenido11 = fila.createCell(2);
			contenido11.setCellValue(responsable);

			HSSFCell subtituloCelda12 = fila.createCell(4);
			subtituloCelda12.setCellValue("Número de Requerimiento:");
			HSSFCell contenido12 = fila.createCell(5);
			contenido12.setCellValue(numRequerimiento);	
			
			fila = hoja.createRow(8);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			HSSFCell celda4 = fila.createCell(4);
			HSSFCell celda5 = fila.createCell(5);
			
			celda.setCellValue("N°");
			celda1.setCellValue("N° de Documento");
			celda2.setCellValue("Fecha de Subida");
			celda3.setCellValue("Motivo");
			celda4.setCellValue("Estado");
			celda5.setCellValue("Estado del Envio del Doc.");			
	        
	        HSSFFont fuente = libro.createFont();
	        fuente.setFontHeightInPoints((short) 11);
	        fuente.setFontName(fuente.FONT_ARIAL);
	        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        Sheet ssheet = libro.getSheetAt(0);
	        ssheet.autoSizeColumn(0);
	        ssheet.autoSizeColumn(1);
	        ssheet.autoSizeColumn(2);
	        ssheet.autoSizeColumn(3);
	        ssheet.autoSizeColumn(4);
	        ssheet.autoSizeColumn(5);
	        
	        HSSFCellStyle estiloCelda = libro.createCellStyle();
		       
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	       estiloCelda.setFont(fuente);
	       estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	       estiloCelda.setBottomBorderColor((short) 8);
	       estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	       estiloCelda.setLeftBorderColor((short) 8);
	       estiloCelda.setBorderRight(HSSFCellStyle.BORDER_THIN);
	       estiloCelda.setRightBorderColor((short) 8);
	       estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THIN);
	       estiloCelda.setTopBorderColor((short) 8);
	       
	       estiloCelda.setFillForegroundColor((short) 22);
	       estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	                
	        celda.setCellStyle(estiloCelda);
	        celda1.setCellStyle(estiloCelda);
	        celda2.setCellStyle(estiloCelda);
	        celda3.setCellStyle(estiloCelda);
	        celda4.setCellStyle(estiloCelda);
	        celda5.setCellStyle(estiloCelda);
	        
	        HSSFCellStyle estiloTitulo = libro.createCellStyle();
	        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        estiloTitulo.setFont(fuente);
	        
	        tituloCelda.setCellStyle(estiloTitulo);

	        HSSFRichTextString texto;
	        HSSFFont fuenteContenido = libro.createFont();
	        HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
	        estiloCeldaContenido.setFont(fuenteContenido);
	        
	        HSSFCellStyle estiloRecorrido = libro.createCellStyle();
	        estiloRecorrido.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        
	        estiloRecorrido.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        estiloRecorrido.setBottomBorderColor((short) 8);
	        estiloRecorrido.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        estiloRecorrido.setLeftBorderColor((short) 8);
	        estiloRecorrido.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        estiloRecorrido.setRightBorderColor((short) 8);
	        estiloRecorrido.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        estiloRecorrido.setTopBorderColor((short) 8);
	        	        
	        int i=0;
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listadoExportar) {
				fila = hoja.createRow(i + 9);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(Utils.toStr(t6613DocExpVirtBean.getCorrelativo()));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.convertirUnicode(Utils.toStr(t6613DocExpVirtBean.getDesTipdoc())));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(t6613DocExpVirtBean.getFechaDocumento());
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(Utils.convertirUnicode(t6613DocExpVirtBean.getDesMotsoldoc()));
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(t6613DocExpVirtBean.getDesEstadoDocumento());
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(t6613DocExpVirtBean.getDesEstadoEnvioDoc());
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
				i++;
			}
	         Calendar calendar = Calendar.getInstance();

	         int anio = (calendar.get(Calendar.YEAR));
	         int dia = (calendar.get(Calendar.DATE));
	         int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
	         int minutos = (calendar.get(Calendar.MINUTE));
	         String dd = (dia<10) ? "0"+dia : dia+"";
           
	         int mes = calendar.get(Calendar.MONTH)+1;
           
	         String mm = (mes<10) ? "0"+mes : mes+"";
           
	         String part=anio+mm+dd+"_"+hora+minutos;
           
	         String filename="rpt_documentos_requerimiento_expVirtual_" + part + ".xls";;
	         FileOutputStream elFichero = new FileOutputStream(new File("/data0/tempo/" + filename));

	         libro.write(elFichero);
	         elFichero.close(); 
	         
	         File downloadFile = new File("/data0/tempo/" + filename);
             FileInputStream inputStream = new FileInputStream(downloadFile);
             ServletContext context = getServletContext();
             String mimeType = context.getMimeType("/data0/tempo/" + filename); 
             if (mimeType == null) {
                mimeType = "application/octet-stream";
             }
	          response.setContentType(mimeType);
	          response.setContentLength((int) downloadFile.length());
	              
	          String headerKey = "Content-Disposition";
	          String headerValue = String.format("attachment; filename=\"%s\"",
                     downloadFile.getName());
             response.setHeader(headerKey, headerValue);
             int BUFFER_SIZE = 8192;
             OutputStream outStream = response.getOutputStream();
      
             byte[] buffer = new byte[BUFFER_SIZE];
             int bytesRead = -1;
      
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outStream.write(buffer, 0, bytesRead);
             }
 			// Seguimiento WS
 			beanSeguiWS = new HashMap<String, Object>();
 			beanSeguiWS.put("num_expedv", numExpVirtual);
 			beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
 			beanSeguiWS.put("fec_seguim", fechaActualCalendar.getTime());
 			beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
 			beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("num_ruc", numeroRuc);
 			beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_REQUERIMIENTO);
 			beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_REQUERIMIENTO_DOCUMENTOS);
 			beanSeguiWS.put("des_datcons", Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS)));
 			beanSeguiWS.put("fec_cons", fechaActualCalendar.getTime());
 			beanSeguiWS.put("cod_respacc",ValidaConstantes.RESPUESTA_ACCION_OK );
 			beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
 			beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
 			beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
 			beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
 			beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION); 			
 			seguiService.registrarSeguimiento(beanSeguiWS);      
             inputStream.close();
             outStream.close(); 
             
	       } catch (Exception ex) {
	             ex.printStackTrace();
	             log.error("**** ERROR ****", ex);
	             mensajeBean.setError(true);
	             mensajeBean
	                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
	                                        + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       }
	       return view;	       

	}
	
	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	public void setParamService(ParametroService paramService) {
		this.paramService = paramService;
	}
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	
	public void setRequerimientoService(RequerimientoService requerimientoService) {
		this.requerimientoService = requerimientoService;
	}

	public void setValidarService(ValidarParametrosService validarService) {
		this.validarService = validarService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	// Inicio [jquispe 27/05/2016]
	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
	// Fin [jquispe 27/05/2016]	

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	
}
//Fin [nchavez 24/05/2015]