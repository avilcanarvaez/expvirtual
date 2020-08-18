package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import static pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.buildWorkbookXLS;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONObject;
import net.sf.sojo.interchange.json.JsonParser;
import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DetalleRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ALIGN;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.pdf.PdfReader;
@Controller
public class CargaDocumentoEscritoIAController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(CargaDocumentoEscritoIAController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private CatalogoService catalogoService;
	private DocumentoExpedienteService documentoExpedienteService;
	private ResponsableService respService;
	private SeguimientoService seguiService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	private DetalleRequerimientoService detalleRequerimientoService;
	private DocumentoItemRequerimientoService documentoItemRequerimientoService; 
	
	
	@RequestMapping(value = "/consultarExpedientesPendientesView", method = RequestMethod.GET)
	public ModelAndView consultarExpedientesPendientesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - CargaDocumentoEscritoIAController.consultarExpedientesPendientesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_001);

			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			List<T01ParamBean> listadoDependencias = configuracionExpedienteService.listarDependencias();
			
			List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			
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
			
			modelAndView.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias) );
			modelAndView.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente) );
			modelAndView.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha) );
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoEscritoIAController.consultarExpedientesPendientesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoIAController.consultarExpedientesPendientesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoIAController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/validarContribuyente" , method =  RequestMethod.GET )
	public ModelAndView validarContribuyente(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled())log.info( "Inicio - CargaDocumentoEscritoIAController.validarContribuyente");
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
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.validarContribuyente");
			}
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.validarContribuyente");
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
			log.info( "Inicio - CargaDocumentoEscritoIAController.cargarListaTiposExpediente");
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
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.cargarListaTiposExpediente");
			}
			
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.cargarListaTiposExpediente");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoIAController.descargarDocumento");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		
		try {
			
//			String codDocumento = Utils.toStr(request.getParameter("hidDocumentosSel"));
			String nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidNombreDoc")));
//			String numRequerimiento = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidTxtNumRequerimiento"))).trim();
			String numExpediente = Utils.toStr(request.getParameter("hidTxtNumExpedienteVirt")).trim();
			/*multipartFile = multipartRequest.getFile("file"+codDocumento);
			archivo = Utils.multipartToFile(multipartFile);
			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+multipartFile.getOriginalFilename());
			FileUtils.copyFile(archivo, destino);*/
			if (log.isDebugEnabled()) log.info( "CargaDocumentoEscritoIAController.descargarDocumento"+ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numExpediente+"@@"+nombreArchivo);
			File downloadFile = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numExpediente+"/"+nombreArchivo);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numExpediente+"/"+nombreArchivo); 
	         
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
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
	}
	
	@RequestMapping(value = "/obtenerTamano", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView obtenerTamano(HttpServletRequest request,HttpServletResponse response) {
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoITController.obtenerTamano");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		ModelAndView modelAndView = null;
		int noPages = 0;
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		Map<String, Object> resultado = null;
		String fileLoc = null;
		String nombreArchivoTempZip = null;
		try {
			modelAndView = new ModelAndView(jsonView);
			multipartFile = multipartRequest.getFile("docFisico");
			String numExpediente = Utils.toStr(request.getParameter("hidTxtNumExpedienteVirt")).trim();
			String mimeType = multipartFile.getContentType();
			byte[] FileByte=multipartFile.getBytes();
			String arrayMimeType[] = null;
			log.debug("Leyendo entrada de mimeType ***: " +  mimeType);
			arrayMimeType = mimeType.split(";");
			mimeType = arrayMimeType[0];
			archivo = Utils.multipartToFile(multipartFile);
			
			log.info("***********Ruta:*****************************"+ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numExpediente+"/"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			log.info("***********Nombre Archivo:(multipartFile.getOriginalFilename())***********************"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			log.debug("numRequerimiento: "+numExpediente);

			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numExpediente+"/"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			int tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IT"));
			
			if(!(multipartFile.getSize()>Math.round((tamanoMaximo*1048576/100000)*100000))){
				modelAndView.addObject("archivoTamanoPermitido", true);
			} else {
				modelAndView.addObject("archivoTamanoPermitido", false);
				return modelAndView;
			}
			
			//LONGITUD NOMBRE DE ARCHIVO			
			String nombreArchivo = Utils.convertirUnicode(multipartFile.getOriginalFilename());
			
			String[] listaSeparada = nombreArchivo.split("\\.");
			Integer num = listaSeparada.length;
			String extensionArchivo = listaSeparada[num - 1].toLowerCase();
			if (log.isDebugEnabled()) log.debug("extensionArchivo: "+extensionArchivo);
			//--------------------------------------------------------------------------------------
			
			//EXTENSIONES PERMITIDAS
			mapa = new HashMap<String,Object>();
			
			List<Map<String, Object>> listadoParametros = new ArrayList<Map<String, Object>>();
			mapa.put("codParametro", "01");
			listadoParametros.add(mapa);
			mapa = new HashMap<String,Object>();
			mapa.put("codParametro", "13");
			listadoParametros.add(mapa);
			
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapa.put("listadoParametrosIncluidos", listadoParametros);
			
			List<T01ParamBean> listExtensionesPermitidas = paramService.listarParametros(mapa);
			String [] extension = new String[2];
			Integer j = 0;
			
			for (T01ParamBean extensionPermitida: listExtensionesPermitidas) {
				extension[j] = extensionPermitida.getDesParametro().trim();
				j = j + 1;				
			}
			
			if ((extension[0].equals(extensionArchivo.trim())) || (extension[1].equals(extensionArchivo.trim()))) { 
				modelAndView.addObject("archivoExtensionPermitida", true);
			} else {
				modelAndView.addObject("archivoExtensionPermitida", false);
				return modelAndView;
			}
			//--------------------------------------------------------------------------------------			
			
			if (("zip").equals(extensionArchivo.trim())) {	
				if (log.isDebugEnabled()) log.debug("Ingreso cuando es archivo .zip");
				if (log.isDebugEnabled()) log.debug("archivo:  "+archivo);
				if (log.isDebugEnabled()) log.debug("destino:  "+destino);
				FileUtils.copyFile(archivo, destino);
	            boolean  validado = validarContenidoZip(destino);
	            if (log.isDebugEnabled()) log.debug("validar archivo:  "+validado);
				//VALIDAR EXISTENCIA DE OTROS ARHIVOS
				if(!validado){
					
					destino.delete();
					modelAndView.addObject("archivoOtrosNoExiste", false);
					return modelAndView;
				} else { 
					modelAndView.addObject("archivoOtrosNoExiste", true);
					// validar que tenga los archivos completos
					boolean  validarCantidad = validarCantidadContenidoZip(destino);
					 if (log.isDebugEnabled()) log.debug("validarCantidad***:  "+validarCantidad);
					if(!validarCantidad){
						modelAndView.addObject("archivoCertSigNoExiste", false);
						return modelAndView;
					}else{
						modelAndView.addObject("archivoCertSigNoExiste", true);
						
						if (log.isDebugEnabled()) log.debug("Inicio - CargaDocumentoEscritoITController.obtenerTamano -  valida Firma archivos ZIP");
						
						if (log.isDebugEnabled())log.debug("destino.getAbsolutePath(): " + destino.getAbsolutePath());

				        //DESCOMPRENSION ARCHIVOS ZIP(PDF TXT)
				        String dirDescomp = ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + nombreArchivo.replace(".zip","") +"/";
				        if (log.isDebugEnabled())log.debug("dirDescomp: " + dirDescomp);
						List<String> lstFiles = Utils.unZipFiles(destino.getAbsolutePath(), dirDescomp);
						
						
						for (String stFile : lstFiles) {
							if (log.isDebugEnabled())log.debug("stFile: " + stFile);
						}
						
						//LEER FILES DEL DIRECTORIO
						File newDir = new File(dirDescomp);
						File[] listOfFiles = newDir.listFiles();
						for (File ofFile : listOfFiles) {
							//primero obtenemos el CERTIFICADO
							if(ofFile.getName().endsWith(".crt")){
								byte[] bytesCrt = Utils.loadFile(ofFile);
					            byte[] encodedCrt = Base64.encodeBase64(bytesCrt);
					            String encodedStringCrt = new String(encodedCrt);
					            if (log.isDebugEnabled())log.debug("*encodedString-->Crt ***: " +   encodedStringCrt);
					            
					            // obtener el archivo txt o xls
					            for (File ofFileTxtXls : listOfFiles) {
					            	if(ofFileTxtXls.getName().endsWith(".txt")||ofFileTxtXls.getName().endsWith(".xls")||ofFileTxtXls.getName().endsWith(".xlsx")){
//					            		byte[] bytesTxt = Utils.loadFile(ofFileTxtXls);
//							            byte[] encodedTxt = Base64.encodeBase64(bytesTxt);
//							            String encodedStringTxt = new String(encodedTxt);
							            String encodedStringTxt=	this.zipB64(ofFileTxtXls);
							            if (log.isDebugEnabled())log.debug("*encodedString-->Txt 2***: " +   encodedStringTxt);
							            if (log.isDebugEnabled())log.debug("*ofFileTxtXls.getName() ***: " +   ofFileTxtXls.getName());
							            if (log.isDebugEnabled())log.debug("*encodedString-->Txt ***: " +   encodedStringTxt);
							            String nombreSinExtension = Utils.obtenerNombreSinExtension(ofFileTxtXls.getName());
							            
							            MimetypesFileTypeMap mtft = new MimetypesFileTypeMap();
										String mimeTypeArchivo = mtft.getContentType(ofFileTxtXls.getName());
										if (log.isDebugEnabled())log.debug("*mimeType1-->Txt o XLS ***: " +   mimeTypeArchivo);
							            // obtener el archivo SIG
							            for (File ofFileTxtSign : listOfFiles) {
											if(nombreSinExtension.concat(".sig").equals(ofFileTxtSign.getName()) ){
												if (log.isDebugEnabled())log.debug("*ofFileTxtXls.getName() sig***: " +   ofFileTxtSign.getName());
												byte[] bytesSig = Utils.loadFile(ofFileTxtSign);
											    byte[] encodedSig = Base64.encodeBase64(bytesSig);
											    String encodedStringSig = new String(encodedSig);
											    if (log.isDebugEnabled())log.debug("*encodedString-->Sig ***: " +   encodedStringSig);
											
											    // validar firma digital
												paramBusqueda.put("archivo", encodedStringTxt);
												paramBusqueda.put("tipoMime", mimeTypeArchivo);
												paramBusqueda.put("firma", encodedStringSig);
												paramBusqueda.put("certificadoX509", encodedStringCrt);
												paramBusqueda.put("tipoCifrado", "sha256");
												// validar llama al servicio de validacion de firma 
												resultado=servicioValidaFirma(paramBusqueda);
												
												if(Utils.toStr(resultado.get("codigo"))=="01"){
													modelAndView.addObject("mensajerpta", resultado.get("mensajerpta"));
													modelAndView.addObject("mensaje", resultado.get("mensaje"))	;
												}else{
													modelAndView.addObject("mensajerpta", resultado.get("mensajerpta"));
													modelAndView.addObject("mensaje", resultado.get("mensaje"));
												}
											}
							            }
					            	}
					            }
							}    
						}
					}
					
				}
				//--------------------------------------------------------------------------------------
				noPages = 1;
				
			} else if (("pdf").equals(extensionArchivo.trim())){
				int iContadorFilesPDFPro = 0;
				int iContadorFilesPDFA = 0;
				
				FileUtils.copyFile(archivo, destino);				
				
				//COMPROBAR QUE EL PDF sea TIPO PDF/A
				try {
					if (log.isDebugEnabled()) log.debug("Inicio - CargaDocumentoEscritoITController.obtenerTamano - PDF/A");
					byte[] bytesArray = new byte[(int) destino.length()];
					FileInputStream fis = new FileInputStream(destino);
					fis.read(bytesArray); //read file into bytes[]
					fis.close();
					
					PdfReader readerPdfA = new PdfReader(bytesArray);
					
                    if( !Utils.validarPDFA(readerPdfA.getMetadata())){
                    	log.debug("El Documento no es PDF/A:" + !Utils.validarPDFA(readerPdfA.getMetadata()));
                    	iContadorFilesPDFA++;
    					destino.delete();
                    }
                    if (log.isDebugEnabled()) log.debug("Fin - CargaDocumentoEscritoITController.obtenerTamano - PDF/A");
                    
				} catch (Throwable ex) {
					if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoEscritoITController.obtenerTamano - PDF/A");
					log.error( ex, ex);
				}
	       
				if(iContadorFilesPDFA > 0){
					modelAndView.addObject("archivoPdfA", false);
					return modelAndView;
				} else {
					modelAndView.addObject("archivoPdfA", true);
				}
				//COMPROBAR QUE EL PDF NO SE ENCUENTRE PROTEGIDO
				if (log.isDebugEnabled()) log.debug("COMPROBAR QUE EL PDF NO SE ENCUENTRE PROTEGIDO");
				try {
					
					byte[] bytesArray = new byte[(int) destino.length()];
					FileInputStream fis = new FileInputStream(destino);
					fis.read(bytesArray); //read file into bytes[]
					fis.close();
					
					PdfReader reader = new PdfReader(bytesArray);
                    
                    if(reader.getPermissions()!=0){
                    	log.debug("El Documento no tiene Permiso:" +reader.getPermissions());
                    }
				} catch (Throwable ex) {
					if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoEscritoITController.obtenerTamano - PDF PROTEGIDO");
					iContadorFilesPDFPro++;
					destino.delete();
				}
				
				if(iContadorFilesPDFPro > 0){
					modelAndView.addObject("archivoPdfProtegido", false);
					return modelAndView;
				} else {
					modelAndView.addObject("archivoPdfProtegido", true);
					// comprobar que pdf tenga firma digital
					
					//COMPRIMIR ARCHIVOS DESCOMPRIMIDOS
						String zipPdfB64=	this.zipB64(archivo);
						if (log.isDebugEnabled()) log.debug("pdf zip cifrado:"+zipPdfB64);
					// validar firma digital
									
//					paramBusqueda.put("archivo", DatatypeConverter.printBase64Binary(FileByte));
					paramBusqueda.put("archivo", zipPdfB64);
					paramBusqueda.put("tipoMime", mimeType);
					paramBusqueda.put("firma", "");
					paramBusqueda.put("certificadoX509", "");
					paramBusqueda.put("tipoCifrado", "");
					// validar llama al servicio de validacion de firma 
					resultado=servicioValidaFirma(paramBusqueda);
					
					if(Utils.toStr(resultado.get("codigo"))=="01"){
						modelAndView.addObject("mensajerpta", resultado.get("mensajerpta"));
						modelAndView.addObject("mensaje", resultado.get("mensaje"))	;
					}else{
						modelAndView.addObject("mensajerpta", resultado.get("mensajerpta"));
						modelAndView.addObject("mensaje", resultado.get("mensaje"));
					}
				}
				
				
				PdfReader document = new PdfReader(new FileInputStream(destino)); 
				noPages = document.getNumberOfPages();
				modelAndView.addObject("archivoOtrosNoExiste", true);
			}			
			modelAndView.addObject("peso", multipartFile.getSize());
			modelAndView.addObject("nombreArchivo", Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			modelAndView.addObject("mimeType", mimeType);
			modelAndView.addObject("folios", noPages);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoITController.obtenerTamano");
			}
			log.error( ex, ex);
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoITController.obtenerTamano");
			}
			NDC.pop();
			NDC.remove();
		}	
		return modelAndView;
	
	}

	public String zipB64(File files) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ZipOutputStream zos = new ZipOutputStream(baos);
			ZipEntry ze = new ZipEntry(files.getName());
			zos.putNextEntry(ze);
			zos.write(IOUtils.toByteArray(new FileInputStream(files)));
			zos.closeEntry();
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] byteszip1 = baos.toByteArray();
		byte[] encodedCrt1 = Base64.encodeBase64(byteszip1);
		String encodedStringCrtzip = new String(encodedCrt1);

		return encodedStringCrtzip;

	}
	public Map<String, Object> servicioValidaFirma(Map<String, Object> parametros) {
		Map<String, Object> mapRpta = new HashMap<String, Object>();
		String  mensaje = null;
		Map<String, Object> mapParamWS = null;
		HttpEntity<String> entity = null;
		String jsonRequest = null;
		String jsonResponse = null;
		RestTemplate restTemplate = null;
		HttpHeaders headers = null;
		JsonSerializer serializer = null;
		byte[] archivoBytes = null;
		String codigoOk=null;
		
		try {
			// SE AGREGO EL SERVICIO REST
			mapParamWS = new HashMap<String, Object>();
			mapParamWS.put("archivo",parametros.get("archivo"));
			mapParamWS.put("tipoMime", parametros.get("tipoMime"));
			mapParamWS.put("firma", parametros.get("firma"));
			mapParamWS.put("certificadoX509", parametros.get("certificadoX509"));
			mapParamWS.put("tipoCifrado", parametros.get("tipoCifrado"));

			String url = "http://Sunatti.k8s.sunat.peru/v1/tecnologia/solucion/t/archivos/validarfirma";
			restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			serializer = new JsonSerializer();
			jsonRequest = (String) serializer.serialize(mapParamWS);
			if (log.isDebugEnabled())log.debug("jsonRequest:" + jsonRequest);
			entity = new HttpEntity<String>(jsonRequest, headers);
			if (log.isDebugEnabled())log.debug("entity:" + entity);
			jsonResponse = restTemplate.postForObject(url, entity,String.class);
			if (log.isDebugEnabled())log.debug("jsonResponse:" + jsonResponse);
			
			ObjectMapper mapperOK = new ObjectMapper();
			JsonNode node = null;
			try {
				node = mapperOK.readTree(jsonResponse);
				codigoOk=node.path("cod").toString();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        if (log.isDebugEnabled())log.debug("codigoOk:" + codigoOk);
	        
			mapRpta.put("codigo",false);
			mapRpta.put("mensajerpta",false);
			mapRpta.put("mensaje","");

		} catch (HttpStatusCodeException ex) {
			logger.error(ex.getResponseBodyAsString());

			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			try {
				node = mapper.readTree(ex.getResponseBodyAsString());
				Iterator<JsonNode> albums = node.path("errors").iterator();
				String  codigo=node.path("cod").toString();
				if (log.isDebugEnabled())log.debug("codigo:" + codigo);
				mapRpta.put("codigo",codigo);
				if(codigo.replace("\"", "").equals("500")){
					mensaje = node.path("msg").toString();
					mapRpta.put("mensajerpta",true);
					mapRpta.put("mensaje",mensaje);
				}else{
					while (albums.hasNext()) {
						mensaje = albums.next().path("msg").toString();
						mapRpta.put("mensajerpta",true);
						mapRpta.put("mensaje",mensaje);
					}
				}
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return mapRpta;
	}
	
	@RequestMapping(value = "/subirDocumentos", method = RequestMethod.POST)
	public ModelAndView subirDocumentos(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoIAController.subirDocumentos");
		
		
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
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.subirDocumentos");
			}
			log.error(ex, ex);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("mensajeRetorno", AvisoConstantes.AVISO_MODULO_04_06_003);
			modelAndView.addObject("error", ValidaConstantes.ERROR_AL_SUBIR_ARCHIVOS);
			return modelAndView;

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.subirDocumentos");
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarListadoExpedientesPendientes", method = RequestMethod.GET)
	public ModelAndView cargarListadoExpedientesPendientes(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoIAController.cargarListadoExpedientesPendientes");

		ModelAndView modelAndView = null;
		List<T6620RequerimBean> listT6620RequerimBean = new ArrayList<T6620RequerimBean>();
		UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
		
		String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
		String numExp = Utils.toStr(request.getParameter("numExp"));

		try {

			modelAndView = new ModelAndView(jsonView);
			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codEstado", ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			//validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN);
				mapParametrosBusqueda.put("numExpediente", numExp);
				
				T6614ExpVirtualBean t6614ExpVirtualBean = expedienteVirtualService.obtenerExpedienteVirtual(mapParametrosBusqueda);
				
				if (Utils.isEmpty(t6614ExpVirtualBean)) {
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_04_003);
					return modelAndView;
				} else {
					
					String tipoExp = t6614ExpVirtualBean.getCodTipoExpediente();
					
					if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(tipoExp) || 
						ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(tipoExp)){
						
						mapParametrosBusqueda = new HashMap<String, Object>();
						mapParametrosBusqueda.put("numExpedienteVirtual", t6614ExpVirtualBean.getNumExpedienteVirtual());
						mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
						
						List<T6621RespExpVirtBean> listaResponsables = respService.listarResponsables(mapParametrosBusqueda);
											
						int cantidad = 0;			
						for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
												
							String usuarioLogueado = usuarioBean.getNroRegistro().trim();
							String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
							
							if(usuarioLogueado.equals(responsableExp)){
								
								cantidad++;						
								break;
								
							}
						}
						if(cantidad== 0){
//							excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
							modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_10);
//							modelAndView.addObject("msjError","El expediente se encuentra asignado a otro agente fiscalizador");
							return modelAndView;
						}
					}else{
						modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_04_003);
						return modelAndView;
					}
						modelAndView.addObject("listT6614ExpVirtualBean",t6614ExpVirtualBean);
						return modelAndView;
				}
				
				
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - CargaDocumentoEscritoIAController.cargarListadoExpedientesPendientes");
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
				log.debug( "Final - CargaDocumentoEscritoIAController.cargarListadoExpedientesPendientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	
	@RequestMapping(value = { "/consultarDetalleExpedienteView" }, method = { RequestMethod.GET })
	public ModelAndView consultarDetalleExpedienteView(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoIAController.consultarDetalleExpedienteView");
		ModelAndView modelAndView;
		
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		DdpBean beanContribuyente;
		
		try {
			request.setCharacterEncoding("UTF-8");
			String numExpedVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_002);
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				 beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 
				 modelAndView.addObject("razonSocial",!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
				 t6614ExpVirtualBean.setDesProceso(t6614ExpVirtualBean.getDesProceso().toUpperCase());
				 t6614ExpVirtualBean.setDesTipoExpediente(t6614ExpVirtualBean.getDesTipoExpediente().toUpperCase());
//				 t6614ExpVirtualBean.setCodTipoExpediente(t6614ExpVirtualBean.getCodTipoExpediente());
				 
				 if (log.isDebugEnabled())log.info("***************111*****:"+t6614ExpVirtualBean.getCodTipoExpediente());
				 modelAndView.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 break;
			}
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			modelAndView.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(mapaTamanoTotalPermitido));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - CargaDocumentoEscritoIAController.consultarDetalleExpedienteView");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - CargaDocumentoEscritoIAController.consultarDetalleExpedienteView");
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/mostrarView",  method =  RequestMethod.GET)
	public ModelAndView mostrarView(HttpServletRequest request,
			HttpServletResponse response) {
		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoIAController.mostrarView");
		
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
				log.debug("Error - CargaDocumentoEscritoIAController.mostrarView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoIAController.mostrarView");
			}
			NDC.pop();
			NDC.remove();
		}
		
		if (log.isDebugEnabled())log.info((Object) "Final - CargaDocumentoEscritoIAController.mostrarView");
		return modelAndView;
	}
	
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
	    
		log.info((Object) "Inicio - CargaDocumentoEscritoIAController.exportarExcelExpedientes");
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_07;
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
			Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numExpedienteOrigen",new ColumOption("N° Expediente ", ALIGN.LEFT));
			columnsProperties.put("desOrigen",new ColumOption("Origen", ALIGN.LEFT));
			columnsProperties.put("desProceso",new ColumOption("Proceso", ALIGN.LEFT));
			columnsProperties.put("desTipoExpediente",new ColumOption("Tipo de Expediente", ALIGN.LEFT));
			columnsProperties.put("fecRegistro",new ColumOption("Fecha de Registro del Expediente", ALIGN.RIGHT));
			
			HSSFWorkbook libro=buildWorkbookXLS(titulo, "Hoja 1", columnsProperties, listadoExportar);
			//

		 Calendar calendar = Calendar.getInstance();
		
		 int anio = (calendar.get(Calendar.YEAR));
		 int dia = (calendar.get(Calendar.DATE));
		 int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
		 int minutos = (calendar.get(Calendar.MINUTE));
		 String dd = (dia<10) ? "0"+dia : dia+"";
		 int mes = calendar.get(Calendar.MONTH)+1;
		 String mm = (mes<10) ? "0"+mes : mes+"";
		 String part=anio+mm+dd+"_"+hora+minutos;
		   
		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_EXPE_XLS + part + ".xls";
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
             log.error("Error - CargaDocumentoEscritoIAController.exportarExcelExpedientes", ex);
             mensajeBean.setError(true);
             mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar "+ ex.getMessage());
             view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		log.info((Object) "Fin - CargaDocumentoEscritoIAController.exportarExcelExpedientes");
       return view;

	}
	
	@RequestMapping(value = { "/registraEscrito" }, method = { RequestMethod.POST })
	public ModelAndView registraEscrito (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - CargaDocumentoEscritoIAController.registraEscrito");
		
		ModelAndView modelo = new ModelAndView("CargaDocumentosEscritoIntranet");
		List<T10373DocAdjReqBean> listaDocAdjuntos = new ArrayList<T10373DocAdjReqBean>();
		List<T10373DocAdjReqBean> listaDocAdjFinal = new ArrayList<T10373DocAdjReqBean>();

		List<T6614ExpVirtualBean> listaExpedientesVirtuales;
		DdpBean beanContribuyente;
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		File archivo;
		
		try {
			request.setCharacterEncoding("UTF-8");
			String lista = Utils.toStr(request.getParameter("listadoExpedientesCadena")); 
			String numExpVirt = Utils.toStr(request.getParameter("hidTxtNumExpVirt")).trim();
			
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpVirt);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
//			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				
				beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());	
				 
				 modelo.addObject("razonSocial",!Utils.isEmpty(beanContribuyente)?beanContribuyente.getDesRazonSocial():ValidaConstantes.CADENA_VACIA);
				 modelo.addObject("t6614ExpVirtualBean",t6614ExpVirtualBean);
				 break;
			}
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoRegistrar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
			if (log.isDebugEnabled())log.info("Lista a registrar: "+ listadoRegistrar);
			
			//Tamaño maximo permitido
			Map<String, Object> mapa = new HashMap<String,Object>();	
			
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);

			archivo = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numExpVirt);
			if (archivo.isDirectory()) {
				if (log.isDebugEnabled())log.debug("borrar: "+archivo);
				FileUtils.deleteDirectory(archivo);
			}
			modelo.addObject("numExpVirt", new JsonSerializer().serialize(numExpVirt));			
			modelo.addObject("tamanoMaximoPermitido",new JsonSerializer().serialize(Utils.toStr(mapaTamanoTotalPermitido.get("IT"))));
			WebUtils.setSessionAttribute(request, "t6614ExpVirtualBean", new JsonSerializer().serialize(listadoRegistrar));
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoEscritoIAController.registraEscrito");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - CargaDocumentoEscritoIAController.registraEscrito");
		}
		return modelo;
		
	}
	
	public static boolean validarContenidoZip(File archivo){
		boolean correcto = true;
		if(!archivo.exists() || !archivo.isFile())
			throw new RuntimeException("El archivo :" +  archivo.getAbsolutePath() + " no existe o no es un directorio");
		if (log.isDebugEnabled())log.debug("Validando archivo " + archivo.getAbsolutePath());
		ZipInputStream zis;
		try {
			zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(archivo)));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (log.isDebugEnabled())log.debug("archivo es folder: " +  entry.isDirectory());
				if(!entry.isDirectory()){
					String nombre = entry.getName();
					if (log.isDebugEnabled())log.debug("Leyendo entrada de archivo: " +  nombre);
					
					if(nombre.toLowerCase().endsWith(".txt") ||
							nombre.toLowerCase().endsWith(".xls") ||
							nombre.toLowerCase().endsWith(".xlsx") ||
							nombre.toLowerCase().endsWith(".sig") ||
							nombre.toLowerCase().endsWith(".crt") ){
						correcto = correcto && true;
					} else{
						correcto = correcto && false;
					}
				}else{
					correcto =  false;
					break;
				}
				
			}
			zis.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e){
			log.debug("Los nombres de los archivos comprimidos en [" + archivo.getAbsolutePath() + "] no están en UTF-8, no se puede verificar, se devuelve FALSE en la validación");
			correcto = false;
		}
		
		return correcto;
	}

	public static boolean validarCantidadContenidoZip(File archivo){
		boolean correcto = true;
		if(!archivo.exists() || !archivo.isFile())
			throw new RuntimeException("El archivo :" +  archivo.getAbsolutePath() + " no existe o no es un directorio");
		if (log.isDebugEnabled())log.debug("Validando archivo " + archivo.getAbsolutePath());
		ZipInputStream zis;
		ZipInputStream zis2;
		String nombreSinExtension = null;
		int cantCertificados = 0;	
		try {
			zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(archivo)));
			
			ZipEntry entry;
			ZipEntry entry2;
			while ((entry = zis.getNextEntry()) != null) {
				String nombre = entry.getName();
				// valida que tenga archivo de firma
				if(nombre.toLowerCase().endsWith(".txt") ||
						nombre.toLowerCase().endsWith(".xls") ||
						nombre.toLowerCase().endsWith(".xlsx")){
					
						nombreSinExtension = Utils.obtenerNombreSinExtension(nombre);
						zis2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(archivo)));
						
						while ((entry2 = zis2.getNextEntry()) != null) {
							String nombre2 = entry2.getName();
							String nombreSinExtension2 = Utils.obtenerNombreSinExtension(nombre2);
							if(nombreSinExtension.equals(nombreSinExtension2) ){
								if(nombreSinExtension.concat(".sig").equals(nombre2) ){
									correcto = correcto && true;
									break;
								} else{
									correcto = correcto && false;
								}
							}
						}
						zis2.close();
				}
				// valida que tenga archivo de Certificado
				if(nombre.toLowerCase().endsWith(".crt")) {
					cantCertificados++;
				}
				
			}
			if(cantCertificados>0){
				correcto = correcto && true;
			}else{
				correcto = correcto && false;
			}
			
			zis.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e){
			log.debug("Los nombres de los archivos comprimidos en [" + archivo.getAbsolutePath() + "] no están en UTF-8, no se puede verificar, se devuelve FALSE en la validación");
			correcto = false;
		}
		
		return correcto;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/validaDocExiste", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView validaDocExiste(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoIAController.validaDocExiste");
		
		ModelAndView modelAndView = null;
		boolean existeDoc = false;
		String existe = "0";
		
		try {
			modelAndView = new ModelAndView(jsonView);
			String listaDocumentosSel = Utils.toStr(request.getParameter("hiddenLstDocAdj"));
			List<Map<String, Object>> listaDocumentosVerificar = new ArrayList<Map<String,Object>>();
			
			listaDocumentosVerificar  = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocumentosSel, java.util.Map.class);
			
			for (Map<String, Object> docAdj : listaDocumentosVerificar) {
				
				if (ValidaConstantes.FILTRO_UNO.equals(docAdj.get("esNuevo"))) {
					String nombreArchivo = Utils.toStr(docAdj.get("nomArchAdj"));
					String numExpVirt = Utils.toStr(docAdj.get("numExpVirt"));
					Integer numItem = 0 ;
					File docAdjVal = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numExpVirt+"/"+nombreArchivo);
					
					if(docAdjVal.exists() && docAdjVal.isFile()) {
						existeDoc = true;
					} else {
						existeDoc = false;
					}
				}				
			}
			
			if (log.isDebugEnabled())log.debug("existeDoc: "+existeDoc+" - listaDocumentosVerificar.size(): "+listaDocumentosVerificar.size());
			if (existeDoc) {
				existe = "1";
			} else {
				existe = "0";
			}			
			modelAndView.addObject("existe", existe);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.validaDocExiste");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.validaDocExiste");
			}
			NDC.pop();
			NDC.remove();
		}
	
		return modelAndView;
	}
	
	@RequestMapping(value = "/grabarDocAdjDetReq", method = RequestMethod.POST)
	public ModelAndView grabarDocAdjDetReq(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoIAController.grabarDocAdjDetReq");
		
		Map<String, Object> paramGrabar = new HashMap<String, Object>();
		UsuarioBean usuarioBean;
		String numExpedienteVirtual = "";
		String numExpedienteOrigen = "";
		String numRuc = "";
		String codTipoExpediente = "";
		String estadoExpediente = "";
		String codTipDoc = "";
		String razonSocial = "";
		String flagAcceso = "";
		String proceso = "";
		String desTipoExpediente = "";
		String fechaVencimiento = "";
		String error="";	
		List<Map<String, Object>> listaDocumentosASubirFinal = new ArrayList<Map<String,Object>>();
		Integer numOrden;
		Integer numItemTemp;
		
		try {
			modelAndView = new ModelAndView("jsonView"); 
			String listaDocAdjSel = Utils.toStr(request.getParameter("hidLstDocAdjGrabar"));
			numExpedienteVirtual = Utils.toStr(request.getParameter("hidTxtNumExpVirt")).trim();
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			List<Map<String, Object>> datosEscrito = (List<Map<String, Object>>) new JsonSerializer().deserialize((WebUtils.getSessionAttribute(request, "t6614ExpVirtualBean")), java.util.Map.class);
			
			List<Map<String, Object>> listaDocumentosASubir = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocAdjSel, java.util.Map.class);
			if (log.isDebugEnabled())log.debug("listaDocumentosASubir: "+ listaDocumentosASubir);
			if (log.isDebugEnabled())log.debug("datosEscrito: "+ datosEscrito);
			
			for (Map<String, Object> datosEscritoReq : datosEscrito) {
				if (numExpedienteVirtual.equals(Utils.toStr(datosEscritoReq.get("numExpedienteVirtual")).trim())) {
					codTipoExpediente = Utils.toStr(datosEscritoReq.get("codTipoExpediente")).trim();
					numExpedienteVirtual = Utils.toStr(datosEscritoReq.get("numExpedienteVirtual")).trim();
					numExpedienteOrigen = Utils.toStr(datosEscritoReq.get("numExpedienteOrigen")).trim();
//					codTipDoc = Utils.toStr(datosEscritoReq.get("codTipDoc"));
					numRuc = Utils.toStr(datosEscritoReq.get("numRuc")).trim();
					razonSocial  = Utils.toStr(datosEscritoReq.get("desRazonSocial"));
//					proceso = Utils.toStr(datosEscritoReq.get("desProceso"));
					desTipoExpediente = Utils.toStr(datosEscritoReq.get("desTipoExpediente"));
				}				
			}
			
			numOrden = 1;
			numItemTemp = 1;
			for (Map<String, Object> lstDocAdj : listaDocumentosASubir) {
				if("1".equals(lstDocAdj.get("esNuevo"))) {
					if (numItemTemp != Utils.toInteger(lstDocAdj.get("numItem"))) {
						numOrden = 1;						
					}
					numItemTemp = Utils.toInteger(lstDocAdj.get("numItem"));
					lstDocAdj.put("numOrden", numOrden);
					listaDocumentosASubirFinal.add(lstDocAdj);
					numOrden = numOrden + 1;
				}
			}
			
			//Datos generales
			paramGrabar = new HashMap<String, Object>();
			paramGrabar.put("listaDocumentosASubir", listaDocumentosASubirFinal);
			paramGrabar.put("numExpedienteVirtual", numExpedienteVirtual);
			paramGrabar.put("numExpedienteOrigen", numExpedienteOrigen); 
			paramGrabar.put("numRequerimientOrigen","0");
			paramGrabar.put("numRuc", numRuc);
			paramGrabar.put("codTipoExpediente", codTipoExpediente);
			paramGrabar.put("codUsuarioCarga", usuarioBean.getNroRegistro());
			paramGrabar.put("numRequerimiento", "0");
			paramGrabar.put("estadoExpediente", estadoExpediente);
			paramGrabar.put("codTipDoc", codTipDoc);
			paramGrabar.put("razonSocial", razonSocial);
			paramGrabar.put("proceso", proceso);
			paramGrabar.put("desTipoExpediente", desTipoExpediente);
			paramGrabar.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTRANET);
			paramGrabar.put("codDep", usuarioBean.getCodDepend());
			paramGrabar.put("flagAcceso", flagAcceso);
			
			error = documentoItemRequerimientoService.adjuntarDocumentosDetEscritos(paramGrabar);
			
			if(!(ValidaConstantes.NO_ERROR.equals(error))){
				modelAndView.addObject("mensajeRetorno", "Ocurri&oacute; un error al guardar los cambios.");
				modelAndView.addObject("error", "0");
			}else{
				modelAndView.addObject("mensajeRetorno", "El escrito electr&oacute;nico fue registrado satisfactoriamente.");
				modelAndView.addObject("error", "1");
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoIAController.grabarDocAdjDetReq");
			}
			log.error(ex, ex);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("mensajeRetorno", "Ocurri&oacute; un error");
			modelAndView.addObject("error","0");
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoIAController.grabarDocAdjDetReq");
			}
		}		
		return modelAndView;
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

	public void setDetalleRequerimientoService(
			DetalleRequerimientoService detalleRequerimientoService) {
		this.detalleRequerimientoService = detalleRequerimientoService;
	}

	public void setDocumentoItemRequerimientoService(
			DocumentoItemRequerimientoService documentoItemRequerimientoService) {
		this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}
	
	
}