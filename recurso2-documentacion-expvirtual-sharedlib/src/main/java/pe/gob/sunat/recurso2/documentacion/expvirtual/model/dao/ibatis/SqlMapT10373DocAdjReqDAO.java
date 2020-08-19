package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10373DocAdjReqDAO;

public class SqlMapT10373DocAdjReqDAO extends SqlMapClientDaoSupport implements T10373DocAdjReqDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarArcDocPorExp(Map<String, Object> parametros) {
		
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T10373DocAdjReq.listarArcDocPorExp", parametros);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public  List<Map<String, Object>> listar(Map<String, Object> parametros) {
		
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T10373DocAdjReq.listar", parametros);
		
	}
	
	//[PAS20191U210500291]: JMC - INI
	public Integer contarArchivos (Map<String, Object> parametros) {
		
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("T10373DocAdjReq.selectCantidadArch", parametros);
			
		return cantidad;
	}
	
	@SuppressWarnings("unchecked")
	public List<T10373DocAdjReqBean> obtenerListaDocAdj (Map<String, Object> parametros) {
		
		return getSqlMapClientTemplate().queryForList("T10373DocAdjReq.listarDocAdj", parametros);
	}
	
	@Override
	public void insertar(T10373DocAdjReqBean t10373DocAdjReqBean) {
		
		getSqlMapClientTemplate().insert("T10373DocAdjReq.insertar", t10373DocAdjReqBean);
	}
	
	public void update(Map<String, Object> parametros) {
		getSqlMapClientTemplate().update("T10373DocAdjReq.update", parametros);
	}
	//[PAS20191U210500291]: JMC - FIN
}