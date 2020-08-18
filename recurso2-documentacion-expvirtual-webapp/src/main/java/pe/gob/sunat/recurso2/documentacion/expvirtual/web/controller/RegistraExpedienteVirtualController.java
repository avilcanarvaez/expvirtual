package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.AduanaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.CatalogoService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ConfiguracionExpedienteService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ExpedienteVirtualService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ParametroService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.PersonaService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.service.ValidarParametrosService;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.factory.EncriptaFactory;

public class RegistraExpedienteVirtualController extends MultiActionController {

	private static final Log log = LogFactory.getLog(RegistraExpedienteVirtualController.class);

	private JsonView jsonView;

	private ParametroService parametroService;
	private CatalogoService catalogoService;
	private PersonaService personaService;
	private ValidarParametrosService validarParametrosService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarService;
	private ExpedienteVirtualService expedienteVirtualService;
	private AduanaService aduanaService; //[oachahuic][PAS20165E210400270]

	public ModelAndView cargarRegistroExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.cargarRegistroExpedienteVirtual");

		ModelAndView modelo = null;

		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - RegistraExpedienteVirtualController.cargarRegistroExpedienteVirtual");

			String indCarga = request.getParameter("indCarga");

			if (ValidaConstantes.CARGA_INICIAL.equals(indCarga)) {

				modelo = new ModelAndView(NavegaConstantes.MANT_MODULO_01_04_001);

				if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

					String usub = request.getParameter("usub");
					String tenc = request.getParameter("tenc");
					usub = usub.replace(' ', '+');
					usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

				} else {

					usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

				}

				usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

				//String codDependencia = usuarioBean.getCodDepend();//[oachahuic][PAS20165E210400270]
				
				Map<String, Object> titulos = new HashMap<String, Object>();

				titulos.put("tituloDefecto", AvisoConstantes.TITULO_MODULO_01_04_001);

				Map<String, Object> excepciones = new HashMap<String, Object>();

				excepciones.put("excepcion01", AvisoConstantes.EXCEP_MODULO_01_04_001);
				excepciones.put("excepcion02", AvisoConstantes.EXCEP_MODULO_01_04_002);
				excepciones.put("excepcion03", AvisoConstantes.EXCEP_MODULO_01_04_003);
				excepciones.put("excepcion04", AvisoConstantes.EXCEP_MODULO_01_04_004);
				excepciones.put("excepcion05", AvisoConstantes.EXCEP_MODULO_01_04_005);
				excepciones.put("excepcion06", AvisoConstantes.EXCEP_MODULO_01_04_006);
				excepciones.put("excepcion07", AvisoConstantes.EXCEP_MODULO_01_04_007);
				excepciones.put("excepcion08", AvisoConstantes.EXCEP_MODULO_01_04_008);
				excepciones.put("excepcion09", AvisoConstantes.EXCEP_MODULO_01_04_009);
				excepciones.put("excepcion10", AvisoConstantes.EXCEP_MODULO_01_04_010);
				excepciones.put("excepcion11", AvisoConstantes.EXCEP_MODULO_01_04_011);
				excepciones.put("excepcion12", AvisoConstantes.EXCEP_MODULO_01_04_012);
				excepciones.put("excepcion13", AvisoConstantes.EXCEP_MODULO_01_04_013);
				excepciones.put("excepcion14", AvisoConstantes.EXCEP_MODULO_01_04_014);
				excepciones.put("excepcion15", AvisoConstantes.EXCEP_MODULO_01_04_015);
				excepciones.put("excepcion16", AvisoConstantes.EXCEP_MODULO_01_04_016);
				excepciones.put("excepcion17", AvisoConstantes.EXCEP_MODULO_01_04_017);
				// Inicio [jjurado 08/06/2016]
				excepciones.put("excepcion18", AvisoConstantes.EXCEP_MODULO_01_04_018);
				excepciones.put("excepcion19", AvisoConstantes.EXCEP_MODULO_01_04_019);
				// Fin [jjurado 08/06/2016]

				Map<String, Object> avisos = new HashMap<String, Object>();

				avisos.put("aviso00", AvisoConstantes.AVISO_MODULO_01_00_000);
				avisos.put("aviso01", AvisoConstantes.AVISO_MODULO_01_04_001);
				avisos.put("aviso02", AvisoConstantes.AVISO_MODULO_01_04_002);
				avisos.put("aviso03", AvisoConstantes.AVISO_MODULO_01_04_003);
				avisos.put("aviso04", AvisoConstantes.AVISO_MODULO_01_04_004);

				Date fecSistema = new Date();

				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

				String fecVista = formatoFechaExporta.format(fecSistema);

				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				mapa.put("indLimite", true);
				mapa.put("limInferior", CatalogoConstantes.LIMITE_INFERIOR_DEPENDENCIA);
				mapa.put("limSuperior", CatalogoConstantes.LIMITE_SUPERIOR_DEPENDENCIA);

				Map<String, Object> mapaDependencias = catalogoService.obtenerCatalogo(mapa);

				//String desDependencia = Utils.toStr(mapaDependencias.get(codDependencia));//[oachahuic][PAS20165E210400270]

				List<T01ParamBean> listadoProcesos = configuracionExpedienteService.listarProcesos();

				String codTipoExpedienteEspecial = ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA;

				Long numBytesArchivo = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_BYTES;
				Integer numMegasArchivo = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_MEGAS;

				modelo.addObject("titulos", new JsonSerializer().serialize(titulos));
				modelo.addObject("excepciones", new JsonSerializer().serialize(excepciones));
				modelo.addObject("avisos", new JsonSerializer().serialize(avisos));
				modelo.addObject("fecVista", new JsonSerializer().serialize(fecVista));
				//Inicio [oachahuic][PAS20165E210400270]
				Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(usuarioBean.getCodUO());
				if (mapDepAdu == null || mapDepAdu.isEmpty()) {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(usuarioBean.getCodDepend()));
					modelo.addObject("desDependencia", new JsonSerializer().serialize(Utils.toStr(mapaDependencias.get(usuarioBean.getCodDepend()))));
				} else {
					modelo.addObject("codDependenciaBase", new JsonSerializer().serialize(mapDepAdu.get("ADUANA").toString().trim()));
					modelo.addObject("desDependencia", new JsonSerializer().serialize(mapDepAdu.get("DEPEN2").toString().trim()));
				}
				//Fin [oachahuic][PAS20165E210400270]
				modelo.addObject("listadoProcesos", new JsonSerializer().serialize(listadoProcesos));
				modelo.addObject("codTipoExpedienteEspecial", new JsonSerializer().serialize(codTipoExpedienteEspecial));
				modelo.addObject("numBytesArchivo", new JsonSerializer().serialize(numBytesArchivo));
				modelo.addObject("numMegasArchivo", new JsonSerializer().serialize(numMegasArchivo));

			} else if (ValidaConstantes.CARGA_POSTERIOR.equals(indCarga)) {

				String indFiltro = request.getParameter("indFiltro");

				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String jsonEnviado = "";

				if (reader != null) {

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
					parametros.put("codTipoExpediente", dataEnvio.get("codTipoExpediente").toString());
					parametros.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					parametros.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
					parametros.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					List<T6623TipDocExpBean> listadoTiposDocumentos = configuracionExpedienteService.listarTiposDocumentos(parametros);
					modelo.addObject("listadoTiposDocumentos", listadoTiposDocumentos);

				} else if (ValidaConstantes.FILTRO_DOS.equals(indFiltro)) {

					String numRuc = dataEnvio.get("numRuc").toString();

					DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
					
					//nchavez 13/06/2016
					if (!Utils.isEmpty(contribuyente.getNumRuc())) {
						//Inicio [oachahuic][PAS20165E210400270]
						String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();
						log.debug("codDependenciaBase: " + codDependenciaBase);
						if (codDependenciaBase != null && codDependenciaBase.length() == 3) {
							contribuyente = aduanaService.obtenerAgenteHabilitado(numRuc, codDependenciaBase);
						} else {
							Map<String, Object> mapa = new HashMap<String, Object>();

							mapa.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
							mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
							mapa.put("indLimite", true);
							mapa.put("limInferior", CatalogoConstantes.LIMITE_INFERIOR_DEPENDENCIA);
							mapa.put("limSuperior", CatalogoConstantes.LIMITE_SUPERIOR_DEPENDENCIA);

							Map<String, Object> mapaDependencias = catalogoService.obtenerCatalogo(mapa);

							String desDependencia = mapaDependencias.get(contribuyente.getCodDependencia()).toString();
							contribuyente.setDesDependencia(desDependencia);
						}
						//Fin [oachahuic][PAS20165E210400270]
					}

					modelo.addObject("contribuyente", contribuyente);

				} else if (ValidaConstantes.FILTRO_TRES.equals(indFiltro)) {

					String codResponsable = dataEnvio.get("codResponsable").toString();
					//String codDependenciaBase = dataEnvio.get("codDependenciaBase").toString();

					parametros.put("codPersona", codResponsable);
					parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);

					T02PerdpBean responsable = personaService.validarPersona(parametros);

					if (responsable != null) {
						//Inicio - [oachahuic][PAS20165E210400270]
						Map<String, Object> mapDepAdu = aduanaService.verificarUoAduana(responsable.getCodUnidadOrganizacional().trim());
						if (mapDepAdu == null || mapDepAdu.isEmpty()) {
							parametros = new HashMap<String, Object>();

							String codUnidadOrganizacional = responsable.getCodUnidadOrganizacional() == null ? null : (responsable.getCodUnidadOrganizacional().trim() == "" ? null : responsable
							        .getCodUnidadOrganizacional().trim());
							
							parametros.put("codUnidadOrganizacional", codUnidadOrganizacional);
							//parametros.put("codDependencia", codDependenciaBase);
							parametros.put("codTipoDependencia", ValidaConstantes.IND_TIPO_DEPENDENCIA_INTRANET);

							T1797DepenUOrgaBean unidadOrganizacionalDependenica = personaService.validarDependenciaUnidadOrganizacional(parametros);

							if (unidadOrganizacionalDependenica != null) {

								responsable = personaService.completarInformacionPersona(responsable);

								responsable.setCodDependencia(unidadOrganizacionalDependenica.getCodDependencia()); 

							} else {

								responsable.setCodDependencia(ValidaConstantes.CADENA_VACIA);
								responsable.setDesDependencia(ValidaConstantes.CADENA_VACIA);

							}
						} else {
							responsable = personaService.completarInformacionPersona(responsable);
							responsable.setCodDependencia(mapDepAdu.get("ADUANA").toString().trim());
						}
						//Fin - [oachahuic][PAS20165E210400270]
					}

					modelo.addObject("responsable", responsable);

				}

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.cargarRegistroExpedienteVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.cargarRegistroExpedienteVirtual");

			NDC.pop();
			NDC.remove();

		}

		return modelo;

	}

	public ModelAndView validarArchivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.validarArchivo");

		ModelAndView modelo = new ModelAndView(jsonView);
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		String paramTamanioIA = "IA";
		long tamanoPermitido = 0;
		int tamanoPermitidoMsj = CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_MEGAS;
		int longitudnombrearchivo = 0;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - RegistraExpedienteVirtualController.validarArchivo");
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("codClase", CatalogoConstantes.CATA_TAMANIO_TOTAL_PERMITIDO); // CATA_TAMANIO_TOTAL_PERMITIDO
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			multipartRequest = (MultipartHttpServletRequest) request;
			multipartFile = multipartRequest.getFile("docFisico");

			List<T01ParamBean> listaTamanioMaximo = parametroService.listarParametros(params);
			for (T01ParamBean item : listaTamanioMaximo) {
				if (paramTamanioIA.equals(item.getCodParametro())) {
					tamanoPermitido = Long.parseLong(item.getDesParametro().toString());
					tamanoPermitido = tamanoPermitido * 1048576;
					
					tamanoPermitidoMsj = Integer.parseInt(item.getDesParametro());
				}
			}
			// CatalogoConstantes.MAX_SIZE_FILE_UPLOAD_BYTES
			// tamaño maximo permitodo en bytes
			if (multipartFile.getSize() < tamanoPermitido) {

				modelo.addObject("numExpedienteOrigenVal", false);

			} else {

				modelo.addObject("tamanoArchivoSuperado", true);
				modelo.addObject("tamanoArchivoPermitido", tamanoPermitidoMsj);

			}

			String nombreArchivo = multipartFile.getOriginalFilename();

			String[] listaSeparada = nombreArchivo.split("\\.");

			Integer num = listaSeparada.length;

			String extensionArchivo = listaSeparada[num - 1].toLowerCase();
			// longitud maxima de nombre del archivo a cargar
			longitudnombrearchivo = CatalogoConstantes.CATA_LONGITUD_MAXIMA_ARCHIVOCARGA;
			if (nombreArchivo.length() > longitudnombrearchivo) {

				modelo.addObject("longitudnombrearchivo", false);

			} else {

				modelo.addObject("longitudnombrearchivo", true);

			}

			// extensiones permitidas para el archivo a cargar
			params.put("codClase", CatalogoConstantes.CATA_EXTENSIONES_PERMITIDAS);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			List<T01ParamBean> listaExtensionesPermitidas = parametroService.listarParametros(params);

			String validaPatron = null;

			if (listaExtensionesPermitidas != null && listaExtensionesPermitidas.size() > 0) {

				validaPatron = "(";

				for (T01ParamBean extensionBean : listaExtensionesPermitidas) {

					validaPatron += extensionBean.getDesParametro().trim().toLowerCase() + "|";

				}

				validaPatron = validaPatron.substring(0, validaPatron.length() - 1);

				validaPatron += ")";

			}

			Pattern patron = Pattern.compile(validaPatron);
			Matcher matcher = patron.matcher(extensionArchivo);

			if (matcher.matches()) {

				modelo.addObject("extensionPermitida", true);

			} else {

				modelo.addObject("extensionPermitida", false);

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.validarArchivo");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.validarArchivo");
			NDC.pop();
			NDC.remove();

		}

		return modelo;

	}

	public ModelAndView validarNumExpedienteOrigen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.validarNumExpedienteOrigen");

		ModelAndView modelo = new ModelAndView(jsonView);
		try {
			String numExpOri = request.getParameter("numExpedo");
			//VALIDAR SI EXISTE EL EXPEDIENTE
			T6614ExpVirtualBean t6614Bean = expedienteVirtualService.obtenerExpVirByNumExpOri(numExpOri);
			if (t6614Bean != null) {
				ExpOrigenBean expOrigen = new ExpOrigenBean();
				expOrigen.setCodRpta("0");
				expOrigen.setDesError(AvisoConstantes.EXCEP_MODULO_01_04_012);
				modelo.addObject("expOrigen", expOrigen);
				return modelo;
			}
			//OBTENER DATOS DEL EXPEDIENTE ORIGEN
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cod_dep", request.getParameter("coddependencia"));
			params.put("num_ruc", request.getParameter("numruc"));
			params.put("cod_tip_exp", request.getParameter("codTipoExpediente"));
			params.put("num_exp_ori", numExpOri);
			ExpOrigenBean expOrigen = configuracionExpedienteService.obtenerDatosExOrigen(params);
			if (expOrigen == null) {
				expOrigen = new ExpOrigenBean();
				expOrigen.setCodRpta("0");
				expOrigen.setDesError("Ocurrió un error al validar el N° de Expediente Origen.");
				modelo.addObject("expOrigen", expOrigen);
			} else {
				modelo.addObject("expOrigen", expOrigen);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.validarNumExpedienteOrigen");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView("PaginaError"); 
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.validarNumExpedienteOrigen");
		}
		return modelo;
	}

	public ModelAndView validarNumExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.validarNumExpedienteVirtual");

		ModelAndView modelo = new ModelAndView(jsonView);
		Map<String, Object> paramExpOrigen = null;
		String numExpedo = null;
		String numExpedienteVirtual = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - RegistraExpedienteVirtualController.validarNumExpedienteVirtual");

			modelo.addObject("numExpedienteOrigenVal", true);

			numExpedo = request.getParameter("numExpedo");

			paramExpOrigen = new HashMap<String, Object>();
			paramExpOrigen.put("numExpedo", numExpedo);
			numExpedienteVirtual = validarService.validarExpedienteOrigen(paramExpOrigen);
			if (numExpedienteVirtual != null) {
				modelo.addObject("numExpedienteOrigenVal", false);
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.validarNumExpedienteVirtual");

			log.error(ex, ex);

			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.validarNumExpedienteVirtual");
			NDC.pop();
			NDC.remove();
		}

		return modelo;

	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.resolveException (MaxUploadSizeExceededException)");

		ModelAndView modelo = new ModelAndView(jsonView);

		if (exception instanceof MaxUploadSizeExceededException) {

			Boolean tamanoArchivoSuperado = false;

			try {

				if (log.isDebugEnabled()) log.debug("Procesa - RegistraExpedienteVirtualController.resolveException (MaxUploadSizeExceededException)");

				modelo.addObject("tamanoArchivoSuperado", tamanoArchivoSuperado);

			} catch (Exception ex) {

				if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.resolveException (MaxUploadSizeExceededException)");
				log.error(ex, ex);
				MensajeBean msb = new MensajeBean();
				modelo = new ModelAndView(jsonView);
				modelo.addObject("indError", true);
				msb.setError(true);
				msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
				modelo.addObject("beanErr", msb);

			} finally {

				if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.resolveException (MaxUploadSizeExceededException)");
				NDC.pop();
				NDC.remove();

			}

		}

		return modelo;

	}

	public ModelAndView registrarExpedienteVirtual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (log.isDebugEnabled()) log.debug("Inicio - RegistraExpedienteVirtualController.registrarExpedienteVirtual");

		ModelAndView modelo = null;

		HttpSession session = request.getSession(true);
		UsuarioBean usuarioBean = null;
		Map<String, Object> paramValAsociacion = new HashMap<String, Object>();
		Map<String, Object> paramExpOrigen = new HashMap<String, Object>();
		boolean existerelacion = false;
		String numExpedienteVirtual = null;

		try {

			if (session == null || session.getAttribute("usuarioBean") == null || request.getParameter("usub") != null) {

				String usub = request.getParameter("usub");
				String tenc = request.getParameter("tenc");
				usub = usub.replace(' ', '+');
				usuarioBean = EncriptaFactory.getEncripta(Integer.parseInt(tenc)).desencripta(usub);

			} else {

				usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

			}

			if (log.isDebugEnabled()) log.debug("Procesa - RegistraExpedienteVirtualController.registrarExpedienteVirtual");

			modelo = new ModelAndView(jsonView);

			DateFormat formatoFecha = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

			MultipartHttpServletRequest multipartRequest = null;
			MultipartFile archivo = null;

			multipartRequest = (MultipartHttpServletRequest) request;
			archivo = multipartRequest.getFile("docFisico");
			
			String dataOculta = request.getParameter("dataOculta").toString();
			String hdnDescripcion = request.getParameter("hdnDescripcion").toString();
			
			if (log.isDebugEnabled()) log.debug("dataOculta: " + dataOculta);

			@SuppressWarnings("unchecked")
			Map<String, Object> infoExpediente = (Map<String, Object>) new JsonSerializer().deserialize(dataOculta, Map.class);

			String numRuc = infoExpediente.get("numRuc").toString();
			String codProceso = infoExpediente.get("codProceso").toString();
			String codTipoExpediente = infoExpediente.get("codTipoExpediente").toString();
			String numExpedienteOrigen = infoExpediente.get("numExpedienteOrigen").toString();
			String codResponsable = infoExpediente.get("codResponsable").toString();
			String codTipoDocumento = infoExpediente.get("codTipoDocumento").toString();
			String desDescripcion = hdnDescripcion;
			if (log.isDebugEnabled()) log.debug("desDescripcion: " + desDescripcion);
			String numDocumento = infoExpediente.get("numDocumento").toString();
			String fecDocumentoCadena = infoExpediente.get("fecDocumento").toString();
			String busquedaClave = infoExpediente.get("busquedaClave").toString();
			String codigoECM = ""; // infoExpediente.get("codigoECM").toString();
			Date fecDocumento = (Date) formatoFecha.parse(fecDocumentoCadena);
			String fechaDocCompleta = infoExpediente.get("fecDocumentoCompleta").toString().trim();
			fechaDocCompleta.replace("null", "");
			if(Utils.equiv(fechaDocCompleta, "null")){
				fechaDocCompleta = "";
			}
			
			usuarioBean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");

			String codUsuario = usuarioBean.getNroRegistro();
			String codDependencia = usuarioBean.getCodDepend();

			Date fecSistema = new Date();

			SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);

			String fecVista = formatoFechaExporta.format(fecSistema);

			String nomArchivo = null;
			byte[] archivoBytes = null;

			String mimeType = null;
			String arrayMimeType[] = null;
			Long archivoTamanho = null;

			if (ValidaConstantes.CADENA_VACIA.equals(codigoECM)) {

				nomArchivo = archivo.getOriginalFilename();
				archivoBytes = archivo.getBytes();
				archivoTamanho = archivo.getSize();

				mimeType = archivo.getContentType();
				arrayMimeType = mimeType.split(";");
				mimeType = arrayMimeType[0];

			}

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("numExpedo", numExpedienteOrigen);
			parametros.put("flagOrigen", ValidaConstantes.IND_INVOC_SERVICIO_MANUAL);
			//parametros.put("codEstexpori", ValidaConstantes.SEPARADOR_GUION);
			//parametros.put("codEtaexpori", ValidaConstantes.SEPARADOR_GUION);
			parametros.put("codUsuregis", codUsuario);
			parametros.put("codOrigen", ValidaConstantes.IND_ORIGEN_EXP_VIRT_MANUAL);
			parametros.put("codOrigDoc", ValidaConstantes.IND_ORIGEN_DOCUMENTO_MANUAl_INTRANET);
			parametros.put("numRuc", numRuc);
			parametros.put("codProc", codProceso);
			parametros.put("codTipexp", codTipoExpediente);
			parametros.put("codTipdoc", codTipoDocumento);
			parametros.put("numDoc", numDocumento);
			

			if(fechaDocCompleta != null && !fechaDocCompleta.equals("")) {
				parametros.put("fecDoc", Utils.stringToDate(fechaDocCompleta, CatalogoConstantes.INT_ONE));
			} else {
				parametros.put("fecDoc", fecDocumento);
			}
			
			DdpBean contribuyente = validarParametrosService.validarRUC(numRuc);
			//Inicio [oachahuic][PAS20165E210400270]
			String codDependenciaBase = infoExpediente.get("codDependenciaBase").toString();
			log.debug("codDependenciaBase: " + codDependenciaBase);
			if (codDependenciaBase != null && codDependenciaBase.length() == 3) {
				contribuyente = aduanaService.obtenerAgenteHabilitado(numRuc, codDependenciaBase);
			}
			//Fin [oachahuic][PAS20165E210400270]
			parametros.put("codDep", contribuyente.getCodDependencia());
			
			parametros.put("metadata", busquedaClave);
			parametros.put("desExpedv", desDescripcion);
			parametros.put("codColab", codResponsable);

			Map<String, Object> paramTipDoc = new HashMap<String, Object>();
			paramTipDoc.put("codTipoDocumento", codTipoDocumento);
			paramTipDoc.put("codTipoExpediente", codTipoExpediente);
			paramTipDoc.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			paramTipDoc.put("indTipDocumento", ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
			paramTipDoc.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
			List<T6623TipDocExpBean> lstTipDoc = configuracionExpedienteService.listarTiposDocumentos(paramTipDoc);
			T6623TipDocExpBean tipDocBean = lstTipDoc != null && !lstTipDoc.isEmpty() ? lstTipDoc.get(0) : null;
			parametros.put("indVisible", tipDocBean == null ? ValidaConstantes.IND_VISIBLE_DOC : tipDocBean.getIndVisibleContribuyente());

			if (ValidaConstantes.CADENA_VACIA.equals(codigoECM)) {

				parametros.put("desArch", nomArchivo);
				parametros.put("binDoc", archivoBytes);
				parametros.put("archMimeType", mimeType);

				parametros.put("archFileName", nomArchivo);
				parametros.put("archLength", archivoTamanho);

			} else {

				parametros.put("codIdecm", codigoECM);

			}

			/* ini: 14-12-15 miguel ochoa - validar la asociacion de proceso y tipo de expediente antes de registrar el cambio */
			paramValAsociacion.put("codProceso", codProceso);
			paramValAsociacion.put("codTipoExpediente", codTipoExpediente);
			paramValAsociacion.put("indEliminado", "0");
			List<T6624TipExpProcBean> listadoValidacion = configuracionExpedienteService.listarTiposExpendiente(paramValAsociacion);

			for (T6624TipExpProcBean tipoExpedienteBean : listadoValidacion) {
				if (tipoExpedienteBean.getCodTipoExpediente().toString().equals(codTipoExpediente)) {
					existerelacion = true;
					break;
				}
			}

			/* fin */

			if (existerelacion) {
				// verificamos si hay duplicdad de registros
				paramExpOrigen = new HashMap<String, Object>();
				paramExpOrigen.put("numExpedo", numExpedienteOrigen);
				numExpedienteVirtual = validarService.validarExpedienteOrigen(paramExpOrigen);
				if (numExpedienteVirtual != null) {
					modelo.addObject("numExpedienteVirtual", null);
					modelo.addObject("fecVista", null);
					modelo.addObject("duplicidad", true);
					modelo.addObject("noexisterelacion", false);
				} else {
					parametros.put("indEmiAlerta", ValidaConstantes.IND_EMI_ALERTA_SI);

					//OBTENER EL ESTADO Y/O ETAPA DEL EXPEDIENTE ORIGEN - [oachahuic][PAS20181U210400241]
					Map<String, Object> estEtapMap = new HashMap<String, Object>();
					estEtapMap.put("codTipoDoc", codTipoDocumento);
					parametros.putAll(configuracionExpedienteService.obtenerEstadoEtapa(estEtapMap));
					
					numExpedienteVirtual = expedienteVirtualService.registrarExpedienteVirtual(parametros);

					modelo.addObject("numExpedienteVirtual", numExpedienteVirtual);
					modelo.addObject("fecVista", fecVista);
					modelo.addObject("duplicidad", false);
					modelo.addObject("noexisterelacion", false);
				}

			} else {
				modelo.addObject("numExpedienteVirtual", null);
				modelo.addObject("fecVista", null);
				modelo.addObject("noexisterelacion", true);
				modelo.addObject("duplicidad", false);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - RegistraExpedienteVirtualController.registrarExpedienteVirtual");

			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(jsonView);
			modelo.addObject("indError", true);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - RegistraExpedienteVirtualController.registrarExpedienteVirtual");

			NDC.pop();
			NDC.remove();

		}

		return modelo;

	}
	
	@RequestMapping(value = "/descargarDocumento", method = RequestMethod.POST)
	public void descargarDocumento(HttpServletRequest request,HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.info( "Inicio - RegistraExpedienteVirtualController.descargarDocumento");
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile multipartFile = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		File archivo;
		
		try {
			
			/*multipartFile = multipartRequest.getFile("file"+codDocumento);
			archivo = Utils.multipartToFile(multipartFile);
			File destino = new File(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO+multipartFile.getOriginalFilename());
			FileUtils.copyFile(archivo, destino);*/
			multipartFile = multipartRequest.getFile("docFisico");
			String nombreArchivo = Utils.convertirUnicode(multipartFile.getOriginalFilename());
			File downloadFile = new File(nombreArchivo);
			multipartFile.transferTo(downloadFile);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        ServletContext context = getServletContext();
	        String mimeType = context.getMimeType(ValidaConstantes.RUTA_SERVIDOR_ARCHIVO +"@@"+nombreArchivo); 
	         
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	              
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", URLEncoder.encode(nombreArchivo));
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
			
	      //  destino.delete();
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraExpedienteVirtualController.descargarDocumento");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraExpedienteVirtualController.descargarDocumento");
			}
			NDC.pop();
			NDC.remove();
		}
		
	}
	@RequestMapping(value = "/verDatosExpedientePDF", method = RequestMethod.GET)
	public void verDatosExpedientePDF(HttpServletRequest request,HttpServletResponse response){
		
		if (log.isDebugEnabled()) {
			log.debug((Object) "Inicio - RegistraExpedienteVirtualController.verDatosExpedientePDF");
		}
		
		try {
			
			//Obtenemos los datos del JSP
			String txtNumExpVirtual = Utils.toBlank(Utils.toStr(request.getParameter("txtNumExpVirtual")));
			String fecha = Utils.toBlank(Utils.toStr(Utils.dateUtilToStringDDMMYYYY(new Date())));
			String txtFechaRegistro = Utils.toBlank(Utils.toStr(request.getParameter("txtFechaRegistro")));
			String txtFechaNotificacion = Utils.toBlank(Utils.toStr(request.getParameter("txtFechaNotificacion")));
			String txtNumRuc =Utils.toBlank( Utils.toStr(request.getParameter("txtNumRuc")));
			String txtDesRazonSocial = Utils.toBlank(Utils.toStr(request.getParameter("txtDesRazonSocial")));
			txtDesRazonSocial = txtDesRazonSocial.replace("\"", "''");
			String txtDependencia = Utils.toBlank(Utils.toStr(request.getParameter("txtDependencia")));
			String selProceso = Utils.toBlank(Utils.toStr(request.getParameter("desProceso")));
			if(Utils.equiv(selProceso, "Seleccione")){
				selProceso="";
			}
			String selTipoExpediente = Utils.toBlank(Utils.toStr(request.getParameter("desTipExpediente")));
			if(Utils.equiv(selTipoExpediente, "Seleccione")){
				selTipoExpediente="";
			}
			String txtNumExpOrigen = Utils.toBlank(Utils.toStr(request.getParameter("txtNumExpOrigen")));
			String txtDescripcion = Utils.toBlank(Utils.toStr(request.getParameter("txtDescripcion")));
			txtDescripcion = txtDescripcion.replace("\"", "''");
			String txtCodResponsable = Utils.toBlank(Utils.toStr(request.getParameter("txtCodResponsable"))).toUpperCase();
			String txtDesResponsable = Utils.toBlank(Utils.toStr(request.getParameter("txtDesResponsable")));
			String selTipoDocumento = Utils.toBlank(Utils.toStr(request.getParameter("selTipoDocumento")));
			if(Utils.equiv(selTipoDocumento, "Seleccione")){
				selTipoDocumento="";
			}
			String txtNumDocumento = Utils.toBlank(Utils.toStr(request.getParameter("txtNumDocumento")));
			String nombreArchivo = Utils.toBlank(Utils.toStr(request.getParameter("nombreArchivo")));
			String txtBusquedaClave = Utils.toBlank(Utils.toStr(request.getParameter("palBusquedaClave")));
			String txtFecDocumento = Utils.toBlank(Utils.toStr(request.getParameter("txtFecDocumento")));
			
			
			
			//Generamos el PDF
			String 	json = "{"+"\""+"cabecera"+"\":{";
			json+="\""+"txtNumExpVirtual"+"\":"+"\""+txtNumExpVirtual;
			json+="\","+"\""+"fecha"+"\":"+"\""+fecha;
			json+="\","+"\""+"txtFechaRegistro"+"\":"+"\""+txtFechaRegistro;
			json+="\","+"\""+"txtFechaNotificacion"+"\":"+"\""+txtFechaNotificacion;
			json+="\","+"\""+"txtNumRuc"+"\":"+"\""+txtNumRuc;
			json+="\","+"\""+"txtDependencia"+"\":"+"\""+txtDependencia;
			json+="\","+"\""+"selProceso"+"\":"+"\""+selProceso;
			json+="\","+"\""+"selTipoExpediente"+"\":"+"\""+selTipoExpediente;
			json+="\","+"\""+"txtNumExpOrigen"+"\":"+"\""+txtNumExpOrigen;
			json+="\","+"\""+"txtDescripcion"+"\":"+"\""+txtDescripcion;
			json+="\","+"\""+"txtCodResponsable"+"\":"+"\""+txtCodResponsable;
			json+="\","+"\""+"txtDesResponsable"+"\":"+"\""+txtDesResponsable;
			json+="\","+"\""+"txtDesRazonSocial"+"\":"+"\""+txtDesRazonSocial.replace("\"", "\\\"");
			json+="\","+"\""+"selTipoDocumento"+"\":"+"\""+selTipoDocumento;
			json+="\","+"\""+"txtNumDocumento"+"\":"+"\""+txtNumDocumento;
			json+="\","+"\""+"nombreArchivo"+"\":"+"\""+nombreArchivo;
			if(!txtBusquedaClave.contains("\"")){
				json+="\","+"\""+"txtBusquedaClave"+"\":"+"\""+txtBusquedaClave;
			}
			
			json+="\","+"\""+"txtFecDocumento"+"\":"+"\""+txtFecDocumento;
			json+="\"}";
			json+= "}";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("json", json);
			parametros.put("codPDF", ExportaConstantes.PDF_COD_PLANTILLA_01_002);
			
			byte[] docConstPresentDocu = expedienteVirtualService.verDatosExpedientePDF(parametros);
			OutputStream outStream = response.getOutputStream();
			response.setContentLength(docConstPresentDocu.length);
		    response.setContentType("application/pdf");
		    outStream.write(docConstPresentDocu);
		    outStream.close();
			
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Error - RegistraExpedienteVirtualController.verDatosExpedientePDF");
			}
			
		} finally {
			if (log.isDebugEnabled()) {
				log.debug((Object) "Final - RegistraExpedienteVirtualController.verDatosExpedientePDF");
			}
			NDC.pop();
			NDC.remove();
		}
		
		
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

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setValidarParametrosService(ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setConfiguracionExpedienteService(ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setExpedienteVirtualService(ExpedienteVirtualService expedienteVirtualService) {
		this.expedienteVirtualService = expedienteVirtualService;
	}

	public void setValidarService(ValidarParametrosService validarService) {
		this.validarService = validarService;
	}

	public void setAduanaService(AduanaService aduanaService) {
		this.aduanaService = aduanaService;
	}

	/* Seteo - Spring - Final */

}