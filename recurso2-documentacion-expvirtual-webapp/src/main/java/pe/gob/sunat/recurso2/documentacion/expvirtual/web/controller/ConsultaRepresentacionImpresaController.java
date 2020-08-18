package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.web.view.JsonView;
import pe.gob.sunat.framework.util.captcha.CaptchaInformation;
import pe.gob.sunat.framework.util.captcha.SunatCaptchaEngine;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.EcmService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.RepresentacionImpresaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

@Controller
public class ConsultaRepresentacionImpresaController extends
		MultiActionController {

	private static final Log log = LogFactory
			.getLog(ConsultaRepresentacionImpresaController.class);

	private String imageView;
	private JsonView jsonView;
	
	private EcmService ecmService;
	private DocumentoExpedienteService documentoExpedienteService; 
	private RepresentacionImpresaService representacionImpresaService;
	private ValidarParametrosService validarParametrosService;

	@RequestMapping(value = { "/consultarReprImpresaView" }, method = { RequestMethod.POST })
	public ModelAndView consultarReprImpresaView(HttpServletRequest request,
			HttpServletResponse response) {

		boolean esBusqAutomatica = false;
		ModelAndView modelo;
		if (log.isDebugEnabled())
			log.debug("Inicio - ConsultaSeguimientoController.consultarReprImpresaView");
		try {
			
			Map<String, Object> excepciones = new HashMap<String, Object>();
			
			excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_04_07_001);
			excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_04_07_002);
			excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_04_07_003);
			excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_04_07_004);
			excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_04_07_005);
			excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_04_07_006);
			excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_04_07_007);
			
			modelo = new ModelAndView("ConsultaValidacionRepresentacionImpresa");
			
			String codTipConsul = CatalogoConstantes.TIPO_CONSULTA_MANUAL;//Manual = 01 - Automatico = 02
			String numRUC = request.getParameter("ruc");
			String numRepreImp = request.getParameter("ri");
			
			if(numRepreImp != null){
				codTipConsul = CatalogoConstantes.TIPO_CONSULTA_AUTOMATICA;
				esBusqAutomatica = true;
			}
			
			numRepreImp =  numRepreImp != null ? numRepreImp : ValidaConstantes.CADENA_VACIA ;
			numRUC = numRUC != null ? numRUC : ValidaConstantes.CADENA_VACIA;
			
			modelo.addObject("numRUC", numRUC);
			modelo.addObject("numRepreImp", numRepreImp);
			modelo.addObject("codTipConsul", codTipConsul);
			modelo.addObject("esBusqAutomatica", esBusqAutomatica);
			modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
			
		} catch (Exception ex) {

			if (log.isDebugEnabled())
				log.debug("Error - ConsultaSeguimientoController.consultarReprImpresaView");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled())
				log.debug("Final - ConsultaSeguimientoController.consultarReprImpresaView");

			NDC.pop();
			NDC.remove();

		}

		return modelo;
	}

	public ModelAndView consultarValiRiev(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.info("Inicio - ConsultaRepresentacionImpresaController.consultarValiRiev");
		ModelAndView modelAndView;
		Map<String, Object> parametros = new HashMap<String, Object>();
		try {

			Map<String, Object> paramPost = obtenerParametrosPost(request);
			String msjErrorExiste = existenRucYReprImpr(paramPost);
			modelAndView = new ModelAndView(jsonView);
			if (!msjErrorExiste.isEmpty()) {
				modelAndView.addObject("error", true);
				modelAndView.addObject("msjError", msjErrorExiste);
			} else {
				parametros.putAll(paramPost);
				parametros.put("codUsuReg", "SIEV");// Gangles 15/09/2016
				parametros.put("grabarSeguim", true);

				T6625repimpexpvirtBean t6625repimpexpvirtBean = representacionImpresaService.buscarExpedienteRepreImpr(parametros);

				modelAndView.addObject("codTipConsul", CatalogoConstantes.TIPO_CONSULTA_MANUAL);
				if (t6625repimpexpvirtBean != null) {
					modelAndView.addObject("numCorDoc", t6625repimpexpvirtBean.getNumCorDoc());
					modelAndView.addObject("numExpedo", t6625repimpexpvirtBean.getNumExpedo());
				} else {
					modelAndView.addObject("numCorDoc", ValidaConstantes.SIN_DATA);
					modelAndView.addObject("numExpedo", ValidaConstantes.SIN_DATA);
				}
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaRepresentacionImpresaController.consultarValiRiev");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaRepresentacionImpresaController.consultarValiRiev");
			NDC.pop();
			NDC.remove();
		}

		return modelAndView;

	}

	private String existenRucYReprImpr(Map<String, Object> paramPost) throws Exception {
		String msjError = ValidaConstantes.CADENA_VACIA;
		Map<String, Object> parametros = new HashMap<String, Object>();

		// Validación existencia RUC
		DdpBean contribuyente = validarParametrosService.validarRUC(Utils.toStr(paramPost.get("numRUC")));
		// nchavez 13/06/2016
		if (Utils.isEmpty(contribuyente.getNumRuc())) {
			msjError = AvisoConstantes.EXCEP_MODULO_04_07_005;
		} else {

			parametros.put("numRepreImp", Utils.toStr(paramPost.get("numRepreImp")));
			parametros.put("grabarSeguim", false);
			T6625repimpexpvirtBean t6625repimpexpvirtBean = representacionImpresaService.buscarExpedienteRepreImpr(parametros);
			if (t6625repimpexpvirtBean == null) {
				msjError = AvisoConstantes.EXCEP_MODULO_04_07_007;
			}
		}

		return msjError;
	}

	// Inicio [jtejada 19/08/2016]
	@RequestMapping(value = "/verPDFRepImpresa", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public ModelAndView verPDFRepImpresa(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Inicio - ConsultaRepresentacionImpresaController.verPDFRepImpresa");
		
		ModelAndView modelo = null;
		//HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		Map<String,Object> mapDatosDocumento = new HashMap<String, Object>();
		Map<String,Object> mapUsuarioBean = new HashMap<String, Object>();
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConsultaRepresentacionImpresaController.verPDFRepImpresa");
			
			//Se obtiene codigo identificador ECM
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numCorDoc", request.getParameter("codCorDoc"));
			List<T6613DocExpVirtBean> lstT6613DocExpVirtBean = documentoExpedienteService.listarDocumentosExpediente(parametros);
			String codIdentificadorECM = lstT6613DocExpVirtBean.get(0).getCodIdentificadorECM(); 
			
			//DESCARGAR EL DOCUMENTO
			Map<String, Object> mapDLECM = new HashMap<String, Object>();
			mapDLECM.put("codIdecm", codIdentificadorECM);
			mapDLECM.put("inline", "true");

			ResponseEntity<byte[]> responseDoc = ecmService.descargarDocumentoECM(mapDLECM);
			
			T6625repimpexpvirtBean t6625repimpexpvirtBean = Utils.generarDocumentoDeRepImpresa(mapDatosDocumento, responseDoc, mapUsuarioBean, response);
			if(t6625repimpexpvirtBean.getNumRepImp().contains("error")){
				String extension = t6625repimpexpvirtBean.getNumRepImp().split("-")[1];
				modelo.addObject("error", "error");
				modelo.addObject("extension", "extension");
			}
		    
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConsultaRepresentacionImpresaController.verPDFRepImpresa");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConsultaRepresentacionImpresaController.verPDFRepImpresa");
			NDC.pop();
			NDC.remove();
		}
		return new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
	}
	// Fin [jtejada 19/08/2016]
	
	private UsuarioBean traerUsuarioBean(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean;
		if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null ) {
			
			String usub = request.getParameter("usub");
			String tenc = request.getParameter("tenc");
			usub = usub.replace(' ', '+');
			usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);
			
		}else{
			
			usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean");
			
		}
		
		return usuarioBean;
	} 
	
	@RequestMapping(value = { "/generaCaptcha" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView generaCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		try {
			ModelAndView viewPage;

			CaptchaInformation captcha = SunatCaptchaEngine.generateCaptcha();
			BufferedImage buffImage = captcha.getImage();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(buffImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			WebUtils.setSessionAttribute(request, "captchaActual",
					captcha.getWord());
			model.put("image", imageInByte);
			viewPage = new ModelAndView(getImageView(), model);

			return viewPage;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error", e);
			return cargaPaginaErrorMensaje(request, response, e.getMessage());
		}
	}

	public ModelAndView validarTextoCaptcha(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		String txtCaptcha = request.getParameter("txtCaptcha");
		MensajeBean mensaje = new MensajeBean();
		mensaje.setError(false);
		String captchaActual = (String) WebUtils.getSessionAttribute(request,
				"captchaActual");
		if (captchaActual != null && txtCaptcha != null) {
			if (!captchaActual.trim().equals(txtCaptcha.trim())) {
				mensaje.setError(true);// El captcha no coincide
			}
		}
		respuesta.put("mensaje", mensaje);
		return new ModelAndView(getJsonView(), respuesta);
	}

	private ModelAndView cargaPaginaErrorMensaje(HttpServletRequest request,
			HttpServletResponse response, String mensaje) throws Exception {

		try {
			List<Map<String, Object>> errores = new ArrayList<Map<String, Object>>();
			Map<String, Object> error;

			error = new HashMap<String, Object>();
			error.put("codigo", "-");
			error.put("mensaje", mensaje);
			errores.add(error);

			log.debug("Valores en cargaPaginaErrorMensajeParametro: " + errores);
			WebUtils.setSessionAttribute(request, "errores", errores);

			ModelAndView viewPage = new ModelAndView("paginaError");
			return viewPage;
		} catch (Exception e) {
			log.error("error", e);
			throw new Exception(e);
		}
	}
	public ModelAndView validarExtension(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.info("Inicio - ConsultaRepresentacionImpresaController.validarExtension");
		ModelAndView modelAndView;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numCorDoc", request.getParameter("numCorDoc"));

			List<T6613DocExpVirtBean> lstT6613DocExpVirtBean = documentoExpedienteService.listarDocumentosExpediente(parametros);
			//String codIdentificadorECM = lstT6613DocExpVirtBean.get(0).getCodIdentificadorECM();
			String desArchivo= lstT6613DocExpVirtBean.get(0).getDescripcionArchivo();
			// DESCARGAR EL DOCUMENTO
			String extension=Utils.obtenerExtension(desArchivo);
			// DESCARGAR EL DOCUMENTO
			modelAndView = new ModelAndView(jsonView);
			modelAndView.addObject("error", !Utils.equiv(extension.toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF));
			modelAndView.addObject("extension", extension.toUpperCase());

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConsultaRepresentacionImpresaController.validarExtension");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelAndView = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelAndView.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConsultaRepresentacionImpresaController.validarExtension");
			NDC.pop();
			NDC.remove();
		}

		if (log.isDebugEnabled()) log.info("Final - ConsultaRepresentacionImpresaController.cargarListaTiposExpediente");
		return modelAndView;

	}

	private Map<String, Object> obtenerParametrosPost(HttpServletRequest request) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonEnviado = ValidaConstantes.CADENA_VACIA;
		
		if(reader != null) {
			jsonEnviado = reader.readLine();
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> mapParametros = (Map<String, Object>) new JsonSerializer().deserialize(jsonEnviado, Map.class);

		return mapParametros;
	}
	
	public void setImageView(String imageView) {
		this.imageView = imageView;
	}

	public String getImageView() {
		return imageView;
	}

	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public JsonView getJsonView() {
		return this.jsonView;
	}

	public RepresentacionImpresaService getRepresentacionImpresaService() {
		return representacionImpresaService;
	}

	public void setRepresentacionImpresaService(RepresentacionImpresaService representacionImpresaService) {
		this.representacionImpresaService = representacionImpresaService;
	}

	public EcmService getEcmService() {
		return ecmService;
	}

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public DocumentoExpedienteService getDocumentoExpedienteService() {
		return documentoExpedienteService;
	}

	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}

	public ValidarParametrosService getValidarParametrosService() {
		return validarParametrosService;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}
	
	
}
