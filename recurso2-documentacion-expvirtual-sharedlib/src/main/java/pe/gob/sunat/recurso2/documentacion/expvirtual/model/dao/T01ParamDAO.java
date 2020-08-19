package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;

public interface T01ParamDAO {
	
	public List<T01ParamBean> listar(Map<String, Object> parametros);
	public T01ParamBean obtener(Map<String, Object> parametros);
	public List<String> listarDocCierreByTipExp(Map<String, Object> parametros);//[oachahuic][PAS20165E210400270]
	public List<String> listarTipDocNotif(Map<String, Object> parametros);//[oachahuic][PAS20165E210400270]
	public List<T01ParamBean> listarEquivalencia(Map<String, Object> parametros);//[oachahuic][PAS20181U210400241]
	public Map<String, Object> obtenerTipValidacion(Map<String, Object> parametros);//[oachahuic][PAS20191U210500076]
}