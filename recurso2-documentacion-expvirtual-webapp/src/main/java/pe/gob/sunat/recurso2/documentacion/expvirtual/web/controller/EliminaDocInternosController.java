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
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CorreosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
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
@Controller
public class EliminaDocInternosController extends MultiActionController{
	private static final Log log = LogFactory.getLog(EliminaDocInternosController.class);	
	private JsonView jsonView;
	private ConfiguracionExpedienteService configuracionExpedienteService;	
	private ExpedienteVirtualService expedienteVirtualService;	
	private DocumentoExpedienteService documentoExpedienteService;	
	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private ValidarParametrosService validarParametrosService;
	private ResponsableService responsableService;
	private AduanaService aduanaService;
	private CorreosService correosService;
	
	//TODO - IU005 METODO CARGA INICIAL 
	public ModelAndView cargarEliminarDocInternos (HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - EliminaDocInternosController.cargarEliminarDocInternos");
				
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
			titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_03_02_001);
			titulos.put("tituloModificar", AvisoConstantes.TITULO_MODULO_03_02_002);
			
			//TODO - EXCEPCIONES
			Map<String, Object> excepciones = new HashMap<String, Object>();
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_03_02_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_03_02_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_03_02_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_03_02_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_03_02_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_03_02_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_03_02_007);
			excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_03_02_008);
			excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_03_02_009);
						
			//TODO - AVISOS
			Map<String, Object> avisos = new HashMap<String, Object>();
			avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_03_02_001);
			avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_03_02_002);
			avisos.put("aviso03", AvisoConstantes.AVISO_MODULO_03_02_004);
			
			//TODO - INTERFACE
			modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_03_02_001);
			
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
			if (log.isDebugEnabled()) log.debug("Error - EliminaDocInternosController.cargarEliminarDocInternos");			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaDocInternosController.cargarEliminarDocInternos");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;
	}
	
	//TODO - IU005 METODO ELIMINAR DOCUMENTO INTERNO
	public ModelAndView eliminarDocumentoInterno (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaDocInternosController.eliminarDocumentoInterno");
		
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
			
			parametros.put("numExpeDv", dataEnvio.get("numExpeDv").toString().trim());
			parametros.put("numExpeDo", dataEnvio.get("numExpeDo").toString().trim());
			parametros.put("numCorDoc", dataEnvio.get("numCorDoc").toString().trim());
			parametros.put("numDoc", dataEnvio.get("numDoc").toString().trim());
			parametros.put("codTipDoc", dataEnvio.get("codTipDoc").toString().trim());
			parametros.put("desTipDoc", dataEnvio.get("desTipDoc").toString().trim());
			parametros.put("desEliDoc", dataEnvio.get("desEliDoc").toString().trim());
			parametros.put("codUsuModif", usuarioBean.getNroRegistro());
			
			//VERIFICAR SI EL TIPO DE DOCUMENTO REALIZAR UN CAMBIO DE ESTADO Y/O ETAPA
			parametros.put("flgEstEta", false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("codTipoDoc", parametros.get("codTipDoc").toString());
			Map<String, Object> mapResult = configuracionExpedienteService.obtenerEstadoEtapa(map);
			
			String codEstexpori = (String)mapResult.get("codEstexpori");
			if(codEstexpori == null || codEstexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEstexpori = ValidaConstantes.CADENA_VACIA;
			}
			String codEtaexpori = (String)mapResult.get("codEtaexpori");
			if(codEtaexpori == null || codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				codEtaexpori = ValidaConstantes.CADENA_VACIA;
			}
			
			if(!codEstexpori.equals(ValidaConstantes.CADENA_VACIA) || !codEtaexpori.equals(ValidaConstantes.CADENA_VACIA)) {
				parametros.put("flgEstEta", true);
			}
			
           	//OBTENER RESPONSABLES
           	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(dataEnvio.get("numExpeDv").toString());
           	
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
			
			String stringResult = documentoExpedienteService.EliminarDocumentoExpediente(parametros);
			@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);			
			
			modelo.addObject("status", MapJsonObject.get("status"));
			modelo.addObject("message",MapJsonObject.get("message"));
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaDocInternosController.eliminarDocumentoInterno");			
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaDocInternosController.eliminarDocumentoInterno");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;
	}
	
	
	//TODO - IU005 METODO CONSULTAR CANTIDAD DOCUMENTOS RELACIONADOS
	public ModelAndView cantidadDocumentoInternoRelacion(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaDocInternosController.consultarDocumentoInternoRelacion");
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
			parametros.put("numCorDoc", dataEnvio.get("numCorDoc").toString());
			parametros.put("numExpVir", dataEnvio.get("numExpVir").toString());
			
			String stringResult = documentoExpedienteService.CantidadDocumentosInternoRelacion(parametros);
			@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);
			
			modelo.addObject("status", MapJsonObject.get("status"));
			modelo.addObject("message",MapJsonObject.get("message"));			
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaDocInternosController.consultarDocumentoInternoRelacion");		
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
						
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaDocInternosController.consultarDocumentoInternoRelacion");		
			NDC.pop();
			NDC.remove();			
		}	
		return modelo;
	}
	//TODO - IU005 METODO CONSULTAR EXPEDIENTE VIRTUAL
	public ModelAndView consultarExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) {		
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaDocInternosController.consultarExpedienteVirtual");
		
		ModelAndView modelo = null;
		
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

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
			
			//TODO - IU005 VERIFICAR SI EXISTE EL NUMERO EXPEDIENTE INGRESADO EN BD
			parametros = new HashMap<String, Object>();
			/*
			parametros.put("indClaseExpediente", dataEnvio.get("indClaseExpediente").toString());
			parametros.put("numExpediente", dataEnvio.get("numExpediente").toString());
			T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerExpedienteVirtual(parametros);
			*/			
			if (ValidaConstantes.IND_CLASE_EXPEDIENTE_ORIGEN.equals(indClaseExpediente)) {				
				parametros.put("numExpedienteOrigen", dataEnvio.get("numExpediente").toString());				
			} else if (ValidaConstantes.IND_CLASE_EXPEDIENTE_VIRTUAL.equals(indClaseExpediente)) {				
				parametros.put("numExpedienteVirtual", dataEnvio.get("numExpediente").toString());				
			}
			parametros.put("tipDocSust", ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN);
			T6614ExpVirtualBean expedienteVirtual = expedienteVirtualService.obtenerDatosExpediente(parametros);
			
			if (expedienteVirtual == null) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_003);
				return modelo;
			}

			//TODO - IU005 VERIFICAR SI EL EXPEDIENTE PERTENECE A LA DEPENDENCIA DEL USUARIO EN SESIÓN
			
			if (!Utils.equiv(codDependenciaBase.substring(0, 3), expedienteVirtual.getCodDependencia().substring(0, 3))) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_004);
				return modelo;
			}
			
			
			//TODO - IU005 VERIFICAR SI ES RESPONSABLE DEL EXPEDIENTE
			parametros = new HashMap<String, Object>();
			parametros.put("num_expedv_par", expedienteVirtual.getNumExpedienteVirtual());
			parametros.put("cod_colab_par", usuarioBean.getNroRegistro());
			parametros.put("ind_del_par", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			T6621RespExpVirtBean t6621Bean = responsableService.obtenerResponsable(parametros);			
			if (t6621Bean == null) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_005);
				return modelo;
			}
						
			//TODO - IU005 VERIFICAR SI SE ENCUENTRA ACTIVO
			if (ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_CERRADO.equals(expedienteVirtual.getCodEstado())) {
				modelo.addObject("status", false);
				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_006);
				return modelo;
			}
			//TODO - IU005 VERIFICA SI ES MANUAL --SE ESTÁ QUITANDO ESTA RESTRICCIÓN PORQUE NO PERMITE ELIMINAR DOCUMENTOS DE ORIGEN MANUAL
//			if(ValidaConstantes.IND_ORIGEN_EXP_VIRT_AUTOMATICO.equals(expedienteVirtual.getCodOrigen())){
//				modelo.addObject("status", false);
//				modelo.addObject("message",AvisoConstantes.EXCEP_MODULO_03_02_007);
//				return modelo;
//			}

			DdpBean contribuyente = validarParametrosService.validarRUC(expedienteVirtual.getNumRuc());
			
			//TODO - IU005 DESCRIPCION EXPEDIENTE - PROCESO
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
			Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
			expedienteVirtual.setDesProceso(mapaProcesos.get(expedienteVirtual.getCodProceso()).toString());

			//TODO - IU005 DESCRIPCION EXPEDIENTE - TIPO DE EXPEDIENTE
			mapa = new HashMap<String, Object>();
			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);			
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);
			expedienteVirtual.setDesTipoExpediente(mapaTiposExpedientes.get(expedienteVirtual.getCodTipoExpediente()).toString());
			
			
			
			
			//VERIFICAR DOCUMENTOS DEL EXPEDIENTE
			Map<String, Object> params = null;
			params = new HashMap<String,Object>();
			params.put("numExpeDv", expedienteVirtual.getNumExpedienteVirtual());
			
			//OBTENER LA LISTA DE DOCUMENTOS DEL EXPEDIENTE
			List<Map<String, Object>> lstDocumentosExpediente = null;
			lstDocumentosExpediente = new ArrayList<Map<String, Object>>();			
			lstDocumentosExpediente = expedienteVirtualService.obtenerDocumentosExpediente(params);
			
			//OBTENER LISTADO DE DOCUMENTOS DE CLASE 1
			HashMap<String, Object> mapParametrosBusqueda = new HashMap<String, Object>();			
			mapParametrosBusqueda.put("codTipoExpediente", expedienteVirtual.getCodTipoExpediente());
			mapParametrosBusqueda.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			List<String> listIndTipDoc = new ArrayList<String>();
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
			listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
			mapParametrosBusqueda.put("listIndTipDoc", listIndTipDoc);
			mapParametrosBusqueda.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);			
			List<T6623TipDocExpBean> listaTemp = configuracionExpedienteService.listarTiposDocumentos(mapParametrosBusqueda);
			
			List<Map<String, Object>> aryDocumentosExpediente =  new ArrayList<Map<String, Object>>();
			boolean remover = true;
			int contador = 1;
			int vueltas = 1;
			for (Map<String, Object> map : lstDocumentosExpediente) {
				
	            String codTipoDocumento = Utils.toStr(map.get("codTipDoc"));
	            //EVALUAR SI SON DOCUMENTOS DE TIPO CLASE 1
	            for (T6623TipDocExpBean t6623TipDocExpBean : listaTemp) {	        
	                if(Utils.equiv(codTipoDocumento, t6623TipDocExpBean.getCodTipoDocumento())){
	                	remover = false;
	                }
                }
	            
	            //ADICIONAR Y ADICIONAR NUMERO ORDEN
	            if(!remover){	            	
	            	map.put("numOrden", contador);
	            	if(vueltas == lstDocumentosExpediente.size()){
	            		map.put("numMax", 1);
	            	}else{
	            		map.put("numMax", 0);
	            	}
	            	aryDocumentosExpediente.add(map);
	            	contador++;
	            }
	            remover = true;
	            vueltas++;
            }
			
			//CARGAR DATOS AL MODELO
			modelo.addObject("status", true);
			modelo.addObject("message","");						
			modelo.addObject("expedienteVirtual", expedienteVirtual);
			modelo.addObject("contribuyente", contribuyente);
			modelo.addObject("expedienteVirtualDocs", new JsonSerializer().serialize(aryDocumentosExpediente));
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaDocInternosController.consultarExpedienteVirtual");
			
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());			
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaDocInternosController.consultarExpedienteVirtual");			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;		
	}
	
	
	//[PAS20191U210500144] Inicio 
	public ModelAndView modificarDocumentoInterno (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - EliminaDocInternosController.modificarDocumentoInterno");
		
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
			
			parametros.put("numExpeDv", dataEnvio.get("numExpeDv").toString().trim());
			parametros.put("numExpeDo", dataEnvio.get("numExpeDo").toString().trim());
			parametros.put("numCorDoc", dataEnvio.get("numCorDoc").toString().trim());
			parametros.put("numDoc", dataEnvio.get("numDoc").toString().trim());
			parametros.put("desTipDoc", dataEnvio.get("desTipDoc").toString().trim());
			parametros.put("indVisDocumento", dataEnvio.get("indVisDocumento").toString().trim());
			parametros.put("indReserTrib", dataEnvio.get("indReserTrib").toString().trim());
			parametros.put("numCorDocRel", dataEnvio.get("numCorDocRel").toString().trim());
			parametros.put("codUsuModif", usuarioBean.getNroRegistro());
			
           	//OBTENER RESPONSABLES
           	List<String> listCodRespAsignados = responsableService.obtenerCodResponsablesAsignados(dataEnvio.get("numExpeDv").toString());
           	
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
			
			String stringResult = documentoExpedienteService.modificarDocumentoExpediente(parametros);
			@SuppressWarnings("unchecked")
			Map<String, Object> MapJsonObject = (Map<String, Object>) new JsonSerializer().deserialize(stringResult, Map.class);			
			
			modelo.addObject("status", MapJsonObject.get("status"));
			modelo.addObject("message",MapJsonObject.get("message"));
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - EliminaDocInternosController.modificarDocumentoInterno");			
			log.error(ex, ex);
			modelo.addObject("status", false);
			modelo.addObject("message",ex.getMessage());
		} finally {			
			if (log.isDebugEnabled()) log.debug("Final - EliminaDocInternosController.modificarDocumentoInterno");
			
			NDC.pop();
			NDC.remove();			
		}
		return modelo;
	}
	//[PAS20191U210500144] Fin
	
	/*Sets*/
	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
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
	
	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
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

	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}
	
}