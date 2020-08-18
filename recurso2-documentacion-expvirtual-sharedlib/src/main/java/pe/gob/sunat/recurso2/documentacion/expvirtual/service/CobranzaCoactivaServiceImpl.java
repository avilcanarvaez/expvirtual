package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.AccExp;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.AccExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class CobranzaCoactivaServiceImpl implements CobranzaCoactivaService {
	
	private static final Log log = LogFactory.getLog(CobranzaCoactivaServiceImpl.class);
	private AccExpDAO accExpDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;

	@Override
	public List<Map<String, String>> obtenerExpedientesAcumulados(Map<String, Object> mapParamExp) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - CobranzaCoactivaServiceImpl.obtenerExpedientesAcumulados");
		List<Map<String, String>> listMapAcum = new ArrayList<Map<String, String>>();
		Map<String, String> mapExpAcu = null;
		int numAcu = 0;
		int numDesAcu = 0;
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("num_rc_par", mapParamExp.get("numRc"));
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + mapParamExp.get("codDep"));
		List<AccExp> listAccExp = accExpDAO.listar(mapParam);
		List<Map<String, Object>> listT6614 = new ArrayList<Map<String, Object>>();
		if (!listAccExp.isEmpty()) {
			mapParam = new HashMap<String, Object>();
			if (ValidaConstantes.FILTRO_UNO.equals(listAccExp.get(0).getInd_acc_exp())) {//SI SE TRATA DE UNA ACUMULACIÓN
				mapParam.put("listExpOri", listAccExp);
			} else {//CASO CONTRARIO ES UNA DES ACUMULACIÓN
				mapParam.put("numAcu", listAccExp.get(0).getNum_exp_des().trim());
			}
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listT6614 = t6614ExpVirtualDAO.listarAcumuladosExpVirtual(mapParam);
		}
		for (AccExp accExp : listAccExp) {
			for (Map<String, Object> mapT6614 : listT6614) {
				if (accExp.getNum_exp_ori().trim().equals(mapT6614.get("numExpedo").toString().trim())) {
					mapExpAcu = new HashMap<String, String>();
					mapExpAcu.put("numExpVir", mapT6614.get("numExpedv").toString().trim());
					if (accExp.getInd_acc_exp().equals(ValidaConstantes.FILTRO_UNO)) {
						mapExpAcu.put("indAcu", ValidaConstantes.IND_ACUMULADOR_ACUMULADO);
						mapExpAcu.put("numAcu", accExp.getNum_exp_des());
						numAcu++;
					} else {
						mapExpAcu.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE);
						mapExpAcu.put("numAcu", ValidaConstantes.FILTRO_CERO);
						numDesAcu++;
					}
					listMapAcum.add(mapExpAcu);
					break;
				}
			}
		}
		if (numAcu > 0) {
			mapExpAcu = new HashMap<String, String>();
			mapExpAcu.put("numExpVir", mapParamExp.get("numExpVir").toString());
			mapExpAcu.put("indAcu", ValidaConstantes.IND_ACUMULADOR_ACUMULADOR);
			mapExpAcu.put("numAcu", ValidaConstantes.FILTRO_CERO);
			listMapAcum.add(mapExpAcu);
		} else if (numDesAcu == listT6614.size()) {
			mapExpAcu = new HashMap<String, String>();
			mapExpAcu.put("numExpVir", mapParamExp.get("numExpVir").toString());
			mapExpAcu.put("indAcu", ValidaConstantes.IND_ACUMULADOR_INDEPENDIENTE);
			mapExpAcu.put("numAcu", ValidaConstantes.FILTRO_CERO);
			listMapAcum.add(mapExpAcu);
		}
		
		if (log.isDebugEnabled()) log.debug("Fin - CobranzaCoactivaServiceImpl.obtenerExpedientesAcumulados");
		return listMapAcum;
	}

	public void setAccExpDAO(AccExpDAO accExpDAO) {
		this.accExpDAO = accExpDAO;
	}

	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}



}
