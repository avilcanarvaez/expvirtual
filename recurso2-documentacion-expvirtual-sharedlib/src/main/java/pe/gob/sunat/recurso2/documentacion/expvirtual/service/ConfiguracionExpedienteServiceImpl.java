package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocNotSineBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.Ccs19DAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpCoaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpFiscaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ResCoaDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6623TipDocExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6624TipExpProcDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class ConfiguracionExpedienteServiceImpl implements ConfiguracionExpedienteService {

	private static final Log log = LogFactory.getLog(ConfiguracionExpedienteServiceImpl.class);

	private GeneralService generalService;
	private CatalogoService catalogoService;

	private T01ParamDAO t01ParamDAO;
	private T6624TipExpProcDAO t6624TipExpProcDAO;
	private T6623TipDocExpDAO t6623TipDocExpDAO;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private ResCoaDAO resCoaDAO;
	private ExpCoaDAO expCoaDAO;
	private Ccs19DAO ccs19DAO;
	private ExpFiscaDAO expFiscaDAO;
	private PersonaService personaService;//[PAS20191U210500076][OAC]
	private DocumentoExpedienteService documentoExpedienteService;//[PAS20191U210500076][OAC]

	@Override
	public List<T01ParamBean> listarProcesos() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarProcesos");

		List<T01ParamBean> listadoProcesosBean = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoProcesosResult = new ArrayList<T01ParamBean>();
		T01ParamBean procesoBeanResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarProcesos");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoProcesosBean = t01ParamDAO.listar(parametros);

			for (T01ParamBean procesoBean : listadoProcesosBean) {

				String codClase = procesoBean.getCodClase().trim();
				String indTipo = procesoBean.getIndTipo().trim();
				String codParametro = procesoBean.getCodParametro().trim();
				String desParametro = procesoBean.getDesParametro().trim();
				
				procesoBeanResult = new T01ParamBean();
				
				procesoBeanResult.setCodClase(codClase);
				procesoBeanResult.setIndTipo(indTipo);
				procesoBeanResult.setCodParametro(codParametro);
				procesoBeanResult.setDesParametro(desParametro);
				
				listadoProcesosResult.add(procesoBeanResult);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarProcesos");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarProcesos");

			NDC.pop();
			NDC.remove();

		}

		return listadoProcesosResult;

	}

	@Override
	public List<T6624TipExpProcBean> listarParametrosTiposExpendiente(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

		List<T6624TipExpProcBean> listadoTiposExpedientesFinales = new ArrayList<T6624TipExpProcBean>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

			String indConsulta = parametros.get("indConsulta") == null ? null : parametros.get("indConsulta").toString();
			String codProceso = parametros.get("codProceso") == null ? null : parametros.get("codProceso").toString();
			String indEliminado = parametros.get("indEliminado") == null ? null : parametros.get("indEliminado").toString();

			if (indConsulta != null) {

				Map<String, Object> params = new HashMap<String, Object>();

				List<T6624TipExpProcBean> listadoTiposExpendientesTemporal = new ArrayList<T6624TipExpProcBean>();

				if (codProceso != null) {

					params.put("codProceso", codProceso);

				}

				if (indEliminado != null) {

					params.put("indEliminado", indEliminado);

				}

				listadoTiposExpendientesTemporal = t6624TipExpProcDAO.listar(params);

				if (ValidaConstantes.IND_TIPO_EXPE_DISPONIBLE.equals(indConsulta)) {

					List<Map<String, Object>> listadoParametros = new ArrayList<Map<String, Object>>();

					for (T6624TipExpProcBean tipoExpedienteBean : listadoTiposExpendientesTemporal) {

						Map<String, Object> parametro = new HashMap<String, Object>();

						String codParametro = tipoExpedienteBean.getCodTipoExpediente().trim();

						parametro.put("codParametro", codParametro);

						listadoParametros.add(parametro);

					}

					List<T01ParamBean> listadoParametrosTiposExpendientes = new ArrayList<T01ParamBean>();

					params.put("listadoParametrosExcluidos", listadoParametros);

					params.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
					params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					//Inicio: jtejada [12-10-2016]
					//Para carga de parámetros, siempre debe ir a dcrecauda.
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
					//Fin: jtejada [12-10-2016]
					listadoParametrosTiposExpendientes = t01ParamDAO.listar(params);

					Integer numOrden = 0;

					for (T01ParamBean tipoExpedienteBean : listadoParametrosTiposExpendientes) {

						numOrden++;

						T6624TipExpProcBean tipoExpediente = new T6624TipExpProcBean();

						String codTipoExpediente = tipoExpedienteBean.getCodParametro().trim();
						String desTipoExpediente = tipoExpedienteBean.getDesParametro().trim();

						tipoExpediente.setNumOrden(numOrden);
						tipoExpediente.setIndSeleccion(false);
						tipoExpediente.setCodTipoExpediente(codTipoExpediente);
						tipoExpediente.setDesTipoExpediente(desTipoExpediente);

						listadoTiposExpedientesFinales.add(tipoExpediente);

					}

				} else if (ValidaConstantes.IND_TIPO_EXPE_ASOCIADO.equals(indConsulta)) {

					Map<String, Object> mapa = new HashMap<String, Object>();

					mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
					mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

					Integer numOrden = 0;

					for (T6624TipExpProcBean tipoExpedienteBean : listadoTiposExpendientesTemporal) {

						numOrden++;

						T6624TipExpProcBean tipoExpediente = new T6624TipExpProcBean();

						String codTipoExpediente = tipoExpedienteBean.getCodTipoExpediente().trim();
						String desTipoExpediente = mapaTiposExpedientes.get(codTipoExpediente) == null ? ValidaConstantes.CADENA_VACIA : mapaTiposExpedientes.get(codTipoExpediente).toString().trim();

						tipoExpediente.setNumOrden(numOrden);
						tipoExpediente.setIndSeleccion(false);
						tipoExpediente.setCodTipoExpediente(codTipoExpediente);
						tipoExpediente.setDesTipoExpediente(desTipoExpediente);

						listadoTiposExpedientesFinales.add(tipoExpediente);

					}

				}

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

			NDC.pop();
			NDC.remove();

		}

		return listadoTiposExpedientesFinales;

	}

	@Override
	public List<T6624TipExpProcBean> listarTiposExpendiente(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarTiposExpendiente");

		List<T6624TipExpProcBean> listadoTiposExpendientes = new ArrayList<T6624TipExpProcBean>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarTiposExpendiente");

			String codProceso = parametros.get("codProceso") == null ? null : parametros.get("codProceso").toString();
			String indEliminado = parametros.get("indEliminado") == null ? null : parametros.get("indEliminado").toString();

			Map<String, Object> mapa = new HashMap<String, Object>();

			mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("codProceso", codProceso);
			params.put("indEliminado", indEliminado);

			listadoTiposExpendientes = t6624TipExpProcDAO.listar(params);

			for (T6624TipExpProcBean tipoExpedienteBean : listadoTiposExpendientes) {

				String desTipoExpediente = ValidaConstantes.CADENA_VACIA;

				String codTipoExpediente = tipoExpedienteBean.getCodTipoExpediente().trim();

				if (mapaTiposExpedientes.get(codTipoExpediente) != null) {

					desTipoExpediente = mapaTiposExpedientes.get(codTipoExpediente).toString();

				}

				tipoExpedienteBean.setDesTipoExpediente(desTipoExpediente);

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarTiposExpendiente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarTiposExpendiente");

			NDC.pop();
			NDC.remove();

		}

		return listadoTiposExpendientes;

	}

	@Override
	public void realizarAsociacionTipoExpedienteProceso(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

			Boolean indUbica = null;

			List<Map<String, Object>> listadoNormal = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoInsertar = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoActivar = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoAcumulado = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> listadoDesactivar = new ArrayList<Map<String, Object>>();

			Map<String, Object> params = new HashMap<String, Object>();

			String codUsuario = parametros.get("codUsuario").toString();
			String codProceso = parametros.get("codProceso").toString();

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (List<Map<String, Object>>) parametros.get("listadoNuevo");

			@SuppressWarnings("unchecked")
			List<T6624TipExpProcBean> listadoActual = (List<T6624TipExpProcBean>) parametros.get("listadoActual");

			@SuppressWarnings("unchecked")
			List<T6624TipExpProcBean> listadoInactivo = (List<T6624TipExpProcBean>) parametros.get("listadoInactivo");

			Date fecSistema = new Date();

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);

			Date fecVacia = null;

			fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);

			for (Map<String, Object> nuevo : listadoNuevo) {

				indUbica = false;

				String codTipoExpedienteNuevo = nuevo.get("codTipoExpediente").toString();

				for (T6624TipExpProcBean actual : listadoActual) {

					String codTipoExpedienteActual = actual.getCodTipoExpediente().trim();
					String indEliminado = actual.getIndEliminado();

					if (codTipoExpedienteActual.equals(codTipoExpedienteNuevo)) {

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

				for (T6624TipExpProcBean inactivo : listadoInactivo) {

					String codTipoExpedienteInactivo = inactivo.getCodTipoExpediente().trim();

					if (codTipoExpedienteInactivo.equals(codTipoExpedienteNuevo)) {

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

			for (T6624TipExpProcBean actual : listadoActual) {

				indUbica = false;

				String codTipoExpedienteActual = actual.getCodTipoExpediente().trim();

				for (Map<String, Object> acumulado : listadoAcumulado) {

					String codTipoExpedienteAcumulado = acumulado.get("codTipoExpediente").toString();

					if (codTipoExpedienteActual.equals(codTipoExpedienteAcumulado)) {

						indUbica = true;

						break;

					}

				}

				if (!indUbica) {

					Map<String, Object> desactiva = new HashMap<String, Object>();

					desactiva.put("codTipoExpediente", codTipoExpedienteActual);

					listadoDesactivar.add(desactiva);

				}

			}

			Map<String, Object> mapa = new HashMap<String, Object>();

			/* Captura y seteo del Pool - Inicio */

			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);

			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);
			Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());


			if (listadoInsertar.size() > 0) {

				for (Map<String, Object> inserta : listadoInsertar) {

					String codTipoExpediente = inserta.get("codTipoExpediente").toString();

					params.put("codTipoExpediente", codTipoExpediente);
					params.put("codProceso", codProceso);
					params.put("codUsuarioRegistro", codUsuario);
					params.put("fecRegistro", fecSistema);
					params.put("codUsuarioModificacion", ValidaConstantes.COD_USUARIO_VACIO);
					params.put("fecModificacion", fecVacia);
					params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					//params.put("origenDatos", origenDatos);

					t6624TipExpProcDAO.insertar(params);

				}

			}

			if (listadoActivar.size() > 0) {

				params.put("codProceso", codProceso);
				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);

				params.put("listadoTiposExpedientes", listadoActivar);

				//params.put("origenDatos", origenDatos);

				t6624TipExpProcDAO.actualizarMasivo(params);

			}

			if (listadoDesactivar.size() > 0) {

				params.put("codUsuarioModificacion", codUsuario);
				params.put("fecModificacion", fecSistema);
				params.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);

				params.put("listadoTiposExpedientes", listadoDesactivar);

				//params.put("origenDatos", origenDatos);

				t6624TipExpProcDAO.actualizarMasivo(params);

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

			NDC.pop();
			NDC.remove();

		}

	}

	@Override
	public List<T6623TipDocExpBean> listarParametrosTiposDocumento(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

		List<T6623TipDocExpBean> listadoTiposDocumentosFinales = new ArrayList<T6623TipDocExpBean>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarParametrosTiposExpendiente");

			String indConsulta = parametros.get("indConsulta") == null ? null : parametros.get("indConsulta").toString();
			String codTipoExpediente = parametros.get("codTipoExpediente") == null ? null : parametros.get("codTipoExpediente").toString();
			String indEliminado = parametros.get("indEliminado") == null ? null : parametros.get("indEliminado").toString();

			if (indConsulta != null) {

				Map<String, Object> params = new HashMap<String, Object>();

				List<T6623TipDocExpBean> listadoTiposDocumentosTemporal = new ArrayList<T6623TipDocExpBean>();

				if (codTipoExpediente != null) {

					params.put("codTipoExpediente", codTipoExpediente);

				}

				if (indEliminado != null) {

					params.put("indEliminado", indEliminado);

				}
				params.put("listIndTipDoc", parametros.get("listIndTipDoc"));
				listadoTiposDocumentosTemporal = t6623TipDocExpDAO.listar(params);

				if (ValidaConstantes.IND_TIPO_DOCU_DISPONIBLE.equals(indConsulta)) {

					List<Map<String, Object>> listadoParametros = new ArrayList<Map<String, Object>>();

					for (T6623TipDocExpBean tipoDocumentoBean : listadoTiposDocumentosTemporal) {

						Map<String, Object> parametro = new HashMap<String, Object>();

						String codParametro = tipoDocumentoBean.getCodTipoDocumento().trim();

						parametro.put("codParametro", codParametro);

						listadoParametros.add(parametro);

					}
					// Inicio [jjurado 08/06/2016]
					params.remove("codTipoExpediente");
					// Fin [jjurado 08/06/2016]
					List<T01ParamBean> listadoParametrosTiposDocumentos = new ArrayList<T01ParamBean>();

					params.put("listadoParametrosExcluidos", listadoParametros);

					params.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					//Inicio: jtejada [12-10-2016]
					//Para carga de parámetros, siempre debe ir a dcrecauda.
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
					//Fin: jtejada [12-10-2016]
					listadoParametrosTiposDocumentos = t01ParamDAO.listar(params);

					Integer numOrden = 0;

					for (T01ParamBean tipoDocumentoBean : listadoParametrosTiposDocumentos) {

						numOrden++;

						T6623TipDocExpBean tipoDocumento = new T6623TipDocExpBean();

						String codTipoDocumento = tipoDocumentoBean.getCodParametro().trim();
						String desTipoDocumento = tipoDocumentoBean.getDesParametro().trim();
						String desTipoDocumentoCompuesto = codTipoDocumento + ValidaConstantes.SEPARADOR_ESPACIO + desTipoDocumento;

						tipoDocumento.setNumOrden(numOrden);
						tipoDocumento.setIndSeleccion(false);
						tipoDocumento.setCodTipoDocumento(codTipoDocumento);
						tipoDocumento.setDesTipoDocumento(desTipoDocumento);
						tipoDocumento.setDesTipoDocumentoCompuesto(desTipoDocumentoCompuesto);
						
						listadoTiposDocumentosFinales.add(tipoDocumento);

					}

				} else if (ValidaConstantes.IND_TIPO_DOCU_ASOCIADO.equals(indConsulta)) {

					Map<String, Object> mapa = new HashMap<String, Object>();

					mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);
					mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

					Map<String, Object> mapaTiposDocumentos = catalogoService.obtenerCatalogo(mapa);

					Integer numOrden = 0;

					for (T6623TipDocExpBean tipoDocumentoBean : listadoTiposDocumentosTemporal) {

						numOrden++;

						T6623TipDocExpBean tipoDocumento = new T6623TipDocExpBean();

						String codTipoDocumento = tipoDocumentoBean.getCodTipoDocumento().trim();
						String desTipoDocumento = mapaTiposDocumentos.get(codTipoDocumento) == null ? ValidaConstantes.CADENA_VACIA : mapaTiposDocumentos.get(codTipoDocumento).toString().trim();
						String desTipoDocumentoCompuesto = codTipoDocumento + ValidaConstantes.SEPARADOR_ESPACIO + desTipoDocumento;
						String indVisibleContribuyente = tipoDocumentoBean.getIndVisibleContribuyente().trim();

						tipoDocumento.setNumOrden(numOrden);
						tipoDocumento.setIndSeleccion(false);
						tipoDocumento.setCodTipoDocumento(codTipoDocumento);
						tipoDocumento.setDesTipoDocumento(desTipoDocumento);
						tipoDocumento.setDesTipoDocumentoCompuesto(desTipoDocumentoCompuesto);
						tipoDocumento.setIndVisibleContribuyente(indVisibleContribuyente);
						tipoDocumento.setIndTipDocumento(tipoDocumentoBean.getIndTipDocumento().trim()); //TODO [ATORRESCH 2017-04-27]

						listadoTiposDocumentosFinales.add(tipoDocumento);

					}

				}

			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoExpedienteProceso");

			NDC.pop();
			NDC.remove();

		}

		return listadoTiposDocumentosFinales;

	}

	public List<T6623TipDocExpBean> listarTiposDocumentos(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarTiposDocumento");
		//Inicio - [oachahuic][PAS20175E210400016]
		String codTipDoc = null;
		StringBuilder sbDesCodTipDoc = null;
		List<T6623TipDocExpBean> listTipDocPorExp = null;
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codClase", parametros.get("claseTipoDoc"));
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			Map<String, Object> mapParamTipDoc = catalogoService.obtenerCatalogo(mapa);
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listTipDocPorExp = t6623TipDocExpDAO.listar(parametros);

			for (T6623TipDocExpBean tipDocPorExp : listTipDocPorExp) {
				codTipDoc = tipDocPorExp.getCodTipoDocumento();
				if (mapParamTipDoc.get(codTipDoc) != null) {
					tipDocPorExp.setDesTipoDocumento(mapParamTipDoc.get(codTipDoc).toString());
					sbDesCodTipDoc = new StringBuilder();
					sbDesCodTipDoc.append(codTipDoc);
					sbDesCodTipDoc.append(ValidaConstantes.SEPARADOR_ESPACIO);
					sbDesCodTipDoc.append(ValidaConstantes.SEPARADOR_GUION);
					sbDesCodTipDoc.append(ValidaConstantes.SEPARADOR_ESPACIO);
					sbDesCodTipDoc.append(mapParamTipDoc.get(codTipDoc).toString());
					tipDocPorExp.setDesTipoDocumentoCompuesto(sbDesCodTipDoc.toString());
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarTiposDocumento");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarTiposDocumento");
			NDC.pop();
			NDC.remove();
		}
		//Fin - [oachahuic][PAS20175E210400016]
		return listTipDocPorExp;
	}

	public Map<String, Object> obtenerEstadoEtapa(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.obtenerEstadoEtapa");
		Map<String, Object> resultado = new HashMap<String, Object>();
		try {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_DOCUMENTOS_ESTADO);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParam.put("codParametro", parametros.get("codTipoDoc"));
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			T01ParamBean paramBean = t01ParamDAO.obtener(mapParam);
			if (paramBean == null) {
				resultado.put("codEstexpori", ValidaConstantes.CADENA_VACIA);
			} else {
				resultado.put("codEstexpori", paramBean.getDesParametro().trim());
			}
			mapParam = new HashMap<String, Object>();
			mapParam.put("codClase", CatalogoConstantes.CATA_DOCUMENTOS_ETAPA);
			mapParam.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParam.put("codParametro", parametros.get("codTipoDoc"));
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			paramBean = t01ParamDAO.obtener(mapParam);
			if (paramBean == null) {
				resultado.put("codEtaexpori", ValidaConstantes.CADENA_VACIA);
			} else {
				resultado.put("codEtaexpori", paramBean.getDesParametro().trim());
			}
			log.debug("resultado: " + resultado);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.obtenerEstadoEtapa");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.obtenerEstadoEtapa");
		}
		return resultado;
	}
	
	//Inicio [eaguilar 24/05/2015]
	public List<Map<String, Object>> listarTablasTipDoc3Dig(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarTablasTipDoc3Dig");
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		String flag3dig = null;
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarTablasTipDoc3Dig");
			Map<String, Object> mapa = new HashMap<String, Object>();
			flag3dig = (String) parametros.get("flag3dig");
			Map<String, Object> catalogo;
			Map<String, Object> itemLista;
			
			//tablas
//			if(flag3dig.equals("3")){
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_3DIG);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				catalogo = catalogoService.obtenerCatalogo(mapa);
//			}
			//tipo doc
//			else{
//				mapa.put("codClase", CatalogoConstantes.CATA_TABLAS_DOCUMENTOS_3DIG);
//				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
//				catalogo = catalogoService.obtenerCatalogo(mapa);
//			}
			
			for (Map.Entry<String, Object> item : catalogo.entrySet())
			{
				itemLista = new HashMap<String, Object>();
				itemLista.put("codigo", item.getKey());
				itemLista.put("descrip", item.getValue());
				itemLista.put("label", item.getValue());
				listado.add(itemLista);
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarTablasTipDoc3Dig");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarTablasTipDoc3Dig");
			NDC.pop();
			NDC.remove();
		}
		return listado;
	}
	//Fin [eaguilar 24/05/2015]
		
	public void realizarAsociacionTipoDocumentoTipoExpediente(Map<String, Object> parametros, List<Map<String, Object>> listadoTipoDoc23) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoDocumentoTipoExpediente");
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoDocumentoTipoExpediente");

			
			//DECLARACION DE VARIABLES
			Boolean indUbica = null;
			List<Map<String, Object>> listadoNormal = new ArrayList<Map<String, Object>>();
			
			List<Map<String, Object>> listadoInsertar = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoActualizar = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> listadoEliminar = new ArrayList<Map<String, Object>>();
			
			List<Map<String, Object>> listadoAcumulado = new ArrayList<Map<String, Object>>();
						
			Map<String, Object> params = new HashMap<String, Object>();

			//SETEAR VALORES
			String codUsuario = parametros.get("codUsuario").toString();
			String codTipoExpediente = parametros.get("codTipoExpediente").toString();

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listadoNuevo = (List<Map<String, Object>>) parametros.get("listadoNuevo");
			@SuppressWarnings("unchecked")
			List<T6623TipDocExpBean> listadoActual = (List<T6623TipDocExpBean>) parametros.get("listadoActual");

			Date fecSistema = new Date();
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);

			Date fecVacia = null;
			fecVacia = formatoDelTexto.parse(ValidaConstantes.FECHA_VACIA);
			
			//ITERACION			
			for(Map<String, Object> nuevo : listadoNuevo) {
				indUbica = false;

				String codTipoDocumentoNuevo = nuevo.get("codTipoDocumento").toString();
				String indVisibleContribuyenteNuevo = nuevo.get("indVisibleContribuyente").toString();
				String indTipDocumentoNuevo = nuevo.get("indTipDocumento").toString();

				for(T6623TipDocExpBean actual : listadoActual) {//LOOP

					String codTipoDocumentoActual = actual.getCodTipoDocumento().trim();
					String indVisibleContribuyenteActual = actual.getIndVisibleContribuyente().trim();
					String indTipDocumentoActual = actual.getIndTipDocumento().trim();
					String indEliminado = actual.getIndEliminado();
					
					//SI TIPODEDOCUMENTO ES IGUAL
					if(codTipoDocumentoActual.equals(codTipoDocumentoNuevo)) {//UBICADO
						if (ValidaConstantes.IND_REGI_NO_ELIMINADO.equals(indEliminado)) {//NO ESTA ELIMINADO							
							if(indVisibleContribuyenteActual.equals(indVisibleContribuyenteNuevo)
								&& indTipDocumentoActual.equals(indTipDocumentoNuevo) ) {//SI ES IGUAL VISIBILIDAD E INDTIP
								listadoNormal.add(nuevo);
								indUbica = true;
								break;
							}else{// NO ES IGUAL VISIBILIDAD E INDTIPDOCUMENTO
								listadoActualizar.add(nuevo);
								indUbica = true;
								break;
							}
						}else{//SI ESTABA ELIMINADO VISIBLE O NO VISIBLE							
							listadoActualizar.add(nuevo);
							indUbica = true;
							break;
						}
					}
					
				}

				if(!indUbica) {//NO ENCONTRADOS
					listadoInsertar.add(nuevo);
				}
			}

			listadoAcumulado.addAll(listadoNormal);
			listadoAcumulado.addAll(listadoActualizar);
			listadoAcumulado.addAll(listadoInsertar);

			for (T6623TipDocExpBean actual : listadoActual) { //ITERAR
				indUbica = false;
				String codTipoDocumentoActual = actual.getCodTipoDocumento().trim();				
				for (Map<String, Object> acumulado : listadoAcumulado) {// ACUMULADOR
					String codTipoDocumentoAcumulado = acumulado.get("codTipoDocumento").toString();
					if (codTipoDocumentoActual.equals(codTipoDocumentoAcumulado)) {
						indUbica = true;
						break;
					}
				}

				if (!indUbica) {
					Map<String, Object> desactiva = new HashMap<String, Object>();
					desactiva.put("codTipoDocumento", codTipoDocumentoActual);
					listadoEliminar.add(desactiva);
				}
			}
			

			Map<String, Object> mapa = new HashMap<String, Object>();
			/* Captura y seteo del Pool - Inicio */
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);			
			Map<String, Object> mapaPool = null; 

			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());

			
			if (listadoInsertar.size() > 0) { //INSERTAR
				for (Map<String, Object> inserta : listadoInsertar) {
					params.put("codTipoDocumento", inserta.get("codTipoDocumento").toString());					
					params.put("codTipoExpediente", codTipoExpediente);
					params.put("indVisibleContribuyente", inserta.get("indVisibleContribuyente").toString());
					params.put("codUsuarioRegistro", codUsuario);
					params.put("fecRegistro", fecSistema);
					params.put("codUsuarioModificacion", ValidaConstantes.COD_USUARIO_VACIO);
					params.put("fecModificacion", fecVacia);
					params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
					params.put("indTipDoc", inserta.get("indTipDocumento").toString());
					
					t6623TipDocExpDAO.insertar(params);
				}
			}
			
			if (listadoActualizar.size() > 0) { //ACTUALIZA
				for (Map<String, Object> actualiza : listadoActualizar) {
					params.put("codTipoDocumento", actualiza.get("codTipoDocumento").toString());
					params.put("codTipoExpediente", codTipoExpediente);
					params.put("indVisibleContribuyente", actualiza.get("indVisibleContribuyente").toString());
					params.put("codUsuarioModificacion", codUsuario);
					params.put("fecModificacion", fecSistema);
					params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);					
					params.put("indTipDocumento", actualiza.get("indTipDocumento").toString());
					t6623TipDocExpDAO.actualizar(params);
				}
			}
		
			if (listadoEliminar.size() > 0) { //ELIMINAR
				for (Map<String, Object> elimina : listadoEliminar) {
					params.put("codTipoDocumento", elimina.get("codTipoDocumento").toString());
					params.put("codTipoExpediente", codTipoExpediente);
					params.put("codUsuarioModificacion", codUsuario);
					params.put("fecModificacion", fecSistema);
					params.put("indEliminado", ValidaConstantes.IND_REGI_SI_ELIMINADO);					
					
					t6623TipDocExpDAO.actualizar(params);
				}
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoDocumentoTipoExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.realizarAsociacionTipoDocumentoTipoExpediente");

			NDC.pop();
			NDC.remove();

		}

	}
	public List<T01ParamBean> listarDependencias() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarDependencias");

		List<T01ParamBean> listadoDependenciasBean = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoDependenciasResult = new ArrayList<T01ParamBean>();
		TreeMap<Integer, T01ParamBean> tMapDependencias = new TreeMap<Integer, T01ParamBean>();
		T01ParamBean t01ParamBeanResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarDependencias");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoDependenciasBean = t01ParamDAO.listar(parametros);

			for (T01ParamBean dependenciaBean : listadoDependenciasBean) {

				String codClase = dependenciaBean.getCodClase().trim();
				String indTipo = dependenciaBean.getIndTipo().trim();
				String codParametro = dependenciaBean.getCodParametro().trim();
				String desParametro = dependenciaBean.getDesParametro().substring(ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA, ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA).trim();

				if (codParametro.length() == 4 && (codParametro.endsWith("1") || codParametro.endsWith("3"))) {
					t01ParamBeanResult = new T01ParamBean();

					t01ParamBeanResult.setCodClase(codClase);
					t01ParamBeanResult.setIndTipo(indTipo);
					t01ParamBeanResult.setCodParametro(codParametro);
					t01ParamBeanResult.setDesParametro(desParametro);

					// Se trabaja con TreeMap para ordenar por código de dependencia.
					tMapDependencias.put(Integer.parseInt(codParametro), t01ParamBeanResult);
				}
			}
			listadoDependenciasResult.addAll(tMapDependencias.values());
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarDependencias");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarDependencias");

			NDC.pop();
			NDC.remove();

		}

		return listadoDependenciasResult; 

	}

	public List<T01ParamBean> listarEstadosExpVirtual() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarEstadosExpVirtual");

		List<T01ParamBean> listadoEstadosExpVirtualBean = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoEstadosExpVirtualResult = new ArrayList<T01ParamBean>();
		T01ParamBean estadosExpVirtualResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarEstadosExpVirtual");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_ESTADOS_EXPEDIENTE_VIRTUAL);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoEstadosExpVirtualBean = t01ParamDAO.listar(parametros);

			for (T01ParamBean estadosExpVirtualBean : listadoEstadosExpVirtualBean) {
				
				if(!ValidaConstantes.IND_ESTADO_EXP_VIRTUAL_ELIMINADO.equals(estadosExpVirtualBean.getCodParametro().trim())){
					//INICIO [ATORRESCH 2017/04/19 04:50PM ] NO CONSIDERAR LOS ELIMINADOS
					estadosExpVirtualResult = new T01ParamBean();
					estadosExpVirtualResult.setCodClase(estadosExpVirtualBean.getCodClase().trim());
					estadosExpVirtualResult.setIndTipo(estadosExpVirtualBean.getIndTipo().trim());
					estadosExpVirtualResult.setCodParametro(estadosExpVirtualBean.getCodParametro().trim());
					estadosExpVirtualResult.setDesParametro(estadosExpVirtualBean.getDesParametro().trim());
					
					listadoEstadosExpVirtualResult.add(estadosExpVirtualResult);
				}
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarEstadosExpVirtual");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarEstadosExpVirtual");

			NDC.pop();
			NDC.remove();

		}

		return listadoEstadosExpVirtualResult;

	}

	@Override
	public List<T01ParamBean> listarFormatosAdicionales() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarProcesos");
		int cont = 0;
		List<T01ParamBean> listadoFormatosAdicionales = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoFormatosAdicionalesResult = new ArrayList<T01ParamBean>();
		T01ParamBean procesoBeanResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarProcesos");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_FORMATOS_ADICIONALES);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoFormatosAdicionales = t01ParamDAO.listar(parametros);

			for (T01ParamBean procesoBean : listadoFormatosAdicionales) {
				cont++;
				String codClase = procesoBean.getCodClase().trim();
				String indTipo = procesoBean.getIndTipo().trim();
				String codParametro = procesoBean.getCodParametro().trim();
				String desParametro = procesoBean.getDesParametro().trim();
				
				procesoBeanResult = new T01ParamBean();
				
				procesoBeanResult.setCodClase(codClase);
				procesoBeanResult.setIndTipo(indTipo);
				procesoBeanResult.setCodParametro(codParametro);
				procesoBeanResult.setDesParametro(desParametro);
				procesoBeanResult.setNumOrden(cont);
				procesoBeanResult.setEstado(ValidaConstantes.FILTRO_CERO);
				
				listadoFormatosAdicionalesResult.add(procesoBeanResult);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarProcesos");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarProcesos");

			NDC.pop();
			NDC.remove();

		}

		return listadoFormatosAdicionalesResult;
	}

	@Override
	public List<T01ParamBean> listarRepTribuAduaneros() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarProcesos");
		int cont = 0;
		List<T01ParamBean> listadoRepTribuAduaneros = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoRepTribuAduanerosResult = new ArrayList<T01ParamBean>();
		T01ParamBean procesoBeanResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarProcesos");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_REP_TRIBUT_ADUANERO);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoRepTribuAduaneros = t01ParamDAO.listar(parametros);

			for (T01ParamBean procesoBean : listadoRepTribuAduaneros) {
				cont++;
				String codClase = procesoBean.getCodClase().trim();
				String indTipo = procesoBean.getIndTipo().trim();
				String codParametro = procesoBean.getCodParametro().trim();
				String desParametro = procesoBean.getDesParametro().trim();

				procesoBeanResult = new T01ParamBean();
				
				procesoBeanResult.setCodClase(codClase);
				procesoBeanResult.setIndTipo(indTipo);
				procesoBeanResult.setCodParametro(codParametro);
				procesoBeanResult.setDesParametro(desParametro);
				procesoBeanResult.setNumOrden(cont);
				procesoBeanResult.setEstado(ValidaConstantes.FILTRO_CERO);
				
				listadoRepTribuAduanerosResult.add(procesoBeanResult);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarProcesos");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarProcesos");

			NDC.pop();
			NDC.remove();

		}

		return listadoRepTribuAduanerosResult;
	}

	public List<T01ParamBean> listarOrigenExpediente() throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarOrigenExpediente");

		List<T01ParamBean> listadoOrigenExpBean = new ArrayList<T01ParamBean>();
		List<T01ParamBean> listadoOrigenExpBeanResult = new ArrayList<T01ParamBean>();
		T01ParamBean origenExpedienteVirtualBeanResult = null;

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarOrigenExpediente");

			Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_ORIGEN_EXPEDIENTE_VIRTUAL);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoOrigenExpBean = t01ParamDAO.listar(parametros);

			for (T01ParamBean origenExpedienteVirtualBean : listadoOrigenExpBean) {

				String codClase = origenExpedienteVirtualBean.getCodClase().trim();
				String indTipo = origenExpedienteVirtualBean.getIndTipo().trim();
				String codParametro = origenExpedienteVirtualBean.getCodParametro().trim();
				String desParametro = origenExpedienteVirtualBean.getDesParametro().trim();
				
				origenExpedienteVirtualBeanResult = new T01ParamBean();
				
				origenExpedienteVirtualBeanResult.setCodClase(codClase);
				origenExpedienteVirtualBeanResult.setIndTipo(indTipo);
				origenExpedienteVirtualBeanResult.setCodParametro(codParametro);
				origenExpedienteVirtualBeanResult.setDesParametro(desParametro);
				
				listadoOrigenExpBeanResult.add(origenExpedienteVirtualBeanResult);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarOrigenExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarOrigenExpediente");

			NDC.pop();
			NDC.remove();

		}

		return listadoOrigenExpBeanResult;

	}

	@Override
	public List<T6613DocExpVirtBean> listarDocumentosExpediente(Map<String, Object> parametros) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

		List<T6613DocExpVirtBean> listaDocumentosExpediente = new ArrayList<T6613DocExpVirtBean>();

		try {

			if (log.isDebugEnabled()) log.debug("Procesa - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			listaDocumentosExpediente = t6613DocExpVirtDAO.listarDocumentosAsociados(parametros);

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.listarDocumentosExpediente");

			NDC.pop();
			NDC.remove();

		}

		return listaDocumentosExpediente;

	}
	//Inicio [eaguilar 27/05/2016] Se cambia el tipo de respuesta
	@Override
	public Map<String, Object> obtenerNumDocumento(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapRetorno = new HashMap<String, Object>();
		
		ResCoaBean obtenerNumDocumento = null;
		ExpCoaBean obtenerIndicadores = null;
		Map<String, Object> params = new HashMap<String, Object>();
		
		try {
			mapRetorno.put("obtenerNumDocumento", null);
			mapRetorno.put("nombreTabla", "");
			
			// Inicio [eaguilar 27/05/2016]
			//FASE 2: Si la t01 no devuelve nombre de tabla no se realiza la consulta
			Map<String, Object> mapSirat = new HashMap<String,Object>();
			mapSirat.put("codMaestro", parametros.get("codTipoDoc3Dig").toString());
			mapSirat.put("numeroRuc", parametros.get("numeroRuc").toString());
			mapSirat.put("numeroDocumento", parametros.get("numRC").toString());
			mapSirat.put("numeroExpediente", parametros.get("numExpediente").toString());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("coddependencia"));
			mapSirat = ccs19DAO.obtenerDatosSirat(mapSirat);
			
			if(parametros.get("codTipoDoc3Dig").toString().equals("-") || mapSirat == null || mapSirat.size() == 0 || mapSirat.get("codRespuesta").toString().trim().equals("0")){
				mapRetorno.put("obtenerNumDocumento", null);
				mapRetorno.put("flagConsultaSirat", "N"); //No existe 0
			}else if (mapSirat.get("codRespuesta").toString().trim().equals("1")){
				obtenerNumDocumento = new ResCoaBean();
				obtenerNumDocumento.setNum_exp(parametros.get("numExpediente").toString());
				obtenerNumDocumento.setNum_rc(mapSirat.get("numeroDoc").toString());
				obtenerNumDocumento.setCod_tip_doc(mapSirat.get("tipDoc").toString());
				obtenerNumDocumento.setFec_emi((Date) mapSirat.get("fechaEmision"));
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				obtenerNumDocumento.setFechaNotificacion(Utils.isEmpty(mapSirat.get("fechaNotificacion").toString())?
															"-":mapSirat.get("fechaNotificacion").toString().equals(ValidaConstantes.FECHA_VACIA)?"-":fmt.format((Date) mapSirat.get("fechaNotificacion")));
				mapRetorno.put("flagConsultaSirat", "E"); //Existe 1
			}else if(mapSirat.get("codRespuesta").toString().trim().equals("-2")){
				mapRetorno.put("obtenerNumDocumento", null);
				mapRetorno.put("flagConsultaSirat", "DNE"); //Numero de documento no pertence al expediente.
			}else if( mapSirat.get("codRespuesta").toString().trim().equals("-3")){
				mapRetorno.put("obtenerNumDocumento", null);
				mapRetorno.put("flagConsultaSirat", "N"); //No existe 0
			}else{
				mapRetorno.put("obtenerNumDocumento", null);
				mapRetorno.put("flagConsultaSirat", "I"); //Invalido -1
				
				//consultar a la t6623 los codigos tipo de documento:
				Map<String, Object> t6623Map = new HashMap<String, Object>();
				t6623Map.put("codTipoDocumentoLike", parametros.get("codTipoDoc3Dig").toString().trim() + "%");
				t6623Map.put("codTipoExpediente", parametros.get("codTipoExpediente").toString());
				t6623Map.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
				List<String> listIndTipDoc = new ArrayList<String>();
				listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_APERTURA);
				listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_CIERRE);
				listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_INTERNO);
				listIndTipDoc.add(ValidaConstantes.IND_CLASE_TIP_DOC_REAPERTURA);
				t6623Map.put("listIndTipDoc", listIndTipDoc);
				t6623Map.put("claseTipoDoc", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO1);

				List<T6623TipDocExpBean> lista6623 = this.listarTiposDocumentos(t6623Map);
				mapRetorno.put("listaEspecifica", lista6623);
			}
			// Fin [eaguilar 27/05/2016]
			
			String numExpediente = null;
			String fecVista = null;
			String fecVistaCompleta = null;
			Date fechaActual = null;

			if (obtenerNumDocumento != null) {

				numExpediente = obtenerNumDocumento.getNum_exp().toString().trim();

				params.put("numExpediente", numExpediente);
				//params.put("origenDatos", origenDatos);
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("coddependencia"));
				obtenerIndicadores = expCoaDAO.obtenerIndicadores(params);

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				fechaActual = obtenerNumDocumento.getFec_emi();
				fecVista = sdf.format(fechaActual);
				obtenerNumDocumento.setFechaEmision(fecVista);				
				
				fecVistaCompleta = sdf2.format(fechaActual);
				obtenerNumDocumento.setFechaEmisionCompleta(fecVistaCompleta);
				
				if (obtenerIndicadores != null) {
					obtenerNumDocumento.setIndAcum(obtenerIndicadores.getInd_acu().toString().trim());
					obtenerNumDocumento.setIndEstado("0" + obtenerIndicadores.getInd_est_ecc().toString().trim());
					obtenerNumDocumento.setIndEtapa(obtenerIndicadores.getInd_eta_ecc().toString().trim());
				} else {
					obtenerNumDocumento.setIndAcum(ValidaConstantes.CADENA_VACIA);
					obtenerNumDocumento.setIndEstado(ValidaConstantes.CADENA_VACIA);
					obtenerNumDocumento.setIndEtapa(ValidaConstantes.CADENA_VACIA);
				}
				
				//fecha notificacion:
//				Map<String, Object> paramsFecnot = new HashMap<String, Object>();
//				String numeroRuc = parametros.get("numeroRuc").toString();
//				paramsFecnot.put("numeroDocumento", !Utils.isEmpty(obtenerNumDocumento.getNum_rc())?"":obtenerNumDocumento.getNum_rc());
//				paramsFecnot.put("numeroRuc", !Utils.isEmpty(numeroRuc)?"":numeroRuc);
//				String fechaNot = this.obtenerFechaNotificacion(paramsFecnot);
//				obtenerNumDocumento.setFechaNotificacion(Utils.isEmpty(fechaNot)?"-":fechaNot.equals(ValidaConstantes.FECHA_VACIA)?"-":fechaNot);
				
				mapRetorno.put("obtenerNumDocumento", obtenerNumDocumento);
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.obtenerNumDocumento");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.obtenerNumDocumento");

			NDC.pop();
			NDC.remove();

		}
		// Inicio [eaguilar 27/05/2016] se cambia el tipo de retorno
		return mapRetorno;
		//return obtenerNumDocumento;
		// Fin [eaguilar 27/05/2016]
	}

	/**
	* @return: ResCoaBean
	* @author: nchavez
	* @date :  25/05/2016
	* @time :  4:37pm
	*/
	@Override
	public ResCoaBean obtenerNumDocumentoRC(Map<String, Object> parametros) throws Exception {
		ResCoaBean resCoaBean = null;
		ExpCoaBean obtenerIndicadores = null;
		Map<String, Object> params = new HashMap<String, Object>();
		
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("coddependencia"));
			resCoaBean = resCoaDAO.obtenerNumeroDocumentoRequerimiento(parametros);

			String numExpediente = null;
			String fecVista = null;
			String fecVistaCompleta = null;
			Date fechaActual = null;

			if (!Utils.isEmpty(resCoaBean)) {
				numExpediente = resCoaBean.getNum_exp().toString().trim();

				params.put("numExpediente", numExpediente);
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("coddependencia"));
				obtenerIndicadores = expCoaDAO.obtenerIndicadores(params);

				SimpleDateFormat sdf = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				SimpleDateFormat sdf2 = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA_HORA);

				fechaActual = resCoaBean.getFec_emi();
				fecVista = sdf.format(fechaActual);
				resCoaBean.setFechaEmision(fecVista);				
				
				fecVistaCompleta = sdf2.format(fechaActual);
				resCoaBean.setFechaEmisionCompleta(fecVistaCompleta);
				
				if (obtenerIndicadores != null) {
					resCoaBean.setIndAcum(obtenerIndicadores.getInd_acu().toString().trim());
					resCoaBean.setIndEstado("0" + obtenerIndicadores.getInd_est_ecc().toString().trim());
					resCoaBean.setIndEtapa(obtenerIndicadores.getInd_eta_ecc().toString().trim());
				} else {

					resCoaBean.setIndAcum(ValidaConstantes.CADENA_VACIA);
					resCoaBean.setIndEstado(ValidaConstantes.CADENA_VACIA);
					resCoaBean.setIndEtapa(ValidaConstantes.CADENA_VACIA);
				}
			}

		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - DocumentoExpedienteServiceImpl.obtenerNumDocumento");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - DocumentoExpedienteServiceImpl.obtenerNumDocumento");

			NDC.pop();
			NDC.remove();

		}
		return resCoaBean;
	}
	
	public T01ParamBean obtenerProceso(String codProceso) throws Exception {
		return obtenerParametro(CatalogoConstantes.CATA_PROCESOS, codProceso);
	}

	public T01ParamBean obtenerTipoExpediente(String codTpoExpdte) throws Exception {
		return obtenerParametro(CatalogoConstantes.CATA_TIPOS_EXPEDIENTES, codTpoExpdte);
	}

	public T01ParamBean obtenerTipoDoc(String codTpoDoc, String claseTipoDoc) throws Exception {
		return obtenerParametro(claseTipoDoc, codTpoDoc);
	}

	@Override
	public T01ParamBean obtenerParametro(String codCata, String cod) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.obtenerParametro");
		T01ParamBean paramBean = null;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("codClase", codCata);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			parametros.put("codParametro", cod);
			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			paramBean = t01ParamDAO.obtener(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.obtenerParametro");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.obtenerParametro");
		}
		return paramBean;
	}

	public List<T6623TipDocExpBean> listarTipDocExpediente(Map<String, Object> mapParametros) throws Exception{
		return  t6623TipDocExpDAO.listar(mapParametros);
	}
	
	@Override
	public Map<String, String> listarMapDependencias() throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarMapDependencias");
		List<T01ParamBean> listadoDependenciasBean = null;
		Map<String, Object> parametros = null;
		String codParametro = null;
		String desParametro = null;
		Map<String, String> mapDependencias = null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarMapDependencias");

			parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_DEPENDENCIAS);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoDependenciasBean = t01ParamDAO.listar(parametros);
			if(listadoDependenciasBean != null) {
				mapDependencias = new HashMap<String, String>();
				
				for (T01ParamBean dependenciaBean : listadoDependenciasBean) {				
					codParametro = dependenciaBean.getCodParametro().trim();
					desParametro = dependenciaBean.getDesParametro().substring(ValidaConstantes.LIMITE_INFERIOR_DEPENDENCIA, ValidaConstantes.LIMITE_SUPERIOR_DEPENDENCIA).trim();
					
					mapDependencias.put(codParametro, desParametro);
				}
			}
		} catch (Exception ex) {

			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarMapDependencias");

			log.error(ex, ex);

			throw ex;

		} finally {

			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarDependencias");

			NDC.pop();
			NDC.remove();

		}

		return mapDependencias;
	}
	
	@Override
	public Map<String, String> listarMapProcesos() throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarMapProcesos");
		List<T01ParamBean> listadoProcesosBean = null;
		Map<String, Object> parametros = null;
		Map<String, String> mapProcesos = null;
		String codParametro = null;
		String desParametro = null;

		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarMapProcesos");
			listadoProcesosBean = new ArrayList<T01ParamBean>();
			parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_PROCESOS);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoProcesosBean = t01ParamDAO.listar(parametros);
			if(listadoProcesosBean != null) {
				mapProcesos = new HashMap<String, String>();
				
				for (T01ParamBean procesoBean : listadoProcesosBean) {
					codParametro = procesoBean.getCodParametro().trim();
					desParametro = procesoBean.getDesParametro().trim();
					
					mapProcesos.put(codParametro, desParametro);
				}
			}	
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarMapProcesos");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarProcesos");

			NDC.pop();
			NDC.remove();
		}

		return mapProcesos;
	}
	
	@Override
	public Map<String, String> listarMapTiposExpediente() throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarMapTiposExpediente");
		List<T01ParamBean> listadoTiposExpedienteBean = null;
		Map<String, Object> parametros = null;
		Map<String, String> mapTiposExpediente = null;
		String codParametro = null;
		String desParametro = null;

		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarMapTiposExpediente");
			listadoTiposExpedienteBean = new ArrayList<T01ParamBean>();
			parametros = new HashMap<String, Object>();

			parametros.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
			parametros.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listadoTiposExpedienteBean = t01ParamDAO.listar(parametros);
			if(listadoTiposExpedienteBean != null) {
				mapTiposExpediente = new HashMap<String, String>();
				
				for (T01ParamBean procesoBean : listadoTiposExpedienteBean) {
					codParametro = procesoBean.getCodParametro().trim();
					desParametro = procesoBean.getDesParametro().trim();
					
					mapTiposExpediente.put(codParametro, desParametro);
				}
			}	
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarMapTiposExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarMapTiposExpediente");

			NDC.pop();
			NDC.remove();
		}

		return mapTiposExpediente;
	}
	
	@Override
	public List<Map<String, Object>> listarTipoDocsNoAsocExp(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");
		List<Map<String, Object>> listaTipoDocs = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = new HashMap<String, Object>(); 
		List<T01ParamBean> listaTipoDocsTipo2 = null;
		List<T01ParamBean> listaTipoDocsTipo3 = null;
		List<T01ParamBean> listaTipoDocsTipoA58 = null;
		List<T01ParamBean> listaTipoDocsTipoTotal = new ArrayList<T01ParamBean>();
		List<T6623TipDocExpBean> listadoTiposDocumentosDisponibles = null;
		boolean existe = false;
		Map<String, Object> mapTipoDoc = null;
		String indClaseTipo = null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");
			
			params.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO2);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			//Inicio: jtejada [12-10-2016]
			//Para carga de parámetros, siempre debe ir a dcrecauda.
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			//Fin: jtejada [12-10-2016]
			listaTipoDocsTipo2 = t01ParamDAO.listar(params);
			
			params.put("codClase", CatalogoConstantes.CATA_TIPOS_DOCUMENTOS_TIPO3);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			listaTipoDocsTipo3 = t01ParamDAO.listar(params);
			
			/*params.put("codClase", CatalogoConstantes.CATA_DOCUMENTOS_OTROS);
			params.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);

			listaTipoDocsTipoA58 = t01ParamDAO.listar(params);*/
			
			
			listaTipoDocsTipoTotal.addAll(listaTipoDocsTipo2);
			listaTipoDocsTipoTotal.addAll(listaTipoDocsTipo3);
			//listaTipoDocsTipoTotal.addAll(listaTipoDocsTipoA58); //eaguilar 04 jul lista de OTROS
			
			params = new HashMap<String, Object>(); 
			params.put("codTipoExpediente", (String)parametros.get("codTipoExpediente"));
			params.put("indEliminado", ValidaConstantes.IND_REGI_NO_ELIMINADO);
			
			listadoTiposDocumentosDisponibles = t6623TipDocExpDAO.listar(params);
			//if(listadoTiposDocumentosDisponibles != null && listadoTiposDocumentosDisponibles.size() > 0) {				
				for(T01ParamBean t01ParamBean : listaTipoDocsTipoTotal) {
					existe = false;
					
					for(T6623TipDocExpBean t6623TipDocExpBean : listadoTiposDocumentosDisponibles) {					
						if(t6623TipDocExpBean.getCodTipoDocumento().trim().equals(t01ParamBean.getCodParametro().trim())) {
							existe = true;
						}
					}
					
					if(!existe) {
						mapTipoDoc = new HashMap<String, Object>();
						indClaseTipo = null;
						
						//eaguilar 04 jul
						/*for(T01ParamBean tipo2 : listaTipoDocsTipoA58) {
							if(t01ParamBean.getCodParametro().trim().equals(tipo2.getCodParametro().trim())) {
								indClaseTipo = ValidaConstantes.IND_CLASE_TIPO_1;
								break;
							}
						}*/
						
						for(T01ParamBean tipo2 : listaTipoDocsTipo2) {
							if(t01ParamBean.getCodParametro().trim().equals(tipo2.getCodParametro().trim())) {
								indClaseTipo = ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO;
								break;
							}
						}
						
						for(T01ParamBean tipo3 : listaTipoDocsTipo3) {
							if(t01ParamBean.getCodParametro().trim().equals(tipo3.getCodParametro().trim())) {
								indClaseTipo = ValidaConstantes.IND_CLASE_TIP_DOC_OTRO;
								break;
							}
						}
						
						mapTipoDoc.put("indClaseTipo", indClaseTipo);
						mapTipoDoc.put("codTipoDoc", t01ParamBean.getCodParametro().trim());
						
						listaTipoDocs.add(mapTipoDoc);
					}
				}
			//}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");

			NDC.pop();
			NDC.remove();
		}

		return listaTipoDocs;
	}

	@Override
	public Map<String, Object> obtenerFechaNotificacion(Map<String, Object> parametros) throws Exception {

		// Inicio nchavez [03/06/2016]
		String fechaNotificacion = null;
		Integer codigo = null;
		Map<String, Object> mapRetorno = new HashMap<String, Object>();
		// Fin nchavez [03/06/2016]

		try {
			Map<String, Object> mapSirat = new HashMap<String, Object>();
			mapSirat.put("codMaestro", ValidaConstantes.CODIGO_AGRUPACION_DOC_COBRANZA_COACTIVA);
			mapSirat.put("numeroDocumento", parametros.get("numeroDocumento").toString());
			mapSirat.put("numeroRuc", parametros.get("numeroRuc").toString());
			mapSirat.put("numeroExpediente", parametros.get("numExpedienteOrigen").toString());
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("coddependencia"));
			mapSirat = ccs19DAO.obtenerDatosSirat(mapSirat);

			if (mapSirat == null || mapSirat.size() == 0 || mapSirat.get("codRespuesta").toString().trim().equals("0")) {
				// No existe 0
				codigo = 0;
				fechaNotificacion = null;
			} else if (mapSirat.get("codRespuesta").toString().trim().equals("1")) {
				// Existe 1
				if (log.isDebugEnabled()) log.debug("ConfiguracionExpedienteServiceImpl.obtenerFechaNotificacion - Result  nts01_sql_fec_not :" + mapSirat);

				fechaNotificacion = Utils.toStr(mapSirat.get("fechaNotificacion"));

				if (log.isDebugEnabled()) log.debug("ConfiguracionExpedienteServiceImpl.obtenerFechaNotificacion - fechaNotificacion :" + fechaNotificacion);

				fechaNotificacion = (Utils.isEmpty(fechaNotificacion) ? null : fechaNotificacion.equals(ValidaConstantes.FECHA_VACIA) ? null : getFormatDDYYAAAA(fechaNotificacion));
				codigo = 1;
			} else if (mapSirat.get("codRespuesta").toString().trim().equals("-2")) {
				// Numero de documento no pertence al expediente.
				fechaNotificacion = null;
				codigo = -2;
			} else {
				fechaNotificacion = null;
				codigo = 0;
			}

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.listarTipoDocsNoAsocExp");

			NDC.pop();
			NDC.remove();
		}

		mapRetorno.put("codigo", codigo);
		mapRetorno.put("fechaNotificacion", fechaNotificacion);
		return mapRetorno;
		// Fin nchavez [03/06/2016]
	}

	private String getFormatDDYYAAAA(String fechaAAAAMMDD) {
		StringBuilder fechaDDMMAAAA;
		String[] fechaSplit;
		if (fechaAAAAMMDD != null && fechaAAAAMMDD.trim().contains(ValidaConstantes.SEPARADOR_GUION)) {
			fechaSplit = fechaAAAAMMDD.split(ValidaConstantes.SEPARADOR_GUION);
			if (fechaSplit.length == 3) {
				fechaDDMMAAAA = new StringBuilder();
				fechaDDMMAAAA.append(fechaSplit[2]).append("/");
				fechaDDMMAAAA.append(fechaSplit[1]).append("/");
				fechaDDMMAAAA.append(fechaSplit[0]);
				return fechaDDMMAAAA.toString();
			}
		}
		return fechaAAAAMMDD;
	}

	@Override
	public ExpOrigenBean obtenerDatosExOrigen(Map<String, Object> parametros)throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.obtenerDatosExOrigen");
		
		ExpOrigenBean expOrigenBean = new ExpOrigenBean();
		Map<String, Object> mapExpOri = null;
		try {
			//VALIDAR NUM EXP ORIGEN
			if (ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA.equals(parametros.get("cod_tip_exp"))) {
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("cod_dep"));
				mapExpOri = ccs19DAO.obtenerDatosExpOrigen(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(parametros.get("cod_tip_exp")) ||
                //PAS20191U210500144 Inicio 400101 RF03 PSALDARRIAGA
				ValidaConstantes.TIPO_EXPE_FISCA_DEF_PAR.equals(parametros.get("cod_tip_exp")) ||
				ValidaConstantes.TIPO_EXPE_CRUCE_INFO.equals(parametros.get("cod_tip_exp"))){
                //PAS20191U210500144 Fin 400101 RF03 PSALDARRIAGA                
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("cod_dep"));
				mapExpOri = expFiscaDAO.spFiValidarExpeOrigen(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_RECTIF_DAM.equals(parametros.get("cod_tip_exp"))) {
				//INVOCAR EL WS
			} else {//NO SE VALIDA LA EXISTENCIA DEL EXPEDIENTE
				mapExpOri = new HashMap<String, Object>();
				mapExpOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
			}

			if (ValidaConstantes.FILTRO_CERO.equals(mapExpOri.get("cod_rpta").toString())) {
				expOrigenBean.setCodRpta(mapExpOri.get("cod_rpta").toString());
				expOrigenBean.setDesError(mapExpOri.get("des_error").toString());
			} else if (ValidaConstantes.FILTRO_UNO.equals(mapExpOri.get("cod_rpta").toString())) {
				expOrigenBean.setCodRpta(mapExpOri.get("cod_rpta").toString());
				expOrigenBean.setCodTipDocApe(mapExpOri.get("cod_tip_doc").toString().trim());
				expOrigenBean.setNumDocApe(mapExpOri.get("num_doc").toString().trim());
				expOrigenBean.setFecEmiDocApe(Utils.stringToDate(Utils.toStr(mapExpOri.get("fec_emi_doc")).substring(0, 10),CatalogoConstantes.INT_FOUR));
				expOrigenBean.setFecNotDocApe(Utils.stringToDate(Utils.toStr(mapExpOri.get("fec_not_doc")).substring(0, 10),CatalogoConstantes.INT_FOUR));
				expOrigenBean.setDesExp((mapExpOri.get("des_exp") == null) ? "" : mapExpOri.get("des_exp").toString());
				expOrigenBean.setPlbsClave((mapExpOri.get("plbrs_clave") == null) ? "" : mapExpOri.get("plbrs_clave").toString());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String strFecEmiDocApe = sdf.format(Utils.stringToDate(Utils.toStr(mapExpOri.get("fec_emi_doc")).substring(0, 19),CatalogoConstantes.INT_FIVE));				
				expOrigenBean.setStrFecEmiDocApe(strFecEmiDocApe);
				
				Map<String, Object> mapParam = new HashMap<String, Object>();
				mapParam.put("codPersona", (mapExpOri.get("cod_resp_exp") == null) ? "0" : mapExpOri.get("cod_resp_exp").toString().trim());
				T02PerdpBean t02PerdpBean = personaService.validarPersona(mapParam);
				if (t02PerdpBean == null) {
					expOrigenBean.setDesRespExp("");
					expOrigenBean.setCodRespExp("");
				} else {
					StringBuilder sbDesRespExp = new StringBuilder();
					sbDesRespExp.append(t02PerdpBean.getDesNombres()).append(" ");
					sbDesRespExp.append(t02PerdpBean.getDesApellidoPaterno()).append(" ");
					sbDesRespExp.append(t02PerdpBean.getDesApellidoMaterno());
					expOrigenBean.setDesRespExp(sbDesRespExp.toString());
					expOrigenBean.setCodRespExp(t02PerdpBean.getCodPersona());
				}
			} else {
				expOrigenBean.setCodRpta(mapExpOri.get("cod_rpta").toString());
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.obtenerDatosExOrigen");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.obtenerDatosExOrigen");
		}
		return expOrigenBean;
	}
	
	public String cantidadTipoDocumentosRelacion(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapResponse = null;
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.cantidadTipoDocumentosRelacion");
		
		try {
			int iCant = t6613DocExpVirtDAO.contarTipoDocumentosRelacion(parametros);
			
			mapResponse = new LinkedHashMap<String, Object>();
			if(iCant > 0 ){
				mapResponse.put("status", false);
				mapResponse.put("message", AvisoConstantes.EXCEP_MODULO_01_02_006);
			}else{
				mapResponse.put("status", true);
				mapResponse.put("message", "");		
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.cantidadTipoDocumentosRelacion");
			log.error(ex, ex);
			mapResponse = new LinkedHashMap<String, Object>();
			mapResponse.put("status", false);
			mapResponse.put("message", ex.getMessage());
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.cantidadTipoDocumentosRelacion");
			
			NDC.pop();
			NDC.remove();
		}		
		JsonSerializer serializer = new JsonSerializer();
		String jsonResponse = (String) serializer.serialize(mapResponse);		
		return jsonResponse;	
	}
	
	//Inicio - [oachahuic][PAS20175E210400016]
	public T6623TipDocExpBean obtenerTipDocExpediente(Map<String, Object> mapParametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.obtenerTipDocExpediente");
		T6623TipDocExpBean t6623Bean = null;
		try {
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			t6623Bean = t6623TipDocExpDAO.getTipDocExp(mapParametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.obtenerTipDocExpediente");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.obtenerTipDocExpediente");
		}
		//Fin - [oachahuic][PAS20175E210400016]
		return t6623Bean;
	}

	@Override
	public DocOrigenBean obtenerDatosDocOrigen(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.obtenerDatosDocOrigen");
		
		DocOrigenBean docOrigenBean = null;
		Map<String, Object> mapDocOri = new HashMap<String, Object>();
		
		try {
			String codTipExp = parametros.get("cod_tip_exp").toString();
			String codTipDoc = ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA.equals(codTipExp) ? "000000" : parametros.get("cod_tip_doc").toString();
			if (ValidaConstantes.GRUPO_TIP_DOC_CONST.equals(codTipDoc.substring(0, 3))) {//CONSTANCIAS DOCUMENTO NOTIFICADO
				mapDocOri = this.obtenerDocNotEnSIEV(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_COBRANZA_COACTIVA.equals(codTipExp)) {
				if (ValidaConstantes.GRUPO_TIP_DOC_OTROS.equals(parametros.get("cod_mae").toString())) {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
				} else {
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("cod_dep"));
					mapDocOri = ccs19DAO.obtenerDatosSirat(parametros);
					parametros.put("cod_tip_doc", mapDocOri.get("cod_tip_doc"));
				}
			} else if (ValidaConstantes.TIPO_EXPE_CARTA_INDUCTIVA.equals(codTipExp)) {
				mapDocOri = this.validarNumDocFisca(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_ESQUELA.equals(codTipExp)) {
				mapDocOri = this.validarNumDocFisca(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFO_OCP.equals(codTipExp)) {
				mapDocOri = this.validarNumDocFisca(parametros);
			//[PAS20191U210500144] Inicio 
			} else if (ValidaConstantes.TIPO_EXPE_FISCA_DEFINITIVA_PARCIAL.equals(codTipExp)) {
				mapDocOri = this.validarNumDocFisca(parametros);
			} else if (ValidaConstantes.TIPO_EXPE_CRUCE_INFORMACION.equals(codTipExp)) {
				mapDocOri = this.validarNumDocFisca(parametros);
			//[PAS20191U210500144] Fin
			} else {
				mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
			}
			log.debug("mapDocOri: " + mapDocOri);
			//FORMATEAR RESPUESTA
			docOrigenBean = new DocOrigenBean();
			docOrigenBean.setCodRpta(mapDocOri.get("cod_rpta").toString());
			docOrigenBean.setDesError((mapDocOri.get("des_error") == null) ? "" : mapDocOri.get("des_error").toString());
			if (ValidaConstantes.FILTRO_UNO.equals(mapDocOri.get("cod_rpta").toString())) {
				docOrigenBean.setCodTipDoc(parametros.get("cod_tip_doc").toString());
				docOrigenBean.setNumDoc(parametros.get("num_doc").toString());
				docOrigenBean.setFecEmiDoc(Utils.stringToDate(Utils.toStr(mapDocOri.get("fec_emi_doc")).substring(0, 10),CatalogoConstantes.INT_FOUR));
				docOrigenBean.setFecNotDoc(Utils.stringToDate(Utils.toStr(mapDocOri.get("fec_not_doc")).substring(0, 10),CatalogoConstantes.INT_FOUR));
				docOrigenBean.setCodTipDocRel((mapDocOri.get("cod_tip_doc_rel") == null) ? "" : mapDocOri.get("cod_tip_doc_rel").toString());
				docOrigenBean.setNumDocRel((mapDocOri.get("num_doc_rel") == null) ? "" : mapDocOri.get("num_doc_rel").toString());
				docOrigenBean.setNumCorDocRel((mapDocOri.get("num_cor_doc_rel") == null) ? "" : mapDocOri.get("num_cor_doc_rel").toString());
				docOrigenBean.setIndVisDocRel((mapDocOri.get("ind_vis_doc_rel") == null) ? "" : mapDocOri.get("ind_vis_doc_rel").toString());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				docOrigenBean.setStrFecEmiDoc(sdf.format(Utils.stringToDate(Utils.toStr(mapDocOri.get("fec_emi_doc")).substring(0, 19),CatalogoConstantes.INT_FIVE)));
				docOrigenBean.setDocNotSine(mapDocOri.get("docNotSine") == null ? null : (DocNotSineBean)mapDocOri.get("docNotSine"));
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.obtenerDatosDocOrigen");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.obtenerDatosDocOrigen");
		}
		return docOrigenBean;
	}
	
	private Map<String, Object> obtenerDocNotEnSIEV(Map<String, Object> parametros) {
		Map<String, Object> mapDocOri = new HashMap<String, Object>();
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("numExpeDv", parametros.get("num_exp_vir"));
		mapParam.put("numDoc", parametros.get("num_doc"));
		mapParam.put("difGrupo", ValidaConstantes.GRUPO_TIP_DOC_CONST + "%");
		DataSourceContextHolder.setKeyDataSource("c");
		T6613DocExpVirtBean docNotif = t6613DocExpVirtDAO.obtenerDocOrigen(mapParam);
		if (docNotif == null) { 
			mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
			mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_35.replace("{num_doc}", parametros.get("num_doc").toString()));
		} else {
			mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_UNO);
			mapDocOri.put("fec_emi_doc", ValidaConstantes.FECHA_HORA_DEFAULT);
			mapDocOri.put("fec_not_doc", ValidaConstantes.FECHA_DEFAULT);
			mapDocOri.put("cod_tip_doc_rel", docNotif.getCodTipDoc());
			mapDocOri.put("num_doc_rel", docNotif.getNumDoc());
			mapDocOri.put("num_cor_doc_rel", docNotif.getNumCorDoc());
			mapDocOri.put("ind_vis_doc_rel", ValidaConstantes.FILTRO_UNO);
		}
		return mapDocOri;
	}
	
	private Map<String, Object> validarNumDocFisca(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ConfiguracionExpedienteServiceImpl.validarNumDocFisca");
		Map<String, Object> mapDocOri = new HashMap<String, Object>();
		try {
			//RECUPERAR TIPO DE VALIDACION
			StringBuilder sbArgumento = new StringBuilder();
			sbArgumento.append(parametros.get("cod_tip_exp")).append("-").append(parametros.get("cod_tip_doc"));
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("t01_numero_par", CatalogoConstantes.CATA_TIP_VAL_TIP_DOC_TIP_EXP);
			mapParam.put("t01_tipo_par", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			mapParam.put("t01_argumento_par", sbArgumento.toString());
			Map<String, Object> mapTipVal = t01ParamDAO.obtenerTipValidacion(mapParam);
			log.debug("mapTipVal: " + mapTipVal);
			if (mapTipVal == null) {//CASO O: SIN VALIDACION
				mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
			} else if (ValidaConstantes.FILTRO_CERO.equals(mapTipVal.get("tip_serv"))) {//CASO O: SIN VALIDACION
				mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
			} else if (ValidaConstantes.FILTRO_UNO.equals(mapTipVal.get("tip_serv"))) {//CASO 1: VALIDAR FORMATO
				if (parametros.get("num_doc").toString().length() == 17) {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_DOS);
				} else {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
					mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_44.replace(ValidaConstantes.PATRON_CAMBIO_MENSAJES_0, mapTipVal.get("funcion").toString()));
				}
			} else if (ValidaConstantes.FILTRO_DOS.equals(mapTipVal.get("tip_serv"))) {//CASO 2: VALIDAR EN SINE
				DocNotSineBean docNotSine = documentoExpedienteService.obtenerDocNotPorSINE(parametros);
				if (docNotSine == null) {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
					mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_43.replace("{num_doc}", parametros.get("num_doc").toString()));
				} else {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_UNO);
					mapDocOri.put("fec_emi_doc", docNotSine.getFecCompDoc());
					mapDocOri.put("fec_not_doc", docNotSine.getFecNot());
					mapDocOri.put("cod_tip_doc_rel", docNotSine.getCodTipCons());
					mapDocOri.put("num_doc_rel", docNotSine.getNumDoc());
					mapDocOri.put("docNotSine", docNotSine);
				}
			} else if (ValidaConstantes.FILTRO_TRES.equals(mapTipVal.get("tip_serv"))) {//CASO 3: VALIDAR EN RFISCA
				if (ValidaConstantes.DES_EXPFISCA.equals(mapTipVal.get("sub_sistema"))) {
					DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + parametros.get("cod_dep"));
					mapDocOri = expFiscaDAO.spFivalidarDocExpediente(parametros);
					
					//[PAS20191U210500144] Inicio 
					log.debug("mapDocOri FILTRO_TRES RFISCA: " + mapDocOri );
					if ("1".equals(mapDocOri.get("cod_rpta").toString()) ) {
						//Verifico si documento fue notificado por SINE
						DocNotSineBean docNotSine = documentoExpedienteService.obtenerDocNotPorSINE(parametros);
						if(docNotSine != null) {//Agrego campos de SINE
							mapDocOri.put("fec_not_doc", docNotSine.getFecNot());	
							mapDocOri.put("cod_tip_doc_rel", docNotSine.getCodTipCons());
							mapDocOri.put("num_doc_rel", docNotSine.getNumDoc());
							mapDocOri.put("docNotSine", docNotSine);
							log.debug("mapDocOri: " + mapDocOri );
						}
					}
					//[PAS20191U210500144] Fin 
					
				} else {//NO SE IMPLEMENTÓ LA VALIDACIÓN DEL TIPO DE DOCUMENTO
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
					mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_45);
				}
			//[PAS20191U210500144] Inicio 
			} else if (ValidaConstantes.FILTRO_CUATRO.equals(mapTipVal.get("tip_serv"))) {//CASO 4: VALIDAR EN SIGAD
				parametros.put("tipDocSigad", mapTipVal.get("param_funcion"));
				Map<String, Object> docSIGAD = documentoExpedienteService.validarDocSIGAD(parametros);
				log.debug("validarDocSIGAD: " + docSIGAD );
				if (docSIGAD == null) {
					mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
					mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_43.replace("{num_doc}", parametros.get("num_doc").toString()));
				} else {
					mapDocOri.putAll(docSIGAD);
					//Verifico si documento fue notificado por SINE
					DocNotSineBean docNotSine = documentoExpedienteService.obtenerDocNotPorSINE(parametros);
					if(docNotSine != null) {//Agrego campos de SINE
						mapDocOri.put("fec_not_doc", docNotSine.getFecNot());	
						mapDocOri.put("cod_tip_doc_rel", docNotSine.getCodTipCons());
						mapDocOri.put("num_doc_rel", docNotSine.getNumDoc());
						mapDocOri.put("docNotSine", docNotSine);
						log.debug("mapDocOri + SIGAD: " + mapDocOri );
					}

				}
			//[PAS20191U210500144] Fin 
			} else {//CASO N: NO SE IMPLEMENTÓ LA VALIDACIÓN
				mapDocOri.put("cod_rpta", ValidaConstantes.FILTRO_CERO);
				mapDocOri.put("des_error", AvisoConstantes.MSJ_MODULO_03_01_EX_45);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ConfiguracionExpedienteServiceImpl.validarNumDocFisca");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ConfiguracionExpedienteServiceImpl.validarNumDocFisca");
		}
		return mapDocOri;
	}

	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}

	public void setT6624TipExpProcDAO(T6624TipExpProcDAO t6624TipExpProcDAO) {
		this.t6624TipExpProcDAO = t6624TipExpProcDAO;
	}

	public void setT6623TipDocExpDAO(T6623TipDocExpDAO t6623TipDocExpDAO) {
		this.t6623TipDocExpDAO = t6623TipDocExpDAO;
	}

	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	public void setResCoaDAO(ResCoaDAO resCoaDAO) {
		this.resCoaDAO = resCoaDAO;
	}

	public void setExpCoaDAO(ExpCoaDAO expCoaDAO) {
		this.expCoaDAO = expCoaDAO;
	}

	public void setCcs19DAO(Ccs19DAO ccs19DAO) {
		this.ccs19DAO = ccs19DAO;
	}

	public void setExpFiscaDAO(ExpFiscaDAO expFiscaDAO) {
		this.expFiscaDAO = expFiscaDAO;
	}
	
	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setDocumentoExpedienteService(
			DocumentoExpedienteService documentoExpedienteService) {
		this.documentoExpedienteService = documentoExpedienteService;
	}
	
	/* Seteo - Spring - Final */

}