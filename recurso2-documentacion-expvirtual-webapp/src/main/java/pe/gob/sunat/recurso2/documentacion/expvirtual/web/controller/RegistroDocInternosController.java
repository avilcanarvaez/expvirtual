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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.BeanParametrosConsultaReq;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocNotSineBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CobranzaCoactivaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.SeguimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.service.ResolucionService;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;
//INICIO LLRB 20012020
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10458VersDocAdjBean;
//FIN LLRB 20012020


public class RegistroDocInternosController extends MultiActionController {
	
private static final Log log = LogFactory.getLog(RegistroDocInternosController.class);
	
	private JsonView jsonView;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ParametroService paramService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private EcmService ecmService;
	private CatalogoService catalogoService;
	private DocumentoExpedienteService documentoExpedienteService;
	// Inicio [jquispe 27/05/2016]
	private SeguimientoService seguiService;
	// Fin [jquispe 27/05/2016]
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	private ResolucionService resolucionService;//[oachahuic][PAS20165E210400270]
	private ResponsableService responsableService;
	private CobranzaCoactivaService cobranzaCoactivaService;


	public ModelAndView consultarProcesos (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.consultarProcesos");
		
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
			
			//usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			String codDependencia = usuarioBean.getCodDepend();
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
			
				Map<String, Object> titulos = new HashMap<String, Object>();
				
				String codDepUsuario = usuarioBean.getCodDepend();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_03_01_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				String errorRango = AvisoConstantes.EXCEP_MODULO_03_01_005;
				errorRango = errorRango.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, AvisoConstantes.RANGO_FECHA_COMPARA);
				
				String excepcion23 = AvisoConstantes.EXCEP_MODULO_03_01_023;
				excepcion23 = excepcion23.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, AvisoConstantes.RANGO_FECHA_COMPARA);
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_03_01_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_03_01_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_03_01_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_03_01_004);
				excepciones.put("excepcion05", errorRango);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_03_01_006);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_03_01_007);
				excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_03_01_008);
				excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_03_01_013);
				excepciones.put("excepcion15", AvisoConstantes.EXCEP_MODULO_03_01_015);
				excepciones.put("excepcion16", AvisoConstantes.EXCEP_MODULO_03_01_016);
				excepciones.put("excepcion17", AvisoConstantes.EXCEP_MODULO_03_01_017);
				excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_03_01_018);
				excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_03_01_019);
				excepciones.put("excepcion20", AvisoConstantes.EXCEP_MODULO_03_01_020);
				excepciones.put("excepcion21", AvisoConstantes.EXCEP_MODULO_03_01_021);
				excepciones.put("excepcion22", AvisoConstantes.EXCEP_MODULO_03_01_022);
				excepciones.put("excepcion23", excepcion23);
				excepciones.put("excepcion24", AvisoConstantes.EXCEP_MODULO_03_01_024);
				excepciones.put("excepcion25", AvisoConstantes.EXCEP_MODULO_03_01_025);
				excepciones.put("excepcion26", AvisoConstantes.EXCEP_MODULO_03_01_026);
				
				//eaguilar: 21 JUN NUEVA EXCEPCION
				excepciones.put("excepcion31", AvisoConstantes.EXCEP_MODULO_03_01_031);
				 //Inicio [gangles 23/08/2016]
				excepciones.put("excepcion32", AvisoConstantes.EXCEP_MODULO_03_01_032);
				 //Fin [gangles 23/08/2016]
				excepciones.put("rangoFecha", AvisoConstantes.RANGO_FECHA_COMPARA);
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
				
				List<T01ParamBean> listadoDependencias = new ArrayList<T01ParamBean>();
				//Inicio - [oachahuic][PAS20165E210400270]
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu == null || mapDepAdu.isEmpty()) {
					List<T01ParamBean> listadoDependenciasIni = configuracionExpedienteService.listarDependencias();
					if (!Utils.isEmpty(listadoDependenciasIni)) {
						for (T01ParamBean t01ParamBean : listadoDependenciasIni) {
							if (Utils.isEmpty(codDepUsuario)||(Utils.equiv(t01ParamBean.getCodParametro().substring(0, 3), usuarioBean.getCodDepend().substring(0, 3))
							        && (t01ParamBean.getCodParametro().endsWith("1") || t01ParamBean.getCodParametro().endsWith("3")))) {
								t01ParamBean.setDesParametro(t01ParamBean.getCodParametro() + " - " + t01ParamBean.getDesParametro());
								listadoDependencias.add(t01ParamBean);
							}
						}
					}
				} else {
					T01ParamBean t01ParamBean = new T01ParamBean();
					t01ParamBean.setCodParametro(mapDepAdu.get("ADUANA").toString().trim());
					t01ParamBean.setDesParametro(mapDepAdu.get("ADUANA").toString().trim() + " - " + mapDepAdu.get("DEPEN2").toString().trim());
					listadoDependencias.add(t01ParamBean);
				}
				//Fin - [oachahuic][PAS20165E210400270]

				List<T01ParamBean> listadoNumeroTipoExpediente = paramService.listarNumeroTipoExpediente();
				
				List<T01ParamBean> listadoTipoFecha = paramService.listarTipoFecha();
				
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_03_01_001);
				
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				modelo.addObject("listDependencias",new JsonSerializer().serialize(listadoDependencias));
				modelo.addObject("listadoNumeroTipoExpediente",new JsonSerializer().serialize(listadoNumeroTipoExpediente));
				modelo.addObject("listadoTipoFecha",new JsonSerializer().serialize(listadoTipoFecha));
				//Inicio - [oachahuic][PAS20165E210400270]
				if (mapDepAdu == null || mapDepAdu.isEmpty()) {
					modelo.addObject("codDepUsuario",new JsonSerializer().serialize(codDepUsuario.trim()));
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
				} else {
					modelo.addObject("codDepUsuario",new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
				}
				//Fin - [oachahuic][PAS20165E210400270]
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
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.consultarProcesos");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.consultarProcesos");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
	@RequestMapping(value = "/removerDatosBusquedaSession", method = RequestMethod.GET)
	public ModelAndView removerDatosBusquedaSession(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView;

		if (log.isDebugEnabled())log.info((Object) "Inicio - RegistroDocInternosController.cargarDatosBusquedaSession");
		
		try {
			
			modelAndView = new ModelAndView(jsonView);
			request.getSession().removeAttribute("beanParametrosConsultaReq");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistroDocInternosController.cargarDatosBusquedaSession");
			}
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView("PaginaError");
			msb.setError(true);
			msb.setMensajeerror("Error al ingresar a la opción.");
			modelAndView.addObject("beanErr", (Object) msb);
		} finally {

			if (log.isDebugEnabled()) {
				log.debug( "Final - RegistroDocInternosController.cargarDatosBusquedaSession");
			}
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;
	}
	
	public ModelAndView cargarListaExpedientes (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.cargarListaExpedientes");
		
		ModelAndView modelo = null;		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		boolean flagBusquedaBoton = false;
		try {
			
			String indCarga = request.getParameter("indCarga");			
			flagBusquedaBoton = Boolean.parseBoolean(Utils.toStr(request.getParameter("flagBusquedaBoton")));
			String numAcumulador = Utils.toStr(request.getParameter("numAcumulador"));
			List<T6614ExpVirtualBean> listaExpedientesVirtualesTemp = new ArrayList<T6614ExpVirtualBean>();
			String indClaseExpediente=ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL;
			
			//SESSION AUTHENTICACION
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);				
			}else{				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");				
			}
			
			String nroRegistro = usuarioBean.getNroRegistro();
			
			//LECTURA DEL REQUEST BODY
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";			
			if(reader != null) {				
				jsonEnviado = reader.readLine();				
			}
			
			@SuppressWarnings("unchecked")
			
			Map<String, Object> datosBusquedaExpedientes = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			List<T6614ExpVirtualBean> listadoExpendientes = new ArrayList<T6614ExpVirtualBean>();
			
			Map<String, Object> params =  new HashMap<String, Object>();
			params.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {//0
				
				String codNumTipoExp = datosBusquedaExpedientes.get("codNumTipoExp").toString();
				if(codNumTipoExp.equals("1")){
					params.put("numExpedOrigen", datosBusquedaExpedientes.get("numExpediente").toString());
					indClaseExpediente=ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN;
				}else if(codNumTipoExp.equals("2")){
					params.put("numExpedVirtual", datosBusquedaExpedientes.get("numExpediente").toString());
					indClaseExpediente=ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL;
				}
				
				modelo = new ModelAndView(jsonView);
				
				//TODO - IU001 VERIFICAR SI EXISTE EL NUMERO EXPEDIENTE INGRESADO EN BD
				listadoExpendientes = expedienteVirtualService.listarRegDocExpediente(params);
				//INICIO LLRB 14022020
				String codTipoExp = listadoExpendientes.get(0).getCodTipoExpediente();	
				modelo.addObject("codTipoExp", codTipoExp);
				
				log.debug("listadoExpendientes-Inicio : "+listadoExpendientes.get(0).getCodTipoExpediente());
				log.debug("codTipoExp : "+codTipoExp);
				//FIN LLRB 14022020
				
				if (Utils.isEmpty(listadoExpendientes)) {
					modelo.addObject("status", false);
					modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_003);
					return modelo;
				}
				
				//TODO - IU001 VERIFICAR SI EL EXPEDIENTE PERTENECE A LA DEPENDENCIA DEL USUARIO EN SESIÓN	
				
				if (!Utils.equiv(datosBusquedaExpedientes.get("codDependenciaBase").toString().trim().substring(0, 3), listadoExpendientes.get(0).getCodDependencia().substring(0, 3))) {
					modelo.addObject("status", false);
					modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_004);
					return modelo;
				}
				
				//TODO - IU001 VERIFICAR SI ES RESPONSABLE DEL EXPEDIENTE				
				HashMap<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("num_expedv_par", listadoExpendientes.get(0).getNumExpedienteVirtual());
				parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
				parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				
				T6621RespExpVirtBean t6621Bean = responsableService.obtenerResponsable(parametros);			
				if (t6621Bean == null) {
					modelo.addObject("status", false);
					modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_005);
					return modelo;
				}
				
				//TODO - IU005 VERIFICAR SI SE ENCUENTRA ACTIVO
				if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO.equals(listadoExpendientes.get(0).getCodEstado())) {
					modelo.addObject("status", false);
					modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_006);
					return modelo;
				}
				
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
				if(!Utils.isEmpty(listadoExpendientes)){
					if(flagBusquedaBoton){
						T6614ExpVirtualBean bean = listadoExpendientes.get(0);
						registrarSeguimientoConsultaDocumentosInternos(bean.getNumExpedienteVirtual());
					}// Fin [jquispe 27/05/2016]
						
				}
				
				modelo.addObject("status", true);
				modelo.addObject("message",listadoExpendientes.size() + " Registros Encontrado(s)");
				modelo.addObject("listadoExpendientes", listadoExpendientes);
				
				return modelo;
				
			}
			
			params.put("codEstado", ValidaConstantes.IND_ESTADO_REQ_ABIERTO);
			params.put("codColab", nroRegistro);
			
			if(ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)){//1
				params.put("codDependencia", datosBusquedaExpedientes.get("codDependencia").toString());
				modelo = new ModelAndView(jsonView);
				if(datosBusquedaExpedientes.get("flagPorRUC") != null){
					params.put("numeroRuc", datosBusquedaExpedientes.get("numRuc").toString());
					if(datosBusquedaExpedientes.get("codProceso") != null && !datosBusquedaExpedientes.get("codProceso").toString().equals("")) params.put("codProceso", datosBusquedaExpedientes.get("codProceso").toString());
					if(datosBusquedaExpedientes.get("codTipoExpe") != null && !datosBusquedaExpedientes.get("codTipoExpe").toString().equals("")) params.put("codTipExpediente", datosBusquedaExpedientes.get("codTipoExpe").toString());
									
					if(datosBusquedaExpedientes.get("codTipoFecha") != null){
						String codTipoFecha = Utils.toStr(datosBusquedaExpedientes.get("codTipoFecha"));
						String strDateDesde = Utils.toStr(datosBusquedaExpedientes.get("fechaDesde"));
						String strDateHasta = Utils.toStr(datosBusquedaExpedientes.get("fechaHasta"));
						
						Date dateDesde = getDate(strDateDesde);
						
						Calendar oCalendar = new GregorianCalendar();
						oCalendar.setTime(dateDesde);
						oCalendar.set(Calendar.HOUR_OF_DAY, 00);
						oCalendar.set(Calendar.MINUTE, 00);
						oCalendar.set(Calendar.SECOND, 00);
						oCalendar.set(Calendar.MILLISECOND, 0);
						dateDesde = oCalendar.getTime();
						
						Date dateHasta = getDate(strDateHasta);
						
										
						Calendar cCalendar = new GregorianCalendar();
						cCalendar.setTime(dateHasta);
						cCalendar.set(Calendar.HOUR_OF_DAY, 23);
						cCalendar.set(Calendar.MINUTE, 59);
						cCalendar.set(Calendar.SECOND, 59);
						cCalendar.set(Calendar.MILLISECOND, 0);
						dateHasta = cCalendar.getTime();
						
						//params.put("codDependencia", datosBusquedaExpedientes.get("codDependencia").toString());
						
						if(codTipoFecha.equals("1")){
							params.put("fecGenIni", dateDesde);
							params.put("fecGenFin", dateHasta);
						}else if(codTipoFecha.equals("2")){
							params.put("fecDocIni", dateDesde);
							params.put("fecDocFin", dateHasta);
						}
					}
				}
				else{
					String codTipoFecha = datosBusquedaExpedientes.get("codTipoFecha").toString();
					
					String strDateDesde = Utils.toStr(datosBusquedaExpedientes.get("fechaDesde"));
					String strDateHasta = Utils.toStr(datosBusquedaExpedientes.get("fechaHasta"));
					
					Date dateDesde = getDate(strDateDesde);
					
					Calendar oCalendar = new GregorianCalendar();
					oCalendar.setTime(dateDesde);
					oCalendar.set(Calendar.HOUR_OF_DAY, 00);
					oCalendar.set(Calendar.MINUTE, 00);
					oCalendar.set(Calendar.SECOND, 00);
					oCalendar.set(Calendar.MILLISECOND, 0);
					dateDesde = oCalendar.getTime();
					
					Date dateHasta = getDate(strDateHasta);
					
									
					Calendar cCalendar = new GregorianCalendar();
					cCalendar.setTime(dateHasta);
					cCalendar.set(Calendar.HOUR_OF_DAY, 23);
					cCalendar.set(Calendar.MINUTE, 59);
					cCalendar.set(Calendar.SECOND, 59);
					cCalendar.set(Calendar.MILLISECOND, 0);
					dateHasta = cCalendar.getTime();
					
					
					params.put("codProceso", datosBusquedaExpedientes.get("codProceso").toString());
					params.put("codTipExpediente", datosBusquedaExpedientes.get("codTipoExpe").toString());
					
					if(codTipoFecha.equals("1")){
						params.put("fecGenIni", dateDesde);
						params.put("fecGenFin", dateHasta);
					}else if(codTipoFecha.equals("2")){
						params.put("fecDocIni", dateDesde);
						params.put("fecDocFin", dateHasta);
					}
					
					
					String ruc =  datosBusquedaExpedientes.get("numRuc").toString();
			
					if(!ValidaConstantes.CADENA_VACIA.equals(ruc)) {					
						params.put("numeroRuc", ruc);
					}
				}
			
				
				listadoExpendientes = expedienteVirtualService.listarRegDocExpediente(params);			
				
				// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada solo cuando el usuario realiza la consulta.
				if(!Utils.isEmpty(listadoExpendientes)){
					if(flagBusquedaBoton){
					   registrarSeguimientoConsultaDocumentosInternos(null);
					}					
				}
				// Fin [jquispe 27/05/2016]
				modelo.addObject("listadoExpendientes", listadoExpendientes);
				
			}else if(indCarga.equals("3")){
				//Inicio [gangles 16/08/2016]
				modelo = new ModelAndView(jsonView);
				numAcumulador =Utils.toStr(datosBusquedaExpedientes.get("numAcumulador")); 
				// validamos número acumulador
				
				if (!Utils.isEmpty(numAcumulador)) {
					params.put("numExpedOrigen", numAcumulador);
					listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(params);
					
					if(listaExpedientesVirtualesTemp != null) {					
						listadoExpendientes.addAll(listaExpedientesVirtualesTemp); 
						params.put("numExpedOrigen", null);
						params.put("numAcumulador", numAcumulador);
						listaExpedientesVirtualesTemp = expedienteVirtualService.obtenerListaExpedienteVirtual(params);						
												
						listadoExpendientes.addAll(listaExpedientesVirtualesTemp);
					}					
					modelo.addObject("listadoExpendientes", listadoExpendientes);
					
					return modelo;
				}
				//Fin [gangles 16/08/2016]
		}else if(ValidaConstantes.FILTRO_DOS.endsWith(indCarga)){
				
				String numRuc = datosBusquedaExpedientes.get("numRuc").toString();
				
				DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
				//Inicio [oachahuic][PAS20165E210400270]
				String codDependencia = datosBusquedaExpedientes.get("codDependenciaBase").toString().trim();
				if (codDependencia.length() == 3) {
					contribuyente = aduanaService.obtenerAgenteHabilitado(contribuyente.getNumRuc(), codDependencia);
				}
				//Fin [oachahuic][PAS20165E210400270]
				modelo = new ModelAndView(jsonView);
				
				modelo.addObject("contribuyente", contribuyente);
				
			}

		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.cargarListaExpedientes");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.cargarListaExpedientes");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoConsultaDocumentosInternos(String numeroExpedienteVirtual) throws Exception{
		
		Map<String, Object> beanSegui = new HashMap<String, Object>();
		
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_DOCUMENTO_INTERNO);
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
	
public ModelAndView validaExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.validaExpediente");
		
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
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			
			if(reader != null) {
				
				jsonEnviado = reader.readLine();
				
			}
			
			@SuppressWarnings("unchecked")
			
			Map<String, Object> datosBusquedaExpedientes = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			List<T6614ExpVirtualBean> listadoExpendientes = new ArrayList<T6614ExpVirtualBean>();
			
			Map<String, Object> params =  new HashMap<String, Object>();
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				String codNumTipoExp = datosBusquedaExpedientes.get("codNumTipoExp").toString();
				if(codNumTipoExp.equals("1")){
					params.put("numExpedo", datosBusquedaExpedientes.get("numExpediente").toString());
					
				}else if(codNumTipoExp.equals("2")){
					params.put("numExpedv", datosBusquedaExpedientes.get("numExpediente").toString());
				}
				
				modelo = new ModelAndView(jsonView);
				
				
				
				listadoExpendientes = expedienteVirtualService.listaExpedientePorRuc(params);
			
				modelo.addObject("listadoExpendientes", listadoExpendientes);
				
			}
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.validaExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.validaExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
@SuppressWarnings("static-access")
public ModelAndView exportarExcelExpedientes(HttpServletRequest request, HttpServletResponse response){
       
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	   Date fechaActual = new Date();
	   String fecImpresion = sdf.format(fechaActual);

       String titulo = ExportaConstantes.TITULO_EXPORTA_03_01;

       ModelAndView view = null;
       MensajeBean mensajeBean = new MensajeBean();
       String listadoExportarCadena = null;
       //Inicio [gangles 29/08/2016]
       String expAcum="";
       //Fin [gangles 29/08/2016]
       
       try {
             
    	listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
		
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
        
        
        HSSFRow fila = hoja.createRow(1);
        CellRangeAddress rango = null;
        HSSFCell tituloCelda = fila.createCell(0);
        tituloCelda.setCellValue(titulo);
        rango = new CellRangeAddress(1,1,0,6);
		hoja.addMergedRegion(rango);
        
        HSSFCell nomFecha = fila.createCell(7);
        nomFecha.setCellValue("Fecha del Reporte:");
        	        
        HSSFCell Fecha = fila.createCell(8);
        Fecha.setCellValue(fecImpresion);
        
        fila = hoja.createRow(4);
        HSSFCell celda = fila.createCell(0);
        HSSFCell celda1 = fila.createCell(1);
        HSSFCell celda2 = fila.createCell(2);
        //Inicio [gangles 29/08/2016]--Se agrega las columnas de acumulación
        HSSFCell nAcumulador = fila.createCell(3);
        HSSFCell indAcumulador = fila.createCell(4);
      //Fin [gangles 29/08/2016] 
        HSSFCell celda5 = fila.createCell(5);
        HSSFCell celda6 = fila.createCell(6);
        HSSFCell celda7 = fila.createCell(7);
        HSSFCell celda8 = fila.createCell(8);
        HSSFCell celda9 = fila.createCell(9);
        HSSFCell celda10 = fila.createCell(10);
        HSSFCell celda11 = fila.createCell(11);
        HSSFCell celda12 = fila.createCell(12);
       
        celda.setCellValue("N°");
        celda1.setCellValue("N° Expediente Origen");
        celda2.setCellValue("N° Expediente Virtual");
        //Inicio [gangles 29/08/2016] 
        nAcumulador.setCellValue("N° Expediente Acumulador");
        indAcumulador.setCellValue("Indicador de Acumulación");
        //Fin [gangles 29/08/2016] 
        celda5.setCellValue("RUC");
        celda6.setCellValue("Proceso");
        celda7.setCellValue("Tipo de Expediente");
        celda8.setCellValue("Fecha de Generación");
        celda9.setCellValue("Fecha de Origen");
        celda10.setCellValue("Fecha de Notificación");
        celda11.setCellValue("Forma de Notificación");
        celda12.setCellValue("Origen");
        
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
        nAcumulador.setCellStyle(estiloCelda);
        indAcumulador.setCellStyle(estiloCelda);
        celda5.setCellStyle(estiloCelda);
        celda6.setCellStyle(estiloCelda);
        celda7.setCellStyle(estiloCelda);
        celda8.setCellStyle(estiloCelda);
        celda9.setCellStyle(estiloCelda);
        celda10.setCellStyle(estiloCelda);
        celda11.setCellStyle(estiloCelda);
        celda12.setCellStyle(estiloCelda);
        
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
           texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
           celda.setCellValue(texto.toString());
           hoja.autoSizeColumn(0);
           celda.setCellStyle(estiloRecorrido);
           celda1 = fila.createCell(1);
           celda1.setCellValue(listadoExportar.get(i).get("numExpedienteOrigen").toString().trim());
           hoja.autoSizeColumn(1);
           celda1.setCellStyle(estiloRecorrido);
           celda2 = fila.createCell(2);
           celda2.setCellValue(listadoExportar.get(i).get("numExpedienteVirtual").toString().trim());
           hoja.autoSizeColumn(2);
           celda2.setCellStyle(estiloRecorrido);
           //Inicio [gangles 29/08/2016]--Se agrega las columnas de acumulación
           nAcumulador = fila.createCell(3);
           expAcum= listadoExportar.get(i).get("numExpedienteAcumulador")!=null?listadoExportar.get(i).get("numExpedienteAcumulador").toString().trim():ValidaConstantes.CADENA_VACIA;
           if(Utils.equiv(expAcum, "0")){
        	   nAcumulador.setCellValue("");
           }else{
        	   nAcumulador.setCellValue(expAcum);
           }
           hoja.autoSizeColumn(3);
           nAcumulador.setCellStyle(estiloRecorrido);
           indAcumulador = fila.createCell(4);
           indAcumulador.setCellValue(listadoExportar.get(i).get("desIndicadorAcumulado").toString().trim());
           hoja.autoSizeColumn(4);
           indAcumulador.setCellStyle(estiloRecorrido);
           //Fin [gangles 29/08/2016]
           celda5 = fila.createCell(5);
           celda5.setCellValue(listadoExportar.get(i).get("numRuc").toString().trim());
           hoja.autoSizeColumn(5);
           celda5.setCellStyle(estiloRecorrido);
           celda6 = fila.createCell(6);
           celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desProceso").toString().trim()));
           hoja.autoSizeColumn(6);
           celda6.setCellStyle(estiloRecorrido);
           celda7 = fila.createCell(7);
           celda7.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString().trim()));
           hoja.autoSizeColumn(7);
           celda7.setCellStyle(estiloRecorrido);
           celda8 = fila.createCell(8);
           celda8.setCellValue(listadoExportar.get(i).get("fecRegistro").toString().trim());
           hoja.autoSizeColumn(8);
           celda8.setCellStyle(estiloRecorrido);
           celda9 = fila.createCell(9);
           celda9.setCellValue(listadoExportar.get(i).get("fechaDocumentoOrigen").toString().trim());
           hoja.autoSizeColumn(9);
           celda9.setCellStyle(estiloRecorrido);
           
           celda10 = fila.createCell(10);
           celda10.setCellValue(listadoExportar.get(i).get("strFecNotifOrigen").toString().trim());
           hoja.autoSizeColumn(10);
           celda10.setCellStyle(estiloRecorrido);
          
           celda11 = fila.createCell(11);
           celda11.setCellValue(listadoExportar.get(i).get("desForNotifOrigen").toString().trim());
           hoja.autoSizeColumn(11);
           celda11.setCellStyle(estiloRecorrido);
           
           celda12 = fila.createCell(12);
           celda12.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desOrigen").toString().trim()));
           hoja.autoSizeColumn(12);
           celda12.setCellStyle(estiloRecorrido);
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
       
         String filename="rpt_expediente_virtual_doc_internos_" + part + ".xls";;
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
          String headerValue = String.format("inline; filename=\"%s\"",
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

	@SuppressWarnings("static-access")
	public ModelAndView seleccionaExportarExcelFisca(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_03_03;

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
	       String pesoTotal = null;
	       
	       try {
	    	   
	    	listadoExportarCadena = request.getParameter("hiddenListaExp");
	    	numRuc = request.getParameter("hiddenNumRuc");
		    razonSocial = request.getParameter("hiddenRazonSocial");
		    numExpOrigen = request.getParameter("hiddenNumExpOrigen");
		    numExpVirtual = request.getParameter("hiddenNumExpVirtual");
		    estExpediente = request.getParameter("hiddenEstExpediente");
		    tipoProceso = request.getParameter("hiddenTipoProceso");
		    tipoExpediente = request.getParameter("hiddenTipoExpediente");
		    descOrigen = request.getParameter("hiddenDescOrigen");
		    fechaGeneracion = request.getParameter("hiddenFechaGeneracion");
		    fechaOrigen = request.getParameter("hiddenFechaOrigen");
		    pesoTotal = request.getParameter("hiddenPesoTotal"); 
			
		    razonSocialRuc = numRuc +" - "+razonSocial;
		    
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
	        hoja.setColumnWidth(5, 6500);
	        hoja.setColumnWidth(6, 5000);
	       	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,4);
			hoja.addMergedRegion(rango);
	        
	        
	        HSSFCell nomFecha = fila.createCell(5);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(6);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(razonSocialRuc);
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);

	        HSSFCell subtituloCelda4 = fila.createCell(5);
	        subtituloCelda4.setCellValue("Estado del Expediente");
	        HSSFCell contenido3 = fila.createCell(6);
	        contenido3.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);
	        
	        HSSFCell subtituloCelda7 = fila.createCell(5);
	        subtituloCelda7.setCellValue("Origen");
	        HSSFCell contenido6 = fila.createCell(6);
	        contenido6.setCellValue(descOrigen);
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda8 = fila.createCell(1);
	        subtituloCelda8.setCellValue("Fecha de Generación del Documento");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda9 = fila.createCell(3);
	        subtituloCelda9.setCellValue("Fecha de Documento Origen");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(fechaOrigen);
	        
	        fila = hoja.createRow(9);
	        CellRangeAddress rango3 = null;
	        HSSFCell subtituloCelda10 = fila.createCell(0);
	        subtituloCelda10.setCellValue("Documentos Pendientes por Adjuntar al Expediente");
	        rango3 = new CellRangeAddress(9,9,0,2);
			hoja.addMergedRegion(rango3);
			
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        
	       
	        celda.setCellValue("N°");
	        celda1.setCellValue("Tipo de Documento");
	        celda2.setCellValue("Número de Documento");
	        celda3.setCellValue("Fecha de Documento");
	        celda4.setCellValue("Archivo seleccionado");
	        celda5.setCellValue("Tamaño del archivo");
	       
	       	        
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
	        
	        int newCelda = 11;           
	        for (int i = 0; i < listadoExportar.size(); i++) {
            
	           fila = hoja.createRow(i + 11);
	           celda = fila.createCell(0);
	           texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
	           celda.setCellValue(texto.toString());
	           hoja.autoSizeColumn(0);
	           celda.setCellStyle(estiloRecorrido);
	           celda1 = fila.createCell(1);
	           celda1.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipdoc").toString().trim()));
	           hoja.autoSizeColumn(1);
	           celda1.setCellStyle(estiloRecorrido);
	           celda2 = fila.createCell(2);
	           celda2.setCellValue(listadoExportar.get(i).get("numDoc").toString().trim());
	           hoja.autoSizeColumn(2);
	           celda2.setCellStyle(estiloRecorrido);
	           
	           /**/
	           celda3 = fila.createCell(3);
	           celda3.setCellValue(listadoExportar.get(i).get("fechaDoc").toString().trim());
	           hoja.autoSizeColumn(3);
	           celda3.setCellStyle(estiloRecorrido);
	           /**/
	           celda4 = fila.createCell(4);
	           celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("nombreArchivo").toString().trim()));
	           hoja.autoSizeColumn(4);
	           celda4.setCellStyle(estiloRecorrido);
	           celda5 = fila.createCell(5);
	           celda5.setCellValue(listadoExportar.get(i).get("tamanoDoc").toString().trim());
	           hoja.autoSizeColumn(5);
	           celda5.setCellStyle(estiloRecorrido);
	           newCelda++;	
	        }
	        
	        fila = hoja.createRow(newCelda);	
	        CellRangeAddress rango5 = null;
	        HSSFCell nombreValorTotal = fila.createCell(3);
	        nombreValorTotal.setCellValue("Total del Tamaño de Archivos");
	        rango5 = new CellRangeAddress(newCelda,newCelda,3,4);
			hoja.addMergedRegion(rango5);
			HSSFCell valor = fila.createCell(5);
			valor.setCellValue(pesoTotal);
			nombreValorTotal.setCellStyle(estiloTitulo);
			valor.setCellStyle(estiloTitulo);
			
	         Calendar calendar = Calendar.getInstance();

	         int anio = (calendar.get(Calendar.YEAR));
	         int dia = (calendar.get(Calendar.DATE));
	         int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
	         int minutos = (calendar.get(Calendar.MINUTE));
	         String dd = (dia<10) ? "0"+dia : dia+"";
           
	         int mes = calendar.get(Calendar.MONTH)+1;
           
	         String mm = (mes<10) ? "0"+mes : mes+"";
           
	         String part=anio+mm+dd+"_"+hora+minutos;
           
	         String filename="rpt_doc_internos_pendientes_adjuntar_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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
	
	public ModelAndView seleccionaNroExpOrigen (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.seleccionaNroExpOrigen");
		ModelAndView modelo = null;
		T6614ExpVirtualBean datosExpBean = null;
		Map<String, Object>	parametros = new HashMap<String, Object>();
		String fecRegistroVista = ValidaConstantes.CADENA_VACIA;
		String fecOrigenDocVista = ValidaConstantes.CADENA_VACIA;
		List<Map<String, Object>> obtenerDocumentosExpediente = null;
		List<Map<String, Object>> listaDocumentosExpediente = null;
		List<T6623TipDocExpBean> listaTemp = null;
		// Inicio [jquispe 27/05/2016] Permite realiza la accion solo cuando es enviada por la pagina principal.
		boolean flagPaginaPrincipal = false;
		// Fin [jquispe 27/05/2016]
		
		try {
			// Inicio [jquispe 27/05/2016] Permite realiza la accion solo cuando es enviada por la pagina principal.
			flagPaginaPrincipal = Boolean.parseBoolean(Utils.toStr(request.getParameter("flagPaginaPrincipal")));
			// Fin [jquispe 27/05/2016]
			
			BeanParametrosConsultaReq beanParametrosConsultaReq = (BeanParametrosConsultaReq) WebUtils.getSessionAttribute(request, "beanParametrosConsultaReq");
			if (Utils.isEmpty(beanParametrosConsultaReq)) {
				beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
				beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
				request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
			} else if(Utils.equiv(request.getParameter("recargarBean"), ValidaConstantes.FILTRO_UNO)) {
				beanParametrosConsultaReq = Utils.mapearBean(request, BeanParametrosConsultaReq.class); 
				beanParametrosConsultaReq.setRealizarBusqueda(ValidaConstantes.FILTRO_UNO);
				request.getSession().setAttribute("beanParametrosConsultaReq", beanParametrosConsultaReq);
			}
			
			String numExpedienteOrigen = request.getParameter("aux");
			if (numExpedienteOrigen != null || numExpedienteOrigen != ValidaConstantes.CADENA_VACIA) {
				//RECUPERAR DATOS DEL EXPEDIENTE VIRTUAL
				parametros = new HashMap<String, Object>();
				parametros.put("numExpedienteOrigen", numExpedienteOrigen);
				parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
				log.debug("datosExpBean: " + datosExpBean);
				
				if (datosExpBean != null) {
					//FORMATEAR FECHAS
					SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
					if (datosExpBean.getFecRegistro() != null) {	
						fecRegistroVista = formatoFechaExporta.format(datosExpBean.getFecRegistro());
					}
					if (datosExpBean.getFechaDocumentoOrigen() != null) {	
						fecOrigenDocVista = formatoFechaExporta.format(datosExpBean.getFechaDocumentoOrigen());
					}
					//RECUPERAR DOCUMENTOS DEL EXPEDIENTE VIRTUAL
					parametros = new HashMap<String,Object>();
					parametros.put("numExpeDv", datosExpBean.getNumExpedienteVirtual());
					obtenerDocumentosExpediente = expedienteVirtualService.obtenerDocumentosExpediente(parametros);
					log.debug("obtenerDocumentosExpediente: " + obtenerDocumentosExpediente);
				}
			}

			//obtenemos Listado de tipos de documentos de clase 1
			parametros.put("codTipoExpediente", datosExpBean.getCodTipoExpediente());
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			parametros.put("listIndTipDoc", listIndTipDoc);
			parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			listaTemp = configuracionExpedienteService.listarTiposDocumentos(parametros);
			log.debug("listaTemp: " + listaTemp);
			
			listaDocumentosExpediente =  new ArrayList<Map<String, Object>>();
			boolean remover = true;
			int contador = 1;
			for (Map<String, Object> map : obtenerDocumentosExpediente) {
				if (log.isDebugEnabled()) log.debug("documento interno: " + map.toString());
	            String codTipoDocumento = Utils.toStr(map.get("codTipDoc"));
	            for (T6623TipDocExpBean t6623TipDocExpBean : listaTemp) {
	                if(Utils.equiv(codTipoDocumento, t6623TipDocExpBean.getCodTipoDocumento())){
	                	remover = false;
	                }
                }
	            if(!remover){
	            	map.put("numOrden", contador);
	            	listaDocumentosExpediente.add(map);
	            	contador++;
	            }
	            remover = true;
            }
			// REGISTRAR SEGUIMIENTO SÓLO CUANDO ES EL FLUJO PRINCIPAL
			if (flagPaginaPrincipal && datosExpBean != null) {
				registrarSeguimientoSeleccionNroExpOrigen(datosExpBean.getNumExpedienteVirtual());
			}			
			
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_03_01_002);
			modelo.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
			modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
			modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fecOrigenDocVista));
			modelo.addObject("lstDocExp", new JsonSerializer().serialize(listaDocumentosExpediente));
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.seleccionaNroExpOrigen");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.seleccionaNroExpOrigen");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoSeleccionNroExpOrigen(String numeroExpedienteVirtual) throws Exception{
		Map<String, Object> beanSegui = new HashMap<String, Object>();
	
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
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_REGISTRO_DOCUMENTO_INTERNO);
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
	
	private Date getDate(String fecha) {
		Date fec = null;
		if (!fecha.equals("//")) {
			int dd = Integer.parseInt(fecha.substring(0, 2));
			int mm = Integer.parseInt(fecha.substring(3, 5)) - 1;
			int aa = Integer.parseInt(fecha.substring(6, 10));
			Calendar cal = Calendar.getInstance();
			cal.set(aa, mm, dd);
			fec = cal.getTime();
		}
		return fec;
	}
	
	public ModelAndView seleccionaAgregarDocumento (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.seleccionaAgregarDocumento");
		
		ModelAndView modelo = null;
		
		T6614ExpVirtualBean datosExpBean = null;
		
		String fecRegistroVista =ValidaConstantes.CADENA_VACIA;
		String fecOrigenDocVista =ValidaConstantes.CADENA_VACIA;
		//List<Map<String, Object>> obtenerDocumentosExpediente = null;
		List<T6613DocExpVirtBean> listDocAsocExped = null;
		List<T6623TipDocExpBean> listadoTipoDocumentos = null;
		Map<String, Object> params = null;
		List<Map<String, Object>> lista3Dig = null;
		
		try {
			//String indCarga = request.getParameter("indCarga");
			Date fechaRegistro = null;
			Date fechaOrigenDoc = null;
			listDocAsocExped = new ArrayList<T6613DocExpVirtBean>();
			//obtenerDocumentosExpediente = new ArrayList<Map<String, Object>>();
			Date fecSistema = new Date();
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			
			String fechaSistema = formatoFecha.format(fecSistema);
			/**/
			Map<String, Object> mapa = new HashMap<String,Object>();
			mapa = new HashMap<String,Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
			int tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IA"));
			/**/
			
			listadoTipoDocumentos = new ArrayList<T6623TipDocExpBean>();
			
			List<Map<String, Object>> catalogoDocsOtros = new ArrayList<Map<String, Object>>();
			
			lista3Dig = new ArrayList<Map<String, Object>>();
			
			Map<String, Object>	parametros = new HashMap<String, Object>();
			
			Map<String, Object>	paramTipoDocumento = new HashMap<String, Object>();
			
			List<T01ParamBean> listaEstDocumento = paramService.listaEstDocumento();
			
			List<T01ParamBean> listaEtapaDocumento = paramService.listaEtapaDocumento();
			
			String numExpedienteOrigen = request.getParameter("aux");
			
			if(numExpedienteOrigen!= null || numExpedienteOrigen!=ValidaConstantes.CADENA_VACIA){
				
				parametros.put("numExpedienteOrigen", numExpedienteOrigen);
				parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				
				datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				if(datosExpBean!=null){ 
					fechaRegistro = datosExpBean.getFecRegistro();
					if(fechaRegistro!=null){	
						fecRegistroVista = formatoFechaExporta.format(fechaRegistro);
					}
					fechaOrigenDoc = datosExpBean.getFechaDocumentoOrigen();
					if(fechaOrigenDoc!=null){	
						fecOrigenDocVista = formatoFechaExporta.format(fechaOrigenDoc);
					}
					paramTipoDocumento.put("codTipoExpediente", datosExpBean.getCodTipoExpediente().trim());
					paramTipoDocumento.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					List<String> listIndTipDoc = new ArrayList<String>();
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
					paramTipoDocumento.put("listIndTipDoc", listIndTipDoc);
					paramTipoDocumento.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					listadoTipoDocumentos = configuracionExpedienteService.listarTiposDocumentos(paramTipoDocumento);
					
					//Inicio [eaguilar 24/05/2015] listas para los combos de tres digitos y tipo de exp
					Map<String, Object> mapaOtros = new HashMap<String, Object>();
					mapaOtros.put("codClase", CatalogoConstantes.CATA_DOCUMENTOS_OTROS);
					mapaOtros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
					Map<String, Object> auxOtros = catalogoService.obtenerCatalogo(mapaOtros);//lista nueva para tipo doc OTROS
					Map<String, Object> auxOtrosItem;
					
					for (Map.Entry<String, Object> item : auxOtros.entrySet()){
						auxOtrosItem = new HashMap<String, Object>();
						auxOtrosItem.put("codTipoDocumento", item.getKey().toString().trim());
						auxOtrosItem.put("desTipoDocumentoCompuesto", item.getKey().toString().trim() + " - " + item.getValue().toString().trim());
						catalogoDocsOtros.add(auxOtrosItem);
					}
					
					paramTipoDocumento.put("flag3dig", "3");
					lista3Dig = configuracionExpedienteService.listarTablasTipDoc3Dig(paramTipoDocumento);
					//Fin [eaguilar 24/05/2015]
					params = new HashMap<String,Object>();
					params.put("numExpedienteVirtual", datosExpBean.getNumExpedienteVirtual());
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
					paramTipoDocumento.put("listIndTipDoc", listIndTipDoc);
					listDocAsocExped = expedienteVirtualService.listarTipoDocPorExpe(params);
				}
			}	
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_03_01_003);
			
			modelo.addObject("tamanoMaximo", new JsonSerializer().serialize(tamanoMaximo));
			modelo.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
			modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
			modelo.addObject("fechaOrigenDoc", new JsonSerializer().serialize(fecOrigenDocVista));
			modelo.addObject("listadoTipoDocumentos", new JsonSerializer().serialize(listadoTipoDocumentos));
			modelo.addObject("listadoTipoDoc3Dig", new JsonSerializer().serialize(lista3Dig));
			modelo.addObject("listDocAsocExped", new JsonSerializer().serialize(listDocAsocExped));
			modelo.addObject("listaEstDocumento", new JsonSerializer().serialize(listaEstDocumento));
			modelo.addObject("listaEtapaDocumento", new JsonSerializer().serialize(listaEtapaDocumento));
			modelo.addObject("listaDocsOtros", new JsonSerializer().serialize(catalogoDocsOtros));
			modelo.addObject("fechaSistema", new JsonSerializer().serialize(fechaSistema));
			//[PAS20191U210500144]
			T01ParamBean pbean = paramService.obtenerServicioSignnet();
			String servicioSignnet = pbean.getDesParametro().substring(0,122);
			log.debug("hostServicioSignnet: " + servicioSignnet);
			modelo.addObject("hostServicioSignnet", new JsonSerializer().serialize(servicioSignnet));
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.seleccionaAgregarDocumento");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.seleccionaAgregarDocumento");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView adjuntarArchivosExpediente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.adjuntarArchivosExpediente");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		MultipartHttpServletRequest multipartRequest = null;
		String nombreArchivoTemp = null;
		Map<String, Object> parametros = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		Integer numCorDocRel = 0; 
		String codTipDoc = null;
		T6623TipDocExpBean t6623Bean = null;
		List<String> listaNumExpAcumCOA = null;
		Map<String, Object> mapParam = null;
		String listaDatosArchivos = null;
		String numExpVirtual = null;
		String codTipoExpediente = null;
		//INICIO LLRB 23/01/2020
		Integer numCopia=0;
		Map<String, Object> nroCopia = null;
		//FIN LLRB 23/01/2020
		try {
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			} else {
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			if(reader != null) {
				jsonEnviado = reader.readLine();
			}
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			log.debug("dataEnvio: " + dataEnvio);
			if (dataEnvio == null) {
				multipartRequest = (MultipartHttpServletRequest) request;
				listaDatosArchivos = multipartRequest.getParameter("listaTabla").toString();
				numExpVirtual = multipartRequest.getParameter("numExpVirtual").toString();
				codTipoExpediente = multipartRequest.getParameter("codTipoExpediente").toString();
			} else {
				listaDatosArchivos = dataEnvio.get("listaTabla").toString();
				numExpVirtual = dataEnvio.get("numExpVirtual").toString();
				codTipoExpediente = dataEnvio.get("codTipoExpediente").toString();
			}
			log.debug("listaDatosArchivos: " + listaDatosArchivos);
			log.debug("numExpVirtual: " + numExpVirtual);
			log.debug("codTipoExpediente: " + codTipoExpediente);
			
			mapParam = new HashMap<String, Object>();
			mapParam.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL);
			mapParam.put("numExpediente", numExpVirtual);
			T6614ExpVirtualBean t6614ExpVirtualBean = expedienteVirtualService.obtenerExpedienteVirtual(mapParam);
			
			List<Map<String, Object>> listaDatosArchivosRes = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listaDatosArchivos, ArrayList.class);
			byte[] data = null;
			for (Map<String, Object> docAdj : listaDatosArchivosRes) {
			//INICIO LLRB 23/01/2020	
			log.debug("numCorDoc For Adjuntar: " + docAdj.get("numCorDoc"));
			log.debug("Tamano For Adjuntar: " + docAdj.get("tamanoDoc"));	
			log.debug("pesoArchivo For Adjuntar: " + docAdj.get("pesoArchivo"));	
			log.debug("fecha Doc For Adjuntar: " + docAdj.get("fechaDoc"));
			log.debug("fechaCompleta Doc For Adjuntar: " + docAdj.get("fechaDocCompleta"));
			if((Integer.parseInt(docAdj.get("numCorDoc").toString()))!=0){
				if (docAdj.get("codIdEcm") == null) {//[oachahuic][PAS20181U210400241]
					nombreArchivoTemp = Utils.convertirUnicode(docAdj.get("nombreArchivoTemp").toString().trim());
					log.debug("ruta de archivo: " + ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + Utils.convertirUnicode(nombreArchivoTemp));
					File archivoTem = new File(ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + Utils.convertirUnicode(nombreArchivoTemp)); //eaguilar 22 JUN
					FileInputStream fis = new FileInputStream(archivoTem);
			        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        byte[] buf = new byte[1024];
			        try {
			            for (int readNum; (readNum = fis.read(buf)) != -1;) {
			                bos.write(buf, 0, readNum); //no doubt here is 0F
			                log.debug("read " + readNum + " bytes,");
			            }
			        } catch (IOException ex) {
			            
			        }
			        data = bos.toByteArray();
				}
		        
				codTipDoc = docAdj.get("codTipoDocumento").toString().trim();
					
				//Integer tamanoDoc = Integer.parseInt((docAdj.get("pesoArchivo").toString()));
				Integer tamanoDoc = Utils.toInteger(docAdj.get("pesoArchivo").toString());
				Integer numCorDoc = Utils.toInteger(docAdj.get("numCorDoc").toString());
			    log.debug("numCordoc: " + numCorDoc + "tamanoDoc: " + tamanoDoc);
			    nroCopia = new HashMap<String, Object>();
			    nroCopia.put("numCorDoc", numCorDoc);
			    Integer numCopiaTemp = expedienteVirtualService.obtenerNroCopia(nroCopia);
				int aumento = 1;				
				numCopia=(Integer.parseInt(numCopiaTemp.toString())) + aumento;		
		        parametros = new HashMap<String, Object>();
		        
		        //DATOS DEL DOCUMENTO
				parametros.put("numExpeDv", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
				parametros.put("codTipdoc", codTipDoc);
				parametros.put("tamanoDoc", tamanoDoc);
				parametros.put("numCorDoc", numCorDoc);
				parametros.put("codTipexp", codTipoExpediente);
				parametros.put("numCopia", numCopia);
				parametros.put("numDoc", docAdj.get("numDoc").toString().trim());
				String fechaDoc = docAdj.get("fechaDoc").toString().trim();
				String fechaDocCompleta = docAdj.get("fechaDocCompleta").toString().trim();
				log.debug("fechaDoc: " + fechaDoc);
				log.debug("fechaDocCompleta: " + fechaDocCompleta);
				if(Utils.equiv(fechaDocCompleta, "null")){
					fechaDocCompleta = "";
				}
				if(fechaDocCompleta != null && !fechaDocCompleta.equals("")) {
					parametros.put("fecDoc", Utils.stringToDate(fechaDocCompleta, CatalogoConstantes.INT_ONE));
				} else {
					parametros.put("fecDoc", Utils.stringToDate(fechaDoc, CatalogoConstantes.INT_TWO));
				}
				parametros.put("codUsuregis", usuarioBean.getNroRegistro());
				parametros.put("desArch", Utils.convertirUnicode(docAdj.get("nombreArchivo").toString().trim()));
				parametros.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_INTERNO);
				//OBTENER EL INDICADOR DE VISIBILIDAD POR EL TIPO DE DOCUMENTO
				mapParam = new HashMap<String, Object>();
				mapParam.put("codTipoExpediente", codTipoExpediente);
				mapParam.put("codTipoDocumento", docAdj.get("codTipoDocumento").toString().trim());
				mapParam.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				t6623Bean = configuracionExpedienteService.obtenerTipDocExpediente(mapParam);
				
				//[PAS20191U210500144] Inicio 
				if(docAdj.get("indVisible") == null || ValidaConstantes.CADENA_VACIA.equals(docAdj.get("indVisible").toString().trim())){
					parametros.put("indVisible", t6623Bean == null ? ValidaConstantes.IND_VISIBLE_DOC : t6623Bean.getIndVisibleContribuyente());
					log.debug("Sin valor de parametro indVisible:  " + docAdj.get("indVisible") + "Parametro indReserTrib: " + docAdj.get("indReserTrib") );
				} else {
					parametros.put("indVisible",docAdj.get("indVisible"));
					log.debug("Con Parametro indVisible:  " + docAdj.get("indVisible") + "Parametro indReserTrib: " + docAdj.get("indReserTrib") );
				}
				
				/*if( docAdj.get("indReserTrib") == null || ValidaConstantes.CADENA_VACIA.equals(docAdj.get("indReserTrib").toString().trim()) ){
					parametros.put("indReserTrib", ValidaConstantes.IND_SIN_RESERVA_TRIBUTARIA);
					log.debug("Sin parametro indReserTrib:  " + docAdj.get("indReserTrib") );
				} else {
					parametros.put("indReserTrib",docAdj.get("indReserTrib"));				
					log.debug("Con parametro indReserTrib:  " + docAdj.get("indReserTrib") );
				}*/
				//[PAS20191U210500144] Fin
				
				if (docAdj.get("codIdEcm") == null) {//Inicio - [oachahuic][PAS20181U210400241]
					//DATOS PARA EL ECM
					parametros.put("numRuc", t6614ExpVirtualBean.getNumRuc());
					parametros.put("archLength", docAdj.get("pesoArchivo").toString().trim());
					parametros.put("archMimeType", docAdj.get("mimeType").toString().trim());
					parametros.put("archFileName", Utils.convertirUnicode(docAdj.get("nombreArchivo").toString().trim()));
					parametros.put("binDoc", data);
					parametros.put("metadata", Utils.convertirUnicode(docAdj.get("busqueda").toString().trim()));
					parametros.put("codDep", usuarioBean.getCodDepend());
				} else {
					parametros.put("codIdecm", docAdj.get("codIdEcm"));
				}//Fin - [oachahuic][PAS20181U210400241]
				
				
				
				
		        //DATOS GENERALES
				parametros.put("numExpedo", t6614ExpVirtualBean.getNumExpedienteOrigen().trim());
				parametros.put("flagOrigen", ValidaConstantes.IND_REG_DOC_INT);
				parametros.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
				parametros.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);
				parametros.put("codTipdocRelParam", "");
				parametros.put("numDocRelParam", "");
		        
		        //VERIFICAR SI EL DOCUMENTO SE VA RELACIONAR CON OTRO DOCUMENTO
		        numCorDocRel = Utils.toInteger(Utils.toStr(docAdj.get("asociado")));
				if (numCorDocRel != null) {
					mapParam = new HashMap<String, Object>();
					if (numCorDocRel.intValue() == 0) {//[oachahuic][PAS20181U210400241]
						mapParam.put("numExpeDv", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
						mapParam.put("codTipDoc", docAdj.get("codTipDocRel").toString().trim());
						mapParam.put("numDoc", parametros.get("numDoc"));
					} else {
						mapParam.put("numCorDoc", numCorDocRel);
					}
					T6613DocExpVirtBean t6613DocRel = documentoExpedienteService.obtenerDocumentoExpediente(mapParam);
					
					//VERIFICAR SI EL DOCUMENTO ES LA CONSTANCIA DE NOTIFICACIÓN
					if (ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {
						parametros.put("fecNotDocRel", Utils.stringToDate(docAdj.get("fecNotRel").toString().trim(), CatalogoConstantes.INT_TWO)); //[ATORRECH 2017-04-26]
						parametros.put("forNotDocRel", codTipDoc);
						//VERIFICAR SI ES LA CONSTANCIA DE NOTIFICACIÓN DEL EXPEDIENTE PARA ACTUALIZAR EL ESTADO Y/O ETAPA
						if (t6614ExpVirtualBean.getNumExpedienteOrigen().trim().equals(t6613DocRel.getNumDoc().trim())) {
							mapParam = new HashMap<String, Object>();
							mapParam.put("codTipoDoc", codTipDoc);
							parametros.putAll(configuracionExpedienteService.obtenerEstadoEtapa(mapParam));
						}
					}
					parametros.put("numCordocRel", t6613DocRel.getNumCorDoc());
					parametros.put("indVisDocRel", Utils.toStr(docAdj.get("indVisDocumento")));
					parametros.put("actDocRel", true);
				} else {					   
					parametros.put("numCordocRel", 0);					
				}
				
				//VERIFICAR SI EL TIPO DE DOCUMENTO REALIZAR UN CAMBIO DE ESTADO Y/O ETAPA SIN CONSIDERAR LAS CONSTANCIAS DE NOTIFICACIÓN
				if (!ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {
					Map<String, Object> estEtapMap = new HashMap<String, Object>();
					estEtapMap.put("codTipoDoc", codTipDoc);
					parametros.putAll(configuracionExpedienteService.obtenerEstadoEtapa(estEtapMap));
				}
				
				//[PAS20181U210400213] VERIFICAR SI EL TIPO DE DOCUMENTO GENERA ACUMULACIÓN O SEPARACIÓN DE EXPEDIENTES. SOLO EXP DE COBRANZA COACTIVA
				if (CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_RC_SEPARACION.equals(codTipDoc) ||
						ValidaConstantes.NUMERO_RC_ACUMULADOR.equals(codTipDoc)) {
					mapParam = new HashMap<String, Object>();
					mapParam.put("numExpVir", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
					mapParam.put("numRc", docAdj.get("numDoc").toString().trim());
					mapParam.put("codDep", t6614ExpVirtualBean.getCodDependencia().trim());
					parametros.put("listExpAcum", cobranzaCoactivaService.obtenerExpedientesAcumulados(mapParam));
					
				} else {
					parametros.put("indAcu", ValidaConstantes.IND_ACU_NO);
				}
				
				//VERIFICAR SI EL TIPO DE DOCUMENTO TIENE COMO FINALIDAD EL CIERRE DEL EXPEDIENTE
				if (ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE.equals(t6623Bean.getIndTipDocumento())) {
					parametros.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
				} else {
					parametros.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
				}
				
				Map<String, Object> mapResponse = expedienteVirtualService.registrarDocumentosExpedienteFisca(parametros); //28 JUNIO se agrego el mapResponse para recibir el retorno del metodo regDoc
				
				//28 JUNIO eaguilar : graba seguimiento para los acumulados
				if (mapResponse.get("mapSeguiAcum") != null) {
					expedienteVirtualService.registrarSeguimientoAcumulados((Map<String, Object>) mapResponse.get("mapSeguiAcum"));
				}

				modelo.addObject("grabadoExitoso", true);
			//FIN LLRB 23/01/2020
			}else{
				if (docAdj.get("codIdEcm") == null) {//[oachahuic][PAS20181U210400241]
					nombreArchivoTemp = Utils.convertirUnicode(docAdj.get("nombreArchivoTemp").toString().trim());
					log.debug("ruta de archivo: " + ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + Utils.convertirUnicode(nombreArchivoTemp));
					File archivoTem = new File(ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + Utils.convertirUnicode(nombreArchivoTemp)); //eaguilar 22 JUN
					FileInputStream fis = new FileInputStream(archivoTem);
			        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        byte[] buf = new byte[1024];
			        try {
			            for (int readNum; (readNum = fis.read(buf)) != -1;) {
			                bos.write(buf, 0, readNum); //no doubt here is 0
			                log.debug("read " + readNum + " bytes,");
			            }
			        } catch (IOException ex) {
			            
			        }
			        data = bos.toByteArray();
				}
		        
		        codTipDoc = docAdj.get("codTipoDocumento").toString().trim();
		        
		        parametros = new HashMap<String, Object>();
		        
		        //DATOS DEL DOCUMENTO
				parametros.put("numExpeDv", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
				parametros.put("codTipdoc", codTipDoc);
				parametros.put("codTipexp", codTipoExpediente);
				parametros.put("numDoc", docAdj.get("numDoc").toString().trim());
				String fechaDoc = docAdj.get("fechaDoc").toString().trim();
				String fechaDocCompleta = docAdj.get("fechaDocCompleta").toString().trim();
				log.debug("fechaDoc: " + fechaDoc);
				log.debug("fechaDocCompleta: " + fechaDocCompleta);
				if(Utils.equiv(fechaDocCompleta, "null")){
					fechaDocCompleta = "";
				}
				if(fechaDocCompleta != null && !fechaDocCompleta.equals("")) {
					parametros.put("fecDoc", Utils.stringToDate(fechaDocCompleta, CatalogoConstantes.INT_ONE));
				} else {
					parametros.put("fecDoc", Utils.stringToDate(fechaDoc, CatalogoConstantes.INT_TWO));
				}
				parametros.put("codUsuregis", usuarioBean.getNroRegistro());
				parametros.put("desArch", Utils.convertirUnicode(docAdj.get("nombreArchivo").toString().trim()));
				parametros.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_INTERNO);
				//OBTENER EL INDICADOR DE VISIBILIDAD POR EL TIPO DE DOCUMENTO
				mapParam = new HashMap<String, Object>();
				mapParam.put("codTipoExpediente", codTipoExpediente);
				mapParam.put("codTipoDocumento", docAdj.get("codTipoDocumento").toString().trim());
				mapParam.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				t6623Bean = configuracionExpedienteService.obtenerTipDocExpediente(mapParam);
				
				//[PAS20191U210500144] Inicio 
				if(docAdj.get("indVisible") == null || ValidaConstantes.CADENA_VACIA.equals(docAdj.get("indVisible").toString().trim())){
					parametros.put("indVisible", t6623Bean == null ? ValidaConstantes.IND_VISIBLE_DOC : t6623Bean.getIndVisibleContribuyente());
					log.debug("Sin valor de parametro indVisible:  " + docAdj.get("indVisible") + "Parametro indReserTrib: " + docAdj.get("indReserTrib") );
				} else {
					parametros.put("indVisible",docAdj.get("indVisible"));
					log.debug("Con Parametro indVisible:  " + docAdj.get("indVisible") + "Parametro indReserTrib: " + docAdj.get("indReserTrib") );
				}
				
				if( docAdj.get("indReserTrib") == null || ValidaConstantes.CADENA_VACIA.equals(docAdj.get("indReserTrib").toString().trim()) ){
					parametros.put("indReserTrib", ValidaConstantes.IND_SIN_RESERVA_TRIBUTARIA);
					log.debug("Sin parametro indReserTrib:  " + docAdj.get("indReserTrib") );
				} else {
					parametros.put("indReserTrib",docAdj.get("indReserTrib"));				
					log.debug("Con parametro indReserTrib:  " + docAdj.get("indReserTrib") );
				}
				//[PAS20191U210500144] Fin
				
				if (docAdj.get("codIdEcm") == null) {//Inicio - [oachahuic][PAS20181U210400241]
					//DATOS PARA EL ECM
					parametros.put("numRuc", t6614ExpVirtualBean.getNumRuc());
					parametros.put("archLength", docAdj.get("pesoArchivo").toString().trim());
					parametros.put("archMimeType", docAdj.get("mimeType").toString().trim());
					parametros.put("archFileName", Utils.convertirUnicode(docAdj.get("nombreArchivo").toString().trim()));
					parametros.put("binDoc", data);
					parametros.put("metadata", Utils.convertirUnicode(docAdj.get("busqueda").toString().trim()));
					parametros.put("codDep", usuarioBean.getCodDepend());
				} else {
					parametros.put("codIdecm", docAdj.get("codIdEcm"));
				}//Fin - [oachahuic][PAS20181U210400241]
				
		        //DATOS GENERALES
				parametros.put("numExpedo", t6614ExpVirtualBean.getNumExpedienteOrigen().trim());
				parametros.put("flagOrigen", ValidaConstantes.IND_REG_DOC_INT);
				parametros.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_EE);
				parametros.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);
				parametros.put("codTipdocRelParam", "");
				parametros.put("numDocRelParam", "");
		        
		        //VERIFICAR SI EL DOCUMENTO SE VA RELACIONAR CON OTRO DOCUMENTO
		        numCorDocRel = Utils.toInteger(Utils.toStr(docAdj.get("asociado")));
				if (numCorDocRel != null) {
					mapParam = new HashMap<String, Object>();
					if (numCorDocRel.intValue() == 0) {//[oachahuic][PAS20181U210400241]
						mapParam.put("numExpeDv", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
						mapParam.put("codTipDoc", docAdj.get("codTipDocRel").toString().trim());
						mapParam.put("numDoc", parametros.get("numDoc"));
					} else {
						mapParam.put("numCorDoc", numCorDocRel);
					}
					T6613DocExpVirtBean t6613DocRel = documentoExpedienteService.obtenerDocumentoExpediente(mapParam);
					//VERIFICAR SI EL DOCUMENTO ES LA CONSTANCIA DE NOTIFICACIÓN
					if (ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {
						parametros.put("fecNotDocRel", Utils.stringToDate(docAdj.get("fecNotRel").toString().trim(), CatalogoConstantes.INT_TWO)); //[ATORRECH 2017-04-26]
						parametros.put("forNotDocRel", codTipDoc);
						//VERIFICAR SI ES LA CONSTANCIA DE NOTIFICACIÓN DEL EXPEDIENTE PARA ACTUALIZAR EL ESTADO Y/O ETAPA
						if (t6614ExpVirtualBean.getNumExpedienteOrigen().trim().equals(t6613DocRel.getNumDoc().trim())) {
							mapParam = new HashMap<String, Object>();
							mapParam.put("codTipoDoc", codTipDoc);
							parametros.putAll(configuracionExpedienteService.obtenerEstadoEtapa(mapParam));
						}
					}
					parametros.put("numCordocRel", t6613DocRel.getNumCorDoc());
					parametros.put("indVisDocRel", Utils.toStr(docAdj.get("indVisDocumento")));
					parametros.put("actDocRel", true);
				} else {					   
					parametros.put("numCordocRel", 0);					
				}
				
				//VERIFICAR SI EL TIPO DE DOCUMENTO REALIZAR UN CAMBIO DE ESTADO Y/O ETAPA SIN CONSIDERAR LAS CONSTANCIAS DE NOTIFICACIÓN
				if (!ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {
					Map<String, Object> estEtapMap = new HashMap<String, Object>();
					estEtapMap.put("codTipoDoc", codTipDoc);
					parametros.putAll(configuracionExpedienteService.obtenerEstadoEtapa(estEtapMap));
				}
				
				//[PAS20181U210400213] VERIFICAR SI EL TIPO DE DOCUMENTO GENERA ACUMULACIÓN O SEPARACIÓN DE EXPEDIENTES. SOLO EXP DE COBRANZA COACTIVA
				if (CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_RC_SEPARACION.equals(codTipDoc) ||
						ValidaConstantes.NUMERO_RC_ACUMULADOR.equals(codTipDoc)) {
					mapParam = new HashMap<String, Object>();
					mapParam.put("numExpVir", t6614ExpVirtualBean.getNumExpedienteVirtual().trim());
					mapParam.put("numRc", docAdj.get("numDoc").toString().trim());
					mapParam.put("codDep", t6614ExpVirtualBean.getCodDependencia().trim());
					parametros.put("listExpAcum", cobranzaCoactivaService.obtenerExpedientesAcumulados(mapParam));
					
				} else {
					parametros.put("indAcu", ValidaConstantes.IND_ACU_NO);
				}
				
				//VERIFICAR SI EL TIPO DE DOCUMENTO TIENE COMO FINALIDAD EL CIERRE DEL EXPEDIENTE
				if (ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE.equals(t6623Bean.getIndTipDocumento())) {
					parametros.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
				} else {
					parametros.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_OTRO);
				}
				
				Map<String, Object> mapResponse = expedienteVirtualService.registrarDocumentosExpediente(parametros); //28 JUNIO se agrego el mapResponse para recibir el retorno del metodo regDoc
				
				//28 JUNIO eaguilar : graba seguimiento para los acumulados
				if (mapResponse.get("mapSeguiAcum") != null) {
					expedienteVirtualService.registrarSeguimientoAcumulados((Map<String, Object>) mapResponse.get("mapSeguiAcum"));
				}
			}
			modelo.addObject("grabadoExitoso", true);
			}
				
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.adjuntarArchivosExpediente");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", "indError");
			modelo.addObject("grabadoExitoso", false);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.adjuntarArchivosExpediente");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	public ModelAndView cargarArchivoTemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.cargarArchivoTemp");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		FileWriter fileWriter = null;
		String fileLoc = null;
		String nombreArchivoTemp = null;
		
		try{
			
			multipartRequest = (MultipartHttpServletRequest) request;
			multipartFile = multipartRequest.getFile("docFisico");
			//String ruta = "../data0/uploads/";
			String nombreFile =  multipartFile.getOriginalFilename();
			
			Date fecSistema = new Date();
			
			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FECHA_TEMP_ARCHIVO);
			
			String fecVista = formatoFechaExporta.format(fecSistema);
				
			nombreArchivoTemp = fecVista+"_"+nombreFile;
			
			//[PAS20191U210500144] Inicio 
			log.debug("flag firma digital: " + request.getParameter("hiddFirmaDigital") );
			if (ValidaConstantes.IND_FIRMA_DIGITAL.equals(request.getParameter("hiddFirmaDigital"))){
				//fileLoc=ValidaConstantes.RUTA_TEMP_LOCAL_POR_FIRMAR+nombreArchivoTemp;
				fileLoc=ValidaConstantes.RUTA_TEMP_LOCAL_POR_FIRMAR + nombreArchivoTemp;
			} else {
				fileLoc=ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + nombreArchivoTemp;
			} 
			//[PAS20191U210500144] Fin 
			
				
			File newFile = new File(fileLoc);
				
            // if the directory does not exist, create it
            if (!newFile.getParentFile().exists()) {
              newFile.getParentFile().mkdirs();  
			}
			
            FileCopyUtils.copy(multipartFile.getBytes(), newFile);
			
			//File archivo_server = new File("../data0/uploads/"+multipartFile.getName());

			modelo.addObject("nombreArchivoTemp", nombreArchivoTemp);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.cargarArchivoTemp");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.cargarArchivoTemp");
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.resolveException");
		ModelAndView view = new ModelAndView(jsonView);
		
		if (exception instanceof MaxUploadSizeExceededException){
			
			Map<String, Object> resultado = new HashMap<String, Object>();
			String indInconsistencia = "1";
			
			try{
				
				resultado.put("indInconsistencia", indInconsistencia);
				view.addObject("resultado", resultado);
				
			} catch (Exception ex) {
				
				if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.resolveException");
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				view = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				view.addObject("beanErr", msb);
				
			} finally {
				
				if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.resolveException");
				NDC.pop();
				NDC.remove();
				
			}
			
		}
		
		return view;
		
	}
	@SuppressWarnings("resource")
	public ModelAndView validaDocumento (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.validaDocumento");
		
		ModelAndView modelo = null;
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		
		Pattern pattern = null;
		Matcher matcher = null;
		FileWriter fileWriter = null;
		
		try {
			
 			String indCarga = request.getParameter("indCarga");
 			log.debug("indCarga: " + indCarga);
 			modelo = new ModelAndView(jsonView);



			if(ValidaConstantes.CARGA_INICIAL.equals(indCarga)){
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();

				}
				
				@SuppressWarnings("unchecked")
				
				Map<String, Object> datosBusquedaExpedientes = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				log.debug("codTipoDocumento: " + datosBusquedaExpedientes.get("codTipoDocumento"));
				log.debug("numDoc: " + datosBusquedaExpedientes.get("numDocuemento"));
				log.debug("numExp: " + datosBusquedaExpedientes.get("numExpeVirtual"));
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				List<Map<String, Object>> listDocRepetido = new ArrayList<Map<String, Object>>();
				
				parametros.put("codTipDoc", datosBusquedaExpedientes.get("codTipoDocumento").toString().trim());
				parametros.put("numDoc", datosBusquedaExpedientes.get("numDocuemento").toString().trim());
				parametros.put("numExpeDv", datosBusquedaExpedientes.get("numExpeVirtual").toString().trim());
			
				listDocRepetido = expedienteVirtualService.obtenerDocumentosExpediente(parametros);
				log.debug("listaDocRepetidos: " + listDocRepetido);

				
				modelo.addObject("listDocRepetido", listDocRepetido);
		
			}else if(ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)){
				
				multipartRequest = (MultipartHttpServletRequest) request;
				multipartFile = multipartRequest.getFile("docFisico");
				
				String pesoTotal = request.getParameter("pesoTotal").toString();
				if(Utils.isEmpty(pesoTotal)){
					pesoTotal = "0";
				}
				long pesoVista = Long.parseLong(pesoTotal);
				
				long pesoEnvio = 0;
				
				String rutaCompleta = null;
				
				String nombreTemporal = null;
				
				String extension = ValidaConstantes.CADENA_VACIA;
				
				String nombreCompleto= ValidaConstantes.CADENA_VACIA;
				
				Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				List<T01ParamBean> listExtensionesPermitidas = paramService.listarParametros(mapa);


				Map<String, Object> params = new HashMap<String,Object>();
				params = new HashMap<String,Object>();
				params.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
				params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(params);
				long tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IA"));
				long tamanoBytes = (tamanoMaximo*1024)*1024;
				boolean archivoValido = false;
				int i = multipartFile.getOriginalFilename().lastIndexOf('.');
				if (i > 0) {
					
				 	extension = multipartFile.getOriginalFilename().substring(i+1);
				 	
					for (T01ParamBean t01ParamBean : listExtensionesPermitidas) {
						if(Utils.equiv(extension, t01ParamBean.getDesParametro())){
							archivoValido = true;
						}
					}
					
					nombreCompleto = Utils.convertirUnicode(multipartFile.getOriginalFilename()); //eaguilar 22 JUN
					
					if(nombreCompleto.length()<=50){
						
						if(!archivoValido) {
							modelo.addObject("tamanoArchivoSuperado", true);
							modelo.addObject("error", 1);
						}else{
							if(multipartFile.getSize() <= 0){
								modelo.addObject("tamanoArchivoSuperado", true);
								modelo.addObject("error", 0);
							}
							else if(multipartFile.getSize() < tamanoBytes){
								pesoEnvio = pesoVista + multipartFile.getSize();
								if(pesoEnvio < tamanoBytes){
									
									modelo.addObject("tamanoArchivoSuperado", false);
									modelo.addObject("pesoArchivo", multipartFile.getSize());
									modelo.addObject("nombreArchivo", Utils.convertirUnicode(multipartFile.getOriginalFilename()));//eaguilar 22 JUN
									modelo.addObject("pesoTotal", pesoEnvio);
									modelo.addObject("mimeType", multipartFile.getContentType());
								}else{
									modelo.addObject("tamanoArchivoSuperado", true);
									modelo.addObject("error", 3);
								}	
								
							} else {
								
								modelo.addObject("tamanoArchivoSuperado", true);
								modelo.addObject("error", 2);
							}
						}

					}else{
						modelo.addObject("tamanoArchivoSuperado", true);
						modelo.addObject("error", 4);
					}
				}
			}
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.validaDocumento");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.validaDocumento");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
	
	}

	/**
	 * ver Archivo
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView verArchivo (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.verArchivo");
		
		ModelAndView modelo;
		String lista = null;
		String numOrden =  null;
		String nombreTemporal = null;
		
		try {
			modelo = null;
			String numArchivo = request.getParameter("hiddenNumArchivo").toString().trim();
			lista = request.getParameter("hiddenListaExp");
				
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(lista, ArrayList.class);
			for (Map<String, Object> map : listadoExportar) {
				numOrden = map.get("numOrden").toString().trim();
				if (numArchivo.equals(numOrden)) {
					
					log.debug("id_ecm: " + map.get("codIdEcm"));
					if (map.get("codIdEcm") == null) {
						nombreTemporal = map.get("nombreArchivoTemp").toString().trim();
						File downloadFile = new File(ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + nombreTemporal);
						FileInputStream inputStream = new FileInputStream(downloadFile);
						ServletContext context = getServletContext();
						String mimeType = context.getMimeType(ValidaConstantes.RUTA_TEMP_LOCAL_SPRING + nombreTemporal);
						
						if (mimeType == null) {
							mimeType = "application/octet-stream";
						}
						
						response.setContentType(mimeType);
						response.setContentLength((int) downloadFile.length());
						
						// set headers for the response
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						response.setHeader(headerKey, headerValue);
						
						byte[] buffer = new byte[(int) downloadFile.length()];
						int bytesRead = inputStream.read(buffer);
						
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						outStream.write(buffer, 0, bytesRead);
						outStream.writeTo(response.getOutputStream());
						outStream.close();
						inputStream.close();
						
					} else {
						Map<String, Object> mapParam =  new HashMap<String, Object>();
						mapParam.put("codIdecm", map.get("codIdEcm").toString());
						mapParam.put("inline", "false");
						ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapParam);
						String headerKey = "Content-Disposition";
				        String headerValue = String.format("attachment; filename=\"%s\"", map.get("nombreArchivo").toString());
				        response.setHeader(headerKey, headerValue);
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						outStream.write(responseDoc.getBody(), 0, responseDoc.getBody().length);
						outStream.writeTo(response.getOutputStream());
						outStream.close();
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.verArchivo");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.verArchivo");
		}
		return modelo;
		
	}
	/*ver archivo ECM*/
	
	@RequestMapping(value = "/descargarArchivo", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public void descargarArchivo (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.descargarArchivo");
		ResponseEntity<byte[]> responseDoc = null;
		ModelAndView modelo = null;
		String numIdEcm;
		OutputStream os=null;
		String nombreArchivo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - RegistroDocInternosController.descargarArchivo");
			
			Map<String, Object> mapParametrosBusqueda=  new HashMap<String, Object>();
			numIdEcm =request.getParameter("codIdentificadorECM").toString().trim(); 
			mapParametrosBusqueda.put("codIdecm", numIdEcm);
			mapParametrosBusqueda.put("inline", "true");
			nombreArchivo = Utils.convertirUnicode(Utils.toStr(request.getParameter("nombreArchivo")));
			
			responseDoc=ecmService.descargarDocumentoECM(mapParametrosBusqueda);
			MediaType contentType = responseDoc.getHeaders().getContentType();
			if(!(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF) || 
					Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) ||
							Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG))){
				String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",nombreArchivo);
		        response.setHeader(headerKey, headerValue);
			}
			os = response.getOutputStream();
			os.write(responseDoc.getBody());
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.descargarArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.descargarArchivo");
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
	
	@SuppressWarnings("unchecked")
	public ModelAndView validarNumDocumento(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.validarNumDocumento");
		
		ModelAndView modelo = new ModelAndView(jsonView);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			boolean copiaDoc = false;
			
			if(reader != null) {
				jsonEnviado = reader.readLine();
			}
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			log.debug("codDependencia: " + dataEnvio.get("codDependencia"));
			log.debug("numeroRuc: " + dataEnvio.get("numeroRuc"));
			log.debug("codTipoExpediente: " + dataEnvio.get("codTipoExpediente"));
			log.debug("numExpeOrigen: " + dataEnvio.get("numExpeOrigen"));
			log.debug("codTipoDoc: " + dataEnvio.get("codTipoDoc"));
			log.debug("numDocumento: " + dataEnvio.get("numDocumento"));
			log.debug("codTipoDoc3Dig: " + dataEnvio.get("codTipoDoc3Dig"));
			log.debug("numExpVirtual: " + dataEnvio.get("numExpVirtual"));
			log.debug("copiaDoc: " + dataEnvio.get("copiaDoc"));
			
			//	INICIO LLRB 28012020
			// 1. si validar si el documento existe en el expediente utilizar documentoExpedienteService y validar si es copia personalizada
			Map<String, Object> parametros  = new HashMap<String, Object>();		
			
			parametros.put("codTipDoc", dataEnvio.get("codTipoDoc").toString().trim());
			parametros.put("numDoc", dataEnvio.get("numDocumento").toString().trim());
			parametros.put("numExpeDv", dataEnvio.get("numExpVirtual").toString().trim());
		
			T6613DocExpVirtBean documentoBean = documentoExpedienteService.obtenerDocumentoExpediente(parametros);
			log.debug("documentoBean: " + documentoBean);
			copiaDoc = (Boolean) dataEnvio.get("copiaDoc");			
			if(documentoBean !=null && copiaDoc == true){	
				log.debug("copiaDocB: " + copiaDoc);
				DocOrigenBean docOrigen = new DocOrigenBean();
				docOrigen.setCodTipDoc(dataEnvio.get("codTipoDoc").toString());
				docOrigen.setNumDoc(dataEnvio.get("numDocumento").toString());
				docOrigen.setFecEmiDoc(Utils.stringToDate(Utils.toStr(documentoBean.getFecDoc()),CatalogoConstantes.INT_FOUR));
				docOrigen.setFecNotDoc(Utils.stringToDate(Utils.toStr(documentoBean.getFecNotif()),CatalogoConstantes.INT_FOUR));
				docOrigen.setCodTipDocRel("");
				docOrigen.setNumDocRel("");
				docOrigen.setNumCorDocRel("");
				docOrigen.setIndVisDocRel("");
				docOrigen.setCodRpta("1");
				docOrigen.setNumCorDoc(documentoBean.getNumCorDoc());
				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				SimpleDateFormat formatoFechaVista = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				Date StrFecEmiDoc = (Date)documentoBean.getFecDoc();				
				docOrigen.setStrFecEmiDoc((formatoFechaVista.format(StrFecEmiDoc)).toString());
				docOrigen.setDocNotSine(null);
				modelo.addObject("docOrigen", docOrigen);
				return modelo;
			}
			if(documentoBean !=null && copiaDoc == false){
				//retorna una excepcion indicando que el nro Y tipo de documento ya existe en el expediente virtual
							
				DocOrigenBean docOrigen = new DocOrigenBean();
				docOrigen.setCodRpta("0");
				docOrigen.setDesError(AvisoConstantes.EXCEP_MODULO_01_06_019);
				modelo.addObject("docOrigen", docOrigen);		
				return modelo;
			}else if(documentoBean ==null && copiaDoc == true){
				DocOrigenBean docOrigen = new DocOrigenBean();
				docOrigen.setCodRpta("0");
				//cambio 13/04-llrb
				docOrigen.setDesError(AvisoConstantes.EXCEP_MODULO_01_06_020);
				modelo.addObject("docOrigen", docOrigen);		
				return modelo;
			}
//			FIN LLRB 28012020
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("cod_dep", dataEnvio.get("codDependencia"));
			mapParam.put("num_ruc", dataEnvio.get("numeroRuc"));
			mapParam.put("cod_tip_exp", dataEnvio.get("codTipoExpediente"));
			mapParam.put("num_exp_ori", dataEnvio.get("numExpeOrigen"));
			mapParam.put("cod_tip_doc", dataEnvio.get("codTipoDoc"));
			mapParam.put("num_doc", dataEnvio.get("numDocumento"));
			mapParam.put("cod_mae", dataEnvio.get("codTipoDoc3Dig"));
			mapParam.put("num_exp_vir", dataEnvio.get("numExpVirtual"));
			
			DocOrigenBean docOrigenBean = configuracionExpedienteService.obtenerDatosDocOrigen(mapParam);
			
			if (docOrigenBean == null) {
				docOrigenBean = new DocOrigenBean();
				docOrigenBean.setCodRpta("0");
				docOrigenBean.setDesError("Ocurrió un error al validar el N° de documento.");
				modelo.addObject("docOrigen", docOrigenBean);
			} else {//No existe la validacion	02
				modelo.addObject("docOrigen", docOrigenBean);	
					
			}
			log.debug("CodRpta: " + docOrigenBean.getCodRpta());

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.validarNumDocumento");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.validarNumDocumento");
		}		
		return modelo;
	}
	//INICIO LLRB 17012020
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpDoc(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_03_02;

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
	    	   
	    	listadoExportarCadena = request.getParameter("hiddenListaExp");
	    	numRuc = request.getParameter("hiddenNumRuc");
		    razonSocial = request.getParameter("hiddenRazonSocial");
		    numExpOrigen = request.getParameter("hiddenNumExpOrigen");
		    numExpVirtual = request.getParameter("hiddenNumExpVirtual");
		    estExpediente = request.getParameter("hiddenEstExpediente");
		    tipoProceso = request.getParameter("hiddenTipoProceso");
		    tipoExpediente = request.getParameter("hiddenTipoExpediente");
		    descOrigen = request.getParameter("hiddenDescOrigen");
		    fechaGeneracion = request.getParameter("hiddenFechaGeneracion");
		    fechaOrigen = request.getParameter("hiddenFechaOrigen");
		    razonSocialRuc = numRuc +" - "+razonSocial;
		    
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
	       	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,4);
			hoja.addMergedRegion(rango);
	        
	        
	        HSSFCell nomFecha = fila.createCell(5);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(6);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(Utils.convertirUnicode(razonSocialRuc));
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente Origen");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);
	        
	        HSSFCell subtituloCelda3 = fila.createCell(3);
	        subtituloCelda3.setCellValue("N° Expediente Virtual");
	        HSSFCell contenido2 = fila.createCell(4);
	        contenido2.setCellValue(numExpVirtual);
	        
	        HSSFCell subtituloCelda4 = fila.createCell(5);
	        subtituloCelda4.setCellValue("Estado del Expediente");
	        HSSFCell contenido3 = fila.createCell(6);
	        contenido3.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);
	        
	        HSSFCell subtituloCelda7 = fila.createCell(5);
	        subtituloCelda7.setCellValue("Origen");
	        HSSFCell contenido6 = fila.createCell(6);
	        contenido6.setCellValue(Utils.convertirUnicode(descOrigen));//eaguilar 6 JUN
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda8 = fila.createCell(1);
	        subtituloCelda8.setCellValue("Fecha de Generación del Documento");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda9 = fila.createCell(3);
	        subtituloCelda9.setCellValue("Fecha de Documento Origen");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(fechaOrigen);
	        
	        fila = hoja.createRow(9);
	        CellRangeAddress rango3 = null;
	        HSSFCell subtituloCelda10 = fila.createCell(0);
	        subtituloCelda10.setCellValue("Documentos Adjuntados al Expediente");
	        rango3 = new CellRangeAddress(9,9,0,2);
			hoja.addMergedRegion(rango3);
			
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        //[PAS20191U210500144] Inicio 
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        //[PAS20191U210500144] Fin
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        HSSFCell celda8 = fila.createCell(8);
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Tipo de Documento");
	        celda2.setCellValue("Número de Documento");
	        celda3.setCellValue("Fecha de Documento");
	        //[PAS20191U210500144] Inicio
	        celda4.setCellValue("Fecha de Notificación");
	        celda5.setCellValue("Forma de Notificación");
	        //[PAS20191U210500144] Fin
	        celda6.setCellValue("Nombre del Archivo");
	        celda7.setCellValue("Origen");
	        celda8.setCellValue("Profesional Responsable");
	        
	        
	       	        
	        HSSFFont fuente = libro.createFont();
	        fuente.setFontHeightInPoints((short) 11);
	        fuente.setFontName(fuente.FONT_ARIAL);
	        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        Sheet ssheet = libro.getSheetAt(0);
	        ssheet.autoSizeColumn(0);
	        ssheet.autoSizeColumn(1);
	        ssheet.autoSizeColumn(2);
	        ssheet.autoSizeColumn(3);
	        //[PAS20191U210500144] Inicio
	        ssheet.autoSizeColumn(4);
	        ssheet.autoSizeColumn(5);
	        //[PAS20191U210500144] Fin
	        ssheet.autoSizeColumn(6);
	        ssheet.autoSizeColumn(7);
	        ssheet.autoSizeColumn(8);
	        
	        
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
	        //[PAS20191U210500144] Inicio
	        celda7.setCellStyle(estiloCelda);
	        celda8.setCellStyle(estiloCelda);
	        //[PAS20191U210500144] Fin
	        	        
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
               texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipdoc").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
               celda2 = fila.createCell(2);
               celda2.setCellValue(listadoExportar.get(i).get("numDoc").toString().trim());
               hoja.autoSizeColumn(2);
               celda2.setCellStyle(estiloRecorrido);
               celda3 = fila.createCell(3);
               celda3.setCellValue(listadoExportar.get(i).get("fechaVista").toString().trim());
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               //[PAS20191U210500144] Inicio
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("fecNotifVista").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desForNotif").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               //[PAS20191U210500144] Fin 
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("descArchivo").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desOrigen").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);
               celda8 = fila.createCell(8);
               celda8.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("nomPersonaCargo").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);
              			
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
           
	         String filename="rpt_ doc_internos x_expediente_virtual_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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
	
	@SuppressWarnings("static-access")
	public ModelAndView seleccionaExportarExcel(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_03_03;

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
	       String pesoTotal = null;
	       
	       try {
	    	   
	    	listadoExportarCadena = request.getParameter("hiddenListaExp");
	    	numRuc = request.getParameter("hiddenNumRuc");
		    razonSocial = request.getParameter("hiddenRazonSocial");
		    numExpOrigen = request.getParameter("hiddenNumExpOrigen");
		    numExpVirtual = request.getParameter("hiddenNumExpVirtual");
		    estExpediente = request.getParameter("hiddenEstExpediente");
		    tipoProceso = request.getParameter("hiddenTipoProceso");
		    tipoExpediente = request.getParameter("hiddenTipoExpediente");
		    descOrigen = request.getParameter("hiddenDescOrigen");
		    fechaGeneracion = request.getParameter("hiddenFechaGeneracion");
		    fechaOrigen = request.getParameter("hiddenFechaOrigen");
		    pesoTotal = request.getParameter("hiddenPesoTotal"); 
			
		    razonSocialRuc = numRuc +" - "+razonSocial;
		    
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
	        hoja.setColumnWidth(5, 6500);
	        hoja.setColumnWidth(6, 5000);
	       	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,4);
			hoja.addMergedRegion(rango);
	        
	        
	        HSSFCell nomFecha = fila.createCell(5);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(6);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(razonSocialRuc);
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente Origen");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);
	        
	        HSSFCell subtituloCelda3 = fila.createCell(3);
	        subtituloCelda3.setCellValue("N° Expediente Virtual");
	        HSSFCell contenido2 = fila.createCell(4);
	        contenido2.setCellValue(numExpVirtual);
	        
	        HSSFCell subtituloCelda4 = fila.createCell(5);
	        subtituloCelda4.setCellValue("Estado del Expediente");
	        HSSFCell contenido3 = fila.createCell(6);
	        contenido3.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);
	        
	        HSSFCell subtituloCelda7 = fila.createCell(5);
	        subtituloCelda7.setCellValue("Origen");
	        HSSFCell contenido6 = fila.createCell(6);
	        contenido6.setCellValue(descOrigen);
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda8 = fila.createCell(1);
	        subtituloCelda8.setCellValue("Fecha de Generación del Documento");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda9 = fila.createCell(3);
	        subtituloCelda9.setCellValue("Fecha de Documento Origen");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(fechaOrigen);
	        
	        fila = hoja.createRow(9);
	        CellRangeAddress rango3 = null;
	        HSSFCell subtituloCelda10 = fila.createCell(0);
	        subtituloCelda10.setCellValue("Documentos Pendientes por Adjuntar al Expediente");
	        rango3 = new CellRangeAddress(9,9,0,2);
			hoja.addMergedRegion(rango3);
			
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        
	       
	        celda.setCellValue("N°");
	        celda1.setCellValue("Tipo de Documento");
	        celda2.setCellValue("Número de Documento");
	        celda3.setCellValue("Fecha de Documento");
	        celda4.setCellValue("Archivo seleccionado");
	        celda5.setCellValue("Tamaño del archivo");
	       
	       	        
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
	        
	        int newCelda = 11;           
	        for (int i = 0; i < listadoExportar.size(); i++) {
            
	           fila = hoja.createRow(i + 11);
	           celda = fila.createCell(0);
	           texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
	           celda.setCellValue(texto.toString());
	           hoja.autoSizeColumn(0);
	           celda.setCellStyle(estiloRecorrido);
	           celda1 = fila.createCell(1);
	           celda1.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipdoc").toString().trim()));
	           hoja.autoSizeColumn(1);
	           celda1.setCellStyle(estiloRecorrido);
	           celda2 = fila.createCell(2);
	           celda2.setCellValue(listadoExportar.get(i).get("numDoc").toString().trim());
	           hoja.autoSizeColumn(2);
	           celda2.setCellStyle(estiloRecorrido);
	           
	           /**/
	           celda3 = fila.createCell(3);
	           celda3.setCellValue(listadoExportar.get(i).get("fechaDoc").toString().trim());
	           hoja.autoSizeColumn(3);
	           celda3.setCellStyle(estiloRecorrido);
	           /**/
	           celda4 = fila.createCell(4);
	           celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("nombreArchivo").toString().trim()));
	           hoja.autoSizeColumn(4);
	           celda4.setCellStyle(estiloRecorrido);
	           celda5 = fila.createCell(5);
	           celda5.setCellValue(listadoExportar.get(i).get("tamanoDoc").toString().trim());
	           hoja.autoSizeColumn(5);
	           celda5.setCellStyle(estiloRecorrido);
	           newCelda++;	
	        }
	        
	        fila = hoja.createRow(newCelda);	
	        CellRangeAddress rango5 = null;
	        HSSFCell nombreValorTotal = fila.createCell(3);
	        nombreValorTotal.setCellValue("Total del Tamaño de Archivos");
	        rango5 = new CellRangeAddress(newCelda,newCelda,3,4);
			hoja.addMergedRegion(rango5);
			HSSFCell valor = fila.createCell(5);
			valor.setCellValue(pesoTotal);
			nombreValorTotal.setCellStyle(estiloTitulo);
			valor.setCellStyle(estiloTitulo);
			
	         Calendar calendar = Calendar.getInstance();

	         int anio = (calendar.get(Calendar.YEAR));
	         int dia = (calendar.get(Calendar.DATE));
	         int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
	         int minutos = (calendar.get(Calendar.MINUTE));
	         String dd = (dia<10) ? "0"+dia : dia+"";
           
	         int mes = calendar.get(Calendar.MONTH)+1;
           
	         String mm = (mes<10) ? "0"+mes : mes+"";
           
	         String part=anio+mm+dd+"_"+hora+minutos;
           
	         String filename="rpt_doc_internos_pendientes_adjuntar_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpedientesFisca(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_03_01;

	       ModelAndView view = null;
	       MensajeBean mensajeBean = new MensajeBean();
	       String listadoExportarCadena = null;
	       //Inicio [gangles 29/08/2016]
	       String expAcum="";
	       //Fin [gangles 29/08/2016]
	       
	       try {
	             
	    	listadoExportarCadena = request.getParameter("listadoExpedientesCadena");
			
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
	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,6);
			hoja.addMergedRegion(rango);
	        
	        HSSFCell nomFecha = fila.createCell(7);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(8);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(4);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);	     
	        //Inicio [gangles 29/08/2016]--Se agrega las columnas de acumulación
	        HSSFCell nAcumulador = fila.createCell(2);
	        HSSFCell indAcumulador = fila.createCell(3);
	      //Fin [gangles 29/08/2016] 
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        HSSFCell celda8 = fila.createCell(8);
	        HSSFCell celda9 = fila.createCell(9);

	       
	        celda.setCellValue("N°");
	        celda1.setCellValue("N° Expediente");	        
	        //Inicio [gangles 29/08/2016] 
	        nAcumulador.setCellValue("N° Expediente Acumulador");
	        indAcumulador.setCellValue("Indicador de Acumulación");
	        //Fin [gangles 29/08/2016] 
	        celda4.setCellValue("RUC");
	        celda5.setCellValue("Proceso");
	        celda6.setCellValue("Tipo de Expediente");
	        celda7.setCellValue("Fecha de Generación");
	        celda8.setCellValue("Fecha de Origen");
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
	        nAcumulador.setCellStyle(estiloCelda);
	        indAcumulador.setCellStyle(estiloCelda);
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
            
               fila = hoja.createRow(i + 5);
               celda = fila.createCell(0);
               texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(listadoExportar.get(i).get("numExpedienteOrigen").toString().trim());
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);              
               //Inicio [gangles 29/08/2016]--Se agrega las columnas de acumulación
               nAcumulador = fila.createCell(2);
               expAcum= listadoExportar.get(i).get("numExpedienteAcumulador")!=null?listadoExportar.get(i).get("numExpedienteAcumulador").toString().trim():ValidaConstantes.CADENA_VACIA;
               if(Utils.equiv(expAcum, "0")){
            	   nAcumulador.setCellValue("");
               }else{
            	   nAcumulador.setCellValue(expAcum);
               }
               hoja.autoSizeColumn(2);
               nAcumulador.setCellStyle(estiloRecorrido);
               indAcumulador = fila.createCell(3);
               indAcumulador.setCellValue(listadoExportar.get(i).get("desIndicadorAcumulado").toString().trim());
               hoja.autoSizeColumn(3);
               indAcumulador.setCellStyle(estiloRecorrido);
               //Fin [gangles 29/08/2016]
               celda4 = fila.createCell(4);
               celda4.setCellValue(listadoExportar.get(i).get("numRuc").toString().trim());
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desProceso").toString().trim()));
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipoExpediente").toString().trim()));
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(listadoExportar.get(i).get("fecRegistro").toString().trim());
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);
               celda8 = fila.createCell(8);
               celda8.setCellValue(listadoExportar.get(i).get("fechaDocumentoOrigen").toString().trim());
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);
               celda9 = fila.createCell(9);
               celda9.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desOrigen").toString().trim()));
               hoja.autoSizeColumn(9);
               celda9.setCellStyle(estiloRecorrido);
  
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
           
	         String filename="rpt_expediente_virtual_doc_internos_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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

	@SuppressWarnings("static-access")
	public ModelAndView exportarExcelExpDocFisca(HttpServletRequest request, HttpServletResponse response){
	       
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   Date fechaActual = new Date();
		   String fecImpresion = sdf.format(fechaActual);
	
	       String titulo = ExportaConstantes.TITULO_EXPORTA_03_02;

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
	    	   
	    	listadoExportarCadena = request.getParameter("hiddenListaExp");
	    	numRuc = request.getParameter("hiddenNumRuc");
		    razonSocial = request.getParameter("hiddenRazonSocial");
		    numExpOrigen = request.getParameter("hiddenNumExpOrigen");
		    numExpVirtual = request.getParameter("hiddenNumExpVirtual");
		    estExpediente = request.getParameter("hiddenEstExpediente");
		    tipoProceso = request.getParameter("hiddenTipoProceso");
		    tipoExpediente = request.getParameter("hiddenTipoExpediente");
		    descOrigen = request.getParameter("hiddenDescOrigen");
		    fechaGeneracion = request.getParameter("hiddenFechaGeneracion");
		    fechaOrigen = request.getParameter("hiddenFechaOrigen");
		    razonSocialRuc = numRuc +" - "+razonSocial;
		    
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
	       	        
	        HSSFRow fila = hoja.createRow(1);
	        CellRangeAddress rango = null;
	        HSSFCell tituloCelda = fila.createCell(0);
	        tituloCelda.setCellValue(titulo);
	        rango = new CellRangeAddress(1,1,0,4);
			hoja.addMergedRegion(rango);
	        
	        
	        HSSFCell nomFecha = fila.createCell(5);
	        nomFecha.setCellValue("Fecha del Reporte:");
	        	        
	        HSSFCell Fecha = fila.createCell(6);
	        Fecha.setCellValue(fecImpresion);
	        
	        fila = hoja.createRow(3);
	        CellRangeAddress rango1 = null;
	        HSSFCell subtituloCelda = fila.createCell(0);
	        subtituloCelda.setCellValue("Datos del Expediente");
	        rango1 = new CellRangeAddress(3,3,0,1);
			hoja.addMergedRegion(rango1);
	        
	        fila = hoja.createRow(4);	
	        CellRangeAddress rango2 = null;
	        HSSFCell subtituloCelda1 = fila.createCell(1);
	        subtituloCelda1.setCellValue("RUC");
	        HSSFCell contenido = fila.createCell(2);
	        contenido.setCellValue(Utils.convertirUnicode(razonSocialRuc));
	        rango2 = new CellRangeAddress(4,4,2,3);
			hoja.addMergedRegion(rango2);
	        
	        fila = hoja.createRow(5);	        
	        HSSFCell subtituloCelda2 = fila.createCell(1);
	        subtituloCelda2.setCellValue("N° Expediente ");
	        HSSFCell contenido1 = fila.createCell(2);
	        contenido1.setCellValue(numExpOrigen);

	        HSSFCell subtituloCelda4 = fila.createCell(5);
	        subtituloCelda4.setCellValue("Estado del Expediente");
	        HSSFCell contenido3 = fila.createCell(6);
	        contenido3.setCellValue(estExpediente);
	        
	        fila = hoja.createRow(6);	        
	        HSSFCell subtituloCelda5 = fila.createCell(1);
	        subtituloCelda5.setCellValue("Proceso");
	        HSSFCell contenido4 = fila.createCell(2);
	        contenido4.setCellValue(tipoProceso);
	        
	        HSSFCell subtituloCelda6 = fila.createCell(3);
	        subtituloCelda6.setCellValue("Tipo de Expediente");
	        HSSFCell contenido5 = fila.createCell(4);
	        contenido5.setCellValue(tipoExpediente);
	        
	        HSSFCell subtituloCelda7 = fila.createCell(5);
	        subtituloCelda7.setCellValue("Origen");
	        HSSFCell contenido6 = fila.createCell(6);
	        contenido6.setCellValue(Utils.convertirUnicode(descOrigen));//eaguilar 6 JUN
	        
	        fila = hoja.createRow(7);	        
	        HSSFCell subtituloCelda8 = fila.createCell(1);
	        subtituloCelda8.setCellValue("Fecha de Generación del Documento");
	        HSSFCell contenido7 = fila.createCell(2);
	        contenido7.setCellValue(fechaGeneracion);
	        
	        HSSFCell subtituloCelda9 = fila.createCell(3);
	        subtituloCelda9.setCellValue("Fecha de Documento Origen");
	        HSSFCell contenido8 = fila.createCell(4);
	        contenido8.setCellValue(fechaOrigen);
	        
	        fila = hoja.createRow(9);
	        CellRangeAddress rango3 = null;
	        HSSFCell subtituloCelda10 = fila.createCell(0);
	        subtituloCelda10.setCellValue("Documentos Adjuntados al Expediente");
	        rango3 = new CellRangeAddress(9,9,0,2);
			hoja.addMergedRegion(rango3);
			
	        fila = hoja.createRow(10);
	        HSSFCell celda = fila.createCell(0);
	        HSSFCell celda1 = fila.createCell(1);
	        HSSFCell celda2 = fila.createCell(2);
	        HSSFCell celda3 = fila.createCell(3);
	        //[PAS20191U210500144] Inicio 
	        HSSFCell celda4 = fila.createCell(4);
	        HSSFCell celda5 = fila.createCell(5);
	        //[PAS20191U210500144] Fin
	        HSSFCell celda6 = fila.createCell(6);
	        HSSFCell celda7 = fila.createCell(7);
	        HSSFCell celda8 = fila.createCell(8);
	        
	        celda.setCellValue("N°");
	        celda1.setCellValue("Tipo de Documento");
	        celda2.setCellValue("Número de Documento");
	        celda3.setCellValue("Fecha de Documento");
	        //[PAS20191U210500144] Inicio
	        celda4.setCellValue("Fecha de Notificación");
	        celda5.setCellValue("Forma de Notificación");
	        //[PAS20191U210500144] Fin
	        celda6.setCellValue("Nombre del Archivo");
	        celda7.setCellValue("Origen");
	        celda8.setCellValue("Profesional Responsable");
	        
	        
	       	        
	        HSSFFont fuente = libro.createFont();
	        fuente.setFontHeightInPoints((short) 11);
	        fuente.setFontName(fuente.FONT_ARIAL);
	        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        Sheet ssheet = libro.getSheetAt(0);
	        ssheet.autoSizeColumn(0);
	        ssheet.autoSizeColumn(1);
	        ssheet.autoSizeColumn(2);
	        ssheet.autoSizeColumn(3);
	        //[PAS20191U210500144] Inicio
	        ssheet.autoSizeColumn(4);
	        ssheet.autoSizeColumn(5);
	        //[PAS20191U210500144] Fin
	        ssheet.autoSizeColumn(6);
	        ssheet.autoSizeColumn(7);
	        ssheet.autoSizeColumn(8);
	        
	        
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
	        //[PAS20191U210500144] Inicio
	        celda7.setCellStyle(estiloCelda);
	        celda8.setCellStyle(estiloCelda);
	        //[PAS20191U210500144] Fin
	        	        
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
               texto = new HSSFRichTextString(listadoExportar.get(i).get("numOrden").toString().trim());
               celda.setCellValue(texto.toString());
               hoja.autoSizeColumn(0);
               celda.setCellStyle(estiloRecorrido);
               celda1 = fila.createCell(1);
               celda1.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desTipdoc").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(1);
               celda1.setCellStyle(estiloRecorrido);
               celda2 = fila.createCell(2);
               celda2.setCellValue(listadoExportar.get(i).get("numDoc").toString().trim());
               hoja.autoSizeColumn(2);
               celda2.setCellStyle(estiloRecorrido);
               celda3 = fila.createCell(3);
               celda3.setCellValue(listadoExportar.get(i).get("fechaVista").toString().trim());
               hoja.autoSizeColumn(3);
               celda3.setCellStyle(estiloRecorrido);
               //[PAS20191U210500144] Inicio
               celda4 = fila.createCell(4);
               celda4.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("fecNotifVista").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(4);
               celda4.setCellStyle(estiloRecorrido);
               celda5 = fila.createCell(5);
               celda5.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desForNotif").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(5);
               celda5.setCellStyle(estiloRecorrido);
               //[PAS20191U210500144] Fin 
               celda6 = fila.createCell(6);
               celda6.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("descArchivo").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(6);
               celda6.setCellStyle(estiloRecorrido);
               celda7 = fila.createCell(7);
               celda7.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("desOrigen").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(7);
               celda7.setCellStyle(estiloRecorrido);
               celda8 = fila.createCell(8);
               celda8.setCellValue(Utils.convertirUnicode(listadoExportar.get(i).get("nomPersonaCargo").toString().trim())); //eaguilar 6 JUN
               hoja.autoSizeColumn(8);
               celda8.setCellStyle(estiloRecorrido);
              			
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
           
	         String filename="rpt_ doc_internos x_expediente_virtual_" + part + ".xls";;
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
	          String headerValue = String.format("inline; filename=\"%s\"",
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
	public ModelAndView seleccionarNroCopia (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistroDocInternosController.seleccionarNroCopia");
		 ModelAndView modelo;
		
		 try{
			 BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				if(reader != null) {
					jsonEnviado = reader.readLine();
				}
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				Map<String, Object> nroCopia = new HashMap<String, Object>();			
				
			log.debug("numCorDoc:"+ dataEnvio.get("numCorDoc"));
			Integer numCorDoc =Integer.parseInt(dataEnvio.get("numCorDoc").toString());
			Integer numCopia = null;
		    nroCopia.put("numCorDoc", numCorDoc);
		    Integer numCopiaTemp = expedienteVirtualService.obtenerNroCopia(nroCopia);
			int aumento = 1;				
			numCopia=(Integer.parseInt(numCopiaTemp.toString())) + aumento;	
			log.debug("nroCopia:"+numCopia);
			modelo = new ModelAndView("jsonView");
			modelo.addObject("numCopia", numCopia);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistroDocInternosController.seleccionarNroCopia");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistroDocInternosController.seleccionarNroCopia");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	//FIN LLRB 17012020
	
	/*Sets*/
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

	public void setExpedienteVirtualService(
			ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setValidarParametrosService(
			ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	
	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	// Inicio [jquispe 27/05/2016]
	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}
	// Fin [jquispe 27/05/2016]

	public void setResolucionService(ResolucionService resolucionService) {
		this.resolucionService = resolucionService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	
	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}

	public void setCobranzaCoactivaService(CobranzaCoactivaService cobranzaCoactivaService) {
		this.cobranzaCoactivaService = cobranzaCoactivaService;
	}
	
}

