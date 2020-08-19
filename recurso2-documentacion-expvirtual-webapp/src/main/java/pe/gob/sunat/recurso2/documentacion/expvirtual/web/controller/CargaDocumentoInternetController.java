package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.io.FileUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import com.opensymphony.oscache.plugins.clustersupport.ClusterNotification;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DetalleRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

@Controller
public class CargaDocumentoInternetController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(CargaDocumentoInternetController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private CatalogoService catalogoService;
	private DocumentoExpedienteService documentoExpedienteService;
	private ResponsableService respService;
	
	//[PAS20191U210500291]: JMC - INI
	private DetalleRequerimientoService detalleRequerimientoService;
	private DocumentoItemRequerimientoService documentoItemRequerimientoService; 
	//[PAS20191U210500291]: JMC - FIN

	@RequestMapping(value = "/consultarRequerimientosPendientesView", method = RequestMethod.GET)
	public ModelAndView consultarRequerimientosPendientesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - CargaDocumentoInternetController.consultarRequerimientosPendientesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_010);

			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			//[PAS20191U210500291]: JMC - INI
			/*List<T01ParamBean> listaProcesosFinal = new ArrayList<T01ParamBean>();
			
			for (T01ParamBean procesoBean : listadoProcesos) {
				if ((procesoBean.getCodParametro().trim()).equals("002")) { 
					listaProcesosFinal.add(procesoBean);
				}
			}*/
			//[PAS20191U210500291]: JMC - FIN
			
			List<T01ParamBean> listadoDependencias = configuracionExpedienteService.listarDependencias();
			
			List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			
			List<T01ParamBean> listadoTipoFecha= paramService.listarTipoFecha();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_04_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_04_003);
			
			//modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize(listaProcesosFinal));
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			modelAndView.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias) );
			modelAndView.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente) );
			modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha) );
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoInternetController.consultarRequerimientosPendientesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.consultarRequerimientosPendientesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/validarContribuyente" , method =  RequestMethod.GET )
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - CargaDocumentoInternetController.validarContribuyente");
		
		DdpBean beanContribuyente;
		
		try {
			modelAndView = new ModelAndView(jsonView);
			String numeroRuc = Utils.toStr( request.getParameter("numRuc"));

			// validamos numero de ruc
			if (!Utils.isEmpty(numeroRuc)) {
				beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
				//nchavez 13/06/2016
				if (Utils.isEmpty(beanContribuyente.getNumRuc())) {
					modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_001);
					return modelAndView;
				} else {
					modelAndView.addObject("razonSocial",beanContribuyente.getDesRazonSocial());
				}
			}
			 
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.validarContribuyente");
			}
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.validarContribuyente");
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
			log.info( "Inicio - CargaDocumentoInternetController.cargarListaTiposExpediente");
		}
		try {
			String codProceso = Utils.toStr( request.getParameter("codProceso"));

			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			// mapParametrosBusqueda.put("indConsulta", "2");
			List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);
			
			//[PAS20191U210500291]: JMC - INI
			/*List<T6624TipExpProcBean> listaTiposExpendientesFinal = new ArrayList<T6624TipExpProcBean>();
			
			for (T6624TipExpProcBean tipoExpedienteBean : listadoTiposExpendientes) {
				if ((tipoExpedienteBean.getCodTipoExpediente().trim()).equals("430") || (tipoExpedienteBean.getCodTipoExpediente().trim()).equals("431")) { 
					listaTiposExpendientesFinal.add(tipoExpedienteBean);
				}
			}*/
			//[PAS20191U210500291]: JMC - FIN
			
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("listadoTiposExpendientes",listadoTiposExpendientes); //[PAS20191U210500291]: JMC
//			modelAndView.addObject("listadoTiposExpendientes",listaTiposExpendientesFinal);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoInternetController.descargarDocumento");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		
		try {
			
			String codDocumento = Utils.toStr(request.getParameter("hidDocumentosSel"));
			String nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidNombreDoc")));
			String numRequerimiento = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidTxtNumRequerimiento"))).trim();
			/*multipartFile = multipartRequest.getFile("file"+codDocumento);
			archivo = Utils.multipartToFile(multipartFile);
			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+multipartFile.getOriginalFilename());
			FileUtils.copyFile(archivo, destino);*/
			
			File downloadFile = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numRequerimiento+"@@"+codDocumento+"@@"+nombreArchivo);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numRequerimiento+"@@"+codDocumento+"@@"+nombreArchivo); 
	         
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	              
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
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
				log.debug((Object) "Error - CargaDocumentoInternetController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
		
	}
	
	@RequestMapping(value = "/obtenerTamano", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView obtenerTamano(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoInternetController.obtenerTamano");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		ModelAndView modelAndView = null;
		try {
			multipartFile = multipartRequest.getFile("docFisico");
			String codDocumento = Utils.toStr(request.getParameter("hidDocumentosSel"));
			String numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento")).trim();
			String mimeType = multipartFile.getContentType();
			String arrayMimeType[] = null;
			arrayMimeType = mimeType.split(";");
			mimeType = arrayMimeType[0];
			archivo = Utils.multipartToFile(multipartFile);
			log.info("***********Ruta:*****************************"+ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+codDocumento+"@@"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			log.info("***********Nombre Archivo:(multipartFile.getOriginalFilename())***********************"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			
			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento+"@@"+codDocumento+"@@"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			int tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IA"));
			
			if(!(multipartFile.getSize()>Math.round((tamanoMaximo*1048576/100000)*100000))){
				FileUtils.copyFile(archivo, destino);
			}
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("peso", multipartFile.getSize());
			modelAndView.addObject("nombreArchivo", Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			modelAndView.addObject("mimeType", mimeType);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.obtenerTamano");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.obtenerTamano");
			}
			NDC.pop();
			NDC.remove();
		}
	
		return modelAndView;
	}
	
	@RequestMapping(value = "/subirDocumentos", method = RequestMethod.POST)
	public ModelAndView subirDocumentos(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoInternetController.subirDocumentos");
		
		Map<String, Object> parametros = null;
		UsuarioBean usuarioBean;
		String numExpedienteVirtual;
		String numExpedienteOrigen;
		String numRuc;
		String codTipoExpediente;
		String numRequerimiento;
		//nchavez [06/06/2016]
		String numRequerimientOrigen;
		String fechaRequerimiento;
		String estadoExpediente;
		String razonSocial;
		String flagAcceso;
		String proceso;
		String desTipoExpediente;
		String fechaVencimiento = null;
		String error="";
		try {
			
			String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel"));
			List<Map<String, Object>> listaDocumentosASubir = null;
			//Inicio LEstrada SNADE307-1031
			List<Map<String, Object>> listaDocumentosASubirInicial = null;
			List<Map<String, Object>> listaDocumentosASubirResult = new ArrayList<Map<String, Object>> ();
			listaDocumentosASubirInicial  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			TreeMap<Integer, Map<String, Object>> tMapDocumentosASubir = new TreeMap<Integer, Map<String, Object>>();	
			if(!Utils.isEmpty(listaDocumentosASubirInicial)){
				for (Map<String, Object> map2 : listaDocumentosASubirInicial) {
					tMapDocumentosASubir.put(Integer.parseInt(map2.get("CORRELATIVO").toString()), map2);
                }
				listaDocumentosASubirResult.addAll(tMapDocumentosASubir.values());
				listaDocumentosASubir=listaDocumentosASubirResult;
				//Fin LEstrada SNADE307-1031
				codTipoExpediente = Utils.toStr(request.getParameter("hidTipoExpediente"));
				numExpedienteVirtual = Utils.toStr(request.getParameter("hidTxtExpedienteVirtual"));
				numExpedienteOrigen = Utils.toStr(request.getParameter("hidTxtExpedienteOrigen"));
				
				Map<String, Object> mapBusqueda = new HashMap<String, Object>();
				mapBusqueda.put("codTipoDocumento", ValidaConstantes.COD_TIPO_DOCU_CONS_PRESENT_DOCUMENTO);
				mapBusqueda.put("codTipoExpediente", codTipoExpediente);
				mapBusqueda.put("indEliminado", ValidaConstantes.INDICADOR_ELIMINADO_NO_ASOC_TIPEXP_TIPDOC);
				//List<T6623TipDocExpBean> listT6623TipDocExpBean = configuracionExpedienteService.listarDocumentosPorExpedienteEstado(mapBusqueda);
				
				List<T6621RespExpVirtBean> listaRespAsignados = respService.obtenerResponsablesAsignadosSepar(numExpedienteVirtual);
				
				numRuc = Utils.toStr(request.getParameter("hidTxtRuc"));
				
				numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento"));
				//Inicio nchavez [06/06/2016]
				numRequerimientOrigen = Utils.toStr(request.getParameter("txtNumRequerimiento"));
				//Fin nchavez [06/06/2016]
				fechaRequerimiento = Utils.toStr(request.getParameter("hidTxtFecRequerimiento"));
				estadoExpediente = Utils.toStr(request.getParameter("hidEstadoExpediente"));
				razonSocial  = Utils.toStr(request.getParameter("hiddTxtRazonSocial"));
				proceso = Utils.toStr(request.getParameter("hiddTxtProceso"));
				desTipoExpediente = Utils.toStr(request.getParameter("hidDesTipoExpediente"));
				fechaVencimiento = Utils.toStr(request.getParameter("hidTxtFecVencimiento"));;
				flagAcceso = Utils.toStr(request.getParameter("hidTxtTipoOpcion"));
				parametros = new HashMap<String, Object>();
				parametros.put("listaDocumentosASubir", listaDocumentosASubir);
				parametros.put("numExpedienteVirtual", numExpedienteVirtual);
				parametros.put("numExpedienteOrigen", numExpedienteOrigen);
				parametros.put("numRuc", numRuc);
				parametros.put("codTipoExpediente", codTipoExpediente);
				parametros.put("codUsuarioCarga", usuarioBean.getUsuarioSOL());
				//parametros.put("codUsuarioCarga", usuarioBean.getNroRegistro());
				parametros.put("numRequerimiento", numRequerimiento);
				parametros.put("fechaRequerimiento", fechaRequerimiento);
				parametros.put("estadoExpediente", estadoExpediente);
				parametros.put("razonSocial", razonSocial);
				parametros.put("proceso", proceso);
				parametros.put("desTipoExpediente", desTipoExpediente);
				parametros.put("fechaVencimiento", fechaVencimiento);
				//parametros.put("listT6623TipDocExpBean", listT6623TipDocExpBean);
				parametros.put("listaRespAsignados", listaRespAsignados);
				parametros.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTERNET);
				parametros.put("codDep", usuarioBean.getCodDepend());
				parametros.put("flagAcceso", flagAcceso);
				
				//para actualizar el requerimiento
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("numExpedienteVirtual", Utils.toStr(parametros.get("numExpedienteVirtual")));
				map.put("numRequerimiento", Utils.toStr(parametros.get("numRequerimiento")));
				map.put("estadoDocumentoReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_SOLICITADO);
				
				List<T6613DocExpVirtBean> listT6613DocExpVirtBean = documentoExpedienteService.listarDocumentosExpediente(map);
				parametros.put("listT6613DocExpVirtBean", listT6613DocExpVirtBean);
				//nchavez [06/06/2016]
				parametros.put("numRequerimientOrigen", numRequerimientOrigen);//agregado numero requerimiento Origen
				
				error = expedienteVirtualService.adjuntarDocumentosRequerimiento(parametros);	
				
					
			}
			
			
			modelAndView = new ModelAndView(jsonView);
			if(!Utils.equiv(error, ValidaConstantes.NO_ERROR)){
				modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_003);
				modelAndView.addObject("error", ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS);
			}else{
				int plazoVigencia = Utils.obtenerDifDias(Utils.stringToDate(fechaVencimiento,CatalogoConstantes.INT_TWO),new Date());
				if(plazoVigencia>=0){
					modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_002);
					modelAndView.addObject("error", ValidaConstantes.NO_ERROR);
				}else {
					modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_001);
					modelAndView.addObject("error", ValidaConstantes.NO_ERROR);
				}
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.subirDocumentos");
			}
			log.error(ex, ex);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_003);
			modelAndView.addObject("error", ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS);
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.subirDocumentos");
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarListadoRequerimientosPendientes", method = RequestMethod.GET)
	public ModelAndView cargarListadoRequerimientosPendientes(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.cargarListadoRequerimientosPendientes");

		ModelAndView modelAndView = null;
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;

		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numExp = Utils.toStr(request.getParameter("numExp"));
		String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
		
		Date fecDesde = null;
		Date fecHasta = null;

		try {
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
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

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
//			mapParametrosBusqueda.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			//Inicio - [oachahuic][PAS20175E210400016]
//			List<String> listCodEstReq = new ArrayList<String>();
//			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
//			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
//			mapParametrosBusqueda.put("listCodEstReq", listCodEstReq);
			//Fin - [oachahuic][PAS20175E210400016]
			mapParametrosBusqueda.put("numeroRuc", usuarioBean.getNumRUC());
			
			//[PAS20191U210500291]: JMC - INI
			List<String> listCodTipExp = new ArrayList<String>();
			listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_DEFPARCIAL);
			listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_CRUCES);
			mapParametrosBusqueda.put("listCodTipExp", listCodTipExp);
			
			mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
			//[PAS20191U210500291]: JMC - FIN
			
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("numExpedVirtual", numExp);
				} else {
					mapParametrosBusqueda.put("numExpedOrigen", numExp);
				}
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				if (Utils.isEmpty(listaExpedientesVirtuales)) {
//					modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_03);
					modelAndView.addObject("msjError","El N° Expediente no existe."); //[PAS20191U210500291]: JMC
					return modelAndView;
				} else {
					
					listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

					if (Utils.isEmpty(listT6620RequerimBean)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
						return modelAndView;
					} else {
						modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);
						return modelAndView;
					}
				}
				
				
			}
			// validamos las fechas
			if(Utils.isEmpty(flagFecExp)){
				flagFecExp=ValidaConstantes.BUSQUEDA_POR_FECHA_EXPEDIENTE_VIRTUAL;
			}
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
			
			//mapParametrosBusqueda.put("codColab ",usuarioBean.getNroRegistro());
			
			listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

			if (Utils.isEmpty(listT6620RequerimBean)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoInternetController.cargarListadoRequerimientosPendientes");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opci�n");
			modelAndView.addObject("beanErr", msb);
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.cargarListadoRequerimientosPendientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = { "/consultarDetalleRequerimientoView" }, method = { RequestMethod.GET })
	public ModelAndView consultarDetalleRequerimientoView(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.consultarDetalleRequerimientoView");
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
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_011);
			
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
				 //Inicio nchavez 13/06/2016
				 modelAndView.addObject("razonSocial",!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
				 //fin nchavez 13/06/2016
				 t6614ExpVirtualBean.setDesProceso(t6614ExpVirtualBean.getDesProceso().toUpperCase());
				 t6614ExpVirtualBean.setDesTipoExpediente(t6614ExpVirtualBean.getDesTipoExpediente().toUpperCase());
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
			
			//mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_REQUERIM);
			mapParametrosBusqueda.remove("tipDocSust");
			lisT6613DocExpVirtBean = requerimientoService.listarDocumentosPorRequerim(mapParametrosBusqueda);
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_06_001);
			/*lestrada 15092015*/
			Map<String, Object> flag = new HashMap<String, Object>();
			flag.put("flagAcceso", AvisoConstantes.FLAG_INTERNET);
			/*lestrada 15092015*/
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion10",AvisoConstantes.EXCEP_MODULO_02_01_010);
			
			//Lista Extensiones Permitidas
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			List<T01ParamBean> listExtensionesPermitidas = paramService.listarParametros(mapa);
			
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			
			modelAndView.addObject("lisT6613DocExpVirtBean",new JsonSerializer().serialize(lisT6613DocExpVirtBean));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("flagAcceso",new JsonSerializer().serialize(flag));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));

			modelAndView.addObject("tamanoMaximoPermitidoIntranet",Utils.toStr(mapaTamanoTotalPermitido.get("IT")));
			modelAndView.addObject("listExtensionesPermitidas",new JsonSerializer().serialize(listExtensionesPermitidas));
			modelAndView.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(mapaTamanoTotalPermitido));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - CargaDocumentoInternetController.consultarDetalleRequerimientoView");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - CargaDocumentoInternetController.consultarDetalleRequerimientoView");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/mostrarView",  method =  RequestMethod.GET)
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.mostrarView");
		
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
				log.debug("Error - CargaDocumentoInternetController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - CargaDocumentoInternetController.mostrarView");
		return modelAndView;
	}

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	       
//		String titulo = ExportaConstantes.TITULO_EXPORTA_02_03;
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_10;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Calendar fechaVacia = null;
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.exportarExcelExpedientes");
		
		try {
			
			listadoExportarCadena = request.getParameter("listadoExpedientesCadena");

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
			if(listadoExportar != null && !listadoExportar.isEmpty()){
				listadoExportar.get(0).put("agregarRuc", true);
			}

			// Fecha actual
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			response.setContentType("application/vnd.ms-excel");
		 HSSFWorkbook libro = null;
		//PAS20201U210500029 - HHC INICIO
		 Map<String,ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
//		 columnsProperties.put("numRequerimOrigen",new ColumOption("N° Requerimiento",ExcelUtil.ALIGN.LEFT));
		 columnsProperties.put("numExpedienteOrigen",new ColumOption("N° Expediente",ExcelUtil.ALIGN.LEFT));
		 columnsProperties.put("desProceso",new ColumOption("Proceso",ExcelUtil.ALIGN.LEFT));
		 columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente",ExcelUtil.ALIGN.LEFT));
		 columnsProperties.put("fechaDocumentoOrigen",new ColumOption("Fecha del Expediente",ExcelUtil.ALIGN.RIGHT));
//		 columnsProperties.put("fechaRequerimiento",new ColumOption("Fecha del Requerimiento",ExcelUtil.ALIGN.RIGHT));
//		 columnsProperties.put("fecVencimiento",new ColumOption("Fecha de Vencimiento",ExcelUtil.ALIGN.RIGHT));
		 //PAS20201U210500029 - HHC FIN
		 
         libro=ExcelUtil.buildWorkbookXLS(titulo, "Hoja 1",columnsProperties, listadoExportar);
		 //Fin nchavez [31/05/2016]
		 Calendar calendar = Calendar.getInstance();
		
		 int anio = (calendar.get(Calendar.YEAR));
		 int dia = (calendar.get(Calendar.DATE));
		 int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
		 int minutos = (calendar.get(Calendar.MINUTE));
		 String dd = (dia<10) ? "0"+dia : dia+"";
		 int mes = calendar.get(Calendar.MONTH)+1;
		 String mm = (mes<10) ? "0"+mes : mes+"";
		 String part=anio+mm+dd+"_"+hora+minutos;
		   
//		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_REQ_XLS + part + ".xls";
		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_EXP_XLS + part + ".xls";
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
    	   if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoInternetController.exportarExcelExpedientes");
			}    	   
             
            mensajeBean.setError(true);
            mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
            view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		
	   if (log.isDebugEnabled())log.info((Object) "Final - CargaDocumentoInternetController.exportarExcelExpedientes");
		
       return view;

	}
	
	@RequestMapping(value = { "/registraEscrito" }, method = { RequestMethod.POST })
	public ModelAndView registraEscrito (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.registraEscrito");
		
		ModelAndView modelo = new ModelAndView("ConsultaDetalleEscritoReq");
		List<T10372DetRequerimBean> listaDetRequerimiento = new ArrayList<T10372DetRequerimBean>();
		List<T10373DocAdjReqBean> listaDocAdjuntos = new ArrayList<T10373DocAdjReqBean>();
		List<T10373DocAdjReqBean> listaDocAdjFinal = new ArrayList<T10373DocAdjReqBean>();
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		Map<String, Object> datosSupervisor = new HashMap<String, Object>();
		File archivo;
		
		try {
			String lista = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			String numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento")); 
			String numExpVirt = Utils.toStr(request.getParameter("hidTxtNumExpVirt"));
					
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoRegistrar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
			log.info("Lista a registrar: "+ listadoRegistrar);
			
			//Tamaño maximo permitido
			Map<String, Object> mapa = new HashMap<String,Object>();	
			
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			
			for (Map<String, Object> datosEscritoReq : listadoRegistrar) {
				if (numRequerimiento.equals(datosEscritoReq.get("numRequerimiento"))) {
					datosEscritoReq.put("desProceso", datosEscritoReq.get("desProceso")); 
					
					paramBusqueda.put("codDependencia", datosEscritoReq.get("codDependencia").toString().trim());
					paramBusqueda.put("numExpOri", datosEscritoReq.get("numExpedienteOrigen").toString().trim());
					
					datosSupervisor = detalleRequerimientoService.obtenerDatosSupervisor(paramBusqueda);
					
					datosEscritoReq.put("codSupervisor", datosSupervisor.get("codSupervisor").toString().trim());
					datosEscritoReq.put("UuOOSupervisor", datosSupervisor.get("UuOOSupervisor").toString().trim());
					
					paramBusqueda = new HashMap<String, Object>();
					paramBusqueda.put("numRequerimiento", datosEscritoReq.get("numRequerimiento"));
					paramBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
					
					listaDetRequerimiento = detalleRequerimientoService.obtenerListaItems(paramBusqueda);
					log.debug("listaDetRequerimiento: "+listaDetRequerimiento);
					modelo.addObject("datosEscritoReq",datosEscritoReq);
					modelo.addObject("listaDetRequerimiento",new JsonSerializer().serialize(listaDetRequerimiento));
				}				
			}
			
			for (T10372DetRequerimBean detRequerimiento : listaDetRequerimiento) {
				paramBusqueda.put("numItem", detRequerimiento.getItem());
				
				listaDocAdjuntos = documentoItemRequerimientoService.obtenerListaDocAdj(paramBusqueda);
				
				if (listaDocAdjuntos.size() > 0) {
					listaDocAdjFinal.addAll(listaDocAdjuntos);
				}
			}
			
			archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento);
			if (archivo.isDirectory()) {
				log.debug("borrar: "+archivo);
				FileUtils.deleteDirectory(archivo);
			}
			modelo.addObject("numExpVirt", new JsonSerializer().serialize(numExpVirt));
			modelo.addObject("listaDocAdjuntos", new JsonSerializer().serialize(listaDocAdjFinal));			
			modelo.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(Utils.toStr(mapaTamanoTotalPermitido.get("IT"))));
			WebUtils.setSessionAttribute(request, "datosEscritoReq", new JsonSerializer().serialize(listadoRegistrar));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.registraEscrito");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.registraEscrito");
		}
		return modelo;
		
	}


	//PAS20201U210500029 - HHC INICIO
	@RequestMapping(value = { "/consultaRequerimientos" }, method = { RequestMethod.POST })
	public ModelAndView consultaRequerimientos (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - CargaDocumentoInternetController.consultaRequerimientos");
		
		ModelAndView modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_013);
		List<T6620RequerimBean> listaDocAdjuntos = new ArrayList<T6620RequerimBean>();
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		
		try {
			String lista = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			String numExpVirt = Utils.toStr(request.getParameter("hidTxtNumExpVirt")).trim();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_001);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_04_001);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_04_003);
			
			
			modelo.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoRegistrar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
			if (log.isDebugEnabled()) log.debug("Lista a registrar Requerimientos: "+ listadoRegistrar);
			
			for (Map<String, Object> datosEscritoReq : listadoRegistrar) {
				if (numExpVirt.equals(Utils.toStr(datosEscritoReq.get("numExpedienteVirtual")).trim())) {
					
					paramBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
					paramBusqueda.put("numExpedVirtual", datosEscritoReq.get("numExpedienteVirtual"));
					
					paramBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
					
					List<String> listCodTipExp = new ArrayList<String>();
					listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_DEFPARCIAL);
					listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_CRUCES);
					paramBusqueda.put("listCodTipExp", listCodTipExp);
				
					listaDocAdjuntos = requerimientoService.obtenerListaRequerimientos(paramBusqueda);
					modelo.addObject("listaDocAdjuntos",new JsonSerializer().serialize(listaDocAdjuntos));
					
					if (log.isDebugEnabled()) log.debug("CargaDocumentoInternetController.consultaRequerimientos listaDocAdjuntos: "+ new JsonSerializer().serialize(listaDocAdjuntos));
				
					modelo.addObject("datosEscritoReq", datosEscritoReq);
				}
			}
			
			modelo.addObject("numExpVirt", new JsonSerializer().serialize(numExpVirt));
			WebUtils.setSessionAttribute(request, "datosEscritoReq", new JsonSerializer().serialize(listadoRegistrar));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoInternetController.consultaRequerimientos");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CargaDocumentoInternetController.consultaRequerimientos");
		}
		return modelo;
		
	}
	@RequestMapping(value = "/cargarListadoExpedientesPendientes", method = RequestMethod.GET)
	public ModelAndView cargarListadoExpedientesPendientes(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.cargarListadoExpedientesPendientes");

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		List<T6614ExpVirtualBean> listaExpedientesVirtualesReq;

		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		String numExp = Utils.toStr(request.getParameter("numExp"));
		String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
		
		Date fecDesde = null;
		Date fecHasta = null;

		try {
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
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

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			List<String> listCodEstReq = new ArrayList<String>();
			//HHC - INI
//			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
//			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
//			mapParametrosBusqueda.put("listCodEstReq", listCodEstReq);
			//HHC - FIN
			mapParametrosBusqueda.put("numeroRuc", usuarioBean.getNumRUC());
			
			List<String> listCodTipExp = new ArrayList<String>();
			listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_DEFPARCIAL);
			listCodTipExp.add(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES_CRUCES);
			mapParametrosBusqueda.put("listCodTipExp", listCodTipExp);
			
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("numExpedVirtual", numExp);
				} else {
					mapParametrosBusqueda.put("numExpedOrigen", numExp);
				}
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				
				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					modelAndView.addObject("msjError","El N° Expediente no existe."); //[PAS20191U210500291]: JMC
					return modelAndView;
				} else {
					//HHC - LISTARÁ TODOS LOS EXPEDIENTES CON REQUERIMIENTO
					listaExpedientesVirtualesReq = expedienteVirtualService.obtenerListaExpedienteVirtualReq(mapParametrosBusqueda);

					if (Utils.isEmpty(listaExpedientesVirtualesReq)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
						return modelAndView;
					} else {
						modelAndView.addObject("listT6620RequerimBean",listaExpedientesVirtualesReq);//HHC
						return modelAndView;
					}
				}
			}
			// validamos las fechas
			if(Utils.isEmpty(flagFecExp)){
				flagFecExp=ValidaConstantes.BUSQUEDA_POR_FECHA_EXPEDIENTE_VIRTUAL;
			}
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
			
			//LISTARÁ EXPEDIENTES CON REQUERIMIENTO 
			listaExpedientesVirtualesReq = expedienteVirtualService.obtenerListaExpedienteVirtualReq(mapParametrosBusqueda);  //HHC 

			if (Utils.isEmpty(listaExpedientesVirtualesReq)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				modelAndView.addObject("listT6620RequerimBean",listaExpedientesVirtualesReq); //HHC
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoInternetController.cargarListadoExpedientesPendientes");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opci�n");
			modelAndView.addObject("beanErr", msb);
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoInternetController.cargarListadoExpedientesPendientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = { "/registraEscritoReq" }, method = { RequestMethod.POST })
	public ModelAndView registraEscritoReq (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - CargaDocumentoInternetController.registraEscritoReq");
		
		ModelAndView modelo = new ModelAndView("ConsultaDetalleEscritoReq");
		List<T10372DetRequerimBean> listaDetRequerimiento = new ArrayList<T10372DetRequerimBean>();
		List<T10373DocAdjReqBean> listaDocAdjuntos = new ArrayList<T10373DocAdjReqBean>();
		List<T10373DocAdjReqBean> listaDocAdjFinal = new ArrayList<T10373DocAdjReqBean>();
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		Map<String, Object> datosSupervisor = new HashMap<String, Object>();
		File archivo;
		
		try {
			String lista = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			String numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento")); 
			String numExpVirt = Utils.toStr(request.getParameter("hidTxtNumExpVirt"));
					
//			@SuppressWarnings("unchecked")
//			List<Map<String, Object>> listadoRegistrar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
//			log.info("Lista a registrar: "+ listadoRegistrar);
			
			@SuppressWarnings("unchecked")
			List<T6620RequerimBean> listadoRegistrar = (ArrayList<T6620RequerimBean>) new JsonSerializer().deserialize(lista, ArrayList.class);
			log.info("Lista a registrar bean: "+ listadoRegistrar);
			
			//Tamaño maximo permitido
			Map<String, Object> mapa = new HashMap<String,Object>();	
			
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			
			for (T6620RequerimBean datosEscritoReq : listadoRegistrar) {
				if (numRequerimiento.equals(datosEscritoReq.getNumRequerimiento())) {
					paramBusqueda.put("codDependencia", datosEscritoReq.getCodDependencia().toString().trim());
					paramBusqueda.put("numExpOri", datosEscritoReq.getNumExpedienteOrigen().toString().trim());
					
					datosSupervisor = detalleRequerimientoService.obtenerDatosSupervisor(paramBusqueda);
					
//					datosEscritoReq.put("UuOOSupervisor", datosSupervisor.get("UuOOSupervisor").toString().trim());
					datosEscritoReq.setCodSupervisor(datosSupervisor.get("codSupervisor").toString().trim());
					datosEscritoReq.setUuOOSupervisor(datosSupervisor.get("UuOOSupervisor").toString().trim());
					
					
					paramBusqueda = new HashMap<String, Object>();
//					paramBusqueda.put("numRequerimiento", datosEscritoReq.get("numRequerimiento"));
					paramBusqueda.put("numRequerimiento", datosEscritoReq.getNumRequerimiento());
					paramBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
					
					listaDetRequerimiento = detalleRequerimientoService.obtenerListaItems(paramBusqueda);
					log.debug("listaDetRequerimiento: "+listaDetRequerimiento);
					modelo.addObject("datosEscritoReq",datosEscritoReq);
					modelo.addObject("listaDetRequerimiento",new JsonSerializer().serialize(listaDetRequerimiento));
				}				
			}
			
			for (T10372DetRequerimBean detRequerimiento : listaDetRequerimiento) {
				paramBusqueda.put("numItem", detRequerimiento.getItem());
				
				listaDocAdjuntos = documentoItemRequerimientoService.obtenerListaDocAdj(paramBusqueda);
				
				if (listaDocAdjuntos.size() > 0) {
					listaDocAdjFinal.addAll(listaDocAdjuntos);
				}
			}
			
			archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento);
			if (archivo.isDirectory()) {
				log.debug("borrar: "+archivo);
				FileUtils.deleteDirectory(archivo);
			}
			modelo.addObject("numExpVirt", new JsonSerializer().serialize(numExpVirt));
			modelo.addObject("listaDocAdjuntos", new JsonSerializer().serialize(listaDocAdjFinal));			
			modelo.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(Utils.toStr(mapaTamanoTotalPermitido.get("IT"))));
			WebUtils.setSessionAttribute(request, "datosEscritoReq", new JsonSerializer().serialize(listadoRegistrar));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoInternetController.registraEscritoReq");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CargaDocumentoInternetController.registraEscritoReq");
		}
		return modelo;
		
	}
	
	public ModelAndView exportarExcelRequerimientos(HttpServletRequest request, HttpServletResponse response){
	       
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_06;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Calendar fechaVacia = null;
		List<Map<String, Object>>  listadoExportarFinal=new ArrayList<Map<String, Object>>();
		Map<String, Object> listadoExportarInicial = new HashMap<String, Object>();
		
		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoInternetController.exportarExcelRequerimientos");
		
		try {
			
			listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
			if (log.isDebugEnabled())log.info("listadoExportarCadena:"+listadoExportarCadena);
			@SuppressWarnings("unchecked")
			List<T6620RequerimBean> listadoExportar = (ArrayList<T6620RequerimBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
			
		
			
			 for(T6620RequerimBean listaT6620 :listadoExportar){
				  listadoExportarInicial = new HashMap<String, Object>();
		    	  listadoExportarInicial.put("numRequerimOrigen", listaT6620.getNumRequerimOrigen());
		    	  listadoExportarInicial.put("strfecNotifOrigen", listaT6620.getStrfecNotifOrigen());
		    	  listadoExportarInicial.put("fecVencimiento", listaT6620.getFecVencimiento());
		    	  listadoExportarInicial.put("numRuc", listaT6620.getNumRuc());
		    	  listadoExportarInicial.put("desRazonSocial", listaT6620.getDesRazonSocial());
		    	  listadoExportarFinal.add(listadoExportarInicial);
		     }
			 
			 if (log.isDebugEnabled())log.info("exportarExcelRequerimientos-listadoExportarFinal:"+listadoExportarFinal);
			 if(listadoExportarFinal != null && !listadoExportarFinal.isEmpty()){
				 listadoExportarFinal.get(0).put("agregarRuc", true);
			 }
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
		 response.setContentType("application/vnd.ms-excel");
		 HSSFWorkbook libro = null;
		 Map<String,ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
		 columnsProperties.put("numRequerimOrigen",new ColumOption("N° Requerimiento",ExcelUtil.ALIGN.LEFT));
		 columnsProperties.put("strfecNotifOrigen",new ColumOption("Fecha de Notificacion",ExcelUtil.ALIGN.RIGHT));
		 columnsProperties.put("fecVencimiento",new ColumOption("Fecha de Vencimiento",ExcelUtil.ALIGN.RIGHT));
       
       libro=ExcelUtil.buildWorkbookXLS(titulo, "Hoja 1",columnsProperties, listadoExportarFinal);
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
  	   if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoInternetController.exportarExcelRequerimientos");
			}    	   
           
          mensajeBean.setError(true);
          mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
          view = new ModelAndView("PagM", "beanM", mensajeBean);
     }
		
	   if (log.isDebugEnabled())log.info((Object) "Final - CargaDocumentoInternetController.exportarExcelRequerimientos");
		
     return view;

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
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setDocumentoExpedienteService(DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	
	public void setRespService(ResponsableService respService) {
		this.respService = respService;
	}
	
	//[PAS20191U210500291]: JMC - INI
	public void setDetalleRequerimientoService(
			DetalleRequerimientoService detalleRequerimientoService) {
		this.detalleRequerimientoService = detalleRequerimientoService;
	}
	//[PAS20191U210500291]: JMC - FIN

	public void setDocumentoItemRequerimientoService(
			DocumentoItemRequerimientoService documentoItemRequerimientoService) {
		this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}
}