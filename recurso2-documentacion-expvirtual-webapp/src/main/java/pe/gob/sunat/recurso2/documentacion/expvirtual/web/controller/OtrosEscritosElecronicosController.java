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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

@Controller
public class OtrosEscritosElecronicosController extends MultiActionController {

	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);

	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private JsonView jsonView;
	private DocumentoItemRequerimientoService documentoItemRequerimientoService;
	// Inicio - [avilcan]
	private ExpedienteVirtualService expedienteVirtualService;
	// Fin - [avilcan]
	@RequestMapping(value = "/inicioConsultaOtrosEscritosEView", method = RequestMethod.GET)
	public ModelAndView inicioConsultaOtrosEscritosEView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())
			log.info("Inicio - OtrosEscritosElecronicosController.inicioConsultaOtrosEscritosEView");

		List<T01ParamBean> listadoProcesos = null;

		try {
			// ConsultaOtrosEscritosContribuyente
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_005);

			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils
					.getSessionAttribute(request, "beanParametrosConsultaReq");

			listadoProcesos = configuracionExpedienteService.listarProcesos();

			listadoProcesos = configuracionExpedienteService.listarProcesos();

			List<T01ParamBean> listaProcesosFinal = new ArrayList<T01ParamBean>();

			for (T01ParamBean procesoBean : listadoProcesos) {
				if ((procesoBean.getCodParametro().trim()).equals("004")) { // Recursos Impugnatorios
					listaProcesosFinal.add(procesoBean);
				}
			}

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_004);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_02_04_005);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_02_04_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_02_04_006);

			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listaProcesosFinal));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - OtrosEscritosElecronicosController.inicioConsultaOtrosEscritosEView");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - OtrosEscritosElecronicosController.inicioConsultaOtrosEscritosEView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView cargarDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())
			log.info((Object) "Inicio - OtrosEscritosElecronicosController.cargarDatosBusquedaSession");

		try {
			modelAndView = new ModelAndView(jsonView);
			BeanParametrosConsultaReq beanParametrosConsultaReq = Utils.mapearBean(request,
					BeanParametrosConsultaReq.class);
			beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
			request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - OtrosEscritosElecronicosController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - OtrosEscritosElecronicosController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())
			log.info((Object) "Inicio - OtrosEscritosElecronicosController.removerDatosBusquedaSession");

		try {
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - OtrosEscritosElecronicosController.removerDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - OtrosEscritosElecronicosController.removerDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarListadoEscritos", method = RequestMethod.GET)
	public ModelAndView cargarListadoEscritos(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.info((Object) "Inicio - OtrosEscritosElecronicosController.cargarListadoEscritos");
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
			codProceso = request.getParameter("codProceso") == null ? ValidaConstantes.CADENA_VACIA
					: request.getParameter("codProceso").toString().trim();
			numEscrito = request.getParameter("numEscrito") == null ? ValidaConstantes.CADENA_VACIA
					: request.getParameter("numEscrito").toString().trim();
			numExp = request.getParameter("numExp") == null ? ValidaConstantes.CADENA_VACIA
					: request.getParameter("numExp").toString().trim();

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			// mapParametrosBusqueda.put("codTipDoc",
			// ValidaConstantes.COD_TIPO_DOCU_ESCRITO);
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			numeroRuc = usuarioBean.getNumRUC();
			mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			beanContribuyente = validarParametrosService.validarRUC(numeroRuc);
			razonSocial = beanContribuyente.getDesRazonSocial();

			if (!Utils.isEmpty(numEscrito)) {
				mapParametrosBusqueda.put("numDoc", numEscrito);
			} else if (!Utils.isEmpty(numExp)) {
				mapParametrosBusqueda.put("numExpedVirtual", numExp);
			} else if (!Utils.isEmpty(request.getParameter("fecDesde"))
					&& !Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecInicio = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")),
						CatalogoConstantes.INT_TWO);
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
					modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_05);
				} else if (!Utils.isEmpty(numExp)) {
					modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_07);
				} else {
					modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
				}
			} else {
				modelAndView.addObject("listT6613docexpvirt", listaDocEscritos);
				modelAndView.addObject("numeroRuc", numeroRuc);
				modelAndView.addObject("razonSocial", razonSocial);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - OtrosEscritosElecronicosController.cargarListadoEscritos");
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
				log.debug("Final - OtrosEscritosElecronicosController.cargarListadoEscritos");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView exportarExcelEscritosElectronicos(HttpServletRequest request, HttpServletResponse response) {

		String titulo = ExportaConstantes.TITULO_EXPORTA_02_11;
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
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer()
					.deserialize(listadoExportarCadena, ArrayList.class);
			numRuc = request.getParameter("hiddenNumRuc") == null ? ValidaConstantes.CADENA_VACIA
					: request.getParameter("hiddenNumRuc").toString().trim();
			razonSocial = request.getParameter("hiddenRazonSocial") == null ? ValidaConstantes.CADENA_VACIA
					: request.getParameter("hiddenRazonSocial").toString().trim();
			razonSocial = numRuc + " - " + razonSocial;

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
				celda.setCellValue(
						Utils.toStr(listadoExportar.get(i).get("numDoc") == null ? ValidaConstantes.CADENA_VACIA
								: listadoExportar.get(i).get("numDoc").toString().trim()));
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(
						Utils.toStr(listadoExportar.get(i).get("fecDoc") == null ? ValidaConstantes.CADENA_VACIA
								: listadoExportar.get(i).get("fecDoc").toString().trim()));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(
						Utils.toStr(listadoExportar.get(i).get("desProceso") == null ? ValidaConstantes.CADENA_VACIA
								: listadoExportar.get(i).get("desProceso").toString().trim()));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("desTipoExpediente") == null
						? ValidaConstantes.CADENA_VACIA
						: Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString().trim())));
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(Utils.toStr(
						listadoExportar.get(i).get("numExpedienteVirtual") == null ? ValidaConstantes.CADENA_VACIA
								: Utils.convertirUnicode(
										listadoExportar.get(i).get("numExpedienteVirtual").toString().trim())));
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(Utils
						.toStr(listadoExportar.get(i).get("numRequerimOrigen") == null ? ValidaConstantes.CADENA_VACIA
								: listadoExportar.get(i).get("numRequerimOrigen").toString().trim()));
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
			}

			Calendar calendar = Calendar.getInstance();
			int anio = (calendar.get(Calendar.YEAR));
			int dia = (calendar.get(Calendar.DATE));
			int hora = (calendar.get(Calendar.HOUR_OF_DAY));
			int minutos = (calendar.get(Calendar.MINUTE));
			String dd = (dia < 10) ? "0" + dia : dia + "";
			int mes = calendar.get(Calendar.MONTH) + 1;
			String mm = (mes < 10) ? "0" + mes : mes + "";
			String part = anio + mm + dd + "_" + hora + minutos;

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
	public void descargarEscrito(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled())
			log.info("Inicio - OtrosEscritosElecronicosController.descargarEscrito");

		try {
			String numeroEscrito = Utils.toStr(request.getParameter("numEscrito")).trim() + ".pdf";

			File downloadFile = new File(ValidaConstantes.CARPETA_TEMPORAL_TEMPO + numeroEscrito);
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
				log.debug((Object) "Error - OtrosEscritosElecronicosController.descargarEscrito");
			}
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - OtrosEscritosElecronicosController.descargarEscrito");
			}
			NDC.pop();
			NDC.remove();
		}
	}

	@RequestMapping(value = "/inicioPresentaOtrosEscritosEView", method = RequestMethod.GET)
	public ModelAndView inicioPresentaOtrosEscritosEView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;
		if (log.isDebugEnabled()) {
			log.info("Inicio - OtrosEscritosElecronicosController.inicioPresentaOtrosEscritosEView");
		}

		List<T01ParamBean> listadoProcesos = null;

		try {
			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_08_01_007);

			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils
					.getSessionAttribute(request, "beanParametrosConsultaReq");

			listadoProcesos = configuracionExpedienteService.listarProcesos();

			List<T01ParamBean> listaProcesosFinal = new ArrayList<T01ParamBean>();

			for (T01ParamBean procesoBean : listadoProcesos) {
				if ((procesoBean.getCodParametro().trim()).equals("004")) { // Recursos Impugnatorios
					listaProcesosFinal.add(procesoBean);
				}
			}

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_01_003);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_02_04_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_02_04_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_02_04_003);

			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listaProcesosFinal));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelAndView.addObject("beanParametrosConsultaReq", beanParametrosConsultaReq);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - OtrosEscritosElecronicosController.inicioPresentaOtrosEscritosEView");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción");
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - OtrosEscritosElecronicosController.inicioPresentaOtrosEscritosEView");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/cargarListadoExpedientesPendientes", method = RequestMethod.GET)
	public ModelAndView cargarListadoExpedientesPendientes(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled())
			log.info((Object) "Inicio - OtrosEscritosElecronicosController.cargarListadoExpedientesPendientes");

		ModelAndView modelAndView = null;

		Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales;

		String codProceso = Utils.toStr(request.getParameter("codProceso"));
		log.info("codProceso=>" + codProceso);
		String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
		log.info("codTipexp=>" + codTipExpediente);
		String numExp = Utils.toStr(request.getParameter("numExp"));
		log.info("numExp=>" + numExp);
		Date fecDesde = null;
		Date fecHasta = null;

		try {
			log.info("TEST 1");
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			log.info("TEST 2");
			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")),
						CatalogoConstantes.INT_TWO);
				Calendar calendarDesde = Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde = calendarDesde.getTime();
				mapParametrosBusqueda.put("fecGenIni", fecDesde);
			}
			log.info("TEST 3");
			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")),
						CatalogoConstantes.INT_TWO);
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")),
						CatalogoConstantes.INT_TWO);
				Calendar calendarHasta = Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta = calendarHasta.getTime();
				mapParametrosBusqueda.put("fecGenFin", fecHasta);
			}
			log.info("TEST 4");

			// validamos el codigoProceso
			if (!Utils.isEmpty(codProceso)) {
				mapParametrosBusqueda.put("codProceso", codProceso);
			}
			// validamos el codigoTipoExpediente
			if (!Utils.isEmpty(codTipExpediente)) {
				mapParametrosBusqueda.put("codTipExpediente", codTipExpediente);
			}

			modelAndView = new ModelAndView(jsonView);
			mapParametrosBusqueda.put("numeroRuc", usuarioBean.getNumRUC());
			log.info("numeroRuc=>" + usuarioBean.getNumRUC());
			log.info("TEST 5");
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			log.info("Cantidad=>" + listaExpedientesVirtuales.size());

			
		    if (!Utils.isEmpty(numExp)) {
	            log.info("TEST 6");
	            if (Utils.isEmpty(listaExpedientesVirtuales)) {
	            	log.info("TEST 7");
	               modelAndView.addObject("msjError","El N° Expediente no existe."); //[PAS20191U210500291]: JMC
	            } else {    
	            	log.info("TEST 8");
	                  modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
	            }
	            
	         }else if (Utils.isEmpty(listaExpedientesVirtuales)) {
	        	 log.info("TEST 9");
	        	   modelAndView.addObject("msjError",AvisoConstantes.EXCEP_MODULO_02_01_005);
	         }else {
	        	 log.info("TEST 10");
	        	 modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
		 			  
	         }

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - OtrosEscritosElecronicosController.cargarListadoExpedientesPendientes");
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
				log.debug("Final - OtrosEscritosElecronicosController.cargarListadoExpedientesPendientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setDocumentoItemRequerimientoService(
			DocumentoItemRequerimientoService documentoItemRequerimientoService) {
		this.documentoItemRequerimientoService = documentoItemRequerimientoService;
	}

	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	// Inicio - [avilcan]

	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}
	// Fin - [avilcan]

}