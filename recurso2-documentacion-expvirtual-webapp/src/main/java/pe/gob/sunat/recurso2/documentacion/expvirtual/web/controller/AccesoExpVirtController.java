package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10459AccepExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AccesoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ALIGN;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils.ExcelUtil.ColumOption;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class AccesoExpVirtController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AccesoExpVirtController.class);
	
	private JsonView jsonView;
	
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private AccesoExpedienteService accesoService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	public ModelAndView cargarAccesoExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtualController.cargarAccesoExpediente");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtualController.cargarAccesoExpediente");
			
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
				
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_08_001);
				
				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				
				String codDependencia = usuarioBean.getCodDepend();
				String registroInsert = "0";
				String registroRegresar = "0";
				String numExpediente = "";
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_08_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_03_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_03_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_03_003);
				excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_01_03_012);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_03_006);
				excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_03_011);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_03_005);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_02_01_005);
				
				Map<String, Object> avisos = new HashMap<String, Object>();
				
				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_03_001);
				avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_03_002);
				
				Date fecSistema = new Date();
				
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				
				String fecVista = formatoFechaExporta.format(fecSistema);
				
				String codEstadoExpedientePermitido = ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO;
				
				Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
				
				String desEstadoExpedientePermitido = mapaEstadoExpediente.get(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO).toString();
				
				List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();
				
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
				modelo.addObject("registroInsert", new JsonSerializer().serialize(registroInsert));
				modelo.addObject("registroRegresar", new JsonSerializer().serialize(registroRegresar));
				modelo.addObject("numExpediente", new JsonSerializer().serialize(numExpediente));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				//Inicio - [oachahuic][PAS20165E210400270]
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
				} else {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
				}
				//Fin - [oachahuic][PAS20165E210400270]
				modelo.addObject("codEstadoExpedientePermitido", new JsonSerializer().serialize(codEstadoExpedientePermitido));
				modelo.addObject("desEstadoExpedientePermitido", new JsonSerializer().serialize(desEstadoExpedientePermitido));
				modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));

				
			} else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {
				
				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				String numExpediente;
				String codDependencia = usuarioBean.getCodDepend();
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();

				numExpediente = dataEnvio.get("numExpediente").toString();

				parametros.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN);
				parametros.put("numExpediente", numExpediente);
				
				T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
				
				DdpBean contribuyente = null;
				
				List<T10459AccepExpVirtBean> responsablesAsignados = null;//new ArrayList<T10459AccepExpVirtBean>();
				
				if (expedienteVirtual != null) {
					
					String numExpedienteVirtual = expedienteVirtual.getNumExpedienteVirtual();
					String numRuc = expedienteVirtual.getNumRuc();
					
					contribuyente = validarParametrosService.validarRUC(numRuc);
					
					//nchavez 13/06/2016
					if (!Utils.isEmpty(contribuyente.getNumRuc())) {
												
						String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();
						
						
						log.debug("depexpediente: " + expedienteVirtual.getCodDependencia().toString() );
						
						modelo.addObject("expedienteVirtual", expedienteVirtual);
						modelo.addObject("contribuyente", contribuyente);
						modelo.addObject("eliminarAcceso", "0");
						
						if (codDependenciaBase.substring(0, 3).equals(expedienteVirtual.getCodDependencia().substring(0, 3))) {
							
							parametros = new HashMap<String, Object>();
							
							parametros.put("numExpedienteVirtual", numExpedienteVirtual);
							
							responsablesAsignados = accesoService.listarRelacionResponsables(parametros);
							
							log.debug("asignados: " + responsablesAsignados);
							
							List<T1797DepenUOrgaBean> listadoUnidOrgaDependencia =  accesoService.listarUnidadesOrganizacionales(parametros);
							
							
							modelo.addObject("responsablesAsignados", responsablesAsignados);
							modelo.addObject("dependencia", ValidaConstantes.FILTRO_UNO);
							if(Utils.isEmpty(listadoUnidOrgaDependencia)){
								
								modelo.addObject("indListaUniOrgaDependVacio", ValidaConstantes.FILTRO_INVALIDO);
							}
							else {
								modelo.addObject("indListaUniOrgaDependVacio", ValidaConstantes.FILTRO_UNO);
								
							}
							
							
							
						} //dependencia
						
						else {
							
						modelo.addObject("responsablesAsignados", responsablesAsignados);
						modelo.addObject("dependencia", ValidaConstantes.FILTRO_INVALIDO);
						
						}
						
						return modelo;
						
					} // numruc
					
					
					
				} //expediente
				
				modelo.addObject("expedienteVirtual", expedienteVirtual);
				modelo.addObject("contribuyente", contribuyente);
				modelo.addObject("indListaUniOrgaDependVacio", ValidaConstantes.FILTRO_UNO);
				modelo.addObject("dependencia", ValidaConstantes.FILTRO_UNO);
				modelo.addObject("responsablesAsignados", responsablesAsignados);

				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtualController.cargarAccesoExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AccesoExpVirtualController.cargarAccesoExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
	@SuppressWarnings("static-access")
	public ModelAndView exportarExcel(HttpServletRequest request, HttpServletResponse response){
	       
		if (log.isDebugEnabled())log.info((Object) "Inicio - AccesoExpVirtController.exportarExcel");
		String titulo = ExportaConstantes.TITULO_EXPORTA_02_08;
		ModelAndView view = null;
		MensajeBean mensajeBean = new MensajeBean();
		String listadoExportarCadena = null;
		Calendar fechaVacia = null;
		
		try {
			
			listadoExportarCadena = request.getParameter("listaCadena");
			
			log.debug("cadena: " +  listadoExportarCadena);

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoExportar = (ArrayList<Map<String, Object>>) new JsonSerializer().deserialize(listadoExportarCadena, ArrayList.class);

			// Fecha actual
			//Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);
			
			response.setContentType("application/vnd.ms-excel");
			//Inicio nchavez [27/05/2016]
			
			Map<String, ColumOption> columnsProperties=new LinkedHashMap<String,ColumOption>();
			columnsProperties.put("numSoli",new ColumOption("N° Solicitud",ALIGN.LEFT));
			columnsProperties.put("fecSoli",new ColumOption("Fecha de Solicitud",ALIGN.LEFT));
			columnsProperties.put("desMotivo",new ColumOption("Motivo",ALIGN.LEFT));
			columnsProperties.put("desTipo",new ColumOption("Tipo",ALIGN.LEFT));
			columnsProperties.put("codColaborador",new ColumOption("Registro Usuario",ALIGN.RIGHT));
			columnsProperties.put("desColaborador",new ColumOption("Apellidos y Nombres",ALIGN.LEFT));
			columnsProperties.put("unidOrganizacional",new ColumOption("Unidad Organizacional",ALIGN.LEFT));
			columnsProperties.put("desPeriodo",new ColumOption("Periodo",ALIGN.RIGHT));
			columnsProperties.put("fecInicio",new ColumOption("fecha Inicio",ALIGN.LEFT));
			columnsProperties.put("fecFinal",new ColumOption("fecha Final",ALIGN.LEFT));
			columnsProperties.put("desEstado",new ColumOption("Estado",ALIGN.LEFT));
			
			HSSFWorkbook libro=ExcelUtil.buildWorkbookXLS(titulo, "Hoja 1", columnsProperties, listadoExportar);
		 //Fin nchavez [27/05/2016]
		 Calendar calendar = Calendar.getInstance();
		
		 int anio = (calendar.get(Calendar.YEAR));
		 int dia = (calendar.get(Calendar.DATE));
		 int hora = (calendar.get(Calendar.HOUR_OF_DAY)); 
		 int minutos = (calendar.get(Calendar.MINUTE));
		 String dd = (dia<10) ? "0"+dia : dia+"";
		 int mes = calendar.get(Calendar.MONTH)+1;
		 String mm = (mes<10) ? "0"+mes : mes+"";
		 String part=anio+mm+dd+"_"+hora+minutos;
		   
		 String filename = CatalogoConstantes.RPT_GEN_CONSULTA_ACCE_XLS + part + ".xls";;
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
    	   	 if (log.isDebugEnabled())log.info((Object) "Error - AccesoExpVirtController.exportarExcel");
    	   	 log.error( ex, ex);
    	   	 
             mensajeBean.setError(true);
             mensajeBean.setMensajeerror("Se ha producido un error inesperador al mostrar "
                                        + ex.getMessage());
             view = new ModelAndView("PagM", "beanM", mensajeBean);
       }
		if (log.isDebugEnabled())log.info((Object) "Fin - AccesoExpVirtController.exportarExcel");
		
       return view;

	}
	
	
	public ModelAndView agregarAccesoExpVirtual (HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtController.agregarAcceso");
		ModelAndView modelo = null;
		T6614ExpVirtualBean datosExpBean = null;
		Map<String, Object>	parametros = new HashMap<String, Object>();
		String fecRegistroVista = ValidaConstantes.CADENA_VACIA;
		
		
		try {
			
			String numExpedienteOrigen = request.getParameter("aux");
			log.debug("expori " + numExpedienteOrigen);
			if (numExpedienteOrigen != null || numExpedienteOrigen != ValidaConstantes.CADENA_VACIA) {
				//RECUPERAR DATOS DEL EXPEDIENTE VIRTUAL
				parametros = new HashMap<String, Object>();
				parametros.put("numExpedienteOrigen", numExpedienteOrigen);
				parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
				datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
			}
			
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_08_002);
			modelo.addObject("datosExpedientes", new JsonSerializer().serialize(datosExpBean));
			modelo.addObject("fechaRegistro", new JsonSerializer().serialize(fecRegistroVista));
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtController.agregarAcceso");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final -AccesoExpVirtController.agregarAcceso");
			NDC.pop();
			NDC.remove();
		}
		return modelo;
	}
	
	
	public ModelAndView regresarAccesoExpVirtual (HttpServletRequest request, HttpServletResponse response) {
if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtualController.regresarAccesoExpVirtual");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtualController.regresarAccesoExpVirtual");
			
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			String numExpediente = request.getParameter("aux");
			
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_08_001);
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			String codDependencia = usuarioBean.getCodDepend();
			String registroInsert = "0";
			String registroRegresar = "1";
			
			Map<String, Object> titulos = new HashMap<String, Object>();
			
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_08_001);
			
			Map<String, Object> excepciones = new HashMap<String, Object>();
			
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_03_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_03_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_03_003);
			excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_01_03_012);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_03_006);
			excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_03_011);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_03_005);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_02_01_005);
			
			Map<String, Object> avisos = new HashMap<String, Object>();
			
			avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
			avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_03_001);
			avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_03_002);
			
			Date fecSistema = new Date();
			
			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			
			String fecVista = formatoFechaExporta.format(fecSistema);
			
			String codEstadoExpedientePermitido = ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO;
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
			
			String desEstadoExpedientePermitido = mapaEstadoExpediente.get(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO).toString();
			
			List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();
			
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
			modelo.addObject("registroInsert", new JsonSerializer().serialize(registroInsert));
			modelo.addObject("registroRegresar", new JsonSerializer().serialize(registroRegresar));
			modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
			modelo.addObject("numExpediente", new JsonSerializer().serialize(numExpediente));
			//Inicio - [oachahuic][PAS20165E210400270]
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
				modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
			} else {
				modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
			}
			//Fin - [oachahuic][PAS20165E210400270]
			modelo.addObject("codEstadoExpedientePermitido", new JsonSerializer().serialize(codEstadoExpedientePermitido));
			modelo.addObject("desEstadoExpedientePermitido", new JsonSerializer().serialize(desEstadoExpedientePermitido));
			modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));
			
} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtualController.regresarAccesoExpVirtual");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AccesoExpVirtualController.regresarAccesoExpVirtual");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
public ModelAndView cargarEmpleado (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtController.cargarEmpleado");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtController.cargarEmpleado");
			
			modelo = new ModelAndView(jsonView);
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			String codUsuario = usuarioBean.getNroRegistro();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			
			if(reader != null) {
				
				jsonEnviado = reader.readLine();
				
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			Map<String, Object>	parametros = new HashMap<String, Object>();
			
			String codigoReg = request.getParameter("codigoReg");
			log.debug("codigo: " + codigoReg);
			
			T02PerdpBean empleado = accesoService.obtenerEmpleado(codigoReg);
			
			modelo.addObject("empleado", empleado);
						
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtController.cargarEmpleado");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AccesoExpVirtController.cargarEmpleado");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}



public ModelAndView verificarAcceso (HttpServletRequest request, HttpServletResponse response) {
	
	if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtController.verificarAcceso");
	
	ModelAndView modelo = null;
	
	HttpSession session = request.getSession(true);
	UsuarioBean usuarioBean = null;
	Integer cantidad = 0;
	
	try {
		
		if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtController.verificarAcceso");
		
		modelo = new ModelAndView(jsonView);
		
		if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
			
			String usub = request.getParameter("usub");
			String tenc = request.getParameter("tenc");
			usub = usub.replace(' ', '+');
			usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			
		}else{
			
			usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			
		}
		
		usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonEnviado = "";
		
		if(reader != null) {
			
			jsonEnviado = reader.readLine();
			
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
		
		String numExpediente = dataEnvio.get("numExpediente").toString();
		String numSoli = dataEnvio.get("numSoli").toString();
		
		Map<String, Object> parametrosExp = new HashMap<String, Object>();
		parametrosExp.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN);
		parametrosExp.put("numExpediente", numExpediente);
		
		T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametrosExp);
		
		Map<String, Object>	parametros = new HashMap<String, Object>();
		
		parametros.put("numSoli", numSoli);
		parametros.put("nroExpVirtual", expedienteVirtual.getNumExpedienteVirtual());
		
		cantidad = accesoService.verificarAcceso(parametros);
		
		modelo.addObject("existe", cantidad);
					
	} catch (Exception ex) {
		
		if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtController.verificarAcceso");
		
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		msb.setError(true);
		msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		modelo.addObject("beanErr", msb);
		
	} finally {
		
		if (log.isDebugEnabled()) log.debug("Final - AccesoExpVirtController.verificarAcceso");
		
		NDC.pop();
		NDC.remove();
		
	}
	
	return modelo;
	
}

	
public ModelAndView registrarAccesoExpediente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	if (log.isDebugEnabled()) log.debug("Inicio - registrarAccesoExpedienteController.registrarAcceso");
		
	ModelAndView modelo = null;
	T6614ExpVirtualBean datosExpBean = null;
	Map<String, Object>	parametros = new HashMap<String, Object>();
	Map<String, Object>	paramRegistro = new HashMap<String, Object>();
	String fecRegistroVista = ValidaConstantes.CADENA_VACIA;
	List<T10459AccepExpVirtBean> responsablesAsignados = new ArrayList<T10459AccepExpVirtBean>();
	Calendar fechaVacia = null;
	Date fecInicio;
	Date fecFin;
	
	HttpSession session = request.getSession(true);
	UsuarioBean usuarioBean = null;
	
	try {
		
		if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtController.cargarEmpleado");
		
		modelo = new ModelAndView(jsonView);
		
		if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
			
			String usub = request.getParameter("usub");
			String tenc = request.getParameter("tenc");
			usub = usub.replace(' ', '+');
			usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			
		}else{
			
			usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			
		}
		
		usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
		String codDependencia = usuarioBean.getCodDepend();
		String codUser = usuarioBean.getNroRegistro();
		
		String numSoli = request.getParameter("numSoli").toString();
		String selMotivo = request.getParameter("selMotivo").toString();
		String selPeriodo = request.getParameter("selPeriodo").toString();
		String selTipoSens = request.getParameter("selTipoSens").toString();
		
		String codigoReg = request.getParameter("codigoReg").toString();
		
		log.debug("selPeriodo: " + selPeriodo);
		
		SimpleDateFormat formatter = null;
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);
		String codPer = "02";
		
		Date fechaSoli = formatter.parse(request.getParameter("fechaSoli").toString());
		
		if (selPeriodo.equals(codPer)) {
		fecInicio  = formatter.parse(request.getParameter("fecInicio").toString());
		fecFin = formatter.parse(request.getParameter("fecFin").toString());
		}
		else {
			fecInicio  = new Date();
			fecFin = fechaVacia.getTime();
		}
	
		log.debug("fecfin: " + fecFin);
		log.debug("fecInicio: " + fecInicio);
		
		
		String numExpeOrigen = request.getParameter("numExpeOrigen").toString();
				
		if (numExpeOrigen != null || numExpeOrigen != ValidaConstantes.CADENA_VACIA) {
			//RECUPERAR DATOS DEL EXPEDIENTE VIRTUAL
			parametros = new HashMap<String, Object>();
			parametros.put("numExpedienteOrigen", numExpeOrigen);
			parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			datosExpBean = expedienteVirtualService.obtenerDatosExpediente(parametros);
		}
		
		log.debug("expediente " + datosExpBean);

		paramRegistro.put("numSoli", numSoli);
		paramRegistro.put("numExpedienteVirtual", datosExpBean.getNumExpedienteVirtual());
		paramRegistro.put("codColaborador", codigoReg);
		paramRegistro.put("codMotivo", selMotivo);
		paramRegistro.put("codTipo", selTipoSens);
		paramRegistro.put("codPeriodo", selPeriodo);
		paramRegistro.put("codUsuarioRegistro", codUser);
		paramRegistro.put("codEstado", ValidaConstantes.IND_ORIGEN_EXP_VIRT_MANUAL);
		paramRegistro.put("fecInicio",fecInicio);
		paramRegistro.put("fecFin", fecFin);
		paramRegistro.put("fecSoli", fechaSoli);
		paramRegistro.put("fecRegistro", new Date());
		accesoService.registrarAccesoExpediente(paramRegistro);
		
		String registroInsert = "1";
		String registroRegresar = "0";
		
		
		DdpBean contribuyente = null;

		String numExpedienteVirtual = datosExpBean.getNumExpedienteVirtual();
		String numRuc = datosExpBean.getNumRuc();
		
		contribuyente = validarParametrosService.validarRUC(numRuc);

				parametros = new HashMap<String, Object>();
											
				parametros.put("numExpedienteVirtual", numExpedienteVirtual);
				
				responsablesAsignados = accesoService.listarRelacionResponsables(parametros);
		
		
		
		
		Map<String, Object> titulos = new HashMap<String, Object>();
		
		titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_08_001);
		
		Map<String, Object> excepciones = new HashMap<String, Object>();
		
		excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_03_001);
		excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_03_002);
		excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_03_003);
		excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_03_004);
		excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_03_006);
		excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_03_011);
		excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_03_005);
		
		Map<String, Object> avisos = new HashMap<String, Object>();
		
		avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
		avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_03_001);
		avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_03_002);
		
		Date fecSistema = new Date();
		
		SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
		
		String fecVista = formatoFechaExporta.format(fecSistema);
		
		modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_08_001);
		modelo.addObject("expedienteVirtual", new JsonSerializer().serialize(datosExpBean));
		modelo.addObject("contribuyente", new JsonSerializer().serialize(contribuyente));
		modelo.addObject("responsablesAsignados", new JsonSerializer().serialize(responsablesAsignados));
		modelo.addObject("registroInsert", new JsonSerializer().serialize(registroInsert));
		modelo.addObject("registroRegresar", new JsonSerializer().serialize(registroRegresar));
		modelo.addObject("numExpediente", new JsonSerializer().serialize(numExpeOrigen));
		
		modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
		modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
		modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
		modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
		Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
		if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
			modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
		} else {
			modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
		}
		
	} catch (Exception ex) {
		if (log.isDebugEnabled()) log.debug("Error - registrarAccesoExpedienteController.registrarAcceso");
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		msb.setError(true);
		msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		modelo.addObject("beanErr", msb);
	} finally {
		if (log.isDebugEnabled()) log.debug("Final -registrarAccesoExpedienteController.registrarAcceso");
		NDC.pop();
		NDC.remove();
	}
	return modelo;
	
	
}



public ModelAndView eliminarAccesoExpediente (HttpServletRequest request, HttpServletResponse response) {
	
	if (log.isDebugEnabled()) log.debug("Inicio - AccesoExpVirtualController.eliminarAccesoExpediente");
	
	ModelAndView modelo = null;
	
	HttpSession session = request.getSession(true);
	UsuarioBean usuarioBean = null;
	List<T10459AccepExpVirtBean> responsablesAsignados = null;
	
	try {
		
		if (log.isDebugEnabled()) log.debug("Procesa - AccesoExpVirtualController.eliminarAccesoExpediente");
		
		
		if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
			
			String usub = request.getParameter("usub");
			String tenc = request.getParameter("tenc");
			usub = usub.replace(' ', '+');
			usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			
		}else{
			
			usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			
		}
		
		
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			String codReg = usuarioBean.getNroRegistro().toString();
			if(reader != null) {
				
				jsonEnviado = reader.readLine();
				
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			modelo = new ModelAndView(jsonView);
			
			Map<String, Object> parametrosExp = new HashMap<String, Object>();
			String numExpediente = dataEnvio.get("numExpediente").toString();
			parametrosExp.put("indClaseExpediente", ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN);
			parametrosExp.put("numExpediente", numExpediente);
			
			T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametrosExp);
			
			
			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			String numSoli = dataEnvio.get("numSoli").toString();
			
			parametros.put("numSoli", numSoli);
			parametros.put("codEstado", "02");
			parametros.put("fecModifica", new Date());
			parametros.put("codUsuModifica", codReg);
			
			log.debug("parametros: " + parametros);
			accesoService.eliminarAccesoExpediente(parametros);
			
			Map<String, Object> parametrosResp = new HashMap<String, Object>();
			parametrosResp.put("numExpedienteVirtual", expedienteVirtual.getNumExpedienteVirtual());
			
			responsablesAsignados = accesoService.listarRelacionResponsables(parametrosResp);
			
			
			
			modelo.addObject("eliminarAcceso", "1");
			modelo.addObject("responsablesAsignados", responsablesAsignados);
			
			
		
		
	} catch (Exception ex) {
		
		if (log.isDebugEnabled()) log.debug("Error - AccesoExpVirtualController.cargarAccesoExpediente");
		
		log.error(ex, ex);
		MensajeBean msb = new MensajeBean();
		modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
		msb.setError(true);
		msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
		modelo.addObject("beanErr", msb);
		
	} finally {
		
		if (log.isDebugEnabled()) log.debug("Final - AccesoExpVirtualController.cargarAccesoExpediente");
		
		NDC.pop();
		NDC.remove();
		
	}
	
	return modelo;
	
}

	public ModelAndView realizarAccesoExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsignaResponsableController.realizarAsignacionResponsablesExpediente");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsignaResponsableController.realizarAsignacionResponsablesExpediente");
			
			modelo = new ModelAndView(jsonView);
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			String codUsuario = usuarioBean.getNroRegistro();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			
			if(reader != null) {
				
				jsonEnviado = reader.readLine();
				
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			
			String numExpedienteVirtual = dataEnvio.get("numExpedienteVirtual").toString();
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (ArrayList<Map<String, Object>>) dataEnvio.get("listadoFinal");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			
			List<T10459AccepExpVirtBean> listadoActual = accesoService.listarResponsables(parametros);
			
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);
			
			List<T10459AccepExpVirtBean> listadoInactivo = accesoService.listarResponsables(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("codUsuario", codUsuario);
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			parametros.put("listadoNuevo", listadoNuevo);
			parametros.put("listadoActual", listadoActual);
			parametros.put("listadoInactivo", listadoInactivo);
			
			accesoService.realizarAsignacionResponsableExpediente(parametros);
			
			modelo.addObject("responsablesAsignados", listadoNuevo);
			
			modelo.addObject("responsablesFinales", listadoNuevo);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsignaResponsableController.realizarAsignacionResponsablesExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsignaResponsableController.realizarAsignacionResponsablesExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
	
	
	/* Seteo - Spring - Inicio */
	
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
	
	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setAccesoExpedienteService(AccesoExpedienteService accesoService) {
		this.accesoService = accesoService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	
	/* Seteo - Spring - Final */
	
}