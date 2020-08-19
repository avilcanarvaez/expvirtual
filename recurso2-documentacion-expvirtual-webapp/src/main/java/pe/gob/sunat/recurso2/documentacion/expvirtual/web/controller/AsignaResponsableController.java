package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ResponsableService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class AsignaResponsableController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsignaResponsableController.class);
	
	private JsonView jsonView;
	
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ExpedienteVirtualService expedienteVirtualService;
	private ValidarParametrosService validarParametrosService;
	private ResponsableService responsableService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]
	
	public ModelAndView cargarAsignacionResponsableExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsignaResponsableController.cargarAsignacionResponsableExpediente");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsignaResponsableController.cargarAsignacionResponsableExpediente");
			
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
				
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_03_001);
				
				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
				
				String codDependencia = usuarioBean.getCodDepend();
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_03_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_03_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_03_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_03_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_03_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_03_005);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_03_006);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_01_03_007);
				excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_01_03_008);
				excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_01_03_009);
				excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_01_03_010);
				excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_03_011);
				
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
				//Inicio - [oachahuic][PAS20165E210400270]
				//String codDependenciaBase = usuarioBean.getCodDepend();
				//Fin - [oachahuic][PAS20165E210400270]
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				String indClaseExpediente = dataEnvio.get("indClaseExpediente").toString();
				String numExpediente = dataEnvio.get("numExpediente").toString();
				
				parametros.put("indClaseExpediente", indClaseExpediente);
				parametros.put("numExpediente", numExpediente);
				
				T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
				
				DdpBean contribuyente = null;
				
				List<T6621RespExpVirtBean> responsablesDisponibles = new ArrayList<T6621RespExpVirtBean>();
				List<T6621RespExpVirtBean> responsablesAsignados = new ArrayList<T6621RespExpVirtBean>();
				
				if (expedienteVirtual != null) {
					
					String numExpedienteVirtual = expedienteVirtual.getNumExpedienteVirtual();
					String numRuc = expedienteVirtual.getNumRuc();
					
					contribuyente = validarParametrosService.validarRUC(numRuc);
					//nchavez 13/06/2016
					if (!Utils.isEmpty(contribuyente.getNumRuc())) {
						
						//String codDependencia = contribuyente.getCodDependencia();
						
						String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();//[oachahuic][PAS20165E210400270]
						
						if (codDependenciaBase.substring(0, 3).equals(expedienteVirtual.getCodDependencia().substring(0, 3))) {
							
							parametros = new HashMap<String, Object>();
							
							parametros.put("indConsulta", ValidaConstantes.IND_RESPO_ASOCIADO);
							
							parametros.put("numExpedienteVirtual", numExpedienteVirtual);
							parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
							//parametros.put("codDependencia", codDependencia);
							parametros.put("codDependencia", usuarioBean.getCodDepend());//[oachahuic][PAS20165E210400270]-SE ENVIO EL CODIGO DE TI
							parametros.put("codTipoDependencia", ValidaConstantes.IND_TIPO_DEPENDENCIA_INTRANET);//
							
							responsablesAsignados = responsableService.listarRelacionResponsables(parametros);
							
							if(Utils.isEmpty(responsablesAsignados)){
								responsablesAsignados = new ArrayList<T6621RespExpVirtBean>();
							}
							parametros.put("indConsulta", ValidaConstantes.IND_RESPO_DISPONIBLE);
							// Inicio [jjurado 21/06/2016] Se comenta para que traiga todos los reponsables de la dependencia
							//parametros.put("codUnidadOrganizacional", usuarioBean.getCodUO());
							List<T1797DepenUOrgaBean> listadoUnidOrgaDependencia =  responsableService.listarUnidadesOrganizacionales(parametros); 
							if(Utils.isEmpty(listadoUnidOrgaDependencia)){
								modelo.addObject("expedienteVirtual", expedienteVirtual);
								modelo.addObject("contribuyente", contribuyente);
								modelo.addObject("responsablesDisponibles", responsablesDisponibles);
								modelo.addObject("responsablesAsignados", responsablesAsignados);
								modelo.addObject("responsablesFinales", responsablesAsignados);
								modelo.addObject("indListaUniOrgaDependVacio", ValidaConstantes.FILTRO_INVALIDO);
								return modelo;
							}
							
							parametros.put("listadoUnidOrgaDependencia", listadoUnidOrgaDependencia);
							// Fin [jjurado 21/06/2016]
							
							responsablesDisponibles = responsableService.listarRelacionResponsables(parametros);
							if(Utils.isEmpty(responsablesDisponibles)){
								responsablesDisponibles = new ArrayList<T6621RespExpVirtBean>();
							}
							
						}
						
					}
					
				}
				
				modelo.addObject("expedienteVirtual", expedienteVirtual);
				modelo.addObject("contribuyente", contribuyente);
				modelo.addObject("indListaUniOrgaDependVacio", ValidaConstantes.FILTRO_UNO);
				modelo.addObject("responsablesDisponibles", responsablesDisponibles);
				modelo.addObject("responsablesAsignados", responsablesAsignados);
				modelo.addObject("responsablesFinales", responsablesAsignados);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsignaResponsableController.cargarAsignacionResponsableExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsignaResponsableController.cargarAsignacionResponsableExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView realizarAsignacionResponsablesExpediente (HttpServletRequest request, HttpServletResponse response) {
		
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
			
			List<T6621RespExpVirtBean> listadoActual = responsableService.listarResponsables(parametros);
			
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);
			
			List<T6621RespExpVirtBean> listadoInactivo = responsableService.listarResponsables(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("codUsuario", codUsuario);
			parametros.put("numExpedienteVirtual", numExpedienteVirtual);
			parametros.put("listadoNuevo", listadoNuevo);
			parametros.put("listadoActual", listadoActual);
			parametros.put("listadoInactivo", listadoInactivo);
			
			responsableService.realizarAsignacionResponsableExpediente(parametros);
			
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

	public void setResponsableService(ResponsableService responsableService) {
		this.responsableService = responsableService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}
	
	/* Seteo - Spring - Final */
	
}