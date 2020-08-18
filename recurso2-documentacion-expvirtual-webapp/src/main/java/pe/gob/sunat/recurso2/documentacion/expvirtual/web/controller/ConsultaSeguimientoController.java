package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteTrabReporteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;


@Controller
public class ConsultaSeguimientoController extends MultiActionController {

private static final Log log = LogFactory.getLog(ConsultaSeguimientoController.class);
	
	private ExpedienteVirtualService expedienteVirtualService; 
	private ConfiguracionExpedienteService configuracionExpedienteService; 
	private ParametroService paramService;
	private JsonView jsonView;
	private ValidarParametrosService validarParametrosService;
	private SeguimientoService seguiService;
	// Inicio [jquispe 27/05/2016]
	private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	private ExpedienteTrabReporteService expedienteTrabReporteService;
	//@RequestMapping(value = "/cargarBusqObsExpVirtual", method = RequestMethod.GET)
	
	public ModelAndView cargarBusqSegExpVirtual(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info("Inicio - ConsultaSeguimientoController.cargarBusqObsExpVirtual");
		
		List<T01ParamBean> listadoProcesos = null;
		List<T01ParamBean> listadoDependencias = null;
		List<T01ParamBean> listadoTipoFecha=null;
		List<T01ParamBean> listadoNumeroTipoExpediente=null;
		List<T01ParamBean> listadoEstadosExpVirtual=null;
		ModelAndView modelAndView = null;
		try {
				
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");//obtener la dependencia del usaurio que ingreso		
			String codDepUsuario=usuarioBean.getCodDepend();		
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			//Inicio - [oachahuic][PAS20165E210400270]
			listadoDependencias = new ArrayList<T01ParamBean>();
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				log.debug(""+usuarioBean.getCodDepend());
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
			listadoEstadosExpVirtual = configuracionExpedienteService.listarEstadosExpVirtual();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_01_003);
			
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
		
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_04_01_003);	
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("codDepUsuario", new JsonSerializer().serialize(codDepUsuario));			
			} else {
				modelAndView.addObject("codDepUsuario", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("listadoTipoFecha", new JsonSerializer().serialize(listadoTipoFecha));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoEstadosExpVirtual", new JsonSerializer().serialize(listadoEstadosExpVirtual));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
	
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.cargarBusqSegExpVirtual");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.cargarBusqObsExpVirtual");
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled()) log.info("Final - ConsultaSeguimientoController.cargarBusqObsExpVirtual");
		return modelAndView;
	}
		
//	@RequestMapping(value = "/cargarListaTiposExpediente", method = RequestMethod.GET)
	public ModelAndView cargarListaTiposExpediente (HttpServletRequest request, HttpServletResponse response){
		
		if (log.isDebugEnabled()) log.info("Inicio - ConsultaSeguimientoController.cargarListaTiposExpediente");
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
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.cargarListaTiposExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.cargarListaTiposExpediente");
			NDC.pop();
			NDC.remove();
		}
		
		
		if (log.isDebugEnabled()) log.info("Final - ConsultaSeguimientoController.cargarListaTiposExpediente");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/cargarListadoExpedientesVirtuales", method = RequestMethod.POST)
	public ModelAndView obtenerSegExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response){

		if (log.isDebugEnabled()) log.info("Inicio -  ConsultaSeguimientoController.cargarListadoExpedientesVirtuales");
				
		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();	
		Date fecDesde = null;
		Date fecHasta = null;
		int cantExpAbiertos=0;
		int cantExpCerrados=0;
		int cantTotalExp=0;
		String codEstadoExp;
		
		try {
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String numExp = Utils.toStr(request.getParameter("numExp"));
			String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
			String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
			String codEstado = Utils.toStr(request.getParameter("codEstado"));
		
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
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			//mapParametrosBusqueda.put("codDependencia", codDepUsuario);
			if(!Utils.isEmpty(codDepUsuario)){
				mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				}
			
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
					// Inicio [jquispe 27/05/2016] Parametro del numero expediente virtual para registrar el seguimiento.
					String parametroNumeroExpedienteVirtual = null;
					// Fin [jquispe 27/05/2016]
					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						codEstadoExp=t6614ExpVirtualBean.getCodEstado();
						// Inicio [jquispe 27/05/2016] Parametro del numero expediente virtual para registrar el seguimiento.
						parametroNumeroExpedienteVirtual = t6614ExpVirtualBean.getNumExpedienteVirtual();
						// Fin [jquispe 27/05/2016]
						if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)){
							cantExpAbiertos=cantExpAbiertos+1;									
						}else{
							cantExpCerrados=cantExpCerrados+1;									
						}
						cantTotalExp=cantExpCerrados + cantExpAbiertos;
					}
					// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
					registrarSeguimientoConsultaSeguimientoExpediente(parametroNumeroExpedienteVirtual);
					// Fin [jquispe 27/05/2016]
					modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
					modelAndView.addObject("cantExpAbiertos",cantExpAbiertos);
					modelAndView.addObject("cantExpCerrados",cantExpCerrados);
					modelAndView.addObject("cantTotalExp",cantTotalExp);						
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
			// validamos estado expediente virtual
			if (!Utils.isEmpty(codEstado)) {
				mapParametrosBusqueda.put("codEstado", codEstado);
			}			
					
			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					codEstadoExp=t6614ExpVirtualBean.getCodEstado();
					if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)){
						cantExpAbiertos=cantExpAbiertos+1;									
					}else{
						cantExpCerrados=cantExpCerrados+1;									
					}
					cantTotalExp=cantExpCerrados + cantExpAbiertos;
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoConsultaSeguimientoExpediente(null);
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales",listaExpedientesVirtuales);
				modelAndView.addObject("cantExpAbiertos",cantExpAbiertos);
				modelAndView.addObject("cantExpCerrados",cantExpCerrados);
				modelAndView.addObject("cantTotalExp",cantTotalExp);
			}
		
			} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaSeguimientoController.cargarListadoExpedientesVirtuales");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaSeguimientoController.cargarListadoExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaSeguimientoExpediente(String numeroExpedienteVirtual) throws Exception{
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_SEGUIMIENTO);
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
		
	@RequestMapping(value = { "/validarContribuyente" }, method = { RequestMethod.POST })
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - ConsultaSeguimientoController.validarContribuyente");
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
				log.debug((Object) "Error - ConsultaSeguimientoController.validarContribuyente");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaSeguimientoController.validarContribuyente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/cargarDetalleSeguimiento" }, method = { RequestMethod.POST })
	public ModelAndView cargarDetalleSeguimiento(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaSeguimientoController.cargarDetalleSeguimiento");
		
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean ;
		
		List<T6613DocExpVirtBean> obtenerEstadosExpediente;
		Map<String, Object> params;
		HashMap<String, Object> mapParametrosBusqueda;
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
			modelo = new ModelAndView(jsonView);
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);		
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				  
				 modelo.addObject("fechaOrigenDoc",new JsonSerializer().serialize(fechaDocOrigen));
				 modelo.addObject("fechaRegistro",new JsonSerializer().serialize(fechaRegExpediente));
				 modelo.addObject("razonSocial",new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				 modelo.addObject("datosExpedientes",new JsonSerializer().serialize(t6614ExpVirtualBean));
				 break;
			}
			obtenerEstadosExpediente = new ArrayList<T6613DocExpVirtBean>();
			List<String> listaTipDocSustento = new ArrayList<String>();
			listaTipDocSustento.add(ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaTipDocSustento.add(ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
			params = new HashMap<String,Object>();
			params.put("numExpeDv",numExpedienteVirtual );
			params.put("listaTipDocSustento", listaTipDocSustento);				
			obtenerEstadosExpediente = expedienteVirtualService.obtenerDocumentosEstadoExpediente(params);	
			modelo.addObject("lstDocExp", new JsonSerializer().serialize(obtenerEstadosExpediente));
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoVerDocumento(numExpedienteVirtual);
			// Fin [jquispe 27/05/2016]
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.cargarDetalleSeguimiento");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.cargarDetalleSeguimiento");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoVerDocumento(String numeroExpedienteVirtual) throws Exception{
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_SEGUIMIENTO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS)));
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

@RequestMapping(value = { "/cargarTrazabilidadExpOrigen" }, method = { RequestMethod.POST })
public ModelAndView cargarTrazabilidadExpOrigen(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		HashMap<String, Object> mapParametrosBusqueda;		
		List<T6622SeguimBean> listTrazabilidad = new ArrayList<T6622SeguimBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales=new ArrayList<T6614ExpVirtualBean>() ;	
		Map<String, Object> params = null;
		String fechaDocOrigen="";
		String fechaRegExpediente="";	
		DdpBean beanContribuyente;
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaSeguimientoController.cargarTrazabilidadExpOrigen");
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}		
			
			String numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));		
			modelo = new ModelAndView(jsonView);
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);		
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				  
				 modelo.addObject("fechaOrigenDoc",new JsonSerializer().serialize(fechaDocOrigen));
				 modelo.addObject("fechaRegistro",new JsonSerializer().serialize(fechaRegExpediente));
				 modelo.addObject("razonSocial",new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				 modelo.addObject("datosExpedientes",new JsonSerializer().serialize(t6614ExpVirtualBean));		 
				 break;
			}
			params = new HashMap<String, Object>();
			params.put("numExpedVirtual", numExpedienteVirtual);
			params.put("codTipSeguim",ValidaConstantes.IND_TIP_SEG_EE);
			listTrazabilidad = seguiService.listarSeguimientos(params);				
			modelo.addObject("lstTrazExp", new JsonSerializer().serialize(listTrazabilidad));
			
			// Inicio [jquispe 01/07/2016] Obtiene el estado del expediente origen del ultimo listado de Estados y Etapas.
			Utils.agregarEstadoExpedienteModelo(listTrazabilidad,modelo);
			// Fin [jquispe 01/07/2016]
			
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoVerEstadoEtapaExpediente(numExpedienteVirtual);
			// Fin [jquispe 27/05/2016]
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.cargarTrazabilidadExpOrigen");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.cargarTrazabilidadExpOrigen");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
	}

    //Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoVerEstadoEtapaExpediente(String numeroExpedienteVirtual) throws Exception{
		
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
		beanSegui.put("num_ruc",ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_SEGUIMIENTO);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_ESTADO_ETAPA_EXPEDIENTE);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_ESTADO_ETAPA_EXPEDIENTE)));
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
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	     
		   String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
		   ModelAndView view = null;
		   MensajeBean mensajeBean = new MensajeBean();
		   String listadoExportarCadena = null;
		   // Inicio [gangles 22/08/2016]
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
		   // Fin [gangles 22/08/2016]
		       
		   try {
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaSeguimientoController.exportarExcelExpedientes");   
		             
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
		    hoja.setColumnWidth(10, 16000);
		    hoja.setColumnWidth(11, 16000);
		  //Inicio [jquispe 16/06/2016] Modificado para agregar la nueva columna Razon Social.	
	        hoja.setColumnWidth(12, 16000);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
			hoja.setColumnWidth(13, 16000);
			hoja.setColumnWidth(14, 16000);
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
	        HSSFCell celda10 = fila.createCell(10);
	        HSSFCell celda11 = fila.createCell(11);
	        //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.		
	        HSSFCell celda12 = fila.createCell(12);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        HSSFCell celda13 = fila.createCell(13);
	        HSSFCell celda14 = fila.createCell(14);
	        //fin JEFFIO [13/03/2017]
	        
	        
	        celda.setCellValue("N�");
	        celda1.setCellValue("Nro. Expediente Origen");
	        celda2.setCellValue("Nro. Expediente Virtual");
	        celda3.setCellValue("Responsable");
	        celda4.setCellValue("RUC");	        
	        // Inicio [jquispe 16/06/2016] Se modifico para agregar la nueva columna Razon Social.
	        celda5.setCellValue("Raz�n Social");	       
	        celda6.setCellValue("Proceso");
	        celda7.setCellValue("Tipo de Expediente");
	        celda8.setCellValue("Estado expediente");
	        celda9.setCellValue("Estado Cierre");
	        celda10.setCellValue("Fecha de Registro del Expediente");
	        celda13.setCellValue("Fecha del documento origen");
	        celda14.setCellValue("Origen");
	        // Fin [jquispe 16/06/2016]
	        
	        //inicio JEFFIO [13/03/2017]
	        celda11.setCellValue("Fecha de Notificaci�n") ;
	        celda12.setCellValue("Forma de Notificaci�n") ;
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
	        ssheet.autoSizeColumn(10);
	        ssheet.autoSizeColumn(11);
	        //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.	
	        ssheet.autoSizeColumn(12);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
	        ssheet.autoSizeColumn(13);
	        ssheet.autoSizeColumn(14);
	        //fin JEFFIO [13/03/2017]
	        
	      //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 22/08/2016]
	        
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
	       celda11.setCellStyle(estiloCelda);
	       //Inicio [jquispe 16/06/2016] Agregado para agregar la nueva columna Razon Social.	
	       celda12.setCellStyle(estiloCelda);  
	       //Fin [jquispe 16/06/2016]
	       //inicio JEFFIO [13/03/2017]
	       celda13.setCellStyle(estiloCelda); 
	       celda14.setCellStyle(estiloCelda); 
	       //fin JEFFIO [13/03/2017]
	       
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
               celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("nombreResponsable")));
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               // Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desRazonSocial").toString()));
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desProceso")));
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desTipoExpediente")));
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);
               
               celda8 = fila.createCell(8);
               celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")));
               hoja.autoSizeColumn(8);               
               celda8.setCellStyle(estiloRecorrido);
               
               celda9 = fila.createCell(9);
               celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstadoCierre")));
               hoja.autoSizeColumn(9);               
               celda9.setCellStyle(estiloRecorrido);
               
               celda10 = fila.createCell(10);
               celda10.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecRegistro")));
               hoja.autoSizeColumn(10);               
               celda10.setCellStyle(estiloRecorrido);
               
               celda11 = fila.createCell(13);
               celda11.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
               hoja.autoSizeColumn(13);
               celda11.setCellStyle(estiloRecorrido);
               
               celda12 = fila.createCell(14);
               celda12.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desOrigen")));
               hoja.autoSizeColumn(14);
               celda12.setCellStyle(estiloRecorrido);
               // Fin [jquispe 10/06/2016]
	   	       //inicio JEFFIO [13/03/2017]
               celda11 = fila.createCell(11);
               celda11.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("strFecNotifOrigen")));
               hoja.autoSizeColumn(11);
               celda11 = fila.createCell(12);
               celda11.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desForNotifOrigen")));
               hoja.autoSizeColumn(12);
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
	    	     if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.exportarExcelExpedientes");
	    	   
	             log.error("**** ERROR ****", ex);
	             mensajeBean.setError(true);
	             mensajeBean
	                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
	                                        + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
				
				if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.exportarExcelExpedientes");
				
				NDC.pop();
				NDC.remove();
				
			}
		   
	       return view;

	}
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelTrazabilidad(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_04_09;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	       String numRuc = "";
	       String razonSocial = "";
	       String numExpOrigen = "";
	       String numExpVirtual = "";
	       String estExpediente = "";
	       String tipoProceso = "";
	       String tipoExpediente = "";
	       String descOrigen = "";
	       String fechaGeneracion = "";
	       String fechaOrigen = "";
	       String razonSocialRuc = "";
	       
	       try {
	    	   
	    	listadoExportarCadena = Utils.toStr(request.getParameter("hiddenListaExp"));
	    	numRuc = Utils.toStr(request.getParameter("hiddenNumRuc"));
	    	razonSocial = Utils.toStr(request.getParameter("hiddenRazonSocial"));
	    	numExpOrigen =  Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
	    	numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtual"));
	    	estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
	    	tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
	    	tipoExpediente =  Utils.toStr(request.getParameter("hiddenTipoExpediente"));
	    	descOrigen =Utils.toStr(request.getParameter("hiddenDescOrigen"));
	    	fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
	    	fechaOrigen =Utils.toStr(request.getParameter("hiddenFechaOrigen"));
	    	razonSocialRuc = numRuc +" - "+razonSocial;
		    
			@SuppressWarnings("unchecked")
			List<T6622SeguimBean> listadoExportar = (ArrayList<T6622SeguimBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
	    	   
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
	       	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,4);
			hoja.addMergedRegion(rango);
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(1);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,1,2);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC:");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(razonSocialRuc);
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        	        
            HSSFCell subtituloCelda5 = fila.createCell(4);
	        subtituloCelda5.setCellValue("Proceso:");
	        HSSFCell contenido4 = fila.createCell(5);
	        contenido4.setCellValue(tipoProceso);

	        fila = hoja.createRow(5);	
	        HSSFCell subtituloCelda6 = fila.createCell(1);
	        subtituloCelda6.setCellValue("Tipo de Expediente:");
	        HSSFCell contenido5 = fila.createCell(2);
	        contenido5.setCellValue(tipoExpediente);
	        
	        HSSFCell subtituloCelda7 = fila.createCell(4);
	        subtituloCelda7.setCellValue("N� Expediente Virtual:");
	        HSSFCell contenido2 = fila.createCell(5);
	        contenido2.setCellValue(numExpVirtual);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda8 = fila.createCell(1);
	        subtituloCelda8.setCellValue("Fecha de Documento Origen:");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaOrigen);
	        
	        HSSFCell subtituloCelda9 = fila.createCell(4);
	        subtituloCelda9.setCellValue("Estado del Expediente:");
	        HSSFCell contenido3 = fila.createCell(5);
	        contenido3.setCellValue(estExpediente);
	     
	        //Inicio [gangles 22/08/2016]
	        fila = hoja.createRow(7);
	        HSSFCell nomFecha = fila.createCell(1);
	        nomFecha.setCellValue("Fecha del Reporte:");	        	        
	        HSSFCell Fecha = fila.createCell(2);
	        Fecha.setCellValue(fecImpresion);
	        //Fin [gangles 22/08/2016]
	        
	        fila = hoja.createRow(9);
	        CellRangeAddress rango3 = null;
	        HSSFCell subtituloCelda10 = fila.createCell(1);
	        subtituloCelda10.setCellValue("Listado de Estados - Etapas del Expediente Origen");
	        rango3 = new CellRangeAddress(8,8,0,4);
			hoja.addMergedRegion(rango3);
	 
			
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	               
	        celda.setCellValue("N�");
	        celda1.setCellValue("Tipo de documento");
	        celda2.setCellValue("N� documento");
	        celda3.setCellValue("Estado");
	        celda4.setCellValue("Etapa");
	        celda5.setCellValue("Fecha de registro");
	        
	        	       	        
	        HSSFFont fuente = libro.createFont();
	        fuente.setFontHeightInPoints((short) 11);
	        fuente.setFontName(fuente.FONT_ARIAL);
	        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        Sheet ssheet = libro.getSheetAt(0);
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
	        subtituloCelda.setCellStyle(estiloTitulo);
	        subtituloCelda1.setCellStyle(estiloTitulo);
	        subtituloCelda5.setCellStyle(estiloTitulo);
	        subtituloCelda6.setCellStyle(estiloTitulo);
	        subtituloCelda7.setCellStyle(estiloTitulo);
	        subtituloCelda8.setCellStyle(estiloTitulo);
	        subtituloCelda9.setCellStyle(estiloTitulo);
	        subtituloCelda10.setCellStyle(estiloTitulo);
	       //Inicio [ gangles 22/08/2016]
	        nomFecha.setCellStyle(estiloTitulo);
	        //Fin [ gangles 22/08/2016]
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
               texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).getNumOrden()));
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).getDesTipoDocumento()));
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
               celda2 = fila.createCell(2);
               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).getNumDocumento()));
               hoja.autoSizeColumn(2);
               celda2.setCellStyle(estiloRecorrido);
               celda3 = fila.createCell(3);
               celda3.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).getDesEstado()));
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).getDesEtapa()));
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.toStr(listadoExportar.get(i).getFecVistaSegui()));
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               hoja.autoSizeColumn(6);               
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
           
	         String filename="rpt_trazabilidad_expediente_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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
	//INICIO LLRB 15012020
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientesFisca(HttpServletRequest request, HttpServletResponse response){
	     
		   String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
		   ModelAndView view = null;
		   MensajeBean mensajeBean = new MensajeBean();
		   String listadoExportarCadena = null;
		   // Inicio [gangles 22/08/2016]
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
		   // Fin [gangles 22/08/2016]
		       
		   try {
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaSeguimientoController.exportarExcelExpedientes");   
		             
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
	        hoja.setColumnWidth(12, 16000);
	        //Fin [jquispe 16/06/2016]
	        //inicio JEFFIO [13/03/2017]
			hoja.setColumnWidth(13, 16000);
			hoja.setColumnWidth(14, 16000);
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
        
	        
	        celda.setCellValue("N�");
	        celda1.setCellValue("Nro. Expediente");	      
	        celda2.setCellValue("Responsable");
	        celda3.setCellValue("RUC");     
	        celda4.setCellValue("Raz�n Social");	       
	        celda5.setCellValue("Proceso");
	        celda6.setCellValue("Tipo de Expediente");
	        celda7.setCellValue("Estado expediente");	       
	        celda8.setCellValue("Fecha de Expediente");	       
	        celda9.setCellValue("Origen");
		
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

	        
	      //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 22/08/2016]
	        
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
               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("nombreResponsable")));
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
               celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);               
               celda9 = fila.createCell(9);
               celda9.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desOrigen")));
               hoja.autoSizeColumn(9);
               celda9.setCellStyle(estiloRecorrido);
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
	    	     if (log.isDebugEnabled()) log.debug("Error - ConsultaSeguimientoController.exportarExcelExpedientes");
	    	   
	             log.error("**** ERROR ****", ex);
	             mensajeBean.setError(true);
	             mensajeBean
	                           .setMensajeerror("Se ha producido un error inesperador al mostrar "
	                                        + ex.getMessage());
	             view = new ModelAndView("PagM", "beanM", mensajeBean);
	       } finally {
				
				if (log.isDebugEnabled()) log.debug("Final - ConsultaSeguimientoController.exportarExcelExpedientes");
				
				NDC.pop();
				NDC.remove();
				
			}
		   
	       return view;

	}
	//FIN LLRB 15012020
	

//PAS20201U210500029 - HHC INICIO

	public ModelAndView cargarBusqSolicitudDes(HttpServletRequest request,
			HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaSeguimientoController.cargarBusqSolicitudDes");
		ModelAndView modelo = null;

		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {
			String indCarga = request.getParameter("indCarga");

			if (session == null || session.getAttribute("usuarioBean") == null
					|| request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(
						Integer.parseInt(tenc)).desencripta(usub);

			} else {

				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaActual = new Date();
			String fecVista = sdf.format(fechaActual);
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils
					.getSessionAttribute(request, "beanParametrosConsultaReq");
			Map<String, Object> titulos = new HashMap<String, Object>();

			titulos.put("tituloDefecto",AvisoConstantes.TITULO_MODULO_05_08_001);

			Map<String, Object> excepciones = new HashMap<String, Object>();
			// excepciones de descarga de expedientes
			excepciones.put("excepcion2",AvisoConstantes.AVISO_MODULO_05_01_002);
			excepciones.put("excepcion3",AvisoConstantes.AVISO_MODULO_05_01_003);
			excepciones.put("excepcion4",AvisoConstantes.AVISO_MODULO_05_01_004);
			excepciones.put("excepcion5",AvisoConstantes.AVISO_MODULO_05_01_005);
			excepciones.put("excepcion6",AvisoConstantes.AVISO_MODULO_05_01_006);
			excepciones.put("excepcion7",AvisoConstantes.AVISO_MODULO_05_01_007);
			excepciones.put("excepcion9",AvisoConstantes.EXCEP_MODULO_05_07_004);

			List<T01ParamBean> listadoProcesos = configuracionExpedienteService
					.listarProcesos();

			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_003);

			modelo.addObject("excepciones",
					new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("fecVista",
					new JsonSerializer().serialize(fecVista));
			modelo.addObject("listadoProcesos",
					new JsonSerializer().serialize(listadoProcesos));
			modelo.addObject("beanParametrosConsultaReq",
					beanParametrosConsultaReq);

		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ConsultaReporteController.cargarBusqPedidos");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ConsultaSeguimientoController.cargarBusqPedidos");

			NDC.pop();
			NDC.remove();
		}

		return modelo;

	}
	
	@RequestMapping(value = "/cargarListaSolicitudDes", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cargarListaSolicitudDes(HttpServletRequest request,
			HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - ConsultaSeguimientoController.cargarListaSolicitudDes");
		}
		ModelAndView modelAndView = null;
		List<T10461SolDesBean> listaPedidos = new ArrayList<T10461SolDesBean>();

		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numPedido = Utils.toStr(request.getParameter("numPedido"));

		Date fecDesde = null;
		Date fecHasta = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}
			String codColab = usuarioBean.getNroRegistro();
			String codDepUsuario = usuarioBean.getCodDepend();

			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(
						Utils.toStr(request.getParameter("fecDesde")),
						CatalogoConstantes.INT_TWO);
				Calendar calendarDesde = Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde = calendarDesde.getTime();
			}
			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(
						Utils.toStr(request.getParameter("fecHasta")),
						CatalogoConstantes.INT_TWO);
				Calendar calendarHasta = Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta = calendarHasta.getTime();
			}

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codUsuarioRegistro", codColab);
			// validamos número pedido
			if (!Utils.isEmpty(numPedido)) {
				mapParametrosBusqueda.put("numSolicitudDes", numPedido);
				listaPedidos = expedienteVirtualService.obtenerListaSolicitudDesc(mapParametrosBusqueda);

				if (Utils.isEmpty(listaPedidos)) {
					modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_05_08_002);
					return modelAndView;
				} else {
					modelAndView.addObject("listaPedidos", listaPedidos);
					return modelAndView;
				}
			}

			mapParametrosBusqueda.put("fecGenIni", fecDesde);
			mapParametrosBusqueda.put("fecGenFin", fecHasta);

			// validamos el codigoProceso
			if (!Utils.isEmpty(codProceso)) {
				mapParametrosBusqueda.put("codProceso", codProceso);
			}
			// validamos el codigoTipoExpediente
			if (!Utils.isEmpty(codTipExpediente)) {
				mapParametrosBusqueda.put("codTipExpediente", codTipExpediente);
			}

			// listaPedidos =
			// impRepService.listarPedidos(mapParametrosBusqueda);
			listaPedidos = expedienteVirtualService.obtenerListaSolicitudDesc(mapParametrosBusqueda);

			if (Utils.isEmpty(listaPedidos)) {modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_05_08_001);
			} else {
				modelAndView.addObject("listaPedidos", listaPedidos);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaReporteController.cargarListaSolicitudDes");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - ConsultaSeguimientoController.cargarListaSolicitudDes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	public ModelAndView descargarSolicitud(HttpServletRequest request,HttpServletResponse response)  {

		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();

		FileInputStream inputStream = null;

		log.debug("Inicio - ConsultaSeguimientoController.descargarSolicitud");

		try {

			response.setContentType("application/pdf");
			String numSolicitud = Utils.toStr(request.getParameter("numSolDescarga"));
			if (log.isDebugEnabled())log.debug("numSolicitud: " + numSolicitud);
			String numExpediente = Utils.toStr(request.getParameter("numExpediente")).trim();
			if (log.isDebugEnabled())log.debug("numExpediente: " + numExpediente);
			String fecInicio = Utils.toStr(request.getParameter("fecInicio"));
			if (log.isDebugEnabled())log.debug("fecInicio: " + fecInicio);
			
			Date fechaInicioT=Utils.stringToDate(fecInicio, 2);
			String fechaInicioConvert=Utils.dateUtilToStringOnlyDDMMYYYY(fechaInicioT);
			StringBuilder sbPathFile = new StringBuilder();
			sbPathFile.append(ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
			sbPathFile.append(numSolicitud).append("/");
//			sbPathFile.append(numSolicitud).append("F.pdf");
			sbPathFile.append(numExpediente).append("_SIEV_descarga_");
			sbPathFile.append(fechaInicioConvert).append(".pdf");
			
			File downloadFile = new File(sbPathFile.toString());
			log.debug("downloadFile : "+downloadFile);
			inputStream = new FileInputStream(downloadFile);
			ServletContext context = getServletContext();
			String mimeType = context.getMimeType(sbPathFile.toString());
			log.debug("mimeType : " + mimeType);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
            String nombreArchivo=downloadFile.getName();
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

			//Actualiza la descarga
			Map<String, Object> mapParam = null;
			mapParam = new HashMap<String, Object>();
			mapParam.put("numSolDescarga", numSolicitud);
			mapParam.put("codEstadoSol", ValidaConstantes.IND_ESTADO_SOL_DES_CONSULTADO);
			mapParam.put("codUsuarioModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
			mapParam.put("fecDescarga", new Date());
			
			expedienteTrabReporteService.actualizarSolicitud(mapParam);
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.error("Error - ConsultaSeguimientoController.descargarSolicitud");
			}

			mensajeBean.setError(true);
			mensajeBean
					.setMensajeerror("Se ha producido un error inesperador al mostrar "
							+ ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		} 

		log.debug("Final - ConsultaSeguimientoController.descargarSolicitud");

		return view;

	}
	
		//HHC - FIN
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

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	// Inicio [jquispe 27/05/2016]
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
	// Fin [jquispe 27/05/2016]

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	public void setExpedienteTrabReporteService(
			ExpedienteTrabReporteService expedienteTrabReporteService) {
		this.expedienteTrabReporteService = expedienteTrabReporteService;
	}
	
		/* Seteo - Spring - Final */

	
}
