package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T1797DepenUOrgaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6621RespExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.MensajeAlertaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class ResponsableServiceImpl implements ResponsableService {

	private static final Log log = LogFactory.getLog(ResponsableServiceImpl.class);

	private T6621RespExpVirtDAO t6621RespExpVirtDAO;
	private T02PerdpDAO t02PerdpDAO;
	private T1797DepenUOrgaDAO t1797DepenUOrgaDAO;
	private SequenceDAO sequenceDAO;

	private GeneralService generalService;
	private SeguimientoService seguiService;
	private CorreosService correosService;
	private PersonaService personaService;
	// Inicio [jquispe 27/05/2016]
	private CatalogoService catalogoService;
	// Fin [jquispe 27/05/2016]
	
	@Override
	public List<T6621RespExpVirtBean> obtenerResponsablesAsignados(String numExpedienteVirtual) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignados");

		List<T6621RespExpVirtBean> listaResponsablesAsig = new ArrayList<T6621RespExpVirtBean>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> paramPers = null;
		T02PerdpBean t02PerdpBean = null;
		String desColaborador = null;

		try {
			params.put("numExpedienteVirtual", numExpedienteVirtual);
			params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listaResponsablesAsig = t6621RespExpVirtDAO.listar(params);

			if (listaResponsablesAsig != null) {
				paramPers = new HashMap<String, Object>();

				for (T6621RespExpVirtBean bean : listaResponsablesAsig) {
					bean.setCodColaborador(bean.getCodColaborador() != null ? bean.getCodColaborador().trim() : null);
					paramPers.put("codPersona", bean.getCodColaborador());
					t02PerdpBean = t02PerdpDAO.obtener(paramPers);
					
					if (t02PerdpBean != null) {
						desColaborador = t02PerdpBean.getDesNombres().trim() + ValidaConstantes.SEPARADOR_ESPACIO + t02PerdpBean.getDesApellidoPaterno().trim() + ValidaConstantes.SEPARADOR_ESPACIO
						        + t02PerdpBean.getDesApellidoMaterno().trim();
						bean.setDesColaborador(desColaborador);
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignados");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignados");
		}
		return listaResponsablesAsig;
	}
	
	@Override
	public List<T6621RespExpVirtBean> obtenerResponsablesAsignadosSepar(String numExpedienteVirtual) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignadosSepar");

		List<T6621RespExpVirtBean> listaResponsablesAsig = new ArrayList<T6621RespExpVirtBean>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> paramPers = null;
		T02PerdpBean t02PerdpBean = null;

		try {

			params.put("numExpedienteVirtual", numExpedienteVirtual);
			params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			listaResponsablesAsig = t6621RespExpVirtDAO.listar(params);

			if (listaResponsablesAsig != null) {
				paramPers = new HashMap<String, Object>();

				for (T6621RespExpVirtBean bean : listaResponsablesAsig) {
					bean.setCodColaborador(bean.getCodColaborador() != null ? bean.getCodColaborador().trim() : null);

					paramPers.put("codPersona", bean.getCodColaborador());

					t02PerdpBean = t02PerdpDAO.obtener(paramPers);
					if (t02PerdpBean != null) {
						bean.setNombres(t02PerdpBean.getDesNombres().trim());
						bean.setApellidosPaterno(t02PerdpBean.getDesApellidoPaterno().trim());
						bean.setApellidosMaterno(t02PerdpBean.getDesApellidoMaterno().trim());
						bean.setUnidOrganizacional(t02PerdpBean.getCodUnidadOrganizacional()); //[PAS20191U210500291]: JMC
					}
				}
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignadosSepar");
			log.error(ex, ex);
			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerResponsablesAsignadosSepar");

			NDC.pop();
			NDC.remove();

		}

		return listaResponsablesAsig;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> actualizarAsignacionResponsables(Map<String, Object> parametros) throws Exception {
		//DataSource origenDatos = null;//se comenta pues ya no se pasa el objeto Datasource a la consulta
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		Map<String, Object> mapDataSource = null;
		Calendar fechaActual = null;
		JsonSerializer serializer = null;
		Map<String, Object> mapRequest = null;
		Map<String, Object> mapResponse = null;
		Map<String, Object> beanSeguiWS = null;
		Calendar fechaVacia = null;
		String jsonResponse = null;
		List<String> listaColaboradores = null;
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		String mensaje = null;
		List<CorreosBean> listaCorreosBeans = null;
		CorreosBean correosBean = null;
		List<T6621RespExpVirtBean> listaRespAsignados = null;
		String codColabParam = null;
		String codColab = null;
		String codColabNew = null;
		T02PerdpBean t02PerdpBean = null;
		List<T02PerdpBean> listaT02PerdpBeans = null;
		Map<String, Object> mapPerdp = null;
		String responsables = "";
		String flagOrigen = null;
		boolean actResponsable = false;
		Map<String, Object> mapRepAsig = null;

		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.actualizarAsignacionResponsables");

		try {
			// Fecha actual
			fechaActual = Calendar.getInstance();

			// Fecha fin
			fechaVacia = Calendar.getInstance();
			fechaVacia.set(1, 0, 1, 0, 0, 0);

			mapDataSource = new HashMap<String, Object>();
			mapDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

			//origenDatos = generalService.obtenerOrigenDatos(mapDataSource);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			mapaPool = generalService.obtenerOrigenDatosMap(mapDataSource); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml		

			listaRespAsignados = (ArrayList<T6621RespExpVirtBean>) parametros.get("listaRespAsignados");
			if (listaRespAsignados != null && listaRespAsignados.size() > 0) {
				codColabParam = (String) parametros.get("codColab");
				listaColaboradores = new ArrayList<String>();

				for (T6621RespExpVirtBean beanRepAsig : listaRespAsignados) {
					codColab = beanRepAsig.getCodColaborador().trim();

					if (codColab.equals(codColabParam) && !beanRepAsig.getIndResponsable().equals(ValidaConstantes.IND_RESP_PRINCIPAL)) {
						mapRepAsig = new HashMap<String, Object>();

						// actualizar
						mapRepAsig.put("numCorresp", beanRepAsig.getNumCorrelativo());
						codColabNew = (String) parametros.get("codColabNew");
						mapRepAsig.put("codColaborador", codColabNew);
						mapRepAsig.put("codUsuModifica", parametros.get("codUsuModResp"));
						mapRepAsig.put("fecModifica", fechaActual.getTime());
						//mapRepAsig.put("origenDatos", origenDatos);

						t6621RespExpVirtDAO.actualizar(mapRepAsig);
						actResponsable = true;
						listaColaboradores.add(codColabNew);
					} else {
						listaColaboradores.add(codColab);
					}
				}

				if (actResponsable) {
					mapConsultaCorreo = new HashMap<String, Object>();
					listaCorreosBeans = new ArrayList<CorreosBean>();
					listaT02PerdpBeans = new ArrayList<T02PerdpBean>();

					for (String codColaborador : listaColaboradores) {
						correosBean = new CorreosBean();
						correosBean.setCodPers(codColaborador);
						listaCorreosBeans.add(correosBean);

						t02PerdpBean = new T02PerdpBean();
						t02PerdpBean.setCodPersona(codColaborador);
						listaT02PerdpBeans.add(t02PerdpBean);
					}

					mapPerdp = new HashMap<String, Object>();
					mapPerdp.put("listadoPersonas", listaT02PerdpBeans);

					listaT02PerdpBeans = t02PerdpDAO.listar(mapPerdp);
					if (listaT02PerdpBeans != null) {
						for (T02PerdpBean bean : listaT02PerdpBeans) {
							responsables += bean.getDesNombres().trim() + " " + bean.getDesApellidoPaterno().trim() + " " + bean.getDesApellidoMaterno().trim() + ", ";
						}

						if (responsables.length() > 0) {
							responsables = responsables.substring(0, responsables.length() - 2);
						}
					}

					mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);

					listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
					// Enviar correos a los responsables asignados al expediente virtual
					if (listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
						if (log.isDebugEnabled()) log.debug("Inicio Envio de correo - ResponsableServiceImpl.actualizarAsignacionResponsables");

						for (CorreosBean bean : listaCorreosBeans) {
							mapEnvioCorreo = new HashMap<String, Object>();
							mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());

							mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ACT_RESP_EXP_VIRT.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, parametros.get("numExpeDv").toString().trim());
							mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, responsables);

							mapEnvioCorreo.put("mensaje", mensaje);

							correosService.enviarCorreo(mapEnvioCorreo);
						}

						if (log.isDebugEnabled()) log.debug("Fin Envio de correo - ResponsableServiceImpl.actualizarAsignacionResponsables");
					}
				}
			}

			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
			mapResponse.put("numExpedv", ((String)parametros.get("numExpeDv")).trim());

			flagOrigen = (String) parametros.get("flagOrigen");
			if (flagOrigen.equals(ValidaConstantes.IND_INVOC_SERVICIO_AUTOMATICO)) {
				// Setear el map del request de seguimiento
				mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("numExpedo", parametros.get("numExpedo"));
				mapRequest.put("codColab", parametros.get("codColab"));
				mapRequest.put("codColabnew", parametros.get("codColabNew"));
				mapRequest.put("codDepnew", parametros.get("codDepNew"));				
				mapRequest.put("codUsumodresp", parametros.get("codUsuModResp"));
				
				// Crear el bean de seguimiento WS
				beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("num_expedv", parametros.get("numExpeDv"));				
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_REC_ACT_RESP_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv", parametros.get("codUsuModResp"));
				serializer = new JsonSerializer();
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				jsonResponse = (String) serializer.serialize(mapResponse);
				beanSeguiWS.put("des_response", jsonResponse);
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);

				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());

				//beanSeguiWS.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 

				seguiService.registrarSeguimiento(beanSeguiWS);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.actualizarAsignacionResponsables");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.actualizarAsignacionResponsables");

			NDC.pop();
			NDC.remove();
		}

		return mapResponse;
	}
	// Inicio [jjurado 22/06/2016 ] obtenemos el listado de unidades organizacionales
		
	public List<T1797DepenUOrgaBean> listarUnidadesOrganizacionales(Map<String, Object> parametros) throws Exception {
		log.debug("Inicio - ResponsableServiceImpl.listarUnidadesOrganizacionales");
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<T1797DepenUOrgaBean> listadoUnidOrgaDependencia;
		String codDependencia = "";
		String codTipoDependencia = "";
		
		
		try {
			codDependencia = parametros.get("codDependencia") == null ? null : parametros.get("codDependencia").toString();
			codTipoDependencia = parametros.get("codTipoDependencia") == null ? null : parametros.get("codTipoDependencia").toString();
			
			if (!Utils.isEmpty(codDependencia)) {
				//Inicio luis Estrada 10/11/2016
				params.put("codDependencia", codDependencia.substring(0, 3));
				//Fin luis Estrada 10/11/2016
			}

			if (!Utils.isEmpty(codTipoDependencia)) {

				params.put("codTipoDependencia", codTipoDependencia);

			}

			listadoUnidOrgaDependencia = t1797DepenUOrgaDAO.listar(params);
			
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.listarUnidadesOrganizacionales");
			log.error(ex, ex);
			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.listarUnidadesOrganizacionales");

			NDC.pop();
			NDC.remove();

		}
		return listadoUnidOrgaDependencia;
	}
	
	@Override
	public List<T6621RespExpVirtBean> listarRelacionResponsables(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.listarRelacionResponsables");

		List<T6621RespExpVirtBean> listadoResponsablesFinales = new ArrayList<T6621RespExpVirtBean>();
		List<T1797DepenUOrgaBean> listadoUnidOrgaDependencia = new ArrayList<T1797DepenUOrgaBean>(); 
		// Map<String, Object> params = new HashMap<String, Object>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ResponsableServiceImpl.listarRelacionResponsables");

			String indConsulta = parametros.get("indConsulta") == null ? null : parametros.get("indConsulta").toString();
			String numExpedienteVirtual = parametros.get("numExpedienteVirtual") == null ? null : parametros.get("numExpedienteVirtual").toString();
			String codDependencia = parametros.get("codDependencia") == null ? null : parametros.get("codDependencia").toString();
			String codTipoDependencia = parametros.get("codTipoDependencia") == null ? null : parametros.get("codTipoDependencia").toString();
			String indEliminado = parametros.get("indEliminado") == null ? null : parametros.get("indEliminado").toString();
			// Inicio [jjurado 22/06/2016] 
			if(Utils.equiv(indConsulta, ValidaConstantes.IND_RESPO_DISPONIBLE)){
				if(!Utils.isEmpty(parametros.get("listadoUnidOrgaDependencia"))){
					listadoUnidOrgaDependencia = (List<T1797DepenUOrgaBean>) parametros.get("listadoUnidOrgaDependencia");
				}else{
					log.debug("La dependencia "+codDependencia +" No cuenta con Unidades Organizacionales");
					return listadoResponsablesFinales;
				}
			}
			// Fin [jjurado 22/06/2016] 	
			if (indConsulta != null) {

				Map<String, Object> params = new HashMap<String, Object>();

				List<T6621RespExpVirtBean> listadoResponsablesTemporal = new ArrayList<T6621RespExpVirtBean>();

				if (numExpedienteVirtual != null) {

					params.put("numExpedienteVirtual", numExpedienteVirtual);

				}

				if (indEliminado != null) {

					params.put("indEliminado", indEliminado);

				}

				listadoResponsablesTemporal = t6621RespExpVirtDAO.listar(params);

				if (ValidaConstantes.IND_RESPO_DISPONIBLE.equals(indConsulta)) {

					params = new HashMap<String, Object>();

					if (codDependencia != null) {

						params.put("codDependencia", codDependencia);

					}

					if (codTipoDependencia != null) {

						params.put("codTipoDependencia", codTipoDependencia);

					}

					//List<T1797DepenUOrgaBean> listadoUnidOrgaDependencia = t1797DepenUOrgaDAO.listar(params);

				List<Map<String, Object>> listadoUnidadesOrganizacionales = new ArrayList<Map<String, Object>>();

					for (T1797DepenUOrgaBean depenUOrgaBean : listadoUnidOrgaDependencia) {

						Map<String, Object> unidadOrga = new HashMap<String, Object>();

						String codUnidadOrganizacional = depenUOrgaBean.getCodUnidadOrganizacional();

						unidadOrga.put("codUnidadOrganizacional", codUnidadOrganizacional);

						listadoUnidadesOrganizacionales.add(unidadOrga);

					}
					
					/*Map<String, Object> unidadOrga = new HashMap<String, Object>();
					unidadOrga.put("codUnidadOrganizacional", parametros.get("codUnidadOrganizacional"));
					listadoUnidadesOrganizacionales.add(unidadOrga);
					params = new HashMap<String, Object>();*/

					params.put("listadoUnidadesOrganizacionales", listadoUnidadesOrganizacionales);
					params.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);

					List<Map<String, Object>> listadoPersona = new ArrayList<Map<String, Object>>();

					for (T6621RespExpVirtBean responsableBean : listadoResponsablesTemporal) {

						Map<String, Object> persona = new HashMap<String, Object>();

						String codPersona = responsableBean.getCodColaborador();

						persona.put("codPersona", codPersona);

						listadoPersona.add(persona);

					}

					params.put("listadoPersonasExcluidas", listadoPersona);

					List<T02PerdpBean> listadoPersonas = t02PerdpDAO.listar(params);

					Integer numOrden = 0;

					for (T02PerdpBean persona : listadoPersonas) {

						numOrden++;

						T6621RespExpVirtBean responsable = new T6621RespExpVirtBean();

						persona = personaService.completarInformacionPersona(persona);
						String codColaborador = "";
						String desColaborador = "";
						
						if(persona != null) {
							codColaborador = persona.getCodPersona();
							desColaborador = persona.getDesNombreCompleto();
						}

						responsable.setNumOrden(numOrden);
						responsable.setIndSeleccion(false);
						responsable.setCodColaborador(codColaborador);
						responsable.setDesColaborador(desColaborador);

						listadoResponsablesFinales.add(responsable);

					}

				} else if (ValidaConstantes.IND_RESPO_ASOCIADO.equals(indConsulta)) {

					params = new HashMap<String, Object>();

					List<Map<String, Object>> listadoPersona = new ArrayList<Map<String, Object>>();

					for (T6621RespExpVirtBean responsableBean : listadoResponsablesTemporal) {

						Map<String, Object> persona = new HashMap<String, Object>();

						String codPersona = responsableBean.getCodColaborador();

						persona.put("codPersona", codPersona);

						listadoPersona.add(persona);

					}

					params.put("listadoPersonas", listadoPersona);

					List<T02PerdpBean> listadoPersonas = t02PerdpDAO.listar(params);

					Map<String, Object> mapaPersona = new HashMap<String, Object>();

					for (T02PerdpBean persona : listadoPersonas) {

						persona = personaService.completarInformacionPersona(persona);

						String codPersona = persona.getCodPersona();
						String desPersona = persona.getDesNombreCompleto();

						mapaPersona.put(codPersona, desPersona);

					}

					Integer numOrden = 0;

					for (T6621RespExpVirtBean responsable : listadoResponsablesTemporal) {

						numOrden++;

						String codColaborador = responsable.getCodColaborador().trim();
						String desColaborador = ValidaConstantes.SEPARADOR_ESPACIO;

						if (mapaPersona.get(codColaborador) != null) {

							desColaborador = mapaPersona.get(codColaborador).toString();

						}

						responsable.setNumOrden(numOrden);
						responsable.setIndSeleccion(false);
						responsable.setDesColaborador(desColaborador);

						listadoResponsablesFinales.add(responsable);

					}

				}

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.listarRelacionResponsables");
			log.error(ex, ex);
			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.listarRelacionResponsables");

			NDC.pop();
			NDC.remove();

		}

		return listadoResponsablesFinales;

	}

	@Override
	public List<T6621RespExpVirtBean> listarResponsables(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.listarResponsables");

		List<T6621RespExpVirtBean> listaResponsables = new ArrayList<T6621RespExpVirtBean>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ResponsableServiceImpl.listarRelacionResponsables");

			listaResponsables = t6621RespExpVirtDAO.listar(parametros);

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.listarResponsables");
			log.error(ex, ex);
			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.listarResponsables");

			NDC.pop();
			NDC.remove();

		}

		return listaResponsables;

	}

	public void realizarAsignacionResponsableExpediente(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.realizarAsignacionResponsableExpediente");
		
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ResponsableServiceImpl.realizarAsignacionResponsableExpediente");

			Boolean indUbica = null;

			List<Map<String, Object>> listadoNormal = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoInsertar = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoActivar = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoAcumulado = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoDesactivar = new ArrayList<Map<String, Object>>();

			Map<String, Object> params = new HashMap<String, Object>();

			String codUsuario = parametros.get("codUsuario").toString();
			String numExpedienteVirtual = parametros.get("numExpedienteVirtual").toString();

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (List<Map<String, Object>>) parametros.get("listadoNuevo");

			@SuppressWarnings("unchecked")
			List<T6621RespExpVirtBean> listadoActual = (List<T6621RespExpVirtBean>) parametros.get("listadoActual");

			@SuppressWarnings("unchecked")
			List<T6621RespExpVirtBean> listadoInactivo = (List<T6621RespExpVirtBean>) parametros.get("listadoInactivo");

			Date fecSistema = new Date();

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);

			Date fecVacia = null;

			fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);

			for (Map<String, Object> nuevo : listadoNuevo) {

				indUbica = false;

				String codColaboradorNuevo = nuevo.get("codColaborador").toString().trim();

				for (T6621RespExpVirtBean actual : listadoActual) {

					String codColaboradorActual = actual.getCodColaborador().trim();
					String indEliminado = actual.getIndEliminado();

					if (codColaboradorActual.equals(codColaboradorNuevo)) {

						if (ValidaConstantes.IND_REGI_NO_ELIMINADO.equals(indEliminado)) {

							listadoNormal.add(nuevo);

							indUbica = true;

							break;

						} else {

							listadoActivar.add(nuevo);

							indUbica = true;

							break;

						}

					}

				}

				for (T6621RespExpVirtBean inactivo : listadoInactivo) {

					String codCodColaboradorInactivo = inactivo.getCodColaborador().trim();

					if (codCodColaboradorInactivo.equals(codColaboradorNuevo)) {

						listadoActivar.add(nuevo);

						indUbica = true;

						break;

					}

				}

				if (!indUbica) {

					listadoInsertar.add(nuevo);

				}

			}

			listadoAcumulado.addAll(listadoNormal);
			listadoAcumulado.addAll(listadoActivar);

			for (T6621RespExpVirtBean actual : listadoActual) {

				indUbica = false;

				String codColaboradorActual = actual.getCodColaborador().trim();

				for (Map<String, Object> acumulado : listadoAcumulado) {

					String codColaboradorAcumulado = acumulado.get("codColaborador").toString().trim();

					if (codColaboradorActual.equals(codColaboradorAcumulado)) {

						indUbica = true;

						break;

					}

				}

				if (!indUbica) {

					Map<String, Object> desactiva = new HashMap<String, Object>();

					desactiva.put("codColaborador", codColaboradorActual);

					if (parametros.get("isWS") == null) listadoDesactivar.add(desactiva); //cuando viene del WS

				}

			}

			Map<String, Object> mapa = new HashMap<String, Object>();

			/* Captura y seteo del Pool - Inicio */

			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml

			if (listadoInsertar.size() > 0) {

				for (Map<String, Object> inserta : listadoInsertar) {

					String codColaborador = inserta.get("codColaborador").toString();
					String indResponsable = inserta.get("indResponsable")==null? "2" : inserta.get("indResponsable").toString(); //eaguilar: viene null desde el WS

					Long numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_RESPONS);

					params.put("numCorrelativo", numCorrelativo);
					params.put("codColaborador", codColaborador);
					params.put("numExpedienteVirtual", numExpedienteVirtual);
					params.put("indResponsable", indResponsable);
					params.put("codUsuarioRegistro", codUsuario);
					params.put("fecRegistro", fecSistema);
					params.put("codUsuarioModificacion", ValidaConstantes.COD_USUARIO_VACIO);
					params.put("fecModificacion", fecVacia);
					params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					//params.put("origenDatos", origenDatos);

					t6621RespExpVirtDAO.insertar(params);

				}

			}

			if (listadoActivar.size() > 0) {

				params.put("numExpedienteVirtual", numExpedienteVirtual);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);

				params.put("listadoResponsables", listadoActivar);

				//params.put("origenDatos", origenDatos);

				t6621RespExpVirtDAO.actualizarMasivo(params);

			}

			if (listadoDesactivar.size() > 0) {

				params.put("numExpedienteVirtual", numExpedienteVirtual);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);

				params.put("listadoResponsables", listadoDesactivar);

				//params.put("origenDatos", origenDatos);

				t6621RespExpVirtDAO.actualizarMasivo(params);

			}

			//21 JUNIO: envia correo cuando NO ES servicio O cuando ES servicio y ademas el flag de envio es 1
 			if(parametros.get("isWS") == null) enviarCorreoAResponsablesAsignados(parametros);
			if(parametros.get("isWS") != null && parametros.get("indEmiAlerta") != null){
				if(parametros.get("indEmiAlerta").toString().equals("1")) enviarCorreoAResponsablesAsignados(parametros);
			}
			// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
			if(parametros.get("isWS") == null) registrarSeguimientoAsignacionResponsable(numExpedienteVirtual);
			//eaguilar 23 JUN: registra la invocacion al servicio (ws 15):
			else{
				JsonSerializer serializer = new JsonSerializer();
				Calendar fechaActual = Calendar.getInstance();
				Calendar fechaVacia = Calendar.getInstance();
				fechaVacia.set(1, 0, 1, 0, 0, 0);
				Map<String, Object> mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("tipoLista", parametros.get("tipoLista"));
				mapRequest.put("codUsuario", parametros.get("codUsuario"));
				mapRequest.put("listaResponsables", parametros.get("listaStrResponsablesAsignar"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));
				if(parametros.get("listaRuc") == null) mapRequest.put("listaNumExpedo", parametros.get("listaNumExpedo"));
				else mapRequest.put("listaRuc", parametros.get("listaRuc"));
				Map<String, Object> mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
				
				//registro del seguimiento:
				Map<String, Object> beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", numExpedienteVirtual);
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_ASIGNAR_RESP);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv",  parametros.get("codUsuario")); // eaguilar 28 JUN se cambio de codUsuregis a codUsuario
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);				
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				
				seguiService.registrarSeguimiento(beanSeguiWS);
			}
			// Fin [jquispe 27/05/2016]

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.realizarAsignacionResponsableExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.realizarAsignacionResponsableExpediente");

			NDC.pop();
			NDC.remove();

		}

	}
	
	// Inicio [jquispe 27/05/2016] Registra el seguimiento de la accion realizada.
	private void registrarSeguimientoAsignacionResponsable(String numeroExpedienteVirtual) throws Exception{
        Map<String, Object> beanSegui = new HashMap<String, Object>();
		
		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();
		
		// Fecha fin
		Calendar fechaVacia =  Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);
		
		//Mapa de descripciones de acciones
		Map<String, Object> mapa = new HashMap<String,Object>();		
		mapa.put("codClase", CatalogoConstantes.CATA_ACCIONES_SISTEMA_INTRANET);
		mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);		
		Map<String, Object> mapaAccionesSistemaIntranet = catalogoService.obtenerCatalogo(mapa);     					
		
		beanSegui.put("num_expedv", numeroExpedienteVirtual);
		beanSegui.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_CI);
		beanSegui.put("fec_seguim", fechaActual.getTime());
		beanSegui.put("fec_invserv", fechaVacia.getTime());
		beanSegui.put("cod_servinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_retservinv", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_usuinvserv",  ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_request", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("des_response", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_opccons", ValidaConstantes.OPCION_INTRANET_ASIGNACION_RESPONSABLE);
		beanSegui.put("cod_accion", ValidaConstantes.ACCION_INTRANET_ASIGNAR_RESPONSABLE);
		beanSegui.put("des_datcons", Utils.toStr(mapaAccionesSistemaIntranet.get(ValidaConstantes.ACCION_INTRANET_ASIGNAR_RESPONSABLE)));
		beanSegui.put("fec_cons", fechaActual.getTime());
		beanSegui.put("cod_respacc", ValidaConstantes.RESPUESTA_ACCION_OK);
		beanSegui.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("num_doc", ValidaConstantes.SEPARADOR_GUION);					
		beanSegui.put("fec_cambest", fechaVacia.getTime());
		beanSegui.put("fec_cambeta", fechaVacia.getTime());
		beanSegui.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
		beanSegui.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);		
		
		seguiService.registrarSeguimiento(beanSegui);
	}
	// Fin [jquispe 27/05/2016]

	private void enviarCorreoAResponsablesAsignados(Map<String, Object> parametros) throws Exception {
		String codColaborador = null;
		String mensaje = null;
		String responsables = "";
		Map<String, Object> mapConsultaCorreo = null;
		Map<String, Object> mapEnvioCorreo = null;
		Map<String, Object> mapPerdp = null;
		List<CorreosBean> listaCorreosBeans = new ArrayList<CorreosBean>();
		List<T02PerdpBean> listaT02PerdpBeans = new ArrayList<T02PerdpBean>();
		CorreosBean correosBean = null;
		T02PerdpBean t02PerdpBean = null;
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listadoNuevo = (List<Map<String, Object>>)parametros.get("listadoNuevo");
		for (Map<String, Object> mapResp : listadoNuevo) {
			codColaborador = mapResp.get("codColaborador").toString();
			correosBean = new CorreosBean();
			correosBean.setCodPers(codColaborador);
			listaCorreosBeans.add(correosBean);

			t02PerdpBean = new T02PerdpBean();
			t02PerdpBean.setCodPersona(codColaborador);
			listaT02PerdpBeans.add(t02PerdpBean);
		}

		mapPerdp = new HashMap<String, Object>();
		mapPerdp.put("listadoPersonas", listaT02PerdpBeans);

		listaT02PerdpBeans = t02PerdpDAO.listar(mapPerdp);
		if (listaT02PerdpBeans != null) {
			for (T02PerdpBean bean : listaT02PerdpBeans) {
				responsables += bean.getDesNombres().trim() + " " + bean.getDesApellidoPaterno().trim() + " " + bean.getDesApellidoMaterno().trim() + ", ";
			}

			if (responsables.length() > 0) {
				responsables = responsables.substring(0, responsables.length() - 2);
			}
		}
		mapConsultaCorreo = new HashMap<String, Object>();
		mapConsultaCorreo.put("listaCodPers", listaCorreosBeans);

		listaCorreosBeans = correosService.listarCorreo(mapConsultaCorreo);
		// Enviar correos a los responsables asignados al expediente virtual
		if (listaCorreosBeans != null && listaCorreosBeans.size() > 0) {
			if (log.isDebugEnabled()) log.debug("Inicio Envio de correo - ResponsableServiceImpl.enviarCorreoAResponsablesAsignados");

			for (CorreosBean bean : listaCorreosBeans) {
				mapEnvioCorreo = new HashMap<String, Object>();
				mapEnvioCorreo.put("destinatario", bean.getSmtp().trim());

				mensaje = MensajeAlertaConstantes.MSJ_ALERTA_ACT_RESP_EXP_VIRT.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, (String) Utils.toStr(parametros.get("numExpedienteVirtual")).trim());
				mensaje = mensaje.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_1, responsables);

				mapEnvioCorreo.put("mensaje", mensaje);

				correosService.enviarCorreo(mapEnvioCorreo);
			}

			if (log.isDebugEnabled()) log.debug("Fin Envio de correo - ResponsableServiceImpl.enviarCorreoAResponsablesAsignados");
		}
	}
	
	//Inicio - [oachahuic][PAS20165E210400270]
	@Override
	public T6621RespExpVirtBean obtenerResponsable(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicial - ResponsableServiceImpl.obtenerResponsable");
		T6621RespExpVirtBean t6621Bean = null;
		try {
			DataSourceContextHolder.setKeyDataSource("dcbdexpvir");
			t6621Bean = t6621RespExpVirtDAO.obtenerDatosResponsable(parametros);
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.obtenerResponsable");
			log.error(ex, ex);
			throw ex;

		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.obtenerResponsable");
			NDC.pop();
			NDC.remove();
		}
		return t6621Bean;
	}
	//Fin - [oachahuic][PAS20165E210400270]

	@Override
	public List<String> obtenerCodResponsablesAsignados(String numExpedVirtual) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicial - ResponsableServiceImpl.obtenerResponsable");
		List<String> listCodRespAsig = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("numExpeDv", numExpedVirtual);
			params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			DataSourceContextHolder.setKeyDataSource("dcbdexpvir");
			listCodRespAsig = t6621RespExpVirtDAO.listarCodigosResponsables(params);
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.obtenerResponsable");
			log.error(ex, ex);
			throw ex;

		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.obtenerResponsable");
			NDC.pop();
			NDC.remove();
		}
		return listCodRespAsig;
	}
    
    //PAS20191U210500144 Inicio 400101 RF28 PSALDARRIAGA
	@SuppressWarnings({ "unchecked"})
	public void realizarReasignacionResponsableExpediente(Map<String, Object> parametros) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.realizarReasignacionResponsableExpediente");
		
		Map<String, Object> mapaPool = null;
        Map<String, Object> desactiva = new HashMap<String, Object>();
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ResponsableServiceImpl.realizarReasignacionResponsableExpediente");
			
			// Variables
			String codUsuario = null;
			String numExpedienteVirtual = null;
			Boolean indUbica = null;
			
			List<Map<String, Object>> listadoNuevo = new ArrayList<Map<String, Object>>();
			List<T6621RespExpVirtBean> listadoActual = new ArrayList<T6621RespExpVirtBean>();
			//JBC - Inicio
			String indResponsable = null;
			Boolean bResponsable = false;
			int contador = 0;
            String codCol = null;
			//JBC - Fin
												
			// Fechas
			Date fecSistema = new Date();
			Date fecVacia = null;
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);
			fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);
			
			// Obtener parametros
			codUsuario = parametros.get("codUsuario").toString();
			numExpedienteVirtual = parametros.get("numExpedienteVirtual").toString();
			
			listadoNuevo = (List<Map<String, Object>>) parametros.get("listadoNuevo");
			listadoActual = (List<T6621RespExpVirtBean>) parametros.get("listadoActual");
												
			// Listas
			List<Map<String, Object>> listadoNormal = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoInsertar = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoActivar = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoAcumulado = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoDesactivar = new ArrayList<Map<String, Object>>();
						
			// Params
			Map<String, Object> params = new HashMap<String, Object>();
			
			//Listado Nuevo comparado con Listado Actual
			for (Map<String, Object> nuevo : listadoNuevo) {
				
				indUbica = false;
				String codColaboradorNuevo = null;
				
				//Colaborador nuevo
				codColaboradorNuevo = nuevo.get("codColaborador").toString().trim();
				
				//Listado Actual
				for (T6621RespExpVirtBean actual : listadoActual) {
					
					String codColaboradorActual = null;
					String indEliminado = null;
					
					codColaboradorActual = actual.getCodColaborador().trim();
					indEliminado = actual.getIndEliminado().trim();
					
					if (codColaboradorActual.equals(codColaboradorNuevo)) {
						
                        nuevo.put("ind_resp",actual.getIndResponsable());
						if (ValidaConstantes.IND_REGI_NO_ELIMINADO.equals(indEliminado)) {
							
							listadoNormal.add(nuevo);
							indUbica = true;
							break;			
							
						} else {
							
							listadoActivar.add(nuevo);
							indUbica = true;
							break;
							
						}
					}
				}
		
				if (!indUbica) {
					
					listadoInsertar.add(nuevo);				
					
				}
			}
			
			// Agregar a Lista acumulado (lista normal + lista activar)
			listadoAcumulado.addAll(listadoNormal);
			listadoAcumulado.addAll(listadoActivar);
			
			// Listado Actual comparado con Listado Acumulado
			for (T6621RespExpVirtBean actual : listadoActual) {
				
				indUbica = false;
				String codColaboradorActual = null;
				//String indResponsable = null;
								
				codColaboradorActual = actual.getCodColaborador().trim();
				//indResponsable = actual.getIndResponsable().trim();				
								
				// Listado Acumulado
				for (Map<String, Object> acumulado : listadoAcumulado) {
					
					String codColaboradorAcumulado = null;				
					
					codColaboradorAcumulado = acumulado.get("codColaborador").toString().trim();
					
					if (codColaboradorActual.equals(codColaboradorAcumulado)) {
					
						indUbica = true;
						break;
					
					} 
				}

				if (!indUbica) {
				
					desactiva = new HashMap<String, Object>();
					desactiva.put("codColaborador", codColaboradorActual);
					listadoDesactivar.add(desactiva);
					
				} 
				
				//JBC - Inicio
				/*if (indResponsable.equals(ValidaConstantes.IND_RESP_PRINCIPAL)){
					
					Map<String, Object> activa = new HashMap<String, Object>();
					activa.put("codColaborador", codColaboradorActual);
					listadoActivar.add(activa);					
				 
				}	*/		
				//JBC - Fin
			}
			
			//  Realizar reasignacion			
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			// Captura y seteo del pool - Inicio
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			
			
			// Listado Desactivar
			if (listadoDesactivar.size() > 0) {
				
				params.put("numExpedienteVirtual", numExpedienteVirtual);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);
				params.put("listadoResponsables", listadoDesactivar);

				t6621RespExpVirtDAO.actualizarMasivo(params);
				
			}
			//JBC - Inicio
			for (Map<String, Object> activo : listadoActivar) {
				indResponsable = activo.get("ind_resp").toString();		
				if ( ValidaConstantes.IND_RESP_PRINCIPAL.equals(indResponsable)){
					bResponsable = true;
					break;
				}
			}
            
            if(!bResponsable){				
				for (Map<String, Object> normal : listadoNormal) {
					indResponsable = normal.get("ind_resp").toString();		
					if ( ValidaConstantes.IND_RESP_PRINCIPAL.equals(indResponsable)){
						bResponsable = true;
						break;
					}
				}				
			}
			//JBC - Fin
			
			// Listado Activar
			if (listadoActivar.size() > 0) {
				
				
				params.put("numExpedienteVirtual", numExpedienteVirtual);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				params.put("listadoResponsables", listadoActivar);
                params.put("indResponsable", ValidaConstantes.IND_RESP_ASOCIADO);
				t6621RespExpVirtDAO.actualizarMasivo(params);
				
			}
			
			
			// Listado Insertar
			if (listadoInsertar.size() > 0) {
				
				for (Map<String, Object> inserta : listadoInsertar) {
					//JBC - Inicio
					if (contador == 0 &&  !bResponsable){
						indResponsable = ValidaConstantes.IND_RESP_PRINCIPAL;
						contador = contador + 1;
					}else{
						indResponsable = ValidaConstantes.IND_RESP_ASOCIADO;
					}
					//JBC - Fin
					
					String codColaborador = inserta.get("codColaborador").toString();
					//indResponsable = inserta.get("indResponsable")==null? "2" : inserta.get("indResponsable").toString();
					Long numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_RESPONS);

					params.put("numCorrelativo", numCorrelativo);
					params.put("codColaborador", codColaborador);
					params.put("numExpedienteVirtual", numExpedienteVirtual);
					params.put("indResponsable", indResponsable);
					params.put("codUsuarioRegistro", codUsuario);
					params.put("fecRegistro", fecSistema);
					params.put("codUsuarioModificacion", ValidaConstantes.COD_USUARIO_VACIO);
					params.put("fecModificacion", fecVacia);
					params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					
					t6621RespExpVirtDAO.insertar(params);
					
				}				
			}
            
            parametros.put("indEliminado",ValidaConstantes.IND_REGI_NO_ELIMINADO);
			List<T6621RespExpVirtBean> listaRespActuales = listarResponsables(parametros);
			bResponsable = false;
			contador = 0;
			
			for(T6621RespExpVirtBean respAct:listaRespActuales){
				if(ValidaConstantes.IND_RESPO_ASOCIADO.equals(respAct.getIndResponsable())){
					if(contador == 0){
						codCol= respAct.getCodColaborador();
						contador ++;
					}					
				}else{
					bResponsable = true;
					break;
				}
				
			}
			
			
			if(!bResponsable){
				listadoDesactivar = new ArrayList<Map<String,Object>>();
				desactiva = new HashMap<String, Object>();
				desactiva.put("codColaborador", codCol);
				listadoDesactivar.add(desactiva);
				params = new HashMap<String, Object>();
				params.put("listadoResponsables", listadoDesactivar);
				params.put("numExpedienteVirtual", numExpedienteVirtual);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indResponsable", ValidaConstantes.IND_RESP_PRINCIPAL);				
				t6621RespExpVirtDAO.actualizarMasivo(params);
			}			
			
			// Enviar correos
 			if(parametros.get("isWS") == null) enviarCorreoAResponsablesAsignados(parametros);
			if(parametros.get("isWS") != null && parametros.get("indEmiAlerta") != null){
				if(parametros.get("indEmiAlerta").toString().equals("1")) enviarCorreoAResponsablesAsignados(parametros); 
			}
			
			// Registrar Seguimiento
			if(parametros.get("isWS") == null) {
				
				registrarSeguimientoAsignacionResponsable(numExpedienteVirtual);
				
			}			
			
			// Registra la invocacion al servicio (ws 15):
			else{
								
				JsonSerializer serializer = new JsonSerializer();
				Calendar fechaActual = Calendar.getInstance();
				Calendar fechaVacia = Calendar.getInstance();
				fechaVacia.set(1, 0, 1, 0, 0, 0);
				Map<String, Object> mapRequest = new LinkedHashMap<String, Object>();
				mapRequest.put("tipoLista", parametros.get("tipoLista"));
				mapRequest.put("codUsuario", parametros.get("codUsuario"));
				mapRequest.put("listaResponsables", parametros.get("listaStrResponsablesAsignar"));
				mapRequest.put("indEmiAlerta", parametros.get("indEmiAlerta"));
				if(parametros.get("listaRuc") == null) mapRequest.put("listaNumExpedo", parametros.get("listaNumExpedo"));
				Map<String, Object> mapResponse = new LinkedHashMap<String, Object>();
				mapResponse.put("cod", AvisoConstantes.COD_HTTP_STATUS_200);
				
				//registro del seguimiento:
				Map<String, Object> beanSeguiWS = new HashMap<String, Object>();
				beanSeguiWS.put("num_expedv", numExpedienteVirtual);
				beanSeguiWS.put("cod_tipseguim", ValidaConstantes.IND_TIP_SEG_WS);
				beanSeguiWS.put("fec_seguim", fechaActual.getTime());
				beanSeguiWS.put("fec_invserv", fechaActual.getTime());
				beanSeguiWS.put("cod_servinv", ValidaConstantes.IND_CODSERV_REC_ACT_RESP_EXP_VIRT);
				beanSeguiWS.put("cod_retservinv", AvisoConstantes.COD_HTTP_STATUS_200);
				beanSeguiWS.put("cod_usuinvserv",  parametros.get("codUsuario")); 
				beanSeguiWS.put("des_request", (String) serializer.serialize(mapRequest));
				beanSeguiWS.put("des_response", (String) serializer.serialize(mapResponse));
				beanSeguiWS.put("num_ruc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_opccons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_accion", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("des_datcons", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("fec_cons", fechaVacia.getTime());
				beanSeguiWS.put("cod_respacc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_tipdoc", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("num_doc", ValidaConstantes.SEPARADOR_GUION);				
				beanSeguiWS.put("cod_estexpori", ValidaConstantes.SEPARADOR_GUION);
				beanSeguiWS.put("cod_etaexpori", ValidaConstantes.SEPARADOR_GUION);				
				beanSeguiWS.put("fec_cambest", fechaVacia.getTime());
				beanSeguiWS.put("fec_cambeta", fechaVacia.getTime());
				
				seguiService.registrarSeguimiento(beanSeguiWS);
				
			}		
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.realizarReasignacionResponsableExpediente");
			log.error(ex, ex);
			throw ex;
			
		}finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.realizarReasignacionResponsableExpediente");
			
		}
	}
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6621RespExpVirtBean> listarResponsablesDependencia(Map<String, Object> mapParamBusq) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ResponsableServiceImpl.listarResponsablesDependencia");

		List<T6621RespExpVirtBean> listarResponsablesDependencia = new ArrayList<T6621RespExpVirtBean>();
		
		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ResponsableServiceImpl.listarResponsablesDependencia");

			List<T02PerdpBean> listadoPersonas = t02PerdpDAO.listarResponsablesDependencia(mapParamBusq);
			
			if (listadoPersonas != null) {
				
				//Obtener lista inicial
				List<String> codColaborador = new ArrayList<String>();					
				codColaborador = (List<String>) mapParamBusq.get("aryRespDep");
								
				ArrayList<String> aryRespIni = new ArrayList<String>();
				
				for(String numResponsableIni : codColaborador){
					
					aryRespIni.add(numResponsableIni);
				}						
				
				// Compararar lista con dependencia y lista inicial	
				for (T02PerdpBean colaboradorDep : listadoPersonas) {
					
					String codColabDependencia = colaboradorDep.getCodPersona();
					
					for(String codRespAsignar : aryRespIni){
				
						if(codColabDependencia.equals(codRespAsignar)){
								
							T6621RespExpVirtBean codResponsableReasignar = new T6621RespExpVirtBean();
							codResponsableReasignar.setCodColaborador(codColabDependencia);							
							listarResponsablesDependencia.add(codResponsableReasignar);
				
						}
					}
				}				
			}
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ResponsableServiceImpl.listarResponsablesDependencia");
			log.error(ex, ex);
			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ResponsableServiceImpl.listarResponsablesDependencia");

			NDC.pop();
			NDC.remove();

		}

		return listarResponsablesDependencia;
		
	}
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA

	
	/* Seteo - Spring - Inicio */
	public void setT6621RespExpVirtDAO(T6621RespExpVirtDAO t6621RespExpVirtDAO) {
		this.t6621RespExpVirtDAO = t6621RespExpVirtDAO;
	}

	public void setT02PerdpDAO(T02PerdpDAO t02PerdpDAO) {
		this.t02PerdpDAO = t02PerdpDAO;
	}

	public void setT1797DepenUOrgaDAO(T1797DepenUOrgaDAO t1797DepenUOrgaDAO) {
		this.t1797DepenUOrgaDAO = t1797DepenUOrgaDAO;
	}

	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setSeguiService(SeguimientoService seguiService) {
		this.seguiService = seguiService;
	}

	public void setCorreosService(CorreosService correosService) {
		this.correosService = correosService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	// Inicio [jquispe 27/05/2016] 
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}	
	// Fin [jquispe 27/05/2016]

	/* Seteo - Spring - Final */

}