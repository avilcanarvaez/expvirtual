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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import static pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.*;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ALIGN;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

@Controller
public class CargaDocumentoIntranetController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(CargaDocumentoIntranetController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private CatalogoService catalogoService;
	private DocumentoExpedienteService documentoExpedienteService;
	private ResponsableService respService;
	// Inicio [jquispe 27/05/2016]
	private SeguimientoService seguiService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]

	@RequestMapping(value = "/consultarRequerimientosPendientesView", method = RequestMethod.GET)
	public ModelAndView consultarRequerimientosPendientesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - CargaDocumentoIntranetController.consultarRequerimientosPendientesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_01_005);
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
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
			
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize( listadoProcesos));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			
			modelAndView.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias));
			modelAndView.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("dependencia", usuarioBean.getCodDepend().trim());	
			} else {
				modelAndView.addObject("dependencia", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoIntranetController.consultarRequerimientosPendientesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoIntranetController.consultarRequerimientosPendientesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoIntranetController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/validarContribuyente" , method =  RequestMethod.GET )
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - CargaDocumentoIntranetController.validarContribuyente");
		UsuarioBean usuarioBean = null;
		DdpBean beanContribuyente;
		
		try {
			modelAndView = new ModelAndView(jsonView);
			String numeroRuc = Utils.toStr( request.getParameter("numRuc"));

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
				log.debug((Object) "Error - CargaDocumentoIntranetController.validarContribuyente");
			}
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoIntranetController.validarContribuyente");
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
			log.info( "Inicio - CargaDocumentoIntranetController.cargarListaTiposExpediente");
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
				log.debug((Object) "Error - CargaDocumentoIntranetController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoIntranetController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoIntranetController.descargarDocumento");
		
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
				log.debug((Object) "Error - CargaDocumentoIntranetController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoIntranetController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
	}
	
	@RequestMapping(value = "/obtenerTamano", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView obtenerTamano(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoIntranetController.obtenerTamaño");
		
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
				log.debug((Object) "Error - CargaDocumentoIntranetController.obtenerTamaño");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoIntranetController.obtenerTamaño");
			}
			NDC.pop();
			NDC.remove();
		}
	
		return modelAndView;
	}
	
	@RequestMapping(value = "/subirDocumentos", method = RequestMethod.POST)
	public ModelAndView subirDocumentos(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoIntranetController.subirDocumentos");
		
		
		Map<String, Object> parametros = null;
		UsuarioBean usuarioBean;
		String numExpedienteVirtual;
		String numExpedienteOrigen;
		String numRuc;
		String codTipoExpediente;
		String numRequerimiento;
		String numRequerimientOrigen;
		String fechaRequerimiento;
		String estadoExpediente;
		String razonSocial;
		String proceso;
		String desTipoExpediente;
		String fechaVencimiento = null;
		String flagAcceso;
		String error="";
		try {
			request.setCharacterEncoding("UTF-8");
			String listaDocumentosSel = Utils.toStr(request.getParameter("listaDocumentosSel"));
			List<Map<String, Object>> listaDocumentosASubir = null;
			
			//Inicio LEstrada SNADE307-1031
			List<Map<String, Object>> listaDocumentosASubirInicial = null;
			List<Map<String, Object>> listaDocumentosASubirResult = new ArrayList<Map<String, Object>> ();
			TreeMap<Integer, Map<String, Object>> tMapDocumentosASubir = new TreeMap<Integer, Map<String, Object>>();
			listaDocumentosASubirInicial  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
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
				List<T6623TipDocExpBean> listT6623TipDocExpBean = configuracionExpedienteService.listarTipDocExpediente(mapBusqueda);
				
				List<T6621RespExpVirtBean> listaRespAsignados = respService.obtenerResponsablesAsignadosSepar(numExpedienteVirtual);
				
				numRuc = Utils.toStr(request.getParameter("hidTxtRuc"));
				
				
				numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento"));
				numRequerimientOrigen = Utils.toStr(request.getParameter("txtNumRequerimiento"));
				fechaRequerimiento = Utils.toStr(request.getParameter("hidTxtFecRequerimiento"));
				estadoExpediente = Utils.toStr(request.getParameter("hidEstadoExpediente"));
				razonSocial  = Utils.toStr(request.getParameter("hiddTxtRazonSocial"));
				proceso = Utils.toStr(request.getParameter("hiddTxtProceso"));
				desTipoExpediente = Utils.toStr(request.getParameter("hidDesTipoExpediente"));
				fechaVencimiento = Utils.toStr(request.getParameter("hidTxtFecVencimiento"));
				flagAcceso=Utils.toStr(request.getParameter("hidTxtTipoOpcion"));
				parametros = new HashMap<String, Object>();
				parametros.put("listaDocumentosASubir", listaDocumentosASubir);
				parametros.put("numExpedienteVirtual", numExpedienteVirtual);
				parametros.put("numExpedienteOrigen", numExpedienteOrigen);
				parametros.put("numRuc", numRuc);
				parametros.put("codTipoExpediente", codTipoExpediente);
				parametros.put("codUsuarioCarga", usuarioBean.getNroRegistro());
				parametros.put("numRequerimiento", numRequerimiento);
				parametros.put("fechaRequerimiento", fechaRequerimiento);
				parametros.put("estadoExpediente", estadoExpediente);
				parametros.put("razonSocial", razonSocial);
				parametros.put("proceso", proceso);
				parametros.put("desTipoExpediente", desTipoExpediente);
				parametros.put("fechaVencimiento", fechaVencimiento);
				parametros.put("listT6623TipDocExpBean", listT6623TipDocExpBean);
				parametros.put("listaRespAsignados", listaRespAsignados);
				parametros.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTRANET);
				parametros.put("codDep", usuarioBean.getCodDepend());
				parametros.put("flagAcceso", flagAcceso);
				//Actualizamos el Requerimiento
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("numExpedienteVirtual", Utils.toStr(parametros.get("numExpedienteVirtual")));
				map.put("numRequerimiento", Utils.toStr(parametros.get("numRequerimiento")));
				map.put("estadoDocumentoReq", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_SOLICITADO);
				
				List<T6613DocExpVirtBean> listT6613DocExpVirtBean = documentoExpedienteService.listarDocumentosExpediente(map);
				parametros.put("listT6613DocExpVirtBean", listT6613DocExpVirtBean);
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
				log.debug((Object) "Error - CargaDocumentoIntranetController.subirDocumentos");
			}
			log.error(ex, ex);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_003);
			modelAndView.addObject("error", ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS);
			return modelAndView;

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoIntranetController.subirDocumentos");
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarListadoRequerimientosPendientes", method = RequestMethod.GET)
	public ModelAndView cargarListadoRequerimientosPendientes(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoIntranetController.cargarListadoRequerimientosPendientes");

		ModelAndView modelAndView = null;
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();
		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		// Inicio [jquispe 27/05/2016] Realiza la accion solo cuando el usuario realiza la consulta.
		boolean flagBusquedaBoton = false;
		// Fin [jquispe 27/05/2016]
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
			mapParametrosBusqueda.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			//Inicio - [oachahuic][PAS20175E210400016]
			List<String> listCodEstReq = new ArrayList<String>();
			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ABIERTO);
			listCodEstReq.add(ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL);
			mapParametrosBusqueda.put("listCodEstReq", listCodEstReq);
			//Fin - [oachahuic][PAS20175E210400016]
			
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
					
					listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

					if (Utils.isEmpty(listT6620RequerimBean)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
						return modelAndView;
					} else {
						// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
						if(flagBusquedaBoton){
							T6620RequerimBean bean = listT6620RequerimBean.get(0);
							realizarSeguimientoConsultaRequerimientoPendientePorAtender(bean.getNumExpedienteVirtual());
						}
						// Fin [jquispe 27/05/2016]
						modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);
						return modelAndView;
					}
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
			
			listT6620RequerimBean = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

			if (Utils.isEmpty(listT6620RequerimBean)) {
				modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
				if(flagBusquedaBoton){
					realizarSeguimientoConsultaRequerimientoPendientePorAtender(null);
				}
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listT6620RequerimBean",listT6620RequerimBean);				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoIntranetController.cargarListadoRequerimientosPendientes");
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
				log.debug( "Final - CargaDocumentoIntranetController.cargarListadoRequerimientosPendientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void realizarSeguimientoConsultaRequerimientoPendientePorAtender(String numeroExpedienteVirtual) throws Exception{
		if (log.isDebugEnabled())log.debug("Inicio - CargaDocumentoIntranetController.realizarSeguimientoConsultaRequerimientoPendientePorAtender");
		
		Map<String, Object> beanSegui = new HashMap<String, Object>();
		
		if (log.isDebugEnabled())log.debug("Proceso - CargaDocumentoIntranetController.realizarSeguimientoConsultaRequerimientoPendientePorAtender");
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_ATENCION_REQUERIMIENTO);
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
		if (log.isDebugEnabled())log.debug("Final - CargaDocumentoIntranetController.realizarSeguimientoConsultaRequerimientoPendientePorAtender");
	}
	// Fin [jquispe 27/05/2016]
	
	@RequestMapping(value = { "/consultarDetalleRequerimientoView" }, method = { RequestMethod.GET })
	public ModelAndView consultarDetalleRequerimientoView(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoIntranetController.consultarDetalleRequerimientoView");
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
			request.setCharacterEncoding("UTF-8");
			String numExpedVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			String numRequerimiento = Utils.toStr(request.getParameter("numRequerimiento"));
			
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_02_002);
			
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
				 //Fin nchavez 13/06/2016
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
			
			/*lestrada 15092016*/
			Map<String, Object> flag = new HashMap<String, Object>();
			flag.put("flagAcceso", AvisoConstantes.FLAG_INTRANET);
			/*lestrada 15092016*/
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
			modelAndView.addObject("tamanoMaximoPermitidoIntranet",Utils.toStr(mapaTamanoTotalPermitido.get("IA")));
			modelAndView.addObject("listExtensionesPermitidas",new JsonSerializer().serialize(listExtensionesPermitidas));
			modelAndView.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(mapaTamanoTotalPermitido));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - CargaDocumentoIntranetController.consultarDetalleRequerimientoView");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - CargaDocumentoIntranetController.consultarDetalleRequerimientoView");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/mostrarView",  method =  RequestMethod.GET)
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoIntranetController.mostrarView");
		
		ModelAndView modelAndView = null;
		
		String pageView = request.getParameter("pageView");
		modelAndView = new ModelAndView("ConsultaExpedientesVirtualesIntranet");
		try {
			
			if(Utils.equiv(pageView, NavegaConstantes.MANT_MODULO_02_01_005)){
				
				UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				
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
				modelAndView.addObject("dependencia", usuarioBean.getCodDepend());
				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoIntranetController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoIntranetController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - CargaDocumentoIntranetController.mostrarView");
		return modelAndView;
	}
	
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	    
		log.info((Object) "Inicio - CargaDocumentoIntranetController.exportarExcelExpedientes");
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_02;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Calendar fechaVacia = null;
   
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
			//Inicio nchavez 09/06/2016
			Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numRequerimOrigen",new ColumOption("N° Requerimiento", ALIGN.LEFT));
			columnsProperties.put("numExpedienteVirtual",new ColumOption("N° Expediente Virtual", ALIGN.LEFT));
			columnsProperties.put("numExpedienteOrigen",new ColumOption("N° Expediente Origen", ALIGN.LEFT));
			columnsProperties.put("numRuc",new ColumOption("RUC", ALIGN.LEFT));
			columnsProperties.put("desRazonSocial",new ColumOption("Razón Social", ALIGN.LEFT));
			columnsProperties.put("desOrigRequerimiento",new ColumOption("Origen", ALIGN.LEFT));
			columnsProperties.put("desProceso",new ColumOption("Proceso", ALIGN.LEFT));
			columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente", ALIGN.LEFT));
			columnsProperties.put("fecRegistroExpediente",new ColumOption("Fecha de Registro del Expediente", ALIGN.RIGHT));
			columnsProperties.put("fechaDocumentoOrigen",new ColumOption("Fecha del Documento Origen", ALIGN.CENTER));
			columnsProperties.put("fechaRequerimiento",new ColumOption("Fecha del Requerimiento", ALIGN.RIGHT));
			columnsProperties.put("fechaVencimiento",new ColumOption("Fecha de Vencimiento", ALIGN.RIGHT));
			
			HSSFWorkbook libro=buildWorkbookXLS(titulo, "Hoja 1", columnsProperties, listadoExportar);
			//Fin nchavez 09/06/2016   

		 Calendar calendar = Calendar.getInstance();
		
		 int anio = (calendar.get(Calendar.YEAR));
		 int dia = (calendar.get(Calendar.DATE));
		 int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
		 int minutos = (calendar.get(Calendar.MINUTE));
		 String dd = (dia<10) ? "0"+dia : dia+"";
		 int mes = calendar.get(Calendar.MONTH)+1;
		 String mm = (mes<10) ? "0"+mes : mes+"";
		 String part=anio+mm+dd+"_"+hora+minutos;
		   
		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_REQ_XLS + part + ".xls";
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
             log.error("Error - CargaDocumentoIntranetController.exportarExcelExpedientes", ex);
             mensajeBean.setError(true);
             mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar "+ ex.getMessage());
             view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		log.info((Object) "Fin - CargaDocumentoIntranetController.exportarExcelExpedientes");
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

	// Inicio [jquispe 27/05/2016]
	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}
	// Fin [jquispe 27/05/2016]

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	
}