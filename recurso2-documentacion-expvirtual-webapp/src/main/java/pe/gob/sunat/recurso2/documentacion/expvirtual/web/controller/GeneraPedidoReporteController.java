package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteTrabReporteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.GeneraReportePedidoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ManttoPlanRepService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.PersonaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class GeneraPedidoReporteController extends MultiActionController{
	private static final Log log = LogFactory.getLog(GeneraPedidoReporteController.class);
	private JsonView jsonView;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ManttoPlanRepService manttoPlanRepService;
	private ValidarParametrosService validarParametrosService;
	private ExpedienteVirtualService expedienteVirtualService;
	private PersonaService personaService;
	private GeneraReportePedidoService generaReportePedidoService;
	private CatalogoService catalogoService;
	private ExpedienteTrabReporteService expedienteTrabReporteService;
	private String numPedido;
	private String nroRegistroUsuario;
	private JmsTemplate jmsTemplate;
	
	public GeneraPedidoReporteController() {
		super();
	}
	
	public GeneraPedidoReporteController(String numPedido, String nroRegistroUsuario, ExpedienteTrabReporteService expedienteTrabReporteService) {
		this.numPedido = numPedido;
		this.nroRegistroUsuario = nroRegistroUsuario;
		this.expedienteTrabReporteService = expedienteTrabReporteService;
	}
	
	//Inicio nchavez 13/07/2016
	/*
	public void run() {
		List<RepExpTrabBean> listaRepExpTrabBeans = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneraPedidoReporteController.run - GenerarPedido");
		
		try {
			listaRepExpTrabBeans = this.getExpedienteTrabReporteService().obtenerDatosGeneraRep(this.getNumPedido());
			this.getExpedienteTrabReporteService().generarReporteRUC(listaRepExpTrabBeans, this.getNumPedido(), this.getNroRegistroUsuario());
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - GeneraPedidoReporteController.run - GenerarPedido");
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - GeneraPedidoReporteController.run - GenerarPedido");
			
			NDC.pop();
			NDC.remove();
		}
    }
	*/
	//Fin nchavez 13/07/2016
	
	public ModelAndView cargarBandPedidoRep (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneraPedidoReporteController.cargarBandPedidoRep");
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		List<T6614ExpVirtualBean> listadoExpendientes = new ArrayList<T6614ExpVirtualBean>();
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
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				String codDependenciaBase = usuarioBean.getCodDepend();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaActual = new Date();
			    String fecVista = sdf.format(fechaActual);
			    
			    /**/
				Map<String, Object> mapa = new HashMap<String,Object>();
				mapa = new HashMap<String,Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(mapa);
				int tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IA"));
				/**/
			    
			    Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_05_06_001);
				titulos.put("tituloRegistro", AvisoConstantes.TITULO_MODULO_05_06_002);
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion1", AvisoConstantes.AVISO_MODULO_05_06_001);
				excepciones.put("excepcion2", AvisoConstantes.AVISO_MODULO_05_06_002);
				excepciones.put("excepcion3", AvisoConstantes.AVISO_MODULO_05_06_003);
				excepciones.put("excepcion4", AvisoConstantes.AVISO_MODULO_05_06_004);
				excepciones.put("excepcion5", AvisoConstantes.AVISO_MODULO_05_06_005);
				excepciones.put("excepcion6", AvisoConstantes.AVISO_MODULO_05_06_006);
				excepciones.put("excepcion7", AvisoConstantes.AVISO_MODULO_05_06_007);
				excepciones.put("excepcion8", AvisoConstantes.AVISO_MODULO_05_06_008);
				excepciones.put("excepcion9", AvisoConstantes.AVISO_MODULO_05_06_009);
				excepciones.put("excepcion10", AvisoConstantes.AVISO_MODULO_05_06_010);
				excepciones.put("excepcion11", AvisoConstantes.AVISO_MODULO_05_06_011);
				excepciones.put("excepcion12", AvisoConstantes.AVISO_MODULO_05_06_012);
				excepciones.put("excepcion13", AvisoConstantes.AVISO_MODULO_05_06_013);
				excepciones.put("excepcion14", AvisoConstantes.AVISO_MODULO_05_06_014);
				excepciones.put("excepcion15", AvisoConstantes.AVISO_MODULO_05_06_015);
				excepciones.put("excepcion16", AvisoConstantes.AVISO_MODULO_05_06_016);
				
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
	
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_05_06_001);
				
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependenciaBase));
				modelo.addObject("tamanoMaximo", new JsonSerializer().serialize(tamanoMaximo));
				
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
				
			}else if(ValidaConstantes.CARGA_SUPERIOR.equals(indCarga)){
				
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
				String codTipoExp = dataEnvio.get("codTipoExp").toString();
				
				parametros.put("codProceso", codProceso);
				parametros.put("codTipoExp", codTipoExp);
				parametros.put("indEstado", ValidaConstantes.IND_ESTADO_PLANTILLA_ACTIVO);
				
				List<T6611CabPlantiBean> listadoPantillasPorExp = manttoPlanRepService.listarPlantillasPorExpe(parametros);
				
				modelo.addObject("listadoPantillasPorExp", listadoPantillasPorExp);
				
			}else if(ValidaConstantes.FILTRO_TRES.equals(indCarga)){
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				String numRuc = dataEnvio.get("numRuc").toString();
				
				DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
				//nchavez 13/06/2016
				if(!Utils.isEmpty(contribuyente.getNumRuc())){
					Map<String, Object> paramsRuc = new HashMap<String, Object>();
					
					paramsRuc.put("numeroRuc", contribuyente.getNumRuc().toString().trim());
					paramsRuc.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
					paramsRuc.put("codEstado", ValidaConstantes.IND_ESTADO_REQ_ABIERTO);
					
					listadoExpendientes = expedienteVirtualService.listarRegDocExpediente(paramsRuc);
					
					if(listadoExpendientes.size()!=0){
						modelo.addObject("listadoExpendientes", listadoExpendientes);
					}
				}
				
				modelo.addObject("contribuyente", contribuyente);
				
			}else{
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
			
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - GeneraPedidoReporteController.cargarBandPedidoRep");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - GeneraPedidoReporteController.cargarBandPedidoRep");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	@SuppressWarnings("resource")
	public ModelAndView validaSubirDocumento(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneraPedidoReporteController.validaSubirDocumento");
		
		ModelAndView modelo = null;
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		Pattern pattern = null;
		Matcher matcher = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		String fileLoc = null;
		String nombreArchivoTemp = null;
		
		try {
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			} else {
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			}
			
			String indCarga = request.getParameter("indCarga");
 			modelo = new ModelAndView(jsonView);
 			
 			Map<String, Object> params = new HashMap<String,Object>();
			params = new HashMap<String,Object>();
			params.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapaTamanoTotalPermitido = catalogoService.obtenerCatalogo(params);
 			long tamanoMaximo = Utils.toInteger(mapaTamanoTotalPermitido.get("IA"));
			long tamanoBytes = (tamanoMaximo*1024)*1024;
			
			if(ValidaConstantes.CARGA_INICIAL.equals(indCarga)){
				
				multipartRequest = (MultipartHttpServletRequest) request;
				multipartFile = multipartRequest.getFile("docFisico");
								
				String extension = ValidaConstantes.CADENA_VACIA;
				
				int i = multipartFile.getOriginalFilename().lastIndexOf('.');
				if (i > 0) {
				    extension = multipartFile.getOriginalFilename().substring(i+1);
				    pattern = Pattern.compile(ValidaConstantes.VALID_EXT_ARCH_PLANO);
					matcher = pattern.matcher(extension);
					
					if((!matcher.matches())) {
						modelo.addObject("fljError", true);
						modelo.addObject("error", 1);
					}else{
						if(multipartFile.getBytes().length==0){
							modelo.addObject("fljError", true);
							modelo.addObject("error", 2);
						}else{	
							
							if(multipartFile.getSize() < tamanoBytes){
								
								String nombreFile =  multipartFile.getOriginalFilename();
								
								Date fecSistema = new Date();
								
								SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FECHA_TEMP_ARCHIVO);
								
								String fecVista = formatoFechaExporta.format(fecSistema);
									
								nombreArchivoTemp = fecVista+"_"+nombreFile;
									
								fileLoc=ValidaConstantes.CARPETA_TEMPORAL+nombreArchivoTemp;
									
								File newFile = new File(fileLoc);
									
					            if (!newFile.getParentFile().exists()) {
					              newFile.getParentFile().mkdirs();  
								}
								
					            FileCopyUtils.copy(multipartFile.getBytes(), newFile);
					            
								modelo.addObject("nombreArchivoTemp", nombreArchivoTemp);
								modelo.addObject("fljError", false);
							}else{
								modelo.addObject("fljError", true);
								modelo.addObject("error", 3);
							}
						}
					}
				}
			}	
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - GeneraPedidoReporteController.validaSubirDocumento");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - GeneraPedidoReporteController.validaSubirDocumento");
			
			NDC.pop();
			NDC.remove();
			//fileWriter.close();
			
		}
		
		return modelo;
	
	}
	
	@SuppressWarnings("resource")
	public ModelAndView validaDocumento(HttpServletRequest request, HttpServletResponse response){
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneraPedidoReporteController.validaDocumento");
		
		ModelAndView modelo = null;
		BufferedReader br = null;
		String numRuc = null;
		String codResponsable = null;
		int fila = 0;
		String lineaError = null;
		List<String> listaErroresAcumulados = new ArrayList<String>();		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		String rutaCompleta = null;
		FileWriter fileWriter = null;
        List<String> listaArchivo = null;
		List<T6614ExpVirtualBean> listadoExpendientes = new ArrayList<T6614ExpVirtualBean>();
		Map<String, Object> parametrosRegistro = new HashMap<String, Object>();
		String numPedido = null;		
		Map<String, Object> mapParam = null;
		File archivo = null;
		
		try {
			
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{
				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
				
			}
			
			String codDependenciaBase = usuarioBean.getCodDepend();
			
 			String indCarga = request.getParameter("indCarga");
 			modelo = new ModelAndView(jsonView);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			
			if(reader != null) {
				jsonEnviado = reader.readLine();
			}
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);

			String codProceso = dataEnvio.get("codProceso").toString().trim();
			String codTipoExp = dataEnvio.get("codTipoExp").toString().trim();
			String codPlantilla = dataEnvio.get("codPlantilla").toString().trim();
			String tipoGenera = dataEnvio.get("tipoGenera").toString().trim();
			
			if(ValidaConstantes.CARGA_INICIAL.equals(indCarga)){
				
				String numRucCarga = dataEnvio.get("numRuc").toString().trim();
				
				parametrosRegistro.put("codProceso", codProceso);
				parametrosRegistro.put("codTipoExp", codTipoExp);
				parametrosRegistro.put("codPlantilla", codPlantilla);
				parametrosRegistro.put("tipoGenera", tipoGenera);
				parametrosRegistro.put("numRuc", numRucCarga);
				parametrosRegistro.put("respRegistro", usuarioBean.getNroRegistro());
				numPedido = generaReportePedidoService.registrarPedidoReporte(parametrosRegistro);
				
				//Inicio nchavez 13/07/2016
				//(new Thread(new GeneraPedidoReporteController(numPedido, ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH, this.getExpedienteTrabReporteService()))).start();
				 
			    Map<String,Object> message=new HashMap<String,Object>();
			    message.put("numPedido", numPedido);
				message.put("nroRegistroUsuario", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
				 
				if (log.isDebugEnabled()) log.debug("GeneraPedidoReporteController - Inicio de envio a  Cola numero pedido:"+numPedido);
				
			    jmsTemplate.convertAndSend(message, new MessagePostProcessor() {
			    	final Log log = LogFactory.getLog(GeneraPedidoReporteController.class);

			    	@Override
					public Message postProcessMessage(Message message) throws JMSException {
			    		 if (log.isDebugEnabled()) log.debug("GeneraPedidoReporteController.MessagePostProcessor - Enviando message");
			    		 
						 message.setStringProperty("application", CatalogoConstantes.SELECTOR_RECURSO_GENERAEXPTRABAJO);
						 return message;
					}
				});
			   //Fin nchavez 13/07/2016
			    
			}else if(ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {
				String nomArchivo = dataEnvio.get("nomArchivo").toString();
				FileReader in = new FileReader(ValidaConstantes.CARPETA_TEMPORAL + nomArchivo);
				archivo = 	new File(ValidaConstantes.CARPETA_TEMPORAL + nomArchivo);
				br = new BufferedReader(in);
				
				if(br != null) {
					listaArchivo = new ArrayList<String>();
					
					String linea = null;
			        while((linea = br.readLine()) != null) {
			        	linea = linea.trim();
			        	
						if(!linea.equals("")) {
							listaArchivo.add(linea);
						}
					}					
				}
				
				if(listaArchivo != null && listaArchivo.size() > 0) {
					for(String linea : listaArchivo) {
						fila++;
						
						if(linea != null) {
							String[] cantColumnas = linea.split(ValidaConstantes.PARAM_SPLIT);
							int numCol = cantColumnas.length;
							
							if(numCol == 1) {
								String ultimoCaracter = null;
								String[] temp = new String[2];
								
								ultimoCaracter = linea.substring(linea.length() - 1, linea.length());
								if(ultimoCaracter.equals(ValidaConstantes.PARAM_PALOTE)) {
									numCol = 2;
									temp[0] = cantColumnas[0];
									temp[1] = "";
									cantColumnas = temp;
								}
							}
							
							//if(fila != 1) {
								if(numCol != 2){
									lineaError = 	linea +
													ValidaConstantes.PARAM_PALOTE +
													AvisoConstantes.AVISO_MODULO_05_06_022 +
													ValidaConstantes.PARAM_PALOTE +
													"línea -->" + fila;
									listaErroresAcumulados.add(lineaError);
								} else {
									numRuc = cantColumnas[0];
									numRuc = numRuc.trim();
									
									if(!numRuc.equals("")) { // inicio if (1)
										if(numRuc.length() == 11) { // inicio if (2)
											DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
											//nchavez 13/06/2016
											if(!Utils.isEmpty(contribuyente)){
												if(codDependenciaBase.equals(contribuyente.getCodDependencia())) {
													
													Map<String, Object> paramsRuc = new HashMap<String, Object>();
													
													paramsRuc.put("numeroRuc", numRuc);
													paramsRuc.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
													paramsRuc.put("codEstado", ValidaConstantes.IND_ESTADO_REQ_ABIERTO);
													
													listadoExpendientes = expedienteVirtualService.listarRegDocExpediente(paramsRuc);
													
													if(listadoExpendientes.size() > 0) {
														codResponsable = cantColumnas[1];
														codResponsable = codResponsable.trim();
														
														if(!codResponsable.equals("")) { // inicio if (3)
															if(codResponsable.length() == 4) { // inicio if (4)
																Map<String, Object> mapPersona = new HashMap<String, Object>();
																
																mapPersona.put("codPersona", codResponsable);
																mapPersona.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);
		
																T02PerdpBean responsable = personaService.validarPersona(mapPersona);
																
																if(responsable != null){
																	String codUnidadOrganizacional = null;
																	
																	if(responsable.getCodUnidadOrganizacional() != null && !responsable.getCodUnidadOrganizacional().equals("")) {
																		codUnidadOrganizacional = responsable.getCodUnidadOrganizacional().trim();
																	}
																	
																	Map<String, Object> mapDependencia = new HashMap<String, Object>();
																	
																	mapDependencia.put("codUnidadOrganizacional", codUnidadOrganizacional);
																	mapDependencia.put("codDependencia", codDependenciaBase);
																	mapDependencia.put("codTipoDependencia", ValidaConstantes.IND_TIPO_DEPENDENCIA_INTRANET);
																	
																	T1797DepenUOrgaBean unidadOrganizacionalDependenica = personaService.validarDependenciaUnidadOrganizacional(mapDependencia);
																	
																	if (unidadOrganizacionalDependenica != null) {
																		String depUnidad = unidadOrganizacionalDependenica.getCodDependencia().toString().trim();
																		
																		if(codDependenciaBase.equals(depUnidad)){
																			//Exito
																		}else{
																			lineaError = 	linea + 
																							ValidaConstantes.PARAM_PALOTE +
																							AvisoConstantes.AVISO_MODULO_05_06_021 + 
																							ValidaConstantes.PARAM_PALOTE +
																							"línea -->" + fila +
																							" columna -->2";
																			listaErroresAcumulados.add(lineaError);
																		}																
																	} else {
																		lineaError = 	linea +
																						ValidaConstantes.PARAM_PALOTE +
																						AvisoConstantes.AVISO_MODULO_05_06_024 +
																						ValidaConstantes.PARAM_PALOTE +
																						"línea -->" + fila +
																						" columna -->2";
																		listaErroresAcumulados.add(lineaError);
																	}															
																} else {															
																	lineaError = 	linea + 
																					ValidaConstantes.PARAM_PALOTE +
																					AvisoConstantes.AVISO_MODULO_05_06_020 +
																					ValidaConstantes.PARAM_PALOTE +
																					"línea -->" + fila +
																					" columna -->2";
																	listaErroresAcumulados.add(lineaError);															
																}													
															} else { // else (4)
																lineaError = 	linea +
																				ValidaConstantes.PARAM_PALOTE +
																				AvisoConstantes.AVISO_MODULO_05_06_025 +
																				ValidaConstantes.PARAM_PALOTE +
																				"línea -->" + fila +
																				" columna -->2";
																listaErroresAcumulados.add(lineaError);
															} // fin if (4)	
														} else { // else (3)
															lineaError = 	linea +
																	ValidaConstantes.PARAM_PALOTE +
																	AvisoConstantes.AVISO_MODULO_05_06_027 +
																	ValidaConstantes.PARAM_PALOTE +
																	"línea -->" + fila +
																	" columna -->2";
															listaErroresAcumulados.add(lineaError);
														} // fin if (3)
													} else {													
														lineaError = 	linea +
																		ValidaConstantes.PARAM_PALOTE +
																		AvisoConstantes.AVISO_MODULO_05_06_023 +
																		ValidaConstantes.PARAM_PALOTE +
																		"línea -->" + fila +
																		" columna -->1";
														listaErroresAcumulados.add(lineaError);													
													}												
												} else {
													lineaError = linea +
																	ValidaConstantes.PARAM_PALOTE +
																	AvisoConstantes.AVISO_MODULO_05_06_018 +
																	ValidaConstantes.PARAM_PALOTE +
																	"línea -->" + fila +
																	" columna -->1";
													listaErroresAcumulados.add(lineaError);
												}
											} else {
												lineaError = 	linea +
																ValidaConstantes.PARAM_PALOTE +
																AvisoConstantes.AVISO_MODULO_05_06_017 +
																ValidaConstantes.PARAM_PALOTE +
																"línea -->" + fila +
																" columna -->1";
												listaErroresAcumulados.add(lineaError);
											}
										} else {  // else (2)
											lineaError = 	linea +
															ValidaConstantes.PARAM_PALOTE +
															AvisoConstantes.AVISO_MODULO_05_06_019 +
															ValidaConstantes.PARAM_PALOTE +
															"línea -->" + fila +
															" columna -->1";
											listaErroresAcumulados.add(lineaError);
										} 
									} else { // else (1)
										lineaError = 	linea +
												ValidaConstantes.PARAM_PALOTE +
												AvisoConstantes.AVISO_MODULO_05_06_026 +
												ValidaConstantes.PARAM_PALOTE +
												"línea -->" + fila +
												" columna -->1";
										listaErroresAcumulados.add(lineaError);
									} // fin if (1)
								}
							//} else {
							//	   lineaError = linea;
							//	   listaErroresAcumulados.add(lineaError);
							//}
						} else {
							break;
						}
					}
				}
				
				//Escribir archivo Errores
				if(listaErroresAcumulados.size() > 0) {
					modelo.addObject("fljError", true);
					modelo.addObject("indicador", 1);
					rutaCompleta = ValidaConstantes.CARPETA_TEMPORAL + ValidaConstantes.NOM_ARCH_ERROR; 
					fileWriter = new FileWriter(rutaCompleta);
					
					for(String s : listaErroresAcumulados) {
						fileWriter.write(s+ "\r\n");
					}
				}else{
					modelo.addObject("fljError", false);
					List<Map<String, Object>> listaRecorrido = null;
					
					if(listaArchivo != null && listaArchivo.size() > 0) {
						Map<String, Object> campos = null;
						listaRecorrido = new ArrayList<Map<String,Object>>();
						
						for(String linea : listaArchivo) {
							if(linea != null) {								
								String[] cantColumnas = linea.split(ValidaConstantes.PARAM_SPLIT);
								
								campos = new HashMap<String, Object>();
								campos.put("numRuc", cantColumnas[0]);
								campos.put("respRucEnvio", cantColumnas[1]);
								
								listaRecorrido.add(campos);
							}
						}						
					}
					
					parametrosRegistro.put("codProceso", codProceso);
					parametrosRegistro.put("codTipoExp", codTipoExp);
					parametrosRegistro.put("codPlantilla", codPlantilla);
					parametrosRegistro.put("tipoGenera", tipoGenera);
					parametrosRegistro.put("listaRecorrido", listaRecorrido);
					parametrosRegistro.put("respRegistro", usuarioBean.getNroRegistro());
					
					numPedido = generaReportePedidoService.registrarPedidoReporte(parametrosRegistro);
				}
			}
			
			modelo.addObject("numPedido", numPedido);
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - GeneraPedidoReporteController.validaDocumento");
			
			log.error(ex, ex);
			
			modelo.addObject("fljError", true);
			modelo.addObject("indicador", 2);
			
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				
				if(fileWriter != null) {
					fileWriter.close();
				}
				
				if(archivo != null) {
					archivo.delete();
				}				
			} catch(Exception ex) {
				log.error(ex, ex);
			}
			
			
			if (log.isDebugEnabled()) log.debug("Final - GeneraPedidoReporteController.validaDocumento");
			
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
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneraPedidoReporteController.verArchivo");
		
		ModelAndView modelo;
		String lista = null;
		String numOrden =  null;
		String nombreTemporal = null;
		String nombreReal = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - GeneraPedidoReporteController.verArchivo");
			
			modelo = null;
			nombreTemporal = ValidaConstantes.NOM_ARCH_ERROR;
			File downloadFile = new File(ValidaConstantes.CARPETA_TEMPORAL + nombreTemporal);
			File archivo = 	new File(ValidaConstantes.CARPETA_TEMPORAL+nombreTemporal);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			ServletContext context = getServletContext();
			String mimeType = context.getMimeType(ValidaConstantes.CARPETA_TEMPORAL + nombreTemporal);
			
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
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
			
			archivo.delete();
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - GeneraPedidoReporteController.verArchivo");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - GeneraPedidoReporteController.verArchivo");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	
	
	/*Sets*/
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	public String getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	public String getNroRegistroUsuario() {
		return nroRegistroUsuario;
	}

	public void setNroRegistroUsuario(String nroRegistroUsuario) {
		this.nroRegistroUsuario = nroRegistroUsuario;
	}

	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	
	public void setManttoPlanRepService(ManttoPlanRepService manttoPlanRepService) {
		this.manttoPlanRepService = manttoPlanRepService;
	}

	public void setValidarParametrosService(
			ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public ExpedienteVirtualService getExpedienteVirtualService() {
		return expedienteVirtualService;
	}

	public void setExpedienteVirtualService(
			ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setGeneraReportePedidoService(
			GeneraReportePedidoService generaReportePedidoService) {
		this.generaReportePedidoService = generaReportePedidoService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public ExpedienteTrabReporteService getExpedienteTrabReporteService() {
		return expedienteTrabReporteService;
	}

	public void setExpedienteTrabReporteService(
			ExpedienteTrabReporteService expedienteTrabReporteService) {
		this.expedienteTrabReporteService = expedienteTrabReporteService;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	
}