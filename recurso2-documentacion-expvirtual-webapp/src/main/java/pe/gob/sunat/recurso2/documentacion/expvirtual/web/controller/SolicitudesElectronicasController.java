package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

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

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Sheet;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

@Controller
public class SolicitudesElectronicasController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);
	
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private DocumentoItemRequerimientoService documentoItemRequerimientoService;
	
	@RequestMapping(value = "/inicioConsultaSolicitudesEView", method = RequestMethod.GET)
	public ModelAndView inicioConsultaSolicitudesEView(HttpServletRequest request, HttpServletResponse response) {
		
	   ModelAndView modelAndView;
		
		if (log.isDebugEnabled())log.info( "Inicio - SolicitudesElectronicasController.inicioConsultaSolicitudesEView");
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			//ConsultaSolicitudElectronica
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_006);
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			List<T01ParamBean> listaProcesosFinal = new ArrayList<T01ParamBean>();
         
         for (T01ParamBean procesoBean : listadoProcesos) {
            if ((procesoBean.getCodParametro().trim()).equals("002") || (procesoBean.getCodParametro().trim()).equals("004")) {
               listaProcesosFinal.add(procesoBean);
            }
         }
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_005);
			
			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_02_04_005);
			excepciones.put("excepcion02",AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03",AvisoConstantes.EXCEP_MODULO_02_04_003);
			excepciones.put("excepcion04",AvisoConstantes.EXCEP_MODULO_02_04_006);
			
			modelAndView.addObject("listadoProcesos",new JsonSerializer().serialize(listaProcesosFinal));
			modelAndView.addObject("excepciones",new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - SolicitudesElectronicasController.inicioConsultaOtrosEscritosEView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - SolicitudesElectronicasController.inicioConsultaOtrosEscritosEView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - SolicitudesElectronicasController.cargarDatosBusquedaSession");
		
		try {
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - SolicitudesElectronicasController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - SolicitudesElectronicasController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - SolicitudesElectronicasController.removerDatosBusquedaSession");
		
		try {
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - SolicitudesElectronicasController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - SolicitudesElectronicasController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/cargarListadoEscritos", method = RequestMethod.GET)
	public ModelAndView cargarListadoEscritos(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) {
		   log.info((Object) "Inicio - SolicitudesElectronicasController.cargarListadoEscritos");
		}

		ModelAndView modelAndView = null;
		List<T6613DocExpVirtBean> listaDocEscritos = new ArrayList<T6613DocExpVirtBean>();
		UsuarioBean usuarioBean;
		String numeroRuc = "";
		String razonSocial = "";
		Date fecInicio = null;
		Date fecFin = null;
		String fecDesde = "";
		String fecHasta = "";
		DdpBean beanContribuyente;
		String codProceso = "";
		String numEscrito = "";
		String numExp = "";
		
		try {
		   codProceso = request.getParameter("codProceso") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("codProceso").toString().trim();
			numEscrito = request.getParameter("numEscrito") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("numEscrito").toString().trim();
			numExp = request.getParameter("numExp") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("numExp").toString().trim();
			
			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			//mapParametrosBusqueda.put("codTipDoc", ValidaConstantes.COD_TIPO_DOCU_ESCRITO);
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			numeroRuc = usuarioBean.getNumRUC();
			mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
			razonSocial = beanContribuyente.getDesRazonSocial();
		
			if (!Utils.isEmpty(numEscrito)) {
			   mapParametrosBusqueda.put("numDoc", numEscrito);
			} else if (!Utils.isEmpty(numExp)) {
            mapParametrosBusqueda.put("numExpedVirtual", numExp);
         } else if (!Utils.isEmpty(request.getParameter("fecDesde")) && !Utils.isEmpty(request.getParameter("fecHasta"))) {
            fecInicio = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")), CatalogoConstantes.INT_TWO);
            SimpleDateFormat formatoFechaExporta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fecDesde = formatoFechaExporta.format(fecInicio);
            mapParametrosBusqueda.put("fecGenIni", fecDesde);
            
            fecFin = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")), CatalogoConstantes.INT_TWO);
            SimpleDateFormat formatoFechaExporta2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fecHasta = formatoFechaExporta2.format(fecFin);
            mapParametrosBusqueda.put("fecGenFin", fecHasta);
         }
			
			listaDocEscritos = documentoItemRequerimientoService.obtenerListaDocumentosEscritos(mapParametrosBusqueda);
			
			if (Utils.isEmpty(listaDocEscritos)) {
			   if (!Utils.isEmpty(numEscrito)) {
			      modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_04_006);
			   } else if (!Utils.isEmpty(numExp)) {
			      modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_07);
			   } else {
			      modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
				}
			} else {
				modelAndView.addObject("listT6613docexpvirt",listaDocEscritos);
				modelAndView.addObject("numeroRuc",numeroRuc);
				modelAndView.addObject("razonSocial",razonSocial);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - SolicitudesElectronicasController.cargarListadoEscritos");
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
				log.debug( "Final - SolicitudesElectronicasController.cargarListadoEscritos");
			}
			NDC.pop();
			NDC.remove();
		}
		
		return modelAndView;
	}
		
	@SuppressWarnings("unchecked")
	public ModelAndView exportarExcelEscritosElectronicos(HttpServletRequest request, HttpServletResponse response) {
	   
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_12;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String numRuc = "";
		String razonSocial = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
   
		try {
		   listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
			numRuc = request.getParameter("hiddenNumRuc") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumRuc").toString().trim();
			razonSocial = request.getParameter("hiddenRazonSocial") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenRazonSocial").toString().trim();
			razonSocial = numRuc+" - "+ razonSocial;
				
			response.setContentType("application/vnd.ms-excel");
				
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");
			hoja.setColumnWidth(0, 2500);
			hoja.setColumnWidth(1, 2500);
			hoja.setColumnWidth(2, 3500);
			hoja.setColumnWidth(3, 4500);
			hoja.setColumnWidth(4, 4800);
			hoja.setColumnWidth(5, 16000);

   		HSSFRow fila = hoja.createRow(1);
   		HSSFCell tituloCelda = fila.createCell(0);
   		tituloCelda.setCellValue(titulo);
   		hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 7));

			fila = hoja.createRow(2);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocial);
			rango2 = new CellRangeAddress(2, 2, 2, 4);
			hoja.addMergedRegion(rango2);
				
			HSSFCell subtituloCelda2 = fila.createCell(5);
			subtituloCelda2.setCellValue("Fecha del Reporte:");
			HSSFCell contenido3 = fila.createCell(6);
			contenido3.setCellValue(fecImpresion);
			
			fila = hoja.createRow(4);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			HSSFCell celda4 = fila.createCell(4);
			HSSFCell celda5 = fila.createCell(5);
				
			celda.setCellValue("Nro. Solicitud");
			celda1.setCellValue("Fecha Solicitud");
			celda2.setCellValue("Tipo de solicitud");
			celda3.setCellValue("Nro. Expediente");
			celda4.setCellValue("Nro. Requerimiento");
			celda5.setCellValue("Estado");
							
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
				celda.setCellValue(Utils.toStr(listadoExportar.get(i).get("numDoc") == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("numDoc").toString().trim()));
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecDoc") == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("fecDoc").toString().trim()));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("codTipoExpediente") == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("codTipoExpediente").toString().trim()));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteVirtual") == null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("numExpedienteVirtual").toString().trim())));
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRequerimiento") == null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("numRequerimiento").toString().trim())));
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("codEstadoExpe") == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("codEstadoExpe").toString().trim()));
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
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
				  
			String filename = CatalogoConstantes.RPT_GEN_CONSULTA_OTRO_ESCRITO_XLS + part + ".xls";
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
         String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
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
		   mensajeBean.setMensajeerror("Se ha producido un error inesperado al mostrar " + ex.getMessage());
		   view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
		
		return view;
	}
	
	@RequestMapping(value = "/descargarEscrito", method = RequestMethod.GET)
   public void descargarEscrito(HttpServletRequest request,HttpServletResponse response) {
      
      if (log.isDebugEnabled()) log.info( "Inicio - SolicitudesElectronicasController.descargarEscrito");
      
      try {
         String numeroEscrito = Utils.toStr(request.getParameter("numEscrito")).trim() + ".pdf";
         
         File downloadFile = new File(ValidaConstantes.CARPETA_TEMPORAL_TEMPO  + numeroEscrito);
         FileInputStream inputStream = new FileInputStream(downloadFile);
         ServletContext context = getServletContext();
         String mimeType = context.getMimeType(ValidaConstantes.CARPETA_TEMPORAL_TEMPO + numeroEscrito); 
            
           if (mimeType == null) {
               mimeType = "application/octet-stream";
           }
           response.setContentType(mimeType);
           response.setContentLength((int) downloadFile.length());
                 
           String headerKey = "Content-Disposition";
           String headerValue = String.format("attachment; filename=\"%s\"", numeroEscrito);
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
            log.debug((Object) "Error - SolicitudesElectronicasController.descargarEscrito");
         }
      } finally {
         if (log.isDebugEnabled()) {
            log.debug((Object) "Final - SolicitudesElectronicasController.descargarEscrito");
         }
         NDC.pop();
         NDC.remove();
      }
   }

	public void setJsonView(JsonView jsonView) {
	   this.jsonView = jsonView;
   }
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
	   this.validarParametrosService = validarParametrosService;
	}
	
	public void setDocumentoItemRequerimientoService(DocumentoItemRequerimientoService documentoItemRequerimientoService) {
	   this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}
	
	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
	   this.configuracionExpedienteService = configuracionExpedienteService;
	}

}