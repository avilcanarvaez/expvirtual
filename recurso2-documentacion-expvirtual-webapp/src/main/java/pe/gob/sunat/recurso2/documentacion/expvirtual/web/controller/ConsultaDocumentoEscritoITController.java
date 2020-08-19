//HHC todo se cambiara para crear uno nuevo
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DetalleRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
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
public class ConsultaDocumentoEscritoITController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);
	
	private ExpedienteVirtualService expedienteVirtualService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private ParametroService paramService;
	private RequerimientoService requerimientoService;
	private DetalleRequerimientoService detalleRequerimientoService; 
//	private EcmService ecmService;

	private DocumentoItemRequerimientoService documentoItemRequerimientoService; //HHC
	private DocumentoExpedienteService documentoExpedienteService;//HHC
	
	
	@RequestMapping(value = "/consultaDocumentoEscritoITView", method = RequestMethod.GET)
	public ModelAndView consultaDocumentoEscritoITView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView;
		if (log.isDebugEnabled())log.info( "Inicio - ConsultaDocumentoEscritoInternetController.consultaDocumentoEscritoITView");
		
		List<T01ParamBean> listadoProcesos = null;
		
		try {
			//ConsultaRequerimientosContribuyente
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_004);
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			
			listadoProcesos = configuracionExpedienteService.listarProcesos();
			
			List<T01ParamBean> listadoDependencias = configuracionExpedienteService.listarDependencias();
			
			List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			
			List<T01ParamBean> listadoTipoFecha= paramService.listarTipoFecha();
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_002);
			
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
				log.debug("Error - ConsultaDocumentoEscritoInternetController.consultaDocumentoEscritoITView");
			}
			log.error( ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr",  msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaDocumentoEscritoInternetController.consultaDocumentoEscritoITView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}


	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaDocumentoEscritoInternetController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaDocumentoEscritoInternetController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaDocumentoEscritoInternetController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaDocumentoEscritoInternetController.removerDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaDocumentoEscritoInternetController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - ConsultaDocumentoEscritoInternetController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	//HHC
		@RequestMapping(value = "/cargarListadoEscritos", method = RequestMethod.GET)
		public ModelAndView cargarListadoEscritos(HttpServletRequest request, HttpServletResponse response) {

			if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaDocumentoEscritoInternetController.cargarListadoEscritos");

			ModelAndView modelAndView = null;
			List<T6613DocExpVirtBean> listaDocEscritos= new ArrayList<T6613DocExpVirtBean>();
			UsuarioBean usuarioBean;
			String numeroRuc;
			Date fecDesde = null;
			Date fecHasta = null;
			DdpBean beanContribuyente;
			String razonSocial="";
			String codTipDocumento;
			String numDoc ;
			String flagNumExp;
			try {
				codTipDocumento = Utils.toStr(request.getParameter("tipDoc"));
				numDoc = request.getParameter("numDoc") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("numDoc").toString().trim();
				flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
				
				modelAndView = new ModelAndView(jsonView);

				Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("codTipDoc", ValidaConstantes.COD_TIPO_DOCU_ESCRITO);
				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				numeroRuc = usuarioBean.getNumRUC();
				mapParametrosBusqueda.put("numeroRuc", numeroRuc);
				beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
				razonSocial=beanContribuyente.getDesRazonSocial();
				//validamos numero expediente virtual
				if (!Utils.isEmpty(numDoc)) {
					if (codTipDocumento.equals("1")) {
						mapParametrosBusqueda.put("numDoc", numDoc);
					}else if (codTipDocumento.equals("2")) {
						mapParametrosBusqueda.put("numRequerimOrigen", numDoc);
					}else if (codTipDocumento.equals("3")) {
						mapParametrosBusqueda.put("numExpedOrigen", numDoc);
					}
					
					listaDocEscritos = documentoItemRequerimientoService.obtenerListaDocumentosEscritos(mapParametrosBusqueda);
					if (Utils.isEmpty(listaDocEscritos)) {
						if (Utils.equiv(flagNumExp,ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
							modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_02);
						} else {
							if (codTipDocumento.equals("1")) {
								modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_05);
							}else if (codTipDocumento.equals("2")) {
								modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_06);
							}else if (codTipDocumento.equals("3")) {
								modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_008_07);
							}
							
						}
						return modelAndView;
					} else {
						if (Utils.isEmpty(listaDocEscritos)) {
							modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
							return modelAndView;
						} else {
							modelAndView.addObject("listT6613docexpvirt",listaDocEscritos);//
							//Lestrada mostrar ruc y razon social en reporte
							modelAndView.addObject("numeroRuc",numeroRuc);
							modelAndView.addObject("razonSocial",razonSocial);
							//fin mostrar ruc y razon social en reporte
							return modelAndView;
						}
					}
					
					
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
				listaDocEscritos = documentoItemRequerimientoService.obtenerListaDocumentosEscritos(mapParametrosBusqueda);

				if (Utils.isEmpty(listaDocEscritos)) {
					modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
				} else {
					modelAndView.addObject("listT6613docexpvirt",listaDocEscritos);
					modelAndView.addObject("numeroRuc",numeroRuc);
					modelAndView.addObject("razonSocial",razonSocial);				
				}
			} catch (Exception ex) {
				if (log.isDebugEnabled()) {
					log.debug("Error - ConsultaDocumentoEscritoInternetController.cargarListadoEscritos");
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
					log.debug( "Final - ConsultaDocumentoEscritoInternetController.cargarListadoEscritos");
				}
				NDC.pop();
				NDC.remove();
			}

			return modelAndView;
		}
		@SuppressWarnings("unchecked")
		public ModelAndView exportarExcelEscritosElectronicos(HttpServletRequest request, HttpServletResponse response){
		       
			String titulo = ExportaConstantes.TITULO_EXPORTA_02_09;
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fechaActual = new Date();
			String fecImpresion = sdf.format(fechaActual);
			String desProceso="";
			String desTipoExpediente="";
	   
			try {
			    listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
				List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
				numRuc = request.getParameter("hiddenNumRuc") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumRuc").toString().trim();
				razonSocial = request.getParameter("hiddenRazonSocial") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenRazonSocial").toString().trim();
				codProceso = request.getParameter("hiddenTipoProceso") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoProceso").toString().trim();
				tipoExpediente = request.getParameter("hiddenTipoExpediente") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoExpediente").toString().trim();
				fechaDesde = request.getParameter("hiddenFechaDesde") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaDesde").toString().trim();
				fechaHasta = request.getParameter("hiddenFechaHasta") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaHasta").toString().trim();
				razonSocial=numRuc+" - "+ razonSocial;
				desProceso=Utils.toStr(listadoExportar.get(0).get("desProceso")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(0).get("desProceso").toString().trim());
				desTipoExpediente=Utils.toStr(listadoExportar.get(0).get("desTipoExpediente")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(0).get("desTipoExpediente").toString().trim());
		
				fechaVacia = Calendar.getInstance();
				fechaVacia.set(1, 0, 1, 0, 0, 0);
				
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
				
				
				//fin lestrada quito algunos campos de cabecera
				fila = hoja.createRow(4);
				HSSFCell celda = fila.createCell(0);
				HSSFCell celda1 = fila.createCell(1);
				HSSFCell celda2 = fila.createCell(2);
				HSSFCell celda3 = fila.createCell(3);
				HSSFCell celda4 = fila.createCell(4);
				HSSFCell celda5 = fila.createCell(5);
				
				celda.setCellValue("Escrito electrónico");
				celda1.setCellValue("Fecha y Hora de presentación");
				celda2.setCellValue("Proceso");
				celda3.setCellValue("Tipo de Expediente");
				celda4.setCellValue("N° Expediente");
				celda5.setCellValue("Requerimiento");
							
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
					celda.setCellValue(Utils.toStr(listadoExportar.get(i).get("numDoc")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("numDoc").toString().trim()));
					hoja.autoSizeColumn(0);
					celda.setCellStyle(estiloRecorrido);
					celda1 = fila.createCell(1);
					celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecDoc")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("fecDoc").toString().trim()));
					hoja.autoSizeColumn(1);
					celda1.setCellStyle(estiloRecorrido);
					celda2 = fila.createCell(2);
					celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("desProceso")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("desProceso").toString().trim()));
					hoja.autoSizeColumn(2);
					celda2.setCellStyle(estiloRecorrido);
					celda3 = fila.createCell(3);
					celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("desTipoExpediente")==null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString().trim())));
					hoja.autoSizeColumn(3);
					celda3.setCellStyle(estiloRecorrido);
					celda4 = fila.createCell(4);
					celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")==null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).get("numExpedienteOrigen").toString().trim())));
					hoja.autoSizeColumn(4);
					celda4.setCellStyle(estiloRecorrido);
					celda5 = fila.createCell(5);
					celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRequerimOrigen")==null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).get("numRequerimOrigen").toString().trim()));
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
				  
				String filename = CatalogoConstantes.RPT_GEN_CONSULTA_ESCRITO_XLS + part + ".xls";
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
		
		@RequestMapping(value = { "/consultaEscrito" }, method = { RequestMethod.POST })
		public ModelAndView consultaEscrito (HttpServletRequest request, HttpServletResponse response) 
		{if (log.isDebugEnabled())log.info((Object) "Inicio - ConsultaDocumentoEscritoInternetController.consultaEscrito");
		ModelAndView modelAndView;
		
		List<T10372DetRequerimBean> lisT10372DetRequerimBean = new ArrayList<T10372DetRequerimBean>();
		Map<String, Object> paramBusqueda = new HashMap<String, Object>();
		List<T10373DocAdjReqBean> listaDocAdjuntos = new ArrayList<T10373DocAdjReqBean>();
		List<T10373DocAdjReqBean> listaDocAdjFinal = new ArrayList<T10373DocAdjReqBean>();
		
		try {
			String numExpedOrigen = Utils.toStr(request.getParameter("hidTxtNumExpOri")).trim();
			String numRequerimiento = Utils.toStr(request.getParameter("hidTxtNumRequerimiento")).trim();
			String numDoc = Utils.toStr(request.getParameter("hidTxtNumDoc")).trim();
			String numRequerimOrigen = Utils.toStr(request.getParameter("hidTxtNumRequerimOrigen")).trim();
			String numExpVirt = Utils.toStr(request.getParameter("hidTxtNumExpVirt")).trim();
			
			String lista = Utils.toStr(request.getParameter("listadoExpedientesCadena"));
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoRegistrar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
			log.info("Lista a registrar: "+ listadoRegistrar);
			
			Map<String, Object> mapParametrosBusqueda1 = new HashMap<String, Object>();
			
			mapParametrosBusqueda1 = new HashMap<String, Object>();
			mapParametrosBusqueda1.put("codTipDoc", ValidaConstantes.COD_TIPO_DOCU_ESCRITO);
			mapParametrosBusqueda1.put("numExpeDv", numExpVirt);
			mapParametrosBusqueda1.put("numDoc", numDoc);

			T6613DocExpVirtBean t6613DocRel = documentoExpedienteService.obtenerDocumentoExpediente(mapParametrosBusqueda1);
			
			if(numRequerimOrigen.equals("-")||numRequerimOrigen.equals("0")) {
				
				modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_014);
				
				if(!Utils.isEmpty(t6613DocRel)){
					paramBusqueda.put("numCorDoc", t6613DocRel.getNumCorDoc());
					listaDocAdjuntos = documentoItemRequerimientoService.obtenerListaDocAdj(paramBusqueda);
					if (listaDocAdjuntos.size() > 0) {
						listaDocAdjFinal.addAll(listaDocAdjuntos);
					}
				}
				for (Map<String, Object> datosEscritoReq : listadoRegistrar) {
					if (numDoc.equals(Utils.toStr(datosEscritoReq.get("numDoc")).trim())) {
						datosEscritoReq.put("desProceso", datosEscritoReq.get("desProceso"));
						modelAndView.addObject("datosEscritoReq",datosEscritoReq);
					}				
				}
				modelAndView.addObject("listaDocAdjuntos", new JsonSerializer().serialize(listaDocAdjFinal));
			}else{
				modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_02_04_016);
			
//				HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
				modelAndView.addObject("numExpedOrigen",numExpedOrigen);
				modelAndView.addObject("numDoc",numDoc);
				modelAndView.addObject("numRequerimOrigen",numRequerimOrigen);

				for (Map<String, Object> datosEscritoReq : listadoRegistrar) {
					if (numRequerimiento.equals(datosEscritoReq.get("numRequerimiento"))) {
						datosEscritoReq.put("desProceso", datosEscritoReq.get("desProceso")); 
						paramBusqueda.put("numRequerimiento", datosEscritoReq.get("numRequerimiento"));
						paramBusqueda.put("numCorDoc", t6613DocRel.getNumCorDoc());
						paramBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
						
						lisT10372DetRequerimBean = detalleRequerimientoService.obtenerListaItems(paramBusqueda);
					}				
				}
				
				for (T10372DetRequerimBean detRequerimiento : lisT10372DetRequerimBean) {
					paramBusqueda.put("numItem", detRequerimiento.getItem());
					listaDocAdjuntos = documentoItemRequerimientoService.obtenerListaDocAdj(paramBusqueda);
					if (listaDocAdjuntos.size() > 0) {
						listaDocAdjFinal.addAll(listaDocAdjuntos);
					}
				}
				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_06_001);
				modelAndView.addObject("numExpVirt", new JsonSerializer().serialize(numExpVirt));
				modelAndView.addObject("listaDocAdjuntos", new JsonSerializer().serialize(listaDocAdjFinal));
				modelAndView.addObject("lisT10372DetRequerimBean",new JsonSerializer().serialize(lisT10372DetRequerimBean));
				modelAndView.addObject("titulos",new JsonSerializer().serialize(titulos));
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug((Object) "Error - ConsultaDocumentoEscritoInternetController.consultaEscrito");
			log.error((Object) ex, (Throwable) ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
			if (log.isDebugEnabled())log.debug((Object) "Final - ConsultaDocumentoEscritoInternetController.consultaEscrito");
			NDC.pop();
			NDC.remove();
		}
		return modelAndView;
	}
	public void setExpedienteVirtualService(
			ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setValidarParametrosService(
			ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setRequerimientoService(RequerimientoService requerimientoService) {
		this.requerimientoService = requerimientoService;
	}
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public void setDocumentoItemRequerimientoService(
			DocumentoItemRequerimientoService documentoItemRequerimientoService) {
		this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}
	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	public void setParamService(ParametroService paramService) {
		this.paramService = paramService;
	}
	public void setDetalleRequerimientoService(
			DetalleRequerimientoService detalleRequerimientoService) {
		this.detalleRequerimientoService = detalleRequerimientoService;
	}
	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	
	
}