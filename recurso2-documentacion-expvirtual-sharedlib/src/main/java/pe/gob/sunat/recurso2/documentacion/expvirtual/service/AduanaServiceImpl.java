package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.CircunOceDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DataCatalogoDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.TabDepDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class AduanaServiceImpl implements AduanaService {
	
	private static final Log log = LogFactory.getLog(AduanaServiceImpl.class);
	private TabDepDAO tabDepDAO;
	private CircunOceDAO circunOceDAO;
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private T6620RequerimDAO t6620RequerimDAO;
	private DataCatalogoDAO dataCatalogoDAO;

	@Override
	public Map<String, Object> verificarUoAduana(String codUO) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.verificarUoAduana");
		Map<String, Object> mapRpta = null;
		try {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("codigo_par", codUO);
			DataSourceContextHolder.setKeyDataSource("c");
			mapRpta = tabDepDAO.getDepAduanaByCodUo(mapParam);
			if (mapRpta != null) {
				mapParam = new HashMap<String, Object>();
				mapParam.put("cod_datacat_par", mapRpta.get("ADUANA").toString().trim());
				DataSourceContextHolder.setKeyDataSource("c");
				Map<String, Object> mapCata = dataCatalogoDAO.getDepAduanaByCod(mapParam);
				mapRpta.put("DEPEN2", mapCata.get("DES_CORTA").toString().trim());
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.verificarUoAduana");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.verificarUoAduana");
			NDC.pop();
			NDC.remove();
		}
		if (log.isDebugEnabled()) log.debug("AduanaServiceImpl.verificarUoAduana.mapRpta: " + mapRpta);
		return mapRpta;
	}

	@Override
	public DdpBean obtenerAgenteHabilitado(String numRuc, String codDep) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.obtenerAgenteHabilitado");
		DdpBean agente = null;
		try {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("num_ruc_par", numRuc);
			mapParam.put("cod_dep_par", codDep);
			DataSourceContextHolder.setKeyDataSource("c");
			agente = circunOceDAO.getAgenteHabilitado(mapParam);
			if (log.isDebugEnabled()) log.debug("AduanaServiceImpl.obtenerAgenteHabilitado.agente: " + agente);
			if (Utils.isEmpty(agente)) {
				agente = new DdpBean();
				agente.setDesRazonSocial(ValidaConstantes.CADENA_VACIA);
				agente.setNumRuc(numRuc);
				agente.setCodDependencia("999");
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.obtenerAgenteHabilitado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.obtenerAgenteHabilitado");
			NDC.pop();
			NDC.remove();
		}
		return agente;
	}

	@Override
	public String validarNumDocSolDAM(String numExpVir, String numDam, String numSol) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.validarNumDocSolDAM");
		String lastSol = null;
		String msjErr = "ERROR NO CONTROLADO";
		try {
			log.debug("numDam: "+numDam);
			log.debug("numSol: "+numSol);
			if (numSol.length() != ValidaConstantes.DAM_SIZE_SOL_RECTIF) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_41.replace("{tam_doc}", ""+ValidaConstantes.DAM_SIZE_SOL_RECTIF);
			}
			if (!numDam.equals(numSol.substring(0, ValidaConstantes.DAM_SIZE_EXP_DAM))) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_34.replace("{num_doc}", numSol);
			}
			
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedienteVirtual", numExpVir);
			mapParam.put("codTipDoc", ValidaConstantes.DAM_TIP_DOC_SOL_RECTIF);
			DataSourceContextHolder.setKeyDataSource("c");
			List<T6613DocExpVirtBean> listDoc = t6613DocExpVirtDAO.listar(mapParam);
			if (listDoc == null || listDoc.isEmpty()) {
				lastSol = numDam + "0";
			} else {
				lastSol = listDoc.get(listDoc.size() - 1).getNumDoc().trim();
			}
			if (this.validarNumeroPosterior(lastSol, numSol)) {
				lastSol = null;
			}
			if (lastSol == null) {
				msjErr = null;
			} else {
				msjErr = AvisoConstantes.MSJ_MODULO_03_01_EX_38.replace("{sol_ant}", lastSol);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.validarNumDocSolDAM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.validarNumDocSolDAM");
			NDC.pop();
			NDC.remove();
		}
		return msjErr;
	}

	@Override
	public String validarNumDocReqDAM(String numExpVir, String numDam, String numReq) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.validarNumDocReqDAM");
		String lastReq = null;
		String msjErr = "ERROR NO CONTROLADO";
		try {
			if (numReq.length() != ValidaConstantes.DAM_SIZE_REQ_RECTIF) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_41.replace("{tam_doc}", ""+ValidaConstantes.DAM_SIZE_REQ_RECTIF);
			}
			if (!numDam.equals(numReq.substring(0, ValidaConstantes.DAM_SIZE_EXP_DAM))) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_34.replace("{num_doc}", numReq);
			}
			
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("numExpeDv", numExpVir);
			mapParam.put("codTipDoc", ValidaConstantes.DAM_TIP_DOC_SOL_RECTIF);
			mapParam.put("numDoc", numReq.substring(0, ValidaConstantes.DAM_SIZE_SOL_RECTIF));
			DataSourceContextHolder.setKeyDataSource("c");
			T6613DocExpVirtBean docSol = t6613DocExpVirtDAO.obtenerDocOrigen(mapParam);
			if (docSol == null) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_39;
			}

			mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedienteVirtual", numExpVir);
			mapParam.put("codTipDoc", ValidaConstantes.DAM_TIP_DOC_REQ_RECTIF);
			mapParam.put("numCorDocRel", docSol.getNumCorDoc());
			DataSourceContextHolder.setKeyDataSource("c");
			List<T6613DocExpVirtBean> listDoc = t6613DocExpVirtDAO.listar(mapParam);
			if (listDoc == null || listDoc.isEmpty()) {
				lastReq = docSol.getNumDoc().trim() + "0";
			} else {
				lastReq = listDoc.get(listDoc.size() - 1).getNumDoc().trim();
			}
			if (this.validarNumeroPosterior(lastReq, numReq)){
				lastReq = null;
			}
			if (lastReq == null) {
				msjErr = null;
			} else {
				msjErr = AvisoConstantes.MSJ_MODULO_03_01_EX_40.replace("{req_ant}", lastReq);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.validarNumDocReqDAM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.validarNumDocReqDAM");
			NDC.pop();
			NDC.remove();
		}
		return msjErr;
	}

	@Override
	public String validarNumReqDAM(String numExpVir, String numDam, String numReq) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.validarNumReqDAM");
		String lastReq = null;
		String msjErr = "ERROR NO CONTROLADO";
		try {
			if (numReq.length() != ValidaConstantes.DAM_SIZE_REQ_RECTIF) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_41.replace("{tam_doc}", ""+ValidaConstantes.DAM_SIZE_REQ_RECTIF);
			}
			if (!numDam.equals(numReq.substring(0, ValidaConstantes.DAM_SIZE_EXP_DAM))) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_34.replace("{num_doc}", numReq);
			}
			
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("numExpeDv", numExpVir);
			mapParam.put("codTipDoc", ValidaConstantes.DAM_TIP_DOC_SOL_RECTIF);
			mapParam.put("numDoc", numReq.substring(0, ValidaConstantes.DAM_SIZE_SOL_RECTIF));
			DataSourceContextHolder.setKeyDataSource("c");
			T6613DocExpVirtBean docSol = t6613DocExpVirtDAO.obtenerDocOrigen(mapParam);
			if (docSol == null) {
				return AvisoConstantes.MSJ_MODULO_03_01_EX_39;
			}

			mapParam = new HashMap<String, Object>();
			mapParam.put("numExpedienteVirtual", numExpVir);
			mapParam.put("codEstReqDifEli", ValidaConstantes.IND_ESTADO_REQUERIMIENTO_ELIMINADO);
			DataSourceContextHolder.setKeyDataSource("c");
			List<T6620RequerimBean> listReq = t6620RequerimDAO.listar(mapParam);
			if (listReq == null || listReq.isEmpty()) {
				lastReq = docSol.getNumDoc().trim() + "0";
			} else {
				lastReq = listReq.get(listReq.size() - 1).getNumRequerimOrigen().trim();
			}
			if (this.validarNumeroPosterior(lastReq, numReq)){
				lastReq = null;
			}
			if (lastReq == null) {
				msjErr = null;
			} else {
				msjErr = AvisoConstantes.MSJ_MODULO_03_01_EX_40.replace("{req_ant}", lastReq);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.validarNumReqDAM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.validarNumReqDAM");
			NDC.pop();
			NDC.remove();
		}
		return msjErr;
	}

	@Override
	public T6613DocExpVirtBean buscarDocNotificado(String numExpVir, String numDoc) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.buscarDocNotificado");
		T6613DocExpVirtBean docNotif = null;
		try {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("numExpeDv", numExpVir);
			mapParam.put("numDoc", numDoc);
			mapParam.put("difGrupo", ValidaConstantes.GRUPO_TIP_DOC_CONST + "%");
			DataSourceContextHolder.setKeyDataSource("c");
			docNotif = t6613DocExpVirtDAO.obtenerDocOrigen(mapParam);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.buscarDocNotificado");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.buscarDocNotificado");
			NDC.pop();
			NDC.remove();
		}
		return docNotif;
	}

	@Override
	public T6613DocExpVirtBean buscarDocSolRectifDAM(String numExpVir, String numDoc) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - AduanaServiceImpl.buscarDocSolRectifDAM");
		T6613DocExpVirtBean docSol = null;
		try {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("numExpeDv", numExpVir);
			mapParam.put("codTipDoc", ValidaConstantes.DAM_TIP_DOC_SOL_RECTIF);
			mapParam.put("numDoc", numDoc);
			DataSourceContextHolder.setKeyDataSource("c");
			docSol = t6613DocExpVirtDAO.obtenerDocOrigen(mapParam);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - AduanaServiceImpl.buscarDocSolRectifDAM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - AduanaServiceImpl.buscarDocSolRectifDAM");
			NDC.pop();
			NDC.remove();
		}
		return docSol;
	}

	@Override
	public Map<String, Object> obtenerDependencias() throws Exception {
		Map<String, Object> mapRpta = new HashMap<String, Object>();
		for (Map<String, Object> mapDep : dataCatalogoDAO.listarDepAduana()) {
			mapRpta.put(mapDep.get("COD_DATACAT").toString().trim(), mapDep.get("DES_CORTA").toString().trim());
		}
		return mapRpta;
	}
	
	private Boolean validarNumeroPosterior(String numDocActual, String numDocSig) {
		Boolean rpta = false;
		String strUltNumA = numDocActual.substring(numDocActual.length() - 1, numDocActual.length());
		String strUltNumS = numDocSig.substring(numDocSig.length() - 1, numDocSig.length());;
		if (ValidaConstantes.NUM_NUEVE.equals(strUltNumA) && ValidaConstantes.LETRA_A.equals(strUltNumS)) {
			rpta = true;
		} else if (strUltNumA.matches("[A-Z]")) {
			char c = strUltNumA.charAt(0);
			c++;
			if (c == strUltNumS.charAt(0)) {
				rpta = true;
			}
		} else if (strUltNumA.matches("[0-9]")) {
			int ultNumA = Integer.parseInt(strUltNumA);
			int ultNumS = 0;
			if (strUltNumS.matches("[0-9]")) {
				ultNumS = Integer.parseInt(strUltNumS);
			}
			if (ultNumA + 1 == ultNumS) {
				rpta = true;
			}
		} else {
			rpta = false;
		}
		return rpta;
	}

	public void setTabDepDAO(TabDepDAO tabDepDAO) {
		this.tabDepDAO = tabDepDAO;
	}

	public void setCircunOceDAO(CircunOceDAO circunOceDAO) {
		this.circunOceDAO = circunOceDAO;
	}

	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}

	public void setT6620RequerimDAO(T6620RequerimDAO t6620RequerimDAO) {
		this.t6620RequerimDAO = t6620RequerimDAO;
	}

	public void setDataCatalogoDAO(DataCatalogoDAO dataCatalogoDAO) {
		this.dataCatalogoDAO = dataCatalogoDAO;
	}

}
