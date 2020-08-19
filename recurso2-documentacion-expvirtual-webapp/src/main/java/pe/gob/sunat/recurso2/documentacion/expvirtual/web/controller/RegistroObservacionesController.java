package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

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

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;

import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ObservacionExpedienteVirtualService;

@Controller
public class RegistroObservacionesController extends MultiActionController {

	private static final Log log = LogFactory.getLog(RegistroObservacionesController.class);
	
	private ExpedienteVirtualService expedienteVirtualService; 
	private ConfiguracionExpedienteService configuracionExpedienteService; 
	private ParametroService paramService;
	private JsonView jsonView;	
	private ValidarParametrosService validarParametrosService;
	private ObservacionExpedienteVirtualService observacionExpedienteVirtualService;	
	// Inicio [jquispe 27/05/2016]
	private SeguimientoService seguiService;
	private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	public ModelAndView cargarBusqObsExpVirtual(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info("Inicio - RegistroObservacionesController.cargarBusqObsExpVirtual");
		
		List<T01ParamBean> listadoProcesos = null;
		List<T01ParamBean> listadoDependencias = null;
		List<T01ParamBean> listadoTipoFecha=null;
		List<T01ParamBean> listadoNumeroTipoExpediente=null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		ModelAndView modelAndView = null;
		try {
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String codDepUsuario = usuarioBean.getCodDepend();
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			//Inicio - [oachahuic][PAS20165E210400270]
			listadoDependencias = new ArrayList<T01ParamBean>();
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
			//Fin - [oachahuic][PAS20165E210400270]
			listadoTipoFecha= paramService.listarTipoFecha();
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_01_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08",AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09",AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10",AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11",AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12",AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13",AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14",AvisoConstantes.EXCEP_MODULO_04_01_014);	
			excepciones.put("excepcion18",AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19",AvisoConstantes.EXCEP_MODULO_04_01_019);
							
			modelAndView = new ModelAndView("ConsultaExpediente");	
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));			
			modelAndView.addObject("listadoTipoFecha", new JsonSerializer().serialize(listadoTipoFecha));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("codDepUsuario", new JsonSerializer().serialize(codDepUsuario));
			} else {
				modelAndView.addObject("codDepUsuario", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));			
	
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroObservacionesController.cargarBusqObsExpVirtual");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroObservacionesController.cargarBusqObsExpVirtual");
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled()) log.info("Final - RegistroObservacionesController.cargarBusqObsExpVirtual");
		return modelAndView;
	}
		
	public ModelAndView cargarListaTiposExpediente (HttpServletRequest request, HttpServletResponse response){
		
		if (log.isDebugEnabled()) log.info("Inicio - RegistroObservacionesController.cargarListaTiposExpediente");
		ModelAndView modelAndView;
		List<T6624TipExpProcBean> listadoTiposExpendientes;
		try {
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			mapParametrosBusqueda.put("indConsulta", ValidaConstantes.IND_TIPO_EXPE_ASOCIADO);
			
			listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			
			modelAndView= new ModelAndView(jsonView);
			modelAndView.addObject("listadoTiposExpendientes",listadoTiposExpendientes);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroObservacionesController.cargarListaTiposExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroObservacionesController.cargarListaTiposExpediente");
			NDC.pop();
			NDC.remove();
		}
		
		
		if (log.isDebugEnabled()) log.info("Final - RegistroObservacionesController.cargarListaTiposExpediente");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/cargarListadoExpedientesVirtuales", method = RequestMethod.POST)
	public ModelAndView cargarListadoExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response){
	
		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - RegistroObservacionesController.cargarListadoExpedientesVirtuales");
		}
		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		
		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numExp = Utils.toStr(request.getParameter("numExp"));
		String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
		
		Date fecDesde = null;
		Date fecHasta = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String codColab=usuarioBean.getNroRegistro();
			//String codDepUsuario=usuarioBean.getCodDepend();
			String codDepUsuario="";
			/*if (!Utils.isEmpty(numExp)) {
				codDepUsuario=usuarioBean.getCodDepend().substring(0, 3);
			}else{
				codDepUsuario= Utils.toStr(request.getParameter("codDependencia"));
			}*/
			if (!Utils.isEmpty(numExp) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else if(Utils.isEmpty(numExp)){
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
			}
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
				Calendar calendarHasta=Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta=calendarHasta.getTime();
			}

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codEstado",ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			mapParametrosBusqueda.put("codColab",codColab);
			if(!Utils.isEmpty(codDepUsuario)){
				mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				}
			//mapParametrosBusqueda.put("codDependencia", codDepUsuario);
			
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("numExpedVirtual", numExp);
				} else {
					mapParametrosBusqueda.put("numExpedOrigen", numExp);
				}
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				//INICIO LLRB 14022020			
				if (listaExpedientesVirtuales.size() !=0){
					String codTipoExp = listaExpedientesVirtuales.get(0).getCodTipoExpediente();	
					modelAndView.addObject("codTipoExp", codTipoExp);
					log.debug("listaExpedientesVirtuales-Inicio : "+listaExpedientesVirtuales.get(0).getCodTipoExpediente());
					log.debug("codTipoExp : "+codTipoExp);
				}else{
					modelAndView.addObject("codTipoExp", "");
				}
				
				//FIN LLRB 14022020

				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_01);
					}
					return modelAndView;
				} else {
					// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
					T6614ExpVirtualBean bean = listaExpedientesVirtuales.get(0);
					registrarSeguimientoConsultaRegistroObservacionExpediente(bean.getNumExpedienteVirtual());
					// Fin [jquispe 27/05/2016]
					modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
					return modelAndView;
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
			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoConsultaRegistroObservacionExpediente(null);
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - RegistroObservacionesController.cargarListadoExpedientesVirtuales");
			}
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistroObservacionesController.cargarListadoExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaRegistroObservacionExpediente(String numeroExpedienteVirtual) throws Exception{
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
	
	@RequestMapping(value = { "/cargarObservacionesPorExpVirtual" }, method = { RequestMethod.POST })
	public ModelAndView cargarObservacionesPorExpVirtual(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView modelo = null;
		T6614ExpVirtualBean datosExpBean = null;
		
		String fecRegistroVista =ValidaConstantes.CADENA_VACIA;
		String fecOrigenDocVista =ValidaConstantes.CADENA_VACIA;
		List<T6615ObsExpBean> obtenerObservacionesExpediente = new ArrayList<T6615ObsExpBean>();
		Map<String, Object> params = null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			
			if(reader != null) {
				
				jsonEnviado = reader.readLine();
				
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			
			Date fechaRegistro = null;
			Date fechaOrigenDoc = null;
						
			Map<String, Object>	parametros = new HashMap<String, Object>();
			
			String numExpedienteVirtual = Utils.toStr(dataEnvio.get("aux"));
			
			if(numExpedienteVirtual!= null || numExpedienteVirtual!=ValidaConstantes.CADENA_VACIA){
				
				parametros.put("numExpedienteVirtual", numExpedienteVirtual);
				parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				
				datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				if(datosExpBean!=null){
					params = new HashMap<String,Object>();
					params.put("numExpedVirtual", datosExpBean.getNumExpedienteVirtual());
					fechaRegistro = datosExpBean.getFecRegistro();
					if(fechaRegistro!=null){	
						fecRegistroVista = formatoFechaExporta.format(fechaRegistro);
					}
					fechaOrigenDoc = datosExpBean.getFechaDocumentoOrigen();
					if(fechaOrigenDoc!=null){	
						fecOrigenDocVista = formatoFechaExporta.format(fechaOrigenDoc);
					}
					obtenerObservacionesExpediente = observacionExpedienteVirtualService.obtenerObservacionesExpediente(params);
					
				}
			}	
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_01_002);
			
			HashMap<String, String> avisos = new HashMap<String, String>();
			avisos.put("aviso1",AvisoConstantes.AVISO_MODULO_04_01_001);
			avisos.put("aviso2",AvisoConstantes.AVISO_MODULO_04_01_002);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_04_01_015);
			
			modelo = new ModelAndView(jsonView);
			modelo.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
			modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
			modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fecOrigenDocVista));
			modelo.addObject("lstObsExp", new JsonSerializer().serialize(obtenerObservacionesExpediente));
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelo.addObject("avisos",new JsonSerializer().serialize(avisos));			
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoConsultaObservacion(numExpedienteVirtual);
			// Fin [jquispe 27/05/2016]
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroObservacionesController.cargarObservacionesPorExpVirtual");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroObservacionesController.cargarObservacionesPorExpVirtual");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaObservacion(String numeroExpedienteVirtual) throws Exception{
		
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
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_OBSERVACIONES);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_OBSERVACIONES)));
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

public ModelAndView registrarObservacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroObservacionesController.registrarObservacion");

			ModelAndView modelAndView = null;
			T6615ObsExpBean t6615ObsExpBean;					
			String numObs = "";
			UsuarioBean usuarioBean = null;
			HttpSession session = request.getSession(true);;
			T6614ExpVirtualBean datosExpBean = null;			
			String fecRegistroVista =ValidaConstantes.CADENA_VACIA;
			String fecOrigenDocVista =ValidaConstantes.CADENA_VACIA;
			List<T6615ObsExpBean> obtenerObservacionesExpediente = new ArrayList<T6615ObsExpBean>();
			Map<String, Object> params = null;
			List<T6614ExpVirtualBean> listaExpedientesVirtuales=new ArrayList<T6614ExpVirtualBean>() ;
			String fechaDocOrigen="";
			String fechaRegExpediente="";
			DdpBean beanContribuyente;
			try {
				
				if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
					
					String usub = request.getParameter("usub");
					String tenc = request.getParameter("tenc");
					usub = usub.replace(' ', '+');
					usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
					
				}else{
					
					usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
					
				}				
				String numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
				String desObservacion = Utils.toStr(request.getParameter("desObservacion"));
				
				//bean Observaciones
				t6615ObsExpBean = new T6615ObsExpBean();
				t6615ObsExpBean.setDesObservacion(desObservacion);
				t6615ObsExpBean.setNumExpedienteVirtual(numExpedienteVirtual);
				
				HashMap<String, Object> mapParametros = new HashMap<String, Object>();				
				mapParametros.put("t6615ObsExpBean", t6615ObsExpBean);
				mapParametros.put("usuarioRegistro", usuarioBean.getNroRegistro());
				numObs = observacionExpedienteVirtualService.registrarObservacion(mapParametros);				
				modelAndView = new ModelAndView(jsonView);
				
				if (Utils.isEmpty(numObs)) {
					modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_04_01_015);
					return modelAndView;
				}
				
				/**/
				Date fechaRegistro = null;
				Date fechaOrigenDoc = null;
				Map<String, Object>	parametros = new HashMap<String, Object>();				
				
				if(numExpedienteVirtual!= null || numExpedienteVirtual!=ValidaConstantes.CADENA_VACIA){
					
					parametros.put("numExpedienteVirtual", numExpedienteVirtual);
					parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
					
					datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
					SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
						
				if(datosExpBean!=null){
						params = new HashMap<String,Object>();
						params.put("numExpedVirtual", datosExpBean.getNumExpedienteVirtual());
						fechaRegistro = datosExpBean.getFecRegistro();
						if(fechaRegistro!=null){	
							fecRegistroVista = formatoFechaExporta.format(fechaRegistro);
						}
						fechaOrigenDoc = datosExpBean.getFechaDocumentoOrigen();
						if(fechaOrigenDoc!=null){	
							fecOrigenDocVista = formatoFechaExporta.format(fechaOrigenDoc);
						}
						params = new HashMap<String,Object>();
						params.put("numExpedVirtual", numExpedienteVirtual);
						obtenerObservacionesExpediente = observacionExpedienteVirtualService.obtenerObservacionesExpediente(params);		
					}
				}	
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_01_002);
				
				HashMap<String, String> avisos = new HashMap<String, String>();
				avisos.put("aviso1",AvisoConstantes.AVISO_MODULO_04_01_001);
				avisos.put("aviso2",AvisoConstantes.AVISO_MODULO_04_01_002);
				
				HashMap<String, String> excepciones = new HashMap<String, String>();
				
				excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_014);
				excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_04_01_015);
				
				modelAndView = new ModelAndView(jsonView);
				modelAndView.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
				modelAndView.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
				modelAndView.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fecOrigenDocVista));
				modelAndView.addObject("lstObsExp", new JsonSerializer().serialize(obtenerObservacionesExpediente));
				modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
				modelAndView.addObject("avisos",new JsonSerializer().serialize(avisos));	
			
			} catch (Exception ex) {
				
				if (log.isDebugEnabled())log.debug("Error - RegistroObservacionesController.registrarObservacion");

				log.error( ex, ex);
				MensajeBean msb = new MensajeBean();
				modelAndView = new ModelAndView("PaginaError");
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.EXCEP_MODULO_04_01_015);
				modelAndView.addObject("beanErr",  msb);
				
			} finally {
				
				if (log.isDebugEnabled())log.debug( "Final - RegistroObservacionesController.registrarObservacion");
				
				NDC.pop();
				NDC.remove();
			}
			
			return modelAndView;
	}

	@RequestMapping(value = { "/validarContribuyente" }, method = { RequestMethod.POST })
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - RegistroObservacionesController.validarContribuyente");
		
		DdpBean beanContribuyente;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
				if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
					
					String usub = request.getParameter("usub");
					String tenc = request.getParameter("tenc");
					usub = usub.replace(' ', '+');
					usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
					
				}else{
					
					usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
					
				}			
				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				String codDepUsuario=usuarioBean.getCodDepend();
			
			modelAndView = new ModelAndView(jsonView);
			String numeroRuc = Utils.toStr( request.getParameter("numRuc"));

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
				log.debug((Object) "Error - RegistroObservacionesController.validarContribuyente");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistroObservacionesController.validarContribuyente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_01;


	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	       
	       try {
	    	if (log.isDebugEnabled())log.info((Object) "Inicio - RegistroObservacionesController.exportarExcelExpedientes");
	    	   
	    	listadoExportarCadena = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
	    	response.setContentType("application/vnd.ms-excel");
	        HSSFWorkbook libro = new HSSFWorkbook();
	        HSSFSheet hoja = libro.createSheet("Hoja 1");
        
	        hoja.setColumnWidth(0, 2500);
	        hoja.setColumnWidth(1, 2500);
	        hoja.setColumnWidth(2, 3500);
	        hoja.setColumnWidth(3, 4500);
	        hoja.setColumnWidth(4, 4800);
	        hoja.setColumnWidth(5, 16000);
	        hoja.setColumnWidth(6, 16000);
	        hoja.setColumnWidth(7, 16000);
	        hoja.setColumnWidth(8, 16000);
	        hoja.setColumnWidth(9, 16000);
	        //Inicio [jquispe 16/06/2016] Modificado para agregar la nueva columna Razon Social.	
	        hoja.setColumnWidth(10, 16000);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
			hoja.setColumnWidth(11, 16000);
			hoja.setColumnWidth(12, 16000);
	        //fin JEFFIO [13/03/2017]
	        
	        
	        HSSFRow fila = hoja.createRow(1);
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        hoja.addMergedRegion(new Region(1,(short)0,1,(short)3));	   
	   
	        fila = hoja.createRow(4);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        HSSFCell celda8 = fila.createCell(8);
	        HSSFCell celda9 = fila.createCell(9);
	        //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.		
	        HSSFCell celda10 = fila.createCell(10);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        HSSFCell celda11 = fila.createCell(11);
	        HSSFCell celda12 = fila.createCell(12);
	        //fin JEFFIO [13/03/2017]
	        
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Nro. Expediente Origen");
	        celda2.setCellValue("Nro. Expediente Virtual");
	        celda3.setCellValue("RUC");	        
	        // Inicio [jquispe 16/06/2016] Se modifico para agregar la nueva columna Razon Social.
	        celda4.setCellValue("Razón Social");	        
	        celda5.setCellValue("Proceso");
	        celda6.setCellValue("Tipo de Expediente");
	        celda7.setCellValue("Estado expediente");
	        celda8.setCellValue("Fecha de Registro del Expediente");
	        celda9.setCellValue("Fecha de Documento Origen");
	        celda12.setCellValue("origen");
	        // Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        celda10.setCellValue("Fecha de Notificación") ;
	        celda11.setCellValue("Forma de Notificación") ;
	        //fin JEFFIO [13/03/2017]
		
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
	        ssheet.autoSizeColumn(6);
	        ssheet.autoSizeColumn(7);
	        ssheet.autoSizeColumn(8);
	        ssheet.autoSizeColumn(9);
	        //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.	
	        ssheet.autoSizeColumn(10);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        ssheet.autoSizeColumn(11);
	        ssheet.autoSizeColumn(12);
	        //fin JEFFIO [13/03/2017]
	      //Inicio [gangles 19/08/2016]-Se agrega fecha del reporte  
	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 19/08/2016]
	        
	     	HSSFCellStyle estiloCelda = libro.createCellStyle();	       
	        
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	       estiloCelda.setFont(fuente);
	       estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setBottomBorderColor((short) 8);
	       estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setLeftBorderColor((short) 8);
	       estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setRightBorderColor((short) 8);
	       estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setTopBorderColor((short) 8);
	       
	       estiloCelda.setFillForegroundColor((short) 22);
	       estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       
	        HSSFCellStyle estiloCeldaDatos = libro.createCellStyle();
	        
	        estiloCeldaDatos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        estiloCeldaDatos.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	        estiloCeldaDatos.setFont(fuente);
	        estiloCeldaDatos.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setBottomBorderColor((short) 8);
	        estiloCeldaDatos.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setLeftBorderColor((short) 8);
	        estiloCeldaDatos.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setRightBorderColor((short) 8);
	        estiloCeldaDatos.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setTopBorderColor((short) 8);
	        
	        celda.setCellStyle(estiloCelda);
	        celda1.setCellStyle(estiloCelda);
	        celda2.setCellStyle(estiloCelda);
	        celda3.setCellStyle(estiloCelda);
	        celda4.setCellStyle(estiloCelda);
	        celda5.setCellStyle(estiloCelda);
	        celda6.setCellStyle(estiloCelda);
	        celda7.setCellStyle(estiloCelda);
	        celda8.setCellStyle(estiloCelda);
	        celda9.setCellStyle(estiloCelda);  
	        //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.	
	        celda10.setCellStyle(estiloCelda);  
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        celda11.setCellStyle(estiloCelda); 
	        celda12.setCellStyle(estiloCelda); 
	        //fin JEFFIO [13/03/2017]
	        
	        HSSFCellStyle estiloTitulo = libro.createCellStyle();
	        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        estiloTitulo.setFont(fuente);
	        
	        tituloCelda.setCellStyle(estiloTitulo);

	        HSSFRichTextString texto;
	        HSSFFont fuenteContenido = libro.createFont();
	        HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
	        estiloCeldaContenido.setFont(fuenteContenido);
	        //Inicio Jeffio 27/06/2017  bordes
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
	        //Fin 27/06/2017
	                    
	        for (int i = 0; i < listadoExportar.size(); i++) {
            
               fila = hoja.createRow(i + 5);
               celda = fila.createCell(0);
               texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")));
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
               celda2 = fila.createCell(2);
               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteVirtual")));
               hoja.autoSizeColumn(2);
               celda2.setCellStyle(estiloRecorrido);
               celda3 = fila.createCell(3);
               celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               // Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desRazonSocial").toString()));
               hoja.autoSizeColumn(4);    
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desProceso")));
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desTipoExpediente")));
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")));
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);
               celda8 = fila.createCell(8);
               celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecRegistro")));
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);
               celda9 = fila.createCell(9);
               celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
               hoja.autoSizeColumn(9);
               celda9.setCellStyle(estiloRecorrido);
               celda10 = fila.createCell(12);
               celda10.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desOrigen")));
               hoja.autoSizeColumn(12);
               celda10.setCellStyle(estiloRecorrido);
              // Fin [jquispe 10/06/2016]
	   	       //inicio JEFFIO [13/03/2017]
               celda11 = fila.createCell(10);
               celda11.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("strFecNotifOrigen")));
               hoja.autoSizeColumn(10);
               celda11.setCellStyle(estiloRecorrido);
               celda12 = fila.createCell(11);
               celda12.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desForNotifOrigen")));
               hoja.autoSizeColumn(11);
               celda12.setCellStyle(estiloRecorrido);
	   	       //fin JEFFIO [13/03/2017]
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
           
	         String filename="rpt_expediente_virtual_" + part + ".xls";;
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
	    	     if (log.isDebugEnabled())log.info((Object) "Error - RegistroObservacionesController.exportarExcelExpedientes");
	    	   	 log.error( ex, ex);
	             mensajeBean.setError(true);
	             mensajeBean
	                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
	                                        + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
	    	   if (log.isDebugEnabled())log.info((Object) "Fin - RegistroObservacionesController.exportarExcelExpedientes");
	       }
	       
	       return view;
	}
	
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelObservaciones(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_02;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	     	      
	       String numExpOrigen = "";
	       String numExpVirtual = "";
	       String numRuc = "";
	       String estExpediente = "";
	       String tipoProceso = "";
	       String tipoExpediente = "";	      
	       String fechaGeneracion = "";
	       String razonSocial = "";
	       String razonSocialRuc = "";
	       try {
	    	if (log.isDebugEnabled())log.info((Object) "Inicio - RegistroObservacionesController.exportarExcelObservaciones");
	    	   
	    	listadoExportarCadena = request.getParameter("listadoObservacionesCadena");
	    	numRuc = Utils.toStr(request.getParameter("hiddenNumRuc"));
	    	razonSocial =  Utils.toStr(request.getParameter("hiddenRazonSocial"));
		    numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
		    numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtual"));	       
		    estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
		    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
		    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
		    fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
		  
		    razonSocialRuc = numRuc +" - "+razonSocial;
			
			@SuppressWarnings("unchecked")
			List<T6615ObsExpBean> listadoExportar = (List<T6615ObsExpBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
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
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,2);
			hoja.addMergedRegion(rango);
	        
			HSSFCell nomFecha = fila.createCell(3);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(4);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC:");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(razonSocialRuc);
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente Origen:");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);
	        
	        HSSFCell subtituloCelda3 = fila.createCell(3);
	        subtituloCelda3.setCellValue("N° Expediente Virtual:");
	        HSSFCell contenido2 = fila.createCell(4);
	        contenido2.setCellValue(numExpVirtual);
	        
	       /* HSSFCell subtituloCelda4 = fila.createCell(5);
	        subtituloCelda4.setCellValue("Estado del Expediente");
	        HSSFCell contenido3 = fila.createCell(6);
	        contenido3.setCellValue(estExpediente);*/
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso:");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente:");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);	   
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda7 = fila.createCell(1);
	        subtituloCelda7.setCellValue("Fecha de Generación del Expediente:");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda8 = fila.createCell(3);
	        subtituloCelda8.setCellValue("Estado del Expediente:");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        //Inicio [gangles 22/08/2016]
	        CellRangeAddress rangoObservacion = null;
	        rangoObservacion = new CellRangeAddress(10, 10, 1, 2);
			hoja.addMergedRegion(rangoObservacion);
			 //Fin [gangles 22/08/2016]
			HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);	       
	       
	        celda.setCellValue("N°");
	        celda1.setCellValue("Observación");
	        celda3.setCellValue("Usuario Registro");
	        celda4.setCellValue("Fecha de registro");
	       	        
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
	    	    
	        HSSFCellStyle estiloCelda = libro.createCellStyle();
		       
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	       estiloCelda.setFont(fuente);
	       estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setBottomBorderColor((short) 8);
	       estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setLeftBorderColor((short) 8);
	       estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setRightBorderColor((short) 8);
	       estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setTopBorderColor((short) 8);
	       
	       estiloCelda.setFillForegroundColor((short) 22);
	       estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       
	        HSSFCellStyle estiloCeldaDatos = libro.createCellStyle();
	        
	        estiloCeldaDatos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        estiloCeldaDatos.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	        estiloCeldaDatos.setFont(fuente);
	        estiloCeldaDatos.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setBottomBorderColor((short) 8);
	        estiloCeldaDatos.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setLeftBorderColor((short) 8);
	        estiloCeldaDatos.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setRightBorderColor((short) 8);
	        estiloCeldaDatos.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setTopBorderColor((short) 8);
	        
	        celda.setCellStyle(estiloCelda);
	        celda1.setCellStyle(estiloCelda);
	        celda2.setCellStyle(estiloCelda);
	        celda3.setCellStyle(estiloCelda);
	        celda4.setCellStyle(estiloCelda);
	        
	        HSSFCellStyle estiloTitulo = libro.createCellStyle();
	        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        estiloTitulo.setFont(fuente);
	        
	        tituloCelda.setCellStyle(estiloTitulo);
	        subtituloCelda.setCellStyle(estiloTitulo);
	        subtituloCelda1.setCellStyle(estiloTitulo);
	        subtituloCelda2.setCellStyle(estiloTitulo);
	        subtituloCelda3.setCellStyle(estiloTitulo);
	        subtituloCelda5.setCellStyle(estiloTitulo);
	        subtituloCelda6.setCellStyle(estiloTitulo);
	        subtituloCelda7.setCellStyle(estiloTitulo);
	        subtituloCelda8.setCellStyle(estiloTitulo);
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
	        for (T6615ObsExpBean t6615ObsExpBean : listadoExportar) {	        
	        	  fila = hoja.createRow(i + 11);
	               celda = fila.createCell(0);
	               texto = new HSSFRichTextString( Utils.toStr(t6615ObsExpBean.getNumOrden()));
	               celda.setCellValue(texto.toString());
	               hoja.autoSizeColumn(0);
	               celda.setCellStyle(estiloRecorrido);
	               celda1 = fila.createCell(1);
	               celda1.setCellValue(Utils.convertirUnicode(t6615ObsExpBean.getDesObservacion()));
	               hoja.autoSizeColumn(1);
	               celda1.setCellStyle(estiloRecorrido);
	               //Inicio [gangles 22/08/2016]
	   	        	CellRangeAddress rangoObservacionVal = null;
	   	        	rangoObservacionVal = new CellRangeAddress(i + 11, i + 11, 1, 2);
	   	        	hoja.addMergedRegion(rangoObservacionVal);
	   	        	celda2 = fila.createCell(2);
	   	        	celda2.setCellStyle(estiloRecorrido);
	   			 //Fin [gangles 22/08/2016]
	               celda3 = fila.createCell(3);
	               celda3.setCellValue(Utils.toStr(t6615ObsExpBean.getNomUsuGeneraObs()));
	               hoja.autoSizeColumn(3);
	               celda3.setCellStyle(estiloRecorrido);
	               celda4 = fila.createCell(4);
	               celda4.setCellValue(Utils.toStr(t6615ObsExpBean.getFecGeneracionObs()));
	               hoja.autoSizeColumn(4);
	               celda4.setCellStyle(estiloRecorrido);
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
           
	         String filename="rpt_Obsevaciones_expediente_virtual_" + part + ".xls";;
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
	    	   	 if (log.isDebugEnabled())log.info((Object) "Error - RegistroObservacionesController.exportarExcelObservaciones");
	    	   	 log.error( ex, ex);
	             mensajeBean.setError(true);
	             mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
	    	   if (log.isDebugEnabled())log.info((Object) "Fin - RegistroObservacionesController.exportarExcelObservaciones");
	       }
	       
	       return view;
	}
	//INICIO LLRB
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientesFisca(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_01;


	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	       
	       try {
	    	if (log.isDebugEnabled())log.info((Object) "Inicio - RegistroObservacionesController.exportarExcelExpedientes");
	    	   
	    	listadoExportarCadena = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
	    	response.setContentType("application/vnd.ms-excel");
	        HSSFWorkbook libro = new HSSFWorkbook();
	        HSSFSheet hoja = libro.createSheet("Hoja 1");
        
	        hoja.setColumnWidth(0, 2500);
	        hoja.setColumnWidth(1, 2500);
	        hoja.setColumnWidth(2, 3500);
	        hoja.setColumnWidth(3, 4500);
	        hoja.setColumnWidth(4, 4800);
	        hoja.setColumnWidth(5, 16000);
	        hoja.setColumnWidth(6, 16000);
	        hoja.setColumnWidth(7, 16000);
	        hoja.setColumnWidth(8, 16000);

	        HSSFRow fila = hoja.createRow(1);
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        hoja.addMergedRegion(new Region(1,(short)0,1,(short)3));	   
	   
	        fila = hoja.createRow(4);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        HSSFCell celda8 = fila.createCell(8);

	        celda.setCellValue("N°");
	        celda1.setCellValue("Nro. Expediente Origen");	       
	        celda2.setCellValue("RUC");      
	        celda3.setCellValue("Razón Social");	        
	        celda4.setCellValue("Proceso");
	        celda5.setCellValue("Tipo de Expediente");
	        celda6.setCellValue("Estado expediente");	
	        celda7.setCellValue("Fecha de Expediente");
	        celda8.setCellValue("origen");

		
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
	        ssheet.autoSizeColumn(6);
	        ssheet.autoSizeColumn(7);
	        ssheet.autoSizeColumn(8);

	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 19/08/2016]
	        
	     	HSSFCellStyle estiloCelda = libro.createCellStyle();	       
	        
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	       estiloCelda.setFont(fuente);
	       estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setBottomBorderColor((short) 8);
	       estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setLeftBorderColor((short) 8);
	       estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setRightBorderColor((short) 8);
	       estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setTopBorderColor((short) 8);
	       
	       estiloCelda.setFillForegroundColor((short) 22);
	       estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       
	        HSSFCellStyle estiloCeldaDatos = libro.createCellStyle();
	        
	        estiloCeldaDatos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        estiloCeldaDatos.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	        estiloCeldaDatos.setFont(fuente);
	        estiloCeldaDatos.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setBottomBorderColor((short) 8);
	        estiloCeldaDatos.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setLeftBorderColor((short) 8);
	        estiloCeldaDatos.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setRightBorderColor((short) 8);
	        estiloCeldaDatos.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setTopBorderColor((short) 8);
	        
	        celda.setCellStyle(estiloCelda);
	        celda1.setCellStyle(estiloCelda);
	        celda2.setCellStyle(estiloCelda);
	        celda3.setCellStyle(estiloCelda);
	        celda4.setCellStyle(estiloCelda);
	        celda5.setCellStyle(estiloCelda);
	        celda6.setCellStyle(estiloCelda);
	        celda7.setCellStyle(estiloCelda);
	        celda8.setCellStyle(estiloCelda);
  
	
	        
	        HSSFCellStyle estiloTitulo = libro.createCellStyle();
	        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        estiloTitulo.setFont(fuente);
	        
	        tituloCelda.setCellStyle(estiloTitulo);

	        HSSFRichTextString texto;
	        HSSFFont fuenteContenido = libro.createFont();
	        HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
	        estiloCeldaContenido.setFont(fuenteContenido);
	        //Inicio Jeffio 27/06/2017  bordes
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
	        //Fin 27/06/2017
	                    
	        for (int i = 0; i < listadoExportar.size(); i++) {
            
               fila = hoja.createRow(i + 5);
               celda = fila.createCell(0);
               texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")));
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
               celda2 = fila.createCell(2);
               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));
               hoja.autoSizeColumn(2);
               celda2.setCellStyle(estiloRecorrido);
               // Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
               celda3 = fila.createCell(3);
               celda3.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desRazonSocial").toString()));
               hoja.autoSizeColumn(3);    
               celda3.setCellStyle(estiloRecorrido);
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desProceso")));
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desTipoExpediente")));
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")));
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);              
               celda8 = fila.createCell(8);
               celda8.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desOrigen")));
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);

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
           
	         String filename="rpt_expediente_virtual_" + part + ".xls";;
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
	    	     if (log.isDebugEnabled())log.info((Object) "Error - RegistroObservacionesController.exportarExcelExpedientes");
	    	   	 log.error( ex, ex);
	             mensajeBean.setError(true);
	             mensajeBean
	                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
	                                        + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
	    	   if (log.isDebugEnabled())log.info((Object) "Fin - RegistroObservacionesController.exportarExcelExpedientes");
	       }
	       
	       return view;
	}
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelObservacionesFisca(HttpServletRequest request, HttpServletResponse response){
	       
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_02;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	     	      
	       String numExpOrigen = "";
	       String numExpVirtual = "";
	       String numRuc = "";
	       String estExpediente = "";
	       String tipoProceso = "";
	       String tipoExpediente = "";	      
	       String fechaGeneracion = "";
	       String razonSocial = "";
	       String razonSocialRuc = "";
	       try {
	    	if (log.isDebugEnabled())log.info((Object) "Inicio - RegistroObservacionesController.exportarExcelObservaciones");
	    	   
	    	listadoExportarCadena = request.getParameter("listadoObservacionesCadena");
	    	numRuc = Utils.toStr(request.getParameter("hiddenNumRuc"));
	    	razonSocial =  Utils.toStr(request.getParameter("hiddenRazonSocial"));
		    numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
		    numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtual"));	       
		    estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
		    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
		    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
		    fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
		  
		    razonSocialRuc = numRuc +" - "+razonSocial;
			
			@SuppressWarnings("unchecked")
			List<T6615ObsExpBean> listadoExportar = (List<T6615ObsExpBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
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
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,2);
			hoja.addMergedRegion(rango);
	        
			HSSFCell nomFecha = fila.createCell(3);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(4);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC:");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(razonSocialRuc);
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente:");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso:");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente:");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);	   
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda7 = fila.createCell(1);
	        subtituloCelda7.setCellValue("Fecha de Generación del Expediente:");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda8 = fila.createCell(3);
	        subtituloCelda8.setCellValue("Estado del Expediente:");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        //Inicio [gangles 22/08/2016]
	        CellRangeAddress rangoObservacion = null;
	        rangoObservacion = new CellRangeAddress(10, 10, 1, 2);
			hoja.addMergedRegion(rangoObservacion);
			 //Fin [gangles 22/08/2016]
			HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);	       
	       
	        celda.setCellValue("N°");
	        celda1.setCellValue("Observación");
	        celda3.setCellValue("Usuario Registro");
	        celda4.setCellValue("Fecha de registro");
	       	        
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
	    	    
	        HSSFCellStyle estiloCelda = libro.createCellStyle();
		       
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	       estiloCelda.setFont(fuente);
	       estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setBottomBorderColor((short) 8);
	       estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setLeftBorderColor((short) 8);
	       estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setRightBorderColor((short) 8);
	       estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	       estiloCelda.setTopBorderColor((short) 8);
	       
	       estiloCelda.setFillForegroundColor((short) 22);
	       estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       
	        HSSFCellStyle estiloCeldaDatos = libro.createCellStyle();
	        
	        estiloCeldaDatos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        estiloCeldaDatos.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	        estiloCeldaDatos.setFont(fuente);
	        estiloCeldaDatos.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setBottomBorderColor((short) 8);
	        estiloCeldaDatos.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setLeftBorderColor((short) 8);
	        estiloCeldaDatos.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setRightBorderColor((short) 8);
	        estiloCeldaDatos.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	        estiloCeldaDatos.setTopBorderColor((short) 8);
	        
	        celda.setCellStyle(estiloCelda);
	        celda1.setCellStyle(estiloCelda);
	        celda2.setCellStyle(estiloCelda);
	        celda3.setCellStyle(estiloCelda);
	        celda4.setCellStyle(estiloCelda);
	        
	        HSSFCellStyle estiloTitulo = libro.createCellStyle();
	        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        estiloTitulo.setFont(fuente);
	        
	        tituloCelda.setCellStyle(estiloTitulo);
	        subtituloCelda.setCellStyle(estiloTitulo);
	        subtituloCelda1.setCellStyle(estiloTitulo);
	        subtituloCelda2.setCellStyle(estiloTitulo);	       
	        subtituloCelda5.setCellStyle(estiloTitulo);
	        subtituloCelda6.setCellStyle(estiloTitulo);
	        subtituloCelda7.setCellStyle(estiloTitulo);
	        subtituloCelda8.setCellStyle(estiloTitulo);
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
	        for (T6615ObsExpBean t6615ObsExpBean : listadoExportar) {	        
	        	  fila = hoja.createRow(i + 11);
	               celda = fila.createCell(0);
	               texto = new HSSFRichTextString( Utils.toStr(t6615ObsExpBean.getNumOrden()));
	               celda.setCellValue(texto.toString());
	               hoja.autoSizeColumn(0);
	               celda.setCellStyle(estiloRecorrido);
	               celda1 = fila.createCell(1);
	               celda1.setCellValue(Utils.convertirUnicode(t6615ObsExpBean.getDesObservacion()));
	               hoja.autoSizeColumn(1);
	               celda1.setCellStyle(estiloRecorrido);
	               //Inicio [gangles 22/08/2016]
	   	        	CellRangeAddress rangoObservacionVal = null;
	   	        	rangoObservacionVal = new CellRangeAddress(i + 11, i + 11, 1, 2);
	   	        	hoja.addMergedRegion(rangoObservacionVal);
	   	        	celda2 = fila.createCell(2);
	   	        	celda2.setCellStyle(estiloRecorrido);
	   			 //Fin [gangles 22/08/2016]
	               celda3 = fila.createCell(3);
	               celda3.setCellValue(Utils.toStr(t6615ObsExpBean.getNomUsuGeneraObs()));
	               hoja.autoSizeColumn(3);
	               celda3.setCellStyle(estiloRecorrido);
	               celda4 = fila.createCell(4);
	               celda4.setCellValue(Utils.toStr(t6615ObsExpBean.getFecGeneracionObs()));
	               hoja.autoSizeColumn(4);
	               celda4.setCellStyle(estiloRecorrido);
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
         
	         String filename="rpt_Obsevaciones_expediente_virtual_" + part + ".xls";;
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
	    	   	 if (log.isDebugEnabled())log.info((Object) "Error - RegistroObservacionesController.exportarExcelObservaciones");
	    	   	 log.error( ex, ex);
	             mensajeBean.setError(true);
	             mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
	    	   if (log.isDebugEnabled())log.info((Object) "Fin - RegistroObservacionesController.exportarExcelObservaciones");
	       }
	       
	       return view;
	}
	
	//FIN LLRB
	
	/* Seteo - Spring - Inicio */
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
			
	public void setObservacionExpedienteVirtualService(ObservacionExpedienteVirtualService observacionExpedienteVirtualService) {
		this.observacionExpedienteVirtualService = observacionExpedienteVirtualService;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
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
	
		/* Seteo - Spring - Final */
}


