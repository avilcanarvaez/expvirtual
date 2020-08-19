package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
// Inicio [gangles 26/05/2016] 11) Incluir los formatos adicionales detallados en el Anexo Nº 1.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
//Fin [gangles 26/05/2016] 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6612DetPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteTrabReporteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ManttoPlanRepService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ManttoPlanRepServiceImpl;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;


public class ManttoPlantillaReporteController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(ManttoPlantillaReporteController.class);
	private JsonView jsonView;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ManttoPlanRepService manttoPlanRepService;
	private ExpedienteTrabReporteService expedienteTrabReporteService; // Inicio [gangles 26/05/2016] 13) Incluir opción de pre-visualización e impresión del contenido de la plantilla creada. Fin [gangles 26/05/2016]

	public ModelAndView cargarBandPlanExpe (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ManttoPlantillaReporteController.cargarBandPlanExpe");
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		
		try {
			String indCarga = request.getParameter("indCarga");
		
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaActual = new Date();
			    String fecVista = sdf.format(fechaActual);
			    
			    Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_06_002);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion1", AvisoConstantes.AVISO_MODULO_05_01_001);
				excepciones.put("excepcion2", AvisoConstantes.AVISO_MODULO_05_01_002);
				excepciones.put("excepcion3", AvisoConstantes.AVISO_MODULO_05_01_003);
				excepciones.put("excepcion4", AvisoConstantes.AVISO_MODULO_05_01_004);
				excepciones.put("excepcion5", AvisoConstantes.AVISO_MODULO_05_01_005);
				excepciones.put("excepcion6", AvisoConstantes.AVISO_MODULO_05_01_006);
				excepciones.put("excepcion7", AvisoConstantes.AVISO_MODULO_05_01_007);
				excepciones.put("excepcion8", AvisoConstantes.AVISO_MODULO_05_04_001);
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
	
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_01_001);
				
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				modelo.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
				
			}else if(ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)){
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				String codProceso = dataEnvio.get("codProceso").toString();
				
				parametros.put("codProceso", codProceso);
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(parametros);
				
				modelo.addObject("listadoTiposExpendientes", listadoTiposExpendientes);
				modelo.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ManttoPlantillaReporteController.cargarBandPlanExpe");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ManttoPlantillaReporteController.cargarBandPlanExpe");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	@RequestMapping(value = "/cargarDatosBusquedaSession", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ManttoPlantillaReporteController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ManttoPlantillaReporteController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ManttoPlantillaReporteController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ManttoPlantillaReporteController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	public ModelAndView cargarListPlantillas(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ManttoPlantillaReporteController.cargarListPlantillas");
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		
		try {
			String indCarga = request.getParameter("indCarga");
			String indFiltro = request.getParameter("indFiltro");
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
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
				
				Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
				
				mapParametrosBusqueda.put("codProceso", dataEnvio.get("codProceso").toString());
				mapParametrosBusqueda.put("codTipoExp", dataEnvio.get("codTipoExp").toString());
								
				if (ValidaConstantes.FILTRO_UNO.equals(indFiltro)) {
					
					Date dateDesde = Utils.stringToDate(Utils.toStr(dataEnvio.get("fechaDesde")), CatalogoConstantes.INT_TWO);
					Date dateHasta = Utils.stringToDate(Utils.toStr(dataEnvio.get("fechaHasta")), CatalogoConstantes.INT_TWO);
					
					Calendar calendarDesde=Calendar.getInstance();
					calendarDesde.setTime(dateDesde);
					calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
					calendarDesde.set(Calendar.MINUTE, 0);
					calendarDesde.set(Calendar.SECOND, 0);
					dateDesde=calendarDesde.getTime();
					mapParametrosBusqueda.put("fechaDesde", dateDesde);
					
					Calendar calendarHasta=Calendar.getInstance();
					calendarHasta.setTime(dateHasta);
					calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
					calendarHasta.set(Calendar.MINUTE, 59);
					calendarHasta.set(Calendar.SECOND, 59);
					dateHasta=calendarHasta.getTime();
					mapParametrosBusqueda.put("fechaHasta", dateHasta);
					
				}
				
				List<T6611CabPlantiBean> listPlantillas = manttoPlanRepService.listarPlantillas(mapParametrosBusqueda);
				
				modelo.addObject("listPlantillas", listPlantillas);
				
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ManttoPlantillaReporteController.cargarListPlantillas");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ManttoPlantillaReporteController.cargarListPlantillas");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	@RequestMapping(value = "/crearPlantillaView", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView crearPlantillaView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - ManttoPlantillaReporteController.crearPlantillaView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_05_03_001);
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_03_001);
			
			List<T01ParamBean> listadoFormatosAdicionales = configuracionExpedienteService.listarFormatosAdicionales();
			List<T01ParamBean> listadoReportesTributariosAduaneros = configuracionExpedienteService.listarRepTribuAduaneros();
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_05_03_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_05_03_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_05_03_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_05_03_004);
			excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_05_03_005);
			excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_05_03_006);
			excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_05_03_007);
			
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize( listadoProcesos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("fechaActual",Utils.dateUtilToStringDDMMYYYY(new Date()));
			
			modelAndView.addObject("listadoFormatosAdicionales",new JsonSerializer().serialize(listadoFormatosAdicionales));
			modelAndView.addObject("listadoReportesTributariosAduaneros",new JsonSerializer().serialize(listadoReportesTributariosAduaneros));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ManttoPlantillaReporteController.crearPlantillaView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ManttoPlantillaReporteController.crearPlantillaView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/modificaPlantillaView", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView modificaPlantillaView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) { log.info( "Inicio - ManttoPlantillaReporteController.modificaPlantillaView");}
		
		List<T6623TipDocExpBean> listaTipDocExp = new ArrayList<T6623TipDocExpBean>();
		List<T6624TipExpProcBean> listadoTiposExpendientes = new ArrayList<T6624TipExpProcBean>();
		List<T01ParamBean> listadoProcesos = null;
		String numPlantilla="";
		List<T6611CabPlantiBean> listT6611CabPlantiBean;
		List<T6612DetPlantiBean> listT6612documentos;
		List<T6612DetPlantiBean> listT6612FormatosAdicionales;
		List<T6612DetPlantiBean> listT6612ReportesTribAdua;
		String codigoProceso="";
		String codTipExpediente="";
		String desPlantilla="";
		String opcionSeleccionada="";
		int numOrden = 1;
		List<T6623TipDocExpBean> listaTipDocExpTemp = null;
		
		try {
			numPlantilla = Utils.toStr(request.getParameter("numPlantilla"));
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			listT6611CabPlantiBean = manttoPlanRepService.listarPlantillasPorExpe(parametros);
			for (T6611CabPlantiBean t6611CabPlantiBean : listT6611CabPlantiBean) {
				codigoProceso = t6611CabPlantiBean.getCodProceso();
				codTipExpediente = t6611CabPlantiBean.getCodTipoExpediente();
				desPlantilla =  t6611CabPlantiBean.getDesPlantilla();
				opcionSeleccionada = t6611CabPlantiBean.getIndRepTrib();
			}
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			
			mapParametrosBusqueda.put("codTipoExpediente", codTipExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			mapParametrosBusqueda.put("listIndTipDoc", listIndTipDoc);
			//mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);			
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);			
			listaTipDocExpTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			
			if(listaTipDocExpTemp != null) {
				listaTipDocExp.addAll(listaTipDocExpTemp);
			}
			
			mapParametrosBusqueda = new HashMap<String, Object>();
			
			mapParametrosBusqueda.put("codTipoExpediente", codTipExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			//mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);			
			mapParametrosBusqueda.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			listaTipDocExpTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			
			if(listaTipDocExpTemp != null) {
				listaTipDocExp.addAll(listaTipDocExpTemp);
			}
			
			for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
				t6623TipDocExpBean.setNumOrden(numOrden);
				numOrden++;
			}
			
		
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelDoc", ValidaConstantes.FILTRO_UNO);
			listT6612documentos = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelForma", ValidaConstantes.FILTRO_UNO);
			listT6612FormatosAdicionales = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelRubro", ValidaConstantes.FILTRO_UNO);
			listT6612ReportesTribAdua = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			parametros = new HashMap<String, Object>();
			parametros.put("codProceso", codigoProceso);
			parametros.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_03_002);
			
			List<T01ParamBean> listadoFormatosAdicionales = configuracionExpedienteService.listarFormatosAdicionales();
			List<T01ParamBean> listadoReportesTributariosAduaneros = configuracionExpedienteService.listarRepTribuAduaneros();
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_05_03_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_05_03_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_05_03_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_05_03_004);
			excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_05_03_005);
			excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_05_03_006);
			excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_05_03_007);
			
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_05_03_002);
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoTiposExpendientes",new JsonSerializer().serialize(listadoTiposExpendientes));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("fechaActual",Utils.dateUtilToStringDDMMYYYY(new Date()));
			modelAndView.addObject("codigoProceso",codigoProceso);
			modelAndView.addObject("codTipExpediente",codTipExpediente);
			modelAndView.addObject("numPlantilla",numPlantilla);
			modelAndView.addObject("desPlantilla",desPlantilla);
			modelAndView.addObject("opcionSeleccionada",opcionSeleccionada);
			modelAndView.addObject("listaTipDocExp",new JsonSerializer().serialize(listaTipDocExp));
			modelAndView.addObject("listT6612documentos",new JsonSerializer().serialize(listT6612documentos));
			modelAndView.addObject("listT6612FormatosAdicionales",new JsonSerializer().serialize(listT6612FormatosAdicionales));
			modelAndView.addObject("listT6612ReportesTribAdua",new JsonSerializer().serialize(listT6612ReportesTribAdua));
			
			modelAndView.addObject("listadoFormatosAdicionales",new JsonSerializer().serialize(listadoFormatosAdicionales));
			modelAndView.addObject("listadoReportesTributariosAduaneros",new JsonSerializer().serialize(listadoReportesTributariosAduaneros));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ManttoPlantillaReporteController.modificaPlantillaView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ManttoPlantillaReporteController.modificaPlantillaView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/verPlantillaView", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView verPlantillaView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) { log.info( "Inicio - ManttoPlantillaReporteController.verPlantillaView");}
		
		List<T6623TipDocExpBean> listaTipDocExp = new ArrayList<T6623TipDocExpBean>();
		List<T6624TipExpProcBean> listadoTiposExpendientes = new ArrayList<T6624TipExpProcBean>();
		List<T01ParamBean> listadoProcesos = null;
		List<T6623TipDocExpBean> listaTemp = null;
		String numPlantilla="";
		List<T6611CabPlantiBean> listT6611CabPlantiBean;
		List<T6612DetPlantiBean> listT6612documentos;
		List<T6612DetPlantiBean> listT6612FormatosAdicionales;
		List<T6612DetPlantiBean> listT6612ReportesTribAdua;
		String codigoProceso="";
		String codTipExpediente="";
		String desPlantilla="";
		String opcionSeleccionada="";
		int numOrden = 1;
		String flagReporteTrib="0";
		try {
			numPlantilla = Utils.toStr(request.getParameter("numPlantilla"));
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			listT6611CabPlantiBean = manttoPlanRepService.listarPlantillasPorExpe(parametros);
			for (T6611CabPlantiBean t6611CabPlantiBean : listT6611CabPlantiBean) {
				codigoProceso = t6611CabPlantiBean.getCodProceso();
				codTipExpediente = t6611CabPlantiBean.getCodTipoExpediente();
				desPlantilla =  t6611CabPlantiBean.getDesPlantilla();
				opcionSeleccionada = t6611CabPlantiBean.getIndRepTrib();
			}
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
					
			mapParametrosBusqueda.put("codTipoExpediente", codTipExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			mapParametrosBusqueda.put("listIndTipDoc", listIndTipDoc);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			
			listaTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			if(listaTemp != null) {
				listaTipDocExp.addAll(listaTemp);
			}
			mapParametrosBusqueda.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			
			listaTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			if(listaTemp != null) {
				listaTipDocExp.addAll(listaTemp);
			}
			// Inicio [gangles 24/05/2016] Se comenta el código por el cambio realizado (10) Plantilla para Reportes: La consulta del contenido de una plantilla existente sólo debe mostrar los documentos que la conforman.)
			/*for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
				t6623TipDocExpBean.setNumOrden(numOrden);
				numOrden++;
			}			
			*/
			//Fin [gangles 24/05/2016] 
			
			List<T01ParamBean> listadoFormatosAdicionales = configuracionExpedienteService.listarFormatosAdicionales();
			List<T01ParamBean> listadoReportesTributariosAduaneros = configuracionExpedienteService.listarRepTribuAduaneros();
		
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelDoc", ValidaConstantes.FILTRO_UNO);
			listT6612documentos = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			// Inicio [gangles 24/05/2016] Cambio realizado - (10) Plantilla para Reportes: La consulta del contenido de una plantilla existente sólo debe mostrar los documentos que la conforman.
			for (T6612DetPlantiBean t6612DetPlantiBean : listT6612documentos) {
				
				for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
					if(t6623TipDocExpBean.getCodTipoDocumento().equals(t6612DetPlantiBean.getCodTipoDocumento())){
						t6612DetPlantiBean.setDesTipdoc(t6623TipDocExpBean.getDesTipoDocumento());						
					}
				}
				t6612DetPlantiBean.setNumOrden(numOrden);
				numOrden++;				
			}			
			//Fin [gangles 24/05/2016] 
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelForma", ValidaConstantes.FILTRO_UNO);
			listT6612FormatosAdicionales = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			// Inicio [gangles 24/05/2016] Cambio realizado - (10) Plantilla para Reportes: La consulta del contenido de una plantilla existente sólo debe mostrar los documentos que la conforman.
			numOrden=1;
			for (T6612DetPlantiBean t6612DetPlantiBean : listT6612FormatosAdicionales) {
				
				for (T01ParamBean T01ParamBean : listadoFormatosAdicionales) {
					if(T01ParamBean.getCodParametro().equals(t6612DetPlantiBean.getCodFormatoAdi().trim())){
						t6612DetPlantiBean.setDesFormato(T01ParamBean.getDesParametro());						
					}
				}
				t6612DetPlantiBean.setNumOrden(numOrden);
				numOrden++;				
			}
			//Fin [gangles 24/05/2016] 
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelRubro", ValidaConstantes.FILTRO_UNO);
			listT6612ReportesTribAdua = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			// Inicio [gangles 24/05/2016] Cambio realizado - (10) Plantilla para Reportes: La consulta del contenido de una plantilla existente sólo debe mostrar los documentos que la conforman.
			numOrden=1;
			for (T6612DetPlantiBean t6612DetPlantiBean : listT6612ReportesTribAdua) {
					
				for (T01ParamBean T01ParamBean : listadoReportesTributariosAduaneros) {
					if(T01ParamBean.getCodParametro().equals(t6612DetPlantiBean.getCodRepTrbib().trim())){
						t6612DetPlantiBean.setDesRubro(T01ParamBean.getDesParametro());						
					}
				}
				t6612DetPlantiBean.setNumOrden(numOrden);
				numOrden++;
				flagReporteTrib="1";
			}				
			//Fin [gangles 24/05/2016] 
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			parametros = new HashMap<String, Object>();
			parametros.put("codProceso", codigoProceso);
			parametros.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
						
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_03_001);
							
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_05_03_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_05_03_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_05_03_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_05_03_004);
			excepciones.put("excepcion05",AvisoConstantes.EXCEP_MODULO_05_03_005);
			excepciones.put("excepcion06",AvisoConstantes.EXCEP_MODULO_05_03_006);
			excepciones.put("excepcion07",AvisoConstantes.EXCEP_MODULO_05_03_007);			
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_05_03_003);
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoTiposExpendientes",new JsonSerializer().serialize(listadoTiposExpendientes));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("fechaActual",Utils.dateUtilToStringDDMMYYYY(new Date()));
			modelAndView.addObject("codigoProceso",codigoProceso);
			modelAndView.addObject("codTipExpediente",codTipExpediente);
			modelAndView.addObject("numPlantilla",numPlantilla);
			modelAndView.addObject("desPlantilla",desPlantilla);
			modelAndView.addObject("opcionSeleccionada",opcionSeleccionada);
			modelAndView.addObject("listaTipDocExp",new JsonSerializer().serialize(listaTipDocExp));
			modelAndView.addObject("listT6612documentos",new JsonSerializer().serialize(listT6612documentos));
			modelAndView.addObject("listT6612FormatosAdicionales",new JsonSerializer().serialize(listT6612FormatosAdicionales));
			modelAndView.addObject("listT6612ReportesTribAdua",new JsonSerializer().serialize(listT6612ReportesTribAdua));
			modelAndView.addObject("listadoFormatosAdicionales",new JsonSerializer().serialize(listadoFormatosAdicionales));
			modelAndView.addObject("listadoReportesTributariosAduaneros",new JsonSerializer().serialize(listadoReportesTributariosAduaneros));
			modelAndView.addObject("flagReporteTrib",new JsonSerializer().serialize(flagReporteTrib));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ManttoPlantillaReporteController.verPlantillaView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ManttoPlantillaReporteController.verPlantillaView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = { "/cargarListaTiposExpediente" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - ManttoPlantillaReporteController.cargarListaTiposExpediente");
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
				log.debug((Object) "Error - ManttoPlantillaReporteController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ManttoPlantillaReporteController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/obtenerDocumentos" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView obtenerDocumentos(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView;
		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.obtenerDocumentos");
		
		List<T6623TipDocExpBean> listaTipDocExp = new ArrayList<T6623TipDocExpBean>();
		List<T6623TipDocExpBean> listaTemp = null;
		String codigoTipoExpediente="";
		int numOrden = 1;
		T6623TipDocExpBean t6623TipDocExpBeanTemp;
		try {
			
			modelAndView = new ModelAndView(jsonView);
			codigoTipoExpediente = Utils.toStr(request.getParameter("codTipexp"));
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			
			mapParametrosBusqueda.put("codTipoExpediente", codigoTipoExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			//mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			mapParametrosBusqueda.put("listIndTipDoc", listIndTipDoc);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			
			listaTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			if(listaTemp != null) {
				listaTipDocExp.addAll(listaTemp);
			}
			
			mapParametrosBusqueda.put("codTipoExpediente", codigoTipoExpediente);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			mapParametrosBusqueda.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO);
			//mapParametrosBusqueda.put("indVisibleContribuyente", ValidaConstantes.IND_VISIBLE_CONTRIBUYENTE);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			
			listaTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			if(listaTemp != null) {
				listaTipDocExp.addAll(listaTemp);
			}
			
			for (T6623TipDocExpBean t6623TipDocExpBean : listaTipDocExp) {
				t6623TipDocExpBean.setNumOrden(numOrden);
				t6623TipDocExpBean.setEstadoSeleccion(ValidaConstantes.FILTRO_CERO);
				numOrden++;
			}
			
			modelAndView.addObject("listaTipDocExp",listaTipDocExp);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - ManttoPlantillaReporteController.obtenerDocumentos");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - ManttoPlantillaReporteController.obtenerDocumentos");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/registrarPlantilla" }, method = { RequestMethod.POST })
	public ModelAndView registrarPlantilla(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.registrarPlantilla");
		ModelAndView modelAndView = null;
		List<Map<String, Object>> listaDocumentos;
		List<Map<String, Object>> listaFormatos;
		List<Map<String, Object>> listaReportes;
		
		try {
			
			
			//request.setCharacterEncoding("UTF-8");
			String desPlantilla = Utils.toStr(request.getParameter("desPlantilla"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";

			if (reader != null) {

				jsonEnviado = reader.readLine();

			}

			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			String listaDocumentosSel = Utils.toStr(dataEnvio.get("listaDocumentosSel"));
			String listaFormatosSel = Utils.toStr(dataEnvio.get("listaFormatosSel"));
			String listaReportesSel = Utils.toStr(dataEnvio.get("listaReportesSel"));
			String codProceso = Utils.toStr(dataEnvio.get("codProceso"));
			String codTipExpediente = Utils.toStr(dataEnvio.get("codTipExpediente"));
			String codOpcion = Utils.toStr(dataEnvio.get("codOpcion"));
			
			
			/*Lista documentos seleccionados**/
			/*String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel"));
			String listaFormatosSel = Utils.toStr(request.getParameter("listaFormatosSel"));
			String listaReportesSel = Utils.toStr(request.getParameter("listaReportesSel"));
			String desPlantilla = Utils.toStr(request.getParameter("dessPlanReporte"));
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String codOpcion = Utils.toStr(request.getParameter("codOpcion"));*/
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			listaDocumentos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			listaFormatos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaFormatosSel, java.util.Map.class);
			listaReportes  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaReportesSel, java.util.Map.class);
								
			HashMap<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("listaDocumentos", listaDocumentos);
			mapParametros.put("listaFormatos", listaFormatos);
			mapParametros.put("listaReportes", listaReportes);
			mapParametros.put("desPlantilla", desPlantilla);
			mapParametros.put("codProceso", codProceso);
			mapParametros.put("codTipExpediente", codTipExpediente);
			mapParametros.put("codOpcion", codOpcion);
			mapParametros.put("usuarioRegistro", usuarioBean.getNroRegistro());
			
			String codigoPlantilla = manttoPlanRepService.registrarPlantilla(mapParametros);
			modelAndView= new ModelAndView(jsonView);
			modelAndView.addObject("codigoPlantilla", codigoPlantilla);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled())log.debug("Error - ManttoPlantillaReporteController.registrarPlantilla");

			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.EXCEP_MODULO_02_01_007);
			modelAndView.addObject("beanErr",  msb);
			
		} finally {
			
			if (log.isDebugEnabled())log.debug( "Final - ManttoPlantillaReporteController.registrarPlantilla");
			
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = { "/modificarPlantilla" }, method = { RequestMethod.POST })
	public ModelAndView modificarPlantilla(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.modificarPlantilla");
		ModelAndView modelAndView = null;
		List<Map<String, Object>> listaDocumentos;
		List<Map<String, Object>> listaFormatos;
		List<Map<String, Object>> listaReportes;
		
		try {
			
			/*Lista documentos seleccionados**/
			/*String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel"));
			String listaFormatosSel = Utils.toStr(request.getParameter("listaFormatosSel"));
			String listaReportesSel = Utils.toStr(request.getParameter("listaReportesSel"));
			String desPlantilla = Utils.toStr(request.getParameter("dessPlanReporte"));
			String codProceso = Utils.toStr(request.getParameter("codigoProceso"));
			String codTipExpediente = Utils.toStr(request.getParameter("codigoTipoExpediente"));
			String codOpcion = Utils.toStr(request.getParameter("codOpcion"));
			String numPlantilla = Utils.toStr(request.getParameter("numPlantilla"));*/
			
			String desPlantilla = Utils.toStr(request.getParameter("desPlantilla"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";

			if (reader != null) {

				jsonEnviado = reader.readLine();

			}

			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			String listaDocumentosSel = Utils.toStr(dataEnvio.get("listaDocumentosSel"));
			String listaFormatosSel = Utils.toStr(dataEnvio.get("listaFormatosSel"));
			String listaReportesSel = Utils.toStr(dataEnvio.get("listaReportesSel"));
			String codProceso = Utils.toStr(dataEnvio.get("codProceso"));
			String codTipExpediente = Utils.toStr(dataEnvio.get("codTipExpediente"));
			String codOpcion = Utils.toStr(dataEnvio.get("codOpcion"));
			String numPlantilla = Utils.toStr(dataEnvio.get("numPlantilla"));
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			listaDocumentos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			listaFormatos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaFormatosSel, java.util.Map.class);
			listaReportes  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaReportesSel, java.util.Map.class);
		
			HashMap<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("listaDocumentos", listaDocumentos);
			mapParametros.put("listaFormatos", listaFormatos);
			mapParametros.put("listaReportes", listaReportes);
			mapParametros.put("desPlantilla", desPlantilla);
			mapParametros.put("codProceso", codProceso);
			mapParametros.put("codTipExpediente", codTipExpediente);
			mapParametros.put("codOpcion", codOpcion);
			mapParametros.put("numPlantilla", numPlantilla);
			mapParametros.put("usuarioRegistro", usuarioBean.getNroRegistro());
			
			/**CABECERA DE LA PLANTILLA**/
			
			List<T6611CabPlantiBean> listT6611CabPlantiBean;
			List<T6612DetPlantiBean> listT6612documentos;
			List<T6612DetPlantiBean> listT6612FormatosAdicionales;
			List<T6612DetPlantiBean> listT6612ReportesTribAdua;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("numPlantilla", numPlantilla);
			listT6611CabPlantiBean = manttoPlanRepService.listarPlantillasPorExpe(parametros);
			
			/**detalle de plantillas anteriores**/
			parametros = new HashMap<String, Object>();
			
			
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelDoc", ValidaConstantes.FILTRO_UNO);
			listT6612documentos = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelForma", ValidaConstantes.FILTRO_UNO);
			listT6612FormatosAdicionales = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			
			parametros = new HashMap<String, Object>();
			parametros.put("numPlantilla", numPlantilla);
			parametros.put("indicadorSelRubro", ValidaConstantes.FILTRO_UNO);
			listT6612ReportesTribAdua = manttoPlanRepService.listarDetPlantillasPorNumPlantilla(parametros);
			/*******************/
			
			mapParametros.put("listT6611CabPlantiBean", listT6611CabPlantiBean);
			mapParametros.put("listT6612documentos", listT6612documentos);
			mapParametros.put("listT6612FormatosAdicionales", listT6612FormatosAdicionales);
			mapParametros.put("listT6612ReportesTribAdua", listT6612ReportesTribAdua);
			
			String codigoPlantilla = manttoPlanRepService.modificarPlantilla(mapParametros);
			modelAndView= new ModelAndView(jsonView);
			modelAndView.addObject("codigoPlantilla", codigoPlantilla);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled())log.debug("Error - ManttoPlantillaReporteController.modificarPlantilla");

			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.EXCEP_MODULO_02_01_007);
			modelAndView.addObject("beanErr",  msb);
			
		} finally {
			
			if (log.isDebugEnabled())log.debug( "Final - ManttoPlantillaReporteController.modificarPlantilla");
			
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	public ModelAndView getBajaPlantilla(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ManttoPlantillaReporteController.getBajaPlantilla");
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		
		try {
			String indCarga = request.getParameter("indCarga");
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
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
				
				parametros.put("numPlantilla", dataEnvio.get("numPlant").toString());
				
				parametros.put("estadoPlant", ValidaConstantes.IND_ESTADO_PLANTILLA_INACTIVO);
				
				parametros.put("codUsuBaja", usuarioBean.getNroRegistro().toString().trim());
				
				manttoPlanRepService.darBajaPlantilla(parametros);
				
				modelo.addObject("flagAccion", ValidaConstantes.FILTRO_CERO);
				
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ManttoPlantillaReporteController.getBajaPlantilla");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			modelo.addObject("flagAccion", ValidaConstantes.FILTRO_UNO);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ManttoPlantillaReporteController.getBajaPlantilla");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
	// Inicio [gangles 26/05/2016] 13) Incluir opción de pre-visualización e impresión del contenido de la plantilla creada.
	@RequestMapping(value = { "/previsualizarPlantilla" }, method = { RequestMethod.POST })
	public void previsualizarPlantilla(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - ManttoPlantillaReporteController.previsualizarPlantilla");
		ModelAndView modelAndView = null;
		List<Map<String, Object>> listaDocumentos;
		List<Map<String, Object>> listaFormatos;
		List<Map<String, Object>> listaReportes;
		List<Map<String, Object>> detalle;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
	    String fecha = sdf.format(fechaActual);
	    
		try {
			//Lista documentos seleccionados
			String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel"));
			String listaFormatosSel = Utils.toStr(request.getParameter("listaFormatosSel"));
			String listaReportesSel = Utils.toStr(request.getParameter("listaReportesSel"));
			String desPlantilla = Utils.toStr(request.getParameter("desPlanReporte"));
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String codOpcion = Utils.toStr(request.getParameter("codOpcion"));
			String descProceso = Utils.toStr(request.getParameter("descProceso"));
			String descTipoExpediente = Utils.toStr(request.getParameter("descTipoExpediente"));
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

			JsonSerializer jsonSerializer=new JsonSerializer();
			Map<String, Object> objJsonReportParam=new LinkedHashMap<String, Object>();
			Map<String, Object> objJsonCabecera=new LinkedHashMap<String, Object>();
			Map<String, Object> objJsonDetalle=new LinkedHashMap<String, Object>();
			
			listaDocumentos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			listaFormatos  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaFormatosSel, java.util.Map.class);
			listaReportes  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaReportesSel, java.util.Map.class);
			detalle  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaReportesSel, java.util.Map.class);
//			detalle  = new ArrayList<Map<String,Object>>(0);
			objJsonCabecera.put("descProceso", descProceso);
			objJsonCabecera.put("descTipoExpediente", descTipoExpediente);
			objJsonCabecera.put("desPlantilla", desPlantilla);
			objJsonCabecera.put("fecha", fecha);
			
			List<Map<String, Object>> listaDocumentosJSON=null; 
			Map<String,Object> objDocumentoJSON=null;
				
			listaDocumentosJSON=new ArrayList<Map<String,Object>>();
			for (int i = 0; i < listaDocumentos.size(); i++) {		        
				objDocumentoJSON=new LinkedHashMap<String, Object>();
				objDocumentoJSON.put("codTipoDocumento",Utils.convertirUnicode(listaDocumentos.get(i).get("codTipoDocumento").toString().trim()));
				objDocumentoJSON.put("desTipoDocumento",Utils.convertirUnicode(listaDocumentos.get(i).get("desTipoDocumento").toString().trim()));				
				listaDocumentosJSON.add(objDocumentoJSON);
			}
			
			List<Map<String, Object>> listaFormatosJSON=null; 
			Map<String,Object> objFormatosJSON=null;
				
			listaFormatosJSON=new ArrayList<Map<String,Object>>();			
			for (int i = 0; i < listaFormatos.size(); i++) {	
				objFormatosJSON=new LinkedHashMap<String, Object>();
				objFormatosJSON.put("codFormato",Utils.convertirUnicode(listaFormatos.get(i).get("codFormato").toString().trim()));
				objFormatosJSON.put("desFormato",Utils.convertirUnicode(listaFormatos.get(i).get("desFormato").toString().trim()));
				listaFormatosJSON.add(objFormatosJSON);
			}
			
			List<Map<String, Object>> listaReporteJSON=null; 
			Map<String,Object> objReporteJSON=null;
				
			listaReporteJSON=new ArrayList<Map<String,Object>>();		
			if(listaReportes.size()>0){
				for (int i = 0; i < listaReportes.size(); i++) {	
					objReporteJSON=new LinkedHashMap<String, Object>();
					objReporteJSON.put("codRubro",Utils.convertirUnicode(listaReportes.get(i).get("codRubro").toString().trim()));
					objReporteJSON.put("desRubro",Utils.convertirUnicode(listaReportes.get(i).get("desRubro").toString().trim()));
					listaReporteJSON.add(objReporteJSON);
				}
			}else {
				objReporteJSON=new LinkedHashMap<String, Object>();	
				detalle.add(new LinkedHashMap<String, Object>());
			}
			objJsonCabecera.put("detalleDocumentos", listaDocumentosJSON);
			objJsonCabecera.put("detalleFormatos", listaFormatosJSON);
			objJsonCabecera.put("detalleReporte", listaReporteJSON);			
			objJsonReportParam.put("cabecera",objJsonCabecera);		
			objJsonReportParam.put("detalle", detalle);
			byte[] bytes=expedienteTrabReporteService.obtenerPrevisualizacionPlantilla(jsonSerializer.serialize(objJsonReportParam).toString());

			OutputStream out=response.getOutputStream();
			response.setContentLength(bytes.length);
			response.setContentType("application/pdf");
			out.write(bytes);
			out.close();
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled())log.debug("Error - ManttoPlantillaReporteController.previsualizarPlantilla");

			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.EXCEP_MODULO_02_01_007);
			modelAndView.addObject("beanErr",  msb);
			
		} finally {
			
			if (log.isDebugEnabled())log.debug( "Final - ManttoPlantillaReporteController.previsualizarPlantilla");
			
			NDC.pop();
			NDC.remove();
		}		
	}
	// Fin [gangles 26/05/2016] 
	
	/*Set*/
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setManttoPlanRepService(ManttoPlanRepService manttoPlanRepService) {
		this.manttoPlanRepService = manttoPlanRepService;
	}

	//Inicio [gangles 26/05/2016] - 13) Incluir opción de pre-visualización e impresión del contenido de la plantilla creada.
	public ExpedienteTrabReporteService getExpedienteTrabReporteService() {
		return expedienteTrabReporteService;
	}

	public void setExpedienteTrabReporteService(
			ExpedienteTrabReporteService expedienteTrabReporteService) {
		this.expedienteTrabReporteService = expedienteTrabReporteService;
	}
//Fin [gangles 26/05/2016]
	
}

