package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
@Controller
public class ConsultaRequerimientoController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private EcmService ecmService;

	@RequestMapping(value = "/consultarRequerimientosContribuyenteView", method = RequestMethod.GET)
	public ModelAndView consultarRequerimientosContribuyenteView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled())log.info( "Inicio - ConsultaRequerimientoController.consultarRequerimientosContribuyenteView");
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			//ConsultaRequerimientosContribuyente
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_012);
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			List<T01ParamBean> listadoTipoFecha= paramService.listarTipoFecha();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_04_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_04_003);
			
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize( listadoProcesos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha) );
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaRequerimientoController.consultarRequerimientosContribuyenteView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaRequerimientoController.consultarRequerimientosContribuyenteView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaRequerimientoController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaRequerimientoController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaRequerimientoController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaRequerimientoController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaRequerimientoController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaRequerimientoController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarListaTiposExpediente", method =  RequestMethod.GET)
	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - ConsultaRequerimientoController.cargarListaTiposExpediente");
		}
		try {
			String codProceso = Utils.toStr( request.getParameter("codProceso"));

			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			// mapParametrosBusqueda.put("indConsulta", "2");
			List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("listadoTiposExpendientes",listadoTiposExpendientes);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaRequerimientoController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaRequerimientoController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarListadoRequerimientos", method = RequestMethod.GET)
	public ModelAndView cargarListadoRequerimientos(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaRequerimientoController.cargarListadoRequerimientos");

		ModelAndView modelAndView = null;
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		UsuarioBean usuarioBean;
		String numeroRuc;
		
		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numExp = Utils.toStr(request.getParameter("numExp"));
		String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		
		Date fecDesde = null;
		Date fecHasta = null;

		//Inicio [gangles 11/08/2016]
		DdpBean beanContribuyente;
		String razonSocial="";
		//Fin [gangles 11/08/2016]
		
		try {
			
			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			//mapParametrosBusqueda.put("codEstReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			numeroRuc = usuarioBean.getNumRUC();
			mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			
			beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
			razonSocial=beanContribuyente.getDesRazonSocial();
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				
				mapParametrosBusqueda.put("numExpedOrigen", numExp);
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_01);
					}
					return modelAndView;
				} else {
					mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
					listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

					if (Utils.isEmpty(listT6620RequerimBean)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
						return modelAndView;
					} else {
						modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);
						//Lestrada mostrar ruc y razon social en reporte
						modelAndView.addObject("numeroRuc",numeroRuc);
						modelAndView.addObject("razonSocial",razonSocial);
						//fin mostrar ruc y razon social en reporte
						return modelAndView;
					}
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
			
			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")),CatalogoConstantes.INT_TWO);
				Calendar calendarDesde=Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde=calendarDesde.getTime();
			}
			mapParametrosBusqueda.put("fecGenIni", fecDesde);
			
			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")),CatalogoConstantes.INT_TWO);
				Calendar calendarHasta=Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta=calendarHasta.getTime();
			}
			mapParametrosBusqueda.put("fecGenFin", fecHasta);
			mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
			listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

			if (Utils.isEmpty(listT6620RequerimBean)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);
				modelAndView.addObject("numeroRuc",numeroRuc);
				modelAndView.addObject("razonSocial",razonSocial);				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaRequerimientoController.cargarListadoRequerimientos");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opci&oacute;n");
			modelAndView.addObject("beanErr", msb);
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaRequerimientoController.cargarListadoRequerimientos");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = { "/consultarDetalleRequerimientoView" }, method = { RequestMethod.GET })
	public ModelAndView consultarDetalleRequerimientoView(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaRequerimientoController.consultarDetalleRequerimientoView");
		ModelAndView modelAndView;
		
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		List<T6620RequerimBean> listT6620RequerimBean;
		List<T6613DocExpVirtBean> lisT6613DocExpVirtBean;
		DdpBean beanContribuyente;
		String fechaDocOrigen="";
		String fechaRegExpediente="";
		String fechaRequerimiento="";
		String fechaVencimiento="";
		
		try {
			String numExpedVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			String numRequerimiento = Utils.toStr(request.getParameter("numRequerimiento"));
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_008);
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				 fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				 modelAndView.addObject("fechaDocOrigen",fechaDocOrigen);
				 modelAndView.addObject("fechaRegExpediente",fechaRegExpediente);
				 modelAndView.addObject("razonSocial",beanContribuyente.getDesRazonSocial());
				 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 break;
			}
			
			mapParametrosBusqueda.put("numRequerimiento", numRequerimiento);
			
			listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);
			
			for (T6620RequerimBean t6620RequerimBean : listT6620RequerimBean) {
				fechaRequerimiento = Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaRequerimiento());
				fechaVencimiento = Utils.dateUtilToStringDDMMYYYY(t6620RequerimBean.getFechaVencimiento());
				modelAndView.addObject("fechaRequerimiento",fechaRequerimiento);
				modelAndView.addObject("fechaVencimiento",fechaVencimiento);
				modelAndView.addObject("t6620RequerimBean",t6620RequerimBean);
			};
			
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_REQUERIM);
			lisT6613DocExpVirtBean = requerimientoService.listarDocumentosPorRequerim(mapParametrosBusqueda);
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_06_001);
			
			modelAndView.addObject("lisT6613DocExpVirtBean",new JsonSerializer().serialize(lisT6613DocExpVirtBean));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - ConsultaRequerimientoController.consultarDetalleRequerimientoView");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - ConsultaRequerimientoController.consultarDetalleRequerimientoView");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/mostrarView",  method =  RequestMethod.GET)
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaRequerimientoController.mostrarView");
		
		ModelAndView modelAndView = null;
		
		String pageView = request.getParameter("pageView");
		modelAndView = new ModelAndView("ConsultaExpedientesVirtualesIntranet");
		try {
			
			if(Utils.equiv(pageView, NavegaConstantes.MANT_MODULO_02_01_001)){
				
				List<T01ParamBean> listadoProcesos = null;
				
				listadoProcesos = configuracionExpedienteService.listarProcesos();
				
				List<T01ParamBean> listadoDependencias = configuracionExpedienteService.listarDependencias();
				
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
				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaRequerimientoController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaRequerimientoController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - ConsultaRequerimientoController.mostrarView");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	       
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_04;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String codProceso = "";
		String tipoExpediente="";
		String fechaDesde="";
		String fechaHasta="";
		String numRuc="";
		String razonSocial="";
		
		Calendar fechaVacia = null;
		//Inicio [gangles 11/08/2016]
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		String desProceso="";
		String desTipoExpediente="";
		//Fin [gangles 11/08/2016]
   
		try {
		    listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
			//Inicio [gangles 11/08/2016]
			numRuc = request.getParameter("hiddenNumRuc") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumRuc").toString().trim();
			razonSocial = request.getParameter("hiddenRazonSocial") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenRazonSocial").toString().trim();
			codProceso = request.getParameter("hiddenTipoProceso") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoProceso").toString().trim();
			tipoExpediente = request.getParameter("hiddenTipoExpediente") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoExpediente").toString().trim();
			fechaDesde = request.getParameter("hiddenFechaDesde") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaDesde").toString().trim();
			fechaHasta = request.getParameter("hiddenFechaHasta") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaHasta").toString().trim();
			razonSocial=numRuc+" - "+ razonSocial;
			desProceso=Utils.toStr(listadoExportar.get(0).get("desProceso")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(0).get("desProceso").toString().trim());
			desTipoExpediente=Utils.toStr(listadoExportar.get(0).get("desTipoExpediente")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(0).get("desTipoExpediente").toString().trim());
			//Fin [gangles 11/08/2016]
			// Fecha actual
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			response.setContentType("application/vnd.ms-excel");
			/*
			//Inicio nchavez [31/05/216]
		    Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numRequerimOrigen",new ColumOption("N° de Requerimiento", ALIGN.LEFT));
			columnsProperties.put("numExpedienteOrigen",new ColumOption("N° de Expediente", ALIGN.LEFT));
			columnsProperties.put("desProceso",new ColumOption("Proceso", ALIGN.LEFT));
			columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente", ALIGN.LEFT));;
			columnsProperties.put("desEstado",new ColumOption("Estado del Requerimiento", ALIGN.LEFT));
			columnsProperties.put("fechaDocumentoOrigen",new ColumOption("Fecha del Expediente", ALIGN.RIGHT));
			columnsProperties.put("fechaRequerimiento",new ColumOption("Fecha del Requerimiento", ALIGN.RIGHT));	          
			HSSFWorkbook libro=buildWorkbookXLS(titulo,"Hoja 1",columnsProperties,listadoExportar);
		    //Fin nchavez [31/05/216]
			
			*/
			//Inicio [gangles 11/08/2016]
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
			hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 7));

			//inicio lestrada quito algunos campos de cabecera
			/*fila = hoja.createRow(2);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocial);
			rango2 = new CellRangeAddress(2, 2, 2, 4);
			hoja.addMergedRegion(rango2);
		
			HSSFCell subtituloCelda3 = fila.createCell(5);
			subtituloCelda3.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(6);
			contenido3.setCellValue(fecImpresion);*/

			fila = hoja.createRow(2);
			HSSFCell subtituloCelda2 = fila.createCell(1);
			subtituloCelda2.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(2);
			contenido3.setCellValue(fecImpresion);
			/*HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(codProceso+"-"+desProceso);

			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido5 = fila.createCell(4);
			contenido5.setCellValue(tipoExpediente+"-"+desTipoExpediente);

			HSSFCell subtituloCelda7 = fila.createCell(5);
			subtituloCelda7.setCellValue("Fecha de Generación del Expediente:");
			HSSFCell contenido2 = fila.createCell(6);
			contenido2.setCellValue(fechaDesde+"-"+fechaHasta);*/
			//fin lestrada quito algunos campos de cabecera
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
			celda1.setCellValue("N° de Requerimiento");
			celda2.setCellValue("N° de Expediente");
			celda3.setCellValue("Proceso");
			celda4.setCellValue("Tipo de Expediente");
			celda5.setCellValue("Estado del Requerimiento");
			celda6.setCellValue("Fecha del Expediente");
			celda7.setCellValue("Fecha del Requerimiento");
						
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
				//inicio lestrada se modifico ubicacion
				fila = hoja.createRow(i + 5);
				//Fin lestrada se modifico ubicacion
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(String.valueOf(i+1));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRequerimOrigen")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("numRequerimOrigen").toString().trim()));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("numExpedienteOrigen").toString().trim()));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				//Inicio Lestrada OBS SNADE307-1065
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("desProceso")==null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("desProceso").toString().trim())));
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("desTipoExpediente")==null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString().trim())));
				//Fin Lestrada OBS SNADE307-1065
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("desEstado").toString().trim()));
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);				
				celda6 = fila.createCell(6);
				celda6.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("fechaDocumentoOrigen").toString().trim()));
				hoja.autoSizeColumn(6);
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(7);
				celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaRequerimiento")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("fechaRequerimiento").toString().trim()));
				hoja.autoSizeColumn(7);
				celda7.setCellStyle(estiloRecorrido);
			}
			//Fin [gangles 11/08/2016]
			
		    Calendar calendar = Calendar.getInstance();
			int anio = (calendar.get(Calendar.YEAR));
			int dia = (calendar.get(Calendar.DATE));
			int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
			int minutos = (calendar.get(Calendar.MINUTE));
			String dd = (dia<10) ? "0"+dia : dia+"";
			int mes = calendar.get(Calendar.MONTH)+1;
			String mm = (mes<10) ? "0"+mes : mes+"";
			String part=anio+mm+dd+"_"+hora+minutos;
			  
			String filename = CatalogoConstantes.RPT_GEN_CONSULTA_REQ_XLS + part + ".xls";;
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
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelDocumentos(HttpServletRequest request, HttpServletResponse response){
	     
		   String titulo = ExportaConstantes.TITULO_EXPORTA_02_05;
		   ModelAndView view = null;
		   MensajeBean mensajeBean = new MensajeBean();
		   String listadoExportarCadena = null;
		   //Inicio [gangles 22/08/2016]
		   String numExpOrigen="";
	       String numExpVirtual="";
	       String numRuc="";
	       String razonSocial="";
	       String tipoProceso="";
	       String tipoExpediente="" ;
	       String fechaDocOrigen="";
	       String fechaRegExp="";
	       String fechaVencimiento="";
	       String numRequerimiento="";
	       String estRequerimiento="";
	       String fechaRequerimiento="";
	       
	       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	       Date fechaActual = new Date();
	       String fecImpresion = sdf.format(fechaActual);
	       //Fin [gangles 22/08/2016]
		       
		   try {
		             
		    listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
			//Inicio [gangles 22/08/2016]
		    numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
	    	numExpVirtual=Utils.toStr(request.getParameter("hiddenNumExpVirtualDoc"));
	    	numRuc=Utils.toStr(request.getParameter("hiddenNumRuc"));
	    	razonSocial=Utils.toStr(request.getParameter("hiddenRazonSocial").trim());
		    tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
		    tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
		    fechaDocOrigen=Utils.toStr(request.getParameter("hiddenFechaDocOrigen"));
		    fechaRegExp=Utils.toStr(request.getParameter("hiddenFechaRegExp"));
		    fechaVencimiento=Utils.toStr(request.getParameter("hiddenFechaVencimiento"));
		    numRequerimiento=Utils.toStr(request.getParameter("hiddenNumRequerimiento"));
		    estRequerimiento=Utils.toStr(request.getParameter("hiddenEstRequerimiento"));
		    fechaRequerimiento=Utils.toStr(request.getParameter("hiddenFechaRequerimiento"));
		    //Fin [gangles 22/08/2016]
		    
			@SuppressWarnings("unchecked")
			List<T6613DocExpVirtBean> listadoExportar = (ArrayList<T6613DocExpVirtBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
		    	   
		    response.setContentType("application/vnd.ms-excel");
		    HSSFWorkbook libro = new HSSFWorkbook();
		    HSSFSheet hoja = libro.createSheet("Hoja 1");
	        hoja.setColumnWidth(0, 2500);
		    hoja.setColumnWidth(1, 2500);
		    hoja.setColumnWidth(2, 4800);
		    hoja.setColumnWidth(3, 4500);
		    hoja.setColumnWidth(4, 4800);
		    hoja.setColumnWidth(5, 16000);
		    hoja.setColumnWidth(6, 16000);
		    hoja.setColumnWidth(7, 16000);
		    hoja.setColumnWidth(8, 16000);
		    hoja.setColumnWidth(9, 16000);
		    hoja.setColumnWidth(10, 16000);
		       
		    /*HSSFRow fila = hoja.createRow(1);
		    HSSFCell tituloCelda = fila.createCell(0);
		    tituloCelda.setCellValue(titulo);
		    hoja.addMergedRegion(new Region(1,(short)0,1,(short)3));*/
		  
		    // Inicio [gangles 22/08/2016]
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
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(numRuc+"-"+razonSocial);
			rango2 = new CellRangeAddress(2, 2, 2,3);
			hoja.addMergedRegion(rango2);

			fila = hoja.createRow(4);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(tipoProceso);

			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido6 = fila.createCell(4);
			contenido6.setCellValue(tipoExpediente);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda7 = fila.createCell(1);
			subtituloCelda7.setCellValue("N° Expediente:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(numExpOrigen);

			HSSFCell subtituloCelda10 = fila.createCell(3);
			subtituloCelda10.setCellValue("Fecha del Expediente:");
			HSSFCell contenido10 = fila.createCell(4);
			contenido10.setCellValue(fechaRegExp);
			
			fila = hoja.createRow(6);				

			HSSFCell subtituloCelda12 = fila.createCell(1);
			subtituloCelda12.setCellValue("Número de Requerimiento:");
			HSSFCell contenido12 = fila.createCell(2);
			contenido12.setCellValue(numRequerimiento);
			
			HSSFCell subtituloCelda9 = fila.createCell(3);
			subtituloCelda9.setCellValue("Estado del Requerimiento:");
			HSSFCell contenido9 = fila.createCell(4);
			contenido9.setCellValue(estRequerimiento);
			
			fila = hoja.createRow(7);				

			HSSFCell celdafechaReq = fila.createCell(1);
			celdafechaReq.setCellValue("Fecha del Requerimiento:");
			HSSFCell celdafechaReqVal = fila.createCell(2);
			celdafechaReqVal.setCellValue(fechaRequerimiento);
			
			HSSFCell celdaFecVencimiento = fila.createCell(3);
			celdaFecVencimiento.setCellValue("Fecha de Vencimiento:");
			HSSFCell celdaFecVencimientoVal = fila.createCell(4);
			celdaFecVencimientoVal.setCellValue(fechaVencimiento);
			
			//Fin [gangles 22/08/2016]
		    fila = hoja.createRow(9);
		    HSSFCell celda = fila.createCell(0);
		    HSSFCell celda1 = fila.createCell(1);
		    //Inicio [gangles 22/08/2016]
	        CellRangeAddress rangoDocumento = null;
	        rangoDocumento = new CellRangeAddress(9, 9, 1, 2);
			hoja.addMergedRegion(rangoDocumento);
			 //Fin [gangles 22/08/2016]
		    HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Nombre del documento");
	        celda3.setCellValue("Fecha de Subida");
	        celda4.setCellValue("Estado del Documento");
		
	        HSSFFont fuente = libro.createFont();
	        fuente.setFontHeightInPoints((short) 11);
	        fuente.setFontName(fuente.FONT_ARIAL);
	        
	        HSSFFont fuenteBold = libro.createFont();
	        fuenteBold.setFontHeightInPoints((short) 11);
	        fuenteBold.setFontName(fuente.FONT_ARIAL);
	        fuenteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        Sheet ssheet = libro.getSheetAt(0);
	        ssheet.autoSizeColumn(0);
	        ssheet.autoSizeColumn(1);
	        ssheet.autoSizeColumn(2);
	        ssheet.autoSizeColumn(3);
	        ssheet.autoSizeColumn(4);
	        
	        HSSFCellStyle estiloCelda = libro.createCellStyle();
		       
	       estiloCelda.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
	       HSSFCellStyle estiloTitulo = libro.createCellStyle();
	       estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	       estiloTitulo.setFont(fuenteBold);
	        
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
	       int cont=0;
		   for (T6613DocExpVirtBean t6613DocExpVirtBean : listadoExportar) {
			   
               fila = hoja.createRow(cont + 10);
               celda = fila.createCell(0);
               texto = new HSSFRichTextString(Utils.toStr(t6613DocExpVirtBean.getCorrelativo()));
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.convertirUnicode(Utils.toStr(t6613DocExpVirtBean.getDesTipdoc())));
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
             //Inicio [gangles 22/08/2016]
  	        	CellRangeAddress rangoODocumentoVal = null;
  	        	rangoODocumentoVal = new CellRangeAddress(cont + 10, cont + 10, 1, 2);
  	        	hoja.addMergedRegion(rangoODocumentoVal);
  	        	celda2 = fila.createCell(2);
  	        	celda2.setCellStyle(estiloRecorrido);
  			 //Fin [gangles 22/08/2016]
               celda3 = fila.createCell(3);
               celda3.setCellValue(Utils.toStr(t6613DocExpVirtBean.getStrFechaCarga() ));
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.toStr(t6613DocExpVirtBean.getDesEstadoDocumento()));
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               cont++;
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
           
	         String filename = CatalogoConstantes.RPT_GEN_CONSULTA_DOC_REQ_XLS + part + ".xls";;
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
	
	@RequestMapping(value = "/descargarArchivo", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivo (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaRequerimientoController.descargarArchivo");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os=null;
		String nombreArchivo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaRequerimientoController.descargarArchivo");
			
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
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaRequerimientoController.descargarArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaRequerimientoController.descargarArchivo");
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

	public void setExpedienteVirtualService(
			ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
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
	
	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}
}