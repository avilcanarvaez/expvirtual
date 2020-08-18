package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import com.lowagie.text.pdf.PdfReader;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

@Controller
public class CargaDocumentoEscritoITController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(CargaDocumentoEscritoITController.class);
	
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private JsonView jsonView;
	private ParametroService paramService;
	private CatalogoService catalogoService;
	private DocumentoItemRequerimientoService documentoItemRequerimientoService; 
	private EcmService ecmService;
	
	private static final int BUFFER_SIZE = 2048;
		
	@RequestMapping(value = "/obtenerTamano", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView obtenerTamano(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoITController.obtenerTamano");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		ModelAndView modelAndView = null;
		int noPages = 0;
		
		try {
			modelAndView = new ModelAndView(jsonView);
			multipartFile = multipartRequest.getFile("docFisico");
			String numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimientoAdj")).trim();
			Integer numItem = Utils.toInteger(request.getParameter("hiddenNumItemAdj"));
			String mimeType = multipartFile.getContentType();
			String arrayMimeType[] = null;
			arrayMimeType = mimeType.split(";");
			mimeType = arrayMimeType[0];
			archivo = Utils.multipartToFile(multipartFile);
			
			log.info("***********Ruta:*****************************"+ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento+"/"+numItem+"/"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			log.info("***********Nombre Archivo:(multipartFile.getOriginalFilename())***********************"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			log.debug("numRequerimiento: "+numRequerimiento);

			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+numRequerimiento+"/"+numItem+"/"+Utils.convertirUnicode(multipartFile.getOriginalFilename()));
			
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
			log.debug("extensionArchivo: "+extensionArchivo);
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
				log.debug("Ingreso cuando es archivo .zip");
				FileUtils.copyFile(archivo, destino);
	            boolean  validado = validarContenidoZip(destino);
				//VALIDAR EXISTENCIA DE OTROS ARHIVOS
				if(!validado){
					destino.delete();
					modelAndView.addObject("archivoOtrosNoExiste", false);
					return modelAndView;
				} else { 
					modelAndView.addObject("archivoOtrosNoExiste", true);
				}
				
				noPages = 1;
				//--------------------------------------------------------------------------------------				
			} else if (("pdf").equals(extensionArchivo.trim())){
				int iContadorFilesPDFPro = 0;
				int iContadorFilesPDFA = 0;
				FileUtils.copyFile(archivo, destino);				
				//HHC - INI
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
				//HHC - FIN
				//COMPROBAR QUE EL PDF NO SE ENCUENTRE PROTEGIDO
				log.debug("COMPROBAR QUE EL PDF NO SE ENCUENTRE PROTEGIDO");
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
					if (log.isDebugEnabled()) log.debug("Error - CargaDocumentoEscritoITController.obtenerTamano");
					iContadorFilesPDFPro++;
					destino.delete();
				}
				
				if(iContadorFilesPDFPro > 0){
					modelAndView.addObject("archivoPdfProtegido", false);
					return modelAndView;
				}else{
					modelAndView.addObject("archivoPdfProtegido", true);					
				}
				
				FileInputStream fis2 = new FileInputStream(destino);
				
				PdfReader document = new PdfReader(fis2); 
				noPages = document.getNumberOfPages();
				modelAndView.addObject("archivoOtrosNoExiste", true);
				fis2.close();
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
		
	/**
	 * ArchivoZip : /data0/ejemplo/archivo.zip
	 * Caso a:
	 * 	archivo.zip
	 *  Contenido: archivo.txt y archivo.xls
	 * Confirmar:
	 *  - True
	 * Caso b:
	 * 	archivo.zip
	 *  Contenido: archivo.txt
	 * Confirmar:
	 *  - True
	 * Caso b:
	 * 	archivo.zip
	 *  Contenido: archivo.pdf
	 * Confirmar:
	 *  - False
	 * Validar:
	 * 	- 
	 * @return
	 */
	public static boolean validarContenidoZip(File archivo){
		boolean correcto = true;
		if(!archivo.exists() || !archivo.isFile())
			throw new RuntimeException("El archivo :" +  archivo.getAbsolutePath() + " no existe o no es un directorio");
		log.debug("Validando archivo " + archivo.getAbsolutePath());
		ZipInputStream zis;
		try {
			zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(archivo)));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				String nombre = entry.getName();
				log.debug("Leyendo entrada de archivo: " +  nombre);
				if(nombre.toLowerCase().endsWith(".txt") ||
						nombre.toLowerCase().endsWith(".xls") ||
						nombre.toLowerCase().endsWith(".xlsx")){
					correcto = correcto && true;
				} else{
					correcto = correcto && false;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/validaDocExiste", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView validaDocExiste(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoInternetController.validaDocExiste");
		
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
					String numRequerimiento = Utils.toStr(docAdj.get("numRequerimiento"));
					Integer numItem = Utils.toInteger(docAdj.get("numItem"));
					File docAdjVal = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numRequerimiento+"/"+numItem+"/"+nombreArchivo);
					
					if(docAdjVal.exists() && docAdjVal.isFile()) {
						existeDoc = true;
					} else {
						existeDoc = false;
					}
				}				
			}
			
			log.debug("existeDoc: "+existeDoc+" - listaDocumentosVerificar.size(): "+listaDocumentosVerificar.size());
			if (existeDoc) {
				existe = "1";
			} else {
				existe = "0";
			}			
			modelAndView.addObject("existe", existe);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoInternetController.validaDocExiste");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoInternetController.validaDocExiste");
			}
			NDC.pop();
			NDC.remove();
		}
	
		return modelAndView;
	}

	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoITController.descargarDocumento");
				
		try {			
			String nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidNombreDoc")));
			String numRequerimiento = Utils.convertirUnicode(Utils.toStr(request.getParameter("hidTxtNumRequerimientoAdj"))).trim();
			Integer numItem = Utils.toInteger(request.getParameter("hiddenNumItemAdj"));
			
			File downloadFile = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numRequerimiento+"/"+numItem+"/"+nombreArchivo);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +numRequerimiento+"/"+numItem+"/"+nombreArchivo); 
	         
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
				log.debug((Object) "Error - CargaDocumentoEscritoITController.descargarDocumento");
				log.error(ex, ex);
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoITController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}		
	}

	@RequestMapping(value = "/grabarDocAdjDetReq", method = RequestMethod.POST)
	public ModelAndView grabarDocAdjDetReq(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = null;
		
		if (log.isDebugEnabled()) log.info( "Inicio - CargaDocumentoEscritoITController.grabarDocAdjDetReq");
		
		Map<String, Object> paramGrabar = new HashMap<String, Object>();
		UsuarioBean usuarioBean;
		String numExpedienteVirtual = "";
		String numExpedienteOrigen = "";
		String numRequerimientOrigen = "";
		String numRuc = "";
		String codTipoExpediente = "";
		String numRequerimiento = "";
		String fechaRequerimiento = "";
		String estadoExpediente = "";
		String codTipDoc = "";
		String razonSocial = "";
		String flagAcceso = "";
		String proceso = "";
		String desTipoExpediente = "";
		String fechaVencimiento = "";
		String error="";	
		String codUUOOSuperv = "";
		String codSuperv = "";
		List<Map<String, Object>> listaDocumentosASubirFinal = new ArrayList<Map<String,Object>>();
		Integer numOrden;
		Integer numItemTemp;
		
		try {
			modelAndView = new ModelAndView("jsonView"); 
			String listaDocAdjSel = Utils.toStr(request.getParameter("hidLstDocAdjGrabar"));
			String listaDetReqSel = Utils.toStr(request.getParameter("hidLstDetReqGrabar")); 
			numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumReqGrabar"));
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			List<Map<String, Object>> datosEscrito = (List<Map<String, Object>>) new JsonSerializer().deserialize((WebUtils.getSessionAttribute(request, "datosEscritoReq")), java.util.Map.class);
			log.debug("Datos Requerimiento: "+ datosEscrito);
			
			List<Map<String, Object>> listaDetRequerim = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDetReqSel, java.util.Map.class);
			log.debug("listaDetRequerim: "+ listaDetRequerim);
			
			List<Map<String, Object>> listaDocumentosASubir = (List<Map<String, Object>>) new JsonSerializer().deserialize(listaDocAdjSel, java.util.Map.class);
			log.debug("listaDocumentosASubir: "+ listaDocumentosASubir);
			
			for (Map<String, Object> datosEscritoReq : datosEscrito) {
				if (numRequerimiento.equals(datosEscritoReq.get("numRequerimiento"))) {
					codTipoExpediente = Utils.toStr(datosEscritoReq.get("codTipoExp"));
					numExpedienteVirtual = Utils.toStr(datosEscritoReq.get("numExpedienteVirtual"));
					numExpedienteOrigen = Utils.toStr(datosEscritoReq.get("numExpedienteOrigen"));
					numRequerimientOrigen = Utils.toStr(datosEscritoReq.get("numRequerimOrigen"));
					fechaRequerimiento = Utils.toStr(datosEscritoReq.get("fecRequerimiento"));
					estadoExpediente = Utils.toStr(datosEscritoReq.get("codEstadoExp"));
					codTipDoc = Utils.toStr(datosEscritoReq.get("codTipDoc"));
					numRuc = Utils.toStr(datosEscritoReq.get("numRuc"));
					razonSocial  = Utils.toStr(datosEscritoReq.get("desRazonSocial"));
					proceso = Utils.toStr(datosEscritoReq.get("desProceso"));
					desTipoExpediente = Utils.toStr(datosEscritoReq.get("desTipoExpediente"));
					fechaVencimiento = Utils.toStr(datosEscritoReq.get("fecVencimiento"));
					codUUOOSuperv = Utils.toStr(datosEscritoReq.get("uuOOSupervisor"));//UuOOSupervisor //revisar
					codSuperv = Utils.toStr(datosEscritoReq.get("codSupervisor"));;
				}				
			}
			
			Collections.sort(listaDocumentosASubir, new Comparator<Map<String, Object>> () {
		         public int compare(Map<String, Object> m1, Map<String, Object> m2) {
		             return ( Utils.toInteger(m1.get("numItem"))).compareTo( Utils.toInteger(m2.get("numItem"))); //ascending order
		             //return ((Integer) m2.get("num")).compareTo((Integer) m1.get("num")); //descending order
		         }
		     });
			
			log.debug("listaDocumentosASubir2: "+ listaDocumentosASubir);
			
			numOrden = 1;
			numItemTemp = 1;
			for (Map<String, Object> lstDocAdj : listaDocumentosASubir) {
				if(ValidaConstantes.FILTRO_UNO.equals(lstDocAdj.get("esNuevo"))) {
//					if (numItemTemp != Utils.toInteger(lstDocAdj.get("numItem"))) {
//						numOrden = 1;						
//					}
//					numItemTemp = Utils.toInteger(lstDocAdj.get("numItem"));
					lstDocAdj.put("numOrden", numOrden);
					listaDocumentosASubirFinal.add(lstDocAdj);
					numOrden = numOrden + 1;
				}
			}
									
			//Datos generales
			paramGrabar = new HashMap<String, Object>();
			paramGrabar.put("listaDocumentosASubir", listaDocumentosASubirFinal);
			paramGrabar.put("listaDetRequerim", listaDetRequerim);
			paramGrabar.put("numExpedienteVirtual", numExpedienteVirtual);
			paramGrabar.put("numExpedienteOrigen", numExpedienteOrigen); 
			paramGrabar.put("numRequerimientOrigen", numRequerimientOrigen);
			paramGrabar.put("numRuc", numRuc);
			paramGrabar.put("codTipoExpediente", codTipoExpediente);
			paramGrabar.put("codUsuarioCarga", usuarioBean.getUsuarioSOL());
			paramGrabar.put("numRequerimiento", numRequerimiento);
			paramGrabar.put("fechaRequerimiento", fechaRequerimiento);
			paramGrabar.put("estadoExpediente", estadoExpediente);
			paramGrabar.put("codTipDoc", codTipDoc);
			paramGrabar.put("razonSocial", razonSocial);
			paramGrabar.put("proceso", proceso);
			paramGrabar.put("desTipoExpediente", desTipoExpediente);
			paramGrabar.put("fechaVencimiento", fechaVencimiento);
			paramGrabar.put("codUUOOSuperv", codUUOOSuperv);
			paramGrabar.put("codSuperv", codSuperv);
			paramGrabar.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTERNET);
			paramGrabar.put("codDep", usuarioBean.getCodDepend());
			paramGrabar.put("flagAcceso", flagAcceso);
			
			error = documentoItemRequerimientoService.adjuntarDocumentosDetRequerimiento(paramGrabar);
			
			if(!(ValidaConstantes.NO_ERROR.equals(error))){
				modelAndView.addObject("mensajeRetorno", "Ocurri&oacute; un error al guardar los cambios.");
				modelAndView.addObject("error", "0");
			}else{
				modelAndView.addObject("mensajeRetorno", "El escrito electr&oacute;nico fue registrado satisfactoriamente.");
				modelAndView.addObject("error", "1");
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoITController.grabarDocAdjDetReq");
			}
			log.error(ex, ex);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("mensajeRetorno", "Ocurri&oacute; un error");
			modelAndView.addObject("error", "0");
			return modelAndView;
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - CargaDocumentoEscritoITController.grabarDocAdjDetReq");
			}
		}		
		return modelAndView;
	}
	
	@RequestMapping(value = "/consultarRequerimientosPendientesView", method = RequestMethod.GET)
	public ModelAndView consultarRequerimientosPendientesView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info( "Inicio - CargaDocumentoEscritoITController.consultarRequerimientosPendientesView");
		}
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_010);

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
				log.debug("Error - CargaDocumentoEscritoITController.consultarRequerimientosPendientesView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoITController.consultarRequerimientosPendientesView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoITController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoITController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoITController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - CargaDocumentoEscritoITController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - CargaDocumentoEscritoITController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - CargaDocumentoEscritoITController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
		
	/* Seteos */
	
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
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setDocumentoItemRequerimientoService(
			DocumentoItemRequerimientoService documentoItemRequerimientoService) {
		this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}
	

}