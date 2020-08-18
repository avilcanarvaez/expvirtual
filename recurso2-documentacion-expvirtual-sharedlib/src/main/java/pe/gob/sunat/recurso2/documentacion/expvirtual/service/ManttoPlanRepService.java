package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6612DetPlantiBean;


public interface ManttoPlanRepService {

	public List<T6611CabPlantiBean> listarPlantillas(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public Map<String, Object> darBajaPlantilla(Map<String, Object> parametros) throws Exception;
	
	public List<T6611CabPlantiBean> listarPlantillasPorExpe (Map<String, Object> parametros) throws Exception;
	
	public String registrarPlantilla(HashMap<String, Object> mapParametros) throws Exception;

	public List<T6612DetPlantiBean> listarDetPlantillasPorNumPlantilla(Map<String, Object> parametros) throws Exception;

	public String modificarPlantilla(HashMap<String, Object> mapParametros) throws Exception;
}
