package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CorreosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.PersonaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class MantenimientoExpedienteVirtualController extends MultiActionController{
	
	private static final Log log = LogFactory.getLog(MantenimientoExpedienteVirtualController.class);
	
	private JsonView jsonView;
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ResponsableService responsableService;
	private AduanaService aduanaService;
	private PersonaService personaService;
	private CorreosService correosService;
	private DocumentoExpedienteService documentoExpedienteService;
	
	
	public ModelAndView cargarMantenimientoExpedienteVirtual(HttpServletRequest request, HttpServletResponse response){
			if (log.isDebugEnabled()) log.debug("Inicio - MantenimientoExpedienteVirtualController.cargarMantenimientoExpedienteVirtual");
			ModelAndView modelo = null;
			HttpSession session = request.getSession(true);
			UsuarioBean usuarioBean = null;
			try{
				if (log.isDebugEnabled()) log.debug("Procesa - MantenimientoExpedienteVirtualController.cargarMantenimientoExpedienteVirtual");
				String indCarga = request.getParameter("indCarga");
				if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {
					String usub = request.getParameter("usub");
					String tenc = request.getParameter("tenc");
					usub = usub.replace(' ', '+');
					usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				} else {
					usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
				}
				
				if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
					modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_07_001);
					usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
					String codDependencia = usuarioBean.getCodDepend();

					Map<String, Object> titulos = new HashMap<String, Object>();
					titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_07_001);
					
					Map<String, Object> excepciones = new HashMap<String, Object>();
					excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_07_001);
					excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_07_002);
					excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_01_07_009);
					

					Map<String, Object> avisos = new HashMap<String, Object>();
					avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
					avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_07_001);
					avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_07_002);
					avisos.put("aviso03", AvisoConstantes.AVISO_MODULO_01_07_003);
					avisos.put("aviso04", AvisoConstantes.AVISO_MODULO_01_07_004);
					
					Date fecSistema = new Date();
					SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
					
					String fecVista = formatoFechaExporta.format(fecSistema);
					String codEstadoExpedientePermitido = ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO;

					Map<String, Object> mapa = new HashMap<String, Object>();
					mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
					mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					Map<String, Object> mapaEstadoExpediente = catalogoService.obtenerCatalogo(mapa);
					String desEstadoExpedientePermitido = mapaEstadoExpediente.get(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO).toString();

					List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();
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
					modelo.addObject("codEstadoExpedientePermitido", new JsonSerializer().serialize(codEstadoExpedientePermitido));
					modelo.addObject("desEstadoExpedientePermitido", new JsonSerializer().serialize(desEstadoExpedientePermitido));
					modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));
					
				}else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {//VALIDAR EL INGRESO DEL NÚMERO DE EXPEDIENTE

					usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
					BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
					String jsonEnviado = "";
					if (reader != null) {
						jsonEnviado = reader.readLine();
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
					String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();
					modelo = new ModelAndView(jsonView);
					Map<String, Object> parametros = null;
					
					//VERIFICAR SI EXISTE EL NUMERO EXPEDIENTE INGRESADO EN BD
					parametros = new HashMap<String, Object>();
					parametros.put("indClaseExpediente", dataEnvio.get("indClaseExpediente").toString());
					parametros.put("numExpediente", dataEnvio.get("numExpediente").toString());
					T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
					if (expedienteVirtual == null || ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ELIMINADO.equals(expedienteVirtual.getCodEstado())) {
	                     modelo.addObject("status", false);
	                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_003);
	                     return modelo;
					}
					//VERIFICAR SI EL EXPEDIENTE PERTENECE A LA DEPENDENCIA DEL USUARIO CONECTADO
					if (!Utils.equiv(codDependenciaBase.substring(0, 3), expedienteVirtual.getCodDependencia().substring(0, 3))) {
	                     modelo.addObject("status", false);
	                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_004);
	                     return modelo;
					}
					//VERIFICAR SI ES RESPONSABLE DEL EXPEDIENTE
					parametros = new HashMap<String, Object>();
					parametros.put("num_expedv_par", expedienteVirtual.getNumExpedienteVirtual());
					parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
					parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					
					T6621RespExpVirtBean t6621Bean = responsableService.obtenerResponsable(parametros);
					if (t6621Bean == null) {
	                     modelo.addObject("status", false);
	                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_005);
					}else{
						parametros = new HashMap<String, Object>();
	                    parametros.put("codPersona", usuarioBean.getNroRegistro());
	                    parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);                
	                    T02PerdpBean responsable = personaService.validarPersona(parametros);                     
	                    if(responsable != null) {
	                        responsable = personaService.completarInformacionPersona(responsable);                      
	                        expedienteVirtual.setNombreResponsable(responsable.getDesNombreCompleto());
	                    }

					}
					
					//VERIFICAR QUE SU PROCEDENCIA NO SEA MANUAL
	                if (ValidaConstantes.IND_ORIGEN_EXP_VIRT_AUTOMATICO.equals(expedienteVirtual.getCodOrigen())) {
	                     modelo.addObject("status", false);
	                     modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_007);
	                     return modelo;
	                }
	                


	                //CARGAR DATOS DEL DOCUMENTO DE ORIGEN
	                
	                parametros = new HashMap<String, Object>();
	                parametros.put("numexpediente", expedienteVirtual.getNumExpedienteVirtual());
	                if (expedienteVirtual.getCodEstado().equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO)){
	                	parametros.put("tipodocsustento", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
	                }else if(expedienteVirtual.getCodEstado().equals(ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ABIERTO)){
	                	parametros.put("tipodocsustento", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
	                }
	                T6613DocExpVirtBean DatosDoc =expedienteVirtualService.buscarDatosDocumentos(parametros);
	                if(DatosDoc == null){
    					modelo.addObject("t6613DocExpVirtBean", " ");
    					modelo.addObject("fechaNotificacionDoc", " ");
    					modelo.addObject("fechaNotificacionDoc", " ");
	                }else{
    					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    					modelo.addObject("fechaEmisionDoc", sdf.format(DatosDoc.getFecDoc()));
    					String fechanotifi = sdf.format(DatosDoc.getFecNotif());
    					if(fechanotifi.equals("01/01/0001")){
    						modelo.addObject("fechaNotificacionDoc","-");
    					}else{
    						modelo.addObject("fechaNotificacionDoc",fechanotifi);
    					}
    					
    					T01ParamBean T01TipDoc = configuracionExpedienteService.obtenerTipoDoc(DatosDoc.getCodTipDoc(), CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
    					DatosDoc.setDesNombreCompuesto(DatosDoc.getCodTipDoc() + " - " +T01TipDoc.getDesParametro());
    					modelo.addObject("t6613DocExpVirtBean", DatosDoc);

	                }

					Map<String, Object> mapa = new HashMap<String, Object>();
					
					mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
					mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
					Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
					expedienteVirtual.setDesProceso(mapaProcesos.get(expedienteVirtual.getCodProceso()).toString());
					
					mapa = new HashMap<String, Object>();
					mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
					mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
					Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
					expedienteVirtual.setDesTipoExpediente(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente()).toString());

					DdpBean contribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
                    
                    if (contribuyente == null) {
                          modelo.addObject("contribuyente", null);
                          return modelo;
                    }else if (!Utils.isEmpty(contribuyente.getNumRuc())) {                              
                         log.debug("codDependenciaBase: " + codDependenciaBase);
                         if (codDependenciaBase != null && codDependenciaBase.length() == 3) {
                                contribuyente = aduanaService.obtenerAgenteHabilitado(contribuyente.getNumRuc(), codDependenciaBase);
                         } else {
                                Map<String, Object> mapaA = new HashMap<String, Object>();

                                mapaA.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
                                mapaA.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
                                mapaA.put("indLimite", true);
                                mapaA.put("limInferior", CatalogoConstantes.LIMITE_INFERIOR_DEPENDENCIA);
                                mapaA.put("limSuperior", CatalogoConstantes.LIMITE_SUPERIOR_DEPENDENCIA);

                                Map<String, Object> mapaDependencias = catalogoService.obtenerCatalogo(mapaA);

                                String desDependencia = mapaDependencias.get(contribuyente.getCodDependencia()).toString();
                                contribuyente.setDesDependencia(desDependencia);
                         }
                    }
                    

					//cargar los objetos
					modelo.addObject("expedienteVirtual", expedienteVirtual);
					modelo.addObject("contribuyente", contribuyente);

				}
			}catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - MantenimientoExpedienteVirtualController.cargarMantenimientoExpedienteVirtual");
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - MantenimientoExpedienteVirtualController.cargarMantenimientoExpedienteVirtual");
			}
			return modelo;
		
		}
	
	public ModelAndView eliminarExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (log.isDebugEnabled()) log.debug("Inicio - MantemientoExpedienteVirtualController.eliminarExpedienteVirtual");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
               
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
               
        	@SuppressWarnings("unchecked")                 
        	Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
        	
        	modelo = new ModelAndView(jsonView);
        	
        	Map<String, Object> parametros = null;                
        	parametros = new HashMap<String, Object>();
        	
		    parametros = new HashMap<String, Object>();
		    //OBTENER RESPONSABLES
        	parametros.put("numExpedienteVirtual",dataEnvio.get("numExpediente").toString());
        	parametros.put("desEliExp", dataEnvio.get("desEliExp").toString());               
        	parametros.put("codUsuarioModifica", usuarioBean.getNroRegistro());

        	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(dataEnvio.get("numExpediente").toString());
        	
        	//OBTENER CORREOS DE RESPONSABLES
        	if (!listCodRespAsignados.isEmpty()) {
        		List<CorreosBean> listaCorreosBeans = new ArrayList<CorreosBean>();
				for (String codColaborador : listCodRespAsignados) {
					CorreosBean correosBean = new CorreosBean();
					correosBean.setCodPers(codColaborador);
					listaCorreosBeans.add(correosBean);
				}
        		Map<String, Object> mapConsultaCorreo =  new HashMap<String, Object>();
        		mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
        		listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);   
        		parametros.put("listCorreos", listaCorreosBeans);
        	}
            //DATOS GENERALES
        	parametros.put("numExpedienteOrigen",dataEnvio.get("numExpedienteOrigen").toString());
           	parametros.put("codTipoDocumento", dataEnvio.get("codTipoDocumento").toString());
           	parametros.put("numDocumento", dataEnvio.get("numDocumento").toString());
           	
        	String stringResult = expedienteVirtualService.EliminarExpVirtual(parametros);
  
            
        	@SuppressWarnings("unchecked")
        	Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);                    
        	modelo.addObject("status", MapJsonObject.get("status"));
        	modelo.addObject("message",MapJsonObject.get("message"));
        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - MantemientoExpedienteVirtualController.eliminarExpedienteVirtual");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - MantemientoExpedienteVirtualController.eliminarExpedienteVirtual");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
	
	public ModelAndView revertirCierreExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.revertirCierreExpedienteVirtual");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        	
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
        	@SuppressWarnings("unchecked")                 
        	Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
                                  
        	modelo = new ModelAndView(jsonView);
			
			Map<String, Object> parametros = null;  
			parametros = new HashMap<String, Object>();
        	parametros.put("numExpedienteVirtual", dataEnvio.get("numExpediente").toString());
        	parametros.put("numExpedienteOrigen", dataEnvio.get("numExpedienteOrigen").toString());               
        	parametros.put("codUsuarioModifica", usuarioBean.getNroRegistro());
               
           	//OBTENER RESPONSABLES
           	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(dataEnvio.get("numExpediente").toString());
           	
           	//OBTENER CORREOS DE RESPONSABLES
           	if (!listCodRespAsignados.isEmpty()) {
           		List<CorreosBean> listaCorreosBeans = new ArrayList<CorreosBean>();
   				for (String codColaborador : listCodRespAsignados) {
   					CorreosBean correosBean = new CorreosBean();
   					correosBean.setCodPers(codColaborador);
   					listaCorreosBeans.add(correosBean);
   				}
           		Map<String, Object> mapConsultaCorreo =  new HashMap<String, Object>();
           		mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);
           		listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);   
           		parametros.put("listCorreos", listaCorreosBeans);
           	}
           	
           	T6613DocExpVirtBean t6613Bean = null;
           	Map<String, Object> mapT6613 = null;
    		//[PAS20181U210400241] OBVIAR SÓLO PARA LOS TIPOS DE EXPEDIENTE DE CARTA INDUCTIVA Y ESQUELA
           	String codTipExp = dataEnvio.get("codTipExp").toString();
    		if (!(ValidaConstantes.TIPO_EXPE_CARTA_INDUCTIVA.equals(codTipExp.trim()) || 
    				ValidaConstantes.TIPO_EXPE_ESQUELA.equals(codTipExp.trim()))) {
               	//OBTENER DOCUMENTO DE CIERRE
               	mapT6613 = new HashMap<String, Object>();
               	mapT6613.put("numExpeDv", dataEnvio.get("numExpediente").toString());
               	mapT6613.put("codTipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_CIERRE);
               	t6613Bean = documentoExpedienteService.obtenerDocumentoExpediente(mapT6613);
               	parametros.put("numCorDocCierre", t6613Bean.getNumCorDoc());
               	
               	//OBTENER CONSTANCIA DEL DOCUMENTO DE CIERRE
               	mapT6613 = new HashMap<String, Object>();
               	mapT6613.put("numExpeDv", dataEnvio.get("numExpediente").toString());
               	mapT6613.put("numCorDocRel", t6613Bean.getNumCorDoc());
               	t6613Bean = documentoExpedienteService.obtenerDocumentoExpediente(mapT6613);
               	if (t6613Bean != null) {
                   	parametros.put("numCorDocConst", t6613Bean.getNumCorDoc());
               	}
    		}
           	
           	//OBTENER EL INDICE DEL EXPEDIENTE
           	mapT6613 = new HashMap<String, Object>();
           	mapT6613.put("numExpeDv", dataEnvio.get("numExpediente").toString());
           	mapT6613.put("codTipDoc", ValidaConstantes.COD_TIPO_DOCU_INDICE_CIERRE);
           	t6613Bean = documentoExpedienteService.obtenerDocumentoExpediente(mapT6613);
           	parametros.put("numCorDocIndice", t6613Bean.getNumCorDoc());
           	
           	//DATOS GENERALES
           	parametros.put("codTipoDocumento", dataEnvio.get("codTipoDocumento").toString());
           	parametros.put("numDocuemento", dataEnvio.get("numDocuemento").toString());
           	
               
           	String stringResult = expedienteVirtualService.RevertirCierreExpVirtual(parametros);
           	@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);                    
               
           	modelo.addObject("status", MapJsonObject.get("status"));
           	modelo.addObject("message",MapJsonObject.get("message"));
     
        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - MantemientoExpedienteVirtualController.revertirCierreExpedienteVirtual");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - MantemientoExpedienteVirtualController.revertirCierreExpedienteVirtual");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
	public ModelAndView validarExpedientesAcumulado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.validarCampos");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        	
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
        	@SuppressWarnings("unchecked")                 
        	Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
                                  
        	modelo = new ModelAndView(jsonView);
        	//VERIFICAR QUE NO SEA UN EXPEDIENTE ACUMULADO
			if (Utils.equiv(dataEnvio.get("indicadorAcumulado").toString(), ValidaConstantes.IND_ACUMULADOR_ACUMULADO)) {
                modelo.addObject("status", false);
                modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_010);
 
                return modelo;
			}
     
        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - MantemientoExpedienteVirtualController.validarCampos");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - MantemientoExpedienteVirtualController.validarCampos");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
	
	public ModelAndView validarDocuemntos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - ReaperturaExpedienteVirtualController.validarCampos");
        
        ModelAndView modelo = null;
        HttpSession session = request.getSession(true);
        UsuarioBean usuarioBean = null;
        try {
        	//TODO - SESSION AUTHENTICACION
        	if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
        		String usub = request.getParameter("usub");
        		String tenc = request.getParameter("tenc");
        		usub = usub.replace(' ', '+');
        		usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
        	} else {                     
        		usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");                        
        	}
        	usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        	
        	//TODO - LECTURA DEL REQUEST BODY
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String jsonEnviado = "";
        	if (reader != null) {
        		jsonEnviado = reader.readLine();
        	}
        	
        	@SuppressWarnings("unchecked")                 
        	Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
                                  
        	modelo = new ModelAndView(jsonView);
        	Map<String, Object> parametros = null; 
			
            //VERIFICAR QUE NO ARRASTE DOCUMENTOS
  			parametros = new HashMap<String, Object>();
  			parametros.put("numexpediente", dataEnvio.get("numExpediente").toString());
  			parametros.put("tipodocsustento", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
              					
		    Integer numDocPorExp = expedienteVirtualService.NumDocRegisPorExpediente(parametros);
		    if(numDocPorExp!=0){
		       modelo.addObject("status", false);
		       modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_01_07_008);
		       return modelo;
		    }
     
        } catch (Exception ex) {                
            if (log.isDebugEnabled()) log.debug("Error - MantemientoExpedienteVirtualController.validarCampos");                  
            log.error(ex, ex);
        
            modelo.addObject("status", false);
            modelo.addObject("message",ex.getMessage());
     } finally {                
            if (log.isDebugEnabled()) log.debug("Final - MantemientoExpedienteVirtualController.validarCampos");
            
            NDC.pop();
            NDC.remove();              
     }
     return modelo;		
	}
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
	
	public void setConfiguracionExpedienteService (ConfiguracionExpedienteService configuracionExpedienteService){
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}
	public void setPersonaService(PersonaService personaService){
		this.personaService = personaService;
	}

	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}

	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
}


