package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.IndiceExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RepresentacionImpresaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/consulta")
public class ConsultaExpedientesController extends MultiActionController {

	private static final Log log = LogFactory.getLog(ConsultaExpedientesController.class);
	@Autowired
	private ExpedienteVirtualService expedienteVirtualService; 
	@Autowired
	private ConfiguracionExpedienteService configuracionExpedienteService; 
	@Autowired
	private ParametroService paramService;
	@Autowired
	private EcmService ecmService;
	@Autowired
	private ValidarParametrosService validarParametrosService;
	@Autowired
	private SeguimientoService seguiService;
	@Autowired
	private CatalogoService catalogoService;
	@Autowired
	private IndiceExpedienteVirtualService indiceExpedienteVirtualService;
	@Autowired
	private RepresentacionImpresaService representacionImpresaService;

	
	@RequestMapping(value="/inicio")
	public ModelAndView cargarBusqExpedientes(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info("Inicio - ConsultaExpedientesController.cargarBusqExpedientes");
		
		List<T01ParamBean> listadoProcesos;
		List<T01ParamBean> listadoDependencias;
		List<T01ParamBean> listadoTipoFecha;
		List<T01ParamBean> listadoNumeroTipoExpediente;
		List<T01ParamBean> listadoEstadosExpVirtual;		
		ModelAndView modelAndView ;

		Map<String, Object> beanSeguiCC;
	    Calendar fechaActual ;
		Calendar fechaVacia ;	
		
       
		try {
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String rucUsuario=usuarioBean.getNumRUC();
			
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaValoresController.inicio");
       /*     
			//TODO - SESSION DE USUARIO                    
            if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {                    
                   String usub = request.getParameter("usub");
                   String tenc = request.getParameter("tenc");
                   usub = usub.replace(' ', '+');
                   usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);                        
            }else{                     
                   usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
            }
      */	
			 //TODO - SESSION DE USUARIO ALMACENADA
            WebUtils.setSessionAttribute(request, "usuarioBean", usuarioBean);      
				
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			listadoDependencias = configuracionExpedienteService.listarDependencias();
			listadoTipoFecha= paramService.listarTipoFecha();
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			listadoEstadosExpVirtual = configuracionExpedienteService.listarEstadosExpVirtual();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
			
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
			excepciones.put("excepcion20",AvisoConstantes.EXCEP_MODULO_04_01_020);
			
			// Inicio [jquispe 27/05/2016] Mensaje de avisos.
			HashMap<String, String> avisos = new HashMap<String, String>();
			avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_04_04_001);
			// Fin [jquispe 27/05/2016]
			
			//INICIO LLRB
			
			
			//FIN LLRB
			
			// Fecha actual
			fechaActual = Calendar.getInstance();
						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);
						
			// Seguimiento CC
			beanSeguiCC = new HashMap<String, Object>();
			beanSeguiCC.put("num_expedv",ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
			beanSeguiCC.put("fec_seguim", fechaActual.getTime());
			beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
			beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_usuinvserv",ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("num_ruc",rucUsuario);
			beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
			beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_INGRESAR_OPC_MENU);		
			beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_INGRESAR_OPC_MENU)));
			beanSeguiCC.put("fec_cons", fechaActual.getTime());
			beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
			beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
			beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
			beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
			beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
			
			seguiService.registrarSeguimiento(beanSeguiCC);
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_04_01_010);	
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			// Inicio [jquispe 27/05/2016] Mensaje de avisos.
			//modelAndView.addObject("avisos", new JsonSerializer().serialize(avisos));
			// Fin [jquispe 27/05/2016]
			modelAndView.addObject("listadoTipoFecha", new JsonSerializer().serialize(listadoTipoFecha));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoEstadosExpVirtual", new JsonSerializer().serialize(listadoEstadosExpVirtual));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
	
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.cargarBusqExpedientes");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.cargarBusqExpedientes");
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled()) log.info("Final - ConsultaExpedientesController.cargarBusqExpedientes");
		return modelAndView;
	}
	
	@RequestMapping(value="/cargarListaTiposExpediente")
	public @ResponseBody String cargarListaTiposExpediente (HttpServletRequest request, HttpServletResponse response){
	
		if (log.isDebugEnabled()) log.info("Inicio - ConsultaExpedientesController.cargarListaTiposExpediente");
		Map<String, Object> responseBody = new HashMap<String, Object>();
		List<T6624TipExpProcBean> listadoTiposExpendientes;
		try {
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			mapParametrosBusqueda.put("indConsulta", ValidaConstantes.IND_TIPO_EXPE_ASOCIADO);
			
			listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			
			responseBody.put("listadoTiposExpendientes",listadoTiposExpendientes);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.cargarListaTiposExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.cargarListaTiposExpediente");
			NDC.pop();
			NDC.remove();
		}
		if (log.isDebugEnabled()) log.info("Final - ConsultaExpedientesController.cargarListaTiposExpediente");
		return (String)new JsonSerializer().serialize(responseBody);	
	}
	
	@RequestMapping(value = "/obtenerExpedientesVirtuales", method = RequestMethod.GET)
	//@RequestMapping(value = "/obtenerExpedientesVirtuales", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public @ResponseBody String obtenerExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response){

		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - ConsultaExpedientesController.obtenerExpedientesVirtuales");
		}
		
		Map<String, Object> responseBody = new HashMap<String, Object>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		
		Date fecDesde=null;
		Date fecHasta=null;
		
		T6622SeguimBean seguimientoBean=new T6622SeguimBean() ;	
	
		Map<String, Object> beanSeguiCC;
	    Calendar fechaActual=null;
		Calendar fechaVacia=null;
		
		List<T6614ExpVirtualBean> listaExpedientesVirtualesTemp = new ArrayList<T6614ExpVirtualBean>();
	
		try {
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			DdpBean beanContribuyente;
			
			String numeroRuc = usuarioBean.getNumRUC();
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String numExp = Utils.toStr(request.getParameter("numExp"));
			String codEstado = Utils.toStr(request.getParameter("codEstado"));
			String numAcumulador = Utils.toStr(request.getParameter("numAcumulador"));
	
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
	
			// Fecha actual
			fechaActual = Calendar.getInstance();
						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			// Inicio [jquispe 27/05/2016] Flag Carga Inicial		
			boolean flagCargaInicial =  Boolean.parseBoolean(request.getParameter("flagCargaInicial"));
			// Fin [jquispe 27/05/2016]
			
	
			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
		
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);
						
			//validamos numero expediente virtual
			// Inicio [jquispe 27/05/2016] Permite obtener los registros en la carga inicial de la pagina.
			if (!Utils.isEmpty(numExp) || flagCargaInicial) {
			// Fin [jquispe 27/05/2016]
				mapParametrosBusqueda.put("numExpedOrigen", numExp);
				mapParametrosBusqueda.put("numeroRuc", numeroRuc);
				// Inicio [jquispe 27/05/2016] Envia el flag de carga inicial para obtener los registros.
				//mapParametrosBusqueda.put("flagCargaInicial", flagCargaInicial);
				// Fin [jquispe 27/05/2016]
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				// Inicio [jquispe 27/05/2016] Obtiene el numero total de expediente virtual.
				//Integer numeroExpedienteVirtual = expedienteVirtualService.obtenerNumExpedienteVirtual(mapParametrosBusqueda);				
				// Fin [jquispe 27/05/2016]
				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if(!flagCargaInicial){
						responseBody.put("msjError",AvisoConstantes.EXCEP_MODULO_04_01_016);
					}else{
						responseBody.put("listaExpedientesVirtuales",listaExpedientesVirtuales);
					}
					
					// Seguimiento CC	- RESULTADO DE LA ACCION ERROR
					beanSeguiCC = new HashMap<String, Object>();
					beanSeguiCC.put("num_expedv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
					beanSeguiCC.put("fec_seguim", fechaActual.getTime());
					beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
					beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_ruc", numeroRuc);
					beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
					beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
					beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
					beanSeguiCC.put("fec_cons", fechaActual.getTime());
					beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_ERROR);
					beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
					beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
					beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
					beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
					seguiService.registrarSeguimiento(beanSeguiCC);
					return (String)new JsonSerializer().serialize(responseBody);
				} else {
								
					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
						 seguimientoBean.setNumExpedienteVirtual(t6614ExpVirtualBean.getNumExpedienteVirtual());
						 seguimientoBean.setNumRUC(t6614ExpVirtualBean.getNumRuc());
						 responseBody.put("t6614ExpVirtualBean",t6614ExpVirtualBean);
						 break;
					}
					// Seguimiento CC	
					beanSeguiCC = new HashMap<String, Object>();
					beanSeguiCC.put("num_expedv", seguimientoBean.getNumExpedienteVirtual());
					beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
					beanSeguiCC.put("fec_seguim", fechaActual.getTime());
					beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
					beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_ruc", numeroRuc);
					beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
					beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
					beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
					beanSeguiCC.put("fec_cons", fechaActual.getTime());
					beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
					beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
					beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
					beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
					beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
					seguiService.registrarSeguimiento(beanSeguiCC);
					
					responseBody.put("listaExpedientesVirtuales",listaExpedientesVirtuales);
					// Inicio [jquispe 27/05/2016] Agrega al model el numero total de expediente virtual.
					//modelAndView.addObject("numeroExpedienteVirtual", numeroExpedienteVirtual);
					// Fin [jquispe 27/05/2016]
					return (String)new JsonSerializer().serialize(responseBody);
				}
			}
			
			// validamos número acumulador
			if (!Utils.isEmpty(numAcumulador)) {
				mapParametrosBusqueda.put("numExpedOrigen", numAcumulador);
				listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				if(listaExpedientesVirtualesTemp != null) {
					int num = listaExpedientesVirtualesTemp.size(); 
					
					listaExpedientesVirtuales.addAll(listaExpedientesVirtualesTemp); 
					mapParametrosBusqueda.put("numExpedOrigen", null);
					mapParametrosBusqueda.put("numAcumulador", numAcumulador);
					listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
					
					if(listaExpedientesVirtualesTemp != null) {
						for(T6614ExpVirtualBean bean : listaExpedientesVirtualesTemp) {
							bean.setNumOrden(bean.getNumOrden() + num);
						}					
	
						listaExpedientesVirtuales.addAll(listaExpedientesVirtualesTemp);
					}
				}
	
				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					responseBody.put("msjError",AvisoConstantes.EXCEP_MODULO_04_01_016);
					// Seguimiento CC	- RESULTADO DE LA ACCION ERROR
					beanSeguiCC = new HashMap<String, Object>();
					beanSeguiCC.put("num_expedv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
					beanSeguiCC.put("fec_seguim", fechaActual.getTime());
					beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
					beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_ruc", numeroRuc);
					beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
					beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
					beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
					beanSeguiCC.put("fec_cons", fechaActual.getTime());
					beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_ERROR);
					beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
					beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
					beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
					beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
					seguiService.registrarSeguimiento(beanSeguiCC);
					return (String)new JsonSerializer().serialize(responseBody);
				} else {
					int cont=1;			
					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
						 seguimientoBean.setNumExpedienteVirtual(t6614ExpVirtualBean.getNumExpedienteVirtual());
						 seguimientoBean.setNumRUC(t6614ExpVirtualBean.getNumRuc());
						 t6614ExpVirtualBean.setNumOrden(cont);
						 cont++;
						 responseBody.put("t6614ExpVirtualBean",t6614ExpVirtualBean);
						 break;
					}
					// Seguimiento CC	
					beanSeguiCC = new HashMap<String, Object>();
					beanSeguiCC.put("num_expedv", seguimientoBean.getNumExpedienteVirtual());
					beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
					beanSeguiCC.put("fec_seguim", fechaActual.getTime());
					beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
					beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_ruc", numeroRuc);
					beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
					beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
					beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
					beanSeguiCC.put("fec_cons", fechaActual.getTime());
					beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
					beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
					beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
					beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
					beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
					beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
					seguiService.registrarSeguimiento(beanSeguiCC);
					
					responseBody.put("listaExpedientesVirtuales",listaExpedientesVirtuales);
					return (String)new JsonSerializer().serialize(responseBody);
				}
			}
			
			mapParametrosBusqueda.put("fecDocIni", fecDesde);
			mapParametrosBusqueda.put("fecDocFin", fecHasta);
			
			// validamos el codigoProceso
			if (!Utils.isEmpty(codProceso)) {
				mapParametrosBusqueda.put("codProceso", codProceso);
			}
			// validamos el codigoTipoExpediente
			if (!Utils.isEmpty(codTipExpediente)) {
				mapParametrosBusqueda.put("codTipExpediente", codTipExpediente);
			}
			
			// validamos estado expediente virtual
			if (!Utils.isEmpty(codEstado)) {
				mapParametrosBusqueda.put("codEstado", codEstado);
			}
			
			mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			// Inicio [jquispe 27/05/2016] Obtiene el numero total de expediente virtual.
			//Integer numeroExpedienteVirtual = this.expedienteVirtualService.obtenerNumExpedienteVirtual(mapParametrosBusqueda);
			// Fin [jquispe 27/05/2016]
	
			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				responseBody.put("msjError",AvisoConstantes.EXCEP_MODULO_04_01_012);
				// Seguimiento CC	- RESULTADO DE LA ACCION ERROR
				beanSeguiCC = new HashMap<String, Object>();
				beanSeguiCC.put("num_expedv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
				beanSeguiCC.put("fec_seguim", fechaActual.getTime());
				beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
				beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("num_ruc", numeroRuc);
				beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
				beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
				beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
				beanSeguiCC.put("fec_cons", fechaActual.getTime());
				beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_ERROR);
				beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
				beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
				beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
				beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);			
				seguiService.registrarSeguimiento(beanSeguiCC);
				
			} else {
				 
				// Fecha actual
				fechaActual = Calendar.getInstance();
							
				//Fecha fin
				fechaVacia = Calendar.getInstance();
				fechaVacia.set(1, 0, 1, 0, 0, 0);
				
				// Seguimiento CC
				beanSeguiCC = new HashMap<String, Object>();
				beanSeguiCC.put("num_expedv",ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
				beanSeguiCC.put("fec_seguim", fechaActual.getTime());
				beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
				beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("num_ruc",numeroRuc);
				beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
				beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_CONSULTAR);
				beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_CONSULTAR)));
				beanSeguiCC.put("fec_cons",fechaActual.getTime());
				beanSeguiCC.put("cod_respacc",ValidaConstantes.RESPUESTA_ACCION_OK);
				beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
				beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
				beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
				beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				
				seguiService.registrarSeguimiento(beanSeguiCC);
				responseBody.put("listaExpedientesVirtuales",listaExpedientesVirtuales);
				// Inicio [jquispe 27/05/2016] Agrega al model el numero total de expediente virtual.
				//modelAndView.addObject("numeroExpedienteVirtual", numeroExpedienteVirtual);	
				// Fin [jquispe 27/05/2016]
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaExpedientesController.obtenerExpedientesVirtuales");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaExpedientesController.obtenerExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}
	
		return (String)new JsonSerializer().serialize(responseBody);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/exportarExcelExpedientes")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
       	  
		String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Map<String, Object> beanSeguiCC = null;
		Calendar fechaActualCalendar = null;	
		Calendar fechaVacia = null;
		//Inicio [gangles 22/08/2016]
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		//Fin [gangles 22/08/2016]
		try {
    	   
			if (log.isDebugEnabled()) log.info("Inicio - ConsultaExpedientesController.exportarExcelExpedientes");
    	   
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String rucUsuario=usuarioBean.getNumRUC();
			
    		listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
    		log.debug("listadoExportarCadena: " + listadoExportarCadena);
   		
    		@SuppressWarnings("unchecked")
    		List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
    		
    		// Fecha actual
    		fechaActualCalendar = Calendar.getInstance();						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
     
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);
			
	    	response.setContentType("application/vnd.ms-excel");
	        HSSFWorkbook libro = new HSSFWorkbook();
	        HSSFSheet hoja = libro.createSheet("Hoja 1");
	    
	        hoja.setColumnWidth(0, 2500);
	        hoja.setColumnWidth(1, 2500);
	        hoja.setColumnWidth(2, 2500);
	        // Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
	        hoja.setColumnWidth(3, 4500);
	        hoja.setColumnWidth(4, 3500);
	        hoja.setColumnWidth(5, 4500);
	        hoja.setColumnWidth(6, 4800);
	        hoja.setColumnWidth(7, 16000);
	        // Fin [jquispe 27/05/2016]
	        HSSFRow fila = hoja.createRow(1);
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        hoja.addMergedRegion(new Region(1,(short)0,1,(short)4));
	              
	        //Inicio [gangles 22/08/2016]
	        fila = hoja.createRow(2);
	        HSSFCell subtituloCelda3 = fila.createCell(1);
			subtituloCelda3.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(2);
			contenido3.setCellValue(fecImpresion);
			//Fin [gangles 22/08/2016]
			
	        fila = hoja.createRow(4);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Nº Expediente Origen");
	        celda2.setCellValue("Nº de Expediente Acumulador");
	        // Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
	        celda3.setCellValue("Indicador de Acumulación");
	        celda4.setCellValue("Proceso");
	        celda5.setCellValue("Tipo de Expediente");
	        celda6.setCellValue("Estado Expediente");
	        celda7.setCellValue("Fecha del Expediente");
	        // Fin [jquispe 27/05/2016]
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
	        ssheet.autoSizeColumn(10);
	        //inicio JEFFIO [13/03/2017]
	        ssheet.autoSizeColumn(11);
	        ssheet.autoSizeColumn(12);
	        //inicio JEFFIO [13/03/2017]
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
	        celda6.setCellStyle(estiloCelda);
	        // Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
	        celda7.setCellStyle(estiloCelda);
	        // Fin [jquispe 27/05/2016]
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
	        for (int i = 0; i < listadoExportar.size(); i++) {
	        	log.debug("en el ciclo for");
				fila = hoja.createRow(i + 5);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(((T6614ExpVirtualBean) listadoExportar.get(i)).getNumOrden().toString().trim());
			
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);

				celda1 = fila.createCell(1);
				celda1.setCellValue(((T6614ExpVirtualBean)listadoExportar.get(i)).getNumExpedienteOrigen());
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);

				String numAcumulador = ((T6614ExpVirtualBean)listadoExportar.get(i)).getNumExpedienteAcumulador();

				numAcumulador = numAcumulador.equals("0") ? "" : numAcumulador; 

				celda2 = fila.createCell(2);
				celda2.setCellValue(numAcumulador);
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);

				celda3 = fila.createCell(3);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
				celda3.setCellValue(((T6614ExpVirtualBean)listadoExportar.get(i)).getDesIndicadorAcumulado());
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);

				celda4 = fila.createCell(4);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
				celda4.setCellValue(Utils.convertirUnicode(((T6614ExpVirtualBean)listadoExportar.get(i)).getDesProceso()));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);

				celda5 = fila.createCell(5);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
				celda5.setCellValue(Utils.convertirUnicode(((T6614ExpVirtualBean)listadoExportar.get(i)).getDesTipoExpediente()));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);

				celda6 = fila.createCell(6);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
				celda6.setCellValue(((T6614ExpVirtualBean)listadoExportar.get(i)).getDesEstado());
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(6);
				celda6.setCellStyle(estiloRecorrido);

				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion
				//[INICIO - ETITO] Formato de fecha
				celda7 = fila.createCell(7);
				Date curDate = ((T6614ExpVirtualBean)listadoExportar.get(i)).getFechaDocumentoOrigen();
		        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		        String DateToStr = format.format(curDate);
		        celda7.setCellValue(DateToStr);
				//FIN - ETITO
				
				hoja.autoSizeColumn(7);
				celda7.setCellStyle(estiloRecorrido);
				// Fin [jquispe 27/05/2016]
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

			String filename="rpt_consulta_expedientes_" + part + ".xls";;
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

	     	// Seguimiento CC
				
			beanSeguiCC = new HashMap<String, Object>();
			beanSeguiCC.put("num_expedv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
			beanSeguiCC.put("fec_seguim", fechaActualCalendar.getTime());
			beanSeguiCC.put("fec_invserv", fechaVacia.getTime());
			beanSeguiCC.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("num_ruc", rucUsuario);
			beanSeguiCC.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
			beanSeguiCC.put("cod_accion", ValidaConstantes.ACCION_INTERNET_EXPORTAREXCEL);
			beanSeguiCC.put("des_datcons",Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_EXPORTAREXCEL)));
			beanSeguiCC.put("fec_cons", fechaActualCalendar.getTime());
			beanSeguiCC.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
			beanSeguiCC.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
			beanSeguiCC.put("fec_cambest", fechaVacia.getTime());
			beanSeguiCC.put("fec_cambeta", fechaVacia.getTime());
			beanSeguiCC.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiCC.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
			seguiService.registrarSeguimiento(beanSeguiCC);

	        inputStream.close();
	        outStream.close(); 
	         
	    } catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaExpedientesController.exportarExcelExpedientes");
			}

			log.error(ex, ex);
			mensajeBean.setError(true);
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar "
										+ ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaExpedientesController.exportarExcelExpedientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return view;
	}


	//@RequestMapping(value = { "/cargarDocumentosExpOrigen" }, method = { RequestMethod.GET })
	@RequestMapping(value="/cargarDocumentosExpOrigen")
	public @ResponseBody String cargarDocumentosExpOrigen(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaExpedientesController.cargarDocumentosExpOrigen");
		
		HashMap<String, Object> mapParametrosBusqueda;
		List<T6613DocExpVirtBean> listaDocumentos=new ArrayList<T6613DocExpVirtBean>() ;		
		List<T6614ExpVirtualBean> listaExpedientesVirtuales=new ArrayList<T6614ExpVirtualBean>() ;	
		T6622SeguimBean seguimientoBean=new T6622SeguimBean() ;	
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";	
		Map<String, Object> beanSeguiWS = null;
	    Calendar fechaActual = null;
		Calendar fechaVacia = null;	
		List<String> listaExpedientes = new ArrayList<String>();
		String indicadorAcumulador="";
		
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador=new ArrayList<T6614ExpVirtualBean>();
		      
		try {
			
			String numExp = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			String numExpOrigen = ""; 
				
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExp);		
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);		

			T6614ExpVirtualBean t6614ExpVirtualBean = listaExpedientesVirtuales.get(0);
			//[PAS20191U210500076][OAC] VERIFICAR SI EL EXPEDIENTE ESTÁ ABIERTO, SÓLO PARA EL TIPO DE EXP OCP
			if (expedienteVirtualService.expAbiertoByTipExp(t6614ExpVirtualBean.getCodTipoExpediente(), t6614ExpVirtualBean.getCodEstado())) {
				HashMap<String, String> excepciones = new HashMap<String, String>();
				excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_022);
				responseBody.put("excepciones", new JsonSerializer().serialize(excepciones));
				log.debug( "Final - ConsultaExpedientesController.cargarDocumentosExpOrigen");
				return (String)new JsonSerializer().serialize(responseBody);
			}
			
			// Fecha actual
			fechaActual = Calendar.getInstance();
						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);		
		
			if (t6614ExpVirtualBean != null) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				 numExpOrigen = t6614ExpVirtualBean.getNumExpedienteOrigen().trim(); 
				 seguimientoBean.setCodTipoSeguim(ValidaConstantes.IND_TIP_SEG_CC);
				 seguimientoBean.setNumExpedienteVirtual(t6614ExpVirtualBean.getNumExpedienteVirtual());
				 seguimientoBean.setNumRUC(t6614ExpVirtualBean.getNumRuc());
				 seguimientoBean.setCodOpcionConsulta(ValidaConstantes.SEPARADOR_GUION);
				 listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				 indicadorAcumulador=t6614ExpVirtualBean.getIndicadorAcumulado();
				 
				 responseBody.put("fechaDocOrigen",new JsonSerializer().serialize(fechaDocOrigen));
				 responseBody.put("fechaRegExpediente",new JsonSerializer().serialize(fechaRegExpediente));
				 responseBody.put("razonSocial",new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				 responseBody.put("t6614ExpVirtualBean",new JsonSerializer().serialize(t6614ExpVirtualBean));
				 responseBody.put("seguimientoBean",new JsonSerializer().serialize(seguimientoBean));		 
			}	
			
			// Seguimiento WS
			beanSeguiWS = new HashMap<String, Object>();
			beanSeguiWS.put("num_expedv", seguimientoBean.getNumExpedienteVirtual());
			beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
			beanSeguiWS.put("fec_seguim", fechaActual.getTime());
			beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
			beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_ruc", seguimientoBean.getNumRUC());
			beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
			beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTERNET_VER_DOCUMENTOS);
			beanSeguiWS.put("des_datcons", Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_VER_DOCUMENTOS)));
			beanSeguiWS.put("fec_cons",  fechaActual.getTime());
			beanSeguiWS.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
			beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
			beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
			beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
			beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
			
			seguiService.registrarSeguimiento(beanSeguiWS);	
			
			if(Utils.equiv(indicadorAcumulador, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)){
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedienteAcumulador", numExpOrigen);
				listaExpedientesdelAcumulador=expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);	
				
				for (T6614ExpVirtualBean t6614ExpVirtualAcuBean : listaExpedientesdelAcumulador) {
					listaExpedientes.add(t6614ExpVirtualAcuBean.getNumExpedienteVirtual());
				}
				
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyenteAcumulado(mapParametrosBusqueda);
			}else{				
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedVirtual", numExp);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyente(mapParametrosBusqueda);	
			}
	
			for (T6613DocExpVirtBean t6613DocExpVirtBean : listaDocumentos) {
				String tipoDocumento=t6613DocExpVirtBean.getCodTipDoc() +ValidaConstantes.SEPARADOR_ESPACIO + ValidaConstantes.SEPARADOR_GUION +ValidaConstantes.SEPARADOR_ESPACIO + t6613DocExpVirtBean.getDesTipdoc();
				t6613DocExpVirtBean.setDesTipdoc(tipoDocumento);
				
			}
			log.debug("listaDocumentos.isEmpty()" + listaDocumentos.isEmpty());
			log.debug("listaDocumentos.size()" + listaDocumentos.size());
			if (listaDocumentos.isEmpty()) {
				HashMap<String, String> excepciones = new HashMap<String, String>();
				excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_017);
				responseBody.put("excepciones", new JsonSerializer().serialize(excepciones));
				log.debug( "Final - ConsultaExpedientesController.cargarDocumentosExpOrigen");
				return (String)new JsonSerializer().serialize(responseBody);
			}
			
			responseBody.put("listaDocumentos",new JsonSerializer().serialize(listaDocumentos));
			        
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaExpedientesController.cargarDocumentosExpOrigen");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);
		} finally {
	
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaExpedientesController.cargarDocumentosExpOrigen");
			}
			NDC.pop();
			NDC.remove();
		}
		
		log.debug("responseBody: " + responseBody);
		return (String)new JsonSerializer().serialize(responseBody);
	
	}
	
	
//  @RequestMapping(value = { "/verDatosExpedienteVirtualPdf" }, method = { RequestMethod.GET })
	@RequestMapping(value = "/verDatosExpedienteVirtualPdf", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void verDatosExpedienteVirtualPdf(HttpServletRequest request,HttpServletResponse response){
		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - ConsultaExpedientesController.verDatosExpedienteVirtualPdf");
		}
		
		String indicadorAcumulador = "";
		HashMap<String, Object> mapParametrosBusqueda;		
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador = new ArrayList<T6614ExpVirtualBean>();
		List<T6613DocExpVirtBean> listaDocumentos=new ArrayList<T6613DocExpVirtBean>() ;
		List<String> listaExpedientes = new ArrayList<String>();
		T6614ExpVirtualBean datosExpBean = null;
		
		Map<String, Object> parametros = null;		
		List<Map<String, Object>> listadoDocumentosExpediente= new ArrayList<Map<String,Object>>();		
		String numExpedienteVirtual = null;
		String razonSocial = null;
		String proceso = null;
		String desTipoExpediente = null;	
		String numRuc = null;
		String codTipoExpediente = null;
		String fecRegistroExpediente = null; 
		
		try{
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");			
			
			String numExpOrigen = ""; 
			numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			 
            mapParametrosBusqueda = new HashMap<String, Object>();				
			
			mapParametrosBusqueda.put("numExpedienteVirtual", numExpedienteVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			datosExpBean = expedienteVirtualService.obtenerDatosExpediente(mapParametrosBusqueda);
							
			indicadorAcumulador = datosExpBean.getIndicadorAcumulado();
			numExpOrigen = datosExpBean.getNumExpedienteOrigen().trim();				
			listaExpedientes.add(datosExpBean.getNumExpedienteVirtual());			
			razonSocial = datosExpBean.getDesRazonSocial();
			proceso = datosExpBean.getDesProceso();
			desTipoExpediente = datosExpBean.getDesTipoExpediente();
			numRuc = datosExpBean.getNumRuc();
			codTipoExpediente = datosExpBean.getCodTipoExpediente();
			
			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			fecRegistroExpediente = formatoFechaExporta.format(datosExpBean.getFecRegistro());
						
			if(Utils.equiv(indicadorAcumulador, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)){
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedienteAcumulador", numExpOrigen);
				listaExpedientesdelAcumulador=expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);	
				
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
					listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				}
				
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyenteAcumulado(mapParametrosBusqueda);
			}else{
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyente(mapParametrosBusqueda);	
			}
			
			if(!Utils.isEmpty(listaDocumentos)){			
				for(T6613DocExpVirtBean t6613DocExpVirtBean:listaDocumentos){
					Map<String, Object> itemResult = new HashMap<String, Object>();
					itemResult.put("fecDocu", t6613DocExpVirtBean.getFecDoc());
					itemResult.put("codTipDoc", t6613DocExpVirtBean.getCodTipDoc());
					itemResult.put("desTipdoc2", t6613DocExpVirtBean.getDesTipdoc());
					itemResult.put("numDoc", t6613DocExpVirtBean.getNumDoc());
					itemResult.put("descArchivo", t6613DocExpVirtBean.getDescripcionArchivo());
					itemResult.put("desOrigen", t6613DocExpVirtBean.getDesOrigenDoc());
					itemResult.put("nroRequerim", t6613DocExpVirtBean.getNumRequerimiento());
					
					listadoDocumentosExpediente.add(itemResult);
				}			
			}		
			
			//Inicio [jjurado 08/08/2016]
			Map<String, Object> mapDocExpVirt = new HashMap<String, Object>();
			mapDocExpVirt.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			mapDocExpVirt.put("numExpeDv", numExpedienteVirtual);
			T6613DocExpVirtBean documentoOrigen = expedienteVirtualService.obtenerDatosDocumentoEstado(mapDocExpVirt);
			//Fin [jjurado 08/08/2016]
			
			parametros = new HashMap<String, Object>();
			parametros.put("listadoDocumentosExpediente", listadoDocumentosExpediente);
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			parametros.put("razonSocial", razonSocial);
			parametros.put("proceso", proceso);
			parametros.put("desTipoExpediente", desTipoExpediente);
			parametros.put("numRuc", numRuc);
			parametros.put("codTipoExpediente", codTipoExpediente);
			parametros.put("fecRegistroExpediente", fecRegistroExpediente);
			parametros.put("numRucUsuario", usuarioBean.getNumRUC());
			parametros.put("codigoUsuario", usuarioBean.getNroRegistro());
			parametros.put("codDep", usuarioBean.getCodDepend());
			//Inicio nchavez 07/07/2016
			parametros.put("numExpedienteOrigen", datosExpBean.getNumExpedienteOrigen());//[jjurado 08/08/2016]
			parametros.put("fecEmi", formatoFechaExporta.format(documentoOrigen.getFecDoc()));// [jjurado 08/07/2016]
			//Fin nchavez 07/07/2016
			
			byte[] archivoPdf = indiceExpedienteVirtualService.generarIndiceArchivoPdf(parametros);
			OutputStream outStream = response.getOutputStream();
			response.setContentLength(archivoPdf.length);
		    response.setContentType("application/pdf");
		    outStream.write(archivoPdf);
		    outStream.close();
		    
			/*
			byte[] archivoPdf = indiceExpedienteVirtualService.generarIndiceArchivoPdf(parametros);
			OutputStream outStream = response.getOutputStream();
			String headerKey = "Content-Disposition";
		    String headerValue = String.format("attachment; filename=\"%s\"","nombre_archivo.pdf");
		    response.setHeader(headerKey, headerValue);
		    outStream = response.getOutputStream();
		    outStream.write(archivoPdf);
		    */  
		    
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaExpedientesController.verDatosExpedienteVirtualPdf");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaExpedientesController.verDatosExpedienteVirtualPdf");
			}
			
			NDC.pop();
			NDC.remove();
		}
	}
	//Fin [jquispe 30/05/2016]
	
	//Inicio [jquispe 13/06/2016] Verifica si la lista de documentos del expediente contiene algun documento.
	@RequestMapping(value="/verificarListaDocumentoExpediente")
	public @ResponseBody String verificarListaDocumentoExpediente(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - ConsultaExpedientesController.verificarListaDocumentoExpediente");
		}
		
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		String indicadorAcumulador = "";
		HashMap<String, Object> mapParametrosBusqueda;		
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador = new ArrayList<T6614ExpVirtualBean>();
		List<T6613DocExpVirtBean> listaDocumentos=new ArrayList<T6613DocExpVirtBean>() ;
		List<String> listaExpedientes = new ArrayList<String>();
		T6614ExpVirtualBean datosExpBean = null;
		String numExpedienteVirtual = null;
		boolean esVacioListaDocumentos = false; 
		
		try{
			
			String numExpOrigen = ""; 
			numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			
            mapParametrosBusqueda = new HashMap<String, Object>();					
			mapParametrosBusqueda.put("numExpedienteVirtual", numExpedienteVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			datosExpBean = expedienteVirtualService.obtenerDatosExpediente(mapParametrosBusqueda);
							
			indicadorAcumulador = datosExpBean.getIndicadorAcumulado();
			numExpOrigen = datosExpBean.getNumExpedienteOrigen().trim();				
			listaExpedientes.add(datosExpBean.getNumExpedienteVirtual());		
			
						
			if(Utils.equiv(indicadorAcumulador, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)){
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedienteAcumulador", numExpOrigen);
				listaExpedientesdelAcumulador=expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);	
				
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
					listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				}
				
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyenteAcumulado(mapParametrosBusqueda);
			}else{
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);
				mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
				
				listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyente(mapParametrosBusqueda);	
			}
			
			if(Utils.isEmpty(listaDocumentos)){
				esVacioListaDocumentos = true;
			}
			
			responseBody.put("esVacioListaDocumentos", esVacioListaDocumentos);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaExpedientesController.verificarListaDocumentoExpediente");
			}
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaExpedientesController.verificarListaDocumentoExpediente");
			}
			
			NDC.pop();
			NDC.remove();
		}
		
		return (String)new JsonSerializer().serialize(responseBody);
		
	}
	//Fin [jquispe 13/06/2016]
    
	@RequestMapping(value = { "/cargarDocumentosExpOrigenAcum" }, method = { RequestMethod.GET })
	public @ResponseBody String cargarDocumentosExpOrigenAcum(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaExpedientesController.cargarDocumentosExpOrigenAcum");
		
		HashMap<String, Object> mapParametrosBusqueda;
		List<T6613DocExpVirtBean> listaDocumentos=new ArrayList<T6613DocExpVirtBean>() ;		
		List<T6614ExpVirtualBean> listaExpedientesVirtuales=new ArrayList<T6614ExpVirtualBean>() ;	
		T6622SeguimBean seguimientoBean=new T6622SeguimBean() ;	
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";	
		Map<String, Object> beanSeguiWS = null;
	    Calendar fechaActual = null;
		Calendar fechaVacia = null;	
		List<String> listaExpedientes = new ArrayList<String>();
		String indicadorAcumulador="";
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador=new ArrayList<T6614ExpVirtualBean>();
	      
		try {
			String numAcumulador = Utils.toStr(request.getParameter("numAcumulador"));
			numAcumulador = numAcumulador.trim();
				
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedOrigen", numAcumulador);		
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);		
			
			// Fecha actual
			fechaActual = Calendar.getInstance();
						
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);		
			String numExpedienteVirtual = "";
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 numExpedienteVirtual = t6614ExpVirtualBean.getNumExpedienteVirtual().trim();
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				 seguimientoBean.setCodTipoSeguim(ValidaConstantes.IND_TIP_SEG_CC);
				 seguimientoBean.setNumExpedienteVirtual(t6614ExpVirtualBean.getNumExpedienteVirtual());
				 seguimientoBean.setNumRUC(t6614ExpVirtualBean.getNumRuc());
				 seguimientoBean.setCodOpcionConsulta(ValidaConstantes.SEPARADOR_GUION);
				 listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				 indicadorAcumulador=t6614ExpVirtualBean.getIndicadorAcumulado();
				 
				 responseBody.put("fechaDocOrigen",new JsonSerializer().serialize(fechaDocOrigen));
				 responseBody.put("fechaRegExpediente",new JsonSerializer().serialize(fechaRegExpediente));
				 responseBody.put("razonSocial",new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				 responseBody.put("t6614ExpVirtualBean",new JsonSerializer().serialize(t6614ExpVirtualBean));
				 responseBody.put("seguimientoBean",new JsonSerializer().serialize(seguimientoBean));		 
				 break;
			}	
			
			// Seguimiento WS
			beanSeguiWS = new HashMap<String, Object>();
			beanSeguiWS.put("num_expedv", seguimientoBean.getNumExpedienteVirtual());
			beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
			beanSeguiWS.put("fec_seguim", fechaActual.getTime());
			beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
			beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_ruc", seguimientoBean.getNumRUC());
			beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
			beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTERNET_VER_DOCUMENTOS);
			beanSeguiWS.put("des_datcons", Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_VER_DOCUMENTOS)));
			beanSeguiWS.put("fec_cons",  fechaActual.getTime());
			beanSeguiWS.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
			beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
			beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
			beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
			beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
			beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);					
			seguiService.registrarSeguimiento(beanSeguiWS);	
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_01_017);
			responseBody.put("excepciones", new JsonSerializer().serialize(excepciones));
			
			mapParametrosBusqueda = new HashMap<String, Object>();			
			mapParametrosBusqueda.put("numExpedienteAcumulador", numAcumulador);

			listaExpedientesdelAcumulador = expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);			
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
				listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
			}
			
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
			mapParametrosBusqueda.put("indVisContribuyente",  ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);			
			listaDocumentos = expedienteVirtualService.obtenerListaDocVisContribuyenteAcumulado(mapParametrosBusqueda);
			
			responseBody.put("listaDocumentos",new JsonSerializer().serialize(listaDocumentos));
					
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaExpedientesController.cargarDocumentosExpOrigenAcum");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);
		} finally {
	
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaExpedientesController.cargarDocumentosExpOrigenAcum");
			}
			NDC.pop();
			NDC.remove();
		}
		return (String)new JsonSerializer().serialize(responseBody);
	
	}

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelDocumentos(HttpServletRequest request, HttpServletResponse response){
	    
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_06;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	     	      
	       String numExpOrigen;
	       String numExpVirtual;
	       String estExpediente;
	       String tipoProceso;
	       String tipoExpediente ;
	       String fechaGeneracion;

			Map<String, Object> beanSeguiWS = null;
		    Calendar fechaActualCalendar = null;
			Calendar fechaVacia = null;
			//Inicio [gangles 22/08/2016]
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    Date fechaActual = new Date();
		    String fecImpresion = sdf.format(fechaActual);
		    //Fin [gangles 22/08/2016]
	       
	       try {
	    		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
	    		String numeroRuc = usuarioBean.getNumRUC();
		    	listadoExportarCadena = Utils.toStr(request.getParameter("listadoDocumentosCadena"));
		    	numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
			    estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
			    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
			    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
			    fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
			    numExpVirtual =Utils.toStr(request.getParameter("hiddenNumExpVirtualDoc"));
			
			    Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);	
				
			@SuppressWarnings("unchecked")
			List<T6613DocExpVirtBean> listadoExportar = (List<T6613DocExpVirtBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
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
	        hoja.setColumnWidth(7, 4500);
	        hoja.setColumnWidth(8, 4500);
	        hoja.setColumnWidth(9, 4500);
	        // Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
	        //hoja.setColumnWidth(10, 4500);
	        // Fin [jquispe 31/05/2016]
	        hoja.setColumnWidth(10, 4500);
	        hoja.setColumnWidth(11, 5200);       
	        
	        HSSFRow fila = hoja.createRow(1);
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        hoja.addMergedRegion(new Region(1,(short)0,1,(short)3));
	        //Inicio [gangles 22/08/2016]
	        fila = hoja.createRow(2);
	        HSSFCell subtituloCelda3 = fila.createCell(1);
			subtituloCelda3.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(2);
			contenido3.setCellValue(fecImpresion);
			 //Fin [gangles 22/08/2016]
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
	        //Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	        //HSSFCell celda10 = fila.createCell(10);	        
	        HSSFCell celda11 = fila.createCell(10);
	        HSSFCell celda12 = fila.createCell(11);
	        // Fin [jquispe 31/05/2016]
	        // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	        HSSFCell celda13 = fila.createCell(12);
	        // Fin [jquispe 31/05/2016]
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Nro. Expediente Origen");
	        celda2.setCellValue("Proceso");
	        celda3.setCellValue("Tipo expediente");
	        celda4.setCellValue("Estado expediente");
	        celda5.setCellValue("Fecha del expediente");
	        celda6.setCellValue("Fecha del documento");
	        // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	        celda7.setCellValue("Fecha de emisión del documento");	        
	        celda8.setCellValue("Nombre del documento");
	        celda9.setCellValue("Nro. del documento ");
	        // Fin [jquispe 31/05/2016]
	        // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	        //celda10.setCellValue("Nombre del archivo");	        
	        celda11.setCellValue("Nro. de requerimiento");
	        celda12.setCellValue("Fecha Registro");
	        // Fin [jquispe 31/05/2016]
	        // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	        celda13.setCellValue("Nº de Expediente Acumulador");
	        // Fin [jquispe 31/05/2016]
	        
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
	        // Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
	        //ssheet.autoSizeColumn(10);
	        // Fin [jquispe 31/05/2016]
	        ssheet.autoSizeColumn(10);
	        ssheet.autoSizeColumn(11);
	        ssheet.autoSizeColumn(12);
	        
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
	        celda6.setCellStyle(estiloCelda);
	        celda7.setCellStyle(estiloCelda);
	        celda8.setCellStyle(estiloCelda);
	        celda9.setCellStyle(estiloCelda);
	        // Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
	        //celda10.setCellStyle(estiloCelda);
	        // Fin [jquispe 31/05/2016]
	        celda11.setCellStyle(estiloCelda);
	        celda12.setCellStyle(estiloCelda);
	        // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	        celda13.setCellStyle(estiloCelda);
	        // Fin [jquispe 31/05/2016]
	        
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
	        	  fila = hoja.createRow(i + 5);
	               celda = fila.createCell(0);
	               texto = new HSSFRichTextString(Integer.toString(i+1));
	               celda.setCellValue(texto.toString());
	               hoja.autoSizeColumn(0);
	               celda.setCellStyle(estiloRecorrido);
	               celda1 = fila.createCell(1);
	               celda1.setCellValue(numExpOrigen);
	               hoja.autoSizeColumn(1);
	               celda1.setCellStyle(estiloRecorrido);
	               celda2 = fila.createCell(2);
	               celda2.setCellValue(tipoProceso);
	               hoja.autoSizeColumn(2);
	               celda2.setCellStyle(estiloRecorrido);
	               celda3 = fila.createCell(3);
	               celda3.setCellValue(tipoExpediente);
	               hoja.autoSizeColumn(3);
	               celda3.setCellStyle(estiloRecorrido);
	               celda4 = fila.createCell(4);
	               celda4.setCellValue(estExpediente);
	               hoja.autoSizeColumn(4);
	               celda4.setCellStyle(estiloRecorrido);
	               celda5 = fila.createCell(5);
	               celda5.setCellValue(fechaGeneracion);
	               hoja.autoSizeColumn(5);
	               celda5.setCellStyle(estiloRecorrido);
	               celda6 = fila.createCell(6);
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	               celda6.setCellValue(Utils.toStr(t6613DocExpVirtBean.getFechaRegistroDoc()));
	               hoja.autoSizeColumn(6);
	               // Fin [jquispe 31/05/2016]
	               celda6.setCellStyle(estiloRecorrido);
	               celda7 = fila.createCell(7);
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	               celda7.setCellValue(Utils.toStr(t6613DocExpVirtBean.getFechaDocumento()));
	               hoja.autoSizeColumn(7);
	               // Fin [jquispe 31/05/2016]
	               celda7.setCellStyle(estiloRecorrido);	               
	               celda8 = fila.createCell(8);
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	               celda8.setCellValue(Utils.convertirUnicode(t6613DocExpVirtBean.getDesTipdoc()));
	               hoja.autoSizeColumn(8);
	               // Fin [jquispe 31/05/2016]
	               celda8.setCellStyle(estiloRecorrido);
	               celda9 = fila.createCell(9);
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	               celda9.setCellValue(Utils.toStr(t6613DocExpVirtBean.getNumDoc()));
	               hoja.autoSizeColumn(9);
	               // Fin [jquispe 31/05/2016]
	               celda9.setCellStyle(estiloRecorrido);
	               // Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
	               /*
	               celda10 = fila.createCell(10);
	               celda10.setCellValue(Utils.toStr(t6613DocExpVirtBean.getDescripcionArchivo()));
	               hoja.autoSizeColumn(10);
	               celda10.setCellStyle(estiloRecorrido);
	               */
	               // Fin [jquispe 31/05/2016]
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	               celda11 = fila.createCell(10);
	               celda11.setCellValue(Utils.toStr(t6613DocExpVirtBean.getNroRequerim()));
	               hoja.autoSizeColumn(10);
	               celda11.setCellStyle(estiloRecorrido);
	               // Fin [jquispe 31/05/2016]
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento y eliminar el nombre del archivo.
	               celda12 = fila.createCell(11);
	               celda12.setCellValue(Utils.toStr(t6613DocExpVirtBean.getFechaRegistroDoc()));
	               hoja.autoSizeColumn(11);
	               celda12.setCellStyle(estiloRecorrido);
	               // Fin [jquispe 31/05/2016]
	               // Inicio [jquispe 31/05/2016] Modificado para agregar la fecha de emision del documento.
	               celda13 = fila.createCell(12);
	               celda13.setCellValue(t6613DocExpVirtBean.getNumAcumuladorVista().trim());              
	               hoja.autoSizeColumn(12);	               
	               celda13.setCellStyle(estiloRecorrido);
	               // Fin [jquispe 31/05/2016]
	               
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
           
	         String filename="rpt_documentos_expediente_virtual_" + part + ".xls";;
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
 			beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
 			beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTERNET_EXPORTAREXCEL);
 			beanSeguiWS.put("des_datcons", Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_EXPORTAREXCEL)));
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

	@RequestMapping(value = "/descargarArchivoDoc", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivoDoc(HttpServletRequest request, HttpServletResponse response) {
		
	if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.descargarArchivoDoc");
	
	ResponseEntity<byte[]> responseDoc = null;
	ModelAndView modelo;
	String numIdEcm;
	OutputStream os = null;
	Map<String, Object> beanSeguiWS ;
	String numExpedienteVirtual;
	Calendar fechaActualCalendar = null;
	Calendar fechaVacia = null;
	String nombreArchivo;
  	try {
			
  		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
		String rucUsuario=usuarioBean.getNumRUC();
		
		if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.descargarArchivoDoc");

		numExpedienteVirtual =Utils.toStr(request.getParameter("numExpedienteVirtual"));
		nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));
		// Fecha actual
		fechaActualCalendar = Calendar.getInstance();
						
		//Fecha fin
		fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);
				
		Map<String, Object> mapa = new HashMap<String,Object>();
		
		mapa = new HashMap<String,Object>();
		
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTERNET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		
		Map<String, Object> mapaAccionesSistemaInternet = catalogoService.obtenerCatalogo(mapa);
		
		Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
		numIdEcm =request.getParameter("codIdentificadorECM").toString().trim(); 
		mapParametrosBusqueda.put("codIdecm", numIdEcm);
		mapParametrosBusqueda.put("inline", "true");
			
		responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
		MediaType contentType = responseDoc.getHeaders().getContentType();
		if(!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || 
				Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) ||
				Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))){
				String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(nombreArchivo));
		        response.setHeader(headerKey, headerValue);
			}	
			
		// Seguimiento WS
		beanSeguiWS = new HashMap<String, Object>();
		beanSeguiWS.put("num_expedv", numExpedienteVirtual);
		beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CC);
		beanSeguiWS.put("fec_seguim", fechaActualCalendar.getTime());
		beanSeguiWS.put("fec_invserv", fechaVacia.getTime());
		beanSeguiWS.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("num_ruc", rucUsuario);
		beanSeguiWS.put("cod_opccons", ValidaConstantes.OPCION_INTERNET_CONSULTA_EXPEDIENTES);
		beanSeguiWS.put("cod_accion", ValidaConstantes.ACCION_INTERNET_DESCARGAR_DOCUMENTO);
		beanSeguiWS.put("des_datcons", Utils.toStr(mapaAccionesSistemaInternet.get(ValidaConstantes.ACCION_INTERNET_DESCARGAR_DOCUMENTO)));
		beanSeguiWS.put("fec_cons", fechaActualCalendar.getTime());
		beanSeguiWS.put("cod_respacc",ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);	
		beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
		beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
		beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
			
		seguiService.registrarSeguimiento(beanSeguiWS);
		
		os = response.getOutputStream();
		os.write(responseDoc.getBody());
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.descargarArchivoDoc");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.descargarArchivoDoc");
			try {
				/*if (os!=null) {
					os.flush();
					os.close();
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			NDC.pop();
			NDC.remove();
			
		}
	}

	@RequestMapping(value="/validarExtension")
	public @ResponseBody String validarExtension(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.info("Inicio - ConsultaExpedientesController.validarExtension");
		Map<String, Object> responseBody = new HashMap<String, Object>();
		try {
			T6613DocExpVirtBean documentoBean = (T6613DocExpVirtBean) new JsonSerializer().deserialize(request.getParameter("docData"));
			
			ObjectMapper m = new ObjectMapper();
			Map<String,Object> documento = m.convertValue(documentoBean, Map.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("numCorDoc", documento.get("numCorDoc").toString().trim());
			map.put("numExpedv", documento.get("numExpedienteVir").toString());
			List<T6613DocExpVirtBean> lstT6613DocExpVirtBean=expedienteVirtualService.listarDocExp(map);
			String extension=Utils.obtenerExtension(lstT6613DocExpVirtBean.get(0).getDescripcionArchivo());
			responseBody.put("error", !Utils.equiv(extension.toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF));
			responseBody.put("extension", extension.toUpperCase());

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.validarExtension");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			responseBody.put("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.validarExtension");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.info("Final - ConsultaExpedientesController.cargarListaTiposExpediente");
		return (String)new JsonSerializer().serialize(responseBody);

	}
	
	// Inicio [jjurado 17/08/2016]
	@RequestMapping(value = "/verRepImpresa", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public ModelAndView verRepImpresa(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.verRepImpresa");
		
		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		String numCorrelativo = null;
		Long numSeq = null;
		int tamSeq = 0;
		Map<String,Object> mapDatosDocumento = new HashMap<String, Object>();
		Map<String,Object> mapUsuarioBean = new HashMap<String, Object>();
		Map<String,Object> mapResult = null;
		ByteArrayOutputStream baos = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.verRepImpresa");
			
			//DATOS DEL DOCUMENTO SELECCIONADO EN GRILLA
			T6613DocExpVirtBean documentoBean = (T6613DocExpVirtBean) new JsonSerializer().deserialize(request.getParameter("docData"));
			
			ObjectMapper m = new ObjectMapper();
			Map<String,Object> documento = m.convertValue(documentoBean, Map.class);
			numSeq = expedienteVirtualService.generarCorrelativo(SequenceConstantes.SEQ_EV_REPIMP);
			numCorrelativo = "0000000" + numSeq.toString();
			tamSeq = numCorrelativo.length(); 
			numCorrelativo = numCorrelativo.substring(tamSeq - 7, tamSeq);
			//DESCARGAR EL DOCUMENTO
			Map<String, Object> mapDLECM = new HashMap<String, Object>();
			mapDLECM.put("codIdecm", documento.get("codIdentificadorECM").toString().trim());
			mapDLECM.put("inline", "true");
	
			ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapDLECM);
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {
	
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
	
			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}
			
			//DATOS CONTRIBUYENTE y REP IMPRESA
			mapDatosDocumento.put("ruc", request.getParameter("ruc").toString().trim());
			mapDatosDocumento.put("razSoc", request.getParameter("razSoc").toString().trim());
			mapDatosDocumento.put("numExpedo", documento.get("codOrigenExpe").toString().trim());
			mapDatosDocumento.put("numDoc", documento.get("numDoc").toString().trim());
			mapDatosDocumento.put("codTipDoc", documento.get("codTipDoc").toString().trim());
			mapDatosDocumento.put("desTipdoc2", documento.get("desTipdoc").toString().trim());
			mapDatosDocumento.put("codTipExp", documento.get("codTipExp").toString().trim());
			mapDatosDocumento.put("numCorDoc", documento.get("numCorDoc").toString().trim());
			mapDatosDocumento.put("codOriDes", ValidaConstantes.COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTERNET);
			mapDatosDocumento.put("numCorrelativo", numCorrelativo);
			
			
			//DATOS DEL USUARIO PARA AUDITORIA
			if(!Utils.isEmpty( usuarioBean.getNroRegistro())){
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getNroRegistro());
			}else{
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getUsuarioSOL());
			}
			mapUsuarioBean.put("fechaRegistro", new Date());
			mapUsuarioBean.put("usuarioModificacion", ValidaConstantes.SEPARADOR_GUION);
			mapUsuarioBean.put("fechaModificacion", ValidaConstantes.FECHA_VACIA);
	
			//generamos la representación impresa
			mapResult = Utils.generarRepImpresa(mapDatosDocumento, responseDoc, mapUsuarioBean);
			
			t6625repimpexpvirtBean = (T6625repimpexpvirtBean) mapResult.get("t6625repimpexpvirtBean");
			
			//grabamos en base de datos
			representacionImpresaService.grabarRepresentacionImpresa(t6625repimpexpvirtBean);	
			
			baos = (ByteArrayOutputStream)mapResult.get("baos");
			
			//mostramos el pdf generado
			response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/pdf");
	        response.setContentLength(baos.size());
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	        os.close();
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.verRepImpresa");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.verRepImpresa");
			NDC.pop();
			NDC.remove();
		}
		return new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
	}
	// Fin [jjurado 17/08/2016]
	
	//Inicio JEFFIO 27/06/2017 construcion de exportar documentos 
	@RequestMapping(value="/exportarExcelDoc")
	public void exportarExcelDoc(HttpServletRequest request, HttpServletResponse response){
		
		//TODO - DECLARACION DE VARIABLES
		ModelAndView view = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		MensajeBean mensajeBean = new MensajeBean();
		String titulo = ExportaConstantes.TITULO_EXPORTA_04_07;
		String listadoExportarCadena = null;
		String numRuc = null;
		String razonSocial = null;
		String numExpOrigen = null;
		String numExpVirtual = null;
		String estExpediente = null;
		String tipoProceso = null;
		String tipoExpediente = null;
		String fechaGeneracion = null;
		String razonSocialRuc = null;

		try {
    		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
    		numRuc = usuarioBean.getNumRUC();
    		razonSocial = usuarioBean.getNombreCompleto();
	    	listadoExportarCadena = Utils.toStr(request.getParameter("listadoDocumentosCadena"));
	    	numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
		    estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
		    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
		    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
		    fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
		    numExpVirtual =Utils.toStr(request.getParameter("hiddenNumExpVirtualDoc"));
		    razonSocialRuc = numRuc + " - " + razonSocial;
		    
		    @SuppressWarnings("unchecked")
			List<T6613DocExpVirtBean> listadoExportar = (List<T6613DocExpVirtBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
		    
		    //TODO - HEADER XLS DEL RESPONSE
			response.setContentType("application/vnd.ms-excel");
			
			//TODO - CREACION DE LIBRO XLS
			HSSFWorkbook libro = new HSSFWorkbook();
			//TODO - CREACION DE HOJA XLS
			HSSFSheet hoja = libro.createSheet("Hoja 1");
			
			//TODO - ANCHOS DE COLUMNA
			hoja.setColumnWidth(0, 2500);
			hoja.setColumnWidth(1, 2500);
			hoja.setColumnWidth(2, 3500);
			hoja.setColumnWidth(3, 4500);
			hoja.setColumnWidth(4, 4800);
			hoja.setColumnWidth(5, 16000);
			hoja.setColumnWidth(6, 16000);
			
			//TODO - CREAR FILA TITULO
			HSSFRow fila = hoja.createRow(1);
			//TODO - CREAR CELDA TITULO
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			//TODO - CREAR RANGO CELDAS - COMBINACION
			CellRangeAddress rango = null;
			rango = new CellRangeAddress(1, 1, 0, 4);
			hoja.addMergedRegion(rango);
			
			//TODO - CREAR CELDA FECHA
			HSSFCell nomFecha = fila.createCell(5);
			nomFecha.setCellValue("Fecha del Reporte:");
						
			HSSFCell Fecha = fila.createCell(6);
			Fecha.setCellValue(fecImpresion);
			
			
			//TODO - CREAR CELDAS BLOQUE DATOS DEL EXPEDIENTE
			fila = hoja.createRow(3);
			CellRangeAddress rango1 = null;
			HSSFCell subtituloCelda = fila.createCell(0);
			subtituloCelda.setCellValue("Datos del Expediente");
			rango1 = new CellRangeAddress(3, 3, 0, 1);
			hoja.addMergedRegion(rango1);

			fila = hoja.createRow(4);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocialRuc);
			rango2 = new CellRangeAddress(4, 4, 2, 3);
			hoja.addMergedRegion(rango2);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda2 = fila.createCell(1);
			subtituloCelda2.setCellValue("Proceso:");
			HSSFCell contenido1 = fila.createCell(2);
			contenido1.setCellValue(tipoProceso);

			HSSFCell subtituloCelda3 = fila.createCell(3);
			subtituloCelda3.setCellValue("Tipo de Expediente:");
			HSSFCell contenido2 = fila.createCell(4);
			contenido2.setCellValue(tipoExpediente);
			
			HSSFCell subtituloCelda4 = fila.createCell(5);
			subtituloCelda4.setCellValue("Estado del Expediente:");
			HSSFCell contenido4 = fila.createCell(6);
			contenido4.setCellValue(estExpediente);

			fila = hoja.createRow(6);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("N° de Expediente Origen:");
			HSSFCell contenido5 = fila.createCell(2);
			contenido5.setCellValue(numExpOrigen);
			
			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("N° de Expediente Virtual:");
			HSSFCell contenido6 = fila.createCell(4);
			contenido6.setCellValue(numExpVirtual);

			HSSFCell subtituloCelda7 = fila.createCell(5);
			subtituloCelda7.setCellValue("Fecha de Registro:");
			HSSFCell contenido7 = fila.createCell(6);
			contenido7.setCellValue(fechaGeneracion);

			//TODO - CREAR FILAS TITULO LISTA DE DOCUMENTOS - COMBINADO
			fila = hoja.createRow(9);
			CellRangeAddress rango3 = null;
			HSSFCell subtituloCelda10 = fila.createCell(0);
			subtituloCelda10.setCellValue("Listado de Documentos");
			rango3 = new CellRangeAddress(9, 9, 0, 2);
			hoja.addMergedRegion(rango3);
			
			//TODO - CREAR FILAS CABECERA GRILLA
			fila = hoja.createRow(10);
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
			HSSFCell celda10 = fila.createCell(10);
			
			//TODO - CONTENIDO CABECERA GRILLA
			celda.setCellValue("N°");
			celda1.setCellValue("Tipo de Documento");
			celda2.setCellValue("Número de Documento");
			celda3.setCellValue("Origen registro documento");
			celda4.setCellValue("N° Requerimiento (Si corresponde)");
			celda5.setCellValue("Fecha de Registro Documento");
			celda6.setCellValue("Fecha de Emisión Documento");
			celda7.setCellValue("Fecha de Notificación");
			celda8.setCellValue("Forma de Notificación");
			celda9.setCellValue("Nº de Expediente Origen");
			celda10.setCellValue("Nº de Expediente Acumulador");
			
			//TODO - TIPO DE FUENTE
			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 11);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			//TODO - AUTOAJUSTE DE COLUMNAS
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
			ssheet.autoSizeColumn(10);
			
			//TODO - BORDEADO DE CELDAS
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
			celda6.setCellStyle(estiloCelda);
			celda7.setCellStyle(estiloCelda);
			celda8.setCellStyle(estiloCelda);
			celda9.setCellStyle(estiloCelda);
			celda10.setCellStyle(estiloCelda);
			
			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);
			tituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda1.setCellStyle(estiloTitulo);
			subtituloCelda2.setCellStyle(estiloTitulo);
			subtituloCelda3.setCellStyle(estiloTitulo);
			subtituloCelda4.setCellStyle(estiloTitulo);
			subtituloCelda5.setCellStyle(estiloTitulo);
			subtituloCelda6.setCellStyle(estiloTitulo);
			subtituloCelda7.setCellStyle(estiloTitulo);
			subtituloCelda10.setCellStyle(estiloTitulo);
			
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
			
			
			
			for (int i = 0; i < listadoExportar.size(); i++) {

				fila = hoja.createRow(i + 11);
				celda = fila.createCell(0);
				
				
				texto = new HSSFRichTextString(String.valueOf(i+1));
				celda.setCellValue(texto.toString());
				
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).getDesTipdoc()));
				
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).getNumDoc()));
	
	
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).getDesOrigenDoc()));				
				celda3.setCellStyle(estiloRecorrido);
				
				celda4 = fila.createCell(4);
				celda4.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).getNroRequerim()));
				
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).getFechaRegistroDoc()));
				
				celda5.setCellStyle(estiloRecorrido);
				celda6 = fila.createCell(6);
				celda6.setCellValue(Utils.toStr(listadoExportar.get(i).getFechaDocumento()));
				
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(7);
				celda7.setCellValue(Utils.toStr(listadoExportar.get(i).getStrfecNotif()));
				
				celda7.setCellStyle(estiloRecorrido);
				celda8 = fila.createCell(8);
				celda8.setCellValue(Utils.toStr(listadoExportar.get(i).getDesForNotif()));
				
				celda8.setCellStyle(estiloRecorrido);
				celda9 = fila.createCell(9);
				celda9.setCellValue(Utils.toStr(listadoExportar.get(i).getCodOrigenExpe()));				
				celda9.setCellStyle(estiloRecorrido);

			
				celda10 = fila.createCell(10);
				celda10.setCellValue(Utils.toStr(listadoExportar.get(i).getNumAcumuladorVista()));				
				celda10.setCellStyle(estiloRecorrido);
				//for(int x = 0; x <= 10; x++){
				//	hoja.autoSizeColumn(x);
				//}
			}		
				
				Calendar calendar = Calendar.getInstance();

				int anio = (calendar.get(Calendar.YEAR));
				int dia = (calendar.get(Calendar.DATE));
				int hora = (calendar.get(Calendar.HOUR_OF_DAY));
				int minutos = (calendar.get(Calendar.MINUTE));
				String dd = (dia < 10) ? "0" + dia : dia + "";

				int mes = calendar.get(Calendar.MONTH) + 1;

				String mm = (mes < 10) ? "0" + mes : mes + "";

				String part = anio + mm + dd + "_" + hora + minutos;

				String filename = "rpt_documentos_expediente_" + part + ".xls";
				
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
				String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			ex.printStackTrace();
			log.error("**** ERROR ****", ex);
			mensajeBean.setError(true);
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
	}
	//Fin JEFFIO construcion de exportar documentos 
	//INICIO LLRB
		@RequestMapping(value="/cargarArchivosExpVirtual")
		public @ResponseBody String cargarArchivosExpVirtual(HttpServletRequest request, HttpServletResponse response) {
	
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedienteControler.cargarArchivosExpVirtual");
				
			Map<String, Object> responseBody = new HashMap<String, Object>();
			List<Map<String, Object>> lstArchExp;	
			HashMap<String, Object> mapParametrosBusqueda;

			try {

				if (log.isDebugEnabled()) log.debug("Entro al try");
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
				lstArchExp = new ArrayList<Map<String, Object>>();
				HashMap<String, String> excepciones = new HashMap<String, String>();
				
				log.debug("numCorDoc:"+request.getParameter("numCorDoc"));

				Integer numCorDoc =Integer.parseInt(request.getParameter("numCorDoc"));

				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numCorDoc", numCorDoc);
				
				log.debug("numcordoc->c " + numCorDoc);
				lstArchExp = expedienteVirtualService.obtenerArchivosExpediente(mapParametrosBusqueda);
				
				if (log.isDebugEnabled()) log.debug("Entro al servicio");
				
				
				
				responseBody.put("titulos", new JsonSerializer().serialize(titulos));
				responseBody.put("lstArchExp", new JsonSerializer().serialize(lstArchExp));
				
			} catch (Exception ex) {
				if (log.isDebugEnabled()) {
					log.debug((Object) "Error - ConsultaExpedientesController.cargarArchivosExpVirtual");
				}
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				responseBody.put("beanErr", msb);

			} finally {
				
				if (log.isDebugEnabled()) {
					log.debug( "Final - ConsultaExpedientesController.cargarArchivosExpVirtual");
				}
				NDC.pop();
				NDC.remove();
			}
			
			log.debug("responseBody: " + responseBody);
			return (String)new JsonSerializer().serialize(responseBody);
		}
		@RequestMapping(value = "/descargarArchivoModal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
		public void descargarArchivoModal(HttpServletRequest request, HttpServletResponse response) {

			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.descargarArchivoModal");
			ResponseEntity<byte[]> responseDoc = null;
			ModelAndView modelo = null;
			String numIdEcm;
			OutputStream os = null;
			String nombreArchivo = null;

			try {

				if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.descargarArchivoModal");

				Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
				numIdEcm = request.getParameter("codIdentificadorECM").toString().trim();
				mapParametrosBusqueda.put("codIdecm", numIdEcm);
				mapParametrosBusqueda.put("inline", "true");
				nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));

				responseDoc = ecmService.descargarDocumentoECM(mapParametrosBusqueda);
				MediaType contentType = responseDoc.getHeaders().getContentType();
				if (!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) || Utils
				        .equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))) {
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
					response.setHeader(headerKey, headerValue);
				}
				os = response.getOutputStream();
				os.write(responseDoc.getBody());

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.descargarArchivoModal");

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.descargarArchivoModal");
				try {
					if (os != null) {
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
	@RequestMapping(value="/validarExtensionArchivo")
	public @ResponseBody String validarExtensionArchivo(HttpServletRequest request, HttpServletResponse response) {

			if (log.isDebugEnabled()) log.info("Inicio - ConsultaExpedientesController.validarExtensionArchivo");
			Map<String, Object> responseBody = new HashMap<String, Object>();
			try {
				Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
				
				Map<String, Object> map = new HashMap<String, Object>();
				log.debug("numCorDoc-cv: " + documento.get("numCorDoc").toString());
				map.put("numCorDoc", documento.get("numCorDoc").toString());
				/*map.put("numExpedv", documento.get("numExpedienteVir").toString());*/
				
				List<Map<String, Object>> lstT10373DocAdjReq;	

			if (log.isDebugEnabled()) log.debug("Entro al try");
			
		
			lstT10373DocAdjReq = new ArrayList<Map<String, Object>>();
			
			lstT10373DocAdjReq = expedienteVirtualService.listarArchivosAdj(map); 
				
			log.debug("listaControlador: " + lstT10373DocAdjReq);
			String extension=Utils.obtenerExtension((String) lstT10373DocAdjReq.get(0).get("nomArcAdj"));
				responseBody.put("error", !Utils.equiv(extension.toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF));
				responseBody.put("extension", extension.toUpperCase());

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - ConsultaExpedientesController.validarExtensionArchivo");

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				responseBody.put("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - ConsultaExpedientesController.validarExtensionArchivo");
				NDC.pop();
				NDC.remove();
			}

			if (log.isDebugEnabled()) log.info("Final - ConsultaExpedientesController.cargarListaTiposExpediente");
			return (String)new JsonSerializer().serialize(responseBody);

	}
	@RequestMapping(value = "/verRepArchivoImpresa", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public ModelAndView verRepArchivoImpresa(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.verRepArchivoImpresa");

		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		String numCorrelativo = null;
		Long numSeq = null;
		int tamSeq = 0;
		Map<String, Object> mapDatosDocumento = new HashMap<String, Object>();
		Map<String, Object> mapUsuarioBean = new HashMap<String, Object>();
		Map<String,Object> mapResult = null;
		ByteArrayOutputStream baos = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaReporteController.verRepArchivoImpresa");

			// DATOS DEL DOCUMENTO SELECCIONADO EN GRILLA
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));

			numSeq = expedienteVirtualService.generarCorrelativo(SequenceConstantes.SEQ_EV_REPIMP);
			numCorrelativo = "0000000" + numSeq.toString();
			tamSeq = numCorrelativo.length();
			numCorrelativo = numCorrelativo.substring(tamSeq - 7, tamSeq);

			// DESCARGAR EL DOCUMENTO
			Map<String, Object> mapDLECM = new HashMap<String, Object>();
			mapDLECM.put("codIdecm", documento.get("codIdecm").toString());
			mapDLECM.put("inline", "true");

			ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapDLECM);

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			// DATOS CONTRIBUYENTE y REP IMPRESA
			mapDatosDocumento.put("ruc", request.getParameter("ruc").toString().trim());
			mapDatosDocumento.put("razSoc", request.getParameter("razSoc").toString().trim());
			mapDatosDocumento.put("numArcAdj", documento.get("numArcAdj").toString().trim());
			mapDatosDocumento.put("nomArcAdj", documento.get("nomArcAdj").toString().trim());
			mapDatosDocumento.put("numArcItem", documento.get("numArcItem").toString().trim());
			mapDatosDocumento.put("cntTamArc", documento.get("cntTamArc").toString().trim());
			mapDatosDocumento.put("numFolios", documento.get("numFolios").toString().trim());
			mapDatosDocumento.put("numCorDoc", documento.get("numCorDoc").toString().trim());

			mapDatosDocumento.put("codOriDes", ValidaConstantes.COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTERNET);
			mapDatosDocumento.put("codTipExp", documento.get("codTipExp").toString().trim());
			mapDatosDocumento.put("codTipDoc", documento.get("codTipDoc").toString().trim());
			mapDatosDocumento.put("numDoc", documento.get("numDoc").toString().trim());
			mapDatosDocumento.put("numExpedO", documento.get("numExpedO").toString().trim());
			
			mapDatosDocumento.put("numCorrelativo", numCorrelativo);

			// DATOS DEL USUARIO PARA AUDITORIA
			if (!Utils.isEmpty(usuarioBean.getNroRegistro())) {
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getNroRegistro());
			} else {
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getUsuarioSOL());
			}
			mapUsuarioBean.put("fechaRegistro", new Date());
			mapUsuarioBean.put("usuarioModificacion", ValidaConstantes.SEPARADOR_GUION);
			mapUsuarioBean.put("fechaModificacion", ValidaConstantes.FECHA_VACIA);

			//generamos la representación impresa
			mapResult = Utils.generarRepImpresaArchivo(mapDatosDocumento, responseDoc, mapUsuarioBean);
			
			t6625repimpexpvirtBean = (T6625repimpexpvirtBean) mapResult.get("t6625repimpexpvirtBean");

			//grabamos en base de datos
			representacionImpresaService.grabarRepresentacionImpresa(t6625repimpexpvirtBean);

			baos = (ByteArrayOutputStream)mapResult.get("baos");
			
			//mostramos el pdf generado
			response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
			
		    
		    
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.verRepArchivoImpresa");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.verRepArchivoImpresa");
			NDC.pop();
			NDC.remove();
		}
		return new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
	}
	//FIN LLRB
		
}
