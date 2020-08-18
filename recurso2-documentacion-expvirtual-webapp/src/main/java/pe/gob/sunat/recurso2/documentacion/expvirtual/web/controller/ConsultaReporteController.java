package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.io.IOUtils;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresDocumentosExpedientesPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualFechaGenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesPorResponsableBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorFechaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.IndiceExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ObservacionExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ReportesIndicadoresService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RepresentacionImpresaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;



public class ConsultaReporteController extends MultiActionController {

	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);

	private JsonView jsonView;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ObservacionExpedienteVirtualService observacionExpedienteVirtualService;
	private SeguimientoService seguiService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ParametroService paramService;
	private ValidarParametrosService validarParametrosService;
	private EcmService ecmService;
	private ReportesIndicadoresService reportesIndicadoresService;
	// Inicio [jquispe 30/05/2016]
	private IndiceExpedienteVirtualService indiceExpedienteVirtualService;
	// Fin [jquispe 30/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]

	private RepresentacionImpresaService representacionImpresaService;
    //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	private ResponsableService responsableService;
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA
	//INICIO LLRB 03/02/2020
	private JmsTemplate jmsTemplate;
	//FIN LLRB 03/02/2020


	private static Map<String, String> hmTitulosRptExcel;
	private static Map<String, String> hmIndicadoresAcumulacion;
	static {
		hmTitulosRptExcel = new HashMap<String, String>();
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_DEP, CatalogoConstantes.RPT_EXP_GEN_X_DEP_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_AMD, CatalogoConstantes.RPT_EXP_GEN_X_AMD_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB, CatalogoConstantes.RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM, CatalogoConstantes.RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONT_X_DCTO, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONT_X_DCTO_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_EXP_GEN_X_RESPONSABLES, CatalogoConstantes.RPT_EXP_GEN_X_RESPONSABLES_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_DOC_GEN_X_EXP_DEP, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONT_X_DCTO_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_DEP, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_DEP_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_FEC, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_FEC_DESC);
		hmTitulosRptExcel.put(CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP_DESC);

		hmIndicadoresAcumulacion = new HashMap<String, String>();
		hmIndicadoresAcumulacion.put(ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE, ValidaConstantes.DES_IND_ACUMULADOR_INDEPENDIENTE);
		hmIndicadoresAcumulacion.put(ValidaConstantes.IND_ACUMULADOR_ACUMULADO, ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADO);
		hmIndicadoresAcumulacion.put(ValidaConstantes.IND_ACUMULADOR_ACUMULADOR, ValidaConstantes.DES_IND_ACUMULADOR_ACUMULADOR);

	}
	//INICIO LLRB 31/01/2020
	public ModelAndView cargarBusqAccesoExpVirtual(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarBusqAccesoExpVirtual");

		List<T01ParamBean> listadoNumeroTipoExpediente = null;
		
		ModelAndView modelAndView = null;
		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");// obtener la dependencia del usaurio que ingreso
			String codDepUsuario = usuarioBean.getCodDepend().trim();
		
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
		
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_04_01_019);

			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_06_01_002);
		
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));			
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarBusqAccesoExpVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqAccesoExpVirtual");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqAccesoExpVirtual");
		return modelAndView;
	}
	
	public ModelAndView cargarBusqAccesoDepExpVirt(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarBusqAccesoDepExpVirt");

		
		List<T01ParamBean> listadoDependencias = null;		
		List<T01ParamBean> listadoNumeroTipoExpediente = null;
		
		ModelAndView modelAndView = null;
		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");// obtener la dependencia del usaurio que ingreso
			String codDepUsuario = usuarioBean.getCodDepend().trim();
			
			listadoDependencias = new ArrayList<T01ParamBean>();
			//Inicio - [oachahuic][PAS20165E210400270]
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				if (!Utils.isEmpty(listadoDependenciasIni)) {
					for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
						if (Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
						        && (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))) {
							t01ParamBean.setDesParametro(t01ParamBean.getCodParametro() + " - " + t01ParamBean.getDesParametro());
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
			//Fin - [oachahuic][PAS20165E210400270]		
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_04_01_019);

			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_06_01_003);			
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("codDepUsuario", new JsonSerializer().serialize(codDepUsuario));
			} else {
				modelAndView.addObject("codDepUsuario", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));			
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarBusqAccesoDepExpVirt");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqAccesoDepExpVirt");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqAccesoDepExpVirt");
		return modelAndView;
	}
	
	public ModelAndView cargarBusqAccesoGenExpVirt(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarBusqRepExpVirtual");

		List<T01ParamBean> listadoProcesos = null;
		List<T01ParamBean> listadoDependencias = null;
		List<T01ParamBean> listadoTipoFecha = null;
		List<T01ParamBean> listadoNumeroTipoExpediente = null;
		List<T01ParamBean> listadoEstadosExpVirtual = null;
		ModelAndView modelAndView = null;
		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");// obtener la dependencia del usaurio que ingreso
			String codDepUsuario = usuarioBean.getCodDepend().trim();

			listadoProcesos = configuracionExpedienteService.listarProcesos();
			listadoDependencias = new ArrayList<T01ParamBean>();
			//Inicio - [oachahuic][PAS20165E210400270]
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				if (!Utils.isEmpty(listadoDependenciasIni)) {
					for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
						if (Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
						        && (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))) {
							t01ParamBean.setDesParametro(t01ParamBean.getCodParametro() + " - " + t01ParamBean.getDesParametro());
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
			//Fin - [oachahuic][PAS20165E210400270]
			listadoTipoFecha = paramService.listarTipoFecha();
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			listadoEstadosExpVirtual = configuracionExpedienteService.listarEstadosExpVirtual();

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_04_01_019);

			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_06_01_004);
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("codDepUsuario", new JsonSerializer().serialize(codDepUsuario));
			} else {
				modelAndView.addObject("codDepUsuario", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("listadoTipoFecha", new JsonSerializer().serialize(listadoTipoFecha));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoEstadosExpVirtual", new JsonSerializer().serialize(listadoEstadosExpVirtual));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarBusqRepExpVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqRepExpVirtual");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqRepExpVirtual");
		return modelAndView;
	}
	//FIN LLRB 31/01/2020
	public ModelAndView cargarBusqRepExpVirtual(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarBusqRepExpVirtual");

		List<T01ParamBean> listadoProcesos = null;
		List<T01ParamBean> listadoDependencias = null;
		List<T01ParamBean> listadoTipoFecha = null;
		List<T01ParamBean> listadoNumeroTipoExpediente = null;
		List<T01ParamBean> listadoEstadosExpVirtual = null;
		ModelAndView modelAndView = null;
		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");// obtener la dependencia del usaurio que ingreso
			String codDepUsuario = usuarioBean.getCodDepend().trim();

			listadoProcesos = configuracionExpedienteService.listarProcesos();
			listadoDependencias = new ArrayList<T01ParamBean>();
			//Inicio - [oachahuic][PAS20165E210400270]
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
				if (!Utils.isEmpty(listadoDependenciasIni)) {
					for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
						if (Utils.isEmpty(usuarioBean.getCodDepend())||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
						        && (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))) {
							t01ParamBean.setDesParametro(t01ParamBean.getCodParametro() + " - " + t01ParamBean.getDesParametro());
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
			//Fin - [oachahuic][PAS20165E210400270]
			listadoTipoFecha = paramService.listarTipoFecha();
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			listadoEstadosExpVirtual = configuracionExpedienteService.listarEstadosExpVirtual();

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_04_01_019);

			modelAndView = new ModelAndView(NavegaConstantes.MANT_MODULO_04_03_001);
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoDependencias", new JsonSerializer().serialize(listadoDependencias));
			//Inicio - [oachahuic][PAS20165E210400270]
			if (mapDepAdu == null || mapDepAdu.isEmpty()) {
				modelAndView.addObject("codDepUsuario", new JsonSerializer().serialize(codDepUsuario));
			} else {
				modelAndView.addObject("codDepUsuario", mapDepAdu.get("ADUANA").toString().trim() );
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelAndView.addObject("listadoTipoFecha", new JsonSerializer().serialize(listadoTipoFecha));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoEstadosExpVirtual", new JsonSerializer().serialize(listadoEstadosExpVirtual));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarBusqRepExpVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqRepExpVirtual");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqRepExpVirtual");
		return modelAndView;
	}

	public ModelAndView cargarListaTiposExpediente(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarListaTiposExpediente");
		ModelAndView modelAndView;
		List<T6624TipExpProcBean> listadoTiposExpendientes;
		try {
			String codProceso = Utils.toStr(request.getParameter("codProceso"));

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("codProceso", codProceso);
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			mapParametrosBusqueda.put("indConsulta", ValidaConstantes.IND_TIPO_EXPE_ASOCIADO);

			listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(mapParametrosBusqueda);

			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("listadoTiposExpendientes", listadoTiposExpendientes);

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarListaTiposExpediente");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposExpediente");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposExpediente");
		return modelAndView;

	}

	@RequestMapping(value = { "/validarContribuyente" }, method = { RequestMethod.GET })
	public ModelAndView validarContribuyente(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.validarContribuyente");

		DdpBean beanContribuyente;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {
			modelAndView = new ModelAndView(jsonView);
			String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {

				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

			}
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			String codDepUsuario = usuarioBean.getCodDepend();

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
				log.debug((Object) "Error - ConsultaReporteController.validarContribuyente");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.validarContribuyente");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response) {

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		//Inicio [gangles 22/08/2016] 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		//Fin [gangles 22/08/2016] 
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarExcelExpedientes");

		try {

			listadoExportarCadena = Utils.toStr(request.getParameter("listadoExpedientesCadena"));

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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
			hoja.setColumnWidth(9, 16000);
			hoja.setColumnWidth(10, 16000);
			hoja.setColumnWidth(11, 16000);
			hoja.setColumnWidth(12, 16000);
			hoja.setColumnWidth(13, 16000);
	        //inicio JEFFIO [13/03/2017]
			hoja.setColumnWidth(14, 16000);
			hoja.setColumnWidth(15, 16000);
	        //fin JEFFIO [13/03/2017]

			HSSFRow fila = hoja.createRow(1);
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 3));

			fila = hoja.createRow(4);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			HSSFCell celda4 = fila.createCell(4);
			HSSFCell celda5 = fila.createCell(5);
			HSSFCell celda6 = fila.createCell(6);
			HSSFCell celda7 = fila.createCell(7);
			HSSFCell celda8 = fila.createCell(8);
			HSSFCell celda9 = fila.createCell(9);
			HSSFCell celda10 = fila.createCell(10);
			HSSFCell celda11 = fila.createCell(11);
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
			HSSFCell celda12 = fila.createCell(12);
			// Fin [jquispe 27/05/2016]
			HSSFCell celda13 = fila.createCell(13);
	        //inicio JEFFIO [13/03/2017]
	        HSSFCell celda14 = fila.createCell(14);
	        HSSFCell celda15 = fila.createCell(15);
	        //fin JEFFIO [13/03/2017]
	        
			celda.setCellValue("N°");
			celda1.setCellValue("N° de Expediente Origen");
			celda2.setCellValue("N° de Expediente Virtual");
			celda3.setCellValue("N° de Expediente Acumulador");
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion y Razon Social.
			celda4.setCellValue("Indicador de Acumulación");
			celda5.setCellValue("RUC");
			celda6.setCellValue("Razón Social");
			celda7.setCellValue("Proceso");
			celda8.setCellValue("Tipo de Expediente");
			celda9.setCellValue("Estado expediente");
			celda10.setCellValue("Estado Cierre");
			celda11.setCellValue("Fecha de Registro del Expediente");
			celda14.setCellValue("Fecha del Documento Origen");
			celda15.setCellValue("Origen");
			// Fin [jquispe 27/05/2016]
	        //inicio JEFFIO [13/03/2017]
	        celda12.setCellValue("Fecha de Notificación") ;
	        celda13.setCellValue("Forma de Notificación") ;
	        //fin JEFFIO [13/03/2017]

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
			ssheet.autoSizeColumn(8);
			ssheet.autoSizeColumn(9);
			ssheet.autoSizeColumn(10);
			ssheet.autoSizeColumn(11);
			// Inicio [jquispe 27/05/2016] 
			ssheet.autoSizeColumn(12);
			// Fin [jquispe 27/05/2016]
			ssheet.autoSizeColumn(13);
	        //inicio JEFFIO [13/03/2017]
	        ssheet.autoSizeColumn(14);
	        ssheet.autoSizeColumn(15);
	        //fin JEFFIO [13/03/2017]
			
			 //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 22/08/2016]
	     	
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
			celda8.setCellStyle(estiloCelda);
			celda9.setCellStyle(estiloCelda);
			celda10.setCellStyle(estiloCelda);
			celda11.setCellStyle(estiloCelda);
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
			celda12.setCellStyle(estiloCelda);
			// Fin [jquispe 27/05/2016]
			celda13.setCellStyle(estiloCelda);
	        //inicio JEFFIO [13/03/2017]
	        celda14.setCellStyle(estiloCelda); 
	        celda15.setCellStyle(estiloCelda); 
	        //fin JEFFIO [13/03/2017]

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
				String numExpedienteAcumulador = Utils.toStr(listadoExportar.get(i).get("numExpedienteAcumulador"));
				if (Utils.equiv(numExpedienteAcumulador, ValidaConstantes.FILTRO_CERO) || Utils.isEmpty(numExpedienteAcumulador)) {
					numExpedienteAcumulador = "";
				}
				fila = hoja.createRow(i + 5);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteVirtual")));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(numExpedienteAcumulador);
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("desIndicadorAcumulado")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
				celda6 = fila.createCell(6);
				// Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
				celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desRazonSocial").toString()));
				// Fin [jquispe 10/06/2016]
				hoja.autoSizeColumn(6);
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(7);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda7.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desProceso")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(7);
				celda7.setCellStyle(estiloRecorrido);
				celda8 = fila.createCell(8);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda8.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desTipoExpediente")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(8);
				celda8.setCellStyle(estiloRecorrido);
				celda9 = fila.createCell(9);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(9);
				celda9.setCellStyle(estiloRecorrido);
				celda10 = fila.createCell(10);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda10.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstadoCierre")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(10);
				celda10.setCellStyle(estiloRecorrido);
				celda11 = fila.createCell(11);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda11.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecRegistro")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(11);
				celda11.setCellStyle(estiloRecorrido);
				celda12 = fila.createCell(14);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda12.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
				// Fin [jquispe 27/05/2016]
				hoja.autoSizeColumn(14);
				celda12.setCellStyle(estiloRecorrido);
				// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Indicador de Acumulacion.
				celda13 = fila.createCell(15);
				celda13.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desOrigen")));
				hoja.autoSizeColumn(15);
				celda13.setCellStyle(estiloRecorrido);
				// Fin [jquispe 27/05/2016]
		   	    //inicio JEFFIO [13/03/2017]
	            celda14 = fila.createCell(12);
	            celda14.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("strFecNotifOrigen")));
	            hoja.autoSizeColumn(12);
	            celda14.setCellStyle(estiloRecorrido);
	            celda15 = fila.createCell(13);
	            celda15.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desForNotifOrigen")));
	            hoja.autoSizeColumn(15);
	            celda15.setCellStyle(estiloRecorrido);
		   	    //fin JEFFIO [13/03/2017]
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

			String filename = "rpt_expediente_virtual_" + part + ".xls";
			;
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
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarExcelExpedientes");
			}

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			view = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			view.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarExcelExpedientes");
			}
			NDC.pop();
			NDC.remove();
		}

		return view;
	}

	@RequestMapping(value = "/obtenerExpedientesVirtuales", method = RequestMethod.GET)
	public ModelAndView obtenerExpedientesVirtuales(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio -  ConsultaReporteController.obtenerExpedientesVirtuales");

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtualesTemp = new ArrayList<T6614ExpVirtualBean>();
		Date fecDesde = null;
		Date fecHasta = null;
		int cantExpAbiertos = 0;
		int cantExpCerrados = 0;
		int cantTotalExp = 0;
		String codEstadoExp;

		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String numeroRuc = Utils.toStr(request.getParameter("numRuc"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String numExp = Utils.toStr(request.getParameter("numExp"));
			String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
			String flagFecExp = Utils.toStr(request.getParameter("codTipBusquedaFecha"));
			String codEstado = Utils.toStr(request.getParameter("codEstado"));
			String numAcumulador = Utils.toStr(request.getParameter("numAcumulador"));

			String codDepUsuario = "";

			if (!Utils.isEmpty(numExp) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else if(Utils.isEmpty(numExp)){
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
			}
			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")), CatalogoConstantes.INT_TWO);
				Calendar calendarDesde = Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde = calendarDesde.getTime();
			}
			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")), CatalogoConstantes.INT_TWO);
				Calendar calendarHasta = Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta = calendarHasta.getTime();
			}

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			if(!Utils.isEmpty(codDepUsuario)){
				mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				}
			//mapParametrosBusqueda.put("codDependencia", codDepUsuario);

			// validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				if (Utils.equiv(flagNumExp, ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
					mapParametrosBusqueda.put("numExpedVirtual", numExp);
				} else {
					mapParametrosBusqueda.put("numExpedOrigen", numExp);
				}

				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
				//INICIO LLRB 14022020			
				if (listaExpedientesVirtuales.size() !=0){
					String codTipoExp = listaExpedientesVirtuales.get(0).getCodTipoExpediente();	
					modelAndView.addObject("codTipoExp", codTipoExp);
					log.debug("listaExpedientesVirtuales-Inicio : "+listaExpedientesVirtuales.get(0).getCodTipoExpediente());
					log.debug("codTipoExp : "+codTipoExp);
				}else{
					modelAndView.addObject("codTipoExp", "");
				}
				
				//FIN LLRB 14022020

				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp, ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_01);
					}
					return modelAndView;
				} else {
					// Inicio [jquispe 27/05/2016] Parametro del numero expediente virtual para registrar el seguimiento.
					String parametroNumeroExpedienteVirtual = listaExpedientesVirtuales.get(0).getNumExpedienteVirtual();
					// Fin [jquispe 27/05/2016]

					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						codEstadoExp = t6614ExpVirtualBean.getCodEstado();
						if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
							cantExpAbiertos = cantExpAbiertos + 1;
						} else {
							cantExpCerrados = cantExpCerrados + 1;
						}
						cantTotalExp = cantExpCerrados + cantExpAbiertos;
					}

					// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
					registrarSeguimientoConsultaReporteExpediente(parametroNumeroExpedienteVirtual);
					// Fin [jquispe 27/05/2016]
					modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
					modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
					modelAndView.addObject("cantExpCerrados", cantExpCerrados);
					modelAndView.addObject("cantTotalExp", cantTotalExp);
					return modelAndView;
				}
			}

			// validamos nÃƒÂºmero acumulador
			if (!Utils.isEmpty(numAcumulador)) {
				mapParametrosBusqueda.put("numExpedOrigen", numAcumulador);
				listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

				if (listaExpedientesVirtualesTemp != null) {
					listaExpedientesVirtuales.addAll(listaExpedientesVirtualesTemp);
					mapParametrosBusqueda.put("numExpedOrigen", null);
					mapParametrosBusqueda.put("numAcumulador", numAcumulador);
					listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

					listaExpedientesVirtuales.addAll(listaExpedientesVirtualesTemp);
				}
				int cont = 1;
				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					codEstadoExp = t6614ExpVirtualBean.getCodEstado();
					t6614ExpVirtualBean.setNumOrden(cont);
					cont++;
					if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
						cantExpAbiertos = cantExpAbiertos + 1;
					} else {
						cantExpCerrados = cantExpCerrados + 1;
					}
					cantTotalExp = cantExpCerrados + cantExpAbiertos;
				}
				modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
				modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
				modelAndView.addObject("cantExpCerrados", cantExpCerrados);
				modelAndView.addObject("cantTotalExp", cantTotalExp);

				return modelAndView;
			}

			// validamos numero de ruc
			if (!Utils.isEmpty(numeroRuc)) {
				mapParametrosBusqueda.put("numeroRuc", numeroRuc);
			}

			// validamos las fechas
			if (!Utils.isEmpty(flagFecExp)) {
				if (Utils.equiv(flagFecExp, ValidaConstantes.BUSQUEDA_POR_FECHA_DOCUMENTO_ORIGEN)) {
					mapParametrosBusqueda.put("fecDocIni", fecDesde);
					mapParametrosBusqueda.put("fecDocFin", fecHasta);
				} else if (Utils.equiv(flagFecExp, ValidaConstantes.BUSQUEDA_POR_FECHA_EXPEDIENTE_VIRTUAL)) {
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
			// validamos estado expediente virtual
			if (!Utils.isEmpty(codEstado)) {
				mapParametrosBusqueda.put("codEstado", codEstado);
			}

			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {

				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					codEstadoExp = t6614ExpVirtualBean.getCodEstado();
					if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
						cantExpAbiertos = cantExpAbiertos + 1;
					} else {
						cantExpCerrados = cantExpCerrados + 1;
					}
					cantTotalExp = cantExpCerrados + cantExpAbiertos;
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoConsultaReporteExpediente(null);
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
				modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
				modelAndView.addObject("cantExpCerrados", cantExpCerrados);
				modelAndView.addObject("cantTotalExp", cantTotalExp);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaReporteController.obtenerExpedientesVirtuales");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - ConsultaReporteController.obtenerExpedientesVirtuales");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaReporteExpediente(String numeroExpedienteVirtual) throws Exception {
		Map<String, Object> beanSegui = new HashMap<String, Object>();

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_EXPEDIENTE);
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
	}

	// Fin [jquispe 27/05/2016]

	public ModelAndView cargarDocumentosExpVirtual(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarDocumentosExpVirtual");
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		List<Map<String, Object>> obtenerDocumentosExpediente;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador = new ArrayList<T6614ExpVirtualBean>();
		String fechaDocOrigen = "";
		String fechaRegExpediente = "";
		DdpBean beanContribuyente;
		String indicadorAcumulador = "";
		HashMap<String, Object> mapParametrosBusqueda;
        HashMap<String, Object> mapParametrosBusquedaResponables;
		List<String> listaExpedientes = new ArrayList<String>();

		try {

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
			obtenerDocumentosExpediente = new ArrayList<Map<String, Object>>();
			HashMap<String, String> excepciones = new HashMap<String, String>();
            HashMap<String, String> noResponsable = new HashMap<String, String>();

			String numExp = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			String numExpOrigen = "";
			mapParametrosBusqueda = new HashMap<String, Object>();
			modelo = new ModelAndView("jsonView");
			mapParametrosBusqueda.put("numExpedVirtual", numExp);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
            
            //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
			String tipoExp = listaExpedientesVirtuales.get(0).getCodTipoExpediente();
						
			Map<String, Object> tipoExpediente = new HashMap<String, Object>();
			tipoExpediente.put("tipoExp",tipoExp);
			modelo.addObject("tipoExp", new JsonSerializer().serialize(tipoExpediente));
			
			if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(tipoExp) || 
				ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(tipoExp)){
				
				mapParametrosBusquedaResponables = new HashMap<String, Object>();
				mapParametrosBusquedaResponables.put("numExpedienteVirtual", numExp);
				mapParametrosBusquedaResponables.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				List<T6621RespExpVirtBean> listaResponsables = responsableService.listarResponsables(mapParametrosBusquedaResponables);
				int C = 0;			
				for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
					
					String usuarioLogueado = usuarioBean.getNroRegistro().trim();
					String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
					
					if(usuarioLogueado.equals(responsableExp)){
				
						C++;						
						break;
						
					}
				}
				
				if(C == 0){
					
					excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
					
				}
			}		
			//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
				fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				indicadorAcumulador = t6614ExpVirtualBean.getIndicadorAcumulado();
				numExpOrigen = t6614ExpVirtualBean.getNumExpedienteOrigen().trim();
				modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fechaDocOrigen));
				modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fechaRegExpediente));
				modelo.addObject("razonSocial", new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));

				modelo.addObject("t6614ExpVirtualBean", new JsonSerializer().serialize(t6614ExpVirtualBean));
				listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				break;
			}

			if (Utils.equiv(indicadorAcumulador, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)) {
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedienteAcumulador", numExpOrigen);

				listaExpedientesdelAcumulador = expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);

				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
					listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				}

				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);

				obtenerDocumentosExpediente = expedienteVirtualService.obtenerListaDocumentosAcumulado(mapParametrosBusqueda);
			} else {
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpeDv", numExp);

				obtenerDocumentosExpediente = expedienteVirtualService.obtenerDocumentosExpediente(mapParametrosBusqueda);
				
			}

			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_017);
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("lstDocExp", new JsonSerializer().serialize(obtenerDocumentosExpediente));
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoVerDocumento(numExp);
			// Fin [jquispe 27/05/2016]

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarDocumentosExpVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarDocumentosExpVirtual");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoVerDocumento(String numeroExpedienteVirtual) throws Exception {

		Map<String, Object> beanSegui = new HashMap<String, Object>();

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_EXPEDIENTE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);

		seguiService.registrarSeguimiento(beanSegui);

	}

	// Fin [jquispe 27/05/2016]

	public ModelAndView cargarDocumentosExpVirtualAcum(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarDocumentosExpVirtualAcum");
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		List<Map<String, Object>> obtenerDocumentosExpediente;
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador = new ArrayList<T6614ExpVirtualBean>();
		String fechaDocOrigen = "";
		String fechaRegExpediente = "";
		DdpBean beanContribuyente;
		String indicadorAcumulador = "";
		HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
		List<String> listaExpedientes = new ArrayList<String>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();

		try {

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			modelo = new ModelAndView("jsonView");
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
			obtenerDocumentosExpediente = new ArrayList<Map<String, Object>>();
			HashMap<String, String> excepciones = new HashMap<String, String>();

			String numAcumulador = Utils.toStr(request.getParameter("numAcumulador"));
			numAcumulador = numAcumulador.trim();
			mapParametrosBusqueda.put("numExpedOrigen", numAcumulador);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
			String numExpedienteVirtual = "";

			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				numExpedienteVirtual = t6614ExpVirtualBean.getNumExpedienteVirtual().trim();
				beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
				fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());
				indicadorAcumulador = t6614ExpVirtualBean.getIndicadorAcumulado();
				modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fechaDocOrigen));
				modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fechaRegExpediente));
				modelo.addObject("razonSocial", new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				modelo.addObject("t6614ExpVirtualBean", new JsonSerializer().serialize(t6614ExpVirtualBean));
				listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				break;
			}

			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedienteAcumulador", numAcumulador);

			listaExpedientesdelAcumulador = expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);

			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
				listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
			}
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
			obtenerDocumentosExpediente = expedienteVirtualService.obtenerListaDocumentosAcumulado(mapParametrosBusqueda);

			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_017);
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("lstDocExp", new JsonSerializer().serialize(obtenerDocumentosExpediente));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarDocumentosExpVirtualAcum");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarDocumentosExpVirtualAcum");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}

	@RequestMapping(value = "/descargarArchivo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivo(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.descargarArchivo");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os = null;
		String nombreArchivo = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.descargarArchivo");

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			numIdEcm = request.getParameter("codIdentificadorECM").toString().trim();
			mapParametrosBusqueda.put("codIdecm", numIdEcm);
			mapParametrosBusqueda.put("inline", "true");
			nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));

			responseDoc = ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			MediaType contentType = responseDoc.getHeaders().getContentType();
			if (!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) || Utils
			        .equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))) {
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
				response.setHeader(headerKey, headerValue);
			}
			os = response.getOutputStream();
			os.write(responseDoc.getBody());

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.descargarArchivo");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.descargarArchivo");
			try {
				if (os != null) {
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

	public ModelAndView validarExtension(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.validarExtension");
		ModelAndView modelAndView;
		try {
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
	
			// DESCARGAR EL DOCUMENTO
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("numCorDoc", documento.get("numCorDoc").toString());
			map.put("numExpedv", documento.get("numExpedv").toString());
			List<T6613DocExpVirtBean> lstT6613DocExpVirtBean=expedienteVirtualService.listarDocExp(map);
			String extension=Utils.obtenerExtension(lstT6613DocExpVirtBean.get(0).getDescripcionArchivo());
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("error", !Utils.equiv(extension.toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF));
			modelAndView.addObject("extension", extension.toUpperCase());
			

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.validarExtension");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.validarExtension");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposExpediente");
		return modelAndView;

	}
	
	@RequestMapping(value = "/verRepImpresa", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public ModelAndView verRepImpresa(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.verRepImpresa");

		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		String numCorrelativo = null;
		Long numSeq = null;
		int tamSeq = 0;
		Map<String, Object> mapDatosDocumento = new HashMap<String, Object>();
		Map<String, Object> mapUsuarioBean = new HashMap<String, Object>();
		Map<String,Object> mapResult = null;
		ByteArrayOutputStream baos = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaReporteController.verRepImpresa");

			// DATOS DEL DOCUMENTO SELECCIONADO EN GRILLA
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));

			numSeq = expedienteVirtualService.generarCorrelativo(SequenceConstantes.SEQ_EV_REPIMP);
			numCorrelativo = "0000000" + numSeq.toString();
			tamSeq = numCorrelativo.length();
			numCorrelativo = numCorrelativo.substring(tamSeq - 7, tamSeq);

			// DESCARGAR EL DOCUMENTO
			Map<String, Object> mapDLECM = new HashMap<String, Object>();
			mapDLECM.put("codIdecm", documento.get("codIdecm").toString());
			mapDLECM.put("inline", "true");

			ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapDLECM);

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			// DATOS CONTRIBUYENTE y REP IMPRESA
			mapDatosDocumento.put("ruc", request.getParameter("ruc").toString().trim());
			mapDatosDocumento.put("razSoc", request.getParameter("razSoc").toString().trim());
			mapDatosDocumento.put("numExpedo", documento.get("numExpedo").toString().trim());
			mapDatosDocumento.put("numDoc", documento.get("numDoc").toString().trim());
			mapDatosDocumento.put("codTipDoc", documento.get("codTipDoc").toString().trim());
			mapDatosDocumento.put("desTipdoc2", documento.get("desTipdoc2").toString().trim());

			mapDatosDocumento.put("codTipExp", documento.get("codTipExp").toString().trim());
			mapDatosDocumento.put("numCorDoc", documento.get("numCorDoc").toString().trim());
			mapDatosDocumento.put("codOriDes", ValidaConstantes.COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTRANET);
			mapDatosDocumento.put("numCorrelativo", numCorrelativo);

			// DATOS DEL USUARIO PARA AUDITORIA
			if (!Utils.isEmpty(usuarioBean.getNroRegistro())) {
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getNroRegistro());
			} else {
				mapUsuarioBean.put("usuarioRegistro", usuarioBean.getUsuarioSOL());
			}
			mapUsuarioBean.put("fechaRegistro", new Date());
			mapUsuarioBean.put("usuarioModificacion", ValidaConstantes.SEPARADOR_GUION);
			mapUsuarioBean.put("fechaModificacion", ValidaConstantes.FECHA_VACIA);

			//generamos la representaciÃƒÂ³n impresa
			mapResult = Utils.generarRepImpresa(mapDatosDocumento, responseDoc, mapUsuarioBean);
			
			t6625repimpexpvirtBean = (T6625repimpexpvirtBean) mapResult.get("t6625repimpexpvirtBean");

			//grabamos en base de datos
			representacionImpresaService.grabarRepresentacionImpresa(t6625repimpexpvirtBean);

			baos = (ByteArrayOutputStream)mapResult.get("baos");
			
			//mostramos el pdf generado
			response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
			
		    
		    
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.verRepImpresa");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.verRepImpresa");
			NDC.pop();
			NDC.remove();
		}
		return new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
	}

	public ModelAndView cargarObservacionesExpVirtual(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		T6614ExpVirtualBean datosExpBean = null;

		String fecRegistroVista = ValidaConstantes.CADENA_VACIA;
		String fecOrigenDocVista = ValidaConstantes.CADENA_VACIA;
		List<T6615ObsExpBean> obtenerObservacionesExpediente = new ArrayList<T6615ObsExpBean>();
		Map<String, Object> params = null;
        
        //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
		HashMap<String, Object> mapParametrosBusquedaResponables;
		HashMap<String, String> excepciones = new HashMap<String, String>();
		//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

		// Inicio [jquispe 27/05/2016] Parametro para registrar el seguimiento de la accion realizada.
		String parametroNumeroExpedienteVirtual = null;
		// Fin [jquispe 27/05/2016]
		try {
			String indCarga = request.getParameter("indCarga");

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {

				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";

			if (reader != null) {

				jsonEnviado = reader.readLine();

			}

			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);

			Date fechaRegistro = null;
			Date fechaOrigenDoc = null;

			Map<String, Object> parametros = new HashMap<String, Object>();

			String numExpedienteVirtual = Utils.toStr(dataEnvio.get("aux"));

			if (numExpedienteVirtual != null || numExpedienteVirtual != ValidaConstantes.CADENA_VACIA) {

				parametros.put("numExpedienteVirtual", numExpedienteVirtual);
				parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

				datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
                
                //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
				String tipoExp = datosExpBean.getCodTipoExpediente();
				
				if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(tipoExp) || 
					ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(tipoExp)){
					
					mapParametrosBusquedaResponables = new HashMap<String, Object>();
					mapParametrosBusquedaResponables.put("numExpedienteVirtual", numExpedienteVirtual);
					mapParametrosBusquedaResponables.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					
					List<T6621RespExpVirtBean> listaResponsables = responsableService.listarResponsables(mapParametrosBusquedaResponables);
										
					int C = 0;			
					for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
											
						String usuarioLogueado = usuarioBean.getNroRegistro().trim();
						String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
						
						if(usuarioLogueado.equals(responsableExp)){
							
							C++;						
							break;
							
						}
					}
					
					if(C == 0){
						
						excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
						
					}
				}
				//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				if (datosExpBean != null) {
					// Inicio [jquispe 27/05/2016] Parametro para registrar el seguimiento de la accion realizada.
					parametroNumeroExpedienteVirtual = datosExpBean.getNumExpedienteVirtual();
					// Fin [jquispe 27/05/2016]
					params = new HashMap<String, Object>();
					params.put("numExpedVirtual", datosExpBean.getNumExpedienteVirtual());
					fechaRegistro = datosExpBean.getFecRegistro();
					if (fechaRegistro != null) {
						fecRegistroVista = formatoFechaExporta.format(fechaRegistro);
					}
					fechaOrigenDoc = datosExpBean.getFechaDocumentoOrigen();
					if (fechaOrigenDoc != null) {
						fecOrigenDocVista = formatoFechaExporta.format(fechaOrigenDoc);
					}
					obtenerObservacionesExpediente = observacionExpedienteVirtualService.obtenerObservacionesExpediente(params);
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoVerObservacion(parametroNumeroExpedienteVirtual);
				// Fin [jquispe 27/05/2016]
			}
			modelo = new ModelAndView(jsonView);
			modelo.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
			modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
			modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fecOrigenDocVista));
			modelo.addObject("lstObsExp", new JsonSerializer().serialize(obtenerObservacionesExpediente));
            //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarObservacionesExpVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarObservacionesExpVirtual");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}

	// Inicio [jquispe 30/05/2016] Metodo para generar el archivo pdf con indice de expediente electronico.
	@RequestMapping(value = "/verDatosExpedienteVirtualPdf", method = RequestMethod.GET)
	public void verDatosExpedienteVirtualPdf(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - ConsultaReporteController.verDatosExpedienteVirtualPdf");
		}

		String indicadorAcumulador = "";
		HashMap<String, Object> mapParametrosBusqueda;
		List<T6614ExpVirtualBean> listaExpedientesdelAcumulador = new ArrayList<T6614ExpVirtualBean>();
		List<String> listaExpedientes = new ArrayList<String>();
		T6614ExpVirtualBean datosExpBean = null;

		Map<String, Object> parametros = null;
		List<Map<String, Object>> listadoDocumentosExpediente;
		String numExpedienteVirtual = null;
		String razonSocial = null;
		String proceso = null;
		String desTipoExpediente = null;
		String numRuc = null;
		String codTipoExpediente = null;
		String fecRegistroExpediente = null;

		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

			String numExpOrigen = "";
			numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			mapParametrosBusqueda = new HashMap<String, Object>();

			mapParametrosBusqueda.put("numExpedienteVirtual", numExpedienteVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);

			datosExpBean = expedienteVirtualService.obtenerDatosExpediente(mapParametrosBusqueda);

			indicadorAcumulador = datosExpBean.getIndicadorAcumulado();
			numExpOrigen = datosExpBean.getNumExpedienteOrigen().trim();
			listaExpedientes.add(datosExpBean.getNumExpedienteVirtual());
			razonSocial = datosExpBean.getDesRazonSocial();
			proceso = datosExpBean.getDesProceso();
			desTipoExpediente = datosExpBean.getDesTipoExpediente();
			numRuc = datosExpBean.getNumRuc();
			codTipoExpediente = datosExpBean.getCodTipoExpediente();

			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			fecRegistroExpediente = formatoFechaExporta.format(datosExpBean.getFecRegistro());

			if (Utils.equiv(indicadorAcumulador, ValidaConstantes.IND_ACUMULADOR_ACUMULADOR)) {
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpedienteAcumulador", numExpOrigen);

				listaExpedientesdelAcumulador = expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);

				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulador) {
					listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual());
				}

				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);

				listadoDocumentosExpediente = expedienteVirtualService.obtenerListaDocumentosAcumulado(mapParametrosBusqueda);
			} else {
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numExpeDv", numExpedienteVirtual);

				listadoDocumentosExpediente = expedienteVirtualService.obtenerDocumentosExpediente(mapParametrosBusqueda);
			}

			// Inicio [jjurado 08/08/2016]
			Map<String, Object> mapDocExpVirt = new HashMap<String, Object>();
			mapDocExpVirt.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			mapDocExpVirt.put("numExpeDv", numExpedienteVirtual);
			T6613DocExpVirtBean documentoOrigen = expedienteVirtualService.obtenerDatosDocumentoEstado(mapDocExpVirt);
			// Fin [jjurado 08/08/2016]

			parametros = new HashMap<String, Object>();
			parametros.put("listadoDocumentosExpediente", listadoDocumentosExpediente);
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			parametros.put("razonSocial", razonSocial);
			parametros.put("proceso", proceso);
			parametros.put("desTipoExpediente", desTipoExpediente);
			parametros.put("numRuc", numRuc);
			parametros.put("codTipoExpediente", codTipoExpediente);
			parametros.put("fecRegistroExpediente", fecRegistroExpediente);
			parametros.put("numRucUsuario", usuarioBean.getNumRUC());
			parametros.put("codigoUsuario", usuarioBean.getNroRegistro());
			parametros.put("codDep", usuarioBean.getCodDepend());
			parametros.put("numExpedienteOrigen", datosExpBean.getNumExpedienteOrigen());// [jjurado 08/07/2016]
			parametros.put("fecEmi", formatoFechaExporta.format(documentoOrigen.getFecDoc()));// [jjurado 08/07/2016]

			byte[] archivoPdf = indiceExpedienteVirtualService.generarIndiceArchivoPdf(parametros);
			OutputStream outStream = response.getOutputStream();
			response.setContentLength(archivoPdf.length);
			response.setContentType("application/pdf");
			outStream.write(archivoPdf);
			outStream.close();

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.verDatosExpedienteVirtualPdf");
			}

		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.verDatosExpedienteVirtualPdf");
			}

			NDC.pop();
			NDC.remove();
		}
	}

	// Fin [jquispe 30/05/2016]

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoVerObservacion(String numeroExpedienteVirtual) throws Exception {
		Map<String, Object> beanSegui = new HashMap<String, Object>();

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_EXPEDIENTE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_OBSERVACIONES);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_OBSERVACIONES)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);

		seguiService.registrarSeguimiento(beanSegui);
	}

	// Fin [jquispe 27/05/2016]

	@RequestMapping(value = { "/cargarTrazabilidadExpOrigen" }, method = { RequestMethod.GET })
	public ModelAndView cargarTrazabilidadExpOrigen(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarTrazabilidadExpOrigen");
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		List<T6622SeguimBean> listTrazabilidad = new ArrayList<T6622SeguimBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();
		Map<String, Object> params;
		String fechaDocOrigen = "";
		String fechaRegExpediente = "";
		DdpBean beanContribuyente;
		HashMap<String, Object> mapParametrosBusqueda;

		try {

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			String numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
			modelo = new ModelAndView(jsonView);
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
				fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());

				modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fechaDocOrigen));
				modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fechaRegExpediente));
				modelo.addObject("razonSocial", new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				modelo.addObject("datosExpedientes", new JsonSerializer().serialize(t6614ExpVirtualBean));
				break;
			}
			params = new HashMap<String, Object>();
			params.put("numExpedVirtual", numExpedienteVirtual);
			params.put("codTipSeguim", ValidaConstantes.IND_TIP_SEG_EE);
			listTrazabilidad = seguiService.listarSeguimientos(params);
			modelo.addObject("lstTrazExp", new JsonSerializer().serialize(listTrazabilidad));

			// Inicio [jquispe 02/07/2016] Obtiene el estado del expediente origen del ultimo listado de Estados y Etapas que es diferente de vacio.
			Utils.agregarEstadoExpedienteModelo(listTrazabilidad, modelo);
			// Fin [jquispe 02/07/2016]

			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			registrarSeguimientoVerEstadoEtapaExpediente(numExpedienteVirtual);
			// Fin [jquispe 27/05/2016]
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarTrazabilidadExpOrigen");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarTrazabilidadExpOrigen");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}

	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoVerEstadoEtapaExpediente(String numeroExpedienteVirtual) throws Exception {

		Map<String, Object> beanSegui = new HashMap<String, Object>();

		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();

		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		// Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);

		beanSegui.put("num_expedv", numeroExpedienteVirtual != null ? numeroExpedienteVirtual : ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_CONSULTA_EXPEDIENTE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_VER_ESTADO_ETAPA_EXPEDIENTE);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_VER_ESTADO_ETAPA_EXPEDIENTE)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);

		seguiService.registrarSeguimiento(beanSegui);

	}

	// Fin [jquispe 27/05/2016]

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpDoc(HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_07;

		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String numRuc = null;
		String razonSocial = null;
		String numExpOrigen = null;
		String numExpVirtual = null;
		String estExpediente = null;
		String tipoProceso = null;
		String tipoExpediente = null;
		String descOrigen = null;
		String fechaGeneracion = null;
		String fechaOrigen = null;
		String razonSocialRuc = null;


		try {

			listadoExportarCadena = Utils.toStr(request.getParameter("hiddenListaExpDocumentoModal"));
			numRuc = Utils.toStr(request.getParameter("hiddenNumRucDocumentoModal"));
			razonSocial = Utils.toStr(request.getParameter("hiddenRazonSocialDocumentoModal"));
			numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigenDocumentoModal"));
			numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtualDocumentoModal"));
			estExpediente = Utils.toStr(request.getParameter("hiddenEstExpedienteDocumentoModal"));
			tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProcesoDocumentoModal"));
			tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpedienteDocumentoModal"));
			descOrigen = Utils.toStr(request.getParameter("hiddenDescOrigenDocumentoModal"));
			fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracionDocumentoModal"));
			fechaOrigen = Utils.toStr(request.getParameter("hiddenFechaOrigenDocumentoModal"));

			razonSocialRuc = numRuc + " - " + razonSocial;
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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
			
			//Inicio Cabecera
			HSSFRow fila = hoja.createRow(1);
			CellRangeAddress rango = null;
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			rango = new CellRangeAddress(1, 1, 0, 4);
			hoja.addMergedRegion(rango);
			
			HSSFCell nomFecha = fila.createCell(5);
			nomFecha.setCellValue("Fecha del Reporte:");
						
			HSSFCell Fecha = fila.createCell(6);
			Fecha.setCellValue(fecImpresion);
			//Fin Cabecera
			
			//Inicio  Cuerpo del Excel
			fila = hoja.createRow(3);
			CellRangeAddress rango1 = null;
			HSSFCell subtituloCelda = fila.createCell(0);
			subtituloCelda.setCellValue("Datos del Expediente");
			rango1 = new CellRangeAddress(3, 3, 0, 1);
			hoja.addMergedRegion(rango1);

			fila = hoja.createRow(4);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocialRuc);
			rango2 = new CellRangeAddress(4, 4, 2, 3);
			hoja.addMergedRegion(rango2);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda2 = fila.createCell(1);
			subtituloCelda2.setCellValue("N° Expediente Origen:");
			HSSFCell contenido1 = fila.createCell(2);
			contenido1.setCellValue(numExpOrigen);

			HSSFCell subtituloCelda3 = fila.createCell(3);
			subtituloCelda3.setCellValue("N° Expediente Virtual:");
			HSSFCell contenido2 = fila.createCell(4);
			contenido2.setCellValue(numExpVirtual);

			HSSFCell subtituloCelda4 = fila.createCell(5);
			subtituloCelda4.setCellValue("Estado del Expediente:");
			HSSFCell contenido3 = fila.createCell(6);
			contenido3.setCellValue(estExpediente);

			fila = hoja.createRow(6);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(tipoProceso);

			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido5 = fila.createCell(4);
			contenido5.setCellValue(tipoExpediente);

			HSSFCell subtituloCelda7 = fila.createCell(5);
			subtituloCelda7.setCellValue("Origen:");
			HSSFCell contenido6 = fila.createCell(6);
			contenido6.setCellValue(descOrigen);

			fila = hoja.createRow(7);
			HSSFCell subtituloCelda8 = fila.createCell(1);
			subtituloCelda8.setCellValue("Fecha de Generación del Expediente:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(fechaGeneracion);

			HSSFCell subtituloCelda9 = fila.createCell(3);
			subtituloCelda9.setCellValue("Fecha de Documento Origen:");
			HSSFCell contenido8 = fila.createCell(4);
			contenido8.setCellValue(fechaOrigen);
			//fin  Cuerpo del Excel
			
			//Titulo de la Grilla
			fila = hoja.createRow(9);
			CellRangeAddress rango3 = null;
			HSSFCell subtituloCelda10 = fila.createCell(0);
			subtituloCelda10.setCellValue("Listado de Documentos");
			rango3 = new CellRangeAddress(9, 9, 0, 2);
			hoja.addMergedRegion(rango3);
			
			
			//Grilla Icio
			fila = hoja.createRow(10);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda4 = fila.createCell(3);
			HSSFCell celda5 = fila.createCell(4);
			HSSFCell celda6 = fila.createCell(5);
			HSSFCell celda7 = fila.createCell(6);
			HSSFCell celda8 = fila.createCell(7);
			HSSFCell celda9 = fila.createCell(8);
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Fecha de emision de documento.
			HSSFCell celda10 = fila.createCell(9);
			// Fin [jquispe 27/05/2016]
			// Inicio  [Jeffio 26/06/2017]
			HSSFCell celda11 = fila.createCell(10);
			HSSFCell celda12 = fila.createCell(11);
			// Fin  [Jeffio 26/06/2017]
			celda.setCellValue("N°");
			celda1.setCellValue("Tipo de Documento");
			celda2.setCellValue("Número de Documento");
			// Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
			// celda3.setCellValue("Nombre del Archivo");
			// Fin [jquispe 31/05/2016]
			celda4.setCellValue("Origen registro documento");
			celda5.setCellValue("N° Requerimiento (Si corresponde)");
			celda6.setCellValue("Responsable registro documento");
			celda7.setCellValue("Fecha registro documento");
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Fecha de emision de documento.
			celda8.setCellValue("Fecha emisión documento");
			//Inicio Jeffio 26/06/2017
			celda9.setCellValue("Fecha de Notificación");
			celda10.setCellValue("Forma de Notificación");
			//Fin Jeffio 26/06/2017
			celda11.setCellValue("Nº de Expediente Origen");
			celda12.setCellValue("Nº de Expediente Acumulador");
			// Fin [jquispe 27/05/2016]

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 11);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			Sheet ssheet = libro.getSheetAt(0);
			ssheet.autoSizeColumn(0);
			ssheet.autoSizeColumn(1);
			ssheet.autoSizeColumn(2);
			// Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
			// ssheet.autoSizeColumn(3);
			// Fin [jquispe 31/05/2016]
			ssheet.autoSizeColumn(3);
			ssheet.autoSizeColumn(4);
			ssheet.autoSizeColumn(5);
			ssheet.autoSizeColumn(6);
			ssheet.autoSizeColumn(7);
			ssheet.autoSizeColumn(8);
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Fecha de emision de documento.
			ssheet.autoSizeColumn(9);
			// Fin [jquispe 27/05/2016]
			//Inicio Jeffio 26/06/2017
			ssheet.autoSizeColumn(10);
			ssheet.autoSizeColumn(11);
			//Fin Jeffio 26/06/2017

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
			// Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
			// celda3.setCellStyle(estiloCelda);
			// Fin [jquispe 31/05/2016]
			celda4.setCellStyle(estiloCelda);
			celda5.setCellStyle(estiloCelda);
			celda6.setCellStyle(estiloCelda);
			celda7.setCellStyle(estiloCelda);
			celda8.setCellStyle(estiloCelda);
			celda9.setCellStyle(estiloCelda);
			// Inicio [jquispe 27/05/2016] Se modifico para agregar la nueva columna Fecha de emision de documento.
			celda10.setCellStyle(estiloCelda);
			// Fin [jquispe 27/05/2016]
			//Inicio Jeffio 26/06/2017
			celda11.setCellStyle(estiloCelda);
			celda12.setCellStyle(estiloCelda);
			//Fin Jeffio 26/06/2017

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda1.setCellStyle(estiloTitulo);
			subtituloCelda2.setCellStyle(estiloTitulo);
			subtituloCelda3.setCellStyle(estiloTitulo);
			subtituloCelda4.setCellStyle(estiloTitulo);
			subtituloCelda5.setCellStyle(estiloTitulo);
			subtituloCelda6.setCellStyle(estiloTitulo);
			subtituloCelda7.setCellStyle(estiloTitulo);
			subtituloCelda8.setCellStyle(estiloTitulo);
			subtituloCelda9.setCellStyle(estiloTitulo);
			subtituloCelda10.setCellStyle(estiloTitulo);

			
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

				fila = hoja.createRow(i + 11);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desTipdoc")));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numDoc")));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				// Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
				/*
				celda3 = fila.createCell(3);
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("descArchivo")));
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				*/
				// Fin [jquispe 31/05/2016]
				// Inicio [jquispe 31/05/2016] Modificado para eliminar el nombre del archivo.
				celda4 = fila.createCell(3);
				celda4.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desOrigen")));
				hoja.autoSizeColumn(3);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(4);
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("nroRequerim")));
				hoja.autoSizeColumn(4);
				celda5.setCellStyle(estiloRecorrido);
				celda6 = fila.createCell(5);
				celda6.setCellValue(Utils.toStr(listadoExportar.get(i).get("nomPersonaCargo")));
				hoja.autoSizeColumn(5);
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(6);
				celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecCargVista")));
				hoja.autoSizeColumn(6);
				celda7.setCellStyle(estiloRecorrido);
				celda8 = fila.createCell(7);
				celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaVista")));
				hoja.autoSizeColumn(7);
				celda8.setCellStyle(estiloRecorrido);
				celda9 = fila.createCell(8);
				celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecNotifVista")));
				hoja.autoSizeColumn(8);
				celda9.setCellStyle(estiloRecorrido);
				celda10 = fila.createCell(9);
				celda10.setCellValue(Utils.toStr(listadoExportar.get(i).get("desForNotif")));
				hoja.autoSizeColumn(9);
				celda10.setCellStyle(estiloRecorrido);
				celda11 = fila.createCell(10);
				celda11.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedo")));
				hoja.autoSizeColumn(10);
				celda11.setCellStyle(estiloRecorrido);
				celda12 = fila.createCell(11);
				celda12.setCellValue(Utils.toStr(listadoExportar.get(i).get("numAcumuladorVista")));
				hoja.autoSizeColumn(11);
				celda12.setCellStyle(estiloRecorrido);
				
				// Fin [jquispe 31/05/2016]
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

			String filename = "rpt_documentos_expediente_" + part + ".xls";
			;
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
			String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
		return view;

	}
    
    //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpDocFis(HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_07;

		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String numRuc = null;
		String razonSocial = null;
		String numExpOrigen = null;
		String numExpVirtual = null;
		String estExpediente = null;
		String tipoProceso = null;
		String tipoExpediente = null;
		String descOrigen = null;
		String fechaGeneracion = null;
		String fechaOrigen = null;
		String razonSocialRuc = null;

		try {

			listadoExportarCadena = Utils.toStr(request.getParameter("hiddenListaExpDocumentoModalFis"));
			numRuc = Utils.toStr(request.getParameter("hiddenNumRucDocumentoModalFis"));
			razonSocial = Utils.toStr(request.getParameter("hiddenRazonSocialDocumentoModalFis"));
			numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigenDocumentoModalFis"));
			numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtualDocumentoModalFis"));
			estExpediente = Utils.toStr(request.getParameter("hiddenEstExpedienteDocumentoModalFis"));
			tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProcesoDocumentoModalFis"));
			tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpedienteDocumentoModalFis"));
			descOrigen = Utils.toStr(request.getParameter("hiddenDescOrigenDocumentoModalFis"));
			fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracionDocumentoModalFis"));
			fechaOrigen = Utils.toStr(request.getParameter("hiddenFechaOrigenDocumentoModalFis"));

			razonSocialRuc = numRuc + " - " + razonSocial;
					
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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
			
			//Inicio Cabecera
			HSSFRow fila = hoja.createRow(1);
			CellRangeAddress rango = null;
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			rango = new CellRangeAddress(1, 1, 0, 4);
			hoja.addMergedRegion(rango);
			
			HSSFCell nomFecha = fila.createCell(5);
			nomFecha.setCellValue("Fecha del Reporte:");
						
			HSSFCell Fecha = fila.createCell(6);
			Fecha.setCellValue(fecImpresion);
			//Fin Cabecera
			
			//Inicio  Cuerpo del Excel
			fila = hoja.createRow(3);
			CellRangeAddress rango1 = null;
			HSSFCell subtituloCelda = fila.createCell(0);
			subtituloCelda.setCellValue("Datos del Expediente");
			rango1 = new CellRangeAddress(3, 3, 0, 1);
			hoja.addMergedRegion(rango1);

			fila = hoja.createRow(4);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocialRuc);
			rango2 = new CellRangeAddress(4, 4, 2, 3);
			hoja.addMergedRegion(rango2);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda2 = fila.createCell(1);
			subtituloCelda2.setCellValue("N° Expediente Origen:");
			HSSFCell contenido1 = fila.createCell(2);
			contenido1.setCellValue(numExpOrigen);

			HSSFCell subtituloCelda3 = fila.createCell(3);
			subtituloCelda3.setCellValue("N° Expediente Virtual:");
			HSSFCell contenido2 = fila.createCell(4);
			contenido2.setCellValue(numExpVirtual);

			HSSFCell subtituloCelda4 = fila.createCell(5);
			subtituloCelda4.setCellValue("Estado del Expediente:");
			HSSFCell contenido3 = fila.createCell(6);
			contenido3.setCellValue(estExpediente);

			fila = hoja.createRow(6);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(tipoProceso);

			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido5 = fila.createCell(4);
			contenido5.setCellValue(tipoExpediente);

			HSSFCell subtituloCelda7 = fila.createCell(5);
			subtituloCelda7.setCellValue("Origen:");
			HSSFCell contenido6 = fila.createCell(6);
			contenido6.setCellValue(descOrigen);

			fila = hoja.createRow(7);
			HSSFCell subtituloCelda8 = fila.createCell(1);
			subtituloCelda8.setCellValue("Fecha de Generación del Expediente:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(fechaGeneracion);

			HSSFCell subtituloCelda9 = fila.createCell(3);
			subtituloCelda9.setCellValue("Fecha de Documento Origen:");
			HSSFCell contenido8 = fila.createCell(4);
			contenido8.setCellValue(fechaOrigen);
			//fin  Cuerpo del Excel
			
			//Titulo de la Grilla
			fila = hoja.createRow(9);
			CellRangeAddress rango3 = null;
			HSSFCell subtituloCelda10 = fila.createCell(0);
			subtituloCelda10.setCellValue("Listado de Documentos");
			rango3 = new CellRangeAddress(9, 9, 0, 2);
			hoja.addMergedRegion(rango3);
			
			
			//Grilla Inicio
			fila = hoja.createRow(10);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda4 = fila.createCell(3);
			HSSFCell celda5 = fila.createCell(4);
			HSSFCell celda6 = fila.createCell(5);
			HSSFCell celda7 = fila.createCell(6);
			HSSFCell celda8 = fila.createCell(7);
			HSSFCell celda9 = fila.createCell(8);
			HSSFCell celda10 = fila.createCell(9);
			HSSFCell celda11 = fila.createCell(10);
			HSSFCell celda12 = fila.createCell(11);
			HSSFCell celda13 = fila.createCell(12);
			HSSFCell celda14 = fila.createCell(13);
						
			celda.setCellValue("N°");
			celda1.setCellValue("Tipo de Documento");
			celda2.setCellValue("Número de Documento");
			celda4.setCellValue("Origen registro documento");
			celda5.setCellValue("Visible al Contrib.");
			celda6.setCellValue("Con Reserva Tributaria");
			celda7.setCellValue("Responsable registro documento");
			celda8.setCellValue("Fecha registro documento");
			celda9.setCellValue("Fecha emisión documento");
			celda10.setCellValue("Fecha de Notificación");
			celda11.setCellValue("Forma de Notificación");
			celda12.setCellValue("Nº de Expediente Origen");
			celda13.setCellValue("Tip. Doc. Relacionado");
			celda14.setCellValue("Num. Doc. Relacionado");
						
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
			ssheet.autoSizeColumn(8);
			ssheet.autoSizeColumn(9);
			ssheet.autoSizeColumn(10);
			ssheet.autoSizeColumn(11);
			ssheet.autoSizeColumn(12);
			ssheet.autoSizeColumn(13);
			
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
			celda4.setCellStyle(estiloCelda);
			celda5.setCellStyle(estiloCelda);
			celda6.setCellStyle(estiloCelda);
			celda7.setCellStyle(estiloCelda);
			celda8.setCellStyle(estiloCelda);
			celda9.setCellStyle(estiloCelda);
			celda10.setCellStyle(estiloCelda);
			celda11.setCellStyle(estiloCelda);
			celda12.setCellStyle(estiloCelda);
			celda13.setCellStyle(estiloCelda);
			celda14.setCellStyle(estiloCelda);
			
			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda1.setCellStyle(estiloTitulo);
			subtituloCelda2.setCellStyle(estiloTitulo);
			subtituloCelda3.setCellStyle(estiloTitulo);
			subtituloCelda4.setCellStyle(estiloTitulo);
			subtituloCelda5.setCellStyle(estiloTitulo);
			subtituloCelda6.setCellStyle(estiloTitulo);
			subtituloCelda7.setCellStyle(estiloTitulo);
			subtituloCelda8.setCellStyle(estiloTitulo);
			subtituloCelda9.setCellStyle(estiloTitulo);
			subtituloCelda10.setCellStyle(estiloTitulo);
			
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

				fila = hoja.createRow(i + 11);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desTipdoc")));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("numDoc")));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(3);
				celda4.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desOrigen")));
				hoja.autoSizeColumn(3);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(4);
				celda5.setCellValue(Utils.toStr(listadoExportar.get(i).get("indVis")));
				hoja.autoSizeColumn(4);
				celda5.setCellStyle(estiloRecorrido);
				celda6 = fila.createCell(5);
				celda6.setCellValue(Utils.toStr(listadoExportar.get(i).get("indResTri")));
				hoja.autoSizeColumn(5);
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(6);
				celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("nomPersonaCargo")));
				hoja.autoSizeColumn(6);
				celda7.setCellStyle(estiloRecorrido);
				celda8 = fila.createCell(7);
				celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecCargVista")));
				hoja.autoSizeColumn(7);
				celda8.setCellStyle(estiloRecorrido);
				celda9 = fila.createCell(8);
				celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaVista")));
				hoja.autoSizeColumn(8);
				celda9.setCellStyle(estiloRecorrido);
				celda10 = fila.createCell(9);
				celda10.setCellValue(Utils.toStr(listadoExportar.get(i).get("fecNotifVista")));
				hoja.autoSizeColumn(9);
				celda10.setCellStyle(estiloRecorrido);
				celda11 = fila.createCell(10);
				celda11.setCellValue(Utils.toStr(listadoExportar.get(i).get("desForNotif")));
				hoja.autoSizeColumn(10);
				celda11.setCellStyle(estiloRecorrido);
				celda12 = fila.createCell(11);
				celda12.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedo")));
				hoja.autoSizeColumn(11);
				celda12.setCellStyle(estiloRecorrido);
				celda13 = fila.createCell(12);
				celda13.setCellValue(Utils.toStr(listadoExportar.get(i).get("desTipdocRel")));
				hoja.autoSizeColumn(12);
				celda13.setCellStyle(estiloRecorrido);
				celda14 = fila.createCell(13);
				celda14.setCellValue(Utils.toStr(listadoExportar.get(i).get("numDocRel")));
				hoja.autoSizeColumn(13);
				celda14.setCellStyle(estiloRecorrido);
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

			String filename = "rpt_documentos_expediente_" + part + ".xls";
			;
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
			String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
		return view;

	}
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelObservaciones(HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_08;

		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String numRuc = null;
		String razonSocial = null;
		String numExpOrigen = null;
		String numExpVirtual = null;
		String estExpediente = null;
		String tipoProceso = null;
		String tipoExpediente = null;
		String descOrigen = null;
		String fechaGeneracion = null;
		String fechaOrigen = null;
		String razonSocialRuc = null;

		try {

			listadoExportarCadena = request.getParameter("hiddenListaExp") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenListaExp").toString().trim();
			numRuc = Utils.toStr(request.getParameter("hiddenNumRuc"));
			razonSocial = Utils.toStr(request.getParameter("hiddenRazonSocial"));
			numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
			numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtual"));
			estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
			tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
			tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
			descOrigen = Utils.toStr(request.getParameter("hiddenDescOrigen"));
			fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
			fechaOrigen = Utils.toStr(request.getParameter("hiddenFechaOrigen"));
			razonSocialRuc = numRuc + " - " + razonSocial;

			@SuppressWarnings("unchecked")
			List<T6615ObsExpBean> listadoExportar = (ArrayList<T6615ObsExpBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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

			HSSFRow fila = hoja.createRow(1);
			CellRangeAddress rango = null;
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			rango = new CellRangeAddress(1, 1, 0, 2);
			hoja.addMergedRegion(rango);

			HSSFCell nomFecha = fila.createCell(3);
			nomFecha.setCellValue("Fecha del Reporte:");

			HSSFCell Fecha = fila.createCell(4);
			Fecha.setCellValue(fecImpresion);

			fila = hoja.createRow(3);
			CellRangeAddress rango1 = null;
			HSSFCell subtituloCelda = fila.createCell(0);
			subtituloCelda.setCellValue("Datos del Expediente");
			rango1 = new CellRangeAddress(3, 3, 0, 1);
			hoja.addMergedRegion(rango1);

			fila = hoja.createRow(4);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocialRuc);
			rango2 = new CellRangeAddress(4, 4, 2, 3);
			hoja.addMergedRegion(rango2);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda2 = fila.createCell(1);
			subtituloCelda2.setCellValue("N° Expediente Origen:");
			HSSFCell contenido1 = fila.createCell(2);
			contenido1.setCellValue(numExpOrigen);

			HSSFCell subtituloCelda3 = fila.createCell(3);
			subtituloCelda3.setCellValue("N° Expediente Virtual:");
			HSSFCell contenido2 = fila.createCell(4);
			contenido2.setCellValue(numExpVirtual);

			fila = hoja.createRow(6);
			HSSFCell subtituloCelda5 = fila.createCell(1);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(2);
			contenido4.setCellValue(tipoProceso);

			HSSFCell subtituloCelda6 = fila.createCell(3);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido5 = fila.createCell(4);
			contenido5.setCellValue(tipoExpediente);

			fila = hoja.createRow(7);
			HSSFCell subtituloCelda8 = fila.createCell(1);
			subtituloCelda8.setCellValue("Fecha de Generación del Expediente:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(fechaGeneracion);

			HSSFCell subtituloCelda9 = fila.createCell(3);
			subtituloCelda9.setCellValue("Fecha de Documento Origen:");
			HSSFCell contenido8 = fila.createCell(4);
			contenido8.setCellValue(fechaOrigen);

			fila = hoja.createRow(8);
			HSSFCell subtituloCelda4 = fila.createCell(1);
			subtituloCelda4.setCellValue("Estado del Expediente:");
			HSSFCell contenido3 = fila.createCell(2);
			contenido3.setCellValue(estExpediente);
			
			HSSFCell subtituloCelda7 = fila.createCell(3);
			subtituloCelda7.setCellValue("Origen:");
			HSSFCell contenido6 = fila.createCell(4);
			contenido6.setCellValue(descOrigen);

			fila = hoja.createRow(10);
			CellRangeAddress rango3 = null;
			HSSFCell subtituloCelda10 = fila.createCell(0);
			subtituloCelda10.setCellValue("Listado de Trazabilidad:");
			rango3 = new CellRangeAddress(10, 10, 0, 2);
			hoja.addMergedRegion(rango3);

			fila = hoja.createRow(11);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			 //Inicio [gangles 22/08/2016]
	        CellRangeAddress rangoObservacion = null;
	        rangoObservacion = new CellRangeAddress(11, 11, 1, 2);
			hoja.addMergedRegion(rangoObservacion);
			 //Fin [gangles 22/08/2016]
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			//Inicio [gangles 22/08/2016]
			HSSFCell celda4 = fila.createCell(4);
			//Fin [gangles 22/08/2016]
			celda.setCellValue("N°");
			celda1.setCellValue("Observación");
			celda3.setCellValue("Usuario Registro");
			celda4.setCellValue("Fecha de registro");

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
			//Inicio [gangles 22/08/2016]
			celda4.setCellStyle(estiloCelda);
			//Fin [gangles 22/08/2016]

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda1.setCellStyle(estiloTitulo);
			subtituloCelda2.setCellStyle(estiloTitulo);
			subtituloCelda3.setCellStyle(estiloTitulo);
			subtituloCelda4.setCellStyle(estiloTitulo);
			subtituloCelda5.setCellStyle(estiloTitulo);
			subtituloCelda6.setCellStyle(estiloTitulo);
			subtituloCelda7.setCellStyle(estiloTitulo);
			subtituloCelda8.setCellStyle(estiloTitulo);
			subtituloCelda9.setCellStyle(estiloTitulo);
			subtituloCelda10.setCellStyle(estiloTitulo);

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

				fila = hoja.createRow(i + 12);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(listadoExportar.get(i).getNumOrden() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNumOrden().toString().trim());
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(listadoExportar.get(i).getDesObservacion() == null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).getDesObservacion().toString().trim()));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				 //Inicio [gangles 22/08/2016]
   	        	CellRangeAddress rangoObservacionVal = null;
   	        	rangoObservacionVal = new CellRangeAddress(i + 12, i + 12, 1, 2);
   	        	hoja.addMergedRegion(rangoObservacionVal);
				celda2 = fila.createCell(2);
				celda2.setCellStyle(estiloRecorrido);
   			 //Fin [gangles 22/08/2016]
				celda3 = fila.createCell(3);
				celda3.setCellValue(listadoExportar.get(i).getNomUsuGeneraObs() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNomUsuGeneraObs().toString().trim());
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(listadoExportar.get(i).getFecRegistroObs() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getFecRegistroObs().toString().trim());
				celda4.setCellStyle(estiloRecorrido);
				/* ajustar la demas columnad */
				hoja.autoSizeColumn(4);
				hoja.autoSizeColumn(5);
				hoja.autoSizeColumn(6);
				hoja.autoSizeColumn(7);
				hoja.autoSizeColumn(8);
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

			String filename = "rpt_observaciones_expediente_" + part + ".xls";
			;
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
			String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
		return view;

	}

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelTrazabilidad(HttpServletRequest request, HttpServletResponse response) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_09;

		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		String numRuc = null;
		String razonSocial = null;
		String numExpOrigen = null;
		String numExpVirtual = null;
		String estExpediente = null;
		String tipoProceso = null;
		String tipoExpediente = null;
		String descOrigen = null;
		String fechaGeneracion = null;
		String fechaOrigen = null;
		String razonSocialRuc = null;

		try {

			listadoExportarCadena = request.getParameter("hiddenListaExp") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenListaExp").toString().trim();
			numRuc = request.getParameter("hiddenNumRuc") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumRuc").toString().trim();
			razonSocial = request.getParameter("hiddenRazonSocial") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenRazonSocial").toString().trim();
			numExpOrigen = request.getParameter("hiddenNumExpOrigen") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumExpOrigen").toString().trim();
			numExpVirtual = request.getParameter("hiddenNumExpVirtual") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenNumExpVirtual").toString().trim();
			estExpediente = request.getParameter("hiddenEstExpediente") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenEstExpediente").toString().trim();
			tipoProceso = request.getParameter("hiddenTipoProceso") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoProceso").toString().trim();
			tipoExpediente = request.getParameter("hiddenTipoExpediente") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenTipoExpediente").toString().trim();
			descOrigen = request.getParameter("hiddenDescOrigen") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenDescOrigen").toString().trim();
			fechaGeneracion = request.getParameter("hiddenFechaGeneracion") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaGeneracion").toString().trim();
			fechaOrigen = request.getParameter("hiddenFechaOrigen") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenFechaOrigen").toString().trim();

			razonSocialRuc = numRuc + " - " + razonSocial;

			@SuppressWarnings("unchecked")
			List<T6622SeguimBean> listadoExportar = (ArrayList<T6622SeguimBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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

			HSSFRow fila = hoja.createRow(1);
			CellRangeAddress rango = null;
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			rango = new CellRangeAddress(1, 1, 0, 4);
			hoja.addMergedRegion(rango);


			fila = hoja.createRow(3);
			CellRangeAddress rango1 = null;
			HSSFCell subtituloCelda = fila.createCell(0);
			subtituloCelda.setCellValue("Datos del Expediente");
			rango1 = new CellRangeAddress(3, 3, 0, 1);
			hoja.addMergedRegion(rango1);

			fila = hoja.createRow(4);
			CellRangeAddress rango2 = null;
			HSSFCell subtituloCelda1 = fila.createCell(1);
			subtituloCelda1.setCellValue("RUC:");
			HSSFCell contenido = fila.createCell(2);
			contenido.setCellValue(razonSocialRuc);
			rango2 = new CellRangeAddress(4, 4, 2, 3);
			hoja.addMergedRegion(rango2);

			/*
			 * fila = hoja.createRow(5); HSSFCell subtituloCelda2 = fila.createCell(1); subtituloCelda2.setCellValue("NÃ‚Â° Expediente Origen"); HSSFCell contenido1 = fila.createCell(2);
			 * contenido1.setCellValue(numExpOrigen);
			 * 
			 * HSSFCell subtituloCelda3 = fila.createCell(3); subtituloCelda3.setCellValue("NÃ‚Â° Expediente Virtual"); HSSFCell contenido2 = fila.createCell(4); contenido2.setCellValue(numExpVirtual);
			 * 
			 * HSSFCell subtituloCelda4 = fila.createCell(5); subtituloCelda4.setCellValue("Estado del Expediente"); HSSFCell contenido3 = fila.createCell(6); contenido3.setCellValue(estExpediente);
			 */
			HSSFCell subtituloCelda5 = fila.createCell(4);
			subtituloCelda5.setCellValue("Proceso:");
			HSSFCell contenido4 = fila.createCell(5);
			contenido4.setCellValue(tipoProceso);

			fila = hoja.createRow(5);
			HSSFCell subtituloCelda6 = fila.createCell(1);
			subtituloCelda6.setCellValue("Tipo de Expediente:");
			HSSFCell contenido5 = fila.createCell(2);
			contenido5.setCellValue(tipoExpediente);

			HSSFCell subtituloCelda7 = fila.createCell(4);
			subtituloCelda7.setCellValue("N° Expediente Origen:");
			HSSFCell contenido2 = fila.createCell(5);
			contenido2.setCellValue(numExpOrigen);

			fila = hoja.createRow(6);
			HSSFCell subtituloCelda8 = fila.createCell(1);
			subtituloCelda8.setCellValue("Fecha de Documento Origen:");
			HSSFCell contenido7 = fila.createCell(2);
			contenido7.setCellValue(fechaOrigen);

			HSSFCell subtituloCelda9 = fila.createCell(4);
			subtituloCelda9.setCellValue("Estado del Expediente Virtual:");
			HSSFCell contenido3 = fila.createCell(5);
			contenido3.setCellValue(estExpediente);

			//Inicio [gangles 22/08/2016]
			fila = hoja.createRow(7);
			HSSFCell nomFecha = fila.createCell(1);
			nomFecha.setCellValue("Fecha del Reporte:");
			HSSFCell Fecha = fila.createCell(2);
			Fecha.setCellValue(fecImpresion);
			//Fin [gangles 22/08/2016]
			
			fila = hoja.createRow(9);
			CellRangeAddress rango3 = null;
			HSSFCell subtituloCelda10 = fila.createCell(0);
			subtituloCelda10.setCellValue("Listado de Estados - Etapas del Expediente Origen");
			rango3 = new CellRangeAddress(9, 9, 0, 4);
			hoja.addMergedRegion(rango3);

			fila = hoja.createRow(10);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			HSSFCell celda4 = fila.createCell(4);
			HSSFCell celda5 = fila.createCell(5);

			celda.setCellValue("N°");
			celda1.setCellValue("Tipo de documento");
			celda2.setCellValue("N° documento");
			celda3.setCellValue("Estado");
			celda4.setCellValue("Etapa");
			celda5.setCellValue("Fecha de registro");

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 11);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			Sheet ssheet = libro.getSheetAt(0);
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
			subtituloCelda.setCellStyle(estiloTitulo);
			subtituloCelda1.setCellStyle(estiloTitulo);
			subtituloCelda5.setCellStyle(estiloTitulo);
			subtituloCelda6.setCellStyle(estiloTitulo);
			subtituloCelda7.setCellStyle(estiloTitulo);
			subtituloCelda8.setCellStyle(estiloTitulo);
			subtituloCelda9.setCellStyle(estiloTitulo);
			subtituloCelda10.setCellStyle(estiloTitulo);
			//Inicio [gangles 22/08/2016]
			nomFecha.setCellStyle(estiloTitulo);
			//Fin [gangles 22/08/2016]
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

				fila = hoja.createRow(i + 11);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(listadoExportar.get(i).getNumOrden() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNumOrden().toString().trim());
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(listadoExportar.get(i).getDesTipoDocumento() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getDesTipoDocumento().toString().trim());
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(listadoExportar.get(i).getNumDocumento() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNumDocumento().toString().trim());
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);
				celda3 = fila.createCell(3);
				celda3.setCellValue(listadoExportar.get(i).getDesEstado() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getDesEstado().toString().trim());
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);
				celda4.setCellValue(listadoExportar.get(i).getDesEtapa() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getDesEtapa().toString().trim());
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);
				celda5.setCellValue(listadoExportar.get(i).getFecVistaSegui() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getFecVistaSegui().toString().trim());
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
				hoja.autoSizeColumn(6);

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

			String filename = "rpt_trazabilidad_expediente_" + part + ".xls";
			;
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
			String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
			view = new ModelAndView("PagM", "beanM", mensajeBean);
		}
		return view;

	}

	/*
	 * public ModelAndView cargarDocumentosExpAcumulado(HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * ModelAndView modelo; HttpSession session = request.getSession(true); UsuarioBean usuarioBean; List<T6613DocExpVirtBean> obtenerDocumentosExpediente=new ArrayList<T6613DocExpVirtBean>() ;;
	 * List<T6614ExpVirtualBean> listaExpedientesVirtuales=new ArrayList<T6614ExpVirtualBean>() ; List<T6614ExpVirtualBean> listaExpedientesdelAcumulado=new ArrayList<T6614ExpVirtualBean>() ;
	 * 
	 * String fechaDocOrigen=""; String fechaRegExpediente=""; DdpBean beanContribuyente; HashMap<String, Object> mapParametrosBusqueda;
	 * 
	 * List<String> listaExpedientes = new ArrayList<String>();
	 * 
	 * try {
	 * 
	 * if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
	 * 
	 * String usub = request.getParameter("usub"); String tenc = request.getParameter("tenc"); usub = usub.replace(' ', '+'); usuarioBean =
	 * EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
	 * 
	 * }else{ usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean"); }
	 * 
	 * Map<String, Object> titulos = new HashMap<String, Object>(); titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
	 * 
	 * String numExp = Utils.toStr(request.getParameter("numExpedienteAcumulador")); mapParametrosBusqueda = new HashMap<String, Object>(); modelo = new ModelAndView("jsonView");
	 * mapParametrosBusqueda.put("numExpedVirtual", numExp); mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN); listaExpedientesVirtuales =
	 * expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);
	 * 
	 * for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) { beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc()); fechaDocOrigen =
	 * Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen()); fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());;
	 * modelo.addObject("fechaOrigenDoc",new JsonSerializer().serialize(fechaDocOrigen)); modelo.addObject("fechaRegistro",new JsonSerializer().serialize(fechaRegExpediente));
	 * modelo.addObject("razonSocial",new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial())); modelo.addObject("t6614ExpVirtualBean",new
	 * JsonSerializer().serialize(t6614ExpVirtualBean)); listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual()); break; }
	 * 
	 * mapParametrosBusqueda = new HashMap<String, Object>(); mapParametrosBusqueda.put("numExpedienteAcumulado", numExp);
	 * 
	 * listaExpedientesdelAcumulado=expedienteVirtualService.obtenerListaExpedienteVirtualAcumulado(mapParametrosBusqueda);
	 * 
	 * for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesdelAcumulado) { listaExpedientes.add(t6614ExpVirtualBean.getNumExpedienteVirtual()); }
	 * 
	 * HashMap<String, String> excepciones = new HashMap<String, String>();
	 * 
	 * excepciones.put("excepcion01",AvisoConstantes.EXCEP_MODULO_04_04_017); modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones)); modelo.addObject("titulos",new
	 * JsonSerializer().serialize(titulos));
	 * 
	 * mapParametrosBusqueda = new HashMap<String, Object>(); mapParametrosBusqueda.put("listaExpedientes", listaExpedientes);
	 * 
	 * obtenerDocumentosExpediente = expedienteVirtualService.obtenerListaDocumentosAcumulado(mapParametrosBusqueda);
	 * 
	 * modelo.addObject("jsp", NavegaConstantes.MANT_MODULO_04_01_007); modelo.addObject("lstDocExp", new JsonSerializer().serialize(obtenerDocumentosExpediente));
	 * 
	 * } catch (Exception ex) {
	 * 
	 * if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.consultarProcesos");
	 * 
	 * log.error(ex, ex); MensajeBean msb = new MensajeBean(); modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR); msb.setError(true);
	 * msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR); modelo.addObject("beanErr", msb);
	 * 
	 * } finally {
	 * 
	 * if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.consultarProcesos");
	 * 
	 * NDC.pop(); NDC.remove();
	 * 
	 * }
	 * 
	 * return modelo; }
	 */

	public ModelAndView cargarIndicadores(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarIndicadores");

		List<T01ParamBean> listadoProcesos = null;
		List<T01ParamBean> listadoNumeroTipoExpediente = null;
		ModelAndView modelAndView = null;
		List<T01ParamBean> listadoOrigenExpediente = null;
		List<T01ParamBean> listadoTipoDocumento = null;
		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");// obtener la dependencia del usaurio que ingreso
			String codDepUsuario = usuarioBean.getCodDepend();

			listadoProcesos = configuracionExpedienteService.listarProcesos();
			listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
			listadoOrigenExpediente = configuracionExpedienteService.listarOrigenExpediente();

			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);

			HashMap<String, String> excepciones = new HashMap<String, String>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_01_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_01_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_01_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_01_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_01_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_01_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_04_01_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_04_01_009);
			excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_04_01_010);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_04_01_011);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_04_01_012);
			excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_04_01_013);
			excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_04_01_014);
			excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_04_01_018);
			excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_04_01_019);

			modelAndView = new ModelAndView("IndicadoresEstadisticasExpVirtual");
			modelAndView.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
			modelAndView.addObject("listadoNumeroTipoExpediente", new JsonSerializer().serialize(listadoNumeroTipoExpediente));
			modelAndView.addObject("listadoOrigenExpediente", new JsonSerializer().serialize(listadoOrigenExpediente));
			modelAndView.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelAndView.addObject("titulos", new JsonSerializer().serialize(titulos));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarIndicadores");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarIndicadores");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarBusqRepExpVirtual");
		return modelAndView;
	}

	public ModelAndView cargarListaTiposDocumentos(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarListaTiposDocumentos");
		ModelAndView modelAndView;
		try {
			String codTipoExpediente = Utils.toStr(request.getParameter("codTipoExpediente"));

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("codTipoExpediente", codTipoExpediente);
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			parametros.put("indConsulta", ValidaConstantes.IND_TIPO_DOCU_DISPONIBLE);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			parametros.put("listIndTipDoc", listIndTipDoc);
			parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			List<T6623TipDocExpBean> listadoTiposDocumentosDisponibles = configuracionExpedienteService.listarTiposDocumentos(parametros);
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("listadoTiposDocumentosDisponibles", listadoTiposDocumentosDisponibles);

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarListaTiposDocumentos");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposDocumentos");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposExpediente");
		return modelAndView;

	}

	@RequestMapping(value = "/generarReportesIndicador", method = RequestMethod.GET)
	public ModelAndView generarReportesIndicador(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio -  ConsultaReporteController.generarReportesIndicador");

		ModelAndView modelAndView = null;
		Date fecDesde = null;
		Date fecHasta = null;
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//Fin luis Estrada 10/11/2016
		try {
			HttpSession session = request.getSession(true);
			UsuarioBean usuarioBean = null;
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			String codProceso = Utils.toStr(request.getParameter("codProceso"));
			String codTipExpediente = Utils.toStr(request.getParameter("codTipexp"));
			String codOrigenExpediente = Utils.toStr(request.getParameter("codOrigenExpediente"));
			String codTipDocumento = Utils.toStr(request.getParameter("codTipDocumento"));
			String opciones = Utils.toStr(request.getParameter("opciones"));
			String fecIni = Utils.toStr(request.getParameter("fecDesde"));
			String fecFin = Utils.toStr(request.getParameter("fecHasta"));
			
			//Inicio [oachahuic][PAS20165E210400270]
			// CARGA DESCRIPCION DEPENDENCIA
			Map<String, Object> mapaDependencias = null;
			if (ValidaConstantes.TIPO_EXPE_RECTIF_DAM.equals(codTipExpediente)) {
				mapaDependencias = aduanaService.obtenerDependencias();
			} else {
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapa.put("indLimite", true);
				mapa.put("limInferior", CatalogoConstantes.LIMITE_INFERIOR_DEPENDENCIA);
				mapa.put("limSuperior", CatalogoConstantes.LIMITE_SUPERIOR_DEPENDENCIA);
				mapaDependencias = catalogoService.obtenerCatalogo(mapa);
			}
			//Fin [oachahuic][PAS20165E210400270]

			if (!Utils.isEmpty(request.getParameter("fecDesde"))) {
				fecDesde = Utils.stringToDate(Utils.toStr(request.getParameter("fecDesde")), CatalogoConstantes.INT_TWO);
				Calendar calendarDesde = Calendar.getInstance();
				calendarDesde.setTime(fecDesde);
				calendarDesde.set(Calendar.HOUR_OF_DAY, 0);
				calendarDesde.set(Calendar.MINUTE, 0);
				calendarDesde.set(Calendar.SECOND, 0);
				fecDesde = calendarDesde.getTime();
			}

			if (!Utils.isEmpty(request.getParameter("fecHasta"))) {
				fecHasta = Utils.stringToDate(Utils.toStr(request.getParameter("fecHasta")), CatalogoConstantes.INT_TWO);
				Calendar calendarHasta = Calendar.getInstance();
				calendarHasta.setTime(fecHasta);
				calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
				calendarHasta.set(Calendar.MINUTE, 59);
				calendarHasta.set(Calendar.SECOND, 59);
				fecHasta = calendarHasta.getTime();
			}

			modelAndView = new ModelAndView(jsonView);
			// validamos las fechas
			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
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
			// validamos Origen expediente virtual
			if (!Utils.isEmpty(codOrigenExpediente)) {
				mapParametrosBusqueda.put("codOrigenExpediente", codOrigenExpediente);
			}
			// validamos tipo de documentos
			if (!Utils.isEmpty(codTipDocumento)) {
				mapParametrosBusqueda.put("codTipDoc", codTipDocumento);
			} else {
				mapParametrosBusqueda.put("codTipDoc", ValidaConstantes.CADENA_VACIA);
			}
			//validamos el cÃƒÂ³digo de dependencia
			if (usuarioBean.getCodDepend() != null && usuarioBean.getCodDepend().length() > 2) {
				mapParametrosBusqueda.put("codDependencia", usuarioBean.getCodDepend().substring(0, 3));
			}

			// validamos Fecha Ini
			if (!Utils.isEmpty(fecIni)) {
				mapParametrosBusqueda.put("fecIni", fecIni);
			}

			// validamos Fecha Fin
			if (!Utils.isEmpty(fecFin)) {
				mapParametrosBusqueda.put("fecFin", fecFin);
			}

			T01ParamBean proceso = configuracionExpedienteService.obtenerProceso(mapParametrosBusqueda.get("codProceso").toString());
			T01ParamBean tipoExpediente = configuracionExpedienteService.obtenerTipoExpediente(mapParametrosBusqueda.get("codTipExpediente").toString());
			T01ParamBean tipoDcto = configuracionExpedienteService.obtenerTipoDoc(mapParametrosBusqueda.get("codTipDoc").toString(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);

			mapParametrosBusqueda.put("tpoRpt", opciones);
			mapParametrosBusqueda.put("proceso", proceso.getDesParametro());
			mapParametrosBusqueda.put("tpoExdte", tipoExpediente.getDesParametro());
			mapParametrosBusqueda.put("tipoDcto", tipoDcto == null ? ValidaConstantes.CADENA_VACIA : tipoDcto.getDesParametro().trim());

			if (mapParametrosBusqueda.get("codTipExpediente") != null && (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP) || Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_AMD))) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("codTipoExpediente", mapParametrosBusqueda.get("codTipExpediente"));
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				parametros.put("indConsulta", ValidaConstantes.IND_TIPO_DOCU_DISPONIBLE);
				List<T6623TipDocExpBean> listadoTiposDocumentosDisponibles;
				if (!mapParametrosBusqueda.get("codTipDoc").toString().isEmpty()) {
					listadoTiposDocumentosDisponibles = new ArrayList<T6623TipDocExpBean>();
					T6623TipDocExpBean tipDocBean = new T6623TipDocExpBean();
					tipDocBean.setCodTipoDocumento(mapParametrosBusqueda.get("codTipDoc").toString());
					listadoTiposDocumentosDisponibles.add(tipDocBean);
				} else {
					List<String> listIndTipDoc = new ArrayList<String>();
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
					parametros.put("listIndTipDoc", listIndTipDoc);
					parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					listadoTiposDocumentosDisponibles = configuracionExpedienteService.listarTiposDocumentos(parametros);
				}

				mapParametrosBusqueda.put("listadoTiposDocumentosDisponibles", listadoTiposDocumentosDisponibles);
			}

			mapParametrosBusqueda.put("codTipDoc", codTipDocumento == null || codTipDocumento.equals(ValidaConstantes.CADENA_VACIA) ? null : codTipDocumento);

			// validamos tipo de documentos
			//inicio luis Estrada 10/11/2016
			if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP)) {
				atribFileName=exportarRptExpGenPorDepend(mapParametrosBusqueda, mapaDependencias);// 01
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_AMD)) {
				atribFileName=exportarRptExpGenPorFechaGenerac(mapParametrosBusqueda);// 02
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB)) {
				atribFileName=exportarRptExpGenPorDependCantConsult(mapParametrosBusqueda, mapaDependencias);// 03
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB)) {
				atribFileName=exportarRptExpGenPorFechaGeneracCantConsult(mapParametrosBusqueda);// 04
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC)) {
				atribFileName=exportarRptExpdteGenerPorDependConsultados(mapParametrosBusqueda, mapaDependencias);// 05
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM)) {
				atribFileName=exportarRptExpdteCobranzaAcumsPorDependencia(mapParametrosBusqueda, mapaDependencias);// 06
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_DEP_CONT_X_DCTO)) {
				atribFileName=exportarRptCantidadDocumentosPorDependencia(mapParametrosBusqueda, mapaDependencias);// 07
				modelAndView = null;
			}// Inicio [gangles 17/08/2016]
			else if (Utils.equiv(opciones, CatalogoConstantes.RPT_EXP_GEN_X_RESPONSABLES)) {
				atribFileName=exportarRptExpGeneradosPorResponsables(mapParametrosBusqueda);// 08
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_DOC_GEN_X_EXP_DEP)) {// Reporte de Documentos Registrados por Expediente y Dependencia
				atribFileName=exportarRptCantDocumentosPorExpDependencia(mapParametrosBusqueda, mapaDependencias);// 09
				modelAndView = null;
			}// Fin [gangles 17/08/2016]
			 // Inicio [jtejada 22/08/2016]
			else if (Utils.equiv(opciones, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_DEP)) {
				atribFileName=exportarRptRepresImprPorDependencia(mapParametrosBusqueda, mapaDependencias);// 10
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_FEC)) {
				atribFileName=exportarRptRepresImprPorFecha(mapParametrosBusqueda);// 11
				modelAndView = null;
			} else if (Utils.equiv(opciones, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP)) {
				atribFileName=exportarRptRepresImprPorExpdteYDependencia(mapParametrosBusqueda, mapaDependencias);// 12
				modelAndView = null;
			}
			
			
			
			if(!Utils.isEmpty(atribFileName) && !Utils.isEmpty(atribFileName.get("filename"))){
			String fileName=atribFileName.get("filename").toString();
			response.setContentType("application/vnd.ms-excel");
			File downloadFile = new File("/data0/tempo/" + fileName);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			ServletContext context = getServletContext();
			String mimeType = context.getMimeType("/data0/tempo/" + fileName);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			response.setHeader("Content-Length", String.valueOf(new File("/data0/tempo/" + fileName).length()));
			OutputStream outStream = response.getOutputStream();
			IOUtils.copy(inputStream, outStream);
			outStream.flush();
			inputStream.close();
			outStream.close();
			}
			//fin Luis Estrada 10/11/2016
			// Fin [jtejada 22/08/2016]
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaReporteController.generarReportesIndicador");
			}

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - ConsultaReporteController.generarReportesIndicador");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}

	/* GENERACION DE REPORTE DE INDICADORES (EXCEL) */
	// 1
	private Map<String, Object> exportarRptExpGenPorDepend(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpGenPorDepend");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String codTipDoc = (String) mapParametrosBusqueda.get("codTipDoc");

			if (codTipDoc != null && codTipDoc.equals("")) {
				mapParametrosBusqueda.remove("codTipDoc");
			}

			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedienteVirtualDependBean> listaReportes = reportesIndicadoresService.reporteIndicadorDepedencias(mapParametrosBusqueda);

			// Inicio [gangles 16/06/2016] se agrega el conteo por AgrupaciÃƒÂ³n de tipo de documentos,
			List<Map<String, Object>> mapCantTipoDocs = reportesIndicadoresService.cantDocsPorTipo(mapParametrosBusqueda);
			List<Map<String, Object>> mapCantDocsDep = reportesIndicadoresService.cantGrupoDocsPorDepedencias(mapParametrosBusqueda);
			// Fin [gangles 16/06/2016]

			obtenerMapaTipDocsOdendados(tipoRpt, listaReportes, mapCantDocsDep);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;

			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHCantRUC = fila.createCell(1);
			HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
			HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
			HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsXEstad = fila.createCell(5);
			HSSFCell celdaHExpdtsXEstad2 = fila.createCell(6);// Creado solo para visualizar bordes

			celdaHDependencia.setCellValue("DEPENDENCIA");
			rango = new CellRangeAddress(row, row + 1, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHCantRUC.setCellValue("CANTIDAD RUC");
			rango = new CellRangeAddress(row, row + 1, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHCantTotalExpdtsVirt.setCellValue("CANTIDAD EXPEDIENTES VIRTUALES");

			rango = new CellRangeAddress(row, row, 2, 4);
			hoja.addMergedRegion(rango);

			celdaHExpdtsXEstad.setCellValue("EXPEDIENTES POR ESTADO");
			rango = new CellRangeAddress(row, row, 5, 6);
			hoja.addMergedRegion(rango);

			if (!mapCantTipoDocs.isEmpty()) {
				int contCeldaHDinamica = 7;
				HSSFCell celdaHDocs = fila.createCell(7);
				celdaHDocs.setCellValue("DOCUMENTOS CONTENIDOS EN EXPEDIENTES VIRTUALES");
				celdaHDocs.setCellStyle(estiloCeldasHeader);
				rango = new CellRangeAddress(row, row, contCeldaHDinamica, contCeldaHDinamica + mapCantTipoDocs.size() - 1);
				hoja.addMergedRegion(rango);
				// Inicio [gangles 16/06/2016] Obtener de la tabla paramÃƒÂ©trica las descripciones de los Tipos de Documentos agrupados
				for (int i = 0; i < mapCantTipoDocs.size() - 1; i++) {
					HSSFCell celdaHDoc = fila.createCell(++contCeldaHDinamica);// Creado solo para visualizar bordes
					celdaHDoc.setCellStyle(estiloCeldasHeader);
				}
				// Fin [gangles 16/06/2016]
			}

			fila = hoja.createRow(++row);
			HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

			HSSFCell celdaHTotal = fila.createCell(2);
			celdaHTotal.setCellValue("TOTAL");
			HSSFCell celdaHAutomaticos = fila.createCell(3);
			celdaHAutomaticos.setCellValue("AUTOMATICOS");

			HSSFCell celdaHManuales = fila.createCell(4);
			celdaHManuales.setCellValue("MANUALES");

			HSSFCell celdaHAbiertos = fila.createCell(5);
			celdaHAbiertos.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados = fila.createCell(6);
			celdaHCerrados.setCellValue("CERRADOS");

			// Inicio [gangles 16/06/2016]
			if (!mapCantTipoDocs.isEmpty()) {
				int contCelda = 7;
				for (int i = 0; i < mapCantTipoDocs.size(); i++) {
					T01ParamBean paramTipoDoc = configuracionExpedienteService.obtenerTipoDoc(mapCantTipoDocs.get(i).get("codTipoDoc").toString().trim(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_3DIG);
					// String descTipDoc = paramTipoDoc == null ? mapCantDocs.get(i).get("codTipoDoc").toString().trim(): paramTipoDoc.getDesParametro().trim();
					String descTipDoc = "";
					if (!Utils.isEmpty(paramTipoDoc)) {
						descTipDoc = paramTipoDoc.getDesParametro().trim();

					} else {
						descTipDoc = mapCantTipoDocs.get(i).get("codTipoDoc").toString().trim();
					}
					if (mapCantTipoDocs.size() == 1) {
						hoja.setColumnWidth(contCelda, 14000);
					} else {
						hoja.setColumnWidth(contCelda, 7000);
					}
					HSSFCell celdaHDoc = fila.createCell(contCelda++);
					celdaHDoc.setCellValue(descTipDoc);
					celdaHDoc.setCellStyle(estiloCeldasHeader);
				}
			}
			// Fin [gangles 16/06/2016]

			celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
			celdaHTotal.setCellStyle(estiloCeldasHeader);
			celdaHAutomaticos.setCellStyle(estiloCeldasHeader);
			celdaHManuales.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad2.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos.setCellStyle(estiloCeldasHeader);
			celdaHCerrados.setCellStyle(estiloCeldasHeader);

			estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHCantRUC.setCellStyle(estiloCeldasHeader);
			celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedienteVirtualDependBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				texto = new HSSFRichTextString(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellValue(texto.toString());
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// CANT. RUC
				celdaHCantRUC = fila.createCell(1);
				celdaHCantRUC.setCellValue(reg.getCantRuc());
				celdaHCantRUC.setCellStyle(estiloRecorrido);

				// CANT. EXPEDIENTES VIRTUALES
				celdaHCantTotalExpdtsVirt = fila.createCell(2);
				celdaHCantTotalExpdtsVirt.setCellValue(reg.getCantExpedientesAutomaticos() + reg.getCantExpedientesManuales());
				celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

				// CANT. AUTOMATICOS
				celdaHAutomaticos = fila.createCell(3);
				celdaHAutomaticos.setCellValue(reg.getCantExpedientesAutomaticos());
				celdaHAutomaticos.setCellStyle(estiloRecorrido);

				// CANT. MANUALES
				celdaHManuales = fila.createCell(4);
				celdaHManuales.setCellValue(reg.getCantExpedientesManuales());
				celdaHManuales.setCellStyle(estiloRecorrido);

				// CANT. EXPEDIENTES POR ESTADO
				celdaHAbiertos = fila.createCell(5);
				celdaHAbiertos.setCellValue(reg.getCantExpedientesAbiertos());
				celdaHAbiertos.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS
				celdaHCerrados = fila.createCell(6);
				celdaHCerrados.setCellValue(reg.getCantExpedientesCerrados());
				celdaHCerrados.setCellStyle(estiloRecorrido);

				// CELDAS DINÃƒÂMICAS
				// Inicio [gangles 17/06/2016]
				if (!mapCantDocsDep.isEmpty()) {
					int contCelda = 7;
					for (int j = 0; j < mapCantTipoDocs.size(); j++) {
						Map<String, Object> mapDocDep = reg.getMapDocs().get(mapCantTipoDocs.get(j).get("codTipoDoc").toString().trim());
						String cant = mapDocDep == null ? "0" : mapDocDep.get("cant").toString();
						HSSFCell celdaHDoc = fila.createCell(contCelda++);
						celdaHDoc.setCellValue(cant);
						celdaHDoc.setCellStyle(estiloRecorrido);
					}
				}
				// Fin [gangles 17/06/2016]
			}
			establecerAnchoColumnas(hoja, 7);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, CatalogoConstantes.RPT_EXP_GEN_X_DEP.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpGenPorDepend");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpGenPorDepend");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 2
	private Map<String, Object> exportarRptExpGenPorFechaGenerac(Map<String, Object> mapParametrosBusqueda) throws Exception {
		String formatoFechaReq = "dd-MM-yyyy";
		String fecha = null;
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
		List<ReporteIndicadoresExpedienteVirtualFechaGenBean> listaReportes = reportesIndicadoresService.reporteIndicadorFechasGeneracion(mapParametrosBusqueda);
		// List<Map<String, Object>> mapCantDocs = reportesIndicadoresService.cantDocsPorFechaGeneracion(mapParametrosBusqueda);
		// List<T6623TipDocExpBean> listadoTiposDocumentosDisponibles = (List<T6623TipDocExpBean>) mapParametrosBusqueda.get("listadoTiposDocumentosDisponibles");
		// Inicio [gangles 20/06/2016] se agrega el conteo por AgrupaciÃƒÂ³n de tipo de documentos,
		List<Map<String, Object>> mapCantTipoDocs = reportesIndicadoresService.cantDocsPorTipo(mapParametrosBusqueda);
		List<Map<String, Object>> mapCantDocsFechas = reportesIndicadoresService.cantGrupoDocsPorFecha(mapParametrosBusqueda);
		// Fin [gangles 20/06/2016]

		obtenerMapaTipDocsOdendados(tipoRpt, listaReportes, mapCantDocsFechas);

		
		HSSFWorkbook libro = new HSSFWorkbook();
		HSSFSheet hoja = libro.createSheet("Hoja 1");

		HSSFRow fila = hoja.createRow(0);
		HSSFCell tituloCelda = fila.createCell(0);
		CellRangeAddress rango = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.putAll(mapParametrosBusqueda);

		int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

		HSSFFont fuente = libro.createFont();
		fuente.setFontHeightInPoints((short) 9);
		fuente.setFontName(fuente.FONT_ARIAL);
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
		setEstiloCabecera(estiloCeldasHeader, fuente);

		fila = hoja.createRow(++row);
		HSSFCell celdaHFechaGen = fila.createCell(0);
		HSSFCell celdaHCantRUC = fila.createCell(1);
		HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
		HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
		HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
		HSSFCell celdaHExpdtsXEstad = fila.createCell(5);
		HSSFCell celdaHExpdtsXEstad2 = fila.createCell(6);// Creado solo para visualizar bordes

		celdaHFechaGen.setCellValue("FECHA DE GENERACION EXPEDIENTE AÃ‘O / MES / DIA");
		rango = new CellRangeAddress(row, row + 1, 0, 0);
		hoja.addMergedRegion(rango);

		celdaHCantRUC.setCellValue("CANTIDAD RUC");
		rango = new CellRangeAddress(row, row + 1, 1, 1);
		hoja.addMergedRegion(rango);

		celdaHCantTotalExpdtsVirt.setCellValue("CANTIDAD EXPEDIENTES VIRTUALES");

		rango = new CellRangeAddress(row, row, 2, 4);
		hoja.addMergedRegion(rango);

		celdaHExpdtsXEstad.setCellValue("EXPEDIENTES POR ESTADO");
		rango = new CellRangeAddress(row, row, 5, 6);
		hoja.addMergedRegion(rango);

		if (!mapCantTipoDocs.isEmpty()) {
			int contCeldaHDinamica = 7;
			HSSFCell celdaHDocs = fila.createCell(7);
			celdaHDocs.setCellValue("DOCUMENTOS CONTENIDOS EN EXPEDIENTES VIRTUALES");
			celdaHDocs.setCellStyle(estiloCeldasHeader);
			rango = new CellRangeAddress(row, row, contCeldaHDinamica, contCeldaHDinamica + mapCantTipoDocs.size() - 1);
			hoja.addMergedRegion(rango);
			// Inicio [gangles 20/06/2016] Obtener de la tabla paramÃƒÂ©trica las descripciones de los Tipos de Documentos agrupados
			for (int i = 0; i < mapCantTipoDocs.size() - 1; i++) {
				HSSFCell celdaHDoc = fila.createCell(++contCeldaHDinamica);// Creado solo para visualizar bordes
				celdaHDoc.setCellStyle(estiloCeldasHeader);
			}
			// Fin [gangles 20/06/2016]
		}

		/*
		if (!listadoTiposDocumentosDisponibles.isEmpty()) {
			int contCeldaHDinamica = 7;
			HSSFCell celdaHDocs = fila.createCell(7);
			celdaHDocs.setCellValue("DOCUMENTOS CONTENIDOS EN EXPEDIENTES VIRTUALES");
			celdaHDocs.setCellStyle(estiloCeldasHeader);
			rango = new CellRangeAddress(row, row, contCeldaHDinamica, contCeldaHDinamica + listadoTiposDocumentosDisponibles.size() - 1);
			hoja.addMergedRegion(rango);

			for (int i = 0; i < listadoTiposDocumentosDisponibles.size() - 1; i++) {
				HSSFCell celdaHDoc = fila.createCell(++contCeldaHDinamica);// Creado solo para visualizar bordes
				celdaHDoc.setCellStyle(estiloCeldasHeader);
			}

		}*/

		fila = hoja.createRow(++row);
		HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

		HSSFCell celdaHTotal = fila.createCell(2);
		celdaHTotal.setCellValue("TOTAL");
		HSSFCell celdaHAutomaticos = fila.createCell(3);
		celdaHAutomaticos.setCellValue("AUTOMATICOS");

		HSSFCell celdaHManuales = fila.createCell(4);
		celdaHManuales.setCellValue("MANUALES");

		HSSFCell celdaHAbiertos = fila.createCell(5);
		celdaHAbiertos.setCellValue("ABIERTOS");

		HSSFCell celdaHCerrados = fila.createCell(6);
		celdaHCerrados.setCellValue("CERRADOS");

		/*if (!listadoTiposDocumentosDisponibles.isEmpty()) {
			int contCelda = 7;

			for (T6623TipDocExpBean codDoc : listadoTiposDocumentosDisponibles) {
				T01ParamBean paramTipoDoc = configuracionExpedienteService.obtenerTipoDoc(codDoc.getCodTipoDocumento(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				String descTipDoc = paramTipoDoc == null ? codDoc.getCodTipoDocumento() : paramTipoDoc.getDesParametro().trim();
				if (listadoTiposDocumentosDisponibles.size() == 1) {
					hoja.setColumnWidth(contCelda, 14000);
				} else {
					hoja.setColumnWidth(contCelda, descTipDoc.length() > 15 ? 7000 : 5000);
				}
				HSSFCell celdaHDoc = fila.createCell(contCelda++);
				celdaHDoc.setCellValue(descTipDoc);
				celdaHDoc.setCellStyle(estiloCeldasHeader);
			}
		}*/
		// Inicio [gangles 20/06/2016]
		if (!mapCantTipoDocs.isEmpty()) {
			int contCelda = 7;
			for (int i = 0; i < mapCantTipoDocs.size(); i++) {
				T01ParamBean paramTipoDoc = configuracionExpedienteService.obtenerTipoDoc(mapCantTipoDocs.get(i).get("codTipoDoc").toString().trim(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_3DIG);
				// String descTipDoc = paramTipoDoc == null ? mapCantDocs.get(i).get("codTipoDoc").toString().trim(): paramTipoDoc.getDesParametro().trim();
				String descTipDoc = "";
				if (!Utils.isEmpty(paramTipoDoc)) {
					descTipDoc = paramTipoDoc.getDesParametro().trim();

				} else {
					descTipDoc = mapCantTipoDocs.get(i).get("codTipoDoc").toString().trim();
				}
				if (mapCantTipoDocs.size() == 1) {
					hoja.setColumnWidth(contCelda, 14000);
				} else {
					hoja.setColumnWidth(contCelda, 7000);
				}
				HSSFCell celdaHDoc = fila.createCell(contCelda++);
				celdaHDoc.setCellValue(descTipDoc);
				celdaHDoc.setCellStyle(estiloCeldasHeader);
			}
		}
		// Fin [gangles 20/06/2016]

		celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
		celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
		celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
		celdaHTotal.setCellStyle(estiloCeldasHeader);
		celdaHAutomaticos.setCellStyle(estiloCeldasHeader);
		celdaHManuales.setCellStyle(estiloCeldasHeader);
		celdaHExpdtsXEstad.setCellStyle(estiloCeldasHeader);
		celdaHExpdtsXEstad2.setCellStyle(estiloCeldasHeader);
		celdaHAbiertos.setCellStyle(estiloCeldasHeader);
		celdaHCerrados.setCellStyle(estiloCeldasHeader);

		estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		celdaHFechaGen.setCellStyle(estiloCeldasHeader);
		celdaHCantRUC.setCellStyle(estiloCeldasHeader);
		celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

		HSSFCellStyle estiloTitulo = libro.createCellStyle();
		estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		estiloTitulo.setFont(fuente);

		tituloCelda.setCellStyle(estiloTitulo);

		HSSFRichTextString texto;
		HSSFFont fuenteContenido = libro.createFont();
		fuenteContenido.setFontHeightInPoints((short) 8);
		HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
		estiloCeldaContenido.setFont(fuenteContenido);
		HSSFCellStyle estiloRecorrido = libro.createCellStyle();
		setEstiloRecorrido(estiloRecorrido, fuente);
		++row;
		for (int i = 0; i < listaReportes.size(); i++) {
			ReporteIndicadoresExpedienteVirtualFechaGenBean reg = listaReportes.get(i);

			fila = hoja.createRow(i + row);

			// FECHA DE GENERACIÃƒâ€œN
			celdaHFechaGen = fila.createCell(0);
			fecha = Utils.convertirDateToString(reg.getFechaGeneracion(), formatoFechaReq);
			texto = new HSSFRichTextString(fecha);
			celdaHFechaGen.setCellValue(texto.toString());
			celdaHFechaGen.setCellStyle(estiloRecorrido);

			// CANT. RUC
			celdaHCantRUC = fila.createCell(1);
			celdaHCantRUC.setCellValue(reg.getCantRuc());
			celdaHCantRUC.setCellStyle(estiloRecorrido);

			// CANT. EXPEDIENTES VIRTUALES
			celdaHCantTotalExpdtsVirt = fila.createCell(2);
			celdaHCantTotalExpdtsVirt.setCellValue(reg.getCantExpedientesAutomaticos() + reg.getCantExpedientesManuales());
			celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

			// CANT. AUTOMATICOS
			celdaHAutomaticos = fila.createCell(3);
			celdaHAutomaticos.setCellValue(reg.getCantExpedientesAutomaticos());
			celdaHAutomaticos.setCellStyle(estiloRecorrido);

			// CANT. MANUALES
			celdaHManuales = fila.createCell(4);
			celdaHManuales.setCellValue(reg.getCantExpedientesManuales());
			celdaHManuales.setCellStyle(estiloRecorrido);

			// CANT. EXPEDIENTES POR ESTADO
			celdaHAbiertos = fila.createCell(5);
			celdaHAbiertos.setCellValue(reg.getCantExpedientesAbiertos());
			celdaHAbiertos.setCellStyle(estiloRecorrido);

			// CANT. CERRADOS
			celdaHCerrados = fila.createCell(6);
			celdaHCerrados.setCellValue(reg.getCantExpedientesCerrados());
			celdaHCerrados.setCellStyle(estiloRecorrido);

			// CELDAS DINÃƒÂMICAS
			/*if (!listadoTiposDocumentosDisponibles.isEmpty()) {
				int contCelda = 7;
				for (T6623TipDocExpBean codDoc : listadoTiposDocumentosDisponibles) {
					Map<String, Object> mapDocDep = reg.getMapDocs().get(codDoc.getCodTipoDocumento());
					String cant = mapDocDep == null ? "0" : mapDocDep.get("cant").toString();
					HSSFCell celdaHDoc = fila.createCell(contCelda++);
					celdaHDoc.setCellValue(cant);
					celdaHDoc.setCellStyle(estiloRecorrido);
				}
			}*/
			// Inicio [gangles 20/06/2016]
			if (!mapCantDocsFechas.isEmpty()) {
				int contCelda = 7;
				for (int j = 0; j < mapCantTipoDocs.size(); j++) {
					Map<String, Object> mapDocFecha = reg.getMapDocs().get(mapCantTipoDocs.get(j).get("codTipoDoc").toString().trim());
					String cant = mapDocFecha == null ? "0" : mapDocFecha.get("cant").toString();
					HSSFCell celdaHDoc = fila.createCell(contCelda++);
					celdaHDoc.setCellValue(cant);
					celdaHDoc.setCellStyle(estiloRecorrido);
				}
			}
			// Fin [gangles 20/06/2016]

		}
		establecerAnchoColumnas(hoja, 7);
		//inicio luis Estrada 10/11/2016
		atribFileName=outputExcel(libro, CatalogoConstantes.RPT_EXP_GEN_X_DEP.toLowerCase());
		//fin luis Estrada 10/11/2016
		return atribFileName;
	}

	// 3
	private Map<String, Object> exportarRptExpGenPorDependCantConsult(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpGenPorDependCantConsult");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedienteVirtualDependBean> listaReportes = reportesIndicadoresService.reporteIndicadorDepedenciasConsultXUsuIntYContrib(mapParametrosBusqueda);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHCantRUC = fila.createCell(1);
			HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
			HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
			HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsXEstad = fila.createCell(5);
			HSSFCell celdaHExpdtsXEstad2 = fila.createCell(6);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsXEstad3 = fila.createCell(7);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXUsuInt = fila.createCell(8);
			HSSFCell celdaHExpdtsConsultXUsuInt2 = fila.createCell(9);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXUsuInt3 = fila.createCell(10);// Creado solo para visualizar bordes
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHExpdtsConsultXContrib = fila.createCell(11);
			HSSFCell celdaHExpdtsConsultXContrib2 = fila.createCell(12);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXContrib3 = fila.createCell(13);// Creado solo para visualizar bordes
			// Fin [jquispe 27/05/2016]
			celdaHDependencia.setCellValue("DEPENDENCIA");
			rango = new CellRangeAddress(row, row + 1, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHCantRUC.setCellValue("CANTIDAD RUC");
			rango = new CellRangeAddress(row, row + 1, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHCantTotalExpdtsVirt.setCellValue("CANTIDAD EXPEDIENTES VIRTUALES");

			rango = new CellRangeAddress(row, row, 2, 4);
			hoja.addMergedRegion(rango);

			celdaHExpdtsXEstad.setCellValue("EXPEDIENTES POR ESTADO");
			rango = new CellRangeAddress(row, row, 5, 7);
			hoja.addMergedRegion(rango);
			
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			/*
			celdaHExpdtsConsultXUsuInt.setCellValue("EXPEDIENTES CONSULTADOS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);*/
			// Fin [jquispe 27/05/2016]
			
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHExpdtsConsultXUsuInt.setCellValue("EXPEDIENTES CONSULTADOS POR USUARIO INTERNO");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);

			celdaHExpdtsConsultXContrib.setCellValue("EXPEDIENTES CONSULTADOS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 11, 13);
			hoja.addMergedRegion(rango);
			// Fin [jquispe 27/05/2016]
			fila = hoja.createRow(++row);
			HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

			HSSFCell celdaHTotalXOrigen = fila.createCell(2);
			celdaHTotalXOrigen.setCellValue("TOTAL");
			HSSFCell celdaHAutomaticos = fila.createCell(3);
			celdaHAutomaticos.setCellValue("AUTOMATICOS");

			HSSFCell celdaHManuales = fila.createCell(4);
			celdaHManuales.setCellValue("MANUALES");

			HSSFCell celdaHTotalXEstados = fila.createCell(5);
			celdaHTotalXEstados.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos = fila.createCell(6);
			celdaHAbiertos.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados = fila.createCell(7);
			celdaHCerrados.setCellValue("CERRADOS");

			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHTotalConsXUsuInt = fila.createCell(8);
			celdaHTotalConsXUsuInt.setCellValue("TOTAL");

			HSSFCell celdaHAbiertosConsXUsuInt = fila.createCell(9);
			celdaHAbiertosConsXUsuInt.setCellValue("ABIERTOS");

			HSSFCell celdaHCerradosConsXUsuInt = fila.createCell(10);
			celdaHCerradosConsXUsuInt.setCellValue("CERRADOS");
			// Fin [jquispe 27/05/2016]
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHTotalConsXContrib = fila.createCell(11);
			// Fin [jquispe 27/05/2016]
			celdaHTotalConsXContrib.setCellValue("TOTAL");
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHAbiertosConsXContrib = fila.createCell(12);
			// Fin [jquispe 27/05/2016]
			celdaHAbiertosConsXContrib.setCellValue("ABIERTOS");
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHCerradosConsXContrib = fila.createCell(13);
			// Fin [jquispe 27/05/2016]
			celdaHCerradosConsXContrib.setCellValue("CERRADOS");

			celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
			celdaHTotalXOrigen.setCellStyle(estiloCeldasHeader);
			celdaHAutomaticos.setCellStyle(estiloCeldasHeader);
			celdaHManuales.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad3.setCellStyle(estiloCeldasHeader);
			celdaHTotalXEstados.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos.setCellStyle(estiloCeldasHeader);
			celdaHCerrados.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXUsuInt2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXUsuInt3.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXContrib.setCellStyle(estiloCeldasHeader);
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHExpdtsConsultXContrib2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXContrib3.setCellStyle(estiloCeldasHeader);
			celdaHTotalConsXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHAbiertosConsXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHCerradosConsXUsuInt.setCellStyle(estiloCeldasHeader);
			// Fin [jquispe 27/05/2016]
			celdaHTotalConsXContrib.setCellStyle(estiloCeldasHeader);
			celdaHAbiertosConsXContrib.setCellStyle(estiloCeldasHeader);
			celdaHCerradosConsXContrib.setCellStyle(estiloCeldasHeader);

			estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHCantRUC.setCellStyle(estiloCeldasHeader);
			celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedienteVirtualDependBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();
				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				texto = new HSSFRichTextString(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellValue(texto.toString());
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// CANT. RUC
				celdaHCantRUC = fila.createCell(1);
				celdaHCantRUC.setCellValue(reg.getCantRuc());
				celdaHCantRUC.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES VIRTUALES
				celdaHCantTotalExpdtsVirt = fila.createCell(2);
				celdaHCantTotalExpdtsVirt.setCellValue(reg.getCantExpedientesAutomaticos() + reg.getCantExpedientesManuales());
				celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

				// CANT. AUTOMATICOS
				celdaHAutomaticos = fila.createCell(3);
				celdaHAutomaticos.setCellValue(reg.getCantExpedientesAutomaticos());
				celdaHAutomaticos.setCellStyle(estiloRecorrido);

				// CANT. MANUALES
				celdaHManuales = fila.createCell(4);
				celdaHManuales.setCellValue(reg.getCantExpedientesManuales());
				celdaHManuales.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES POR ESTADO
				celdaHTotalXEstados = fila.createCell(5);
				celdaHTotalXEstados.setCellValue(reg.getCantExpedientesAbiertos() + reg.getCantExpedientesCerrados());
				celdaHTotalXEstados.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS
				celdaHAbiertos = fila.createCell(6);
				celdaHAbiertos.setCellValue(reg.getCantExpedientesAbiertos());
				celdaHAbiertos.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS
				celdaHCerrados = fila.createCell(7);
				celdaHCerrados.setCellValue(reg.getCantExpedientesCerrados());
				celdaHCerrados.setCellStyle(estiloRecorrido);
				
				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				/*// CANT. TOTAL EXPEDIENTES CONSULT X CONTRIBUYENTE
				celdaHTotalConsXContrib = fila.createCell(8);
				celdaHTotalConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos() + reg.getCantExpedientesConsultContribCerrados());
				celdaHTotalConsXContrib.setCellStyle(estiloRecorrido);
	
				// CANT. ABIERTOS CONSULT X CONTRIBUYENTE
				celdaHAbiertosConsXContrib = fila.createCell(9);
				celdaHAbiertosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos());
				celdaHAbiertosConsXContrib.setCellStyle(estiloRecorrido);
	
				// CANT. CERRADOS CONSULT X CONTRIBUYENTE
				celdaHCerradosConsXContrib = fila.createCell(10);
				celdaHCerradosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribCerrados());
				celdaHCerradosConsXContrib.setCellStyle(estiloRecorrido);*/
				// Fin [jquispe 27/05/2016]

				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				// CANT. TOTAL EXPEDIENTES CONSULT X USU. INTERNO
				celdaHTotalConsXUsuInt = fila.createCell(8);
				celdaHTotalConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntAbiertos() + reg.getCantExpedientesConsultUsuIntCerrados());
				celdaHTotalConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS CONSULT X USU. INTERNO
				celdaHAbiertosConsXUsuInt = fila.createCell(9);
				celdaHAbiertosConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntAbiertos());
				celdaHAbiertosConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS CONSULT X USU. INTERNO
				celdaHCerradosConsXUsuInt = fila.createCell(10);
				celdaHCerradosConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntCerrados());
				celdaHCerradosConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES CONSULT X CONTRIBUYENTE
				celdaHTotalConsXContrib = fila.createCell(11);
				celdaHTotalConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos() + reg.getCantExpedientesConsultContribCerrados());
				celdaHTotalConsXContrib.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS CONSULT X CONTRIBUYENTE
				celdaHAbiertosConsXContrib = fila.createCell(12);
				celdaHAbiertosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos());
				celdaHAbiertosConsXContrib.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS CONSULT X CONTRIBUYENTE
				celdaHCerradosConsXContrib = fila.createCell(13);
				celdaHCerradosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribCerrados());
				celdaHCerradosConsXContrib.setCellStyle(estiloRecorrido);
				// Fin [jquispe 27/05/2016]
			}
			establecerAnchoColumnas(hoja, 14);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//Fin
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpGenPorDependCantConsult");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpGenPorDependCantConsult");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 4
	private Map<String, Object> exportarRptExpGenPorFechaGeneracCantConsult(Map<String, Object> mapParametrosBusqueda) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpGenPorFechaGeneracCantConsult");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String formatoFechaReq = "dd-MM-yyyy";
			String fecha = null;
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedienteVirtualFechaGenBean> listaReportes = reportesIndicadoresService.reporteIndicadorFechasGeneracionConsultXUsuIntYContrib(mapParametrosBusqueda);
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHFechaGen = fila.createCell(0);
			HSSFCell celdaHCantRUC = fila.createCell(1);
			HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
			HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
			HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsXEstad = fila.createCell(5);
			HSSFCell celdaHExpdtsXEstad2 = fila.createCell(6);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsXEstad3 = fila.createCell(7);// Creado solo para visualizar bordes
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			/*
			HSSFCell celdaHExpdtsConsultXContrib = fila.createCell(8);
			HSSFCell celdaHExpdtsConsultXContrib2 = fila.createCell(9);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXContrib3 = fila.createCell(10);// Creado solo para visualizar bordes
			*/
			// Fin [jquispe 27/05/2016]
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHExpdtsConsultXUsuInt = fila.createCell(8);
			HSSFCell celdaHExpdtsConsultXUsuInt2 = fila.createCell(9);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXUsuInt3 = fila.createCell(10);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXContrib = fila.createCell(11);
			HSSFCell celdaHExpdtsConsultXContrib2 = fila.createCell(12);// Creado solo para visualizar bordes
			HSSFCell celdaHExpdtsConsultXContrib3 = fila.createCell(13);// Creado solo para visualizar bordes
			// Fin [jquispe 27/05/2016]
			celdaHFechaGen.setCellValue("FECHA DE GENERACION EXPEDIENTE AÃ‘O / MES / DIA");
			rango = new CellRangeAddress(row, row + 1, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHCantRUC.setCellValue("CANTIDAD RUC");
			rango = new CellRangeAddress(row, row + 1, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHCantTotalExpdtsVirt.setCellValue("CANTIDAD EXPEDIENTES VIRTUALES");

			rango = new CellRangeAddress(row, row, 2, 4);
			hoja.addMergedRegion(rango);

			celdaHExpdtsXEstad.setCellValue("EXPEDIENTES POR ESTADO");
			rango = new CellRangeAddress(row, row, 5, 7);
			hoja.addMergedRegion(rango);
	
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			/*
			celdaHExpdtsConsultXContrib.setCellValue("EXPEDIENTES CONSULTADOS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);*/
			// Fin [jquispe 27/05/2016]
			
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHExpdtsConsultXUsuInt.setCellValue("EXPEDIENTES CONSULTADOS POR USUARIO INTERNO");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);

			celdaHExpdtsConsultXContrib.setCellValue("EXPEDIENTES CONSULTADOS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 11, 13);
			hoja.addMergedRegion(rango);
			// Fin [jquispe 27/05/2016]

			fila = hoja.createRow(++row);
			HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

			HSSFCell celdaHTotalXOrigen = fila.createCell(2);
			celdaHTotalXOrigen.setCellValue("TOTAL");
			HSSFCell celdaHAutomaticos = fila.createCell(3);
			celdaHAutomaticos.setCellValue("AUTOMATICOS");

			HSSFCell celdaHManuales = fila.createCell(4);
			celdaHManuales.setCellValue("MANUALES");

			HSSFCell celdaHTotalXEstados = fila.createCell(5);
			celdaHTotalXEstados.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos = fila.createCell(6);
			celdaHAbiertos.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados = fila.createCell(7);
			celdaHCerrados.setCellValue("CERRADOS");
	
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			/*
			HSSFCell celdaHTotalConsXContrib = fila.createCell(8);
			celdaHTotalConsXContrib.setCellValue("TOTAL");
	
			HSSFCell celdaHAbiertosConsXContrib = fila.createCell(9);
			celdaHAbiertosConsXContrib.setCellValue("ABIERTOS");
	
			HSSFCell celdaHCerradosConsXContrib = fila.createCell(10);
			celdaHCerradosConsXContrib.setCellValue("CERRADOS");
			*/
			// Fin [jquispe 27/05/2016]
			
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHTotalConsXUsuInt = fila.createCell(8);
			celdaHTotalConsXUsuInt.setCellValue("TOTAL");

			HSSFCell celdaHAbiertosConsXUsuInt = fila.createCell(9);
			celdaHAbiertosConsXUsuInt.setCellValue("ABIERTOS");

			HSSFCell celdaHCerradosConsXUsuInt = fila.createCell(10);
			celdaHCerradosConsXUsuInt.setCellValue("CERRADOS");

			HSSFCell celdaHTotalConsXContrib = fila.createCell(11);
			celdaHTotalConsXContrib.setCellValue("TOTAL");

			HSSFCell celdaHAbiertosConsXContrib = fila.createCell(12);
			celdaHAbiertosConsXContrib.setCellValue("ABIERTOS");

			HSSFCell celdaHCerradosConsXContrib = fila.createCell(13);
			celdaHCerradosConsXContrib.setCellValue("CERRADOS");
			// Fin [jquispe 27/05/2016]

			celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
			celdaHTotalXOrigen.setCellStyle(estiloCeldasHeader);
			celdaHAutomaticos.setCellStyle(estiloCeldasHeader);
			celdaHManuales.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsXEstad3.setCellStyle(estiloCeldasHeader);
			celdaHTotalXEstados.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos.setCellStyle(estiloCeldasHeader);
			celdaHCerrados.setCellStyle(estiloCeldasHeader);
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHExpdtsConsultXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXUsuInt2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXUsuInt3.setCellStyle(estiloCeldasHeader);
			// Fin [jquispe 27/05/2016]
			celdaHExpdtsConsultXContrib.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXContrib2.setCellStyle(estiloCeldasHeader);
			celdaHExpdtsConsultXContrib3.setCellStyle(estiloCeldasHeader);
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHTotalConsXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHAbiertosConsXUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHCerradosConsXUsuInt.setCellStyle(estiloCeldasHeader);
			// Fin [jquispe 27/05/2016]
			celdaHTotalConsXContrib.setCellStyle(estiloCeldasHeader);
			celdaHAbiertosConsXContrib.setCellStyle(estiloCeldasHeader);
			celdaHCerradosConsXContrib.setCellStyle(estiloCeldasHeader);

			estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			celdaHFechaGen.setCellStyle(estiloCeldasHeader);
			celdaHCantRUC.setCellStyle(estiloCeldasHeader);
			celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);
			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedienteVirtualFechaGenBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				// FECHA DE GENERACIÃƒâ€œN
				celdaHFechaGen = fila.createCell(0);
				fecha = Utils.convertirDateToString(reg.getFechaGeneracion(), formatoFechaReq);
				texto = new HSSFRichTextString(fecha);
				celdaHFechaGen.setCellValue(texto.toString());
				celdaHFechaGen.setCellStyle(estiloRecorrido);

				// CANT. RUC
				celdaHCantRUC = fila.createCell(1);
				celdaHCantRUC.setCellValue(reg.getCantRuc());
				celdaHCantRUC.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES VIRTUALES
				celdaHCantTotalExpdtsVirt = fila.createCell(2);
				celdaHCantTotalExpdtsVirt.setCellValue(reg.getCantExpedientesAutomaticos() + reg.getCantExpedientesManuales());
				celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

				// CANT. AUTOMATICOS
				celdaHAutomaticos = fila.createCell(3);
				celdaHAutomaticos.setCellValue(reg.getCantExpedientesAutomaticos());
				celdaHAutomaticos.setCellStyle(estiloRecorrido);

				// CANT. MANUALES
				celdaHManuales = fila.createCell(4);
				celdaHManuales.setCellValue(reg.getCantExpedientesManuales());
				celdaHManuales.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES POR ESTADO
				celdaHTotalXEstados = fila.createCell(5);
				celdaHTotalXEstados.setCellValue(reg.getCantExpedientesAbiertos() + reg.getCantExpedientesCerrados());
				celdaHTotalXEstados.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS
				celdaHAbiertos = fila.createCell(6);
				celdaHAbiertos.setCellValue(reg.getCantExpedientesAbiertos());
				celdaHAbiertos.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS
				celdaHCerrados = fila.createCell(7);
				celdaHCerrados.setCellValue(reg.getCantExpedientesCerrados());
				celdaHCerrados.setCellStyle(estiloRecorrido);
	
				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				/*// CANT. TOTAL EXPEDIENTES CONSULT X CONTRIBUYENTE
				celdaHTotalConsXContrib = fila.createCell(8);
				celdaHTotalConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos() + reg.getCantExpedientesConsultContribCerrados());
				celdaHTotalConsXContrib.setCellStyle(estiloRecorrido);
	
				// CANT. ABIERTOS CONSULT X CONTRIBUYENTE
				celdaHAbiertosConsXContrib = fila.createCell(9);
				celdaHAbiertosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos());
				celdaHAbiertosConsXContrib.setCellStyle(estiloRecorrido);
	
				// CANT. CERRADOS CONSULT X CONTRIBUYENTE
				celdaHCerradosConsXContrib = fila.createCell(10);
				celdaHCerradosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribCerrados());
				celdaHCerradosConsXContrib.setCellStyle(estiloRecorrido);*/
				// Fin [jquispe 27/05/2016]
				
				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				// CANT. TOTAL EXPEDIENTES CONSULT X USU. INTERNO
				celdaHTotalConsXUsuInt = fila.createCell(8);
				celdaHTotalConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntAbiertos() + reg.getCantExpedientesConsultUsuIntCerrados());
				celdaHTotalConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS CONSULT X USU. INTERNO
				celdaHAbiertosConsXUsuInt = fila.createCell(9);
				celdaHAbiertosConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntAbiertos());
				celdaHAbiertosConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS CONSULT X USU. INTERNO
				celdaHCerradosConsXUsuInt = fila.createCell(10);
				celdaHCerradosConsXUsuInt.setCellValue(reg.getCantExpedientesConsultUsuIntCerrados());
				celdaHCerradosConsXUsuInt.setCellStyle(estiloRecorrido);

				// CANT. TOTAL EXPEDIENTES CONSULT X CONTRIBUYENTE
				celdaHTotalConsXContrib = fila.createCell(11);
				celdaHTotalConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos() + reg.getCantExpedientesConsultContribCerrados());
				celdaHTotalConsXContrib.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS CONSULT X CONTRIBUYENTE
				celdaHAbiertosConsXContrib = fila.createCell(12);
				celdaHAbiertosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribAbiertos());
				celdaHAbiertosConsXContrib.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS CONSULT X CONTRIBUYENTE
				celdaHCerradosConsXContrib = fila.createCell(13);
				celdaHCerradosConsXContrib.setCellValue(reg.getCantExpedientesConsultContribCerrados());
				celdaHCerradosConsXContrib.setCellStyle(estiloRecorrido);
				// Fin [jquispe 27/05/2016]
			}
			establecerAnchoColumnas(hoja, 14);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpGenPorFechaGeneracCantConsult");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpGenPorFechaGeneracCantConsult");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 5
	private Map<String, Object> exportarRptExpdteGenerPorDependConsultados(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpdteGenerPorDependConsultados");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String formatoFechaReq = "dd-MM-yyyy";
			String fecha = null;
		
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> listaReportes = reportesIndicadoresService.reporteExpdteGenerPorDependConsultados(mapParametrosBusqueda);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHRUC = fila.createCell(1);
			HSSFCell celdaHNumExdpteOrigen = fila.createCell(2);
			HSSFCell celdaHNumExdpteVirt = fila.createCell(3);
			HSSFCell celdaHProceso = fila.createCell(4);
			HSSFCell celdaHTipoExpediente = fila.createCell(5);
			HSSFCell celdaHFechaGeneracion = fila.createCell(6);
			HSSFCell celdaHOrigen = fila.createCell(7);
			HSSFCell celdaHEstado = fila.createCell(8);
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			HSSFCell celdaHIndicLectUsuInt = fila.createCell(9);
			HSSFCell celdaHFecUltLectUsuInt = fila.createCell(10);
			HSSFCell celdaHIndicLectContrib = fila.createCell(11);
			HSSFCell celdaHFecUltLectContrib = fila.createCell(12);
			// Fin [jquispe 27/05/2016]

			celdaHDependencia.setCellValue("DEPENDENCIA");
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHRUC.setCellValue("RUC");
			celdaHRUC.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteOrigen.setCellValue("NUMERO EXPEDIENTE ORIGEN");
			celdaHNumExdpteOrigen.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteVirt.setCellValue("NUMERO EXPEDIENTE VIRTUAL");
			celdaHNumExdpteVirt.setCellStyle(estiloCeldasHeader);
			celdaHProceso.setCellValue("PROCESO");
			celdaHProceso.setCellStyle(estiloCeldasHeader);
			celdaHTipoExpediente.setCellValue("TIPO DE EXPEDIENTE");
			celdaHTipoExpediente.setCellStyle(estiloCeldasHeader);
			celdaHFechaGeneracion.setCellValue("FECHA DE GENERACION");
			celdaHFechaGeneracion.setCellStyle(estiloCeldasHeader);
			celdaHOrigen.setCellValue("ORIGEN");
			celdaHOrigen.setCellStyle(estiloCeldasHeader);
			celdaHEstado.setCellValue("ESTADO");
			celdaHEstado.setCellStyle(estiloCeldasHeader);
			// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
			celdaHIndicLectUsuInt.setCellValue("INDICADOR DE LECTURA USUARIO INTERNO");
			celdaHIndicLectUsuInt.setCellStyle(estiloCeldasHeader);
			celdaHFecUltLectUsuInt.setCellValue("FECHA ULTIMA LECTURA USUARIO INTERNO");
			celdaHFecUltLectUsuInt.setCellStyle(estiloCeldasHeader);
			// Fin [jquispe 27/05/2016]
			celdaHIndicLectContrib.setCellValue("INDICADOR DE LECTURA CONTRIBUYENTE");
			celdaHIndicLectContrib.setCellStyle(estiloCeldasHeader);
			celdaHFecUltLectContrib.setCellValue("FECHA ULTIMA LECTURA CONTRIBUYENTE");
			celdaHFecUltLectContrib.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			// MAPA DE PROCESOS
			Map<String, Object> paramsProc = new HashMap<String, Object>();
			paramsProc.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			Map<String, Object> mapProcesos = catalogoService.obtenerCatalogo(paramsProc);

			// MAPA DE TIPOS DE EXPEDIENTE
			Map<String, Object> paramsTipExpd = new HashMap<String, Object>();
			paramsTipExpd.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			Map<String, Object> mapTipExps = catalogoService.obtenerCatalogo(paramsTipExpd);

			// MAPA DE TIPOS DE ORIGEN
			Map<String, Object> paramsOrig = new HashMap<String, Object>();
			paramsOrig.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapOrig = catalogoService.obtenerCatalogo(paramsOrig);
			// MAPA DE ESTADOS
			Map<String, Object> paramsEstados = new HashMap<String, Object>();
			paramsEstados.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapEstados = catalogoService.obtenerCatalogo(paramsEstados);

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				celdaHDependencia.setCellValue(reg.getCodDependencia() + " - " + mapaDependencias.get(reg.getCodDependencia().trim()));
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// RUC
				celdaHRUC = fila.createCell(1);
				celdaHRUC.setCellValue(reg.getNumRuc());
				celdaHRUC.setCellStyle(estiloRecorrido);

				// NÃƒÅ¡MERO EXPEDIENTE ORIGEN
				celdaHNumExdpteOrigen = fila.createCell(2);
				celdaHNumExdpteOrigen.setCellValue(reg.getNumExpdteOrigen());
				celdaHNumExdpteOrigen.setCellStyle(estiloRecorrido);

				// NÃƒÅ¡MERO EXPEDIENTE VIRTUAL
				celdaHNumExdpteVirt = fila.createCell(3);
				celdaHNumExdpteVirt.setCellValue(reg.getNumExpdteVirtual());
				celdaHNumExdpteVirt.setCellStyle(estiloRecorrido);

				// PROCESO
				celdaHProceso = fila.createCell(4);
				celdaHProceso.setCellValue(Utils.toStr(mapProcesos.get(reg.getCodProceso())));
				celdaHProceso.setCellStyle(estiloRecorrido);

				// TIPO EXPEDIENTE
				celdaHTipoExpediente = fila.createCell(5);
				celdaHTipoExpediente.setCellValue(Utils.toStr(mapTipExps.get(reg.getCodTipoExpediente())));
				celdaHTipoExpediente.setCellStyle(estiloRecorrido);

				// FECHA DE GENERACIÃƒâ€œN
				celdaHFechaGeneracion = fila.createCell(6);
				fecha = Utils.convertirDateToString(reg.getFechaGeneracion(), formatoFechaReq);
				celdaHFechaGeneracion.setCellValue(fecha);
				celdaHFechaGeneracion.setCellStyle(estiloRecorrido);

				// ORIGEN
				celdaHOrigen = fila.createCell(7);
				celdaHOrigen.setCellValue(Utils.toStr(mapOrig.get(reg.getCodOrigen())));
				celdaHOrigen.setCellStyle(estiloRecorrido);
				
				// ESTADO
				celdaHEstado = fila.createCell(8);
				celdaHEstado.setCellValue(Utils.toStr(mapEstados.get(reg.getCodEstado())));
				celdaHEstado.setCellStyle(estiloRecorrido);

				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				// INDICADOR DE LECTURA DE USUARIO INTERNO
				celdaHIndicLectUsuInt = fila.createCell(9);
				celdaHIndicLectUsuInt.setCellValue(reg.getIndicLectUsuInt());
				celdaHIndicLectUsuInt.setCellStyle(estiloRecorrido);

				// FECHA ULTIMA LECTURA USUARIO INTERNO
				celdaHFecUltLectUsuInt = fila.createCell(10);
				fecha = Utils.convertirDateToString(reg.getFecUltLecUsuInt(), formatoFechaReq);
				if (fecha.equals("01-01-0001")) {
					fecha = "-";
				}

				celdaHFecUltLectUsuInt.setCellValue(fecha);
				celdaHFecUltLectUsuInt.setCellStyle(estiloRecorrido);
				// Fin [jquispe 27/05/2016]

				// INDICADOR DE LECTURA DE CONTRIBUYENTE
				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				celdaHIndicLectContrib = fila.createCell(11);
				// Fin [jquispe 27/05/2016]
				celdaHIndicLectContrib.setCellValue(reg.getIndicLectContrib());
				celdaHIndicLectContrib.setCellStyle(estiloRecorrido);

				// FECHA ULTIMA LECTURA CONTRIBUYENTE
				// Inicio [jquispe 27/05/2016] Modificado para mostrar las columnas del Usuario Interno.
				celdaHFecUltLectContrib = fila.createCell(12);
				// Fin [jquispe 27/05/2016]
				fecha = Utils.convertirDateToString(reg.getFecUltLecContrib(), formatoFechaReq);
				if (fecha.equals("01-01-0001")) {
					fecha = "-";
				}

				celdaHFecUltLectContrib.setCellValue(fecha);
				celdaHFecUltLectContrib.setCellStyle(estiloRecorrido);
			}
			establecerAnchoColumnas(hoja, 13);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpdteGenerPorDependConsultados");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpdteGenerPorDependConsultados");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 6
	private Map<String, Object> exportarRptExpdteCobranzaAcumsPorDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpdteCobranzaAcumsPorDependencia");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> listaReportes = reportesIndicadoresService.reporteExpdtesCobranzaAcumPorDependencia(mapParametrosBusqueda);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHRUC = fila.createCell(1);
			HSSFCell celdaHNumExpdteOrigen = fila.createCell(2);
			HSSFCell celdaHIndicAcum = fila.createCell(3);
			HSSFCell celdaHExpdteRelac = fila.createCell(4);
			HSSFCell celdaHCantDocumentos = fila.createCell(5);
			HSSFCell celdaHOrigenExpdte = fila.createCell(6);

			celdaHDependencia.setCellValue("DEPENDENCIA");
			celdaHDependencia.setCellStyle(estiloCeldasHeader);

			celdaHRUC.setCellValue("RUC");
			celdaHRUC.setCellStyle(estiloCeldasHeader);
			celdaHNumExpdteOrigen.setCellValue("NUMERO EXPEDIENTE ORIGEN");
			celdaHNumExpdteOrigen.setCellStyle(estiloCeldasHeader);
			celdaHIndicAcum.setCellValue("INDICADOR DE ACUMULACION");
			celdaHIndicAcum.setCellStyle(estiloCeldasHeader);
			celdaHExpdteRelac.setCellValue("EXPEDIENTE RELACIONADO");
			celdaHExpdteRelac.setCellStyle(estiloCeldasHeader);
			celdaHCantDocumentos.setCellValue("CANTIDAD DE DOCUMENTOS EXPEDIENTE VIRTUAL");
			celdaHCantDocumentos.setCellStyle(estiloCeldasHeader);
			celdaHOrigenExpdte.setCellValue("ORIGEN DEL EXPEDIENTE");
			celdaHOrigenExpdte.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			// SE OBTIENE DESCRIPCION DE ORIGEN DEL EXPEDIENTE
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaOrigenExpediente = catalogoService.obtenerCatalogo(mapa);

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				celdaHDependencia.setCellValue(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// RUC
				celdaHRUC = fila.createCell(1);
				celdaHRUC.setCellValue(reg.getNumRuc());
				celdaHRUC.setCellStyle(estiloRecorrido);

				// NUMERO EXPEDIENTE ORIGEN
				celdaHNumExpdteOrigen = fila.createCell(2);
				celdaHNumExpdteOrigen.setCellValue(reg.getNumExpdteOrigen());
				celdaHNumExpdteOrigen.setCellStyle(estiloRecorrido);

				// INDICADOR DE ACUMULACIÃƒâ€œN
				celdaHIndicAcum = fila.createCell(3);
				celdaHIndicAcum.setCellValue(hmIndicadoresAcumulacion.get(reg.getIndicAcum()));
				celdaHIndicAcum.setCellStyle(estiloRecorrido);

				// EXPEDIENTE RELACIONADO
				celdaHExpdteRelac = fila.createCell(4);
				celdaHExpdteRelac.setCellValue(reg.getExpdteRelac());
				celdaHExpdteRelac.setCellStyle(estiloRecorrido);

				// CANTIDAD DE DOCUMENTOS
				celdaHCantDocumentos = fila.createCell(5);
				celdaHCantDocumentos.setCellValue(reg.getCantDocsExpdteVirt());
				celdaHCantDocumentos.setCellStyle(estiloRecorrido);

				// ORIGEN DEL EXPEDIENTE
				celdaHOrigenExpdte = fila.createCell(6);
				celdaHOrigenExpdte.setCellValue(Utils.toStr(mapaOrigenExpediente.get(reg.getCodOrigenExpdte())));
				celdaHOrigenExpdte.setCellStyle(estiloRecorrido);
			}
			establecerAnchoColumnas(hoja, 7);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpdteCobranzaAcumsPorDependencia");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpdteCobranzaAcumsPorDependencia");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 7
	private Map<String, Object> exportarRptCantidadDocumentosPorDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptCantidadDocumentosPorDependencia");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();

			List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> listaReportes = reportesIndicadoresService.reporteCantidadDocumentosPorDependencia(mapParametrosBusqueda);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHTipoDocumento = fila.createCell(1);
			HSSFCell celdaHDescDocumento = fila.createCell(2);
			HSSFCell celdaHCantDocumentos = fila.createCell(3);

			celdaHDependencia.setCellValue("DEPENDENCIA");
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHTipoDocumento.setCellValue("TIPO DE DOCUMENTO");
			celdaHTipoDocumento.setCellStyle(estiloCeldasHeader);
			celdaHDescDocumento.setCellValue("DESCRIPCIÃ“N DEL DOCUMENTO");
			celdaHDescDocumento.setCellStyle(estiloCeldasHeader);
			celdaHCantDocumentos.setCellValue("CANTIDAD DE DOCUMENTOS");
			celdaHCantDocumentos.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			// SE OBTIENE DESCRIPCION DE ORIGEN DEL EXPEDIENTE
			Map<String, Object> paramTipDoc = new HashMap<String, Object>();
			paramTipDoc.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			paramTipDoc.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			Map<String, Object> mapaTipDoc = new HashMap<String, Object>();
			Map<String, Object> mapaTipDocTemp = catalogoService.obtenerCatalogo(paramTipDoc);

			if (mapaTipDocTemp != null) {
				mapaTipDoc.putAll(mapaTipDocTemp);
			}

			paramTipDoc = new HashMap<String, Object>();
			paramTipDoc.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			paramTipDoc.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			mapaTipDocTemp = catalogoService.obtenerCatalogo(paramTipDoc);

			if (mapaTipDocTemp != null) {
				mapaTipDoc.putAll(mapaTipDocTemp);
			}

			paramTipDoc = new HashMap<String, Object>();
			paramTipDoc.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			paramTipDoc.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			mapaTipDocTemp = catalogoService.obtenerCatalogo(paramTipDoc);

			if (mapaTipDocTemp != null) {
				mapaTipDoc.putAll(mapaTipDocTemp);
			}

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				celdaHDependencia.setCellValue(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// TIPO DOCUMENTO
				celdaHTipoDocumento = fila.createCell(1);
				celdaHTipoDocumento.setCellValue(reg.getCodTipoDoc());
				celdaHTipoDocumento.setCellStyle(estiloRecorrido);

				// DESCRIPCION DOCUMENTO
				celdaHDescDocumento = fila.createCell(2);
				celdaHDescDocumento.setCellValue(Utils.toStr(mapaTipDoc.get(reg.getCodTipoDoc())));
				celdaHDescDocumento.setCellStyle(estiloRecorrido);

				// CANTIDAD DOCUMENTO
				celdaHCantDocumentos = fila.createCell(3);
				celdaHCantDocumentos.setCellValue(reg.getCantidadDocumentos());
				celdaHCantDocumentos.setCellStyle(estiloRecorrido);
			}
			establecerAnchoColumnas(hoja, 4);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptCantidadDocumentosPorDependencia");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptCantidadDocumentosPorDependencia");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	// 8
	private Map<String, Object> exportarRptExpGeneradosPorResponsables(Map<String, Object> mapParametrosBusqueda) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptExpGeneradosPorResponsables");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String formatoFechaReq = "dd-MM-yyyy";
			String fecha = null;
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresExpedientesPorResponsableBean> listaReportes = reportesIndicadoresService.reporteRptExpGeneradosPorResponsables(mapParametrosBusqueda);
			Map<String, Object> parametros = new HashMap<String, Object>();

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHResponsables = fila.createCell(0);
			HSSFCell celdaHFechaRegResponsable = fila.createCell(1);
			HSSFCell celdaHDependencia = fila.createCell(2);
			HSSFCell celdaHRUC = fila.createCell(3);
			HSSFCell celdaHNumExdpteOrigen = fila.createCell(4);
			HSSFCell celdaHNumExdpteVirt = fila.createCell(5);
			HSSFCell celdaHProceso = fila.createCell(6);
			HSSFCell celdaHTipoExpediente = fila.createCell(7);
			HSSFCell celdaHFecEmisionExpOrigen = fila.createCell(8);
			HSSFCell celdaHFecNotExpOrigen = fila.createCell(9);
			HSSFCell celdaHFechaGeneracion = fila.createCell(10);
			HSSFCell celdaHOrigen = fila.createCell(11);
			HSSFCell celdaHEstado = fila.createCell(12);

			celdaHResponsables.setCellValue("RESPONSABLES");
			celdaHResponsables.setCellStyle(estiloCeldasHeader);
			celdaHFechaRegResponsable.setCellValue("FECHA DE REGISTRO DEL RESPONSABLE");
			celdaHFechaRegResponsable.setCellStyle(estiloCeldasHeader);
			celdaHDependencia.setCellValue("DEPENDENCIA");
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHRUC.setCellValue("RUC");
			celdaHRUC.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteOrigen.setCellValue("NUMERO EXPEDIENTE ORIGEN");
			celdaHNumExdpteOrigen.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteVirt.setCellValue("NUMERO EXPEDIENTE VIRTUAL");
			celdaHNumExdpteVirt.setCellStyle(estiloCeldasHeader);
			celdaHProceso.setCellValue("PROCESO");
			celdaHProceso.setCellStyle(estiloCeldasHeader);
			celdaHTipoExpediente.setCellValue("TIPO DE EXPEDIENTE");
			celdaHTipoExpediente.setCellStyle(estiloCeldasHeader);
			celdaHFecEmisionExpOrigen.setCellValue("FECHA DE EMISION EXPEDIENTE DE ORIGEN");
			celdaHFecEmisionExpOrigen.setCellStyle(estiloCeldasHeader);
			celdaHFecNotExpOrigen.setCellValue("FECHA DE NOTIFICACION EXPEDIENTE DE ORIGEN");
			celdaHFecNotExpOrigen.setCellStyle(estiloCeldasHeader);
				celdaHFechaGeneracion.setCellValue("FECHA DE GENERACION EXPEDIENTE VIRTUAL");
			celdaHFechaGeneracion.setCellStyle(estiloCeldasHeader);
			celdaHOrigen.setCellValue("ORIGEN");
			celdaHOrigen.setCellStyle(estiloCeldasHeader);
			celdaHEstado.setCellValue("ESTADO");
			celdaHEstado.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			//Inicio - [oachahuic][PAS20175E210400016]
			String fecEmiExp = "";
			String fecNotifExp = "";
			//Fin - [oachahuic][PAS20175E210400016]
			
			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresExpedientesPorResponsableBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				// RESPONSABLE

				celdaHResponsables = fila.createCell(0);
				celdaHResponsables.setCellValue(reg.getCodResponsable() + " - " + reg.getNombreResponsable());
				celdaHResponsables.setCellStyle(estiloRecorrido);

				// FECHA DE REGISTRO DEL RESPONSABLE
				celdaHFechaRegResponsable = fila.createCell(1);
				fecha = Utils.convertirDateToString(reg.getFecRegistroResponsable(), formatoFechaReq);
				celdaHFechaRegResponsable.setCellValue(fecha);
				celdaHFechaRegResponsable.setCellStyle(estiloRecorrido);

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(2);
				celdaHDependencia.setCellValue(reg.getCodDependencia() + " - " + reg.getDesDependencia());
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// RUC
				celdaHRUC = fila.createCell(3);
				celdaHRUC.setCellValue(reg.getNumRuc());
				celdaHRUC.setCellStyle(estiloRecorrido);

				// NÃƒÅ¡MERO EXPEDIENTE ORIGEN
				celdaHNumExdpteOrigen = fila.createCell(4);
				celdaHNumExdpteOrigen.setCellValue(reg.getNumExpdteOrigen());
				celdaHNumExdpteOrigen.setCellStyle(estiloRecorrido);

				// NÃƒÅ¡MERO EXPEDIENTE VIRTUAL
				celdaHNumExdpteVirt = fila.createCell(5);
				celdaHNumExdpteVirt.setCellValue(reg.getNumExpdteVirtual());
				celdaHNumExdpteVirt.setCellStyle(estiloRecorrido);

				// PROCESO
				celdaHProceso = fila.createCell(6);
				celdaHProceso.setCellValue(reg.getDesProceso());
				celdaHProceso.setCellStyle(estiloRecorrido);

				// TIPO EXPEDIENTE
				celdaHTipoExpediente = fila.createCell(7);
				celdaHTipoExpediente.setCellValue(reg.getDesTipoExpediente());
				celdaHTipoExpediente.setCellStyle(estiloRecorrido);

				// FECHA DE EMISION EXPEDIENTE DE ORIGEN
				if (Utils.esFechaVacia(reg.getFecEmisionExpOrigen())) {
					fecEmiExp = ValidaConstantes.CADENA_VACIA;
				} else {
					fecEmiExp = Utils.convertirDateToString(reg.getFecEmisionExpOrigen(), formatoFechaReq);
				}
				celdaHFecEmisionExpOrigen = fila.createCell(8);
				celdaHFecEmisionExpOrigen.setCellValue(fecEmiExp);
				celdaHFecEmisionExpOrigen.setCellStyle(estiloRecorrido);

				// FECHA DE NOTIFICACION EXPEDIENTE DE ORIGEN
				if (Utils.esFechaVacia(reg.getFecNotificacionExpOrigen())) {
					fecNotifExp = ValidaConstantes.CADENA_VACIA;
				} else {
					fecNotifExp = Utils.convertirDateToString(reg.getFecNotificacionExpOrigen(), formatoFechaReq);
				}
				celdaHFecNotExpOrigen = fila.createCell(9);
				celdaHFecNotExpOrigen.setCellValue(fecNotifExp);
				celdaHFecNotExpOrigen.setCellStyle(estiloRecorrido);

				// FECHA DE GENERACIÃƒâ€œN
				celdaHFechaGeneracion = fila.createCell(10);
				fecha = Utils.convertirDateToString(reg.getFechaGeneracion(), formatoFechaReq);
				celdaHFechaGeneracion.setCellValue(fecha);
				celdaHFechaGeneracion.setCellStyle(estiloRecorrido);

				// ORIGEN
				celdaHOrigen = fila.createCell(11);
				celdaHOrigen.setCellValue(reg.getDesOrigen());
				celdaHOrigen.setCellStyle(estiloRecorrido);

				// ESTADO
				celdaHEstado = fila.createCell(12);
				celdaHEstado.setCellValue(reg.getDesEstado());
				celdaHEstado.setCellStyle(estiloRecorrido);
			}
			establecerAnchoColumnas(hoja, 13);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptExpGeneradosPorResponsables");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptExpGeneradosPorResponsables");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;

	}
	 
	//9
	private Map<String, Object> exportarRptCantDocumentosPorExpDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptCantDocumentosPorExpDependencia");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String formatoFechaReq = "dd-MM-yyyy";
			String fecha = null;
			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresDocumentosExpedientesPorDepBean> listaReportes = reportesIndicadoresService.reporteRptCantDocumentosPorExpDependencia(mapParametrosBusqueda, mapaDependencias);
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");
	
			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);
	
			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);
	
			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	
			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);
	
			fila = hoja.createRow(++row);
			
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHRUC = fila.createCell(1);
			HSSFCell celdaHNumExdpteOrigen = fila.createCell(2);
			HSSFCell celdaHNumExdpteVirt = fila.createCell(3);
			HSSFCell celdaHProceso = fila.createCell(4);
			HSSFCell celdaHTipoExpediente = fila.createCell(5);
			HSSFCell celdaHFecEmisionExpOrigen = fila.createCell(6);
			HSSFCell celdaHFecNotExpOrigen = fila.createCell(7);
			HSSFCell celdaHFechaGeneracion = fila.createCell(8);
			HSSFCell celdaHOrigen = fila.createCell(9);
			HSSFCell celdaHEstado = fila.createCell(10);
			HSSFCell celdaHTipoDocumento = fila.createCell(11);	
			HSSFCell celdaHDesTipoDocumento = fila.createCell(12);	
			HSSFCell celdaHNumDocumento = fila.createCell(13);	
			HSSFCell celdaHFecEmisionDocumento = fila.createCell(14);
			HSSFCell celdaHFecNotDocumento = fila.createCell(15);
			HSSFCell celdaHFecRegDocumento = fila.createCell(16);	
			
			celdaHDependencia.setCellValue("DEPENDENCIA");
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHRUC.setCellValue("RUC");
			celdaHRUC.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteOrigen.setCellValue("NUMERO EXPEDIENTE ORIGEN");
			celdaHNumExdpteOrigen.setCellStyle(estiloCeldasHeader);
			celdaHNumExdpteVirt.setCellValue("NUMERO EXPEDIENTE VIRTUAL");
			celdaHNumExdpteVirt.setCellStyle(estiloCeldasHeader);
			celdaHProceso.setCellValue("PROCESO");
			celdaHProceso.setCellStyle(estiloCeldasHeader);
			celdaHTipoExpediente.setCellValue("TIPO DE EXPEDIENTE");
			celdaHTipoExpediente.setCellStyle(estiloCeldasHeader);
			celdaHFecEmisionExpOrigen.setCellValue("FECHA DE EMISION EXPEDIENTE DE ORIGEN");
			celdaHFecEmisionExpOrigen.setCellStyle(estiloCeldasHeader);
			celdaHFecNotExpOrigen.setCellValue("FECHA DE NOTIFICACION EXPEDIENTE DE ORIGEN");
			celdaHFecNotExpOrigen.setCellStyle(estiloCeldasHeader);
			celdaHFechaGeneracion.setCellValue("FECHA DE GENERACION EXPEDIENTE VIRTUAL");
			celdaHFechaGeneracion.setCellStyle(estiloCeldasHeader);
			celdaHOrigen.setCellValue("ORIGEN");
			celdaHOrigen.setCellStyle(estiloCeldasHeader);
			celdaHEstado.setCellValue("ESTADO");
			celdaHEstado.setCellStyle(estiloCeldasHeader);
			celdaHTipoDocumento.setCellValue("TIPO DE DOCUMENTO");
			celdaHTipoDocumento.setCellStyle(estiloCeldasHeader);
			celdaHDesTipoDocumento.setCellValue("DESCRIPCIÃ“N TIPO DE DOCUMENTO");
			celdaHDesTipoDocumento.setCellStyle(estiloCeldasHeader);
			celdaHNumDocumento.setCellValue("NUMERO DOCUMENTO");
			celdaHNumDocumento.setCellStyle(estiloCeldasHeader);
			celdaHFecEmisionDocumento.setCellValue("FECHA DE EMISIÃ“N DEL DOCUMENTO");
			celdaHFecEmisionDocumento.setCellStyle(estiloCeldasHeader);
			celdaHFecNotDocumento.setCellValue("FECHA DE NOTIFICACIÃ“N DEL DOCUMENTO");				
			celdaHFecNotDocumento.setCellStyle(estiloCeldasHeader);
			celdaHFecRegDocumento.setCellValue("FECHA DE REGISTRO EN EL EXPEDIENTE VIRTUAL");				
			celdaHFecRegDocumento.setCellStyle(estiloCeldasHeader);
	
			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);
	
			tituloCelda.setCellStyle(estiloTitulo);
	
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);
			
			//Inicio - [oachahuic][PAS20175E210400016]
			String fecEmiExp = "";
			String fecNotifExp = "";
			String fecEmiDoc = "";
			String fecNotifDoc = "";
			//Fin - [oachahuic][PAS20175E210400016]
			
			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresDocumentosExpedientesPorDepBean reg = listaReportes.get(i);
	
				fila = hoja.createRow(i + row);
				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				celdaHDependencia.setCellValue(reg.getCodDependencia() + " - " + reg.getDesDependencia());
				celdaHDependencia.setCellStyle(estiloRecorrido);
	
				// RUC
				celdaHRUC = fila.createCell(1);
				celdaHRUC.setCellValue(reg.getNumRuc());
				celdaHRUC.setCellStyle(estiloRecorrido);
	
				// NÃƒÅ¡MERO EXPEDIENTE ORIGEN
				celdaHNumExdpteOrigen = fila.createCell(2);
				celdaHNumExdpteOrigen.setCellValue(reg.getNumExpdteOrigen());
				celdaHNumExdpteOrigen.setCellStyle(estiloRecorrido);
	
				// NÃƒÅ¡MERO EXPEDIENTE VIRTUAL
				celdaHNumExdpteVirt = fila.createCell(3);
				celdaHNumExdpteVirt.setCellValue(reg.getNumExpdteVirtual());
				celdaHNumExdpteVirt.setCellStyle(estiloRecorrido);
	
				// PROCESO
				celdaHProceso = fila.createCell(4);
				celdaHProceso.setCellValue(reg.getDesProceso());
				celdaHProceso.setCellStyle(estiloRecorrido);
	
				// TIPO EXPEDIENTE
				celdaHTipoExpediente = fila.createCell(5);
				celdaHTipoExpediente.setCellValue(reg.getDesTipoExpediente());
				celdaHTipoExpediente.setCellStyle(estiloRecorrido);
				
				// FECHA DE EMISION EXPEDIENTE DE ORIGEN
				//Inicio - [oachahuic][PAS20175E210400016]
				if (reg.getFecEmisionExpOrigen() != null) {
					if (Utils.esFechaVacia(reg.getFecEmisionExpOrigen())) {
						fecEmiExp = ValidaConstantes.CADENA_VACIA;
					} else {
						fecEmiExp = Utils.convertirDateToString(reg.getFecEmisionExpOrigen(), formatoFechaReq);	
					}
				}
				//Fin - [oachahuic][PAS20175E210400016]
				celdaHFecEmisionExpOrigen = fila.createCell(6);
				celdaHFecEmisionExpOrigen.setCellValue(fecEmiExp);
				celdaHFecEmisionExpOrigen.setCellStyle(estiloRecorrido);
				
				//FECHA DE NOTIFICACION EXPEDIENTE DE ORIGEN
				//Inicio - [oachahuic][PAS20175E210400016]
				if (reg.getFecNotificacionExpOrigen() != null) {
					if (Utils.esFechaVacia(reg.getFecNotificacionExpOrigen())) {
						fecNotifExp = ValidaConstantes.CADENA_VACIA;
					} else {
						fecNotifExp = Utils.convertirDateToString(reg.getFecNotificacionExpOrigen(), formatoFechaReq);
					}
				}
				//Fin - [oachahuic][PAS20175E210400016]
				celdaHFecNotExpOrigen = fila.createCell(7);
				celdaHFecNotExpOrigen.setCellValue(fecNotifExp);
				celdaHFecNotExpOrigen.setCellStyle(estiloRecorrido);
				
				// FECHA DE GENERACIÃƒâ€œN
				celdaHFechaGeneracion = fila.createCell(8);
				fecha = Utils.convertirDateToString(reg.getFechaGeneracion(), formatoFechaReq);
				celdaHFechaGeneracion.setCellValue(fecha);
				celdaHFechaGeneracion.setCellStyle(estiloRecorrido);
	
				// ORIGEN
				celdaHOrigen = fila.createCell(9);
				celdaHOrigen.setCellValue(reg.getDesOrigen());
				celdaHOrigen.setCellStyle(estiloRecorrido);
	
				// ESTADO
				celdaHEstado = fila.createCell(10);
				celdaHEstado.setCellValue(reg.getDesEstado());
				celdaHEstado.setCellStyle(estiloRecorrido);	
				
				// TIPO DE DOCUMENTO
				celdaHTipoDocumento = fila.createCell(11);
				celdaHTipoDocumento.setCellValue(reg.getCodTipoDocumento());
				celdaHTipoDocumento.setCellStyle(estiloRecorrido);
				
				// DESCRIPCION DEL TIPO DE DOCUMENTO
				celdaHDesTipoDocumento = fila.createCell(12);
				celdaHDesTipoDocumento.setCellValue(reg.getDesTipoDocumento());
				celdaHDesTipoDocumento.setCellStyle(estiloRecorrido);
				
				// NUMERO DE DOCUMENTO
				celdaHNumDocumento = fila.createCell(13);
				celdaHNumDocumento.setCellValue(reg.getNumDocumento());
				celdaHNumDocumento.setCellStyle(estiloRecorrido);
				
				// FECHA DE EMISIÃƒâ€œN DEL DOCUMENTO
				//Inicio - [oachahuic][PAS20175E210400016]
				if (Utils.esFechaVacia(reg.getFecEmisionDocumento())) {
					fecEmiDoc = ValidaConstantes.CADENA_VACIA;
				} else {
					fecEmiDoc = Utils.convertirDateToString(reg.getFecEmisionDocumento(), formatoFechaReq);
				}
				//Fin - [oachahuic][PAS20175E210400016]
				celdaHFecEmisionDocumento = fila.createCell(14);
				celdaHFecEmisionDocumento.setCellValue(fecEmiDoc);
				celdaHFecEmisionDocumento.setCellStyle(estiloRecorrido);
				
				// FECHA DE NOTIFICACIÃƒâ€œN DEL DOCUMENTO
				//Inicio - [oachahuic][PAS20175E210400016]
				if (Utils.esFechaVacia(reg.getFecNotificacionDocumento())) {
					fecNotifDoc = ValidaConstantes.CADENA_VACIA;
				} else {
					fecNotifDoc = Utils.convertirDateToString(reg.getFecNotificacionDocumento(), formatoFechaReq);
				}
				//Fin - [oachahuic][PAS20175E210400016]
				celdaHFecNotDocumento = fila.createCell(15);
				celdaHFecNotDocumento.setCellValue(fecNotifDoc);
				celdaHFecNotDocumento.setCellStyle(estiloRecorrido);
				
				// FECHA DE REGISTRO DEL DOCUMENTO EN EL EXPEDIENTE VIRTUAL
				celdaHFecRegDocumento = fila.createCell(16);
				fecha = Utils.convertirDateToString(reg.getFecRegistroDocExpVirt(), formatoFechaReq);
				celdaHFecRegDocumento.setCellValue(fecha);
				celdaHFecRegDocumento.setCellStyle(estiloRecorrido);					
			}
			establecerAnchoColumnas(hoja, 14);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, tipoRpt.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptCantDocumentosPorExpDependencia");
			}
			log.error(ex, ex);			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptCantDocumentosPorExpDependencia");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	private Map<String, Object> exportarRptRepresImprPorDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {// 10
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptRepresImprPorDependencia");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//inicio luis Estrada 10/11/2016
		try {
			String codTipDoc = (String) mapParametrosBusqueda.get("codTipDoc");

			if (codTipDoc != null && codTipDoc.equals("")) {
				mapParametrosBusqueda.remove("codTipDoc");
			}

			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresRepresImprPorDepBean> listaReportes = reportesIndicadoresService.reporteIndicadorRepresImprPorDepend(mapParametrosBusqueda);

			List<Map<String, Object>> mapCantTipoDocs = new ArrayList<Map<String, Object>>();// reportesIndicadoresService.cantDocsPorTipo(mapParametrosBusqueda);
			List<Map<String, Object>> mapCantDocsDep = new ArrayList<Map<String, Object>>(); // reportesIndicadoresService.cantGrupoDocsPorDepedencias(mapParametrosBusqueda);

//			obtenerMapaTipDocsOdendados(tipoRpt, listaReportes, mapCantDocsDep);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;

			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHCantRUC = fila.createCell(1);
			HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
			HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
			HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGener = fila.createCell(5);
			HSSFCell celdaHCantRepreImprGener2 = fila.createCell(6);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGener3 = fila.createCell(7);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGenerXContrib = fila.createCell(8);
			HSSFCell celdaHCantRepreImprGenerXContrib2 = fila.createCell(9);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGenerXContrib3 = fila.createCell(10);// Creado solo para visualizar bordes

			celdaHDependencia.setCellValue("DEPENDENCIA");
			rango = new CellRangeAddress(row, row + 1, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHCantRUC.setCellValue("CANTIDAD RUC");
			rango = new CellRangeAddress(row, row + 1, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHCantTotalExpdtsVirt.setCellValue("EXPEDIENTES POR ESTADO");

			rango = new CellRangeAddress(row, row, 2, 4);
			hoja.addMergedRegion(rango);

			celdaHCantRepreImprGener.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS POR USUARIOS INTERNOS");
			rango = new CellRangeAddress(row, row, 5, 7);
			hoja.addMergedRegion(rango);

			celdaHCantRepreImprGenerXContrib.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);

			fila = hoja.createRow(++row);
			HSSFCell celdaDependBottom = fila.createCell(0);// Creado solo para visualizar bordes
			HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

			HSSFCell celdaHTotal = fila.createCell(2);
			celdaHTotal.setCellValue("TOTAL");
			HSSFCell celdaHAbiertos = fila.createCell(3);
			celdaHAbiertos.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados = fila.createCell(4);
			celdaHCerrados.setCellValue("CERRADOS");

			HSSFCell celdaHTotal2 = fila.createCell(5);
			celdaHTotal2.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos2 = fila.createCell(6);
			celdaHAbiertos2.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados2 = fila.createCell(7);
			celdaHCerrados2.setCellValue("CERRADOS");

			HSSFCell celdaHTotal3 = fila.createCell(8);
			celdaHTotal3.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos3 = fila.createCell(9);
			celdaHAbiertos3.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados3 = fila.createCell(10);
			celdaHCerrados3.setCellValue("CERRADOS");

			celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
			celdaHTotal.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos.setCellStyle(estiloCeldasHeader);
			celdaHCerrados.setCellStyle(estiloCeldasHeader);
			celdaHTotal2.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos2.setCellStyle(estiloCeldasHeader);
			celdaHCerrados2.setCellStyle(estiloCeldasHeader);
			celdaHTotal3.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos3.setCellStyle(estiloCeldasHeader);
			celdaHCerrados3.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener2.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener3.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib2.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib3.setCellStyle(estiloCeldasHeader);

			estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHCantRUC.setCellStyle(estiloCeldasHeader);
			celdaDependBottom.setCellStyle(estiloCeldasHeader);
			celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresRepresImprPorDepBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				texto = new HSSFRichTextString(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellValue(texto.toString());
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// CANT. RUC
				celdaHCantRUC = fila.createCell(1);
				celdaHCantRUC.setCellValue(String.valueOf(reg.getCantidadRuc()));
				celdaHCantRUC.setCellStyle(estiloRecorrido);

				// CANT. EXPEDIENTES VIRTUALES
				celdaHCantTotalExpdtsVirt = fila.createCell(2);
				celdaHCantTotalExpdtsVirt.setCellValue(String.valueOf(reg.getCantidadExpAbiertos() + reg.getCantidadExpCerrados()));
				celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS
				celdaHAbiertos = fila.createCell(3);
				celdaHAbiertos.setCellValue(String.valueOf(reg.getCantidadExpAbiertos()));
				celdaHAbiertos.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS
				celdaHCerrados = fila.createCell(4);
				celdaHCerrados.setCellValue(String.valueOf(reg.getCantidadExpCerrados()));
				celdaHCerrados.setCellStyle(estiloRecorrido);

				// TOTAL CANT. REP. IMPRE. DE EXPDT.  USUARIO INTERNO
				celdaHTotal2 = fila.createCell(5);
				celdaHTotal2.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpUsu() + reg.getCantidadExpCerrRepImpUsu()));
				celdaHTotal2.setCellStyle(estiloRecorrido);

				// CANT. REP. IMPRE. DE EXPDT. ABIERTOS USUARIO INTERNO
				celdaHAbiertos2 = fila.createCell(6);
				celdaHAbiertos2.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpUsu()));
				celdaHAbiertos2.setCellStyle(estiloRecorrido);

				// CANT. REP. IMPRE. DE EXPDT. CERRADOS  USUARIO INTERNO
				celdaHCerrados2 = fila.createCell(7);
				celdaHCerrados2.setCellValue(String.valueOf(reg.getCantidadExpCerrRepImpUsu()));
				celdaHCerrados2.setCellStyle(estiloRecorrido);

				// TOTAL CANT. REP. IMPRE. DE EXPDT.  CONTRIBUYENTE
				celdaHTotal3 = fila.createCell(8);
				celdaHTotal3.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpContrib() + reg.getCantidadExpCerrRepImpContrib()));
				celdaHTotal3.setCellStyle(estiloRecorrido);
				
				// CANT. REP. IMPRE. DE EXPDT. ABIERTOS CONTRIBUYENTE
				celdaHAbiertos3 = fila.createCell(9);
				celdaHAbiertos3.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpContrib()));
				celdaHAbiertos3.setCellStyle(estiloRecorrido);
				
				// CANT. REP. IMPRE. DE EXPDT. CERRADOS CONTRIBUYENTE
				celdaHCerrados3 = fila.createCell(10);
				celdaHCerrados3.setCellValue(String.valueOf(reg.getCantidadExpCerrRepImpContrib()));
				celdaHCerrados3.setCellStyle(estiloRecorrido);

			}
			establecerAnchoColumnas(hoja, 5);
			hoja.setColumnWidth(5, 5000);
			hoja.setColumnWidth(6, 5000);
			hoja.setColumnWidth(7, 5000);
			hoja.setColumnWidth(8, 6500);
			hoja.setColumnWidth(9, 6500);
			hoja.setColumnWidth(10, 6500);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_DEP.toLowerCase());
			//Fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptRepresImprPorDependencia");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptRepresImprPorDependencia");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	private Map<String, Object> exportarRptRepresImprPorFecha(Map<String, Object> mapParametrosBusqueda) throws Exception {// 11
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptRepresImprPorFecha");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String codTipDoc = (String) mapParametrosBusqueda.get("codTipDoc");

			if (codTipDoc != null && codTipDoc.equals("")) {
				mapParametrosBusqueda.remove("codTipDoc");
			}

			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresRepresImprPorFechaBean> listaReportes = reportesIndicadoresService.reporteIndicadorRepresImprPorFecha(mapParametrosBusqueda);

			// Inicio [gangles 16/06/2016] se agrega el conteo por AgrupaciÃƒÂ³n de tipo de documentos,
			List<Map<String, Object>> mapCantTipoDocs = new ArrayList<Map<String, Object>>();// reportesIndicadoresService.cantDocsPorTipo(mapParametrosBusqueda);
			List<Map<String, Object>> mapCantDocsDep = new ArrayList<Map<String, Object>>(); // reportesIndicadoresService.cantGrupoDocsPorDepedencias(mapParametrosBusqueda);
			// Fin [gangles 16/06/2016]

			// obtenerMapaTipDocsOdendados(tipoRpt, listaReportes, mapCantDocsDep);

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);

			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;

			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			fila.setHeight((short) 900);
			HSSFCell celdaHFecGenExpdt = fila.createCell(0);
			HSSFCell celdaHCantRUC = fila.createCell(1);
			HSSFCell celdaHCantTotalExpdtsVirt = fila.createCell(2);
			HSSFCell celdaHCantExpdtsVirt2 = fila.createCell(3);// Creado solo para visualizar bordes
			HSSFCell celdaHCantExpdtsVirt3 = fila.createCell(4);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGener = fila.createCell(5);
			HSSFCell celdaHCantRepreImprGener2 = fila.createCell(6);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGener3 = fila.createCell(7);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGenerXContrib = fila.createCell(8);
			HSSFCell celdaHCantRepreImprGenerXContrib2 = fila.createCell(9);// Creado solo para visualizar bordes
			HSSFCell celdaHCantRepreImprGenerXContrib3 = fila.createCell(10);// Creado solo para visualizar bordes

			celdaHFecGenExpdt.setCellValue("FECHA DE GENERACIÃ“N EXPEDIENTE AÃ‘O/MES/DÃA");
			rango = new CellRangeAddress(row, row + 1, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHCantRUC.setCellValue("CANTIDAD RUC");
			rango = new CellRangeAddress(row, row + 1, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHCantTotalExpdtsVirt.setCellValue("EXPEDIENTES POR ESTADO");

			rango = new CellRangeAddress(row, row, 2, 4);
			hoja.addMergedRegion(rango);

			celdaHCantRepreImprGener.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS POR USUARIOS INTERNOS");
			rango = new CellRangeAddress(row, row, 5, 7);
			hoja.addMergedRegion(rango);

			celdaHCantRepreImprGenerXContrib.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS POR CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 8, 10);
			hoja.addMergedRegion(rango);

			fila = hoja.createRow(++row);
			HSSFCell celdaDependBottom = fila.createCell(0);// Creado solo para visualizar bordes
			HSSFCell celdaCantRUCBottom = fila.createCell(1);// Creado solo para visualizar bordes

			HSSFCell celdaHTotal = fila.createCell(2);
			celdaHTotal.setCellValue("TOTAL");
			HSSFCell celdaHAbiertos = fila.createCell(3);
			celdaHAbiertos.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados = fila.createCell(4);
			celdaHCerrados.setCellValue("CERRADOS");

			HSSFCell celdaHTotal2 = fila.createCell(5);
			celdaHTotal2.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos2 = fila.createCell(6);
			celdaHAbiertos2.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados2 = fila.createCell(7);
			celdaHCerrados2.setCellValue("CERRADOS");

			HSSFCell celdaHTotal3 = fila.createCell(8);
			celdaHTotal3.setCellValue("TOTAL");

			HSSFCell celdaHAbiertos3 = fila.createCell(9);
			celdaHAbiertos3.setCellValue("ABIERTOS");

			HSSFCell celdaHCerrados3 = fila.createCell(10);
			celdaHCerrados3.setCellValue("CERRADOS");

			celdaHCantTotalExpdtsVirt.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt2.setCellStyle(estiloCeldasHeader);
			celdaHCantExpdtsVirt3.setCellStyle(estiloCeldasHeader);
			celdaHTotal.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos.setCellStyle(estiloCeldasHeader);
			celdaHCerrados.setCellStyle(estiloCeldasHeader);
			celdaHTotal2.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos2.setCellStyle(estiloCeldasHeader);
			celdaHCerrados2.setCellStyle(estiloCeldasHeader);
			celdaHTotal3.setCellStyle(estiloCeldasHeader);
			celdaHAbiertos3.setCellStyle(estiloCeldasHeader);
			celdaHCerrados3.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener2.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGener3.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib2.setCellStyle(estiloCeldasHeader);
			celdaHCantRepreImprGenerXContrib3.setCellStyle(estiloCeldasHeader);

			estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			celdaHFecGenExpdt.setCellStyle(estiloCeldasHeader);
			celdaHCantRUC.setCellStyle(estiloCeldasHeader);
			celdaDependBottom.setCellStyle(estiloCeldasHeader);
			celdaCantRUCBottom.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			String fecRegistro = "";
			
			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresRepresImprPorFechaBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				// FECHA
				fecRegistro = reg.getFecha() == null ? ValidaConstantes.SEPARADOR_GUION : Utils.dateUtilToStringDDMMYYYY(reg.getFecha());
				celdaHFecGenExpdt = fila.createCell(0);
				texto = new HSSFRichTextString(fecRegistro);
				celdaHFecGenExpdt.setCellValue(texto.toString());
				celdaHFecGenExpdt.setCellStyle(estiloRecorrido);

				// CANT. RUC
				celdaHCantRUC = fila.createCell(1);
				celdaHCantRUC.setCellValue(String.valueOf(reg.getCantidadRuc()));
				celdaHCantRUC.setCellStyle(estiloRecorrido);

				// CANT. EXPEDIENTES VIRTUALES
				celdaHCantTotalExpdtsVirt = fila.createCell(2);
				celdaHCantTotalExpdtsVirt.setCellValue(String.valueOf(reg.getCantidadExpAbiertos() + reg.getCantidadExpCerrados()));
				celdaHCantTotalExpdtsVirt.setCellStyle(estiloRecorrido);

				// CANT. ABIERTOS
				celdaHAbiertos = fila.createCell(3);
				celdaHAbiertos.setCellValue(String.valueOf(reg.getCantidadExpAbiertos()));
				celdaHAbiertos.setCellStyle(estiloRecorrido);

				// CANT. CERRADOS
				celdaHCerrados = fila.createCell(4);
				celdaHCerrados.setCellValue(String.valueOf(reg.getCantidadExpCerrados()));
				celdaHCerrados.setCellStyle(estiloRecorrido);

				// TOTAL CANT. REP. IMPRE. DE EXPDT.  USUARIO INTERNO
				celdaHTotal2 = fila.createCell(5);
				celdaHTotal2.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpUsu() + reg.getCantidadExpCerrRepImpUsu()));
				celdaHTotal2.setCellStyle(estiloRecorrido);

				// CANT. REP. IMPRE. DE EXPDT. ABIERTOS USUARIO INTERNO
				celdaHAbiertos2 = fila.createCell(6);
				celdaHAbiertos2.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpUsu()));
				celdaHAbiertos2.setCellStyle(estiloRecorrido);

				// CANT. REP. IMPRE. DE EXPDT. CERRADOS  USUARIO INTERNO
				celdaHCerrados2 = fila.createCell(7);
				celdaHCerrados2.setCellValue(String.valueOf(reg.getCantidadExpCerrRepImpUsu()));
				celdaHCerrados2.setCellStyle(estiloRecorrido);

				// TOTAL CANT. REP. IMPRE. DE EXPDT.  CONTRIBUYENTE
				celdaHTotal3 = fila.createCell(8);
				celdaHTotal3.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpContrib() + reg.getCantidadExpCerrRepImpContrib()));
				celdaHTotal3.setCellStyle(estiloRecorrido);
				
				// CANT. REP. IMPRE. DE EXPDT. ABIERTOS CONTRIBUYENTE
				celdaHAbiertos3 = fila.createCell(9);
				celdaHAbiertos3.setCellValue(String.valueOf(reg.getCantidadExpAbiRepImpContrib()));
				celdaHAbiertos3.setCellStyle(estiloRecorrido);
				
				// CANT. REP. IMPRE. DE EXPDT. CERRADOS CONTRIBUYENTE
				celdaHCerrados3 = fila.createCell(10);
				celdaHCerrados3.setCellValue(String.valueOf(reg.getCantidadExpCerrRepImpContrib()));
				celdaHCerrados3.setCellStyle(estiloRecorrido);

			}
			establecerAnchoColumnas(hoja, 5);
			hoja.setColumnWidth(5, 5000);
			hoja.setColumnWidth(6, 5000);
			hoja.setColumnWidth(7, 5000);
			hoja.setColumnWidth(8, 6500);
			hoja.setColumnWidth(9, 6500);
			hoja.setColumnWidth(10, 6500);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_FEC.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptRepresImprPorFecha");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptRepresImprPorFecha");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	private Map<String, Object> exportarRptRepresImprPorExpdteYDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception {// 12
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarRptRepresImprPorExpdteYDependencia");
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		try {
			String codTipDoc = (String) mapParametrosBusqueda.get("codTipDoc");

			if (codTipDoc != null && codTipDoc.equals("")) {
				mapParametrosBusqueda.remove("codTipDoc");
			}

			String tipoRpt = mapParametrosBusqueda.get("tpoRpt").toString();
			List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> listaReportes = reportesIndicadoresService.reporteIndicadorPorExpdteYDependencia(mapParametrosBusqueda);

			// Inicio [gangles 16/06/2016] se agrega el conteo por AgrupaciÃƒÂ³n de tipo de documentos,
			List<Map<String, Object>> mapCantTipoDocs = new ArrayList<Map<String, Object>>();// reportesIndicadoresService.cantDocsPorTipo(mapParametrosBusqueda);
			List<Map<String, Object>> mapCantDocsDep = new ArrayList<Map<String, Object>>(); // reportesIndicadoresService.cantGrupoDocsPorDepedencias(mapParametrosBusqueda);
			// Fin [gangles 16/06/2016]

			
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("Hoja 1");

			HSSFRow fila = hoja.createRow(0);
			HSSFCell tituloCelda = fila.createCell(0);
			CellRangeAddress rango = null;

			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(mapParametrosBusqueda);

			int row = generarCabecera(tipoRpt, hoja, fila, rango, tituloCelda, params);

			HSSFFont fuente = libro.createFont();
			fuente.setFontHeightInPoints((short) 9);
			fuente.setFontName(fuente.FONT_ARIAL);
			fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle estiloCeldasHeader = libro.createCellStyle();
			setEstiloCabecera(estiloCeldasHeader, fuente);

			fila = hoja.createRow(++row);
			fila.setHeight((short) 1200);
			HSSFCell celdaHDependencia = fila.createCell(0);
			HSSFCell celdaHNumRUC = fila.createCell(1);
			HSSFCell celdaHNumExpdteOrigen = fila.createCell(2);
			HSSFCell celdaHNumExpdteVirtual = fila.createCell(3);
			HSSFCell celdaHFecExpdte = fila.createCell(4);
			HSSFCell celdaHOrigenExpdte = fila.createCell(5);
			HSSFCell celdaHEstadoExpdte = fila.createCell(6);
			HSSFCell celdaHCantDocsExpdte = fila.createCell(7);
			HSSFCell celdaHCantRepImpUsu = fila.createCell(8);
			HSSFCell celdaHFecUltRepImpUsu = fila.createCell(9);
			HSSFCell celdaHCantRepImpCont = fila.createCell(10);
			HSSFCell celdaHFecUltRepImpCont = fila.createCell(11);

			celdaHDependencia.setCellValue("DEPENDENCIA");
			rango = new CellRangeAddress(row, row, 0, 0);
			hoja.addMergedRegion(rango);

			celdaHNumRUC.setCellValue("RUC");
			rango = new CellRangeAddress(row, row, 1, 1);
			hoja.addMergedRegion(rango);

			celdaHNumExpdteOrigen.setCellValue("NÃšMERO EXPEDIENTE ORIGEN");

			rango = new CellRangeAddress(row, row, 2, 2);
			hoja.addMergedRegion(rango);

			celdaHNumExpdteVirtual.setCellValue("NÃšMERO EXPEDIENTE VIRTUAL");
			rango = new CellRangeAddress(row, row, 3, 3);
			hoja.addMergedRegion(rango);

			celdaHFecExpdte.setCellValue("FECHA GENERACIÃ“N EXPEDIENTE");
			rango = new CellRangeAddress(row, row, 4, 4);
			hoja.addMergedRegion(rango);
			
			celdaHOrigenExpdte.setCellValue("ORIGEN EXPEDIENTE");
			rango = new CellRangeAddress(row, row, 5, 5);
			hoja.addMergedRegion(rango);
			
			celdaHEstadoExpdte.setCellValue("ESTADO EXPEDIENTE");
			rango = new CellRangeAddress(row, row, 6, 6);
			hoja.addMergedRegion(rango);
			
			celdaHCantDocsExpdte.setCellValue("CANTIDAD DE DOCUMENTOS EXPEDIENTE");
			rango = new CellRangeAddress(row, row, 7, 7);
			hoja.addMergedRegion(rango);
			
			celdaHCantRepImpUsu.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS POR USUARIOS INTERNOS");
			rango = new CellRangeAddress(row, row, 8, 8);
			hoja.addMergedRegion(rango);
			
			celdaHFecUltRepImpUsu.setCellValue("FECHA ÃšLTIMA GENERACIÃ“N DE REPRESENTACIÃ“N IMPRESA USUARIO INTERNO");
			rango = new CellRangeAddress(row, row, 9, 9);
			hoja.addMergedRegion(rango);
			
			celdaHCantRepImpCont.setCellValue("NÃšMERO DE REPRESENTACIONES IMPRESAS GENERADAS CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 10, 10);
			hoja.addMergedRegion(rango);
			
			celdaHFecUltRepImpCont.setCellValue("FECHA ÃšLTIMA GENERACIÃ“N DE REPRESENTACIÃ“N IMPRESA CONTRIBUYENTE");
			rango = new CellRangeAddress(row, row, 11, 11);
			hoja.addMergedRegion(rango);


			celdaHDependencia.setCellStyle(estiloCeldasHeader);
			celdaHNumRUC.setCellStyle(estiloCeldasHeader);
			celdaHNumExpdteOrigen.setCellStyle(estiloCeldasHeader);
			celdaHNumExpdteVirtual.setCellStyle(estiloCeldasHeader);
			celdaHFecExpdte.setCellStyle(estiloCeldasHeader);
			celdaHOrigenExpdte.setCellStyle(estiloCeldasHeader);
			celdaHEstadoExpdte.setCellStyle(estiloCeldasHeader);
			celdaHCantDocsExpdte.setCellStyle(estiloCeldasHeader);
			celdaHCantRepImpUsu.setCellStyle(estiloCeldasHeader);
			celdaHFecUltRepImpUsu.setCellStyle(estiloCeldasHeader);
			celdaHCantRepImpCont.setCellStyle(estiloCeldasHeader);
			celdaHFecUltRepImpCont.setCellStyle(estiloCeldasHeader);

			HSSFCellStyle estiloTitulo = libro.createCellStyle();
			estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			estiloTitulo.setFont(fuente);

			tituloCelda.setCellStyle(estiloTitulo);

			HSSFRichTextString texto;
			HSSFFont fuenteContenido = libro.createFont();
			fuenteContenido.setFontHeightInPoints((short) 8);
			HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			estiloCeldaContenido.setFont(fuenteContenido);
			HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			setEstiloRecorrido(estiloRecorrido, fuente);

			// MAPA DE TIPOS DE ORIGEN
			Map<String, Object> paramsOrig = new HashMap<String, Object>();
			paramsOrig.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapOrig = catalogoService.obtenerCatalogo(paramsOrig);

			// MAPA DE ESTADOS
			Map<String, Object> paramsEstados = new HashMap<String, Object>();
			paramsEstados.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			Map<String, Object> mapEstados = catalogoService.obtenerCatalogo(paramsEstados);
			
			String fechaFormato = "";
			++row;
			for (int i = 0; i < listaReportes.size(); i++) {
				ReporteIndicadoresRepresImprPorExpdteYDependenciaBean reg = listaReportes.get(i);

				fila = hoja.createRow(i + row);

				String desDependencia = mapaDependencias.get(reg.getCodDependencia().trim()).toString();

				// DEPENDENCIA
				celdaHDependencia = fila.createCell(0);
				texto = new HSSFRichTextString(reg.getCodDependencia() + " - " + desDependencia);
				celdaHDependencia.setCellValue(texto.toString());
				celdaHDependencia.setCellStyle(estiloRecorrido);

				// NUM. RUC
				celdaHNumRUC = fila.createCell(1);
				celdaHNumRUC.setCellValue(reg.getNumeroRuc());
				celdaHNumRUC.setCellStyle(estiloRecorrido);

				// NUM. EXPDTE. ORIGEN
				celdaHNumExpdteOrigen = fila.createCell(2);
				celdaHNumExpdteOrigen.setCellValue(reg.getNumExpdteOrigen());
				celdaHNumExpdteOrigen.setCellStyle(estiloRecorrido);

				// NUM. EXPDTE. VIRTUAL
				celdaHNumExpdteVirtual = fila.createCell(3);
				celdaHNumExpdteVirtual.setCellValue(reg.getNumExpdteVirtual());
				celdaHNumExpdteVirtual.setCellStyle(estiloRecorrido);

				// FEC. GENERACION EXPEDIENTE
				fechaFormato = Utils.dateUtilToStringDDMMYYYY(reg.getFecRegistroExpdte());
				celdaHFecExpdte = fila.createCell(4);
				celdaHFecExpdte.setCellValue(fechaFormato);
				celdaHFecExpdte.setCellStyle(estiloRecorrido);

				// ORIGEN EXPEDIENTE
				celdaHOrigenExpdte = fila.createCell(5);
				celdaHOrigenExpdte.setCellValue(Utils.toStr(mapOrig.get(reg.getCodOrigen())));
				celdaHOrigenExpdte.setCellStyle(estiloRecorrido);
				
				
				// ESTADO EXPEDIENTE
				celdaHEstadoExpdte = fila.createCell(6);
				celdaHEstadoExpdte.setCellValue(Utils.toStr(mapEstados.get(reg.getCodEstado())));
				celdaHEstadoExpdte.setCellStyle(estiloRecorrido);

				// CANT. DE DOCUMENTOS EXPEDIENTE
				celdaHCantDocsExpdte = fila.createCell(7);
				celdaHCantDocsExpdte.setCellValue(String.valueOf(reg.getCantidadDocumentos()));
				celdaHCantDocsExpdte.setCellStyle(estiloRecorrido);

				// NÃƒÅ¡MERO DE REPRESENTACIONES IMPRESAS GENERADAS USUARIO INTERNO
				celdaHCantRepImpUsu = fila.createCell(8);
				celdaHCantRepImpUsu.setCellValue(String.valueOf(reg.getCantidadExpRepImpUsu()));
				celdaHCantRepImpUsu.setCellStyle(estiloRecorrido);
				
				// FECHA ÃƒÅ¡LTIMA REPRESENTACIÃƒâ€œN IMPRESA GENERADAS USUARIO INTERNO
				fechaFormato = reg.getFecUltRepImpUsu() == null ? ValidaConstantes.SEPARADOR_GUION : Utils.dateUtilToStringDDMMYYYY(reg.getFecUltRepImpUsu());
				celdaHFecUltRepImpUsu = fila.createCell(9);
				celdaHFecUltRepImpUsu.setCellValue(fechaFormato);
				celdaHFecUltRepImpUsu.setCellStyle(estiloRecorrido);
				
				// NÃƒÅ¡MERO DE REPRESENTACIONES IMPRESAS GENERADAS CONTRIBUYENTE
				celdaHCantRepImpUsu = fila.createCell(10);
				celdaHCantRepImpUsu.setCellValue(String.valueOf(reg.getCantidadExpRepImpCont()));
				celdaHCantRepImpUsu.setCellStyle(estiloRecorrido);
				
				// FECHA ÃƒÅ¡LTIMA REPRESENTACIÃƒâ€œN IMPRESA GENERADAS CONTRIBUYENTE
				fechaFormato = reg.getFecUltRepImpCont() == null ? ValidaConstantes.SEPARADOR_GUION : Utils.dateUtilToStringDDMMYYYY(reg.getFecUltRepImpCont());
				celdaHFecUltRepImpUsu = fila.createCell(11);
				celdaHFecUltRepImpUsu.setCellValue(fechaFormato);
				celdaHFecUltRepImpUsu.setCellStyle(estiloRecorrido);

			}
			establecerAnchoColumnas(hoja, 10);
			hoja.setColumnWidth(6, 4500);
			hoja.setColumnWidth(7, 5000);
			hoja.setColumnWidth(8, 6500);
			hoja.setColumnWidth(9, 6000);
			hoja.setColumnWidth(10, 6500);
			hoja.setColumnWidth(11, 6000);
			//inicio luis Estrada 10/11/2016
			atribFileName=outputExcel(libro, CatalogoConstantes.RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP.toLowerCase());
			//fin luis Estrada 10/11/2016
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarRptRepresImprPorExpdteYDependencia");
			}
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarRptRepresImprPorExpdteYDependencia");
			}
			NDC.pop();
			NDC.remove();
		}
		return atribFileName;
	}

	private void establecerAnchoColumnas(HSSFSheet hoja, int tamanho) {
		for (int i = 0; i < tamanho; i++) {
			hoja.autoSizeColumn(i);
		}
	}

	private void obtenerMapaTipDocsOdendados(String tipoRpt, List listaReportes, List<Map<String, Object>> lstCantDocs) {
		// List<String> docs = new ArrayList<String>();
		Map<String, Map<String, Map<String, Object>>> mapCantDocs = new HashMap<String, Map<String, Map<String, Object>>>();
		String formatoFechaReq = "yyyy-MM-dd";
		String fecha = null;

		// Se recorre la lista que contiene las cantidades de documentos con su respectivo cÃƒÂ³d. de documento y cÃƒÂ³d. de dependencia
		for (Map<String, Object> map : lstCantDocs) {
			String keyMap = CatalogoConstantes.RPT_EXP_GEN_X_DEP.equals(tipoRpt) ? "codDependencia" : "fechaGeneracion";
			String codRegistroRpt = map.get(keyMap).toString();
			String codTipoDoc = map.get("codTipoDoc").toString();

			// // Lista que se recorrera horizontalemente con todos los tipos de documentos
			// if (!docs.contains(codTipoDoc)) {
			// docs.add(codTipoDoc);
			// }

			// Se crea una lista vacÃƒÂ­a por cada codigo de dependencia, cada lista tendrÃƒÂ¡ el m
			if (!mapCantDocs.containsKey(codRegistroRpt)) {
				mapCantDocs.put(codRegistroRpt, new HashMap<String, Map<String, Object>>());
			}
			mapCantDocs.get(codRegistroRpt).put(map.get("codTipoDoc").toString().trim(), map);

		}

		// Se recorre cada reporte (cada uno representa un reporte por dependencia)
		// for (ReporteIndicadoresExpedienteVirtualBean rpt : listaReportes) {
		for (int i = 0; i < listaReportes.size(); i++) {
			ReporteIndicadoresExpedienteVirtualBean rpt = (ReporteIndicadoresExpedienteVirtualBean) listaReportes.get(i);
			String keyMap = "";
			if (rpt instanceof ReporteIndicadoresExpedienteVirtualDependBean) {
				keyMap = ((ReporteIndicadoresExpedienteVirtualDependBean) rpt).getCodDependencia();
			} else {
				fecha = Utils.convertirDateToString(((ReporteIndicadoresExpedienteVirtualFechaGenBean) rpt).getFechaGeneracion(), formatoFechaReq);
				keyMap = fecha;
			}

			if (mapCantDocs.containsKey(keyMap)) {
				rpt.setMapDocs(mapCantDocs.get(keyMap));
			} else {
				rpt.setMapDocs(new HashMap<String, Map<String, Object>>());
			}
		}

		// return docs;
	}

	private void setEstiloRecorrido(HSSFCellStyle estiloRecorrido, HSSFFont fuente) {
		estiloRecorrido.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		estiloRecorrido.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloRecorrido.setBottomBorderColor((short) 8);
		estiloRecorrido.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloRecorrido.setLeftBorderColor((short) 8);
		estiloRecorrido.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloRecorrido.setRightBorderColor((short) 8);
		estiloRecorrido.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloRecorrido.setTopBorderColor((short) 8);
	}

	private void setEstiloCabecera(HSSFCellStyle estiloCeldasHeader, HSSFFont fuente) {
		estiloCeldasHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldasHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCeldasHeader.setFont(fuente);
		estiloCeldasHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCeldasHeader.setBottomBorderColor((short) 8);
		estiloCeldasHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloCeldasHeader.setLeftBorderColor((short) 8);
		estiloCeldasHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCeldasHeader.setRightBorderColor((short) 8);
		estiloCeldasHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCeldasHeader.setTopBorderColor((short) 8);
		estiloCeldasHeader.setFillForegroundColor((short) 22);
		estiloCeldasHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloCeldasHeader.setWrapText(true);
	}

	private int generarCabecera(String tipoRpt, HSSFSheet hoja, HSSFRow fila, CellRangeAddress rango, HSSFCell tituloCelda, Map<String, Object> params) {
		int row = 0;
		// Inicio [gangles 17/08/2016]
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		// Fin [gangles 17/08/2016]
		// TITULO
		tituloCelda.setCellValue(hmTitulosRptExcel.get(tipoRpt));
		rango = new CellRangeAddress(0, 0, 0, 6);
		hoja.addMergedRegion(rango);

		// SUBHEADER 1
		fila = hoja.createRow(++row);
		HSSFCell celdaProceso = fila.createCell(0);
		celdaProceso.setCellValue("Proceso: ");

		HSSFCell celdaProcesoVal = fila.createCell(1);
		// celdaProcesoVal.setCellValue(proceso.getDesParametro());
		celdaProcesoVal.setCellValue(params.get("proceso").toString().trim());

		// Inicio [gangles 17/08/2016]--Se agrega la fecha en la que se genera el reporte
		HSSFCell celdaFecReporte = fila.createCell(2);
		celdaFecReporte.setCellValue("Fecha del Reporte: ");
		HSSFCell celdaFecReporteVal = fila.createCell(3);
		celdaFecReporteVal.setCellValue(fecImpresion);
		// Fin [gangles 17/08/2016]

		// SUBHEADER 2
		fila = hoja.createRow(++row);
		HSSFCell celdaTpoExpdte = fila.createCell(0);
		celdaTpoExpdte.setCellValue("Tipo de Expediente: ");

		HSSFCell celdaTpoExpdteVal = fila.createCell(1);
		// celdaTpoExpdteVal.setCellValue(tipoExpediente.getDesParametro());
		celdaTpoExpdteVal.setCellValue(params.get("tpoExdte").toString().trim());
		if (CatalogoConstantes.RPT_EXP_GEN_X_DEP.equals(tipoRpt) || CatalogoConstantes.RPT_EXP_GEN_X_AMD.equals(tipoRpt)) {
			// SUBHEADER 3
			fila = hoja.createRow(++row);
			HSSFCell celdaTpoDoc = fila.createCell(0);
			celdaTpoDoc.setCellValue("Tipo de Documento: ");
			HSSFCell celdaTpoDocVal = fila.createCell(1);
			// celdaTpoDocVal.setCellValue(tipoDcto.getDesParametro());
			celdaTpoDocVal.setCellValue(params.get("tipoDcto").toString().trim());
		}

		// SUBHEADER 4
		fila = hoja.createRow(++row);
		HSSFCell celdaRangFec = fila.createCell(0);
		celdaRangFec.setCellValue("Rango de Fechas: ");
		HSSFCell celdaRangFecVal = fila.createCell(1);
		// celdaRangFecVal.setCellValue(" De " + mapParametrosBusqueda.get("fecIni").toString() + "  A " + mapParametrosBusqueda.get("fecFin").toString());
		celdaRangFecVal.setCellValue(" De " + params.get("fecIni").toString() + "  A " + params.get("fecFin").toString());
		return row;
	}

	private Map<String, Object> outputExcel(HSSFWorkbook libro, String nomArchivo) throws IOException {
		Calendar calendar = Calendar.getInstance();
		//inicio luis Estrada 10/11/2016
		Map<String, Object> atribFileName = new HashMap<String, Object>();
		//fin luis Estrada 10/11/2016
		int anio = (calendar.get(Calendar.YEAR));
		int dia = (calendar.get(Calendar.DATE));
		int hora = (calendar.get(Calendar.HOUR_OF_DAY));
		int minutos = (calendar.get(Calendar.MINUTE));
		int seconds = (calendar.get(Calendar.SECOND));
		int miliseconds = (calendar.get(Calendar.MILLISECOND));
		String dd = (dia < 10) ? "0" + dia : dia + "";

		int mes = calendar.get(Calendar.MONTH) + 1;

		String mm = (mes < 10) ? "0" + mes : mes + "";

		String part = anio + mm + dd + "_" + hora + minutos + seconds + miliseconds;

		String filename = nomArchivo + "_" + part + ".xls";
		FileOutputStream elFichero = new FileOutputStream(new File("/data0/tempo/" + filename));
		atribFileName.put("filename",filename);
		libro.write(elFichero);
		elFichero.close();
		//inicio luis Estrada 10/11/2016
		return atribFileName; 
		//fin luis Estrada 10/11/2016
		
	}
    
    //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	public ModelAndView consultarRespExpVirtual(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.consultarRespExpVirtual");

		// Variables
		ModelAndView modelo = new ModelAndView("jsonView");
		String numExpedienteVirtual = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		T6614ExpVirtualBean datosExpBean = null;
		HashMap<String, Object> mapParametrosBusquedaResponables;
		HashMap<String, String> excepciones = new HashMap<String, String>();
		
		try {
			
			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			numExpedienteVirtual = Utils.toStr(request.getParameter("numExpedienteVirtual"));
						
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
			
			String tipoExp = datosExpBean.getCodTipoExpediente();
						
			if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(tipoExp) || 
				ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(tipoExp)){
				
				mapParametrosBusquedaResponables = new HashMap<String, Object>();
				
				mapParametrosBusquedaResponables.put("numExpedienteVirtual", numExpedienteVirtual);
				mapParametrosBusquedaResponables.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				List<T6621RespExpVirtBean> listaResponsables = responsableService.listarResponsables(mapParametrosBusquedaResponables);
				
				int C = 0;			
				for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
										
					String usuarioLogueado = usuarioBean.getNroRegistro().trim();
					String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
					
					if(usuarioLogueado.equals(responsableExp)){
						
						C++;						
						break;
						
					}
				}
				
				if(C == 0){
										
					excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
					
				}
			}
			
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.consultarRespExpVirtual");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.consultarRespExpVirtual");
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
	}	
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

	//LLRB 16012020 //
		public ModelAndView cargarArchivosEscElec(HttpServletRequest request, HttpServletResponse response) {
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarArchivosExpVirtual");
			ModelAndView modelo;
			//HttpSession session = request.getSession(true);
			//UsuarioBean usuarioBean;
			List<Map<String, Object>> obtenerArchivosExpediente;	
			HashMap<String, Object> mapParametrosBusqueda;
			//List<String> listaExpedientes = new ArrayList<String>();

			try {

				
				if (log.isDebugEnabled()) log.debug("Entro al try");
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
				obtenerArchivosExpediente = new ArrayList<Map<String, Object>>();
				HashMap<String, String> excepciones = new HashMap<String, String>();
				
				log.debug("numCorDoc:"+request.getParameter("numCorDoc"));

				Integer numCorDoc =Integer.parseInt(request.getParameter("numCorDoc"));
				
				//mapParametrosBusqueda = new HashMap<String, Object>();
				modelo = new ModelAndView("jsonView");
				mapParametrosBusqueda = new HashMap<String, Object>();
				mapParametrosBusqueda.put("numCorDoc", numCorDoc);
				
				log.debug("numcordoc->c " + numCorDoc);
				obtenerArchivosExpediente = expedienteVirtualService.obtenerArchivosExpediente(mapParametrosBusqueda);
				
				if (log.isDebugEnabled()) log.debug("Entro al servicio");
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_017);
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("lstArchExp", new JsonSerializer().serialize(obtenerArchivosExpediente));

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarArchivosExpVirtual");

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarArchivosExpVirtual");

				NDC.pop();
				NDC.remove();

			}

			return modelo;
		}
		@RequestMapping(value = "/descargarArchivoModal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
		public void descargarArchivoModal(HttpServletRequest request, HttpServletResponse response) {

			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.descargarArchivoModal");
			ResponseEntity<byte[]> responseDoc = null;
			ModelAndView modelo = null;
			String numIdEcm;
			OutputStream os = null;
			String nombreArchivo = null;

			try {

				if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.descargarArchivoModal");

				Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
				numIdEcm = request.getParameter("codIdentificadorECM").toString().trim();
				mapParametrosBusqueda.put("codIdecm", numIdEcm);
				mapParametrosBusqueda.put("inline", "true");
				nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));

				responseDoc = ecmService.descargarDocumentoECM(mapParametrosBusqueda);
				MediaType contentType = responseDoc.getHeaders().getContentType();
				if (!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) || Utils
				        .equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))) {
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
					response.setHeader(headerKey, headerValue);
				}
				os = response.getOutputStream();
				os.write(responseDoc.getBody());

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.descargarArchivoModal");

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.descargarArchivoModal");
				try {
					if (os != null) {
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
		public ModelAndView validarArchivoExtension(HttpServletRequest request, HttpServletResponse response) {

			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.validarArchivoExtension");
			ModelAndView modelAndView;
			try {
				Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
		
				// DESCARGAR EL DOCUMENTO
				Map<String, Object> map = new HashMap<String, Object>();
				
		
				log.debug("numCorDoc-cv: " + documento.get("numCorDoc").toString());
				map.put("numCorDoc", documento.get("numCorDoc").toString());

				List<Map<String, Object>> lstT10373DocAdjReq;	
		
					if (log.isDebugEnabled()) log.debug("Entro al try");
					
				
					lstT10373DocAdjReq = new ArrayList<Map<String, Object>>();
					
					lstT10373DocAdjReq = expedienteVirtualService.obtenerArchivosExpediente(map); 
		
				log.debug("listaControlador: " + lstT10373DocAdjReq);

				String extension=Utils.obtenerExtension((String) lstT10373DocAdjReq.get(0).get("nomArcAdj"));
				
				log.debug("extension: " + extension);
				modelAndView = new ModelAndView(jsonView);
				modelAndView.addObject("error", !Utils.equiv(extension.toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF));
				modelAndView.addObject("extension", extension.toUpperCase());
				

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.validarArchivoExtension");

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelAndView.addObject("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.validarArchivoExtension");
				NDC.pop();
				NDC.remove();
			}

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarListaTiposExpediente");
			return modelAndView;

		}
		@RequestMapping(value = "/verRepArchivoImpresa", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
		public ModelAndView verRepArchivoImpresa(HttpServletRequest request, HttpServletResponse response) {
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.verRepArchivoImpresa");

			ModelAndView modelo = null;
			HttpSession session = request.getSession(true);
			UsuarioBean usuarioBean;
			String numCorrelativo = null;
			Long numSeq = null;
			int tamSeq = 0;
			Map<String, Object> mapDatosDocumento = new HashMap<String, Object>();
			Map<String, Object> mapUsuarioBean = new HashMap<String, Object>();
			Map<String,Object> mapResult = null;
			ByteArrayOutputStream baos = null;
			T6625repimpexpvirtBean t6625repimpexpvirtBean;
			
			try {
				if (log.isDebugEnabled()) log.debug("Procesa - ConsultaReporteController.verRepArchivoImpresa");

				// DATOS DEL DOCUMENTO SELECCIONADO EN GRILLA
				Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));

				numSeq = expedienteVirtualService.generarCorrelativo(SequenceConstantes.SEQ_EV_REPIMP);
				numCorrelativo = "0000000" + numSeq.toString();
				tamSeq = numCorrelativo.length();
				numCorrelativo = numCorrelativo.substring(tamSeq - 7, tamSeq);

				// DESCARGAR EL DOCUMENTO
				Map<String, Object> mapDLECM = new HashMap<String, Object>();
				mapDLECM.put("codIdecm", documento.get("codIdecm").toString());
				mapDLECM.put("inline", "true");

				ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapDLECM);

				if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

					String usub = request.getParameter("usub");
					String tenc = request.getParameter("tenc");
					usub = usub.replace(' ', '+');
					usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

				} else {
					usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
				}

				// DATOS CONTRIBUYENTE y REP IMPRESA
				mapDatosDocumento.put("ruc", request.getParameter("ruc").toString().trim());
				mapDatosDocumento.put("razSoc", request.getParameter("razSoc").toString().trim());
				mapDatosDocumento.put("numArcAdj", documento.get("numArcAdj").toString().trim());
				mapDatosDocumento.put("nomArcAdj", documento.get("nomArcAdj").toString().trim());
				mapDatosDocumento.put("numArcItem", documento.get("numArcItem").toString().trim());
				mapDatosDocumento.put("cntTamArc", documento.get("cntTamArc").toString().trim());
				mapDatosDocumento.put("numFolios", documento.get("numFolios").toString().trim());
				mapDatosDocumento.put("numCorDoc", documento.get("numCorDoc").toString().trim());

				mapDatosDocumento.put("codOriDes", ValidaConstantes.COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTERNET);
				mapDatosDocumento.put("codTipExp", documento.get("codTipExp").toString().trim());
				mapDatosDocumento.put("codTipDoc", documento.get("codTipDoc").toString().trim());
				mapDatosDocumento.put("numDoc", documento.get("numDoc").toString().trim());
				mapDatosDocumento.put("numExpedO", documento.get("numExpedO").toString().trim());
				
				mapDatosDocumento.put("numCorrelativo", numCorrelativo);

				// DATOS DEL USUARIO PARA AUDITORIA
				if (!Utils.isEmpty(usuarioBean.getNroRegistro())) {
					mapUsuarioBean.put("usuarioRegistro", usuarioBean.getNroRegistro());
				} else {
					mapUsuarioBean.put("usuarioRegistro", usuarioBean.getUsuarioSOL());
				}
				mapUsuarioBean.put("fechaRegistro", new Date());
				mapUsuarioBean.put("usuarioModificacion", ValidaConstantes.SEPARADOR_GUION);
				mapUsuarioBean.put("fechaModificacion", ValidaConstantes.FECHA_VACIA);

				//generamos la representaciÃƒÆ’Ã‚Â³n impresa
				mapResult = Utils.generarRepImpresaArchivo(mapDatosDocumento, responseDoc, mapUsuarioBean);
				
				t6625repimpexpvirtBean = (T6625repimpexpvirtBean) mapResult.get("t6625repimpexpvirtBean");

				//grabamos en base de datos
				representacionImpresaService.grabarRepresentacionImpresa(t6625repimpexpvirtBean);

				baos = (ByteArrayOutputStream)mapResult.get("baos");
				
				//mostramos el pdf generado
				response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            response.setContentType("application/pdf");
	            response.setContentLength(baos.size());
	            OutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();
				
			    
			    
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.verRepArchivoImpresa");
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.verRepArchivoImpresa");
				NDC.pop();
				NDC.remove();
			}
			return new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		}
		
		@SuppressWarnings("static-access")
		public ModelAndView exportarExcelExpedientesFisca(HttpServletRequest request, HttpServletResponse response) {

			String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
			ModelAndView view = null;
			MensajeBean mensajeBean = new MensajeBean();
			String listadoExportarCadena = null;
			//Inicio [gangles 22/08/2016] 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fechaActual = new Date();
			String fecImpresion = sdf.format(fechaActual);
			//Fin [gangles 22/08/2016] 
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarExcelExpedientes");

			try {

				listadoExportarCadena = Utils.toStr(request.getParameter("listadoExpedientesCadena"));

				@SuppressWarnings("unchecked")
				List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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
				hoja.setColumnWidth(9, 16000);


				HSSFRow fila = hoja.createRow(1);
				HSSFCell tituloCelda = fila.createCell(0);
				tituloCelda.setCellValue(titulo);
				hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 3));

				fila = hoja.createRow(4);
				HSSFCell celda = fila.createCell(0);
				HSSFCell celda1 = fila.createCell(1);
				HSSFCell celda2 = fila.createCell(2);
				HSSFCell celda3 = fila.createCell(3);
				HSSFCell celda4 = fila.createCell(4);
				HSSFCell celda5 = fila.createCell(5);
				HSSFCell celda6 = fila.createCell(6);
				HSSFCell celda7 = fila.createCell(7);
				HSSFCell celda8 = fila.createCell(8);
				HSSFCell celda9 = fila.createCell(9);

		        
				celda.setCellValue("N°");
				celda1.setCellValue("N° de Expediente");
				celda2.setCellValue("Responsable");				
				celda3.setCellValue("RUC");
				celda4.setCellValue("Razón Social");
				celda5.setCellValue("Proceso");
				celda6.setCellValue("Tipo de Expediente");
				celda7.setCellValue("Estado expediente");				
				celda8.setCellValue("Fecha de Expediente");				
				celda9.setCellValue("Origen");

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
				ssheet.autoSizeColumn(8);
				ssheet.autoSizeColumn(9);

				
				 //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
		     	fila = hoja.createRow(2);
		     	HSSFCell celdaFecha = fila.createCell(1);
		     	celdaFecha.setCellValue("Fecha del Reporte:");
		     	HSSFCell celdaValFecha = fila.createCell(2);
		     	celdaValFecha.setCellValue(fecImpresion);
		     	//Fin [gangles 22/08/2016]
		     	
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
				celda8.setCellStyle(estiloCelda);
				celda9.setCellStyle(estiloCelda);


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
					String numExpedienteAcumulador = Utils.toStr(listadoExportar.get(i).get("numExpedienteAcumulador"));
					if (Utils.equiv(numExpedienteAcumulador, ValidaConstantes.FILTRO_CERO) || Utils.isEmpty(numExpedienteAcumulador)) {
						numExpedienteAcumulador = "";
					}
					 fila = hoja.createRow(i + 5);
		               celda = fila.createCell(0);
		               texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
		               celda.setCellValue(texto.toString());
		               hoja.autoSizeColumn(0);
		               celda.setCellStyle(estiloRecorrido);
		               celda1 = fila.createCell(1);
		               celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")));
		               hoja.autoSizeColumn(1);
		               celda1.setCellStyle(estiloRecorrido);              
		               celda2 = fila.createCell(2);
		               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("nombreResponsable")));
		               hoja.autoSizeColumn(2);
		               celda2.setCellStyle(estiloRecorrido);
		               celda3 = fila.createCell(3);
		               celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));
		               hoja.autoSizeColumn(3);
		               celda3.setCellStyle(estiloRecorrido);
		               // Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
		               celda4 = fila.createCell(4);
		               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desRazonSocial").toString()));
		               hoja.autoSizeColumn(4);
		               celda4.setCellStyle(estiloRecorrido);
		               celda5 = fila.createCell(5);
		               celda5.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desProceso")));
		               hoja.autoSizeColumn(5);
		               celda5.setCellStyle(estiloRecorrido);
		               celda6 = fila.createCell(6);
		               celda6.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desTipoExpediente")));
		               hoja.autoSizeColumn(6);
		               celda6.setCellStyle(estiloRecorrido);               
		               celda7 = fila.createCell(7);
		               celda7.setCellValue(Utils.toStr(listadoExportar.get(i).get("desEstado")));
		               hoja.autoSizeColumn(7);               
		               celda7.setCellStyle(estiloRecorrido);               
		               celda8 = fila.createCell(8);
		               celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));
		               hoja.autoSizeColumn(8);
		               celda8.setCellStyle(estiloRecorrido);               
		               celda9 = fila.createCell(9);
		               celda9.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).get("desOrigen")));
		               hoja.autoSizeColumn(9);
		               celda9.setCellStyle(estiloRecorrido);
		             
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

				String filename = "rpt_expediente_virtual_" + part + ".xls";
				;
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
				if (log.isDebugEnabled()) {
					log.debug((Object) "Error - ConsultaReporteController.exportarExcelExpedientes");
				}

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				view = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				view.addObject("beanErr", msb);
			} finally {
				if (log.isDebugEnabled()) {
					log.debug((Object) "Final - ConsultaReporteController.exportarExcelExpedientes");
				}
				NDC.pop();
				NDC.remove();
			}

			return view;
		}
		
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelObservacionesFisca(HttpServletRequest request, HttpServletResponse response) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fechaActual = new Date();
			String fecImpresion = sdf.format(fechaActual);

			String titulo = ExportaConstantes.TITULO_EXPORTA_04_08;

			ModelAndView view = null;
			MensajeBean mensajeBean = new MensajeBean();
			String listadoExportarCadena = null;
			String numRuc = null;
			String razonSocial = null;
			String numExpOrigen = null;
			String numExpVirtual = null;
			String estExpediente = null;
			String tipoProceso = null;
			String tipoExpediente = null;
			String descOrigen = null;
			String fechaGeneracion = null;
			String fechaOrigen = null;
			String razonSocialRuc = null;

			try {

				listadoExportarCadena = request.getParameter("hiddenListaExp") == null ? ValidaConstantes.CADENA_VACIA : request.getParameter("hiddenListaExp").toString().trim();
				numRuc = Utils.toStr(request.getParameter("hiddenNumRuc"));
				razonSocial = Utils.toStr(request.getParameter("hiddenRazonSocial"));
				numExpOrigen = Utils.toStr(request.getParameter("hiddenNumExpOrigen"));
				numExpVirtual = Utils.toStr(request.getParameter("hiddenNumExpVirtual"));
				estExpediente = Utils.toStr(request.getParameter("hiddenEstExpediente"));
				tipoProceso = Utils.toStr(request.getParameter("hiddenTipoProceso"));
				tipoExpediente = Utils.toStr(request.getParameter("hiddenTipoExpediente"));
				descOrigen = Utils.toStr(request.getParameter("hiddenDescOrigen"));
				fechaGeneracion = Utils.toStr(request.getParameter("hiddenFechaGeneracion"));
				fechaOrigen = Utils.toStr(request.getParameter("hiddenFechaOrigen"));
				razonSocialRuc = numRuc + " - " + razonSocial;

				@SuppressWarnings("unchecked")
				List<T6615ObsExpBean> listadoExportar = (ArrayList<T6615ObsExpBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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

				HSSFRow fila = hoja.createRow(1);
				CellRangeAddress rango = null;
				HSSFCell tituloCelda = fila.createCell(0);
				tituloCelda.setCellValue(titulo);
				rango = new CellRangeAddress(1, 1, 0, 2);
				hoja.addMergedRegion(rango);

				HSSFCell nomFecha = fila.createCell(3);
				nomFecha.setCellValue("Fecha del Reporte:");

				HSSFCell Fecha = fila.createCell(4);
				Fecha.setCellValue(fecImpresion);

				fila = hoja.createRow(3);
				CellRangeAddress rango1 = null;
				HSSFCell subtituloCelda = fila.createCell(0);
				subtituloCelda.setCellValue("Datos del Expediente");
				rango1 = new CellRangeAddress(3, 3, 0, 1);
				hoja.addMergedRegion(rango1);

				fila = hoja.createRow(4);
				CellRangeAddress rango2 = null;
				HSSFCell subtituloCelda1 = fila.createCell(1);
				subtituloCelda1.setCellValue("RUC:");
				HSSFCell contenido = fila.createCell(2);
				contenido.setCellValue(razonSocialRuc);
				rango2 = new CellRangeAddress(4, 4, 2, 3);
				hoja.addMergedRegion(rango2);

				fila = hoja.createRow(5);
				HSSFCell subtituloCelda2 = fila.createCell(1);
				subtituloCelda2.setCellValue("N° Expediente:");
				HSSFCell contenido1 = fila.createCell(2);
				contenido1.setCellValue(numExpOrigen);

				fila = hoja.createRow(6);
				HSSFCell subtituloCelda5 = fila.createCell(1);
				subtituloCelda5.setCellValue("Proceso:");
				HSSFCell contenido4 = fila.createCell(2);
				contenido4.setCellValue(tipoProceso);

				HSSFCell subtituloCelda6 = fila.createCell(3);
				subtituloCelda6.setCellValue("Tipo de Expediente:");
				HSSFCell contenido5 = fila.createCell(4);
				contenido5.setCellValue(tipoExpediente);

				fila = hoja.createRow(7);
				HSSFCell subtituloCelda7 = fila.createCell(1);
				subtituloCelda7.setCellValue("Fecha de Generación del Expediente:");
				HSSFCell contenido7 = fila.createCell(2);
				contenido7.setCellValue(fechaGeneracion);

				HSSFCell subtituloCelda8 = fila.createCell(3);
				subtituloCelda8.setCellValue("Fecha de Documento Origen:");
				HSSFCell contenido8 = fila.createCell(4);
				contenido8.setCellValue(fechaOrigen);

				fila = hoja.createRow(8);
				HSSFCell subtituloCelda4 = fila.createCell(1);
				subtituloCelda4.setCellValue("Estado del Expediente:");
				HSSFCell contenido3 = fila.createCell(2);
				contenido3.setCellValue(estExpediente);
				
				HSSFCell subtituloCelda9 = fila.createCell(3);
				subtituloCelda9.setCellValue("Origen:");
				HSSFCell contenido6 = fila.createCell(4);
				contenido6.setCellValue(descOrigen);

				fila = hoja.createRow(10);
				CellRangeAddress rango3 = null;
				HSSFCell subtituloCelda10 = fila.createCell(0);
				subtituloCelda10.setCellValue("Listado de Trazabilidad:");
				rango3 = new CellRangeAddress(10, 10, 0, 2);
				hoja.addMergedRegion(rango3);

				fila = hoja.createRow(11);
				HSSFCell celda = fila.createCell(0);
				HSSFCell celda1 = fila.createCell(1);
				 //Inicio [gangles 22/08/2016]
		        CellRangeAddress rangoObservacion = null;
		        rangoObservacion = new CellRangeAddress(11, 11, 1, 2);
				hoja.addMergedRegion(rangoObservacion);
				 //Fin [gangles 22/08/2016]
				HSSFCell celda2 = fila.createCell(2);
				HSSFCell celda3 = fila.createCell(3);
				//Inicio [gangles 22/08/2016]
				HSSFCell celda4 = fila.createCell(4);
				//Fin [gangles 22/08/2016]
				celda.setCellValue("N°");
				celda1.setCellValue("Observación");
				celda3.setCellValue("Usuario Registro");
				celda4.setCellValue("Fecha de registro");

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
				//Inicio [gangles 22/08/2016]
				celda4.setCellStyle(estiloCelda);
				//Fin [gangles 22/08/2016]

				HSSFCellStyle estiloTitulo = libro.createCellStyle();
				estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				estiloTitulo.setFont(fuente);

				tituloCelda.setCellStyle(estiloTitulo);
				subtituloCelda.setCellStyle(estiloTitulo);
				subtituloCelda1.setCellStyle(estiloTitulo);
				subtituloCelda2.setCellStyle(estiloTitulo);				
				subtituloCelda4.setCellStyle(estiloTitulo);
				subtituloCelda5.setCellStyle(estiloTitulo);
				subtituloCelda6.setCellStyle(estiloTitulo);
				subtituloCelda7.setCellStyle(estiloTitulo);
				subtituloCelda8.setCellStyle(estiloTitulo);
				subtituloCelda9.setCellStyle(estiloTitulo);
				subtituloCelda10.setCellStyle(estiloTitulo);

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

					fila = hoja.createRow(i + 12);
					celda = fila.createCell(0);
					texto = new HSSFRichTextString(listadoExportar.get(i).getNumOrden() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNumOrden().toString().trim());
					celda.setCellValue(texto.toString());
					hoja.autoSizeColumn(0);
					celda.setCellStyle(estiloRecorrido);
					celda1 = fila.createCell(1);
					celda1.setCellValue(listadoExportar.get(i).getDesObservacion() == null ? ValidaConstantes.CADENA_VACIA : Utils.convertirUnicode(listadoExportar.get(i).getDesObservacion().toString().trim()));
					hoja.autoSizeColumn(1);
					celda1.setCellStyle(estiloRecorrido);
					 //Inicio [gangles 22/08/2016]
	   	        	CellRangeAddress rangoObservacionVal = null;
	   	        	rangoObservacionVal = new CellRangeAddress(i + 12, i + 12, 1, 2);
	   	        	hoja.addMergedRegion(rangoObservacionVal);
					celda2 = fila.createCell(2);
					celda2.setCellStyle(estiloRecorrido);
	   			 //Fin [gangles 22/08/2016]
					celda3 = fila.createCell(3);
					celda3.setCellValue(listadoExportar.get(i).getNomUsuGeneraObs() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNomUsuGeneraObs().toString().trim());
					hoja.autoSizeColumn(3);
					celda3.setCellStyle(estiloRecorrido);
					celda4 = fila.createCell(4);
					celda4.setCellValue(listadoExportar.get(i).getFecRegistroObs() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getFecRegistroObs().toString().trim());
					celda4.setCellStyle(estiloRecorrido);
					/* ajustar la demas columnad */
					hoja.autoSizeColumn(4);
					hoja.autoSizeColumn(5);
					hoja.autoSizeColumn(6);
					hoja.autoSizeColumn(7);
					hoja.autoSizeColumn(8);
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

				String filename = "rpt_observaciones_expediente_" + part + ".xls";
				;
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
				String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
				mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar " + ex.getMessage());
				view = new ModelAndView("PagM", "beanM", mensajeBean);
			}
			return view;

		}
	public ModelAndView cargarCopiasEscElec(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarCopiasEscElec");
		ModelAndView modelo;
		List<Map<String, Object>> obtenerCopiasExpediente;	
		HashMap<String, Object> mapParametrosBusqueda;
		try {

			
			if (log.isDebugEnabled()) log.debug("Entro al try");
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
			obtenerCopiasExpediente = new ArrayList<Map<String, Object>>();
			HashMap<String, String> excepciones = new HashMap<String, String>();
			
			log.debug("numCorDoc:"+request.getParameter("numCorDoc"));

			Integer numCorDoc =Integer.parseInt(request.getParameter("numCorDoc"));
			
			//mapParametrosBusqueda = new HashMap<String, Object>();
			modelo = new ModelAndView("jsonView");
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numCorDoc", numCorDoc);
			
			log.debug("numcordoc->c " + numCorDoc);
			obtenerCopiasExpediente = expedienteVirtualService.obtenerCopiasExpediente(mapParametrosBusqueda);
			
			if (log.isDebugEnabled()) log.debug("Entro al servicio");
			
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_017);
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("lstCopExp", new JsonSerializer().serialize(obtenerCopiasExpediente));

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarCopiasEscElec");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarCopiasEscElec");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}
	
	@RequestMapping(value = "/descargarCopiaModal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarCopiaModal(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaExpedientesController.descargarCopiaModal");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os = null;
		String nombreArchivo = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaExpedientesController.descargarCopiaModal");

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			numIdEcm = request.getParameter("codIdentificadorECM").toString().trim();
			mapParametrosBusqueda.put("codIdecm", numIdEcm);
			mapParametrosBusqueda.put("inline", "true");
			nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));

			responseDoc = ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			MediaType contentType = responseDoc.getHeaders().getContentType();
			if (!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) || Utils
			        .equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))) {
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
				response.setHeader(headerKey, headerValue);
			}
			os = response.getOutputStream();
			os.write(responseDoc.getBody());

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.descargarCopiaModal");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.descargarCopiaModal");
			try {
				if (os != null) {
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
	@RequestMapping(value = { "/validarExpediente" }, method = { RequestMethod.GET })
	public ModelAndView validarExpediente(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelo;
		Integer numExp;	
		HashMap<String, Object> mapParametrosBusqueda;
		HashMap<String, Object> mapParam;
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.validarExpediente");

		UsuarioBean usuarioBean = null;

		try {									
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
	
			mapParam = new HashMap<String, Object>();
			String numeroExp = Utils.toStr(request.getParameter("numExp"));
			log.debug("numeroExp->c " + request.getParameter("numExp"));
			String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
			log.debug("flagNumExp->c " + request.getParameter("codTipBusquedaExp"));

			if (Utils.equiv(flagNumExp, ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
				mapParam.put("numExpedVirtual", numeroExp);
				log.debug("numExpedVirtual->c " + numeroExp);
				
			} else {
				mapParam.put("numExpedOrigen", numeroExp);
				log.debug("numExpedOrigen->c " + numeroExp);
			}
			Map<String, Object> nroExpVirtual = new HashMap<String, Object>();	
			nroExpVirtual = expedienteVirtualService.obtenerExpVirtual(mapParam);			
			
			if(nroExpVirtual != null){
				mapParametrosBusqueda = new HashMap<String, Object>();					
				mapParametrosBusqueda.put("codUsuregis", usuarioBean.getNroRegistro());
				mapParametrosBusqueda.put("nroExpVirtual", nroExpVirtual.get("numExpedv").toString());
				log.debug("codUsuregis->c " + usuarioBean.getNroRegistro());
				
				numExp = expedienteVirtualService.obtenerNroExpediente(mapParametrosBusqueda);
				log.debug("numExp->c " + numExp);	
			}else{
				numExp = 999;
				log.debug("numExp->c " + numExp);	
			}
			
			
			if (log.isDebugEnabled()) log.debug("Entro al servicio");
			
			modelo = new ModelAndView("jsonView");	
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_04_001);
			
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
			modelo.addObject("numExpediente", numExp);
			
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.validarExpediente");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.validarExpediente");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	@RequestMapping(value = { "/validarSolicitud" }, method = { RequestMethod.GET })
	public ModelAndView validarSolicitud(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelo;
		String numExp;	
		Integer numSolicitud;
		Map<String, Object> parametrosRegistro = new HashMap<String, Object>();
		HashMap<String, Object> mapParametrosBusquedaResponables;
		HashMap<String, String> excepciones = new HashMap<String, String>();
		HashMap<String, Object> mapParam;
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.validarSolicitud");

		UsuarioBean usuarioBean = null;

		try {	
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
	
			mapParam = new HashMap<String, Object>();			
			log.debug("numeroExp->c " + documento.get("numExpedienteOrigen").toString());
			String numeroExp = Utils.toStr(documento.get("numExpedienteOrigen").toString());

			mapParam.put("numExpedOrigen", numeroExp);
			log.debug("numExpedOrigen->c " + numeroExp);
			
			Map<String, Object> nroExpVirtual = new HashMap<String, Object>();	
			nroExpVirtual = expedienteVirtualService.obtenerExpVirtual(mapParam);
			numExp = (String)nroExpVirtual.get("numExpedv");
			
			parametrosRegistro.put("codUsuRegis", usuarioBean.getNroRegistro());					
			parametrosRegistro.put("nroExpVirtual", numExp);
			
			numSolicitud = expedienteVirtualService.obtenerNroSolicitud(parametrosRegistro);
			log.debug("numSolicitud->c " + numSolicitud);
			
			mapParametrosBusquedaResponables = new HashMap<String, Object>();
			mapParametrosBusquedaResponables.put("numExpedienteVirtual", numExp);
			mapParametrosBusquedaResponables.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			List<T6621RespExpVirtBean> listaResponsables = responsableService.listarResponsables(mapParametrosBusquedaResponables);
			int C = 0;			
			for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
				
				String usuarioLogueado = usuarioBean.getNroRegistro().trim();
				String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
				
				if(usuarioLogueado.equals(responsableExp)){
			
					C++;						
					break;
					
				}
			}
			
			if(C == 0){
				
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
				
			}
		
			
			if (log.isDebugEnabled()) log.debug("Entro al servicio");
			
			modelo = new ModelAndView("jsonView");	
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_04_03_001);
			
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));		
			modelo.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
			modelo.addObject("numSolicitud", numSolicitud);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.validarSolicitud");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.validarSolicitud");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	@SuppressWarnings("resource")
	public ModelAndView generarSolicitud(HttpServletRequest request, HttpServletResponse response){
		
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.generarSolicitud");
		
		ModelAndView modelo = null;
		BufferedReader br = null;	
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		String rutaCompleta = null;		
		Map<String, Object> parametrosRegistro = new HashMap<String, Object>();
		String numSolicitud = null;		
		String numExpe = null;
		String codProc = null;
		String codTipExp = null;
		Map<String, Object> mapParam = null;
	
		
		try {
			log.debug("DocData->c " + request.getParameter("docData"));
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			mapParam = new HashMap<String, Object>();			
			log.debug("numeroExp->c " + documento.get("numExpedienteOrigen").toString());
			String numeroExp = Utils.toStr(documento.get("numExpedienteOrigen").toString());

			mapParam.put("numExpedOrigen", numeroExp);
			log.debug("numExpedOrigen->c " + numeroExp);
			
			Map<String, Object> nroExpVirtual = new HashMap<String, Object>();	
			nroExpVirtual = expedienteVirtualService.obtenerExpVirtual(mapParam);
			numExpe = (String)nroExpVirtual.get("numExpedv");
			codProc = (String)nroExpVirtual.get("codProc");
			codTipExp = (String)nroExpVirtual.get("codTipExp");
			log.debug("nroExpVirtual->c " + nroExpVirtual.get("numExpedv"));
			log.debug("codProc->c " + nroExpVirtual.get("codProc"));
			log.debug("codTipExp->c " + nroExpVirtual.get("codTipExp"));
			
			String codDependenciaBase = usuarioBean.getCodDepend();
			
 			String indCarga = request.getParameter("indCarga");
 			modelo = new ModelAndView(jsonView);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";		
	
				parametrosRegistro.put("codUsuRegis", usuarioBean.getNroRegistro());					
				parametrosRegistro.put("numExpe", numExpe);
				parametrosRegistro.put("codProc", codProc);
				parametrosRegistro.put("codTipExp", codTipExp);
				
				numSolicitud = expedienteVirtualService.registrarSolicitudExpVirt(parametrosRegistro);
				log.debug("numSolicitud->c " + numSolicitud);			

			  /*  Map<String,Object> message=new HashMap<String,Object>();
			    message.put("numSolicitud", numSolicitud);
						 
				if (log.isDebugEnabled()) log.debug("ConsultaReporteController - Inicio de envio a  Cola numero pedido:"+numSolicitud);
				
			    jmsTemplate.convertAndSend(message, new MessagePostProcessor() {
			    	final Log log = LogFactory.getLog(GeneraPedidoReporteController.class);			    	

			    	@Override
					public Message postProcessMessage(Message message) throws JMSException {
			    		 if (log.isDebugEnabled()) log.debug("ConsultaReporteController.MessagePostProcessor - Enviando message");
			    		 //CATALOGO CONSTANTES - GENERAR MENSAJE - ARQUITECTURA
			    		 //message.setStringProperty("application", CatalogoConstantes.SELECTOR_RECURSO_GENERAEXPTRABAJO);
			    		 message.setStringProperty("application", CatalogoConstantes.SELECTOR_RECURSO_GENERAEXPTRABAJO);
			    		 log.debug("message->c " + message);	
						 return message;
					}
				});*/
			    
	
			   //Fin nchavez 13/07/2016
			    modelo.addObject("numSolicitud", numSolicitud);
			    modelo.addObject("codProc", codProc);
			    modelo.addObject("codTipExp", codTipExp);
			   
			} catch (Exception ex) {
				
				if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.generarSolicitud");
				
				log.error(ex, ex);
				
				modelo.addObject("fljError", true);
				modelo.addObject("indicador", 2);
				
			} finally {
				try {
					if(br != null) {
						br.close();
					}					
				
				} catch(Exception ex) {
					log.error(ex, ex);
				}
				
				
				if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.generarSolicitud");
				
				NDC.pop();
				NDC.remove();
			}
			
			return modelo;
		}
	
	@RequestMapping(value = "/cargarExpVirtVinculados", method = RequestMethod.GET)
	public ModelAndView cargarExpVirtVinculados(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.cargarExpVirtVinculados");
		ModelAndView modelo;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		List<T6622SeguimBean> listTrazabilidad = new ArrayList<T6622SeguimBean>();
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();		
		Map<String, Object> params;
		String fechaDocOrigen = "";
		String fechaRegExpediente = "";
		String numExpe = null;
		DdpBean beanContribuyente;
		HashMap<String, Object> mapParametrosBusqueda;
		HashMap<String, Object> mapParametros;
		HashMap<String, Object> mapParametrosBqdaLista;
		HashMap<String, Object> mapParametrosBusquedaResponables;
		List<Map<String, Object>> listaExpVirtVinculados;	
		List<T6614ExpVirtualBean> listaExpVirtuales = new ArrayList<T6614ExpVirtualBean>();	
		String codDepUsuario = "";
		
		try {
			Map<String, Object> documento = (Map<String, Object>) new JsonSerializer().deserialize(request.getParameter("docData"));
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {
				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
			}

			String numExpedienteVirtual = Utils.toStr(documento.get("numExpedienteVirtual").toString());
			modelo = new ModelAndView(jsonView);
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", numExpedienteVirtual);		
			listaExpVirtVinculados = expedienteVirtualService.obtenerNumExpVirVinc(mapParametrosBusqueda);
			
			log.debug("numExpedVirtual-Antes : "+listaExpVirtVinculados);
			List<T6614ExpVirtualBean> listaResultado = new ArrayList<T6614ExpVirtualBean>();	
		
			  for(Map<String, Object> mapT10460 : listaExpVirtVinculados){
				mapParametrosBqdaLista = new HashMap<String, Object>();
				numExpe = (String)mapT10460.get("numExpedv");
				mapParametrosBqdaLista.put("numExpedVirtual", numExpe);
				log.debug("numExpedVirtual-Des : "+numExpe);
				
				mapParametrosBqdaLista.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				
					if (!Utils.isEmpty(numExpe) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
						codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
					} else if(Utils.isEmpty(numExpe)){
						codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
					}
	
					if(!Utils.isEmpty(codDepUsuario)){
						mapParametrosBqdaLista.put("codDependencia", codDepUsuario);
					}
			
					listaResultado.addAll(expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBqdaLista));
	
			  } 
			    modelo.addObject("lstExpVinc", new JsonSerializer().serialize(listaResultado));
		
				mapParametros = new HashMap<String, Object>();	
				mapParametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				mapParametros.put("numExpedVirtual", numExpedienteVirtual);
				if (!Utils.isEmpty(numExpe) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
					codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
				} else if(Utils.isEmpty(numExpe)){
					codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				}

				if(!Utils.isEmpty(codDepUsuario)){
					mapParametros.put("codDependencia", codDepUsuario);
				}
				
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametros);
				
				log.debug("ListaT6614 : "+listaExpedientesVirtuales);
			
			for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
				beanContribuyente = validarParametrosService.validarRUC(t6614ExpVirtualBean.getNumRuc());
				fechaDocOrigen = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFechaDocumentoOrigen());
				fechaRegExpediente = Utils.dateUtilToStringDDMMYYYY(t6614ExpVirtualBean.getFecRegistro());

				modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fechaDocOrigen));
				modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fechaRegExpediente));
				modelo.addObject("razonSocial", new JsonSerializer().serialize(beanContribuyente.getDesRazonSocial()));
				modelo.addObject("datosExpedientes", new JsonSerializer().serialize(t6614ExpVirtualBean));
				break;
			}
			HashMap<String, String> excepciones = new HashMap<String, String>();
			//PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
			String tipoExp = listaExpedientesVirtuales.get(0).getCodTipoExpediente();
						
			Map<String, Object> tipoExpediente = new HashMap<String, Object>();
			tipoExpediente.put("tipoExp",tipoExp);
			modelo.addObject("tipoExp", new JsonSerializer().serialize(tipoExpediente));
			
			if (ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(tipoExp) || 
				ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(tipoExp)){
				
				mapParametrosBusquedaResponables = new HashMap<String, Object>();
				mapParametrosBusquedaResponables.put("numExpedienteVirtual", numExpedienteVirtual);
				mapParametrosBusquedaResponables.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				List<T6621RespExpVirtBean> listaResponsables = responsableService.listarResponsables(mapParametrosBusquedaResponables);
				int C = 0;			
				for (T6621RespExpVirtBean t6621RespExpVirtBean : listaResponsables){
					
					String usuarioLogueado = usuarioBean.getNroRegistro().trim();
					String responsableExp = t6621RespExpVirtBean.getCodColaborador().trim();
					
					if(usuarioLogueado.equals(responsableExp)){
				
						C++;						
						break;
						
					}
				}
				
				if(C == 0){
					
					excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_04_001);
					
				}
			}		
			//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA

			params = new HashMap<String, Object>();
			params.put("numExpedVirtual", numExpedienteVirtual);
			params.put("codTipSeguim", ValidaConstantes.IND_TIP_SEG_EE);
			listTrazabilidad = seguiService.listarSeguimientos(params);
			
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_01_017);
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));

			Utils.agregarEstadoExpedienteModelo(listTrazabilidad, modelo);

			registrarSeguimientoVerEstadoEtapaExpediente(numExpedienteVirtual);
			
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaReporteController.cargarExpVirtVinculados");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaReporteController.cargarExpVirtVinculados");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
		
	}
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelAccesoExp(HttpServletRequest request, HttpServletResponse response) {

		String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		//Inicio [gangles 22/08/2016] 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = new Date();
		String fecImpresion = sdf.format(fechaActual);
		//Fin [gangles 22/08/2016] 
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarExcelAccesoExp");

		try {

			listadoExportarCadena = Utils.toStr(request.getParameter("listadoExpedientesCadena"));

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

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
			hoja.setColumnWidth(9, 16000);
			hoja.setColumnWidth(10, 16000);
			hoja.setColumnWidth(11, 16000);
			hoja.setColumnWidth(12, 16000);
			hoja.setColumnWidth(13, 16000);
	        //inicio JEFFIO [13/03/2017]
			hoja.setColumnWidth(14, 16000);
			hoja.setColumnWidth(15, 16000);
	        //fin JEFFIO [13/03/2017]

			HSSFRow fila = hoja.createRow(1);
			HSSFCell tituloCelda = fila.createCell(0);
			tituloCelda.setCellValue(titulo);
			hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 3));

			fila = hoja.createRow(4);
			HSSFCell celda = fila.createCell(0);
			HSSFCell celda1 = fila.createCell(1);
			HSSFCell celda2 = fila.createCell(2);
			HSSFCell celda3 = fila.createCell(3);
			HSSFCell celda4 = fila.createCell(4);
			HSSFCell celda5 = fila.createCell(5);
			HSSFCell celda6 = fila.createCell(6);
			HSSFCell celda7 = fila.createCell(7);
			HSSFCell celda8 = fila.createCell(8);
			HSSFCell celda9 = fila.createCell(9);	
	        
			celda.setCellValue("N°");
			celda1.setCellValue("N° de Expediente");
			celda2.setCellValue("Profesional Responsable");
			celda3.setCellValue("RUC");			
			celda4.setCellValue("Razón Social");
			celda5.setCellValue("Proceso");
			celda6.setCellValue("Tipo de Expediente");
			celda7.setCellValue("Estado expediente");
			celda8.setCellValue("Fecha de Expediente");
			celda9.setCellValue("Origen");	
	
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
			ssheet.autoSizeColumn(8);
			ssheet.autoSizeColumn(9);

			
			 //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
	     	fila = hoja.createRow(2);
	     	HSSFCell celdaFecha = fila.createCell(1);
	     	celdaFecha.setCellValue("Fecha del Reporte:");
	     	HSSFCell celdaValFecha = fila.createCell(2);
	     	celdaValFecha.setCellValue(fecImpresion);
	     	//Fin [gangles 22/08/2016]
	     	
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
			celda8.setCellStyle(estiloCelda);
			celda9.setCellStyle(estiloCelda);

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
				String numExpedienteAcumulador = Utils.toStr(listadoExportar.get(i).get("numExpedienteAcumulador"));
				if (Utils.equiv(numExpedienteAcumulador, ValidaConstantes.FILTRO_CERO) || Utils.isEmpty(numExpedienteAcumulador)) {
					numExpedienteAcumulador = "";
				}
				fila = hoja.createRow(i + 5);
				celda = fila.createCell(0);
				texto = new HSSFRichTextString(Utils.toStr(listadoExportar.get(i).get("numOrden")));
				celda.setCellValue(texto.toString());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estiloRecorrido);
				celda1 = fila.createCell(1);
				celda1.setCellValue(Utils.toStr(listadoExportar.get(i).get("numExpedienteOrigen")));
				hoja.autoSizeColumn(1);
				celda1.setCellStyle(estiloRecorrido);
				celda2 = fila.createCell(2);
				celda2.setCellValue(Utils.toStr(listadoExportar.get(i).get("nombreResponsable")));
				hoja.autoSizeColumn(2);
				celda2.setCellStyle(estiloRecorrido);				
				celda3 = fila.createCell(3);				
				celda3.setCellValue(Utils.toStr(listadoExportar.get(i).get("numRuc")));			
				hoja.autoSizeColumn(3);
				celda3.setCellStyle(estiloRecorrido);
				celda4 = fila.createCell(4);				
				celda4.setCellValue(Utils.toStr(listadoExportar.get(i).get("desRazonSocial")));				
				hoja.autoSizeColumn(4);
				celda4.setCellStyle(estiloRecorrido);
				celda5 = fila.createCell(5);				
				celda5.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desProceso").toString()));
				hoja.autoSizeColumn(5);
				celda5.setCellStyle(estiloRecorrido);
				celda6 = fila.createCell(6);			
				celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString()));			
				hoja.autoSizeColumn(6);
				celda6.setCellStyle(estiloRecorrido);
				celda7 = fila.createCell(7);				
				celda7.setCellValue(Utils.convertirUnicode((String) listadoExportar.get(i).get("desEstado")));			
				hoja.autoSizeColumn(7);
				celda7.setCellStyle(estiloRecorrido);
				celda8 = fila.createCell(8);				
				celda8.setCellValue(Utils.toStr(listadoExportar.get(i).get("fechaDocumentoOrigen")));				
				hoja.autoSizeColumn(8);
				celda8.setCellStyle(estiloRecorrido);
				celda9 = fila.createCell(9);			
				celda9.setCellValue(Utils.toStr(listadoExportar.get(i).get("desOrigen")));			
				hoja.autoSizeColumn(9);
				celda9.setCellStyle(estiloRecorrido);
				
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

			String filename = "rpt_expediente_virtual_" + part + ".xls";
			;
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
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - ConsultaReporteController.exportarExcelAccesoExp");
			}

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			view = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			view.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - ConsultaReporteController.exportarExcelAccesoExp");
			}
			NDC.pop();
			NDC.remove();
		}

		return view;
	}

	@RequestMapping(value = "/obtenerAccesoExpVirt", method = RequestMethod.GET)
	public ModelAndView obtenerAccesoExpVirt(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio -  ConsultaReporteController.obtenerAccesoExpVirt");

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();		
		int cantExpAbiertos = 0;
		int cantExpCerrados = 0;
		int cantTotalExp = 0;
		String codEstadoExp;

		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

		
			String numExp = Utils.toStr(request.getParameter("numExp"));
			String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		
			String codDepUsuario = "";
			if (!Utils.isEmpty(numExp)) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else {
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				log.debug("codDepUsuario1 : "+codDepUsuario);
			}
			
			if (!Utils.isEmpty(numExp) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else if(Utils.isEmpty(numExp)){
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				log.debug("codDepUsuario2 : "+codDepUsuario);
			}

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			if(!Utils.isEmpty(codDepUsuario)){
				mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				log.debug("codDependencia : "+codDepUsuario);
				}


			// validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				mapParametrosBusqueda.put("numExpedOrigen", numExp);
				mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				log.debug("numExpedOrigen : "+numExp);
	
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp, ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_04_04_023);
					}
					return modelAndView;
				} else {
					// Inicio [jquispe 27/05/2016] Parametro del numero expediente virtual para registrar el seguimiento.
					String parametroNumeroExpedienteVirtual = listaExpedientesVirtuales.get(0).getNumExpedienteVirtual();
					// Fin [jquispe 27/05/2016]

					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						codEstadoExp = t6614ExpVirtualBean.getCodEstado();
						if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
							cantExpAbiertos = cantExpAbiertos + 1;
						} else {
							cantExpCerrados = cantExpCerrados + 1;
						}
						cantTotalExp = cantExpCerrados + cantExpAbiertos;
					}

					// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
					registrarSeguimientoConsultaReporteExpediente(parametroNumeroExpedienteVirtual);
					// Fin [jquispe 27/05/2016]
					modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
					modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
					modelAndView.addObject("cantExpCerrados", cantExpCerrados);
					modelAndView.addObject("cantTotalExp", cantTotalExp);
					return modelAndView;
				}
			}

			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {

				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					codEstadoExp = t6614ExpVirtualBean.getCodEstado();
					if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
						cantExpAbiertos = cantExpAbiertos + 1;
					} else {
						cantExpCerrados = cantExpCerrados + 1;
					}
					cantTotalExp = cantExpCerrados + cantExpAbiertos;
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoConsultaReporteExpediente(null);
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
				modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
				modelAndView.addObject("cantExpCerrados", cantExpCerrados);
				modelAndView.addObject("cantTotalExp", cantTotalExp);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaReporteController.obtenerAccesoExpVirt");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - ConsultaReporteController.obtenerAccesoExpVirt");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelVinculados(HttpServletRequest request, HttpServletResponse response) {

			String titulo = ExportaConstantes.TITULO_EXPORTA_04_05;
			ModelAndView view = null;
			MensajeBean mensajeBean = new MensajeBean();
			String listadoExportarCadena = null;
			//Inicio [gangles 22/08/2016] 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fechaActual = new Date();
			String fecImpresion = sdf.format(fechaActual);
			//Fin [gangles 22/08/2016] 
			if (log.isDebugEnabled()) log.debug("Inicio - ConsultaReporteController.exportarExcelVinculados");

			try {

				listadoExportarCadena = Utils.toStr(request.getParameter("hiddenListaExp"));
				log.debug("listadoExportarCadena : "+request.getParameter("hiddenListaExp"));

				@SuppressWarnings("unchecked")
				List<T6614ExpVirtualBean> listadoExportar = (ArrayList<T6614ExpVirtualBean>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);
				log.debug("listadoExportarBean : "+listadoExportar);
				
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
				hoja.setColumnWidth(9, 16000);


				HSSFRow fila = hoja.createRow(1);
				HSSFCell tituloCelda = fila.createCell(0);
				tituloCelda.setCellValue(titulo);
				hoja.addMergedRegion(new Region(1, (short) 0, 1, (short) 3));

				fila = hoja.createRow(4);
				HSSFCell celda = fila.createCell(0);
				HSSFCell celda1 = fila.createCell(1);
				HSSFCell celda2 = fila.createCell(2);
				HSSFCell celda3 = fila.createCell(3);
				HSSFCell celda4 = fila.createCell(4);
				HSSFCell celda5 = fila.createCell(5);
				HSSFCell celda6 = fila.createCell(6);
				HSSFCell celda7 = fila.createCell(7);
				HSSFCell celda8 = fila.createCell(8);
				HSSFCell celda9 = fila.createCell(9);

		        
				celda.setCellValue("N°");
				celda1.setCellValue("N° de Expediente");
				celda2.setCellValue("Responsable");				
				celda3.setCellValue("RUC");
				celda4.setCellValue("Razón Social");
				celda5.setCellValue("Proceso");
				celda6.setCellValue("Tipo de Expediente");
				celda7.setCellValue("Estado expediente");				
				celda8.setCellValue("Fecha de Expediente");				
				celda9.setCellValue("Origen");

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
				ssheet.autoSizeColumn(8);
				ssheet.autoSizeColumn(9);

				
				 //Inicio [gangles 22/08/2016]-Se agrega fecha del reporte  
		     	fila = hoja.createRow(2);
		     	HSSFCell celdaFecha = fila.createCell(1);
		     	celdaFecha.setCellValue("Fecha del Reporte:");
		     	HSSFCell celdaValFecha = fila.createCell(2);
		     	celdaValFecha.setCellValue(fecImpresion);
		     	//Fin [gangles 22/08/2016]
		     	
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
				celda8.setCellStyle(estiloCelda);
				celda9.setCellStyle(estiloCelda);


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
				log.debug("listadoExportar : "+listadoExportar);
				for (int i = 0; i < listadoExportar.size(); i++) {
					log.debug("listadoExportar For : "+listadoExportar.get(i).getNumOrden());
					   fila = hoja.createRow(i + 5);
		               celda = fila.createCell(0);
		               texto = new HSSFRichTextString(listadoExportar.get(i).getNumOrden() == null ? ValidaConstantes.CADENA_VACIA : listadoExportar.get(i).getNumOrden().toString().trim());
		               celda.setCellValue(texto.toString());
		               hoja.autoSizeColumn(0);
		               celda.setCellStyle(estiloRecorrido);
		               celda1 = fila.createCell(1);
		               celda1.setCellValue(Utils.toStr(listadoExportar.get(i).getNumExpedienteOrigen()));
		               hoja.autoSizeColumn(1);
		               celda1.setCellStyle(estiloRecorrido);              
		               celda2 = fila.createCell(2);
		               celda2.setCellValue(Utils.toStr(listadoExportar.get(i).getNombreResponsable()));
		               hoja.autoSizeColumn(2);
		               celda2.setCellStyle(estiloRecorrido);
		               celda3 = fila.createCell(3);
		               celda3.setCellValue(Utils.toStr(listadoExportar.get(i).getNumRuc()));
		               hoja.autoSizeColumn(3);
		               celda3.setCellStyle(estiloRecorrido);
		               // Inicio [jquispe 10/06/2016] Se modifico para agregar la nueva columna Razon Social.
		               celda4 = fila.createCell(4);
		               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).getDesRazonSocial()).toString());
		               hoja.autoSizeColumn(4);
		               celda4.setCellStyle(estiloRecorrido);
		               celda5 = fila.createCell(5);
		               celda5.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).getDesProceso()));
		               hoja.autoSizeColumn(5);
		               celda5.setCellStyle(estiloRecorrido);
		               celda6 = fila.createCell(6);
		               celda6.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).getDesTipoExpediente()));
		               hoja.autoSizeColumn(6);
		               celda6.setCellStyle(estiloRecorrido);               
		               celda7 = fila.createCell(7);
		               celda7.setCellValue(Utils.toStr(listadoExportar.get(i).getDesEstado()));
		               hoja.autoSizeColumn(7);               
		               celda7.setCellStyle(estiloRecorrido);               
		               celda8 = fila.createCell(8);
		               celda8.setCellValue(Utils.toStr(listadoExportar.get(i).getFechaDocumentoOrigen()));
		               hoja.autoSizeColumn(8);
		               celda8.setCellStyle(estiloRecorrido);               
		               celda9 = fila.createCell(9);
		               celda9.setCellValue(Utils.convertirUnicode((String)listadoExportar.get(i).getDesOrigen()));
		               hoja.autoSizeColumn(9);
		               celda9.setCellStyle(estiloRecorrido);
		             
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

				String filename = "rpt_expediente_virtual_" + part + ".xls";
				;
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
				if (log.isDebugEnabled()) {
					log.debug((Object) "Error - ConsultaReporteController.exportarExcelVinculados");
				}

				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				view = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				view.addObject("beanErr", msb);
			} finally {
				if (log.isDebugEnabled()) {
					log.debug((Object) "Final - ConsultaReporteController.exportarExcelVinculados");
				}
				NDC.pop();
				NDC.remove();
			}

			return view;
		}
	@RequestMapping(value = "/obtenerAccesoTotalExpVirt", method = RequestMethod.GET)
	public ModelAndView obtenerAccesoTotalExpVirt(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio -  ConsultaReporteController.obtenerAccesoTotalExpVirt");

		ModelAndView modelAndView = null;
		List<T6614ExpVirtualBean> listaExpedientesVirtuales = new ArrayList<T6614ExpVirtualBean>();		
		int cantExpAbiertos = 0;
		int cantExpCerrados = 0;
		int cantTotalExp = 0;
		String codEstadoExp;

		try {

			UsuarioBean usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

		
			String numExp = Utils.toStr(request.getParameter("numExp"));
			String flagNumExp = Utils.toStr(request.getParameter("codTipBusquedaExp"));
		
			String codDepUsuario = "";
			if (!Utils.isEmpty(numExp)) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else {
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				log.debug("codDepUsuario1 : "+codDepUsuario);
			}
			
			if (!Utils.isEmpty(numExp) && !Utils.isEmpty(usuarioBean.getCodDepend())) {
				codDepUsuario = usuarioBean.getCodDepend().substring(0, 3);
			} else if(Utils.isEmpty(numExp)){
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				log.debug("codDepUsuario2 : "+codDepUsuario);
			}

			modelAndView = new ModelAndView(jsonView);

			Map<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			if(!Utils.isEmpty(codDepUsuario)){
				//mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				log.debug("codDependencia : "+codDepUsuario);
				}


			// validamos numero expediente virtual
			if (!Utils.isEmpty(numExp)) {
				codDepUsuario = Utils.toStr(request.getParameter("codDependencia"));
				mapParametrosBusqueda.put("numExpedOrigen", numExp);
				//mapParametrosBusqueda.put("codDependencia", codDepUsuario);
				log.debug("numExpedOrigen : "+numExp);
				log.debug("mapParametrosBusqueda-C : "+mapParametrosBusqueda);
				listaExpedientesVirtuales = expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

				if (Utils.isEmpty(listaExpedientesVirtuales)) {
					if (Utils.equiv(flagNumExp, ValidaConstantes.BUSQUEDA_POR_EXPEDIENTE_VIRTUAL)) {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_008_02);
					} else {
						modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_04_04_001);
					}
					return modelAndView;
				} else {
					// Inicio [jquispe 27/05/2016] Parametro del numero expediente virtual para registrar el seguimiento.
					String parametroNumeroExpedienteVirtual = listaExpedientesVirtuales.get(0).getNumExpedienteVirtual();
					// Fin [jquispe 27/05/2016]

					for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
						codEstadoExp = t6614ExpVirtualBean.getCodEstado();
						if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
							cantExpAbiertos = cantExpAbiertos + 1;
						} else {
							cantExpCerrados = cantExpCerrados + 1;
						}
						cantTotalExp = cantExpCerrados + cantExpAbiertos;
					}

					// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
					registrarSeguimientoConsultaReporteExpediente(parametroNumeroExpedienteVirtual);
					// Fin [jquispe 27/05/2016]
					modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
					modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
					modelAndView.addObject("cantExpCerrados", cantExpCerrados);
					modelAndView.addObject("cantTotalExp", cantTotalExp);
					return modelAndView;
				}
			}

			listaExpedientesVirtuales = this.expedienteVirtualService.obtenerListaExpedienteVirtual(mapParametrosBusqueda);

			if (Utils.isEmpty(listaExpedientesVirtuales)) {
				modelAndView.addObject("msjError", AvisoConstantes.EXCEP_MODULO_02_01_005);
			} else {

				for (T6614ExpVirtualBean t6614ExpVirtualBean : listaExpedientesVirtuales) {
					codEstadoExp = t6614ExpVirtualBean.getCodEstado();
					if (codEstadoExp.equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)) {
						cantExpAbiertos = cantExpAbiertos + 1;
					} else {
						cantExpCerrados = cantExpCerrados + 1;
					}
					cantTotalExp = cantExpCerrados + cantExpAbiertos;
				}
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
				registrarSeguimientoConsultaReporteExpediente(null);
				// Fin [jquispe 27/05/2016]
				modelAndView.addObject("listaExpedientesVirtuales", listaExpedientesVirtuales);
				modelAndView.addObject("cantExpAbiertos", cantExpAbiertos);
				modelAndView.addObject("cantExpCerrados", cantExpCerrados);
				modelAndView.addObject("cantTotalExp", cantTotalExp);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug("Error - ConsultaReporteController.obtenerAccesoTotalExpVirt");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("Final - ConsultaReporteController.obtenerAccesoTotalExpVirt");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
		
//fin LLRB
	/***/
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setObservacionExpedienteVirtualService(ObservacionExpedienteVirtualService observacionExpedienteVirtualService) {
		this.observacionExpedienteVirtualService = observacionExpedienteVirtualService;
	}

	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setParamService(ParametroService paramService) {
		this.paramService = paramService;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setReportesIndicadoresService(ReportesIndicadoresService reportesIndicadoresService) {
		this.reportesIndicadoresService = reportesIndicadoresService;
	}

	// Inicio [jquispe 30/05/2016]
	public void setIndiceExpedienteVirtualService(IndiceExpedienteVirtualService indiceExpedienteVirtualService) {
		this.indiceExpedienteVirtualService = indiceExpedienteVirtualService;
	}
	//Fin [jquispe 30/05/2016]representacionImpresaService
	
	//Inicio [jjurado 19/08/2016]
	public void setRepresentacionImpresaService(RepresentacionImpresaService representacionImpresaService) {
		this.representacionImpresaService = representacionImpresaService;
	}
	//Fin [jquispe 19/08/2016]

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
    //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA
	//INICIO LLRB 03/02/2020
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	//FIN LLRB 03/02/2020

}
