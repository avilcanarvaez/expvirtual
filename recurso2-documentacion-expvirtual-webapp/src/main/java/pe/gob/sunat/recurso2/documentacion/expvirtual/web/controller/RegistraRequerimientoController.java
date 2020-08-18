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
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.Sgs33Service;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ALIGN;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

@Controller
public class RegistraRequerimientoController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(RegistraRequerimientoController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	// Inicio [nchavez 26/05/2015]
	private ValidarParametrosService validarService;
	// Fin [nchavez 26/05/2015]
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	// Inicio [nchavez 26/05/2015]
	private EcmService ecmService;
	private Sgs33Service  sgs33Service;
	// Fin [nchavez 26/05/2015]
	// Inicio [jquispe 27/05/2016]
	private SeguimientoService seguiService;
	private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	@RequestMapping(value = "/consultarExpedientesVirtualesView", method = RequestMethod.GET)
	public ModelAndView consultarExpedientesVirtualesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - RegistraRequerimientoController.consultarExpedientesVirtualesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			 
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");

			modelAndView = new ModelAndView("ConsultaExpedientesVirtualesIntranet");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
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
			excepciones.put("excepcion15",AvisoConstantes.EXCEP_MODULO_02_01_015);
			
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
				log.debug("Error - RegistraRequerimientoController.consultarExpedientesVirtualesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.consultarExpedientesVirtualesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.POST)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.POST)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/consultarDetalleExpedienteVirtualView", method = RequestMethod.POST)
	public ModelAndView consultarDetalleExpedienteVirtualView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.consultarDetalleExpedienteVirtualView");
		
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
			modelAndView = new ModelAndView("ConsultaRequerimientosAbiertos");
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
				 // nchavez 13/06/2016
				 modelAndView.addObject("razonSocial",!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial().trim():ValidaConstantes.CADENA_VACIA);
				 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 break;
			}
			mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
			listaRequerimientos = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);
			
			modelAndView.addObject("listaRequerimientos",new JsonSerializer().serialize(listaRequerimientos));
			
			// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.
			if(flagPaginaPrincipal){
			  registrarSeguimientoDetalleExpedienteVirtual(parametroNumeroExpedienteVirtual);
			}
			// Fin [jquispe 16/06/2016]
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoController.consultarDetalleExpedienteVirtualView");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.consultarDetalleExpedienteVirtualView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 16/06/2016] Registra el seguimiento de la accion realizada.	
	private void registrarSeguimientoDetalleExpedienteVirtual(String numeroExpedienteVirtual) throws Exception{
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.registrarSeguimientoDetalleExpedienteVirtual");
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_REQUERIMIENTO);
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
			log.debug( "Final - RegistraRequerimientoController.registrarSeguimientoDetalleExpedienteVirtual");
		}
		
	}
	// Fin [jquispe 16/06/2016]
	
	@RequestMapping(value = { "/validarContribuyente" }, method = { RequestMethod.POST })
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - RegistraRequerimientoController.validarContribuyente");
		
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
				log.debug((Object) "Error - RegistraRequerimientoController.validarContribuyente");
			}
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraRequerimientoController.validarContribuyente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	
	@RequestMapping(value = { "/cargarListaTiposExpediente" }, method = { RequestMethod.GET })
	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - RegistraRequerimientoController.cargarListaTiposExpediente");
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
				log.debug((Object) "Error - RegistraRequerimientoController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraRequerimientoController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	
	@RequestMapping(value = "/cargarListadoExpedientesVirtuales", method = RequestMethod.POST)
	public ModelAndView cargarListadoExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - RegistraRequerimientoController.cargarListadoExpedientesVirtuales");
		}

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
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
			//mapParametrosBusqueda.put("codEstado", "01");[ATORRESCH 20170047]
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim() );
			
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
					//INICIO [ATORRESCH 20170047]
					if(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO.equals(listaExpedientesVirtuales.get(0).getCodEstado())){
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_016);
					}else{//FIN [ATORRESCH 20170047]											
						//Inicio [nchavez 26/05/2016]
						DdpBean beanContribuyente;
						for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
							beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
							t6614ExpVirtualBean.setDesRazonSocial(!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
						}
						modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
						// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
						if(flagBusquedaBoton){
							T6614ExpVirtualBean bean = listaExpedientesVirtuales.get(0);					
							registrarSeguimientoConsultaExpedientesVirtuales(bean.getNumExpedienteVirtual());
						}					
						// Fin [jquispe 27/05/2016]
					}					
					return modelAndView;
				}
			}

			// validamos numero de ruc
			if (!Utils.isEmpty(numeroRuc)) {
				mapParametrosBusqueda.put("numeroRuc", numeroRuc);
				mapParametrosBusqueda.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO); //[ATORRESCH 20170047]
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
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistraRequerimientoController.cargarListadoExpedientesVirtuales");
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
				log.debug( "Final - RegistraRequerimientoController.cargarListadoExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaExpedientesVirtuales(String numeroExpedienteVirtual) throws Exception{
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_REQUERIMIENTO);
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
		
	}
	// Fin [jquispe 27/05/2016]

	@RequestMapping(value="/listarDocumentoExpediente",method=RequestMethod.GET)
	public ModelAndView listarDocumentoExpediente(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelAndView;
		// Inicio [jquispe 16/06/2016] Numero del expediente virtual.
		String parametroNumeroExpedienteVirtual = null;
		// Fin [jquispe 16/06/2016]
		// Inicio [gangles 19/08/2016]
		T6614ExpVirtualBean datosExpediente=null;
		Date fecvenReq=null;
		// Inicio [gangles 19/08/2016]
	try {
			
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.listarDocumentoExpediente");

		String numExpedVirtual=String.valueOf(request.getParameter("numExpedVirtual"));
		String numRequerimiento=String.valueOf(request.getParameter("numRequerimiento"));
		//Inicio nchavez [02/06/2016]
		String numRequerimientoOrigen=String.valueOf(request.getParameter("numRequerimientoOrigen"));
		//Fin nchavez [02/06/2016]
		modelAndView=new ModelAndView("ConsultaDocumentosRequerimiento");
		List<T6613DocExpVirtBean> lisT6613DocExpVirtBean=null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		HashMap<String, Object> mapParametrosBusqueda;
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";
		String numExp = Utils.toStr(numExpedVirtual);
		
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
		
		//Inicio [gangles 19/08/2016]--Se obtiene la fecha de vencimiento del Requerimiento
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
						 t6620RequerimBean.setDesOrigRequerimiento(t6620RequerimBean.getCodOrigRequerimiento()
									.equals(ValidaConstantes.IND_ORIGEN_REQ_MANUAL)?ValidaConstantes.DES_ORIGEN_REQ_DIGITALIZADO:ValidaConstantes.DES_ORIGEN_REQ_AUTOMATICO);						
						 t6620RequerimBean.setFecRequerimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaRequerimiento()));
						 t6620RequerimBean.setFecVencimiento(Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaVencimiento()));
						 //INICIO [ATORRESCH 20170412]
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
						 //FIN [ATORRESCH 20170412]
						 
						 modelAndView.addObject("t6620RequerimBean",t6620RequerimBean);
						 break;
					 }
					 
				}
		//Fin [gangles 19/08/2016]
				
		parametros=new HashMap<String, Object>();	
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
			log.debug((Object) "Error - RegistraRequerimientoController.listarDocumentoExpediente");
		}
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelAndView = new ModelAndView("PaginaError");
		msb.setError(true);
		msb.setMensajeerror("Error al ingresar a la opción.");
		modelAndView.addObject("beanErr", (Object) msb);
	} finally {

		if (log.isDebugEnabled()) {
			log.debug( "Final - RegistraRequerimientoController.listarDocumentoExpediente");
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_REQUERIMIENTO);
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
	
	@RequestMapping(value = { "/registrarRequerimientoView" }, method = { RequestMethod.GET })
	public ModelAndView registrarRequerimientoView(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView;
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.registrarRequerimientoView");
		List<T6623TipDocExpBean> listaTipDocExp = new ArrayList<T6623TipDocExpBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";
		String codigoTipoExpediente="";
		int numOrden = 1;
		try {
			String numExpedVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_01_003);
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				 codigoTipoExpediente = t6614ExpVirtualBean.getCodTipoExpediente();
				 modelAndView.addObject("fechaDocOrigen",fechaDocOrigen);
				 modelAndView.addObject("fechaRegExpediente",fechaRegExpediente);
				 modelAndView.addObject("razonSocial",beanContribuyente.getDesRazonSocial().trim());
				 modelAndView.addObject("codDependencia",beanContribuyente.getCodDependencia()); // [jjurado 03/08/2016]
				 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 modelAndView.addObject("esProcesoCobranza", Utils.equiv(t6614ExpVirtualBean.getCodProceso(), ValidaConstantes.COD_PROCESO_COBRANZA));
				 modelAndView.addObject("esProcesoAduana", Utils.equiv(t6614ExpVirtualBean.getCodProceso(), ValidaConstantes.COD_PROCESO_ADUANA));//[oachahuic][PAS20165E210400270]
				 break;
			}
			
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codTipoExpediente", codigoTipoExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
			mapParametrosBusqueda.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			listaTipDocExp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
				t6623TipDocExpBean.setNumOrden(numOrden);
				numOrden++;
			}
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_04_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion10",AvisoConstantes.EXCEP_MODULO_02_01_010);
			excepciones.put("excepcion11",AvisoConstantes.EXCEP_MODULO_02_03_002);
			Date fechaSistema = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(fechaSistema); 
			c.add(Calendar.DATE, 1);
			fechaSistema = c.getTime();
			
			modelAndView.addObject("fechaSistema", Utils.dateUtilToStringDDMMYYYY(fechaSistema));
			modelAndView.addObject("listaTipDocExp",new JsonSerializer().serialize(listaTipDocExp));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - RegistraRequerimientoController.registrarRequerimientoView");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - RegistraRequerimientoController.registrarRequerimientoView");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/mostrarView",  method =  RequestMethod.GET)
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.mostrarView");
		
		ModelAndView modelAndView = null;
		
		String pageView = request.getParameter("pageView");
		modelAndView = new ModelAndView("ConsultaExpedientesVirtualesIntranet");
		try {
			
			if(Utils.equiv(pageView, NavegaConstantes.MANT_MODULO_02_01_001)){
				
				UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
				
				List<T01ParamBean> listadoProcesos = null;
				
				listadoProcesos = configuracionExpedienteService.listarProcesos();
				// Inicio [nchavez 24/05/2015]
				List<T01ParamBean> listadoDependencias;
				
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				listadoDependencias = new ArrayList<T01ParamBean>();
				if(!Utils.isEmpty(listadoDependenciasIni)){
					for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
						if(Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
								&& (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))){
							t01ParamBean.setDesParametro(t01ParamBean.getCodParametro()+" - " + t01ParamBean.getDesParametro());
							listadoDependencias.add(t01ParamBean);
						}
	                }
				}
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
				modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim() );
				modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistraRequerimientoController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - RegistraRequerimientoController.mostrarView");
		return modelAndView;
	}

	@RequestMapping(value = { "/registrarRequerimiento" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView registrarRequerimiento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.registrarRequerimiento");
		ModelAndView modelAndView = null;
		List<T6613DocExpVirtBean> listT6613DocExpVirtBean = new ArrayList<T6613DocExpVirtBean>(); 
		T6613DocExpVirtBean t6613DocExpVirtBean;
		List<T6614ExpVirtualBean> listT6614ExpVirtualBean;
		T6614ExpVirtualBean t6614ExpVirtualBean = new T6614ExpVirtualBean();
		T6620RequerimBean t6620RequerimBean;
		String numReq = "";
		
		try {
			Map<String, Object> mapaData = obtenerMapa(request);
			
			//REEMPLAZADO
//			String numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
//			String numReqOrigen = Utils.toStr(request.getParameter("numReqOrigen"));
//			String fecVencimiento = Utils.toStr(request.getParameter("fecVencimiento"));
			
			String numExpedienteVirtual = Utils.toStr(mapaData.get("numExpedienteVirtual"));
			String numReqOrigen = Utils.toStr(mapaData.get("numReqOrigen"));
			String fecVencimiento = Utils.toStr(mapaData.get("fecVencimiento"));
			String ruc=Utils.toStr(mapaData.get("ruc"));
			String razonSocial=Utils.toStr(mapaData.get("razonSocial"));
			
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listT6614ExpVirtualBean = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			for (T6614ExpVirtualBean t6614ExpVirtualBean2 : listT6614ExpVirtualBean) {
				t6614ExpVirtualBean = t6614ExpVirtualBean2;
				 break;
			}
			
			/*Lista documentos seleccionados**/
			//String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel")); //REEMPLAZADO
			List<Map<String, Object>> listaDocumentosSeleccionados = (List<Map<String, Object>>) mapaData.get("listaDocumentosSel"); //REEMPLAZADO
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			//List<Map<String, Object>> listaDocumentosSeleccionados = null;
			//listaDocumentosSeleccionados  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
		
			if(!Utils.isEmpty(listaDocumentosSeleccionados)){
				
				for (Map<String, Object> map : listaDocumentosSeleccionados) {
					
					t6613DocExpVirtBean = new T6613DocExpVirtBean();
					t6613DocExpVirtBean.setCodTipDoc(Utils.toStr(map.get("CODTIPODOC")));
					// Inicio [nchavez 26/05/2015]
					t6613DocExpVirtBean.setDesMotsoldoc(Utils.toStr(map.get("MOTIVO"))!=null?Utils.convertirUnicode(Utils.toStr(map.get("MOTIVO"))):ValidaConstantes.SEPARADOR_GUION);
					// Fin [nchavez 26/05/2015]
					t6613DocExpVirtBean.setCodOrigenDocuento(ValidaConstantes.SEPARADOR_GUION);
					t6613DocExpVirtBean.setCodUsuarioRegistro(Utils.toStr(usuarioBean.getNroRegistro()));
					t6613DocExpVirtBean.setCodUsucarga(ValidaConstantes.SEPARADOR_GUION);
					// Inicio [nchavez 26/05/2015]
					t6613DocExpVirtBean.setDesNombreCompuesto(Utils.toStr(map.get("DESCRIPCION")));
					// Fin [nchavez 26/05/2015]
					listT6613DocExpVirtBean.add(t6613DocExpVirtBean);
				}
			}
			
		
			
			//bean Requerimiento
			t6620RequerimBean = new T6620RequerimBean();
			t6620RequerimBean.setNumRequerimOrigen(numReqOrigen);
			t6620RequerimBean.setCodOrigRequerimiento(ValidaConstantes.IND_ORIGEN_REQ_MANUAL);
			t6620RequerimBean.setCodUsuMod(ValidaConstantes.SEPARADOR_GUION);
			t6620RequerimBean.setCodUsuRegis(Utils.toStr(usuarioBean.getNroRegistro()));
			t6620RequerimBean.setFechaRequerimiento(new Date());
			if(!Utils.isEmpty(fecVencimiento)){
				t6620RequerimBean.setFechaVencimiento(Utils.stringToDate(fecVencimiento,CatalogoConstantes.INT_TWO));
			}
			if(!Utils.isEmpty(numReqOrigen)){
				t6620RequerimBean.setNumRequerimOrigen(numReqOrigen);
			}
			
			
			HashMap<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("t6614ExpVirtualBean", t6614ExpVirtualBean);
			mapParametros.put("listT6613DocExpVirtBean", listT6613DocExpVirtBean);
			mapParametros.put("t6620RequerimBean", t6620RequerimBean);
			// Inicio [nchavez 26/05/2015]
			mapParametros.put("ruc", ruc);
			mapParametros.put("razonSocial", razonSocial);
			// Fin [nchavez 26/05/2015]
			//mapParametros.put("usuarioRegistro", usuarioBean.getNroRegistro());
			// Inicio - [oachahuic][PAS20165E210400270]
			mapParametros.put("desTipProceso", Utils.toStr(mapaData.get("desTipProceso")));
			mapParametros.put("desTipExpediente", Utils.toStr(mapaData.get("desTipExpediente")));
			// Fin - [oachahuic][PAS20165E210400270]
			
			numReq = requerimientoService.registrarRequerimientoManual(mapParametros);
			
			modelAndView = new ModelAndView(jsonView);
			
			HashMap<String, Object> mapRequerimientos = new HashMap<String, Object>();
			
			if (Utils.isEmpty(numReq)) {
				modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_007);
				return modelAndView;
			}
			modelAndView.addObject("aviso01",AvisoConstantes.AVISO_MODULO_02_01_001.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, numReq));
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled())log.debug("Error - RegistraRequerimientoController.registrarRequerimiento");

			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.EXCEP_MODULO_02_01_007);
			modelAndView.addObject("beanErr",  msb);
			
		} finally {
			
			if (log.isDebugEnabled())log.debug( "Final - RegistraRequerimientoController.registrarRequerimiento");
			
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * Valida el numero de Requerimiento Origen con el RSIRAT y verifica que esta notificado
	 */
	// Inicio [nchavez 24/05/2015] 
	public ModelAndView validarNumRequerimientoOrigen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraRequerimientoController.validarNumRequerimientoOrigen");

		ModelAndView modelo = new ModelAndView(jsonView);
		String numExpeOrigen = null;
		String numExpeVirtual = null;
		String numRequerimientoOrigen = null;
		String codTipoExpediente = null;
		ResCoaBean numeroDocumentoRC = null;
		//nchavez [03/06/2016]
		String numeroRuc=null;
		String codDependencia="";// [jjurado 03/08/2016]
		String message=null;
		
		try {

			if (log.isDebugEnabled()) log.debug("Procesa - RegistraRequerimientoController.validarNumRequerimientoOrigen");
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
//			boolean isValidReqOrigin=false; //valida cualquier tipo de Expediente en el RSIRAT
			boolean isValidReqOrigin=true; //Solo valida con RSIRAT el tipo de Expiente Cobranza Coactiva
			//nchavez [08/06/2016]
			message=AvisoConstantes.EXCEP_MODULO_02_01_011;
			numExpeOrigen = request.getParameter("numExpeOrigen");
			numExpeVirtual = request.getParameter("numExpeVirtual");
			numRequerimientoOrigen = request.getParameter("numRequerimiento");
			codTipoExpediente = request.getParameter("codTipoExpediente");
			numeroRuc=request.getParameter("numeroRuc");
			codDependencia = request.getParameter("codDependencia");// [jjurado 03/08/2016]
			//Inicio nchavez [31/05/216]
			Map<String, Object> parametros =new HashMap<String, Object>();
			parametros.put("numRequerimientoOrigen", numRequerimientoOrigen);
			parametros.put("numExpedVirtual", numExpeVirtual);//[ATORRESCH 2017/04/18] EXPEDIENTE
			parametros.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[ATORRESCH 2017/04/18] NO ELIMINADOS
			String numReqOrigen=requerimientoService.validarRequerimientoOrigen(parametros);
			
			//Si no existe el numero de requerimiento origen se valida con RSIRAT
			if (Utils.isEmpty(numReqOrigen)) {
				parametros =new HashMap<String, Object>();
				parametros.put("numExpediente", numExpeOrigen);
				parametros.put("numRC", numRequerimientoOrigen);
				//parametros.put("coddependencia", usuarioBean.getCodDepend());
				parametros.put("coddependencia", codDependencia);// [jjurado 03/08/2016]
				
				if (Utils.equiv(codTipoExpediente,ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA)){
					isValidReqOrigin=false;
					parametros.put("nombreTabla", "res_coa");
					numeroDocumentoRC = configuracionExpedienteService.obtenerNumDocumentoRC(parametros);
					if (!Utils.isEmpty(numeroDocumentoRC)){
						parametros=new HashMap<String, Object>();
						parametros.put("tipoExpe", ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA);
						List<String> tiposDocValidos=requerimientoService.obtenerTipoDocumentosPorRequerimientoValidos(parametros);
						//tiposDocValidos.add("007312");Pruebas Borrar
						if (!Utils.isEmpty(tiposDocValidos)) {
							if (evaluarTipoDocumento(tiposDocValidos, numeroDocumentoRC.getCod_tip_doc())) {
								//Inicio nchavez [01/07/2016]
								Map<String, Object> paramsFecnot = new HashMap<String, Object>();
								paramsFecnot.put("numeroDocumento",numRequerimientoOrigen);
								paramsFecnot.put("numeroRuc",numeroRuc);
								paramsFecnot.put("numExpedienteOrigen", numExpeOrigen);
								paramsFecnot.put("coddependencia", codDependencia);// [jjurado 03/08/2016]
								//Fin nchavez [01/07/2016]
								if (log.isDebugEnabled()) log.debug("RegistraRequerimientoController.validarNumRequerimientoOrigen - Parametros FecNot:"+paramsFecnot);
								
							    Map<String,Object> mapFechaNotificacion=configuracionExpedienteService.obtenerFechaNotificacion(paramsFecnot);
							   // mapFechaNotificacion.put("fechaNotificacion", "15/07/2016");--Pruebas borrar
							    
								if (log.isDebugEnabled()) log.debug("RegistraRequerimientoController.validarNumRequerimientoOrigen - fechaNotificacion:"+mapFechaNotificacion);
								
								if (!Utils.isEmpty(mapFechaNotificacion) && Integer.parseInt(String.valueOf(mapFechaNotificacion.get("codigo")))==1  && !Utils.isEmpty(mapFechaNotificacion.get("fechaNotificacion"))) {
									message=null;
									isValidReqOrigin=true;
									//Inicio [gangles 10/08/2016]--Recomendación 11: Debe ser: Fecha Vencimiento >= Fecha Notificación									
									modelo.addObject("mapFechaNotificacion",mapFechaNotificacion);									
									//Fin [gangles 10/08/2016]
									if (log.isDebugEnabled()) log.debug("RegistraRequerimientoController.validarNumRequerimientoOrigen - Requerimiento notificado");
								}else if(!Utils.isEmpty(mapFechaNotificacion) && Integer.parseInt(String.valueOf(mapFechaNotificacion.get("codigo")))==0){
//									message=AvisoConstantes.EXCEP_MODULO_02_01_011;//no existe
									isValidReqOrigin=false;
								}else if(!Utils.isEmpty(mapFechaNotificacion) && Integer.parseInt(String.valueOf(mapFechaNotificacion.get("codigo")))==-2){
									message=AvisoConstantes.EXCEP_MODULO_02_01_014;
									isValidReqOrigin=false;
								}else{
									message=AvisoConstantes.EXCEP_MODULO_02_01_012;
									if (log.isDebugEnabled()) log.debug("RegistraRequerimientoController.validarNumRequerimientoOrigen - mensaje:"+AvisoConstantes.EXCEP_MODULO_02_01_012);
								}
							}
						} 
					}
				//Inicio - [oachahuic][PAS20165E210400270]
				} else if (ValidaConstantes.TIPO_EXPE_RECTIF_DAM.equals(codTipoExpediente)) {
					String numExpVir = request.getParameter("numExpVirtual");
					String strMsjErr = aduanaService.validarNumReqDAM(numExpVir.trim(), numExpeOrigen, numRequerimientoOrigen);
					if (strMsjErr == null) {
						T6613DocExpVirtBean docNotif = aduanaService.buscarDocSolRectifDAM(numExpVir, numRequerimientoOrigen.substring(0, ValidaConstantes.DAM_SIZE_SOL_RECTIF));
						if (docNotif != null && docNotif.getFecDoc() != null) {
							Map<String, Object> mapFecVenceIni = new HashMap<String, Object>();
							mapFecVenceIni.put("codigo", 1);
							mapFecVenceIni.put("fechaNotificacion", new SimpleDateFormat("dd/MM/yyyy").format(docNotif.getFecDoc()));
							modelo.addObject("mapFechaNotificacion", mapFecVenceIni);
						}
						message = null;
						isValidReqOrigin = true;
					} else {
						message = strMsjErr;
						isValidReqOrigin = false;
					}
				}
				//Fin - [oachahuic][PAS20165E210400270]
//				else{
//					message=null;
//					isValidReqOrigin=true;
//				}
			}else{
				message=AvisoConstantes.EXCEP_MODULO_02_01_013;
				isValidReqOrigin=false;
			}
			//Fin nchavez [31/05/2016]
			
			modelo.addObject("numExpedienteOrigenVal",isValidReqOrigin);
			modelo.addObject("message", message);
			
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - RegistraRequerimientoController.validarNumRequerimientoOrigen");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - RegistraRequerimientoController.validarNumRequerimientoOrigen");
			NDC.pop();
			NDC.remove();

		}

		return modelo;

	}
	// Fin [nchavez 24/05/2015]

	/**
	 * 
	 * @param request
	 * @param response
	 * @return jsonview
	 * Valida si la fecha es un día hábil
	 */
	// Inicio [nchavez 25/05/2015]
	public ModelAndView validarFechaDiaHabil(HttpServletRequest request,HttpServletResponse response){
		if (log.isDebugEnabled()) log.debug("Inicio - RegistraRequerimientoController.validarFechaDiaHabil");
		
		ModelAndView  modelo=new ModelAndView(jsonView);
		boolean isValid=false;
		
		UsuarioBean usuarioBean = null;
		HttpSession session = request.getSession(true);
		String codDependencia = null;
		
		try {
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			} else {
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			}
			
			String fechaRequerimieto=Utils.toStr(request.getParameter("fechavencimiento"));
			
			Date dateVencimiento=new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA).parse(fechaRequerimieto);
			fechaRequerimieto=new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO).format(dateVencimiento);
			
			Map<String, Object> parametros=new HashMap<String, Object>();
			parametros.put("fecha_Evaluar", fechaRequerimieto);
			
			codDependencia = usuarioBean.getCodDepend();
			//Inicio - [oachahuic][PAS20165E210400270]
			if (codDependencia.trim().length() == 3) {
				codDependencia = "0011";//NOTA: SE DEBE DE MEJORAR Y OBTENER LOS FERIADOS DE ADUANAS
			}
			//Fin - [oachahuic][PAS20165E210400270]
			parametros.put("coddependencia", codDependencia); //eaguilar: agregador ori datos
			isValid=sgs33Service.validarFechaDiaHabil(parametros);//eaguilar 14 jun
			
			modelo.addObject("fechaValida", isValid);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistraRequerimientoController.validarFechaDiaHabil");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistraRequerimientoController.validarFechaDiaHabil");
			NDC.pop();
			NDC.remove();
		}
		
		return modelo;
	}
	// Fin [nchavez 25/05/2015]
	
	
	//Inicio [nchavez 24/05/2015]
	private boolean evaluarTipoDocumento(List<String> tiposDocumentoValidos,String codTipoDocumento){
		if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.evaluarTipoDocumento");
		try {
			for (String codigo : tiposDocumentoValidos) {
				if (Utils.equiv(codTipoDocumento,codigo)) {
					if (log.isDebugEnabled()) log.debug("Inicio - RequerimientoServiceImpl.evaluarTipoDocumento - Tipo Documento válido:"+codTipoDocumento);
					return true;
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistraRequerimientoController.evaluarTipoDocumento");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistraRequerimientoController.evaluarTipoDocumento");
			NDC.pop();
			NDC.remove();
		}
		return false;
	}
	// Fin [nchavez 24/05/2015]
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	       
		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.exportarExcelExpedientes");
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
			//Inicio nchavez [27/05/2016]
			
			Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numExpedienteVirtual",new ColumOption("N° Expediente Virtual",ALIGN.LEFT));
			columnsProperties.put("numExpedienteOrigen",new ColumOption("N° Expediente Origen",ALIGN.LEFT));
			columnsProperties.put("numRuc",new ColumOption("RUC",ALIGN.LEFT));
			columnsProperties.put("desRazonSocial",new ColumOption("Razón Social",ALIGN.LEFT));
			columnsProperties.put("fechaDocumentoOrigen",new ColumOption("Fecha del Documento Origen",ALIGN.RIGHT));
			columnsProperties.put("desProceso",new ColumOption("Proceso",ALIGN.LEFT));
			columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente",ALIGN.LEFT));
			columnsProperties.put("fecRegistro",new ColumOption("Fecha de Registro del Expediente",ALIGN.RIGHT));
			columnsProperties.put("nombreResponsable",new ColumOption("Responsable del Expediente",ALIGN.LEFT));
			
			HSSFWorkbook libro=ExcelUtil.buildWorkbookXLS(titulo, "Hoja 1", columnsProperties, listadoExportar);
		 //Fin nchavez [27/05/2016]
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
    	   	 if (log.isDebugEnabled())log.info((Object) "Error - RegistraRequerimientoController.exportarExcelExpedientes");
    	   	 log.error( ex, ex);
    	   	 
             mensajeBean.setError(true);
             mensajeBean
                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
                                        + ex.getMessage());
             view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		if (log.isDebugEnabled())log.info((Object) "Fin - RegistraRequerimientoController.exportarExcelExpedientes");
		
       return view;

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
	
	
	@RequestMapping(value = "/descargarArchivo", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivo (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistraRequerimientoController.descargarArchivo");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os=null;
		String nombreArchivo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RegistraRequerimientoController.descargarArchivo");
			
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
			
			if (log.isDebugEnabled()) log.debug("Error - RegistraRequerimientoController.descargarArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistraRequerimientoController.descargarArchivo");
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
	
	@RequestMapping(value="previsualizarArchivo",method=RequestMethod.GET)
	public void previsualizarArchivo(HttpServletRequest request,HttpServletResponse response){
		try {
			if (log.isDebugEnabled())log.info((Object) "Inicio - RegistraRequerimientoController.previsualizarArchivo");

			String numExpedVirtual=String.valueOf(request.getParameter("numExpedVirtual"));
			String numRequerimiento=String.valueOf(request.getParameter("numRequerimiento"));
			String fechaVencimiento=String.valueOf(request.getParameter("fechaVencimiento"));
			String documentos=String.valueOf(request.getParameter("documentos"));
			
			JsonSerializer jsonSerializer=new JsonSerializer();
			Map<String, Object> objJsonReportParam=new LinkedHashMap<String, Object>();
			Map<String, Object> objJsonCabecera=new LinkedHashMap<String, Object>();
			List<T6623TipDocExpBean> listaTipDocExp;
			List<T6614ExpVirtualBean> listaExpedientesVirtuales;
			String codigoTipoExpediente=null;
			
			HashMap<String, Object> mapParametrosBusqueda;
			String numExp = Utils.toStr(numExpedVirtual);
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExp); 
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
			if (listaExpedientesVirtuales!=null && !listaExpedientesVirtuales.isEmpty()) {
				 T6614ExpVirtualBean  t6614ExpVirtualBean=listaExpedientesVirtuales.get(0);
				 DdpBean beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 codigoTipoExpediente=t6614ExpVirtualBean.getCodTipoExpediente();
				 
				 objJsonCabecera.put("numRuc", t6614ExpVirtualBean.getNumRuc());
				 objJsonCabecera.put("proceso", t6614ExpVirtualBean.getDesProceso());
				 objJsonCabecera.put("nExpedienteOrigen", t6614ExpVirtualBean.getNumExpedienteOrigen());
				 objJsonCabecera.put("fechaOrigen", Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen()));
				 objJsonCabecera.put("responsable", t6614ExpVirtualBean.getNombreResponsable());
				 objJsonCabecera.put("razonSocial",beanContribuyente.getDesRazonSocial().trim().replace("\"", "\\\""));
				 //Inicio nchavez 24/06/2016
				 objJsonCabecera.put("tipoExp", Utils.convertirUnicode(t6614ExpVirtualBean.getDesTipoExpediente()));
				 //Fin nchavez 24/06/2016
				 objJsonCabecera.put("nExpedienteVirtual", t6614ExpVirtualBean.getNumExpedienteVirtual());
				 objJsonCabecera.put("fechaRegExpediente",Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro()));
				 
				 String[] array_documentos=documentos.split("[|]");
				 List<String> idsdocumentos=new ArrayList<String>(array_documentos.length);
				 String[] descdocumentos=new String[array_documentos.length];
				
				 if (array_documentos.length>0) {
					for (int i = 0; i < array_documentos.length; i++) {
						String[] objDoc=array_documentos[i].split(",");
						idsdocumentos.add(i, objDoc[0]);
						descdocumentos[i]=objDoc.length>1?Utils.convertirUnicode(objDoc[1]):"";
					}
				 }
					
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("codTipoExpediente", codigoTipoExpediente);
				mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				mapParametrosBusqueda.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO);
				mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
				mapParametrosBusqueda.put("codigosTipoDocumento",idsdocumentos);
					
				List<Map<String, Object>> listaDocumentosJSON=null; 
				Map<String,Object> objDocumentoJSON=null;
				listaTipDocExp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
					
				if (listaTipDocExp!=null && !listaTipDocExp.isEmpty()) {
					listaDocumentosJSON=new ArrayList<Map<String,Object>>();
					int numOrden=1;

					for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
						t6623TipDocExpBean.setNumOrden(numOrden);
						objDocumentoJSON=new LinkedHashMap<String, Object>();
						objDocumentoJSON.put("correlativo",String.valueOf(t6623TipDocExpBean.getNumOrden()));
						objDocumentoJSON.put("tipodocumento", Utils.convertirUnicode(t6623TipDocExpBean.getDesTipoDocumentoCompuesto().trim())); //eaguilar 12 JUL agregado convertUnic
						//Inicio nchavez 24/06/2016
						objDocumentoJSON.put("motivo",descdocumentos.length>0?Utils.convertirUnicode(descdocumentos[numOrden-1]).trim():"");
						//Fin nchavez 24/06/2016
						numOrden++;
						listaDocumentosJSON.add(objDocumentoJSON);
					}
				}
					
				objJsonCabecera.put("fechavencimiento",fechaVencimiento);
				objJsonCabecera.put("nrequerimientoOrigen", numRequerimiento);
				objJsonCabecera.put("fecreqnotificacion",null);//Campos temprales - por confirmar si apareceran en el reporte
				objJsonCabecera.put("fecreqemision",null);
				
				objJsonReportParam.put("cabecera",objJsonCabecera);
				objJsonReportParam.put("detalle", listaDocumentosJSON);
				
				byte[] bytes=requerimientoService.obtenerPrevisuaizacionRegistroRequerimiento(jsonSerializer.serialize(objJsonReportParam).toString());
				
//				PrintWriter out=response.getWriter();
//				response.setContentType("application/json");
//				out.write(jsonSerializer.serialize(objJsonReportParam).toString());
				OutputStream out=response.getOutputStream();
				response.setContentLength(bytes.length);
				response.setContentType("application/pdf");
				out.write(bytes);
				out.close();
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraRequerimientoController.previsualizarArchivo");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
//			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
//			modelAndView.addObject("beanErr", (Object) msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistraRequerimientoController.previsualizarArchivo");
			}
			NDC.pop();
			NDC.remove();
		}
	}
	
	//Inicio [gangles 19/08/2016]
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
	//Fin [gangles 19/08/2016]	
	
	
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
	// Inicio [nchavez 26/05/2015]
	public void setValidarService(ValidarParametrosService validarService) {
		this.validarService = validarService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}
	public void setSgs33Service(Sgs33Service sgs33Service) {
		this.sgs33Service = sgs33Service;
	}
	// Fin [nchavez 26/05/2015]
		
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