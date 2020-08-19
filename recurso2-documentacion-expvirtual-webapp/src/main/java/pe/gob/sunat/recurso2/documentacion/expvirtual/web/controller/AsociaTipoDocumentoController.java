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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class AsociaTipoDocumentoController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(AsociaTipoDocumentoController.class);
	
	private JsonView jsonView;
	
	private ConfiguracionExpedienteService configuracionExpedienteService;
	
	
	public ModelAndView validarQuitarTipodocumento(HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoExpedienteController.validarQuitarTipoExpediente");
		ModelAndView modelo = null;
		modelo = new ModelAndView(jsonView);
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoExpedienteController.validarQuitarTipoExpediente");
			Map<String, Object> parametros = new HashMap<String, Object>();
			String codTipdoc = request.getParameter("codtipdoc").toString().trim();
			String codTipexp = request.getParameter("codtipexp").toString().trim();
			parametros = new HashMap<String, Object>();
			
			parametros.put("codtipdoc", codTipdoc);
			parametros.put("codtipexp", codTipexp);
			
			List<T6613DocExpVirtBean> listadoTiposExpAsociados = configuracionExpedienteService.listarDocumentosExpediente(parametros);
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
	
	public ModelAndView cargarAsociacionTipoDocumentoTipoExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoDocumentoController.cargarAsociacionTipoExpedienteProceso");
		
		ModelAndView modelo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoDocumentoController.cargarAsociacionTipoDocumentoTipoExpediente");
			
			String indCarga = request.getParameter("indCarga");
			
			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {
				
				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_02_001);
				
				Map<String, Object> titulos = new HashMap<String, Object>();
				
				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_02_001);
				
				Map<String, Object> excepciones = new HashMap<String, Object>();
				
				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_02_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_02_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_02_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_02_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_02_005);
				
				Map<String, Object> avisos = new HashMap<String, Object>();
				
				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_02_001);
				avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_02_002);
				avisos.put("aviso03", AvisoConstantes.AVISO_MODULO_01_02_003);
				
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
				
				String indFiltro = request.getParameter("indFiltro");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";
				
				if(reader != null) {
					
					jsonEnviado = reader.readLine();
					
				}
				
				@SuppressWarnings("unchecked")
				Map<String, Object> dataEnvio = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);
				
				modelo = new ModelAndView(jsonView);
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				
				if (ValidaConstantes.FILTRO_CERO.equals(indFiltro)) {
					
					String codProceso = dataEnvio.get("codProceso").toString();
					
					parametros.put("codProceso", codProceso);
					parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					
					List<T6624TipExpProcBean> listadoTiposExpendientes = configuracionExpedienteService.listarTiposExpendiente(parametros);
					
					modelo.addObject("listadoTiposExpendientes", listadoTiposExpendientes);
					
				} else if (ValidaConstantes.FILTRO_UNO.equals(indFiltro)) {
					
					String codTipoExpediente = dataEnvio.get("codTipoExpediente").toString();
					
					parametros.put("codTipoExpediente", codTipoExpediente);
					parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					List<String> listIndTipDoc = new ArrayList<String>();
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
					listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
					parametros.put("listIndTipDoc", listIndTipDoc);
					parametros.put("indConsulta", ValidaConstantes.IND_TIPO_DOCU_DISPONIBLE);
					
					List<T6623TipDocExpBean> listadoTiposDocumentosDisponibles = configuracionExpedienteService.listarParametrosTiposDocumento(parametros);
					
					parametros.put("indConsulta", ValidaConstantes.IND_TIPO_DOCU_ASOCIADO);
					
					List<T6623TipDocExpBean> listadoTiposDocumentosAsociados = configuracionExpedienteService.listarParametrosTiposDocumento(parametros);
					
					modelo.addObject("listadoTiposDocumentosDisponibles", listadoTiposDocumentosDisponibles);
					modelo.addObject("listadoTiposDocumentosAsociados", listadoTiposDocumentosAsociados);
					modelo.addObject("listadoTiposDocumentoFinales", listadoTiposDocumentosAsociados);
					
				}
				
			} else if (ValidaConstantes.CARGA_INVALIDA.equals(indCarga)) {
				
				modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
				
			}
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoDocumentoController.cargarAsociacionTipoDocumentoTipoExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoDocumentoController.cargarAsociacionTipoDocumentoTipoExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView realizarAsociacionTipoDocumentoTipoExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
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
			
			String codTipoExpediente = dataEnvio.get("codTipoExpediente").toString();
			String codProceso = dataEnvio.get("codProceso").toString();
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (ArrayList<Map<String, Object>>) dataEnvio.get("listadoFinal");			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			Map<String, Object> paramValAsociacion = new HashMap<String, Object>();
			boolean existerelacion = false;
			
			parametros.put("codTipoExpediente", codTipoExpediente);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			parametros.put("listIndTipDoc", listIndTipDoc);
			parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			List<T6623TipDocExpBean> listadoActual = configuracionExpedienteService.listarTiposDocumentos(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("codUsuario", codUsuario);
			parametros.put("codTipoExpediente", codTipoExpediente);
			parametros.put("listadoNuevo", listadoNuevo);
			parametros.put("listadoActual", listadoActual);			
			/*ini: 14-12-15 miguel ochoa - validar la asociacion de proceso y tipo de expediente antes de registrar el cambio */
			paramValAsociacion.put("codProceso",codProceso);
			paramValAsociacion.put("codTipoExpediente",codTipoExpediente);
			paramValAsociacion.put("indEliminado","0");
			List<T6624TipExpProcBean> listadoValidacion =  configuracionExpedienteService.listarTiposExpendiente(paramValAsociacion);
			
			for (T6624TipExpProcBean tipoExpedienteBean : listadoValidacion){
				if(tipoExpedienteBean.getCodTipoExpediente().toString().equals(codTipoExpediente)) {
					existerelacion=true; break;
				}
			}			
			if(existerelacion){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("codTipoExpediente", codTipoExpediente);
				List<Map<String, Object>> listadoTipoDoc23 =  configuracionExpedienteService.listarTipoDocsNoAsocExp(params);			 				
				
				configuracionExpedienteService.realizarAsociacionTipoDocumentoTipoExpediente(parametros, listadoTipoDoc23);
				modelo.addObject("listadoTiposExpedientesAsociados", listadoNuevo);				
				modelo.addObject("listadoTiposExpedientesFinales", listadoNuevo);
			}else{
				modelo.addObject("listadoTiposExpedientesAsociados",null);
				modelo.addObject("listadoTiposExpedientesFinales", null);
			}
			/*fin: 14-12-15  */

			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
	public ModelAndView cantidadTipoDocumentoRelacion(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoDocumentoController.cantidadTipoDocumentoRelacion");
		ModelAndView modelo = null;
		
		try{			
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
			parametros.put("codTipoExpediente", dataEnvio.get("codTipoExpediente").toString());
			parametros.put("codTipoDocumento", dataEnvio.get("codTipoDocumento").toString());
			
			String stringResult = configuracionExpedienteService.cantidadTipoDocumentosRelacion(parametros);
			@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);
			
			modelo.addObject("status", MapJsonObject.get("status"));
			modelo.addObject("message",MapJsonObject.get("message"));			
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoDocumentoController.cantidadTipoDocumentoRelacion");		
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
						
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoDocumentoController.cantidadTipoDocumentoRelacion");		
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
	//*************************************
	//Modificado JEFFIO
public ModelAndView cambiarFinalidadTipoExpediente (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
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
			
			String codTipoExpediente = dataEnvio.get("codTipoExpediente").toString();
			String codProceso = dataEnvio.get("codProceso").toString();
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (ArrayList<Map<String, Object>>) dataEnvio.get("listadoFinal");			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			Map<String, Object> paramValAsociacion = new HashMap<String, Object>();
			boolean existerelacion = false;
			
			parametros.put("codTipoExpediente", codTipoExpediente);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			parametros.put("listIndTipDoc", listIndTipDoc);
			parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			List<T6623TipDocExpBean> listadoActual = configuracionExpedienteService.listarTiposDocumentos(parametros);
			
			parametros = new HashMap<String, Object>();
			
			parametros.put("codUsuario", codUsuario);
			parametros.put("codTipoExpediente", codTipoExpediente);
			parametros.put("listadoNuevo", listadoNuevo);
			parametros.put("listadoActual", listadoActual);			
			/*ini: 14-12-15 miguel ochoa - validar la asociacion de proceso y tipo de expediente antes de registrar el cambio */
			paramValAsociacion.put("codProceso",codProceso);
			paramValAsociacion.put("codTipoExpediente",codTipoExpediente);
			paramValAsociacion.put("indEliminado","0");
			List<T6624TipExpProcBean> listadoValidacion =  configuracionExpedienteService.listarTiposExpendiente(paramValAsociacion);
			
			for (T6624TipExpProcBean tipoExpedienteBean : listadoValidacion){
				if(tipoExpedienteBean.getCodTipoExpediente().toString().equals(codTipoExpediente)) {
					existerelacion=true; break;
				}
			}			
			if(existerelacion){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("codTipoExpediente", codTipoExpediente);
				List<Map<String, Object>> listadoTipoDoc23 =  configuracionExpedienteService.listarTipoDocsNoAsocExp(params);			 				
				
				configuracionExpedienteService.realizarAsociacionTipoDocumentoTipoExpediente(parametros, listadoTipoDoc23);
				modelo.addObject("listadoTiposExpedientesAsociados", listadoNuevo);				
				modelo.addObject("listadoTiposExpedientesFinales", listadoNuevo);
			}else{
				modelo.addObject("listadoTiposExpedientesAsociados",null);
				modelo.addObject("listadoTiposExpedientesFinales", null);
			}
			/*fin: 14-12-15  */

			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - AsociaTipoDocumentoController.realizarAsociacionTipoDocumentoTipoExpediente");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	

}