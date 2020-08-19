package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.stereotype.Controller;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;

import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;

import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CorreosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.PersonaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RequerimientoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;

import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;

import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

@Controller
public class EliminaRequerimientoController extends MultiActionController{
	private static final Log log = LogFactory.getLog(EliminaDocInternosController.class);	
	private JsonView jsonView;
	
	private ExpedienteVirtualService expedienteVirtualService;	
	private RequerimientoService requerimientoService;	
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ValidarParametrosService validarParametrosService;
	private ResponsableService responsableService;
	private PersonaService personaService;
	private AduanaService aduanaService; 
	private CorreosService correosService;

	public ModelAndView mostrarView(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaRequerimientoController.mostrarView");
				
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		try{
			//TODO - SESSION DE USUARIO
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);				
			}else{				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");				
			}

			String codDependencia = usuarioBean.getCodDepend();
			
			//TODO - TITULO DEL MODULO			
			Map<String, Object> titulos = new HashMap<String, Object>();
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_02_05_001);
			
			//TODO - EXCEPCIONES
			Map<String, Object> excepciones = new HashMap<String, Object>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_02_05_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_02_05_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_02_05_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_02_05_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_02_05_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_02_05_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_02_05_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_02_05_008);
						
			//TODO - AVISOS
			Map<String, Object> avisos = new HashMap<String, Object>();
			avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_02_05_001); //AVISO_MODULO_01_00_000
			avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_02_05_002);
			avisos.put("aviso03", AvisoConstantes.AVISO_MODULO_02_05_003);
			
			//TODO - INTERFACE
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_02_05_001);
			
			//TODO - FECHA ACTUAL
			Date fecSistema = new Date();
			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
			String fecVista = formatoFechaExporta.format(fecSistema);
			
			//TODO - TIPOS DE EXPEDIENTES
			List<T01ParamBean> listadoTiposNumeroExpediente = parametroService.listarNumeroTipoExpediente();
			
			//TODO - CARGAR AL MODEL
			modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
			modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
			
			//TODO - CARGAR LA DEPENDENCIA			
			Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
			if (mapDepAdu != null && mapDepAdu.get("ADUANA") != null) {
				modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
			} else {
				modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(codDependencia));
			}
			//TODO - CARGAR TIPO DE EXPEDIENTES
			modelo.addObject("listadoTiposNumeroExpediente", new JsonSerializer().serialize(listadoTiposNumeroExpediente));
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaRequerimientoController.mostrarView");			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaRequerimientoController.mostrarView");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;		
	}
	
	public ModelAndView consultarExpedienteJSON(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaRequerimientoController.consultarExpedienteJSON");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		T6613DocExpVirtBean t6613DocExpVirtBean=null;

		try{
			//SESSION AUTHENTICACION
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);				
			}else{				
				usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");				
			}
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
			
			//LECTURA DEL REQUEST BODY
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonEnviado = "";
			if (reader != null) {
				jsonEnviado = reader.readLine();
			}

			@SuppressWarnings("unchecked")			
			Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
			String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();
			String indClaseExpediente = dataEnvio.get("indClaseExpediente").toString();
			
			modelo = new ModelAndView(jsonView);
			Map<String, Object> parametros = null;
			
			//TODO - IU015 VERIFICAR SI EXISTE EL NUMERO EXPEDIENTE INGRESADO EN BD
			parametros = new HashMap<String, Object>();			
			if (ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN.equals(indClaseExpediente)) {				
				parametros.put("numExpedienteOrigen", dataEnvio.get("numExpediente").toString());				
			} else if (ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL.equals(indClaseExpediente)) {				
				parametros.put("numExpedienteVirtual", dataEnvio.get("numExpediente").toString());				
			}
			parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN); //DOCUMENTO SUSTENTO ORIGEN
			
			T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerDatosExpediente(parametros);
			
			if (expedienteVirtual == null) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_02_05_003);
				return modelo;
			}

			//TODO - IU015 VERIFICAR SI EL EXPEDIENTE PERTENECE A LA DEPENDENCIA DEL USUARIO EN SESION			
			 if (!Utils.equiv(codDependenciaBase.substring(0, 3), expedienteVirtual.getCodDependencia().substring(0, 3))) {			 
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_02_05_004);
				return modelo;
			}
			
			
			//TODO - IU015 VERIFICAR SI ES RESPONSABLE DEL EXPEDIENTE
			parametros = new HashMap<String, Object>();
			parametros.put("num_expedv_par", expedienteVirtual.getNumExpedienteVirtual());
			parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
			parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			T6621RespExpVirtBean t6621Bean = responsableService.obtenerResponsable(parametros);			
			if (t6621Bean == null) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_02_05_005);
				return modelo;
			}else{
				//OBTENER RESPONSABLE NOMBRE COMPLETO
				parametros = new HashMap<String, Object>();
				parametros.put("codPersona", usuarioBean.getNroRegistro());
				parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);				
				T02PerdpBean responsable = personaService.validarPersona(parametros);				
				if(responsable != null) {
					responsable = personaService.completarInformacionPersona(responsable);					
					expedienteVirtual.setNombreResponsable(responsable.getDesNombreCompleto());
				}
			}
			
						
			//TODO - IU015 VERIFICAR SI SE ENCUENTRA ACTIVO
			if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO.equals(expedienteVirtual.getCodEstado())) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_02_05_006);
				return modelo;
			}
			
			//TODO - IU015 VERIFICA SI TIENE REQUERIMIENTOS REGISTRADOS			
			HashMap<String, Object> mapParametrosBusqueda;
			mapParametrosBusqueda = new HashMap<String, Object>();
			mapParametrosBusqueda.put("numExpedVirtual", expedienteVirtual.getNumExpedienteVirtual());
			mapParametrosBusqueda.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			mapParametrosBusqueda.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);//[oachahuic][PAS20175E210400016]
			List<T6620RequerimBean> aryRequerimientosExpediente = requerimientoService.obtenerListaRequerimientos(mapParametrosBusqueda);

			if (Utils.isEmpty(aryRequerimientosExpediente)){
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_02_05_007);
				return modelo;
			}

			DdpBean contribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
			
			//TODO - IU015 DESCRIPCION EXPEDIENTE - PROCESO
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
			expedienteVirtual.setDesProceso(mapaProcesos.get(expedienteVirtual.getCodProceso()).toString());

			//TODO - IU015 DESCRIPCION EXPEDIENTE - TIPO DE EXPEDIENTE
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);			
			expedienteVirtual.setDesTipoExpediente(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente()).toString());
			
			//TODO - IU015 DOCUMENTO ORIGEN NOTIFICACION
			parametros = new HashMap<String, Object>();
			parametros.put("numexpediente", expedienteVirtual.getNumExpedienteVirtual());
			parametros.put("tipodocsustento", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			
			t6613DocExpVirtBean = expedienteVirtualService.buscarDatosDocumentos(parametros);			
			if("-".equalsIgnoreCase(t6613DocExpVirtBean.getCodForNotif().trim())){
				t6613DocExpVirtBean.setDesForNotif("-");
			}else{
				mapa = new HashMap<String, Object>();
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapa.put("codParametro", t6613DocExpVirtBean.getCodForNotif().trim());
				//mapa.put("codTipoExpediente", "803");
				Map<String, Object> mapaTiposNotificaciones = catalogoService.obtenerCatalogo(mapa);				
				t6613DocExpVirtBean.setDesForNotif(mapaTiposNotificaciones.get(t6613DocExpVirtBean.getCodForNotif().trim()).toString());
			}
			
			//CARGAR DATOS AL MODELO
			modelo.addObject("status", true);
			modelo.addObject("message","");						
			modelo.addObject("expedienteVirtual", expedienteVirtual);
			modelo.addObject("expedienteVirtualDocOri",t6613DocExpVirtBean);
			modelo.addObject("contribuyente", contribuyente);			
			modelo.addObject("expedienteVirtualReqs",new JsonSerializer().serialize(aryRequerimientosExpediente));
			
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaRequerimientoController.consultarExpedienteJSON");			
			log.error(ex, ex);			
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
			
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaRequerimientoController.consultarExpedienteJSON");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;	
	}
	
	public ModelAndView eliminarRequerimientoJSON(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaRequerimientoController.eliminarRequerimientoJSON");
		
		ModelAndView modelo = null;
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		try{
			//TODO - SESSION AUTHENTICACION
			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
				
				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
				
			}else{				
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
			parametros.put("numExpedienteVirtual", dataEnvio.get("numExpedienteVirtual").toString());
			parametros.put("numRequerimiento", dataEnvio.get("numRequerimiento").toString());
			parametros.put("numRequerimientoOrigen", dataEnvio.get("numRequerimientoOrigen").toString());
			parametros.put("desEliReq", dataEnvio.get("desEliReq").toString());
			parametros.put("codUsuModif", usuarioBean.getNroRegistro());
			
           	//OBTENER RESPONSABLES
           	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(dataEnvio.get("numExpedienteVirtual").toString());
           	
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
			
			String stringResult = requerimientoService.eliminarRequerimiento(parametros);
			
			@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);
			modelo.addObject("status", MapJsonObject.get("status"));
			modelo.addObject("message",MapJsonObject.get("message"));
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaRequerimientoController.eliminarRequerimientoJSON");			
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaRequerimientoController.eliminarRequerimientoJSON");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;		
	}
	
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	
	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}
	
	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	
	public void setRequerimientoService(RequerimientoService requerimientoService) {
		this.requerimientoService = requerimientoService;
	}
	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}
	
	
}