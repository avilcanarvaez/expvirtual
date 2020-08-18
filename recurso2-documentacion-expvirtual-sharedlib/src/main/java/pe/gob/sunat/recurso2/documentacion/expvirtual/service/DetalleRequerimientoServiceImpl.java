package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.RecAudDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10372DetRequerimDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10373DocAdjReqDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class DetalleRequerimientoServiceImpl implements DetalleRequerimientoService {
	private static final Log log = LogFactory.getLog(DetalleRequerimientoServiceImpl.class);
		
	private T10372DetRequerimDAO t10372DetRequerimDAO;
	private T10373DocAdjReqDAO t10373DocAdjReqDAO;
    private RecAudDAO recAudDAO;
    private T02PerdpDAO t02PerdpDAO;
    
	@Override
	
	public List<T10372DetRequerimBean> obtenerListaItems(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		List<T10372DetRequerimBean> lstT10372DetRequerimBean = new ArrayList<T10372DetRequerimBean>();
		List<T10372DetRequerimBean> lstT10372DetRequerimBeanTemp = new ArrayList<T10372DetRequerimBean>();
		StringBuilder desItem = new StringBuilder(); 

		Integer cantArchivos = 0;
		
		if (log.isDebugEnabled()) log.debug("Inicio - DetalleRequerimientoServiceImpl.obtenerListaItems");
		
		try {			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			lstT10372DetRequerimBeanTemp = t10372DetRequerimDAO.listarItems(mapParametrosBusqueda);
			
			int i = 0;
			int numItem = 0;
			int numItemaux = 0;
			boolean flag = true;
			//[PAS20201U210500082] HHC - INI	
			while (i < lstT10372DetRequerimBeanTemp.size()) {
//				 numItemaux= lstT10372DetRequerimBeanTemp.get(0).getItem();
//				 numItem=lstT10372DetRequerimBeanTemp.get(i).getItem();
				if (numItem == lstT10372DetRequerimBeanTemp.get(i).getItem()){
					log.debug("numItem1: "+numItem);
//					desItem = desItem.append(lstT10372DetRequerimBeanTemp.get(i).getTextitem().trim());
//					numItem = lstT10372DetRequerimBeanTemp.get(i).getItem();
//					flag = false;
//					i = i + 1;
				} else {
					numItem = lstT10372DetRequerimBeanTemp.get(i).getItem();
					lstT10372DetRequerimBean.add(lstT10372DetRequerimBeanTemp.get(i));
//					log.debug("numItem2: "+numItem);
//					flag = true;
//					i = i - 1;
				}
				i = i + 1;
//				if (flag){
//					lstT10372DetRequerimBeanTemp.get(i).setTextitem(desItem.toString());					
//					lstT10372DetRequerimBean.add(lstT10372DetRequerimBeanTemp.get(i));
//					desItem = new StringBuilder();	
//					i = i + 1;
//					numItem = lstT10372DetRequerimBeanTemp.get(i).getItem();
//					log.debug("numItem: "+numItem+" - lstT10372DetRequerimBean: "+lstT10372DetRequerimBean);	
//				}				
			}
			
//			if (lstT10372DetRequerimBeanTemp.size() > 0) {
//				lstT10372DetRequerimBeanTemp.get(i-1).setTextitem(desItem.toString());				
//				lstT10372DetRequerimBean.add(lstT10372DetRequerimBeanTemp.get(i-1));
//			}			
			//[PAS20201U210500082] HHC - FIN
			for (T10372DetRequerimBean t10372DetRequerimBean : lstT10372DetRequerimBean) {
				mapParametrosBusqueda.put("numItem", t10372DetRequerimBean.getItem());
				
				if(t10372DetRequerimBean.getTextitem().length() > 40) {
					t10372DetRequerimBean.setDesItemCorta(t10372DetRequerimBean.getTextitem().substring(0,40).trim().concat("..."));
				} else {
					t10372DetRequerimBean.setDesItemCorta(t10372DetRequerimBean.getTextitem().trim());
				}
				
				log.debug("mapParametrosBusqueda: "+mapParametrosBusqueda+" - lstT10372DetRequerimBean: "+t10372DetRequerimBean.getItem());
				cantArchivos = t10373DocAdjReqDAO.contarArchivos(mapParametrosBusqueda);
				
				if (cantArchivos == 0){
					t10372DetRequerimBean.setCantArchivos(0);
				} else {
					t10372DetRequerimBean.setCantArchivos(cantArchivos);
				}
				
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DetalleRequerimientoServiceImpl.obtenerListaItems");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DetalleRequerimientoServiceImpl.obtenerListaItems");
			
			NDC.pop();
			NDC.remove();
		}
		
		return lstT10372DetRequerimBean;
	}
	
	public Map<String, Object> obtenerDatosSupervisor(Map<String, Object> mapParametrosBusqueda) throws Exception {
		
		if (log.isDebugEnabled()) log.debug("Inicio - DetalleRequerimientoServiceImpl.obtenerDatosSupervisor");
		Map<String, Object> datosSupervisor = new HashMap<String, Object>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		try {
			
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT + mapParametrosBusqueda.get("codDependencia").toString().trim());
			
			String codSupervisor = recAudDAO.obtenerCodSupervisor(mapParametrosBusqueda);
			
			parametros = new HashMap<String, Object>();
			parametros.put("codPersona", codSupervisor.trim());
			
			String codUUOO = t02PerdpDAO.obtenerUUOOSupervisor(parametros);
			
			datosSupervisor.put("codSupervisor", !Utils.isEmpty(codSupervisor)?codSupervisor.trim():ValidaConstantes.CADENA_VACIA);
			datosSupervisor.put("UuOOSupervisor", !Utils.isEmpty(codUUOO)?codUUOO.trim():ValidaConstantes.CADENA_VACIA);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - DetalleRequerimientoServiceImpl.obtenerDatosSupervisor");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - DetalleRequerimientoServiceImpl.obtenerDatosSupervisor");
			
			NDC.pop();
			NDC.remove();
		}
		
		return datosSupervisor;		
	}
			
	/*Sets*/

	public void setT10372DetRequerimDAO(T10372DetRequerimDAO t10372DetRequerimDAO) {
		this.t10372DetRequerimDAO = t10372DetRequerimDAO;
	}

	public void setT10373DocAdjReqDAO(T10373DocAdjReqDAO t10373DocAdjReqDAO) {
		this.t10373DocAdjReqDAO = t10373DocAdjReqDAO;
	}
	
	public void setRecAudDAO(RecAudDAO recAudDAO) {
		this.recAudDAO = recAudDAO;
	}

	public void setT02PerdpDAO(T02PerdpDAO t02PerdpDAO) {
		this.t02PerdpDAO = t02PerdpDAO;
	}
}