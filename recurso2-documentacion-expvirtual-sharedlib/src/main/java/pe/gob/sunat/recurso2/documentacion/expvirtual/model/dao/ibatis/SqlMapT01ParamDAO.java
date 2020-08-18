package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;

public class SqlMapT01ParamDAO extends SqlMapClientDaoSupport implements T01ParamDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T01ParamBean> listar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//return (List<T01ParamBean>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForList("T01param.listar", parametros);
		
		//DESPUES:
		return (List<T01ParamBean>) this.getSqlMapClientTemplate().queryForList("T01param.listar", parametros);
		
	}
	
	@Override
	public T01ParamBean obtener(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//return (T01ParamBean) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("T01param.obtener", parametros);
		
		//DESPUES:
		return (T01ParamBean) this.getSqlMapClientTemplate().queryForObject("T01param.obtener", parametros);
		//return (T01ParamBean) this.getSqlMapClientTemplate().queryForObject("t01param.obtener", parametros);
		
	}
	//Inicio - [oachahuic][PAS20165E210400270]
	@SuppressWarnings("unchecked")
	public List<String> listarDocCierreByTipExp(Map<String, Object> parametros) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList("T01param.listarDocCierreByTipExp", parametros);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarTipDocNotif(Map<String, Object> parametros) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList("T01param.listarTipDocNotif", parametros);
	}
	//Fin - [oachahuic][PAS20165E210400270]
	
	//Inicio - [oachahuic][PAS20181U210400241]
	@SuppressWarnings("unchecked")
	public List<T01ParamBean> listarEquivalencia(Map<String, Object> parametros) {	
		return getSqlMapClientTemplate().queryForList("T01param.listarEquivalencia", parametros);		
	}
	//Fin - [oachahuic][PAS20181U210400241]

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> obtenerTipValidacion(Map<String, Object> parametros) {
		return (Map<String, Object>) this.getSqlMapClientTemplate().queryForObject("T01param.obtenerTipValidacion", parametros);
	}
}