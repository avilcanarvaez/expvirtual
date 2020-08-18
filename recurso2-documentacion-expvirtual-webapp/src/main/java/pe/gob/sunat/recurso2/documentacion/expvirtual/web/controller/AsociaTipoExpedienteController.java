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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class AsociaTipoExpedienteController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsociaTipoExpedienteController.class);
	
	private JsonView jsonView;
	
	private ConfiguracionExpedienteService configuracionExpedienteService;
	
	public ModelAndView validarQuitarTipoExpediente(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoExpedienteController.validarQuitarTipoExpediente");
		ModelAndView modelo = null;
		modelo = new ModelAndView(jsonView);
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoExpedienteController.validarQuitarTipoExpediente");
			Map<String, Object> parametros = new HashMap<String, Object>();
			String codTipExp = request.getParameter("codtipexp").toString().trim();
			parametros = new HashMap<String, Object>();
			
			parametros.put("codTipoExpediente", codTipExp);
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			parametros.put("indConsulta", ValidaConstantes.IND_TIPO_DOCU_ASOCIADO);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			parametros.put("listIndTipDoc", listIndTipDoc);
			
			List<T6623TipDocExpBean> listadoTiposExpAsociados = configuracionExpedienteService.listarParametrosTiposDocumento(parametros);
			if(listadoTiposExpAsociados.size()>0){
				modelo.addObject("respuesta", false);
			}else{
				modelo.addObject("respuesta", true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoExpedienteController.validarQuitarTipoExpediente");
		}
		
		return modelo;
	}
	
	public ModelAndView cargarAsociacionTipoExpedienteProceso (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoExpedienteController.cargarAsociacionTipoExpedienteProceso");
		
		ModelAndView modelo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoExpedienteController.cargarAsociacionTipoExpedienteProceso");
			
			String indCarga = request.getParameter("indCarga");
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_01_001);
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_01_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_01_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_01_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_01_003);
				/*ini:miguel ochoa - add mensajes para excepciones*/
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_01_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_01_005);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_01_006);
				/*fin:miguel ochoa - add mensajes para excepciones*/
				Map<String, Object> avisos = new HashMap<String, Object>();
				
				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_01_001);
				avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_01_002);
				
				Date fecSistema = new Date();
				
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				
				String fecVista = formatoFechaExporta.format(fecSistema);
				
				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();
				
				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				
			} else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				String codProceso = dataEnvio.get("codProceso").toString();
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				parametros.put("indConsulta", ValidaConstantes.IND_TIPO_EXPE_DISPONIBLE);
				
				List<T6624TipExpProcBean> listadoTiposExpedientesDisponibles = configuracionExpedienteService.listarParametrosTiposExpendiente(parametros);
				
				parametros.put("codProceso", codProceso);
				parametros.put("indConsulta", ValidaConstantes.IND_TIPO_EXPE_ASOCIADO);
				
				List<T6624TipExpProcBean> listadoTiposExpedientesAsociados = configuracionExpedienteService.listarParametrosTiposExpendiente(parametros);
				
				modelo.addObject("listadoTiposExpedientesDisponibles", listadoTiposExpedientesDisponibles);
				modelo.addObject("listadoTiposExpedientesAsociados", listadoTiposExpedientesAsociados);
				modelo.addObject("listadoTiposExpedientesFinales", listadoTiposExpedientesAsociados);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoExpedienteController.cargarAsociacionTipoExpedienteProceso");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoExpedienteController.cargarAsociacionTipoExpedienteProceso");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView realizarAsociacionTipoExpedienteProceso (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoExpedienteController.realizarAsociacionTipoExpedienteProceso");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoExpedienteController.realizarAsociacionTipoExpedienteProceso");
			
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
			
			String codProceso = dataEnvio.get("codProceso").toString();
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (ArrayList<Map<String, Object>>) dataEnvio.get("listadoFinal");
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("codProceso", codProceso);
			
			List<T6624TipExpProcBean> listadoActual = configuracionExpedienteService.listarTiposExpendiente(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);
			
			List<T6624TipExpProcBean> listadoInactivo = configuracionExpedienteService.listarTiposExpendiente(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("codUsuario", codUsuario);
			parametros.put("codProceso", codProceso);
			parametros.put("listadoNuevo", listadoNuevo);
			parametros.put("listadoActual", listadoActual);
			parametros.put("listadoInactivo", listadoInactivo);
			
			configuracionExpedienteService.realizarAsociacionTipoExpedienteProceso(parametros);
			
			modelo.addObject("listadoTiposExpedientesAsociados", listadoNuevo);
			
			modelo.addObject("listadoTiposExpedientesFinales", listadoNuevo);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoExpedienteController.realizarAsociacionTipoExpedienteProceso");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoExpedienteController.realizarAsociacionTipoExpedienteProceso");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	/* Seteo - Spring - Inicio */
	
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}
	
	/* Seteo - Spring - Final */
	
}