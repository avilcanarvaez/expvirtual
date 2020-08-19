
package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6616PedRepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ImpRepService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ManttoPlanRepService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;
//import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;

public class ImprimeReporteController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(ImprimeReporteController.class);
	private JsonView jsonView;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ManttoPlanRepService manttoPlanRepService;
	private ImpRepService impRepService;
	private EcmService ecmService;
	private CatalogoService catalogoService;
	
	public ModelAndView cargarBusqPedidos (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.cargarBusqPedidos");
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaActual = new Date();
			    String fecVista = sdf.format(fechaActual);
			    BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			    Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_07_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion1", AvisoConstantes.AVISO_MODULO_05_01_001);
				excepciones.put("excepcion2", AvisoConstantes.AVISO_MODULO_05_01_002);
				excepciones.put("excepcion3", AvisoConstantes.AVISO_MODULO_05_01_003);
				excepciones.put("excepcion4", AvisoConstantes.AVISO_MODULO_05_01_004);
				excepciones.put("excepcion5", AvisoConstantes.AVISO_MODULO_05_01_005);
				excepciones.put("excepcion6", AvisoConstantes.AVISO_MODULO_05_01_006);
				excepciones.put("excepcion7", AvisoConstantes.AVISO_MODULO_05_01_007);
				excepciones.put("excepcion8", AvisoConstantes.AVISO_MODULO_05_04_001);
				excepciones.put("excepcion9", AvisoConstantes.EXCEP_MODULO_05_07_004);
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
	
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_07_001);
				
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));	
				modelo.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.cargarBusqPedidos");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.cargarBusqPedidos");
			
			NDC.pop();
			NDC.remove();			
		}
		
		return modelo;
		
	}
	
	public ModelAndView cargarReportePorPedido (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.cargarReportePorPedido");
		ModelAndView modelAndView;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		HashMap<String, Object> mapParametrosBusqueda;		
		List<T6616PedRepBean> listaExpedientesPorPedidos= new ArrayList<T6616PedRepBean>();
		T6618RepGenExpBean t6618RepGenExpBean = null;
		List<String> listaErroresRepGenExp = new ArrayList<String>();
		
		try {
	
			String numeroPedido = Utils.toStr(request.getParameter("numeroPedido"));		
			String codProceso="";
			String codTipoExpediente="";
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");				
			}
				mapParametrosBusqueda = new HashMap<String, Object>();
				modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_05_08_002);
				mapParametrosBusqueda.put("numeroPedido", numeroPedido);
				
				listaExpedientesPorPedidos = impRepService.listarExpedientesPorPedido(mapParametrosBusqueda);
				
				Map<String, Object> lstErrDescParam = new HashMap<String, Object>();
				lstErrDescParam.put("codClase", CatalogoConstantes.CATA_ERROR_GEN_DOC_CABECERA);
				lstErrDescParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				lstErrDescParam = catalogoService.obtenerCatalogo(lstErrDescParam);
				
				if (!Utils.isEmpty(listaExpedientesPorPedidos)){
					for (T6616PedRepBean t6616PedRepBean : listaExpedientesPorPedidos) {
						codProceso=t6616PedRepBean.getCodProceso();
						codTipoExpediente=t6616PedRepBean.getCodTipoExpediente();
						
						mapParametrosBusqueda.put("numReportePedido", t6616PedRepBean.getNumReportePedido());
						//eaguilar: se obtain la lista de errores:
						t6618RepGenExpBean = impRepService.obtenerExpedientePorPedido(mapParametrosBusqueda);
						//04 jul eaguilar: compara con guion y null
						if(!Utils.isEmpty(t6618RepGenExpBean.getCodError()) && !t6618RepGenExpBean.getCodError().equals(ValidaConstantes.SEPARADOR_GUION)){
							String[] aux = t6618RepGenExpBean.getCodError().split("\\.");
							listaErroresRepGenExp = new LinkedList<String>(Arrays.asList(aux));
							Set<String> hs = new HashSet<String>();
							hs.addAll(listaErroresRepGenExp);
							listaErroresRepGenExp.clear();
							if(!Utils.isEmpty(lstErrDescParam)){
								for(String sError : hs){
									listaErroresRepGenExp.add(lstErrDescParam.get(sError).toString()!=null ? lstErrDescParam.get(sError).toString() : "");
									}
								//listaErroresRepGenExp.addAll(hs);
							}else{
								listaErroresRepGenExp.add("");
							}								
						}
						t6616PedRepBean.setLstErrors(listaErroresRepGenExp);
						modelAndView.addObject("t6616PedRepBean", new JsonSerializer().serialize(t6616PedRepBean));
						break;
					}	
				} else {						
					return modelAndView;
				}	
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaActual = new Date();
			    String fecVista = sdf.format(fechaActual);
			    
			    Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_08_002);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion1", AvisoConstantes.AVISO_MODULO_05_01_001);
				excepciones.put("excepcion2", AvisoConstantes.AVISO_MODULO_05_01_002);
				excepciones.put("excepcion3", AvisoConstantes.AVISO_MODULO_05_01_003);
				excepciones.put("excepcion4", AvisoConstantes.AVISO_MODULO_05_01_004);
				excepciones.put("excepcion5", AvisoConstantes.AVISO_MODULO_05_01_005);
				excepciones.put("excepcion6", AvisoConstantes.AVISO_MODULO_05_01_006);
				excepciones.put("excepcion7", AvisoConstantes.AVISO_MODULO_05_01_007);
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("codProceso", codProceso);
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(parametros);
								
				modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
				modelAndView.addObject("fecVista",new JsonSerializer().serialize(fecVista));
				modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));	
				modelAndView.addObject("listadoTiposExpendientes",new JsonSerializer().serialize(listadoTiposExpendientes));	
				modelAndView.addObject("codProceso",new JsonSerializer().serialize(codProceso));	
				modelAndView.addObject("codTipoExpediente", new JsonSerializer().serialize(codTipoExpediente));	
				modelAndView.addObject("numeroPedido", numeroPedido);		
				modelAndView.addObject("listaExpedientesPorPedidos",new JsonSerializer().serialize(listaExpedientesPorPedidos));
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.cargarReportePorPedido");			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.cargarReportePorPedido");
			
			NDC.pop();
			NDC.remove();			
		}		
		return modelAndView;
		
	}
	@RequestMapping(value = "/previsualizacionExpediente", method = { RequestMethod.POST, RequestMethod.GET },produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void previsualizacionExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.previsualizacionExpediente");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os=null;
		String nombreArchivo = null;
		String numReportePedido;
		String numeroPedido;
		T6618RepGenExpBean t6618RepGenExpBean;
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ImprimeReporteController.previsualizacionExpediente");
			
			numReportePedido = Utils.toStr(request.getParameter("numReportePedido"));
			numeroPedido = Utils.toStr(request.getParameter("numeroPedido"));
			
			Map<String, Object> parametrosBusqueda=  new HashMap<String, Object>();
			parametrosBusqueda.put("numReportePedido", numReportePedido);
			parametrosBusqueda.put("numeroPedido", numeroPedido);
			
			t6618RepGenExpBean=impRepService.obtenerExpedientePorPedido(parametrosBusqueda);
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			numIdEcm =t6618RepGenExpBean.getCodigoIdecm();
			mapParametrosBusqueda.put("codIdecm", numIdEcm);
			mapParametrosBusqueda.put("inline", "true");
			nombreArchivo = "Expediente_Virtual_" + numReportePedido;
		
			responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			MediaType contentType = responseDoc.getHeaders().getContentType();
			if(!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || 
					Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) ||
							Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))){
				String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",nombreArchivo+'.'+contentType.getSubtype().toUpperCase());
		        response.setHeader(headerKey, headerValue);
			}
			os = response.getOutputStream();
			os.write(responseDoc.getBody());
			
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.previsualizacionExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.previsualizacionExpediente");
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
	
	
public ModelAndView cargarDocumentosExpTrab (HttpServletRequest request, HttpServletResponse response) {
		
	if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.cargarDocumentosExpTrab");
	ModelAndView modelAndView= null;
	
	HttpSession session = request.getSession(true);
	UsuarioBean usuarioBean = null;
	HashMap<String, Object> mapParametrosBusqueda;		
	List<T6616PedRepBean> listaExpedientesPorPedidos= new ArrayList<T6616PedRepBean>();
	
	try {

		String codPedido = Utils.toStr(request.getParameter("codPedido"));
		String numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
		
		String codProceso="";
		String codTipoExpediente="";
	
		if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
			
			String usub = request.getParameter("usub");
			String tenc = request.getParameter("tenc");
			usub = usub.replace(' ', '+');
			usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			
		}else{
			
			usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");				
		}
			mapParametrosBusqueda = new HashMap<String, Object>();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_05_08_003);
			mapParametrosBusqueda.put("numeroPedido", codPedido);
			listaExpedientesPorPedidos = impRepService.listarExpedientesPorPedido(mapParametrosBusqueda);
			
			if (!Utils.isEmpty(listaExpedientesPorPedidos)){
				for (T6616PedRepBean t6616PedRepBean : listaExpedientesPorPedidos) {
					codProceso=t6616PedRepBean.getCodProceso();
					codTipoExpediente=t6616PedRepBean.getCodTipoExpediente();
					modelAndView.addObject("t6616PedRepBean", new JsonSerializer().serialize(t6616PedRepBean));
					break;
				}	
			} else {						
				return modelAndView;
			}	
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaActual = new Date();
		    String fecVista = sdf.format(fechaActual);
		    
		    Map<String, Object> titulos = new HashMap<String, Object>();
			
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_08_002);
			
			Map<String, Object> excepciones = new HashMap<String, Object>();
			
			excepciones.put("excepcion1", AvisoConstantes.AVISO_MODULO_05_01_001);
			excepciones.put("excepcion2", AvisoConstantes.AVISO_MODULO_05_01_002);
			excepciones.put("excepcion3", AvisoConstantes.AVISO_MODULO_05_01_003);
			excepciones.put("excepcion4", AvisoConstantes.AVISO_MODULO_05_01_004);
			excepciones.put("excepcion5", AvisoConstantes.AVISO_MODULO_05_01_005);
			excepciones.put("excepcion6", AvisoConstantes.AVISO_MODULO_05_01_006);
			excepciones.put("excepcion7", AvisoConstantes.AVISO_MODULO_05_01_007);
			
			List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("codProceso", codProceso);
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(parametros);
							
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelAndView.addObject("fecVista", new JsonSerializer().serialize(fecVista));
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));	
			modelAndView.addObject("listadoTiposExpendientes", new JsonSerializer().serialize(listadoTiposExpendientes));	
			modelAndView.addObject("codProceso", new JsonSerializer().serialize(codProceso));	
			modelAndView.addObject("codTipoExpediente", new JsonSerializer().serialize(codTipoExpediente));	
			modelAndView.addObject("codPedido", new JsonSerializer().serialize(codPedido));	
			modelAndView.addObject("numExpedienteVirtual", new JsonSerializer().serialize(numExpedienteVirtual));	
			modelAndView.addObject("listaExpedientesPorPedidos", new JsonSerializer().serialize(listaExpedientesPorPedidos));					
		
	} catch (Exception ex) {
		
		if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.cargarDocumentosExpTrab");			
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		msb.setError(true);
		msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		modelAndView.addObject("beanErr", msb);
		
	} finally {
		
		if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.cargarDocumentosExpTrab");
		
		NDC.pop();
		NDC.remove();			
	}		
	return modelAndView;
		
	}
	public ModelAndView cargarListPlantillas(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.cargarListPlantillas");
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
					mapParametrosBusqueda.put("fechaDesde", dateDesde);
					mapParametrosBusqueda.put("fechaHasta", dateHasta);
					
				}
				
				List<T6611CabPlantiBean> listPlantillas = manttoPlanRepService.listarPlantillas(mapParametrosBusqueda);
				
				modelo.addObject("listPlantillas", listPlantillas);
				
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.cargarListPlantillas");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.cargarListPlantillas");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView cargarBandRepGeneral (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.cargarBandRepGeneral");
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
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaActual = new Date();
			    String fecVista = sdf.format(fechaActual);
			    
			    Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_07_001);
				
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
	
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_07_001);
				
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				
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
				
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.cargarBandRepGeneral");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.cargarBandRepGeneral");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	@RequestMapping(value = "/imprimirExpedienteTrabajo", method = { RequestMethod.POST, RequestMethod.GET },produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void imprimirExpedienteTrabajo (HttpServletRequest request, HttpServletResponse response) {
	if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.imprimirExpedienteTrabajo");
	ResponseEntity<byte[]> responseDoc = null;
	ModelAndView modelo = null;
	String numIdEcm;
	OutputStream os=null;
	String nombreArchivo = null;
	String numReportePedido;
	String numeroPedido;
	T6618RepGenExpBean t6618RepGenExpBean;
	InputStream is = null;

	FileInputStream psStream = null;
	try {
		
		if (log.isDebugEnabled()) log.debug("Procesa - ImprimeReporteController.imprimirExpedienteTrabajo");
		
		numReportePedido = Utils.toStr(request.getParameter("numReportePedido"));
		numeroPedido = Utils.toStr(request.getParameter("numeroPedido"));
		
		Map<String, Object> parametrosBusqueda=  new HashMap<String, Object>();
		parametrosBusqueda.put("numReportePedido", numReportePedido);
		parametrosBusqueda.put("numeroPedido", numeroPedido);
		
		t6618RepGenExpBean=impRepService.obtenerExpedientePorPedido(parametrosBusqueda);
		
		Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
		numIdEcm =t6618RepGenExpBean.getCodigoIdecm();
		mapParametrosBusqueda.put("codIdecm", numIdEcm);
		mapParametrosBusqueda.put("inline", "true");
	//	nombreArchivo = "Pedido" + numeroPedido;
	
		   Calendar calendar = Calendar.getInstance();

	         int anio = (calendar.get(Calendar.YEAR));
	         int dia = (calendar.get(Calendar.DATE));
	         int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
	         int minutos = (calendar.get(Calendar.MINUTE));
	         String dd = (dia<10) ? "0"+dia : dia+"";
        
	         int mes = calendar.get(Calendar.MONTH)+1;
        
	         String mm = (mes<10) ? "0"+mes : mes+"";
	         
	    String part=anio+mm+dd+"_"+hora+minutos;
	    String filename="Expediente_de_Trabajo" + part + ".pdf";
	    
		responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
		String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"",filename+'.'+"pdf");
	    response.setHeader(headerKey, headerValue);
	    File downloadFile = new File("/data0/tempo/" + filename);
	    OutputStream outputStream = new FileOutputStream(downloadFile); // path y nombre del nuevo fichero creado
	    outputStream.write(responseDoc.getBody());
 
        FileInputStream inputStream = new FileInputStream(downloadFile);
		
		DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;	
		Doc document = new SimpleDoc(inputStream, docFormat, null);    
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
       
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();   
            try {
                printJob.print(document, attributeSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
        

					
	} catch (Exception ex) {
		
		if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.imprimirExpedienteTrabajo");
		
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		msb.setError(true);
		msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		modelo.addObject("beanErr", msb);
		
	} finally {
		
		if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.imprimirExpedienteTrabajo");
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
	
	
	@RequestMapping(value = { "/cargarListaTiposExpediente" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - ImprimeReporteController.cargarListaTiposExpediente");
		}
		try {
			String codProceso = Utils.toStr( request.getParameter("codProceso"));

			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);			
			List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			modelAndView = new ModelAndView((View) this.jsonView);
			modelAndView.addObject("listadoTiposExpendientes",listadoTiposExpendientes);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ImprimeReporteController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ImprimeReporteController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/cargarListadoPedidos", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cargarListadoPedidos(HttpServletRequest request, HttpServletResponse response){
	
		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - ImprimeReporteController.cargarListadoPedidos");
		}
		ModelAndView modelAndView = null;
		List<T6616PedRepBean> listaPedidos= new ArrayList<T6616PedRepBean>();
		
		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numPedido = Utils.toStr(request.getParameter("numPedido"));
		
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
			String codColab=usuarioBean.getNroRegistro();
			String codDepUsuario=usuarioBean.getCodDepend();
			
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
            // PAS20191U210500144 Inicio 400105 RF10 PSALDARRIAGA
			mapParametrosBusqueda.put("codUsuarioRegistro", codColab);
			// PAS20191U210500144 Fin 400105 RF10 PSALDARRIAGA			
			//validamos número pedido
			if (!Utils.isEmpty(numPedido)) {			
					mapParametrosBusqueda.put("numeroPedido", numPedido);			
				
				listaPedidos = impRepService.listarPedidos(mapParametrosBusqueda);

				if (Utils.isEmpty(listaPedidos)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_05_07_003);					
					return modelAndView;
				} else {
					modelAndView.addObject("listaPedidos",listaPedidos);
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
			
			listaPedidos = impRepService.listarPedidos(mapParametrosBusqueda);

			if (Utils.isEmpty(listaPedidos)) {
				modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_05_07_001);
			} else {
				modelAndView.addObject("listaPedidos",listaPedidos);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ImprimeReporteController.cargarListadoPedidos");
			}
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ImprimeReporteController.cargarListadoPedidos");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/mostrarView",  method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - ImprimeReporteController.mostrarView");
		
		ModelAndView modelo = null;
		
		String pageView = request.getParameter("pageView");
		modelo = new ModelAndView("ImprimeReporteGeneradoExpTrab");
		try {
			
			if(Utils.equiv(pageView, NavegaConstantes.MANT_MODULO_05_07_001)){
				
				HttpSession session = request.getSession(true);
				UsuarioBean usuarioBean = null;		
				
			
					String indCarga = request.getParameter("indCarga");
				
					if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
						
						String usub = request.getParameter("usub");
						String tenc = request.getParameter("tenc");
						usub = usub.replace(' ', '+');
						usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
						
					}else{
						
						usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
						
					}
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date fechaActual = new Date();
					    String fecVista = sdf.format(fechaActual);
					    
					    BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
					    
					    Map<String, Object> titulos = new HashMap<String, Object>();
						
						titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_07_001);
						
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
			
						modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_07_001);
						
						modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
						modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
						modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
						modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
						modelo.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ImprimeReporteController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelo.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ImprimeReporteController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - ImprimeReporteController.mostrarView");
		return modelo;
	}
	
	@RequestMapping(value = "/imprimirDocumentosPorExpNumPedido", method = { RequestMethod.POST, RequestMethod.GET },produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public ModelAndView imprimirDocumentosPorExpNumPedido (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ImprimeReporteController.imprimirDocumentosPorExpNumPedido");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		FileOutputStream os=null;
		String nombreArchivo = null;
		String numReportePedido;
		String numeroPedido;
		List<T6618RepGenExpBean> listT6618RepGenExpBean;
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ImprimeReporteController.imprimirDocumentosPorExpNumPedido");
			
			numReportePedido = Utils.toStr(request.getParameter("numReportePedido"));
			numeroPedido = Utils.toStr(request.getParameter("numeroPedido"));
			
			Map<String, Object> parametrosBusqueda=  new HashMap<String, Object>();
			parametrosBusqueda.put("numReportePedido", numReportePedido);
			parametrosBusqueda.put("numPedido", numeroPedido);
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			List<String> listDocuentos = new ArrayList<String>();
			
			listT6618RepGenExpBean = impRepService.obtenerListaExpedientesTrabajo(parametrosBusqueda);
			
			for (T6618RepGenExpBean t6618RepGenExpBean : listT6618RepGenExpBean) {
				mapParametrosBusqueda=  new HashMap<String, Object>();
				numIdEcm =t6618RepGenExpBean.getCodigoIdecm();
				mapParametrosBusqueda.put("codIdecm", numIdEcm);
				mapParametrosBusqueda.put("inline", "true");
				nombreArchivo = Utils.toStr(t6618RepGenExpBean.getNumReporteExpediente());
				
				responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			
				os = new FileOutputStream(ValidaConstantes.CARPETA_TEMPORAL_TEMPO+nombreArchivo+'.'+ValidaConstantes.TIPO_ARCHIVO_PDF);
				os.write(responseDoc.getBody());
				os.close();
				
				listDocuentos.add(nombreArchivo);
			}
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_03_004);
			modelo.addObject("listaDocumentos", new JsonSerializer().serialize(listDocuentos));
			modelo.addObject("ruta",request.getRequestURL().toString());	
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ImprimeReporteController.imprimirDocumentosPorExpNumPedido");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ImprimeReporteController.imprimirDocumentosPorExpNumPedido");
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
		return modelo;
	}
	
	@RequestMapping(value = "/descargarExpTrabajo", method = RequestMethod.POST)
	public void descargarExpTrabajo(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - ImprimeReporteController.descargarExpTrabajo");
		
		try {
			
			String numeroExpedienteTrabajo = Utils.toStr(request.getParameter("numExpeTrabajo"));
			
			File downloadFile = new File(ValidaConstantes.CARPETA_TEMPORAL_TEMPO +numeroExpedienteTrabajo);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.CARPETA_TEMPORAL_TEMPO +numeroExpedienteTrabajo); 
	         
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	              
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", numeroExpedienteTrabajo);
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
	        downloadFile.deleteOnExit();
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ImprimeReporteController.descargarExpTrabajo");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ImprimeReporteController.descargarExpTrabajo");
			}
			NDC.pop();
			NDC.remove();
		}
		
	}
	
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


	public void setImpRepService(ImpRepService impRepService) {
		this.impRepService = impRepService;
	}
	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
}